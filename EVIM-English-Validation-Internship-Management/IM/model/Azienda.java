package model;

import java.util.ArrayList;

/**
 * 
 * @author simon Entity dell'azienda
 */

/*
 * Azienda( ID_Azienda int not null auto_increment, CF char (11) not null,
 * Telefono varchar(8) not null, Nome varchar(50) not null, Password
 * varchar(50), Email varchar(20), SitoWeb varchar(40), Indirizzo varchar(100)
 * not null, Descizione varchar(100) not null, primary key(ID_Azienda,CF) );
 */

public class Azienda {
	int ID_Azienda;
	String CF, Telefono, Nome, Password, Email, SitoWeb, Indirizzo, Descrizione, numeroDipendenti, codiceAteco, idReferente,idConvenzione;
	ArrayList<Proposta> proposte; // inserito questa variabile cos� che posso inserire ad ogni azienda le proprie
									// proposte di tirocinio da servlet
	ReferenteAziendale referente;
	
	
	public ReferenteAziendale getReferente() {
		return referente;
	}

	public void setReferente(ReferenteAziendale referente) {
		this.referente = referente;
	}

	public void setID_Azienda(int iD_Azienda) {
		ID_Azienda = iD_Azienda;
	}

	public ArrayList<Proposta> getProposte() {
		return proposte;
	}
	
	public Azienda() {}

	public Azienda(int iD_Azienda, String cF, String telefono, String nome,
											String password, String email, String sitoWeb, String indirizzo,
											String descrizione, String numeroDipendenti, String codiceAteco,
											String idReferente, String idConvenzione) {
										super();
										ID_Azienda = iD_Azienda;
										CF = cF;
										Telefono = telefono;
										Nome = nome;
										Password = password;
										Email = email;
										SitoWeb = sitoWeb;
										Indirizzo = indirizzo;
										Descrizione = descrizione;
										this.numeroDipendenti = numeroDipendenti;
										this.codiceAteco = codiceAteco;
										this.idReferente = idReferente;
										this.idConvenzione = idConvenzione;
									}

	public void setProposte(ArrayList<Proposta> proposte) {
		this.proposte = proposte;
	}

	public int getID_Azienda() {
		return ID_Azienda;
	}

	public void setID_Azinda(int iD_Azienda) {
		ID_Azienda = iD_Azienda;
	}

	public String getCF() {
		return CF;
	}

	public void setCF(String cF) {
		CF = cF;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getSitoWeb() {
		return SitoWeb;
	}

	public void setSitoWeb(String sitoWeb) {
		SitoWeb = sitoWeb;
	}

	public String getIndirizzo() {
		return Indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		Indirizzo = indirizzo;
	}

	public String getDescrizione() {
		return Descrizione;
	}

	public void setDescrizione(String descrizione) {
		Descrizione = descrizione;
	}

	public String getNumeroDipendenti() {
		return numeroDipendenti;
	}

	public void setNumeroDipendenti(String numeroDipendenti) {
		this.numeroDipendenti = numeroDipendenti;
	}

	public String getCodiceAteco() {
		return codiceAteco;
	}

	public void setCodiceAteco(String codiceAteco) {
		this.codiceAteco = codiceAteco;
	}

	public String getIdReferente() {
		return idReferente;
	}

	public void setIdReferente(String idReferente) {
		this.idReferente = idReferente;
	}

	public String getIdConvenzione() {
		return idConvenzione;
	}

	public void setIdConvenzione(String idConvenzione) {
		this.idConvenzione = idConvenzione;
	}
	
	

}
