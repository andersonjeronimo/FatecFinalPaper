package controller.servlets.crud;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.database.data.DataAccess;
import model.database.metadata.MetadataAccess;
import model.entity.dynamic.DynamicEntity;

/**
 * Servlet implementation class ReadEntityServlet
 */
@WebServlet("/ReadEntityServlet")
public class ReadEntityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadEntityServlet() {
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
		
		List<DynamicEntity> entityList = new LinkedList<DynamicEntity>(); 
		DataAccess dao = new DataAccess();
		try {
			entityList = dao.selectEntity(entityName, entityId);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		List<String> attributeList = new LinkedList<>();
		List<Integer> tableIdList = new LinkedList<>();
		HashMap<String, Properties> entityHashMap = new HashMap<String, Properties>();	
		
		MetadataAccess metadata = new MetadataAccess();
		tableIdList = metadata.listTableId(entityName);		
		
		for(int i = 0; i < entityList.size(); i++){
			DynamicEntity entity = entityList.get(i);
			attributeList = entity.listEntityAttributes();			
			Properties attributeValues = new Properties();
			
			for(String attributeName:attributeList){		
				String attributeValue = entity.getAttributeValue(attributeName).toString();
				attributeValues.put(attributeName, attributeValue);
			}
			entityHashMap.put(String.valueOf(tableIdList.get(i)), attributeValues);			
		}		
		
		request.setAttribute("entityName", entityName);
		request.setAttribute("entityHashMap", entityHashMap);
		request.setAttribute("attributeList", attributeList);		
		
		request.setAttribute("message", "A entidade " + entityName + " foi recuperada com sucesso.");
		request.getRequestDispatcher("crud/readEntity.jsp").forward(request, response);		
	}

}
