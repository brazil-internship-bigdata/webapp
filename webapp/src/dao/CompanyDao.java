package dao;

import beans.Company;

public interface CompanyDao {

	public void create(Company company) throws DAOException;

	public Company find(String email) throws DAOException;

	public boolean loginCheck(String email, String password) throws DAOException;

}