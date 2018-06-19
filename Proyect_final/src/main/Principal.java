package main;

import java.awt.EventQueue;

import controlador.AlumnoControlador;
import vista.GuiPrincipal;

public class Principal {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiPrincipal vista = new GuiPrincipal();
					new AlumnoControlador(vista);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		// actualizarAlumno();
		// consultar();
		 
		//TablaAlumno tabla = new TablaAlumno();
//		tabla.crearTableGUI();
//		consultarSexoNombre();
//		AlumnoControlador controlador = new AlumnoControlador();
//		for (int i =1; i <101;i++) {
//			
//			controlador.borrarAlumno(i);
//		}
//		
//		controlador.readCSV();
//		
//		listarTodos();
	}
/*
	public static void consultar() {
		int id = 1;
		AlumnoControlador controlador = new AlumnoControlador();
		System.out.println(controlador.consultaID(id));
	}

	public static void listarTodos() {
		AlumnoControlador controlador = new AlumnoControlador();
		System.out.println(controlador.devolverTodos());
	}
	
	public static void consultarSexoNombre() {
		String sexo = "Mujer";
		String nombre = "";
		AlumnoControlador controlador = new AlumnoControlador();
	}
	
	public static void actualizarAlumno() {

		AlumnoDTO alumno = new AlumnoDTO(1, "Paco", "Jimenez", "csutheran0@opensource.org", Sexo.HOMBRE);
		AlumnoControlador controlador = new AlumnoControlador();
		System.out.println(controlador.updateAlumno(alumno));
	}
*/
	
}
