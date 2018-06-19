package modelo;

import java.util.List;

public interface AlumnoDAO {

	boolean createDatabase();
	boolean createAlumno(AlumnoDTO alumnoNuevo);
	boolean updateAlumno(AlumnoDTO alumnoUpdated);
	AlumnoDTO consultarAlumno(int id);
	List<AlumnoDTO> listarAlumnos();
	List<AlumnoDTO> consultarAlumnoSexoNombre(String sexo, String nombre);
	boolean deleteAlumno(int id);
}
