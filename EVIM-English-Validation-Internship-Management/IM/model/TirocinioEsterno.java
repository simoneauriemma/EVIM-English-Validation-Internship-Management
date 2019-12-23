package model;

public class TirocinioEsterno {
	int ID_TirocinioEsterno, ID_TutorAccademico, ID_TutorAziendale, OreTotali, CFU, ID_Proposta;
	String EMAIL, data, status;
	Boolean FirmaAzienda, FirmaTutorAziendale, FirmaTutorAccademico, FirmaPdCD;

	public TirocinioEsterno() {

	}

	public TirocinioEsterno(int iD_TirocinioEsterno, int iD_TutorAccademico, int iD_TutorAziendale, int oreTotali,
			int cFU, int iD_Proposta, String eMAIL, String data, String status, Boolean firmaAzienda,
			Boolean firmaTutorAziendale, Boolean firmaTutorAccademico, Boolean firmaPdCD) {
		ID_TirocinioEsterno = iD_TirocinioEsterno;
		ID_TutorAccademico = iD_TutorAccademico;
		ID_TutorAziendale = iD_TutorAziendale;
		OreTotali = oreTotali;
		CFU = cFU;
		ID_Proposta = iD_Proposta;
		EMAIL = eMAIL;
		this.data = data;
		this.status = status;
		FirmaAzienda = firmaAzienda;
		FirmaTutorAziendale = firmaTutorAziendale;
		FirmaTutorAccademico = firmaTutorAccademico;
		FirmaPdCD = firmaPdCD;
	}

	public int getID_TirocinioEsterno() {
		return ID_TirocinioEsterno;
	}

	public void setID_TirocinioEsterno(int iD_TirocinioEsterno) {
		ID_TirocinioEsterno = iD_TirocinioEsterno;
	}

	public int getID_TutorAccademico() {
		return ID_TutorAccademico;
	}

	public void setID_TutorAccademico(int iD_TutorAccademico) {
		ID_TutorAccademico = iD_TutorAccademico;
	}

	public int getID_TutorAziendale() {
		return ID_TutorAziendale;
	}

	public void setID_TutorAziendale(int iD_TutorAziendale) {
		ID_TutorAziendale = iD_TutorAziendale;
	}

	public int getOreTotali() {
		return OreTotali;
	}

	public void setOreTotali(int oreTotali) {
		OreTotali = oreTotali;
	}

	public int getCFU() {
		return CFU;
	}

	public void setCFU(int cFU) {
		CFU = cFU;
	}

	public int getID_Proposta() {
		return ID_Proposta;
	}

	public void setID_Proposta(int iD_Proposta) {
		ID_Proposta = iD_Proposta;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getFirmaAzienda() {
		return FirmaAzienda;
	}

	public void setFirmaAzienda(Boolean firmaAzienda) {
		FirmaAzienda = firmaAzienda;
	}

	public Boolean getFirmaTutorAziendale() {
		return FirmaTutorAziendale;
	}

	public void setFirmaTutorAziendale(Boolean firmaTutorAziendale) {
		FirmaTutorAziendale = firmaTutorAziendale;
	}

	public Boolean getFirmaTutorAccademico() {
		return FirmaTutorAccademico;
	}

	public void setFirmaTutorAccademico(Boolean firmaTutorAccademico) {
		FirmaTutorAccademico = firmaTutorAccademico;
	}

	public Boolean getFirmaPdCD() {
		return FirmaPdCD;
	}

	public void setFirmaPdCD(Boolean firmaPdCD) {
		FirmaPdCD = firmaPdCD;
	}

}
