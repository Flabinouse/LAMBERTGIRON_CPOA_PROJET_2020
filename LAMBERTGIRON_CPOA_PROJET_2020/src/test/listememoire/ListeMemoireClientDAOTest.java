package test.listememoire;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import modele.dao.listememoire.ListeMemoireClientDAO;
import modele.metier.Client;

class ListeMemoireClientDAOTest {

	@Test
	public void ClientCreateOK() {
		Client cli = new Client(0, "Covert", "Harry", "pl@ul.fr", "toto", "12", "rue des étudiants", "57990", "Metz",
				"France");
		ListeMemoireClientDAO.getInstance().create(cli);
		int id = cli.getIdclient();
		Client cliliste = ListeMemoireClientDAO.getInstance().getById(id);
		assertNotNull(cliliste);
	}

	@Test
	public void ClientCreateEchec() {
		Client cli = new Client(0, "", "", "", "", "", "", "", "", "");
		ListeMemoireClientDAO.getInstance().create(cli);
		int id = cli.getIdclient();
		Client cliliste = ListeMemoireClientDAO.getInstance().getById(id);
		assertNotNull(cliliste);
	}

	@Test
	public void ClientUpdateOK() {
		Client cli = new Client(3, "Covert", "Harry", "pl@ul.fr", "toto", "12", "rue des étudiants", "57990", "Metz",
				"France");
		ListeMemoireClientDAO.getInstance().update(cli);
		int id = cli.getIdclient();
		Client cliliste = ListeMemoireClientDAO.getInstance().getById(id);
		assertNotNull(cliliste);
	}

	@Test
	public void ClientUpdateEchec() {
		try {
			Client cli = new Client(-1, "Covert", "Harry", "pl@ul.fr", "toto", "12", "rue des étudiants", "57990",
					"Metz", "France");
			ListeMemoireClientDAO.getInstance().update(cli);
			fail("Modification d'un client inexistant");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void ClientDeleteOK() {
		int t = ListeMemoireClientDAO.getInstance().findAll().size();
		Client cli = new Client(2, "Covert", "Harry", "pl@ul.fr", "toto", "12", "rue des étudiants", "57990", "Metz",
				"France");
		ListeMemoireClientDAO.getInstance().delete(cli);
		assertEquals(t - 1, ListeMemoireClientDAO.getInstance().findAll().size());
	}

	@Test
	public void ClientDeleteEchec() {
		try {
			Client cli = new Client(-1, "Covert", "Harry", "pl@ul.fr", "toto", "12", "rue des étudiants", "57990",
					"Metz", "France");
			ListeMemoireClientDAO.getInstance().delete(cli);
			fail("Suppression d'un client inexistant");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void ClientGetByIdOK() {
		Client cli = ListeMemoireClientDAO.getInstance().getById(1);
		assertNotNull(cli);
	}

	@Test
	public void ClientGetByIdEchec() {
		try {
			Client cliliste = ListeMemoireClientDAO.getInstance().getById(-1);
			fail("Aucun client ne possede cet identifiant");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void CLientFindAllOk() {
		assertNotNull(ListeMemoireClientDAO.getInstance().findAll());
	}

}
