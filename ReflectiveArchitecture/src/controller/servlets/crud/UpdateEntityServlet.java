package controller.servlets.crud;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.TypeParser;

import model.database.data.DataAccess;
import model.entity.dynamic.DynamicEntity;
import model.entity.persistent.PersistentObjects;

/**
 * Servlet implementation class UpdateEntityServlet
 */
@WebServlet("/UpdateEntityServlet")
public class UpdateEntityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEntityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String entityName = request.getParameter("entityName");
		String entityId = request.getParameter("entityId");
		String updateType = request.getParameter("updateType");
		
		if(updateType.equals("preUpdate")){
			
			List<DynamicEntity> entityList = new LinkedList<DynamicEntity>(); 
			DataAccess dao = new DataAccess();
			try {
				entityList = dao.selectEntity(entityName, entityId);
			} catch (SQLException e) {			
				e.printStackTrace();
			}
			
			List<String> attributeList = new LinkedList<>();
			Properties attributeValues = new Properties();				
			
			for(int i = 0; i < entityList.size(); i++){
				DynamicEntity entity = entityList.get(i);
				attributeList = entity.listEntityAttributes();				
				
				for(String attributeName:attributeList){		
					String attributeValue = entity.getAttributeValue(attributeName).toString();
					attributeValues.put(attributeName, attributeValue);
				}							
			}		
			
			request.setAttribute("entityName", entityName);
			request.setAttribute("entityId", entityId);
			request.setAttribute("attributeValues", attributeValues);
			request.setAttribute("attributeList", attributeList);		
			
			request.setAttribute("message", "A entidade " + entityName + " foi recuperada com sucesso.");
			request.getRequestDispatcher("crud/updateEntity.jsp").forward(request, response);
			
		}else{
			/*Não houve consulta ao banco, já que a instância já existe no objeto PersistentObjects*/
			DynamicEntity entity = PersistentObjects.getEntity(entityName);
			LinkedList<String> list = entity.listEntityAttributes();
			for(String attribute:list){
				String stringValue = request.getParameter(attribute);			
				String attributeClassName = entity.getAttributeClass(attribute).toString();
				Class<?> attributeClass = entity.getAttributeClass(attribute);
				
				if(attributeClassName.equals("class java.sql.Date")){
					//método de parse date aqui
					Date dateValue;
					try {
						dateValue = TypeParser.parseStringToDate(stringValue);
						entity.setAttributeValue(attribute, dateValue);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					
				}else{				
					//método de parse object aqui
					Object objectValue = TypeParser.parseStringToObject(attributeClass, stringValue);
					entity.setAttributeValue(attribute, objectValue);
				}						
			}
			
			DataAccess dao = new DataAccess();
			try {
				dao.updateEntity(entity, entityId);
			} catch (SQLException e) {			
				e.printStackTrace();
			}		
			
			request.setAttribute("message", "A entidade " + entityName + " foi atualizada com sucesso.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		
	}

}
