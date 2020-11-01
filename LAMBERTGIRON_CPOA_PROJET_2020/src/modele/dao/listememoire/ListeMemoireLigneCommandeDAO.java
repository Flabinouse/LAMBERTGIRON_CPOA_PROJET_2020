package modele.dao.listememoire;

import java.util.ArrayList;
import java.util.List;

import modele.dao.LigneCommandeDAO;
import modele.metier.LigneCommande;

public class ListeMemoireLigneCommandeDAO implements LigneCommandeDAO {

	private static ListeMemoireLigneCommandeDAO instance;

	private List<LigneCommande> donnees;

	public static ListeMemoireLigneCommandeDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireLigneCommandeDAO();
		}

		return instance;
	}

	private ListeMemoireLigneCommandeDAO() {

		this.donnees = new ArrayList<LigneCommande>();
		this.donnees.add(new LigneCommande(1, 2, 2, 41.5));
		this.donnees.add(new LigneCommande(1, 6, 1, 15));
		this.donnees.add(new LigneCommande(2, 12, 4, 35));
		this.donnees.add(new LigneCommande(3, 2, 4, 30));
		this.donnees.add(new LigneCommande(4, 6, 4, 45));
	}

	@Override
	public boolean create(LigneCommande ligncom) {

		boolean ok = this.donnees.add(ligncom);

		return ok;
	}

	@Override
	public boolean update(LigneCommande ligncom) {

		int idx = -1;

		for (LigneCommande ligncom2 : this.donnees) {
			if (ligncom.getIdcom() == ligncom2.getIdcom())
				if (ligncom.getIdprod() == ligncom2.getIdprod())
					idx = this.donnees.indexOf(ligncom2);
		}

		// int idx = this.donnees.indexOf(ligncom);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'une ligne commande inexistante");
		} else {

			this.donnees.set(idx, ligncom);
		}

		return true;
	}

	@Override
	public boolean delete(LigneCommande ligncom) {

		LigneCommande supprime;

		int idx = -1;

		for (LigneCommande ligncom2 : this.donnees) {
			if (ligncom.getIdcom() == ligncom2.getIdcom())
				if (ligncom.getIdprod() == ligncom2.getIdprod())
					idx = this.donnees.indexOf(ligncom2);
		}

		// int idx = this.donnees.indexOf(ligncom);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'une ligne commande inexistante");
		} else {
			supprime = this.donnees.remove(idx);
		}

		return ligncom.equals(supprime);
	}

	@Override
	public LigneCommande getById(int idcom, int idprod) {

		LigneCommande ligncom = new LigneCommande(idcom, idprod, 2, 41.5);
		int idx = -1;

		for (LigneCommande ligncom2 : this.donnees) {
			if (ligncom.getIdcom() == ligncom2.getIdcom())
				if (ligncom.getIdprod() == ligncom2.getIdprod())
					idx = this.donnees.indexOf(ligncom2);
		}

		// int idx = this.donnees.indexOf(new LigneCommande(1, 2, 2, 41.5));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucune ligne commande ne possede cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

	@Override
	public ArrayList<LigneCommande> findAll() {
		return (ArrayList<LigneCommande>) this.donnees;
	}

	@Override
	public LigneCommande getById(int id) throws Exception {
		return null;
	}

}
