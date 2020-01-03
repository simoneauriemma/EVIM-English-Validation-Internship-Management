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
import model.TutorAziendale;
import model.TutorAziendaleDAO;

/**
 * Servlet implementation class modificaModificaProposta
 */
@WebServlet("/VisualizzaModificaProposta")
public class VisualizzaModificaProposta extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
			// non adatto per lo studente,pdcd,ufficio carriere
			if(tipoUtente.equalsIgnoreCase("model.User")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/permissiondenied.jsp");
				dispatcher.forward(request, response);
			}
			// tirocinio interno
			else if(tipoUtente.equalsIgnoreCase("model.tutoraccademico")) {
				Proposta proposta=PropostaDAO.getPropostaInterno(idProposta);
				
				request.setAttribute("proposta", proposta);
				request.setAttribute("type", "tutoraccademico");
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/modificaProposta.jsp");
				dispatcher.forward(request, response);
				
			}
			//tirocinio esterno
			else if(tipoUtente.equalsIgnoreCase("model.azienda")) {
				Proposta proposta=PropostaDAO.getPropostaEsterno(idProposta);
				
				
				Azienda azienda=(Azienda) sessione.getAttribute("utenteLoggato");
				
				ArrayList<TutorAziendale> elencoTutorAziendali=TutorAziendaleDAO.getElencoTutorAziendali(azienda.getID_Azienda());
				int idTutorAziendale=proposta.getID_Tutor();
				TutorAziendale tutorAziendale=TutorAziendaleDAO.getInformationTutorAziendale(idTutorAziendale);
				
				request.setAttribute("tutorSelezionato", tutorAziendale);
				request.setAttribute("proposta", proposta);
				request.setAttribute("elencoTutorAziendali", elencoTutorAziendali);
				request.setAttribute("type", "azienda");
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/modificaProposta.jsp");
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
