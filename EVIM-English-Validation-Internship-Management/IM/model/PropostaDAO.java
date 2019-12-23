package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.management.RuntimeErrorException;

/**
 *
 * @author Simone Auriemma Classe che eseguira' le query riguardante la
 *
 */

public class PropostaDAO {

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
	 *           della proposta
	 * @return Restituisce l'oggetto proposta
	 */
	public Proposta findByID(Integer id) { // trova le proposte di tirocinio annesse ad un'azienda(id)
		return null;
	}

	/**
	 * @param id dato dal getParameter del form, che dovrebbe corrispondere all'id
	 *           della proposta
	 * @return Restituisce l'oggetto proposta
	 */
	public static ArrayList<Proposta> findByIdAzienda(int id_azienda) { // restituisce un Array di Proposte di tirocinio
																		// per il dato id dell'azienda
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("select * from EVIM.proposta where ID_Azienda=?");
			ps.setInt(1, id_azienda);

			ArrayList<Proposta> proposte = new ArrayList<Proposta>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Proposta prop = new Proposta();
				prop.setID_Proposta(rs.getInt(1));
				prop.setObiettivi(rs.getString(2));
				prop.setCompetenze(rs.getString(3));
				prop.setAttivita(rs.getString(4));
				prop.setModalita(rs.getString(5));
				prop.setID_Azienda(rs.getInt(6));
				prop.setID_Tutor(rs.getInt(7));

				proposte.add(prop);
			}
			return proposte;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * Restituisce la lista di tutte le proposte
	 * 
	 * @author Antonio Giano
	 * @return restituisce un ArrayList<Proposta> di proposte
	 */
	public static ArrayList<Proposta> getListaProposta() {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("select * from EVIM.proposta");

			ArrayList<Proposta> listaProposte = new ArrayList<Proposta>();

			ResultSet risultato = ps.executeQuery();

			while (risultato.next()) {
				Proposta prop = new Proposta();
				prop.setID_Proposta(risultato.getInt(1));
				prop.setObiettivi(risultato.getString(2));
				prop.setCompetenze(risultato.getString(3));
				prop.setAttivita(risultato.getString(4));
				prop.setModalita(risultato.getString(5));
				prop.setID_Azienda(risultato.getInt(6));
				prop.setID_Tutor(risultato.getInt(7));

				listaProposte.add(prop);
			}
			return listaProposte;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	/**
	 * @author Antonio Giano
	 * @author Nicola Sisti Metodo che prende tutte le proposte relative ad un tutor
	 *         specifico
	 * @param idTutorAccademico
	 * @return Arralist<Proposta>
	 */
	public static ArrayList<Proposta> findByIdTutorAccademico(int idTutorAccademico) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("select * from EVIM.proposta where ID_Tutor=?");
			ps.setInt(1, idTutorAccademico);

			ArrayList<Proposta> proposte = new ArrayList<Proposta>();
			ResultSet risultato = ps.executeQuery();

			while (risultato.next()) {
				Proposta proposta = new Proposta();
				proposta.setID_Proposta(risultato.getInt(1));
				proposta.setObiettivi(risultato.getString(2));
				proposta.setCompetenze(risultato.getString(3));
				proposta.setAttivita(risultato.getString(4));
				proposta.setModalita(risultato.getString(5));
				proposta.setID_Azienda(risultato.getInt(6));
				proposta.setID_Tutor(risultato.getInt(7));
				proposte.add(proposta);
			}

			return proposte;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
}
