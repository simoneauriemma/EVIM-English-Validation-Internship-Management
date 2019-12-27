package controller.GestioneProposta;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.PropostaDAO;


/**
 * Servlet implementation class RimuoviProposta
 */
@WebServlet("/RimuoviProposta")
public class RimuoviProposta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione=request.getSession();
		int idProposta=Integer.parseInt(request.getParameter("idProposta"));
		// controllo se è loggato l'utente altrimenti reindirizzo alla pagina login
		if (sessione.getAttribute("utenteLoggato") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		else {
			String tipoUtente=sessione.getAttribute("utenteLoggato").getClass().getName();
			// non adatto per lo studente,pdcd,ufficio carriere
			if(tipoUtente.equalsIgnoreCase("model.User")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("permissionDenied.jsp");
				dispatcher.forward(request, response);
			}
			// tirocinio interno
			else if(tipoUtente.equalsIgnoreCase("model.tutoraccademico") || tipoUtente.equalsIgnoreCase("model.azienda")) {	
				if(PropostaDAO.removeProposta(idProposta)==true)
					request.setAttribute("rimuovi", true);
				else
					request.setAttribute("rimuovi", false);
				
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/home.jsp");
				dispatcher.forward(request, response);
				
			}
			//tirocinio esterno
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
