package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAOImpl implements AlumnoDAO {

	private static Connection conexion = Conexion.getConexion();

	@Override
	public boolean createAlumno(AlumnoDTO alumno) {

		int total = 0;

		String sql = "INSERT INTO alumno (id, nombre, apellidos, email, genero)" + " VALUES (?,?,?,?,?);";
		try (PreparedStatement psStatement = conexion.prepareStatement(sql);) {

			psStatement.setInt(1, alumno.getId());
			psStatement.setString(2, alumno.getNombre());
			psStatement.setString(3, alumno.getApellidos());
			psStatement.setString(4, alumno.getEmail());
			psStatement.setString(5, alumno.getGenero().name());

			total = psStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total == 1;

	}

	@Override
	public boolean createDatabase() {
		try {

			Statement statement = conexion.createStatement();
			statement
					.execute("CREATE TABLE IF NOT EXISTS " + "alumno (id INTEGER PRIMARY KEY, " + "nombre VARCHAR2(20),"
							+ "apellidos VARCHAR2(40)," + "email VARCHAR2(50)," + "genero VARCHAR2(10) )");

			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateAlumno(AlumnoDTO alumnoUpdated) {
		
		int actualizar = 0;
		String sql = "UPDATE alumno SET nombre = ? , apellidos = ? ,email = ?, genero = ? WHERE id = ?;";

		try {
			PreparedStatement preparedStatement = conexion.prepareStatement(sql);
			preparedStatement.setString(1, alumnoUpdated.getNombre());
			preparedStatement.setString(2, alumnoUpdated.getApellidos());
			preparedStatement.setString(3, alumnoUpdated.getEmail());
			preparedStatement.setString(4, alumnoUpdated.getGenero().name());
			preparedStatement.setInt(5, alumnoUpdated.getId());
			
			actualizar = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actualizar != 0;
	}

	@Override
	public AlumnoDTO consultarAlumno(int id) {
		String sql = "SELECT * FROM alumno WHERE id = ?; ";
		AlumnoDTO alumno = null;
		try {
			PreparedStatement preparedStatement = conexion.prepareStatement(sql);
			preparedStatement.setInt(1, id);


			ResultSet resulset = preparedStatement.executeQuery();
			
			while (resulset.next()) {
				Sexo sexo = Sexo.MUJER;
				if(resulset.getString(5).equals("HOMBRE")) {
					sexo = Sexo.HOMBRE;
				}else {
					sexo = Sexo.MUJER;
				}
				alumno = new AlumnoDTO(resulset.getInt(1), resulset.getString(2), resulset.getString(3), resulset.getString(4), sexo );
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return alumno;
	}

	@Override
	public List<AlumnoDTO> listarAlumnos() {
		List<AlumnoDTO> listaAlumnos = new ArrayList<>();
		String sql = "SELECT * FROM alumno;";
		try{
			Statement statement = conexion.createStatement();
			ResultSet resulset = statement.executeQuery(sql);
			while (resulset.next()) {
				Sexo sexo = Sexo.MUJER;
				System.out.println(resulset.getString(5));
				if(resulset.getString(5).toUpperCase().equals("HOMBRE")) {
					sexo = Sexo.HOMBRE;
				}else {
					sexo = Sexo.MUJER;
				}
				AlumnoDTO alumno = new AlumnoDTO(resulset.getInt(1), resulset.getString(2), resulset.getString(3), resulset.getString(4), sexo );
				listaAlumnos.add(alumno);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaAlumnos;
	}

	@Override
	public List<AlumnoDTO> consultarAlumnoSexoNombre(String sexo, String nombre) {
		String sql = "SELECT * FROM alumno WHERE genero = ? and nombre like ?; ";
		List<AlumnoDTO> alumnos = new ArrayList<AlumnoDTO>();
		try {
			PreparedStatement preparedStatement = conexion.prepareStatement(sql);
			preparedStatement.setString(1, sexo.toUpperCase());
			preparedStatement.setString(2, "%" + nombre + "%");

			ResultSet resulset = preparedStatement.executeQuery();
			
			while (resulset.next()) {
				Sexo sexoAlumno = Sexo.MUJER;
				if(resulset.getString(5).equals("HOMBRE")) {
					sexoAlumno = Sexo.HOMBRE;
				}else {
					sexoAlumno = Sexo.MUJER;
				}
				AlumnoDTO alumno = new AlumnoDTO(resulset.getInt(1), resulset.getString(2), resulset.getString(3), resulset.getString(4), sexoAlumno );
				alumnos.add(alumno);
			
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return alumnos;
	}

	@Override
	public boolean deleteAlumno(int id) {

		int eliminados = 0;
		String sql = "DELETE FROM alumno WHERE id = ?;";

		try {
			PreparedStatement preparedStatement = conexion.prepareStatement(sql);
			preparedStatement.setInt(1, id); 
			eliminados = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eliminados != 0;
	}

}
