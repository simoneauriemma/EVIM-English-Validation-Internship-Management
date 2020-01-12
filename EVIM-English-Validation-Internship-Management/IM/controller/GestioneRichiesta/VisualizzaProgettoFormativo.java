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

// import riguardanti alla creazione del PDF, grazie ad itext5
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;

import model.PDFProgettoFormativo;

import model.TirocinioEsternoDAO;
import model.TirocinioInternoDAO;

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
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession sessione = request.getSession();
		if (sessione.getAttribute("utenteLoggato") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/login.jsp");
			dispatcher.forward(request, response);
			}
		
		else {
			int id= Integer.parseInt((String) request.getAttribute("id"));
			
			String tirocinio=(String) request.getAttribute("tirocinio");
			
			response.setContentType("application/pdf");
			try {
				if(tirocinio.equalsIgnoreCase("interno"))
				{
					PDFProgettoFormativo pdfProgetto=TirocinioInternoDAO.getProgettoFormativoInterno(id);
					createProgettoFormativoInterno(request, response, pdfProgetto);
				}
				if(tirocinio.equalsIgnoreCase("esterno")) {
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
}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
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
		
		
		Font campiTitoli=new Font(Font.FontFamily.HELVETICA,10,Font.BOLD,BaseColor.BLACK);
		Font campiStatiche= new Font(Font.FontFamily.HELVETICA,8,Font.NORMAL,BaseColor.BLACK);
		Font campiCompilati=new Font(Font.FontFamily.HELVETICA,8,Font.BOLD,BaseColor.BLACK);
		
		
		Phrase stringaUniversita= new Phrase();
		stringaUniversita.add(new Chunk("UNIVERSITÀ DEGLI STUDI DI SALERNO 	\n DIPARTIMENTO DI INFORMATICA",campiTitoli));
		
		Phrase stringaProgetto=new Phrase();
		stringaProgetto.add(new Chunk("PROGETTO FORMATIVO E DI ORIENTAMENTO LAUREA TRIENNALE / LAUREA MAGISTRALE\n",campiTitoli));
		
		Phrase stringaConvenzione=new Phrase();
		stringaConvenzione.add(new Chunk("Relativo alla Convenzione per tirocinio di formazione ed orientamento (curriculare) stipulata in data ",campiStatiche));
		stringaConvenzione.add(new Chunk(pdfProgetto.getDataConvenzione(),campiCompilati));
		stringaConvenzione.add(new Chunk(", Repertorio N. ",campiStatiche));
		stringaConvenzione.add(new Chunk(pdfProgetto.getReportorioConvenzione(),campiCompilati));
		
		Phrase stringaPromotore=new Phrase();
		stringaPromotore.add(new Chunk("\nSOGGETTO PROMOTORE",campiTitoli));
		stringaPromotore.add(new Chunk("\nDipartimento di Informatica dell’Università degli Studi di Salerno;\nSede in Via Giovanni Paolo II, 132, 84084 Fisciano (Salerno)\nIndirizzo PEC ammicent@pec.unisa.it\n" + 
				"Codice Fiscale 80018670655\n" + 
				"Rappresentante legale: prof. Alfredo De Santis, in qualità di Direttore pro tempore, nato a nato a Nocera Inferiore (SA) il 07/12/1960.\n",campiStatiche));
		
		
		//inizio dati tirocinante
		String stringaTirocinanteTitle = "\nTirocinante";
		Phrase stringaTirocinante=new Phrase();
		stringaTirocinante.add(new Chunk("Cognome ",campiStatiche));
		stringaTirocinante.add(new Chunk(pdfProgetto.getCognomeStudente(),campiCompilati));
		stringaTirocinante.add(new Chunk(" Nome ",campiStatiche));
		stringaTirocinante.add(new Chunk(pdfProgetto.getNomeStudente(),campiCompilati));
		stringaTirocinante.add(new Chunk("\nData ",campiStatiche));
		stringaTirocinante.add(new Chunk(pdfProgetto.getDataNascitaStudente(),campiCompilati));
		stringaTirocinante.add(new Chunk(" e luogo di nascita ",campiStatiche));
		stringaTirocinante.add(new Chunk(pdfProgetto.getLuogoNascitaStudente(),campiCompilati));
		stringaTirocinante.add(new Chunk("\nResidenza ",campiStatiche));
		stringaTirocinante.add(new Chunk(pdfProgetto.getResidenteStudente(),campiCompilati));
		stringaTirocinante.add(new Chunk("\nTelefono n° ",campiStatiche));
		stringaTirocinante.add(new Chunk(pdfProgetto.getTelefonoStudente(),campiCompilati));
		stringaTirocinante.add(new Chunk("\nIndirizzo e-mail ",campiStatiche));
		stringaTirocinante.add(new Chunk(pdfProgetto.getEmailStudente(),campiCompilati));
		stringaTirocinante.add(new Chunk("\nIscritto al corso di Laurea ",campiStatiche));
		stringaTirocinante.add(new Chunk(pdfProgetto.getCorsoLaurea(),campiCompilati));
		stringaTirocinante.add(new Chunk(" in Informatica",campiStatiche));
		// fine dati tirocinante
		
		
	
		
		//inizio sezione tutor designato dal dipartimento
		Phrase stringaTutorDesignatoAccademico=new Phrase();
		stringaTutorDesignatoAccademico.add(new Chunk("TUTOR DESIGNATO DAL DIPARTIMENTO: ",campiTitoli));
		stringaTutorDesignatoAccademico.add(new Chunk(pdfProgetto.getNomeTutorAccademico()+" "+pdfProgetto.getCognomeTutorAccademico(),campiCompilati));
		//fine sezione tutor designato dal soggetto ospitante

		
		// inizio sezione n.totale di crediti
		int totCFU=pdfProgetto.getTotCFU();
		Phrase numeroTitleTotCFU=new Phrase();
		numeroTitleTotCFU.add(new Chunk("\nN.TOTALE DI CREDITI FORMATIVI PREVISTI PER L'ATTIVITA DI TIROCINIO ",campiTitoli));
		numeroTitleTotCFU.add(new Chunk(""+totCFU,campiCompilati));
		numeroTitleTotCFU.add(new Chunk(" di cui:",campiTitoli));
		if(pdfProgetto.getCorsoLaurea().equalsIgnoreCase("triennale")) {
			switch(totCFU) {
				case 6:
					numeroTitleTotCFU.add(new Chunk("\n6 CFU provienenti da tirocinio esterno (1) a scelta",campiCompilati));
					break;
				case 11:
					numeroTitleTotCFU.add(new Chunk("\n11 CFU per tirocinio curriculare",campiCompilati));
					break;
				case 17:
					numeroTitleTotCFU.add(new Chunk("\n11 CFU per tirocinio curriculare",campiCompilati));
					numeroTitleTotCFU.add(new Chunk("\n6 CFU provienenti da tirocinio esterno (1) a scelta",campiCompilati));
					break;
				case 23:
					numeroTitleTotCFU.add(new Chunk("\n11 CFU per tirocinio curriculare",campiCompilati));
					numeroTitleTotCFU.add(new Chunk("\n6 CFU provienenti da tirocinio esterno (1) a scelta",campiCompilati));
					numeroTitleTotCFU.add(new Chunk("\n6 CFU provienenti da tirocinio esterno (2) a scelta",campiCompilati));
			}
		}
		
		// fine sezione n.totale di crediti
		
		// inizio sezione Proposta
		Phrase stringaSede=new Phrase();
		stringaSede.add(new Chunk("\nSEDE DI SVOLGIMENTO DEL TIROCINIO: ",campiTitoli));
		stringaSede.add(new Chunk("Laboratorio dipartimento di Informatica",campiCompilati));
		
		Phrase sezioneObbComAttMod= new Phrase();
		sezioneObbComAttMod.add(new Chunk("\nINDICAZIONE DEGLI OBIETTIVI:\n",campiTitoli));
		sezioneObbComAttMod.add(new Chunk(pdfProgetto.getObiettivi(),campiCompilati));
		sezioneObbComAttMod.add(new Chunk("\nINDICAZIONE LE COMPETENZE DA ACQUISIRE:\n",campiTitoli));
		sezioneObbComAttMod.add(new Chunk(pdfProgetto.getCompetenze(),campiCompilati));
		sezioneObbComAttMod.add(new Chunk("\nINDICAZIONE DELLE ATTIVITA' FORMATIVE PREVISTE:\n",campiTitoli));
		sezioneObbComAttMod.add(new Chunk(pdfProgetto.getAttivita(),campiCompilati));
		sezioneObbComAttMod.add(new Chunk("\nINDICAZIONE DELLE MODALITA DI SVOLGIMENTO DEL TIROCINIO:\n",campiTitoli));
		sezioneObbComAttMod.add(new Chunk(pdfProgetto.getModalita(),campiCompilati));
		// fine sezione Proposta
		
		// inizio durata del tirocinio
		
		Phrase stringaDurata=new Phrase();
		stringaDurata.add(new Chunk("\nDURATA DEL TIROCINIO: ",campiTitoli));
		stringaDurata.add(new Chunk("n°",campiStatiche));
		stringaDurata.add(new Chunk(""+pdfProgetto.getTotOre(),campiCompilati));
		stringaDurata.add(new Chunk(" ore",campiStatiche));
		// fine durata del tirocinio
		

		// inizio sezione indicazione dell'orario
		Phrase stringaOrario= new Phrase();
		stringaOrario.add(new Chunk("\nINDICAZIONE DELL'ORARIO DI SVOLGIMENTO DEL TIROCINIO",campiTitoli));
		stringaOrario.add(new Chunk(" Da lunedi a venerdi dalle 9:00/13:00 e 14:00/18:00",campiStatiche));
		// fine sezione indicazione dell'orario
		
		// inizio sezione polizze
		Phrase stringaPolizze= new Phrase();
		stringaPolizze.add(new Chunk("\nPOLIZZE ASSICURATIVE",campiTitoli));
		stringaPolizze.add(new Chunk("\nAi sensi dell’art.5 della convenzione",campiStatiche));
		stringaPolizze.add(new Chunk(pdfProgetto.getReportorioConvenzione(),campiCompilati));
		stringaPolizze.add(new Chunk(", a cui fa riferimento il presente progetto formativo, il Soggetto ospitante, in caso di infortunio del tirocinante durante lo svolgimento del tirocinio, si impegna a segnalare tempestivamente l’evento al Dipartimento di Informatica e al Responsabile dell’Ufficio Stato Giuridico e Formazione dell’Università, al fine di consentire a quest’ultimo di trasmettere la denuncia di infortunio all'INAIL in via telematica entro i tempi previsti dalla normativa vigente (48 ore).\n"+ 
				"Il Responsabile pro tempore dell’Ufficio Stato Giuridico e Formazione dell’Ateneo è il dott. Pasquale Talarico, di cui si indicano di seguito il recapito telefonico e gli indirizzi e-mail a cui far pervenire la segnalazione dell’infortunio con copia della convenzione e del progetto formativo.\n" + 
				"Inoltre all’Ufficio Stato Giuridico e Formazione vanno trasmessi, a cura del tirocinante, una copia del certificato medico di infortunio lavorativo e una relazione scritta sulle modalità in cui è avvenuto l’infortunio (orario dell’infortunio, data e ora di abbandono del posto del di lavoro, attività svolta in occasione dell’infortunio e cause dello stesso). Tale documentazione deve essere trasmessa con la massima tempestività per le vie brevi oppure tramite e-mail.",campiStatiche));
		
		Phrase stringaUfficio=new Phrase();
		stringaUfficio.add(new Chunk("Ufficio Stato Giuridico e Formazione\nTel. 089 96 6204\ne-mail p.talarico@unisa.it\ne-mail ufgiufor@unisa.it",campiStatiche));
		// fine sezione polizze
		
		
		// inizio sessione obblighi tirocinante
		Phrase stringaObbligo=new Phrase();
		stringaObbligo.add(new Chunk("OBBLIGHI DEL TIROCINANTE",campiTitoli));
		stringaObbligo.add(new Chunk("\n"
				+ "- Svolgere le attività previste dal presente progetto formativo e di orientamento, rispettando l’ambiente di lavoro;\n" + 
				"- seguire le indicazioni dei tutori e fare riferimento ad essi per qualsiasi esigenza di tipo organizzativo o altre evenienze;\n" + 
				"- rispettare gli orari e le regole di comportamento concordati nel presente progetto;\n" + 
				"- rispettare i regolamenti interni e le norme disciplinari in uso presso il soggetto ospitante;\n" + 
				"- rispettare le norme in materia di igiene, sicurezza e salute sui luoghi di lavoro;\n" + 
				"- rispettare gli obblighi di riservatezza, sia durante che dopo lo svolgimento del tirocinio per quanto attiene ai dati, alle informazioni o a tutto quanto acquisito in termini di conoscenze in merito a processi produttivi e prodotti/servizi dell’azienda ospitante.",campiStatiche));
		// fine sessione obblighi tirocinante
		
		// inizio sessione autorizzazione al trattamento
		Phrase stringaAutorizzazione=new Phrase();
		stringaAutorizzazione.add(new Chunk("\nAUTORIZZAZIONE AL TRATTAMENTO DEI DATI PERSONALI ED AZIENDALI ED ASSUNZIONE DI RESPONSABILITÀ:",campiTitoli));
		stringaAutorizzazione.add(new Chunk("\nCon la sottoscrizione del presente progetto si autorizza il trattamento dei dati personali e del soggetto ospitante ai sensi e per gli effetti del Decreto Legislativo 30 giugno 2003, n. 196." + 
				"Agli effetti delle vigenti leggi e nella consapevolezza delle conseguenze penali connesse a dichiarazioni mendaci, si dichiara che tutti i dati sopra riportati sono veri.",campiStatiche));
		// fine sessione autorizzazione al trattamento
		
		
		Paragraph paragrafoTitleConvenzione=new Paragraph(stringaConvenzione);
		
		Paragraph paragrafoUniversita = new Paragraph(stringaUniversita);
		
		Paragraph paragrafoProgetto = new Paragraph(stringaProgetto);
				FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
		Paragraph paragrafoPromotore = new Paragraph(stringaPromotore);
		
		
		
		// inizio sezione tirocinante
		Paragraph paragrafoTirocinanteTitle = new Paragraph(stringaTirocinanteTitle,campiTitoli);
		Paragraph paragrafoTirocinante = new Paragraph(stringaTirocinante);
		// fine sezione tirocinante
		
		// inizio sezione tutor desingato dal dipartimento
		Paragraph paragrafoDesignatoAccademico=new Paragraph(stringaTutorDesignatoAccademico);
		// fine sezione tutor desingato dal dipartimento
		
		
		// inizio ntotale CFU
		Paragraph paragrafoTitleNumeroCFU=new Paragraph(numeroTitleTotCFU);
		// fine ntotale CFU
		
		//Inizio Sezione Proposta
		Paragraph paragrafoDatiObbAttCompMod= new Paragraph(sezioneObbComAttMod);
		// Fine sezione Proposta
		
		// Inizio sezione durata
		Paragraph paragrafoTitleDurata=new Paragraph(stringaDurata);
		// Fine sezione durata
		
		// inizio sezione indicazione dell'orario
		Paragraph paragrafoTitleOrario=new Paragraph(stringaOrario);
		// fine sezione indicazione dell'orario
		
		// inizio sezione polizze
		Paragraph paragrafoTitlePolizze=new Paragraph(stringaPolizze);
		Paragraph paragrafoUfficio=new Paragraph(stringaUfficio);
		// fine sezione polizze
		
		// inizio sezione obblighi
		Paragraph paragrafoTitleObbligo=new Paragraph(stringaObbligo);
		// fine sezione obblighi
		
		// inizio sezione autorizzazione
		Paragraph paragrafoTitleAutorizzazione=new Paragraph(stringaAutorizzazione);
		// fine sezione autorizzazione
		

		
		
		
		listeParagrafi.add(paragrafoUniversita);
		listeParagrafi.add(paragrafoProgetto);
		listeParagrafi.add(paragrafoTitleConvenzione);
		listeParagrafi.add(paragrafoPromotore);
		

		//tirocinante
		listeParagrafi.add(paragrafoTirocinanteTitle);
		listeParagrafi.add(paragrafoTirocinante);
		
		//tutor designato accademico
		listeParagrafi.add(paragrafoDesignatoAccademico);
		

		
		//inizio ntotali CFU
		listeParagrafi.add(paragrafoTitleNumeroCFU);
		
		//fine ntotali CFU
		
		//inserimento dei paragrafi della sezione proposta nella lista di paragrafi
		listeParagrafi.add(paragrafoDatiObbAttCompMod);
		// fine inserimento dei paragrafi della sezione proposta
		
		
		// inizio sezione durata
		listeParagrafi.add(paragrafoTitleDurata);
		// fine sezione durata
		
		// inizio sezione orario
		listeParagrafi.add(paragrafoTitleOrario);
		// fine sezione orario
		
		// inizio sezione polizze
		listeParagrafi.add(paragrafoTitlePolizze);
		listeParagrafi.add(paragrafoUfficio);
		// fine sezione polizze
		
		// inizio sezione obbligo
		listeParagrafi.add(paragrafoTitleObbligo);
		// fine sezione obbligo
		
		// inizio sezione autorizzazione
		listeParagrafi.add(paragrafoTitleAutorizzazione);
		// fine sezione autorizzazione
		
		paragrafoTitleConvenzione.setAlignment(Element.ALIGN_CENTER);
		paragrafoUfficio.setAlignment(Paragraph.ALIGN_CENTER);
		paragrafoUniversita.setAlignment(Element.ALIGN_CENTER);
		paragrafoProgetto.setAlignment(Element.ALIGN_CENTER);

		for (int i = 0; i < listeParagrafi.size(); i++) {
			document.add(listeParagrafi.get(i));
		}
		
		document.close();
		
		return document;
		}

	private Document createProgettoFormativoEsterno(HttpServletRequest request, HttpServletResponse response,
			PDFProgettoFormativo pdfProgetto) throws DocumentException, IOException {
		Document document = new Document();

		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		ArrayList<Paragraph> listeParagrafi = new ArrayList<Paragraph>();
		
		
		Font campiTitoli=new Font(Font.FontFamily.HELVETICA,10,Font.BOLD,BaseColor.BLACK);
		Font campiStatiche= new Font(Font.FontFamily.HELVETICA,8,Font.NORMAL,BaseColor.BLACK);
		Font campiCompilati=new Font(Font.FontFamily.HELVETICA,8,Font.BOLD,BaseColor.BLACK);
		
		
		Phrase stringaUniversita= new Phrase();
		stringaUniversita.add(new Chunk("UNIVERSITÀ DEGLI STUDI DI SALERNO 	\n DIPARTIMENTO DI INFORMATICA",campiTitoli));
		
		Phrase stringaProgetto=new Phrase();
		stringaProgetto.add(new Chunk("PROGETTO FORMATIVO E DI ORIENTAMENTO LAUREA TRIENNALE / LAUREA MAGISTRALE\n",campiTitoli));
		
		Phrase stringaConvenzione=new Phrase();
		stringaConvenzione.add(new Chunk("Relativo alla Convenzione per tirocinio di formazione ed orientamento (curriculare) stipulata in data ",campiStatiche));
		stringaConvenzione.add(new Chunk(pdfProgetto.getDataConvenzione(),campiCompilati));
		stringaConvenzione.add(new Chunk(", Repertorio N. ",campiStatiche));
		stringaConvenzione.add(new Chunk(pdfProgetto.getReportorioConvenzione(),campiCompilati));
		
		Phrase stringaPromotore=new Phrase();
		stringaPromotore.add(new Chunk("\nSOGGETTO PROMOTORE",campiTitoli));
		stringaPromotore.add(new Chunk("\nDipartimento di Informatica dell’Università degli Studi di Salerno;\nSede in Via Giovanni Paolo II, 132, 84084 Fisciano (Salerno)\nIndirizzo PEC ammicent@pec.unisa.it\n" + 
				"Codice Fiscale 80018670655\n" + 
				"Rappresentante legale: prof. Alfredo De Santis, in qualità di Direttore pro tempore, nato a nato a Nocera Inferiore (SA) il 07/12/1960.\n",campiStatiche));
		
		
		
		
		
		
		
		// inizio sezione soggetto ospitante
		
		String stringaTitleSoggettoOspitante="SOGGETTO OSPITANTE\n";
		
		Phrase sezioneSoggettoOspitante=new Phrase();
		sezioneSoggettoOspitante.add(new Chunk("Denominazione ",campiStatiche));
		sezioneSoggettoOspitante.add(new Chunk(pdfProgetto.getNomeDenominazione(),campiCompilati));
		sezioneSoggettoOspitante.add(new Chunk("\nSede Legale in ",campiStatiche));
		sezioneSoggettoOspitante.add(new Chunk(pdfProgetto.getSedeLegale(),campiCompilati));
		sezioneSoggettoOspitante.add(new Chunk("\nIndirizzoEmail ",campiStatiche));
		sezioneSoggettoOspitante.add(new Chunk(pdfProgetto.getIndirizzoEmail(),campiCompilati));
		sezioneSoggettoOspitante.add(new Chunk("\nCodice Fiscale ",campiStatiche));
		sezioneSoggettoOspitante.add(new Chunk(pdfProgetto.getCodiceFiscale(),campiCompilati));
		sezioneSoggettoOspitante.add(new Chunk("\nRappresentante legale: ",campiStatiche));
		sezioneSoggettoOspitante.add(new Chunk(pdfProgetto.getNomeReferenteAziendale()+" "+ pdfProgetto.getCognomeReferenteAziendale(),campiCompilati));
		sezioneSoggettoOspitante.add(new Chunk(" in qualita di ",campiStatiche));
		sezioneSoggettoOspitante.add(new Chunk(pdfProgetto.getRuoloReferenteAziendale(),campiCompilati));
		sezioneSoggettoOspitante.add(new Chunk(", nato a ",campiStatiche));
		sezioneSoggettoOspitante.add(new Chunk(pdfProgetto.getNatoReferenteAziendale(),campiCompilati));
		sezioneSoggettoOspitante.add(new Chunk(", il ",campiStatiche));
		sezioneSoggettoOspitante.add(new Chunk(pdfProgetto.getDataReferenteAziendale(),campiCompilati));
		sezioneSoggettoOspitante.add(new Chunk("\nCodice ATECO ",campiStatiche));
		sezioneSoggettoOspitante.add(new Chunk(pdfProgetto.getCodiceATECO(),campiCompilati));
		sezioneSoggettoOspitante.add(new Chunk("\nNumero Dipendenti a tempo indeterminato ",campiStatiche));
		sezioneSoggettoOspitante.add(new Chunk(pdfProgetto.getNumeroDipendenti(),campiCompilati));
		
		// fine sezione soggetto ospitante
		
		
		
		//inizio dati tirocinante
		String stringaTirocinanteTitle = "\nTirocinante";
		Phrase stringaTirocinante=new Phrase();
		stringaTirocinante.add(new Chunk("Cognome ",campiStatiche));
		stringaTirocinante.add(new Chunk(pdfProgetto.getCognomeStudente(),campiCompilati));
		stringaTirocinante.add(new Chunk(" Nome ",campiStatiche));
		stringaTirocinante.add(new Chunk(pdfProgetto.getNomeStudente(),campiCompilati));
		stringaTirocinante.add(new Chunk("\nData ",campiStatiche));
		stringaTirocinante.add(new Chunk(pdfProgetto.getDataNascitaStudente(),campiCompilati));
		stringaTirocinante.add(new Chunk(" e luogo di nascita ",campiStatiche));
		stringaTirocinante.add(new Chunk(pdfProgetto.getLuogoNascitaStudente(),campiCompilati));
		stringaTirocinante.add(new Chunk("\nResidenza ",campiStatiche));
		stringaTirocinante.add(new Chunk(pdfProgetto.getResidenteStudente(),campiCompilati));
		stringaTirocinante.add(new Chunk("\nTelefono n° ",campiStatiche));
		stringaTirocinante.add(new Chunk(pdfProgetto.getTelefonoStudente(),campiCompilati));
		stringaTirocinante.add(new Chunk("\nIndirizzo e-mail ",campiStatiche));
		stringaTirocinante.add(new Chunk(pdfProgetto.getEmailStudente(),campiCompilati));
		stringaTirocinante.add(new Chunk("\nIscritto al corso di Laurea ",campiStatiche));
		stringaTirocinante.add(new Chunk(pdfProgetto.getCorsoLaurea(),campiCompilati));
		stringaTirocinante.add(new Chunk(" in Informatica",campiStatiche));
		// fine dati tirocinante
		
		
	
		
		//inizio sezione tutor designato dal dipartimento
		Phrase stringaTutorDesignatoAccademico=new Phrase();
		stringaTutorDesignatoAccademico.add(new Chunk("TUTOR DESIGNATO DAL DIPARTIMENTO: ",campiTitoli));
		stringaTutorDesignatoAccademico.add(new Chunk(pdfProgetto.getNomeTutorAccademico()+" "+pdfProgetto.getCognomeTutorAccademico(),campiCompilati));
		//fine sezione tutor designato dal soggetto ospitante
		
		//inizio sezione tutor desginato dal soggetto ospitante
		Phrase stringaTutorDesignatoAziendale=new Phrase();
		stringaTutorDesignatoAziendale.add(new Chunk("\nTUTOR DESIGNATO DAL SOGGETTO OSPITANTE: ",campiTitoli));
		stringaTutorDesignatoAziendale.add(new Chunk(pdfProgetto.getNomeTutorAziendale()+" "+ pdfProgetto.getCognomeTutorAziendale(),campiCompilati));
		stringaTutorDesignatoAziendale.add(new Chunk("\nTel: ",campiStatiche));
		stringaTutorDesignatoAziendale.add(new Chunk(pdfProgetto.getTelefonoAziendale(),campiCompilati));
		stringaTutorDesignatoAziendale.add(new Chunk("\nE-mail: ",campiStatiche));
		stringaTutorDesignatoAziendale.add(new Chunk(pdfProgetto.getEmailTutorAziendale(),campiCompilati));
		
		//fine sezione tutor designato dal soggetto ospitante
		
		// inizio sezione n.totale di crediti
		int totCFU=pdfProgetto.getTotCFU();
		Phrase numeroTitleTotCFU=new Phrase();
		numeroTitleTotCFU.add(new Chunk("\nN.TOTALE DI CREDITI FORMATIVI PREVISTI PER L'ATTIVITA DI TIROCINIO ",campiTitoli));
		numeroTitleTotCFU.add(new Chunk(""+totCFU,campiCompilati));
		numeroTitleTotCFU.add(new Chunk(" di cui:",campiTitoli));
		if(pdfProgetto.getCorsoLaurea().equalsIgnoreCase("triennale")) {
			switch(totCFU) {
				case 6:
					numeroTitleTotCFU.add(new Chunk("\n6 CFU provienenti da tirocinio esterno (1) a scelta",campiCompilati));
					break;
				case 11:
					numeroTitleTotCFU.add(new Chunk("\n11 CFU per tirocinio curriculare",campiCompilati));
					break;
				case 17:
					numeroTitleTotCFU.add(new Chunk("\n11 CFU per tirocinio curriculare",campiCompilati));
					numeroTitleTotCFU.add(new Chunk("\n6 CFU provienenti da tirocinio esterno (1) a scelta",campiCompilati));
					break;
				case 23:
					numeroTitleTotCFU.add(new Chunk("\n11 CFU per tirocinio curriculare",campiCompilati));
					numeroTitleTotCFU.add(new Chunk("\n6 CFU provienenti da tirocinio esterno (1) a scelta",campiCompilati));
					numeroTitleTotCFU.add(new Chunk("\n6 CFU provienenti da tirocinio esterno (2) a scelta",campiCompilati));
			}
		}
		else if(pdfProgetto.getCorsoLaurea().equalsIgnoreCase("magistrale")) {
			switch(totCFU) {
				case 6:
					numeroTitleTotCFU.add(new Chunk("\n6 CFU provienenti da tirocinio esterno (1) a scelta",campiCompilati));
					break;
				case 12:
					numeroTitleTotCFU.add(new Chunk("\n6 CFU provienenti da tirocinio esterno (1) a scelta",campiCompilati));
					numeroTitleTotCFU.add(new Chunk("\n6 CFU provienenti da tirocinio esterno (2) a scelta",campiCompilati));
					break;
				
			}
		}
		// fine sezione n.totale di crediti
		
		// inizio sezione Proposta
		Phrase stringaSede=new Phrase();
		stringaSede.add(new Chunk("\nSEDE DI SVOLGIMENTO DEL TIROCINIO: ",campiTitoli));
		stringaSede.add(new Chunk(pdfProgetto.getNomeDenominazione(),campiCompilati));
		
		Phrase sezioneObbComAttMod= new Phrase();
		sezioneObbComAttMod.add(new Chunk("\nINDICAZIONE DEGLI OBIETTIVI:\n",campiTitoli));
		sezioneObbComAttMod.add(new Chunk(pdfProgetto.getObiettivi(),campiCompilati));
		sezioneObbComAttMod.add(new Chunk("\nINDICAZIONE LE COMPETENZE DA ACQUISIRE:\n",campiTitoli));
		sezioneObbComAttMod.add(new Chunk(pdfProgetto.getCompetenze(),campiCompilati));
		sezioneObbComAttMod.add(new Chunk("\nINDICAZIONE DELLE ATTIVITA' FORMATIVE PREVISTE:\n",campiTitoli));
		sezioneObbComAttMod.add(new Chunk(pdfProgetto.getAttivita(),campiCompilati));
		sezioneObbComAttMod.add(new Chunk("\nINDICAZIONE DELLE MODALITA DI SVOLGIMENTO DEL TIROCINIO:\n",campiTitoli));
		sezioneObbComAttMod.add(new Chunk(pdfProgetto.getModalita(),campiCompilati));
		// fine sezione Proposta
		
		// inizio durata del tirocinio
		
		Phrase stringaDurata=new Phrase();
		stringaDurata.add(new Chunk("\nDURATA DEL TIROCINIO: ",campiTitoli));
		stringaDurata.add(new Chunk("n°",campiStatiche));
		stringaDurata.add(new Chunk(""+pdfProgetto.getTotOre(),campiCompilati));
		stringaDurata.add(new Chunk(" ore",campiStatiche));
		// fine durata del tirocinio
		

		// inizio sezione indicazione dell'orario
		Phrase stringaOrario= new Phrase();
		stringaOrario.add(new Chunk("\nINDICAZIONE DELL'ORARIO DI SVOLGIMENTO DEL TIROCINIO",campiTitoli));
		stringaOrario.add(new Chunk(" Da lunedi a venerdi dalle 9:00/13:00 e 14:00/18:00",campiStatiche));
		// fine sezione indicazione dell'orario
		
		// inizio sezione polizze
		Phrase stringaPolizze= new Phrase();
		stringaPolizze.add(new Chunk("\nPOLIZZE ASSICURATIVE",campiTitoli));
		stringaPolizze.add(new Chunk("\nAi sensi dell’art.5 della convenzione",campiStatiche));
		stringaPolizze.add(new Chunk(pdfProgetto.getReportorioConvenzione(),campiCompilati));
		stringaPolizze.add(new Chunk(", a cui fa riferimento il presente progetto formativo, il Soggetto ospitante, in caso di infortunio del tirocinante durante lo svolgimento del tirocinio, si impegna a segnalare tempestivamente l’evento al Dipartimento di Informatica e al Responsabile dell’Ufficio Stato Giuridico e Formazione dell’Università, al fine di consentire a quest’ultimo di trasmettere la denuncia di infortunio all'INAIL in via telematica entro i tempi previsti dalla normativa vigente (48 ore).\n"+ 
				"Il Responsabile pro tempore dell’Ufficio Stato Giuridico e Formazione dell’Ateneo è il dott. Pasquale Talarico, di cui si indicano di seguito il recapito telefonico e gli indirizzi e-mail a cui far pervenire la segnalazione dell’infortunio con copia della convenzione e del progetto formativo.\n" + 
				"Inoltre all’Ufficio Stato Giuridico e Formazione vanno trasmessi, a cura del tirocinante, una copia del certificato medico di infortunio lavorativo e una relazione scritta sulle modalità in cui è avvenuto l’infortunio (orario dell’infortunio, data e ora di abbandono del posto del di lavoro, attività svolta in occasione dell’infortunio e cause dello stesso). Tale documentazione deve essere trasmessa con la massima tempestività per le vie brevi oppure tramite e-mail.",campiStatiche));
		
		Phrase stringaUfficio=new Phrase();
		stringaUfficio.add(new Chunk("Ufficio Stato Giuridico e Formazione\nTel. 089 96 6204\ne-mail p.talarico@unisa.it\ne-mail ufgiufor@unisa.it",campiStatiche));
		// fine sezione polizze
		
		
		// inizio sessione obblighi tirocinante
		Phrase stringaObbligo=new Phrase();
		stringaObbligo.add(new Chunk("OBBLIGHI DEL TIROCINANTE",campiTitoli));
		stringaObbligo.add(new Chunk("\n"
				+ "- Svolgere le attività previste dal presente progetto formativo e di orientamento, rispettando l’ambiente di lavoro;\n" + 
				"- seguire le indicazioni dei tutori e fare riferimento ad essi per qualsiasi esigenza di tipo organizzativo o altre evenienze;\n" + 
				"- rispettare gli orari e le regole di comportamento concordati nel presente progetto;\n" + 
				"- rispettare i regolamenti interni e le norme disciplinari in uso presso il soggetto ospitante;\n" + 
				"- rispettare le norme in materia di igiene, sicurezza e salute sui luoghi di lavoro;\n" + 
				"- rispettare gli obblighi di riservatezza, sia durante che dopo lo svolgimento del tirocinio per quanto attiene ai dati, alle informazioni o a tutto quanto acquisito in termini di conoscenze in merito a processi produttivi e prodotti/servizi dell’azienda ospitante.",campiStatiche));
		// fine sessione obblighi tirocinante
		
		// inizio sessione autorizzazione al trattamento
		Phrase stringaAutorizzazione=new Phrase();
		stringaAutorizzazione.add(new Chunk("\nAUTORIZZAZIONE AL TRATTAMENTO DEI DATI PERSONALI ED AZIENDALI ED ASSUNZIONE DI RESPONSABILITÀ:",campiTitoli));
		stringaAutorizzazione.add(new Chunk("\nCon la sottoscrizione del presente progetto si autorizza il trattamento dei dati personali e del soggetto ospitante ai sensi e per gli effetti del Decreto Legislativo 30 giugno 2003, n. 196." + 
				"Agli effetti delle vigenti leggi e nella consapevolezza delle conseguenze penali connesse a dichiarazioni mendaci, si dichiara che tutti i dati sopra riportati sono veri.",campiStatiche));
		// fine sessione autorizzazione al trattamento
		
		
		Paragraph paragrafoTitleConvenzione=new Paragraph(stringaConvenzione);
		
		Paragraph paragrafoUniversita = new Paragraph(stringaUniversita);
		
		Paragraph paragrafoProgetto = new Paragraph(stringaProgetto);
				FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
		Paragraph paragrafoPromotore = new Paragraph(stringaPromotore);
		
		
		// inizio sezione soggetto ospitante
		Paragraph paragrafoTitleSoggettoOspitante=new Paragraph(stringaTitleSoggettoOspitante,FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,BaseColor.BLACK));
		Paragraph paragrafoSezioneSoggettoOspitante=new Paragraph(sezioneSoggettoOspitante);
		// fine sezione soggetto ospitante
		
		// inizio sezione tirocinante
		Paragraph paragrafoTirocinanteTitle = new Paragraph(stringaTirocinanteTitle,campiTitoli);
		Paragraph paragrafoTirocinante = new Paragraph(stringaTirocinante);
		// fine sezione tirocinante
		
		// inizio sezione tutor desingato dal dipartimento
		Paragraph paragrafoDesignatoAccademico=new Paragraph(stringaTutorDesignatoAccademico);
		// fine sezione tutor desingato dal dipartimento
		
		
		// inizio sezione tutor desginato dal soggetto ospitante
		Paragraph paragrafoDesignatoAziendale=new Paragraph(stringaTutorDesignatoAziendale);
		//fine sezione tutor designato dal soggetto ospitante
		
		// inizio ntotale CFU
		Paragraph paragrafoTitleNumeroCFU=new Paragraph(numeroTitleTotCFU);
		// fine ntotale CFU
		
		//Inizio Sezione Proposta
		Paragraph paragrafoDatiObbAttCompMod= new Paragraph(sezioneObbComAttMod);
		// Fine sezione Proposta
		
		// Inizio sezione durata
		Paragraph paragrafoTitleDurata=new Paragraph(stringaDurata);
		// Fine sezione durata
		
		// inizio sezione indicazione dell'orario
		Paragraph paragrafoTitleOrario=new Paragraph(stringaOrario);
		// fine sezione indicazione dell'orario
		
		// inizio sezione polizze
		Paragraph paragrafoTitlePolizze=new Paragraph(stringaPolizze);
		Paragraph paragrafoUfficio=new Paragraph(stringaUfficio);
		// fine sezione polizze
		
		// inizio sezione obblighi
		Paragraph paragrafoTitleObbligo=new Paragraph(stringaObbligo);
		// fine sezione obblighi
		
		// inizio sezione autorizzazione
		Paragraph paragrafoTitleAutorizzazione=new Paragraph(stringaAutorizzazione);
		// fine sezione autorizzazione
		

		
		
		
		listeParagrafi.add(paragrafoUniversita);
		listeParagrafi.add(paragrafoProgetto);
		listeParagrafi.add(paragrafoTitleConvenzione);
		listeParagrafi.add(paragrafoPromotore);
		
		// soggetto ospitante
		listeParagrafi.add(paragrafoTitleSoggettoOspitante);
		listeParagrafi.add(paragrafoSezioneSoggettoOspitante);
		//tirocinante
		listeParagrafi.add(paragrafoTirocinanteTitle);
		listeParagrafi.add(paragrafoTirocinante);
		
		//tutor designato accademico
		listeParagrafi.add(paragrafoDesignatoAccademico);
		
		//tutor designato aziendale
		listeParagrafi.add(paragrafoDesignatoAziendale);
		
		//inizio ntotali CFU
		listeParagrafi.add(paragrafoTitleNumeroCFU);
		
		//fine ntotali CFU
		
		//inserimento dei paragrafi della sezione proposta nella lista di paragrafi
		listeParagrafi.add(paragrafoDatiObbAttCompMod);
		// fine inserimento dei paragrafi della sezione proposta
		
		
		// inizio sezione durata
		listeParagrafi.add(paragrafoTitleDurata);
		// fine sezione durata
		
		// inizio sezione orario
		listeParagrafi.add(paragrafoTitleOrario);
		// fine sezione orario
		
		// inizio sezione polizze
		listeParagrafi.add(paragrafoTitlePolizze);
		listeParagrafi.add(paragrafoUfficio);
		// fine sezione polizze
		
		// inizio sezione obbligo
		listeParagrafi.add(paragrafoTitleObbligo);
		// fine sezione obbligo
		
		// inizio sezione autorizzazione
		listeParagrafi.add(paragrafoTitleAutorizzazione);
		// fine sezione autorizzazione
		
		paragrafoTitleConvenzione.setAlignment(Element.ALIGN_CENTER);
		paragrafoUfficio.setAlignment(Paragraph.ALIGN_CENTER);
		paragrafoUniversita.setAlignment(Element.ALIGN_CENTER);
		paragrafoProgetto.setAlignment(Element.ALIGN_CENTER);

		for (int i = 0; i < listeParagrafi.size(); i++) {
			document.add(listeParagrafi.get(i));
		}
		
		document.close();
		
		return document;
		
	}
}
