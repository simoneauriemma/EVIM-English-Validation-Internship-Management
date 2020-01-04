package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TutorAccademico;
import model.TutorAccademicoDAO;
import model.User;
import model.UserDAO;


/**
 * Servlet implementation class Registrazione
 */
@WebServlet("/Registrazione")
public class Registrazione extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Servlet di registrazione come studente
	 * 
	 * @author Nicola Sisti
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// regex utenti

		String regexStudente = "[a-z0-9\\.]+@studenti\\.unisa\\.it";
		String regexDocente = "[a-z0-9\\.]+@unisa\\.it";
		
		
		String regexNome="^[A-Za-z]{1,50}";
		String regexCognome="^[A-Za-z]{1,50}";
		String regexPassword="^[A-Za-z0-9]{8,50}";
		String regexData="^([0-2][0-9]|(3)[0-1])(\\/|-)(((0)[0-9])|((1)[0-2]))(\\/|-)\\d{4}$";
		String regexLuogoNascita="^[A-Za-z]{1,46}";
		String regexIndirizzo="'^[a-z A-Z]+.[1-9]+{1,100}";
		String regexTelefono="^[0-9]{8,16}";
		String regexLuogoResidenza="^[A-Za-z]{1,46}";
		String regexSesso="^[m f M F]";
		String regexCorso="magistrale|triennale";
		String regexMatricola="^[0-9]{10,10}";
		
		// campi comuni
		String email = request.getParameter("email");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String password = request.getParameter("password");
		String cpassword = request.getParameter("cpassword");

		// campi solo studente
		String sesso = request.getParameter("sesso");
		String corso = request.getParameter("corso");
		String matricola = request.getParameter("matricola");
		String data = request.getParameter("data");
		String luogonascita = "" + request.getParameter("comunen") + "(" + request.getParameter("provincian") + ")";
		String provincian = request.getParameter("provincian");
		String indirizzo = request.getParameter("indirizzo");
		String telefono = request.getParameter("telefono");
		String luogoresidenza = "" + request.getParameter("comuner") + "(" + request.getParameter("provinciar") + ")";
		String provinciar = request.getParameter("provinciar"); 
		String regexProvincia= "^[A-Z]{2,2}";

		boolean result = false;
// se non sono loggato mi posso registrare altrimenti non ha senso
		if (request.getParameter("utenteLoggato") == null && email != null) {
// controllo se sono un docente
			if (email.matches(regexDocente)) { // controllo del formato e lunghezza caratteri
				// controllo la bontà dei dati
				if (password.equals(cpassword) 
						&& nome.matches(regexNome)
						&& cognome.matches(regexCognome)
						&& password.matches(regexPassword)
					
						&& sesso.matches(regexSesso)
						&& email.length() < 51) {

					result = TutorAccademicoDAO.insertNewTutorAccademico(nome, cognome, cpassword, sesso, email);

				}

			}
// controllo se sono uno studente
			else if (email.matches(regexStudente)) {
				// controllo la bontà dei dati
				if (password.equals(cpassword) 
						
						
						
						&& nome.matches(regexNome) 
						&& cognome.matches(regexCognome)
						&& password.matches(regexPassword)
						&& sesso.matches(regexSesso)
						&& corso.matches(regexCorso)
						&& email.length() < 51 
						&& data.matches(regexData)
						&& luogonascita.matches(regexLuogoNascita)
						&& luogoresidenza.matches(regexLuogoResidenza)
						&& indirizzo.matches(regexIndirizzo)
						&& telefono.matches(regexTelefono)
						&& matricola.matches(regexMatricola)
						&& provincian.matches(regexProvincia)
						&& provinciar.matches(regexProvincia)

				) {
					User u = new User(email, nome, cognome, sesso.charAt(0), password, 1, corso, luogonascita +"("+ provincian +")", data,
							luogoresidenza +"("+ provinciar +")", indirizzo, telefono, matricola);

					result = UserDAO.insertNewUser(u);
				}
			}
		}
		// ! se l'operazione è andata buon fine invio un bool a true per dire che è
		// andato a buon fine
		if (result) {
			request.setAttribute("result", true);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/home.jsp");
			rd.forward(request, response);
		}
		// altrimenti il bool lo mando a false
		else {
			request.setAttribute("result", false);
			RequestDispatcher rd = request.getRequestDispatcher("RedirectToRegistration");
			rd.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
