package forms;

import java.util.HashMap;
import java.util.Map;

import dao.CompanyDao;

public abstract class Form {

	public static final String	CONNECTION_ERROR			= "compteInconnu";

	public static final String	SIGNIN_FAIL_LONG			= "Intern error during sign up, please contact the administrator.";
	public static final String	SIGNIN_FAIL					= "Sign up failed";
	public static final String	SIGNIN_SUCCESSFUL			= "Sign up successful";

	// TODO lemettre dans un fichier properties
	// TODO Déplacer dao properties et en faire un fjchier de config globale
	public static final String	STORAGE_FILE_PATH			= "/home/geourjoa/git/webapp/webapp/storage/";

	public static final String	FIELD_COMPANY_NAME			= "company_name";
	public static final String	FIELD_COMPANY_FULL_NAME		= "company_full_name";
	public static final String	FIELD_PASSWORD_COMPANY		= "password_company";
	public static final String	FIELD_PASSWORD_CONFIRMATION	= "password_confirmation";
	public static final String	FIELD_RESPONSIBLE_1_NAME	= "responsible_1_name";
	public static final String	FIELD_RESPONSIBLE_1_PHONE	= "responsible_1_phone";
	public static final String	FIELD_RESPONSIBLE_1_EMAIL	= "responsible_1_email";
	public static final String	FIELD_RESPONSIBLE_2_NAME	= "responsible_2_name";
	public static final String	FIELD_RESPONSIBLE_2_PHONE	= "responsible_2_phone";
	public static final String	FIELD_RESPONSIBLE_2_EMAIL	= "responsible_2_email";
	public static final String	FIELD_PROJECT_RESPONSIBLE	= "project_responsible";
	public static final String	FIELD_SUBMISSION_TYPE		= "submission_type";
	public static final String	FIELD_FILE_TYPE				= "file_type";
	public static final String	FIELD_DATA_DESCRIPTION		= "data_description";

	public Form(CompanyDao companyDao) {
		this.companyDao = companyDao;
		errors = new HashMap<String, String>();
	}

	protected CompanyDao			companyDao;
	protected String				results;
	protected Map<String, String>	errors;

	public Map<String, String> getErrors() {
		return errors;
	}

	public String getResults() {
		return results;
	}

	protected void setErreur(String champ, String message) {
		errors.put(champ, message);
	}

	protected void checkEmail(String email) throws Exception {
		if (email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
			throw new Exception("Please enter a valid email.");
		}
	}

	protected void checkPassword(String password) throws Exception {
		if (password != null) {
			if (password.length() < 3) {
				throw new Exception("The password must contains 3 characters.");
			}
		} else {
			throw new Exception("Please enter a password.");
		}
	}

	protected void checkCompanyName(String name) throws FormValidationException {
		if (name != null && name.length() < 3) {
			throw new FormValidationException("Company name must contains at least 3 characters.");
		}
	}
}
