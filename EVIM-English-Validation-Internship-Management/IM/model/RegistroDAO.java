package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistroDAO {

	public int doSave(Integer ID_Tirocinio, String status, String tipo) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			String inSvolgimento = "in svolgimento";
			PreparedStatement ps = con
					.prepareStatement("insert into registro (ID_Tirocinio,Status, Tipo) VALUES (?,?,?)");
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

	public static boolean doAlterFirmaTutorAc(int idregistro) { // per tutor accademici che approvano il registro di
		// tirocinio esterno
		boolean result = false;
		try (Connection con = DriverManagerConnectionPool.getConnection()) {

			PreparedStatement ps = con.prepareStatement(
					"UPDATE `evim`.`registro` SET `FirmaTutorAccamico` = '1' WHERE (`ID_Registro` = ? and `FirmaResponsabile` = '1' );");
			ps.setInt(1, idregistro);

			if (ps.executeUpdate() == 1) {
				ps=  con.prepareStatement(
						"UPDATE `evim`.`registro` SET `status` = 'completato' ;");
				if(ps.executeUpdate()==1) {
					result = true;
				}
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}

	public static boolean doAlterFirmaTutorAz(int idregistro) { // per tutor aziendali che approvano il registro di
		// tirocinio esterno
		boolean result = false;
		try (Connection con = DriverManagerConnectionPool.getConnection()) {

			PreparedStatement ps = con.prepareStatement(
					"UPDATE `evim`.`registro` SET `FirmaResponsabile` = '1' WHERE (`ID_Registro` = ?);");
			ps.setInt(1, idregistro);

			if (ps.executeUpdate() == 1) {
				result = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;

	}

	public static boolean doAlterFirmaTutorInterno(int idregistro) { // per tutor accademici che approvano il registro
		// di tirocinio interno
		boolean result = false;
		try (Connection con = DriverManagerConnectionPool.getConnection()) {

			PreparedStatement ps = con.prepareStatement(
					"UPDATE `evim`.`registro` SET `FirmaResponsabile` = '1', `FirmaTutorAccamico` = '1' WHERE (`ID_Registro` = ? ); "); // il
			// tutor
			// firma

			ps.setInt(1, idregistro);

			if (ps.executeUpdate() == 1) {
				ps=  con.prepareStatement(
						"UPDATE `evim`.`registro` SET `status` = 'completato' ;");
				if(ps.executeUpdate()==1) {
					result = true;
				}
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}

	public static boolean doAlterFirmaPdcdEsterno(int idregistro, int idtirocinio) { // per pdcd che approva un
		// registro di tirocinio
		// esterno
		boolean result = false;
		try (Connection con = DriverManagerConnectionPool.getConnection()) {

			
				PreparedStatement ps = con.prepareStatement(
						"UPDATE `evim`.`tirocinioesterno` SET `status` = 'completato' WHERE (`ID_TirocinioEsterno` = ?);");
				ps.setInt(1, idtirocinio);
				if (ps.executeUpdate() == 1) {
					// result = true;
					ps = con.prepareStatement(
							"UPDATE `evim`.`registro` SET `Status` = 'approvato' WHERE (`ID_Registro` = ?);"); // il
					// registro
					// diventa
					// approvato
					ps.setInt(1, idregistro);
					if (ps.executeUpdate() == 1) {
						result = true;
					}
					

				

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}

	public static boolean doAlterFirmaPdcdInterno(int idregistro, int idtirocinio) { // per pdcd che approva un
		// registro di tirocinio
		// interno
		boolean result = false;
		try (Connection con = DriverManagerConnectionPool.getConnection()) {

			PreparedStatement ps = con.prepareStatement(
					"UPDATE `evim`.`tirociniointerno` SET `status` = 'completato' WHERE (`ID_TirocinioInterno` = ?);");
			ps.setInt(1, idtirocinio);
			if (ps.executeUpdate() == 1) {

				ps = con.prepareStatement(
						"UPDATE `evim`.`registro` SET `Status` = 'approvato' WHERE (`ID_Registro` = ?);"); // il
				// registro
				// diventa
				// approvato
				ps.setInt(1, idregistro);
				if (ps.executeUpdate() == 1) {
					result = true;
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}

}
