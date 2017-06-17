package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class DAOFactory {

	private static final String	PROPERTIES_FILE		= "/dao/dao.properties";
	private static final String	PROPERTY_URL		= "url";
	private static final String	PROPERTY_DRIVER		= "driver";
	private static final String	PROPERTY_USERNAME	= "username";
	private static final String	PROPERTY_PASSWORD	= "password";

	/* package */BoneCP			connectionPool		= null;

	/* package */ DAOFactory(BoneCP connectionPool) {
		this.connectionPool = connectionPool;
	}

	/*
	 * Get informations about database, load JDBC driver and return instance of
	 * DAOFactory
	 */
	public static DAOFactory getInstance() throws DAOConfigurationException {
		Properties properties = new Properties();
		String url;
		String driver;
		String username;
		String password;
		BoneCP connectionPool = null;

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fileProperties = classLoader.getResourceAsStream(PROPERTIES_FILE);

		if (fileProperties == null) {
			throw new DAOConfigurationException("The file properties " + PROPERTIES_FILE + " is not found.");
		}

		try {
			properties.load(fileProperties);
			url = properties.getProperty(PROPERTY_URL);
			driver = properties.getProperty(PROPERTY_DRIVER);
			username = properties.getProperty(PROPERTY_USERNAME);
			password = properties.getProperty(PROPERTY_PASSWORD);
		} catch (FileNotFoundException e) {
			throw new DAOConfigurationException("The file properties " + PROPERTIES_FILE + " is not found.", e);
		} catch (IOException e) {
			throw new DAOConfigurationException("Impossible to load the file properties " + PROPERTIES_FILE, e);
		}

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new DAOConfigurationException("Driver not found in the classpath.", e);
		}

		try {
			/*
			 * Initialize configuration of a connectionPool
			 */
			BoneCPConfig config = new BoneCPConfig();
			/* Mise en place de l'URL, du nom et du mot de passe */
			config.setJdbcUrl(url);
			config.setUsername(username);
			config.setPassword(password);
			config.setMinConnectionsPerPartition(5);
			config.setMaxConnectionsPerPartition(10);
			config.setPartitionCount(2);
			/*
			 * Creating pool
			 */
			connectionPool = new BoneCP(config);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOConfigurationException("Error of configuration of the pool of connections.", e);
		}

		DAOFactory instance = new DAOFactory(connectionPool);
		return instance;
	}

	/* Provide connection to connection */
	/* package */Connection getConnection() throws SQLException {
		return connectionPool.getConnection();
	}

	// TODO Complete the following

	/*
	 * Méthodes de récupération de l'implémentation des différents DAO (un seul
	 * pour le moment)
	 */
	// public ClientDao getClientDao() {
	// return new ClientDaoImpl(this);
	// }
	//
	// public CommandeDao getCommandeDao() {
	// return new CommandeDaoImpl(this);
	// }
}