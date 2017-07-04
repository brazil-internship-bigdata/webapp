package beans;

import java.sql.Timestamp;

public class Company {

	private Long		id;
	private String		companyName;
	private String		companyFullName;
	private String		passwordCompany;
	private String		responsible1Email;
	private String		responsible1Name;
	private String		responsible1Phone;
	private String		responsible2Email;
	private String		responsible2Name;
	private String		responsible2Phone;
	private String		projectResponsible;
	private String		submissionType;
	private String		fileType;
	private String		dataDescription;
	private Timestamp	signInDate;
	private int			dataFileNumber;
	private int			dataQuantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPasswordCompany() {
		return passwordCompany;
	}

	public void setPasswordCompany(String passwordCompany) {
		this.passwordCompany = passwordCompany;
	}

	public String getResponsible1Email() {
		return responsible1Email;
	}

	public void setResponsible1Email(String responsible1Email) {
		this.responsible1Email = responsible1Email;
	}

	public String getResponsible1Name() {
		return responsible1Name;
	}

	public void setResponsible1Name(String responsible1Name) {
		this.responsible1Name = responsible1Name;
	}

	public String getResponsible1Phone() {
		return responsible1Phone;
	}

	public void setResponsible1Phone(String responsible1Phone) {
		this.responsible1Phone = responsible1Phone;
	}

	public String getResponsible2Email() {
		return responsible2Email;
	}

	public void setResponsible2Email(String responsible2Email) {
		this.responsible2Email = responsible2Email;
	}

	public String getResponsible2Name() {
		return responsible2Name;
	}

	public void setResponsible2Name(String responsible2Name) {
		this.responsible2Name = responsible2Name;
	}

	public String getResponsible2Phone() {
		return responsible2Phone;
	}

	public void setResponsible2Phone(String responsible2Phone) {
		this.responsible2Phone = responsible2Phone;
	}

	public String getProjectResponsible() {
		return projectResponsible;
	}

	public void setProjectResponsible(String projectResponsible) {
		this.projectResponsible = projectResponsible;
	}

	public String getSubmissionType() {
		return submissionType;
	}

	public void setSubmissionType(String submissionType) {
		this.submissionType = submissionType;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getDataDescription() {
		return dataDescription;
	}

	public void setDataDescription(String dataDescription) {
		this.dataDescription = dataDescription;
	}

	public Timestamp getSignInDate() {
		return signInDate;
	}

	public void setSignInDate(Timestamp signInDate) {
		this.signInDate = signInDate;
	}

	public String getCompanyFullName() {
		return companyFullName;
	}

	public void setCompanyFullName(String companyFullName) {
		this.companyFullName = companyFullName;
	}

	public int getDataFileNumber() {
		return dataFileNumber;
	}

	public void setDataFileNumber(int dataFileNumber) {
		this.dataFileNumber = dataFileNumber;
	}

	public int getDataQuantity() {
		return dataQuantity;
	}

	public void setDataQuantity(int dataQuantity) {
		this.dataQuantity = dataQuantity;
	}

}