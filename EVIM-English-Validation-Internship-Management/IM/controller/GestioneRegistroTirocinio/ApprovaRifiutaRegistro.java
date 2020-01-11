package controller.GestioneRegistroTirocinio;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.RegistroDao;
import model.User;

/**
 * @author Antonio Giano
 * Questa servlet permette di gestire l'approvazione o il rifiuto di un registro cambiando definitivamente lo stato di tale registro sul database
 */
@WebServlet("/ApprovaRifiutaRegistro")
public class ApprovaRifiutaRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione=request.getSession();
		if(sessione.getAttribute("utenteLoggato")==null) {
			request.getRequestDispatcher("./WEB-INF/login.jsp").forward(request, response);
		}
		else {
			String tipoUtente=sessione.getAttribute("utenteLoggato").getClass().getName();
			if(!tipoUtente.equalsIgnoreCase("model.User")) {
				request.getRequestDispatcher("permissionDenied.jsp").forward(request, response);
			}
			else {
				User utente=(User) sessione.getAttribute("utenteLoggato");
				//Controlli di sicurezza. Lo studente e l'Ufficio Carriera non deve aver a che fare con tale pagina. 
				if(!(utente.getUserType()==2)) {
					request.getRequestDispatcher("permissionDenied.jsp").forward(request, response);
				}
				else {
					int idRegistro=Integer.parseInt(request.getParameter("idRegistro"));
					String modifica=request.getParameter("modifica");
					if(modifica.equalsIgnoreCase("rifiuta")) {
						RegistroDao.changeStatoRegistro(idRegistro,"Rifiutato");
					}
					else if(modifica.equalsIgnoreCase("approva")) {
						RegistroDao.changeStatoRegistro(idRegistro,"Approvato");
					}
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
