package test.listememoire;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import modele.dao.listememoire.ListeMemoireLigneCommandeDAO;
import modele.metier.LigneCommande;

class ListeMemoireLigneCommandeDAOTest {

	@Test
	public void LigneCommandeCreateOK() {
		LigneCommande lignecom = new LigneCommande(2, 6, 2, 41.5);
		ListeMemoireLigneCommandeDAO.getInstance().create(lignecom);
		int idc = lignecom.getIdcom();
		int idp = lignecom.getIdprod();
		LigneCommande lignecomliste = ListeMemoireLigneCommandeDAO.getInstance().getById(idc, idp);
		assertNotNull(lignecomliste);
	}

	@Test
	public void LigneCommandeCreateEchec() {
		LigneCommande lignecom = new LigneCommande(-1, -1, -1, -1);
		ListeMemoireLigneCommandeDAO.getInstance().create(lignecom);
		int idc = lignecom.getIdcom();
		int idp = lignecom.getIdprod();
		LigneCommande lignecomliste = ListeMemoireLigneCommandeDAO.getInstance().getById(idc, idp);
		assertNotNull(lignecomliste);
	}

	@Test
	public void LigneCommandeUpdateOK() {
		LigneCommande lignecom = new LigneCommande(1, 6, 2, 41.5);
		ListeMemoireLigneCommandeDAO.getInstance().update(lignecom);
		int idc = lignecom.getIdcom();
		int idp = lignecom.getIdprod();
		LigneCommande lignecomliste = ListeMemoireLigneCommandeDAO.getInstance().getById(idc, idp);
		assertNotNull(lignecomliste);
	}

	@Test
	public void LigneCommandeUpdateEchec() {
		try {
			LigneCommande lignecom = new LigneCommande(-1, -1, 2, 41.5);
			ListeMemoireLigneCommandeDAO.getInstance().update(lignecom);
			fail("Modification d'une ligne commande inexistante");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void LigneCommandeDeleteOK() {
		int t = ListeMemoireLigneCommandeDAO.getInstance().findAll().size();
		LigneCommande lignecom = new LigneCommande(2, 12, 2, 41.5);
		ListeMemoireLigneCommandeDAO.getInstance().delete(lignecom);
		assertEquals(t - 1, ListeMemoireLigneCommandeDAO.getInstance().findAll().size());
	}

	@Test
	public void LigneCommandeDeleteEchec() {
		try {
			LigneCommande lignecom = new LigneCommande(-1, -1, 2, 41.5);
			ListeMemoireLigneCommandeDAO.getInstance().delete(lignecom);
			fail("Suppression d'une ligne commande inexistante");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void LigneCommandeGetByIdOK() {
		LigneCommande lignecom = ListeMemoireLigneCommandeDAO.getInstance().getById(1, 2);
		assertNotNull(lignecom);
	}

	@Test
	public void LigneCommandeGetByIdEchec() {
		try {
			LigneCommande lignecomliste = ListeMemoireLigneCommandeDAO.getInstance().getById(-1, -1);
			fail("Aucune ligne commande ne possede cet identifiant");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void LigneCommandeFindAllOk() {
		assertNotNull(ListeMemoireLigneCommandeDAO.getInstance().findAll());
	}

}
