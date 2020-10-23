package test.listememoire;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import modele.dao.listememoire.ListeMemoireCategorieDAO;
import modele.metier.Categorie;

class ListeMemoireCategorieDAOTest {

	@Test
	public void CategorieCreateOK() {
		Categorie categ = new Categorie(3, "chaussures", "chaussures.png");
		ListeMemoireCategorieDAO.getInstance().create(categ);
		int id = categ.getIdcategorie();
		Categorie categliste = ListeMemoireCategorieDAO.getInstance().getById(id);
		assertNotNull(categliste);
	}

	@Test
	public void CategorieCreateEchec() {
		Categorie categ = new Categorie(3, "", "");
		ListeMemoireCategorieDAO.getInstance().create(categ);
		int id = categ.getIdcategorie();
		Categorie categliste = ListeMemoireCategorieDAO.getInstance().getById(id);
		assertNotNull(categliste);
	}

	@Test
	public void CategorieUpdateOK() {
		Categorie categ = new Categorie(1, "chaussures", "chaussures.png");
		ListeMemoireCategorieDAO.getInstance().update(categ);
		int id = categ.getIdcategorie();
		Categorie categliste = ListeMemoireCategorieDAO.getInstance().getById(id);
		assertNotNull(categliste);
	}

	@Test
	public void CategorieUpdateEchec() {
		try {
			Categorie categ = new Categorie(-1, "chaussures", "chaussures.png");
			ListeMemoireCategorieDAO.getInstance().update(categ);
			fail("Modification d'une categorie inexistante");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void CategorieDeleteOK() {
		int t = ListeMemoireCategorieDAO.getInstance().findAll().size();
		Categorie categ = new Categorie(2, "chaussures", "chaussures.png");
		ListeMemoireCategorieDAO.getInstance().delete(categ);
		assertEquals(t - 1, ListeMemoireCategorieDAO.getInstance().findAll().size());
	}

	@Test
	public void CategorieDeleteEchec() {
		try {
			Categorie categ = new Categorie(-1, "chaussures", "chaussures.png");
			ListeMemoireCategorieDAO.getInstance().delete(categ);
			fail("Suppression d'une categorie inexistante");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void CategorieGetByIdOK() {
		Categorie categ = ListeMemoireCategorieDAO.getInstance().getById(1);
		assertNotNull(categ);
	}

	@Test
	public void CategorieGetByIdEchec() {
		try {
			Categorie categliste = ListeMemoireCategorieDAO.getInstance().getById(-1);
			fail("Aucune categorie ne possede cet identifiant");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void CategorieFindAllOk() {
		assertNotNull(ListeMemoireCategorieDAO.getInstance().findAll());
	}

}
