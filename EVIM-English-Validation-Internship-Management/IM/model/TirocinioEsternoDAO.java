package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.GestioneRichiesta.VisualizzaRichieste.TirocinioQueryEsternoStudente;
import controller.GestioneRichiesta.VisualizzaRichieste.TirocinioQueryEsternoTutorAcc;
import controller.GestioneRichiesta.VisualizzaRichieste.TirocinioQueryEsternoTutorAz;
import controller.GestioneRichiesta.VisualizzaRichieste.TirocinioQueryPdCD;
import controller.GestioneTirocinio.ListaTirocini.RegistroQuery;

public class TirocinioEsternoDAO {

	/**
	 * @author Emilio Schiavo (del metodo doInsert) il metodo inserisce un nuovo
	 *         tirocinio e restituisce true se la query va a buon fine, false
	 *         altrimenti
	 */
	public static boolean doInsert(int idTutAcc, int idTutAz, int oreTotali, int cfu, int idProposta, String email,
			String data, String status) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			String q = "INSERT INTO `evim`.`tirocinioesterno`(`EMAIL`, `ID_TutorAccademico`, `ID_TutorAziendale`, `Data`, `OreTotali`, `status`, `CFU`, `ID_Proposta`) VALUES ("
					+ "'" + email + "','" + idTutAcc + "','" + idTutAz + "','" + data + "','" + oreTotali + "','"
					+ status + "','" + cfu + "','" + idProposta + "');";

			// INSERT INTO `evim`.`tirocinioesterno` (`EMAIL`, `ID_TutorAccademico`,
			// `ID_TutorAziendale`, `Data`, `OreTotali`, `status`, `CFU`, `ID_Proposta`)
			// VALUES ('e.schiavo8@studenti.unisa.it', '1', '1', '22/12/2019', '300', 'IN
			// Approvazione', '11', '1');
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
	public ArrayList<TirocinioQueryEsternoStudente> doRetriveAllByStudent(String EMAIL) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			// query per la visualizzazione della pagina da parte dello studente
			// query per visualizzare le richieste in valutazione
			String inValutazione = "in approvazione";
			String inSvolgimento = "in svolgimento";
			String rifiutato = "non approvato";

			PreparedStatement ps = con.prepareStatement(
					"select ID_TirocinioEsterno,tiro.EMAIL, tutorAcc.Nome as nomeTutorAcc,tutorAcc.Cognome as cognomeTutorAcc,tutorAz.Nome as nomeTutorAz, "
							+ "tutorAz.Cognome as cognomeTutorAz, Data, OreTotali, status, CFU, FirmaAzienda, FirmaTutorAziendale, FirmaTutorAccademico, FirmaPdCD, ID_Proposta "
							+ "from TirocinioEsterno AS tiro "
							+ "JOIN TutorAccademico as tutorAcc ON tiro.ID_tutorAccademico = tutorAcc.ID_TutorAccademico "
							+ "JOIN TutorAziendale as tutorAz ON tiro.ID_TutorAziendale = tutorAz.ID_TutorAziendale "
							+ "where tiro.EMAIL=? AND (status=? OR status=? OR status=?)");
			ps.setString(1, EMAIL);
			ps.setString(2, inValutazione);
			ps.setString(3, inSvolgimento);
			ps.setString(4, rifiutato);
			ArrayList<TirocinioQueryEsternoStudente> richieste = new ArrayList<TirocinioQueryEsternoStudente>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TirocinioQueryEsternoStudente a = new TirocinioQueryEsternoStudente();
				a.setID_TirocinioEsterno(rs.getInt(1));
				a.setEmail(rs.getString(2));
				a.setNomeTutorAcc(rs.getString(3));
				a.setCognomeTutorAcc(rs.getString(4));
				a.setNomeTutorAz(rs.getString(5));
				a.setCognomeTutorAz(rs.getString(6));
				a.setData(rs.getString(7));
				a.setOreTotali(rs.getInt(8));
				a.setStatus(rs.getString(9));
				a.setCFU(rs.getInt(10));
				a.setFirmaAzienda(rs.getBoolean(11));
				a.setFirmaTutorAziendale(rs.getBoolean(12));
				a.setFirmaTutorAccademico(rs.getBoolean(13));
				a.setFirmaPdCD(rs.getBoolean(14));
				a.setID_Proposta(rs.getInt(15));

				richieste.add(a);
			}
			return richieste;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	/**
	 * @author Simone Auriemma
	 * @param EMAIL
	 * @return ArrayList<TirocinioEsterno>
	 */
	public ArrayList<TirocinioEsterno> doRetriveAllByStudentRegistro(String EMAIL) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			// query per la visualizzazione della pagina da parte dello studente
			// query per visualizzare le richieste in valutazione
			String inValutazione = "in svolgimento";
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
	 * 
	 * @author Simone Auriemma
	 * @return ArryList<TirocinioEsterno>
	 */
	public ArrayList<TirocinioQueryPdCD> doRetriveAllValutazionePdCD() {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			String inValutazione = "in approvazione";
			PreparedStatement ps = con.prepareStatement(
					"select ID_TirocinioEsterno,tiro.EMAIL, User.NAME as nomeStud,User.SURNAME as cognomeStud,TutorAccademico.Nome as nomeTutorAcc,TutorAccademico.Cognome as cognomeTutorAcc, Data, OreTotali, status, CFU, FirmaPdCD, FirmaTutorAccademico, FirmaTutorAccademico, ID_Proposta, firmaAzienda, tutorAz.Nome as nomeTutorAz, tutorAz.Cognome as cognomeTutorAz "
							+ "from TirocinioEsterno AS tiro JOIN User ON tiro.EMAIL = User.EMAIL "
							+ "JOIN TutorAccademico on tiro.ID_TutorAccademico=TutorAccademico.ID_TutorAccademico "
							+ "JOIN TutorAziendale as tutorAz ON tiro.ID_TutorAziendale = tutorAz.ID_TutorAziendale "
							+ "JOIN Azienda ON Azienda.ID_Azienda = tutorAz.ID_Azienda "
							+ "WHERE status='in approvazione'");
			ArrayList<TirocinioQueryPdCD> richieste = new ArrayList<TirocinioQueryPdCD>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TirocinioQueryPdCD a = new TirocinioQueryPdCD();
				a.setID_TirocinioEsterno(rs.getInt(1));
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
				a.setFirmaAzienda(rs.getBoolean(15));
				a.setNomeTutorAziedale(rs.getString(16));
				a.setCognomeTutorAziendale(rs.getString(17));
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
	 * @return ArrayList<TirocinioQueryEsternoTutorAcc>
	 */
	public ArrayList<TirocinioQueryEsternoTutorAcc> doRetriveAllByTutorAcc(int IDTutor) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			// query per la visualizzazione della pagina da parte dello studente
			// query per visualizzare le richieste in valutazione
			String inValutazione = "in approvazione";
			PreparedStatement ps = con.prepareStatement(
					"select ID_TirocinioEsterno,tiro.EMAIL, User.NAME as nomeStud,User.SURNAME as cognomeStud,tutorAz.Nome as nomeTutorAz,tutorAz.Cognome as cognomeTutorAz, Data, OreTotali, status, CFU, FirmaPdCD, FirmaTutorAccademico, ID_Proposta, FirmaAzienda, FirmaTutorAziendale from TirocinioEsterno AS tiro JOIN User ON tiro.EMAIL = User.EMAIL JOIN TutorAziendale as tutorAz ON tiro.ID_TutorAziendale = tutorAz.ID_TutorAziendale where status=? AND tiro.ID_TutorAccademico=?");
			ps.setString(1, inValutazione);
			ps.setInt(2, IDTutor);
			ArrayList<TirocinioQueryEsternoTutorAcc> richieste = new ArrayList<TirocinioQueryEsternoTutorAcc>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TirocinioQueryEsternoTutorAcc a = new TirocinioQueryEsternoTutorAcc();
				a.setID_TirocinioEsterno(rs.getInt(1));
				a.setEmail(rs.getString(2));
				a.setNomeStudente(rs.getString(3));
				a.setCognomeStudente(rs.getString(4));
				a.setNomeTutorAziendale(rs.getString(5));
				a.setCognomeTutorAziendale(rs.getString(6));
				a.setData(rs.getString(7));
				a.setOreTotali(rs.getInt(8));
				a.setStatus(rs.getString(9));
				a.setCFU(rs.getInt(10));
				a.setFirmaPdCD(rs.getBoolean(11));
				a.setFirmaTutorAccademico(rs.getBoolean(12));
				a.setID_Proposta(rs.getInt(13));
				a.setFirmaAzienda(rs.getBoolean(14));
				a.setFirmaTutorAziendale(rs.getBoolean(15));
				richieste.add(a);
			}
			return richieste;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	/**
	 * @author Simona Grieco
	 */
	// valutazione Azienda
	public int updateFirmaTrueAzienda(boolean b, int idTirocinio, int idAzienda) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {

			PreparedStatement ps = con.prepareStatement("update EVIM.TirocinioEsterno "
					+ "JOIN TutorAziendale on TirocinioEsterno.ID_TutorAziendale=tutoraziendale.ID_TutorAziendale "
					+ "JOIN Azienda ON TutorAziendale.ID_Azienda=Azienda.ID_Azienda " + "SET FirmaAzienda=? "
					+ "where Azienda.ID_Azienda=? AND TirocinioEsterno.ID_TirocinioEsterno=? ");
			ps.setBoolean(1, b);
			ps.setInt(2, idAzienda);
			ps.setInt(3, idTirocinio);
			int rs = ps.executeUpdate();
			return rs;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * @author Simona Grieco
	 */
	public int updateFirmaFalseAzienda(boolean b, int idTirocinio, int id) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {

			PreparedStatement ps = con.prepareStatement("update EVIM.TirocinioEsterno "
					+ "JOIN TutorAziendale on TirocinioEsterno.ID_TutorAziendale=tutoraziendale.ID_TutorAziendale "
					+ "JOIN Azienda ON TutorAziendale.ID_Azienda=Azienda.ID_Azienda " + "SET FirmaAzienda=?, status=? "
					+ "where Azienda.ID_Azienda=?  AND TirocinioEsterno.ID_TirocinioEsterno=? ");
			ps.setBoolean(1, b);
			ps.setString(2, "rifiutato");
			ps.setInt(2, id);
			ps.setInt(3, idTirocinio);
			int rs = ps.executeUpdate();

			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
	// fine valutazione azienda

	/**
	 * @author Antonio Giano
	 * @param id si tratta del campo, chiave primaria, id del tirocinio esterno
	 * @return restituisce un progetto formativo di tirocinio esterno
	 */
	public static PDFProgettoFormativo getProgettoFormativoEsterno(int id) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("select USER.NAME as NomeStudente,USER.SURNAME as CognomeStudente,USER.EMAIL \n"
							+ "as EmailStudente,USER.tipoCorso as CorsoStudente,USER.Telefono as TelefonoStudente,USER.Data_Nascita as DataNascitaStudente,\n"
							+ "USER.Luogo_Nascita as LuogoNascitaStudente,USER.Residente as ResidenteStudente,TutorAccademico.Nome as NomeTutor,\n"
							+ "TutorAccademico.cognome as CognomeTut,TutorAziendale.nome as NomeTutorAz,\n"
							+ "TutorAziendale.cognome as CognomeAz,TutorAziendale.email as EmailTutorAz,TutorAziendale.Telefono as TelefonoTutAz,\n"
							+ "Proposta.Obiettivi as Obiettivi,Proposta.Attivita as Attivita,Proposta.Modalita as Modalita,Proposta.Competenze as Competenze,\n"
							+ "TirocinioEsterno.OreTotali as Oretotal,TirocinioEsterno.CFU as CFU,Azienda.nome as NomeAzienda, Azienda.Indirizzo as IndirizzoAzienda,\n"
							+ "Azienda.CF as CodiceFiscaleAzienda,referente_aziendale.nome as NomeReferente,referente_aziendale.cognome as CognomeReferente,\n"
							+ "referente_aziendale.ruolo as RuoloReferente,referente_aziendale.luogo_nascita as LuogoNascitaReferente,\n"
							+ "referente_aziendale.data_nascita as DataNascitaReferente,Azienda.codice_ateco as CodiceAtecoAzienda,\n"
							+ "Azienda.numero_dipendenti as NumeroDipendentiAzienda,Azienda.email as EmailAzienda,\n"
							+ "convenzione.DataConvezione as DataConvenzione, convenzione.Repertorio as RepertorioConvezione\n"
							+ "from TirocinioEsterno join evim.USER on TirocinioEsterno.EMAIL=USER.EMAIL\n"
							+ "join evim.Proposta on TirocinioEsterno.ID_Proposta=Proposta.ID_Proposta\n"
							+ "join evim.TutorAccademico on TutorAccademico.ID_TutorAccademico=TirocinioEsterno.ID_tutorAccademico\n"
							+ "join evim.TutorAziendale on TutorAziendale.ID_TutorAziendale=TirocinioEsterno.ID_TirocinioEsterno\n"
							+ "join evim.Azienda on Azienda.ID_Azienda=TutorAziendale.ID_Azienda\n"
							+ "join evim.referente_aziendale on referente_aziendale.CF=Azienda.CF_Referente\n"
							+ "join evim.convenzione on Azienda.ID_Convenzione=convenzione.ID\n"
							+ "where ID_TirocinioEsterno=?;");

			ps.setInt(1, id);
			PDFProgettoFormativo pdf = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pdf = new PDFProgettoFormativo();
				// dati studente
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

				// dati Tutor Aziendale
				pdf.setNomeTutorAziendale(rs.getString(11));
				pdf.setCognomeTutorAziendale(rs.getNString(12));
				pdf.setEmailTutorAziendale(rs.getString(13));
				pdf.setTelefonoAziendale(rs.getString(14));

				// dati progetto formativo
				pdf.setObiettivi(rs.getString(15));
				pdf.setAttivita(rs.getString(16));
				pdf.setModalita(rs.getString(17));
				pdf.setCompetenze(rs.getString(18));

				pdf.setTotOre(rs.getInt(19));

				pdf.setTotCFU(rs.getInt(20));

				// dati azienda e referente aziendale
				pdf.setNomeDenominazione(rs.getString(21));
				pdf.setSedeLegale(rs.getString(22));
				pdf.setCodiceFiscale(rs.getString(23));
				pdf.setNomeReferenteAziendale(rs.getString(24));
				pdf.setCognomeReferenteAziendale(rs.getString(25));
				pdf.setRuoloReferenteAziendale(rs.getString(26));
				pdf.setNatoReferenteAziendale(rs.getString(27));
				pdf.setDataReferenteAziendale(rs.getString(28));
				pdf.setCodiceATECO(rs.getString(29));
				pdf.setNumeroDipendenti(rs.getString(30));
				pdf.setIndirizzoEmail(rs.getString(31));

				// dati convenzione
				pdf.setDataConvenzione(rs.getString(32));
				pdf.setReportorioConvenzione(rs.getString(33));

			}

			return pdf;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * @author Simone Auriemma
	 * @param firma
	 * @param idTirocinio
	 * @param idTutorAccademico
	 * @return int
	 */
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

	/**
	 * @author Simone Auriemma
	 * @param firma
	 * @param idTirocinio
	 * @return
	 */
	public int updateFirmaPdCDTrue(boolean firma, int idTirocinio) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			String inSvolgimento = "in svolgimento";
			PreparedStatement ps = con.prepareStatement("update EVIM.TirocinioEsterno " + "SET FirmaPdCD=?, status=?"
					+ "where FirmaAzienda=true AND FirmaTutorAziendale=true AND FirmaTutorAccademico=true AND tirocinioesterno.ID_TirocinioEsterno=?");
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
			PreparedStatement ps = con.prepareStatement("update EVIM.TirocinioEsterno " + "SET FirmaPdCD=?, status=?"
					+ "where FirmaAzienda=true AND FirmaTutorAziendale=true AND FirmaTutorAccademico=true AND tirocinioesterno.ID_TirocinioEsterno=?");
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

	/**
	 * @author Simone Auriemma
	 * @param firma
	 * @param idTirocinio
	 * @param idTutorAccademico
	 * @return int
	 */
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

	/**
	 * @author Simone Auriemma
	 * @param b
	 * @param idTirocinio
	 * @param idTutorAziendale
	 * @return
	 */
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

	/**
	 * @author Simone Auriemma
	 * @param b
	 * @param idTirocinio
	 * @param id
	 * @return
	 */
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

	/**
	 * @author Simone Auriemma
	 * @param EMAIL
	 * @return ArrayList<TirocinioEsterno>
	 */
	public ArrayList<TirocinioEsterno> doRetriveTirocinioInSvolgimentoStudente(String EMAIL) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			String inSvolgimento = "in svolgimento";
			PreparedStatement ps = con
					.prepareStatement(" select * from EVIM.TirocinioEsterno where EMAIL=? AND status=?");
			ps.setString(1, EMAIL);
			ps.setString(2, inSvolgimento);
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
			}
			return richieste;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public ArrayList<RegistroQuery> doRetriveTirocinioInSvolgimentoStudenteRegistro(String email) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {

			PreparedStatement ps = con.prepareStatement( // questa query è ok
					"select  tutoraziendale.Nome,tutoraziendale.Cognome,registro.Status,TirocinioEsterno.ID_TirocinioEsterno,Registro.FirmaTutorAccamico ,Registro.FirmaResponsabile, TirocinioEsterno.status, "
							+ "TirocinioEsterno.OreTotali, Registro.ID_Registro, Registro.OreRaggiunte, "
							+ "TirocinioEsterno.CFU from tirocinioesterno \n"
							+ "join Registro on registro.ID_Tirocinio= TirocinioEsterno.ID_TirocinioEsterno\n "
							+ "join tutoraziendale "
							+ "on tutoraziendale.ID_TutorAziendale = TirocinioEsterno.ID_TutorAziendale "
							+ "where tirocinioesterno.EMAIL=? and tirocinioesterno.status='in svolgimento' and registro.tipo='esterno';");
			ps.setString(1, email);

			ArrayList<RegistroQuery> lista = new ArrayList<RegistroQuery>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				RegistroQuery a = new RegistroQuery();
				int idTir = rs.getInt("ID_TirocinioEsterno");
				a.setID_Tirocinio(idTir);
				a.setFirmaResponsabile(rs.getBoolean("FirmaResponsabile"));
				a.setStatus(rs.getString("TirocinioEsterno.status"));
				a.setNumeroCFU(rs.getInt("CFU"));
				a.setFirmaTutorAccademico(rs.getBoolean("FirmaTutorAccamico"));
				int oretotali = rs.getInt("OreTotali");
				int oreraggiunte = rs.getInt("OreRaggiunte");
				a.setOreTotali(oretotali);
				a.setID_Registro(rs.getInt("ID_Registro"));
				a.setOreRaggiunte(oreraggiunte);
				a.setNome_responsabile(rs.getString("tutoraccademico.Nome"));
				a.setCognome_responsabile(rs.getString("tutoraccademico.Cognome"));
				String status_registro = rs.getString("registro.Status");
				a.setRegistro_status(status_registro);

				if (status_registro.contentEquals("completato")) {
					String query = "select relazione.ID_Relazione from relazione join TirocinioEsterno on TirocinioEsterno.ID_TutorAziendale= relazione.ID_TutorAziendale "
							+ "where TirocinioEsterno.ID_TirocinioEsterno=? and relazione.email=?"; // aggiustare
					ps = con.prepareStatement(query);
					ps.setInt(1, idTir);
					ps.setString(2, email);
					ResultSet rsdue = ps.executeQuery();

					if (rsdue.next()) {
						a.setID_Relazione(rsdue.getInt(1));
					}

					String querydue = "select Questionario_s.ID_Questionario from questionario_s "
							+ "where questionario_s.email=?";
					ps = con.prepareStatement(querydue);

					ps.setString(1, email);
					ResultSet rstre = ps.executeQuery();
					;
					if (rstre.next()) {
						int idq = rstre.getInt(1);
						a.setID_Questionario(idq);
						System.out.println(idq);
					}

				}

				lista.add(a);
			}

			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public ArrayList<RegistroQuery> doRetriveTirocinioInSvolgimentoTutorAccRegistro(int id) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"select distinct tutoraccademico.Nome,tutoraccademico.Cognome,registro.Status,TirocinioEsterno.ID_TirocinioEsterno,Registro.FirmaTutorAccamico ,Registro.FirmaResponsabile, TirocinioEsterno.status, "
							+ "TirocinioEsterno.OreTotali, Registro.ID_Registro, Registro.OreRaggiunte, "
							+ "TirocinioEsterno.CFU from tirocinioesterno \n"
							+ "join Registro on registro.ID_Tirocinio= TirocinioEsterno.ID_TirocinioEsterno\n "
							+ "join tutoraccademico "
							+ "on tutoraccademico.ID_TutorAccademico = TirocinioEsterno.ID_TutorAccademico "
							+ "where TirocinioEsterno.ID_Tutoraccademico = ? and tirocinioesterno.status='in svolgimento' and registro.tipo='esterno';");
			ps.setInt(1, id);

			ArrayList<RegistroQuery> lista = new ArrayList<RegistroQuery>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				RegistroQuery a = new RegistroQuery();
				int idTir = rs.getInt("ID_TirocinioEsterno");
				a.setID_Tirocinio(idTir);
				a.setFirmaResponsabile(rs.getBoolean("FirmaResponsabile"));
				a.setStatus(rs.getString("TirocinioEsterno.status"));
				a.setNumeroCFU(rs.getInt("CFU"));
				a.setOreTotali(rs.getInt("OreTotali"));
				a.setID_Registro(rs.getInt("ID_Registro"));
				a.setNome_responsabile(rs.getString("tutoraccademico.Nome"));
				a.setCognome_responsabile(rs.getString("tutoraccademico.Cognome"));
				String status_registro = rs.getString("registro.Status");
				a.setRegistro_status(status_registro);
				if (status_registro.contentEquals("completato")) {
					String query = "select relazione.ID_Relazione from relazione join TirocinioEsterno on TirocinioEsterno.ID_TutorAccademico= relazione.ID_TutorAccademico "
							+ "where TirocinioEsterno.ID_TirocinioEsterno=? and TirocinioEsterno.ID_TutorAccademico=?"; // aggiustare
					ps = con.prepareStatement(query);
					ps.setInt(1, idTir);
					ps.setInt(2, id);
					ResultSet rsdue = ps.executeQuery();

					if (rsdue.next()) {
						a.setID_Relazione(rsdue.getInt(1));
					}

				}
//
				lista.add(a);
			}

			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
//
	}

	public ArrayList<RegistroQuery> doRetriveTirocinioInSvolgimentoTutorAzRegistro(int id) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"select TirocinioEsterno.ID_TirocinioEsterno, Registro.FirmaResponsabile, TirocinioEsterno.status, TirocinioEsterno.CFU,"
							+ "TirocinioEsterno.OreTotali, Registro.ID_Registro "
							+ "from TirocinioEsterno, Registro, turoraziendale "
							+ "where TirocinioEsterno.ID_TirocinioEsterno = Registro.ID_Tirocinio AND "
							+ "TirocinioEsterno.ID_TutorAziendale = ? AND TirocinioEsterno.status='in svolgimento'");
			ps.setInt(1, id);

			ArrayList<RegistroQuery> lista = new ArrayList<RegistroQuery>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				RegistroQuery a = new RegistroQuery();
				a.setID_Tirocinio(rs.getInt("ID_TirocinioEsterno"));
				// a.setFirmaResponsabile(rs.getBoolean("FirmaResponsabile"));
				a.setStatus(rs.getString("status"));
				a.setNumeroCFU(rs.getInt("CFU"));
				a.setOreTotali(rs.getInt("OreTotali"));
				a.setID_Registro(rs.getInt("ID_Registro"));
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
			PreparedStatement ps = con.prepareStatement(
					"select TirocinioEsterno.ID_TirocinioEsterno, Registro.FirmaResponsabile, TirocinioEsterno.status, TirocinioEsterno.CFU,TirocinioEsterno.OreTotali, Registro.ID_Registro, tutoraziendale.Nome as nomeTutorAz, tutoraziendale.Cognome as cognomeTutorAz "
							+ "from TirocinioEsterno "
							+ "JOIN Registro on tirocinioesterno.ID_TirocinioEsterno=registro.ID_Tirocinio "
							+ "JOIN TutorAziendale on tirocinioesterno.ID_TutorAziendale=tutoraziendale.ID_TutorAziendale "
							+ "where TirocinioEsterno.status='in svolgimento'");

			ArrayList<RegistroQuery> lista = new ArrayList<RegistroQuery>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				RegistroQuery a = new RegistroQuery();
				a.setID_Tirocinio(rs.getInt("ID_TirocinioEsterno"));
				a.setFirmaResponsabile(rs.getBoolean("FirmaResponsabile"));
				a.setStatus(rs.getString("status"));
				a.setNumeroCFU(rs.getInt("CFU"));
				a.setOreTotali(rs.getInt("OreTotali"));
				a.setID_Registro(rs.getInt("ID_Registro"));
				a.setNome_responsabile(rs.getString("nomeTutorAz"));
				a.setCognome_responsabile(rs.getString("cognomeTutorAz"));
				lista.add(a);
			}
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public ArrayList<TirocinioQueryEsternoTutorAz> doRetriveAllByTutorAz(int id) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			String inValutazione = "in approvazione";
			PreparedStatement ps = con.prepareStatement(
					"select ID_TirocinioEsterno,tiro.EMAIL, User.NAME as nomeStud,User.SURNAME as cognomeStud,TutorAccademico.Nome as nomeTutorAcc,TutorAccademico.Cognome as cognomeTutorAcc, Data, OreTotali, status, CFU, FirmaPdCD, FirmaTutorAccademico, FirmaTutorAccademico, ID_Proposta, FirmaAzienda "
							+ "from TirocinioEsterno AS tiro " + "JOIN User ON tiro.EMAIL = User.EMAIL "
							+ "JOIN TutorAccademico on tiro.ID_TutorAccademico=TutorAccademico.ID_TutorAccademico "
							+ "JOIN TutorAziendale as tutorAz ON tiro.ID_TutorAziendale = tutorAz.ID_TutorAziendale "
							+ "where status=? AND tiro.ID_TutorAziendale=?");

			ps.setString(1, inValutazione);
			ps.setInt(2, id);

			ArrayList<TirocinioQueryEsternoTutorAz> richieste = new ArrayList<TirocinioQueryEsternoTutorAz>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TirocinioQueryEsternoTutorAz a = new TirocinioQueryEsternoTutorAz();
				a.setID_TirocinioEsterno(rs.getInt(1));
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
				a.setFirmaAzienda(rs.getBoolean(15));
				richieste.add(a);
			}
			return richieste;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public ArrayList<TirocinioQueryEsternoTutorAz> doRetriveAllByAzienda(int id) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			String inValutazione = "in approvazione";
			String inSvolgimento = "in svolgimento";

			PreparedStatement ps = con.prepareStatement(
					"select ID_TirocinioEsterno,tiro.EMAIL, User.NAME as nomeStud,User.SURNAME as cognomeStud,TutorAccademico.Nome as nomeTutorAcc,TutorAccademico.Cognome as cognomeTutorAcc, Data, OreTotali, status, CFU, FirmaPdCD, FirmaTutorAccademico, FirmaTutorAccademico, ID_Proposta, firmaAzienda "
							+ "from TirocinioEsterno AS tiro JOIN User ON tiro.EMAIL = User.EMAIL "
							+ "JOIN TutorAccademico on tiro.ID_TutorAccademico=TutorAccademico.ID_TutorAccademico "
							+ "JOIN TutorAziendale as tutorAz ON tiro.ID_TutorAziendale = tutorAz.ID_TutorAziendale "
							+ "JOIN Azienda ON Azienda.ID_Azienda = tutorAz.ID_Azienda "
							+ "where (status=? OR status=?) AND Azienda.ID_Azienda=?");

			ps.setString(1, inValutazione);
			ps.setString(2, inSvolgimento);
			ps.setInt(3, id);

			ArrayList<TirocinioQueryEsternoTutorAz> richieste = new ArrayList<TirocinioQueryEsternoTutorAz>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TirocinioQueryEsternoTutorAz a = new TirocinioQueryEsternoTutorAz();
				a.setID_TirocinioEsterno(rs.getInt(1));
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
				a.setFirmaAzienda(rs.getBoolean(15));
				richieste.add(a);
			}
			return richieste;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
