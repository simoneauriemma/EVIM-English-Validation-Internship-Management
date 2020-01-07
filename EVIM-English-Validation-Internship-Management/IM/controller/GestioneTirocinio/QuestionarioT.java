package controller.GestioneTirocinio;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.QuestionariDAO;
import model.Questionario_s;
import model.Questionario_t;
import model.TutorAziendale;
import model.User;

/**
 * Servlet implementation class QuestionarioT
 */
@WebServlet("/QuestionarioT")
public class QuestionarioT extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionarioT() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Questionario_t quest = new Questionario_t();
		String nomeUtente = (String) session.getAttribute("type");
		TutorAziendale tutor= (TutorAziendale) session.getAttribute("utenteLoggato");
		User studente = (User) session.getAttribute("studente");
		boolean result = false;
		boolean compiled = true;

		for (int i = 1; i < 14; i++) {

			if (request.getParameter("gruppo" + i) == null) {
				compiled = false;
				break;
			}

		}

		if (compiled) {

			if (tutor != null && nomeUtente.contentEquals("studente")) {
				quest.setEmail(studente.getEmail());
				quest.setId_tutor(tutor.getId());
				quest.setDurata(Integer.parseInt(request.getParameter("gruppo1")));
				quest.setObiettivi(Integer.parseInt(request.getParameter("gruppo2")));
				quest.setCollaborazione(Integer.parseInt(request.getParameter("gruppo3")));
				quest.setUtilita(Integer.parseInt(request.getParameter("gruppo4")));
				quest.setCompetenze_ingresso(Integer.parseInt(request.getParameter("gruppo5")));
				quest.setCompetenze_acquisite(Integer.parseInt(request.getParameter("gruppo6")));
				quest.setMotivazione(Integer.parseInt(request.getParameter("gruppo8")));
				quest.setCapacita(Integer.parseInt(request.getParameter("gruppo9")));
				quest.setInformazioni(Integer.parseInt(request.getParameter("gruppo10")));
				quest.setAssistenza(Integer.parseInt(request.getParameter("gruppo11")));
				quest.setServizi(Integer.parseInt(request.getParameter("gruppo12")));

				result = QuestionariDAO.insertQuestionarioT(quest);
			}

			if (result) {
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/home.jsp");
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/questionarioS");
				rd.forward(request, response);
			}

		}
		
		else {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/questionarioS");
			rd.forward(request, response);
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
