package forms;

import javax.servlet.http.HttpServletRequest;

import beans.Company;
import dao.CompanyDao;

public final class LoginForm extends Form {
	private static final String	CHAMP_EMAIL			= "email";
	private static final String	CHAMP_PASS			= "motdepasse";
	private static final String	CONNECTION_ERROR	= "compteInconnu";

	public LoginForm(CompanyDao companyDao) {
		super(companyDao);
	}

	public Company loginCompany(HttpServletRequest request) {
		/* Récupération des champs du formulaire */
		String email = getValeurChamp(request, CHAMP_EMAIL);
		String password = getValeurChamp(request, CHAMP_PASS);

		Company company = new Company();

		/* Validation du champ email. */
		try {
			checkEmail(email);
		} catch (Exception e) {
			setErreur(CHAMP_EMAIL, e.getMessage());
		}
		company.setEmail(email);

		/* Validation du champ mot de passe. */
		try {
			checkPassword(password);
		} catch (Exception e) {
			setErreur(CHAMP_PASS, e.getMessage());
		}
		company.setMotDePasse(password);

		if (companyDao.loginCheck(company.getEmail(), company.getMotDePasse())) {
			company = companyDao.find(email);
		} else {
			setErreur(CONNECTION_ERROR, "Email or password don't match");
		}

		/* Initialisation du résultat global de la validation. */
		if (errors.isEmpty()) {
			results = "Connection successful";

		} else {
			results = "Connection failed.";
		}

		return company;
	}

}