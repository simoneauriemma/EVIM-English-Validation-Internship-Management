package controller.GestioneRegistroTirocinio;

import controller.GestioneAutenticazione.*;

import model.AttivitaDAO;
import model.User;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CompilaRegistro Solamente per lo studente
 * 
 * @author Simone Auriemma
 */
@SuppressWarnings("serial")
@WebServlet("/CompilaRegistro")
public class CompilaRegistro extends BaseServlet {

	public CompilaRegistro() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sessione = request.getSession();

		if (sessione.getAttribute("utenteLoggato") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/login.jsp");
			dispatcher.forward(request, response);
		} else {

			String tipoUtente = sessione.getAttribute("utenteLoggato").getClass().getName();

			// in base all'oggetto nella sessione , controllo se l'utente è abilitato
			if (tipoUtente.equalsIgnoreCase("model.User")) {
				// è loggato un tipo "user"
				// a causa dello usertype, devo verificare se è loggato esattamente uno studente
				User studente = (User) sessione.getAttribute("utenteLoggato");
				// è loggato un utente
				if (studente.getUserType() == 0) {
					String descrizione = request.getParameter("attivita");
					String data = request.getParameter("data");
					// parameter degli orari
					int orarioIngresso = Integer.parseInt(request.getParameter("orarioIngresso"));
					int orarioUscita = Integer.parseInt(request.getParameter("orarioUscita"));
					int ID_Registro = Integer.parseInt(request.getParameter("IDRegistro"));

					// errore nei prarametri
					if (descrizione == null || ID_Registro == 0 || orarioIngresso == 0 || orarioUscita == 0) {
						RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
						dispatcher.forward(request, response);
					} else {
						// inserimento nel db
						if (new AttivitaDAO().doInsert(ID_Registro, descrizione, orarioIngresso, orarioUscita) == 1) {
							// query andata bene
							request.setAttribute("errore", 0);
							RequestDispatcher dispatcher = request
									.getRequestDispatcher("registroTirocinio(studente).jsp");
							dispatcher.forward(request, response);
						} else {
							// qualcosa andata male
							request.setAttribute("errore", 1);
							RequestDispatcher dispatcher = request
									.getRequestDispatcher("registroTirocinio(studente).jsp");
							dispatcher.forward(request, response);
						}
					}
				} else {
					// User diverso dallo studente e può essere PdCD o Ufficio Carriere
					RequestDispatcher dispatcher = request.getRequestDispatcher("permissionDenied.jsp");
					dispatcher.forward(request, response);
				}
			} else {
				// Utente non autorizzato diverso da user
				RequestDispatcher dispatcher = request.getRequestDispatcher("permissionDenied.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
