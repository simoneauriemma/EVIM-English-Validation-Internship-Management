package controller.GestioneProposta;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Azienda;
import model.Proposta;
import model.PropostaDAO;
import model.TutorAccademico;
import model.TutorAziendale;
import model.TutorAziendaleDAO;

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
			String sede=request.getParameter("sede");
			String temaAmbito=request.getParameter("tema_ambito");
			String materialeRisorse=request.getParameter("materiale_risorse");
			// non adatto per lo studente,pdcd,ufficio carriere
			if(tipoUtente.equalsIgnoreCase("model.User")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/permissiondenied.jsp");
				dispatcher.forward(request, response);
			}

			// tirocinio interno
			else if(tipoUtente.equalsIgnoreCase("model.tutoraccademico")) {
				PropostaDAO.modificationPropostaInterno(obiettivo, sede, temaAmbito, materialeRisorse, idProposta);
				
				request.setAttribute("modifica", true);
				RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/home.jsp");
				dispatcher.forward(request, response);
				
			}
			//tirocinio esterno
			else if(tipoUtente.equalsIgnoreCase("model.azienda")) {
				int idTutorAziendale=Integer.parseInt(request.getParameter("tutorAziendale"));
					
				PropostaDAO.modificationPropostaEsterno(obiettivo, sede, temaAmbito, materialeRisorse, idTutorAziendale, idProposta);
				
				request.setAttribute("modifica", true);
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
