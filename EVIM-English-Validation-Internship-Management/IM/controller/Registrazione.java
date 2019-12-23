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
	 * Servlet di registrazione come studente
	 * 
	 * @author Nicola Sisti
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String regexStudente = "[a-z0-9\\.]+@studenti\\.unisa\\.it";
		String regexDocente = "[a-z0-9\\.]+@unisa\\.it";
		String email = request.getParameter("email");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String password = request.getParameter("password");
		String cpassword = request.getParameter("cpassword");
		String sesso = request.getParameter("sesso");
		String corso = request.getParameter("corso");
		String telefono = "";
		// se non sono loggato mi posso registrare altrimenti non ha senso
		if (request.getParameter("utenteLoggato") == null) {

			if (email.matches(regexDocente)) { // controllo del formato e lunghezza caratteri
				if (password.equals(cpassword) && nome != "" && cognome != "" && password != "" && nome.length() > 7
						&& cognome.length() > 7) {
					// TurorAccademico u= new
					// TutorAccademico(nome,cognome,sesso.charAt(0),password,1,corso);
					// TutorAccademico.DAO // da fare
				}

			}
		}

		else if (email.matches(regexStudente)) {

			if (password.equals(cpassword) && nome != "" && cognome != "" && password != "" && sesso != ""
					&& nome.length() > 7 && cognome.length() > 7) {
				User u = new User(email, nome, cognome, sesso.charAt(0), password, 1, corso);
				UserDAO.insertNewUser(u);
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/home.jsp");
				rd.forward(request, response);
			}
		}

		// se non rispetta i formati ritorna alla registrazione e invio un valore che
		// conferma l'errato immissione dei dati

		request.setAttribute("errorReg", false);
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/registrazione.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
