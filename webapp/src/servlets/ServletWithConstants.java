package servlets;

import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServlet;

@SuppressWarnings("serial")
public abstract class ServletWithConstants extends HttpServlet {

	private static final String	STORAGE_PROPERTY			= "storage";

	public static final String	CONF_DAO_FACTORY			= "daofactory";

	// TODO fichier porperties
	public static String		STORAGE_FILE_PATH;
	public static final String	RESOURCE_FILE_PATH			= "/resource/";
	public static final String	DATA_FILE_PATH				= "/data/";

	public static final int		BUFFER_SIZE_UPLOAD			= 200;
	public static final int		BUFFER_SIZE_DOWNLOAD		= 200;

	public static final String	API_PARAM_COMPANY			= "company";
	public static final String	API_PARAM_PASSWORD			= "password";
	public static final String	API_PARAM_FILE				= "file";

	public static final String	HOMEPAGE					= "homepage";
	public static final String	HOMEPAGE_VIEW				= "/WEB-INF/homepage.jsp";

	public static final String	LOGIN_VIEW					= "/WEB-INF/login.jsp";

	public static final String	SIGNUP_VIEW					= "/WEB-INF/signup.jsp";

	public static final String	ADMIN_VIEW					= "/WEB-INF/admin.jsp";
	public static final String	ADMIN						= "admin";

	public static final String	ATT_COMPANY					= "company";
	public static final String	ATT_FORM					= "form";

	public static final String	ATT_COMPANY_SESSION			= "companySession";
	public static final String	ATT_COMPANIES_SESSION		= "companies";
	public static final String	ATT_FILES_DATA_SESSION		= "dataFiles";
	public static final String	ATT_FILES_RESOURCE_SESSION	= "resourceFiles";

	public static final String	ATT_QUANTITY				= "quantityData";
	public static final String	ATT_NUMBER_DATA_FILE		= "numberDataFile";
	public static final String	ATT_NUMBER_RESOURCE_FILE	= "numberResourceFile";

	public static final String	URL_REDIRECTION				= "/login";

	public static final String	FILE_PROPERTIES				= "webapp.properties";

	public static final String	DATA_FILE_EXTENSION			= "csv";
	public static final String	ATT_NB_COMPANY				= "nbCompany";

	public ServletWithConstants() {
		Properties properties = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fileProperties = classLoader.getResourceAsStream(FILE_PROPERTIES);

		try {
			properties.load(fileProperties);
			STORAGE_FILE_PATH = properties.getProperty(STORAGE_PROPERTY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
