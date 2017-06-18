package dao;

import beans.Utilisateur;

public interface UtilisateurDao {

	public void creer(Utilisateur utilisateur) throws DAOException;

	public Utilisateur trouver(String email) throws DAOException;

	public boolean connecter(String email, String mdp) throws DAOException;

}