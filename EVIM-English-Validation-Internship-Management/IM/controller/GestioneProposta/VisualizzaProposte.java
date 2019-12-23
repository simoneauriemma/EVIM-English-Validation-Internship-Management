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
		System.out.println("Eseguo");
		HttpSession sessione=request.getSession();
		// controllo se è loggato l'utente altrimenti reindirizzo alla pagina login
		if (sessione.getAttribute("utenteLoggato") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		else {
			String tipoUtente=sessione.getAttribute("utenteLoggato").getClass().getName();
			System.out.println("tipo utente-->"+tipoUtente);
			if(tipoUtente.equalsIgnoreCase("model.User")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("permissiondenied.jsp");
				dispatcher.forward(request, response);
			
			}
			
			// tirocinio interno
			else if(tipoUtente.equalsIgnoreCase("model.Tutoraccademico")) {
				System.out.println("sono dentro");
				TutorAccademico tutor=(TutorAccademico) sessione.getAttribute("utenteLoggato");
				System.out.println("ID-->"+tutor.getIdTutorAccademico());
				ArrayList<Proposta> proposteInterne=PropostaDAO.findByIdTutorAccademico(tutor.getIdTutorAccademico());
				System.out.println("finito");
				
				for(int i=0;i<proposteInterne.size();i++) {
					System.out.println("proposta-->"+proposteInterne.get(i).getID_Proposta());
				}
				
				request.setAttribute("proposte", proposteInterne);
				RequestDispatcher dispatcher = request.getRequestDispatcher("propostaTirocinio.jsp");
				dispatcher.forward(request, response);
				
			}
			//tirocinio esterno
			else if(tipoUtente.equalsIgnoreCase("model.Tutoraziendale")) {
				TutorAziendale tutor=(TutorAziendale) sessione.getAttribute("utenteLoggato");
				ArrayList<Proposta> proposteEsterne=PropostaDAO.getProposteAziendaWithIdAzienda(tutor.getIdAzienda());
				
				request.setAttribute("proposte", proposteEsterne);
				RequestDispatcher dispatcher = request.getRequestDispatcher("propostaTirocinio.jsp");
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
