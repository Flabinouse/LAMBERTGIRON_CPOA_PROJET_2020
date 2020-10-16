package modele.dao.factory;

import modele.dao.CategorieDAO;
import modele.dao.ClientDAO;
import modele.dao.CommandeDAO;
import modele.dao.LigneCommandeDAO;
import modele.dao.ProduitDAO;

public abstract class DAOFactory {

	public static DAOFactory getDAOFactory(Persistance cible) {

		DAOFactory daoF = null;

		switch (cible) {
		case MYSQL:
			daoF = new MySQLDAOFactory();
			break;
		case LISTE_MEMOIRE:
			daoF = new ListeMemoireDAOFactory();
			break;
		}

		return daoF;
	}

	public abstract CategorieDAO getCategorieDAO();

	public abstract ClientDAO getClientDAO();

	public abstract ProduitDAO getProduitDAO();

	public abstract CommandeDAO getCommandeDAO();

	public abstract LigneCommandeDAO getLigneCommandeDAO();
}
