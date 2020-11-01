package application;

import com.sun.xml.internal.txw2.IllegalAnnotationException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele.dao.factory.DAOFactory;
import modele.metier.Client;

public class ClientController implements IAjoutModifVisu<Client> {

	private DAOFactory dao;

	private MainController main;

	private EnumAction action;

	private Client cli;

	@FXML
	private TextField idTextNom;

	@FXML
	private TextField idTextPrenom;

	@FXML
	private TextField idTextIdent;

	@FXML
	private TextField idTextMdp;

	@FXML
	private TextField idTextNum;

	@FXML
	private TextField idTextRue;

	@FXML
	private TextField idTextPostal;

	@FXML
	private TextField idTextVille;

	@FXML
	private TextField idTextPays;

	@FXML
	private Button idBoutonValider;

	@FXML
	private Label idLabelAffi;

	public void defineTF(String nom, String prenom, String identifiant, String mdp, String numero, String rue,
			String postal, String ville, String pays) {

		idTextNom.setText(nom);

		idTextPrenom.setText(prenom);

		idTextIdent.setText(identifiant);

		idTextMdp.setText(mdp);

		idTextNum.setText(numero);

		idTextRue.setText(rue);

		idTextPostal.setText(postal);

		idTextVille.setText(ville);

		idTextPays.setText(pays);
	}

	@Override
	public void create(FXMLLoader fxmlLoader, DAOFactory daoF, Stage stage, MainController mainC, EnumAction actionC) {
		try {

			Parent root = (Parent) fxmlLoader.load();

			ClientController param = fxmlLoader.getController();
			param.action = actionC;
			param.dao = daoF;
			param.main = mainC;

			stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Ajout d'un Client");
			stage.setResizable(false);
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(FXMLLoader fxmlLoader, DAOFactory daoF, Stage stage, MainController mainC, EnumAction actionU,
			Client cliU) {
		try {

			Parent root = (Parent) fxmlLoader.load();

			ClientController param = fxmlLoader.getController();
			param.action = actionU;
			param.dao = daoF;
			param.main = mainC;
			param.cli = cliU;

			String nom = cliU.getNom();
			String prenom = cliU.getPrenom();
			String identifiant = cliU.getIdentifiant();
			String mdp = cliU.getMdp();
			String numero = cliU.getNumero();
			String rue = cliU.getRue();
			String postal = cliU.getPostal();
			String ville = cliU.getVille();
			String pays = cliU.getPays();

			param.defineTF(nom, prenom, identifiant, mdp, numero, rue, postal, ville, pays);

			stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Modification d'un Client");
			stage.setResizable(false);
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifDoublon(Client client) throws Exception {
		for (Client cli : dao.getClientDAO().findAll()) {
			client.setIdclient(cli.getIdclient());
			if (client.getNom().equals(cli.getNom()) && client.getPrenom().equals(cli.getPrenom())
					&& client.getIdentifiant().equals(cli.getIdentifiant())) {
				throw new IllegalAnnotationException("Client deja existant !");
			}
		}
	}

	@Override
	public void visualisation(FXMLLoader fxmlLoader, DAOFactory daoF, Stage stage, MainController mainC,
			EnumAction actionV, Client cliV) {
		try {

			Parent root = (Parent) fxmlLoader.load();

			ClientController param = fxmlLoader.getController();
			param.action = actionV;
			param.dao = daoF;
			param.main = mainC;

			String nom = cliV.getNom();
			String prenom = cliV.getPrenom();
			String identifiant = cliV.getIdentifiant();
			String mdp = cliV.getMdp();
			String numero = cliV.getNumero();
			String rue = cliV.getRue();
			String postal = cliV.getPostal();
			String ville = cliV.getVille();
			String pays = cliV.getPays();

			param.defineTF(nom, prenom, identifiant, mdp, numero, rue, postal, ville, pays);
			param.idTextIdent.setDisable(true);
			param.idTextMdp.setDisable(true);
			param.idTextNom.setDisable(true);
			param.idTextNum.setDisable(true);
			param.idTextPays.setDisable(true);
			param.idTextPostal.setDisable(true);
			param.idTextPrenom.setDisable(true);
			param.idTextRue.setDisable(true);
			param.idTextVille.setDisable(true);

			stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Visualisation d'un Client");
			stage.setResizable(false);
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	public void validation(ActionEvent event) throws Exception {
		if (action == EnumAction.Create) {
			try {
				String nom = this.idTextNom.getText().trim().toUpperCase();
				String prenom = this.idTextPrenom.getText().trim();
				Client cliA = new Client();
				cliA.setNom(nom);
				cliA.setPrenom(prenom);
				cliA.setIdentifiant(this.idTextIdent.getText().trim());
				cliA.setMdp(this.idTextMdp.getText().trim());
				cliA.setNumero(this.idTextNum.getText().trim());
				cliA.setRue(this.idTextRue.getText().trim().toLowerCase());
				cliA.setPostal(this.idTextPostal.getText().trim());
				cliA.setVille(this.idTextVille.getText().trim());
				cliA.setPays(this.idTextPays.getText().trim());

				verifDoublon(cliA);
				dao.getClientDAO().create(cliA);

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Ajout d'un Client");
				alert.setHeaderText("Client creer : " + nom + " " + prenom);
				alert.showAndWait();

				main.tableUpdate();
				Stage stage = (Stage) idBoutonValider.getScene().getWindow();
				stage.close();

			} catch (IllegalArgumentException e) {
				this.idLabelAffi.setStyle("-fx-text-fill: red;");
				this.idLabelAffi.setText("Veuillez saisir tous les champs");
			} catch (IllegalAnnotationException e1) {
				this.idLabelAffi.setStyle("-fx-text-fill: red;");
				this.idLabelAffi.setText("Ce client existe deja !");
			}
		} else if (action == EnumAction.Update) {
			try {
				String nom = this.idTextNom.getText().trim();
				String prenom = this.idTextPrenom.getText().trim();
				cli.setNom(nom);
				cli.setPrenom(prenom);
				cli.setIdentifiant(this.idTextIdent.getText().trim());
				cli.setMdp(this.idTextMdp.getText().trim());
				cli.setNumero(this.idTextNum.getText().trim());
				cli.setRue(this.idTextRue.getText().trim());
				cli.setPostal(this.idTextPostal.getText().trim());
				cli.setVille(this.idTextVille.getText().trim());
				cli.setPays(this.idTextPays.getText().trim());

				dao.getClientDAO().update(cli);

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("modification d'un client");
				alert.setHeaderText("Client modifier : " + nom + " " + prenom);
				alert.showAndWait();

				main.tableUpdate();
				Stage stage = (Stage) idBoutonValider.getScene().getWindow();
				stage.close();

			} catch (IllegalArgumentException e) {
				this.idLabelAffi.setStyle("-fx-text-fill: red;");
				this.idLabelAffi.setText("Veuillez saisir tous les champs");
			}

		} else {
			Stage stage = (Stage) idBoutonValider.getScene().getWindow();
			stage.close();
		}

	}

}
