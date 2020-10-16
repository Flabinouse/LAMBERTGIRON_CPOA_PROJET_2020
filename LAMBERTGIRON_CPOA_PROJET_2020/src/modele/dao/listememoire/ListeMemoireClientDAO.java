package modele.dao.listememoire;

import java.util.ArrayList;
import java.util.List;

import modele.dao.ClientDAO;
import modele.metier.Client;

public class ListeMemoireClientDAO implements ClientDAO {

	private static ListeMemoireClientDAO instance;

	private List<Client> donnees;

	public static ListeMemoireClientDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireClientDAO();
		}

		return instance;
	}

	private ListeMemoireClientDAO() {

		this.donnees = new ArrayList<Client>();
		this.donnees.add(new Client(1, "LAROCHE", "Pierre", "pl@ul.fr", "toto", "12", "rue des étudiants", "57990",
				"Metz", "France"));
		this.donnees.add(new Client(2, "LAMBERT", "Flavien", "pl@ul.fr", "toto", "12", "rue des étudiants", "57990",
				"Metz", "France"));
		this.donnees.add(new Client(3, "GIRON", "Maxence", "pl@ul.fr", "toto", "12", "rue des étudiants", "57990",
				"Metz", "France"));
	}

	@Override
	public boolean create(Client cli) {

		cli.setIdclient(4);

		while (this.donnees.contains(cli)) {

			cli.setIdclient(cli.getIdclient() + 1);
		}
		boolean ok = this.donnees.add(cli);

		return ok;
	}

	@Override
	public boolean update(Client cli) {

		int idx = -1;

		for (Client cli2 : this.donnees) {
			if (cli.getIdclient() == cli2.getIdclient())
				idx = this.donnees.indexOf(cli2);
		}

		// int idx = this.donnees.indexOf(cli);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'une client inexistant");
		} else {

			this.donnees.set(idx, cli);
		}

		return true;
	}

	@Override
	public boolean delete(Client cli) {

		Client supprime;

		int idx = -1;

		for (Client cli2 : this.donnees) {
			if (cli.getIdclient() == cli2.getIdclient())
				idx = this.donnees.indexOf(cli2);
		}

		// int idx = this.donnees.indexOf(cli);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un client inexistant");
		} else {
			supprime = this.donnees.remove(idx);
		}

		return cli.equals(supprime);
	}

	@Override
	public Client getById(int id) {

		Client cli = new Client(id, "nom", "prenom", "identifiant", "mot_de_passe", "adr_numero", "adr_voie",
				"code_postal", "adr_ville", "adr_pays");
		int idx = -1;

		for (Client cli2 : this.donnees) {
			if (cli.getIdclient() == cli2.getIdclient())
				idx = this.donnees.indexOf(cli2);
		}

		// int idx = this.donnees.indexOf(new Client(id, "nom", "prenom", "identifiant",
		// "mot_de_passe", "adr_numero",
		// "adr_voie", "code_postal", "adr_ville", "adr_pays"));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun client ne possede cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

	@Override
	public ArrayList<Client> findAll() {
		return (ArrayList<Client>) this.donnees;
	}
}