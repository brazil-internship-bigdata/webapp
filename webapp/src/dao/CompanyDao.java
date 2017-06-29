package dao;

import beans.Company;

public interface CompanyDao {

	public void create(Company company) throws DAOException;

	public Company find(String companyName) throws DAOException;

	public boolean loginCheck(String companyName, String password) throws DAOException;

}