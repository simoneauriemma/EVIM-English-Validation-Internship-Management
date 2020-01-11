package controller.GestioneRichiesta;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controller.GestioneAutenticazione.*;
import controller.GestioneRichiesta.VisualizzaRichieste.TirocinioQueryEsternoTutorAcc;
import controller.GestioneRichiesta.VisualizzaRichieste.TirocinioQueryEsternoTutorAz;
import controller.GestioneRichiesta.VisualizzaRichieste.TirocinioQueryInternoTutorAcc;
import model.TirocinioEsternoDAO;
import model.TirocinioInternoDAO;
import model.TutorAccademico;
import model.TutorAziendale;

/**
 * Servlet implementation class ValutareRichieste Possono usare questa servlet
 * SOLAMENTE Il tutor Accademico e tutor aziendale
 * 
 * @author Simone Auriemma
 * 
 */
@SuppressWarnings("serial")
@WebServlet("/ValutareRichieste")
public class ValutareRichieste extends BaseServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// vedo dalla sessione se ha i permessi
		HttpSession session = request.getSession();

		/*
		 * ValuatareRichiesta?confermato=si&id=(fare con JSTL) runtime (PER INTERNO)
		 * ValuatareRichiesta?confermato=si&id=(fare con JSTL)&azienda=azienda(per
		 * tirocinio ESTERNO) ValuatareRichiesta?confermato=no&id=&azienda=azienda
		 * ValuatareRichiesta?confermato=no&id=
		 */
		System.out.println(TutorAccademico.class.getName());
		System.out.println(TutorAziendale.class.getName());
		System.out.println(session.getAttribute("utenteLoggato"));

		if (session.getAttribute("utenteLoggato") == null) {
			request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
		} else {

			if (session.getAttribute("utenteLoggato").getClass().getName()
					.equalsIgnoreCase(TutorAccademico.class.getName())
					|| session.getAttribute("utenteLoggato").getClass().getName()
							.equalsIgnoreCase(TutorAziendale.class.getName())) {
				// prendo i dati in input
				String conferma = request.getParameter("confermato");
				System.out.println("conferma->" + conferma);
				int idTirocinio = 0;
				if (request.getParameter("id") != null) {
					// dato corretto
					idTirocinio = Integer.parseInt(request.getParameter("id"));
					System.out.println("idTirocinio->" + idTirocinio);
				}
				// per riconoscere se � interno o esterno
				String azienda = request.getParameter("azienda");
				System.out.println("Parametro azienda->" + azienda);
				// verifico se sono stati manomessi dati
				/*
				 * if (!(conferma.equalsIgnoreCase("si")) || !(conferma.equalsIgnoreCase("no")))
				 * { System.out.println("Sto nell'IF della conferma"); RequestDispatcher
				 * dispatcher = request.getRequestDispatcher("WEB-INF/permissionDenied.jsp");
				 * dispatcher.forward(request, response); }
				 */

				// indica se l'update ha avuto successo
				// 1 si 0 altrimenti
				int risposta;

				if (azienda == null) {
					System.out.println("Sto nel getParameter di azienda riga 75");
					// tirocinio interno
					TutorAccademico tutor = (TutorAccademico) session.getAttribute("utenteLoggato");
					if (conferma.equalsIgnoreCase("si")) {
						// tirocinio interno
						risposta = new TirocinioInternoDAO().updateFirmaTrue(true, idTirocinio,
								tutor.getIdTutorAccademico());
						request.setAttribute("esito", risposta);

						ArrayList<TirocinioQueryInternoTutorAcc> tirocinioInterno = new TirocinioInternoDAO()
								.doRetriveAllByTutorAcc(tutor.getIdTutorAccademico());
						ArrayList<TirocinioQueryEsternoTutorAcc> tirocinioEsterno = new TirocinioEsternoDAO()
								.doRetriveAllByTutorAcc(tutor.getIdTutorAccademico());

						request.setAttribute("arrayTirocinioEsterno", tirocinioEsterno);
						request.setAttribute("arrayTirocinioInterno", tirocinioInterno);

						RequestDispatcher dispatcher = request
								.getRequestDispatcher("WEB-INF/viewListaRichiesteTirocinio.jsp");
						dispatcher.forward(request, response);

					} else if (conferma.equalsIgnoreCase("no")) {
						// cambia status in rifiutato
						risposta = new TirocinioInternoDAO().updateFirmaFalse(false, idTirocinio,
								tutor.getIdTutorAccademico());
						request.setAttribute("esito", risposta);

						ArrayList<TirocinioQueryInternoTutorAcc> tirocinioInterno = new TirocinioInternoDAO()
								.doRetriveAllByTutorAcc(tutor.getIdTutorAccademico());
						ArrayList<TirocinioQueryEsternoTutorAcc> tirocinioEsterno = new TirocinioEsternoDAO()
								.doRetriveAllByTutorAcc(tutor.getIdTutorAccademico());

						request.setAttribute("arrayTirocinioEsterno", tirocinioEsterno);
						request.setAttribute("arrayTirocinioInterno", tirocinioInterno);

						RequestDispatcher dispatcher = request
								.getRequestDispatcher("WEB-INF/viewListaRichiesteTirocinio.jsp");
						dispatcher.forward(request, response);
					}
				} else if (azienda.equalsIgnoreCase("azienda")) {
					// tirocinio esterno
					// devo vedere chi e' loggato
					System.out.println("Sto nell'if dell'azienda nel tirocinio esterno-> 100");
					if (session.getAttribute("utenteLoggato").getClass().getName()
							.equals(TutorAccademico.class.getName())) {
						// � loggato un tutor accademico
						System.out.println("If nel tutor accademico,prima della query");
						TutorAccademico tutor = (TutorAccademico) session.getAttribute("utenteLoggato");

						if (conferma.equalsIgnoreCase("si")) {
							risposta = new TirocinioEsternoDAO().updateFirmaTrueTutorAccademico(true, idTirocinio,
									tutor.getIdTutorAccademico());
							System.out.println("risposta->" + risposta);
							request.setAttribute("esito", risposta);

							ArrayList<TirocinioQueryInternoTutorAcc> tirocinioInterno = new TirocinioInternoDAO()
									.doRetriveAllByTutorAcc(tutor.getIdTutorAccademico());
							ArrayList<TirocinioQueryEsternoTutorAcc> tirocinioEsterno = new TirocinioEsternoDAO()
									.doRetriveAllByTutorAcc(tutor.getIdTutorAccademico());

							request.setAttribute("arrayTirocinioEsterno", tirocinioEsterno);
							request.setAttribute("arrayTirocinioInterno", tirocinioInterno);

							RequestDispatcher dispatcher = request
									.getRequestDispatcher("WEB-INF/viewListaRichiesteTirocinio.jsp");
							dispatcher.forward(request, response);

						} else if (conferma.equalsIgnoreCase("no")) {
							risposta = new TirocinioEsternoDAO().updateFirmaFalseTutorAccademico(false, idTirocinio,
									tutor.getIdTutorAccademico());
							request.setAttribute("esito", risposta);

							ArrayList<TirocinioQueryInternoTutorAcc> tirocinioInterno = new TirocinioInternoDAO()
									.doRetriveAllByTutorAcc(tutor.getIdTutorAccademico());
							ArrayList<TirocinioQueryEsternoTutorAcc> tirocinioEsterno = new TirocinioEsternoDAO()
									.doRetriveAllByTutorAcc(tutor.getIdTutorAccademico());

							request.setAttribute("arrayTirocinioEsterno", tirocinioEsterno);
							request.setAttribute("arrayTirocinioInterno", tirocinioInterno);

							RequestDispatcher dispatcher = request
									.getRequestDispatcher("WEB-INF/viewRichiesteTirocinio.jsp");
							dispatcher.forward(request, response);
						}

					} else if (session.getAttribute("utenteLoggato").getClass().getName()
							.equals(TutorAziendale.class.getName())) {
						// loggato un tutor aziendale
						TutorAziendale tutor = (TutorAziendale) session.getAttribute("utenteLoggato");
						if (conferma.equalsIgnoreCase("si")) {
							risposta = new TirocinioEsternoDAO().updateFirmaTrueAziendale(true, idTirocinio,
									tutor.getId());
							request.setAttribute("esito", risposta);

							ArrayList<TirocinioQueryEsternoTutorAz> tirocinioEsterno = new TirocinioEsternoDAO()
									.doRetriveAllByTutorAz(tutor.getId());
							request.setAttribute("arrayTirocinioEsterno", tirocinioEsterno);

							RequestDispatcher dispatcher = request
									.getRequestDispatcher("WEB-INF/viewRichiesteTirocinio.jsp");
							dispatcher.forward(request, response);
						} else if (conferma.equalsIgnoreCase("no")) {
							// cambia status in rifiutato
							risposta = new TirocinioEsternoDAO().updateFirmaFalseAziendale(false, idTirocinio,
									tutor.getId());
							request.setAttribute("esito", risposta);

							ArrayList<TirocinioQueryEsternoTutorAz> tirocinioEsterno = new TirocinioEsternoDAO()
									.doRetriveAllByTutorAz(tutor.getId());

							request.setAttribute("arrayTirocinioEsterno", tirocinioEsterno);

							RequestDispatcher dispatcher = request
									.getRequestDispatcher("WEB-INF/viewRichiesteTirocinioEsterno.jsp");
							dispatcher.forward(request, response);

						}

					} else {
						// parametri compromessi
						RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/permissionDenied.jsp");
						dispatcher.forward(request, response);
					}

				} else {
					// e' loggata una persona non autorizzarata
					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/permissionDenied.jsp");
					dispatcher.forward(request, response);
				}

			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
