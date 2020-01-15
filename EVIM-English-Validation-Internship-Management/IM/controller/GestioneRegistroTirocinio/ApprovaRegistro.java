package controller.GestioneRegistroTirocinio;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.GestioneAutenticazione.BaseServlet;
import model.RegistroDAO;

/**
 * Servlet implementation class ApprovaRegistro
 */
@WebServlet("/ApprovaRegistro")
public class ApprovaRegistro extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApprovaRegistro() {
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
		String name = (String) session.getAttribute("type");
		int idregistro = Integer.parseInt((String) request.getParameter("idregistro"));
		
		boolean result = false;
		String tipotirocinio= (String) request.getParameter("tipotirocinio");
		
		//per tutor accademico
		
		if (name.equals("tutoraccademico")) {
			
			//caso di tirocinio interno --> firmo come responsabile e come tutor
			if(tipotirocinio.equals("interno")) {
				//approvo e cambio lo status del registro in completo
				
				result = RegistroDAO.doAlterFirmaTutorInterno(idregistro);
			
			
			}
			else { // caso di tirocinio esterno firmo solo come tutor accademico
				result = RegistroDAO.doAlterFirmaTutorAc(idregistro);
			}
			
			
		}
		
		//pdcd
		
		else if (name.contentEquals("pdcd")) { // pdcd firma come responsabile per il tirocinio esterno
			
			int idtirocinio = Integer.parseInt((String) request.getParameter("idtirocinio"));
			if(tipotirocinio.equals("esterno")) {
				result = RegistroDAO.doAlterFirmaPdcdEsterno(idtirocinio,idtirocinio);
			}
			else if(tipotirocinio.equals("interno")) {
				result = RegistroDAO.doAlterFirmaPdcdInterno(idregistro,idtirocinio);
			}

		}
		
		//tutoraziendale
		else if(name.contentEquals("tutoraziendale")) {
			
			result=RegistroDAO.doAlterFirmaTutorAz(idregistro);
			
		}
		
		request.setAttribute("resultapprovaregistro", result);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/listaTirocini.jsp");
		dispatcher.forward(request, response);
		

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
