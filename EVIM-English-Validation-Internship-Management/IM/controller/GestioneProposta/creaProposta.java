package controller.GestioneProposta;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Azienda;
import model.PropostaDAO;
import model.TutorAccademico;

/**
 * @author Antonio Giano
 * Servlet implementation class creaProposta
 * Questa servlet permette di creare una proposta di tirocinio curriculare interno o esterno
 */
@WebServlet("/creaProposta")
public class creaProposta extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione=request.getSession();
		// controllo se è loggato l'utente altrimenti reindirizzo alla pagina login
		if (sessione.getAttribute("utenteLoggato") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/login.jsp");
			dispatcher.forward(request, response);
		}
		else {
			String tipoUtente=sessione.getAttribute("utenteLoggato").getClass().getName();
			// non adatto per lo studente,pdcd,ufficio carriere
			if(tipoUtente.equalsIgnoreCase("model.User")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("permissionDenied.jsp");
				dispatcher.forward(request, response);
			}
			
			// tirocinio interno
			else if(tipoUtente.equalsIgnoreCase("model.tutoraccademico")) {
				TutorAccademico tutor=(TutorAccademico) sessione.getAttribute("utenteLoggato");
				String competenze=request.getParameter("competenze");
				String attivita=request.getParameter("attivita");
				String obiettivo=request.getParameter("obiettivo");
				String modalita=request.getParameter("modalita");
				
				
				
				if(PropostaDAO.insertPropostaInterno(obiettivo, competenze, attivita, modalita, tutor.getIdTutorAccademico())) {
					request.setAttribute("risultatoInserimentoProposta",true);
					
				}
				else {
					request.setAttribute("risultatoInserimentoProposta", false);
				}
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/home.jsp");
				dispatcher.forward(request, response);
				
			}
			//tirocinio esterno
			else if(tipoUtente.equalsIgnoreCase("model.azienda")) {
				Azienda tutor= (Azienda) sessione.getAttribute("utenteLoggato");
				int idTutorAziendale=Integer.parseInt(request.getParameter("tutorAziendale"));
				String competenze=request.getParameter("competenze");
				String attivita=request.getParameter("attivita");
				String obiettivo=request.getParameter("obiettivo");
				String modalita=request.getParameter("modalita");
				
			if(idTutorAziendale!=-1 && competenze.length()<=200 && attivita.length()<=200 && obiettivo.length()<=200 && modalita.length()<=200)
					if(PropostaDAO.insertPropostaEsterno(obiettivo, competenze, attivita, modalita, tutor.getID_Azinda(),idTutorAziendale)) {
						request.setAttribute("risultatoInserimentoProposta",true);
						RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/home.jsp");
						dispatcher.forward(request, response);
					}
					else {
						RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/home.jsp");
						request.setAttribute("risultatoInserimentoProposta",false);
						dispatcher.forward(request, response);
					}
				}
			else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/home.jsp");
				request.setAttribute("risultatoInserimentoProposta",false);
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
