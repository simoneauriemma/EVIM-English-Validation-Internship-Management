package controller.GestioneRegistroTirocinio;

import controller.GestioneAutenticazione.*;
import model.AttivitaDAO;
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

		if(session.getAttribute("utenteLoggato")==null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("permissionDenied.jsp");
			dispatcher.forward(request, response);
		}
		String tipoUtente = session.getAttribute("utenteLoggato").getClass().getName();

		if (tipoUtente.equalsIgnoreCase("model.User")) {
			// loggato un USER, a causa dell'user type, può esssere il PdCD, uno studente o
			// l'ufficio carriere
			User user = (User) session.getAttribute("utenteLoggato");
			// verifico l'user type, 0 con studente, 1 PdCD, 2 Ufficio Carriere
			if (user.getUserType() == 0) {
				// si tratta di uno studente
				if (user.getCorso().equalsIgnoreCase("magistrale")) {
					// solo esterno
					ArrayList<RegistroQuery> listaTirociniEsterno = new AttivitaDAO()
							.doRetriveAllEsterno(user.getEmail());

					request.setAttribute("listaAttivitaEsterno", listaTirociniEsterno);
					//VERIFICARE
					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/registroTirocinio(studente).jsp");
					dispatcher.forward(request, response);
				} else {
					// studente triennale

					ArrayList<RegistroQuery> listaTirociniEsterno = new AttivitaDAO()
							.doRetriveAllEsterno(user.getEmail());
					ArrayList<RegistroQuery> listaTirociniInterno = new AttivitaDAO()
							.doRetriveAllInterno(user.getEmail());

					request.setAttribute("listaAttivitaEsterno", listaTirociniEsterno);
					request.setAttribute("listaAttivitaInterno", listaTirociniInterno);

					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/registroTirocinio(studente).jsp");
					dispatcher.forward(request, response);

				}
			} else if (user.getUserType() == 2) {

				ArrayList<RegistroQuery> listaTirociniEsterno = new AttivitaDAO().doRetriveAllEsternoPdCD();
				ArrayList<RegistroQuery> listaTirociniInterno = new AttivitaDAO().doRetriveAllInternoPdCD();

				request.setAttribute("listaAttivitaEsterno", listaTirociniEsterno);
				request.setAttribute("listaAttivitaInterno", listaTirociniInterno);

				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/registroTirocinio(tutor).jsp");
				dispatcher.forward(request, response);

			}

		} else if (tipoUtente.equalsIgnoreCase("model.TutorAccademico")) {

			TutorAccademico accademico = (TutorAccademico) session.getAttribute("utenteLoggato");
			String EMAIL = request.getParameter("EMAIL");
			ArrayList<RegistroQuery> listaTirociniEsterno = new AttivitaDAO().doRetriveAllEsternoTutorAcc(EMAIL,
					accademico.getIdTutorAccademico());
			
			ArrayList<RegistroQuery> listaTirociniInterno = new AttivitaDAO().doRetriveAllInternoTutorAcc(EMAIL,
					accademico.getIdTutorAccademico());

			request.setAttribute("listaAttivitaEsterno", listaTirociniEsterno);
			request.setAttribute("listaAttivitaInterno", listaTirociniInterno);

			// INSERIRE LISTA ATTIVITA
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/registroTirocinio(tutor).jsp");
			dispatcher.forward(request, response);

		} else if (tipoUtente.equalsIgnoreCase("model.TutorAziendale")) {
			TutorAziendale aziendale = (TutorAziendale) session.getAttribute("utenteLoggato");
			String EMAIL = request.getParameter("EMAIL");
			if (EMAIL != null) {
				ArrayList<RegistroQuery> listaTirociniEsterno = new AttivitaDAO()
						.doRetriveAllEsternoTutorAzi(aziendale.getId(), EMAIL);

				request.setAttribute("listaAttivitaEsterno", listaTirociniEsterno);

				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/registroTirocinio(tutor).jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("permissionDenied.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("permissionDenied.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public static class RegistroQuery {
		String descrizione, data, nomeAzienda, nomeStudente, cognomeStudente, nomeTutorAcc, cognomeTutorAcc;
		int orarioIngresso, orarioUscita, oreRaggiunte, oreSvolte, idRegistro, idAttivita;
		boolean firmaResponsabile;

		public RegistroQuery() {
		}

		public RegistroQuery(String descrizione, String data, String nomeAzienda, String nomeStudente,
				String cognomeStudente, String nomeTutorAcc, String cognomeTutorAcc, int orarioIngresso,
				int orarioUscita, int oreRaggiunte, int oreSvolte, boolean firmaResponsabile) {
			this.descrizione = descrizione;
			this.data = data;
			this.nomeAzienda = nomeAzienda;
			this.nomeStudente = nomeStudente;
			this.cognomeStudente = cognomeStudente;
			this.nomeTutorAcc = nomeTutorAcc;
			this.cognomeTutorAcc = cognomeTutorAcc;
			this.orarioIngresso = orarioIngresso;
			this.orarioUscita = orarioUscita;
			this.oreRaggiunte = oreRaggiunte;
			this.oreSvolte = oreSvolte;
			this.firmaResponsabile = firmaResponsabile;
		}

		public String getDescrizione() {
			return descrizione;
		}
		
		

		public int getIdAttivita() {
			return idAttivita;
		}

		public void setIdAttivita(int idAttivita) {
			this.idAttivita = idAttivita;
		}

		public int getIdRegistro() {
			return idRegistro;
		}
		
		public void setIdRegistro(int id) {
			idRegistro=id;
		}
		public void setDescrizione(String descrizione) {
			this.descrizione = descrizione;
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

		public String getNomeAzienda() {
			return nomeAzienda;
		}

		public void setNomeAzienda(String nomeAzienda) {
			this.nomeAzienda = nomeAzienda;
		}

		public String getNomeStudente() {
			return nomeStudente;
		}

		public void setNomeStudente(String nomeStudente) {
			this.nomeStudente = nomeStudente;
		}

		public String getCognomeStudente() {
			return cognomeStudente;
		}

		public void setCognomeStudente(String cognomeStudente) {
			this.cognomeStudente = cognomeStudente;
		}

		public String getNomeTutorAcc() {
			return nomeTutorAcc;
		}

		public void setNomeTutorAcc(String nomeTutorAcc) {
			this.nomeTutorAcc = nomeTutorAcc;
		}

		public String getCognomeTutorAcc() {
			return cognomeTutorAcc;
		}

		public void setCognomeTutorAcc(String cognomeTutorAcc) {
			this.cognomeTutorAcc = cognomeTutorAcc;
		}

		public int getOrarioIngresso() {
			return orarioIngresso;
		}

		public void setOrarioIngresso(int orarioIngresso) {
			this.orarioIngresso = orarioIngresso;
		}

		public int getOrarioUscita() {
			return orarioUscita;
		}

		public void setOrarioUscita(int orarioUscita) {
			this.orarioUscita = orarioUscita;
		}

		public int getOreRaggiunte() {
			return oreRaggiunte;
		}

		public void setOreRaggiunte(int oreRaggiunte) {
			this.oreRaggiunte = oreRaggiunte;
		}

		public int getOreSvolte() {
			return oreSvolte;
		}

		public void setOreSvolte(int oreSvolte) {
			this.oreSvolte = oreSvolte;
		}

		public boolean isFirmaResponsabile() {
			return firmaResponsabile;
		}

		public void setFirmaResponsabile(boolean firmaResponsabile) {
			this.firmaResponsabile = firmaResponsabile;
		}

	}

}
