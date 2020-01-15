package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.GestioneRichiesta.VisualizzaRichieste.TirocinioQueryEsternoTutorAz;
import controller.GestioneRichiesta.VisualizzaRichieste.TirocinioQueryInternoStudente;
import controller.GestioneRichiesta.VisualizzaRichieste.TirocinioQueryInternoTutorAcc;
import controller.GestioneRichiesta.VisualizzaRichieste.TirocinioQueryPdCD;
import controller.GestioneTirocinio.ListaTirocini.RegistroQuery;

public class TirocinioInternoDAO {

	/**
	 * Metodo per inserire un nuovo tirocinio interno
	 * 
	 * @author Emilio Schiavo
	 * @return boolean success/fail
	 */
	public static boolean doInsert(String email, int idTutAcc, String data, int oreTot, String status, int numCFU,
			int IDProp) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO `evim`.`TirocinioInterno` (`EMAIL`, `ID_tutorAccademico`, `Data`, `OreTotali`, `status`, `NumeroCFU`, `ID_Proposta`) VALUES (?, ?, ?, ?, ?, ?, ?);");
			ps.setString(1, email);
			ps.setInt(2, idTutAcc);
			ps.setString(3, data);
			ps.setInt(4, oreTot);
			ps.setString(5, status);
			ps.setInt(6, numCFU);
			ps.setInt(7, IDProp);

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
	 * Metodo per visualizzare le richieste effettuate effettuate dallo studente in
	 * fase di valutazione dato uno studente
	 * 
	 * @author Simone Auriemma
	 * @return ArrayList<TirocinioInterno>
	 */
	public ArrayList<TirocinioQueryInternoStudente> doRetriveAllByStudent(String EMAIL) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			// query per la visualizzazione della pagina da parte dello studente
			// query per visualizzare le richieste in valutazione
			String inValutazione = "in approvazione";
			String inSvolgimento = "in svolgimento";
			String rifiutato = "non approvato";
			PreparedStatement ps = con.prepareStatement(
					"select ID_TirocinioInterno,tiro.EMAIL, tutor.Nome,tutor.Cognome, Data, OreTotali, status, NumeroCFU, FirmaPdCD, FirmaTutorAccademico, ID_Proposta from TirocinioInterno AS tiro JOIN TutorAccademico as tutor ON tiro.ID_tutorAccademico = tutor.ID_TutorAccademico where tiro.EMAIL=? AND (status=? OR status=? OR status=?)");
			ps.setString(1, EMAIL);
			ps.setString(2, inValutazione);
			ps.setString(3, inSvolgimento);
			ps.setString(4, rifiutato);

			ArrayList<TirocinioQueryInternoStudente> richieste = new ArrayList<TirocinioQueryInternoStudente>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TirocinioQueryInternoStudente a = new TirocinioQueryInternoStudente();
				a.setID_TirocinioInterno(rs.getInt(1));
				a.setEMAIL(rs.getString(2));
				a.setNomeTutorAcc(rs.getString(3));
				a.setCognomeTutorAcc(rs.getString(4));
				a.setData(rs.getString("Data"));
				a.setOreTotali(rs.getInt(6));
				a.setStatus(rs.getString("status"));
				a.setNumeroCFU(rs.getInt(8));
				a.setFirmaPdCD(rs.getBoolean("FirmaPdCD"));
				a.setFirmaTutorAccademico(rs.getBoolean("FirmaTutorAccademico"));
				a.setID_Proposta(rs.getInt(11));
				richieste.add(a);
			}
			return richieste;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	/**
	 * Query che restituisce tutti i tirocini in fase di valutazione
	 * 
	 * @author Simone Auriemma
	 * @return ArrayList<TirocinioQueryInternoTutorAcc>
	 */
	public ArrayList<TirocinioQueryInternoTutorAcc> doRetriveAllByTutorAcc(int IDTutor) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			// query per la visualizzazione della pagina da parte dello studente
			// query per visualizzare le richieste in valutazione
			String inValutazione = "in approvazione";
			PreparedStatement ps = con.prepareStatement(
					"select ID_TirocinioInterno,tiro.EMAIL, User.NAME as nomeStud,User.SURNAME as cognomeStud, Data, OreTotali, status, NumeroCFU, FirmaPdCD, FirmaTutorAccademico, ID_Proposta from tirociniointerno AS tiro "
							+ "JOIN User ON tiro.EMAIL = User.EMAIL "
							+ "JOIN tutoraccademico on tutoraccademico.ID_TutorAccademico=tiro.ID_tutorAccademico "
							+ "where status=? AND tiro.ID_TutorAccademico=?");
			ps.setString(1, inValutazione);
			ps.setInt(2, IDTutor);
			ArrayList<TirocinioQueryInternoTutorAcc> richieste = new ArrayList<TirocinioQueryInternoTutorAcc>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TirocinioQueryInternoTutorAcc a = new TirocinioQueryInternoTutorAcc();
				a.setID_TirocinioInterno(rs.getInt(1));
				a.setEmail(rs.getString("EMAIL"));
				a.setNomeStud(rs.getString(3));
				a.setCognomeStud(rs.getString(4));
				a.setData(rs.getString("Data"));
				a.setOreTotali(rs.getInt("OreTotali"));
				a.setStatus(rs.getString("status"));
				a.setNumeroCFU(rs.getInt("NumeroCFU"));
				a.setFirmaPdCD(rs.getBoolean("FirmaPdCD"));
				a.setFirmaTutorAccademico(rs.getBoolean("FirmaTutorAccademico"));
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
	 * @param id si tratta il campo id,chiave primaria del tirocinio interno del
	 *           database
	 * @return un progetto formativo in pdf
	 */
	public static PDFProgettoFormativo getProgettoFormativoInterno(int id) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"select USER.NAME as NomeStudente,USER.SURNAME as CognomeStudente,USER.EMAIL as EmailStudente,USER.tipoCorso as CorsoStudente,USER.Telefono as TelefonoStudente,USER.Data_Nascita as DataNascitaStudente,USER.Luogo_Nascita as LuogoNascitaStudente,USER.Residente as ResidenteStudente,TutorAccademico.Nome as NomeTutor,TutorAccademico.Cognome as CognomeTutor, Proposta.Obiettivi as Obiettivi,Proposta.Attivita as Attivita,Proposta.Modalita as Modalita,Proposta.Competenze as Competenze ,TirocinioInterno.NumeroCFU as NumeroCFU,TirocinioInterno.OreTotali as OreTotali\n"
							+ "from evim.TirocinioInterno join evim.USER on TirocinioInterno.EMAIL=USER.EMAIL \n"
							+ "join evim.Proposta on TirocinioInterno.ID_Proposta=Proposta.ID_Proposta\n"
							+ "join evim.TutorAccademico on TutorAccademico.ID_TutorAccademico=TirocinioInterno.ID_tutorAccademico\n"
							+ "where ID_TirocinioInterno=?");

			ps.setInt(1, id);
			PDFProgettoFormativo pdf = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pdf = new PDFProgettoFormativo();

				// dati studenti
				pdf.setNomeStudente(rs.getString(1));
				pdf.setCognomeStudente(rs.getString(2));
				pdf.setEmailStudente(rs.getString(3));
				pdf.setCorsoLaurea(rs.getString(4));
				pdf.setTelefonoStudente(rs.getString(5));
				pdf.setDataNascitaStudente(rs.getString(6));
				pdf.setLuogoNascitaStudente(rs.getString(7));
				pdf.setResidenteStudente(rs.getString(8));

				// dati Tutor Accademico
				pdf.setNomeTutorAccademico(rs.getString(9));
				pdf.setCognomeTutorAccademico(rs.getString(10));

				// dati del progetto formativo
				pdf.setObiettivi(rs.getString(11));
				pdf.setAttivita(rs.getString(12));
				pdf.setModalita(rs.getString(13));
				pdf.setCompetenze(rs.getString(14));

				pdf.setTotCFU(rs.getInt(15));

				pdf.setTotOre(rs.getInt(16));

				// dati convenzione
				pdf.setDataConvenzione("");
				pdf.setReportorioConvenzione("");
			}

			return pdf;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * @author Simone Auriemma
	 * @return
	 */
	public ArrayList<TirocinioQueryPdCD> doRetriveAllValutazionePdCD() {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			String inValutazione = "in approvazione";
			PreparedStatement ps = con.prepareStatement(
					"select ID_TirocinioInterno,tiro.EMAIL, User.NAME as nomeStud,User.SURNAME as cognomeStud,TutorAccademico.Nome as nomeTutorAcc,TutorAccademico.Cognome as cognomeTutorAcc, Data, OreTotali, status, NumeroCFU, FirmaPdCD, FirmaTutorAccademico, FirmaTutorAccademico, ID_Proposta " + 
					"from TirocinioInterno AS tiro JOIN User ON tiro.EMAIL = User.EMAIL " + 
					"JOIN TutorAccademico on tiro.ID_TutorAccademico=TutorAccademico.ID_TutorAccademico "
					+ "WHERE status=? AND firmaTutorAccademico=true");
			ps.setString(1, inValutazione);
			ArrayList<TirocinioQueryPdCD> richieste = new ArrayList<TirocinioQueryPdCD>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TirocinioQueryPdCD a = new TirocinioQueryPdCD();
				a.setID_TirocinioInterno(rs.getInt(1));
				a.setEmail(rs.getString(2));
				a.setNomeStudente(rs.getString(3));
				a.setCognomeStudente(rs.getString(4));
				a.setNomeTutorAcc(rs.getString(5));
				a.setCognomeTutorAcc(rs.getString(6));
				a.setData(rs.getString(7));
				a.setOreTotali(rs.getInt(8));
				a.setStatus(rs.getString(9));
				a.setCFU(rs.getInt(10));
				a.setFirmaPdCD(rs.getBoolean(11));
				a.setFirmaTutorAccademico(rs.getBoolean(12));
				a.setFirmaTutorAziendale(rs.getBoolean(13));
				a.setID_Proposta(rs.getInt(14));
				richieste.add(a);
			}
			return richieste;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * Questo metodo inserisce la firma del tutor accademico nella tabella
	 * "tirocinio interno" inserendo "true"
	 * 
	 * @author Simone Auriemma
	 * @param firma
	 * @param idTirocinio
	 * @param idTutorAccademico
	 * @return int
	 */
	public int updateFirmaTrue(boolean firma, int idTirocinio, int idTutorAccademico) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			// query per la visualizzazione della pagina da parte dello studente
			// query per visualizzare le richieste in valutazione
			String inValutazione = "in approvazione";
			PreparedStatement ps = con.prepareStatement(
					"update EVIM.TirocinioInterno SET FirmaTutorAccademico=? where ID_tutorAccademico=? AND status=? AND ID_TirocinioInterno=?");
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

	/**
	 * Rifiuta la richiesta di uno studente
	 * 
	 * @author Simone Auriemma
	 * @param firma
	 * @param idTirocinio
	 * @param idTutorAccademico
	 * @return
	 */
	public int updateFirmaFalse(boolean firma, int idTirocinio, int idTutorAccademico) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			String inValutazione = "in approvazione";
			PreparedStatement ps = con.prepareStatement(
					"update EVIM.TirocinioInterno SET FirmaTutorAccademico=?,status=? where ID_tutorAccademico=? AND status=? AND ID_TirocinioInterno=?");
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

	public ArrayList<RegistroQuery> doRetriveTirocinioInSvolgimentoStudenteRegistro(String email) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"select distinct TirocinioInterno.ID_TirocinioInterno, ID_Registro,registro.FirmaTutorAccamico,TirocinioInterno.OreTotali,  Registro.FirmaResponsabile, TirocinioInterno.status, TirocinioInterno.numeroCFU, TirocinioInterno.EMAIL from TirocinioInterno \n"
							+ "join registro on registro.ID_Tirocinio= TirocinioInterno.ID_TirocinioInterno\n"
							+ "where tirociniointerno.EMAIL=? and tirociniointerno.status='in svolgimento' "); // controllare
			ps.setString(1, email);
			ArrayList<RegistroQuery> lista = new ArrayList<RegistroQuery>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				RegistroQuery a = new RegistroQuery();
				a.setID_Tirocinio(rs.getInt("ID_TirocinioInterno"));
				a.setFirmaResponsabile(rs.getBoolean("FirmaResponsabile"));
				a.setFirmaResponsabile(rs.getBoolean("FirmaTutorAccamico"));
				a.setStatus(rs.getString("status"));
				a.setNumeroCFU(rs.getInt("numeroCFU"));
				a.setOreTotali(rs.getInt("OreTotali"));
				a.setID_Registro(rs.getInt("ID_Registro"));
				a.setEmailStudente(rs.getString("TirocinioInterno.EMAIL"));
				lista.add(a);
			}
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}


	}

	/**
	 * @author Simone Auriemma
	 * @param firma
	 * @param idTirocinio
	 * @return
	 */
	public int updateFirmaPdCDTrue(boolean firma, int idTirocinio) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			String inSvolgimento = "in svolgimento";
			PreparedStatement ps = con.prepareStatement("update EVIM.TirocinioInterno " + "SET FirmaPdCD=?, status=?"
					+ "where FirmaTutorAccademico=true AND tirocinioInterno.ID_TirocinioInterno=?");
			ps.setBoolean(1, firma);
			ps.setString(2, inSvolgimento);
			ps.setInt(3, idTirocinio);
			int rs = ps.executeUpdate();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public int updateFirmaPdCDFalse(boolean firma, int idTirocinio) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			String inSvolgimento = "rifiutato";
			PreparedStatement ps = con.prepareStatement("update EVIM.TirocinioInterno " + "SET FirmaPdCD=?, status=?"
					+ "where FirmaTutorAccademico=true AND tirocinioesterno.ID_TirocinioEsterno=?");
			ps.setBoolean(1, firma);
			ps.setString(2, inSvolgimento);
			ps.setInt(3, idTirocinio);
			int rs = ps.executeUpdate();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public ArrayList<RegistroQuery> doRetriveTirocinioInSvolgimentoTutorAccRegistro(int id) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"select distinct registro.FirmaTutorAccamico,user.NAME, user.SURNAME, TirocinioInterno.ID_TirocinioInterno,tutoraccademico.nome,tutoraccademico.cognome, Registro.FirmaResponsabile, TirocinioInterno.status, TirocinioInterno.NumeroCFU,"
							+ "TirocinioInterno.OreTotali, Registro.ID_Registro ,TirocinioInterno.EMAIL "
							+ "from TirocinioInterno join Registro on registro.ID_Tirocinio= TirocinioInterno.ID_TirocinioInterno join"
							+ " tutoraccademico on tutoraccademico.ID_tutorAccademico= TirocinioInterno.ID_tutorAccademico join User on user.EMAIL=TirocinioInterno.EMAIL "
							+ "where "
							+ "TirocinioInterno.ID_tutorAccademico = ? AND tirociniointerno.status='in svolgimento' and registro.Tipo='interno'");
			ps.setInt(1, id);

			ArrayList<RegistroQuery> lista = new ArrayList<RegistroQuery>();
			ResultSet rs = ps.executeQuery();
//ww
			while (rs.next()) {
				RegistroQuery a = new RegistroQuery();
				a.setID_Tirocinio(rs.getInt("TirocinioInterno.ID_TirocinioInterno"));
				a.setFirmaResponsabile(rs.getBoolean("FirmaResponsabile"));
				a.setNome_responsabile(rs.getString("tutoraccademico.Nome"));
				a.setCognome_responsabile(rs.getString("tutoraccademico.Cognome"));
				a.setStatus(rs.getString("status"));
				a.setFirmaTutorAccademico(rs.getBoolean("FirmaTutorAccamico"));
				a.setNumeroCFU(rs.getInt("NumeroCFU"));
				a.setOreTotali(rs.getInt("OreTotali"));
				a.setID_Registro(rs.getInt("ID_Registro"));
				a.setEmailStudente(rs.getString("EMAIL"));
				a.setNomeStudente(rs.getString("user.NAME"));
				a.setCognomeStudente(rs.getString("user.SURNAME"));
				lista.add(a);
			}
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public ArrayList<RegistroQuery> doRetriveTirocinioInSvolgimentoPdcdRegistro() {
		
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			String inSvolgimento="in svolgimento";
			PreparedStatement ps = con.prepareStatement(
					"select user.NAME as n,user.SURNAME as s,registro.FirmaTutorAccamico,TirocinioInterno.ID_TirocinioInterno, Registro.FirmaResponsabile, tirociniointerno.status, tirociniointerno.NumeroCFU ,tirociniointerno.OreTotali, Registro.ID_Registro, tutoraccademico.Nome as nomeTutorAcc, tutoraccademico.Cognome as cognomeTutorAcc " + 
					"from tirociniointerno " + 
					"JOIN Registro on registro.ID_Tirocinio=tirocinioInterno.ID_TirocinioInterno " + 
					"JOIN TutorAccademico on tirociniointerno.ID_tutorAccademico=tutoraccademico.ID_TutorAccademico "
					+ "JOIN user on user.EMAIL=tirociniointerno.email " + 
					"where registro.tipo='interno'");
			
			ArrayList<RegistroQuery> lista = new ArrayList<RegistroQuery>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				RegistroQuery a = new RegistroQuery();
				a.setID_Tirocinio(rs.getInt("ID_TirocinioInterno"));
				a.setFirmaResponsabile(rs.getBoolean("FirmaResponsabile"));
				a.setCognomeStudente(rs.getString("s"));
				a.setNomeStudente(rs.getString("n"));
				a.setStatus(rs.getString("status"));
				a.setFirmaTutorAccademico(rs.getBoolean("FirmaTutorAccamico"));
				a.setNumeroCFU(rs.getInt("NumeroCFU"));
				a.setOreTotali(rs.getInt("OreTotali"));
				a.setID_Registro(rs.getInt("ID_Registro"));
				a.setNome_responsabile(rs.getString("nomeTutorAcc"));
				a.setCognome_responsabile(rs.getString("cognomeTutorAcc"));
				lista.add(a);
			}
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	
	/**
	 * @author Antonio Giano
	 * @param email
	 * @return
	 */
	public static boolean getStatusTirocinioInterno(String email) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			String inValutazione = "in approvazione";
			PreparedStatement ps = con.prepareStatement(
					"select * \n" + 
					"from evim.TirocinioInterno\n" + 
					"where status='in approvazione' or status='in svolgimento' and EMAIL=?;");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
