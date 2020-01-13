package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistroDAO {

	public int doSave(Integer ID_Tirocinio, String status, String tipo) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			String inSvolgimento = "in svolgimento";
			PreparedStatement ps = con.prepareStatement(
					"insert into registro (ID_Tirocinio,Status, Tipo) VALUES (?,?,?)");
			ps.setInt(1, ID_Tirocinio);
			ps.setString(2, inSvolgimento);
			ps.setString(3, tipo);
			int rs = ps.executeUpdate();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
