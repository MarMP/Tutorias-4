package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;

import java.io.IOException;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControladorVentanaPrincipal {

	private IControlador controladorMVC;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}
	//Variables de fxml ventana principal
	@FXML Button btnAnadirProfesor;
	@FXML Button btnBuscarProfesor;
	@FXML Button btnBorrarProfesor;
	@FXML Button btnListarProfesores;
	
	private Stage anadirProfesor;
    private ControladorAnadirProfesor cAnadirProfesor;
    private Stage mostrarProfesor;
    private ControladorMostrarProfesor cMostrarProfesor;
    private Stage listarProfesores;
    private ControladorListarProfesores cListarProfesores;
    
	@FXML
	void salir() {
		if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro de que quieres salir de la aplicación?",
				null)) {
			controladorMVC.terminar();
			System.exit(0);
		}
	}

	@FXML
	void acercaDe() throws IOException {
		VBox contenido = FXMLLoader.load(getClass().getResource("../vistas/AcercaDe.fxml"));
		Dialogos.mostrarDialogoInformacionPersonalizado("Tutorias", contenido);
	}

	@FXML
	private void anadirProfesor() throws IOException {
		crearAnadirProfesor();
		anadirProfesor.showAndWait();
	}

	@FXML
	private void buscarProfesor() {
		String dni = Dialogos.mostrarDialogoTexto("Buscar Profesor", "Correo:");
		if (dni != null) {
			Profesor profesor = null;
			try {
				profesor = controladorMVC.buscar(Profesor.getProfesorFicticio(dni));
				if (profesor != null) {
					crearMostrarProfesor(profesor);
					mostrarProfesor.showAndWait();
				} else {
					Dialogos.mostrarDialogoError("Profesor no encontrado", "No existe ningún profesor con ese DNI");
				}
			} catch (Exception e) {
				Dialogos.mostrarDialogoError("El DNI no es válido", e.getMessage());
			}
		}
	}

	@FXML
	private void borrarProfesor() {
		String dni = Dialogos.mostrarDialogoTexto("Buscar profesor", "DNI:");
		if (dni != null) {
			Profesor profesor = null;
			try {
				profesor = controladorMVC.buscar(Profesor.getProfesorFicticio(dni));
				if (profesor != null) {
					if (Dialogos.mostrarDialogoConfirmacion("Confirmar",
							"¿Estás seguro de que desea eliminar al profesor:  " + profesor + "?", null)) {
						controladorMVC.borrar(profesor);
						Dialogos.mostrarDialogoAdvertencia("", "El profesor se ha eliminado correctamente");
					}
				} else {
					Dialogos.mostrarDialogoError("El profesor no se ha encontrado",
							"No existe ningún profesor con ese DNI");
				}
			} catch (Exception e) {
				Dialogos.mostrarDialogoError("DNI no válido", e.getMessage());
			}
		}
	}
	
	@FXML
	private void listarProfesores() throws IOException {
		crearListarProfesores();
		listarProfesores.showAndWait();
	}

	private void crearAnadirProfesor() throws IOException {
		if (anadirProfesor == null) {
			anadirProfesor = new Stage();
			FXMLLoader cargadorAnadirProfesor = new FXMLLoader(getClass().getResource("../vistas/AnadirProfesor.fxml"));
			VBox raizAnadirProfesor = cargadorAnadirProfesor.load();
			cAnadirProfesor = cargadorAnadirProfesor.getController();
			cAnadirProfesor.setControladorMVC(controladorMVC);
			cAnadirProfesor.inicializa();
			Scene escenaAnadirProfesor = new Scene(raizAnadirProfesor);
			anadirProfesor.setTitle("Añadir Profesor");
			anadirProfesor.initModality(Modality.APPLICATION_MODAL);
			anadirProfesor.setScene(escenaAnadirProfesor);
		} else {
			cAnadirProfesor.inicializa();
		}
	}

	private void crearMostrarProfesor(Profesor profesor) throws IOException {
		if (mostrarProfesor == null) {
			mostrarProfesor = new Stage();
			FXMLLoader cargadorMostrarProfesor = new FXMLLoader(
					getClass().getResource("../vistas/MostrarProfesor.fxml"));
			VBox raizMostrarProfesor = cargadorMostrarProfesor.load();
			cMostrarProfesor = cargadorMostrarProfesor.getController();
			cMostrarProfesor.setControladorMVC(controladorMVC);
			cMostrarProfesor.setProfesor(profesor);
			Scene escenaMostrarProfesor = new Scene(raizMostrarProfesor);
			mostrarProfesor.setTitle("Mostrar Profesor");
			mostrarProfesor.initModality(Modality.APPLICATION_MODAL);
			mostrarProfesor.setScene(escenaMostrarProfesor);
		} else {
			cMostrarProfesor.setProfesor(profesor);
		}
	}
	
	private void crearListarProfesores() throws IOException {
		if (listarProfesores == null) {
			listarProfesores = new Stage();
			FXMLLoader cargadorListarProfesores = new FXMLLoader(
						getClass().getResource("../vistas/ListarProfesores.fxml"));
			VBox raizListarProfesores = cargadorListarProfesores.load();
			cListarProfesores = cargadorListarProfesores.getController();
			cListarProfesores.setControladorMVC(controladorMVC);
			cListarProfesores.inicializa();
			Scene escenaListarProfesores = new Scene(raizListarProfesores);
			listarProfesores.setTitle("Listar Profesores");
			listarProfesores.initModality(Modality.APPLICATION_MODAL); 
			listarProfesores.setScene(escenaListarProfesores);
		} else {
			cListarProfesores.inicializa();
		}
	}
	

	
	

}
