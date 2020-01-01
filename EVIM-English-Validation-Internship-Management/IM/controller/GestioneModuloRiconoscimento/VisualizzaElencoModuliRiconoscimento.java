package controller.GestioneModuloRiconoscimento;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.Riconoscimento;
import model.RiconoscimentoDao;

/**
 * @author Antonio Giano
 * Questa servlet genera un elenco che viene differenziate in base al tipo di utente loggato. 
 * Nel caso dello studente viene generato un elenco dei propri moduli di riconoscimento di attività lavorativa. 
 * Nel caso del PDcD e dell'Ufficio Carriera viene generato un elenco dei moduli di riconoscimento di attività lavorativa di tutti gli studenti. 
 */
@WebServlet("/VisualizzaElencoModuliRiconoscimento")
public class VisualizzaElencoModuliRiconoscimento extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione=request.getSession();
		if (sessione.getAttribute("utenteLoggato") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/login.jsp");
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
			// studente--> visualizza i propri moduli di riconoscimento di attività lavorativa
			else if(utente.getUserType()==1) {
				ArrayList<Riconoscimento> elencoRiconoscimenti=RiconoscimentoDao.getModuliRiconoscimentiWithEmailStudente(utente.getEmail());
				
				request.setAttribute("elencoRiconoscimento", elencoRiconoscimenti);
				request.getRequestDispatcher("visualizzaElencoRiconoscimenti.jsp").forward(request, response);
			}
			//PdCD e Ufficio Carriera--> visualizzano i moduli di riconoscimento di attività lavorativa di tutti gli studenti. 
			else if(utente.getUserType()==2){
				ArrayList<Riconoscimento> elencoRiconoscimenti=RiconoscimentoDao.getModuliRiconoscimentiWithStudenti();
				
				request.setAttribute("elencoRiconoscimento", elencoRiconoscimenti);
				request.getRequestDispatcher("visualizzaElencoRiconoscimenti.jsp").forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
