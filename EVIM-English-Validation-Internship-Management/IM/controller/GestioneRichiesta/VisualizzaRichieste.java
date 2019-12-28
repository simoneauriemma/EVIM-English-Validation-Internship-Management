package controller.GestioneRichiesta;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controller.BaseServlet;
import model.TirocinioEsterno;
import model.TirocinioEsternoDAO;
import model.TirocinioInterno;
import model.TirocinioInternoDAO;
import model.TutorAccademico;
import model.TutorAziendale;
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
@WebServlet("/VisualizzaRichieste")
public class VisualizzaRichieste extends BaseServlet {
	User user;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// vedo prima se l'utente � loggato e se ha i permessi per visualizzare la
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
			// quindi in base all'oggetto nella sessione , controllo se l'utente � abilitato
			if (tipoUtente.equalsIgnoreCase("model.User")) {
				// � loggato un tipo "user"
				// a causa dello usertype, devo verificare se � loggato esattamente uno studente
				User studente = (User) sessione.getAttribute("utenteLoggato");
				// � loggato un utente
				if (studente.getUserType() == 0) {
					
					// verifico se l'utente � magistrale o triennale, in quanto per gli studenti
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
						//System.out.println(tirocinioEsterno);
						request.setAttribute("arrayTirocinioEsterno", tirocinioEsterno);
						request.setAttribute("arrayTirocinioInterno", tirocinioInterno);
					
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("WEB-INF/viewRichiestaTiricinioStudente.jsp");
						dispatcher.forward(request, response);

					}

				} else if(studente.getUserType()==1) {
					// � loggato il PdCD
					
					ArrayList<TirocinioInterno> tirocinioInterno = new TirocinioInternoDAO().doRetriveAllValutazionePdCD();
					ArrayList<TirocinioEsterno> tirocinioEsterno = new TirocinioEsternoDAO().doRetriveAllValutazionePdCD();
					
					request.setAttribute("arrayTirocinioEsterno", tirocinioEsterno);
					request.setAttribute("arrayTirocinioInterno", tirocinioInterno);
					
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/viewRichiestaTiricinioStudente.jsp");
					dispatcher.forward(request, response);
				}
			} else if (tipoUtente.equalsIgnoreCase("model.tutoraccademico")) {
				// viewListaRichiesteTirocinioInterno
				// 2 arraylist
				
				TutorAccademico accademico= (TutorAccademico) sessione.getAttribute("utenteLoggato");

				ArrayList<TirocinioInterno> tirocinioInterno = new TirocinioInternoDAO().doRetriveAllByTutor(accademico.getIdTutorAccademico());
				ArrayList<TirocinioEsterno> tirocinioEsterno = new TirocinioEsternoDAO().doRetriveAllByTutor(accademico.getIdTutorAccademico());
				
				

				request.setAttribute("arrayTirocinioEsterno", tirocinioEsterno);
				request.setAttribute("arrayTirocinioInterno", tirocinioInterno);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/viewListaRichiesteTirocinio.jsp");
				dispatcher.forward(request, response);

			} else if (tipoUtente.equalsIgnoreCase("model.tutoraziendale")) {
				// viewListaRichiesteTirocinioEsterno
				// solo 1 arraylist
				TutorAziendale aziendale= (TutorAziendale) sessione.getAttribute("utenteLoggato");
				ArrayList<TirocinioEsterno> tirocinioEsterno = new TirocinioEsternoDAO().doRetriveAllByTutor(aziendale.getId());
				request.setAttribute("arrayTirocinioEsterno", tirocinioEsterno);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/viewListaRichiesteTirocinio.jsp");
				dispatcher.forward(request, response);
			}

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
