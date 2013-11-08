package model.entity.dynamic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

public class DynamicEntity {
	private String name;
    private HashMap<String, DynamicAttribute> attributes;
    //private Properties sqlTypeNames;
        
    public DynamicEntity(String entityName, HashMap<String, DynamicAttribute> attributes/*, Properties sqlTypeNames*/){        
        this.name = entityName;
        this.attributes = attributes;   
        //this.sqlTypeNames = sqlTypeNames;
    }    
    
    public String getName() {
        return name;
    }
    
    public Class<?> getAttributeClass(String attributeName) {
        return this.attributes.get(attributeName).getClassName();       
    }
    
    public Object getAttributeValue(String attributeName) {
        return this.attributes.get(attributeName).getValue();     
    }
    
    public void setAttributeValue(String attributeName, Object objectValue) {    
           this.attributes.get(attributeName).setValue(objectValue);       
    }  
	
	/*public String getSqlTypeName(String attributeName){
		return this.sqlTypeNames.getProperty(attributeName);
	}*/

	public Set<String> getKeySet() {
        Set<String> keySet = this.attributes.keySet();
        return keySet;
    }
	
	public LinkedList<String> listEntityAttributes(){
		LinkedList<String> list = new LinkedList<>();
		Set<String> set = this.getKeySet();
		for(String item : set){
			list.add(item);
		}
		return list;
	}

	public HashMap<String, DynamicAttribute> getAttributes() {
		return attributes;
	}	
    
}

