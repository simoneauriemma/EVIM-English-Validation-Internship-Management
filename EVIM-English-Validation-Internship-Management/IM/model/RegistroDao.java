package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class RegistroDao {

	/**
	 * @author Antonio Giano
	 * @param idRegistro, chiave primaria del registro nel database
	 * @return un valore booleano di true o false che indicano rispettivamente "update con successo" e "update fallito"
	 * Permette di cambiare lo stato in base allo stato ricevuto. 
	 */
	public static boolean changeStatoRegistro(int idRegistro,String stato) {
		String query= "UPDATE `evim`.`registro` SET `Status` = ? WHERE (`ID_Registro` = ?)";
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, stato);
			ps.setInt(2, idRegistro);
			
			if(ps.executeUpdate()>0)
				return true;
			else 
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new RuntimeException(e);
			
		}
	}
}
