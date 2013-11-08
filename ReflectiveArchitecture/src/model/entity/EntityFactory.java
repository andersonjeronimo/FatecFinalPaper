package model.entity;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import model.database.metadata.MetadataAccess;
import model.entity.dynamic.DynamicAttribute;
import model.entity.dynamic.DynamicEntity;

public class EntityFactory {
	
	private static MetadataAccess metadata = new MetadataAccess();
	
	private EntityFactory() {
        //construtor privado
    }

	 public static Properties getObjectAttributes(String entityName) {
	    	Properties objectAttributes = new Properties();
	    		        
	        try {
	            objectAttributes = metadata.getObjectAttributes(entityName);
	        } catch (Exception e) {
	        }

	        return objectAttributes;
	    }
	
	 public static Properties getTableAttributes(String entityName) {
	    	Properties tableAttributes = new Properties();
	    	        
	        try {
	        	tableAttributes = metadata.getTableAttributes(entityName);
	        } catch (Exception e) {
	        }

	        return tableAttributes;
	    }
	
	public static DynamicEntity getEntityInstance(String tableName) {
    	DynamicEntity instance = null;
        HashMap<String, DynamicAttribute> attributes = new HashMap<String, DynamicAttribute>();

        try {
            Properties objProp = metadata.getObjectAttributes(tableName);
            //Properties tabProp = metadata.getTableAttributes(entityName);
            String attributeName;
            String javaClassName;             
            Enumeration<?> list = objProp.propertyNames();            

            while (list.hasMoreElements()) {
                attributeName = (String) list.nextElement();
                System.out.println(attributeName);
                javaClassName = objProp.getProperty(attributeName); 
                System.out.println(Class.forName(javaClassName));
                DynamicAttribute attribute = new DynamicAttribute(attributeName, Class.forName(javaClassName));
                attributes.put(attributeName, attribute);
            }
            instance = new DynamicEntity(tableName, attributes/*, tabProp*/);

        } catch (Exception e) {
        }

        return instance;
    }
}
