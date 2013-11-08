package model.database.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import model.database.connection.ConnectionFactory;
import model.database.metadata.MetadataAccess;
import model.entity.EntityFactory;
import model.entity.dynamic.DynamicEntity;


public class DataAccess {

public void insertEntity(DynamicEntity entity) throws SQLException{
		
		Set<String> keySet = entity.getKeySet();
			
		String sql1 = "INSERT INTO " + entity.getName() + "(";
		String sql2 = "";
		String sql3 = ") VALUES(";
		
		for(String key:keySet){
			if(sql2 == ""){
				sql2 += key;
				sql3 += "?";
			}else{
				sql2 += "," + key;
				sql3 += ", ?";
			}
			
		}
		sql3 += ")";
	    sql1 += sql2;
	    sql1 += sql3;
	    
	    Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql1);
		
		int i = 1;
		Object value;
		String attributeClassName;
		for(String attributeName:keySet ){
			attributeClassName = entity.getAttributeClass(attributeName).toString();
					
			if(attributeClassName.equals("class java.lang.String")){
				value = entity.getAttributeValue(attributeName);
				pstmt.setString(i,(String)value);			
				
			}else if(attributeClassName.equals("class java.lang.Long")){
				value = entity.getAttributeValue(attributeName);
				pstmt.setLong(i, (Long)value);
				
			}else if(attributeClassName.equals("class java.sql.Date")){
				value = entity.getAttributeValue(attributeName);
				pstmt.setDate(i, (Date)value);	
			
			}else if(attributeClassName.equals("class java.lang.Integer")){
				value = entity.getAttributeValue(attributeName);
				pstmt.setInt(i, (int)value);				
			}			
			i += 1;			
		}		
		
		pstmt.execute();	
		
		conn.close();
		pstmt.close();	
		
	}	
	
	public List<DynamicEntity> selectEntity(String entityName, String id) throws SQLException{
				
		List<DynamicEntity> entityList = new LinkedList<DynamicEntity>();
		
		MetadataAccess metadata = new MetadataAccess();	
		
		DynamicEntity entity = EntityFactory.getEntityInstance(entityName);
		
		List<String> constraintPK = metadata.getConstraintName(entityName, "primary key");
		String columnIdLabel = metadata.getPKColumnName(entityName, constraintPK.get(0));

		String sql = "";
		if(id.equals("0")){
			sql = "SELECT * FROM " + entityName;
		}else{
			sql = "SELECT * FROM " + entityName + " WHERE " + columnIdLabel + " = " + id;			
		}
				
		Connection conn = ConnectionFactory.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		//rs.next();
		Set<String> entityAttributes = entity.getKeySet();
		String attributeClassName;
		while(rs.next()){
			DynamicEntity dynamic = EntityFactory.getEntityInstance(entityName);
			for(String attribute : entityAttributes){
				
				attributeClassName = dynamic.getAttributeClass(attribute).toString();
				
				if(attributeClassName.equals("class java.lang.String")){				
					Object value = rs.getString(attribute);
					dynamic.setAttributeValue(attribute, value);
					
				}else if(attributeClassName.equals("class java.lang.Long")){			
					Object value = rs.getLong(attribute);
					dynamic.setAttributeValue(attribute, value);
				
				}else if(attributeClassName.equals("class java.lang.Integer")){
					Object value = rs.getInt(attribute);
					dynamic.setAttributeValue(attribute, value);		
				
				}else if(attributeClassName.equals("class java.sql.Date")){
					Date date = rs.getDate(attribute);
					dynamic.setAttributeValue(attribute, date);
				}
			}
			entityList.add(dynamic);
		}

		conn.close();
		stmt.close();
		
		return entityList;
		
	}
	
	public void deleteEntity(String entityName, String entityId) throws SQLException{
		
		MetadataAccess metadata = new MetadataAccess();		
		List<String> constraintPK = metadata.getConstraintName(entityName, "primary key");
		String columnIdLabel = metadata.getPKColumnName(entityName, constraintPK.get(0));		
		String sql = "DELETE FROM " + entityName + " WHERE " + columnIdLabel + " = " + entityId;		
		Connection conn = ConnectionFactory.getConnection();		
		Statement stmt = conn.createStatement();		
		stmt.execute(sql);
		
		conn.close();
		stmt.close();		
	}
	
	public void updateEntity(DynamicEntity entity, String entityId) throws SQLException{
		
		MetadataAccess metadata = new MetadataAccess();		
		List<String> constraintPK = metadata.getConstraintName(entity.getName(), "primary key");
		String columnIdLabel = metadata.getPKColumnName(entity.getName(), constraintPK.get(0));
		
		Set<String> keySet = entity.getKeySet();
				
		String sql1 = "UPDATE " + entity.getName() + " SET ";
		String sql2 = "";
		String sql3 = " WHERE " + columnIdLabel + " = " + entityId; 
		
		for(String key:keySet){
			if(sql2 == ""){
				sql2 += key + " = ?";				
			}else{
				sql2 += ", " + key + " = ?";				
			}
			
		}
		
	    sql1 += sql2;
	    sql1 += sql3;
	    
	    System.out.println(sql1 + " na classe DataAccess.");
	    
	    Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql1);
		
		int i = 1;
		Object value;
		String attributeClassName;
		for(String attributeName:keySet ){
			attributeClassName = entity.getAttributeClass(attributeName).toString();
					
			if(attributeClassName.equals("class java.lang.String")){
				value = entity.getAttributeValue(attributeName);
				pstmt.setString(i,(String)value);			
				
			}else if(attributeClassName.equals("class java.lang.Long")){
				value = entity.getAttributeValue(attributeName);
				pstmt.setLong(i, (Long)value);
				
			}else if(attributeClassName.equals("class java.sql.Date")){
				value = entity.getAttributeValue(attributeName);
				pstmt.setDate(i, (Date)value);	
			
			}else if(attributeClassName.equals("class java.lang.Integer")){
				value = entity.getAttributeValue(attributeName);
				pstmt.setInt(i, (int)value);				
			}
			
			i += 1;			
		}		
		
		pstmt.execute();	
		
		conn.close();
		pstmt.close();	
		
	}

}
