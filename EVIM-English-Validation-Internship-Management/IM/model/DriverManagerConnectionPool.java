package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimeZone;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

/**
 * 
 * @author Simone Auriemma 
 * Questa classe permette la connessione con il DB e
 * restituisce la connessione che sarà usata nei DAO Useremo i
 * datasource poichè sarà di vitale importanza per disaccopiare le
 * classi del model con le connessioni Inoltre, le connessioni, saranno
 * in un pool gestito dal container stesso e saranno riusate quando
 * necessario per diminuire gli accessi al db Questo garantisce più
 * performance, mentre la sicurezza della connessione sarà affidata al
 * container che implementa automaticamente la sicurezza di JEE
 * 
 */
public class DriverManagerConnectionPool {
	private static DataSource datasource;

	public static Connection getConnection() throws SQLException {
		if (datasource == null) {
			// inizializziamo un pool di connessioni e andiamo a specificare le impostazioni
			// del pool
			PoolProperties p = new PoolProperties();
			p.setUrl("jdbc:mysql://localhost:3306/evim?serverTimezone=" + TimeZone.getDefault().getID());

			// inseriamo il driver
			p.setDriverClassName("com.mysql.cj.jdbc.Driver");
			p.setUsername("root");
			p.setPassword("root");

			// impostiamo per il container il numero massimo di utenti attivi
			// contemporaneamente
			p.setMaxActive(1000);

			// inseriamo un numero iniziale di connessioni già "prefatte"
			p.setInitialSize(100);

			// settiamo il minimo numero di connessioni che dovrebbero rimanere nel pool di
			// connessioni
			p.setMinIdle(10);

			// il tempo in secondi per il quale la connessione può essere considerata
			// abbandonata
			p.setRemoveAbandonedTimeout(60);

			// con il flag true vengono eliminate le connessioni che verranno segnalate come
			// abbandonate
			p.setRemoveAbandoned(true);
			datasource = new DataSource();
			// settiamo al pool di connessioni le nostre impostazioni
			datasource.setPoolProperties(p);
		}
		return datasource.getConnection();
	}
}