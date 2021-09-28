package dto;

import java.sql.Date;

public class RezervacijaDTO {
	
	private Integer RezervacijaID;
	private RecepcionerDTO recepcioner;
	private GostDTO gost;
	private Date datumPrijave;
	private Date datumOdijave;
	private SobaDTO soba;
	public RezervacijaDTO() {
		super();
	}
	public RezervacijaDTO(Integer rezervacijaID, RecepcionerDTO recepcioner, GostDTO gost, Date datumPrijave,
			Date datumOdijave, SobaDTO soba) {
		super();
		RezervacijaID = rezervacijaID;
		this.recepcioner = recepcioner;
		this.gost = gost;
		this.datumPrijave = datumPrijave;
		this.datumOdijave = datumOdijave;
		this.soba = soba;
	}
	
	
	public Integer getRezervacijaID() {
		return RezervacijaID;
	}
	public void setRezervacijaID(Integer rezervacijaID) {
		RezervacijaID = rezervacijaID;
	}
	public RecepcionerDTO getRecepcioner() {
		return recepcioner;
	}
	public void setRecepcioner(RecepcionerDTO recepcioner) {
		this.recepcioner = recepcioner;
	}
	public GostDTO getGost() {
		return gost;
	}
	public void setGost(GostDTO gost) {
		this.gost = gost;
	}
	public Date getDatumPrijave() {
		return datumPrijave;
	}
	public void setDatumPrijave(Date datumPrijave) {
		this.datumPrijave = datumPrijave;
	}
	public Date getDatumOdijave() {
		return datumOdijave;
	}
	public void setDatumOdijave(Date datumOdijave) {
		this.datumOdijave = datumOdijave;
	}
	public SobaDTO getSoba() {
		return soba;
	}
	public void setSoba(SobaDTO soba) {
		this.soba = soba;
	}
	@Override
	public String toString() {
		return "RezervacijaDTO [RezervacijaID=" + RezervacijaID + ", recepcioner=" + recepcioner + ", gost=" + gost
				+ ", datumPrijave=" + datumPrijave + ", datumOdijave=" + datumOdijave + ", soba=" + soba + "]";
	}
	
	
	

}
