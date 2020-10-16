package modele.dao.factory;

import modele.dao.CategorieDAO;
import modele.dao.ClientDAO;
import modele.dao.CommandeDAO;
import modele.dao.LigneCommandeDAO;
import modele.dao.ProduitDAO;
import modele.dao.listememoire.ListeMemoireCategorieDAO;
import modele.dao.listememoire.ListeMemoireClientDAO;
import modele.dao.listememoire.ListeMemoireCommandeDAO;
import modele.dao.listememoire.ListeMemoireLigneCommandeDAO;
import modele.dao.listememoire.ListeMemoireProduitDAO;

public class ListeMemoireDAOFactory extends DAOFactory {

	@Override
	public CategorieDAO getCategorieDAO() {
		return ListeMemoireCategorieDAO.getInstance();
	}

	@Override
	public ClientDAO getClientDAO() {
		return ListeMemoireClientDAO.getInstance();
	}

	@Override
	public ProduitDAO getProduitDAO() {
		return ListeMemoireProduitDAO.getInstance();
	}

	@Override
	public CommandeDAO getCommandeDAO() {
		return ListeMemoireCommandeDAO.getInstance();
	}

	@Override
	public LigneCommandeDAO getLigneCommandeDAO() {
		return ListeMemoireLigneCommandeDAO.getInstance();
	}

}
