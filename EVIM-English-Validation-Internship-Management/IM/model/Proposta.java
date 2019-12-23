package model;

/**
 * 
 * @author simon
 *
 */

/*
 * Proposta( ID_Proposta int not null auto_increment, Obiettivi varchar(200) not
 * null, Competenze varchar(200) not null, Attivita varchar(200) not null,
 * Modalita varchar(200) not null, ID_Azienda int not null, ID_Tutor int not
 * null, primary key(ID_Proposta), foreign key(ID_Azienda) references
 * Azienda(ID_Azienda) ON UPDATE cascade ON DELETE CASCADE );
 */

public class Proposta {
	int ID_Proposta, ID_Azienda, ID_Tutor;
	String obiettivi, sede,temaAmbito,materialeRisorse,nomeTutorAziendale,cognomeTutorAziendale;

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

	public int getID_Proposta() {
		return ID_Proposta;
	}

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public String getTemaAmbito() {
		return temaAmbito;
	}

	public void setTemaAmbito(String temaAmbito) {
		this.temaAmbito = temaAmbito;
	}

	public String getMaterialeRisorse() {
		return materialeRisorse;
	}

	public void setMaterialeRisorse(String materialeRisorse) {
		this.materialeRisorse = materialeRisorse;
	}

	public void setID_Proposta(int iD_Proposta) {
		this.ID_Proposta = iD_Proposta;
	}

	public int getID_Azienda() {
		return ID_Azienda;
	}

	public void setID_Azienda(int iD_Azienda) {
		this.ID_Azienda = iD_Azienda;
	}

	public int getID_Tutor() {
		return ID_Tutor;
	}

	public void setID_Tutor(int iD_Tutor) {
		this.ID_Tutor=iD_Tutor;
	}

	public String getObiettivi() {
		return obiettivi;
	}

	public void setObiettivi(String obiettivi) {
		this.obiettivi = obiettivi;
	}

}
