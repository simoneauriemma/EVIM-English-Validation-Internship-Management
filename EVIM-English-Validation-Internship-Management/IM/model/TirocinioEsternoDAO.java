package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.GestioneRichiesta.VisualizzaRichieste.TirocinioQueryEsternoStudente;
import controller.GestioneRichiesta.VisualizzaRichieste.TirocinioQueryEsternoTutorAcc;
import controller.GestioneRichiesta.VisualizzaRichieste.TirocinioQueryEsternoTutorAz;
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
			PreparedStatement ps = con.prepareStatement(
					"select ID_TirocinioEsterno,tiro.EMAIL, tutorAcc.Nome as nomeTutorAcc,tutorAcc.Cognome as cognomeTutorAcc,tutorAz.Nome as nomeTutorAz, "+
					"tutorAz.Cognome as cognomeTutorAz, Data, OreTotali, status, CFU, FirmaAzienda, FirmaTutorAziendale, FirmaTutorAccademico, FirmaPdCD, ID_Proposta "+
					"from TirocinioEsterno AS tiro "+
					"JOIN TutorAccademico as tutorAcc ON tiro.ID_tutorAccademico = tutorAcc.ID_TutorAccademico "+
					"JOIN TutorAziendale as tutorAz ON tiro.ID_TutorAziendale = tutorAz.ID_TutorAziendale "+
					"where tiro.EMAIL=? AND status=?");
			ps.setString(1, EMAIL);
			ps.setString(2, inValutazione);

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
	public ArrayList<TirocinioEsterno> doRetriveAllValutazionePdCD() {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			String inValutazione = "in approvazione";
			PreparedStatement ps = con.prepareStatement(
					" select * from EVIM.TirocinioEsterno where status=? AND FirmaTutorAccademico=true AND FirmaAzienda=true AND FirmaTutorAziendale=true");
			ps.setString(1, inValutazione);
			ArrayList<TirocinioEsterno> richieste = new ArrayList<TirocinioEsterno>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TirocinioEsterno a = new TirocinioEsterno();
				a.setID_TirocinioEsterno(rs.getInt("ID_tirocinioInterno"));
				a.setID_TutorAccademico(rs.getInt("ID_tutorAccademico"));
				a.setData(rs.getString("Data"));
				a.setStatus(rs.getString("status"));
				a.setFirmaTutorAccademico(rs.getBoolean("FirmaTutorAccademico"));
				a.setFirmaPdCD(rs.getBoolean("FirmaPdCD"));
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
					"select ID_TirocinioEsterno,tiro.EMAIL, User.NAME as nomeStud,User.SURNAME as cognomeStud,tutorAz.Nome as nomeTutorAz,tutorAz.Cognome as cognomeTutorAz, Data, OreTotali, status, CFU, FirmaPdCD, FirmaTutorAccademico, ID_Proposta from TirocinioEsterno AS tiro JOIN User ON tiro.EMAIL = User.EMAIL JOIN TutorAziendale as tutorAz ON tiro.ID_TutorAziendale = tutorAz.ID_TutorAziendale where status=? AND tiro.ID_TutorAccademico=?");
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
				a.setNomeTutorAz(rs.getString(5));
				a.setCognomeTutorAz(rs.getString(6));
				a.setData(rs.getString(7));
				a.setOreTotali(rs.getInt(8));
				a.setStatus(rs.getString(9));
				a.setCFU(rs.getInt(10));
				a.setFirmaPdCD(rs.getBoolean(11));
				a.setFirmaTutorAccademico(rs.getBoolean(12));
				a.setID_Proposta(rs.getInt(13));
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
					"select USER.NAME as NomeStudente,USER.SURNAME as CognomeStudente,USER.EMAIL as EmailStudente,TutorAccademico.Nome as NomeTutor,TutorAccademico.Cognome as CognomeTutor,TutorAccademico.email as EmailTutor,TutorAziendale.nome as NomeTutorAz,TutorAziendale.cognome as CognomeAz,TutorAziendale.email as EmailTutorAz,Proposta.Obiettivi as Obiettivi,Proposta.Attivita as Attivita,Proposta.Modalita as Modalita,Proposta.Competenze as Competenze,TirocinioEsterno.OreTotali as Oretotal,TirocinioEsterno.CFU as CFU \n"
							+ "from TirocinioEsterno join evim.USER on TirocinioEsterno.EMAIL=USER.EMAIL\n"
							+ "join Proposta on TirocinioEsterno.ID_Proposta=Proposta.ID_Proposta\n"
							+ "join TutorAccademico on TutorAccademico.ID_TutorAccademico=TirocinioEsterno.ID_tutorAccademico\n"
							+ "join TutorAziendale on TutorAziendale.ID_TutorAziendale=TirocinioEsterno.ID_TirocinioEsterno\n"
							+ "where ID_TirocinioEsterno=?;");
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
				pdf.setCompetenze(rs.getString(13));
				pdf.setTotOre(rs.getInt(14));
				pdf.setTotCFU(rs.getInt(15));
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
					"select TirocinioEsterno.ID_TirocinioEsterno, Registro.FirmaResponsabile, TirocinioEsterno.status, TirocinioEsterno.CFU from tirocinioesterno\n" +
					"TirocinioEsterno.OreTotali, Registro.ID_Registro, Registro.OreRaggiunte"+
					"join registro on registro.ID_Tirocinio= tirocinioesterno.ID_TirocinioEsterno\n" + 
					 
					"where tirocinioesterno.EMAIL=? and tirocinioesterno.status='in svolgimento';");
			ps.setString(1, email);
			
			ArrayList<RegistroQuery> lista = new ArrayList<RegistroQuery>();
			ResultSet rs = ps.executeQuery();
			System.out.println("querty lista tirocini studente eseguita--> esterno");
			while (rs.next()) {
				RegistroQuery a = new RegistroQuery();
				int idTir=rs.getInt("ID_TirocinioEsterno");
				a.setID_Tirocinio(idTir);
				a.setFirmaResponsabile(rs.getBoolean("FirmaResponsabile"));
				a.setStatus(rs.getString("status"));
				a.setNumeroCFU(rs.getInt("CFU"));
				int oretotali=rs.getInt("OreTotali");
				int oreraggiunte=rs.getInt("OreTotali");
				a.setOreTotali(oretotali);
				a.setID_Registro(oreraggiunte);
				a.setOreRaggiunte(rs.getInt("OreRaggiunte"));
			
				if(oreraggiunte>=oretotali) {
				String query="select relazione.ID_Relazione from relazione join TirocinioEsterno on TirocinioEsterno.ID_TutorAziendale= relazione.ID_TutorAziendale "
						+ "where TirocinioEsterno.ID_TirocinioEsterno=? and relazione.email=?";
				ps = con.prepareStatement(query);
				ps.setInt(1, idTir);
				ps.setString(2, email);
				ResultSet rsdue=ps.executeQuery();
				;
				if(rsdue.next()) {
					a.setID_Relazione(rs.getInt(1));
				}
				
				String querydue="select Questionario_s.Questionario_s from questionario_s"
						+ "where Questionario_s.ID_TirocinioEsterno=? and relazione.email=?";
				ps = con.prepareStatement(querydue);
				
				ps.setString(1, email);
				ResultSet rstre=ps.executeQuery();
				;
				if(rstre.next()) {
					a.setID_Questionario((rs.getInt(1)));
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

	public ArrayList<RegistroQuery> doRetriveTirocinioInSvolgimentoTutorAccRegistro(int id) { // corregere query
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"select TirocinioEsterno.ID_TirocinioEsterno, Registro.FirmaResponsabile, TirocinioEsterno.status, TirocinioEsterno.CFU,"
							+ "TirocinioEsterno.OreTotali, Registro.ID_Registro "
							+ "from TirocinioEsterno, Registro, tutoraccademico "
							+ "where TirocinioEsterno.ID_TirocinioEsterno = Registro.ID_Tirocinio AND "
							+ "TirocinioEsterno.ID_TutorAccademico = ? AND TirocinioEsterno.status='in svolgimento'");
			ps.setInt(1, id);

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
				lista.add(a);
			}
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

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
				a.setFirmaResponsabile(rs.getBoolean("FirmaResponsabile"));
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
					"select TirocinioEsterno.ID_TirocinioEsterno, Registro.FirmaResponsabile, TirocinioEsterno.status, TirocinioEsterno.CFU,"
							+ "TirocinioEsterno.OreTotali, Registro.ID_Registro " + "from TirocinioEsterno, Registro"
							+ "where TirocinioEsterno.ID_TirocinioEsterno = Registro.ID_Tirocinio"
							+ "AND TirocinioEsterno.status='in svolgimento'");

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
					"select ID_TirocinioEsterno,tiro.EMAIL, User.NAME as nomeStud,User.SURNAME as cognomeStud,TutorAccademico.Nome as nomeTutorAcc,TutorAccademico.Cognome as cognomeTutorAcc, Data, OreTotali, status, CFU, FirmaPdCD, FirmaTutorAccademico, FirmaTutorAccademico, ID_Proposta "
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
				richieste.add(a);
			}
			return richieste;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
