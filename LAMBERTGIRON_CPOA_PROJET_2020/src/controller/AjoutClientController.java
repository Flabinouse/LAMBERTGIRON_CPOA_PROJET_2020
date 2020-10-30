package controller;

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

public class AjoutClientController implements IAjoutModif<Client> {

	private DAOFactory dao;

	private MainController main;

	private enumAction action;

	private Client cli;

	public void defineTF(String nom, String prenom, String identifiant, String mdp, String numero, 
			String rue, String postal, String ville, String pays) {

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
	
	@Override
	public void create(FXMLLoader fxmlLoader, DAOFactory daoF, Stage stage, MainController mainC, enumAction actionC) {
		try {

			Parent root = (Parent) fxmlLoader.load();

			AjoutClientController param = fxmlLoader.getController();
			param.action = actionC;
			param.dao = daoF;
			param.main = mainC;

			stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Ajout d'un Client");
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void update(FXMLLoader fxmlLoader, DAOFactory daoF, Stage stage, MainController mainC, enumAction actionU,
			Client cliU) {
		try {

			Parent root = (Parent) fxmlLoader.load();

			AjoutClientController param = fxmlLoader.getController();
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
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void validation(ActionEvent event) throws Exception {
		if (action == enumAction.Create) {
			try {
				String nom = this.idTextNom.getText().trim();
				String prenom = this.idTextPrenom.getText().trim();
				String ident = this.idTextIdent.getText().trim();
				String mdp = this.idTextMdp.getText().trim();
				String numero = this.idTextNum.getText().trim();
				String rue = this.idTextRue.getText().trim();
				String postal = this.idTextPostal.getText().trim();
				String ville = this.idTextVille.getText().trim();
				String pays = this.idTextPays.getText().trim();
				Client cliA = new Client();
				cliA.setNom(this.idTextNom.getText().trim());
				cliA.setPrenom(this.idTextPrenom.getText().trim());
				cliA.setIdentifiant(this.idTextIdent.getText().trim());
				cliA.setMdp(this.idTextMdp.getText().trim());
				cliA.setNumero(this.idTextNum.getText().trim());
				cliA.setRue(this.idTextRue.getText().trim());
				cliA.setPostal(this.idTextPostal.getText().trim());
				cliA.setVille(this.idTextVille.getText().trim());
				cliA.setPays(this.idTextPays.getText().trim());

				dao.getClientDAO().create(cliA);

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Ajout d'un Client");
				alert.setHeaderText("Client cree : " + nom + " " + prenom + " " + ident + " " + mdp + " " + numero + " "
						+ rue + " " + postal + " " + ville + " " + pays);
				alert.showAndWait();

				main.tableUpdate();
				Stage stage = (Stage) idBoutonValider.getScene().getWindow();
				stage.close();

			} catch (IllegalArgumentException e) {
				this.idLabelAffi.setStyle("-fx-text-fill: red;");
				this.idLabelAffi.setText("Veuillez saisir tous les champs");
			}
		} else {
			try {
				String nom = this.idTextNom.getText().trim();
				String prenom = this.idTextPrenom.getText().trim();
				String ident = this.idTextIdent.getText().trim();
				String mdp = this.idTextMdp.getText().trim();
				String numero = this.idTextNum.getText().trim();
				String rue = this.idTextRue.getText().trim();
				String postal = this.idTextPostal.getText().trim();
				String ville = this.idTextVille.getText().trim();
				String pays = this.idTextPays.getText().trim();
				cli.setNom(this.idTextNom.getText().trim());
				cli.setPrenom(this.idTextPrenom.getText().trim());
				cli.setIdentifiant(this.idTextIdent.getText().trim());
				cli.setMdp(this.idTextMdp.getText().trim());
				cli.setNumero(this.idTextNum.getText().trim());
				cli.setRue(this.idTextRue.getText().trim());
				cli.setPostal(this.idTextPostal.getText().trim());
				cli.setVille(this.idTextVille.getText().trim());
				cli.setPays(this.idTextPays.getText().trim());

				dao.getClientDAO().update(cli);

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Ajout d'une categorie");
				alert.setHeaderText("Client modifie : " + nom + " " + prenom + " " + ident + " " + mdp + " " + numero + " "
						+ rue + " " + postal + " " + ville + " " + pays);
				alert.showAndWait();

				main.tableUpdate();
				Stage stage = (Stage) idBoutonValider.getScene().getWindow();
				stage.close();

			} catch (IllegalArgumentException e) {
				this.idLabelAffi.setStyle("-fx-text-fill: red;");
				this.idLabelAffi.setText("Veuillez saisir tous les champs");
			}

		}

	}

}
