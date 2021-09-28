package dto;

public class SobaDTO {
	
	private Integer SobaID;
	private Integer BrojSobe;
	private Double Cijena;
	private String TipSobe;
	private Boolean Zauzeta;
	
	public SobaDTO() {
		super();
	}
	
	

	public SobaDTO(Integer sobaID, Integer brojSobe, Double cijena, String tipSobe, Boolean zauzeta) {
		super();
		SobaID = sobaID;
		BrojSobe = brojSobe;
		Cijena = cijena;
		TipSobe = tipSobe;
		Zauzeta=zauzeta;
	}

	public SobaDTO(Integer brojSobe, Double cijena, String tipSobe, Boolean zauzeta) {
		super();
		BrojSobe = brojSobe;
		Cijena = cijena;
		TipSobe = tipSobe;
		Zauzeta = zauzeta;
	}



	public Integer getSobaID() {
		return SobaID;
	}

	public void setSobaID(Integer sobaID) {
		SobaID = sobaID;
	}

	public Integer getBrojSobe() {
		return BrojSobe;
	}

	public void setBrojSobe(Integer brojSobe) {
		BrojSobe = brojSobe;
	}

	public Double getCijena() {
		return Cijena;
	}

	public void setCijena(Double cijena) {
		Cijena = cijena;
	}

	public String getTipSobe() {
		return TipSobe;
	}

	public void setTipSobe(String tipSobe) {
		TipSobe = tipSobe;
	}
	
	

	public Boolean getZauzeta() {
		return Zauzeta;
	}

	public void setZauzeta(Boolean zauzeta) {
		Zauzeta = zauzeta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((BrojSobe == null) ? 0 : BrojSobe.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SobaDTO other = (SobaDTO) obj;
		if (BrojSobe == null) {
			if (other.BrojSobe != null)
				return false;
		} else if (!BrojSobe.equals(other.BrojSobe))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SobaDTO [SobaID=" + SobaID + ", BrojSobe=" + BrojSobe + ", Cijena=" + Cijena + ", TipSobe=" + TipSobe
				+ ", Zauzeta=" + Zauzeta + "]";
	}

	
	
	
	
	

}
