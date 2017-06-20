package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Company;

public class CompanyDaoImpl implements CompanyDao {

	private static final String	SQL_SELECT_BY_NAME	= "SELECT * FROM Company WHERE  company_name = ?";
	private static final String	SQL_INSERT			= "INSERT INTO Utilisateur (email, mot_de_passe, nom, date_inscription) VALUES (?, ?, ?, NOW())";
	private static final String	SQL_CONNECTION		= "SELECT * FROM Company WHERE company_name = ? AND password_company = ?";

	private DAOFactory			daoFactory;

	CompanyDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	/*
	 * ImplÃ©mentation de la mÃ©thode dÃ©finie dans l'interface UtilisateurDao
	 */
	@Override
	public Company find(String companyName) throws DAOException {
		return find(SQL_SELECT_BY_NAME, companyName);
	}

	/*
	 * ImplÃ©mentation de la mÃ©thode dÃ©finie dans l'interface UtilisateurDao
	 */
	@Override
	public void create(Company utilisateur) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = DAOTools.initializePreparedStatement(connexion, SQL_INSERT, true,
					utilisateur.getEmail(), utilisateur.getMotDePasse(), utilisateur.getNom());
			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new DAOException("Ã‰chec de la crÃ©ation de l'utilisateur, aucune ligne ajoutÃ©e dans la table.");
			}
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if (valeursAutoGenerees.next()) {
				utilisateur.setId(valeursAutoGenerees.getLong(1));
			} else {
				throw new DAOException(
						"Ã‰chec de la crÃ©ation de l'utilisateur en base, aucun ID auto-gÃ©nÃ©rÃ© retournÃ©.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOTools.silentCloses(valeursAutoGenerees, preparedStatement, connexion);
		}
	}

	@Override
	public boolean loginCheck(String companyName, String password) throws DAOException {

		System.out.println("Mail " + companyName);
		System.out.println("mdp " + password);

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean result = false;

		try {
			/* RÃ©cupÃ©ration d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * PrÃ©paration de la requÃªte avec les objets passÃ©s en arguments
			 * (ici, uniquement une adresse email) et exÃ©cution.
			 */
			preparedStatement = DAOTools.initializePreparedStatement(connexion, SQL_CONNECTION, false, companyName, password);
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de donnÃ©es retournÃ©e dans le ResultSet */
			if (resultSet.next()) {
				System.out.println("result ");
				result = true;
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOTools.silentCloses(resultSet, preparedStatement, connexion);
		}

		return result;
	}

	/*
	 * MÃ©thode gÃ©nÃ©rique utilisÃ©e pour retourner un utilisateur depuis la
	 * base de donnÃ©es, correspondant Ã la requÃªte SQL donnÃ©e prenant en
	 * paramÃ¨tres les objets passÃ©s en argument.
	 */
	private Company find(String sql, Object... objets) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Company company = null;

		try {
			/* RÃ©cupÃ©ration d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * PrÃ©paration de la requÃªte avec les objets passÃ©s en arguments
			 * (ici, uniquement une adresse email) et exÃ©cution.
			 */
			preparedStatement = DAOTools.initializePreparedStatement(connexion, sql, false, objets);
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de donnÃ©es retournÃ©e dans le ResultSet */
			if (resultSet.next()) {
				company = map(resultSet);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOTools.silentCloses(resultSet, preparedStatement, connexion);
		}

		return company;
	}

	/*
	 * Simple mÃ©thode utilitaire permettant de faire la correspondance (le
	 * mapping) entre une ligne issue de la table des utilisateurs (un
	 * ResultSet) et un bean Utilisateur.
	 */
	private static Company map(ResultSet resultSet) throws SQLException {
		Company company = new Company();
		company.setId(resultSet.getLong("id"));
		company.setCompanyName("company_name");
		company.setCompanyFullName("company_full_name");
		company.setPasswordCompany("password_company");
		company.setResponsible1Name("responsible_1_name");
		company.setResponsible1Email("responsible_1_email");
		company.setResponsible1Phone("responsible_1_phone");
		company.setResponsible2Name("responsible_2_name");
		company.setResponsible2Email("responsible_2_email");
		company.setResponsible2Phone("responsible_2_phone");
		company.setProjectResponsible("project_responsible");
		company.setSubmissionType("submission_type");
		company.setFileType("file_type");
		company.setDataDescription("data_description");
		company.setSignInDate("sign_in_date");
		return company;
	}

}