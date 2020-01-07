package model;

public class Attivita {
	int ID_Attivita, ID_Registro, OrarioIngresso, OrarioUscita;
	String Descrizione;
	boolean FirmaResponsabile;

	public Attivita(int iD_Attivitan, int iD_Registro, int orarioIngresso, int orarioUscita, String descrizione,
			boolean firmaResponsabile) {
		ID_Attivita = iD_Attivitan;
		ID_Registro = iD_Registro;
		OrarioIngresso = orarioIngresso;
		OrarioUscita = orarioUscita;
		Descrizione = descrizione;
		FirmaResponsabile = firmaResponsabile;
	}

	public Attivita() {
	}

	public int getID_Attivita() {
		return ID_Attivita;
	}

	public void setID_Attivita(int iD_Attivita) {
		ID_Attivita = iD_Attivita;
	}

	public int getID_Registro() {
		return ID_Registro;
	}

	public void setID_Registro(int iD_Registro) {
		ID_Registro = iD_Registro;
	}

	public int getOrarioIngresso() {
		return OrarioIngresso;
	}

	public void setOrarioIngresso(int orarioIngresso) {
		OrarioIngresso = orarioIngresso;
	}

	public int getOrarioUscita() {
		return OrarioUscita;
	}

	public void setOrarioUscita(int orarioUscita) {
		OrarioUscita = orarioUscita;
	}

	public String getDescrizione() {
		return Descrizione;
	}

	public void setDescrizione(String descrizione) {
		Descrizione = descrizione;
	}

	public boolean isFirmaResponsabile() {
		return FirmaResponsabile;
	}

	public void setFirmaResponsabile(boolean firmaResponsabile) {
		FirmaResponsabile = firmaResponsabile;
	}

}
