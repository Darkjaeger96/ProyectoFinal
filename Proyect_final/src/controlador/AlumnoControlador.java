package controlador;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.csvreader.CsvReader;

import modelo.AlumnoDAO;
import modelo.AlumnoDAOImpl;
import modelo.AlumnoDTO;
import modelo.AnnadirExcepcion;
import modelo.Logs;
import modelo.Sexo;
import vista.GuiPrincipal;
import vista.TablaAlumno;

public class AlumnoControlador {

	private AlumnoDAO alumnoDao = new AlumnoDAOImpl();
	private GuiPrincipal vista;
	private List<AlumnoDTO> listadoAlumnos = new ArrayList<>();
	private TablaAlumno tablaAlumno = new TablaAlumno();
	
	public AlumnoControlador(GuiPrincipal vista) {
		this.vista = vista;
		refreshTabla();
		registerListener();
	}
	
	public void refreshTabla() {
		tablaAlumno.clearTable();
		
		for (Component component : vista.getPanelTabla().getComponents()) {
			vista.getPanelTabla().remove(component);  
		}

		vista.getPanelTabla().revalidate();
		vista.getPanelTabla().repaint();
		vista.getPanelTabla().add(tablaAlumno.construyeUI(listadoAlumnos));
	}
		
		
		
		
	
	
	public void registerListener() {
		
		vista.getBtnBuscar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listadoAlumnos = consultarSexoNombre(vista.getRdbtnHombre().isSelected(), vista.getRdbtnMujer().isSelected(), vista.getCampoNombre().getText());
				refreshTabla();
			}
		});
		
		vista.getBtnEliminar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Integer> listaIdsEliminar = tablaAlumno.eliminarAlumnos();
				
				for (int id: listaIdsEliminar) {
					alumnoDao.deleteAlumno(id);
					Logs.crearLog("ELIMINAR", String.valueOf(id));
					listadoAlumnos  = new ArrayList<>();
					refreshTabla();
				}
				
			}
		});
		
		vista.getBtnAadir().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(vista.getCampoId().getText().length() == 0 || vista.getCampoNombreAdd().getText().length() == 0 || vista.getCampoApellidos().getText().length() == 0 || vista.getCampoEmail().getText().length() == 0 || vista.getCajaGenero().getActionCommand().length() == 0) {
					
					JOptionPane jpJOptionPane = new JOptionPane();
					jpJOptionPane.showMessageDialog(vista.getFrame(),
							"Existen campos sin completar.", "Error al añadir", 
							JOptionPane.INFORMATION_MESSAGE);
				}else {
					
					try {
						refreshTabla();
						boolean esCreado = annadirAlumno(vista.getCampoId().getText(), vista.getCampoNombreAdd().getText(), vista.getCampoApellidos().getText(), vista.getCampoEmail().getText(), (String) vista.getCajaGenero().getSelectedItem());
						AlumnoDTO alumno = alumnoDao.consultarAlumno(Integer.parseInt(vista.getCampoId().getText()));
						listadoAlumnos =  new ArrayList<>();
						listadoAlumnos.add(alumno);
						
						refreshTabla();
						Logs.crearLog("CREAR", vista.getCampoId().getText());
						
					} catch (AnnadirExcepcion e1) {
						e1.printStackTrace();
						
						JOptionPane jpJOptionPane = new JOptionPane();
						jpJOptionPane.showMessageDialog(vista.getFrame(),
								"El id '" + vista.getCampoId().getText() + "' ya existe.", "Error al añadir", 
								JOptionPane.INFORMATION_MESSAGE);
					}
					
						
					
				}
			}
		});
		
		vista.getMntmNewMenuItem().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		
		vista.getMntmCrditos().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hemos pulsado los creditos");
				JOptionPane jpJOptionPane = new JOptionPane();
				jpJOptionPane.showMessageDialog(vista.getFrame(), "Creado por JVG.", "Créditos", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
	
	public boolean createBD() {
		return alumnoDao.createDatabase();
	}
	
	public void readCSV() {
		try {
            CsvReader alumnos = new CsvReader("bd/DataColegio.csv",',');
            alumnos.readHeaders();
            while (alumnos.readRecord())
            {
                int alumnoID = Integer.parseInt(alumnos.get("id"));
                String alumnoNombre = alumnos.get("Nombre");
                String alumnoApellido = alumnos.get("Apellido");
                String alumnoEmail = alumnos.get("email");
                String alumnoGenero = alumnos.get("Genero");
                System.out.println(alumnoGenero);
                Sexo genero = Sexo.HOMBRE;
                if (alumnoGenero.toLowerCase().equals("male")) {
                	genero = Sexo.HOMBRE;
                }else {
                	genero = Sexo.MUJER;
                }
                
                AlumnoDTO alumnoDto = new AlumnoDTO(alumnoID, alumnoNombre, alumnoApellido, alumnoEmail, genero);
                
                boolean esCreado = alumnoDao.createAlumno(alumnoDto);
                System.out.println(esCreado);
            
            }

            alumnos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public boolean updateAlumno(AlumnoDTO alumnoUpdated) {
		return alumnoDao.updateAlumno(alumnoUpdated);
	}
	
	public AlumnoDTO consultaID (int id) {
		return alumnoDao.consultarAlumno(id);
	}
	public List<AlumnoDTO> devolverTodos(){
		return alumnoDao.listarAlumnos();
	}
	
	public boolean borrarAlumno(int id) {
		return alumnoDao.deleteAlumno(id);
	}
	
	public List<AlumnoDTO> consultarSexoNombre(boolean hombreSelect, boolean mujerSelect , String nombre){
		
		String sexo = null;
		if(hombreSelect == true) {
			sexo = "Hombre";
		}else {
			sexo = "Mujer";
		}
		
		
		return alumnoDao.consultarAlumnoSexoNombre(sexo, nombre);
	}
	
	public boolean annadirAlumno(String id, String nombre, String apellido, String email, String genero) throws AnnadirExcepcion {
		boolean esCreado = false;
		
		// debemos comprobar que es numero
		
		// debemos comprobar que no existe en la bbdd
		AlumnoDTO alumnoExistente = alumnoDao.consultarAlumno(Integer.parseInt(id));
		
		if(alumnoExistente == null) {
			
			Sexo generoAlumno = Sexo.HOMBRE;
	        if (genero.equals("Hombre")) {
	        	generoAlumno = Sexo.HOMBRE;
	        }else {
	        	generoAlumno = Sexo.MUJER;
	        }
	        
	        AlumnoDTO alumnoDto = new AlumnoDTO(Integer.parseInt(id), nombre, apellido, email, generoAlumno);
	        
	        esCreado = alumnoDao.createAlumno(alumnoDto);
	        
		}else {
			throw new AnnadirExcepcion("Ya existe en la BBDD el id " + id);
		}
       
        return esCreado;
	}
	

	public AlumnoDAO getAlumnoDao() {
		return alumnoDao;
	}

	public void setAlumnoDao(AlumnoDAO alumnoDao) {
		this.alumnoDao = alumnoDao;
	}

	public GuiPrincipal getVista() {
		return vista;
	}

	public void setVista(GuiPrincipal vista) {
		this.vista = vista;
	}

	public List<AlumnoDTO> getListadoAlumnos() {
		return listadoAlumnos;
	}

	public void setListadoAlumnos(List<AlumnoDTO> listadoAlumnos) {
		this.listadoAlumnos = listadoAlumnos;
	}

	
}
