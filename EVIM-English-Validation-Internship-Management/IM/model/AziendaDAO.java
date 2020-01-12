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
				a.setNumeroDipendenti(rs.getString(10));
				a.setCodiceAteco(rs.getString(11));
				a.setIdReferente(rs.getString(12));
				a.setIdConvenzione(rs.getString(13));
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
			Azienda azienda = new Azienda();
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				azienda.setID_Azinda(rs.getInt(1));
				azienda.setCF(rs.getString(2));
				azienda.setTelefono(rs.getString(3));
				azienda.setNome(rs.getString(4));
				azienda.setPassword(rs.getString(5));
				azienda.setEmail(rs.getString(6));
				azienda.setSitoWeb(rs.getString(7));
				azienda.setIndirizzo(rs.getString(8));
				azienda.setDescrizione(rs.getString(9));
				azienda.setNumeroDipendenti(rs.getString(10));
				azienda.setCodiceAteco(rs.getString(11));
				azienda.setIdReferente(rs.getString(12));
				azienda.setIdConvenzione(rs.getString(13));
				return azienda;
			} else
				return null;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
	
	public static ReferenteAziendale getReferenteAziendale(int idAzienda) {
		try(Connection con=DriverManagerConnectionPool.getConnection()){
			PreparedStatement ps=con.prepareStatement("select referente_aziendale.nome,referente_aziendale.cognome,referente_aziendale.luogo_nascita,referente_aziendale.data_nascita,referente_aziendale.ruolo\n" + 
					"from evim.Azienda join referente_aziendale on Azienda.CF_Referente=referente_aziendale.CF\n" + 
					"where Azienda.ID_Azienda=?");
			ps.setInt(1, idAzienda);
			ReferenteAziendale referente=new ReferenteAziendale();
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				referente.setNome(rs.getString(1));
				referente.setCognome(rs.getString(2));
				referente.setLuogoNascita(rs.getString(3));
				referente.setDataNascita(rs.getString(4));
				referente.setRuolo(rs.getString(5));
			}
			return referente;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}