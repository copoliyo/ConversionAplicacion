package mantenimientos;

import general.DatosComunes;
import general.MysqlConnect;

import indices.IndiceCuentas;
import indices.IndicePaises;
import indices.IndicePrevisionesPago;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


import tablas.ClienteContable;
import tablas.Cuenta;
import tablas.EfectoPagar;
import util.Apariencia;
import util.BaseDatos;
import util.Cadena;
import util.EscapeDialog;
import util.JTextFieldFecha;
import util.JTextFieldFijo;
import util.JTextFieldNumero2Decimales;
import util.JTextFieldNumeroFijo;

public class MantenimientoPrevisionesPago {

	static ResultSet rs = null;
	static MysqlConnect m = null;
	
	private static EfectoPagar efectoPagar = new EfectoPagar();
	IndicePrevisionesPago indicePrevisionesPago = null;
	
	// Definiciones de componentes de pantalla
	public JFrame frameMenu = null;
	
	JDialog pantalla;
	
	JLabel lVencimiento, lEfecto, lCentro, lCuenta, lDescripcionCuenta, lBancoDomiciliacion, lDescripcionBanco,
	       lDocumento, lFechaFactura, lImporte, lFechaPago, lNumeroPagare, lFechaPagare, lBancoPagare;	
	
	JTextFieldNumeroFijo jtfnfEfecto, jtfnfCentro, jtfnfCuentaContable, jtfnfBancoDomiciliacion, jtfnfDocumento,
	       jtfnfNumeroPagare, jtfnfBancoPagare;	
	
	JTextFieldFijo jtfNombre;
	
	JTextFieldFecha jtffeVencimiento, jtffeFechaFactura, jtffeFechaPago, jtffeFechaPagare;
	
	JTextFieldNumero2Decimales jtfn2Importe; 	
	
	JButton jbIndicePrevisionPago, jbIndiceCuentaContable, jbIndiceBanco, jbSalir, jbDepuracion, jbBorrar, jbGrabar, jbAdelante, jbAtras;
	
	Image imgBuscar, imgAtras, imgAdelante, imgSalir;

	public MantenimientoPrevisionesPago(){
		m = MysqlConnect.getDbCon();
		creaGui();
	}
	
	private void creaGui(){
		
		pantalla = new EscapeDialog();
		
		pantalla.setTitle("Mantenimiento Previsiones de Pago");
		pantalla.setModal(true);
		pantalla.setSize(800, 400);
		pantalla.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		pantalla.setLayout(null);
		pantalla.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//frameMenu.setVisible(true);
				frameMenu.setEnabled(true);
				pantalla.dispose();
			}

		});
		
		lVencimiento = new JLabel("Vencimiento");
		lVencimiento.setBounds(10, 15, 110, 20);
		lVencimiento.setFont(Apariencia.cambiaFuente());
		pantalla.add(lVencimiento);
			
		jbIndicePrevisionPago = new JButton();
		jbIndicePrevisionPago.setBounds(120, 15, 25, 25);
		try {
			imgBuscar = ImageIO.read(getClass().getResource("/imagenes/BUSCAR.gif"));
			jbIndicePrevisionPago.setIcon(new ImageIcon(imgBuscar));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		jbIndicePrevisionPago.addActionListener(new BotonIndicePrevisionesPagoListener());
		jbIndicePrevisionPago.setFocusable(false);
		pantalla.add(jbIndicePrevisionPago);
		
		jtffeVencimiento = new JTextFieldFecha();
		jtffeVencimiento.setBounds(150, 15, 90, 25);
		jtffeVencimiento.addActionListener(new FechaVencimientoListener());
		jtffeVencimiento.setFont(Apariencia.cambiaFuente());
		pantalla.add(jtffeVencimiento);              
					
		lEfecto = new JLabel("Efecto");
		lEfecto.setBounds(280, 15, 60, 20);
		lEfecto.setFont(Apariencia.cambiaFuente());
		pantalla.add(lEfecto);
		
		jtfnfEfecto = new JTextFieldNumeroFijo(4);
		jtfnfEfecto.setBounds(350, 15, 40, 25);
		jtfnfEfecto.addActionListener(new NumeroEfectoListener());
		jtfnfEfecto.setFont(Apariencia.cambiaFuente());
		pantalla.add(jtfnfEfecto);
		
		lCentro = new JLabel("Centro");
		lCentro.setBounds(610, 15, 60, 20);
		lCentro.setFont(Apariencia.cambiaFuente());
		pantalla.add(lCentro);
		
		jtfnfCentro = new JTextFieldNumeroFijo(3);
		jtfnfCentro.setBounds(670, 15, 40, 25);
		//jtfnfCentro.addActionListener(new NumeroEfectoListener());
		jtfnfCentro.setFont(Apariencia.cambiaFuente());
		if(DatosComunes.sisTienda == 1)
			jtfnfCentro.setEnabled(false);
		pantalla.add(jtfnfCentro);
		
		// Datos previsión
		Border borderDatosPrevision = BorderFactory.createRaisedBevelBorder();
		Border titledBorderDatosPrevision = new TitledBorder(borderDatosPrevision, "Datos Previsión", TitledBorder.LEFT, TitledBorder.TOP, Apariencia.cambiaFuente(), Color.BLACK);
		JPanel pDatosPrevision = new JPanel();
		pDatosPrevision.setLayout(null);
		pDatosPrevision.setBorder(titledBorderDatosPrevision);
		pDatosPrevision.setBounds(10, 50, 750, 240);
		pantalla.add(pDatosPrevision);
		
		lCuenta = new JLabel("Cuenta");
		lCuenta.setBounds(10, 30, 110, 20);
		lCuenta.setFont(Apariencia.cambiaFuente());
		pDatosPrevision.add(lCuenta);
			
		jbIndiceCuentaContable = new JButton();
		jbIndiceCuentaContable.setBounds(120, 30, 25, 25);
		try {
			imgBuscar = ImageIO.read(getClass().getResource("/imagenes/BUSCAR.gif"));
			jbIndiceCuentaContable.setIcon(new ImageIcon(imgBuscar));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		jbIndiceCuentaContable.addActionListener(new BotonIndiceCuentaContableListener());
		jbIndiceCuentaContable.setFocusable(false);
		pDatosPrevision.add(jbIndiceCuentaContable);
		
		jtfnfCuentaContable = new JTextFieldNumeroFijo(9);
		jtfnfCuentaContable.setBounds(150, 30, 100, 25);
		jtfnfCuentaContable.addActionListener(new CuentaContableListener());
		jtfnfCuentaContable.setFont(Apariencia.cambiaFuente());
		pDatosPrevision.add(jtfnfCuentaContable);
		
		lDescripcionCuenta = new JLabel("");
		lDescripcionCuenta.setBounds(260, 30, 400, 20);
		lDescripcionCuenta.setFont(Apariencia.cambiaFuente());
		pDatosPrevision.add(lDescripcionCuenta);
						
		lBancoDomiciliacion = new JLabel("Banco");
		lBancoDomiciliacion.setBounds(10, 60, 110, 20);
		lBancoDomiciliacion.setFont(Apariencia.cambiaFuente());
		pDatosPrevision.add(lBancoDomiciliacion);
			
		jbIndiceBanco = new JButton();
		jbIndiceBanco.setBounds(120, 60, 25, 25);
		try {
			imgBuscar = ImageIO.read(getClass().getResource("/imagenes/BUSCAR.gif"));
			jbIndiceBanco.setIcon(new ImageIcon(imgBuscar));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		jbIndiceBanco.addActionListener(new BotonIndiceBancoListener());
		jbIndiceBanco.setFocusable(false);
		pDatosPrevision.add(jbIndiceBanco);
		
		jtfnfBancoDomiciliacion = new JTextFieldNumeroFijo(4);
		jtfnfBancoDomiciliacion.setBounds(150, 60, 60, 25);
		jtfnfBancoDomiciliacion.addActionListener(new CuentaContableListener());
		jtfnfBancoDomiciliacion.setFont(Apariencia.cambiaFuente());
		pDatosPrevision.add(jtfnfBancoDomiciliacion);
		
		lDescripcionBanco = new JLabel("");
		lDescripcionBanco.setBounds(260, 60, 200, 20);
		lDescripcionBanco.setFont(Apariencia.cambiaFuente());
		pDatosPrevision.add(lDescripcionBanco);		
		
		lDocumento = new JLabel("Documento");
		lDocumento.setBounds(10, 90, 110, 20);
		lDocumento.setFont(Apariencia.cambiaFuente());
		pDatosPrevision.add(lDocumento);			
		
		jtfnfDocumento = new JTextFieldNumeroFijo(6);
		jtfnfDocumento.setBounds(150, 90, 60, 25);
		jtfnfDocumento.addActionListener(new CuentaContableListener());
		jtfnfDocumento.setFont(Apariencia.cambiaFuente());
		pDatosPrevision.add(jtfnfDocumento);
		
		lFechaFactura = new JLabel("Fecha Factura");
		lFechaFactura.setBounds(10, 120, 110, 20);
		lFechaFactura.setFont(Apariencia.cambiaFuente());
		pDatosPrevision.add(lFechaFactura);			
		
		jtffeFechaFactura = new JTextFieldFecha();
		jtffeFechaFactura.setBounds(150, 120, 90, 25);
		jtffeFechaFactura.setFont(Apariencia.cambiaFuente());
		pDatosPrevision.add(jtffeFechaFactura);
		
		lImporte = new JLabel("Importe");
		lImporte.setBounds(10, 150, 110, 20);
		lImporte.setFont(Apariencia.cambiaFuente());
		pDatosPrevision.add(lImporte);			
		
		jtfn2Importe = new JTextFieldNumero2Decimales();
		jtfn2Importe.setBounds(150, 150, 90, 25);
		jtfn2Importe.setFont(Apariencia.cambiaFuente());
		pDatosPrevision.add(jtfn2Importe);
		
		lFechaPago = new JLabel("Fecha Pago");
		lFechaPago.setBounds(10, 180, 110, 20);
		lFechaPago.setFont(Apariencia.cambiaFuente());
		pDatosPrevision.add(lFechaPago);			
		
		jtffeFechaPago = new JTextFieldFecha();
		jtffeFechaPago.setBounds(150, 180, 90, 25);
		jtffeFechaPago.setFont(Apariencia.cambiaFuente());
		pDatosPrevision.add(jtffeFechaPago);
		
		// Datos Pagaré
		Border borderDatosPagare = BorderFactory.createRaisedBevelBorder();
		Border titledBorderDatosPagare = new TitledBorder(borderDatosPagare, "Datos Pagaré/Confirming, etc.", TitledBorder.LEFT, TitledBorder.TOP, Apariencia.cambiaFuente(), Color.BLACK);
		JPanel pDatosPagare = new JPanel();
		pDatosPagare.setLayout(null);
		pDatosPagare.setBorder(titledBorderDatosPagare);
		pDatosPagare.setBounds(450, 90, 280, 130);
		pDatosPrevision.add(pDatosPagare);
		
		lNumeroPagare = new JLabel("Numero Pagaré");
		lNumeroPagare.setBounds(10, 30, 150, 20);
		lNumeroPagare.setFont(Apariencia.cambiaFuente());
		pDatosPagare.add(lNumeroPagare);			
		
		jtfnfNumeroPagare = new JTextFieldNumeroFijo(10);
		jtfnfNumeroPagare.setBounds(170, 30, 60, 25);
		jtfnfNumeroPagare.addActionListener(new CuentaContableListener());
		jtfnfNumeroPagare.setFont(Apariencia.cambiaFuente());
		pDatosPagare.add(jtfnfNumeroPagare);
		
		lFechaPagare = new JLabel("Fecha Pagaré");
		lFechaPagare.setBounds(10, 60, 110, 20);
		lFechaPagare.setFont(Apariencia.cambiaFuente());
		pDatosPagare.add(lFechaPagare);			
		
		jtffeFechaPagare = new JTextFieldFecha();
		jtffeFechaPagare.setBounds(170, 60, 100, 25);
		jtffeFechaPagare.setFont(Apariencia.cambiaFuente());
		pDatosPagare.add(jtffeFechaPagare);
		
		lBancoPagare = new JLabel("Banco Pagaré");
		lBancoPagare.setBounds(10, 90, 150, 20);
		lBancoPagare.setFont(Apariencia.cambiaFuente());
		pDatosPagare.add(lBancoPagare);			
		
		jtfnfBancoPagare = new JTextFieldNumeroFijo(4);
		jtfnfBancoPagare.setBounds(170, 90, 60, 25);
		jtfnfBancoPagare.addActionListener(new CuentaContableListener());
		jtfnfBancoPagare.setFont(Apariencia.cambiaFuente());
		pDatosPagare.add(jtfnfBancoPagare);
		
		jbDepuracion = new JButton("Depurar");
		jbDepuracion.setBounds(380, 320, 90, 30);
		jbDepuracion.setFont(Apariencia.cambiaFuente());
		jbDepuracion.addActionListener(new BotonDepurarListener());
		pantalla.add(jbDepuracion);
		
		jbBorrar = new JButton("Borrar");
		jbBorrar.setBounds(480, 320, 90, 30);
		jbBorrar.setFont(Apariencia.cambiaFuente());
		jbBorrar.addActionListener(new BotonBorrarListener());
		pantalla.add(jbBorrar);
		
		
		jbGrabar = new JButton("Grabar");
		jbGrabar.setBounds(580, 320, 90, 30);
		jbGrabar.setFont(Apariencia.cambiaFuente());
		jbGrabar.addActionListener(new BotonGrabarListener());
		pantalla.add(jbGrabar);
		
		
		
		jbAtras = new JButton();
		jbAtras.setBounds(690, 320, 30, 30);
		jbAtras.setFont(Apariencia.cambiaFuente());
		jbAtras.addActionListener(new AtrasListener());
		try {
			imgAtras = ImageIO.read(getClass().getResource("/imagenes/Atras.gif"));
			jbAtras.setIcon(new ImageIcon(imgAtras));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		pantalla.add(jbAtras);
		
		jbAdelante = new JButton();
		jbAdelante.setBounds(730, 320, 30, 30);
		jbAdelante.setFont(Apariencia.cambiaFuente());
		jbAdelante.addActionListener(new AdelanteListener());
		try {
			imgAdelante = ImageIO.read(getClass().getResource("/imagenes/Adelante.gif"));
			jbAdelante.setIcon(new ImageIcon(imgAdelante));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		pantalla.add(jbAdelante);
									
		jbSalir = new JButton();
		jbSalir.setBounds(20, 320, 30, 30);
		jbSalir.setFont(Apariencia.cambiaFuente());
		jbSalir.addActionListener(new SalirListener());
		try {
			imgSalir = ImageIO.read(getClass().getResource("/imagenes/SALIR.gif"));
			jbSalir.setIcon(new ImageIcon(imgSalir));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		pantalla.add(jbSalir);
		
		pantalla.setVisible(true);
	}
	
	class FechaVencimientoListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int fechaVencimiento, numeroEfecto;
			
			if(util.Fecha.fechaValida(jtffeVencimiento.getText()))
				fechaVencimiento = util.Cadena.cadenaAfecha(jtffeVencimiento.getText());
			else
				fechaVencimiento = 0;
			
			jtffeVencimiento.setText(util.Fecha.fechaAcadena(fechaVencimiento));
			
			if(jtfnfEfecto.getText().trim().length() == 0)
				numeroEfecto = 0;
			else
				numeroEfecto = Integer.valueOf(jtfnfEfecto.getText());
			
			if(fechaVencimiento != 0)
				cargaPorFechaYnumeroVencimiento(fechaVencimiento, numeroEfecto);
		}
		
	}
	
	class BotonIndicePrevisionesPagoListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		
		if(indicePrevisionesPago == null)
			indicePrevisionesPago = new IndicePrevisionesPago();
		else
			indicePrevisionesPago.muestra();
		
		efectoPagar = indicePrevisionesPago.getEfectoPagar();
			
		cargaPorFechaYnumeroVencimiento(efectoPagar.getVencimiento(), efectoPagar.getEfecto());
		}
		
	}
	
	class NumeroEfectoListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int fechaVencimiento, numeroEfecto;
			
			fechaVencimiento = util.Cadena.cadenaAfecha(jtffeVencimiento.getText());
			if(jtfnfEfecto.getText().trim().length() == 0)
				numeroEfecto = 0;
			else
				numeroEfecto = Integer.valueOf(jtfnfEfecto.getText());
			
			if(fechaVencimiento != 0)
				cargaPorFechaYnumeroVencimiento(fechaVencimiento, numeroEfecto);
		}
		
	}
	
	class BotonIndiceCuentaContableListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String strCuenta = "";
			
			IndiceCuentas ic = new IndiceCuentas();
			
			strCuenta = ic.getCuenta();
			
			jtfnfCuentaContable.setText(strCuenta);
			
			//Tenemos que averiguar la descripción de la cuenta contable
			
			Cuenta cuenta = new Cuenta();
			
			if(cuenta.existeCuenta(strCuenta, efectoPagar.getCentro())){
				cuenta.read(strCuenta, efectoPagar.getCentro());
				lDescripcionCuenta.setText(cuenta.getTitulo());
				jtfnfCuentaContable.transferFocus();
			}
		}
		
	}
	
	class CuentaContableListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String strCuenta = "";
			
			strCuenta = jtfnfCuentaContable.getText().trim();
			//Tenemos que averiguar la descripción de la cuenta contable
			
			Cuenta cuenta = new Cuenta();
			
			if(cuenta.existeCuenta(strCuenta, efectoPagar.getCentro())){
				cuenta.read(strCuenta, efectoPagar.getCentro());
				lDescripcionCuenta.setText(cuenta.getTitulo());
				jtfnfCuentaContable.transferFocus();
			}
		}
		
	}
	
	class BotonIndiceBancoListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class BotonDepurarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class BotonBorrarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {                        
			int centro = DatosComunes.centroCont;
			int fechaEfecto = util.Cadena.cadenaAfecha(jtffeVencimiento.getText());
			int numeroEfecto = Integer.valueOf(jtfnfEfecto.getText());
			
			if(centro == 0 && jtfnfCentro.getText().trim().length() == 0)
				centro = 1;
			if(centro == 0 && jtfnfCentro.getText().trim() == "0")
				centro = 1;
			if(centro == 0 && Integer.valueOf(jtfnfCentro.getText().trim()) > 0)
				centro = Integer.valueOf(jtfnfCentro.getText().trim());
			
			String strSql = "SELECT * FROM EFEPAG WHERE EMPRESA = '" + 
	         DatosComunes.eEmpresa + 
	         "' AND EFEPAG_VENCIM = " + fechaEfecto + " AND EFEPAG_EFECTO = " + numeroEfecto;
			
			if(DatosComunes.centroCont != 0)
				strSql += " AND EFEPAG_CENTRO = " + centro;				

			// Comprobramos si existe el efecto, SOLO tiene que existir uno que cumpla la condición
			if(BaseDatos.countRows(strSql) == 1){
				efectoPagar.read(strSql);
				
				// Damos la oportunidad de no borrar
				Object[] opciones = {"Si", "No"};
				int n = JOptionPane.showOptionDialog(pantalla,
						"<html><font size='4'><strong>" +
						"Desea borrar el efecto con vencimiento:<BR>'" 
						+ fechaEfecto + "  y número de efecto: " + numeroEfecto  
						+"'</strong></font></html>",
						"Borrar Registro",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,     // Sin Icono personalizado.
						opciones,  // Título de los botonoes
						opciones[1]); // Botón por defecto.

				if(n == 0){
					if(efectoPagar.delete(centro, fechaEfecto, numeroEfecto) > 0){
						Apariencia.mensajeInformativo(5, "Efecto borrado correctamente...");
					}else{
						Apariencia.mensajeInformativo(5, "No existe este vencimiento!!!<BR>Revisarlo!!!");
					}					
				}
			}else{
				JOptionPane.showMessageDialog(null, "<html><font size='4'><strong>" + 
						"No se puede borrar ese Efecto a Pagar porque no existe!!!" + 
				"</strong></font></html>");
			}			
		}
		
	}
	
	class BotonGrabarListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int fechaVencimiento = util.Cadena.cadenaAfecha(jtffeVencimiento.getText().trim());
			int numeroEfecto = 0;
			int centro = DatosComunes.centroCont;
			
			if(jtfnfEfecto.getText().trim().length() == 0)
				numeroEfecto = 0;
			else
				numeroEfecto = Integer.valueOf(jtfnfEfecto.getText().trim());
			
			if(fechaVencimiento == 0 || numeroEfecto == 0)
				Apariencia.mensajeInformativo(5, "Sin Fecha de Vencimiento o sin Numero de Efecto -> No se graba!!!");
			else{
				efectoPagar.setEmpresa(DatosComunes.eEmpresa);
				efectoPagar.setVencimiento(fechaVencimiento);
				efectoPagar.setEfecto(numeroEfecto);
				if(jtfnfCentro.getText().trim().length() > 0)
					centro = Integer.valueOf(jtfnfCentro.getText().trim());
				efectoPagar.setCentro(centro);
				efectoPagar.setCuenta(jtfnfCuentaContable.getText().trim());
				efectoPagar.setFechaPago(util.Cadena.cadenaAfecha(jtffeFechaPago.getText().trim()));
				efectoPagar.setBanco(Integer.valueOf(jtfnfBancoDomiciliacion.getText().trim()));
				efectoPagar.setDocumentoOrigen(Integer.valueOf(jtfnfDocumento.getText().trim()));
				efectoPagar.setFechaFactura(util.Cadena.cadenaAfecha(jtffeFechaFactura.getText().trim()));
				efectoPagar.setImporte(jtfn2Importe.getDouble());
				efectoPagar.setFechaPagare(util.Cadena.cadenaAfecha(jtffeFechaPagare.getText().trim()));
				efectoPagar.setBancoPagare(Integer.valueOf(jtfnfBancoPagare.getText().trim()));
				efectoPagar.setPagare(Integer.valueOf(jtfnfNumeroPagare.getText().trim()));
				efectoPagar.write();
			}
		}
	}
	
	class AtrasListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int fechaVencimiento = util.Cadena.cadenaAfecha(jtffeVencimiento.getText().trim());
			int numeroEfecto = 0;
			
			// Apariencia.mensajeInformativo(5, "Fecha Vencimiento: " + fechaVencimiento);
			
			if(jtfnfEfecto.getText().trim().length() == 0)
				numeroEfecto = 0;
			else
				numeroEfecto = Integer.valueOf(jtfnfEfecto.getText().trim());
						
			// Primero vamos a ver si en esa fecha hay un efecto con Numero de Efecto superior.
			// Si no es así, tendremos que buscarlo en la siguiente fecha.
			String strSql = "SELECT * FROM EFEPAG WHERE EMPRESA = '" + 
	         DatosComunes.eEmpresa + 
	         "' AND EFEPAG_VENCIM = " + fechaVencimiento;
			
			if(DatosComunes.centroCont != 0)
				strSql += " AND EFEPAG_CENTRO = " + DatosComunes.centroCont;
			
	        strSql += " AND EFEPAG_EFECTO < " + numeroEfecto + " ORDER BY EFEPAG_VENCIM, EFEPAG_EFECTO DESC LIMIT 1";
	        
	        if(BaseDatos.countRows(strSql) == 0){
	        	// Significa que en esa fecha no hay mas efectos, tenemos que fuscar en la siguiente fecha
	        	strSql = "SELECT * FROM EFEPAG WHERE EMPRESA = '" + 
		         DatosComunes.eEmpresa + 
		         "' AND EFEPAG_VENCIM < " + fechaVencimiento;
				
				if(DatosComunes.centroCont != 0)
					strSql += " AND EFEPAG_CENTRO = " + DatosComunes.centroCont;
				
		        strSql += " ORDER BY EFEPAG_VENCIM DESC LIMIT 1";	        	
	        }
			
			cargaDatos(strSql);		
		}
	}
	
	class AdelanteListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int fechaVencimiento = util.Cadena.cadenaAfecha(jtffeVencimiento.getText().trim());
			int numeroEfecto = 0;
			
			// Apariencia.mensajeInformativo(5, "Fecha Vencimiento: " + fechaVencimiento);
			
			if(jtfnfEfecto.getText().trim().length() == 0)
				numeroEfecto = 0;
			else
				numeroEfecto = Integer.valueOf(jtfnfEfecto.getText().trim());
						
			// Primero vamos a ver si en esa fecha hay un efecto con Numero de Efecto superior.
			// Si no es así, tendremos que buscarlo en la siguiente fecha.
			String strSql = "SELECT * FROM EFEPAG WHERE EMPRESA = '" + 
	         DatosComunes.eEmpresa + 
	         "' AND EFEPAG_VENCIM = " + fechaVencimiento;
			
			if(DatosComunes.centroCont != 0)
				strSql += " AND EFEPAG_CENTRO = " + DatosComunes.centroCont;
			
	        strSql += " AND EFEPAG_EFECTO > " + numeroEfecto + " LIMIT 1";
	        
	        if(BaseDatos.countRows(strSql) == 0){
	        	// Significa que en esa fecha no hay mas efectos, tenemos que fuscar en la siguiente fecha
	        	strSql = "SELECT * FROM EFEPAG WHERE EMPRESA = '" + 
		         DatosComunes.eEmpresa + 
		         "' AND EFEPAG_VENCIM > " + fechaVencimiento;
				
				if(DatosComunes.centroCont != 0)
					strSql += " AND EFEPAG_CENTRO = " + DatosComunes.centroCont;
				
		        strSql += " LIMIT 1";	        	
	        }
			
			cargaDatos(strSql);		
		}
	}
	
	class SalirListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private void cargaPorFechaYnumeroVencimiento(int fechaVencimiento, int numeroEfecto){
		
		String strSql = "SELECT * FROM EFEPAG WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' ";
		
		if(DatosComunes.centroCont != 0)
			strSql += " AND EFEPAG_CENTRO = " + DatosComunes.centroCont;
		strSql += " AND EFEPAG_VENCIM = " + fechaVencimiento + " AND EFEPAG_EFECTO = " + numeroEfecto;
		
		// Si no tenemos fecha de vencimiento, no dejamos hacer nada 
		if(fechaVencimiento != 0){		
			if(BaseDatos.countRows(strSql) == 0){						
				borrarPantalla();
				// Tenemos fecha de vencimiento pero no tenemos número de efecto, entonces
				// tenemos que buscar el siguiente número de efecto para esa fecha.
				int nuevoEfecto = 0;
				
				strSql = "SELECT * FROM EFEPAG WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' ";
				
				if(DatosComunes.centroCont != 0)
					strSql += " AND EFEPAG_CENTRO = " + DatosComunes.centroCont;
				strSql += " AND EFEPAG_VENCIM = " + fechaVencimiento + " ORDER BY EFEPAG_EFECTO DESC LIMIT 1";
				
				// Si no deuelve registros, significa que ese día no hay efectos, con lo que tiene
				// que ser el efecto 1 de ese día.
				if(BaseDatos.countRows(strSql) == 0){
					nuevoEfecto = 1;
				}else{
					// Ese día ya hay efectos y sabemos cual es el último del día
					efectoPagar.read(strSql);
					nuevoEfecto = efectoPagar.getEfecto() + 1;
				}
				jtffeVencimiento.setText(util.Cadena.fechaAcadena(fechaVencimiento));
				jtfnfEfecto.setText(String.valueOf(nuevoEfecto));
				jtfnfCentro.setText(String.valueOf(DatosComunes.centroCont));
			}else{				
				cargaDatos(strSql);		
			}
		}				      
	}
	
	private void borrarPantalla(){
		jtffeVencimiento.setText("");
		jtfnfEfecto.setText("0");
		jtfnfCentro.setText(String.valueOf(DatosComunes.centroCont));
		jtfnfCuentaContable.setText("");
		lDescripcionCuenta.setText("");
		jtfnfBancoDomiciliacion.setText("0");
		jtfnfDocumento.setText("0");
		jtffeFechaFactura.setText("");
		jtfn2Importe.setText("0,00");
		jtffeFechaPago.setText("");
		jtfnfNumeroPagare.setText("0");
		jtffeFechaPagare.setText("");
		jtfnfBancoPagare.setText("0");
	}
	
	private void cargaDatos(String strSql){
		
		efectoPagar.read(strSql);
		
		jtffeVencimiento.setText(util.Cadena.fechaAcadena(efectoPagar.getVencimiento()));
		jtfnfEfecto.setText(String.valueOf(efectoPagar.getEfecto()));
		jtfnfCentro.setText(String.valueOf(efectoPagar.getCentro()));
		jtfnfCuentaContable.setText(efectoPagar.getCuenta());
		jtfnfBancoDomiciliacion.setText(String.valueOf(efectoPagar.getBanco()));
		jtfnfDocumento.setText(String.valueOf(efectoPagar.getDocumentoOrigen()));
		jtffeFechaFactura.setText(util.Cadena.fechaAcadena(efectoPagar.getFechaFactura()));
		jtfn2Importe.setText(String.valueOf(efectoPagar.getImporte()));
		jtffeFechaPago.setText(util.Cadena.fechaAcadena(efectoPagar.getFechaPago()));
		jtfnfNumeroPagare.setText(String.valueOf(efectoPagar.getPagare()));
		jtffeFechaPagare.setText(util.Cadena.fechaAcadena(efectoPagar.getFechaPagare()));
		jtfnfBancoPagare.setText(String.valueOf(efectoPagar.getBancoPagare()));	
		
		//Tenemos que averiguar la descripción de la cuenta contable
		
		Cuenta cuenta = new Cuenta();
		
		if(cuenta.existeCuenta(efectoPagar.getCuenta(), efectoPagar.getCentro())){
			cuenta.read(efectoPagar.getCuenta(), efectoPagar.getCentro());
			lDescripcionCuenta.setText(cuenta.getTitulo());
		}
	}
}
