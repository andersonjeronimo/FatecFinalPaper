package model.database.metadata;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import model.database.connection.ConnectionFactory;

public class MetadataAccess {

	public List<String> listTables() {
		
		String sql = "SHOW TABLES";
		Connection con = ConnectionFactory.getConnection();
		
		List<String> list = new LinkedList<String>();
		Statement stmt; 
		ResultSet rs; 
		try{
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String entity = rs.getString("TABLES_IN_" + ConnectionFactory.getSchema());
				list.add(entity);
			} 
			stmt.close();
			rs.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	/* este método é utilizado para verificar o tipo sql da coluna */
	/*public String getColumnTypeName(String columnName, String tableName) {
				
		Connection con = ConnectionFactory.getConnection();		
		String sql = "SELECT data_type FROM information_schema.columns "
				+ "WHERE table_schema = '" + ConnectionFactory.getSchema() + "' "
				+ "AND table_name = '" + tableName + "' "
				+ "AND column_name = '" + columnName + "'";
		
		String columnTypeName = new String();
		Statement stmt;
		ResultSet rs;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			columnTypeName = rs.getString("data_type");
			stmt.close();
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return columnTypeName;
	}*/

	/* método utilizado para filtrar colunas com propriedade de auto-incremento */
	public boolean isAutoIncrement(String columnName, String tableName) {
		
		boolean autoIncrement = false;		
		Connection con = ConnectionFactory.getConnection();		
		String sql = "SELECT extra FROM information_schema.columns "
				+ "WHERE table_schema = '" + ConnectionFactory.getSchema() + "' "
				+ "AND table_name = '" + tableName + "' "
				+ "AND column_name = '" + columnName + "'";
		
		Statement stmt;
		ResultSet rs;
		String extra = new String();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);		
			rs.next();
			extra = rs.getString("extra");
			if (extra.equalsIgnoreCase("auto_increment")) {
				autoIncrement = true;
			}
			stmt.close();
			rs.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}		

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return autoIncrement;
	}

	/*
	 * este método é utilizado para listar somente os nomes das colunas de uma
	 * determinada tabela
	 */
	public List<String> listTableColumns(String tableName) {		
		
		Connection con = ConnectionFactory.getConnection();
		String sql = "SELECT column_name FROM information_schema.columns "
				+ "WHERE table_schema = '" + ConnectionFactory.getSchema() + "' "
				+ "AND table_name = '" + tableName + "'";
		
		Statement stmt;
		ResultSet rs;
		List<String> list = new LinkedList<String>();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String columnName = rs.getString("column_name");
				if (!isAutoIncrement(columnName, tableName)) {
					list.add(columnName);
				}
			}
			stmt.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return list;

	}

	public List<Integer> listTableId(String tableName){
		
		List<Integer> tableIdList = new LinkedList<Integer>();
		
		List<String> constraintPK = getConstraintName(tableName, "primary key");
		String columnId = getPKColumnName(tableName, constraintPK.get(0));
		
		Connection con = ConnectionFactory.getConnection();
		String sql = "SELECT " + columnId + " FROM " + tableName;
		
		Statement stmt; 
		ResultSet rs;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				tableIdList.add(rs.getInt(columnId));
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tableIdList;		
	}
	
	/*
	 * este método retorna o nome e o tipo em java de todas as colunas de uma
	 * tabela
	 */
	public Properties getObjectAttributes(String tableName) {		
		/*
		 * os dois métodos abaixo foram utilizados para encontrar a coluna ID da
		 * tabela
		 */
		List<String> constraintPK = getConstraintName(tableName, "primary key");
		String columnId = getPKColumnName(tableName, constraintPK.get(0));
		
		Connection con = ConnectionFactory.getConnection();
		String sql = "SELECT * FROM " + tableName + " WHERE " + columnId + " = 0";
		
		Properties columnClass = new Properties();		
		Statement stmt; 
		ResultSet rs; 
		ResultSetMetaData rsmd; 
		int count; 
		
		try{
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			rsmd = rs.getMetaData();
			count = rsmd.getColumnCount();
			
			for (int i = 1; i <= count; i++) {
				if (!rsmd.isAutoIncrement(i)) {					
						String columnName = rsmd.getColumnName(i);
						String className = rsmd.getColumnClassName(i);
						columnClass.setProperty(columnName, className);
					}
				}			
			stmt.close();
			rs.close();			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		try {
			con.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		return columnClass;

	}

	
	/*
	 * este método retorna o nome e o tipo em sql de todas as colunas de uma
	 * tabela
	 */
	public Properties getTableAttributes(String tableName) {		
		/*
		 * os dois métodos abaixo foram utilizados para encontrar a coluna ID da
		 * tabela
		 */
		List<String> constraintPK = getConstraintName(tableName, "primary key");
		String columnId = getPKColumnName(tableName, constraintPK.get(0));

		Connection con = ConnectionFactory.getConnection();		
		String sql = "SELECT * FROM " + tableName + " WHERE " + columnId + " = 0";		
		
		Properties columnType = new Properties();		
		Statement stmt;
		ResultSet rs;
		ResultSetMetaData rsmd;
		int count = 0;
		
		//Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			rsmd = rs.getMetaData();
			count = rsmd.getColumnCount();
			for (int i = 1; i <= count; i++) {
				if (!rsmd.isAutoIncrement(i)) {
					String columnName = rsmd.getColumnName(i);
					String typeName = rsmd.getColumnTypeName(i);
					columnType.setProperty(columnName, typeName);
				}
			}			
			stmt.close();
			rs.close();						
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}	

		try {
			con.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return columnType;

	}
		 

	public List<Object> getColumnValues(String columnName, String tableName) {
		
		Connection con = ConnectionFactory.getConnection();
		String sql = "SELECT " + columnName + " FROM " + tableName;
		
		Statement stmt;		
		ResultSet rs ;		
		ResultSetMetaData rsmd;
		String type;
		List<Object> values = new LinkedList<Object>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			rsmd = rs.getMetaData();
			type = rsmd.getColumnTypeName(1);
			
			switch (type) {
			case "int":
				while (rs.next()) {
					int intValue = rs.getInt(columnName);
					values.add(intValue);
				}
				break;

			default:
				while (rs.next()) {
					String strValue = rs.getString(columnName);
					values.add(strValue);
				}
				break;
			}
					
			stmt.close();
			rs.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		try {
			con.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		return values;
	}
	
	public List<String> getConstraintName(String tableName,	String constraintType) {
		
		Connection con = ConnectionFactory.getConnection();
		String sql = "SELECT constraint_name FROM information_schema.table_constraints	"
				+ "WHERE constraint_schema = '"
				+ ConnectionFactory.getSchema()
				+ "' "
				+ "AND table_name = '"
				+ tableName
				+ "' AND constraint_type = '" + constraintType + "'";

		List<String> constraints = new LinkedList<String>();
		Statement stmt;
		ResultSet rs;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String constraintName = rs.getString("constraint_name");
				constraints.add(constraintName);
			}				
			stmt.close();
			rs.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		try {
			con.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		return constraints;
	}

	/* método retorna o rótulo da coluna que representa a chave primária */
	public String getPKColumnName(String tableName, String constraintName) {
		
		Connection con = ConnectionFactory.getConnection();
		String sql = "SELECT column_name FROM information_schema.key_column_usage"
				+ " WHERE constraint_schema = '"
				+ ConnectionFactory.getSchema()
				+ "'"
				+ "AND table_name = '"
				+ tableName
				+ "'"
				+ " AND constraint_name = '" + constraintName + "'";

		String column = new String();
		Statement stmt;
		ResultSet rs;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			column = rs.getString("column_name");			
			stmt.close();
			rs.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		try {
			con.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		return column;
	}

	/* método retorna o nome da coluna que represente uma chave estrangeira */
	public String getFKColumnName(String constraintName) {
		
		Connection con = ConnectionFactory.getConnection();
		String sql = "SELECT column_name from information_schema.key_column_usage"
				+ " WHERE constraint_schema = '"
				+ ConnectionFactory.getSchema()
				+ "'"
				+ " AND constraint_name = '" + constraintName + "'";

		String column = new String();
		Statement stmt;
		ResultSet rs;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			column = rs.getString("column_name");
			
			stmt.close();
			rs.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		try {
			con.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}	
		return column;
	}

	/*
	 * método retorna nome da tabela referenciada por uma constraint do tipo
	 * Foreign Key
	 */
	public String getReferencedTableName(String constraintFK) {
		
		Connection con = ConnectionFactory.getConnection();

		String sql = "SELECT referenced_table_name"
				+ " FROM information_schema.referential_constraints"
				+ " WHERE constraint_schema = '" + ConnectionFactory.getSchema() + "'"
				+ " AND constraint_name = '" + constraintFK + "'";

		
		String table = new String();
		Statement stmt;
		ResultSet rs;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			table = rs.getString("referenced_table_name");
			
			stmt.close();
			rs.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		try {
			con.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}	
		return table;
	}

	/* retorna a coluna referenciada por uma contraint do tipo Foreign Key */
	public String getReferencedColumnName(String constraintFK) {
		
		Connection con = ConnectionFactory.getConnection();		
		String sql = " SELECT referenced_column_name"
				+ " FROM information_schema.key_column_usage"
				+ " WHERE constraint_schema = '" + ConnectionFactory.getSchema() + "'"
				+ " AND constraint_name = '" + constraintFK + "'";
		String column = new String();		
		Statement stmt;
		ResultSet rs;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			column = rs.getString("referenced_column_name");			
			stmt.close();
			rs.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		
		try {
			con.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		return column;
	}

	/* retorna a o rótulo da coluna com o dado mais relevante de uma tabela */
	public String getColumnLabel(String tableName, int ordinalPosition)	 {
		
		Connection con = ConnectionFactory.getConnection();		
		String sql = "SELECT column_name FROM information_schema.columns"
				+ " WHERE table_schema = '" + ConnectionFactory.getSchema() + "'"
				+ " AND table_name= '" + tableName
				+ "' AND ordinal_position = " + ordinalPosition;		
		String label = new String();
		Statement stmt;
		ResultSet rs;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			label = rs.getString("column_name");			
			stmt.close();
			rs.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		
		try {
			con.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}	
		return label;
	}

}
