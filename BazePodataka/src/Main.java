import java.sql.Date;
import java.util.List;

import dao.ZaposleniDAO;
import dao.mysql.MySQLOsobaDAO;
import dao.mysql.MySQLZaposleniDAO;
import dto.Mjesto;
import dto.OsobaDTO;
import dto.ZaposleniDTO;

public class Main {
	
	public static void main(String args[])
	{
		String dat = "1995-06-11";
		ZaposleniDTO novizap = new ZaposleniDTO(null, "Milancic", "Markovicic", "789654123", "Muski", new Mjesto(78000),Date.valueOf(dat), "milan@milan", "065123654", "Sekretar", "milanuser", "pass", 450.0);;
	
		MySQLZaposleniDAO zap = new MySQLZaposleniDAO();
		zap.dodajZaposleni(novizap);
	}

}
