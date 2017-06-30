package servlets;

public interface ServletConstants {

	public static final String	CONF_DAO_FACTORY		= "daofactory";

	// TODO fichier porperties
	public static final String	STORAGE_FILE_PATH		= "/home/geourjoa/git/webapp/webapp/storage/";
	public static final String	RESOURCE_FILE_PATH		= "/resource/";
	public static final String	DATA_FILE_PATH			= "/data/";

	public static final int		BUFFER_SIZE_UPLOAD		= 200;
	public static final int		BUFFER_SIZE_DOWNLOAD	= 200;

	public static final String	API_PARAM_COMPANY		= "company";
	public static final String	API_PARAM_PASSWORD		= "password";
	public static final String	API_PARAM_FILE			= "file";

	public static final String	HOMEPAGE				= "homepage";
	public static final String	HOMEPAGE_VIEW			= "/WEB-INF/homepage.jsp";

	public static final String	LOGIN_VIEW				= "/WEB-INF/login.jsp";

	public static final String	SIGNUP_VIEW				= "/WEB-INF/signup.jsp";

	public static final String	ATT_COMPANY				= "company";
	public static final String	ATT_FORM				= "form";

	public static final String	ATT_COMPANY_SESSION		= "companySession";
	public static final String	ATT_FILES_SESSION		= "files";

	public static final String	URL_REDIRECTION			= "/login";
}
