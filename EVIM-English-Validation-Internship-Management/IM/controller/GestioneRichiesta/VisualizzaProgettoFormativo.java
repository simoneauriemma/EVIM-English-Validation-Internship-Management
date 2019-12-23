package controller.GestioneRichiesta;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import model.PDFProgettoFormativo;
import model.TirocinioEsterno;
import model.TirocinioEsternoDAO;
import model.TirocinioInternoDAO;

/**
 * @author Antonio Giano Questa servlet permette di generare un pdf del progetto
 *         formativo.
 */
@WebServlet("/VisualizzaProgettoFormativo")
public class VisualizzaProgettoFormativo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id= (int) request.getAttribute("id");
		String tirocinio=(String) request.getAttribute("tirocinio");
		response.setContentType("application/pdf");
		try {
			if(tirocinio.equalsIgnoreCase("interno"))
			{
				PDFProgettoFormativo pdfProgetto=TirocinioInternoDAO.getProgettoFormativoInterno(id);
				createProgettoFormativoInterno(request, response, pdfProgetto);
			}
			else if(tirocinio.equalsIgnoreCase("esterno")) {
				PDFProgettoFormativo pdfProgetto=TirocinioEsternoDAO.getProgettoFormativoEsterno(id);
				createProgettoFormativoEsterno(request, response, pdfProgetto);
			}
		}catch (DocumentException e) {
			e.printStackTrace();
		}catch (IOException m) {
			m.printStackTrace();
			throw new IOException(m.getMessage());
		}
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
	
	private Document createProgettoFormativoInterno(HttpServletRequest request, HttpServletResponse response,PDFProgettoFormativo pdfProgetto) throws DocumentException, IOException
	{
			Document document = new Document();

			PdfWriter.getInstance(document, response.getOutputStream());

			document.open();

			ArrayList<Paragraph> listeParagrafi = new ArrayList<Paragraph>();
			// Image image1=Image.getInstance(image);
			String stringaUniversita = "UNIVERSITÀ DEGLI STUDI DI SALERNO 	\n DIPARTIMENTO DI INFORMATICA";
			String stringaProgetto = "PROGETTO FORMATIVO E DI ORIENTAMENTO LAUREA TRIENNALE / LAUREA MAGISTRALE\n";
			String stringaPromotoreTitle = "\nSOGGETTO PROMOTORE";
			String stringaPromotore = "Dipartimento di Informatica dell’Università degli Studi di Salerno;\nSede in Via Giovanni Paolo II, 132, 84084 Fisciano (Salerno)\nIndirizzo PEC ammicent@pec.unisa.it\n"
					+ "Codice Fiscale 80018670655\n"
					+ "Rappresentante legale: prof. Alfredo De Santis, in qualità di Direttore pro tempore, nato a nato a Nocera Inferiore (SA) il 07/12/1960.\n";

			String stringaTirocinanteTitle = "\nTirocinante";
			String stringaTirocinante = "Cognome:" + pdfProgetto.getCognomeStudente()+ " Nome:"+pdfProgetto.getNomeStudente()+"\n" + "Data e luogo di nascita "
					+ "\nCittadinanza" + "\nResidenza\n"+ "Email"+pdfProgetto.getEmailStudente();
			
			String stringaTutorAccademicoTitle="\nTutor Accademico";
			String stringaTutorAccademico="Cognome:"+pdfProgetto.getCognomeTutorAccademico()+" Nome:"+ pdfProgetto.getNomeStudente()+"\nEmail:"+pdfProgetto.getEmailTutorAccademico();
			
			String stringaPropostaTitle="\nProposta";
			String stringaPropsota="\nObiettivo:"+ pdfProgetto.getObiettivi()+"\nAttività:"+pdfProgetto.getAttivita()+"\nModalità:"+pdfProgetto.getModalita();

			
			
			Paragraph paragrafoUniversita = new Paragraph(stringaUniversita,
					FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, new BaseColor(0, 0, 111)));
			
			Paragraph paragrafoProgetto = new Paragraph(stringaProgetto,
					FontFactory.getFont(FontFactory.TIMES_BOLD, 10, BaseColor.BLACK));
			
			Paragraph paragrafoPromotoreTitle = new Paragraph(stringaPromotoreTitle,
					FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.BLACK));
			Paragraph paragrafoPromotore = new Paragraph(stringaPromotore,
					FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.BLACK));
			
			Paragraph paragrafoTirocinanteTitle = new Paragraph(stringaTirocinanteTitle,
					FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.BLACK));
			Paragraph paragrafoTirocinante = new Paragraph(stringaTirocinante,
					FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.BLACK));
			
			Paragraph paragrafoTutorTitle=new Paragraph(stringaTutorAccademicoTitle,FontFactory.getFont(FontFactory.TIMES_BOLD,8,Font.BOLD,BaseColor.BLACK));
			Paragraph paragrafoTutor=new Paragraph(stringaTutorAccademico,FontFactory.getFont(FontFactory.TIMES_BOLD,8,BaseColor.BLACK));
			
			Paragraph paragrafoPropostaTitle=new Paragraph(stringaPropostaTitle,FontFactory.getFont(FontFactory.TIMES_BOLD,8,Font.BOLD,BaseColor.BLACK));
			Paragraph paragrafoProposta=new Paragraph(stringaPropsota,FontFactory.getFont(FontFactory.TIMES_BOLD,8,BaseColor.BLACK));
			

			listeParagrafi.add(paragrafoUniversita);
			listeParagrafi.add(paragrafoProgetto);
			listeParagrafi.add(paragrafoPromotoreTitle);
			listeParagrafi.add(paragrafoPromotore);
			listeParagrafi.add(paragrafoTirocinanteTitle);
			listeParagrafi.add(paragrafoTirocinante);
			listeParagrafi.add(paragrafoTutorTitle);
			listeParagrafi.add(paragrafoTutor);
			listeParagrafi.add(paragrafoPropostaTitle);
			listeParagrafi.add(paragrafoProposta);
			

			paragrafoUniversita.setAlignment(Element.ALIGN_CENTER);
			paragrafoProgetto.setAlignment(Element.ALIGN_CENTER);

			for (int i = 0; i < listeParagrafi.size(); i++) {
				document.add(listeParagrafi.get(i));
			}
			// document.add(image1);
			// step 5
			document.close();
			
			return document;
		}

	private Document createProgettoFormativoEsterno(HttpServletRequest request, HttpServletResponse response,
			PDFProgettoFormativo pdfProgetto) throws DocumentException, IOException {
		Document document = new Document();

		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		ArrayList<Paragraph> listeParagrafi = new ArrayList<Paragraph>();
		// Image image1=Image.getInstance(image);
		String stringaUniversita = "UNIVERSITÀ DEGLI STUDI DI SALERNO 	\n DIPARTIMENTO DI INFORMATICA";
		String stringaProgetto = "PROGETTO FORMATIVO E DI ORIENTAMENTO LAUREA TRIENNALE / LAUREA MAGISTRALE\n";
		String stringaPromotoreTitle = "\nSOGGETTO PROMOTORE";
		String stringaPromotore = "Dipartimento di Informatica dell’Università degli Studi di Salerno;\nSede in Via Giovanni Paolo II, 132, 84084 Fisciano (Salerno)\nIndirizzo PEC ammicent@pec.unisa.it\n"
				+ "Codice Fiscale 80018670655\n"
				+ "Rappresentante legale: prof. Alfredo De Santis, in qualità di Direttore pro tempore, nato a nato a Nocera Inferiore (SA) il 07/12/1960.\n";

		String stringaTirocinanteTitle = "\nTirocinante";
		String stringaTirocinante = "Cognome:" + pdfProgetto.getCognomeStudente()+ " Nome:"+pdfProgetto.getNomeStudente()+"\n" + "Data e luogo di nascita "
				+ "\nCittadinanza" + "\nResidenza\n"+ "Email"+pdfProgetto.getEmailStudente();
		
		String stringaTutorAccademicoTitle="\nTutor Accademico";
		String stringaTutorAccademico="Cognome:"+pdfProgetto.getCognomeTutorAccademico()+" Nome:"+ pdfProgetto.getNomeStudente()+"\nEmail:"+pdfProgetto.getEmailTutorAccademico();
		
		String stringaTutorAziendaleTitle="\nTutor Aziendale:";
		String stringaTutorAziendale="Cognome: "+ pdfProgetto.getCognomeTutorAziendale() + "Nome: " + pdfProgetto.getNomeTutorAziendale()+"\nEmail: " + pdfProgetto.getEmailTutorAziendale();
		
		String stringaPropostaTitle="\nProposta";
		String stringaPropsota="\nObiettivo:"+ pdfProgetto.getObiettivi()+"\nAttività:"+pdfProgetto.getAttivita()+"\nModalità:"+pdfProgetto.getModalita();

		
		
		Paragraph paragrafoUniversita = new Paragraph(stringaUniversita,
				FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, new BaseColor(0, 0, 111)));
		
		Paragraph paragrafoProgetto = new Paragraph(stringaProgetto,
				FontFactory.getFont(FontFactory.TIMES_BOLD, 10, BaseColor.BLACK));
		
		Paragraph paragrafoPromotoreTitle = new Paragraph(stringaPromotoreTitle,
				FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.BLACK));
		Paragraph paragrafoPromotore = new Paragraph(stringaPromotore,
				FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.BLACK));
		
		Paragraph paragrafoTirocinanteTitle = new Paragraph(stringaTirocinanteTitle,
				FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.BLACK));
		Paragraph paragrafoTirocinante = new Paragraph(stringaTirocinante,
				FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.BLACK));
		
		Paragraph paragrafoTutorTitle=new Paragraph(stringaTutorAccademicoTitle,FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD,BaseColor.BLACK));
		Paragraph paragrafoTutor=new Paragraph(stringaTutorAccademico,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		
		Paragraph paragrafoPropostaTitle=new Paragraph(stringaPropostaTitle,FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD,BaseColor.BLACK));
		Paragraph paragrafoProposta=new Paragraph(stringaPropsota,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		
		Paragraph paragrafoTutorAziendaleTitle=new Paragraph(stringaTutorAziendaleTitle,FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD,BaseColor.BLACK));
		Paragraph paragrafoTutorAziendale=new Paragraph(stringaTutorAziendale,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		

		listeParagrafi.add(paragrafoUniversita);
		listeParagrafi.add(paragrafoProgetto);
		listeParagrafi.add(paragrafoPromotoreTitle);
		listeParagrafi.add(paragrafoPromotore);
		listeParagrafi.add(paragrafoTirocinanteTitle);
		listeParagrafi.add(paragrafoTirocinante);
		listeParagrafi.add(paragrafoTutorTitle);
		listeParagrafi.add(paragrafoTutor);
		listeParagrafi.add(paragrafoTutorAziendaleTitle);
		listeParagrafi.add(paragrafoTutorAziendale);
		listeParagrafi.add(paragrafoPropostaTitle);
		listeParagrafi.add(paragrafoProposta);
		
		

		paragrafoUniversita.setAlignment(Element.ALIGN_CENTER);
		paragrafoProgetto.setAlignment(Element.ALIGN_CENTER);

		for (int i = 0; i < listeParagrafi.size(); i++) {
			document.add(listeParagrafi.get(i));
		}
		// document.add(image1);
		// step 5
		document.close();
		
		return document;
		
	}
}
