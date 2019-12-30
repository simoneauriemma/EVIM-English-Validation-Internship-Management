package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			PreparedStatement ps=con.prepareStatement("INSERT INTO `evim`.`Riconoscimento` (`Email_User`, `Ente_Azienda`, `Profilo`, `Indirizzo_Sede`, `Tipo_Contratto`, `Periodo`, `Ore_Svolte`, `CFU_TirocinioObbligatorio`, `CFU_TirocinioEsterno`,`CFU_AccompagnamentoLavoro`) VALUES (?,?,?, ?, ?, ?, ?, ?, ?,?)");
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

	public static  Riconoscimento getModuloRiconoscimento(int idModuloRiconoscimento) {
		try(Connection con=DriverManagerConnectionPool.getConnection()){
			PreparedStatement ps=con.prepareStatement("SELECT * FROM evim.Riconoscimento WHERE ID_Riconoscimento=?;");
			ps.setInt(1, idModuloRiconoscimento);
			ResultSet rs=ps.executeQuery();
			rs.next();
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
			
			return moduloRiconoscimento;
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
