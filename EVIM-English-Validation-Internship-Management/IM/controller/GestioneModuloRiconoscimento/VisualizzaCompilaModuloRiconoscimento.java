package controller.GestioneModuloRiconoscimento;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

/**
 * Questa servlet permette di reindirizzare alla pagina compila modulo di riconoscimento attività lavorativa con dei campi gia precompilati.
 * @author Antonio Giano
 */
@WebServlet("/VisualizzaCompilaModuloRiconoscimento")
public class VisualizzaCompilaModuloRiconoscimento extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione=request.getSession();
		// controllo se è loggato l'utente altrimenti reindirizzo alla pagina login
		if (sessione.getAttribute("utenteLoggato") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/login.jsp");
			dispatcher.forward(request, response);
		}
		else {
			String tipoUtente=sessione.getAttribute("utenteLoggato").getClass().getName();
			User utente=(User) sessione.getAttribute("utenteLoggato");
			// servlet non adatta per i tutor accademici, per le aziende e per i tutor aziendali
			if(!tipoUtente.equalsIgnoreCase("model.User")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("permissionDenied.jsp");
				dispatcher.forward(request, response);
			}
			// studente--> in questo modo nel momento in cui lo studente compila il modulo, troverò i dati anagrafici già precompilati
			else if(utente.getUserType()==0){
				request.setAttribute("studente", utente);
				request.getRequestDispatcher("compilaModuloRiconoscimento.jsp").forward(request, response);
			}
			//non adatto ufficio carriera e PdCD
			else if(utente.getUserType()==1 || utente.getUserType()==2) {
				request.getRequestDispatcher("permissionDenied.jsp").forward(request, response);
 			}		
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
