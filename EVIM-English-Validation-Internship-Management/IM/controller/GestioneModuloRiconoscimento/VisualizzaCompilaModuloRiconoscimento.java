package controller.GestioneModuloRiconoscimento;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import model.DriverManagerConnectionPool;
import model.RiconoscimentoDao;
import model.User;

/**
 * Questa servlet permette di reindirizzare alla pagina compila modulo di riconoscimento attività lavorativa con dei campi gia precompilati.
 * @author Antonio Giano
 */
@WebServlet("/VisualizzaCompilaModuloRiconoscimento")
public class VisualizzaCompilaModuloRiconoscimento extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione=request.getSession();
		// controllo se è loggato l'utente altrimenti reindirizzo alla pagina login
		if (sessione.getAttribute("utenteLoggato") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/login.jsp");
			dispatcher.forward(request, response);
		}
		else {
			String tipoUtente=sessione.getAttribute("utenteLoggato").getClass().getName();
			User utente=(User) sessione.getAttribute("utenteLoggato");
			// servlet non adatta per i tutor accademici, per le aziende e per i tutor aziendali
			if(!tipoUtente.equalsIgnoreCase("model.User")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("permissionDenied.jsp");
				dispatcher.forward(request, response);
			}
			
			
			else {
				//studente--> in questo modo nel momento in cui lo studente compila il modulo, troverò i dati anagrafici già precompilati
				
				if(utente.getUserType()==0){
					int cfuInglese=getCFUinglese(utente.getEmail());
					int totCFU=RiconoscimentoDao.getSumCFUStudente(utente.getEmail());
					int totCFUWithEnglish=cfuInglese+totCFU;
					if(totCFUWithEnglish>=12) {
						request.setAttribute("cfuSuperati", true);
						RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/home.jsp");
						dispatcher.forward(request, response);
					}
					else {
						
						request.setAttribute("CFUInglese",cfuInglese);
						request.setAttribute("studente", utente);
						request.getRequestDispatcher("WEB-INF/compilaModuloRiconoscimento.jsp").forward(request, response);
					}
				}
				//non adatto ufficio carriera e PdCD
				if(utente.getUserType()==1 || utente.getUserType()==2) {
					request.getRequestDispatcher("permissionDenied.jsp").forward(request, response);
	 			}		
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	// metodo che stabilisce se lo studente in questione ha fatto qualche richieste di inglese. Se si, prende i tot CFU in merito al riconoscimento di inglese
	private static int getCFUinglese(String emailUser){
		try(Connection con=DriverManagerConnectionPool.getConnection()){
			PreparedStatement ps=(PreparedStatement) con.prepareStatement("select * \n" + 
					"from evim.request \n" + 
					"join evim.state on request.FK_STATE=state.ID_state\n" + 
					"where state.ID_STATE=6 and request.FK_USER=?");
			ps.setString(1, emailUser);
			ResultSet rs=ps.executeQuery();
			int cfu=0;
			if(rs.next()) {
				cfu=rs.getInt("VALIDATED_CFU");
			}
			return cfu;
		
		}catch(SQLException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

}
