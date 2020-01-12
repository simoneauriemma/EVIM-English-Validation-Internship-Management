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
import model.Azienda;
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
				// è loggato un tipo "user"
				// a causa dello usertype, devo verificare se è loggato esattamente uno studente
				User studente = (User) sessione.getAttribute("utenteLoggato");
				// è loggato un utente
				if (studente.getUserType() == 0) {

					// verifico se l'utente è magistrale o triennale, in quanto per gli studenti
					// magistrali i tirocini possono essere solamente esterni
					if (studente.getCorso().equalsIgnoreCase("magistrale")) {
						ArrayList<TirocinioQueryEsternoStudente> tirocinioEsterno = new TirocinioEsternoDAO()
								.doRetriveAllByStudent(studente.getEmail());
						request.setAttribute("arrayTirocinioEsterno", tirocinioEsterno);
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("WEB-INF/viewRichiestaTiricinioStudente.jsp");
						dispatcher.forward(request, response);
					} else {
						// studenti triennali, quindi i tirocini possono essere sia interni che esterni

						ArrayList<TirocinioQueryInternoStudente> tirocinioInterno = new TirocinioInternoDAO()
								.doRetriveAllByStudent(studente.getEmail());

						ArrayList<TirocinioQueryEsternoStudente> tirocinioEsterno = new TirocinioEsternoDAO()
								.doRetriveAllByStudent(studente.getEmail());
						// System.out.println(tirocinioEsterno);
						request.setAttribute("arrayTirocinioEsterno", tirocinioEsterno);
						request.setAttribute("arrayTirocinioInterno", tirocinioInterno);

						RequestDispatcher dispatcher = request
								.getRequestDispatcher("WEB-INF/viewRichiestaTiricinioStudente.jsp");
						dispatcher.forward(request, response);

					}

				} else if (studente.getUserType() == 2) {
					// è loggato il PdCD

					ArrayList<TirocinioInterno> tirocinioInterno = new TirocinioInternoDAO()
							.doRetriveAllValutazionePdCD();
					ArrayList<TirocinioEsterno> tirocinioEsterno = new TirocinioEsternoDAO()
							.doRetriveAllValutazionePdCD();

					request.setAttribute("arrayTirocinioEsterno", tirocinioEsterno);
					request.setAttribute("arrayTirocinioInterno", tirocinioInterno);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF/viewListaRichiesteTirocinio.jsp");
					dispatcher.forward(request, response);
				} else if (studente.getUserType() == 1) {

					// loggato l'ufficio carriere
					ArrayList<TirocinioInterno> tirocinioInterno = new TirocinioInternoDAO()
							.doRetriveAllValutazionePdCD();
					ArrayList<TirocinioEsterno> tirocinioEsterno = new TirocinioEsternoDAO()
							.doRetriveAllValutazionePdCD();

					request.setAttribute("arrayTirocinioEsterno", tirocinioEsterno);
					request.setAttribute("arrayTirocinioInterno", tirocinioInterno);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF/viewListaRichiesteTirocinio.jsp");
					dispatcher.forward(request, response);
				}

			} else if (tipoUtente.equalsIgnoreCase("model.tutoraccademico")) {
				// viewListaRichiesteTirocinioInterno
				// 2 arraylist

				TutorAccademico accademico = (TutorAccademico) sessione.getAttribute("utenteLoggato");

				ArrayList<TirocinioQueryInternoTutorAcc> tirocinioInterno = new TirocinioInternoDAO()
						.doRetriveAllByTutorAcc(accademico.getIdTutorAccademico());
				ArrayList<TirocinioQueryEsternoTutorAcc> tirocinioEsterno = new TirocinioEsternoDAO()
						.doRetriveAllByTutorAcc(accademico.getIdTutorAccademico());

				request.setAttribute("arrayTirocinioEsterno", tirocinioEsterno);
				request.setAttribute("arrayTirocinioInterno", tirocinioInterno);

				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/viewListaRichiesteTirocinio.jsp");
				dispatcher.forward(request, response);

			} else if (tipoUtente.equalsIgnoreCase("model.tutoraziendale")) {
				// viewListaRichiesteTirocinioEsterno
				// solo 1 arraylist
				TutorAziendale aziendale = (TutorAziendale) sessione.getAttribute("utenteLoggato");
				ArrayList<TirocinioQueryEsternoTutorAz> tirocinioEsterno = new TirocinioEsternoDAO()
						.doRetriveAllByTutorAz(aziendale.getId());
				request.setAttribute("arrayTirocinioEsterno", tirocinioEsterno);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/viewListaRichiesteTirocinio.jsp");
				dispatcher.forward(request, response);
			}
			
			else if (tipoUtente.equalsIgnoreCase("model.azienda")) {
				// viewListaRichiesteTirocinioEsterno
				// solo 1 arraylist
				Azienda azienda = (Azienda) sessione.getAttribute("utenteLoggato");
				
				ArrayList<TirocinioQueryEsternoTutorAz> tirocinioEsterno = new TirocinioEsternoDAO()
						.doRetriveAllByAzienda(azienda.getID_Azienda());
				
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

	public static class TirocinioQueryInternoStudente {
		int ID_TirocinioInterno, oreTotali, numeroCFU, ID_Proposta;
		String EMAIL, nomeTutorAcc, cognomeTutorAcc, data, status;
		boolean firmaPdCD, firmaTutorAccademico;

		public TirocinioQueryInternoStudente() {
		}

		public TirocinioQueryInternoStudente(int iD_TirocinioInterno, int oreTotali, int numeroCFU, int iD_Proposta,
				String eMAIL, String nomeTutorAcc, String cognomeTutorAcc, String data, String status,
				boolean firmaPdCD, boolean firmaTutorAccademico) {
			ID_TirocinioInterno = iD_TirocinioInterno;
			this.oreTotali = oreTotali;
			this.numeroCFU = numeroCFU;
			ID_Proposta = iD_Proposta;
			EMAIL = eMAIL;
			this.nomeTutorAcc = nomeTutorAcc;
			this.cognomeTutorAcc = cognomeTutorAcc;
			this.data = data;
			this.status = status;
			this.firmaPdCD = firmaPdCD;
			this.firmaTutorAccademico = firmaTutorAccademico;
		}

		public int getID_TirocinioInterno() {
			return ID_TirocinioInterno;
		}

		public void setID_TirocinioInterno(int iD_TirocinioInterno) {
			ID_TirocinioInterno = iD_TirocinioInterno;
		}

		public int getOreTotali() {
			return oreTotali;
		}

		public void setOreTotali(int oreTotali) {
			this.oreTotali = oreTotali;
		}

		public int getNumeroCFU() {
			return numeroCFU;
		}

		public void setNumeroCFU(int numeroCFU) {
			this.numeroCFU = numeroCFU;
		}

		public int getID_Proposta() {
			return ID_Proposta;
		}

		public void setID_Proposta(int iD_Proposta) {
			ID_Proposta = iD_Proposta;
		}

		public String getEMAIL() {
			return EMAIL;
		}

		public void setEMAIL(String eMAIL) {
			EMAIL = eMAIL;
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

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public boolean isFirmaPdCD() {
			return firmaPdCD;
		}

		public void setFirmaPdCD(boolean firmaPdCD) {
			this.firmaPdCD = firmaPdCD;
		}

		public boolean isFirmaTutorAccademico() {
			return firmaTutorAccademico;
		}

		public void setFirmaTutorAccademico(boolean firmaTutorAccademico) {
			this.firmaTutorAccademico = firmaTutorAccademico;
		}

	}

	public static class TirocinioQueryEsternoStudente {
		int ID_TirocinioEsterno, oreTotali, CFU, ID_Proposta;
		String nomeTutorAcc, cognomeTutorAcc, nomeTutorAz, cognomeTutorAz, data, status, email;
		boolean firmaAzienda, firmaTutorAziendale, firmaTutorAccademico, firmaPdCD;

		public TirocinioQueryEsternoStudente(int iD_TirocinioEsterno, int oreTotali, int cFU, String nomeTutorAcc,
				String cognomeTutorAcc, String nomeTutorAz, String cognomeTutorAz, String data, String status,
				String email, boolean firmaAzienda, boolean firmaTutorAziendale, boolean firmaTutorAccademico,
				boolean firmaPdCD, int IDproposta) {
			ID_TirocinioEsterno = iD_TirocinioEsterno;
			this.oreTotali = oreTotali;
			CFU = cFU;
			this.nomeTutorAcc = nomeTutorAcc;
			this.cognomeTutorAcc = cognomeTutorAcc;
			this.nomeTutorAz = nomeTutorAz;
			this.cognomeTutorAz = cognomeTutorAz;
			this.data = data;
			this.status = status;
			this.email = email;
			this.firmaAzienda = firmaAzienda;
			this.firmaTutorAziendale = firmaTutorAziendale;
			this.firmaTutorAccademico = firmaTutorAccademico;
			this.firmaPdCD = firmaPdCD;
			this.ID_Proposta = IDproposta;
		}

		public TirocinioQueryEsternoStudente() {
		}

		public int getID_TirocinioEsterno() {
			return ID_TirocinioEsterno;
		}

		public void setID_TirocinioEsterno(int iD_TirocinioEsterno) {
			ID_TirocinioEsterno = iD_TirocinioEsterno;
		}

		public int getOreTotali() {
			return oreTotali;
		}

		public void setOreTotali(int oreTotali) {
			this.oreTotali = oreTotali;
		}

		public int getCFU() {
			return CFU;
		}

		public void setCFU(int cFU) {
			CFU = cFU;
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

		public String getNomeTutorAz() {
			return nomeTutorAz;
		}

		public void setNomeTutorAz(String nomeTutorAz) {
			this.nomeTutorAz = nomeTutorAz;
		}

		public String getCognomeTutorAz() {
			return cognomeTutorAz;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public void setCognomeTutorAz(String cognomeTutorAz) {
			this.cognomeTutorAz = cognomeTutorAz;
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public boolean isFirmaAzienda() {
			return firmaAzienda;
		}

		public void setFirmaAzienda(boolean firmaAzienda) {
			this.firmaAzienda = firmaAzienda;
		}

		public boolean isFirmaTutorAziendale() {
			return firmaTutorAziendale;
		}

		public void setFirmaTutorAziendale(boolean firmaTutorAziendale) {
			this.firmaTutorAziendale = firmaTutorAziendale;
		}

		public boolean isFirmaTutorAccademico() {
			return firmaTutorAccademico;
		}

		public void setFirmaTutorAccademico(boolean firmaTutorAccademico) {
			this.firmaTutorAccademico = firmaTutorAccademico;
		}

		public boolean isFirmaPdCD() {
			return firmaPdCD;
		}

		public void setFirmaPdCD(boolean firmaPdCD) {
			this.firmaPdCD = firmaPdCD;
		}

		public int getID_Proposta() {
			return ID_Proposta;
		}

		public void setID_Proposta(int iD_Proposta) {
			ID_Proposta = iD_Proposta;
		}

	}

	public static class TirocinioQueryEsternoTutorAcc {

		int ID_TirocinioEsterno, oreTotali, CFU, ID_Proposta;
		String nomeTutorAz, cognomeTutorAz, nomeStudente, cognomeStudente, data, status, email;
		boolean firmaAzienda, firmaTutorAziendale, firmaTutorAccademico, firmaPdCD;

		public TirocinioQueryEsternoTutorAcc() {
		}

		public TirocinioQueryEsternoTutorAcc(int iD_TirocinioEsterno, int oreTotali, int cFU, int iD_Proposta,
				String nomeTutorAz, String cognomeTutorAz, String nomeStudente, String cognomeStudente, String data,
				String status, String email, boolean firmaAzienda, boolean firmaTutorAziendale,
				boolean firmaTutorAccademico, boolean firmaPdCD) {
			super();
			ID_TirocinioEsterno = iD_TirocinioEsterno;
			this.oreTotali = oreTotali;
			CFU = cFU;
			ID_Proposta = iD_Proposta;
			this.nomeTutorAz = nomeTutorAz;
			this.cognomeTutorAz = cognomeTutorAz;
			this.nomeStudente = nomeStudente;
			this.cognomeStudente = cognomeStudente;
			this.data = data;
			this.status = status;
			this.email = email;
			this.firmaAzienda = firmaAzienda;
			this.firmaTutorAziendale = firmaTutorAziendale;
			this.firmaTutorAccademico = firmaTutorAccademico;
			this.firmaPdCD = firmaPdCD;
		}

		public int getID_TirocinioEsterno() {
			return ID_TirocinioEsterno;
		}

		public void setID_TirocinioEsterno(int iD_TirocinioEsterno) {
			ID_TirocinioEsterno = iD_TirocinioEsterno;
		}

		public int getOreTotali() {
			return oreTotali;
		}

		public void setOreTotali(int oreTotali) {
			this.oreTotali = oreTotali;
		}

		public int getCFU() {
			return CFU;
		}

		public void setCFU(int cFU) {
			CFU = cFU;
		}

		public String getNomeTutorAz() {
			return nomeTutorAz;
		}

		public void setNomeTutorAz(String nomeTutorAz) {
			this.nomeTutorAz = nomeTutorAz;
		}

		public String getCognomeTutorAz() {
			return cognomeTutorAz;
		}

		public void setCognomeTutorAz(String cognomeTutorAz) {
			this.cognomeTutorAz = cognomeTutorAz;
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

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public boolean isFirmaAzienda() {
			return firmaAzienda;
		}

		public void setFirmaAzienda(boolean firmaAzienda) {
			this.firmaAzienda = firmaAzienda;
		}

		public boolean isFirmaTutorAziendale() {
			return firmaTutorAziendale;
		}

		public void setFirmaTutorAziendale(boolean firmaTutorAziendale) {
			this.firmaTutorAziendale = firmaTutorAziendale;
		}

		public boolean isFirmaTutorAccademico() {
			return firmaTutorAccademico;
		}

		public void setFirmaTutorAccademico(boolean firmaTutorAccademico) {
			this.firmaTutorAccademico = firmaTutorAccademico;
		}

		public boolean isFirmaPdCD() {
			return firmaPdCD;
		}

		public void setFirmaPdCD(boolean firmaPdCD) {
			this.firmaPdCD = firmaPdCD;
		}

		public int getID_Proposta() {
			return ID_Proposta;
		}

		public void setID_Proposta(int iD_Proposta) {
			ID_Proposta = iD_Proposta;
		}

	}

	public static class TirocinioQueryEsternoTutorAz {

		int ID_TirocinioEsterno, oreTotali, CFU, ID_Proposta;
		String nomeTutorAcc, cognomeTutorAcc, nomeStudente, cognomeStudente, data, status, email;
		boolean firmaAzienda, firmaTutorAziendale, firmaTutorAccademico, firmaPdCD;

		public TirocinioQueryEsternoTutorAz() {
		}

		public TirocinioQueryEsternoTutorAz(int iD_TirocinioEsterno, int oreTotali, int cFU, int iD_Proposta,
				String nomeTutorAcc, String cognomeTutorAcc, String nomeStudente, String cognomeStudente, String data,
				String status, String email, boolean firmaAzienda, boolean firmaTutorAziendale,
				boolean firmaTutorAccademico, boolean firmaPdCD) {
			super();
			ID_TirocinioEsterno = iD_TirocinioEsterno;
			this.oreTotali = oreTotali;
			CFU = cFU;
			ID_Proposta = iD_Proposta;
			this.nomeTutorAcc = nomeTutorAcc;
			this.cognomeTutorAcc = cognomeTutorAcc;
			this.nomeStudente = nomeStudente;
			this.cognomeStudente = cognomeStudente;
			this.data = data;
			this.status = status;
			this.email = email;
			this.firmaAzienda = firmaAzienda;
			this.firmaTutorAziendale = firmaTutorAziendale;
			this.firmaTutorAccademico = firmaTutorAccademico;
			this.firmaPdCD = firmaPdCD;
		}

		public int getID_TirocinioEsterno() {
			return ID_TirocinioEsterno;
		}

		public void setID_TirocinioEsterno(int iD_TirocinioEsterno) {
			ID_TirocinioEsterno = iD_TirocinioEsterno;
		}

		public int getOreTotali() {
			return oreTotali;
		}

		public void setOreTotali(int oreTotali) {
			this.oreTotali = oreTotali;
		}

		public int getCFU() {
			return CFU;
		}

		public void setCFU(int cFU) {
			CFU = cFU;
		}

		public int getID_Proposta() {
			return ID_Proposta;
		}

		public void setID_Proposta(int iD_Proposta) {
			ID_Proposta = iD_Proposta;
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

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public boolean isFirmaAzienda() {
			return firmaAzienda;
		}

		public void setFirmaAzienda(boolean firmaAzienda) {
			this.firmaAzienda = firmaAzienda;
		}

		public boolean isFirmaTutorAziendale() {
			return firmaTutorAziendale;
		}

		public void setFirmaTutorAziendale(boolean firmaTutorAziendale) {
			this.firmaTutorAziendale = firmaTutorAziendale;
		}

		public boolean isFirmaTutorAccademico() {
			return firmaTutorAccademico;
		}

		public void setFirmaTutorAccademico(boolean firmaTutorAccademico) {
			this.firmaTutorAccademico = firmaTutorAccademico;
		}

		public boolean isFirmaPdCD() {
			return firmaPdCD;
		}

		public void setFirmaPdCD(boolean firmaPdCD) {
			this.firmaPdCD = firmaPdCD;
		}

	}

	public static class TirocinioQueryInternoTutorAcc {
		int ID_TirocinioInterno, oreTotali, numeroCFU, ID_Proposta;
		String EMAIL, nomeStud, cognomeStud, data, status;
		boolean firmaPdCD, firmaTutorAccademico;

		public TirocinioQueryInternoTutorAcc(int iD_TirocinioInterno, int oreTotali, int numeroCFU, int iD_Proposta,
				String eMAIL, String nomeStud, String cognomeStud, String data, String status, boolean firmaPdCD,
				boolean firmaTutorAccademico) {
			super();
			ID_TirocinioInterno = iD_TirocinioInterno;
			this.oreTotali = oreTotali;
			this.numeroCFU = numeroCFU;
			ID_Proposta = iD_Proposta;
			EMAIL = eMAIL;
			this.nomeStud = nomeStud;
			this.cognomeStud = cognomeStud;
			this.data = data;
			this.status = status;
			this.firmaPdCD = firmaPdCD;
			this.firmaTutorAccademico = firmaTutorAccademico;
		}

		public TirocinioQueryInternoTutorAcc() {
		}

		public int getID_TirocinioInterno() {
			return ID_TirocinioInterno;
		}

		public void setID_TirocinioInterno(int iD_TirocinioInterno) {
			ID_TirocinioInterno = iD_TirocinioInterno;
		}

		public int getOreTotali() {
			return oreTotali;
		}

		public void setOreTotali(int oreTotali) {
			this.oreTotali = oreTotali;
		}

		public int getNumeroCFU() {
			return numeroCFU;
		}

		public void setNumeroCFU(int numeroCFU) {
			this.numeroCFU = numeroCFU;
		}

		public int getID_Proposta() {
			return ID_Proposta;
		}

		public void setID_Proposta(int iD_Proposta) {
			ID_Proposta = iD_Proposta;
		}

		public String getEMAIL() {
			return EMAIL;
		}

		public void setEMAIL(String eMAIL) {
			EMAIL = eMAIL;
		}

		public String getNomeStud() {
			return nomeStud;
		}

		public void setNomeStud(String nomeStud) {
			this.nomeStud = nomeStud;
		}

		public String getCognomeStud() {
			return cognomeStud;
		}

		public void setCognomeStud(String cognomeStud) {
			this.cognomeStud = cognomeStud;
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public boolean isFirmaPdCD() {
			return firmaPdCD;
		}

		public void setFirmaPdCD(boolean firmaPdCD) {
			this.firmaPdCD = firmaPdCD;
		}

		public boolean isFirmaTutorAccademico() {
			return firmaTutorAccademico;
		}

		public void setFirmaTutorAccademico(boolean firmaTutorAccademico) {
			this.firmaTutorAccademico = firmaTutorAccademico;
		}
	}
}
