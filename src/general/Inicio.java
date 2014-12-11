package general;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import org.jvnet.substance.SubstanceLookAndFeel;

import consultasMantenimientos.ConManProveedor;


import tablas.Acceso;
import util.Apariencia;
import util.BaseDatos;


public class Inicio extends JFrame {

	JFrame pantallaInicial;
	public JTree jtMenu;
	private JScrollPane scrollPane;
	JPanel topPanel, workPanel;
	JTextArea jtTexto;
	MysqlConnect m = null;
	// Acceso datosMenu;
	static final int MAX_OPCIONES = 38;
	ResultSet rs = null;

	Inicio() {
		m = MysqlConnect.getDbCon();
	}

	public void creaGui() {

		// SOLO HAY QUE UTILIZARLO UNA VEZ!!!!!!!!!!
		try
        {
            UIManager.setLookAndFeel("org.jvnet.substance.skin.Substance"+"Sahara"+"LookAndFeel");        	    	
        } catch(Exception e)
        {
            System.out.println("Falló la carga del tema");
        } 
		
		pantallaInicial = new JFrame();

		// Necesitamos que esté aquí para cargar los datos comunes
		DatosInicio di = new DatosInicio();

		cargaMenu();

		pantallaInicial.setTitle("TxusCO, Ltd.");
		pantallaInicial.setSize(1000, 800);
		setBackground(Color.gray);
		pantallaInicial.setBackground(Color.gray);
		pantallaInicial.setLayout(null);
		
		topPanel = new JPanel();
		topPanel.setLayout(null);
		topPanel.setSize(400, 800);
		topPanel.setBounds(0, 0, 400, 800);
		pantallaInicial.add(topPanel);

		workPanel = new JPanel();
		workPanel.setLayout(null);
		workPanel.setSize(600, 800);
		workPanel.setBounds(400, 0, 800, 800);

		pantallaInicial.add(workPanel);

		// Add the listbox to a scrolling pane
		scrollPane = new JScrollPane();
		scrollPane.setSize(400, 800);
		scrollPane.getViewport().add(jtMenu);
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		topPanel.add(scrollPane, null);

		pantallaInicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pantallaInicial.setVisible(true);
	}

	public void cargaMenu() {

		boolean existeUsuario = false;
		String menu = "";

		OpcionMenu opcion = null;

		DefaultMutableTreeNode root = new DefaultMutableTreeNode(
				"Menú Principal");
		jtMenu = new JTree(root);
		jtMenu.setFont(new java.awt.Font("Terminal", Font.BOLD, 16));
		jtMenu.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		jtMenu.addMouseListener(new RatonListener());
		jtMenu.addKeyListener(new TeclaListener());

		String usuario = DatosComunes.usuario.toUpperCase();

		// Buscamos el menú del usuario
		if (usuario.length() > 0) {
			try {
				rs = m.query("SELECT ACCUSU_MENU FROM ACCUSU WHERE EMPRESA = '"
						+ DatosComunes.eEmpresa + "' AND ACCUSU_USUARIO = '"
						+ usuario + "'");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"Error en lectura fichero de Usuarios!!!");
			}

			try {
				if (!rs.next())
					existeUsuario = false;
				else {
					menu = rs.getString("ACCUSU_MENU").trim();
					if (menu.length() == 0)
						menu = "TIENDA";
					existeUsuario = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Usuario incorrectos!!!");
			}
		}

		creaArbol(menu, root);
	}

	class OpcionMenu {
		// Tipo -> 0 = Texto, 1 = Programa, 2 = Menu, 3 = Ejecutable
		private int tipo;
		private String texto;
		private String programa;
		private int visible;
		private boolean nodoFinal;

		OpcionMenu() {
			this.tipo = 0;
			this.texto = "";
			this.programa = "";
			this.visible = 0;
			this.nodoFinal = false;
		}

		OpcionMenu(int tipo, String texto, String programa, int visible) {
			this.tipo = tipo;
			this.texto = texto;
			this.programa = programa;
			this.visible = visible;
			nodoFinal = false;

			if (tipo == 0 && visible == 1)
				nodoFinal = false;
			if (tipo == 2)
				nodoFinal = false;
			if (tipo == 1)
				nodoFinal = true;
		}

		public int getTipo() {
			return tipo;
		}

		public void setTipo(int tipo) {
			this.tipo = tipo;
		}

		public String getTexto() {
			return texto;
		}

		public void setTexto(String texto) {
			this.texto = texto;
		}

		public String getPrograma() {
			return programa;
		}

		public void setPrograma(String programa) {
			this.programa = programa;
		}

		public boolean isNodoFinal() {
			return nodoFinal;
		}

		public void setNodoFinal(boolean nodoFinal) {
			this.nodoFinal = nodoFinal;
		}

		public int isVisible() {
			return visible;
		}

		public void setVisible(int visible) {
			this.visible = visible;
		}

		@Override
		public String toString() {
			return texto;
		}

	}

	public void creaArbol(String nombreMenu, DefaultMutableTreeNode raiz) {
		DefaultMutableTreeNode padre = null;
		DefaultMutableTreeNode padreNuevo = null;
		DefaultMutableTreeNode hijo = null;

		try {
			String strQuery = "SELECT * FROM ACCESO WHERE EMPRESA = '"
					+ DatosComunes.eEmpresa + "' AND ACCESO_MENU = '"
					+ nombreMenu + "'";
			//System.out.println("Numero registros 'rs' : " + BaseDatos.countRows(strQuery));
			rs = m.query(strQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"Error en lectura fichero de Usuarios!!!");
		}

		
		Acceso datosMenu = new Acceso(rs);


		// En 'datosMenu' ya tenemos los datos del menu de acceso.
		// Ahora hay que crear el arbol.

		// El primer padre tiene que ser la raiz
		padre = raiz;

		for (int i = 0; i < MAX_OPCIONES; i++) {
			// Si es un programa, lo colgamos de su padre y listo
			if (datosMenu.getTipoOpcion(i) == 1){
				
			}
			if (datosMenu.getTipoOpcion(i) == 1) {
				hijo = new DefaultMutableTreeNode(new OpcionMenu(
						datosMenu.getTipoOpcion(i), datosMenu.getDescripcion(i),
						datosMenu.getPrograma(i), datosMenu.getVisible(i)));
				padre.add(hijo);
			}
			// Si es TEXTO y VISIBLE es que es una carpeta aparte
			if (datosMenu.getTipoOpcion(i) == 0 && datosMenu.getVisible(i) == 1) {
				padreNuevo = new DefaultMutableTreeNode(new OpcionMenu(
						datosMenu.getTipoOpcion(i), datosMenu.getDescripcion(i),
						datosMenu.getPrograma(i), datosMenu.getVisible(i)));
				if (padre != raiz)
					padre = (DefaultMutableTreeNode) padre.getParent();

				padre.add(padreNuevo);
				padre = padreNuevo;
			}
			// Si es un nuevo menú, tenemos que hacer una llamada recursiva!!!!
			if (datosMenu.getTipoOpcion(i) == 2) {
				padreNuevo = new DefaultMutableTreeNode(new OpcionMenu(
						datosMenu.getTipoOpcion(i), datosMenu.getDescripcion(i),
						datosMenu.getPrograma(i), datosMenu.getVisible(i)));
				padre.add(padreNuevo);
				padre = padreNuevo;
				creaArbol(datosMenu.getPrograma(i), padre);
				padre = (DefaultMutableTreeNode) padre.getParent();
			}
		}
	}

	public void llamaPrograma(String programa) {
		pantallaInicial.setEnabled(false);
		if (programa.equalsIgnoreCase("MMSISTEM")) {
			MantenimientoSistem ms = new MantenimientoSistem(pantallaInicial);
		}
		if (programa.equalsIgnoreCase("CMPROVAC")) {
			new ConManProveedor(pantallaInicial, true);
		}
	}

	class TeclaListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent arg0) {
			// Si donde se ha pulsado ha sido en el árbol...
			if (arg0.getSource() instanceof JTree) {
				// Y además ha sido un ENTER
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER
						|| arg0.getKeyCode() == KeyEvent.VK_SPACE) {
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jtMenu
							.getLastSelectedPathComponent();

					if (selectedNode.isLeaf()) {
						OpcionMenu om = new OpcionMenu();
						om = (OpcionMenu) selectedNode.getUserObject();

						// en 'om.getProgra()' está el programa que corresponde
						// al menú que hemos elegido.

					}
				}

			}

		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	class RatonListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// Si donde se ha pinchado ha sido en el árbol...
			if (arg0.getSource() instanceof JTree) {
				// Y además ha sido un DobleClick...
				if (arg0.getClickCount() == 2) {
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jtMenu
							.getLastSelectedPathComponent();

					if (selectedNode.isLeaf()) {
						OpcionMenu om = new OpcionMenu();
						om = (OpcionMenu) selectedNode.getUserObject();
						//pantallaInicial.setVisible(false);
						
						llamaPrograma(om.getPrograma());
						// en 'om.getProgra()' está el programa que corresponde
						// al menú que hemos elegido.
						// System.out.println(om.getPrograma());
					}
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Inicio pi = new Inicio();
				pi.creaGui();
			}
		});

	}

}
