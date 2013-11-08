package controller.servlets;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.persistent.PersistentObjects;

/**
 * Servlet implementation class ListPersistentObjectsServlet
 */
@WebServlet("/ListPersistentObjects")
public class ListPersistentObjects extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListPersistentObjects() {
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
		Set<String> entityNames = PersistentObjects.getObjectNames();		
		
		request.setAttribute("message", "Lista de entidades atualizada com sucesso.");
		request.setAttribute("entityList", entityNames);
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

}
