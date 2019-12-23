package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.UserDAO;

/**
 * Servlet implementation class Registrazione
 */
@WebServlet("/Registrazione")
public class Registrazione extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String nome= request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String password= request.getParameter("password");
		String cpassword= request.getParameter("cpassword");
		String sesso= request.getParameter("sesso");
		if(request.getParameter("utenteLoggato")==null) {
			if(password.equals(cpassword)) {
				User u= new User(email,nome,cognome,sesso.charAt(0),password,1);
				UserDAO.insertNewUser(u);
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/home.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
