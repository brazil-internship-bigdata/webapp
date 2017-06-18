package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Utilisateur;

public class UtilisateurDaoImpl implements UtilisateurDao {

	private static final String	SQL_SELECT_PAR_EMAIL	= "SELECT id, email, nom, mot_de_passe, date_inscription FROM Utilisateur WHERE email = ?";
	private static final String	SQL_INSERT				= "INSERT INTO Utilisateur (email, mot_de_passe, nom, date_inscription) VALUES (?, ?, ?, NOW())";
	private static final String	SQL_CONNECTION			= "SELECT id, email, nom, mot_de_passe, date_inscription FROM Utilisateur WHERE email = ? AND mot_de_passe = ?";

	private DAOFactory			daoFactory;

	UtilisateurDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	/*
	 * ImplÃ©mentation de la mÃ©thode dÃ©finie dans l'interface UtilisateurDao
	 */
	@Override
	public Utilisateur trouver(String email) throws DAOException {
		return trouver(SQL_SELECT_PAR_EMAIL, email);
	}

	/*
	 * ImplÃ©mentation de la mÃ©thode dÃ©finie dans l'interface UtilisateurDao
	 */
	@Override
	public void creer(Utilisateur utilisateur) throws DAOException {
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
	public boolean connecter(String email, String mdp) throws DAOException {

		System.out.println("Mail " + email);
		System.out.println("mdp " + mdp);

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
			preparedStatement = DAOTools.initializePreparedStatement(connexion, SQL_CONNECTION, false, email, mdp);
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
	private Utilisateur trouver(String sql, Object... objets) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Utilisateur utilisateur = null;

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
				utilisateur = map(resultSet);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOTools.silentCloses(resultSet, preparedStatement, connexion);
		}

		return utilisateur;
	}

	/*
	 * Simple mÃ©thode utilitaire permettant de faire la correspondance (le
	 * mapping) entre une ligne issue de la table des utilisateurs (un
	 * ResultSet) et un bean Utilisateur.
	 */
	private static Utilisateur map(ResultSet resultSet) throws SQLException {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(resultSet.getLong("id"));
		utilisateur.setEmail(resultSet.getString("email"));
		utilisateur.setMotDePasse(resultSet.getString("mot_de_passe"));
		utilisateur.setNom(resultSet.getString("nom"));
		utilisateur.setDateInscription(resultSet.getTimestamp("date_inscription"));
		return utilisateur;
	}

}