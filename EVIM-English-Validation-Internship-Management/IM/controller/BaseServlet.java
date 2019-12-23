package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Simone Auriemma Questa classe sarà la servlet principale che
 *         permetterà di rendere "globali" le informazioni in modo da rendere
 *         accesibili le informazioni puù importanti Inoltre, sarà di importanza
 *         fondamentale poichè questa classe sarà ESTESA da tutte le servlet
 */

@SuppressWarnings("serial")
public class BaseServlet extends HttpServlet {

	/**
	 * Il metodo service, sarà l'unico metodo di questa classe e NON fa ovveride del
	 * service della HTTPServlet ma la invoca quando ha concluso tutte le sue
	 * operazioni Non facciamo controlli sul tipo di utenti poichè la home page è
	 * accessibile a tutti
	 */
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.service(req, resp);
	}

}
