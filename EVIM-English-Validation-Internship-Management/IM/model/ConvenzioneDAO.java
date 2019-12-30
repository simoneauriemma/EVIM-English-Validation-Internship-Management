package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConvenzioneDAO {

	public Convenzione findByID(int id) {
		
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("select * from EVIM.convenzione where ID= ?");
			ps.setInt(1, id);
			Convenzione conv=new Convenzione();
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				conv.setId(rs.getInt(1));
				conv.setDataConvenzione(rs.getString(2));
				conv.setRepertorio(rs.getString(3));
				conv.setDurata(rs.getString(4));
				return conv;
			} else
				return null;
			
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
