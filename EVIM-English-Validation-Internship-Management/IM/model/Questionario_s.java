package model;

public class Questionario_s {
	int id_questionario;
	String email;
	int assistenza_disp;
	int informazioni;
	int servizi;
	int assistenza;
	int logistica;
	int ambiente;
	int durata;
	int mansioni;
	int attivita;
	int formazione;
	int possibilita;
	int valutazione;
	int competenze;
	
	public Questionario_s() {
		
	}
	
	
	public Questionario_s(int id_questionario, String email, int assistenza_disp, int informazioni, int servizi,
			int assistenza, int logistica, int ambiente, int durata, int mansioni, int attivita, int formazione,
			int possibilita, int valutazione, int competenze) {
		super();
		this.id_questionario = id_questionario;
		this.email = email;
		this.assistenza_disp = assistenza_disp;
		this.informazioni = informazioni;
		this.servizi = servizi;
		this.assistenza = assistenza;
		this.logistica = logistica;
		this.ambiente = ambiente;
		this.durata = durata;
		this.mansioni = mansioni;
		this.attivita = attivita;
		this.formazione = formazione;
		this.possibilita = possibilita;
		this.valutazione = valutazione;
		this.competenze = competenze;
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
	public int getAssistenza_disp() {
		return assistenza_disp;
	}
	public void setAssistenza_disp(int assistenza_disp) {
		this.assistenza_disp = assistenza_disp;
	}
	public int getInformazioni() {
		return informazioni;
	}
	public void setInformazioni(int informazioni) {
		this.informazioni = informazioni;
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
	public int getLogistica() {
		return logistica;
	}
	public void setLogistica(int logistica) {
		this.logistica = logistica;
	}
	public int getAmbiente() {
		return ambiente;
	}
	public void setAmbiente(int ambiente) {
		this.ambiente = ambiente;
	}
	public int getDurata() {
		return durata;
	}
	public void setDurata(int durata) {
		this.durata = durata;
	}
	public int getMansioni() {
		return mansioni;
	}
	public void setMansioni(int mansioni) {
		this.mansioni = mansioni;
	}
	public int getAttivita() {
		return attivita;
	}
	public void setAttivita(int attivita) {
		this.attivita = attivita;
	}
	public int getFormazione() {
		return formazione;
	}
	public void setFormazione(int formazione) {
		this.formazione = formazione;
	}
	public int getPossibilita() {
		return possibilita;
	}
	public void setPossibilita(int possibilita) {
		this.possibilita = possibilita;
	}
	public int getValutazione() {
		return valutazione;
	}
	public void setValutazione(int valutazione) {
		this.valutazione = valutazione;
	}
	public int getCompetenze() {
		return competenze;
	}
	public void setCompetenze(int competenze) {
		this.competenze = competenze;
	}
}
