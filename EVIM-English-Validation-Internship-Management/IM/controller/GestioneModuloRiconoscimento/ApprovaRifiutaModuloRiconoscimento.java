package controller.GestioneModuloRiconoscimento;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.RiconoscimentoDao;
import model.User;

/**
 * @author Antonio Giano
 * Questa servlet modifica lo stato di un modulo di riconoscimento di attività lavorativa in base al parametro ricevuto
 */
@WebServlet("/ApprovaRifiutaModuloRiconoscimento")
public class ApprovaRifiutaModuloRiconoscimento extends HttpServlet {
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
					int idRiconoscimento=Integer.parseInt(request.getParameter("idRiconoscimento"));
					String modifica=request.getParameter("modifica");
					boolean risultato = false;
					if(modifica.equalsIgnoreCase("rifiuta")) {
						risultato=RiconoscimentoDao.changeStatoModulo(idRiconoscimento,"R"); // R sta per rifiutato
					}
					else if(modifica.equalsIgnoreCase("approva")) {
						risultato=RiconoscimentoDao.changeStatoModulo(idRiconoscimento,"A"); // A sta per approvato
					}
					if(risultato)
						request.setAttribute("modificaModulo", true);
					else
						request.setAttribute("modificaModulo", false);
					
					request.getRequestDispatcher("visualizzaElencoModuliRiconoscimento.jsp").forward(request, response);
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
