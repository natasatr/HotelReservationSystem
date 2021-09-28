package dto;

import java.sql.Date;

public class GostDTO extends OsobaDTO {
	

	private String BrojLK;
	public GostDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GostDTO(Integer osobaID, String ime, String prezime, String jmb, String pol, Mjesto mjesto,
			Date DatumRodjenja, String brojLK) {
		super(osobaID, ime, prezime, jmb, pol, mjesto, DatumRodjenja);
		// TODO Auto-generated constructor stub
		this.BrojLK = brojLK;
	}

	public GostDTO(String ime, String prezime, String jmb, String pol, Mjesto mjesto, Date DatumRodjenja, String brojLK) {
		super(ime, prezime, jmb, pol, mjesto, DatumRodjenja);
		// TODO Auto-generated constructor stub
		this.BrojLK=brojLK;
		
	}

	public String getBrojLK() {
		return BrojLK;
	}

	public void setBrojLK(String brojLK) {
		BrojLK = brojLK;
	}

	@Override
	public String toString() {
		return super.toString() + "GostDTO [BrojLK=" + BrojLK + "]";
	}
	

}
