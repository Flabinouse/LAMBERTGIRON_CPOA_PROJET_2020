package test.listememoire;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import modele.dao.listememoire.ListeMemoireCommandeDAO;
import modele.metier.Commande;

class ListeMemoireCommandeDAOTest {

	private LocalDate dt = LocalDate.now();
	private LocalDate dtnull = null;

	@Test
	public void CommandeCreateOK() {
		Commande com = new Commande(0, dt, 1);
		ListeMemoireCommandeDAO.getInstance().create(com);
		int id = com.getIdcom();
		Commande comliste = ListeMemoireCommandeDAO.getInstance().getById(id);
		assertNotNull(comliste);
	}

	@Test
	public void CommandeCreateEchec() {
		Commande com = new Commande(0, dtnull, -1);
		ListeMemoireCommandeDAO.getInstance().create(com);
		int id = com.getIdcom();
		Commande comliste = ListeMemoireCommandeDAO.getInstance().getById(id);
		assertNotNull(comliste);
	}

	@Test
	public void CommandeUpdateOK() {
		Commande com = new Commande(1, dt, 1);
		ListeMemoireCommandeDAO.getInstance().update(com);
		int id = com.getIdcom();
		Commande comliste = ListeMemoireCommandeDAO.getInstance().getById(id);
		assertNotNull(comliste);
	}

	@Test
	public void CommandeUpdateEchec() {
		try {
			Commande com = new Commande(-1, dt, 1);
			ListeMemoireCommandeDAO.getInstance().update(com);
			fail("Modification d'une commande inexistante");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void CommandeDeleteOK() {
		int t = ListeMemoireCommandeDAO.getInstance().findAll().size();
		Commande com = new Commande(2, dt, 1);
		ListeMemoireCommandeDAO.getInstance().delete(com);
		assertEquals(t - 1, ListeMemoireCommandeDAO.getInstance().findAll().size());
	}

	@Test
	public void CommandeDeleteEchec() {
		try {
			Commande com = new Commande(-1, dt, 1);
			ListeMemoireCommandeDAO.getInstance().delete(com);
			fail("Suppression d'une commande inexistante");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void CommandeGetByIdOK() {
		Commande com = ListeMemoireCommandeDAO.getInstance().getById(1);
		assertNotNull(com);
	}

	@Test
	public void CommandeGetByIdEchec() {
		try {
			Commande comliste = ListeMemoireCommandeDAO.getInstance().getById(-1);
			fail("Aucune commande ne possede cet identifiant");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void CommandeFindAllOk() {
		assertNotNull(ListeMemoireCommandeDAO.getInstance().findAll());
	}

}
