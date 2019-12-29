package controller.GestioneModuloRiconoscimento;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Antonio Giano
 * Questa servlet crea il modulo di riconoscimento prendendo dei parametri dal form ricevutosi.
 */
@WebServlet("/CompilaModuloRiconoscimento")
public class CompilaModuloRiconoscimento extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione=request.getSession();
		// controllo se è loggato l'utente altrimenti reindirizzo alla pagina login
		if (sessione.getAttribute("utenteLoggato") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/login.jsp");
			dispatcher.forward(request, response);
		}
		else {
			String tipoUtente=sessione.getAttribute("utenteLoggato").getClass().getName();
			// non adatto per lo studente,pdcd,ufficio carriere
			if(!tipoUtente.equalsIgnoreCase("model.User")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("permissionDenied.jsp");
				dispatcher.forward(request, response);
			}
			else {
				String enteAzienda=request.getParameter("enteAzienda");
				String indirizzoSede=request.getParameter("indirizzoSede");
				String profilo=request.getParameter("profilo");
				String tipoContratto=request.getParameter("tipoContratto");
				String periodo=request.getParameter("periodo");
				String oreSvolte=request.getParameter("oreSvolte");
				int CFUTirocinioObbligatorio=Integer.parseInt(request.getParameter("CFUObbligatorio"));
				int CFUTirocinioEsterno=Integer.parseInt(request.getParameter("CFUEsterno"));
				int CFUAccompagnamento=Integer.parseInt(request.getParameter("CFUAccompagnamento"));
				
				// prendo i file allegati dallo studente.
				
			}
		}
	}

}
