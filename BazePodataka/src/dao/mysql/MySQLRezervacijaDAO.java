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

import dao.RezervacijaDAO;
import dto.Mjesto;
import dto.RezervacijaDTO;
import dto.RezervacijaView;
import dto.ZaposleniDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import util.ConnectionPool;
import util.DBUtil;

public class MySQLRezervacijaDAO implements RezervacijaDAO {

	@Override
	public ObservableList<RezervacijaView> prikazSvihRezervacija() {
	
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;

			//String query = "select o.OsobaID, o.Ime, o.Prezime, o.JMB, o.Pol, o.DatumRodjenja, m.BrojPoste, m.Naziv  from osoba o inner join mjesto m where m.BrojPoste = o.Mjesto_BrojPoste";

			String query = "select * from rezervacija_view";
			ArrayList<RezervacijaView> resultList = new ArrayList<>();

			try {
				conn = DBUtil.getConnection();
				ps = conn.prepareStatement(query);
				rs = ps.executeQuery();

				while (rs.next()) {
					resultList.add(new RezervacijaView(rs.getInt(1), rs.getString(2), rs.getString(3),
							rs.getDate(4), rs.getDate(5), rs.getInt(6)));}

			} catch (SQLException ex) {
				Logger.getLogger(MySQLZaposleniDAO.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				DBUtil.close(rs, ps, conn);
			}

			return FXCollections.observableArrayList(resultList);
		}
	

	@Override
	public List<RezervacijaDTO> pretraga(String kljucnaRijec) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean kreirajRezervaciju(RezervacijaDTO rezervacija) {
		
		boolean res=false;
		Connection conn = null;
		//PreparedStatement ps = null;
		CallableStatement ps = null;

		String query = "{call kreiraj_proceduru(?, ?, ?,?, ?,?, ? ,? ,?, ?, ?)}";
				
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareCall(query);
			ps.setInt(1, rezervacija.getRecepcioner().getRecID());
			ps.setString(2, rezervacija.getGost().getIme());
			ps.setString(3, rezervacija.getGost().getPrezime());
			ps.setString(4, rezervacija.getGost().getJMB());
			ps.setString(5, rezervacija.getGost().getPol());
			ps.setInt(6, rezervacija.getGost().getMjesto_BrojPoste().getBrojPoste());
			ps.setDate(7, rezervacija.getGost().getDatumRodjenja());
			ps.setString(8, rezervacija.getGost().getBrojLK());
			ps.setDate(9, rezervacija.getDatumPrijave());
			ps.setDate(10, rezervacija.getDatumOdijave());
			ps.setInt(11, rezervacija.getSoba().getSobaID());
			
			 res = ps.execute();

		} catch (SQLException ex) {
			Logger.getLogger(MySQLZaposleniDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DBUtil.close(ps, conn);
		}

		return res;
	}


	@Override
	public boolean azurirajRezervaciju(RezervacijaDTO rezervacija) {
		boolean res=false;
		Connection conn = null;
		//PreparedStatement ps = null;
		CallableStatement ps = null;

		String query = "{call azuriranje_rezervacije(?,?)}";
				
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareCall(query);
			ps.setInt(1, rezervacija.getRezervacijaID());
			ps.setInt(2, rezervacija.getSoba().getSobaID());
			
			 res = ps.execute();

		} catch (SQLException ex) {
			Logger.getLogger(MySQLZaposleniDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DBUtil.close(ps, conn);
		}

		return res;
	}

	

	@Override
	public boolean ukloniRezervaciju(Integer rezervacija) {
		
		boolean res=false;
		Connection conn = null;
		//PreparedStatement ps = null;
		CallableStatement ps = null;

		String query = "{call brisi_rezervaciju(?)}";
				
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareCall(query);
			ps.setInt(1, rezervacija);
			
			 res = ps.execute();

		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Greska");
			alert.setHeaderText("Cannot delete or update a parent row: a foreign key constraint fails");


			alert.showAndWait();
		} finally {
			DBUtil.close(ps, conn);
		}

		return res;
	}

}
