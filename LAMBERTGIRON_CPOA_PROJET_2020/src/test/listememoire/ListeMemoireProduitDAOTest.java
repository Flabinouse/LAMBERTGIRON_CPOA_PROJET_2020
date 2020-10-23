package test.listememoire;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import modele.dao.listememoire.ListeMemoireProduitDAO;
import modele.metier.Produit;

class ListeMemoireProduitDAOTest {

	@Test
	public void ProduitCreateOK() {
		Produit prod = new Produit(0, "Mario te kiffe", "Inspire par la Saga", 45, "pull0.png", 1);
		ListeMemoireProduitDAO.getInstance().create(prod);
		int id = prod.getIdproduit();
		Produit prodliste = ListeMemoireProduitDAO.getInstance().getById(id);
		assertNotNull(prodliste);
	}

	@Test
	public void ProduitCreateEchec() {
		Produit prod = new Produit(0, "", "", 0, "", -1);
		ListeMemoireProduitDAO.getInstance().create(prod);
		int id = prod.getIdproduit();
		Produit prodliste = ListeMemoireProduitDAO.getInstance().getById(id);
		assertNotNull(prodliste);
	}

	@Test
	public void ProduitUpdateOK() {
		Produit prod = new Produit(6, "Mario te kiffe", "Inspire par la Saga", 45, "pull0.png", 1);
		ListeMemoireProduitDAO.getInstance().update(prod);
		int id = prod.getIdproduit();
		Produit prodliste = ListeMemoireProduitDAO.getInstance().getById(id);
		assertNotNull(prodliste);
	}

	@Test
	public void ProduitUpdateEchec() {
		try {
			Produit prod = new Produit(-1, "Mario te kiffe", "Inspire par la Saga", 45, "pull0.png", 1);
			ListeMemoireProduitDAO.getInstance().update(prod);
			fail("Modification d'un produit inexistant");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void ProduitDeleteOK() {
		int t = ListeMemoireProduitDAO.getInstance().findAll().size();
		Produit prod = new Produit(12, "Mario te kiffe", "Inspire par la Saga", 45, "pull0.png", 1);
		ListeMemoireProduitDAO.getInstance().delete(prod);
		assertEquals(t - 1, ListeMemoireProduitDAO.getInstance().findAll().size());
	}

	@Test
	public void ProduitDeleteEchec() {
		try {
			Produit prod = new Produit(-1, "Mario te kiffe", "Inspire par la Saga", 45, "pull0.png", 1);
			ListeMemoireProduitDAO.getInstance().delete(prod);
			fail("Suppression d'un produit inexistant");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void ProduitGetByIdOK() {
		Produit prod = ListeMemoireProduitDAO.getInstance().getById(2);
		assertNotNull(prod);
	}

	@Test
	public void ProduitGetByIdEchec() {
		try {
			Produit prodliste = ListeMemoireProduitDAO.getInstance().getById(-1);
			fail("Aucun produit ne possede cet identifiant");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void ProduitFindAllOk() {
		assertNotNull(ListeMemoireProduitDAO.getInstance().findAll());
	}

}
