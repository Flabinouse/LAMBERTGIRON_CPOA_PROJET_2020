package test.mysql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;

import org.junit.jupiter.api.Test;

import modele.dao.mysql.MySQLProduitDAO;
import modele.metier.Produit;

class MySQLProduitDAOTest {

	@Test
	public void testCreateOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		Produit prod = new Produit(0, "test", "Les tests sont la !", 10, "test.png", 1);
		MySQLProduitDAO.getInstance().create(prod);
		int id = prod.getIdproduit();

		Produit categbdd = MySQLProduitDAO.getInstance().getById(id);
		assertNotNull(categbdd);

	}

	@Test
	public void testCreateNotOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		Produit prod = new Produit(0, "", "", -10, "", -1);
		MySQLProduitDAO.getInstance().create(prod);
		int id = prod.getIdproduit();

		Produit prodbdd = MySQLProduitDAO.getInstance().getById(id);
		assertNull(prodbdd);

	}

	@Test
	public void testDelteOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		int t = MySQLProduitDAO.getInstance().findAll().size();
		Produit prod = new Produit(9, "nom", "description", 10, "visuel", 1);
		MySQLProduitDAO.getInstance().delete(prod);
		assertEquals(t - 1, MySQLProduitDAO.getInstance().findAll().size());

	}

	@Test
	public void testDelteNotOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		int t = MySQLProduitDAO.getInstance().findAll().size();
		Produit prod = new Produit(-1, "nom", "description", 10, "visuel", 1);
		MySQLProduitDAO.getInstance().delete(prod);
		assertEquals(t, MySQLProduitDAO.getInstance().findAll().size());

	}

	@Test
	public void testUpdateOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		Produit prod = new Produit(1, "test", "Les tests, ca test ! et c''est cool !", 10, "test.png", 1);
		MySQLProduitDAO.getInstance().update(prod);
		int id = prod.getIdproduit();

		Produit prodbdd = MySQLProduitDAO.getInstance().getById(id);
		assertEquals(prodbdd, prod);

	}

	@Test
	public void testUpdateNotOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		Produit prod = new Produit(-1, "test", "Les tests, ca test ! et c''est cool !", 10, "test.png", 1);
		MySQLProduitDAO.getInstance().update(prod);
		int id = prod.getIdcategorie();

		Produit prodbdd = MySQLProduitDAO.getInstance().getById(id);
		assertNotEquals(prodbdd, prod);

	}

	@Test
	public void testgetByIdOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		Produit prodbdd = MySQLProduitDAO.getInstance().getById(1);
		assertNotNull(prodbdd);

	}

	@Test
	public void testgetByIdNotOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		Produit prodbdd = MySQLProduitDAO.getInstance().getById(-1);
		assertNull(prodbdd);

	}

	@Test
	public void testfindAllOk() throws InvalidPropertiesFormatException, SQLException, IOException {
		assertNotNull(MySQLProduitDAO.getInstance().findAll());

	}
}
