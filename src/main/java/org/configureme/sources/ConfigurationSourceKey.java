package org.configureme.sources;

/**
 * A configuration source key is the unique identification of a configuration source. This class is also used as key in maps.
 * @author lrosenberg
 */
public class ConfigurationSourceKey {
	/**
	 * The type of the source.
	 */
	public enum Type {
		/**
		 * Configuration comes from a file.
		 */
		FILE,
		/**
		 * Configuration comes from a test fixture.
		 */
		FIXTURE,

		/**
		 * Configuration comes from a REST API.
		 */
		REST,

		/**
		 * Configuration comes from a repository.
		 */
		REPOSITORY
	}

	/**
	 * The format of the file. The format decides which parser will be used to parse the file.
	 * @author lrosenberg
	 */
	public enum Format{
		/**
		 * JSON format.
		 */
		JSON,
		/**
		 * Properties format.
		 */
		PROPERTIES,
		/**
		 * XML format.
		 */
		XML;
		
		/**
		 * Returns the extension under which file would be stored. 
		 * @return the extension under which file would be stored
		 */
		public String getExtension(){
			return toString().toLowerCase();
		}
	}
	
	/**
	 * The type of the source.
	 */
	private Type type;
	/**
	 * The format of the source.
	 */
	private Format format;
	/**
	 * The name of the source.
	 */
	private String name;
	/**
	 * Externally provided url for remote configuration repository.
	 */
	private String remoteConfigurationRepositoryUrl;
	
	/**
	 * Creates a new key.
	 */
	public ConfigurationSourceKey(){
		
	}
	
	/**
	 * Creates a new key preset to given parameters.
	 * @param aType the type of the source
	 * @param aFormat the format of the source
	 * @param aName the name of the source
	 */
	public ConfigurationSourceKey(Type aType, Format aFormat, String aName){
		type = aType;
		format = aFormat;
		name = aName;
	}

	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public void setTypeIfNotDefault(Type defType, Type toChange){
		if(defType != Type.FILE){
			setType(defType);
		} else {
			setType(toChange);
		}
	}
	public Format getFormat() {
		return format;
	}
	public void setFormat(Format format) {
		this.format = format;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemoteConfigurationRepositoryUrl() {
		return remoteConfigurationRepositoryUrl;
	}
	public void setRemoteConfigurationRepositoryUrl(String remoteConfigurationRepositoryUrl) {
		this.remoteConfigurationRepositoryUrl = remoteConfigurationRepositoryUrl;
	}

	@Override public String toString(){
		return getType().toString()+"::"+getName()+"::"+getFormat();
	}
	
	@Override public boolean equals(Object o){
		return o instanceof ConfigurationSourceKey ? 
				((ConfigurationSourceKey)o).type == type &&
				((ConfigurationSourceKey)o).format == format &&
				((ConfigurationSourceKey)o).name.equals(name) : 
					false;
	}
	
	@Override public int hashCode(){
		int result = 17;
		result = 31 * result + type.hashCode();
		result = 31 * result + format.hashCode();
		result = 31 * result + name.hashCode();
		return result;
	}
	
	/**
	 * Creates a new configuration source key for property files.
	 * @param name name of the property file.
	 * @return a new configuration source key instance for property files
	 */
	public static final ConfigurationSourceKey propertyFile(String name){
		return new ConfigurationSourceKey(Type.FILE, Format.PROPERTIES, name);
	}

	/**
	 * Creates a new configuration source key for xml files.
	 * @param name name of the xml file.
	 * @return a new configuration source key instance for xml files
	 */
	public static final ConfigurationSourceKey xmlFile(String name){
		return new ConfigurationSourceKey(Type.FILE, Format.XML, name);
	}

	/**
	 * Creates a new configuration source key for json files.
	 * @param name name of the json file.
	 * @return a new configuration source key instance for json files
	 */
	public static final ConfigurationSourceKey jsonFile(String name){
		return new ConfigurationSourceKey(Type.FILE, Format.JSON, name);
	}

}
