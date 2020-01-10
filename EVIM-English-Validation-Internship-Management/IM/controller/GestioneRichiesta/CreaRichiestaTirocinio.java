package controller.GestioneRichiesta;

import controller.GestioneAutenticazione.*;
import model.TirocinioEsternoDAO;
import model.TirocinioInternoDAO;
import model.TutorAziendaleDAO;
import model.User;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Emilio Schiavo
 * 
 *         Servlet implementation class CreaRichiestaTirocinio
 */
@SuppressWarnings("serial")
@WebServlet("/CreaRichiestaTirocinio")
public class CreaRichiestaTirocinio extends BaseServlet {
	User user;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Controllo se l'utente è loggato e ha i permessi per la pagina
		HttpSession sessione = request.getSession();
		if (sessione.getAttribute("utenteLoggato") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/login.jsp");
			dispatcher.forward(request, response);
			// Redirect a login in caso di utente non loggato
		} else {

			// Prendo dalla sessione il nome dell'utente loggato
			String tipoUtente = sessione.getAttribute("utenteLoggato").getClass().getName();
			if (tipoUtente.equalsIgnoreCase("model.user")) {
				User studente = (User) sessione.getAttribute("utenteLoggato");
				// Verifico che sia studente
				if (studente.getUserType() == 0) {

					// data attuale formata correttamente
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					Date dateobj = new Date();
					String data = df.format(dateobj);

					// Get IDProposta
					int idProp = Integer.parseInt(request.getParameter("sel3"));

					// get idTutAcc
					int idTutAcc = Integer.parseInt(request.getParameter("sel4"));

					// Si tratta di tirocinio esterno
					System.out.println(request.getParameter("sel1"));
					if (request.getParameter("sel1").equals("tirocinio1")) {
						
						
						cfu=Integer.parseInt(request.getParameter("cfu"));
						// per ogni cfu vanno effettuate 25 ore di tirocinio
						int ore = cfu * 25;

						// Get tutor aziendale from proposta
						int idTutAz = TutorAziendaleDAO.doRetriveByIDProposta(idProp);
						System.out.println(idTutAz+" id");
						

						if (TirocinioEsternoDAO.doInsert(idTutAcc, idTutAz, ore, cfu, idProp, studente.getEmail(), data,
								"in approvazione")) {
							System.out.println("Query successfully executed");
							RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/home.jsp");
							dispatcher.forward(request, response);

						} else {
							System.out.println("Something gone wrong -.-");
							// TODO
						}

					} else/* Si tratta di tirocinio Interno */ {
						if (TirocinioInternoDAO.doInsert(studente.getEmail(), idTutAcc, data, (11 * 25),
								"in approvazione", 11, idProp)) {
							RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/home.jsp");
							dispatcher.forward(request, response);
						} else {
							System.out.println("Something gone wrong -.-");
							// TODO
						}

					}

				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("permissionDenied.jsp");
					dispatcher.forward(request, response);
				}
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("permissionDenied.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private int idTutAcc, idTutAz, oreTotali, cfu, idProp;
	private String email, data, status;
}
