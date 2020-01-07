package model;

public class Questionario_t {
	int id_questionario;
	String email;
	int id_tutor;
	int competenze_ingresso;
	int competenze_acquisite;
	int utilita;
	int motivazione;
	int capacita;
	int informazioni;
	int obiettivi;
	int servizi;
	int assistenza;
	int collaborazione;
	int durata;
	


	public Questionario_t() {
		super();
	}
	

	public Questionario_t(int id_questionario, String email, int id_tutor, int competenze_ingresso,
			int competenze_acquisite, int utilita, int motivazione, int capacita, int informazioni, int obiettivi,
			int servizi, int assistenza, int collaborazione, int durata) {
		super();
		this.id_questionario = id_questionario;
		this.email = email;
		this.id_tutor = id_tutor;
		this.competenze_ingresso = competenze_ingresso;
		this.competenze_acquisite = competenze_acquisite;
		this.utilita = utilita;
		this.motivazione = motivazione;
		this.capacita = capacita;
		this.informazioni = informazioni;
		this.obiettivi = obiettivi;
		this.servizi = servizi;
		this.assistenza = assistenza;
		this.collaborazione = collaborazione;
		this.durata = durata;
	}

	public int getId_questionario() {
		return id_questionario;
	}
	public void setId_questionario(int id_questionario) {
		this.id_questionario = id_questionario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId_tutor() {
		return id_tutor;
	}
	public void setId_tutor(int id_tutor) {
		this.id_tutor = id_tutor;
	}
	public int getCompetenze_ingresso() {
		return competenze_ingresso;
	}
	public void setCompetenze_ingresso(int competenze_ingresso) {
		this.competenze_ingresso = competenze_ingresso;
	}
	public int getCompetenze_acquisite() {
		return competenze_acquisite;
	}
	public void setCompetenze_acquisite(int competenze_acquisite) {
		this.competenze_acquisite = competenze_acquisite;
	}
	public int getUtilita() {
		return utilita;
	}
	public void setUtilita(int utilita) {
		this.utilita = utilita;
	}
	public int getMotivazione() {
		return motivazione;
	}
	public void setMotivazione(int motivazione) {
		this.motivazione = motivazione;
	}
	public int getCapacita() {
		return capacita;
	}
	public void setCapacita(int capacita) {
		this.capacita = capacita;
	}
	public int getInformazioni() {
		return informazioni;
	}
	public void setInformazioni(int informazioni) {
		this.informazioni = informazioni;
	}
	public int getObiettivi() {
		return obiettivi;
	}
	public void setObiettivi(int obiettivi) {
		this.obiettivi = obiettivi;
	}
	public int getServizi() {
		return servizi;
	}
	public void setServizi(int servizi) {
		this.servizi = servizi;
	}
	public int getAssistenza() {
		return assistenza;
	}
	public void setAssistenza(int assistenza) {
		this.assistenza = assistenza;
	}
	public int getCollaborazione() {
		return collaborazione;
	}
	public void setCollaborazione(int collaborazione) {
		this.collaborazione = collaborazione;
	}
	
	
	public int getDurata() {
		return durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}
}
