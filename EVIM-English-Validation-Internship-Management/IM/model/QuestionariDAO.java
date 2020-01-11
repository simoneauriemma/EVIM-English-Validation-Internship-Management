package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuestionariDAO {
	
	public static boolean insertQuestionarioS(Questionario_s quest) {
		String query= "INSERT INTO `evim`.`questionario_s` (`Email`, `AssistenzaDisp`, `Informazioni`, `Servizi`, `Assistenza`, `Logistica`, `Ambiente`, `Durata`, `Mansioni`, `Attivita`, `Formazione`, `Possibilita`, `Valutazione`, `Competenze`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);";
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
	
	public static boolean insertQuestionarioT(Questionario_t quest) {
		String query= "INSERT INTO `evim`.`questionario_t` (`Email`, `ID_TutorAziendale`, `ComptenzeIngresso`, `CompetenzeAcquisite`, `Utilita`, `Motivazione`, `Capacita`, `Informazioni`, `Obiettivi`, `Servizi`, `Assistenza`, `Collaborazione`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);;";
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, quest.getEmail());
			ps.setInt(2, quest.getId_tutor());
			ps.setInt(3, quest.getCompetenze_ingresso());
			ps.setInt(4, quest.getCompetenze_acquisite());
			ps.setInt(5, quest.getUtilita());
			
			ps.setInt(6, quest.getMotivazione());
			ps.setInt(7, quest.getCapacita());
			
			ps.setInt(8, quest.getInformazioni());
			ps.setInt(9, quest.getObiettivi());
			ps.setInt(10,quest.getServizi());
			ps.setInt(11, quest.getAssistenza());
			ps.setInt(12, quest.getCollaborazione());

		
			
			
			
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
