package model;

import java.sql.Connection;
/**
 * 
 * @author Vincenzo Colacicco 
 *	Classe che esegue delle query per prelevare i dati del tutor aziendale.
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TutorAziendaleDAO {

	public static TutorAziendale findbyID() {
		try (Connection connection = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = connection.prepareStatement("select * from EVIM.tutoraziendale");

			ResultSet risultato = ps.executeQuery();

			TutorAziendale A = null;

			while (risultato.next()) {
				int id = risultato.getInt(1);
				int idazienda = risultato.getInt(2);
				String nome = risultato.getString(3);
				String cognome = risultato.getString(4);
				String email = risultato.getString(5);
				String password = risultato.getString(6);
				String sesso = risultato.getString(7);
				A = new TutorAziendale(id, idazienda, nome, cognome, email, password, sesso);

			}

			return A;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * @author Emilio Schiavo Questo metodo serve per trovare l'id del tutor
	 *         aziendale associato ad una proposta di tirocinio
	 * @return int IDTutAz, èl'id del tutor aziendale
	 */
	public static int doRetriveByIDProposta(int IDProp) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			int idProp = -1;
			PreparedStatement ps = con.prepareStatement("select ID_Tutor from proposta where ID_Proposta = ?");
			ps.setInt(1, IDProp);
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				idProp = res.getInt(1);
			}
			return idProp;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * Metodo che restituisce un tutoraziendale se presente nel db altrimenti
	 * restituisce null
	 * 
	 * @param email password
	 * @return TutorAziendale
	 * @author Nicola Sisti
	 */
	public static TutorAziendale doRetrieveByLoginData(String email, String password) {

		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"select * from EVIM.tutoraziendale where Email= ? AND Password= ? ");
			ps.setString(1, email);
			ps.setString(2, password);
			TutorAziendale utente = new TutorAziendale();

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				utente.setId(rs.getInt(1));
				utente.setIdAzienda(rs.getInt(2));
				utente.setNome(rs.getString(3));
				utente.setCognome(rs.getString(4));
				utente.setEmail(rs.getString(5));
				utente.setPassword(rs.getString(6));
				utente.setSesso(rs.getString(7));
				return utente;
			} else
				return null;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

}
