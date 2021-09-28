package dto;

import java.io.Serializable;

public class Mjesto implements Serializable{
	private Integer BrojPoste;
	private String Naziv;
	
	public Mjesto() {}

	public Mjesto(Integer brojPoste) {
		super();
		BrojPoste = brojPoste;
	}
	
	public Mjesto(Integer BrojPoste, String Naziv)
	{
		this.BrojPoste=BrojPoste;
		this.Naziv=Naziv;
	}

	public Integer getBrojPoste() {
		return BrojPoste;
	}

	public void setBrojPoste(Integer brojPoste) {
		BrojPoste= brojPoste;
	}

	public String getNaziv() {
		return Naziv;
	}

	public void setNaziv(String naziv) {
		Naziv=naziv;
	}

	@Override
	public String toString() {
		return "Mjesto [BrojPoste=" + BrojPoste + ", Naziv=" + Naziv + "]";
	}
	
	
	
	
	
	
	
	

}
