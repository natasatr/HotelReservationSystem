package dao;

import java.util.ArrayList;
import java.util.List;
import dto.SobaDTO;
import javafx.collections.ObservableList;

public interface SobaDAO {
	
		
		ObservableList<SobaDTO> sveSobe();
		boolean dodajSobu(SobaDTO soba);
		boolean azurirajSobu(SobaDTO soba);
		boolean obrisiSobu(SobaDTO soba);

		boolean azurirajCijenuSobe(SobaDTO soba);
		ObservableList<SobaDTO> pretraga(String tip);

}
