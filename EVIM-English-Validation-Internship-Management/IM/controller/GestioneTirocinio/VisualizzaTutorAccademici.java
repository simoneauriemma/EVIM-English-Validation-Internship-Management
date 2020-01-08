package controller.GestioneTirocinio;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.GestioneAutenticazione.*;

import model.Azienda;
import model.AziendaDAO;
import model.PropostaDAO;
import model.TutorAccademico;
import model.TutorAccademicoDAO;

/**
 * Servlet implementation class VisualizzaProposteTirocinioInterno
 */
@SuppressWarnings("serial")
@WebServlet("/VisualizzaTutorAccademici")
public class VisualizzaTutorAccademici extends BaseServlet {

	/**
	 * Servlet implementation class VisualizzaAziende * @author Antonio Giano,
	 * Nicola Sisti la servlet prende tutti i tutor aziendale dal db; poi fa un
	 * foreach e per ogni tutor viene inserito un arraylist con le proprie propostes
	 * di tirocinio(caricate da db)
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<TutorAccademico> tutors = TutorAccademicoDAO.doRetrieveAll();

		for (TutorAccademico ta : tutors) {
			ta.setListeProposte(PropostaDAO.findByIdTutorAccademico(ta.getIdTutorAccademico()));

		}

		request.setAttribute("tutors", tutors);

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/elencoTutorAccademici.jsp");
		rd.forward(request, response);
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
