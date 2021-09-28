package controllers;

import java.sql.Date;

import dao.mysql.MySQLRezervacijaDAO;
import dto.RezervacijaDTO;
import dto.RezervacijaView;
import dto.SobaDTO;
import dto.ZaposleniDTO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.NumberStringConverter;

public class PregledRezervacijaController {

    @FXML
    private AnchorPane paneTableRez;

    @FXML
    private BorderPane borderPane;

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private TableView<RezervacijaView> TabelaOsoba;

    @FXML
    private TableColumn<RezervacijaView, Integer> rezervacijaIDCol;

    @FXML
    private TableColumn<RezervacijaView, String> imeRecCol;

    @FXML
    private TableColumn<RezervacijaView, String> imeGostaCol;

    @FXML
    private TableColumn<RezervacijaView, Date> datumPrijaveCol;

    @FXML
    private TableColumn<RezervacijaView, Date> datumOdjaveCol;

    @FXML
    private TableColumn<RezervacijaView, Number> brojRezSobeCol;

    @FXML
    private Button brisiButton;

    @FXML
    private Button sortiranjeButton;
    
    @FXML 
    private Button azurirajButton;
    
    MySQLRezervacijaDAO prikaz = new MySQLRezervacijaDAO();
    ObservableList<RezervacijaView> obList = FXCollections.observableArrayList();
    MySQLRezervacijaDAO rez = new MySQLRezervacijaDAO();

  

    @FXML
    void sortiranjeRezervacije(ActionEvent event) {

    }
  
    public void init(BorderPane pane)
    {
    	pane.setCenter(paneTableRez);
    	TabelaOsoba.setEditable(true);
    	rezervacijaIDCol.setCellValueFactory(new PropertyValueFactory<RezervacijaView, Integer>("RezervacijaID"));
		imeRecCol.setCellValueFactory(new PropertyValueFactory<RezervacijaView, String>("ImeRecepcionera"));
		imeGostaCol.setCellValueFactory(new PropertyValueFactory<RezervacijaView, String>("ImeGosta"));
		datumPrijaveCol.setCellValueFactory(new PropertyValueFactory<RezervacijaView, Date>("DatumPrijave"));
		datumOdjaveCol.setCellValueFactory(new PropertyValueFactory<RezervacijaView, Date>("DatumOdjave"));
		brojRezSobeCol.setCellValueFactory(new PropertyValueFactory<RezervacijaView, Number>("BrojRezervisaneSobe"));
		
		TabelaOsoba.setItems(obList);

		for (RezervacijaView o : rez.prikazSvihRezervacija()) {
			Platform.runLater(() -> obList.add(o));
		}
		
		
		brojRezSobeCol.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		
		brojRezSobeCol.setOnEditCommit((CellEditEvent<RezervacijaView, Number> ev)->{
			RezervacijaView rez =TabelaOsoba.getSelectionModel().getSelectedItem();

	    	RezervacijaDTO r = new RezervacijaDTO();
	    	r.setRezervacijaID(rez.getRezervacijaID());
	    	SobaDTO s = new SobaDTO();
	    	s.setSobaID(ev.getNewValue().intValue());
	    	r.setSoba(s);
	    
	    	new MySQLRezervacijaDAO().azurirajRezervaciju(r);
		});

		
    }
    
    @FXML
    void brisiRezervaciju(ActionEvent event) {
    	
    	RezervacijaView rezV = TabelaOsoba.getSelectionModel().getSelectedItem();
    	RezervacijaDTO r = new RezervacijaDTO();
    	r.setRezervacijaID(rezV.getRezervacijaID());
    	rez.ukloniRezervaciju(r.getRezervacijaID());

    }


}
