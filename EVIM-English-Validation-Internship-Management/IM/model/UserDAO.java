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
	 * prende un user a seconda del flag usertype, restituiesce null se non è
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
				return utente;
			} else
				return null;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
	
	public static boolean insertNewUser(User u) { // SOLO PER STUDENTE
		String query= "INSERT INTO EVIM.user(`EMAIL`,`NAME`,`SURNAME`,`SEX`,`PASSWORD`,`USER_TYPE`,`tipoCorso`) VALUES(?,?,?,?,?,?,?)";
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, u.getEmail());
			ps.setString(2, u.getName());
			ps.setString(3, u.getSurname());
			ps.setString(4, u.getSex()+ "");
			ps.setString(5, u.getPassword());
			ps.setInt(6, 0);
			ps.setString(7, u.getCorso());
			
			if(ps.executeUpdate()==1) {
				return true;
			}
			
				

		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return false;

		
	}

}
