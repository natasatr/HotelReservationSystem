package controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import dao.mysql.MySQLOsobaDAO;
import dao.mysql.MySQLZaposleniDAO;
import dto.Mjesto;
import dto.OsobaDTO;
import dto.RecepcionerDTO;
import dto.ZaposleniDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.PasswordField;
	
public class DodavanjeOsobaController {
	    @FXML
	    private TextField imeID;

	    @FXML
	    private TextField prezimeID;

	    @FXML
	    private TextField jmbID;

	    @FXML
	    private ComboBox<String> polID;

	    @FXML
	    private TextField brojID;

	    @FXML
	    private DatePicker datumID;

	    @FXML
	    private TextField emailField;

	    @FXML
	    private TextField telField;

	    @FXML
	    private ComboBox<String> pozicijaField;

	    @FXML
	    private TextField userField;

	    @FXML
	    private PasswordField passField;

	    @FXML
	    private TextField plataField;

	    @FXML
	    private Button dodajID;

	    @FXML
	    private Button odustaniID;
   
	    ObservableList<String> polString = FXCollections.observableArrayList("Muski", "Zenski");
	    ObservableList<String> pozicija = FXCollections.observableArrayList("Recepcioner", "Menadzer","Sobarice","Kuhari","Treneri");
	    
	    
	    Mjesto mjesto = new Mjesto();
	    MySQLZaposleniDAO o = new MySQLZaposleniDAO();
	
    
    
    public void init() {
    	
    	//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MMM-dd");
    	
    
    	polID.setItems(polString);
    	pozicijaField.setItems(pozicija);
    
    
    }
    
    @FXML
    void dodaj(ActionEvent event) {
      	LocalDate date = datumID.getValue();
    	System.out.println(date);
    	String ime = imeID.getText();
    	System.out.println(ime);
    	String prezime = prezimeID.getText();
    	String jmb = jmbID.getText();
    	String pol = polID.getValue();
    	String postanskiBroj = brojID.getText();    
    	
    	Integer pBroj = Integer.parseInt(postanskiBroj);
    	 String email = emailField.getText();
    	 String tel = telField.getText();
    	 String poz = pozicijaField.getValue();
    	 String user = userField.getText();
    	 String pass = passField.getText();
    	 String plata = plataField.getText();
    	 System.out.println(plata);
    	 Double plataDoubl = Double.parseDouble(plata);
    
    	 ZaposleniDTO novizap = new ZaposleniDTO(ime, prezime, jmb, pol, new Mjesto(pBroj),Date.valueOf(date), email, tel, poz, user, pass, plataDoubl);
    	
    	o.dodajZaposleni(novizap);
    	System.out.println(novizap);
    	
    	clearFields();
    }

    @FXML
    void odustani(ActionEvent event) {
  
    	odustaniID.getScene().getWindow().hide();
    }
    
    public void clearFields()
    {
    	imeID.clear();
    	prezimeID.clear();
    	jmbID.clear();
    	polID.setValue(null);
    	brojID.clear();
    	datumID.setValue(null);
    	
    }
   
}
