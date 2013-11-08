package controller;

import java.lang.reflect.Constructor;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TypeParser {
	
	public static Object parseStringToObject(Class<?> attributeClass, String stringValue) {
		String className = attributeClass.toString();
		Object value = null;
		try {
			Constructor<?> construtor = attributeClass.getConstructor(new Class[] { className.getClass() });
			value = construtor.newInstance(new Object[] { stringValue });

		} catch (Exception e) {
		}
		return value;
	}
	
	public static Date parseStringToDate(String stringValue) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new java.sql.Date(formatter.parse(stringValue).getTime());		
		return date;
	}

}
