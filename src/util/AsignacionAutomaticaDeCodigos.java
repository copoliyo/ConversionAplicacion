package util;

import general.DatosComunes;
import general.MysqlConnect;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;





import tablas.Cuenta;

public class AsignacionAutomaticaDeCodigos {
	
	private Cuenta c = null;
	private String cuentaContable = "";
	
	public void setCuentaContable(String cuentaContable) {
		this.cuentaContable = cuentaContable;
	}

	JDialog pantalla;
	
	JLabel lCuenta2doGrado, lDescripionCuenta, lApellidosRazon, lOrden, lMensaje;
	
	JTextFieldNumeroFijo jtnfCuentaContable;
	JTextFieldFijo jtfApellidosRazon;
	JComboBox jcbOrden;
	
	JButton jbSalir, jbOk;
	
	Image imgSalir;
	
	public AsignacionAutomaticaDeCodigos(){
		creaGui();
	}
	
	private void creaGui(){
		pantalla = new EscapeDialog();
		pantalla.setTitle("Asignación Automática de Códigos");
		pantalla.setModal(true);
		pantalla.setSize(480, 230);
		pantalla.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		pantalla.setLayout(null);	
		
		lCuenta2doGrado = new JLabel("Cuenta 2do Grado");
		lCuenta2doGrado.setBounds(10, 15, 150, 20);
		lCuenta2doGrado.setFont(Apariencia.cambiaFuente());
		pantalla.add(lCuenta2doGrado);
		
		jtnfCuentaContable = new JTextFieldNumeroFijo(5);
		jtnfCuentaContable.setBounds(160, 15, 70, 25);
		jtnfCuentaContable.setFont(Apariencia.cambiaFuente());
		jtnfCuentaContable.setTextoAyuda("Cuenta contable de segundo grado.<BR>Si Proveedor 400XX<BR>Si Acreedor 410XX<BR>Si cliente 430XX", 4);
		jtnfCuentaContable.addActionListener(new CuentaContableListener());
		jtnfCuentaContable.addFocusListener(new CuentaContableListener());
		pantalla.add(jtnfCuentaContable);
		
		lDescripionCuenta = new JLabel();
		lDescripionCuenta.setBounds(240, 15, 150, 20);
		lDescripionCuenta.setFont(Apariencia.cambiaFuente());
		pantalla.add(lDescripionCuenta);				
		
		lApellidosRazon = new JLabel("Apellidos - Razón");
		lApellidosRazon.setBounds(10, 45, 150, 20);
		lApellidosRazon.setFont(Apariencia.cambiaFuente());
		pantalla.add(lApellidosRazon);
		
		jtfApellidosRazon = new JTextFieldFijo(30);
		jtfApellidosRazon.setBounds(160, 45, 250, 25);
		jtfApellidosRazon.setFont(Apariencia.cambiaFuente());		
		jtfApellidosRazon.setTextoAyuda("Apellidos o Razón Social, necesario para calcular codigo adecuado.", 4);
		jtfApellidosRazon.addFocusListener(new ApellidosRazonListener());
		pantalla.add(jtfApellidosRazon);
		
		lOrden = new JLabel("Orden");
		lOrden.setBounds(10, 75, 150, 20);
		lOrden.setFont(Apariencia.cambiaFuente());
		pantalla.add(lOrden);
		
		jcbOrden = new JComboBox();
		jcbOrden.addItem("Orden Alfabético");
		jcbOrden.addItem("Asigna Primer Código Libre");
		jcbOrden.setSelectedIndex(0);
		//jcbTipoIva.addActionListener(this);
		jcbOrden.setBounds(160, 75, 250, 25);
		jcbOrden.setFont(Apariencia.cambiaFuente());
		pantalla.add(jcbOrden);
		
		jbSalir = new JButton();
		jbSalir.setBounds(20, 155, 30, 30);
		jbSalir.setFont(Apariencia.cambiaFuente());
		jbSalir.addActionListener(new SalirListener());
		try {
			imgSalir = ImageIO.read(getClass().getResource("/imagenes/SALIR.gif"));
			jbSalir.setIcon(new ImageIcon(imgSalir));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		pantalla.add(jbSalir);
		
		jbOk = new JButton("Ok");
		jbOk.setBounds(370, 155, 40, 30);
		jbOk.setFont(Apariencia.cambiaFuente());
		jbOk.addActionListener(new BotonOkListener());
		pantalla.add(jbOk);
		
		lMensaje = new JLabel("");
		lMensaje.setBounds(10, 115, 420, 20);
		lMensaje.setFont(Apariencia.cambiaFuente());
		lMensaje.setForeground(Color.RED);
		pantalla.add(lMensaje);
		
		pantalla.setVisible(true);
	}
	
	class SalirListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			cuentaContable = null;
			pantalla.dispose();
		}
	}
	
	class BotonOkListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(jtfApellidosRazon.getText().isEmpty() && jcbOrden.getSelectedIndex() == 0)
				JOptionPane.showMessageDialog(null,	"No se admite sin Apellidos o Razón Social!!!");
			else{
				switch(jcbOrden.getSelectedIndex()){
				case 0	: cuentaContable = buscaCuentaOrdenAlFabetico();
						break;
				case 1	: cuentaContable = buscaCuentaOrdenNumerico();
						break;
				default : JOptionPane.showMessageDialog(null, "Debes seleccionar un orden!!!");
						break;
				}
				
				//System.out.println(cuentaContable);
				
				if(!cuentaContable.isEmpty())
					pantalla.dispose();
			}
		}
	}
	
	class CuentaContableListener implements ActionListener, FocusListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			String strSql = "SELECT * FROM CONTAB WHERE EMPRESA = '" + DatosComunes.eEmpresa + "'" +
					" AND CONTAB_GRADO = 2 " +
					" AND CONTAB_CENTRO = " + DatosComunes.centroCont + 
	                " AND CONTAB_CUENTA = '" + jtnfCuentaContable.getText() + "'";
			
			String strCuenta = jtnfCuentaContable.getText();

			if(strCuenta.length() != 5)
				JOptionPane.showMessageDialog(null,	"Tiene que ser una cuenta de segundo grado!!!");
			else
				if(!strCuenta.startsWith("40") && !strCuenta.startsWith("41") && !strCuenta.startsWith("43"))
					JOptionPane.showMessageDialog(null, "Esa no es una cuenta válida!!!");
				else
					if(BaseDatos.countRows(strSql) == 0)
						JOptionPane.showMessageDialog(null,	"Cuenta inexistente!!!");				
					else{
						jtnfCuentaContable.transferFocus();
						Cuenta c = new Cuenta(BaseDatos.sqlToRecordSet(strSql));
						if(!c.getTitulo().isEmpty()){
							lDescripionCuenta.setText(c.getTitulo());
						}
					}
		}

		@Override
		public void focusGained(FocusEvent arg0) {
			lMensaje.setText("Proveedor 400XX, Acreedor 410XX, Cliente 430XX");			
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			lMensaje.setText("");		
		}		
	}
	
	class ApellidosRazonListener implements FocusListener {

		@Override
		public void focusGained(FocusEvent arg0) {
			lMensaje.setText("Apellidos o Razón Social");			
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			jtfApellidosRazon.setText(jtfApellidosRazon.getText().trim().toUpperCase());
			lMensaje.setText("");		
		}		
	}
	
	private String buscaCuentaOrdenAlFabetico(){
		String strCuenta = "";
		String strCuentaPrimerMenor = "";
		String strCuentaPrimerMayor = "";
		String strSql = "";
		int cuentaPrimerMayor, cuentaPrimerMenor, cuentaPropuesta;
		Cuenta cuentaMayor = null;
		Cuenta cuentaMenor = null;
	
		ResultSet rs = null;
		MysqlConnect m = null;
		m = MysqlConnect.getDbCon();
				
		String strSqlPrimerMenor = "SELECT * FROM CONTAB WHERE EMPRESA = '" + DatosComunes.eEmpresa + 
		"' AND CONTAB_CENTRO = " + DatosComunes.centroCont + 
		" AND CONTAB_GRADO = 3" +
		" AND CONTAB_CUENTA LIKE '" + jtnfCuentaContable.getText() + "%'" +
		" AND CONTAB_TITULO < '" + jtfApellidosRazon.getText().toUpperCase().trim() + "'" + 
		" ORDER BY CONTAB_TITULO DESC LIMIT 1";
		
		String strSqlPrimerMayor = "SELECT * FROM CONTAB WHERE EMPRESA = '" + DatosComunes.eEmpresa + 
		"' AND CONTAB_CENTRO = " + DatosComunes.centroCont + 
		" AND CONTAB_GRADO = 3" +
		" AND CONTAB_CUENTA LIKE '" + jtnfCuentaContable.getText() + "%'" +
		" AND CONTAB_TITULO > '" + jtfApellidosRazon.getText().toUpperCase().trim() + "'" + 
		" ORDER BY CONTAB_TITULO ASC LIMIT 1";
		
		if(BaseDatos.countRows(strSqlPrimerMenor) == 0){
			strCuentaPrimerMenor = jtnfCuentaContable.getText() + "0010";
			cuentaMenor = new Cuenta();
			cuentaMenor.setCuenta(strCuentaPrimerMenor);
		}else{
			try {
				rs = m.query(strSqlPrimerMenor);
				cuentaMenor = new Cuenta(rs);
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,	"Error leyendo Cuenta!!!");
			}
		}
		
		if(BaseDatos.countRows(strSqlPrimerMayor) == 0){
			strCuentaPrimerMayor = jtnfCuentaContable.getText() + "9999";
			cuentaMayor = new Cuenta();
			cuentaMayor.setCuenta(strCuentaPrimerMayor);
		}else{
			try {
				rs = m.query(strSqlPrimerMayor);
				cuentaMayor = new Cuenta(rs);
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,	"Error leyendo Cuenta!!!");
			}
		}
		
		cuentaPrimerMenor = Integer.valueOf(cuentaMenor.getCuenta().substring(5, 9));
		cuentaPrimerMayor = Integer.valueOf(cuentaMayor.getCuenta().substring(5, 9));
		
		cuentaPropuesta = (cuentaPrimerMayor + cuentaPrimerMenor) / 2;
		
		strCuenta = jtnfCuentaContable.getText() + String.format("%04d", cuentaPropuesta);
		
		strSql = "SELECT * FROM CONTAB WHERE EMPRESA = '" + DatosComunes.eEmpresa + 
		"' AND CONTAB_CENTRO = " + DatosComunes.centroCont + 
		" AND CONTAB_GRADO = 3" +
		" AND CONTAB_CUENTA = '" + strCuenta + "'";
		
		if(BaseDatos.countRows(strSql) != 0){
			jtnfCuentaContable.setText(strCuenta);
			//JOptionPane.showMessageDialog(null,	"No es posible encontrar una cuenta adecuada!!!");
			strCuenta = buscaCuentaOrdenNumerico();
		}
			
		return strCuenta;
	}
	
	public String buscaCuentaOrdenNumerico(){
		
		String strCuenta = "";

		String strCuentaBuscada;
		String strCuentaMaxima;
		
		// Si la longitud de lo que hay en 'jtnfCuentaContable' es > 5
		// significa que no hemos podido encontrar un hueco por orden alfabético
		// y tenemos que buscar por orden numérico a partir de la cuenta teórica
		// que hemos calculado para orden alfabético y luego tenemos que buscar 
		// la primera libre desde esa.
		if(jtnfCuentaContable.getText().length() == 5){
			strCuentaBuscada = jtnfCuentaContable.getText() + "0010";
			strCuentaMaxima= jtnfCuentaContable.getText() + "9999";
		}else{
			strCuentaBuscada = jtnfCuentaContable.getText();
			strCuentaMaxima= jtnfCuentaContable.getText().substring(0, 5) + "9999";
		}
		int cuentaBuscada = Integer.valueOf(strCuentaBuscada);		
		int cuentaMaxima = Integer.valueOf(strCuentaMaxima);
		
		String strSql = "SELECT * FROM CONTAB WHERE EMPRESA = '" + DatosComunes.eEmpresa + 
		"' AND CONTAB_CENTRO = " + DatosComunes.centroCont + 
		" AND CONTAB_CUENTA = '" + cuentaBuscada + "'" +
		" AND CONTAB_GRADO = 3";
		
		while(cuentaBuscada < cuentaMaxima && BaseDatos.countRows(strSql) > 0){
			cuentaBuscada++;
			strSql = "SELECT * FROM CONTAB WHERE EMPRESA = '" + DatosComunes.eEmpresa + 
			"' AND CONTAB_CENTRO = " + DatosComunes.centroCont + 
			" AND CONTAB_CUENTA = '" + cuentaBuscada + "'" +
			" AND CONTAB_GRADO = 3";
		}
		
		if(cuentaBuscada < cuentaMaxima)
			strCuenta = String.valueOf(cuentaBuscada);
		
		//System.out.println("Cuenta encontrada = " + strCuentaBuscada);
		
		return strCuenta;
	}
	
	public String getCuentaContable(){		
		return cuentaContable;
	}
	
}
