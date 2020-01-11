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
import model.Proposta;
import model.PropostaDAO;
import model.ReferenteAziendale;

/**
 * Servlet implementation class VisualizzaAziende * @author Nicola Sisti la
 * servlet prende tutte le aziende dal db; poi fa un foreach e per ogni azienda
 * viene inserito un arraylist con le proprie proposte di tirocinio(caricate da
 * db)
 */
@SuppressWarnings("serial")
@WebServlet("/VisualizzaAziende")

public class VisualizzaAziende extends BaseServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Azienda> aziende = AziendaDAO.doRetriveAll();

		for (Azienda az : aziende) {
			System.out.println("\n\n id"+az.getID_Azienda());
			az.setProposte(PropostaDAO.findByIdAzienda(az.getID_Azienda()));
			az.setReferente(AziendaDAO.getReferenteAziendale(az.getID_Azienda()));
		}


		request.setAttribute("aziende", aziende);

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/elencoAziende.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
