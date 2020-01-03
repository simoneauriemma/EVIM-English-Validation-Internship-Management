package model;

public class Riconoscimento {

	
private int idRiconoscimento;
private String emailUser;
private String enteAzienda;
private String profilo;
private String indirizzoSede;
private String tipoContratto;
private String periodo; 
private int oreSvolte;
private int CFUTirocinioEsterno;
private int CFUTirocinioObbligatorio;
private int CFUAccompagnamentoLavoro;
private String stato;

private String nomeStudente;
private String cognomeStudente;
private String matricolaStudente;

	
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
	public String getMatricolaStudente() {
		return matricolaStudente;
	}
	public void setMatricolaStudente(String matricolaStudente) {
		this.matricolaStudente = matricolaStudente;
	}
	public int getIdRiconoscimento() {
		return idRiconoscimento;
	}
	public void setIdRiconoscimento(int idRiconoscimento) {
		this.idRiconoscimento = idRiconoscimento;
	}
	public String getEmailUser() {
		return emailUser;
	}
	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}
	public String getEnteAzienda() {
		return enteAzienda;
	}
	public void setEnteAzienda(String enteAzienda) {
		this.enteAzienda = enteAzienda;
	}
	public String getProfilo() {
		return profilo;
	}
	public void setProfilo(String profilo) {
		this.profilo = profilo;
	}
	public String getIndirizzoSede() {
		return indirizzoSede;
	}
	public void setIndirizzoSede(String indirizzoSede) {
		this.indirizzoSede = indirizzoSede;
	}
	public String getTipoContratto() {
		return tipoContratto;
	}
	public void setTipoContratto(String tipoContratto) {
		this.tipoContratto = tipoContratto;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public int getOreSvolte() {
		return oreSvolte;
	}
	public void setOreSvolte(int oreSvolte) {
		this.oreSvolte = oreSvolte;
	}
	public int getCFUTirocinioEsterno() {
		return CFUTirocinioEsterno;
	}
	public void setCFUTirocinioEsterno(int cFUTirocinioEsterno) {
		CFUTirocinioEsterno = cFUTirocinioEsterno;
	}
	public int getCFUTirocinioObbligatorio() {
		return CFUTirocinioObbligatorio;
	}
	public void setCFUTirocinioObbligatorio(int cFUTirocinioObbligatorio) {
		CFUTirocinioObbligatorio = cFUTirocinioObbligatorio;
	}
	public int getCFUAccompagnamentoLavoro() {
		return CFUAccompagnamentoLavoro;
	}
	public void setCFUAccompagnamentoLavoro(int cFUAccompagnamentoLavoro) {
		CFUAccompagnamentoLavoro = cFUAccompagnamentoLavoro;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	
	
}
