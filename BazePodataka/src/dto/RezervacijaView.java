package dto;

import java.sql.Date;

public class RezervacijaView {

	private Integer RezervacijaID;
	private String imeRecepcionera;
	private String imeGosta;
	private Date DatumPrijave;
	private Date DatumOdjave;
	private int BrojRezervisaneSobe;
	
	
	public Integer getRezervacijaID() {
		return RezervacijaID;
	}
	public void setRezervacijaID(Integer rezervacijaID) {
		RezervacijaID = rezervacijaID;
	}
	public String getImeRecepcionera() {
		return imeRecepcionera;
	}
	public void setImeRecepcionera(String imeRecepcionera) {
		this.imeRecepcionera = imeRecepcionera;
	}
	public String getImeGosta() {
		return imeGosta;
	}
	public void setImeGosta(String imeGosta) {
		this.imeGosta = imeGosta;
	}
	public Date getDatumPrijave() {
		return DatumPrijave;
	}
	public void setDatumPrijave(Date datumPrijave) {
		DatumPrijave = datumPrijave;
	}
	public Date getDatumOdjave() {
		return DatumOdjave;
	}
	public void setDatumOdjave(Date datumOdjave) {
		DatumOdjave = datumOdjave;
	}
	public int getBrojRezervisaneSobe() {
		return BrojRezervisaneSobe;
	}
	public void setBrojRezervisaneSobe(int brojRezervisaneSobe) {
		BrojRezervisaneSobe = brojRezervisaneSobe;
	}
	public RezervacijaView() {
		super();
	}
	public RezervacijaView(Integer rezervacijaID, String imeRecepcionera, String imeGosta, Date datumPrijave,
			Date datumOdjave, int brojRezervisaneSobe) {
		super();
		RezervacijaID = rezervacijaID;
		this.imeRecepcionera = imeRecepcionera;
		this.imeGosta = imeGosta;
		DatumPrijave = datumPrijave;
		DatumOdjave = datumOdjave;
		BrojRezervisaneSobe = brojRezervisaneSobe;
	}
	@Override
	public String toString() {
		return "RezervacijaView [RezervacijaID=" + RezervacijaID + ", imeRecepcionera=" + imeRecepcionera
				+ ", imeGosta=" + imeGosta + ", DatumPrijave=" + DatumPrijave + ", DatumOdjave=" + DatumOdjave
				+ ", BrojRezervisaneSobe=" + BrojRezervisaneSobe + "]";
	}
	
	
	
	
}
