package dto;

import java.io.Serializable;
import java.sql.Date;

public class OsobaDTO implements Serializable{
	
	private Integer OsobaID;
	private String Ime;
	private String Prezime;
	private String JMB;
	private String Pol;
	private Mjesto Mjesto_BrojPoste;
	private Date DatumRodjenja;
	
	public OsobaDTO() {
		super();
	}
	
	public OsobaDTO (String ime, String prezime, String jmb, String pol, Mjesto mjesto, Date DatumRodjenja) {
		super();
	
		Ime = ime;
		Prezime = prezime;
		JMB =jmb;
		Pol =pol;
		this.Mjesto_BrojPoste = mjesto;
		this.DatumRodjenja=DatumRodjenja;
	}
	public OsobaDTO(Integer osobaID, String ime, String prezime, String jmb, String pol, Mjesto mjesto, Date DatumRodjenja) {
		super();
		OsobaID = osobaID;
		Ime = ime;
		Prezime = prezime;
		JMB =jmb;
		Pol =pol;
		this.Mjesto_BrojPoste = mjesto;
		this.DatumRodjenja=DatumRodjenja;
	}

	
	
	public Integer getOsobaID() {
		return OsobaID;
	}

	public void setOsobaID(Integer osobaID) {
		OsobaID = osobaID;
	}

	public String getIme() {
		return Ime;
	}

	public void setIme(String ime) {
		Ime = ime;
	}

	public String getPrezime() {
		return Prezime;
	}

	public void setPrezime(String prezime) {
		Prezime = prezime;
	}

	public String getJMB() {
		return JMB;
	}

	public void setJMB(String jMB) {
		JMB = jMB;
	}

	public String getPol() {
		return Pol;
	}

	public void setPol(String pol) {
		Pol = pol;
	}

	public Mjesto getMjesto_BrojPoste() {
		return Mjesto_BrojPoste;
	}

	public void setMjesto_BrojPoste(Mjesto mjesto_BrojPoste) {
		Mjesto_BrojPoste = mjesto_BrojPoste;
	}
	
	

	public Date getDatumRodjenja() {
		return DatumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		DatumRodjenja = datumRodjenja;
	}

	@Override
	public String toString() {
		return "OsobaDTO [OsobaID=" + OsobaID + ", Ime=" + Ime + ", Prezime=" + Prezime + ", JMB=" + JMB + ", Pol="
				+ Pol + ", Mjesto_BrojPoste=" + Mjesto_BrojPoste + "]";
	}

	

	
	
	
}
