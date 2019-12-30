package controller.GestioneModuloRiconoscimento;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.Riconoscimento;
import model.RiconoscimentoDao;
import model.User;

/**
 * @author Antonio Giano
 * Questa servlet crea il modulo dsi riconoscimento prendendo dei parametri dal form ricevutosi.
 */
@WebServlet("/CompilaModuloRiconoscimento")
public class CompilaModuloRiconoscimento extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("permissionDenied.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione=request.getSession();
		// controllo se è loggato l'utente altrimenti reindirizzo alla pagina login
		if (sessione.getAttribute("utenteLoggato") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/login.jsp");
			dispatcher.forward(request, response);
		}
		else {
			String tipoUtente=sessione.getAttribute("utenteLoggato").getClass().getName();
			// non adatto per lo studente,pdcd,ufficio carriere
			if(!tipoUtente.equalsIgnoreCase("model.User")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("permissionDenied.jsp");
				dispatcher.forward(request, response);
			}
			else {
				User studente=(User) sessione.getAttribute("utenteLoggato");
				
				
				int idModulo=Integer.parseInt(request.getParameter("idRiconoscimento"));
				String emailStudente=studente.getEmail();
				String enteAzienda=request.getParameter("enteAzienda");
				String indirizzoSede=request.getParameter("indirizzoSede");
				String profilo=request.getParameter("profilo");
				String tipoContratto=request.getParameter("tipoContratto");
				String periodo=request.getParameter("periodo");
				int oreSvolte=Integer.parseInt(request.getParameter("oreSvolte"));
				int CFUTirocinioObbligatorio=Integer.parseInt(request.getParameter("CFUObbligatorio"));
				int CFUTirocinioEsterno=Integer.parseInt(request.getParameter("CFUEsterno"));
				int CFUAccompagnamento=Integer.parseInt(request.getParameter("CFUAccompagnamento"));
				
				// prendo tutti i campi dal form e inserisco nei rispettivi campi nel Database
				if(!RiconoscimentoDao.insertRiconoscimenot(emailStudente, enteAzienda, indirizzoSede, profilo, tipoContratto, periodo, oreSvolte, CFUTirocinioObbligatorio, CFUTirocinioEsterno, CFUAccompagnamento)) {
					System.out.println("inserimento riconoscimento non successo");
				}
				
				// prendo i file allegati dallo studente salvando i file nella directory di tale web application
				Riconoscimento moduloRiconoscimento=RiconoscimentoDao.getModuloRiconoscimento(1);
				uploadFile(request,response,moduloRiconoscimento.getIdRiconoscimento());
				
				
				
				
				
			}
		}
	}


	private void uploadFile(HttpServletRequest request, HttpServletResponse response,int idRiconoscimento) throws IOException, ServletException {
	// prendiamo il path solluto di tale web application
	String applicazionePath=request.getServletContext().getRealPath("");
	// costruiamo la directory la quale viene salvato il file
	String uploadFilePath=applicazionePath+File.separator+"moduliRiconoscimenti"+File.separator+idRiconoscimento;
	
	// creiamo la directory se non esiste 
	File directoryPadre=new File(uploadFilePath);
	if(!directoryPadre.exists()) {
		directoryPadre.mkdirs();
		
	}
	System.out.println("Upload File Directory="+directoryPadre.getAbsolutePath());
	
	//Poiche nel form l'enctype è multipart, prendiamo tutte le parti di cui è costituito nella request. Quindi se uno studente manda due file, con questo pezzo di codice inseriamo tali due file nella directory giusta.
	for(Part part: request.getParts()) {
		String fileName= getFileName(part);
		if(!fileName.equals(""))
				part.write(uploadFilePath+File.separator+fileName);
	}
	
	request.setAttribute("message", "File uploaded successo");
	request.getRequestDispatcher("./WEB-INF/home.jsp").forward(request, response);
	
	}
	
	
	// con questo metodo prendo soltanto il campo includente il file allegato dallo studente
	private String getFileName(Part part) {
		String contenutoDisposition=part.getHeader("content-disposition");
		System.out.println("content-disposition header= "+contenutoDisposition);
		String[] tokens=contenutoDisposition.split(";");
		for(String token: tokens) {
			if(token.trim().startsWith("filename")) {
				return token.substring(token.indexOf("=")+2,token.length()-1);
			}
		}
		return "";
	}

}
