package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;

import java.io.IOException;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
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
	@FXML Button btnAnadir;
	@FXML Button btnBuscar;
	@FXML Button btnBorrar;
	@FXML Button btnListar;
	@FXML Button btnAnadirAlumno;
	@FXML Button btnBuscarAlumno;
	@FXML Button btnBorrarAlumno;
	@FXML Button btnListarAlumnos;
	
	private Stage anadirProfesor;
    private ControladorAnadirProfesor cAnadirProfesor;
    private Stage mostrarProfesor;
    private ControladorMostrarProfesor cMostrarProfesor;
    private Stage listarProfesores;
    private ControladorListarProfesores cListarProfesores;
    private Stage anadirAlumno;
    private ControladorAnadirAlumno cAnadirAlumno;
    private Stage mostrarAlumno;
    private ControladorMostrarAlumno cMostrarAlumno;
    private Stage listarAlumnos;
    private ControladorListarAlumnos cListarAlumnos;
    
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

	// PROFESORES
	@FXML
	private void anadirProfesor() throws IOException {
		crearAnadirProfesor();
		anadirProfesor.showAndWait();
	}

	@FXML
	private void buscarProfesor() {
		String dni = Dialogos.mostrarDialogoTexto("Buscar Profesor", "DNI:");
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

	// ALUMNOS
	@FXML
	private void anadirAlumno() throws IOException {
		crearAnadirAlumno();
		anadirAlumno.showAndWait();
	}

	@FXML
	private void buscarAlumno() {
		String correo = Dialogos.mostrarDialogoTexto("Buscar Alumno", "Correo:");
		if (correo != null) {
			Alumno alumno = null;
			try {
				alumno = controladorMVC.buscar(Alumno.getAlumnoFicticio(correo));
				if (alumno != null) {
					crearMostrarAlumno(alumno);
					mostrarAlumno.showAndWait();
				} else {
					Dialogos.mostrarDialogoError("Alumno no encontrado", "No existe ningún alumno con ese correo");
				}
			} catch (Exception e) {
				Dialogos.mostrarDialogoError("El formato del correo no es válido", e.getMessage());
			}
		}
	}

	@FXML
	private void borrarAlumno() {
		String correo = Dialogos.mostrarDialogoTexto("Buscar alumno", "Correo:");
		if (correo != null) {
			Alumno alumno = null;
			try {
				alumno = controladorMVC.buscar(Alumno.getAlumnoFicticio(correo));
				if (alumno != null) {
					if (Dialogos.mostrarDialogoConfirmacion("Confirmar",
							"¿Estás seguro de que desea eliminar al alumno:  " + alumno + "?", null)) {
						controladorMVC.borrar(alumno);
						Dialogos.mostrarDialogoAdvertencia("", "El alumno se ha eliminado correctamente");
					}
				} else {
					Dialogos.mostrarDialogoError("El alumnono no se ha encontrado",
							"No existe ningún alumno con ese correo");
				}
			} catch (Exception e) {
				Dialogos.mostrarDialogoError("Correo no válido", e.getMessage());
			}
		}
	}

	@FXML
	private void listarAlumnos() throws IOException {
		crearListarAlumnos();
		listarAlumnos.showAndWait();
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

	private void crearAnadirAlumno() throws IOException {
		if (anadirAlumno == null) {
			anadirAlumno = new Stage();
			FXMLLoader cargadorAnadirAlumno = new FXMLLoader(getClass().getResource("../vistas/AnadirAlumnos.fxml"));
			VBox raizAnadirAlumno = cargadorAnadirAlumno.load();
			cAnadirAlumno = cargadorAnadirAlumno.getController();
			cAnadirAlumno.setControladorMVC(controladorMVC);
			cAnadirAlumno.inicializa();
			Scene escenaAnadirAlumno = new Scene(raizAnadirAlumno);
			anadirAlumno.setTitle("Añadir Alumno");
			anadirAlumno.initModality(Modality.APPLICATION_MODAL);
			anadirAlumno.setScene(escenaAnadirAlumno);
		} else {
			cAnadirAlumno.inicializa();
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

	private void crearMostrarAlumno(Alumno alumno) throws IOException {
		if (mostrarAlumno == null) {
			mostrarAlumno = new Stage();
			FXMLLoader cargadorMostrarAlumno = new FXMLLoader(getClass().getResource("../vistas/MostrarAlumno.fxml"));
			VBox raizMostrarAlumno = cargadorMostrarAlumno.load();
			cMostrarAlumno = cargadorMostrarAlumno.getController();
			cMostrarAlumno.setControladorMVC(controladorMVC);
			cMostrarAlumno.setAlumno(alumno);
			Scene escenaMostrarAlumno = new Scene(raizMostrarAlumno);
			mostrarAlumno.setTitle("Mostrar Alumno");
			mostrarAlumno.initModality(Modality.APPLICATION_MODAL);
			mostrarAlumno.setScene(escenaMostrarAlumno);
		} else {
			cMostrarAlumno.setAlumno(alumno);
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

	private void crearListarAlumnos() throws IOException {
		if (listarAlumnos == null) {
			listarAlumnos = new Stage();
			FXMLLoader cargadorListarAlumnos = new FXMLLoader(getClass().getResource("../vistas/ListarAlumnos.fxml"));
			VBox raizListarAlumnos = cargadorListarAlumnos.load();
			cListarAlumnos = cargadorListarAlumnos.getController();
			cListarAlumnos.setControladorMVC(controladorMVC);
			cListarAlumnos.inicializa();
			Scene escenaListarAlumnos = new Scene(raizListarAlumnos);
			listarAlumnos.setTitle("Listar Alumnos");
			listarAlumnos.initModality(Modality.APPLICATION_MODAL);
			listarAlumnos.setScene(escenaListarAlumnos);
		} else {
			cListarAlumnos.inicializa();
		}
	}

}
