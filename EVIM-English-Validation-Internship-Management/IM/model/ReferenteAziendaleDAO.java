package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReferenteAziendaleDAO {

	public ReferenteAziendale findByID(String id) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("select * from EVIM.referente_aziendale where CF= ?");
			ps.setString(1, id);
			ReferenteAziendale ref=new ReferenteAziendale();
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				ref.setCodiceFiscale(rs.getString(1));
				ref.setNome(rs.getString(2));
				ref.setCognome(rs.getString(3));
				ref.setLuogoNascita(rs.getString(4));
				ref.setDataNascita(rs.getString(5));
				ref.setRuolo(rs.getString(6));
				return ref;
			} else
				return null;
			
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
