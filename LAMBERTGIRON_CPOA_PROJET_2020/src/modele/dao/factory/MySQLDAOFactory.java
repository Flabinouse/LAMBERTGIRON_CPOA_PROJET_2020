package modele.dao.factory;

import modele.dao.CategorieDAO;
import modele.dao.ClientDAO;
import modele.dao.CommandeDAO;
import modele.dao.LigneCommandeDAO;
import modele.dao.ProduitDAO;
import modele.dao.mysql.MySQLCategorieDAO;
import modele.dao.mysql.MySQLClientDAO;
import modele.dao.mysql.MySQLCommandeDAO;
import modele.dao.mysql.MySQLLigneCommandeDAO;
import modele.dao.mysql.MySQLProduitDAO;

public class MySQLDAOFactory extends DAOFactory {

	@Override
	public CategorieDAO getCategorieDAO() {
		return MySQLCategorieDAO.getInstance();
	}

	@Override
	public ClientDAO getClientDAO() {
		return MySQLClientDAO.getInstance();
	}

	@Override
	public ProduitDAO getProduitDAO() {
		return MySQLProduitDAO.getInstance();
	}

	@Override
	public CommandeDAO getCommandeDAO() {
		return MySQLCommandeDAO.getInstance();
	}

	@Override
	public LigneCommandeDAO getLigneCommandeDAO() {
		return MySQLLigneCommandeDAO.getInstance();
	}

}
