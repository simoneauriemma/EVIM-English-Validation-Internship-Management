package model;

public class User {

	private String email;
	private String name;
	private String surname;
	private char sex;
	private String password;
	private int userType;
	private String corso;
	private String luogoDiNascita;
	private String dataDiNascita;
	private String residente;
	private String via;
	private String telefono;
	private String matricola;

	// costruttore vuoto
	public User() {

	}

	// costruttore pieno
	public User(String email, String name, String surname, char sex, String password, int userType, String corso,
			String luogoNascita, String dataDiNascita, String residente, String via, String telefono,
			String matricola) {
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.sex = sex;
		this.password = password;
		this.userType = userType;
		this.corso = corso;
		this.luogoDiNascita = luogoNascita;
		this.dataDiNascita = dataDiNascita;
		this.residente = residente;
		this.via = via;
		this.telefono = telefono;
		this.matricola = matricola;
	}

	// tutti i getter e setter

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getCorso() {
		return corso;
	}

	public void setCorso(String corso) {
		this.corso = corso;
	}

	public String getLuogoDiNascita() {
		return luogoDiNascita;
	}

	public void setLuogoDiNascita(String luogoDiNascita) {
		this.luogoDiNascita = luogoDiNascita;
	}

	public String getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(String dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getResidente() {
		return residente;
	}

	public void setResidente(String residente) {
		this.residente = residente;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

}