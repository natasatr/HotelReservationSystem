package dao.mysql;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import dao.OsobaDAO;
import dto.Mjesto;
import dto.OsobaDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.ConnectionPool;
import util.DBUtil;

public class MySQLOsobaDAO implements OsobaDAO{

	@Override
	public ObservableList<OsobaDTO> sveOsobe() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select o.OsobaID, o.Ime, o.Prezime, o.JMB, o.Pol, o.DatumRodjenja, m.BrojPoste, m.Naziv  from osoba o inner join mjesto m where m.BrojPoste = o.Mjesto_BrojPoste";

		ArrayList<OsobaDTO> resultList = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				resultList.add(new OsobaDTO(rs.getInt("OsobaID"), rs.getString("Ime"), rs.getString("Prezime"),
						rs.getString("JMB"), rs.getString("Pol"), new Mjesto(rs.getInt("BrojPoste"), rs.getString("Naziv")),rs.getDate("DatumRodjenja")));}

		} catch (SQLException ex) {
			Logger.getLogger(MySQLZaposleniDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DBUtil.close(rs, ps, conn);
		}

		return FXCollections.observableArrayList(resultList);
	}


	@Override
	public List<OsobaDTO> pretraga(String kljucnaRijec) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean dodajOsobu(OsobaDTO osoba) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO osoba(Ime, Prezime, JMB, Pol, Mjesto_BrojPoste, DatumRodjenja) values( ?, ?, ?, ?, ?, ?)";
				
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, osoba.getIme());
			ps.setString(2, osoba.getPrezime());
			ps.setString(3, osoba.getJMB());
			ps.setString(4, osoba.getPol());
			ps.setInt(5, osoba.getMjesto_BrojPoste().getBrojPoste());
			ps.setDate(6, osoba.getDatumRodjenja());
			
			

			retVal = ps.executeUpdate() == 1;
		} catch (SQLException ex) {
			Logger.getLogger(MySQLZaposleniDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DBUtil.close(ps, conn);
		}

		return retVal;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public boolean azurirajOsobu(OsobaDTO zaposleni) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean obrisiOsobu(OsobaDTO zaposleni) {
		// TODO Auto-generated method stub
		return false;
	}

}
