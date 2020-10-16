package test.mysql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;

import org.junit.jupiter.api.Test;

import modele.dao.mysql.MySQLClientDAO;
import modele.metier.Client;

class MySQLClientDAOTest {

	@Test
	public void testCreateOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		Client cli = new Client(0, "nom", "prenom", "identifiant", "mdp", "num", "voie", "postal", "ville", "pays");
		MySQLClientDAO.getInstance().create(cli);
		int id = cli.getIdclient();

		Client clibdd = MySQLClientDAO.getInstance().getById(id);
		assertNotNull(clibdd);

	}

	@Test
	public void testCreateNotOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		Client cli = new Client(0, "", "", "", "", "", "", "", "", "");
		MySQLClientDAO.getInstance().create(cli);
		int id = cli.getIdclient();

		Client clibdd = MySQLClientDAO.getInstance().getById(id);
		assertNull(clibdd);

	}

	@Test
	public void testDelteOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		int t = MySQLClientDAO.getInstance().findAll().size();
		Client cli = new Client(10, "nom", "prenom", "identifiant", "mdp", "num", "voie", "postal", "ville", "pays");
		MySQLClientDAO.getInstance().delete(cli);
		assertEquals(t - 1, MySQLClientDAO.getInstance().findAll().size());

	}

	@Test
	public void testDelteNotOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		int t = MySQLClientDAO.getInstance().findAll().size();
		Client cli = new Client(-1, "nom", "prenom", "identifiant", "mdp", "num", "voie", "postal", "ville", "pays");
		MySQLClientDAO.getInstance().delete(cli);
		assertEquals(t, MySQLClientDAO.getInstance().findAll().size());

	}

	@Test
	public void testUpdateOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		Client cli = new Client(9, "test", "test", "test", "test", "test", "test", "test", "test", "test");
		MySQLClientDAO.getInstance().update(cli);
		int id = cli.getIdclient();

		Client clibdd = MySQLClientDAO.getInstance().getById(id);
		assertEquals(clibdd, cli);

	}

	@Test
	public void testUpdateNotOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		Client cli = new Client(-1, "nom", "prenom", "identifiant", "mdp", "num", "voie", "postal", "ville", "pays");
		MySQLClientDAO.getInstance().update(cli);
		int id = cli.getIdclient();

		Client clibdd = MySQLClientDAO.getInstance().getById(id);
		assertNotEquals(clibdd, cli);

	}

	@Test
	public void testgetByIdOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		Client clibdd = MySQLClientDAO.getInstance().getById(1);
		assertNotNull(clibdd);

	}

	@Test
	public void testgetByIdNotOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		Client clibdd = MySQLClientDAO.getInstance().getById(-1);
		assertNull(clibdd);

	}

	@Test
	public void testfindAllOk() throws InvalidPropertiesFormatException, SQLException, IOException {
		assertNotNull(MySQLClientDAO.getInstance().findAll());

	}
}
