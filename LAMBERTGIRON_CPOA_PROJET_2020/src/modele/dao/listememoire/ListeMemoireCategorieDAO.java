package modele.dao.listememoire;

import java.util.ArrayList;
import java.util.List;

import modele.dao.CategorieDAO;
import modele.metier.Categorie;

public class ListeMemoireCategorieDAO implements CategorieDAO {

	private static ListeMemoireCategorieDAO instance;

	private List<Categorie> donnees;

	public static ListeMemoireCategorieDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireCategorieDAO();
		}

		return instance;
	}

	private ListeMemoireCategorieDAO() {

		this.donnees = new ArrayList<Categorie>();
		this.donnees.add(new Categorie(1, "Pulls", "pulls.png"));
		this.donnees.add(new Categorie(2, "Bonnets", "bonnets.png"));
	}

	@Override
	public boolean create(Categorie categ) {

		categ.setIdcategorie(3);

		while (this.donnees.contains(categ)) {

			categ.setIdcategorie(categ.getIdcategorie() + 1);
		}
		boolean ok = this.donnees.add(categ);

		return ok;
	}

	@Override
	public boolean update(Categorie categ) {
		int idx = -1;

		for (Categorie categ2 : this.donnees) {
			if (categ.getIdcategorie() == categ2.getIdcategorie())
				idx = this.donnees.indexOf(categ2);
		}
		// int idx = this.donnees.indexOf(categ);

		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'une categorie inexistante");
		} else {

			this.donnees.set(idx, categ);
		}

		return true;
	}

	@Override
	public boolean delete(Categorie categ) {

		Categorie supprime;

		int idx = -1;

		for (Categorie categ2 : this.donnees) {
			if (categ.getIdcategorie() == categ2.getIdcategorie())
				idx = this.donnees.indexOf(categ2);
		}

		// int idx = this.donnees.indexOf(categ);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'une categorie inexistante");
		} else {
			supprime = this.donnees.remove(idx);
		}

		return categ.equals(supprime);
	}

	@Override
	public Categorie getById(int id) {

		Categorie categ = new Categorie(id, "titre", "visuel");
		int idx = -1;

		for (Categorie categ2 : this.donnees) {
			if (categ.getIdcategorie() == categ2.getIdcategorie())
				idx = this.donnees.indexOf(categ2);
		}

		// int idx = this.donnees.indexOf(new Categorie(id, "titre", "visuel"));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucune categorie ne possede cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

	@Override
	public ArrayList<Categorie> findAll() {
		return (ArrayList<Categorie>) this.donnees;
	}
}
