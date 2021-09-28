package dto;

import java.sql.Date;

public class ZaposleniDTO extends OsobaDTO{
	
	private String Email;
	private String Telefon;
	private String Pozicija;
	private String Username;
	private String Password;
	private Double Plata;
	public ZaposleniDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ZaposleniDTO(Integer osobaID, String ime, String prezime, String jmb, String pol, Mjesto mjesto,
			Date DatumRodjenja, String email, String telefon, String pozicija, String user, String sifra, Double plata) {
		super(osobaID, ime, prezime, jmb, pol, mjesto, DatumRodjenja);
		// TODO Auto-generated constructor stub
		
		this.Email=email;
		this.Telefon=telefon;
		this.Pozicija=pozicija;
		this.Username=user;
		this.Password=sifra;
		this.Plata=plata;
		
		
	}
	public ZaposleniDTO(String ime, String prezime, String jmb, String pol, Mjesto mjesto, Date DatumRodjenja,
			String email,String telefon, String pozicija, String user, String sifra, Double plata) {
		super(ime, prezime, jmb, pol, mjesto, DatumRodjenja);
		// TODO Auto-generated constructor stub
		
		this.Email=email;
		this.Telefon=telefon;
		this.Pozicija=pozicija;
		this.Username=user;
		this.Password=sifra;
		this.Plata=plata;
	}
	
	
	
	public ZaposleniDTO(Integer osobaID) {
		osobaID=osobaID;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getTelefon() {
		return Telefon;
	}
	public void setTelefon(String telefon) {
		Telefon = telefon;
	}
	public String getPozicija() {
		return Pozicija;
	}
	public void setPozicija(String pozicija) {
		Pozicija = pozicija;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public Double getPlata() {
		return Plata;
	}
	public void setPlata(Double plata) {
		Plata = plata;
	}
	@Override
	public String toString() {
		return super.toString() + "ZaposleniDTO [Email=" + Email + ", Telefon=" + Telefon + ", Pozicija=" + Pozicija + ", Username="
				+ Username + ", Password=" + Password + ", Plata=" + Plata + "]";
	}
	
	
	
	

}
