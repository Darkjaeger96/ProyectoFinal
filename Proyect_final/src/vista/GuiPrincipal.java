package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import modelo.AlumnoDTO;

public class GuiPrincipal {

	private JFrame frame;
	private JTextField campoId;
	private JTextField campoNombreAdd;
	private JTextField campoApellidos;
	private JTextField campoEmail;
	private JTextField campoNombre;
	
	private List<AlumnoDTO> listadoAlumnos = new ArrayList<>();
	private JButton btnEliminar = new JButton("Eliminar");
	public JButton getBtnEliminar() {
		return btnEliminar;
	}
	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	private JComboBox cajaGenero = new JComboBox();
	private JMenuBar menuBar = new JMenuBar();
	private JMenu mnArchivo = new JMenu("Archivo");
	private JMenuItem mntmNewMenuItem = new JMenuItem("Cerrar");
	private JMenu mnCreditos = new JMenu("Ayuda");
	private JMenuItem mntmCrditos = new JMenuItem("Cr\u00E9ditos");
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private JPanel panelTabla = new JPanel();
	private JPanel panel = new JPanel();
	private JLabel lblNewLabel_4 = new JLabel("G\u00E9nero:");
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnHombre = new JRadioButton("Hombre");
	private JRadioButton rdbtnMujer = new JRadioButton("Mujer");
	private JButton btnBuscar = new JButton("Buscar");
	private JLabel lblNombre = new JLabel("Nombre:");
	private JPanel panel_1 = new JPanel();
	private JLabel lblNewLabel = new JLabel("Id:");
	private JLabel lblNewLabel_1 = new JLabel("Nombre:");
	private JLabel lblNewLabel_2 = new JLabel("Apellidos:");
	private JLabel lblNewLabel_3 = new JLabel("Email:");
	private JButton btnAadir = new JButton("A\u00F1adir");
	
	private GroupLayout gl_panel = new GroupLayout(panel);
	private GroupLayout gl_panel_1 = new GroupLayout(panel_1);
	
	/**
	 * Initialize the contents of the frame.
	 */
	
	public GuiPrincipal() {
		initialize();
	}
	public void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setBounds(100, 100, 474, 588);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(menuBar);
		
		menuBar.add(mnArchivo);
		
		mnArchivo.add(mntmNewMenuItem);
		
		menuBar.add(mnCreditos);

		mnCreditos.add(mntmCrditos);
		frame.getContentPane().setLayout(null);
		
		tabbedPane.setBounds(0, 0, 467, 205);
		frame.getContentPane().add(tabbedPane);
		
		panelTabla.setBounds(0, 209, 467, 252);
		
		frame.getContentPane().add(panelTabla);
		
		tabbedPane.addTab("Consultar", null, panel, null);
		
		rdbtnHombre.setSelected(true);
		
		buttonGroup.add(rdbtnHombre);
		buttonGroup.add(rdbtnMujer); 
		
		campoNombre = new JTextField();
		campoNombre.setColumns(10);
		
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNombre)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnBuscar)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(campoNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(220, Short.MAX_VALUE))))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(4)
					.addComponent(rdbtnHombre)
					.addGap(4)
					.addComponent(rdbtnMujer)
					.addContainerGap(292, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(rdbtnHombre)
						.addComponent(rdbtnMujer))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(campoNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addComponent(btnBuscar)
					.addContainerGap(36, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		
		tabbedPane.addTab("Añadir", null, panel_1, null);
		
		campoId = new JTextField();
		campoId.setColumns(10);
		
		campoNombreAdd = new JTextField();
		campoNombreAdd.setColumns(10);
		
		campoApellidos = new JTextField();
		campoApellidos.setColumns(10);
		
		campoEmail = new JTextField();
		campoEmail.setColumns(10);
		
		cajaGenero.setModel(new DefaultComboBoxModel(new String[] {"Hombre", "Mujer"}));
		
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addGap(18)
									.addComponent(campoId, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
									.addGap(48)
									.addComponent(lblNewLabel_4)
									.addGap(35)
									.addComponent(cajaGenero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel_1.createSequentialGroup()
										.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
											.addComponent(lblNewLabel_2)
											.addComponent(lblNewLabel_1))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(campoNombreAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(campoApellidos, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
											.addGroup(gl_panel_1.createSequentialGroup()
												.addComponent(campoEmail)
												.addPreferredGap(ComponentPlacement.RELATED))))
									.addComponent(lblNewLabel_3))))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(147)
							.addComponent(btnAadir)))
					.addContainerGap(89, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_4)
							.addComponent(cajaGenero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel)
							.addComponent(campoId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(campoNombreAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(campoApellidos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(campoEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3))
					.addGap(18)
					.addComponent(btnAadir)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		
		btnEliminar.setBounds(170, 477, 115, 29);
		frame.getContentPane().add(btnEliminar);
		
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JTextField getCampoId() {
		return campoId;
	}

	public void setCampoId(JTextField campoId) {
		this.campoId = campoId;
	}

	public JTextField getCampoNombreAdd() {
		return campoNombreAdd;
	}

	public void setCampoNombreAdd(JTextField campoNombreAdd) {
		this.campoNombreAdd = campoNombreAdd;
	}

	public JTextField getCampoApellidos() {
		return campoApellidos;
	}

	public void setCampoApellidos(JTextField campoApellidos) {
		this.campoApellidos = campoApellidos;
	}

	public JTextField getCampoEmail() {
		return campoEmail;
	}

	public void setCampoEmail(JTextField campoEmail) {
		this.campoEmail = campoEmail;
	}

	public JTextField getCampoNombre() {
		return campoNombre;
	}

	public void setCampoNombre(JTextField campoNombre) {
		this.campoNombre = campoNombre;
	}

	public List<AlumnoDTO> getListadoAlumnos() {
		return listadoAlumnos;
	}

	public void setListadoAlumnos(List<AlumnoDTO> listadoAlumnos) {
		this.listadoAlumnos = listadoAlumnos;
	}

	public JComboBox getCajaGenero() {
		return cajaGenero;
	}

	public void setCajaGenero(JComboBox cajaGenero) {
		this.cajaGenero = cajaGenero;
	}

	public JMenuBar getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}

	public JMenu getMnArchivo() {
		return mnArchivo;
	}

	public void setMnArchivo(JMenu mnArchivo) {
		this.mnArchivo = mnArchivo;
	}

	public JMenuItem getMntmNewMenuItem() {
		return mntmNewMenuItem;
	}

	public void setMntmNewMenuItem(JMenuItem mntmNewMenuItem) {
		this.mntmNewMenuItem = mntmNewMenuItem;
	}

	public JMenu getMnCreditos() {
		return mnCreditos;
	}

	public void setMnCreditos(JMenu mnCreditos) {
		this.mnCreditos = mnCreditos;
	}

	public JMenuItem getMntmCrditos() {
		return mntmCrditos;
	}

	public void setMntmCrditos(JMenuItem mntmCrditos) {
		this.mntmCrditos = mntmCrditos;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	public JPanel getPanelTabla() {
		return panelTabla;
	}

	public void setPanelTabla(JPanel panelTabla) {
		this.panelTabla = panelTabla;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JLabel getLblNewLabel_4() {
		return lblNewLabel_4;
	}

	public void setLblNewLabel_4(JLabel lblNewLabel_4) {
		this.lblNewLabel_4 = lblNewLabel_4;
	}

	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}

	public void setButtonGroup(ButtonGroup buttonGroup) {
		this.buttonGroup = buttonGroup;
	}

	public JRadioButton getRdbtnHombre() {
		return rdbtnHombre;
	}

	public void setRdbtnHombre(JRadioButton rdbtnHombre) {
		this.rdbtnHombre = rdbtnHombre;
	}

	public JRadioButton getRdbtnMujer() {
		return rdbtnMujer;
	}

	public void setRdbtnMujer(JRadioButton rdbtnMujer) {
		this.rdbtnMujer = rdbtnMujer;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(JLabel lblNombre) {
		this.lblNombre = lblNombre;
	}

	public JPanel getPanel_1() {
		return panel_1;
	}

	public void setPanel_1(JPanel panel_1) {
		this.panel_1 = panel_1;
	}

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	public void setLblNewLabel(JLabel lblNewLabel) {
		this.lblNewLabel = lblNewLabel;
	}

	public JLabel getLblNewLabel_1() {
		return lblNewLabel_1;
	}

	public void setLblNewLabel_1(JLabel lblNewLabel_1) {
		this.lblNewLabel_1 = lblNewLabel_1;
	}

	public JLabel getLblNewLabel_2() {
		return lblNewLabel_2;
	}

	public void setLblNewLabel_2(JLabel lblNewLabel_2) {
		this.lblNewLabel_2 = lblNewLabel_2;
	}

	public JLabel getLblNewLabel_3() {
		return lblNewLabel_3;
	}

	public void setLblNewLabel_3(JLabel lblNewLabel_3) {
		this.lblNewLabel_3 = lblNewLabel_3;
	}

	public JButton getBtnAadir() {
		return btnAadir;
	}

	public void setBtnAadir(JButton btnAadir) {
		this.btnAadir = btnAadir;
	}

	public GroupLayout getGl_panel() {
		return gl_panel;
	}

	public void setGl_panel(GroupLayout gl_panel) {
		this.gl_panel = gl_panel;
	}

	public GroupLayout getGl_panel_1() {
		return gl_panel_1;
	}

	public void setGl_panel_1(GroupLayout gl_panel_1) {
		this.gl_panel_1 = gl_panel_1;
	}
}
