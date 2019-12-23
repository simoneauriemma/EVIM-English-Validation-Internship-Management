package model;
/**
 * 
 * @author Antonio Giano
 *	Classe che permette la creazione di un progetto formativo del tirocinio interno/esterno
 */

public class PDFProgettoFormativo {
	public PDFProgettoFormativo() {
	}
	
	

public String getNomeStudente() {
		return nomeStudente;
	}
	public void setNomeStudente(String nomeStudente) {
		this.nomeStudente = nomeStudente;
	}
	public String getCognomeStudente() {
		return cognomeStudente;
	}
	public void setCognomeStudente(String cognomeStudente) {
		this.cognomeStudente = cognomeStudente;
	}
	public String getEmailStudente() {
		return emailStudente;
	}
	public void setEmailStudente(String emailStudente) {
		this.emailStudente = emailStudente;
	}
	public String getNomeTutorAccademico() {
		return nomeTutorAccademico;
	}
	public void setNomeTutorAccademico(String nomeTutorAccademico) {
		this.nomeTutorAccademico = nomeTutorAccademico;
	}
	public String getCognomeTutorAccademico() {
		return cognomeTutorAccademico;
	}
	public void setCognomeTutorAccademico(String cognomeTutorAccademico) {
		this.cognomeTutorAccademico = cognomeTutorAccademico;
	}
	public String getEmailTutorAccademico() {
		return emailTutorAccademico;
	}
	public void setEmailTutorAccademico(String emailTutorAccademico) {
		this.emailTutorAccademico = emailTutorAccademico;
	}
	public String getObiettivi() {
		return obiettivi;
	}
	public void setObiettivi(String obiettivi) {
		this.obiettivi = obiettivi;
	}
	public String getAttivita() {
		return attivita;
	}
	public void setAttivita(String attivita) {
		this.attivita = attivita;
	}
	public String getModalita() {
		return modalita;
	}
	public void setModalita(String modalita) {
		this.modalita= modalita;
	}
	
	public String getNomeTutorAziendale() {
		return nomeTutorAziendale;
	}

	public void setNomeTutorAziendale(String nomeTutorAziendale) {
		this.nomeTutorAziendale = nomeTutorAziendale;
	}



	public String getCognomeTutorAziendale() {
		return cognomeTutorAziendale;
	}



	public void setCognomeTutorAziendale(String cognomeTutorAziendale) {
		this.cognomeTutorAziendale = cognomeTutorAziendale;
	}



	public String getEmailTutorAziendale() {
		return emailTutorAziendale;
	}
	

public void setEmailTutorAziendale(String emailTutorAziendale) {
	this.emailTutorAziendale = emailTutorAziendale;
}

private String nomeStudente;
private String cognomeStudente;
private String emailStudente;
private String nomeTutorAccademico;
private String cognomeTutorAccademico;
private String emailTutorAccademico;
private String nomeTutorAziendale;
private String cognomeTutorAziendale;
private String emailTutorAziendale;
private String obiettivi;
private String attivita;
private String modalita;
}
