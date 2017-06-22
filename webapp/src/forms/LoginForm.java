package forms;

import javax.servlet.http.HttpServletRequest;

import beans.Company;
import dao.CompanyDao;

public final class LoginForm extends Form {
	private static final String	FIELD_COMPANY_NAME		= "company_name";
	private static final String	FIELD_PASSWORD_COMPANY	= "password_company";

	private static final String	CONNECTION_ERROR		= "compteInconnu";

	public LoginForm(CompanyDao companyDao) {
		super(companyDao);
	}

	public Company loginCompany(HttpServletRequest request) {
		/* Récupération des champs du formulaire */
		String companyName = getValeurChamp(request, FIELD_COMPANY_NAME);
		String passwordCompany = getValeurChamp(request, FIELD_PASSWORD_COMPANY);

		Company company = new Company();

		/* Validation du champ email. */
		try {
			checkCompanyName(passwordCompany);
		} catch (Exception e) {
			setErreur(FIELD_COMPANY_NAME, e.getMessage());
		}
		company.setCompanyName(companyName);

		/* Validation du champ mot de passe. */
		try {
			checkPassword(passwordCompany);
		} catch (Exception e) {
			setErreur(FIELD_PASSWORD_COMPANY, e.getMessage());
		}
		company.setPasswordCompany(passwordCompany);

		if (companyDao.loginCheck(company.getCompanyName(), company.getPasswordCompany())) {
			company = companyDao.find(company.getCompanyName());
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

	private void checkCompanyName(String name) throws FormValidationException {
		if (name != null && name.length() < 3) {
			throw new FormValidationException("Company name must contains at least 3 characters.");
		}
	}

}