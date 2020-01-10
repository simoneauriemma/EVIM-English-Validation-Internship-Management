package controller.GestioneAutenticazione;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class RedirectToRegistration
 */
@WebServlet("/RedirectToRegistration")
public class RedirectToRegistration extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedirectToRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		
		if(session.getAttribute("utenteLoggato")==null) {
		
		JSONObject objjson;
		JSONArray json= new JSONArray(JSONarray);
		Iterator i= json.iterator();
		ArrayList <String> sigle = new ArrayList<String>();
		while(i.hasNext()) {
			objjson= (JSONObject) i.next();
			sigle.add(objjson.getString("sigla"));
		}
		request.setAttribute("sigle", sigle);
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/registrazione.jsp");
		rd.forward(request, response);
		
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/home.jsp");
			request.setAttribute("Autorizzato", false);
			rd.forward(request, response);
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	
	
	public String JSONarray="[\n" + 
			"  {\n" + 
			"    \"nome\": \"Agrigento\",\n" + 
			"    \"sigla\": \"AG\",\n" + 
			"    \"regione\": \"Sicilia\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Alessandria\",\n" + 
			"    \"sigla\": \"AL\",\n" + 
			"    \"regione\": \"Piemonte\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Ancona\",\n" + 
			"    \"sigla\": \"AN\",\n" + 
			"    \"regione\": \"Marche\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Arezzo\",\n" + 
			"    \"sigla\": \"AR\",\n" + 
			"    \"regione\": \"Toscana\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Ascoli Piceno\",\n" + 
			"    \"sigla\": \"AP\",\n" + 
			"    \"regione\": \"Marche\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Asti\",\n" + 
			"    \"sigla\": \"AT\",\n" + 
			"    \"regione\": \"Piemonte\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Avellino\",\n" + 
			"    \"sigla\": \"AV\",\n" + 
			"    \"regione\": \"Campania\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Bari\",\n" + 
			"    \"sigla\": \"BA\",\n" + 
			"    \"regione\": \"Puglia\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Barletta-Andria-Trani\",\n" + 
			"    \"sigla\": \"BT\",\n" + 
			"    \"regione\": \"Puglia\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Belluno\",\n" + 
			"    \"sigla\": \"BL\",\n" + 
			"    \"regione\": \"Veneto\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Benevento\",\n" + 
			"    \"sigla\": \"BN\",\n" + 
			"    \"regione\": \"Campania\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Bergamo\",\n" + 
			"    \"sigla\": \"BG\",\n" + 
			"    \"regione\": \"Lombardia\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Biella\",\n" + 
			"    \"sigla\": \"BI\",\n" + 
			"    \"regione\": \"Piemonte\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Bologna\",\n" + 
			"    \"sigla\": \"BO\",\n" + 
			"    \"regione\": \"Emilia-Romagna\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Bolzano/Bozen\",\n" + 
			"    \"sigla\": \"BZ\",\n" + 
			"    \"regione\": \"Trentino-Alto Adige/Südtirol\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Brescia\",\n" + 
			"    \"sigla\": \"BS\",\n" + 
			"    \"regione\": \"Lombardia\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Brindisi\",\n" + 
			"    \"sigla\": \"BR\",\n" + 
			"    \"regione\": \"Puglia\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Cagliari\",\n" + 
			"    \"sigla\": \"CA\",\n" + 
			"    \"regione\": \"Sardegna\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Caltanissetta\",\n" + 
			"    \"sigla\": \"CL\",\n" + 
			"    \"regione\": \"Sicilia\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Campobasso\",\n" + 
			"    \"sigla\": \"CB\",\n" + 
			"    \"regione\": \"Molise\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Carbonia-Iglesias\",\n" + 
			"    \"sigla\": \"CI\",\n" + 
			"    \"regione\": \"Sardegna\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Caserta\",\n" + 
			"    \"sigla\": \"CE\",\n" + 
			"    \"regione\": \"Campania\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Catania\",\n" + 
			"    \"sigla\": \"CT\",\n" + 
			"    \"regione\": \"Sicilia\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Catanzaro\",\n" + 
			"    \"sigla\": \"CZ\",\n" + 
			"    \"regione\": \"Calabria\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Chieti\",\n" + 
			"    \"sigla\": \"CH\",\n" + 
			"    \"regione\": \"Abruzzo\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Como\",\n" + 
			"    \"sigla\": \"CO\",\n" + 
			"    \"regione\": \"Lombardia\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Cosenza\",\n" + 
			"    \"sigla\": \"CS\",\n" + 
			"    \"regione\": \"Calabria\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Cremona\",\n" + 
			"    \"sigla\": \"CR\",\n" + 
			"    \"regione\": \"Lombardia\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Crotone\",\n" + 
			"    \"sigla\": \"KR\",\n" + 
			"    \"regione\": \"Calabria\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Cuneo\",\n" + 
			"    \"sigla\": \"CN\",\n" + 
			"    \"regione\": \"Piemonte\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Enna\",\n" + 
			"    \"sigla\": \"EN\",\n" + 
			"    \"regione\": \"Sicilia\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Fermo\",\n" + 
			"    \"sigla\": \"FM\",\n" + 
			"    \"regione\": \"Marche\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Ferrara\",\n" + 
			"    \"sigla\": \"FE\",\n" + 
			"    \"regione\": \"Emilia-Romagna\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Firenze\",\n" + 
			"    \"sigla\": \"FI\",\n" + 
			"    \"regione\": \"Toscana\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Foggia\",\n" + 
			"    \"sigla\": \"FG\",\n" + 
			"    \"regione\": \"Puglia\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Forlì-Cesena\",\n" + 
			"    \"sigla\": \"FC\",\n" + 
			"    \"regione\": \"Emilia-Romagna\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Frosinone\",\n" + 
			"    \"sigla\": \"FR\",\n" + 
			"    \"regione\": \"Lazio\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Genova\",\n" + 
			"    \"sigla\": \"GE\",\n" + 
			"    \"regione\": \"Liguria\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Gorizia\",\n" + 
			"    \"sigla\": \"GO\",\n" + 
			"    \"regione\": \"Friuli-Venezia Giulia\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Grosseto\",\n" + 
			"    \"sigla\": \"GR\",\n" + 
			"    \"regione\": \"Toscana\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Imperia\",\n" + 
			"    \"sigla\": \"IM\",\n" + 
			"    \"regione\": \"Liguria\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Isernia\",\n" + 
			"    \"sigla\": \"IS\",\n" + 
			"    \"regione\": \"Molise\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"L'Aquila\",\n" + 
			"    \"sigla\": \"AQ\",\n" + 
			"    \"regione\": \"Abruzzo\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"La Spezia\",\n" + 
			"    \"sigla\": \"SP\",\n" + 
			"    \"regione\": \"Liguria\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Latina\",\n" + 
			"    \"sigla\": \"LT\",\n" + 
			"    \"regione\": \"Lazio\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Lecce\",\n" + 
			"    \"sigla\": \"LE\",\n" + 
			"    \"regione\": \"Puglia\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Lecco\",\n" + 
			"    \"sigla\": \"LC\",\n" + 
			"    \"regione\": \"Lombardia\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Livorno\",\n" + 
			"    \"sigla\": \"LI\",\n" + 
			"    \"regione\": \"Toscana\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Lodi\",\n" + 
			"    \"sigla\": \"LO\",\n" + 
			"    \"regione\": \"Lombardia\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Lucca\",\n" + 
			"    \"sigla\": \"LU\",\n" + 
			"    \"regione\": \"Toscana\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Macerata\",\n" + 
			"    \"sigla\": \"MC\",\n" + 
			"    \"regione\": \"Marche\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Mantova\",\n" + 
			"    \"sigla\": \"MN\",\n" + 
			"    \"regione\": \"Lombardia\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Massa-Carrara\",\n" + 
			"    \"sigla\": \"MS\",\n" + 
			"    \"regione\": \"Toscana\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Matera\",\n" + 
			"    \"sigla\": \"MT\",\n" + 
			"    \"regione\": \"Basilicata\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Medio Campidano\",\n" + 
			"    \"sigla\": \"VS\",\n" + 
			"    \"regione\": \"Sardegna\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Messina\",\n" + 
			"    \"sigla\": \"ME\",\n" + 
			"    \"regione\": \"Sicilia\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Milano\",\n" + 
			"    \"sigla\": \"MI\",\n" + 
			"    \"regione\": \"Lombardia\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Modena\",\n" + 
			"    \"sigla\": \"MO\",\n" + 
			"    \"regione\": \"Emilia-Romagna\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Monza e della Brianza\",\n" + 
			"    \"sigla\": \"MB\",\n" + 
			"    \"regione\": \"Lombardia\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Napoli\",\n" + 
			"    \"sigla\": \"NA\",\n" + 
			"    \"regione\": \"Campania\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Novara\",\n" + 
			"    \"sigla\": \"NO\",\n" + 
			"    \"regione\": \"Piemonte\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Nuoro\",\n" + 
			"    \"sigla\": \"NU\",\n" + 
			"    \"regione\": \"Sardegna\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Ogliastra\",\n" + 
			"    \"sigla\": \"OG\",\n" + 
			"    \"regione\": \"Sardegna\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Olbia-Tempio\",\n" + 
			"    \"sigla\": \"OT\",\n" + 
			"    \"regione\": \"Sardegna\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Oristano\",\n" + 
			"    \"sigla\": \"OR\",\n" + 
			"    \"regione\": \"Sardegna\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Padova\",\n" + 
			"    \"sigla\": \"PD\",\n" + 
			"    \"regione\": \"Veneto\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Palermo\",\n" + 
			"    \"sigla\": \"PA\",\n" + 
			"    \"regione\": \"Sicilia\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Parma\",\n" + 
			"    \"sigla\": \"PR\",\n" + 
			"    \"regione\": \"Emilia-Romagna\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Pavia\",\n" + 
			"    \"sigla\": \"PV\",\n" + 
			"    \"regione\": \"Lombardia\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Perugia\",\n" + 
			"    \"sigla\": \"PG\",\n" + 
			"    \"regione\": \"Umbria\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Pesaro e Urbino\",\n" + 
			"    \"sigla\": \"PU\",\n" + 
			"    \"regione\": \"Marche\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Pescara\",\n" + 
			"    \"sigla\": \"PE\",\n" + 
			"    \"regione\": \"Abruzzo\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Piacenza\",\n" + 
			"    \"sigla\": \"PC\",\n" + 
			"    \"regione\": \"Emilia-Romagna\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Pisa\",\n" + 
			"    \"sigla\": \"PI\",\n" + 
			"    \"regione\": \"Toscana\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Pistoia\",\n" + 
			"    \"sigla\": \"PT\",\n" + 
			"    \"regione\": \"Toscana\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Pordenone\",\n" + 
			"    \"sigla\": \"PN\",\n" + 
			"    \"regione\": \"Friuli-Venezia Giulia\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Potenza\",\n" + 
			"    \"sigla\": \"PZ\",\n" + 
			"    \"regione\": \"Basilicata\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Prato\",\n" + 
			"    \"sigla\": \"PO\",\n" + 
			"    \"regione\": \"Toscana\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Ragusa\",\n" + 
			"    \"sigla\": \"RG\",\n" + 
			"    \"regione\": \"Sicilia\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Ravenna\",\n" + 
			"    \"sigla\": \"RA\",\n" + 
			"    \"regione\": \"Emilia-Romagna\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Reggio di Calabria\",\n" + 
			"    \"sigla\": \"RC\",\n" + 
			"    \"regione\": \"Calabria\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Reggio nell'Emilia\",\n" + 
			"    \"sigla\": \"RE\",\n" + 
			"    \"regione\": \"Emilia-Romagna\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Rieti\",\n" + 
			"    \"sigla\": \"RI\",\n" + 
			"    \"regione\": \"Lazio\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Rimini\",\n" + 
			"    \"sigla\": \"RN\",\n" + 
			"    \"regione\": \"Emilia-Romagna\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Roma\",\n" + 
			"    \"sigla\": \"RM\",\n" + 
			"    \"regione\": \"Lazio\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Rovigo\",\n" + 
			"    \"sigla\": \"RO\",\n" + 
			"    \"regione\": \"Veneto\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Salerno\",\n" + 
			"    \"sigla\": \"SA\",\n" + 
			"    \"regione\": \"Campania\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Sassari\",\n" + 
			"    \"sigla\": \"SS\",\n" + 
			"    \"regione\": \"Sardegna\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Savona\",\n" + 
			"    \"sigla\": \"SV\",\n" + 
			"    \"regione\": \"Liguria\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Siena\",\n" + 
			"    \"sigla\": \"SI\",\n" + 
			"    \"regione\": \"Toscana\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Siracusa\",\n" + 
			"    \"sigla\": \"SR\",\n" + 
			"    \"regione\": \"Sicilia\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Sondrio\",\n" + 
			"    \"sigla\": \"SO\",\n" + 
			"    \"regione\": \"Lombardia\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Taranto\",\n" + 
			"    \"sigla\": \"TA\",\n" + 
			"    \"regione\": \"Puglia\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Teramo\",\n" + 
			"    \"sigla\": \"TE\",\n" + 
			"    \"regione\": \"Abruzzo\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Terni\",\n" + 
			"    \"sigla\": \"TR\",\n" + 
			"    \"regione\": \"Umbria\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Torino\",\n" + 
			"    \"sigla\": \"TO\",\n" + 
			"    \"regione\": \"Piemonte\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Trapani\",\n" + 
			"    \"sigla\": \"TP\",\n" + 
			"    \"regione\": \"Sicilia\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Trento\",\n" + 
			"    \"sigla\": \"TN\",\n" + 
			"    \"regione\": \"Trentino-Alto Adige/Südtirol\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Treviso\",\n" + 
			"    \"sigla\": \"TV\",\n" + 
			"    \"regione\": \"Veneto\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Trieste\",\n" + 
			"    \"sigla\": \"TS\",\n" + 
			"    \"regione\": \"Friuli-Venezia Giulia\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Udine\",\n" + 
			"    \"sigla\": \"UD\",\n" + 
			"    \"regione\": \"Friuli-Venezia Giulia\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Valle d'Aosta/Vallée d'Aoste\",\n" + 
			"    \"sigla\": \"AO\",\n" + 
			"    \"regione\": \"Valle d'Aosta/Vallée d'Aoste\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Varese\",\n" + 
			"    \"sigla\": \"VA\",\n" + 
			"    \"regione\": \"Lombardia\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Venezia\",\n" + 
			"    \"sigla\": \"VE\",\n" + 
			"    \"regione\": \"Veneto\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Verbano-Cusio-Ossola\",\n" + 
			"    \"sigla\": \"VB\",\n" + 
			"    \"regione\": \"Piemonte\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Vercelli\",\n" + 
			"    \"sigla\": \"VC\",\n" + 
			"    \"regione\": \"Piemonte\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Verona\",\n" + 
			"    \"sigla\": \"VR\",\n" + 
			"    \"regione\": \"Veneto\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Vibo Valentia\",\n" + 
			"    \"sigla\": \"VV\",\n" + 
			"    \"regione\": \"Calabria\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Vicenza\",\n" + 
			"    \"sigla\": \"VI\",\n" + 
			"    \"regione\": \"Veneto\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"nome\": \"Viterbo\",\n" + 
			"    \"sigla\": \"VT\",\n" + 
			"    \"regione\": \"Lazio\"\n" + 
			"  }\n" + 
			"]\n" + 
			"";


}
