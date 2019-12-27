package controller.GestioneProposta;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.PropostaDAO;




/**
 * @author antonio
 * Questa servlet permette di modificare campi specifici di una proposta selezionata. 
 */
@WebServlet("/ModificaProposta")
public class ModificaProposta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione=request.getSession();
		int idProposta=Integer.parseInt(request.getParameter("idProposta"));
		// controllo se è loggato l'utente altrimenti reindirizzo alla pagina login
		if (sessione.getAttribute("utenteLoggato") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		else {
			String tipoUtente=sessione.getAttribute("utenteLoggato").getClass().getName();
			String obiettivo=request.getParameter("obiettivo");
			String competenze=request.getParameter("competenze");
			String attivita=request.getParameter("attivita");
			String modalita=request.getParameter("modalita");
			
			// non adatto per lo studente,pdcd,ufficio carriere
			if(tipoUtente.equalsIgnoreCase("model.User")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("permissionDenied.jsp");
				dispatcher.forward(request, response);
			}

			// tirocinio interno
			else if(tipoUtente.equalsIgnoreCase("model.tutoraccademico")) {
				if(PropostaDAO.modificationPropostaInterno(obiettivo, competenze, attivita, modalita, idProposta)==true)
					request.setAttribute("modifica", true);
				else
					request.setAttribute("modifica", false);
				RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/home.jsp");
				dispatcher.forward(request, response);
				
			}
			//tirocinio esterno
			else if(tipoUtente.equalsIgnoreCase("model.azienda")) {
				int idTutorAziendale=Integer.parseInt(request.getParameter("tutorAziendale"));
					
				if(PropostaDAO.modificationPropostaEsterno(obiettivo, competenze, attivita, modalita, idTutorAziendale, idProposta)==true)
					request.setAttribute("modifica", true);
				else
					request.setAttribute("modifica", false);
				RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/home.jsp");
				dispatcher.forward(request, response);
				
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
