package controller.GestioneProposta;

import java.io.IOException;
import java.util.ArrayList;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import model.Azienda;
import model.Proposta;
import model.PropostaDAO;
import model.TutorAccademico;
import model.TutorAziendale;

/**
 * @author Antonio Giano Servlet implementation class VisualizzaProposte Questa
 *         servlet provedde ad elencare tutte le proposte presenti nel database
 */
@WebServlet("/VisualizzaProposte")
public class VisualizzaProposte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession sessione=request.getSession();
		// controllo se è loggato l'utente altrimenti reindirizzo alla pagina login
		if (sessione.getAttribute("utenteLoggato") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		else {
			String tipoUtente=sessione.getAttribute("utenteLoggato").getClass().getName();
			if(tipoUtente.equalsIgnoreCase("model.User")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("permissiondenied.jsp");
				dispatcher.forward(request, response);
			
			}
			
			// tirocinio interno
			else if(tipoUtente.equalsIgnoreCase("model.Tutoraccademico")) {
				
				TutorAccademico tutor=(TutorAccademico) sessione.getAttribute("utenteLoggato");
				
				ArrayList<Proposta> proposteInterne=PropostaDAO.findByIdTutorAccademico(tutor.getIdTutorAccademico());

				request.setAttribute("proposte", proposteInterne);
				request.setAttribute("type", "tutoraccademico");
				RequestDispatcher dispatcher = request.getRequestDispatcher("viewPropostaTirocinio.jsp");
				dispatcher.forward(request, response);
				
			}
			//tirocinio esterno
			else if(tipoUtente.equalsIgnoreCase("model.Azienda")) {
				Azienda tutor=(Azienda) sessione.getAttribute("utenteLoggato");
				ArrayList<Proposta> proposteEsterne=PropostaDAO.getProposteAziendaWithIdAzienda(tutor.getID_Azinda());
				
				request.setAttribute("proposte", proposteEsterne);
				request.setAttribute("type", "azienda");
				RequestDispatcher dispatcher = request.getRequestDispatcher("viewPropostaTirocinio.jsp");
				dispatcher.forward(request, response);
			}
			else if(tipoUtente.equalsIgnoreCase("model.tutoraziendale")) {
				TutorAziendale tutoraziendale=(TutorAziendale) sessione.getAttribute("utenteLoggato");
				ArrayList<Proposta> proposteEsterne=PropostaDAO.findByIdTutorAziendale(tutoraziendale.getId());
				
				request.setAttribute("proposte", proposteEsterne);
				request.setAttribute("type", "tutoraziendale");
				RequestDispatcher dispatcher = request.getRequestDispatcher("viewPropostaTirocinio.jsp");
				dispatcher.forward(request, response);
			}
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
