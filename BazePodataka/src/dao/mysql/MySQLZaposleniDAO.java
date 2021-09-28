package dao.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.ZaposleniDAO;
import dto.Mjesto;
import dto.OsobaDTO;
import dto.ZaposleniDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import util.ConnectionPool;
import util.DBUtil;

public class MySQLZaposleniDAO implements ZaposleniDAO {

	@Override
	public ObservableList<ZaposleniDTO> svizaposleni() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		//String query = "select o.OsobaID, o.Ime, o.Prezime, o.JMB, o.Pol, o.DatumRodjenja, m.BrojPoste, m.Naziv  from osoba o inner join mjesto m where m.BrojPoste = o.Mjesto_BrojPoste";

		String query = "select z.OsobaZaposleniID, o.Ime, o.Prezime, o.JMB, o.Pol, m.BrojPoste, m.Naziv, o.DatumRodjenja, z.Email, z.Telefon, z.Pozicija, z.Username, z.Password, z.Plata from osoba o inner join mjesto m on o.Mjesto_BrojPoste = BrojPoste inner join zaposleni z where z.OsobaZaposleniID =o.OsobaID";
		ArrayList<ZaposleniDTO> resultList = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				resultList.add(new ZaposleniDTO(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5),new Mjesto(rs.getInt(6), rs.getString(7)), rs.getDate(8),rs.getString(9),
						rs.getString(10),rs.getString(11), rs.getString(12), rs.getString(13), rs.getDouble(14)));}

		} catch (SQLException ex) {
			Logger.getLogger(MySQLZaposleniDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DBUtil.close(rs, ps, conn);
		}

		return FXCollections.observableArrayList(resultList);
	}
	
	@Override
	public List<ZaposleniDTO> pretraga(String kljucnaRijec) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean dodajZaposleni(ZaposleniDTO zaposleni) {

		boolean res=false;
		Connection conn = null;
		//PreparedStatement ps = null;
		CallableStatement ps = null;

		String query = "{call dodaj_zaposlenog(?, ?, ?,?, ?,?, ? ,? ,?, ?, ?, ?)}";
				
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareCall(query);
			ps.setString(1, zaposleni.getIme());
			ps.setString(2, zaposleni.getPrezime());
			ps.setString(3, zaposleni.getJMB());
			ps.setString(4, zaposleni.getPol());
			ps.setInt(5, zaposleni.getMjesto_BrojPoste().getBrojPoste());
			ps.setDate(6, zaposleni.getDatumRodjenja());
			ps.setString(7, zaposleni.getEmail());
			ps.setString(8, zaposleni.getTelefon());
			ps.setString(9, zaposleni.getPozicija());
			ps.setString(10, zaposleni.getUsername());
			ps.setString(11, zaposleni.getPassword());
			ps.setDouble(12, zaposleni.getPlata());
			
			 res = ps.execute();

		} catch (SQLException ex) {
			Logger.getLogger(MySQLZaposleniDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DBUtil.close(ps, conn);
		}

		return res;
	}

	
	@Override
	public boolean azurirajZaposlenog(ZaposleniDTO zaposleni) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE zaposleni AS z INNER JOIN osoba AS o ON z.OsobaZaposleniID = o.OsobaID SET z.Email = ?  where OsobaZaposleniID= ?; ";
		try {

			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, zaposleni.getEmail());
			ps.setInt(2, zaposleni.getOsobaID());

			retVal = ps.executeUpdate() == 1;
		} catch (SQLException ex) {
			Logger.getLogger(MySQLZaposleniDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DBUtil.close(ps, conn);
		}

		return retVal;
	}


	@Override
	public boolean obrisiZaposlenog(ZaposleniDTO zaposleni) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM zaposleni WHERE OsobaZaposleniID=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, zaposleni.getOsobaID());

			retVal = ps.executeUpdate() == 1;
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Greska");
			alert.setHeaderText("Cannot delete or update a parent row: a foreign key constraint fails");


			alert.showAndWait();

		} finally {
			DBUtil.close(ps, conn);
		}

		return retVal;
	}

	
}
