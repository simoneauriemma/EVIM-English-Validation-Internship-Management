package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.UserDAO;
import model.Azienda;
import model.AziendaDAO;
import model.TutorAccademico;
import model.TutorAccademicoDAO;
import model.TutorAziendale;
import model.TutorAziendaleDAO;

@SuppressWarnings("serial")
@WebServlet("/Login")
public class Login extends BaseServlet {

	public Login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		// controllo campi null

		if (email == null) {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}

		if (password == null) {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}

		// dominio utenza (controllo degli indirizzi email)

		// controllo studente (@studenti.unisa.it)

		if (email.endsWith("@studenti.unisa.it")) {
			User studente = UserDAO.doRetrieveByLoginData(0, email, password);
			if (studente != null) {
				session.setAttribute("utenteLoggato", studente);
				session.setAttribute("type", "studente");
				request.setAttribute("logged", true);
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/home.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("logged", false);
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
		}

		// controllo pdcd (fferrucci@unisa.it)

		else if (email.contains("fferrucci@unisa.it")) {
			User pdcd = UserDAO.doRetrieveByLoginData(1, email, password);
			if (pdcd != null) {
				session.setAttribute("utenteLoggato", pdcd);
				session.setAttribute("type", "pdcd");
				request.setAttribute("logged", true);
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/home.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("logged", false);
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
		}

		// controllo tutor accademico (@unisa.it)

		else if (email.endsWith("@unisa.it")) {
			TutorAccademico tutoracc = TutorAccademicoDAO.doRetrieveByLoginData(email, password);
			if (tutoracc != null) {
				session.setAttribute("utenteLoggato", tutoracc);
				session.setAttribute("type", "tutoraccademico");
				request.setAttribute("logged", true);
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/home.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("logged", false);
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
				rd.forward(request, response);
			}

		}

		// controllo tutor aziendale (@tutor.azienda.it)

		else if (email.contains("tutor")) {
			TutorAziendale tutoraz = TutorAziendaleDAO.doRetrieveByLoginData(email, password);
			if (tutoraz != null) {
				session.setAttribute("utenteLoggato", tutoraz);
				session.setAttribute("type", "tutoraziendale");
				request.setAttribute("logged", true);
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/home.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("logged", false);
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
		}
		/*
		 * controllo azienda (@azienda.it) qualsiasi sia il dominio della email, diverso
		 * da studenti.unisa.it o unisa.it vuol dire che si tratta di un account
		 * appartenente ad un'azienda
		 */

		else {
			Azienda az = AziendaDAO.doRetrieveByLoginData(email, password);
			if (az != null) {
				session.setAttribute("utenteLoggato", az);
				session.setAttribute("type", "azienda");
				request.setAttribute("logged", true);
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/home.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("logged", false);
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
		}
	}
}
