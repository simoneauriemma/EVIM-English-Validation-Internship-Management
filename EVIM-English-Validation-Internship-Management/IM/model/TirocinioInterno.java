package model;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class TirocinioInterno {
	int ID_TirocinioInterno, ID_tutorAccademico, OreTotali, numeroCFU, ID_Proposta;
	String EMAIL, status;
	Boolean FirmaPdCD, FirmaTutorAccademico;
	String data;
	// la data uscirà in questo formato= 18/12/19

	public TirocinioInterno(int iD_TirocinioInterno, int iD_tutorAccademico, int oreTotali, int numeroCFU,
			int iD_Proposta, String eMAIL, String status, Boolean firmaPdCD, Boolean firmaTutorAccademico) {
		super();
		ID_TirocinioInterno = iD_TirocinioInterno;
		ID_tutorAccademico = iD_tutorAccademico;
		OreTotali = oreTotali;
		this.numeroCFU = numeroCFU;
		ID_Proposta = iD_Proposta;
		EMAIL = eMAIL;
		this.status = status;
		FirmaPdCD = firmaPdCD;
		FirmaTutorAccademico = firmaTutorAccademico;
		Date d = new Date();
		DateFormat formatoData = DateFormat.getDateInstance(DateFormat.LONG, Locale.ITALY);
		data = formatoData.format(d);

	}

	public TirocinioInterno() {
	}

	public int getID_TirocinioInterno() {
		return ID_TirocinioInterno;
	}

	public void setID_TirocinioInterno(int iD_TirocinioInterno) {
		ID_TirocinioInterno = iD_TirocinioInterno;
	}

	public int getID_tutorAccademico() {
		return ID_tutorAccademico;
	}

	public void setID_tutorAccademico(int iD_tutorAccademico) {
		ID_tutorAccademico = iD_tutorAccademico;
	}

	public int getOreTotali() {
		return OreTotali;
	}

	public void setOreTotali(int oreTotali) {
		OreTotali = oreTotali;
	}

	public int getNumeroCFU() {
		return numeroCFU;
	}

	public void setNumeroCFU(int numeroCFU) {
		numeroCFU = numeroCFU;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getFirmaPdCD() {
		return FirmaPdCD;
	}

	public void setFirmaPdCD(Boolean firmaPdCD) {
		FirmaPdCD = firmaPdCD;
	}

	public Boolean getFirmaTutorAccademico() {
		return FirmaTutorAccademico;
	}

	public void setFirmaTutorAccademico(Boolean firmaTutorAccademico) {
		FirmaTutorAccademico = firmaTutorAccademico;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
