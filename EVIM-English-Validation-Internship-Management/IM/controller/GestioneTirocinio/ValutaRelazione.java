package controller.GestioneTirocinio;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DriverManagerConnectionPool;
import model.RelazioneDAO;
import model.TutorAccademico;
import model.TutorAziendale;

/**
 * Servlet implementation class ValutaRelazione
 */
@WebServlet("/ValutaRelazione")
public class ValutaRelazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValutaRelazione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		TutorAccademico tutor= (TutorAccademico) session.getAttribute("utenteLoggato");
		String nome= (String) session.getAttribute("type");
		String approva= request.getParameter("approva");
		boolean result=false;
		int idrelazione= Integer.parseInt( request.getParameter("idrelazione"));
		
		System.out.println("approva:"+approva);
		if(tutor!=null && nome.contentEquals("tutoraccademico")) {
			if(approva.contentEquals("1")) {
				result=RelazioneDAO.doAlterRelazione(true, idrelazione);
			}
			else {
				result=RelazioneDAO.doAlterRelazione(false, idrelazione);
			}
			
			if(result) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/ListaTirocini");
				dispatcher.forward(request, response);
			}
			else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/ListaTirocini");
				dispatcher.forward(request, response);
			}
	
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
