package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TirocinioEsternoDAO {

	/**
	 * @author Emilio Schiavo (del metodo doInsert) il metodo inserisce un nuovo
	 *         tirocinio e restituisce true se la query va a buon fine, false
	 *         altrimenti
	 */
	public static boolean doInsert(int idTutAcc, int idTutAz, int oreTotali, int cfu, int idProposta, String email,
			String data, String status) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			String q ="INSERT INTO `evim`.`tirocinioesterno`(`EMAIL`, `ID_TutorAccademico`, `ID_TutorAziendale`, `Data`, `OreTotali`, `status`, `CFU`, `ID_Proposta`) VALUES ("
					+ "'" + email + "','" + idTutAcc + "','" + idTutAz + "','" + data + "','"
					+ oreTotali + "','" + status + "','" + cfu + "','" + idProposta + "');";
			
			//INSERT INTO `evim`.`tirocinioesterno` (`EMAIL`, `ID_TutorAccademico`, `ID_TutorAziendale`, `Data`, `OreTotali`, `status`, `CFU`, `ID_Proposta`) VALUES ('e.schiavo8@studenti.unisa.it', '1', '1', '22/12/2019', '300', 'IN Approvazione', '11', '1');
			PreparedStatement ps = con.prepareStatement(q);
			System.out.println("query insert: " + q);
			if (ps.executeUpdate() > 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * Query che restituisce tutti i tirocini esterni in valutazione data la chiave
	 * primaria dello studente
	 * 
	 * @author Simone Auriemma
	 * @param EMAIL
	 * @return ArrayList<TirocinioEsterno>
	 */
	public ArrayList<TirocinioEsterno> doRetriveAllByStudent(String EMAIL) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			// query per la visualizzazione della pagina da parte dello studente
			// query per visualizzare le richieste in valutazione
			String inValutazione = "in approvazione";
			PreparedStatement ps = con
					.prepareStatement(" select * from EVIM.TirocinioEsterno where EMAIL=? AND status=?");
			
			ps.setString(1, EMAIL);
			ps.setString(2, inValutazione);
			
			ArrayList<TirocinioEsterno> richieste = new ArrayList<TirocinioEsterno>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TirocinioEsterno a = new TirocinioEsterno();
				a.setID_TirocinioEsterno(rs.getInt("ID_TirocinioEsterno"));
				a.setEMAIL(rs.getString("EMAIL"));
				a.setID_TutorAccademico(rs.getInt("ID_TutorAccademico"));
				a.setID_TutorAziendale(rs.getInt("ID_TutorAziendale"));
				a.setData(rs.getString("Data"));
				a.setOreTotali(rs.getInt("OreTotali"));
				a.setStatus(rs.getString("status"));
				a.setCFU(rs.getInt("CFU"));
				a.setFirmaAzienda(rs.getBoolean("FirmaAzienda"));
				a.setFirmaTutorAziendale(rs.getBoolean("FirmaTutorAziendale"));
				a.setFirmaTutorAccademico(rs.getBoolean("FirmaTutorAccademico"));
				a.setFirmaPdCD(rs.getBoolean("FirmaPdCD"));
				a.setID_Proposta(rs.getInt("ID_Proposta"));
				
				richieste.add(a);
			}
			return richieste;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	/**
	 * Query per restituire TUTTI i tirocini esterni in fase di valutazione
	 * 
	 * @author Simone Auriemma
	 * @return ArrayList<TirocinioEsterno>
	 */
	public ArrayList<TirocinioEsterno> doRetriveAll() {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			// query per la visualizzazione della pagina da parte dello studente
			// query per visualizzare le richieste in valutazione
			String inValutazione = "in approvazione";
			PreparedStatement ps = con.prepareStatement("select * from EVIM.TirocinioEsterno where status=?");
			ps.setString(1, inValutazione);
			ArrayList<TirocinioEsterno> richieste = new ArrayList<TirocinioEsterno>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TirocinioEsterno a = new TirocinioEsterno();
				a.setID_TirocinioEsterno(rs.getInt("ID_TirocinioEsterno"));
				a.setEMAIL(rs.getString("EMAIL"));
				a.setID_TutorAccademico(rs.getInt("ID_tutorAccademico"));
				a.setID_TutorAziendale(rs.getInt("ID_TurorAziendale"));
				a.setData(rs.getString("Data"));
				a.setOreTotali(rs.getInt("OreTotali"));
				a.setStatus(rs.getString("status"));
				a.setCFU(rs.getInt("CFU"));
				a.setFirmaAzienda(rs.getBoolean("FirmaAzienda"));
				a.setFirmaTutorAziendale(rs.getBoolean("FirmaTutorAziendale"));
				a.setFirmaTutorAccademico(rs.getBoolean("FirmaTutorAccademco"));
				a.setFirmaPdCD(rs.getBoolean("FirmaPdCD"));
				a.setID_Proposta(rs.getInt("ID_Proposta"));
				richieste.add(a);
			}
			return richieste;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	/**
	 * @author Antonio Giano
	 * @param id si tratta del campo, chiave primaria, id del tirocinio esterno
	 * @return restituisce un progetto formativo di tirocinio esterno
	 */
	public static PDFProgettoFormativo getProgettoFormativoEsterno(int id) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"select USER.NAME as NomeStudente,USER.SURNAME as CognomeStudente,USER.EMAIL as EmailStudente,TutorAccademico.Nome as NomeTutor,TutorAccademico.Cognome as CognomeTutor,TutorAccademico.email as EmailTutor,TutorAziendale.nome as NomeTutorAz,TutorAziendale.cognome as CognomeAz,TutorAziendale.email as EmailTutorAz,Proposta.Obiettivi as Obiettivi,Proposta.Attività as Attività,Proposta.Modalità as Modalita\r\n"
							+ "from TirocinioEsterno join evim.USER on TirocinioEsterno.EMAIL=USER.EMAIL\r\n"
							+ "join Proposta on TirocinioEsterno.ID_Proposta=Proposta.ID_Proposta\r\n"
							+ "join TutorAccademico on TutorAccademico.ID_TutorAccademico=TirocinioEsterno.ID_tutorAccademico\r\n"
							+ "join TutorAziendale on TutorAziendale.ID_TutorAziendale=TirocinioEsterno.ID_TirocinioEsterno\r\n"
							+ "where ID_TirocinioEsterno=?");
			ps.setInt(1, id);
			PDFProgettoFormativo pdf = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pdf = new PDFProgettoFormativo();
				pdf.setNomeStudente(rs.getString(1));
				pdf.setCognomeStudente(rs.getString(2));
				pdf.setEmailStudente(rs.getString(3));
				pdf.setNomeTutorAccademico(rs.getString(4));
				pdf.setCognomeTutorAccademico(rs.getString(5));
				pdf.setEmailTutorAccademico(rs.getString(6));
				pdf.setNomeTutorAziendale(rs.getString(7));
				pdf.setCognomeTutorAziendale(rs.getNString(8));
				pdf.setEmailTutorAziendale(rs.getString(9));
				pdf.setObiettivi(rs.getString(10));
				pdf.setAttivita(rs.getString(11));
				pdf.setModalita(rs.getString(12));
			}

			return pdf;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public int updateFirmaTrueTutorAccademico(boolean firma, int idTirocinio, int idTutorAccademico) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			String inValutazione = "in approvazione";
			PreparedStatement ps = con.prepareStatement(
					"update EVIM.TirocinioEsterno SET FirmaTutorAccademico=? where ID_tutorAccademico=? AND status=? AND ID_TirocinioEsterno=?");
			ps.setBoolean(1, firma);
			ps.setInt(2, idTutorAccademico);
			ps.setString(3, inValutazione);
			ps.setInt(4, idTirocinio);
			int rs = ps.executeUpdate();

			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public int updateFirmaFalseTutorAccademico(boolean firma, int idTirocinio, int idTutorAccademico) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			String inValutazione = "in approvazione";
			PreparedStatement ps = con.prepareStatement(
					"update EVIM.TirocinioEsterno SET FirmaTutorAccademico=?,status=? where ID_tutorAccademico=? AND status=? AND ID_TirocinioEsterno=?");
			ps.setBoolean(1, firma);
			ps.setString(2, "rifiutato");
			ps.setInt(3, idTutorAccademico);
			ps.setString(4, inValutazione);
			ps.setInt(5, idTirocinio);
			int rs = ps.executeUpdate();

			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public int updateFirmaTrueAziendale(boolean b, int idTirocinio, int idTutorAziendale) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {

			String inValutazione = "in approvazione";
			PreparedStatement ps = con.prepareStatement(
					"update EVIM.TirocinioEsterno SET FirmaTutorAziendale=? where ID_TutorAziendale=? AND status=? AND ID_TirocinioEsterno=?");
			ps.setBoolean(1, b);
			ps.setInt(2, idTutorAziendale);
			ps.setString(3, inValutazione);
			ps.setInt(4, idTirocinio);
			int rs = ps.executeUpdate();

			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public int updateFirmaFalseAziendale(boolean b, int idTirocinio, int id) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			String inValutazione = "in approvazione";
			PreparedStatement ps = con.prepareStatement(
					"update EVIM.TirocinioEsterno SET FirmaTutorAziendale=?,status=? where ID_TutorAziendale=? AND status=? AND ID_TirocinioEsterno=?");
			ps.setBoolean(1, b);
			ps.setString(2, "rifiutato");
			ps.setInt(3, id);
			ps.setString(4, inValutazione);
			ps.setInt(5, idTirocinio);
			int rs = ps.executeUpdate();

			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
}