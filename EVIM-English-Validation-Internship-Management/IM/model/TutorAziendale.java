package model;

public class TutorAziendale {

	public TutorAziendale(int id, int idazienda, String nome, String cognome, String email, String password,
			String sesso) {

		this.id = id;
		this.idazienda = idazienda;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.sesso = sesso;
	}

	public TutorAziendale() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdAzienda() {
		return idazienda;
	}

	public void setIdAzienda(int idazienda) {
		this.idazienda = idazienda;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	private int id;
	private int idazienda;
	private String nome, cognome, email, password, sesso;

}