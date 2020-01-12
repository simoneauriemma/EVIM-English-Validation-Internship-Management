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

import controller.GestioneAutenticazione.*;

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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		String nomeutente = (String) session.getAttribute("type");
		String descrizione= (String) request.getParameter("descrizione");
		String status="in approvazione";
		//User studente = (User) session.getAttribute("studente"); //mi sa che nin va bene
		
		String emailstudente= (String) request.getParameter("emailstudente");
		//ArrayList<User> studenti = new ArrayList<User>();
		
		
		boolean result=false;
		
		//se l'utente loggato ed è il tutor aziendale
		if(nomeutente!=null && nomeutente.contentEquals("tutoraziendale")) {
			TutorAziendale utente= (TutorAziendale) session.getAttribute("utenteLoggato");

			//controllo se devo inviare la relazione o se devo reindirizzare alla giusta pagina per compilare la relazione
			if(descrizione!=null && emailstudente!=null) {
				
				result=RelazioneDAO.insertRelezione(utente.getId(), emailstudente, descrizione, status);
				request.setAttribute("resultrelazione", result);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/home.jsp");
				dispatcher.forward(request, response);
			} 
			else { 			//reindirizzo a compila relazione
				//studenti=RelazioneDAO.doRetriveStudenti(utente.getId());
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("ListaTirocini");
				request.setAttribute("resultrelazione", false);
				dispatcher.forward(request, response);
			}
		}
		else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/login.jsp");
			request.setAttribute("resultrelazione", false);
			dispatcher.forward(request, response);
		}
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
