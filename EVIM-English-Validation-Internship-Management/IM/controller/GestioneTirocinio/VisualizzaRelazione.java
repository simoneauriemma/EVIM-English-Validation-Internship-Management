package controller.GestioneTirocinio;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import model.Relazione;
import model.RelazioneDAO;
import model.TutorAccademico;
import model.TutorAziendale;
import model.User;

/**
 * Servlet implementation class VisualizzaRelazione
 */
@WebServlet("/VisualizzaRelazione")
public class VisualizzaRelazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaRelazione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		
		TutorAccademico u= (TutorAccademico) session.getAttribute("utenteLoggato");
		int  idrelazione= Integer.parseInt(request.getParameter("idrelazione"));
		
		
		if(session.getAttribute("utenteLoggato")!=null) {
			
			Relazione r= RelazioneDAO.doRetriveRelazionefromId(idrelazione);
			JSONObject relazione= new JSONObject();
			relazione.put("id",r.getIdrelazione() );
			relazione.put("descrizione", r.getDescrizione());
			relazione.put("email",r.getEmail());
			relazione.put("status", r.getStatus());
			relazione.put("idtutor", r.getIdtutor());
	
			response.getWriter().append(relazione.toString());
			
		}
		
		else {
			
			response.getWriter().append("");
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
