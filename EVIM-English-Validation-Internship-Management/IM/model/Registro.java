package model;

public class Registro {
	int ID_Registro, TirocinioEsterno,FirmaResponsabile,FirmaTutorAccamico;
	String Status;
	public Registro(int iD_Registro, int tirocinioEsterno, int firmaResponsabile, int firmaTutorAccamico,
			String status) {
		super();
		ID_Registro = iD_Registro;
		TirocinioEsterno = tirocinioEsterno;
		FirmaResponsabile = firmaResponsabile;
		FirmaTutorAccamico = firmaTutorAccamico;
		Status = status;
	}
	public int getID_Registro() {
		return ID_Registro;
	}
	public void setID_Registro(int iD_Registro) {
		ID_Registro = iD_Registro;
	}
	public int getTirocinioEsterno() {
		return TirocinioEsterno;
	}
	public void setTirocinioEsterno(int tirocinioEsterno) {
		TirocinioEsterno = tirocinioEsterno;
	}
	public int getFirmaResponsabile() {
		return FirmaResponsabile;
	}
	public void setFirmaResponsabile(int firmaResponsabile) {
		FirmaResponsabile = firmaResponsabile;
	}
	public int getFirmaTutorAccamico() {
		return FirmaTutorAccamico;
	}
	public void setFirmaTutorAccamico(int firmaTutorAccamico) {
		FirmaTutorAccamico = firmaTutorAccamico;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}

}
