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

		String companyName = getValeurChamp(request, FIELD_COMPANY_NAME);
		String passwordCompany = getValeurChamp(request, FIELD_PASSWORD_COMPANY);

		Company company = new Company();

		try {
			checkCompanyName(passwordCompany);
		} catch (Exception e) {
			setErreur(FIELD_COMPANY_NAME, e.getMessage());
		}
		company.setCompanyName(companyName);

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

		if (errors.isEmpty()) {
			results = "Connection successful";

		} else {
			results = "Connection failed.";
		}

		return company;
	}
}