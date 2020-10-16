package test.mysql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;

import org.junit.jupiter.api.Test;

import modele.dao.mysql.MySQLLigneCommandeDAO;
import modele.metier.LigneCommande;

class MySQLLigneCommandeDAOTest {

	@Test
	public void testCreateOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		LigneCommande ligncom = new LigneCommande(3, 12, 5, 10.5);
		MySQLLigneCommandeDAO.getInstance().create(ligncom);
		int idcom = ligncom.getIdcom();
		int idprod = ligncom.getIdprod();

		LigneCommande ligncombdd = MySQLLigneCommandeDAO.getInstance().getById(idcom, idprod);
		assertNotNull(ligncombdd);

	}

	@Test
	public void testCreateNotOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		LigneCommande ligncom = new LigneCommande(-1, -1, -1, -1);
		MySQLLigneCommandeDAO.getInstance().create(ligncom);
		int idcom = ligncom.getIdcom();
		int idprod = ligncom.getIdprod();

		LigneCommande ligncombdd = MySQLLigneCommandeDAO.getInstance().getById(idcom, idprod);
		assertNull(ligncombdd);

	}

	@Test
	public void testDelteOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		int t = MySQLLigneCommandeDAO.getInstance().findAll().size();
		LigneCommande ligncom = new LigneCommande(2, 12, 4, 35);
		MySQLLigneCommandeDAO.getInstance().delete(ligncom);
		assertEquals(t - 1, MySQLLigneCommandeDAO.getInstance().findAll().size());

	}

	@Test
	public void testDelteNotOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		int t = MySQLLigneCommandeDAO.getInstance().findAll().size();
		LigneCommande ligncom = new LigneCommande(-1, -1, 5, 10.5);
		MySQLLigneCommandeDAO.getInstance().delete(ligncom);
		assertEquals(t, MySQLLigneCommandeDAO.getInstance().findAll().size());

	}

	@Test
	public void testUpdateOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		LigneCommande ligncom = new LigneCommande(1, 6, 15, 18.5);
		MySQLLigneCommandeDAO.getInstance().update(ligncom);
		int idcom = ligncom.getIdcom();
		int idprod = ligncom.getIdprod();

		LigneCommande ligncombdd = MySQLLigneCommandeDAO.getInstance().getById(idcom, idprod);
		assertEquals(ligncombdd, ligncom);

	}

	@Test
	public void testUpdateNotOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		LigneCommande ligncom = new LigneCommande(-1, -1, 5, 10.5);
		MySQLLigneCommandeDAO.getInstance().update(ligncom);
		int idcom = ligncom.getIdcom();
		int idprod = ligncom.getIdprod();

		LigneCommande ligncombdd = MySQLLigneCommandeDAO.getInstance().getById(idcom, idprod);
		assertNotEquals(ligncombdd, ligncom);

	}

	@Test
	public void testgetByIdOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		LigneCommande ligncombdd = MySQLLigneCommandeDAO.getInstance().getById(1, 2);
		assertNotNull(ligncombdd);

	}

	@Test
	public void testgetByIdNotOk() throws InvalidPropertiesFormatException, SQLException, IOException {

		LigneCommande ligncombdd = MySQLLigneCommandeDAO.getInstance().getById(-1, -1);
		assertNull(ligncombdd);

	}

	@Test
	public void testfindAllOk() throws InvalidPropertiesFormatException, SQLException, IOException {
		assertNotNull(MySQLLigneCommandeDAO.getInstance().findAll());

	}
}
