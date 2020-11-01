package modele.dao.listememoire;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modele.dao.CommandeDAO;
import modele.metier.Commande;

public class ListeMemoireCommandeDAO implements CommandeDAO {

	private LocalDate dt = LocalDate.now();

	private static ListeMemoireCommandeDAO instance;

	private List<Commande> donnees;

	public static ListeMemoireCommandeDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireCommandeDAO();
		}

		return instance;
	}

	private ListeMemoireCommandeDAO() {

		this.donnees = new ArrayList<Commande>();
		this.donnees.add(new Commande(1, dt, 1));
		this.donnees.add(new Commande(2, dt, 2));
		this.donnees.add(new Commande(3, dt, 3));
		this.donnees.add(new Commande(4, dt, 3));
	}

	@Override
	public boolean create(Commande com) {

		com.setIdcom(5);

		while (this.donnees.contains(com)) {

			com.setIdcom(com.getIdcom() + 1);
		}
		boolean ok = this.donnees.add(com);

		return ok;
	}

	@Override
	public boolean update(Commande com) {

		int idx = -1;

		for (Commande com2 : this.donnees) {
			if (com.getIdcom() == com2.getIdcom())
				idx = this.donnees.indexOf(com2);
		}

		// int idx = this.donnees.indexOf(com);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'une commande inexistante");
		} else {

			this.donnees.set(idx, com);
		}

		return true;
	}

	@Override
	public boolean delete(Commande com) {

		Commande supprime;

		int idx = -1;

		for (Commande com2 : this.donnees) {
			if (com.getIdcom() == com2.getIdcom())
				idx = this.donnees.indexOf(com2);
		}

		// int idx = this.donnees.indexOf(com);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'une commande inexistante");
		} else {
			supprime = this.donnees.remove(idx);
		}

		return com.equals(supprime);
	}

	@Override
	public Commande getById(int id) {

		Commande com = new Commande(id, dt, 1);
		int idx = -1;

		for (Commande com2 : this.donnees) {
			if (com.getIdcom() == com2.getIdcom())
				idx = this.donnees.indexOf(com2);
		}

		// int idx = this.donnees.indexOf(new Commande(id, dt, 1));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucune commande ne possede cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

	@Override
	public ArrayList<Commande> findAll() {
		return (ArrayList<Commande>) this.donnees;
	}
}
