package forms;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import beans.Company;
import dao.CompanyDao;

public final class SignUpForm extends Form {

	// private static final String ENCRYPTION_ALGORITHM = "SHA-256";

	public SignUpForm(CompanyDao companyDao) {
		super(companyDao);
	}

	public Company signUpCompany(HttpServletRequest request) {

		String companyName = request.getParameter(FIELD_COMPANY_NAME);
		String companyFullName = request.getParameter(FIELD_COMPANY_FULL_NAME);
		String passwordCompany = request.getParameter(FIELD_PASSWORD_COMPANY);
		String passwordConfirmation = request.getParameter(FIELD_PASSWORD_CONFIRMATION);
		String responsible1Email = request.getParameter(FIELD_RESPONSIBLE_1_EMAIL);
		String responsible1Phone = request.getParameter(FIELD_RESPONSIBLE_1_PHONE);
		String responsible1Name = request.getParameter(FIELD_RESPONSIBLE_1_NAME);
		String responsible2Email = request.getParameter(FIELD_RESPONSIBLE_2_EMAIL);
		String responsible2Phone = request.getParameter(FIELD_RESPONSIBLE_2_PHONE);
		String responsible2Name = request.getParameter(FIELD_RESPONSIBLE_2_NAME);
		String projectResponsible = request.getParameter(FIELD_PROJECT_RESPONSIBLE);
		String submissionType = request.getParameter(FIELD_SUBMISSION_TYPE);
		String fileType = request.getParameter(FIELD_FILE_TYPE);
		String dataDescription = request.getParameter(FIELD_DATA_DESCRIPTION);

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
			treatDataDescription(fileType, company);

			if (errors.isEmpty()) {
				companyDao.create(company);
				createCompanySpace(company.getCompanyName());
				results = SIGNIN_SUCCESSFUL;
			} else {
				results = SIGNIN_FAIL;
			}
		} catch (Exception e) {
			results = SIGNIN_FAIL_LONG;
			e.printStackTrace();
		}

		return company;
	}

	private void treatDataDescription(String dataDescription, Company company) {
		try {
			checkDataDescription(dataDescription);
		} catch (FormValidationException e) {
			setErreur(FIELD_DATA_DESCRIPTION, e.getMessage());
		}
		company.setDataDescription(dataDescription);

	}

	private void checkDataDescription(String dataDescription) throws FormValidationException {
		if (dataDescription.length() > 300) {
			throw new FormValidationException("The data description must contains less than 300 characters. Actually + "
					+ dataDescription.length() + "characters");
		}

	}

	private void createCompanySpace(String companyName) throws Exception {

		File dirResource = new File(STORAGE_FILE_PATH + companyName + "/resource");
		File dirData = new File(STORAGE_FILE_PATH + companyName + "/data");

		if (!(dirData.mkdirs() & dirResource.mkdirs())) {
			throw new Exception("Failet to create the directory of the company.");
		}

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
			checkProjectResponsible(name);
		} catch (FormValidationException e) {
			setErreur(FIELD_RESPONSIBLE_1_NAME, e.getMessage());
		}
		company.setResponsible1Name(name);
	}

	private void treatResponsible2Email(String email, Company company) {
		try {
			if (email != null && email.length() > 0) {
				checkEmail(email);
			}

		} catch (FormValidationException e) {
			setErreur(FIELD_RESPONSIBLE_2_EMAIL, e.getMessage());
		}
		company.setResponsible2Email(email);
	}

	private void treatResponsible2Phone(String phone, Company company) {
		try {
			if (phone != null && phone.length() > 0) {
				checkPhone(phone);
			}

		} catch (FormValidationException e) {
			setErreur(FIELD_RESPONSIBLE_2_PHONE, e.getMessage());
		}
		company.setResponsible2Phone(phone);
	}

	private void treatResponsible2Name(String name, Company company) {
		try {
			if (name != null && name.length() > 0) {
				checkProjectResponsible(name);
			}
		} catch (FormValidationException e) {
			setErreur(FIELD_RESPONSIBLE_2_NAME, e.getMessage());
		}
		company.setResponsible2Name(name);
	}

	private void treatProjectResponsible(String name, Company company) {
		try {
			checkProjectResponsible(name);
		} catch (FormValidationException e) {
			setErreur(FIELD_PROJECT_RESPONSIBLE, e.getMessage());
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

	private void checkProjectResponsible(String name) throws FormValidationException {
		if (name != null && name.length() < 3) {
			throw new FormValidationException("Project responsible must contains at least 3 characters.");
		}

	}

	private void checkPhone(String phone) throws FormValidationException {
		// TODO Auto-generated method stub

	}

	private void checkFileType(String fileType) throws FormValidationException {
		// TODO Auto-generated method stub

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

	private void checkCompanyFullName(String name) throws FormValidationException {
		if (name != null && name.length() < 3) {
			throw new FormValidationException("Company name must contains at least 3 characters.");
		}
	}

}