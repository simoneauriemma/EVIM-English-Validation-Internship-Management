package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * @author Antonio Giano
 * Questa servlet permette di eseguire vari tipi di query nei confronti dell'entita Riconoscimento
 *
 */

public class RiconoscimentoDao {
	
	/**
	 * @author Antonio Giano
	 * Questo metodo inserisce i campi obbligatori del riconoscimento nel database.
	 * @param emailUser
	 * @param enteAzienda
	 * @param indirizzoSede
	 * @param profilo
	 * @param tipoContratto
	 * @param periodo
	 * @param oreSvolte
	 * @param CFUTirocinioObbligatorio
	 * @param CFUTirocinioEsterno
	 * @param CFUAccompagnmento 
	 */
	public static boolean insertRiconoscimenot(String emailUser,String enteAzienda, String indirizzoSede,String profilo,String tipoContratto,String periodo,int oreSvolte,int CFUTirocinioObbligatorio,int CFUTirocinioEsterno, int CFUAccompagnmento) {
		try(Connection con=DriverManagerConnectionPool.getConnection()){
			PreparedStatement ps=con.prepareStatement("INSERT INTO `evim`.`Riconoscimento` (`Email_User`, `Ente_Azienda`, `Profilo`, `Indirizzo_Sede`, `Tipo_Contratto`, `Periodo`, `Ore_Svolte`, `CFU_TirocinioObbligatorio`, `CFU_TirocinioEsterno`,`CFU_AccompagnamentoLavoro`,`Stato`) VALUES (?,?,?, ?, ?, ?, ?, ?, ?,?,'V')");
			ps.setString(1, emailUser);
			ps.setString(2, enteAzienda);
			ps.setString(3,profilo);
			ps.setString(4,indirizzoSede);
			ps.setString(5, tipoContratto);
			ps.setString(6, periodo);
			ps.setInt(7, oreSvolte);
			ps.setInt(8, CFUTirocinioObbligatorio);
			ps.setInt(9, CFUTirocinioEsterno);
			ps.setInt(10,CFUAccompagnmento);
			
			if(ps.executeUpdate()!=1) 
				return false;
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * @author Antonio Giano
	 * @param idModuloRiconoscimento, id del campo chiave dell'entity Riconoscimento per fare una ricerca univoca nel Database.
	 * @return un modulo di riconoscimento di attività lavorativa 
	 */
	public static  Riconoscimento getModuloRiconoscimento(String emailUser) {
		try(Connection con=DriverManagerConnectionPool.getConnection()){
			PreparedStatement ps=con.prepareStatement("SELECT * \n" + 
					"FROM evim.riconoscimento\n" + 
					"WHERE Email_User=?\n" + 
					"order by ID_Riconoscimento DESC LIMIT 1;");
			ps.setString(1, emailUser);
			ResultSet rs=ps.executeQuery();
			rs.next();
			System.out.println("email-->"+emailUser);
			Riconoscimento moduloRiconoscimento=new Riconoscimento();
			moduloRiconoscimento.setIdRiconoscimento(rs.getInt(1));
			moduloRiconoscimento.setEmailUser(rs.getString(2));
			moduloRiconoscimento.setEnteAzienda(rs.getString(3));
			moduloRiconoscimento.setProfilo(rs.getString(4));
			moduloRiconoscimento.setIndirizzoSede(rs.getString(5));
			moduloRiconoscimento.setTipoContratto(rs.getString(6));
			moduloRiconoscimento.setPeriodo(rs.getString(7));
			moduloRiconoscimento.setOreSvolte(rs.getInt(8));
			moduloRiconoscimento.setCFUTirocinioObbligatorio(rs.getInt(9));
			moduloRiconoscimento.setCFUTirocinioEsterno(rs.getInt(10));
			moduloRiconoscimento.setCFUAccompagnamentoLavoro(rs.getInt(11));
			moduloRiconoscimento.setStato(rs.getString(12));
			
			return moduloRiconoscimento;
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 
	 * @author Antonio Giano
	 * @return un elenco di tutti moduli di riconoscimento di attività lavorativa presenti sul database. Ad ognuno di questi moduli contiene l'email dello studente.  
	 */
	public static  ArrayList<Riconoscimento> getModuliRiconoscimentiWithStudenti() {
		try(Connection con=DriverManagerConnectionPool.getConnection()){
			PreparedStatement ps=con.prepareStatement("select ID_Riconoscimento,Email_User,Ente_Azienda,Profilo,Indirizzo_Sede,Tipo_Contratto,Periodo,Ore_Svolte,CFU_TirocinioObbligatorio,CFU_TirocinioEsterno,CFU_AccompagnamentoLAvoro,NAME,SURNAME,Matricola,Stato\n" + 
					"from evim.riconoscimento JOIN evim.User on riconoscimento.Email_User=User.Email;");
			ArrayList<Riconoscimento> elencoRiconoscimenti=new ArrayList<Riconoscimento>();
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Riconoscimento moduloRiconoscimento=new Riconoscimento();
				moduloRiconoscimento.setIdRiconoscimento(rs.getInt(1));
				moduloRiconoscimento.setEmailUser(rs.getString(2));
				moduloRiconoscimento.setEnteAzienda(rs.getString(3));
				moduloRiconoscimento.setProfilo(rs.getString(4));
				moduloRiconoscimento.setIndirizzoSede(rs.getString(5));
				moduloRiconoscimento.setTipoContratto(rs.getString(6));
				moduloRiconoscimento.setPeriodo(rs.getString(7));
				moduloRiconoscimento.setOreSvolte(rs.getInt(8));
				moduloRiconoscimento.setCFUTirocinioObbligatorio(rs.getInt(9));
				moduloRiconoscimento.setCFUTirocinioEsterno(rs.getInt(10));
				moduloRiconoscimento.setCFUAccompagnamentoLavoro(rs.getInt(11));
				moduloRiconoscimento.setNomeStudente(rs.getString(12));
				moduloRiconoscimento.setCognomeStudente(rs.getString(13));
				moduloRiconoscimento.setMatricolaStudente(rs.getString(14));
				moduloRiconoscimento.setStato(rs.getString(15));
				
				elencoRiconoscimenti.add(moduloRiconoscimento);
			}
			return elencoRiconoscimenti;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @author Antonio Giano
	 * @param emailStudente rappresenta un campo primario dello studente nel database
	 * @return restituisce un elenco di moduli di riconoscimento di attività lavorativa di uno studente specifico.
	 */
	public static  ArrayList<Riconoscimento> getModuliRiconoscimentiWithEmailStudente(String emailStudente) {
		try(Connection con=DriverManagerConnectionPool.getConnection()){
			PreparedStatement ps=con.prepareStatement("SELECT * FROM evim.Riconoscimento WHERE Email_User=?;");
			ps.setString(1, emailStudente);
			ArrayList<Riconoscimento> elencoRiconoscimenti=new ArrayList<Riconoscimento>();
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Riconoscimento moduloRiconoscimento=new Riconoscimento();
				moduloRiconoscimento.setIdRiconoscimento(rs.getInt(1));
				moduloRiconoscimento.setEmailUser(rs.getString(2));
				moduloRiconoscimento.setEnteAzienda(rs.getString(3));
				moduloRiconoscimento.setProfilo(rs.getString(4));
				moduloRiconoscimento.setIndirizzoSede(rs.getString(5));
				moduloRiconoscimento.setTipoContratto(rs.getString(6));
				moduloRiconoscimento.setPeriodo(rs.getString(7));
				moduloRiconoscimento.setOreSvolte(rs.getInt(8));
				moduloRiconoscimento.setCFUTirocinioObbligatorio(rs.getInt(9));
				moduloRiconoscimento.setCFUTirocinioEsterno(rs.getInt(10));
				moduloRiconoscimento.setCFUAccompagnamentoLavoro(rs.getInt(11));
				moduloRiconoscimento.setStato(rs.getString(12));
				elencoRiconoscimenti.add(moduloRiconoscimento);
			}
			return elencoRiconoscimenti;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static boolean changeStatoModulo(int idRiconoscimento, String modifica) {
		try(Connection con=DriverManagerConnectionPool.getConnection()){
			PreparedStatement ps=con.prepareStatement("UPDATE `evim`.`riconoscimento` SET `Stato` = ? WHERE (`ID_Riconoscimento` = ?)");
			ps.setString(1, modifica);
			ps.setInt(2, idRiconoscimento);
			
			if(ps.executeUpdate() > 0) 
				return true;
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
}
