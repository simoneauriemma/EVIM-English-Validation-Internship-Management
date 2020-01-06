package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuestionariDAO {
	
	public static boolean insertQuestionarioS(Questionario_s quest) {
		String query= "INSERT INTO `evim`.`questionario_s` (`Email`, `AssistenzaDisp`, `Informazioni`, `Servizi`, `Assistenza`, `Logistica`, `Ambiente`, `Durata`, `Mansioni`, `Attivita`, `Formazione`, `Possibilita`, `Valutazione`, `Competenze`) VALUES ('?', '?', '?', '?', '?', '?', '?', '?', '?', '?', '?', '?', '?', '?');";
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, quest.getEmail());
			ps.setInt(2, quest.getAssistenza_disp());
			ps.setInt(3, quest.getInformazioni());
			ps.setInt(4, quest.getServizi());
			ps.setInt(5, quest.getAssistenza());
			ps.setInt(6, quest.getLogistica());
			ps.setInt(7, quest.getAmbiente());
			ps.setInt(8, quest.getDurata());
			ps.setInt(9, quest.getMansioni());
			ps.setInt(10, quest.getAttivita());
			ps.setInt(11,quest.getFormazione());
			ps.setInt(12, quest.getPossibilita());
			ps.setInt(13, quest.getValutazione());
			ps.setInt(14, quest.getCompetenze());
		
			
			
			
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

}
