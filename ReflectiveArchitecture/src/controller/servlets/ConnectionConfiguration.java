package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.database.connection.ConnectionFactory;
import model.entity.persistent.PersistentObjects;

/**
 * Servlet implementation class ConnectionConfigurationServlet
 */
@WebServlet("/ConnectionConfiguration")
public class ConnectionConfiguration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectionConfiguration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ConnectionFactory.setServer(request.getParameter("server"));
		ConnectionFactory.setPort(request.getParameter("port"));
		ConnectionFactory.setSchema(request.getParameter("schema"));
		ConnectionFactory.setUsername(request.getParameter("username"));
		ConnectionFactory.setPassword(request.getParameter("password"));
		
		/*Limpar coleção de objetos persistentes em memória*/
		PersistentObjects.clearPersistentObjects();
		
		request.setAttribute("message", "Conexão configurada com sucesso!");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
