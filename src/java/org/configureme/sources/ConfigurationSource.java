package org.configureme.sources;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.configureme.repository.ConfigurationRepository;

import net.anotheria.util.NumberUtils;

/**
 * Represents a loaded configuration source for example a file. Doesn't contain the content of the file, only metadata is included. The ConfigurationSource object is a surogate which is used to execute functions
 * which are semantically ment to be executed on the configuration source itself, like registering for a change event etc.
 * @author lrosenberg
 */
public class ConfigurationSource {
	/**
	 * The key for the underlying source
	 */
	private ConfigurationSourceKey key;
	/**
	 * A list of listeners
	 */
	private List<ConfigurationSourceListener> listeners;
	/**
	 * Last detected change timestamp
	 */
	private long lastChangeTimestamp;
	
	/**
	 * Logger.
	 */
	private static Logger log = Logger.getLogger(ConfigurationSource.class);
	
	/**
	 * Creates a new configuration source
	 * @param aKey
	 */
	public ConfigurationSource(ConfigurationSourceKey aKey){
		key = aKey;
		listeners = new ArrayList<ConfigurationSourceListener>(); 
		lastChangeTimestamp = System.currentTimeMillis();
		listeners.add(ConfigurationRepository.INSTANCE);
	}
	
	/**
	 * Adds a listener to this source. 
	 * @param listener a listener to add
	 */
	public void addListener(ConfigurationSourceListener listener){
		synchronized(listeners){
			listeners.add(listener);
		}
	}
	/**
	 * Removes the listener from this source
	 * @param listener
	 */
	public void removeListener(ConfigurationSourceListener listener){
		synchronized(listeners){
			listeners.remove(listener);
		}
	}
	
	@Override public String toString(){
		return "ConfigurationSource "+key+", listeners: "+listeners.size()+", "+NumberUtils.makeISO8601TimestampString(getLastChangeTimestamp());
	}

	/**
	 * Return the last change timestamp of this source in millis
	 * @return
	 */
	public long getLastChangeTimestamp() {
		return lastChangeTimestamp;
		
	}

	/**
	 * Returns the config key of this source
	 * @return
	 */
	public ConfigurationSourceKey getKey(){
		return key;
	}
	
	/**
	 * Returns true if this source's change timestamp is older as the given timestamp
	 * @param sourceChangeTimestamp
	 * @return
	 */
	public boolean isOlderAs(long sourceChangeTimestamp){
		return lastChangeTimestamp < sourceChangeTimestamp;
	}
	
	/**
	 * Called by the ConfigurationSourceRegistry if a change in the underlying source is detected.
	 * @param timestamp
	 */
	public void fireUpdateEvent(long timestamp){
		synchronized(listeners){
			for (ConfigurationSourceListener listener : listeners){
				try{
					log.debug("Calling configurationSourceUpdated on "+listener);
					listener.configurationSourceUpdated(this);
				}catch(Exception e){
					log.error("Error in notifying configuration source listener:"+listener, e);
				}
			}
		}
		lastChangeTimestamp = timestamp;
	}
}
