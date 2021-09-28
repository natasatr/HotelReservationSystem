package dao;

import java.util.List;

import dto.RezervacijaDTO;
import dto.RezervacijaView;
import dto.ZaposleniDTO;
import javafx.collections.ObservableList;

public interface RezervacijaDAO {
	ObservableList<RezervacijaView> prikazSvihRezervacija();
	List<RezervacijaDTO> pretraga(String kljucnaRijec);
	boolean kreirajRezervaciju(RezervacijaDTO rezervacija) ;
	boolean azurirajRezervaciju(RezervacijaDTO rezervacija);
	boolean ukloniRezervaciju(Integer rezervacija);

}
