package dao;

import java.util.ArrayList;
import java.util.List;

import dto.Mjesto;

public interface MjestoDAO {
	
	ArrayList<Mjesto> mjestaSva();
	List<Mjesto> mjesta(String brojPoste);
	
	boolean dodajMjesto(Mjesto mjesto);
	boolean azirurajMjesto(Mjesto mjesto);
	boolean obrisiMjesto(String brojPoste);

}
