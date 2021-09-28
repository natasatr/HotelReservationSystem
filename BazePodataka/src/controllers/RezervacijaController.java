package controllers;

import java.sql.Date;
import java.time.LocalDate;

import dao.mysql.MySQLRezervacijaDAO;
import dao.mysql.MySQLSobaDAO;
import dto.GostDTO;
import dto.Mjesto;
import dto.RecepcionerDTO;
import dto.RezervacijaDTO;
import dto.SobaDTO;
import dto.ZaposleniDTO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;

public class RezervacijaController {

	@FXML
	private VBox vBox;

	@FXML
	private TextField imeField;

	@FXML
	private TextField prezimeField;

	@FXML
	private TextField jmbField;

	@FXML
	private ComboBox<String> polField;

	@FXML
	private TextField brojPosteField;

	@FXML
	private DatePicker datumRodjenjaField;

	@FXML
	private TextField brojLicneField;

	@FXML
	private DatePicker datumPrijavaField;

	@FXML
	private DatePicker datumOdjaveField;
	@FXML
	private TableView<SobaDTO> PrikazSlobodnihSobaTable;

	@FXML
	private TableColumn<SobaDTO, Integer> sobaIDCol;

	@FXML
	private TableColumn<SobaDTO, Integer> brojSobeCol;

	@FXML
	private TableColumn<SobaDTO, Double> CijenaSobeCol;

	@FXML
	private TableColumn<SobaDTO, String> TipSobeCol;

	@FXML
	private Button rezervacijaButton;

	@FXML
	private TableColumn<SobaDTO, Boolean> zauzetaCol;

	ObservableList<String> polString = FXCollections.observableArrayList("Muski", "Zenski");
	ObservableList<SobaDTO> obList = FXCollections.observableArrayList();
	MySQLSobaDAO sobaDAO = new MySQLSobaDAO();
	MySQLRezervacijaDAO rezervacijaDAO = new MySQLRezervacijaDAO();

	@FXML
	void kreirajRezervaciju(ActionEvent event) {

		String ime = imeField.getText();
		String prezime = prezimeField.getText();
		String jmb = jmbField.getText();
		String pol = polField.getValue();
		String brPoste = brojPosteField.getText();
		Integer broj = Integer.parseInt(brPoste);
		LocalDate datum = datumRodjenjaField.getValue();
		String brojLK = brojLicneField.getText();
		System.out.println(brojLK);

		SobaDTO selectItem = PrikazSlobodnihSobaTable.getSelectionModel().getSelectedItem();
		if (selectItem.getZauzeta()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Greska");
			alert.setHeaderText("Soba je vec zauzeta");
			alert.setContentText("");

			alert.showAndWait();
		} else {
			selectItem.setZauzeta(true);

			LocalDate datumPrijave = datumPrijavaField.getValue();
			LocalDate datumOdjave = datumOdjaveField.getValue();

			RezervacijaDTO rez = new RezervacijaDTO(null, new RecepcionerDTO(12),
					new GostDTO(ime, prezime, jmb, pol, new Mjesto(broj), Date.valueOf(datum), brojLK),
					Date.valueOf(datumPrijave), Date.valueOf(datumOdjave), selectItem);
			System.out.println(rez);
			// if()
			rezervacijaDAO.kreirajRezervaciju(rez);
			sobaDAO.azurirajSobu(selectItem);
		}

	}

	public void init(BorderPane pane) {

		pane.setCenter(vBox);
		PrikazSlobodnihSobaTable.setEditable(true);
		polField.setItems(polString);
		sobaIDCol.setCellValueFactory(new PropertyValueFactory<SobaDTO, Integer>("SobaID"));
		brojSobeCol.setCellValueFactory(new PropertyValueFactory<SobaDTO, Integer>("BrojSobe"));
		CijenaSobeCol.setCellValueFactory(new PropertyValueFactory<SobaDTO, Double>("Cijena"));
		TipSobeCol.setCellValueFactory(new PropertyValueFactory<SobaDTO, String>("TipSobe"));
		zauzetaCol.setCellValueFactory(new PropertyValueFactory<SobaDTO, Boolean>("Zauzeta"));
		PrikazSlobodnihSobaTable.setItems(obList);
		for (SobaDTO o : sobaDAO.sveSobe()) {
			Platform.runLater(() -> obList.add(o));
		}
		PrikazSlobodnihSobaTable.setEditable(true);

	}

}
