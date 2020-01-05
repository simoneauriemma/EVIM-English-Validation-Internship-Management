package controller.GestioneTirocinio;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.BaseServlet;
import model.RelazioneDAO;
import model.TutorAziendale;
import model.TutorAziendaleDAO;
import model.User;

/**
 * Servlet implementation class CreaRelazione
 */
@WebServlet("/CreaRelazione")
public class CreaRelazione extends BaseServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		String nomeutente = (String) session.getAttribute("type");
		String descrizione= (String) request.getParameter("descrizione");
		String status="in approvazione";
		String emailstudente= (String) request.getParameter("emailstudente");
		ArrayList<User> studenti = new ArrayList<User>();
		boolean result=false;
		TutorAziendale utente= (TutorAziendale) session.getAttribute("utenteLoggato");

		//se l'utente loggato è il tutor aziendale
		if(nomeutente!=null&&nomeutente.contentEquals("tutoraziendale")) {
			
			//controllo se devo inviare la relazione o se devo reindirizzare alla giusta pagina per compilare la relazione
			if(descrizione!=null&&emailstudente!=null) {
				
				result=RelazioneDAO.insertRelezione(utente.getId(), emailstudente, descrizione, status);
				request.setAttribute("resultrelazione", result);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/home.jsp");
				dispatcher.forward(request, response);
			}
			else { 			//reindirizzo a compila relazione
				studenti=RelazioneDAO.doRetriveStudenti(utente.getId());
				session.setAttribute("studenti", studenti);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/compilaRelazione.jsp");
				dispatcher.forward(request, response);
			}
		}
		else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/login.jsp");
			dispatcher.forward(request, response);
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
