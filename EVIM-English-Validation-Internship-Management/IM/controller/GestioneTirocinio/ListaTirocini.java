package controller.GestioneTirocinio;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.TirocinioInternoDAO;
import model.TirocinioEsterno;
import model.TirocinioEsternoDAO;
import model.TirocinioInterno;
import model.User;
import model.UserDAO;

/**
 * Servlet implementation class ListaTirocini
 */
@WebServlet("/ListaTirocini")
public class ListaTirocini extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<TirocinioInterno> interno= new ArrayList<TirocinioInterno>();
		ArrayList<TirocinioEsterno> esterno= new ArrayList<TirocinioEsterno>();
		HttpSession session= request.getSession();
		//
		if(session.getAttribute("utenteLoggato")!=null) {
			
			//studente
			if(session.getAttribute("type").equals("studente")) {
				
				User studente=(User) session.getAttribute("utenteLoggato");
				interno= new TirocinioInternoDAO().doRetriveTirocinioInSvolgimentoStudente(studente.getEmail());
				esterno= new TirocinioEsternoDAO().doRetriveTirocinioInSvolgimentoStudente(studente.getEmail());
				if(!interno.isEmpty()&& interno!=null) {
					session.setAttribute("interno", interno);
					
				}
				if(!esterno.isEmpty()&& esterno!=null) {
					session.setAttribute("esterno", esterno);
					
				}
				
			}
			
			//Tutor Accademico
			
			else if (session.getAttribute("type").equals("tutoraccademico")) {
				
			}
			
			// Tutor Aziendale
			else if(session.getAttribute("type").equals("tutoraaziendale")) {
				
			}
			
			//Pdcd
			else if(session.getAttribute("type").equals("pdcd")) {
				
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
