package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.CompanyDao;

public abstract class Form {

	public Form(CompanyDao companyDao) {
		this.companyDao = companyDao;
		errors = new HashMap<String, String>();
	}

	protected CompanyDao		companyDao;
	protected String			results;
	private Map<String, String>	errors;

	public Map<String, String> getErrors() {
		return errors;
	}

	protected void setErreur(String champ, String message) {
		errors.put(champ, message);
	}

	/*
	 * MÃ©thode utilitaire qui retourne null si un champ est vide, et son
	 * contenu sinon.
	 */
	protected static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur;
		}
	}

	protected void checkEmail(String email) throws Exception {
		if (email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
			throw new Exception("Please enter a valid email.");
		}
	}

	/**
	 * Valide le mot de passe saisi.
	 */
	protected void checkPassword(String password) throws Exception {
		if (password != null) {
			if (password.length() < 3) {
				throw new Exception("The password must contains 3 characters.");
			}
		} else {
			throw new Exception("Please enter a password.");
		}
	}
}
