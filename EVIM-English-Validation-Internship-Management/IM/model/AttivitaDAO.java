package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.GestioneRegistroTirocinio.VisualizzareRegistroTirocinio.RegistroQuery;

public class AttivitaDAO {

	/**
	 * RegistroQuery è una classe interna di visualizzaRegistro
	 * 
	 * @author Simone Auriemma
	 * @param email
	 * @param i
	 * @return ArrayList<RegistroQuery>
	 */
	public ArrayList<RegistroQuery> doRetriveAllEsterno(String email) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"select Descrizione, OrarioIngresso, OrarioUscita, attivita.FirmaResponsabile, Registro.OreRaggiunte, attivita.OreSvolte, attivita.data, azienda.Nome as nomeAzienda "
							+ "from attivita " + "JOIN Registro ON Registro.ID_Registro = Attivita.ID_Registro "
							+ "JOIN TirocinioEsterno ON TirocinioEsterno.ID_TirocinioEsterno = Registro.ID_Tirocinio "
							+ "JOIN TutorAziendale ON TirocinioEsterno.ID_TutorAziendale = TutorAziendale.ID_TutorAziendale "
							+ "JOIN Azienda ON TutorAziendale.ID_Azienda = Azienda.ID_Azienda "
							+ "where TirocinioEsterno.EMAIL=?");
			ps.setString(1, email);
			ArrayList<RegistroQuery> listaAttività = new ArrayList<RegistroQuery>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				RegistroQuery a = new RegistroQuery();
				a.setDescrizione(rs.getString(1));
				a.setOrarioIngresso(rs.getInt(2));
				a.setOrarioUscita(rs.getInt(3));
				a.setFirmaResponsabile(rs.getBoolean(4));
				a.setOreRaggiunte(rs.getInt(5));
				a.setOreSvolte(rs.getInt(6));
				a.setData(rs.getString(7));
				a.setNomeAzienda(rs.getString(8));

				listaAttività.add(a);
			}
			return listaAttività;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	/**
	 * @author Simone Auriemma
	 * @param email
	 * @return ArrayList<RegistroQuery>
	 */
	public ArrayList<RegistroQuery> doRetriveAllInterno(String email) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"select Descrizione, OrarioIngresso, OrarioUscita, attivita.FirmaResponsabile, Registro.OreRaggiunte, attivita.OreSvolte, attivita.data, tutoraccademico.Nome as nomeTutorAcc, tutoraccademico.Cognome as cognomeTutorAcc "
							+ "from attivita " + "JOIN Registro ON Registro.ID_Registro = Attivita.ID_Registro "
							+ "JOIN TirocinioInterno ON TirocinioInterno.ID_TirocinioInterno = Registro.ID_Tirocinio "
							+ "JOIN tutoraccademico ON TirocinioInterno.ID_tutorAccademico=tutoraccademico.ID_TutorAccademico "
							+ "WHERE tirociniointerno.EMAIL=?;");
			ps.setString(1, email);
			ArrayList<RegistroQuery> listaAttività = new ArrayList<RegistroQuery>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				RegistroQuery a = new RegistroQuery();
				a.setDescrizione(rs.getString(1));
				a.setOrarioIngresso(rs.getInt(2));
				a.setOrarioUscita(rs.getInt(3));
				a.setFirmaResponsabile(rs.getBoolean(4));
				a.setOreRaggiunte(rs.getInt(5));
				a.setOreSvolte(rs.getInt(6));
				a.setData(rs.getString(7));
				a.setNomeTutorAcc(rs.getString(8));
				a.setCognomeTutorAcc(rs.getString(9));

				listaAttività.add(a);
			}
			return listaAttività;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	/**
	 * @author Simone Auriemma
	 * @param iDTutorAziendaele
	 * @param eMAIL
	 * @return
	 */
	public ArrayList<RegistroQuery> doRetriveAllEsternoTutorAzi(int iDTutorAziendaele, String eMAIL) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"select Descrizione, OrarioIngresso, OrarioUscita, attivita.FirmaResponsabile, Registro.OreRaggiunte, attivita.OreSvolte, attivita.data, user.Name as nomeStudente, user.surname as cognomeStudente"
							+ "from attivita " + "JOIN Registro ON Registro.ID_Registro = Attivita.ID_Registro "
							+ "JOIN tirocinioesterno ON tirocinioesterno.ID_TirocinioEsterno = Registro.ID_Tirocinio "
							+ "JOIN tutoraziendale ON tirocinioesterno.ID_TutorAccademico=tutoraziendale.ID_TutorAziendale "
							+ "JOIN User ON tirocinioesterno.EMAIL=user.EMAIL "
							+ "where TirocinioEsterno.EMAIL=? AND TutorAziendale.ID_TutorAziendale=?");
			ps.setString(1, eMAIL);
			ps.setInt(2, iDTutorAziendaele);
			ArrayList<RegistroQuery> listaAttività = new ArrayList<RegistroQuery>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				RegistroQuery a = new RegistroQuery();
				a.setDescrizione(rs.getString(1));
				a.setOrarioIngresso(rs.getInt(2));
				a.setOrarioUscita(rs.getInt(3));
				a.setFirmaResponsabile(rs.getBoolean(4));
				a.setOreRaggiunte(rs.getInt(5));
				a.setOreSvolte(rs.getInt(6));
				a.setData(rs.getString(7));

				listaAttività.add(a);

			}
			return listaAttività;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * @author Simone Auriemma
	 * @param eMAIL (id dell'user)
	 * @param i     (id del tutor accademico)
	 * @return
	 */
	public ArrayList<RegistroQuery> doRetriveAllInternoTutorAcc(String eMAIL, int i) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"select Descrizione, OrarioIngresso, OrarioUscita, attivita.FirmaResponsabile, Registro.OreRaggiunte, attivita.OreSvolte, attivita.data, user.Name as nomeStudente, user.surname as cognomeStudente "
							+ "from attivita " + "JOIN Registro ON Registro.ID_Registro = Attivita.ID_Registro "
							+ "JOIN TirocinioInterno ON TirocinioInterno.ID_TirocinioInterno = Registro.ID_Tirocinio "
							+ "JOIN tutoraccademico ON TirocinioInterno.ID_tutorAccademico=tutoraccademico.ID_TutorAccademico "
							+ "JOIN User ON TirocinioInterno.EMAIL=user.EMAIL "
							+ "where tutoraccademico.ID_TutorAccademico=? AND tirocinioesterno.EMAIL=?");
			ps.setInt(1, i);
			ps.setString(2, eMAIL);
			ArrayList<RegistroQuery> listaAttività = new ArrayList<RegistroQuery>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				RegistroQuery a = new RegistroQuery();
				a.setDescrizione(rs.getString(1));
				a.setOrarioIngresso(rs.getInt(2));
				a.setOrarioUscita(rs.getInt(3));
				a.setFirmaResponsabile(rs.getBoolean(4));
				a.setOreRaggiunte(rs.getInt(5));
				a.setOreSvolte(rs.getInt(6));
				a.setData(rs.getString(7));
				a.setNomeStudente(rs.getString(8));
				a.setCognomeStudente(rs.getString(9));

				listaAttività.add(a);
			}
			return listaAttività;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * @author Simone Auriemma
	 * @param eMAIL
	 * @param i
	 * @return ArrayList<RegistroQuery>
	 */
	public ArrayList<RegistroQuery> doRetriveAllEsternoTutorAcc(String eMAIL, int iDTutorAcc) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"select Descrizione, OrarioIngresso, OrarioUscita, attivita.FirmaResponsabile, Registro.OreRaggiunte, attivita.OreSvolte, attivita.data, user.Name as nomeStudente, user.surname as cognomeStudente "
							+ "from attivita " + "JOIN Registro ON Registro.ID_Registro = Attivita.ID_Registro "
							+ "JOIN tirocinioesterno ON tirocinioesterno.ID_TirocinioEsterno = Registro.ID_Tirocinio "
							+ "JOIN tutoraccademico ON tirocinioesterno.ID_TutorAccademico=tutoraccademico.ID_TutorAccademico "
							+ "JOIN User ON tirocinioesterno.EMAIL=user.EMAIL "
							+ "where tutoraccademico.ID_TutorAccademico=? AND tirocinioesterno.EMAIL=?");
			ps.setInt(1, iDTutorAcc);
			ps.setString(2, eMAIL);
			ArrayList<RegistroQuery> listaAttività = new ArrayList<RegistroQuery>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				RegistroQuery a = new RegistroQuery();
				a.setDescrizione(rs.getString(1));
				a.setOrarioIngresso(rs.getInt(2));
				a.setOrarioUscita(rs.getInt(3));
				a.setFirmaResponsabile(rs.getBoolean(4));
				a.setOreRaggiunte(rs.getInt(5));
				a.setOreSvolte(rs.getInt(6));
				a.setData(rs.getString(7));
				a.setNomeStudente(rs.getString(8));
				a.setCognomeStudente(rs.getString(9));

				listaAttività.add(a);
			}
			return listaAttività;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * @author Simone Auriemma
	 * @param iD_Registro
	 * @param descrizione
	 * @param orarioIngresso
	 * @param orarioUscita
	 */
	public int doInsert(int iD_Registro, String descrizione, int orarioIngresso, int orarioUscita) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"insert into Attivita(ID_Registro, Descrizione, OrarioIngresso, OrarioUscita) VALUES (?,?,?,?)");
			ps.setInt(1, iD_Registro);
			ps.setString(2, descrizione);
			ps.setInt(3, orarioIngresso);
			ps.setInt(4, orarioUscita);

			return ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
