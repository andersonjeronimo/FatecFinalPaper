package controller.servlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.dynamic.DynamicEntity;
import model.entity.persistent.PersistentObjects;

/**
 * Servlet implementation class SelectActionServlet
 */
@WebServlet("/SelectAction")
public class SelectAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = request.getParameter("action");
		String entityName = request.getParameter("entityName");
				
		switch (action) {

		case "insert":
			
			DynamicEntity entity = PersistentObjects.getEntity(entityName);
			List<String> attributeList = new LinkedList<>();
			List<String> dateAttributes = new LinkedList<>();
			
			attributeList = entity.listEntityAttributes();
			for(String attribute:attributeList){
				String attributeClass = entity.getAttributeClass(attribute).toString();
				if(attributeClass.equalsIgnoreCase("class java.sql.Date")){
					dateAttributes.add(attribute);
				}
			}
			
			request.setAttribute("entityName", entityName);
			request.setAttribute("attributeList", attributeList);
			request.setAttribute("dateAttributes", dateAttributes);			
						
			request.setAttribute("message", "Formulário gerado com sucesso.");
			request.getRequestDispatcher("crud/createEntity.jsp").forward(request, response);			
			break;
		
		case "select":
			
			ServletContext context = this.getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/ReadEntityServlet");
			
			String entityId = request.getParameter("entityId");			
			request.setAttribute("entityName", entityName);
			request.setAttribute("entityId", entityId);			
			dispatcher.forward(request, response);
			
			break;
			
		case "update":
			request.getRequestDispatcher("crud/updateEntity.jsp").forward(request, response);
			break;
			
		case "delete":
			request.getRequestDispatcher("crud/deleteEntity.jsp").forward(request, response);
			break;
		}
		
	}

}
