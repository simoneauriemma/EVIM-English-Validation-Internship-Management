package controller.GestioneProposta;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.PropostaDAO;
import model.TutorAccademico;
import model.TutorAziendale;

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
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		else {
			String tipoUtente=sessione.getAttribute("utenteLoggato").getClass().getName();
			// non adatto per lo studente,pdcd,ufficio carriere
			if(tipoUtente.equalsIgnoreCase("model.User")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}
			
			// tirocinio interno
			else if(tipoUtente.equalsIgnoreCase("tutoraccademico")) {
				TutorAccademico tutor=(TutorAccademico) sessione.getAttribute("utenteLoggato");
				String sede=request.getParameter("sede");
				String temaAmbito=request.getParameter("tema/ambito");
				String obiettivo=request.getParameter("Obiettivo");
				String materialeRisorse=request.getParameter("materiale/risorse");
				
				if(PropostaDAO.insertPropostaInterno(obiettivo, sede, temaAmbito, materialeRisorse, tutor.getIdTutorAccademico())) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/home.jsp");
					dispatcher.forward(request, response);
				}
				else {
					System.out.println("insert non andato a buon fine");
				}
				
			}
			//tirocinio esterno
			else if(tipoUtente.equalsIgnoreCase("tutoraziendale")) {
				TutorAziendale tutor= (TutorAziendale) sessione.getAttribute("utenteLoggato");
				int idTutorAziendale=Integer.parseInt(request.getParameter("tutorAziendale"));
				String sede=request.getParameter("sede");
				String temaAmbito=request.getParameter("tema/ambito");
				String obiettivo=request.getParameter("Obiettivo");
				String materialeRisorse=request.getParameter("materiale/risorse");
				
				if(PropostaDAO.insertPropostaEsterno(obiettivo, sede, temaAmbito, materialeRisorse, tutor.getIdAzienda(),idTutorAziendale));
				RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/home.jsp");
				dispatcher.forward(request, response);
			}
			else {
				System.out.println("insert non andato a buon fine");
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
