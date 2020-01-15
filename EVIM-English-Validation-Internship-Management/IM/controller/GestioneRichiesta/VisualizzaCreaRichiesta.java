package controller.GestioneRichiesta;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.TirocinioEsterno;
import model.TirocinioEsternoDAO;
import model.TirocinioInternoDAO;
import model.User;

/**
 * Servlet implementation class VisualizzaCreaRichiesta
 */
@WebServlet("/VisualizzaCreaRichiesta")
public class VisualizzaCreaRichiesta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione=request.getSession();
		// controllo se è loggato l'utente altrimenti reindirizzo alla pagina login
		if (sessione.getAttribute("utenteLoggato") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/login.jsp");
			dispatcher.forward(request, response);
		}
		else {
			String tipoUtente=sessione.getAttribute("utenteLoggato").getClass().getName();
			// non adatto per il tutor aziendale, il tutor accademico e l'azienda
			if(!tipoUtente.equalsIgnoreCase("model.User")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("permissionDenied.jsp");
				request.setAttribute("risultatoInserimentoProposta",false);
				dispatcher.forward(request, response);
			}
			else {
				User utente=(User) sessione.getAttribute("utenteLoggato");
				if(utente.getUserType()==0) {
					String email=utente.getEmail();
					if(TirocinioEsternoDAO.getStatusTirocinioEsterno(email) || TirocinioInternoDAO.getStatusTirocinioInterno(email)) {
						//setto l'attributo a 1 vuol dire che sta facendo un tirocinio
						request.setAttribute("richiesta", true);
						request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
						//response.getWriter().append("approvato");
					}
					else {
						//altrimenti non ha fatto nessun tirocinio
						request.getRequestDispatcher("creazioneRichiesta.jsp").forward(request, response);
					}

				}
				
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
