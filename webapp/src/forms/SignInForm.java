package forms;

import javax.servlet.http.HttpServletRequest;

import beans.Company;
import dao.CompanyDao;
import dao.DAOException;

public final class SignInForm extends Form {
	private static final String	SIGNIN_FAIL_LONG			= "Echec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
	private static final String	SIGNIN_FAIL					= "Echec de l'inscription.";
	private static final String	SIGNIN_SUCCESSFUL			= "Succes de l'inscription.";

	private static final String	FIELD_COMPANY_NAME			= "company_name";
	private static final String	FIELD_COMPANY_FULL_NAME		= "company_full_name";
	private static final String	FIELD_PASSWORD_COMPANY		= "password_company";
	private static final String	FIELD_PASSWORD_CONFIRMATION	= "password_confirmation";
	private static final String	FIELD_RESPONSIBLE_1_NAME	= "responsible_1_name";
	private static final String	FIELD_RESPONSIBLE_1_PHONE	= "responsible_1_phone";
	private static final String	FIELD_RESPONSIBLE_1_EMAIL	= "responsible_1_email";
	private static final String	FIELD_RESPONSIBLE_2_NAME	= "responsible_2_name";
	private static final String	FIELD_RESPONSIBLE_2_PHONE	= "responsible_2_phone";
	private static final String	FIELD_RESPONSIBLE_2_EMAIL	= "responsible_2_email";
	private static final String	FIELD_PROJECT_RESPONSIBLE	= "project_responsible";
	private static final String	FIELD_SUBMISSION_TYPE		= "submission_type";
	private static final String	FIELD_FILE_TYPE				= "file_type";

	// private static final String ENCRYPTION_ALGORITHM = "SHA-256";

	public SignInForm(CompanyDao companyDao) {
		super(companyDao);
	}

	public Company signInCompany(HttpServletRequest request) {

		String companyName = getValeurChamp(request, FIELD_COMPANY_NAME);
		String companyFullName = getValeurChamp(request, FIELD_COMPANY_FULL_NAME);
		String passwordCompany = getValeurChamp(request, FIELD_PASSWORD_COMPANY);
		String passwordConfirmation = getValeurChamp(request, FIELD_PASSWORD_CONFIRMATION);
		String responsible1Email = getValeurChamp(request, FIELD_RESPONSIBLE_1_EMAIL);
		String responsible1Phone = getValeurChamp(request, FIELD_RESPONSIBLE_1_PHONE);
		String responsible1Name = getValeurChamp(request, FIELD_RESPONSIBLE_1_NAME);
		String responsible2Email = getValeurChamp(request, FIELD_RESPONSIBLE_2_EMAIL);
		String responsible2Phone = getValeurChamp(request, FIELD_RESPONSIBLE_2_PHONE);
		String responsible2Name = getValeurChamp(request, FIELD_RESPONSIBLE_2_NAME);
		String projectResponsible = getValeurChamp(request, FIELD_PROJECT_RESPONSIBLE);
		String submissionType = getValeurChamp(request, FIELD_SUBMISSION_TYPE);
		String fileType = getValeurChamp(request, FIELD_FILE_TYPE);

		Company company = new Company();
		try {
			treatCompanyName(companyName, company);
			treatCompanyFullName(companyFullName, company);
			treatPassword(passwordCompany, passwordConfirmation, company);
			treatResponsible1Email(responsible1Email, company);
			treatResponsible1Phone(responsible1Phone, company);
			treatResponsible1Name(responsible1Name, company);
			treatResponsible2Email(responsible2Email, company);
			treatResponsible2Phone(responsible2Phone, company);
			treatResponsible2Name(responsible2Name, company);
			treatProjectResponsible(projectResponsible, company);
			treatSubmissionType(submissionType, company);
			treatFileType(fileType, company);

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

	private void treatResponsible1Email(String email, Company company) {
		try {
			checkEmail(email);
		} catch (FormValidationException e) {
			setErreur(FIELD_RESPONSIBLE_1_EMAIL, e.getMessage());
		}
		company.setResponsible1Email(email);
	}

	private void treatResponsible1Phone(String phone, Company company) {
		try {
			checkPhone(phone);
		} catch (FormValidationException e) {
			setErreur(FIELD_RESPONSIBLE_1_PHONE, e.getMessage());
		}
		company.setResponsible1Phone(phone);
	}

	private void treatResponsible1Name(String name, Company company) {
		try {
			checkName(name);
		} catch (FormValidationException e) {
			setErreur(FIELD_RESPONSIBLE_1_NAME, e.getMessage());
		}
		company.setResponsible1Name(name);
	}

	private void treatResponsible2Email(String email, Company company) {
		try {
			checkEmail(email);
		} catch (FormValidationException e) {
			setErreur(FIELD_RESPONSIBLE_2_EMAIL, e.getMessage());
		}
		company.setResponsible2Email(email);
	}

	private void treatResponsible2Phone(String phone, Company company) {
		try {
			checkPhone(phone);
		} catch (FormValidationException e) {
			setErreur(FIELD_RESPONSIBLE_2_PHONE, e.getMessage());
		}
		company.setResponsible2Phone(phone);
	}

	private void treatResponsible2Name(String name, Company company) {
		try {
			checkName(name);
		} catch (FormValidationException e) {
			setErreur(FIELD_RESPONSIBLE_2_NAME, e.getMessage());
		}
		company.setResponsible2Name(name);
	}

	private void treatProjectResponsible(String name, Company company) {
		try {
			checkName(name);
		} catch (FormValidationException e) {
			setErreur(FIELD_RESPONSIBLE_2_NAME, e.getMessage());
		}
		company.setProjectResponsible(name);
	}

	private void treatSubmissionType(String submissionType, Company company) {
		try {
			checkSubmissionType(submissionType);
		} catch (FormValidationException e) {
			setErreur(FIELD_SUBMISSION_TYPE, e.getMessage());
		}
		company.setSubmissionType(submissionType);
	}

	private void treatFileType(String fileType, Company company) {
		try {
			checkFileType(fileType);
		} catch (FormValidationException e) {
			setErreur(FIELD_FILE_TYPE, e.getMessage());
		}
		company.setFileType(fileType);
	}

	private void treatPassword(String password, String confirmation, Company company) {
		try {
			checkPassword(password, confirmation);
		} catch (FormValidationException e) {
			setErreur(FIELD_PASSWORD_COMPANY, e.getMessage());
			setErreur(FIELD_PASSWORD_CONFIRMATION, null);
		}

		// TODO Security
		// ConfigurablePasswordEncryptor passwordEncryptor = new
		// ConfigurablePasswordEncryptor();
		// passwordEncryptor.setAlgorithm(ALGO_CHIFFREMENT);
		// passwordEncryptor.setPlainDigest(false);
		// String encryptPassword = passwordEncryptor.encryptPassword(password);

		company.setPasswordCompany(password);
	}

	private void treatCompanyName(String name, Company company) {
		try {
			checkCompanyName(name);
		} catch (FormValidationException e) {
			setErreur(FIELD_COMPANY_NAME, e.getMessage());
		}
		company.setCompanyName(name);
	}

	private void treatCompanyFullName(String name, Company company) {
		try {
			checkCompanyFullName(name);
		} catch (FormValidationException e) {
			setErreur(FIELD_COMPANY_FULL_NAME, e.getMessage());
		}
		company.setCompanyFullName(name);
	}

	private void checkSubmissionType(String submissionType) throws FormValidationException {
		// TODO Auto-generated method stub

	}

	private void checkName(String name) throws FormValidationException {
		// TODO Auto-generated method stub

	}

	private void checkPhone(String phone) throws FormValidationException {
		// TODO Auto-generated method stub

	}

	private void checkFileType(String fileType) throws FormValidationException {
		// TODO Auto-generated method stub

	}

	private void checkCompanyFullName(String name) throws FormValidationException {
		if (name != null) {
			if (name.length() < 3) {
				throw new FormValidationException("Company name must contains at least 3 characters.");
			}
			if (name.length() > 10) {
				throw new FormValidationException("Company name must contains at more 10 characters.");
			}
		}
	}

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

	private void checkCompanyName(String name) throws FormValidationException {
		if (name != null && name.length() < 3) {
			throw new FormValidationException("Company name must contains at least 3 characters.");
		}
	}

}