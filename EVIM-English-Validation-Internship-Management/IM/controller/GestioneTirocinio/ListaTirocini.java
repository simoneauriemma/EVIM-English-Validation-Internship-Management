package controller.GestioneTirocinio;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.BaseServlet;
import model.TirocinioInternoDAO;
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
			
			System.out.println();

			ArrayList<TirocinioInterno> interno = new ArrayList<TirocinioInterno>();
			ArrayList<TirocinioEsterno> esterno = new ArrayList<TirocinioEsterno>();
			// studente
			if (session.getAttribute("type").equals("studente")) {
				

				User studente = (User) session.getAttribute("utenteLoggato");
				interno = new TirocinioInternoDAO().doRetriveTirocinioInSvolgimentoStudente(studente.getEmail());
				esterno = new TirocinioEsternoDAO().doRetriveTirocinioInSvolgimentoStudente(studente.getEmail());
				
				System.out.println(interno.toString());
				System.out.println(esterno.toString());

				//TO DO: DA MODIFICARE IN
				// if (!(interno.isEmpty())) {
				if ((interno.isEmpty())) {
					System.out.println("interno non è empty");
					// significa che ha fatto il tirocinio interno
					ArrayList<RegistroQuery> listaTirociniInterno = new TirocinioInternoDAO()
							.doRetriveTirocinioInSvolgimentoStudenteRegistro();

					request.setAttribute("registroQueryInterno", listaTirociniInterno);
					System.out.println("prima del disp");

					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/listaTirocini.jsp");
					dispatcher.forward(request, response);

				} else if (!(esterno.isEmpty())) {
					//esterno
					System.out.println("esterno non è empty");

					ArrayList<RegistroQuery> listaTirociniEsterno = new TirocinioEsternoDAO()
							.doRetriveTirocinioInSvolgimentoStudenteRegistro();

					request.setAttribute("registroQueryEsterno", listaTirociniEsterno);
					
					System.out.println("prima del disp");

					
					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/listaTirocini.jsp");
					dispatcher.forward(request, response);
				}
			}
			
			
			
			

			// Tutor Accademico

			else if (session.getAttribute("type").equals("tutoraccademico")) {

			}

			// Tutor Aziendale
			else if (session.getAttribute("type").equals("tutoraaziendale")) {

			}

			// Pdcd
			else if (session.getAttribute("type").equals("pdcd")) {

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
		private int ID_Tirocinio, NumeroCFU, OreTotali, ID_Registro;
		boolean FirmaResponsabile;
		String status;

		public RegistroQuery() {
		}

		public RegistroQuery(int iD_Tirocinio, int numeroCFU, int oreTotali, int iD_Registro, boolean firmaResponsabile,
				String status) {
			super();
			ID_Tirocinio = iD_Tirocinio;
			NumeroCFU = numeroCFU;
			OreTotali = oreTotali;
			ID_Registro = iD_Registro;
			FirmaResponsabile = firmaResponsabile;
			this.status = status;
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

		public boolean isFirmaResponsabile() {
			return FirmaResponsabile;
		}

		public void setFirmaResponsabile(boolean firmaResponsabile) {
			FirmaResponsabile = firmaResponsabile;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

	}

}
