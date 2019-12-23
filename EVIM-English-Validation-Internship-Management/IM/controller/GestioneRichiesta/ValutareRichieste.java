package controller.GestioneRichiesta;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controller.BaseServlet;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// vedo dalla sessione se ha i permessi
		HttpSession session = request.getSession();

		/*
		 * ValuatareRichiesta?confermato=si&id=(fare con JSTL) runtime (PER INTERNO)
		 * ValuatareRichiesta?confermato=si&id=(fare con JSTL)&azienda=azienda(per
		 * tirocinio ESTERNO) ValuatareRichiesta?confermato=no&id=&azienda=azienda
		 * ValuatareRichiesta?confermato=no&id=
		 */

		if (session.getAttribute("utenteLoggato").equals(TutorAccademico.class.getName())
				|| session.getAttribute("utenteLoggato").equals(TutorAccademico.class.getName())) {
			// prendo i dati in input
			String conferma = request.getParameter("confermato");
			int idTirocinio = 0;
			if (request.getParameter("id") != null) {
				// dato corretto
				idTirocinio = Integer.parseInt(request.getParameter("id"));
			} else {
				// dato errato
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/permissionDenied.jsp");
				dispatcher.forward(request, response);
			}

			// per riconoscere se è interno o esterno
			String azienda = request.getParameter("azienda");

			// verifico se sono stati manomessi dati
			if (!(conferma.equalsIgnoreCase("si") || conferma.equalsIgnoreCase("no"))) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/permissionDenied.jsp");
				dispatcher.forward(request, response);
			}

			// indica se l'update ha avuto successo
			// 1 si 0 altrimenti
			int risposta;
			if (request.getParameter("azienda") == null) {
				// tirocinio interno
				if (conferma.equalsIgnoreCase("si")) {
					// tirocinio interno
					TutorAccademico tutor = (TutorAccademico) session.getAttribute("utenteLoggato");
					risposta = new TirocinioInternoDAO().updateFirmaTrue(true, idTirocinio,
							tutor.getIdTutorAccademico());
					request.setAttribute("esito", risposta);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF/viewListaRichiesteTirocinioInterno.jsp");
					dispatcher.forward(request, response);

				} else if (conferma.equalsIgnoreCase("no")) {
					// cambia status in rifiutato
					TutorAccademico tutor = (TutorAccademico) session.getAttribute("utenteLoggato");
					risposta = new TirocinioInternoDAO().updateFirmaFalse(false, idTirocinio,
							tutor.getIdTutorAccademico());
					request.setAttribute("esito", risposta);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF/viewListaRichiesteTirocinioInterno.jsp");
					dispatcher.forward(request, response);
				}
			} else if (request.getParameter("azienda").equalsIgnoreCase("azienda")) {
				// tirocinio esterno
				// devo vedere chi è loggato

				if (session.getAttribute("utenteLoggato").equals(TutorAccademico.class.getName())) {
					// è loggato un tutor accademico
					TutorAccademico tutor = (TutorAccademico) session.getAttribute("utenteLoggato");
					risposta = new TirocinioEsternoDAO().updateFirmaTrueTutorAccademico(true, idTirocinio,
							tutor.getIdTutorAccademico());
					request.setAttribute("esito", risposta);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF/viewRichiesteTirocinioEsterno.jsp");
					dispatcher.forward(request, response);

				} else if (conferma.equalsIgnoreCase("no")) {
					// cambia status in rifiutato
					TutorAccademico tutor = (TutorAccademico) session.getAttribute("utenteLoggato");
					risposta = new TirocinioEsternoDAO().updateFirmaFalseTutorAccademico(false, idTirocinio,
							tutor.getIdTutorAccademico());
					request.setAttribute("esito", risposta);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF/viewRichiesteTirocinioEsterno.jsp");
					dispatcher.forward(request, response);

				} else if (session.getAttribute("utenteLoggato").equals(TutorAziendale.class.getName())) {
					// loggato un tutor aziendale
					TutorAziendale tutor = (TutorAziendale) session.getAttribute("utenteLoggato");
					risposta = new TirocinioEsternoDAO().updateFirmaTrueAziendale(true, idTirocinio, tutor.getId());
					request.setAttribute("esito", risposta);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF/viewRichiesteTirocinioEsterno.jsp");
					dispatcher.forward(request, response);

				} else if (conferma.equalsIgnoreCase("no")) {
					// cambia status in rifiutato
					TutorAziendale tutor = (TutorAziendale) session.getAttribute("utenteLoggato");
					risposta = new TirocinioEsternoDAO().updateFirmaFalseAziendale(false, idTirocinio, tutor.getId());
					request.setAttribute("esito", risposta);
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
			// è loggata una persona non autorizzarata
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/permissionDenied.jsp");
			dispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
