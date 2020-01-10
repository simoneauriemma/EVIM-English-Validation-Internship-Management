package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * @author Simone Auriemma In questa classe verranno eseguire tutte le query
 *         riguardanti l'user
 * 
 */

public class UserDAO {

	/**
	 * @param id dato dal getParameter del form, che dovrebbe corrispondere all'id
	 *           dell'USER
	 * @return Restituisce l'email(primary key) dell'utente
	 */
	public String findByID(String id) {
		return null;
	}

	/**
	 * Restituisce l'oggetto USER tramite un id(EMAIL)
	 * 
	 * @param id id dato dal getParameter del form, che dovrebbe corrispondere
	 *           all'id dell'USER
	 * @return
	 */
	public User findByID1(String id) {
		return null;
	}

	/**
	 * Restituisce la lista di tutti gli utenti
	 * 
	 * @return
	 */
	public ArrayList<User> listaUtenti() {
		return null;
	}

	/**
	 * Restituisce la lista di tutti gli studenti
	 * 
	 * @param user_type tipo dell'utente
	 * @return
	 */
	public ArrayList<User> listaStudenti(int user_type) {
		return null;
	}

	/**
	 * prende un user a seconda del flag usertype, restituiesce null se non �
	 * presente nel db
	 * 
	 * @param usertype email password dell'USER
	 * @author nicola sisti
	 * @return User
	 */
	public static User doRetrieveByLoginData(int usertype, String email, String password) {

		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("select * from EVIM.user where EMAIL= ? and PASSWORD= ?  and USER_TYPE=?");
			User utente = new User();
			ps.setString(1, email);
			ps.setString(2, password);
			ps.setInt(3, usertype);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				utente.setEmail(rs.getString(1));
				utente.setName(rs.getString(2));
				utente.setSurname(rs.getString(3));
				utente.setSex(rs.getString(4).charAt(0));
				utente.setPassword(rs.getString(5));
				utente.setUserType(rs.getInt(6));
				utente.setCorso(rs.getString(7));
				utente.setLuogoDiNascita(rs.getString(8));
				utente.setDataDiNascita(rs.getString(9));
				utente.setResidente(rs.getString(10));
				utente.setVia(rs.getString(11));
				utente.setTelefono(rs.getString(12));
				utente.setMatricola(rs.getString(13));
				
				return utente;
			} else
				return null;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
	
	public static boolean insertNewUser(User u) { // SOLO PER STUDENTE
		String query= "INSERT INTO EVIM.user(`EMAIL`,`NAME`,`SURNAME`,`SEX`,`PASSWORD`,`USER_TYPE`,`tipoCorso`,`Luogo_Nascita`,`Data_Nascita`,`Residente`,`Via`,`Telefono`,`Matricola`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, u.getEmail());
			ps.setString(2, u.getName());
			ps.setString(3, u.getSurname());
			ps.setString(4, u.getSex()+ "");
			ps.setString(5, u.getPassword());
			ps.setInt(6, 0);
			ps.setString(7, u.getCorso());
			ps.setString(8,u.getLuogoDiNascita());//luogonascita
			ps.setString(9,u.getDataDiNascita());//data nascita
			ps.setString(10,u.getResidente());//residente
			ps.setString(11,u.getVia());//via
			ps.setString(12,u.getTelefono());//telefono
			ps.setString(13,u.getMatricola());//matricola
			if(ps.executeUpdate()==1) {
				return true;
			}
			
				

		} catch (SQLException e) {
		e.printStackTrace();
			
		}
		return false;

		
	}
	/**
	 * @author Antonio Giano
	 * @param email, per fare la ricerca univoca di uno studente
	 * @return ritorna l'oggetto creato di user
	 */
	public static User getStudenteWithEmail(String email) {

		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("select * from EVIM.user where EMAIL= ?");
			User utente = new User();
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				utente.setEmail(rs.getString(1));
				utente.setName(rs.getString(2));
				utente.setSurname(rs.getString(3));
				utente.setSex(rs.getString(4).charAt(0));
				utente.setPassword(rs.getString(5));
				utente.setUserType(rs.getInt(6));
				utente.setCorso(rs.getString(7));
				utente.setLuogoDiNascita(rs.getString(8));
				utente.setDataDiNascita(rs.getString(9));
				utente.setResidente(rs.getString(10));
				utente.setVia(rs.getString(11));
				utente.setTelefono(rs.getString(12));
				utente.setMatricola(rs.getString(13));
				
				return utente;
			} else
				return null;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

}
