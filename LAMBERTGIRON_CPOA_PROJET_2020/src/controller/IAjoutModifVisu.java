package controller;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import modele.dao.factory.DAOFactory;

public interface IAjoutModifVisu<T> {

	public abstract void create(FXMLLoader fxmlLoader, DAOFactory daoF, Stage stage, MainController mainC,
			enumAction action);

	public abstract void update(FXMLLoader fxmlLoader, DAOFactory daoF, Stage stage, MainController mainC,
			enumAction action, T objet);

	public abstract void visualisation(FXMLLoader fxmlLoader, DAOFactory daoF, Stage stage, MainController mainC,
			enumAction action, T objet);

}