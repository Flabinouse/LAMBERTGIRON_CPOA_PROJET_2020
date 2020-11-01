package application;

import java.time.LocalDate;

import com.sun.xml.internal.txw2.IllegalAnnotationException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele.dao.factory.DAOFactory;
import modele.metier.Client;
import modele.metier.Commande;
import modele.metier.LigneCommande;
import modele.metier.Produit;

public class CommandeController implements IAjoutModifVisu<Commande> {

	private DAOFactory dao;

	private MainController main;

	private EnumAction action;

	private Commande com;

	@FXML
	private DatePicker dtCom;

	@FXML
	private TextField textQte;

	@FXML
	private TextField textTarifUnit;

	@FXML
	private ChoiceBox<Client> choixClient;

	@FXML
	private ChoiceBox<Produit> choixProduit;

	@FXML
	private Button ajoutProd;

	@FXML
	private Button modifProd;

	@FXML
	private Button supprProd;

	@FXML
	private Button validButton;

	@FXML
	private Button validModif;

	@FXML
	private TableView<LigneCommande> tableProd = new TableView<LigneCommande>();

	@FXML
	TableColumn<LigneCommande, Integer> nomProduit;

	@FXML
	TableColumn<LigneCommande, Integer> qteProd;

	@FXML
	TableColumn<LigneCommande, Integer> tarifUnit;

	@FXML
	private Label idLabelAffi;

	public void defineTF(LocalDate dt, int idclient) throws Exception {

		dtCom.setValue(dt);

		for (Client cli : dao.getClientDAO().findAll()) {
			if (cli.getIdclient() == idclient) {
				choixClient.setValue(cli);
			}
		}

	}

	public void recupeLigneCom(int idcom) throws Exception {
		for (LigneCommande lc : dao.getLigneCommandeDAO().findAll()) {
			if (lc.getIdcom() == idcom) {
				updateTable(lc);
			}
		}
	}

	public void init() throws Exception {

		ObservableList<Produit> listP = FXCollections.observableArrayList(dao.getProduitDAO().findAll());
		this.choixProduit.setItems(listP);

		ObservableList<Client> listC = FXCollections.observableArrayList(dao.getClientDAO().findAll());
		this.choixClient.setItems(listC);
	}

	@SuppressWarnings("unchecked")
	public void updateTable(LigneCommande list) {
		nomProduit.setCellValueFactory(new PropertyValueFactory<LigneCommande, Integer>("idprod"));
		qteProd.setCellValueFactory(new PropertyValueFactory<LigneCommande, Integer>("quantite"));
		tarifUnit.setCellValueFactory(new PropertyValueFactory<LigneCommande, Integer>("tarifunit"));

		this.tableProd.getColumns().setAll(nomProduit, qteProd, tarifUnit);

		this.tableProd.getItems().addAll(list);

	}

	@FXML
	public void selectParam() {
		if (this.tableProd.isFocused() == true) {
			modifProd.setDisable(false);
			supprProd.setDisable(false);
		}
	}

	@FXML
	public void deselectParam() {
		modifProd.setDisable(true);
		supprProd.setDisable(true);
	}

	@FXML
	public void add(ActionEvent event) {
		LigneCommande lc = new LigneCommande();
		lc.setIdprod(this.choixProduit.getValue().getIdproduit());
		lc.setQuantite(Integer.parseInt(this.textQte.getText().trim()));
		lc.setTarifunit(Float.valueOf(this.textTarifUnit.getText().trim()));
		updateTable(lc);
		this.textQte.setText("");
		this.textTarifUnit.setText("");
		choixProduit.setValue(null);
		if (dtCom.getValue() != null && choixClient.getValue() != null) {
			dtCom.setDisable(true);
			choixClient.setDisable(true);
		}
	}

	@FXML
	public void upd(ActionEvent event) throws Exception {

		LigneCommande ligncom = this.tableProd.getSelectionModel().getSelectedItem();
		this.textQte.setText(Integer.toString(ligncom.getQuantite()));

		this.textTarifUnit.setText(Double.toString(ligncom.getTarifunit()));

		for (Produit prod : dao.getProduitDAO().findAll()) {
			if (prod.getIdproduit() == ligncom.getIdprod()) {
				choixProduit.setValue(prod);
			}
		}
		ajoutProd.setDisable(true);
		validModif.setDisable(false);
	}

	@FXML
	public void validModif(ActionEvent event) {
		LigneCommande remove = this.tableProd.getSelectionModel().getSelectedItem();
		this.tableProd.getItems().remove(remove);
		LigneCommande ligncom = new LigneCommande();
		ligncom.setIdprod(this.choixProduit.getValue().getIdproduit());
		ligncom.setQuantite(Integer.parseInt(this.textQte.getText().trim()));
		ligncom.setTarifunit(Float.valueOf(this.textTarifUnit.getText().trim()));
		updateTable(ligncom);
		validModif.setDisable(true);
		ajoutProd.setDisable(false);
	}

	@FXML
	public void del(ActionEvent event) {
		LigneCommande remove = this.tableProd.getSelectionModel().getSelectedItem();
		this.tableProd.getItems().remove(remove);
		modifProd.setDisable(true);
		supprProd.setDisable(true);
		validModif.setDisable(true);

	}

	@Override
	public void create(FXMLLoader fxmlLoader, DAOFactory daoF, Stage stage, MainController mainC, EnumAction actionC) {

		try {

			Parent root = (Parent) fxmlLoader.load();

			CommandeController param = fxmlLoader.getController();
			param.action = actionC;
			param.dao = daoF;
			param.main = mainC;
			param.init();
			param.modifProd.setDisable(true);
			param.supprProd.setDisable(true);
			param.validModif.setDisable(true);

			stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Ajout d'une commande");
			stage.setResizable(false);
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void verifDoublon(Commande commande) throws Exception {
		for (Commande com : dao.getCommandeDAO().findAll()) {
			commande.setIdcom(com.getIdcom());
			if (commande.getDatecom().equals(com.getDatecom()) && commande.getIdcli() == (com.getIdcli())) {
				throw new IllegalAnnotationException("Commande deja existante !");
			}
		}
	}

	@Override
	public void update(FXMLLoader fxmlLoader, DAOFactory daoF, Stage stage, MainController mainC, EnumAction actionU,
			Commande comU) {
		try {

			Parent root = (Parent) fxmlLoader.load();

			CommandeController param = fxmlLoader.getController();
			param.action = actionU;
			param.dao = daoF;
			param.main = mainC;
			param.com = comU;
			param.init();
			param.modifProd.setDisable(true);
			param.supprProd.setDisable(true);
			param.validModif.setDisable(true);

			int idcom = comU.getIdcom();
			LocalDate dt = comU.getDatecom();
			int idcli = comU.getIdcli();

			param.recupeLigneCom(idcom);
			param.defineTF(dt, idcli);

			stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Modification d'une commande");
			stage.setResizable(false);
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void visualisation(FXMLLoader fxmlLoader, DAOFactory daoF, Stage stage, MainController mainC,
			EnumAction actionV, Commande comV) {
		try {

			Parent root = (Parent) fxmlLoader.load();

			CommandeController param = fxmlLoader.getController();
			param.action = actionV;
			param.dao = daoF;
			param.main = mainC;
			param.init();
			param.ajoutProd.setDisable(true);
			param.modifProd.setDisable(true);
			param.supprProd.setDisable(true);
			param.validModif.setDisable(true);
			param.textQte.setDisable(true);
			param.textTarifUnit.setDisable(true);
			param.dtCom.setDisable(true);
			param.choixClient.setDisable(true);
			param.choixProduit.setDisable(true);
			param.tableProd.setDisable(true);

			int idcom = comV.getIdcom();
			LocalDate dt = comV.getDatecom();
			int idcli = comV.getIdcli();

			param.recupeLigneCom(idcom);
			param.defineTF(dt, idcli);

			stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Visualisation d'une commande");
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
				if (this.choixClient.getValue() == null) {
					throw new IllegalArgumentException("Client non saisie !");
				}
				int idclient = this.choixClient.getValue().getIdclient();
				LocalDate dt = this.dtCom.getValue();
				Commande comV = new Commande();
				comV.setIdcli(idclient);
				comV.setDatecom(dt);

				verifDoublon(comV);
				dao.getCommandeDAO().create(comV);

				for (Commande searchcom : dao.getCommandeDAO().findAll()) {
					if (searchcom.getIdcom() == comV.getIdcom()) {
						int idcom = searchcom.getIdcom();

						for (LigneCommande eachligne : this.tableProd.getItems()) {
							int qte = eachligne.getQuantite();
							Double tarif = eachligne.getTarifunit();
							int idproduit = eachligne.getIdprod();
							LigneCommande ligncom = new LigneCommande();
							ligncom.setIdcom(idcom);
							ligncom.setIdprod(idproduit);
							ligncom.setQuantite(qte);
							ligncom.setTarifunit(tarif);
							dao.getLigneCommandeDAO().create(ligncom);
						}

					}
				}

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Ajout d'une commande");
				alert.setHeaderText("Commande creer pour : " + this.choixClient.getValue().getNom() + " "
						+ this.choixClient.getValue().getPrenom());
				alert.showAndWait();
				main.tableUpdate();
				Stage stage = (Stage) validButton.getScene().getWindow();
				stage.close();

			} catch (IllegalArgumentException e) {
				this.idLabelAffi.setStyle("-fx-text-fill: red;");
				this.idLabelAffi.setText("Veuillez saisir tous les champs");
			} catch (IllegalAnnotationException e1) {
				this.idLabelAffi.setStyle("-fx-text-fill: red;");
				this.idLabelAffi.setText("Cette commande existe deja !");
			}
		} else if (action == EnumAction.Update) {
			try {
				int idclient = this.choixClient.getValue().getIdclient();
				LocalDate dt = this.dtCom.getValue();

				com.setIdcli(idclient);
				com.setDatecom(dt);

				dao.getCommandeDAO().update(com);

				for (Commande searchcom : dao.getCommandeDAO().findAll()) {
					if (searchcom.getIdcom() == com.getIdcom()) {
						int idcom = searchcom.getIdcom();

						for (LigneCommande lc2 : dao.getLigneCommandeDAO().findAll()) {
							if (lc2.getIdcom() == idcom) {
								dao.getLigneCommandeDAO().delete(lc2);
							}
						}

						for (LigneCommande eachligne : this.tableProd.getItems()) {
							int qte = eachligne.getQuantite();
							Double tarif = eachligne.getTarifunit();
							int idproduit = eachligne.getIdprod();
							LigneCommande ligncom = new LigneCommande();
							ligncom.setIdcom(idcom);
							ligncom.setIdprod(idproduit);
							ligncom.setQuantite(qte);
							ligncom.setTarifunit(tarif);
							dao.getLigneCommandeDAO().create(ligncom);
						}

					}
				}

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Ajout d'une commande");
				alert.setHeaderText("Commande modifier pour : " + this.choixClient.getValue().getNom() + " "
						+ this.choixClient.getValue().getPrenom());
				alert.showAndWait();
				main.tableUpdate();
				Stage stage = (Stage) validButton.getScene().getWindow();
				stage.close();

			} catch (IllegalArgumentException e) {
				this.idLabelAffi.setStyle("-fx-text-fill: red;");
				this.idLabelAffi.setText("Veuillez saisir tous les champs");
			}
		} else {
			Stage stage = (Stage) validButton.getScene().getWindow();
			stage.close();
		}

	}

}
