package test.mysql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InvalidPropertiesFormatException;

import org.junit.jupiter.api.Test;

import modele.dao.mysql.MySQLCommandeDAO;
import modele.metier.Commande;

class MySQLCommandeDAOTest {

	DateTimeFormatter formatage = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	LocalDate datecomi = LocalDate.parse("10/05/2019", formatage);
	LocalDate datemodif = LocalDate.parse("09/01/2020", formatage);
	LocalDate datefalse = LocalDate.parse("35/01/0000", formatage);

	@Test
	public void testCreateOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		Commande com = new Commande(0, datecomi, 1);
		MySQLCommandeDAO.getInstance().create(com);
		int id = com.getIdcom();

		Commande combdd = MySQLCommandeDAO.getInstance().getById(id);
		assertNotNull(combdd);
	}

	@Test
	public void testCreateNotOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		Commande com = new Commande(1, datefalse, 0);
		MySQLCommandeDAO.getInstance().create(com);
		int id = com.getIdcom();

		Commande combdd = MySQLCommandeDAO.getInstance().getById(id);
		assertNull(combdd);

	}

	@Test
	public void testDelteOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		int t = MySQLCommandeDAO.getInstance().findAll().size();
		Commande com = new Commande(7, datecomi, 1);
		MySQLCommandeDAO.getInstance().delete(com);
		assertEquals(t - 1, MySQLCommandeDAO.getInstance().findAll().size());

	}

	@Test
	public void testDelteNotOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		int t = MySQLCommandeDAO.getInstance().findAll().size();
		Commande com = new Commande(-1, datecomi, 1);
		MySQLCommandeDAO.getInstance().delete(com);
		assertEquals(t, MySQLCommandeDAO.getInstance().findAll().size());

	}

	@Test
	public void testUpdateOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		Commande com = new Commande(2, datemodif, 2);
		MySQLCommandeDAO.getInstance().update(com);
		int id = com.getIdcom();

		Commande combdd = MySQLCommandeDAO.getInstance().getById(id);
		assertEquals(combdd, com);

	}

	@Test
	public void testUpdateNotOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		Commande com = new Commande(-1, datecomi, 1);
		MySQLCommandeDAO.getInstance().update(com);
		int id = com.getIdcom();

		Commande combdd = MySQLCommandeDAO.getInstance().getById(id);
		assertNotEquals(combdd, com);

	}

	@Test
	public void testgetByIdOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		Commande combdd = MySQLCommandeDAO.getInstance().getById(1);
		assertNotNull(combdd);

	}

	@Test
	public void testgetByIdNotOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		Commande combdd = MySQLCommandeDAO.getInstance().getById(-1);
		assertNull(combdd);

	}

	@Test
	public void testfindAllOk() throws InvalidPropertiesFormatException, SQLException, IOException {
		assertNotNull(MySQLCommandeDAO.getInstance().findAll());

	}
}
