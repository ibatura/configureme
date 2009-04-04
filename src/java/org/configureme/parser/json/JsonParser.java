package org.configureme.parser.json;

import java.util.ArrayList;
import java.util.List;

import org.configureme.Environment;
import org.configureme.environments.DynamicEnvironment;
import org.configureme.parser.ConfigurationParser;
import org.configureme.parser.ConfigurationParserException;
import org.configureme.parser.ParsedArtefact;
import org.configureme.parser.ParsedAttribute;
import org.json.JSONException;
import org.json.JSONObject;


public class JsonParser implements ConfigurationParser {

	@Override
	public ParsedArtefact parseArtefact(String name, String content) throws ConfigurationParserException {
		
		try {
			JSONObject j = new JSONObject(content);
			ParsedArtefact pa = new ParsedArtefact(name);
			
			DynamicEnvironment env = new DynamicEnvironment();
			
			for (String key : JSONObject.getNames(j)) {
				
				List<ParsedAttribute> attList = parse(key, j, env);
				if (attList!=null){
					for (ParsedAttribute att : attList){
						pa.addAttribute(att);
					}
				}
			}
			
			System.out.println("Returning: "+pa);
			return pa;
			
		} catch (JSONException e) {
			throw new ConfigurationParserException("JSON Error", e); 
		}
	}
	
	private List<ParsedAttribute> parse(String key, JSONObject root, DynamicEnvironment environment) throws JSONException{
		List<ParsedAttribute> ret = new ArrayList<ParsedAttribute>();
		
		System.out.println("Parsing "+key+" in "+environment);
		
		Object value = root.get(key);
		// an object value means a change in environment, let's see what it is
		if (value instanceof JSONObject){
			environment.extendThis(key);
			JSONObject inc = (JSONObject) value;
			for (String subKey : JSONObject.getNames(inc)){
				List<ParsedAttribute> subAttributes = parse(subKey, inc, environment);
				if (subAttributes!=null)
					ret.addAll(subAttributes);
			}
			environment.reduceThis();
		}
		// other cases can just be set as strings
		else {
			
			ParsedAttribute at = new ParsedAttribute();
			at.setName(key);
			at.setValue(root.getString(key));//.getString(key));
			at.setEnvironment((Environment)environment.clone());
			ret.add(at);
			
		}
		return ret;
	}
	
}