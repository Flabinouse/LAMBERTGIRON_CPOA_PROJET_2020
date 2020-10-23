package test.mysql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;

import org.junit.jupiter.api.Test;

import modele.dao.mysql.MySQLCategorieDAO;
import modele.metier.Categorie;

class MySQLCategorieDAOTest {

	@Test
	public void testCreateOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		Categorie categ = new Categorie(0, "gants", "gants.jpeg");
		MySQLCategorieDAO.getInstance().create(categ);
		int id = categ.getIdcategorie();

		Categorie categbdd = MySQLCategorieDAO.getInstance().getById(id);
		assertNotNull(categbdd);

	}

	@Test
	public void testCreateNotOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		Categorie categ = new Categorie(1, "", "");
		MySQLCategorieDAO.getInstance().create(categ);
		int id = categ.getIdcategorie();

		Categorie categbdd = MySQLCategorieDAO.getInstance().getById(id);
		assertNull(categbdd);
	}

	@Test
	public void testDelteOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		int t = MySQLCategorieDAO.getInstance().findAll().size();
		Categorie categ = new Categorie(4, "gants", "gants.jpeg");
		MySQLCategorieDAO.getInstance().delete(categ);
		assertEquals(t - 1, MySQLCategorieDAO.getInstance().findAll().size());

	}

	@Test
	public void testDelteNotOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		int t = MySQLCategorieDAO.getInstance().findAll().size();
		Categorie categ = new Categorie(-1, "gants", "gants.jpeg");
		MySQLCategorieDAO.getInstance().delete(categ);
		assertEquals(t, MySQLCategorieDAO.getInstance().findAll().size());

	}

	@Test
	public void testUpdateOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		Categorie categ = new Categorie(2, "pantalons", "pantalons.jpeg");
		MySQLCategorieDAO.getInstance().update(categ);
		int id = categ.getIdcategorie();

		Categorie categbdd = MySQLCategorieDAO.getInstance().getById(id);
		assertEquals(categbdd, categ);

	}

	@Test
	public void testUpdateNotOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		Categorie categ = new Categorie(-1, "pantalons", "pantalons.jpeg");
		MySQLCategorieDAO.getInstance().update(categ);
		int id = categ.getIdcategorie();

		Categorie categbdd = MySQLCategorieDAO.getInstance().getById(id);
		assertNotEquals(categbdd, categ);

	}

	@Test
	public void testgetByIdOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		Categorie categbdd = MySQLCategorieDAO.getInstance().getById(1);
		assertNotNull(categbdd);

	}

	@Test
	public void testgetByIdNotOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		Categorie categbdd = MySQLCategorieDAO.getInstance().getById(-1);
		assertNull(categbdd);

	}

	@Test
	public void testfindAllOk() throws InvalidPropertiesFormatException, SQLException, IOException {
		assertNotNull(MySQLCategorieDAO.getInstance().findAll());

	}
}
