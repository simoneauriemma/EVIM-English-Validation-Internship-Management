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
import model.TutorAziendale;
import model.TutorAziendaleDAO;

/**
 * @author Antonio Giano
 * Questa servlet prende i tutor aziendali facenti parti di un'azienda in modo da elencare tra i tanti tutor aziendali disponibili per il tirocinio esterno 
 */
@WebServlet("/visualizzaCreaProposta")
public class visualizzaCreaProposta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sessione=request.getSession();
		// controllo se è loggato l'utente altrimenti reindirizzo alla pagina login
		if (sessione.getAttribute("utenteLoggato") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		else {
			String tipoUtente=sessione.getAttribute("utenteLoggato").getClass().getName();
			// non adatto per lo studente,pdcd,ufficio carriere
			if(tipoUtente.equalsIgnoreCase("model.User")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/permissiondenied.jsp");
				dispatcher.forward(request, response);
			}
			
			// tirocinio interno
			else if(tipoUtente.equalsIgnoreCase("model.tutoraccademico")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/creazioneProposta.jsp");
				dispatcher.forward(request, response);
			}
			//tirocinio esterno
			else if(tipoUtente.equalsIgnoreCase("model.azienda")) {
				
				Azienda azienda=(Azienda) sessione.getAttribute("utenteLoggato");
				
				ArrayList<TutorAziendale> elencoTutorAziendali=TutorAziendaleDAO.getElencoTutorAziendali(azienda.getID_Azinda());
				
				
				
				request.setAttribute("elencoTutorAziendali", elencoTutorAziendali);
				request.setAttribute("type", "azienda");
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/creazioneProposta.jsp");
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
