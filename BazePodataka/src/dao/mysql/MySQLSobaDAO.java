package dao.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.SobaDAO;
import dto.Mjesto;
import dto.SobaDTO;
import dto.ZaposleniDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import util.ConnectionPool;
import util.DBUtil;

public class MySQLSobaDAO implements SobaDAO {

	@Override
	public ObservableList<SobaDTO> sveSobe() {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select * from soba";
		ArrayList<SobaDTO> resultList = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				resultList.add(
						new SobaDTO(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getString(4), rs.getBoolean(5)));
			}

		} catch (SQLException ex) {
			Logger.getLogger(MySQLZaposleniDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DBUtil.close(rs, ps, conn);
		}

		return FXCollections.observableArrayList(resultList);
	}

	@Override
	public boolean dodajSobu(SobaDTO soba) {

		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO soba(BrojSobe, Cijena, TipSobe, Zauzeta) values( ?, ?, ?, ?)";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, soba.getBrojSobe());
			ps.setDouble(2, soba.getCijena());
			ps.setString(3, soba.getTipSobe());
			ps.setBoolean(4, soba.getZauzeta());

			retVal = ps.executeUpdate() == 1;
		} catch (SQLException ex) {
			Logger.getLogger(MySQLZaposleniDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DBUtil.close(ps, conn);
		}

		return retVal;
	}

	@Override
	public boolean azurirajSobu(SobaDTO soba) {
		System.out.println(soba);
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "update soba s inner join rezervacija r on r.Soba_SobaID = s.SobaID SET s.Zauzeta=?";
		try {

			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setBoolean(1, soba.getZauzeta());

			retVal = ps.executeUpdate() == 1;
		} catch (SQLException ex) {
			Logger.getLogger(MySQLZaposleniDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DBUtil.close(ps, conn);
		}

		return retVal;
	}

	@Override
	public boolean obrisiSobu(SobaDTO soba) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "delete from soba where SobaID=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, soba.getSobaID());

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

	@Override
	public boolean azurirajCijenuSobe(SobaDTO soba) {
		boolean res=false;
		Connection conn = null;
		//PreparedStatement ps = null;
		CallableStatement ps = null;

		String query = "{call azuriraj_cijenu_sobe(?, ?)}";
				
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareCall(query);
			ps.setInt(1, soba.getSobaID());
			ps.setDouble(2, soba.getCijena());
	
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

	@Override
	public ObservableList<SobaDTO> pretraga(String tip) {
		ArrayList<SobaDTO> resultList = new ArrayList<>();
		Connection conn = null;
		//PreparedStatement ps = null;
		CallableStatement ps = null;
		ResultSet rs = null;

		String query = "{call pretragaSobe(?)}";
				
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareCall(query);
			ps.setString(1, tip);
	
			rs= ps.executeQuery();
				while (rs.next()) {
					resultList.add(
							new SobaDTO(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getString(4), rs.getBoolean(5)));
				}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.close(ps, conn);
		}

		return FXCollections.observableArrayList(resultList);
		
	}
	
}
