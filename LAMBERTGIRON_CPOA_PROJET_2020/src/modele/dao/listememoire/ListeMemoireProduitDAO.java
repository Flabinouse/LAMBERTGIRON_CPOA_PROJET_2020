package modele.dao.listememoire;

import java.util.ArrayList;
import java.util.List;

import modele.dao.ProduitDAO;
import modele.metier.Produit;

public class ListeMemoireProduitDAO implements ProduitDAO {

	private static ListeMemoireProduitDAO instance;

	private List<Produit> donnees;

	public static ListeMemoireProduitDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireProduitDAO();
		}

		return instance;
	}

	private ListeMemoireProduitDAO() {

		this.donnees = new ArrayList<Produit>();
		this.donnees.add(new Produit(2, "Sonic te kiffe",
				"Inspiré par la saga Séga (c'est plus fort que toi !), un pull 100% gamer qui te permettra de faire baver d'envie tes petits camarades de jeu.",
				41, "pull1.png", 1));
		this.donnees.add(new Produit(6, "La chaleur des rennes",
				"Classique mais efficace, un bonnet dont l'élégance n'est pas à souligner, il vous grattera comme il faut !",
				15, "bonnet0.png", 2));
		this.donnees
				.add(new Produit(12, "Dall", "Joyeux Noël avec nos petits lutins dansants !", 35, "bonnet1.png", 2));
	}

	@Override
	public boolean create(Produit prod) {

		prod.setId_produit(13);

		while (this.donnees.contains(prod)) {

			prod.setId_produit(prod.getId_produit() + 1);
		}
		boolean ok = this.donnees.add(prod);

		return ok;
	}

	@Override
	public boolean update(Produit prod) {

		int idx = -1;

		for (Produit prod2 : this.donnees) {
			if (prod.getId_produit() == prod2.getId_produit())
				idx = this.donnees.indexOf(prod2);
		}

		// int idx = this.donnees.indexOf(prod);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un produit inexistant");
		} else {

			this.donnees.set(idx, prod);
		}

		return true;
	}

	@Override
	public boolean delete(Produit prod) {

		Produit supprime;

		int idx = -1;

		for (Produit prod2 : this.donnees) {
			if (prod.getId_produit() == prod2.getId_produit())
				idx = this.donnees.indexOf(prod2);
		}

		// int idx = this.donnees.indexOf(prod);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un produit inexistant");
		} else {
			supprime = this.donnees.remove(idx);
		}

		return prod.equals(supprime);
	}

	@Override
	public Produit getById(int id) {

		Produit prod = new Produit(id, "nom", "description", 41, "visuel", 13);
		int idx = -1;

		for (Produit prod2 : this.donnees) {
			if (prod.getId_produit() == prod2.getId_produit())
				idx = this.donnees.indexOf(prod2);
		}

		// int idx = this.donnees.indexOf(new Produit(id, "nom", "description", 41,
		// "visuel", 13));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun produit ne possede cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

	@Override
	public ArrayList<Produit> findAll() {
		return (ArrayList<Produit>) this.donnees;
	}
}
