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
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
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
				
				String downloadFilePath=applicazionePath+"moduliRiconoscimenti"+File.separator+idRiconoscimento;
				
				
			
				File directory= new File(downloadFilePath);
				
				// prendiamo tutti i file allegati, salvati nel momento in cui lo studente ha consengato tali file.
				
				File[] listFile= directory.listFiles();
				ArrayList<File> arrayFile=new ArrayList<File>();
				
				for(File file:listFile) {
					arrayFile.add(file);
				}
				
				// oltre ai file allegati, inseriamo nella lista anche il file del modulo di riconoscimento di attività lavorativa.
			
				arrayFile.add(fileRiconoscimento);
				
		        response.setContentType("application/zip");
		        response.setHeader("Content-Disposition", "attachment; filename=mytest.zip");
		        
		        ServletOutputStream out=response.getOutputStream();
		        ZipOutputStream zos=new ZipOutputStream(new BufferedOutputStream(out));
		        for(int i=0;i<arrayFile.size();i++) {
			        
			        File downloadFile = arrayFile.get(i);
			        
			        
			        
			        zos.putNextEntry(new ZipEntry(downloadFile.getName()));
			        
			        FileInputStream inStream = new FileInputStream(downloadFile);
			        
			        BufferedInputStream inBuff=new BufferedInputStream(inStream);
			        
			        int data=0;
			        while((data=inBuff.read())!=-1) {
			        	zos.write(data);
		        }
		        inBuff.close();
		        
		        zos.closeEntry();
		        
		        }  
		        zos.close();
		     
			}
			else
				request.getRequestDispatcher("permissionDenied.jsp").forward(request, response);
			
			
		}
		
	}

	private File createModuloRiconoscimento(int idRiconoscimento,User utente,HttpServletRequest request) throws FileNotFoundException, DocumentException {
		Document documento=new Document();
		String pathFileWebApp=request.getServletContext().getRealPath("");
		File fileRiconoscimento=new File(pathFileWebApp+File.separator+"moduloRiconoscimento.pdf");
		
		
		
		PdfWriter writer=PdfWriter.getInstance(documento, new FileOutputStream(fileRiconoscimento));
		writer.setPdfVersion(PdfWriter.PDF_VERSION_1_7);
		writer.setTagged();
		writer.setViewerPreferences(PdfWriter.DisplayDocTitle);
		writer.createXmpMetadata();
		
		Riconoscimento moduloRiconoscimento=null;
		User studente=null;
		// se l'utente loggato è il pdcd devo prendere i dati dello studente.
		if(utente.getUserType()==2) {
			String email=request.getParameter("emailUser");
			moduloRiconoscimento=RiconoscimentoDao.getModuloRiconoscimento(email);
			studente=UserDAO.getStudenteWithEmail(email);
		}
		else {
			//altrimenti prendo i dati dello studente direttamente dall'utente loggato. 
			moduloRiconoscimento=RiconoscimentoDao.getModuloRiconoscimento(utente.getEmail());
			studente=utente;
		}
		
		Font campiTitoli=new Font(Font.FontFamily.HELVETICA,10,Font.BOLD,BaseColor.BLACK);
		Font campiStatiche= new Font(Font.FontFamily.HELVETICA,8,Font.NORMAL,BaseColor.BLACK);
		Font campiCompilati=new Font(Font.FontFamily.HELVETICA,8,Font.BOLD,BaseColor.BLACK);
		
		documento.open();
		Phrase stringaTitleDomanda= new Phrase();
		stringaTitleDomanda.add(new Chunk("DOMANDA DI RICONOSCIMENTO DEI CREDITI FORMATIVI PREVISTI PER IL TIROCINIO",campiTitoli));
		
		Phrase sezioneDatiAnagrafici=new Phrase();
		
		// per scrivere i paragrafi in base al sesso dello studente del modulo corrispondete. 
		if(studente.getSex()=='M'){
			sezioneDatiAnagrafici.add(new Chunk("Io sottoscrito ",campiStatiche));
			sezioneDatiAnagrafici.add(new Chunk(studente.getName()+" "+ studente.getSurname(),campiCompilati));
			sezioneDatiAnagrafici.add(new Chunk(" nato a ",campiStatiche));
			sezioneDatiAnagrafici.add(new Chunk(studente.getLuogoDiNascita(),campiCompilati));
			sezioneDatiAnagrafici.add(new Chunk(" il ",campiStatiche));
			sezioneDatiAnagrafici.add(new Chunk(studente.getDataDiNascita(),campiCompilati));
			sezioneDatiAnagrafici.add(new Chunk(" residente a ",campiStatiche));
			sezioneDatiAnagrafici.add(new Chunk(studente.getResidente(),campiCompilati));
			sezioneDatiAnagrafici.add(new Chunk(" via ",campiStatiche));
			sezioneDatiAnagrafici.add(new Chunk(studente.getVia(),campiCompilati));
			sezioneDatiAnagrafici.add(new Chunk(" telefono ",campiStatiche));
			sezioneDatiAnagrafici.add(new Chunk(studente.getTelefono(),campiCompilati));
			sezioneDatiAnagrafici.add(new Chunk(", email ",campiStatiche));
			sezioneDatiAnagrafici.add(new Chunk(studente.getEmail(),campiCompilati));
			sezioneDatiAnagrafici.add(new Chunk(" iscritto al corso di laurea",campiStatiche));
			sezioneDatiAnagrafici.add(new Chunk(studente.getCorso(),campiCompilati));
			sezioneDatiAnagrafici.add(new Chunk(" matricola n° ",campiStatiche));
			sezioneDatiAnagrafici.add(new Chunk(studente.getMatricola(),campiCompilati));
		}
		else if(studente.getSex()=='F'){
			sezioneDatiAnagrafici.add(new Chunk("Io sottoscrita ",campiStatiche));
			sezioneDatiAnagrafici.add(new Chunk(studente.getName()+" "+ studente.getSurname(),campiCompilati));
			sezioneDatiAnagrafici.add(new Chunk(" nata a ",campiStatiche));
			sezioneDatiAnagrafici.add(new Chunk(studente.getLuogoDiNascita(),campiCompilati));
			sezioneDatiAnagrafici.add(new Chunk(" il ",campiStatiche));
			sezioneDatiAnagrafici.add(new Chunk(studente.getDataDiNascita(),campiCompilati));
			sezioneDatiAnagrafici.add(new Chunk(" residente a ",campiStatiche));
			sezioneDatiAnagrafici.add(new Chunk(studente.getResidente(),campiCompilati));
			sezioneDatiAnagrafici.add(new Chunk(" via ",campiStatiche));
			sezioneDatiAnagrafici.add(new Chunk(studente.getVia(),campiCompilati));
			sezioneDatiAnagrafici.add(new Chunk(" telefono ",campiStatiche));
			sezioneDatiAnagrafici.add(new Chunk(studente.getTelefono(),campiCompilati));
			sezioneDatiAnagrafici.add(new Chunk(", email ",campiStatiche));
			sezioneDatiAnagrafici.add(new Chunk(studente.getEmail(),campiCompilati));
			sezioneDatiAnagrafici.add(new Chunk(" iscritta al corso di laurea ",campiStatiche));
			sezioneDatiAnagrafici.add(new Chunk(studente.getCorso(),campiCompilati));
			sezioneDatiAnagrafici.add(new Chunk(" matricola n° ",campiStatiche));
			sezioneDatiAnagrafici.add(new Chunk(studente.getMatricola(),campiCompilati));
		}
		
		Phrase stringaChiedo=new Phrase(new Chunk("CHIEDO",campiTitoli));
		
		Phrase stringaValutata= new Phrase(new Chunk("Che venga valutata l’esperienza professionale da me maturata e così caratterizzata:",campiStatiche));
		
		Phrase stringaPrimaEnteAzienda=new Phrase(new Chunk("ENTE/AZIENDA\nPresso cui è stata svolta l'attività",campiStatiche));
		
		Phrase stringaCampoEnteAzienda= new Phrase(new Chunk(moduloRiconoscimento.getEnteAzienda(),campiCompilati));
		
		Phrase stringaSecondaIndirizzoSede=new Phrase(new Chunk("INDIRIZZO SEDE\nPresso cui è stata svolta l'attività",campiStatiche));
		Phrase stringaCampoIndirizzoSede=new Phrase(new Chunk(moduloRiconoscimento.getIndirizzoSede(),campiCompilati));
		
		
		Phrase stringaTerzaProfilo= new Phrase(new Chunk("PROFILO",campiStatiche));
		Phrase stringaCampoProfilo=new Phrase(new Chunk(moduloRiconoscimento.getProfilo(),campiCompilati));
		
		
		Phrase stringaQuartaTipo=new Phrase(new Chunk("TIPO DI CONTRATTO",campiStatiche));
		Phrase stringaCampoTipo=new Phrase(new Chunk(moduloRiconoscimento.getTipoContratto(),campiCompilati));
		
		
		Phrase stringaQuintaPeriodo=new Phrase(new Chunk("PERIODO\n		(data inizio-data fine)",campiStatiche));
		Phrase stringaCampoPeriodo=new Phrase(new Chunk(moduloRiconoscimento.getPeriodo(),campiCompilati));
		
		
		Phrase stringaSestaOre=new Phrase(new Chunk("Ore svolte alla data della certificazione dell'azienda",campiStatiche));
		Phrase stringaCampoOre=new Phrase(new Chunk(""+moduloRiconoscimento.getOreSvolte(),campiCompilati));
		
		
		int cfuTirocinioEsterno=moduloRiconoscimento.getCFUTirocinioEsterno();
		int cfuTirocinioObbligatorio=moduloRiconoscimento.getCFUTirocinioObbligatorio();
		int cfuAccompagnamento=moduloRiconoscimento.getCFUAccompagnamentoLavoro();
		
		int cfuTotale= cfuTirocinioEsterno+cfuTirocinioObbligatorio+cfuAccompagnamento;
		
		Phrase stringaNtotaleCFU=new Phrase();
		stringaNtotaleCFU.add(new Chunk("ai fini del riconoscimento di N° ",campiStatiche));
		stringaNtotaleCFU.add(new Chunk(""+cfuTotale,campiCompilati));
		stringaNtotaleCFU.add(new Chunk(" CFU relativi al tirocinio previsti nel mio piano di studi, di cui N° ",campiStatiche));
		stringaNtotaleCFU.add(new Chunk(""+cfuTirocinioObbligatorio,campiCompilati));
		stringaNtotaleCFU.add(new Chunk(" CFU di Tirocinio Obbligatorio e ",campiStatiche));
		stringaNtotaleCFU.add(new Chunk(""+cfuTirocinioEsterno,campiCompilati));
		stringaNtotaleCFU.add(new Chunk(" CFU di tirocinio Esterno come scelta libera e di ",campiStatiche));
		stringaNtotaleCFU.add(new Chunk(""+cfuAccompagnamento,campiCompilati));
		stringaNtotaleCFU.add(new Chunk(" CFU di Accompagnamento al mondo del Lavoro, previsto nel mio piano di studi.",campiStatiche));
		
		

		
		
		
		
		Paragraph paragrafoTitleDomanda = new Paragraph(stringaTitleDomanda);
		Paragraph paragrafoDatiAnagrafici=new Paragraph(sezioneDatiAnagrafici);
		Paragraph paragrafoChiedo = new Paragraph(stringaChiedo);
		Paragraph paragrafoValutata=new Paragraph(stringaValutata);
		
		PdfPTable table= new PdfPTable(6);
		table.setWidths(new int[] {1,1,1,1,2,1});
		table.setWidthPercentage(100);
		
		Paragraph campoPrimaEnteAzienda=new Paragraph(stringaPrimaEnteAzienda);
		Paragraph campoSecondaIndirizzo=new Paragraph(stringaSecondaIndirizzoSede);
		Paragraph campoTerzoProfilo=new Paragraph(stringaTerzaProfilo);
		Paragraph campoQuartoTipo= new Paragraph(stringaQuartaTipo);
		Paragraph campoQuintoPeriodo= new Paragraph(stringaQuintaPeriodo);
		Paragraph campoSestoOre=new Paragraph(stringaSestaOre);
		
		
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
		campo5.setPadding(20);
		
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
		
		Paragraph paragrafoNTotaleCFU=new Paragraph(stringaNtotaleCFU);
		
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
