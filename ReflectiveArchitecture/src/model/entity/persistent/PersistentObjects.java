package model.entity.persistent;

import java.util.HashMap;
import java.util.Set;

import model.entity.dynamic.DynamicEntity;

public class PersistentObjects {
	
	private static HashMap<String, DynamicEntity> persistentObjects = new HashMap<>();
	
	private PersistentObjects(){
		
	}
	
	public static DynamicEntity getEntity(String entityName){
		DynamicEntity entity = persistentObjects.get(entityName);
		return entity;
	}

	public static void setPersistentObjects(
			HashMap<String, DynamicEntity> persistentObjects) {
		PersistentObjects.persistentObjects = persistentObjects;
	}

	public static HashMap<String, DynamicEntity> getPersistentObjects() {
		return persistentObjects;
	}
	
	public static Set<String> getObjectNames(){
		Set<String> names = persistentObjects.keySet();		
		return names;
	}
	
	public static void clearPersistentObjects(){
		persistentObjects.clear();
	}
	

}
