package dao;

import java.util.ArrayList;
import java.util.List;

import dto.OsobaDTO;
import javafx.collections.ObservableList;

public interface OsobaDAO {
	
	ObservableList<OsobaDTO> sveOsobe();
	List<OsobaDTO> pretraga(String kljucnaRijec);
	boolean dodajOsobu(OsobaDTO zaposleni);
	boolean azurirajOsobu(OsobaDTO zaposleni);
	boolean obrisiOsobu(OsobaDTO zaposleni);


}
