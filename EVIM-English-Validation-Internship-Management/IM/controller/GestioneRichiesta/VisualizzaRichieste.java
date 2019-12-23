package controller.GestioneRichiesta;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.BaseServlet;
import model.TirocinioEsterno;
import model.TirocinioEsternoDAO;
import model.TirocinioInterno;
import model.TirocinioInternoDAO;
import model.User;

/**
 * 
 * @author Simone Auriemma Questa servlet avra' il compito di visualizzare tutte
 *         le richieste La visualizzazione delle richieste potranno essere
 *         visualizzate solamente se gli attori sono i seguenti: Studente, Tutor
 *         Accademico, Azienda
 * 
 * 
 * 
 */
@SuppressWarnings("serial")
@WebServlet("/visualizzaRichieste")
public class VisualizzaRichieste extends BaseServlet {
	User user;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// vedo prima se l'utente è loggato e se ha i permessi per visualizzare la
		// pagina
		
		HttpSession sessione = request.getSession();
		// verifico prima se esiste un utente nella sessione, se non esiste lo
		// reindirizzo nella pagina di login
		if (sessione.getAttribute("utenteLoggato") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/login.jsp");
			dispatcher.forward(request, response);
		} else {
			// prendo dalla sessione il nome dell'oggetto dell'utente loggato
			
			String tipoUtente = sessione.getAttribute("utenteLoggato").getClass().getName();
			
			// l'utente visualizza una pagina differente dal tutor accademico ed aziendale,
			// quindi in base all'oggetto nella sessione , controllo se l'utente è abilitato
			if (tipoUtente.equalsIgnoreCase("model.User")) {
				System.out.println("funziona?");
				// è loggato un tipo "user"
				// a causa dello usertype, devo verificare se è loggato esattamente uno studente
				User studente = (User) sessione.getAttribute("utenteLoggato");
				// è loggato un utente
				if (studente.getUserType() == 0) {
					
					// verifico se l'utente è magistrale o triennale, in quanto per gli studenti
					// magistrali i tirocini possono essere solamente esterni
					if (studente.getCorso().equalsIgnoreCase("magistrale")) {
						ArrayList<TirocinioEsterno> tirocinioEsterno = new TirocinioEsternoDAO()
								.doRetriveAllByStudent(studente.getEmail());
						request.setAttribute("arrayTirocinioEsterno", tirocinioEsterno);
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("WEB-INF/viewRichiestaTiricinioStudente.jsp");
						dispatcher.forward(request, response);
					} else {
						// studenti triennali, quindi i tirocini possono essere sia interni che esterni
						ArrayList<TirocinioInterno> tirocinioInterno = new TirocinioInternoDAO()
								.doRetriveAllByStudent(studente.getEmail());
					
						ArrayList<TirocinioEsterno> tirocinioEsterno = new TirocinioEsternoDAO()
								.doRetriveAllByStudent(studente.getEmail());
						System.out.println(tirocinioEsterno);
						request.setAttribute("arrayTirocinioEsterno", tirocinioEsterno);
						request.setAttribute("arrayTirocinioInterno", tirocinioInterno);
					
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("WEB-INF/viewRichiestaTiricinioStudente.jsp");
						dispatcher.forward(request, response);

					}

				} else {
					// è loggato il PdCD o ufficio carriere
					// pagina da fare permession denied
					RequestDispatcher dispatcher = request.getRequestDispatcher("PermessionDeniend.jsp");
					dispatcher.forward(request, response);
				}
			} else if (tipoUtente.equalsIgnoreCase("tutoraccademico")) {
				// viewListaRichiesteTirocinioInterno
				// 2 arraylist
				ArrayList<TirocinioInterno> tirociniInterno = new TirocinioInternoDAO().doRetriveAll();
				ArrayList<TirocinioEsterno> tirocinioEsterno = new TirocinioEsternoDAO().doRetriveAll();

				request.setAttribute("arrayTirocinioEsterno", tirocinioEsterno);
				request.setAttribute("arrayTirocinioInterno", tirociniInterno);
				RequestDispatcher dispatcher = request.getRequestDispatcher("viewListaRichiesteTirocinioInterno.jsp");
				dispatcher.forward(request, response);

			} else if (tipoUtente.equalsIgnoreCase("tutoraziendale")) {
				// viewListaRichiesteTirocinioEsterno
				// solo 1 arraylist
				ArrayList<TirocinioEsterno> tirocinioEsterno = new TirocinioEsternoDAO().doRetriveAll();
				request.setAttribute("arrayTirocinioEsterno", tirocinioEsterno);
				RequestDispatcher dispatcher = request.getRequestDispatcher("viewListaRichiesteTirocinioEsterno.jsp");
				dispatcher.forward(request, response);
			}

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
