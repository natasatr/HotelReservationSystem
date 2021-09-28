package controllers;
import java.sql.Date;
import java.sql.SQLIntegrityConstraintViolationException;

import dao.mysql.MySQLRezervacijaDAO;
import dao.mysql.MySQLSobaDAO;
import dao.mysql.MySQLZaposleniDAO;
import dto.RezervacijaDTO;
import dto.RezervacijaView;
import dto.SobaDTO;
import dto.ZaposleniDTO;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class SobaController {

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private TableView<SobaDTO> tabelaSoba;

    @FXML
    private TableColumn<SobaDTO, Integer> sobaCol;

    @FXML
    private TableColumn<SobaDTO, Integer> brojSobeCol;

    @FXML
    private TableColumn<SobaDTO, Number> cijenaSobeCol;

    @FXML
    private TableColumn<SobaDTO, String> tipSobeCol;

    @FXML
    private TableColumn<SobaDTO, CheckBox> zauzetaCol;

    @FXML
    private Button dodajSobuID;
    @FXML 
    private TextField textField;
    
    @FXML
    private Button pretraga;

    @FXML
    private Button ukloniSobuID;

    @FXML
    private Button AzurirajSobu;
    
    @FXML
    private Button sobaID;

    @FXML
    private Button rezervacijaID;

    @FXML
    private Button odjavaID;

    @FXML
    private Button ZaposleniButton;
    
	@FXML
	private BorderPane borderPane;

	MySQLSobaDAO sobaDAO = new MySQLSobaDAO();
    ObservableList<SobaDTO> obList = FXCollections.observableArrayList();
    
	public void init(BorderPane b) {
		b.setCenter(mainAnchor);
		//b.getScene().getWindow().setWidth(696);	

		tabelaSoba.setEditable(true);
		
		sobaCol.setCellValueFactory(new PropertyValueFactory<SobaDTO, Integer>("SobaID"));
		brojSobeCol.setCellValueFactory(new PropertyValueFactory<SobaDTO, Integer>("BrojSobe"));
		cijenaSobeCol.setCellValueFactory(new PropertyValueFactory<SobaDTO, Number>("Cijena"));
		tipSobeCol.setCellValueFactory(new PropertyValueFactory<SobaDTO, String>("TipSobe"));
		zauzetaCol.setCellValueFactory(new PropertyValueFactory<SobaDTO, CheckBox>("Zauzeta"));
		
		cijenaSobeCol.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		
		cijenaSobeCol.setOnEditCommit((CellEditEvent<SobaDTO, Number> ev)->{
			SobaDTO rez =tabelaSoba.getSelectionModel().getSelectedItem();
	    
	    	rez.setCijena(ev.getNewValue().doubleValue());
	   
	    	new MySQLSobaDAO().azurirajCijenuSobe(rez);
		});

		tabelaSoba.setItems(obList);

		for (SobaDTO o : sobaDAO.sveSobe()) {
			Platform.runLater(() -> obList.add(o));
		}
		
	}
	
	 @FXML
	    void dodajSobu(ActionEvent event) {
		 
		 try {
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DodavanjeSobeFXML.fxml"));
				Parent root = loader.load();

				DodavanjeSobeController cont = (DodavanjeSobeController) loader.getController();
				cont.init();
				Scene scene = new Scene(root, 300, 400);
				// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);// Main.getPrimaryStage().setScene();
				primaryStage.show();
			} catch (Exception e) {
				e.printStackTrace();

	    }

}
	 
	 @FXML
	    void ukloniSobu(ActionEvent event) {
		
		SobaDTO soba=  tabelaSoba.getSelectionModel().getSelectedItem();
		
		sobaDAO.obrisiSobu(soba);
			
		}
	 
	 
	    @FXML
	    void pretraga(ActionEvent event) {
	    	
	    	String text = textField.getText();
	    
	    	tabelaSoba.getItems().setAll(sobaDAO.pretraga(text));
	    	
	    	

	    }
		
	 
}