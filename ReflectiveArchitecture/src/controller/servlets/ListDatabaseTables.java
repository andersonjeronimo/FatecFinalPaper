package controller.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.database.metadata.MetadataAccess;
import model.entity.EntityFactory;
import model.entity.dynamic.DynamicEntity;
import model.entity.persistent.PersistentObjects;

/**
 * Servlet implementation class ListPersistentObjectsServlet
 */
@WebServlet("/ListDatabaseTables")
public class ListDatabaseTables extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListDatabaseTables() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/*
		 * CRIAR OS OBJETOS DA CLASSE DYNAMICOBJECT E ARMAZENAR
		 * NO HASHMAP PERSISTENTOBJECTS 
		 * */
		HashMap<String, DynamicEntity> persistentObjects = new HashMap<>();
		List<String> schemaTables = new MetadataAccess().listTables();
		
		for(String tableName:schemaTables){
			DynamicEntity entity = EntityFactory.getEntityInstance(tableName);
			persistentObjects.put(tableName, entity);
		}
		
		System.out.println(persistentObjects);
		
		PersistentObjects.setPersistentObjects(persistentObjects);
		
		ServletContext context = this.getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/ListPersistentObjects");
		dispatcher.forward(request, response);		
		
	}

}
