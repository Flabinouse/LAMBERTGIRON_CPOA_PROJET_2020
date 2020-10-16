package modele.dao.mysql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

import modele.connexion.Connexion;
import modele.dao.ClientDAO;
import modele.metier.Client;

public class MySQLClientDAO implements ClientDAO {

	private static MySQLClientDAO instance;

	private MySQLClientDAO() {
	}

	public static MySQLClientDAO getInstance() {

		if (instance == null) {
			instance = new MySQLClientDAO();
		}

		return instance;
	}

	@Override
	public Client getById(int id_Client) throws SQLException, InvalidPropertiesFormatException, IOException {

		String nom = "";
		String prenom = "";
		String identifiant = "";
		String mdp = "";
		String adrnum = "";
		String adrvoie = "";
		String adrcp = "";
		String adrville = "";
		String adrpays = "";
		Client cli = null;
		Connection laConnexion = Connexion.getInstance().creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Client where id_client=?");
		requete.setInt(1, id_Client);
		ResultSet res = requete.executeQuery();
		while (res.next()) {
			nom = res.getString("nom");
			prenom = res.getString("prenom");
			identifiant = res.getString("identifiant");
			mdp = res.getString("mot_de_passe");
			adrnum = res.getString("adr_numero");
			adrvoie = res.getString("adr_voie");
			adrcp = res.getString("adr_code_postal");
			adrville = res.getString("adr_ville");
			adrpays = res.getString("adr_pays");
			cli = new Client(id_Client, nom, prenom, identifiant, mdp, adrnum, adrvoie, adrcp, adrville, adrpays);
		}

		if (res != null) {
			res.close();
		}
		if (requete != null) {
			requete.close();
		}
		if (laConnexion != null) {
			laConnexion.close();
		}

		return cli;

	}

	@Override
	public boolean create(Client cli) throws SQLException, InvalidPropertiesFormatException, IOException {

		boolean verif = false;
		int check = 0;

		Connection laConnexion = Connexion.getInstance().creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
				"INSERT INTO Client (id_client, nom, prenom, identifiant, mot_de_passe, adr_numero, adr_voie, adr_code_postal, adr_ville, adr_pays)"
						+ " VALUES (default,?,?,?,?,?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);
		requete.setString(1, cli.getNom());
		requete.setString(2, cli.getPrenom());
		requete.setString(3, cli.getIdentifiant());
		requete.setString(4, cli.getMdp());
		requete.setString(5, cli.getNumero());
		requete.setString(6, cli.getRue());
		requete.setString(7, cli.getPostal());
		requete.setString(8, cli.getVille());
		requete.setString(9, cli.getPays());
		requete.executeUpdate();

		if(check == 0)
			verif = true;

		ResultSet res = requete.getGeneratedKeys();

		if (res.next()) {
			int id = res.getInt(1);
			cli.setIdclient(id);
		}

		if (requete != null)
			requete.close();
		if (laConnexion != null)
			laConnexion.close();

		return verif;

	}

	@Override
	public boolean update(Client cli) throws SQLException, InvalidPropertiesFormatException, IOException {

		boolean verif = false;
		int check = 0;

		Connection laConnexion = Connexion.getInstance().creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
				"UPDATE Client SET nom =?, prenom=?, identifiant=?, mot_de_passe=?, adr_numero=?, adr_voie=?, adr_code_postal=?, adr_ville=?, adr_pays=? WHERE id_client=?");
		requete.setString(1, cli.getNom());
		requete.setString(2, cli.getPrenom());
		requete.setString(3, cli.getIdentifiant());
		requete.setString(4, cli.getMdp());
		requete.setString(5, cli.getNumero());
		requete.setString(6, cli.getRue());
		requete.setString(7, cli.getPostal());
		requete.setString(8, cli.getVille());
		requete.setString(9, cli.getPays());
		requete.setInt(10, cli.getIdclient());
		requete.executeUpdate();

		if(check == 0)
			verif = true;

		if (requete != null)
			requete.close();
		if (laConnexion != null)
			laConnexion.close();

		return verif;
	}

	@Override
	public boolean delete(Client cli) throws SQLException, InvalidPropertiesFormatException, IOException {

		boolean verif = false;
		int check = 0;

		Connection laConnexion = Connexion.getInstance().creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("DELETE FROM Client WHERE id_client=?");
		requete.setInt(1, cli.getIdclient());
		requete.executeUpdate();

		if(check == 0)
			verif = true;

		if (requete != null)
			requete.close();
		if (laConnexion != null)
			laConnexion.close();

		return verif;
	}

	@Override
	public ArrayList<Client> findAll() throws SQLException, InvalidPropertiesFormatException, IOException {

		ArrayList<Client> lcli = new ArrayList<Client>();

		Connection laConnexion = Connexion.getInstance().creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Client");
		ResultSet res = requete.executeQuery();

		while (res.next()) {
			int id_cli = res.getInt("id_client");
			String nom = res.getString("nom");
			String prenom = res.getString("prenom");
			String identifiant = res.getString("identifiant");
			String mdp = res.getString("mot_de_passe");
			String adrnum = res.getString("adr_numero");
			String adrvoie = res.getString("adr_voie");
			String adrcp = res.getString("adr_code_postal");
			String adrville = res.getString("adr_ville");
			String adrpays = res.getString("adr_pays");
			Client concat = new Client(id_cli, nom, prenom, identifiant, mdp, adrnum, adrvoie, adrcp, adrville,
					adrpays);

			lcli.add(concat);
		}

		return lcli;
	}
}
