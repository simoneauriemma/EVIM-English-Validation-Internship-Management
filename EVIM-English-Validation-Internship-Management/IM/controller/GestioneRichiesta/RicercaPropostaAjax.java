package controller.GestioneRichiesta;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.cj.xdevapi.JsonArray;

import model.Proposta;
import model.PropostaDAO;

/**
 * Servlet implementation class RicercaPropostaAjax
 */
@WebServlet("/RicercaPropostaAjax")
public class RicercaPropostaAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RicercaPropostaAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String propostaTutor=request.getParameter("tutor");
		if(propostaTutor.equalsIgnoreCase("Azienda")) {
			JSONObject objectProposte=new JSONObject();
			
			JSONArray jsoneProposte=new JSONArray();
			
			String stringidAzienda=request.getParameter("id");
			int idAzienda=Integer.parseInt(stringidAzienda);
			
			ArrayList<Proposta> elencoProposte=PropostaDAO.findByIdAzienda(idAzienda);
			
			for(int i=0;i<elencoProposte.size();i++) {
				JSONObject objectTMP=new JSONObject();
				objectTMP.put("Obiettivo", elencoProposte.get(i).getObiettivi());
				objectTMP.put("ID", elencoProposte.get(i).getID_Proposta());
				jsoneProposte.put(objectTMP);
			}
			
			objectProposte.put("Proposte", jsoneProposte);
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			response.getWriter().append(objectProposte.toString());
		}
		else if(propostaTutor.equalsIgnoreCase("Accademico")) {
			JSONObject objectProposte=new JSONObject();
			
			JSONArray jsoneProposte=new JSONArray();
			
			String stringidAccademico=request.getParameter("id");
			int idAccademico=Integer.parseInt(stringidAccademico);
			
			ArrayList<Proposta> elencoProposte=PropostaDAO.findByIdTutorAccademico(idAccademico);
			
			for(int i=0;i<elencoProposte.size();i++) {
				JSONObject objectTMP=new JSONObject();
				objectTMP.put("Obiettivo", elencoProposte.get(i).getObiettivi());
				objectTMP.put("ID", elencoProposte.get(i).getID_Proposta());
				jsoneProposte.put(objectTMP);
			}
			
			objectProposte.put("Proposte", jsoneProposte);
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			response.getWriter().append(objectProposte.toString());
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
