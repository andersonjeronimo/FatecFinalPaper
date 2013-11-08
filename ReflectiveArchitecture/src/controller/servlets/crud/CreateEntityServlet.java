package controller.servlets.crud;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedList;
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
 * Servlet implementation class CreateEntityServlet
 */
@WebServlet("/CreateEntityServlet")
public class CreateEntityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateEntityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String entityName = request.getParameter("entityName");
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
			dao.insertEntity(entity);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
				
		request.setAttribute("message", "A entidade " + entityName + " foi inserida com sucesso.");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}	

}
