package controllers;

import dao.mysql.MySQLSobaDAO;
import dto.SobaDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class DodavanjeSobeController {

	@FXML
	private TextField brojSobeField;

	@FXML
	private ComboBox<Double> cijenaSobeField;

	@FXML
	private ComboBox<String> tipSobeField;

	@FXML
	private ChoiceBox<Boolean> zauzetaSobaField;
	@FXML
	private Button dodajSobu;

	ObservableList<Double> cijenaSobe = FXCollections.observableArrayList(100.0, 200.0, 250.0, 300.0, 350.0);
	ObservableList<String> tipSobe = FXCollections.observableArrayList("Jednokrevetna", "Dvokrevetna", "Trokrevetna","Apartman");
	ObservableList<Boolean> zauzetaSoba = FXCollections.observableArrayList(true, false);
	
	MySQLSobaDAO sobaDodaj = new MySQLSobaDAO();

	public void init()
	{
		cijenaSobeField.setItems(cijenaSobe);
	    tipSobeField.setItems(tipSobe);
	    zauzetaSobaField.setItems(zauzetaSoba);
		
	}
	@FXML
	void dodajSobu(ActionEvent event) {
	    	
	    	String broj = brojSobeField.getText();
	    	int brojSobe = Integer.parseInt(broj);
	    	Double cijena = cijenaSobeField.getValue();
	    	String tip = tipSobeField.getValue();
	    	Boolean zauzeta = zauzetaSobaField.getValue();
	    	
	    	SobaDTO soba = new SobaDTO(brojSobe, cijena, tip, zauzeta);
	    	sobaDodaj.dodajSobu(soba);
	    	

	    }

}
