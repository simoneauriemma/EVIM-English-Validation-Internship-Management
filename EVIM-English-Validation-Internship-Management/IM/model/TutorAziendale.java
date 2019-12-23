package model;

public class TutorAziendale {

	public TutorAziendale(int id, int idazienda, String nome, String cognome, String email, String password,
			String telefono) {
		this.id = id;
		this.idAzienda = idazienda;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.telefono= telefono;
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
		return idAzienda;
	}

	public void setIdAzienda(int idAzienda) {
		this.idAzienda = idAzienda;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	private int id;
	private int idAzienda;
	private String nome, cognome, email, password, telefono,sesso;


}
