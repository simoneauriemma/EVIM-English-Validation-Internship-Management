package controller.GestioneRegistroTirocinio;

import controller.BaseServlet;
import model.AttivitaDAO;
import model.Attività;
import model.TirocinioEsterno;
import model.TirocinioEsternoDAO;
import model.TirocinioInterno;
import model.TirocinioInternoDAO;
import model.TutorAccademico;
import model.TutorAziendale;
import model.User;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author Simone Auriemma Questa servlet permette di visualizzare il registro
 *         di tirocinio allo studente, tutor accademico e tutor aziendale
 *
 */

@SuppressWarnings("serial")
@WebServlet("/VisualizzaRegistroTirocinio")
public class VisualizzareRegistroTirocinio extends BaseServlet {

	public VisualizzareRegistroTirocinio() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// verifico il tipo di utente loggato dalla sessione
		HttpSession session = request.getSession();

		String tipoUtente = session.getAttribute("utenteLoggato").getClass().getName();

		if (tipoUtente.equalsIgnoreCase("model.user")) {
			// loggato un USER, a causa dell'user type, può esssere il PdCD, uno studente o
			// l'ufficio carriere
			User user = (User) session.getAttribute("utenteLoggato");
			// verifico l'user type, 0 con studente, 1 PdCD, 2 Ufficio Carriere
			if (user.getUserType() == 0) {
				// si tratta di uno studente
				if (user.getCorso().equalsIgnoreCase("magistrale")) {
					// solo esterno
					ArrayList<Attività> listaTirociniEsterno = new AttivitaDAO().doRetriveAllEsterno(user.getEmail());

					request.setAttribute("listaAttivitaEsterno", listaTirociniEsterno);
					RequestDispatcher dispatcher = request.getRequestDispatcher("registroTirocinio(studente).jsp");
					dispatcher.forward(request, response);
				} else {
					// studente triennale

					ArrayList<Attività> listaTirociniEsterno = new AttivitaDAO().doRetriveAllEsterno(user.getEmail());
					ArrayList<Attività> listaTirociniInterno = new AttivitaDAO().doRetriveAllInterno(user.getEmail());

					request.setAttribute("listaAttivitaEsterno", listaTirociniEsterno);
					request.setAttribute("listaAttivitaInterno", listaTirociniInterno);

					RequestDispatcher dispatcher = request.getRequestDispatcher("registroTirocinio(studente).jsp");
					dispatcher.forward(request, response);

				}
			}
		} else if (tipoUtente.equalsIgnoreCase("model.TutorAccademico")) {

			TutorAccademico accademico = (TutorAccademico) session.getAttribute("utenteLoggato");

			ArrayList<Attività> listaTirociniEsterno = new AttivitaDAO()
					.doRetriveAllEsternoTutorAcc(accademico.getIdTutorAccademico());
			ArrayList<Attività> listaTirociniInterno = new AttivitaDAO()
					.doRetriveAllInternoTutorAcc(accademico.getIdTutorAccademico());

			request.setAttribute("listaAttivitaEsterno", listaTirociniEsterno);
			request.setAttribute("listaAttivitaInterno", listaTirociniInterno);

			RequestDispatcher dispatcher = request.getRequestDispatcher("registroTirocinio(tutor).jsp");
			dispatcher.forward(request, response);

		} else if (tipoUtente.equalsIgnoreCase("model.TutorAziendale")) {
			TutorAziendale aziendale = (TutorAziendale) session.getAttribute("utenteLoggato");
			ArrayList<Attività> listaTirociniEsterno = new AttivitaDAO().doRetriveAllEsternoTutorAzi(aziendale.getId());

			request.setAttribute("listaAttivitaEsterno", listaTirociniEsterno);

			RequestDispatcher dispatcher = request.getRequestDispatcher("registroTirocinio(tutor).jsp");
			dispatcher.forward(request, response);

		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("permissionDenied.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
