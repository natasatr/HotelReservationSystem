package controllers;

import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.Main;
import dao.mysql.MySQLOsobaDAO;
import dao.mysql.MySQLZaposleniDAO;
import dto.Mjesto;
import dto.OsobaDTO;
import dto.ZaposleniDTO;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Controller {

	@FXML
	private AnchorPane mainAnchor;

	@FXML
	private BorderPane borderPane;

	@FXML
	private Button ZaposleniButton;

	@FXML
	private TableView<ZaposleniDTO> TabelaOsoba;

	@FXML
	private TableColumn<ZaposleniDTO, Integer> idCol;
	@FXML
	private TableColumn<ZaposleniDTO, String> imeCol;
	@FXML
	private TableColumn<ZaposleniDTO, String> prezimeCol;
	@FXML
	private TableColumn<ZaposleniDTO, String> jmbCol;
	@FXML
	private TableColumn<ZaposleniDTO, String> polCol;
	@FXML
	private TableColumn<ZaposleniDTO, Number> brojPosteCol;
	@FXML
	private TableColumn<ZaposleniDTO, String> nazivCol;

	@FXML
	private TableColumn<ZaposleniDTO, Date> datumID;

	@FXML
	private TableColumn<ZaposleniDTO, String> emailID;

	@FXML
	private TableColumn<ZaposleniDTO, String> telefonID;

	@FXML
	private TableColumn<ZaposleniDTO, String> pozicijaID;

	@FXML
	private TableColumn<ZaposleniDTO, String> usernameID;

	@FXML
	private TableColumn<ZaposleniDTO, String> passID;

	@FXML
	private TableColumn<ZaposleniDTO, String> plataID;

	@FXML
	private Button dodajId;

	@FXML
	private Button obrisiId;

	@FXML
	private Button updateId;
	
	@FXML

    private Button rezervacijaID;
	
	@FXML
	private Button pregledRezButton;

	ObservableList<ZaposleniDTO> obList = FXCollections.observableArrayList();
MySQLZaposleniDAO zaposleniDAO = new MySQLZaposleniDAO();
	public void init() {
		

		idCol.setCellValueFactory(new PropertyValueFactory<ZaposleniDTO, Integer>("OsobaID"));
		imeCol.setCellValueFactory(new PropertyValueFactory<ZaposleniDTO, String>("Ime"));
		prezimeCol.setCellValueFactory(new PropertyValueFactory<ZaposleniDTO, String>("Prezime"));
		jmbCol.setCellValueFactory(new PropertyValueFactory<ZaposleniDTO, String>("JMB"));
		polCol.setCellValueFactory(new PropertyValueFactory<ZaposleniDTO, String>("Pol"));
		brojPosteCol.setCellValueFactory(
				cellData -> new SimpleIntegerProperty(cellData.getValue().getMjesto_BrojPoste().getBrojPoste()));
		nazivCol.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getMjesto_BrojPoste().getNaziv()));
		datumID.setCellValueFactory(new PropertyValueFactory<ZaposleniDTO, Date>("DatumRodjenja"));
		emailID.setCellValueFactory(new PropertyValueFactory<ZaposleniDTO, String>("Email"));
		telefonID.setCellValueFactory(new PropertyValueFactory<ZaposleniDTO, String>("Telefon"));
		pozicijaID.setCellValueFactory(new PropertyValueFactory<ZaposleniDTO, String>("Pozicija"));
		usernameID.setCellValueFactory(new PropertyValueFactory<ZaposleniDTO, String>("Username"));
		passID.setCellValueFactory(new PropertyValueFactory<ZaposleniDTO, String>("Password"));
		plataID.setCellValueFactory(new PropertyValueFactory<ZaposleniDTO, String>("Plata"));

		TabelaOsoba.setItems(obList);

		ZaposleniButton.setOnAction(action -> {
			borderPane.setCenter(mainAnchor);
			obList.clear();
			for (ZaposleniDTO o : zaposleniDAO.svizaposleni()) {
				Platform.runLater(() -> obList.add(o));
			}
		});
		
		TabelaOsoba.setEditable(true);
		emailID.setCellFactory(TextFieldTableCell.forTableColumn());
	
	}

	@FXML
	void prikazDodavanje(ActionEvent event) {

		try {
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DodavanjeZaposlenog.fxml"));
			Parent root = loader.load();

			DodavanjeOsobaController cont = (DodavanjeOsobaController) loader.getController();
			cont.init();
			Scene scene = new Scene(root, 600, 350);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);// Main.getPrimaryStage().setScene();
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	void zaposleniView(ActionEvent event) {

	}

	@FXML
	void sobe(ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Soba.fxml"));
			Parent root = loader.load();

			 SobaController cont = (SobaController) loader.getController();
			 cont.init(borderPane);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
    @FXML
    void napraviRezeraciju(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RezervacijaFXML.fxml"));
			Parent root = loader.load();

			 RezervacijaController cont = (RezervacijaController) loader.getController();
			 cont.init(borderPane);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	

    }


    @FXML
    void pregledRezervacija(ActionEvent event)
    {
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PregledRezervacijaFXML.fxml"));
			Parent root = loader.load();

			 PregledRezervacijaController cont = (PregledRezervacijaController) loader.getController();
			 cont.init(borderPane);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    MySQLZaposleniDAO zaposleni = new MySQLZaposleniDAO();
    
    @FXML
    void AzuriranjeEmail(TableColumn.CellEditEvent<ZaposleniDTO, String> event) {
    	
    	ZaposleniDTO zap = TabelaOsoba.getSelectionModel().getSelectedItem();
    	zap.setEmail(event.getNewValue());
    	zaposleni.azurirajZaposlenog(zap);
    	
    }
    
    @FXML
    void obrisiButton(ActionEvent event) {
    	
    
    	ZaposleniDTO zap = TabelaOsoba.getSelectionModel().getSelectedItem();
    	zaposleniDAO.obrisiZaposlenog(zap);
    	

    }
}
