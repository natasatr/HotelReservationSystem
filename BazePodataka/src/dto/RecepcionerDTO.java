package dto;

import java.sql.Date;

public class RecepcionerDTO extends ZaposleniDTO {

	private Integer recID;
	public RecepcionerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RecepcionerDTO(Integer osobaID)
	{
		this.recID=osobaID;
	}
	public Integer getRecID() {
		return recID;
	}

	public void setRecID(Integer recID) {
		this.recID = recID;
	}

	public RecepcionerDTO(Integer osobaID, String ime, String prezime, String jmb, String pol, Mjesto mjesto,
			Date DatumRodjenja, String email, String telefon, String pozicija, String user, String sifra,
			Double plata) {
		super(osobaID, ime, prezime, jmb, pol, mjesto, DatumRodjenja, email, telefon, pozicija, user, sifra, plata);
		// TODO Auto-generated constructor stub
	}

	public RecepcionerDTO(String ime, String prezime, String jmb, String pol, Mjesto mjesto, Date DatumRodjenja,
			String email, String telefon, String pozicija, String user, String sifra, Double plata) {
		super(ime, prezime, jmb, pol, mjesto, DatumRodjenja, email, telefon, pozicija, user, sifra, plata);
		// TODO Auto-generated constructor stub
	}
	
	

}