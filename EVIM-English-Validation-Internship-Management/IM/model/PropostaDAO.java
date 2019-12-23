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
				prop.setSede(rs.getString(3));
				prop.setTemaAmbito(rs.getString(4));
				prop.setMaterialeRisorse(rs.getString(5));
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
				prop.setSede(risultato.getString(3));
				prop.setTemaAmbito(risultato.getString(4));
				prop.setMaterialeRisorse(risultato.getString(5));
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
				proposta.setSede(risultato.getString(3));
				proposta.setTemaAmbito(risultato.getString(4));
				proposta.setMaterialeRisorse(risultato.getString(5));
				proposta.setID_Tutor(risultato.getInt(7));
				proposte.add(proposta);
			}

			return proposte;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
	
	
	/**
	 * 
	 * @param idAzienda
	 * @return restituisce un array di proposte con delle informazioni come nome e cognome di un tutor aziendale
	 */
	public static ArrayList<Proposta> getProposteAziendaWithIdAzienda(int idAzienda) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			
			PreparedStatement ps = con.prepareStatement("select ID_Proposta,Obiettivi,Sede,Tema_Ambito,Materiale_Risorse,Proposta.ID_TutorAziendale,TutorAziendale.Nome,TutorAziendale.Cognome\r\n" + 
					"from evim.Proposta join TutorAziendale on Proposta.ID_TutorAziendale=TutorAziendale.ID_TutorAziendale\r\n"+ 
					"where Proposta.ID_Azienda=?");
			ps.setInt(1, idAzienda);

			ArrayList<Proposta> proposte = new ArrayList<Proposta>();
			ResultSet risultato = ps.executeQuery();

			while (risultato.next()) {
				
				Proposta proposta = new Proposta();
				proposta.setID_Proposta(risultato.getInt(1));
				proposta.setObiettivi(risultato.getString(2));
				proposta.setSede(risultato.getString(3));
				proposta.setTemaAmbito(risultato.getString(4));
				proposta.setMaterialeRisorse(risultato.getString(5));
				proposta.setID_Tutor(risultato.getInt(6));
				proposta.setNomeTutorAziendale(risultato.getString(7));
				proposta.setCognomeTutorAziendale(risultato.getString(8));
				
				proposte.add(proposta);
			}

			return proposte;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
	
	
	/**
	 * 
	 * @param idAzienda
	 * @return restituisce un array di proposte di un tutor accademico con delle informazion come nome e cognome del TutorAccademioc
	 */
	/*
	public static ArrayList<Proposta> getProposteAziendaWithIdTutorAccademico(int idAzienda) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			System.out.println("Eseguo la query");
			PreparedStatement ps = con.prepareStatement("select ID_Proposta,Obiettivi,Sede,Tema_Ambito,Materiale_Risorse, Proposta.ID_TutorAcc,TutorAccademico.Nome,TutorAccademico.Cognome\r\n" + 
					"from evim.Proposta join TutorAccademico on Proposta.ID_TutorAcc=TutorAccademico.ID_TutorAccademico\r\n" + 
					"where Proposta.ID_TutorAcc=?;");
			ps.setInt(1, idAzienda);

			ArrayList<Proposta> proposte = new ArrayList<Proposta>();
			ResultSet risultato = ps.executeQuery();

			while (risultato.next()) {
				System.out.println("Query");
				Proposta proposta = new Proposta();
				proposta.setID_Proposta(risultato.getInt(1));
				proposta.setObiettivi(risultato.getString(2));
				proposta.setSede(risultato.getString(3));
				proposta.setTemaAmbito(risultato.getString(4));
				proposta.setMaterialeRisorse(risultato.getString(5));
				proposta.setID_TutorAcc(risultato.getInt(6));
				proposte.add(proposta);
				
			}

			return proposte;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}*/
	
	
	/**
	 * @author Antonio Giano
	 * @param obiettivi
	 * @param sede
	 * @param temaAmbito
	 * @param materialeRisorse
	 * @param idTutorAccademico
	 * @return inserisce una proposta di tirocinio interno
	 */
	public static boolean insertPropostaInterno(String obiettivi, String sede,String temaAmbito,String materialeRisorse,int idTutorAccademico) {
		try(Connection con=DriverManagerConnectionPool.getConnection()){
			PreparedStatement ps=con.prepareStatement("INSERT INTO `evim`.`Proposta` (`Obiettivi`, `Sede`, `Tema_Ambito`, `Materiale_Risorse`, `ID_Tutor`) VALUES (?, ?, ?, ?,?)");
			ps.setString(1, obiettivi);
			ps.setString(2, sede );
			ps.setString(3,temaAmbito);
			ps.setString(4,materialeRisorse);
			ps.setInt(5, idTutorAccademico);
			
			if(ps.executeUpdate()>0) {
				return true;
			}
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * @author Antonio Giano
	 * @param obiettivi
	 * @param sede
	 * @param temaAmbito
	 * @param materialeRisorse
	 * @param idAzienda
	 * @param intTutorAziendale
	 * @return inserisce una proposta di tircocinio esterno
	 */
	public static boolean insertPropostaEsterno(String obiettivi, String sede,String temaAmbito,String materialeRisorse,int idAzienda,int intTutorAziendale) {
		try(Connection con=DriverManagerConnectionPool.getConnection()){
			PreparedStatement ps=con.prepareStatement("INSERT INTO `evim`.`Proposta` (`Obiettivi`, `Sede`, `Tema_Ambito`, `Materiale_Risorse`, `ID_Azienda`,`ID_Tutor`) VALUES (?, ?, ?, ?,?,?)");
			ps.setString(1, obiettivi);
			ps.setString(2, sede );
			ps.setString(3,temaAmbito);
			ps.setString(4,materialeRisorse);
			ps.setInt(5, idAzienda);
			ps.setInt(6, intTutorAziendale);
			
			if(ps.executeUpdate()>0) {
				return true;
			}
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}