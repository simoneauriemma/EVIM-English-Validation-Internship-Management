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
import controller.GestioneRichiesta.VisualizzaRichieste.TirocinioQueryPdCD;
import model.Azienda;
import model.RegistroDAO;
import model.TirocinioEsterno;
import model.TirocinioEsternoDAO;
import model.TirocinioInterno;
import model.TirocinioInternoDAO;
import model.TutorAccademico;
import model.TutorAziendale;
import model.User;

/**
 * Servlet implementation class ValutareRichieste Possono usare questa servlet
 * SOLAMENTE Il tutor Accademico e tutor aziendale e
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

		if (session.getAttribute("utenteLoggato") == null) {
			request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
		} else {

			if (session.getAttribute("utenteLoggato").getClass().getName()
					.equalsIgnoreCase(TutorAccademico.class.getName())
					|| session.getAttribute("utenteLoggato").getClass().getName()
							.equalsIgnoreCase(TutorAziendale.class.getName())
					|| session.getAttribute("utenteLoggato").getClass().getName()
							.equalsIgnoreCase(Azienda.class.getName())
					|| session.getAttribute("utenteLoggato").getClass().getName()
							.equalsIgnoreCase(User.class.getName())) {
				// prendo i dati in input
				String conferma = request.getParameter("confermato");
				int idTirocinio = 0;
				if (request.getParameter("id") != null) {
					// dato corretto
					idTirocinio = Integer.parseInt(request.getParameter("id"));
				}
				// per riconoscere se � interno o esterno
				String azienda = request.getParameter("azienda");
				// verifico se sono stati manomessi dati

				// indica se l'update ha avuto successo
				// 1 si 0 altrimenti
				int risposta;

				if (azienda == null) {
					// tirocinio interno
					if (session.getAttribute("utenteLoggato").getClass().getName()
							.equalsIgnoreCase(TutorAccademico.class.getName())) {
						//tutoraccademico
						TutorAccademico tutor = (TutorAccademico) session.getAttribute("utenteLoggato");
						if (conferma.equalsIgnoreCase("si")) {
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
					}
					else if(session.getAttribute("utenteLoggato").getClass().getName().equalsIgnoreCase(User.class.getName())) {
						User user = (User) session.getAttribute("utenteLoggato");
						if(user.getUserType()==2) {
							//loggato il Pdcd
							if (conferma.equalsIgnoreCase("si")) {
								risposta = new TirocinioInternoDAO().updateFirmaPdCDTrue(true, idTirocinio);
								request.setAttribute("esito", risposta);
								
								if(risposta==1) {
									new RegistroDAO().doSave(idTirocinio, "in svolgimento", "interno");
								}

								ArrayList<TirocinioQueryPdCD> tirocinioInterno = new TirocinioInternoDAO()
										.doRetriveAllValutazionePdCD();
								ArrayList<TirocinioQueryPdCD> tirocinioEsterno = new TirocinioEsternoDAO()
										.doRetriveAllValutazionePdCD();

								request.setAttribute("arrayTirocinioEsterno", tirocinioEsterno);
								request.setAttribute("arrayTirocinioInterno", tirocinioInterno);

								RequestDispatcher dispatcher = request
										.getRequestDispatcher("WEB-INF/viewListaRichiesteTirocinio.jsp");
								dispatcher.forward(request, response);

							} else if (conferma.equalsIgnoreCase("no")) {
								// cambia status in rifiutato
								risposta = new TirocinioInternoDAO().updateFirmaPdCDFalse(false, idTirocinio);
								request.setAttribute("esito", risposta);

								ArrayList<TirocinioQueryPdCD> tirocinioInterno = new TirocinioInternoDAO()
										.doRetriveAllValutazionePdCD();
								ArrayList<TirocinioQueryPdCD> tirocinioEsterno = new TirocinioEsternoDAO()
										.doRetriveAllValutazionePdCD();

								request.setAttribute("arrayTirocinioEsterno", tirocinioEsterno);
								request.setAttribute("arrayTirocinioInterno", tirocinioInterno);

								request.setAttribute("arrayTirocinioEsterno", tirocinioEsterno);
								request.setAttribute("arrayTirocinioInterno", tirocinioInterno);

								RequestDispatcher dispatcher = request
										.getRequestDispatcher("WEB-INF/viewListaRichiesteTirocinio.jsp");
								dispatcher.forward(request, response);
							}
							
						}
						else {
							request.getRequestDispatcher("WEB-INF/permissionDenied.jsp").forward(request, response);

						}
					}
					else {
						request.getRequestDispatcher("WEB-INF/permissionDenied.jsp").forward(request, response);
					}
				} else if (azienda.equalsIgnoreCase("azienda")) {
					// tirocinio esterno
					// devo vedere chi e' loggato
					if (session.getAttribute("utenteLoggato").getClass().getName()
							.equals(TutorAccademico.class.getName())) {
						// � loggato un tutor accademico
						TutorAccademico tutor = (TutorAccademico) session.getAttribute("utenteLoggato");

						if (conferma.equalsIgnoreCase("si")) {
							risposta = new TirocinioEsternoDAO().updateFirmaTrueTutorAccademico(true, idTirocinio,
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
									.getRequestDispatcher("WEB-INF/viewListaRichiesteTirocinio.jsp");
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
									.getRequestDispatcher("WEB-INF/viewListaRichiesteTirocinio.jsp");
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
									.getRequestDispatcher("WEB-INF/viewListaRichiesteTirocinioEsterno.jsp");
							dispatcher.forward(request, response);

						}
					} // azienda
					else if (session.getAttribute("utenteLoggato").getClass().getName()
							.equals(Azienda.class.getName())) {
						// loggato azienda
						Azienda az = (Azienda) session.getAttribute("utenteLoggato");
						if (conferma.equalsIgnoreCase("si")) {

							risposta = new TirocinioEsternoDAO().updateFirmaTrueAzienda(true, idTirocinio,
									az.getID_Azienda());

							request.setAttribute("esito", risposta);

							ArrayList<TirocinioQueryEsternoTutorAz> tirocinioEsterno = new TirocinioEsternoDAO()
									.doRetriveAllByTutorAz(az.getID_Azienda());
							request.setAttribute("arrayTirocinioEsterno", tirocinioEsterno);

							RequestDispatcher dispatcher = request
									.getRequestDispatcher("WEB-INF/viewListaRichiesteTirocinio.jsp");
							dispatcher.forward(request, response);

						} else if (conferma.equalsIgnoreCase("no")) {
							// cambia status in rifiutato
							risposta = new TirocinioEsternoDAO().updateFirmaFalseAzienda(false, idTirocinio,
									az.getID_Azienda());
							request.setAttribute("esito", risposta);

							ArrayList<TirocinioQueryEsternoTutorAz> tirocinioEsterno = new TirocinioEsternoDAO()
									.doRetriveAllByTutorAz(az.getID_Azienda());

							request.setAttribute("arrayTirocinioEsterno", tirocinioEsterno);

							RequestDispatcher dispatcher = request
									.getRequestDispatcher("WEB-INF/viewListaRichiesteTirocinioEsterno.jsp");
							dispatcher.forward(request, response);

						}
					} else if (session.getAttribute("utenteLoggato").getClass().getName()
							.equals(User.class.getName())) {

						User user = (User) session.getAttribute("utenteLoggato");
						if (user.getUserType() == 2) {
							if (conferma.equalsIgnoreCase("si")) {
								risposta = new TirocinioEsternoDAO().updateFirmaPdCDTrue(true, idTirocinio);
								request.setAttribute("esito", risposta);

								if (risposta == 1) {
									new RegistroDAO().doSave(idTirocinio, "in svolgimento", "esterno");
								}

								ArrayList<TirocinioQueryPdCD> tirocinioInterno = new TirocinioInternoDAO()
										.doRetriveAllValutazionePdCD();
								ArrayList<TirocinioQueryPdCD> tirocinioEsterno = new TirocinioEsternoDAO()
										.doRetriveAllValutazionePdCD();

								request.setAttribute("arrayTirocinioEsterno", tirocinioEsterno);
								request.setAttribute("arrayTirocinioInterno", tirocinioInterno);

								RequestDispatcher dispatcher = request
										.getRequestDispatcher("WEB-INF/viewListaRichiesteTirocinio.jsp");
								dispatcher.forward(request, response);

							} else if (conferma.equalsIgnoreCase("no")) {
								risposta = new TirocinioEsternoDAO().updateFirmaPdCDFalse(false, idTirocinio);
								request.setAttribute("esito", risposta);

								ArrayList<TirocinioQueryPdCD> tirocinioInterno = new TirocinioInternoDAO()
										.doRetriveAllValutazionePdCD();
								ArrayList<TirocinioQueryPdCD> tirocinioEsterno = new TirocinioEsternoDAO()
										.doRetriveAllValutazionePdCD();

								request.setAttribute("arrayTirocinioEsterno", tirocinioEsterno);
								request.setAttribute("arrayTirocinioInterno", tirocinioInterno);

								RequestDispatcher dispatcher = request
										.getRequestDispatcher("WEB-INF/viewListaRichiesteTirocinio.jsp");
								dispatcher.forward(request, response);

							}
						} else {
							request.getRequestDispatcher("WEB-INF/permissionDenied.jsp").forward(request, response);
						}
					}
				}

				else {
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

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}