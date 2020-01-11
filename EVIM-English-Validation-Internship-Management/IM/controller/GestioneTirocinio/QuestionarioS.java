package controller.GestioneTirocinio;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.GestioneAutenticazione.*;

import model.QuestionariDAO;
import model.Questionario_s;
import model.User;

/**
 * Servlet implementation class QuestionarioS
 */
@WebServlet("/QuestionarioS")
public class QuestionarioS extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuestionarioS() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Questionario_s quest = new Questionario_s();
		String nomeUtente = (String) session.getAttribute("type");
		User studente = (User) session.getAttribute("utenteLoggato");
		boolean result = false;
		boolean compiled = true;

		for (int i = 1; i < 14; i++) {

			if (request.getParameter("gruppo" + i) == null) {
				compiled = false;
				break;
			}

		}

		if (compiled) {

			if (studente != null && nomeUtente.contentEquals("studente")) {
				quest.setEmail(studente.getEmail());
				quest.setAttivita(Integer.parseInt(request.getParameter("gruppo1")));
				quest.setFormazione(Integer.parseInt(request.getParameter("gruppo2")));
				quest.setDurata(Integer.parseInt(request.getParameter("gruppo3")));
				quest.setValutazione(Integer.parseInt(request.getParameter("gruppo4")));
				quest.setMansioni(Integer.parseInt(request.getParameter("gruppo5")));
				quest.setAmbiente(Integer.parseInt(request.getParameter("gruppo6")));
				quest.setCompetenze(Integer.parseInt(request.getParameter("gruppo7")));
				quest.setAssistenza(Integer.parseInt(request.getParameter("gruppo8")));
				quest.setInformazioni(Integer.parseInt(request.getParameter("gruppo9")));
				quest.setAssistenza_disp(Integer.parseInt(request.getParameter("gruppo10")));
				quest.setServizi(Integer.parseInt(request.getParameter("gruppo11")));
				quest.setPossibilita(Integer.parseInt(request.getParameter("gruppo12")));
				quest.setLogistica(Integer.parseInt(request.getParameter("gruppo13")));
				result = QuestionariDAO.insertQuestionarioS(quest);
				session.setAttribute("resultqs", result);
				
			}

			if (result) {
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/home.jsp");
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/questionarioS.jsp");
				rd.forward(request, response);
			}

		}
		
		else {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/questionarioS.jsp");
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
