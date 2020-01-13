package controller.GestioneTirocinio;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.GestioneAutenticazione.*;

import model.TirocinioInternoDAO;
import model.TutorAccademico;
import model.TutorAziendale;
import model.TirocinioEsterno;
import model.TirocinioEsternoDAO;
import model.TirocinioInterno;
import model.User;

/**
 * Servlet implementation class ListaTirocini
 * 
 * Questa lista può essere visualizzata dal tutor accademico, aziendale e
 * studente
 * 
 */
@SuppressWarnings("serial")
@WebServlet("/ListaTirocini")
public class ListaTirocini extends BaseServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (session.getAttribute("utenteLoggato") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/login.jsp");
			dispatcher.forward(request, response);
		} else {

			ArrayList<RegistroQuery> interno = new ArrayList<RegistroQuery>();
			ArrayList<RegistroQuery> esterno = new ArrayList<RegistroQuery>();
			// studente
			if (session.getAttribute("type").equals("studente")) {

				User studente = (User) session.getAttribute("utenteLoggato");
				interno = new TirocinioInternoDAO()
						.doRetriveTirocinioInSvolgimentoStudenteRegistro(studente.getEmail());

				esterno = new TirocinioEsternoDAO()
						.doRetriveTirocinioInSvolgimentoStudenteRegistro(studente.getEmail());

				// significa che ha fatto il tirocinio interno

				// interno
				request.setAttribute("registroQueryInterno", interno);

				// esterno

				request.setAttribute("registroQueryEsterno", esterno);

				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/listaTirociniStudente.jsp");
				dispatcher.forward(request, response);

			}

			// Tutor Accademico

			else if (session.getAttribute("type").equals("tutoraccademico")) {

				TutorAccademico tutora = (TutorAccademico) session.getAttribute("utenteLoggato");
				interno = new TirocinioInternoDAO()
						.doRetriveTirocinioInSvolgimentoTutorAccRegistro(tutora.getIdTutorAccademico());

				esterno = new TirocinioEsternoDAO()
						.doRetriveTirocinioInSvolgimentoTutorAccRegistro(tutora.getIdTutorAccademico());
				System.out.println("sono un tutor accademico");
				System.out.println(interno.toString());
				System.out.println(esterno.toString());

				request.setAttribute("registroQueryInterno", interno);

				request.setAttribute("registroQueryEsterno", esterno);

				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/listaTirocini.jsp");
				dispatcher.forward(request, response);
			}

			// Tutor Aziendale
			else if (session.getAttribute("type").equals("tutoraaziendale")) {

				TutorAziendale tutoraz = (TutorAziendale) session.getAttribute("utenteLoggato");

				esterno = new TirocinioEsternoDAO().doRetriveTirocinioInSvolgimentoTutorAzRegistro(tutoraz.getId());

				System.out.println(esterno.toString());

				// esterno
				System.out.println("esterno non è empty");

				request.setAttribute("registroQueryEsterno", esterno);

				System.out.println("prima del disp");

				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/listaTirocini.jsp");
				dispatcher.forward(request, response);

			}

			// Pdcd
			else if (session.getAttribute("type").equals("pdcd")) {
				User pdcd = (User) session.getAttribute("utenteLoggato");

				interno = new TirocinioInternoDAO().doRetriveTirocinioInSvolgimentoPdcdRegistro();
				esterno = new TirocinioEsternoDAO().doRetriveTirocinioInSvolgimentoPdcdRegistro();

				request.setAttribute("registroQueryInterno", interno);

				// esterno

				request.setAttribute("registroQueryEsterno", esterno);

				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/listaTirocini.jsp");
				dispatcher.forward(request, response);

			}

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	// classe interna per la restituzione dell'oggetto per una specifica query
	public static class RegistroQuery {
		private int ID_Tirocinio, NumeroCFU, OreTotali, ID_Registro, OreRaggiunte;
		private String nome_responsabile, cognome_responsabile, registro_status, status;
		private boolean firmaResponsabile,firmaTutorAccademico;
		int ID_Questionario = 0;
		int ID_Relazione = 0;

		public RegistroQuery() {
		}

		public RegistroQuery(int iD_Tirocinio, int numeroCFU, int oreTotali, int iD_Registro, int oreRaggiunte,
				String nome_responsabile, String cognome_responsabile, String registro_status, String status,
				boolean firmaResponsabile, int iD_Questionario, int iD_Relazione) {
			super();
			ID_Tirocinio = iD_Tirocinio;
			NumeroCFU = numeroCFU;
			OreTotali = oreTotali;
			ID_Registro = iD_Registro;
			OreRaggiunte = oreRaggiunte;
			this.nome_responsabile = nome_responsabile;
			this.cognome_responsabile = cognome_responsabile;
			this.registro_status = registro_status;
			this.status = status;
			this.firmaResponsabile = firmaResponsabile;
			ID_Questionario = iD_Questionario;
			ID_Relazione = iD_Relazione;
		}

		public int getID_Tirocinio() {
			return ID_Tirocinio;
		}

		public void setID_Tirocinio(int iD_Tirocinio) {
			ID_Tirocinio = iD_Tirocinio;
		}

		public int getNumeroCFU() {
			return NumeroCFU;
		}

		public void setNumeroCFU(int numeroCFU) {
			NumeroCFU = numeroCFU;
		}

		public int getOreTotali() {
			return OreTotali;
		}

		public void setOreTotali(int oreTotali) {
			OreTotali = oreTotali;
		}

		public int getID_Registro() {
			return ID_Registro;
		}

		public void setID_Registro(int iD_Registro) {
			ID_Registro = iD_Registro;
		}

		public int getOreRaggiunte() {
			return OreRaggiunte;
		}

		public void setOreRaggiunte(int oreRaggiunte) {
			OreRaggiunte = oreRaggiunte;
		}

		public String getNome_responsabile() {
			return nome_responsabile;
		}

		public void setNome_responsabile(String nome_responsabile) {
			this.nome_responsabile = nome_responsabile;
		}

		public String getCognome_responsabile() {
			return cognome_responsabile;
		}

		public void setCognome_responsabile(String cognome_responsabile) {
			this.cognome_responsabile = cognome_responsabile;
		}

		public String getRegistro_status() {
			return registro_status;
		}

		public void setRegistro_status(String registro_status) {
			this.registro_status = registro_status;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public boolean isFirmaResponsabile() {
			return firmaResponsabile;
		}

		public void setFirmaResponsabile(boolean firmaResponsabile) {
			this.firmaResponsabile = firmaResponsabile;
		}

		public int getID_Questionario() {
			return ID_Questionario;
		}

		public void setID_Questionario(int iD_Questionario) {
			ID_Questionario = iD_Questionario;
		}

		public int getID_Relazione() {
			return ID_Relazione;
		}

		public void setID_Relazione(int iD_Relazione) {
			ID_Relazione = iD_Relazione;
		}

		public boolean isFirmaTutorAccademico() {
			return firmaTutorAccademico;
		}

		public void setFirmaTutorAccademico(boolean firmaTutorAccademico) {
			this.firmaTutorAccademico = firmaTutorAccademico;
		}

	}
}
