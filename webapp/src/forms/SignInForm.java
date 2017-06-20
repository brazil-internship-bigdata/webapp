package forms;

import javax.servlet.http.HttpServletRequest;

import beans.Company;
import dao.CompanyDao;
import dao.DAOException;

public final class SignInForm extends Form {
	private static final String	SIGNIN_FAIL_LONG	= "Echec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
	private static final String	SIGNIN_FAIL			= "Echec de l'inscription.";
	private static final String	SIGNIN_SUCCESSFUL	= "Succes de l'inscription.";
	private static final String	CHAMP_EMAIL			= "email";
	private static final String	CHAMP_PASS			= "motdepasse";
	private static final String	CHAMP_CONF			= "confirmation";
	private static final String	CHAMP_NAME			= "nom";

	// private static final String ENCRYPTION_ALGORITHM = "SHA-256";

	public SignInForm(CompanyDao companyDao) {
		super(companyDao);
	}

	public Company signInCompany(HttpServletRequest request) {
		String email = getValeurChamp(request, CHAMP_EMAIL);
		String password = getValeurChamp(request, CHAMP_PASS);
		String confirmation = getValeurChamp(request, CHAMP_CONF);
		String name = getValeurChamp(request, CHAMP_NAME);

		Company company = new Company();
		try {
			treatEmail(email, company);
			treatPassword(password, confirmation, company);
			treatCompanyName(name, company);

			if (errors.isEmpty()) {
				companyDao.create(company);
				results = SIGNIN_SUCCESSFUL;
			} else {
				results = SIGNIN_FAIL;
			}
		} catch (DAOException e) {
			results = SIGNIN_FAIL_LONG;
			e.printStackTrace();
		}

		return company;
	}

	/*
	 * Appel Ã la validation de l'adresse email reÃ§ue et initialisation de la
	 * propriÃ©tÃ© email du bean
	 */
	private void treatEmail(String email, Company company) {
		try {
			checkEmail(email);
		} catch (FormValidationException e) {
			setErreur(CHAMP_EMAIL, e.getMessage());
		}
		company.setEmail(email);
	}

	/*
	 * Appel Ã la validation des mots de passe reÃ§us, chiffrement du mot de
	 * passe et initialisation de la propriÃ©tÃ© motDePasse du bean
	 */
	private void treatPassword(String password, String confirmation, Company company) {
		try {
			checkPassword(password, confirmation);
		} catch (FormValidationException e) {
			setErreur(CHAMP_PASS, e.getMessage());
			setErreur(CHAMP_CONF, null);
		}

		/*
		 * Utilisation de la bibliothÃ¨que Jasypt pour chiffrer le mot de passe
		 * efficacement.
		 * 
		 * L'algorithme SHA-256 est ici utilisÃ©, avec par dÃ©faut un salage
		 * alÃ©atoire et un grand nombre d'itÃ©rations de la fonction de
		 * hashage.
		 * 
		 * La String retournÃ©e est de longueur 56 et contient le hash en
		 * Base64.
		 */
		// TODO Security
		// ConfigurablePasswordEncryptor passwordEncryptor = new
		// ConfigurablePasswordEncryptor();
		// passwordEncryptor.setAlgorithm(ALGO_CHIFFREMENT);
		// passwordEncryptor.setPlainDigest(false);
		// String encryptPassword = passwordEncryptor.encryptPassword(password);

		company.setMotDePasse(password);
	}

	/*
	 * Appel Ã la validation du nom reÃ§u et initialisation de la propriÃ©tÃ©
	 * nom du bean
	 */
	private void treatCompanyName(String name, Company company) {
		try {
			checkCompanyName(name);
		} catch (FormValidationException e) {
			setErreur(CHAMP_NAME, e.getMessage());
		}
		company.setNom(name);
	}

	/* Validation de l'adresse email */
	protected void checkEmail(String email) throws FormValidationException {
		if (email != null) {
			if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				throw new FormValidationException("Please enter a valid email");
			} else if (companyDao.find(email) != null) {
				throw new FormValidationException("The adress " + email + " is already use. ");
			}
		} else {
			throw new FormValidationException("Please enter a email adress.");
		}
	}

	/* Validation des mots de passe */
	private void checkPassword(String password, String confirmation) throws FormValidationException {
		if (password != null && confirmation != null) {
			if (!password.equals(confirmation)) {
				throw new FormValidationException("Password and confirmation password are different");
			} else if (password.length() < 3) {
				throw new FormValidationException("Password must contains at least 3 characters.");
			}
		} else {
			throw new FormValidationException("Please enter and confirm a password");
		}
	}

	/* Validation du nom */
	private void checkCompanyName(String name) throws FormValidationException {
		if (name != null && name.length() < 3) {
			throw new FormValidationException("Company name must contains at least 3 characters.");
		}
	}

}