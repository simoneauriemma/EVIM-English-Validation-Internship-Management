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

import model.Azienda;
import model.AziendaDAO;
import model.TutorAccademico;
import model.TutorAccademicoDAO;
import model.TutorAziendale;
import model.TutorAziendaleDAO;

/**
 * Servlet implementation class RicercaTutor
 */
@WebServlet("/RicercaTutor")
public class RicercaTutorAJAX extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tirocinio=(String) request.getParameter("tirocinio");
		//tirocinio esterno
		if(tirocinio.equalsIgnoreCase("tirocinio1")) {
				
				JSONArray elencoTutor=new JSONArray();
				ArrayList<Azienda> elencoTutorAziendali=AziendaDAO.doRetriveAll(); 
				ArrayList<TutorAccademico> elencoTutorAccademico=TutorAccademicoDAO.doRetrieveAll();
				
				JSONArray jsonTutorAziendali=new JSONArray();
				for(int i=0;i<elencoTutorAziendali.size();i++){
					JSONObject objectTMP=new JSONObject();
					objectTMP.put("Nome", elencoTutorAziendali.get(i).getNome());
					objectTMP.put("ID", elencoTutorAziendali.get(i).getID_Azinda());
					jsonTutorAziendali.put(objectTMP);
				}
				JSONObject objectTutorAziendali=new JSONObject();
				objectTutorAziendali.put("TutorAziendali", jsonTutorAziendali);
				
				
				JSONArray jsonTutorAccademici=new JSONArray();
				for(int i=0;i<elencoTutorAccademico.size();i++) {
					JSONObject objectTMP=new JSONObject();
					objectTMP.put("Nome", elencoTutorAccademico.get(i).getNome());
					objectTMP.put("ID", elencoTutorAccademico.get(i).getIdTutorAccademico());
					jsonTutorAccademici.put(objectTMP);
				}
				JSONObject objectTutorAccademici=new JSONObject();
				objectTutorAccademici.put("TutorAccademici", jsonTutorAccademici);
				
				elencoTutor.put(objectTutorAziendali);
				elencoTutor.put(objectTutorAccademici);
				
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json");
				response.getWriter().append(elencoTutor.toString());
			}
		//tirocinioInterno
		else if(tirocinio.equalsIgnoreCase("tirocinio2")) {
			JSONArray elencoTutor=new JSONArray();
			ArrayList<TutorAccademico> elencoTutorAccademico=TutorAccademicoDAO.doRetrieveAll();
			
			JSONArray jsonTutorAccademici=new JSONArray();
			for(int i=0;i<elencoTutorAccademico.size();i++) {
				JSONObject objectTMP=new JSONObject();
				objectTMP.put("Nome", elencoTutorAccademico.get(i).getNome());
				objectTMP.put("ID", elencoTutorAccademico.get(i).getIdTutorAccademico());
				jsonTutorAccademici.put(objectTMP);
			}
			JSONObject objectTutorAccademici=new JSONObject();
			objectTutorAccademici.put("TutorAccademici", jsonTutorAccademici);
			
			elencoTutor.put(objectTutorAccademici);
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			System.out.println(elencoTutor.toString());
			response.getWriter().append(elencoTutor.toString());
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
