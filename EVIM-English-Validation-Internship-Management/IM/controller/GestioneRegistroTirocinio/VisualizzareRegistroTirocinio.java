package controller.GestioneRegistroTirocinio;

import controller.BaseServlet;
import model.TirocinioEsterno;
import model.TirocinioEsternoDAO;
import model.TirocinioInterno;
import model.TirocinioInternoDAO;
import model.User;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author Simone Auriemma Questa servlet permette di visualizzare il registro
 *         di tirocinio allo studente, tutor accademico e tutor aziendale
 *
 */

@SuppressWarnings("serial")
@WebServlet("/VisualizzaRegistroTirocinio")
public class VisualizzareRegistroTirocinio extends BaseServlet {

	public VisualizzareRegistroTirocinio() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// verifico il tipo di utente loggato dalla sessione
		HttpSession session = request.getSession();

		String tipoUtente = session.getAttribute("utenteLoggato").getClass().getName();

		if (tipoUtente.equalsIgnoreCase("model.user")) {
			// loggato un USER, a causa dell'user type, può esssere il PdCD, uno studente o
			// l'ufficio carriere
			User user = (User) session.getAttribute("utenteLoggato");
			// verifico l'user type, 0 con studente, 1 PdCD, 2 Ufficio Carriere
			if (user.getUserType() == 0) {
				ArrayList<TirocinioEsterno> tirocinioEsterno = new TirocinioEsternoDAO()
						.doRetriveAllByStudentRegistro(user.getEmail());
				ArrayList<TirocinioInterno> tirocinioInterno = new TirocinioInternoDAO()
						.doRetriveTirocinioInSvolgimentoStudente(user.getEmail());

				if (!(tirocinioEsterno.isEmpty())) {
					//significa che ha un tirocinio esterno
					
					
				}else if(!(tirocinioInterno.isEmpty())) {
					// significa cha ha un tirocinio interno
				}
			}

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
