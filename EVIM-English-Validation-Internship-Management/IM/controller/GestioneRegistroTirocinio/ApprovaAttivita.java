package controller.GestioneRegistroTirocinio;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AttivitaDAO;
/**
 * @author Antonio Giano
 * Questa servlet permette di gestire l'approvazione o il rifiuto di un registro cambiando definitivamente lo stato di tale registro sul database
 */
@WebServlet("/ApprovaAttivita")
public class ApprovaAttivita extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione=request.getSession();
		if(sessione.getAttribute("utenteLoggato")==null) {
			request.getRequestDispatcher("./WEB-INF/login.jsp").forward(request, response);
		}
		else {
			String tipoUtente=sessione.getAttribute("utenteLoggato").getClass().getName();
			// solo il tutor aziendale e il tutor accademico possono approvare l'attivita
			if(tipoUtente.equalsIgnoreCase("model.tutoraccademico") || tipoUtente.equalsIgnoreCase("model.tutoraziendale")) {
				int idAttivita=Integer.parseInt(request.getParameter("idAttivita"));
				String modifica=request.getParameter("modifica");
				if(modifica.equalsIgnoreCase("approva")) {
					if(!AttivitaDAO.changeFirmaResponsabile(idAttivita,true)) // 1= attivita approvata
						throw new IllegalAccessError("Errore nell'approvare l'attivita");
					else request.getRequestDispatcher("WEB-INF/registroTirocinio(tutor).jsp").forward(request, response);
				}
			}
			//tutti gli altri utenti gli sono stati negati per l'approvazione di una attivita
			else {
				request.getRequestDispatcher("permissionDenied.jsp").forward(request, response);	
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
