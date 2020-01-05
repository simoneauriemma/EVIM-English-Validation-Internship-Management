package model;

public class Relazione {
	public Relazione(int idrelazione, String descrizione, String email, String status, int idtutor) {
		super();
		this.idrelazione = idrelazione;
		this.descrizione = descrizione;
		this.email = email;
		this.status = status;
		this.idtutor = idtutor;
	}
	
	public Relazione() {
		
	}
	public int getIdrelazione() {
		return idrelazione;
	}
	public void setIdrelazione(int idrelazione) {
		this.idrelazione = idrelazione;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getIdtutor() {
		return idtutor;
	}
	public void setIdtutor(int idtutor) {
		this.idtutor = idtutor;
	}
	int idrelazione;
	String descrizione;
	String email;
	String status;
	int idtutor;
}
