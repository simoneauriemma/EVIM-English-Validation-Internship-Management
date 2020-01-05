package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RelazioneDAO {
	
	public static boolean insertRelezione(int idTutor,String email, String descrizione, String status) {
		String query= "INSERT INTO EVIM.relazione(`Descrizione`,`Email`,`status`,`ID_TutorAziendale`) VALUES(?,?,?,?)";
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, descrizione);
			ps.setString(2, email);
			ps.setString(3, status);
			ps.setInt(4, idTutor);
		
			
			
			
			if(ps.executeUpdate()==1) {
				return true;
			}
			else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new RuntimeException(e);
			
		}
	}
	
	public static ArrayList<User> doRetriveStudenti(int idTutor) {
		ArrayList<User> studenti= new ArrayList<User>();
		String query= "select USER.EMAIL,NAME,SURNAME,MATRICOLA from USER join tirocinioesterno on tirocinioesterno.EMAIL=USER.EMAIL join tutoraziendale on tutoraziendale.ID_TutorAziendale = tirocinioesterno.ID_TutorAziendale\r\n" + 
				"where tirocinioesterno.status= 'in approvazione' and tirocinioesterno.ID_TutorAziendale=? AND USER.EMAIL NOT IN(SELECT relazione.Email from relazione where relazione.ID_TutorAziendale= ?);";
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(query);
			
			
			
			ps.setInt(1, idTutor);
			ps.setInt(2, idTutor);
			User u;
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				u=new User();
				u.setEmail(rs.getString(1));
				u.setName(rs.getString(2));
				u.setSurname(rs.getString(3));
				u.setMatricola(rs.getString(4));
				studenti.add(u);
			}
			
			
			
			

		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new RuntimeException(e);
			
		}
		return studenti;
	}

}
