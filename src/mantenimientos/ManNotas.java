package mantenimientos;

import general.DatosComunes;
import general.MysqlConnect;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;


import tablas.Nota;
import util.Apariencia;
import util.BaseDatos;
import util.EscapeDialog;
import util.JTextFieldNumeroFijo;

public class ManNotas {

	JDialog pantalla;
	JLabel lCodigo, lDesCripcionCodigo, lNotas;		
	JTextField jtCodigo;
	public final JTextArea jtaNotas = new JTextArea();
	JScrollPane spPanelScroll;
	JButton jbSalir, jbGrabar;
	Image imgSalir;
	
	ResultSet rs = null;
	MysqlConnect m = null;
	
	String strFichero = null, strClave = null, strDescripcionClave = null;
	
	public ManNotas(String fichero, String clave, String descripcionClave){
		strFichero = fichero.trim();
		strClave = clave.trim();
		strDescripcionClave = descripcionClave.trim();
		creaGui();
	}
	
	private void creaGui(){
		pantalla = new EscapeDialog();
		pantalla.setTitle("Notas");
		pantalla.setModal(true);
		pantalla.setSize(600, 400);
		pantalla.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		pantalla.setLayout(null);
		
		lCodigo = new JLabel("Código");
		lCodigo.setBounds(10, 15, 90, 20);
		lCodigo.setFont(Apariencia.cambiaFuente());
		pantalla.add(lCodigo);
		
		jtCodigo = new JTextField();
		jtCodigo.setBounds(110, 15, 120, 25);
		jtCodigo.setText(strClave);
		jtCodigo.setFont(Apariencia.cambiaFuente());
		jtCodigo.setEnabled(false);
		pantalla.add(jtCodigo);
		
		lDesCripcionCodigo = new JLabel(strDescripcionClave);
		lDesCripcionCodigo.setBounds(240, 15, 340, 20);
		lDesCripcionCodigo.setFont(Apariencia.cambiaFuente());
		pantalla.add(lDesCripcionCodigo);
		
		//lNotas = new JLabel("Notas");
		//lNotas.setBounds(10, 45, 90, 20);
		//lNotas.setFont(Apariencia.cambiaFuente());
		//pantalla.add(lNotas);
		
		jtaNotas.setFont(Apariencia.cambiaFuente());
		spPanelScroll = new JScrollPane(jtaNotas);
		spPanelScroll.setBounds(30, 60, 540, 260);
		spPanelScroll.setFont(Apariencia.cambiaFuente());
		spPanelScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pantalla.add(spPanelScroll);
		
		jbGrabar = new JButton("Grabar");
		jbGrabar.setBounds(470, 330, 90, 30);
		jbGrabar.setFont(Apariencia.cambiaFuente());
		jbGrabar.addActionListener(new BotonGrabarListener());
		pantalla.add(jbGrabar);
		
		jbSalir = new JButton();
		jbSalir.setBounds(30, 330, 30, 30);
		jbSalir.setFont(Apariencia.cambiaFuente());
		jbSalir.addActionListener(new SalirListener());
		try {
			imgSalir = ImageIO.read(getClass().getResource("/imagenes/SALIR.gif"));
			jbSalir.setIcon(new ImageIcon(imgSalir));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		pantalla.add(jbSalir);
		
		cargaNota();
		
		pantalla.setVisible(true);
	}
	
	class SalirListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			salir();
		}
	}
	
	class BotonGrabarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			grabarNota();
			salir();
		}
	}
	
	private void cargaNota(){
		String strSql = "SELECT * FROM NOTASF WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' AND " +
				"NOTASF_FICHERO LIKE '" + strFichero + "%' AND " +
				"NOTASF_CLAVE LIKE '" + strClave + "%' " ;
		
		
		m = MysqlConnect.getDbCon();

		if(BaseDatos.countRows(strSql) == 0){
			jtaNotas.setText("");
		}else{
			try {
				rs = m.query(strSql);

				Nota nota = new Nota(rs);

				jtaNotas.setText(nota.getNota());

				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,
				"Error en lectura fichero de Notas!!!");
			}
		}
	}
	
	private void grabarNota(){
		
		Nota nota = new Nota();
		
		nota.setEmpresa(DatosComunes.eEmpresa);
		nota.setFichero(strFichero);
		nota.setClave(strClave);
		nota.setNota(jtaNotas.getText().trim());
		nota.write();
	}
	
	private void salir(){
		pantalla.dispose();
	}
}
