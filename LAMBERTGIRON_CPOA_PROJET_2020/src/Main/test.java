package Main;

import modele.dao.factory.DAOFactory;
import modele.dao.factory.Persistance;

public class test {

	public static void main(String[] args) throws Exception {

		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		System.out.println(dao.getLigneCommandeDAO().findAll());

	}

}
