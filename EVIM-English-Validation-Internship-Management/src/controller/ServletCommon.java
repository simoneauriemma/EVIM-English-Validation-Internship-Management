package controller;

import model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Admin;
import model.Secretary;
import model.Student;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class ServletCommon.
 */
@WebServlet("/ServletCommon")
public class ServletCommon extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * Constructor.
   * 
   * @see HttpServlet#HttpServlet()
   */
  public ServletCommon() {
    super();
  }

  /**
   * Method doGet().
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  /**
   * Method doPost().
   * 
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @SuppressWarnings("unchecked")
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Integer result = 0;
    String error = "";
    String content = "";
    String redirect = "";
    PreparedStatement stmt = null;

    int flag = Integer.parseInt(request.getParameter("flag"));
    Connection conn = new DbConnection().getInstance().getConn();
    String sql = "";

    if (conn != null) {

      if (flag == 2) { // Aggiornamento Nome
        String idUser = request.getParameter("idUser");
        String newName = request.getParameter("newName");

        try {
          sql = "UPDATE user SET name = ? WHERE email = ?";
          stmt = conn.prepareStatement(sql);
          stmt.setString(1, newName);
          stmt.setString(2, idUser);
          if (stmt.executeUpdate() > 0) {
            content = "Nome Aggiornato";
            result = 1;
          } else {
            error = "Errore aggiornamento Nome.";
          }

          if (result == 0) {
            conn.rollback();
          } else {
            conn.commit();
          }

        } catch (Exception e) {
          error += e.getMessage();
        }
      } else if (flag == 3) { // Aggiornamento Cognome
        String idUser = request.getParameter("idUser");
        String newSurname = request.getParameter("newSurname");

        try {
          sql = "UPDATE user SET surname = ? WHERE email = ?";
          stmt = conn.prepareStatement(sql);
          stmt.setString(1, newSurname);
          stmt.setString(2, idUser);
          if (stmt.executeUpdate() > 0) {
            content = "Cognome Aggiornato";
            result = 1;
          } else {
            error = "Errore aggiornamento Cognome.";
          }

          if (result == 0) {
            conn.rollback();
          } else {
            conn.commit();
          }

        } catch (Exception e) {
          error += e.getMessage();
        }
      }


    } else {
      error = "Nessuna connessione al database.";
    }


    JSONObject res = new JSONObject();
    res.put("result", result);
    res.put("error", error);
    res.put("content", content);
    res.put("redirect", redirect);
    PrintWriter out = response.getWriter();
    out.println(res);
    response.setContentType("json");
  }
}
