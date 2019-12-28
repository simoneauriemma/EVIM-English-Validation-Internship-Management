package controller.GestioneRichiesta;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import model.PDFProgettoFormativo;

import model.TirocinioEsternoDAO;
import model.TirocinioInternoDAO;
import model.User;

/**
 * @author Antonio Giano 
 * Questa servlet permette di generare un pdf del progetto
 * formativo.
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
		
		HttpSession sessione = request.getSession();
		if (sessione.getAttribute("utenteLoggato") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/login.jsp");
			dispatcher.forward(request, response);
			}
		else {
			User utente =(User) sessione.getAttribute("utenteLoggato");
			String corsoUtente=utente.getCorso();
			
			int id= (int) request.getAttribute("id");
			
			String tirocinio=(String) request.getAttribute("tirocinio");
			
			response.setContentType("application/pdf");
			try {
				if(tirocinio.equalsIgnoreCase("interno"))
				{
					PDFProgettoFormativo pdfProgetto=TirocinioInternoDAO.getProgettoFormativoInterno(id);
					createProgettoFormativoInterno(request, response, pdfProgetto,corsoUtente);
				}
				else if(tirocinio.equalsIgnoreCase("esterno")) {
					PDFProgettoFormativo pdfProgetto=TirocinioEsternoDAO.getProgettoFormativoEsterno(id);
					createProgettoFormativoEsterno(request, response, pdfProgetto,corsoUtente);
				}
			}catch (DocumentException e) {
				e.printStackTrace();
			}catch (IOException m) {
				m.printStackTrace();
				throw new IOException(m.getMessage());
			}
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
	
	private Document createProgettoFormativoInterno(HttpServletRequest request, HttpServletResponse response,PDFProgettoFormativo pdfProgetto,String corsoUtente) throws DocumentException, IOException
	{
		Document document = new Document();

		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		ArrayList<Paragraph> listeParagrafi = new ArrayList<Paragraph>();
		
		
		
		String stringaUniversita = "UNIVERSITÀ DEGLI STUDI DI SALERNO 	\n DIPARTIMENTO DI INFORMATICA";
		String stringaProgetto = "PROGETTO FORMATIVO E DI ORIENTAMENTO LAUREA TRIENNALE / LAUREA MAGISTRALE\n";
		String stringaPromotoreTitle = "\nSOGGETTO PROMOTORE";
		String stringaPromotore = "Dipartimento di Informatica dell’Università degli Studi di Salerno;\nSede in Via Giovanni Paolo II, 132, 84084 Fisciano (Salerno)\nIndirizzo PEC ammicent@pec.unisa.it\n"
				+ "Codice Fiscale 80018670655\n"
				+ "Rappresentante legale: prof. Alfredo De Santis, in qualità di Direttore pro tempore, nato a nato a Nocera Inferiore (SA) il 07/12/1960.\n";
		
		String stringaConvenzione="\nRelativo alla Convenzione per tirocinio di formazione ed orientamento (curriculare) stipulata con ____________________________ in data _________________, Repertorio N. _______";
		
		// inizio sezione soggetto ospitante
		String stringaTitleSoggettoOspitante="SOGGETTO OSPITANTE\n";
		String sezioneSoggettoOspitante="Denominazione ___________ (specificare la natura giuridica)\nSede legane in ___________"+
		"\nIndirizzo PEC ___________\nCodice Fiscale e Partita IVA ___________\nRappresentante legale: ___________, in qualita di ___________, nato a ___________, il ___________\n"+
				"Attivita economica esercitata ___________\nNumero Dipedenti a tempo indeterminato___________";
		// fine sezione soggetto ospitante
		
		
		String stringaTirocinanteTitle = "\nTirocinante";
		String stringaTirocinante = "Cognome: " + pdfProgetto.getCognomeStudente()+ " Nome: "+pdfProgetto.getNomeStudente()+"\n" + "Data e luogo di nascita:______ "
				+ "\nResidenza:______\n"+ "Codice Fiscale:______\n"+"Telefono:______\n"+"Email: "+pdfProgetto.getEmailStudente()+"\nIscritto al Corso di Laurea Triennale in Informatica\n";
		
		//inizio sezione tutor designato dal dipartimento
		String stringaTutorTitleDesignatoAccademico="\nTUTOR DESIGNATO DAL DIPARTIMENTO: ";
		String stringaTutorDesignatoAccademico=pdfProgetto.getNomeTutorAccademico()+ " " + pdfProgetto.getCognomeTutorAccademico()+"\n";
		//fine sezione tutor designato dal soggetto ospitante
		
		//inizio sezione tutor desginato dal soggetto ospitante
		String stringaTutorTitleDesignatoAziendale="\nTUTOR DESIGNATO DAL SOGGETTO OSPITANTE: ";
		String stringaTutorDesignatoAziendale=pdfProgetto.getNomeTutorAziendale()+" "+ pdfProgetto.getCognomeTutorAziendale();
		String stringaTelefonoTutorAziendale="Tel:_______\n";
		String stringaEmailTutorAziendale="Email:_______\n";
		//fine sezione tutor designato dal soggetto ospitante
		
		// inizio sezione n.totale di crediti
		int totCFU=pdfProgetto.getTotCFU();
		String stringaEsterno1;
		String stringaEsterno2;
		String stringaCurriculare;
		
		Paragraph paragrafoEsterno1 = null;
		Paragraph paragrafoEsterno2=null;
		Paragraph paragrafoCurriculare=null;
		String numeroTitleTotCFU="\nN.TOTALE DI CREDITI FORMATIVI PREVISTI PER L'ATTIVITA DI TIROCINIO "+totCFU+" di cui\n";
		if(corsoUtente.equalsIgnoreCase("triennale")) {
			switch(totCFU) {
				case 6:
					stringaEsterno1="6 CFU provienenti da tirocinio esterno (1) a scelta";
					paragrafoEsterno1=new Paragraph(stringaEsterno1,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
					break;
				case 11:
					stringaCurriculare="11 CFU per tirocinio curriculare";
					paragrafoCurriculare=new Paragraph(stringaCurriculare,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
					break;
				case 17:
					stringaCurriculare="11 CFU per tirocinio curriculare";
					stringaEsterno1="6 CFU provienenti da tirocinio esterno (1) a scelta";
					
					paragrafoEsterno1=new Paragraph(stringaEsterno1,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
					paragrafoCurriculare=new Paragraph(stringaCurriculare,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
					break;
				case 23:
					stringaCurriculare="11 CFU per tirocinio curriculare";
					stringaEsterno1="6 CFU provienenti da tirocinio esterno (1) a scelta";
					stringaEsterno2="6 CFU provienenti da tirocinio esterno (2) a scelta";
					
					paragrafoEsterno1=new Paragraph(stringaEsterno1,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
					paragrafoEsterno2=new Paragraph(stringaEsterno2,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
					paragrafoCurriculare=new Paragraph(stringaCurriculare,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
					
			}
		}
		else if(corsoUtente.equalsIgnoreCase("magistrale")) {
			switch(totCFU) {
				case 6:
					stringaEsterno1="6 CFU provienenti da tirocinio esterno (1) a scelta";
					paragrafoEsterno1=new Paragraph(stringaEsterno1,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
					break;
				case 12:
					stringaEsterno1="6 CFU provienenti da tirocinio esterno (1) a scelta";
					stringaEsterno2="6 CFU provienenti da tirocinio esterno (2) a scelta";
					paragrafoEsterno1=new Paragraph(stringaEsterno1,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
					paragrafoEsterno2=new Paragraph(stringaEsterno2,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
					break;
				
			}
		}
		// fine sezione n.totale di crediti
		
		// inizio sezione Proposta
		String stringaSedeTitle="SEDE DI SVOLGIMENTO DEL TIROCINIO: ";
		String stringaSede="_______";
		
		String stringaTitleObiettivo="\nINDICAZIONE DEGLI OBIETTIVI:\n";
		String stringaObiettivo=pdfProgetto.getObiettivi()+"\n";
		String stringaTitleCompetenze="INDICAZIONE LE COMPETENZE DA ACQUISIRE:\n";
		String stringaCompetenze=pdfProgetto.getCompetenze()+"\n";
		String stringaTitleAttivita="INDICAZIONE DELLE ATTIVITA' FORMATIVE PREVISTE:\n";
		String stringaAttivita=pdfProgetto.getAttivita()+"\n";
		String stringaTitleModalita="INDICAZIONE DELLE MODALITA DI SVOLGIMENTO DEL TIROCINIO:\n";
		String stringaModalita=pdfProgetto.getModalita()+"\n";
		// fine sezione Proposta
		
		// inizio durata del tirocinio
		String stringaTitleDurata="DURATA DEL TIROCINIO\n";
		String stringaDurata="n."+pdfProgetto.getTotCFU()+" ore";
		// fine durata del tirocinio
		

		// inizio sezione indicazione dell'orario
		String stringaTitleOrario="INDICAZIONE DELL'ORARIO DI SVOLGIMENTO DEL TIROCINIO";
		String stringaOrario="_________";
		// fine sezione indicazione dell'orario
		
		// inizio sezione polizze
		String stringaTitlePolizze="\nPOLIZZE ASSICURATIVE";
		String sezionePolizze="Ai sensi dell’art.5 della convenzione Rep.n.___________, a cui fa riferimento il presente progetto formativo, il Soggetto ospitante, in caso di infortunio del tirocinante durante lo svolgimento del tirocinio, si impegna a segnalare tempestivamente l’evento al Dipartimento di Informatica e al Responsabile dell’Ufficio Stato Giuridico e Formazione dell’Università, al fine di consentire a quest’ultimo di trasmettere la denuncia di infortunio all'INAIL in via telematica entro i tempi previsti dalla normativa vigente (48 ore).\n" + 
				"Il Responsabile pro tempore dell’Ufficio Stato Giuridico e Formazione dell’Ateneo è il dott. Pasquale Talarico, di cui si indicano di seguito il recapito telefonico e gli indirizzi e-mail a cui far pervenire la segnalazione dell’infortunio con copia della convenzione e del progetto formativo.\n" + 
				"Inoltre all’Ufficio Stato Giuridico e Formazione vanno trasmessi, a cura del tirocinante, una copia del certificato medico di infortunio lavorativo e una relazione scritta sulle modalità in cui è avvenuto l’infortunio (orario dell’infortunio, data e ora di abbandono del posto del di lavoro, attività svolta in occasione dell’infortunio e cause dello stesso). Tale documentazione deve essere trasmessa con la massima tempestività per le vie brevi oppure tramite e-mail.\n";
		String stringaUffcio="Ufficio Stato Giuridico e Formazione\nTel. 089 96 6204\ne-mail p.talarico@unisa.it\ne-mail ufgiufor@unisa.it ";
		// fine sezione polizze
		
		
		// inizio sessione obblighi tirocinante
		String stringaTitleObbligo="OBBLIGHI DEL TIROCINANTE";
		String sezioneObbligo=""
				+ "- Svolgere le attività previste dal presente progetto formativo e di orientamento, rispettando l’ambiente di lavoro;\n" + 
				"- seguire le indicazioni dei tutori e fare riferimento ad essi per qualsiasi esigenza di tipo organizzativo o altre evenienze;\n" + 
				"- rispettare gli orari e le regole di comportamento concordati nel presente progetto;\n" + 
				"- rispettare i regolamenti interni e le norme disciplinari in uso presso il soggetto ospitante;\n" + 
				"- rispettare le norme in materia di igiene, sicurezza e salute sui luoghi di lavoro;\n" + 
				"- rispettare gli obblighi di riservatezza, sia durante che dopo lo svolgimento del tirocinio per quanto attiene ai dati, alle informazioni o a tutto quanto acquisito in termini di conoscenze in merito a processi produttivi e prodotti/servizi dell’azienda ospitante.";
		// fine sessione obblighi tirocinante
		
		// inizio sessione autorizzazione al trattamento
		String stringaTitleAutorizzazione="AUTORIZZAZIONE AL TRATTAMENTO DEI DATI PERSONALI ED AZIENDALI ED ASSUNZIONE DI RESPONSABILITÀ:";
		String sezioneAutorizzazione=""
				+ "Con la sottoscrizione del presente progetto si autorizza il trattamento dei dati personali e del soggetto ospitante ai sensi e per gli effetti del Decreto Legislativo 30 giugno 2003, n. 196.\n" + 
				"Agli effetti delle vigenti leggi e nella consapevolezza delle conseguenze penali connesse a dichiarazioni mendaci, si dichiara che tutti i dati sopra riportati sono veri.";
		// fine sessione autorizzazione al trattamento
		
		
		Paragraph paragrafoTitleConvenzione=new Paragraph(stringaConvenzione,FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,BaseColor.BLACK));
		
		Paragraph paragrafoUniversita = new Paragraph(stringaUniversita,
				FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, new BaseColor(0, 0, 111)));
		
		Paragraph paragrafoProgetto = new Paragraph(stringaProgetto,
				FontFactory.getFont(FontFactory.TIMES_BOLD, 10, BaseColor.BLACK));
		Paragraph paragrafoPromotoreTitle = new Paragraph(stringaPromotoreTitle,
				FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.BLACK));
		Paragraph paragrafoPromotore = new Paragraph(stringaPromotore,
				FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.BLACK));
		
		
		// inizio sezione soggetto ospitante
		Paragraph paragrafoTitleSoggettoOspitante=new Paragraph(stringaTitleSoggettoOspitante,FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,BaseColor.BLACK));
		Paragraph paragrafoSezioneSoggettoOspitante=new Paragraph(sezioneSoggettoOspitante,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		// fine sezione soggetto ospitante
		
		// inizio sezione tirocinante
		Paragraph paragrafoTirocinanteTitle = new Paragraph(stringaTirocinanteTitle,
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, BaseColor.BLACK));
		Paragraph paragrafoTirocinante = new Paragraph(stringaTirocinante,
				FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.BLACK));
		// fine sezione tirocinante
		
		// inizio sezione tutor desingato dal dipartimento
		Paragraph paragrafoTitleDesignatoAccademico=new Paragraph(stringaTutorTitleDesignatoAccademico,FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,BaseColor.BLACK));
		Paragraph paragrafoDesignatoAccademico=new Paragraph(stringaTutorDesignatoAccademico,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		// fine sezione tutor desingato dal dipartimento
		
		
		// inizio ntotale CFU
		Paragraph paragrafoTitleNumeroCFU=new Paragraph(numeroTitleTotCFU,FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,BaseColor.BLACK));
		// fine ntotale CFU
		
		//Inizio Sezione Proposta
		Paragraph paragrafoTitleSede=new Paragraph(stringaSedeTitle,FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,BaseColor.BLACK));
		Paragraph paragrafoSede=new Paragraph(stringaSede,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		
		Paragraph paragrafoTitleObiettivo=new Paragraph(stringaTitleObiettivo,FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,BaseColor.BLACK));
		Paragraph paragrafoObiettivo=new Paragraph(stringaObiettivo,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		
		Paragraph paragrafoTitleCompetenze=new Paragraph(stringaTitleCompetenze,FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,BaseColor.BLACK));
		Paragraph paragrafoCompetenze=new Paragraph(stringaCompetenze,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		
		Paragraph paragrafoTitleAttivita=new Paragraph(stringaTitleAttivita,FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,BaseColor.BLACK));
		Paragraph paragrafoAttivita=new Paragraph(stringaAttivita,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		
		Paragraph paragrafoTitleModalita=new Paragraph(stringaTitleModalita,FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,BaseColor.BLACK));
		Paragraph paragrafoModalita=new Paragraph(stringaModalita,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		// Fine sezione Proposta
		
		// Inizio sezione durata
		Paragraph paragrafoTitleDurata=new Paragraph(stringaTitleDurata,FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,BaseColor.BLACK));
		Paragraph paragrafoDurata=new Paragraph(stringaDurata,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		// Fine sezione durata
		
		// inizio sezione indicazione dell'orario
		Paragraph paragrafoTitleOrario=new Paragraph(stringaTitleOrario,FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,BaseColor.BLACK));
		Paragraph paragrafoOrario=new Paragraph(stringaOrario,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		// fine sezione indicazione dell'orario
		
		// inizio sezione polizze
		Paragraph paragrafoTitlePolizze=new Paragraph(stringaTitlePolizze,FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,BaseColor.BLACK));
		Paragraph paragrafoPolizze=new Paragraph(sezionePolizze,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		Paragraph paragrafoUfficio=new Paragraph(stringaUffcio,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		// fine sezione polizze
		
		// inizio sezione obblighi
		Paragraph paragrafoTitleObbligo=new Paragraph(stringaTitleObbligo,FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,BaseColor.BLACK));
		Paragraph paragrafoObbligo=new Paragraph(sezioneObbligo,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		// fine sezione obblighi
		
		// inizio sezione autorizzazione
		Paragraph paragrafoTitleAutorizzazione=new Paragraph(stringaTitleAutorizzazione,FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,BaseColor.BLACK));
		Paragraph paragrafoAutorizzazione=new Paragraph(sezioneAutorizzazione,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		// fine sezione autorizzazione
		

		
		
		
		listeParagrafi.add(paragrafoUniversita);
		listeParagrafi.add(paragrafoProgetto);
		listeParagrafi.add(paragrafoTitleConvenzione);
		listeParagrafi.add(paragrafoPromotoreTitle);
		listeParagrafi.add(paragrafoPromotore);
		
		// soggetto ospitante
		listeParagrafi.add(paragrafoTitleSoggettoOspitante);
		listeParagrafi.add(paragrafoSezioneSoggettoOspitante);
		//tirocinante
		listeParagrafi.add(paragrafoTirocinanteTitle);
		listeParagrafi.add(paragrafoTirocinante);
		
		//tutor designato accademico
		listeParagrafi.add(paragrafoTitleDesignatoAccademico);
		listeParagrafi.add(paragrafoDesignatoAccademico);
		
		//inizio ntotali CFU
		listeParagrafi.add(paragrafoTitleNumeroCFU);
		
		if(corsoUtente.equalsIgnoreCase("triennale")) {
			switch(totCFU) {
				case 6:
					listeParagrafi.add(paragrafoEsterno1);
					break;
				case 11:
					listeParagrafi.add(paragrafoCurriculare);
					break;
				case 17:
					listeParagrafi.add(paragrafoEsterno1);
					listeParagrafi.add(paragrafoCurriculare);
					break;
				case 23:
					listeParagrafi.add(paragrafoEsterno1);
					listeParagrafi.add(paragrafoEsterno2);
					listeParagrafi.add(paragrafoCurriculare);
					break;
			}
		}
		else if(corsoUtente.equalsIgnoreCase("magistrale")) {
			switch(totCFU) {
				case 6:
					listeParagrafi.add(paragrafoEsterno1);
					break;
				case 12:
					listeParagrafi.add(paragrafoEsterno1);
					listeParagrafi.add(paragrafoEsterno2);
					break;
			}
		}
		//fine ntotali CFU
		
		//inserimento dei paragrafi della sezione proposta nella lista di paragrafi
		listeParagrafi.add(paragrafoTitleSede);
		listeParagrafi.add(paragrafoSede);
		listeParagrafi.add(paragrafoTitleObiettivo);
		listeParagrafi.add(paragrafoObiettivo);
		listeParagrafi.add(paragrafoTitleCompetenze);
		listeParagrafi.add(paragrafoCompetenze);
		listeParagrafi.add(paragrafoTitleAttivita);
		listeParagrafi.add(paragrafoAttivita);
		listeParagrafi.add(paragrafoTitleModalita);
		listeParagrafi.add(paragrafoModalita);
		// fine inserimento dei paragrafi della sezione proposta
		
		
		// inizio sezione durata
		listeParagrafi.add(paragrafoTitleDurata);
		listeParagrafi.add(paragrafoDurata);
		// fine sezione durata
		
		// inizio sezione orario
		listeParagrafi.add(paragrafoTitleOrario);
		listeParagrafi.add(paragrafoOrario);
		// fine sezione orario
		
		// inizio sezione polizze
		listeParagrafi.add(paragrafoTitlePolizze);
		listeParagrafi.add(paragrafoPolizze);
		listeParagrafi.add(paragrafoUfficio);
		// fine sezione polizze
		
		// inizio sezione obbligo
		listeParagrafi.add(paragrafoTitleObbligo);
		listeParagrafi.add(paragrafoObbligo);
		// fine sezione obbligo
		
		// inizio sezione autorizzazione
		listeParagrafi.add(paragrafoTitleAutorizzazione);
		listeParagrafi.add(paragrafoAutorizzazione);
		// fine sezione autorizzazione
		
		paragrafoTitleConvenzione.setAlignment(Element.ALIGN_CENTER);
		paragrafoUfficio.setAlignment(Paragraph.ALIGN_CENTER);
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
			PDFProgettoFormativo pdfProgetto,String corsoUtente) throws DocumentException, IOException {
		Document document = new Document();

		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		ArrayList<Paragraph> listeParagrafi = new ArrayList<Paragraph>();
		
		
		
		String stringaUniversita = "UNIVERSITÀ DEGLI STUDI DI SALERNO 	\n DIPARTIMENTO DI INFORMATICA";
		String stringaProgetto = "PROGETTO FORMATIVO E DI ORIENTAMENTO LAUREA TRIENNALE / LAUREA MAGISTRALE\n";
		String stringaPromotoreTitle = "\nSOGGETTO PROMOTORE";
		String stringaPromotore = "Dipartimento di Informatica dell’Università degli Studi di Salerno;\nSede in Via Giovanni Paolo II, 132, 84084 Fisciano (Salerno)\nIndirizzo PEC ammicent@pec.unisa.it\n"
				+ "Codice Fiscale 80018670655\n"
				+ "Rappresentante legale: prof. Alfredo De Santis, in qualità di Direttore pro tempore, nato a nato a Nocera Inferiore (SA) il 07/12/1960.\n";
		
		String stringaConvenzione="\nRelativo alla Convenzione per tirocinio di formazione ed orientamento (curriculare) stipulata con ____________________________ in data _________________, Repertorio N. _______";
		
		// inizio sezione soggetto ospitante
		String stringaTitleSoggettoOspitante="SOGGETTO OSPITANTE\n";
		String sezioneSoggettoOspitante="Denominazione ___________ (specificare la natura giuridica)\nSede legane in ___________"+
		"\nIndirizzo PEC ___________\nCodice Fiscale e Partita IVA ___________\nRappresentante legale: ___________, in qualita di ___________, nato a ___________, il ___________\n"+
				"Attivita economica esercitata ___________\nNumero Dipedenti a tempo indeterminato___________";
		// fine sezione soggetto ospitante
		
		
		String stringaTirocinanteTitle = "\nTirocinante";
		String stringaTirocinante = "Cognome: " + pdfProgetto.getCognomeStudente()+ " Nome: "+pdfProgetto.getNomeStudente()+"\n" + "Data e luogo di nascita:______ "
				+ "\nResidenza:______\n"+ "Codice Fiscale:______\n"+"Telefono:______\n"+"Email: "+pdfProgetto.getEmailStudente()+"\nIscritto al Corso di Laurea Triennale in Informatica\n";
		
		//inizio sezione tutor designato dal dipartimento
		String stringaTutorTitleDesignatoAccademico="\nTUTOR DESIGNATO DAL DIPARTIMENTO: ";
		String stringaTutorDesignatoAccademico=pdfProgetto.getNomeTutorAccademico()+ " " + pdfProgetto.getCognomeTutorAccademico()+"\n";
		//fine sezione tutor designato dal soggetto ospitante
		
		//inizio sezione tutor desginato dal soggetto ospitante
		String stringaTutorTitleDesignatoAziendale="\nTUTOR DESIGNATO DAL SOGGETTO OSPITANTE: ";
		String stringaTutorDesignatoAziendale=pdfProgetto.getNomeTutorAziendale()+" "+ pdfProgetto.getCognomeTutorAziendale();
		String stringaTelefonoTutorAziendale="Tel:_______\n";
		String stringaEmailTutorAziendale="Email:_______\n";
		//fine sezione tutor designato dal soggetto ospitante
		
		// inizio sezione n.totale di crediti
		int totCFU=pdfProgetto.getTotCFU();
		String stringaEsterno1;
		String stringaEsterno2;
		String stringaCurriculare;
		
		Paragraph paragrafoEsterno1 = null;
		Paragraph paragrafoEsterno2=null;
		Paragraph paragrafoCurriculare=null;
		String numeroTitleTotCFU="\nN.TOTALE DI CREDITI FORMATIVI PREVISTI PER L'ATTIVITA DI TIROCINIO "+totCFU+" di cui\n";
		if(corsoUtente.equalsIgnoreCase("triennale")) {
			switch(totCFU) {
				case 6:
					stringaEsterno1="6 CFU provienenti da tirocinio esterno (1) a scelta";
					paragrafoEsterno1=new Paragraph(stringaEsterno1,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
					break;
				case 11:
					stringaCurriculare="11 CFU per tirocinio curriculare";
					paragrafoCurriculare=new Paragraph(stringaCurriculare,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
					break;
				case 17:
					stringaCurriculare="11 CFU per tirocinio curriculare";
					stringaEsterno1="6 CFU provienenti da tirocinio esterno (1) a scelta";
					
					paragrafoEsterno1=new Paragraph(stringaEsterno1,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
					paragrafoCurriculare=new Paragraph(stringaCurriculare,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
					break;
				case 23:
					stringaCurriculare="11 CFU per tirocinio curriculare";
					stringaEsterno1="6 CFU provienenti da tirocinio esterno (1) a scelta";
					stringaEsterno2="6 CFU provienenti da tirocinio esterno (2) a scelta";
					
					paragrafoEsterno1=new Paragraph(stringaEsterno1,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
					paragrafoEsterno2=new Paragraph(stringaEsterno2,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
					paragrafoCurriculare=new Paragraph(stringaCurriculare,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
					
			}
		}
		else if(corsoUtente.equalsIgnoreCase("magistrale")) {
			switch(totCFU) {
				case 6:
					stringaEsterno1="6 CFU provienenti da tirocinio esterno (1) a scelta";
					paragrafoEsterno1=new Paragraph(stringaEsterno1,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
					break;
				case 12:
					stringaEsterno1="6 CFU provienenti da tirocinio esterno (1) a scelta";
					stringaEsterno2="6 CFU provienenti da tirocinio esterno (2) a scelta";
					paragrafoEsterno1=new Paragraph(stringaEsterno1,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
					paragrafoEsterno2=new Paragraph(stringaEsterno2,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
					break;
				
			}
		}
		// fine sezione n.totale di crediti
		
		// inizio sezione Proposta
		String stringaSedeTitle="SEDE DI SVOLGIMENTO DEL TIROCINIO: ";
		String stringaSede="_______";
		
		String stringaTitleObiettivo="\nINDICAZIONE DEGLI OBIETTIVI:\n";
		String stringaObiettivo=pdfProgetto.getObiettivi()+"\n";
		String stringaTitleCompetenze="INDICAZIONE LE COMPETENZE DA ACQUISIRE:\n";
		String stringaCompetenze=pdfProgetto.getCompetenze()+"\n";
		String stringaTitleAttivita="INDICAZIONE DELLE ATTIVITA' FORMATIVE PREVISTE:\n";
		String stringaAttivita=pdfProgetto.getAttivita()+"\n";
		String stringaTitleModalita="INDICAZIONE DELLE MODALITA DI SVOLGIMENTO DEL TIROCINIO:\n";
		String stringaModalita=pdfProgetto.getModalita()+"\n";
		// fine sezione Proposta
		
		// inizio durata del tirocinio
		String stringaTitleDurata="DURATA DEL TIROCINIO\n";
		String stringaDurata="n."+pdfProgetto.getTotCFU()+" ore";
		// fine durata del tirocinio
		

		// inizio sezione indicazione dell'orario
		String stringaTitleOrario="INDICAZIONE DELL'ORARIO DI SVOLGIMENTO DEL TIROCINIO";
		String stringaOrario="_________";
		// fine sezione indicazione dell'orario
		
		// inizio sezione polizze
		String stringaTitlePolizze="\nPOLIZZE ASSICURATIVE";
		String sezionePolizze="Ai sensi dell’art.5 della convenzione Rep.n.___________, a cui fa riferimento il presente progetto formativo, il Soggetto ospitante, in caso di infortunio del tirocinante durante lo svolgimento del tirocinio, si impegna a segnalare tempestivamente l’evento al Dipartimento di Informatica e al Responsabile dell’Ufficio Stato Giuridico e Formazione dell’Università, al fine di consentire a quest’ultimo di trasmettere la denuncia di infortunio all'INAIL in via telematica entro i tempi previsti dalla normativa vigente (48 ore).\n" + 
				"Il Responsabile pro tempore dell’Ufficio Stato Giuridico e Formazione dell’Ateneo è il dott. Pasquale Talarico, di cui si indicano di seguito il recapito telefonico e gli indirizzi e-mail a cui far pervenire la segnalazione dell’infortunio con copia della convenzione e del progetto formativo.\n" + 
				"Inoltre all’Ufficio Stato Giuridico e Formazione vanno trasmessi, a cura del tirocinante, una copia del certificato medico di infortunio lavorativo e una relazione scritta sulle modalità in cui è avvenuto l’infortunio (orario dell’infortunio, data e ora di abbandono del posto del di lavoro, attività svolta in occasione dell’infortunio e cause dello stesso). Tale documentazione deve essere trasmessa con la massima tempestività per le vie brevi oppure tramite e-mail.\n";
		String stringaUffcio="Ufficio Stato Giuridico e Formazione\nTel. 089 96 6204\ne-mail p.talarico@unisa.it\ne-mail ufgiufor@unisa.it ";
		// fine sezione polizze
		
		
		// inizio sessione obblighi tirocinante
		String stringaTitleObbligo="OBBLIGHI DEL TIROCINANTE";
		String sezioneObbligo=""
				+ "- Svolgere le attività previste dal presente progetto formativo e di orientamento, rispettando l’ambiente di lavoro;\n" + 
				"- seguire le indicazioni dei tutori e fare riferimento ad essi per qualsiasi esigenza di tipo organizzativo o altre evenienze;\n" + 
				"- rispettare gli orari e le regole di comportamento concordati nel presente progetto;\n" + 
				"- rispettare i regolamenti interni e le norme disciplinari in uso presso il soggetto ospitante;\n" + 
				"- rispettare le norme in materia di igiene, sicurezza e salute sui luoghi di lavoro;\n" + 
				"- rispettare gli obblighi di riservatezza, sia durante che dopo lo svolgimento del tirocinio per quanto attiene ai dati, alle informazioni o a tutto quanto acquisito in termini di conoscenze in merito a processi produttivi e prodotti/servizi dell’azienda ospitante.";
		// fine sessione obblighi tirocinante
		
		// inizio sessione autorizzazione al trattamento
		String stringaTitleAutorizzazione="AUTORIZZAZIONE AL TRATTAMENTO DEI DATI PERSONALI ED AZIENDALI ED ASSUNZIONE DI RESPONSABILITÀ:";
		String sezioneAutorizzazione=""
				+ "Con la sottoscrizione del presente progetto si autorizza il trattamento dei dati personali e del soggetto ospitante ai sensi e per gli effetti del Decreto Legislativo 30 giugno 2003, n. 196.\n" + 
				"Agli effetti delle vigenti leggi e nella consapevolezza delle conseguenze penali connesse a dichiarazioni mendaci, si dichiara che tutti i dati sopra riportati sono veri.";
		// fine sessione autorizzazione al trattamento
		
		
		Paragraph paragrafoTitleConvenzione=new Paragraph(stringaConvenzione,FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,BaseColor.BLACK));
		
		Paragraph paragrafoUniversita = new Paragraph(stringaUniversita,
				FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, new BaseColor(0, 0, 111)));
		
		Paragraph paragrafoProgetto = new Paragraph(stringaProgetto,
				FontFactory.getFont(FontFactory.TIMES_BOLD, 10, BaseColor.BLACK));
		Paragraph paragrafoPromotoreTitle = new Paragraph(stringaPromotoreTitle,
				FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.BLACK));
		Paragraph paragrafoPromotore = new Paragraph(stringaPromotore,
				FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.BLACK));
		
		
		// inizio sezione soggetto ospitante
		Paragraph paragrafoTitleSoggettoOspitante=new Paragraph(stringaTitleSoggettoOspitante,FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,BaseColor.BLACK));
		Paragraph paragrafoSezioneSoggettoOspitante=new Paragraph(sezioneSoggettoOspitante,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		// fine sezione soggetto ospitante
		
		// inizio sezione tirocinante
		Paragraph paragrafoTirocinanteTitle = new Paragraph(stringaTirocinanteTitle,
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, BaseColor.BLACK));
		Paragraph paragrafoTirocinante = new Paragraph(stringaTirocinante,
				FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.BLACK));
		// fine sezione tirocinante
		
		// inizio sezione tutor desingato dal dipartimento
		Paragraph paragrafoTitleDesignatoAccademico=new Paragraph(stringaTutorTitleDesignatoAccademico,FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,BaseColor.BLACK));
		Paragraph paragrafoDesignatoAccademico=new Paragraph(stringaTutorDesignatoAccademico,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		// fine sezione tutor desingato dal dipartimento
		
		
		// inizio sezione tutor desginato dal soggetto ospitante
		Paragraph paragrafoTitleDesignatoAziendale=new Paragraph(stringaTutorTitleDesignatoAziendale,FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,BaseColor.BLACK));
		Paragraph paragrafoDesignatoAziendale=new Paragraph(stringaTutorDesignatoAziendale,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		Paragraph paragrafoDesignatoTelefonoAziendale=new Paragraph(stringaTelefonoTutorAziendale,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		Paragraph paragrafoDesignatoEmailAziendale=new Paragraph(stringaEmailTutorAziendale,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		//fine sezione tutor designato dal soggetto ospitante
		
		// inizio ntotale CFU
		Paragraph paragrafoTitleNumeroCFU=new Paragraph(numeroTitleTotCFU,FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,BaseColor.BLACK));
		// fine ntotale CFU
		
		//Inizio Sezione Proposta
		Paragraph paragrafoTitleSede=new Paragraph(stringaSedeTitle,FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,BaseColor.BLACK));
		Paragraph paragrafoSede=new Paragraph(stringaSede,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		
		Paragraph paragrafoTitleObiettivo=new Paragraph(stringaTitleObiettivo,FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,BaseColor.BLACK));
		Paragraph paragrafoObiettivo=new Paragraph(stringaObiettivo,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		
		Paragraph paragrafoTitleCompetenze=new Paragraph(stringaTitleCompetenze,FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,BaseColor.BLACK));
		Paragraph paragrafoCompetenze=new Paragraph(stringaCompetenze,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		
		Paragraph paragrafoTitleAttivita=new Paragraph(stringaTitleAttivita,FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,BaseColor.BLACK));
		Paragraph paragrafoAttivita=new Paragraph(stringaAttivita,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		
		Paragraph paragrafoTitleModalita=new Paragraph(stringaTitleModalita,FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,BaseColor.BLACK));
		Paragraph paragrafoModalita=new Paragraph(stringaModalita,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		// Fine sezione Proposta
		
		// Inizio sezione durata
		Paragraph paragrafoTitleDurata=new Paragraph(stringaTitleDurata,FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,BaseColor.BLACK));
		Paragraph paragrafoDurata=new Paragraph(stringaDurata,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		// Fine sezione durata
		
		// inizio sezione indicazione dell'orario
		Paragraph paragrafoTitleOrario=new Paragraph(stringaTitleOrario,FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,BaseColor.BLACK));
		Paragraph paragrafoOrario=new Paragraph(stringaOrario,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		// fine sezione indicazione dell'orario
		
		// inizio sezione polizze
		Paragraph paragrafoTitlePolizze=new Paragraph(stringaTitlePolizze,FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,BaseColor.BLACK));
		Paragraph paragrafoPolizze=new Paragraph(sezionePolizze,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		Paragraph paragrafoUfficio=new Paragraph(stringaUffcio,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		// fine sezione polizze
		
		// inizio sezione obblighi
		Paragraph paragrafoTitleObbligo=new Paragraph(stringaTitleObbligo,FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,BaseColor.BLACK));
		Paragraph paragrafoObbligo=new Paragraph(sezioneObbligo,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		// fine sezione obblighi
		
		// inizio sezione autorizzazione
		Paragraph paragrafoTitleAutorizzazione=new Paragraph(stringaTitleAutorizzazione,FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,BaseColor.BLACK));
		Paragraph paragrafoAutorizzazione=new Paragraph(sezioneAutorizzazione,FontFactory.getFont(FontFactory.HELVETICA,8,BaseColor.BLACK));
		// fine sezione autorizzazione
		

		
		
		
		listeParagrafi.add(paragrafoUniversita);
		listeParagrafi.add(paragrafoProgetto);
		listeParagrafi.add(paragrafoTitleConvenzione);
		listeParagrafi.add(paragrafoPromotoreTitle);
		listeParagrafi.add(paragrafoPromotore);
		
		// soggetto ospitante
		listeParagrafi.add(paragrafoTitleSoggettoOspitante);
		listeParagrafi.add(paragrafoSezioneSoggettoOspitante);
		//tirocinante
		listeParagrafi.add(paragrafoTirocinanteTitle);
		listeParagrafi.add(paragrafoTirocinante);
		
		//tutor designato accademico
		listeParagrafi.add(paragrafoTitleDesignatoAccademico);
		listeParagrafi.add(paragrafoDesignatoAccademico);
		
		//tutor designato aziendale
		listeParagrafi.add(paragrafoTitleDesignatoAziendale);
		listeParagrafi.add(paragrafoDesignatoAziendale);
		listeParagrafi.add(paragrafoDesignatoTelefonoAziendale);
		listeParagrafi.add(paragrafoDesignatoEmailAziendale);
		
		//inizio ntotali CFU
		listeParagrafi.add(paragrafoTitleNumeroCFU);
		
		if(corsoUtente.equalsIgnoreCase("triennale")) {
			switch(totCFU) {
				case 6:
					listeParagrafi.add(paragrafoEsterno1);
					break;
				case 11:
					listeParagrafi.add(paragrafoCurriculare);
					break;
				case 17:
					listeParagrafi.add(paragrafoEsterno1);
					listeParagrafi.add(paragrafoCurriculare);
					break;
				case 23:
					listeParagrafi.add(paragrafoEsterno1);
					listeParagrafi.add(paragrafoEsterno2);
					listeParagrafi.add(paragrafoCurriculare);
					break;
			}
		}
		else if(corsoUtente.equalsIgnoreCase("magistrale")) {
			switch(totCFU) {
				case 6:
					listeParagrafi.add(paragrafoEsterno1);
					break;
				case 12:
					listeParagrafi.add(paragrafoEsterno1);
					listeParagrafi.add(paragrafoEsterno2);
					break;
			}
		}
		//fine ntotali CFU
		
		//inserimento dei paragrafi della sezione proposta nella lista di paragrafi
		listeParagrafi.add(paragrafoTitleSede);
		listeParagrafi.add(paragrafoSede);
		listeParagrafi.add(paragrafoTitleObiettivo);
		listeParagrafi.add(paragrafoObiettivo);
		listeParagrafi.add(paragrafoTitleCompetenze);
		listeParagrafi.add(paragrafoCompetenze);
		listeParagrafi.add(paragrafoTitleAttivita);
		listeParagrafi.add(paragrafoAttivita);
		listeParagrafi.add(paragrafoTitleModalita);
		listeParagrafi.add(paragrafoModalita);
		// fine inserimento dei paragrafi della sezione proposta
		
		
		// inizio sezione durata
		listeParagrafi.add(paragrafoTitleDurata);
		listeParagrafi.add(paragrafoDurata);
		// fine sezione durata
		
		// inizio sezione orario
		listeParagrafi.add(paragrafoTitleOrario);
		listeParagrafi.add(paragrafoOrario);
		// fine sezione orario
		
		// inizio sezione polizze
		listeParagrafi.add(paragrafoTitlePolizze);
		listeParagrafi.add(paragrafoPolizze);
		listeParagrafi.add(paragrafoUfficio);
		// fine sezione polizze
		
		// inizio sezione obbligo
		listeParagrafi.add(paragrafoTitleObbligo);
		listeParagrafi.add(paragrafoObbligo);
		// fine sezione obbligo
		
		// inizio sezione autorizzazione
		listeParagrafi.add(paragrafoTitleAutorizzazione);
		listeParagrafi.add(paragrafoAutorizzazione);
		// fine sezione autorizzazione
		
		paragrafoTitleConvenzione.setAlignment(Element.ALIGN_CENTER);
		paragrafoUfficio.setAlignment(Paragraph.ALIGN_CENTER);
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
