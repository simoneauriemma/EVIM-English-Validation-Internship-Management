package controller.GestioneModuloRiconoscimento;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


// import riguardanti alla creazione del PDF, grazie ad itext5
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import model.Riconoscimento;
import model.RiconoscimentoDao;
import model.User;
import model.UserDAO;

/**
 * @author Antonio Giano
 * Questa servlet permette la visualizzazione del modulo di riconoscimento di attivita lavorativa e i file allegati. 
 */
@WebServlet("/VisualizzaModuloRiconoscimento")
public class VisualizzaModuloRiconoscimento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaModuloRiconoscimento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // reads input file from an absolute path
		HttpSession sessione = request.getSession();
		if (sessione.getAttribute("utenteLoggato") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/login.jsp");
			dispatcher.forward(request, response);
			}
		else {
			int idRiconoscimento=Integer.parseInt(request.getParameter("idRiconoscimento"));
			String tipoUtente=(String) sessione.getAttribute("utenteLoggato").getClass().getName();
			if(tipoUtente.equalsIgnoreCase("model.user")) {
				User studente=(User) sessione.getAttribute("utenteLoggato");
				File fileRiconoscimento=null;
				try {
					 fileRiconoscimento=createModuloRiconoscimento(idRiconoscimento,studente,request);
					
				} catch (FileNotFoundException | DocumentException e) {
					e.printStackTrace();
				}
				
				String applicazionePath=request.getServletContext().getRealPath("");
				System.out.println("applicazione path-->"+applicazionePath);
				String downloadFilePath=applicazionePath+"moduliRiconoscimenti"+File.separator+idRiconoscimento;
				
				System.out.println("Path assoluto del web application-->"+downloadFilePath);
				File directory= new File(downloadFilePath);
				
				// prendiamo tutti i file allegati, salvati nel momento in cui lo studente ha consengato tali file.
				
				File[] listFile= directory.listFiles();
				ArrayList<File> arrayFile=new ArrayList<File>();
				
				for(File file:listFile) {
					arrayFile.add(file);
				}
				
				// oltre ai file allegati, inseriamo nella lista anche il file del modulo di riconoscimento di attività lavorativa.
			
				arrayFile.add(fileRiconoscimento);
				
				for(File file: arrayFile) {
					System.out.println("file preso-->"+file.getAbsolutePath());
				}
				
		        response.setContentType("application/zip");
		        response.setHeader("Content-Disposition", "attachment; filename=mytest.zip");
		        
		        ServletOutputStream out=response.getOutputStream();
		        ZipOutputStream zos=new ZipOutputStream(new BufferedOutputStream(out));
		        for(int i=0;i<arrayFile.size();i++) {
			        System.out.println("\n\nsetto\n\n");
			        File downloadFile = arrayFile.get(i);
			        
			        
			        System.out.println("aggiungiamo il file-->"+downloadFile.getName());
			        zos.putNextEntry(new ZipEntry(downloadFile.getName()));
			        
			        FileInputStream inStream = new FileInputStream(downloadFile);
			        
			        BufferedInputStream inBuff=new BufferedInputStream(inStream);
			        
			        int data=0;
			        while((data=inBuff.read())!=-1) {
			        	zos.write(data);
		        }
		        inBuff.close();
		        
		        zos.closeEntry();
		        System.out.println("Finito il file-->"+downloadFile.getName());
		        }  
		        zos.close();
			}
			else
				request.getRequestDispatcher("permissionDenied.jsp").forward(request, response);
			
			
		}
		
	}

	private File createModuloRiconoscimento(int idRiconoscimento,User utente,HttpServletRequest request) throws FileNotFoundException, DocumentException {
		Document documento=new Document();
		File fileRiconoscimento=new File("moduloRiconoscimento.pdf");
		
		PdfWriter writer=PdfWriter.getInstance(documento, new FileOutputStream(fileRiconoscimento));
		writer.setPdfVersion(PdfWriter.PDF_VERSION_1_7);
		writer.setTagged();
		writer.setViewerPreferences(PdfWriter.DisplayDocTitle);
		writer.createXmpMetadata();
		System.out.println("path assoluta"+fileRiconoscimento.getAbsolutePath());
		Riconoscimento moduloRiconoscimento=null;
		User studente=null;
		if(utente.getUserType()==2) {
			String email=request.getParameter("emailUser");
			moduloRiconoscimento=RiconoscimentoDao.getModuloRiconoscimento(email);
			studente=UserDAO.getStudenteWithEmail(email);
		}
		else {
			moduloRiconoscimento=RiconoscimentoDao.getModuloRiconoscimento(utente.getEmail());
			studente=utente;
		}
		
		documento.open();
		
		String stringaTitleDomanda="DOMANDA DI RICONOSCIMENTO DEI CREDITI FORMATIVI PREVISTI PER IL TIROCINIO";
		String sezioneDatiAnagrafici="";
		// per scrivere i paragrafi in base al sesso dello studente del modulo corrispondete. 
		if(studente.getSex()=='M'){
			 sezioneDatiAnagrafici="Io sottoscritto "+ studente.getName()+" "+ studente.getSurname()+" nato a "+ studente.getLuogoDiNascita()+
					" il "+ studente.getDataDiNascita()+" residente a "+ studente.getResidente()+" via "+ studente.getVia()+" telefono "+ studente.getTelefono()+
					",email "+ studente.getEmail() + " iscritto al corso di laurea "+ studente.getCorso()+ "matricola n° "+ studente.getMatricola();
		}
		else if(studente.getSex()=='F'){
			sezioneDatiAnagrafici="Io sottoscritta "+ studente.getName()+" "+ studente.getSurname()+" nata a "+ studente.getLuogoDiNascita()+
					" il "+ studente.getDataDiNascita()+" residente a "+ studente.getResidente()+" via "+ studente.getVia()+" telefono "+ studente.getTelefono()+
					", email "+ studente.getEmail() + " iscritta al corso di laurea "+ studente.getCorso()+ " matricola n° "+ studente.getMatricola();
		}
		String stringaChiedo="CHIEDO";
		
		String stringaValutata="Che venga valutata l’esperienza professionale da me maturata e così caratterizzata:\n";
		
		String stringaPrimaEnteAzienda="ENTE/AZIENDA\nPresso cui è stata svolta l'attività";
		String stringaCampoEnteAzienda=moduloRiconoscimento.getEnteAzienda();
		
		String stringaSecondaIndirizzoSede="INDIRIZZO SEDE\nPresso cui è stata svolta l'attività";
		String stringaCampoIndirizzoSede=moduloRiconoscimento.getIndirizzoSede();
		
		String stringaTerzaProfilo="PROFILO";
		String stringaCampoProfilo=moduloRiconoscimento.getProfilo();
		
		String stringaQuartaTipo="TIPO DI CONTRATTO";
		String stringaCampoTipo=moduloRiconoscimento.getTipoContratto();
		
		String stringaQuintaPeriodo="PERIODO\n(data inizio-data fine)";
		String stringaCampoPeriodo=moduloRiconoscimento.getPeriodo();
		
		String stringaSestaOre="Ore svolte alla data della certificazione dell'azienda";
		String stringaCampoOre=""+moduloRiconoscimento.getOreSvolte();
		
		int CFUTirocinioEsterno=moduloRiconoscimento.getCFUTirocinioEsterno();
		int CFUTirocinioObbligatorio=moduloRiconoscimento.getCFUTirocinioObbligatorio();
		int CFUAccompagnamento=moduloRiconoscimento.getCFUAccompagnamentoLavoro();
		
		String stringaNTotaleCFU="ai fini del riconoscimento di N° "+CFUTirocinioEsterno+CFUTirocinioObbligatorio+" CFU relativi al tirocinio previsti"
				+ "nel mio piano di studi, di cui N° "+CFUTirocinioObbligatorio+" CFU di Tirocinio Obbligatorio e "+ CFUTirocinioEsterno+" CFU di tirocinio Esterno come scelta libera"
						+ " e di "+ CFUAccompagnamento+" CFU di Accompagnamento al mondo del Lavoro, previsto nel mio piano di studi.";
		
		
		
		
		Paragraph paragrafoTitleDomanda = new Paragraph(stringaTitleDomanda,FontFactory.getFont(FontFactory.TIMES_BOLD, 10, BaseColor.BLACK));
		Paragraph paragrafoDatiAnagrafici=new Paragraph(sezioneDatiAnagrafici,FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.BLACK));
		Paragraph paragrafoChiedo = new Paragraph(stringaChiedo,FontFactory.getFont(FontFactory.TIMES_BOLD, 10, BaseColor.BLACK));
		Paragraph paragrafoValutata=new Paragraph(stringaValutata,FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.BLACK));
		
		PdfPTable table= new PdfPTable(6);
		table.setWidths(new int[] {1,1,1,1,2,1});
		Font fontCampi=FontFactory.getFont(FontFactory.TIMES_BOLD,8,BaseColor.BLACK);
		
		Paragraph campoPrimaEnteAzienda=new Paragraph(stringaPrimaEnteAzienda,fontCampi);
		Paragraph campoSecondaIndirizzo=new Paragraph(stringaSecondaIndirizzoSede,fontCampi);
		Paragraph campoTerzoProfilo=new Paragraph(stringaTerzaProfilo,fontCampi);
		Paragraph campoQuartoTipo= new Paragraph(stringaQuartaTipo,fontCampi);
		Paragraph campoQuintoPeriodo= new Paragraph(stringaQuintaPeriodo,fontCampi);
		Paragraph campoSestoOre=new Paragraph(stringaSestaOre,fontCampi);
		
		
		PdfPCell campo1=new PdfPCell(campoPrimaEnteAzienda);
		PdfPCell campo2=new PdfPCell(campoSecondaIndirizzo);
		PdfPCell campo3=new PdfPCell(campoTerzoProfilo);
		PdfPCell campo4=new PdfPCell(campoQuartoTipo);
		PdfPCell campo5=new PdfPCell(campoQuintoPeriodo);
		PdfPCell campo6=new PdfPCell(campoSestoOre);
	
		campo1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		campo1.setUseAscender(true);
		campo1.setUseDescender(true);
		campo1.setPadding(10);
		
		campo2.setVerticalAlignment(Element.ALIGN_MIDDLE);
		campo2.setUseAscender(true);
		campo2.setUseDescender(true);
		campo2.setPadding(10);
		
		campo3.setVerticalAlignment(Element.ALIGN_MIDDLE);
		campo3.setUseAscender(true);
		campo3.setUseDescender(true);
		campo3.setPadding(10);
		
		campo4.setVerticalAlignment(Element.ALIGN_MIDDLE);
		campo4.setUseAscender(true);
		campo4.setUseDescender(true);
		campo4.setPadding(10);
		
		campo5.setVerticalAlignment(Element.ALIGN_MIDDLE);
		campo5.setUseAscender(true);
		campo5.setUseDescender(true);
		campo5.setPadding(10);
		
		campo6.setVerticalAlignment(Element.ALIGN_MIDDLE);
		campo6.setUseAscender(true);
		campo6.setUseDescender(true);
		campo6.setPadding(10);
		
		
		table.addCell(campo1);
		table.addCell(campo2);
		table.addCell(campo3);
		table.addCell(campo4);
		table.addCell(campo5);
		table.addCell(campo6);
		
		table.setSpacingBefore(10);
		table.setSpacingAfter(10);
		
		
		PdfPCell valore1=new PdfPCell(new Paragraph(stringaCampoEnteAzienda));
		PdfPCell valore2=new PdfPCell(new Paragraph(stringaCampoIndirizzoSede));
		PdfPCell valore3=new PdfPCell(new Paragraph(stringaCampoProfilo));
		PdfPCell valore4=new PdfPCell(new Paragraph(stringaCampoTipo));
		PdfPCell valore5=new PdfPCell(new Paragraph(stringaCampoPeriodo));
		PdfPCell valore6=new PdfPCell(new Paragraph(stringaCampoOre));
		
		table.addCell(valore1);
		table.addCell(valore2);
		table.addCell(valore3);
		table.addCell(valore4);
		table.addCell(valore5);
		table.addCell(valore6);
		
		Paragraph paragrafoNTotaleCFU=new Paragraph(stringaNTotaleCFU,FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.BLACK));
		
		paragrafoTitleDomanda.setAlignment(Element.ALIGN_CENTER);
		paragrafoChiedo.setAlignment(Element.ALIGN_CENTER);
		
		
		documento.add(paragrafoTitleDomanda);
		documento.add(paragrafoDatiAnagrafici);
		documento.add(paragrafoChiedo);
		documento.add(paragrafoValutata);
		documento.add(table);
		documento.add(paragrafoNTotaleCFU);
		
		documento.close();
		
		return fileRiconoscimento;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
