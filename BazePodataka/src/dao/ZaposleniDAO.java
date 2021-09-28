package dao;

import java.sql.SQLException;
import java.util.List;

import dto.OsobaDTO;
import dto.ZaposleniDTO;
import javafx.collections.ObservableList;

public interface ZaposleniDAO {
	
	ObservableList<ZaposleniDTO> svizaposleni();
	List<ZaposleniDTO> pretraga(String kljucnaRijec);
	boolean dodajZaposleni(ZaposleniDTO zaposleni) ;
	boolean azurirajZaposlenog(ZaposleniDTO zaposleni);
	boolean obrisiZaposlenog(ZaposleniDTO zaposleni);

}
