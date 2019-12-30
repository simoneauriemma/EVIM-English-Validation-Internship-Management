package model;

public class Convenzione {
	
	private int id;
	private String dataConvenzione, repertorio,durata;
	
	
	
	public Convenzione() {
	}
	
	public Convenzione(int id, String dataConvenzione, String repertorio, String durata) {
		this.id = id;
		this.dataConvenzione = dataConvenzione;
		this.repertorio = repertorio;
		this.durata = durata;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDataConvenzione() {
		return dataConvenzione;
	}
	public void setDataConvenzione(String dataConvenzione) {
		this.dataConvenzione = dataConvenzione;
	}
	public String getRepertorio() {
		return repertorio;
	}
	public void setRepertorio(String repertorio) {
		this.repertorio = repertorio;
	}
	public String getDurata() {
		return durata;
	}
	public void setDurata(String durata) {
		this.durata = durata;
	}
	
	

}
