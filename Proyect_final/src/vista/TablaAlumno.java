package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import modelo.AlumnoDAO;
import modelo.AlumnoDAOImpl;
import modelo.AlumnoDTO;
import modelo.Logs;
import modelo.Sexo;

public class TablaAlumno {

	private AlumnoDAO alumnoDao = new AlumnoDAOImpl();
	private final String[] columnNames = { "ID", "Nombre", "Apellido", "Email", "Género" };

	private final JLabel labelPaginaActual = new JLabel();
	private final JLabel labelTotalPaginas = new JLabel();

	private final int alumnosPorPagina = 10;
	private int totalPaginas;
	private int paginaActual = 1;

	// Creamos la tabla con DefaultTableModel
	private final DefaultTableModel model = new DefaultTableModel(null, columnNames) {
		@Override
		public Class<?> getColumnClass(int column) {
			return (column == 0) ? Integer.class : Object.class;
		}
	};

	private final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);

	private final JTable table = new JTable(model);

	public JTable getTable() {
		return table;
	}

	// botón ir al inicio
	private final JButton first = new JButton(new AbstractAction("|<") {
		@Override
		public void actionPerformed(ActionEvent e) {
			paginaActual = 1;
			iniciarBotones();
		}
	});

	// botón retroceder
	private final JButton prev = new JButton(new AbstractAction("<") {
		@Override
		public void actionPerformed(ActionEvent e) {
			paginaActual -= 1;
			iniciarBotones();
		}
	});

	// botón avanzar
	private final JButton next = new JButton(new AbstractAction(">") {
		@Override
		public void actionPerformed(ActionEvent e) {
			paginaActual += 1;
			iniciarBotones();
		}
	});

	// botón ir al final
	private final JButton last = new JButton(new AbstractAction(">|") {
		@Override
		public void actionPerformed(ActionEvent e) {
			paginaActual = totalPaginas;
			iniciarBotones();
		}
	});

	public void cargarAlumnos(List<AlumnoDTO> listaAlumnos) {

		for (AlumnoDTO alumno : listaAlumnos)
			model.addRow(new Object[] { alumno.getId(), alumno.getNombre(), alumno.getApellidos(), alumno.getEmail(),
					alumno.getGenero().name() });
	}

	public JComponent construyeUI(List<AlumnoDTO> listaAlumnos) {
//		System.out.println("Vamos a cargar la tabla con " + listaAlumnos.size());
		// esto asegura que la tabla nunca sea más pequeña que la ventana
		table.setFillsViewportHeight(true);
		table.setRowSorter(sorter);

		cargarAlumnos(listaAlumnos);
		int totalFilas = model.getRowCount();

		int resto = 0;
		if (totalFilas % alumnosPorPagina == 0)
			resto = 0;
		else
			resto = 1;

		totalPaginas = totalFilas / alumnosPorPagina + resto;

		labelTotalPaginas.setText(String.format("/ %d", totalPaginas));
		labelPaginaActual.setText(String.valueOf(paginaActual));

		// panel para encerrar paginaactual / total paginas
		JPanel po = new JPanel();
		po.add(labelPaginaActual);
		po.add(labelTotalPaginas);

		// panel para encerrar los botones de adelante y atrás
		JPanel box = new JPanel(new GridLayout(1, 4, 2, 2));
		for (JComponent r : Arrays.asList(first, prev, po, next, last)) {
			box.add(r);
		}

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(box, BorderLayout.NORTH);
		panel.add(new JScrollPane(table));

		iniciarBotones();

		addListener();

		return panel;
	}

	private void addListener() {
		model.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {

				DefaultTableModel model = (DefaultTableModel) e.getSource();

				Vector vectorAlumnos = model.getDataVector();

				int row = e.getFirstRow();
				int column = e.getColumn();

				if(e.getType()==TableModelEvent.UPDATE){

					
					// hacer comprobaciones de campos, lanzar excep... todo igual que el añadir
					
					String valorModificado = String.valueOf( model.getValueAt(row, column) );
					Vector<String> vectorAlumno = (Vector) vectorAlumnos.get(row);

					Sexo sexo = Sexo.HOMBRE;
					if(vectorAlumno.get(4).toLowerCase().equals("hombre")) {
						sexo = sexo.HOMBRE;
					}else {
						sexo = sexo.MUJER;
					}
					String id = String.valueOf(vectorAlumno.get(0));
					AlumnoDTO alumnoUpdated = new AlumnoDTO(Integer.parseInt( id ), vectorAlumno.get(1), vectorAlumno.get(2), vectorAlumno.get(3), sexo);


					switch (column) {
					case 1:
						alumnoUpdated.setNombre(valorModificado);
						break;
					case 2:
						alumnoUpdated.setApellidos(valorModificado);
						break;
					case 3:
						alumnoUpdated.setEmail(valorModificado);
						break;
					case 4:
						
						if(valorModificado.toLowerCase().equals("hombre")) {
							sexo = sexo.HOMBRE;
						}else {
							sexo = sexo.MUJER;
						}
						// no poner else, poner else if
						// por si indica otro sexo

						alumnoUpdated.setGenero(sexo);
						break;

					default:
						break;
					}

					alumnoDao.updateAlumno(alumnoUpdated); 
					Logs.crearLog("MODIFICAR", String.valueOf(alumnoUpdated.getId()));
				}
			}
		});
	}

	public void clearTable() {
		model.setRowCount(0);
	}

	private void iniciarBotones() {
		sorter.setRowFilter(new RowFilter<TableModel, Integer>() {
			@Override
			public boolean include(Entry<? extends TableModel, ? extends Integer> entry) {
				int ti = paginaActual - 1;
				int ei = entry.getIdentifier();
				return ti * alumnosPorPagina <= ei && ei < ti * alumnosPorPagina + alumnosPorPagina;
			}
		});

		// habilitamos cuando estemos en una pagina superior a la primera
		first.setEnabled(paginaActual > 1);
		prev.setEnabled(paginaActual > 1);

		// habilitamos cuando no estemos en la última página
		next.setEnabled(paginaActual < totalPaginas);
		last.setEnabled(paginaActual < totalPaginas);

		labelPaginaActual.setText(Integer.toString(paginaActual));
	}
	
	public ArrayList<Integer>  eliminarAlumnos() {
		
		Vector vectorAlumnos = model.getDataVector();
		ArrayList<Integer> listaAlumnosEliminar = new ArrayList<>();
		for(int i: table.getSelectedRows()) {
			int fila = (paginaActual - 1) * alumnosPorPagina + i;
			Vector<String> vectorAlumno = (Vector) vectorAlumnos.get(fila);
			String id = String.valueOf(vectorAlumno.get(0));
			int identificador = Integer.parseInt(id);
			
			listaAlumnosEliminar.add(identificador);
			
		}
		
		return listaAlumnosEliminar;
	}
}