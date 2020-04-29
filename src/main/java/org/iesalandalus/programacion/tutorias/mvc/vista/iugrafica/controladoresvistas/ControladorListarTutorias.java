package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ControladorListarTutorias {
	
	private IControlador controladorMVC;
		
	@FXML private TableColumn<Tutoria, String> tcProfesor;
    @FXML private TableColumn<Tutoria, String> tcTutoria;
    @FXML private TableView<Tutoria> tvTutorias;
    @FXML private Button btnAceptar;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	public void inicializa() {
		tvTutorias.setItems(FXCollections.observableArrayList(controladorMVC.getTutorias()));
	}

	@FXML
	private void initialize() {
		tcProfesor.setCellValueFactory(tutoria -> new SimpleStringProperty(tutoria.getValue().getProfesor().getNombre()));
		tcTutoria.setCellValueFactory(tutoria -> new SimpleStringProperty(tutoria.getValue().getNombre()));
	}

	@FXML
	private void aceptar() {
		Stage escena = (Stage) btnAceptar.getScene().getWindow();
		escena.close();
	}

}
