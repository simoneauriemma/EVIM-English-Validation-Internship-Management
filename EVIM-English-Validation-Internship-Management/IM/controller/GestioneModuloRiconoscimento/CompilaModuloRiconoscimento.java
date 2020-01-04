package controller.GestioneModuloRiconoscimento;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
@MultipartConfig(fileSizeThreshold = 1024*1024*10,
					maxFileSize = 1024*1024*50,
					maxRequestSize = 1024*1024*100)
public class CompilaModuloRiconoscimento extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/permissionDenied.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione=request.getSession();
		// controllo se è loggato l'utente altrimenti reindirizzo alla pagina login
		if (sessione.getAttribute("utenteLoggato") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/login.jsp");
			dispatcher.forward(request, response);
		}
		else {
			String tipoUtente=sessione.getAttribute("utenteLoggato").getClass().getName();
			User utente=(User) sessione.getAttribute("utenteLoggato");
			// non adatto per lo studente,pdcd,ufficio carriere
			if(!tipoUtente.equalsIgnoreCase("model.User")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/permissionDenied.jsp");
				dispatcher.forward(request, response);
			}
			//inserisco tali controlli per una maggiore sicurezza. Il PdCD e l'ufficio carriera non possono aver a che fare con tale pagina di compila modulo.
			else if(utente.getUserType()!=0){
				System.out.println("diverso");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/permissionDenied.jsp");
				dispatcher.forward(request, response);
			}
			else {
				String emailStudente=utente.getEmail();
				String enteAzienda = null;
				String indirizzoSede=null;
				
				String profilo=null;
				String tipoContratto=null;
				String periodo=null;
				int oreSvolte=-1;
				
				int cfuTirocinioObbligatorio=-1;
				int cfuTirocinioEsterno=-1;
				int cfuAccompagnamento=-1;
				
				ArrayList<filesNamesWithPart> filePartNames= new ArrayList<filesNamesWithPart>();
				
				InputStream inputStream;
				for(Part part: request.getParts()) {
						String nome=part.getName();
						System.out.println("Nome-->"+nome);
						
						inputStream = request.getPart(part.getName()).getInputStream();
						int i=inputStream.available();
						byte[] stringhe= new byte[i];
						inputStream.read(stringhe);
						String valore=new String(stringhe);
						System.out.println("il valore-->"+valore);
						
						if(nome.equalsIgnoreCase("file1[]")) {
							String nomeFile=getFileName(part);
							filePartNames.add(new filesNamesWithPart(part, nomeFile));
						}
						
						switch (nome) {
							case "enteAzienda":
								 enteAzienda=valore;
								break;
							case "indirizzoSede":
								 indirizzoSede=valore;
								break;
							case "profilo":
								 profilo=valore;
								break;
							case "tipoContratto":
								 tipoContratto=valore;
								break;
							case "periodo":
								 periodo=valore;
								break;
							case "oreSvolte":
								 oreSvolte=Integer.parseInt(valore);
								break;
							case "CFUObbligatorio":
								 cfuTirocinioObbligatorio=Integer.parseInt(valore);
								break;
							case "CFUEsterno":
								 cfuTirocinioEsterno=Integer.parseInt(valore);
								break;
							case "CFUAccompagnamento":
								 cfuAccompagnamento=Integer.parseInt(valore);
								break;
							default: 
								break;
						}
				}
				
			
				// prendo tutti i campi dal form e inserisco nei rispettivi campi nel Database
				if(!RiconoscimentoDao.insertRiconoscimenot(emailStudente, enteAzienda, indirizzoSede, profilo, tipoContratto, periodo, oreSvolte, cfuTirocinioObbligatorio, cfuTirocinioEsterno, cfuAccompagnamento)) {
					request.setAttribute("compilaModulo", false);
					request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
				}
				else {
					// prendo i file allegati dallo studente salvando i file nella directory di tale web application
					Riconoscimento moduloRiconoscimento=RiconoscimentoDao.getModuloRiconoscimento(utente.getEmail());
					uploadFile(request,response,moduloRiconoscimento.getIdRiconoscimento(),filePartNames);
					request.setAttribute("compilaModulo", true);
					request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
				}
			}
		}	
	}




	private void uploadFile(HttpServletRequest request, HttpServletResponse response,int idRiconoscimento,ArrayList<filesNamesWithPart> files) throws IOException, ServletException {
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
	
	for(filesNamesWithPart file: files) {
		System.out.println("nome-->"+file.getFileName());
		System.out.println("part nome-->"+file.getPartFile().getName());
	}
	
	for(filesNamesWithPart file: files) {
		if(!file.getFileName().equals("")) {
			file.getPartFile().write(uploadFilePath+File.separator+file.getFileName());
		}
	}
	
	//Poiche nel form l'enctype è multipart, prendiamo tutte le parti nella request. Quindi se uno studente manda due file, con questo pezzo di codice inseriamo tali due file nella directory giusta.
	/*for(Part part: request.getParts()) {
		String fileName= getFileName(part);
		System.out.println("prendo i file"+fileName);
		if(!fileName.equals(""))
				part.write(uploadFilePath+File.separator+fileName);
	}*/
	
	
	
	
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

	class filesNamesWithPart{
		Part partFile;
		String fileName;
		
		filesNamesWithPart(Part part, String fileName){
			this.partFile=part;
			this.fileName=fileName;
		}

		public Part getPartFile() {
			return partFile;
		}

		public String getFileName() {
			return fileName;
		}
		
		
	}
}
