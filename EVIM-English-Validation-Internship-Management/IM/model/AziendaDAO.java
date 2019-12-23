package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * @author Simone Auriemma Classe che eseguir� le query per quanto riguarda
 *         l'azienda
 */

public class AziendaDAO {

	/*
	 * Esempio di DAO public Immagine doRetrieveById(Immagine immagine) { try
	 * (Connection con = DriverManagerConnectionPool.getConnection()) {
	 * PreparedStatement ps =
	 * con.prepareStatement("select locazione from Immagine where IDfoto=?");
	 * ps.setInt(1,immagine.getIDfoto());
	 * 
	 * ResultSet rs=ps.executeQuery();
	 * 
	 * if(rs.next()) { immagine.setLocazione(rs.getString("locazione")); } return
	 * immagine; } catch (SQLException e) { throw new RuntimeException(); }
	 */

	/**
	 * @param id dato dal getParameter del form, che dovrebbe corrispondere all'id
	 *           dell'azienda
	 * @return Restituisce l'ID(primary key) dell'azienda
	 */

	public static ArrayList<Azienda> doRetriveAll() { // prende tutte le aziende presenti nel database
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("select * from EVIM.azienda");

			ArrayList<Azienda> aziende = new ArrayList<Azienda>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Azienda a = new Azienda();
				a.setID_Azinda(rs.getInt(1));
				a.setCF(rs.getString(2));
				a.setTelefono(rs.getString(3));
				a.setNome(rs.getString(4));
				a.setPassword(rs.getString(5));
				a.setEmail(rs.getString(6));
				a.setSitoWeb(rs.getString(7));
				a.setIndirizzo(rs.getString(8));
				a.setDescrizione(rs.getString(9));
				aziende.add(a);
			}
			return aziende;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		/**
		 * 
		 * @return ArrayLisy<Azienda> contenente tutte le aziende presenti nel db
		 */

	}

	public Azienda findByID(Integer id) {
		return null;
	}

	/**
	 * Restituisce la lista di tutte le proposte
	 * 
	 * @return ArrayList<> di proposte
	 */
	public ArrayList<Azienda> listaAziende() {
		return null;
	}

	/**
	 * Metodo che restituisce un aziends se presente nel db altrimenti restituisce
	 * null
	 * 
	 * @param email password
	 * @return Azienda
	 * @author Nicola Sisti
	 */
	public static Azienda doRetrieveByLoginData(String email, String password) {

		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"select * from EVIM.azienda where Email= ? AND Password= ?");
			Azienda utente = new Azienda();
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				utente.setID_Azinda(rs.getInt(1));
				utente.setCF(rs.getString(2));
				utente.setTelefono(rs.getString(3));
				utente.setNome(rs.getString(4));
				utente.setPassword(rs.getString(5));
				utente.setEmail(rs.getString(6));
				utente.setSitoWeb(rs.getString(7));
				utente.setIndirizzo(rs.getString(8));
				utente.setDescrizione(rs.getString(9));
				return utente;
			} else
				return null;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

}