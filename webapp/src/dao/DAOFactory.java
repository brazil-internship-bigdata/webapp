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

	private static final String	FILE_PROPERTIES		= "webapp.properties";
	private static final String	PROPERTY_URL		= "url";
	private static final String	PROPERTY_DRIVER		= "driver";
	private static final String	PROPERTY_USERNAME	= "username";
	private static final String	PROPERTY_PASSWORD	= "password";

	/* package */BoneCP			connectionPool		= null;

	/* package */ DAOFactory(BoneCP connectionPool) {
		this.connectionPool = connectionPool;
	}

	/*
	 * Méthode chargée de récupérer les informations de connexion à la base de
	 * données, charger le driver JDBC et retourner une instance de la Factory
	 */
	public static DAOFactory getInstance() throws DAOConfigurationException {
		Properties properties = new Properties();
		String url;
		String driver;
		String username;
		String password;
		BoneCP connectionPool = null;

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fichierProperties = classLoader.getResourceAsStream(FILE_PROPERTIES);

		if (fichierProperties == null) {
			throw new DAOConfigurationException("The file properties " + FILE_PROPERTIES + " is not found.");
		}

		try {
			properties.load(fichierProperties);
			url = properties.getProperty(PROPERTY_URL);
			driver = properties.getProperty(PROPERTY_DRIVER);
			username = properties.getProperty(PROPERTY_USERNAME);
			password = properties.getProperty(PROPERTY_PASSWORD);
		} catch (FileNotFoundException e) {
			throw new DAOConfigurationException("The file properties " + FILE_PROPERTIES + " is not found.", e);
		} catch (IOException e) {
			throw new DAOConfigurationException("Impossible to load le fichier properties " + FILE_PROPERTIES, e);
		}

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new DAOConfigurationException("The driver is not found in the classpath.", e);
		}

		try {
			/*
			 * Création d'une configuration de pool de connexions via l'objet
			 * BoneCPConfig et les différents setters associés.
			 */
			BoneCPConfig config = new BoneCPConfig();
			/* Mise en place de l'URL, du nom et du mot de passe */
			config.setJdbcUrl(url);
			config.setUsername(username);
			config.setPassword(password);
			/* Paramétrage de la taille du pool */
			config.setMinConnectionsPerPartition(5);
			config.setMaxConnectionsPerPartition(10);
			config.setPartitionCount(2);
		
			/*
			 * Création du pool à partir de la configuration, via l'objet BoneCP
			 */
			connectionPool = new BoneCP(config);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOConfigurationException("Error during  configuration of the pool of connexions.", e);
		}
		/*
		 * Enregistrement du pool créé dans une variable d'instance via un appel
		 * au constructeur de DAOFactory
		 */
		DAOFactory instance = new DAOFactory(connectionPool);
		return instance;
	}

	/* Méthode chargée de fournir une connexion à la base de données */
	/* package */Connection getConnection() throws SQLException {
		return connectionPool.getConnection();
	}

	/*
	 * Méthodes de récupération de l'implémentation des différents DAO (un seul
	 * pour le moment)
	 */
	public CompanyDao getCompanyDao() {
		return new CompanyDaoImpl(this);
	}

	public FileUploadDao getFileUploadDao() {
		return new FileUploadDaoImpl(this);
	}
}