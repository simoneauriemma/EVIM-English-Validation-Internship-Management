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
import model.Proposta;
import model.PropostaDAO;

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
		ArrayList<Proposta> listaProposta = PropostaDAO.getListaProposta();
		request.setAttribute("listaProposte", listaProposta);
		RequestDispatcher redirect = request.getRequestDispatcher("WEB_INF/propostaTirocinio.jsp");
		redirect.forward(request, response);
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
