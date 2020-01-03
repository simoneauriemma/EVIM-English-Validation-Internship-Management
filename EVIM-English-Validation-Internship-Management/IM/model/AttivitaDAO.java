package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttivitaDAO {

	/**
	 * @author Simone Auriemma
	 * @param email
	 * @return
	 */
	public ArrayList<Attività> doRetriveAllEsterno(String email) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"select ID_Attivita, Attivita.ID_Registro, Descrizione, OrarioIngresso, OrarioUscita, Attivita.FirmaResponsabile from Attivita "
					+ "JOIN Registro on Attivita.ID_Registro=Registro.ID_Registro "
					+ "JOIN TirocinioEsterno ON Registro.ID_Registro=TirocinioEsterno.ID_TirocinioEsterno"
					+ " where TirocinioEsterno.EMAIL=?");
			ps.setString(1, email);
			ArrayList<Attività> listaAttività = new ArrayList<Attività>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Attività a = new Attività();
				a.setID_Attivita(rs.getInt("ID_Attivita"));
				a.setID_Registro(rs.getInt("ID_Registro"));
				a.setDescrizione(rs.getString("Descrizione"));
				a.setOrarioIngresso(rs.getInt("OrarioIngresso"));
				a.setOrarioUscita(rs.getInt("OrarioUscita"));
				a.setFirmaResponsabile(rs.getBoolean("FirmaResponsabile"));
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
	 * @return
	 */
	public ArrayList<Attività> doRetriveAllInterno(String email) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"select ID_Attivita, Attivita.ID_Registro, Descrizione, OrarioIngresso, OrarioUscita, Attivita.FirmaResponsabile from Attivita JOIN Registro on Attivita.ID_Registro=Registro.ID_Registro JOIN TirocinioInterno ON Registro.ID_Registro=TirocinioInterno.ID_TirocinioInterno where TirocinioInterno.EMAIL=?");
			ps.setString(1, email);
			ArrayList<Attività> listaAttività = new ArrayList<Attività>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Attività a = new Attività();
				a.setID_Attivita(rs.getInt("ID_Attivita"));
				a.setID_Registro(rs.getInt("ID_Registro"));
				a.setDescrizione(rs.getString("Descrizione"));
				a.setOrarioIngresso(rs.getInt("OrarioIngresso"));
				a.setOrarioUscita(rs.getInt("OrarioUscita"));
				a.setFirmaResponsabile(rs.getBoolean("FirmaResponsabile"));
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
	 * @param i
	 * @return
	 */
	public ArrayList<Attività> doRetriveAllEsternoTutorAzi(int i) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"select ID_Attivita, Attivita.ID_Registro, Descrizione, OrarioIngresso, OrarioUscita, Attivita.FirmaResponsabile from Attivita JOIN Registro on Attivita.ID_Registro=Registro.ID_Registro JOIN TirocinioEsterno ON Registro.ID_Registro=TirocinioEsterno.ID_TirocinioEsterno where ID_TutorAziendale=?");
			ps.setInt(1, i);
			ArrayList<Attività> listaAttività = new ArrayList<Attività>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Attività a = new Attività();
				a.setID_Attivita(rs.getInt("ID_Attivita"));
				a.setID_Registro(rs.getInt("ID_Registro"));
				a.setDescrizione(rs.getString("Descrizione"));
				a.setOrarioIngresso(rs.getInt("OrarioIngresso"));
				a.setOrarioUscita(rs.getInt("OrarioUscita"));
				a.setFirmaResponsabile(rs.getBoolean("FirmaResponsabile"));
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
	 * @param i
	 * @return
	 */
	public ArrayList<Attività> doRetriveAllEsternoTutorAcc(int i) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"select ID_Attivita, Attivita.ID_Registro, Descrizione, OrarioIngresso, OrarioUscita, Attivita.FirmaResponsabile from Attivita JOIN Registro on Attivita.ID_Registro=Registro.ID_Registro JOIN TirocinioEsterno ON Registro.ID_Registro=TirocinioEsterno.ID_TirocinioEsterno where ID_TutorAccademico=?");
			ps.setInt(1, i);
			ArrayList<Attività> listaAttività = new ArrayList<Attività>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Attività a = new Attività();
				a.setID_Attivita(rs.getInt("ID_Attivita"));
				a.setID_Registro(rs.getInt("ID_Registro"));
				a.setDescrizione(rs.getString("Descrizione"));
				a.setOrarioIngresso(rs.getInt("OrarioIngresso"));
				a.setOrarioUscita(rs.getInt("OrarioUscita"));
				a.setFirmaResponsabile(rs.getBoolean("FirmaResponsabile"));
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
	 * @param i
	 * @return
	 */
	public ArrayList<Attività> doRetriveAllInternoTutorAcc(int i) {
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"select ID_Attivita, Attivita.ID_Registro, Descrizione, OrarioIngresso, OrarioUscita, Attivita.FirmaResponsabile from Attivita JOIN Registro on Attivita.ID_Registro=Registro.ID_Registro JOIN TirocinioInterno ON Registro.ID_Registro=TirocinioInterno.ID_TirocinioInterno where ID_TutorAccademico=?");
			ps.setInt(1, i);
			ArrayList<Attività> listaAttività = new ArrayList<Attività>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Attività a = new Attività();
				a.setID_Attivita(rs.getInt("ID_Attivita"));
				a.setID_Registro(rs.getInt("ID_Registro"));
				a.setDescrizione(rs.getString("Descrizione"));
				a.setOrarioIngresso(rs.getInt("OrarioIngresso"));
				a.setOrarioUscita(rs.getInt("OrarioUscita"));
				a.setFirmaResponsabile(rs.getBoolean("FirmaResponsabile"));
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
