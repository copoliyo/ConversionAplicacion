package consultasMantenimientos;


import general.DatosComunes;
import general.MysqlConnect;
import indices.IndiceAcumuladosContables;
import indices.IndiceAcumuladosEstadisticos;
import indices.IndiceBancosSucursales;
import indices.IndiceFacturasRecibidas;
import indices.IndiceMovimientosContables;
import indices.IndicePaises;
import indices.IndicePoblaciones;
import indices.IndicePrevisionesPago;
import indices.IndiceProveedores;
import indices.IndiceProvincias;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

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

import mantenimientos.ManNotas;
import tablas.Cuenta;
import tablas.Proveedor;
import util.Apariencia;
import util.AsignacionAutomaticaDeCodigos;
import util.BaseDatos;
import util.Cadena;
import util.CuentaBancaria;
import util.EscapeDialog;
import util.JTextFieldFijo;
import util.JTextFieldNumero2Decimales;
import util.JTextFieldNumeroFijo;

public class ConManProveedor {
	
	// Con esta variable definimos si estamos en una consulta (TRUE) o en
	// un mantenimiento (FALSE). Nos servirá para tener un sólo programa
	// para algunas Consultas/Mantenimientos que pueden compartir las 
	// mismas pantallas.
	private static boolean consulta;
	private CuentaBancaria cb = null;
	
        private String cuentaProveedor = "";
        
	IndicePoblaciones indicePoblaciones = null;
	IndiceProvincias indiceProvincias = null;
	IndicePaises indicePaises = null;
	
	static ResultSet rs = null;
	static MysqlConnect m = null;
	
	public Proveedor proveedor = new Proveedor();
	
	// Definiciones de componentes de pantalla
	public JFrame frameMenu = null;
	
	JDialog pantalla;
	JLabel lCuentaContable, lCentro, lNombre, lApellidosRazon, lContacto, lAttDireccion,
	       lDireccion, lPais, lPoblacion, lCodigoPostal, lTelefono, lFax, lProvincia,
	       lNif, lSuCliente, lObservaciones, lEMail, lSaldo, lNumeroEfectos, lIntervalo, 
	       lPrimerVencimiento, lBanco, lSucursal, lDigitosControl, lCuentaBancaria, lIBAN,
	       lDtoPP, lDtoComercial, lTipoProveedor, lConfictividad, lTipoFacturacion, lRappel, 
	       lTipoRappel, lIva;	
	
	JTextFieldNumeroFijo jtnfCuentaContable, jtnfCentro;	
	
	JTextFieldFijo jtfNombre, jtfApellidosRazon, jtfContacto, jtfAttDireccion, jtfDireccion,
	       jtfPais, jtfPoblacion, jtfCodigoPostal, jtfTelefono, jtfFax, jtfProvincia, jtfNif,
	       jtfSuCliente, jtfObservaciones, jtfEMail, jtfNumeroEfectos, jtfIBAN,
	       jtfIntervalo, jtfPrimervencimiento, jtfBanco, jtfSucursal, jtfDigitosControl,
	       jtfCuentaBancaria, jtfTipoProveedor, jtfConfictividad,
	       jtfTipoFacturacion, jtfTipoRappel, jtfIva;
	
	JTextFieldNumero2Decimales jtn2DtoPP, jtn2DtoComercial, jtn2Rappel, jtn2Saldo; 
	
	JCheckBox jcActivado, jcSeNegocia, jcEmisionPagares;
	
	JButton jbIndiceCuentaContable, jbIndicePais, jbIndicePoblacion, jbIndiceTelefono,
	       jbIndiceProvincia, jbIndiceNif, jbNotas, jbIndiceBanco, jbIndiceSucursal,
	       jbAsignarCodigo,
	       jbBorrar, jbGrabar, jbAtras, jbAdelante, jbAcumuladoContable, jbAcumuladoEstadistico,
	       jbMovimientos, jbFacturas, jbUnaFactura, jbPrevisionesCobro, jbPrevisionesPago,
	       jbSalir, jbNuevo;

	JComboBox jcbTipoIva;
	
	Image imgBuscar, imgAtras, imgAdelante, imgSalir, imgNuevo;

        public ConManProveedor(boolean consultaOmantenimiento){
		this.consulta = consultaOmantenimiento;
		//this.consulta = false;
		m = MysqlConnect.getDbCon();
                cuentaProveedor = "";
		creaGui();
	}
        
	public ConManProveedor(boolean consultaOmantenimiento, String cuentaContableProveedor){
		this.consulta = consultaOmantenimiento;
		//this.consulta = false;
		m = MysqlConnect.getDbCon();
                cuentaProveedor = cuentaContableProveedor;
		creaGui();
	}
	
	public ConManProveedor(JFrame parentFrame, boolean consultaOmantenimiento){
		frameMenu = parentFrame;
		this.consulta = consultaOmantenimiento;
		//this.consulta = false;
		m = MysqlConnect.getDbCon();
                cuentaProveedor = "";
		creaGui();
	}
	
	private void creaGui(){
		//pantalla = new JDialog();
		pantalla = new EscapeDialog();
		if(consulta)
			pantalla.setTitle("Consulta Proveedores / Acreedores");
		else
			pantalla.setTitle("Mantenimiento Proveedores / Acreedores");
		pantalla.setModal(true);
		pantalla.setSize(970, 600);
		pantalla.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		pantalla.setLayout(null);
		pantalla.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//frameMenu.setVisible(true);
				frameMenu.setEnabled(true);
				pantalla.dispose();
			}

		});
		
		lCuentaContable = new JLabel("Cuenta");
		lCuentaContable.setBounds(10, 15, 90, 20);
		lCuentaContable.setFont(Apariencia.cambiaFuente());
		pantalla.add(lCuentaContable);
				
		jbIndiceCuentaContable = new JButton();
		jbIndiceCuentaContable.setBounds(80, 15, 25, 25);
		jbIndiceCuentaContable.addActionListener(new IndiceCuentaContableListener());
		try {
			imgBuscar = ImageIO.read(getClass().getResource("/imagenes/BUSCAR.gif"));
			jbIndiceCuentaContable.setIcon(new ImageIcon(imgBuscar));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		jbIndiceCuentaContable.setFocusable(false);
		pantalla.add(jbIndiceCuentaContable);
		
		jtnfCuentaContable = new JTextFieldNumeroFijo(9);
		jtnfCuentaContable.setBounds(110, 15, 120, 25);
		jtnfCuentaContable.setTextoAyuda("Cuenta contable del proveedor o acreedor.<BR>" +
				"Pinchar en 'Asignar Código' para obtener codigo adecuado.", 4);
		jtnfCuentaContable.setFont(Apariencia.cambiaFuente());
		jtnfCuentaContable.addActionListener(new CuentaContableListener());
		pantalla.add(jtnfCuentaContable);
		
		jbNuevo = new JButton();
		jbNuevo.setBounds(250, 15, 30, 30);
		jbNuevo.addActionListener(new NuevoListener());
		try {
			imgNuevo = ImageIO.read(getClass().getResource("/imagenes/Nuevo.gif"));
			jbNuevo.setIcon(new ImageIcon(imgNuevo));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		jbNuevo.setFocusable(false);
		pantalla.add(jbNuevo);
		
		lCentro = new JLabel("Centro");
		lCentro.setBounds(300, 15, 90, 20);
		lCentro.setFont(Apariencia.cambiaFuente());
		pantalla.add(lCentro);
		
		// Dastos identificación
		Border borderDatosIdentificacion = BorderFactory.createRaisedBevelBorder();
		Border titledBorderDatosIdentificacion = new TitledBorder(borderDatosIdentificacion, "Datos Identificación", TitledBorder.LEFT, TitledBorder.TOP, Apariencia.cambiaFuente(), Color.BLACK);
		JPanel pDatosIdentificacion = new JPanel();
		pDatosIdentificacion.setLayout(null);
		pDatosIdentificacion.setBorder(titledBorderDatosIdentificacion);
		pDatosIdentificacion.setBounds(10, 50, 560, 400);
		pantalla.add(pDatosIdentificacion);
				
		lNombre = new JLabel("Nombre");
		lNombre.setBounds(10, 30, 90, 20);
		lNombre.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(lNombre);
		
		jtfNombre = new JTextFieldFijo(16);
		jtfNombre.setBounds(180, 30, 200, 25);
		jtfNombre.setTextoAyuda("En caso de ser persona física poner nombre.<BR>" +
				"En caso de ser empresa, dejar vacio.", 4);
		jtfNombre.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(jtfNombre);
		
		lApellidosRazon = new JLabel("Apellidos/Razón");
		lApellidosRazon.setBounds(10, 60, 180, 20);
		lApellidosRazon.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(lApellidosRazon);
		
		jtfApellidosRazon = new JTextFieldFijo(30);
		jtfApellidosRazon.setBounds(180, 60, 350, 25);
		jtfApellidosRazon.setTextoAyuda("En caso de ser persona física poner apellidos.<BR>" +
				"En caso de ser empresa, poner la Razón Social.", 4);
		jtfApellidosRazon.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(jtfApellidosRazon);
		
		lContacto = new JLabel("Contacto");
		lContacto.setBounds(10, 90, 180, 20);
		lContacto.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(lContacto);
		
		jtfContacto = new JTextFieldFijo(30);
		jtfContacto.setBounds(180, 90, 350, 25);
		jtfContacto.setTextoAyuda("Persona de contacto.<BR>" +
				"Se puede dejar vacío.", 4);
		jtfContacto.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(jtfContacto);
		
		lAttDireccion = new JLabel("Att.Direccion");
		lAttDireccion.setBounds(10, 120, 180, 20);
		lAttDireccion.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(lAttDireccion);
		
		jtfAttDireccion = new JTextFieldFijo(30);
		jtfAttDireccion.setBounds(180, 120, 350, 25);
		jtfAttDireccion.setTextoAyuda("Para poner a la Atención de persona o departamento concretos.<BR>" +
				"También se puede usar para aumentar el campo de dirección.<BR>" +
				"Se puede dejar vacío.", 4);
		jtfAttDireccion.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(jtfAttDireccion);
		
		lDireccion = new JLabel("Direccion");
		lDireccion.setBounds(10, 150, 180, 20);
		lDireccion.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(lDireccion);
		
		jtfDireccion = new JTextFieldFijo(30);
		jtfDireccion.setBounds(180, 150, 350, 25);
		jtfDireccion.setTextoAyuda("Dirección.<BR>" +
				"Se puede dejar vacío.", 4);
		jtfDireccion.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(jtfDireccion);
		
		lPais = new JLabel("Pais");
		lPais.setBounds(10, 180, 60, 20);
		lPais.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(lPais);
		
		jbIndicePais = new JButton();
		jbIndicePais.setBounds(50, 180, 25, 25);
		jbIndicePais.addActionListener(new BotonIndicePaisListener());
		jbIndicePais.setIcon(new ImageIcon(imgBuscar));
		jbIndicePais.setFocusable(false);
		pDatosIdentificacion.add(jbIndicePais);
				
		jtfPais = new JTextFieldFijo(10);
		jtfPais.setBounds(80, 180, 50, 25);
		jtfPais.setTextoAyuda("Código de pais. Normalmente 0.<BR>" +
				"Se puede dejar vacío.", 4);
		jtfPais.setFont(Apariencia.cambiaFuente());
		jtfPais.addActionListener(new PaisListener());
		jtfPais.addFocusListener(new PaisListener());
		pDatosIdentificacion.add(jtfPais);
		
		lPoblacion = new JLabel("Poblacion");
		lPoblacion.setBounds(180, 180, 80, 20);
		lPoblacion.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(lPoblacion);
		
		jbIndicePoblacion = new JButton();
		jbIndicePoblacion.setBounds(270, 180, 25, 25);
		jbIndicePoblacion.addActionListener(new PoblacionListener());
		jbIndicePoblacion.setIcon(new ImageIcon(imgBuscar));
		jbIndicePoblacion.setFocusable(false);
		pDatosIdentificacion.add(jbIndicePoblacion);
				
		jtfPoblacion = new JTextFieldFijo(20);
		jtfPoblacion.setBounds(300, 180, 230, 25);
		jtfPoblacion.setTextoAyuda("Población.<BR>" +
				"Se puede dejar vacío.", 4);
		jtfPoblacion.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(jtfPoblacion);
				
		lCodigoPostal = new JLabel("Cp");
		lCodigoPostal.setBounds(10, 210, 50, 20);
		lCodigoPostal.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(lCodigoPostal);
		
		jtfCodigoPostal = new JTextFieldFijo(5);
		jtfCodigoPostal.setBounds(60, 210, 70, 25);
		jtfCodigoPostal.setTextoAyuda("Código Postal según Correos.<BR>" +
				"No se puede dejar vacío.", 4);
		jtfCodigoPostal.setFont(Apariencia.cambiaFuente());
		jtfCodigoPostal.addActionListener(new CodigoPostalListener());
		jtfCodigoPostal.addFocusListener(new CodigoPostalListener());
		pDatosIdentificacion.add(jtfCodigoPostal);
		
		lTelefono = new JLabel("Teléfono");
		lTelefono.setBounds(150, 210, 80, 20);
		lTelefono.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(lTelefono);
		
		jbIndiceTelefono = new JButton();
		jbIndiceTelefono.setBounds(230, 210, 25, 25);
		//jbIndiceCuentaContable.addActionListener(new SalirListener());
		jbIndiceTelefono.setIcon(new ImageIcon(imgBuscar));
		jbIndiceTelefono.setFocusable(false);
		pDatosIdentificacion.add(jbIndiceTelefono);
				
		jtfTelefono = new JTextFieldFijo(16);
		jtfTelefono.setBounds(260, 210, 110, 25);
		jtfTelefono.setTextoAyuda("Teléfono.<BR>" +
				"Se puede dejar vacío.", 4);
		jtfTelefono.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(jtfTelefono);
		
		lFax = new JLabel("Fax");
		lFax.setBounds(380, 210, 40, 20);
		lFax.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(lFax);
				
		jtfFax = new JTextFieldFijo(16);
		jtfFax.setBounds(420, 210, 110, 25);
		jtfFax.setTextoAyuda("Fax.<BR>" +
				"Se puede dejar vacío.", 4);
		jtfFax.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(jtfFax);			
		
		lProvincia = new JLabel("Prov.");
		lProvincia.setBounds(10, 240, 50, 20);
		lProvincia.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(lProvincia);
		
		jbIndiceProvincia = new JButton();
		jbIndiceProvincia.setBounds(50, 240, 25, 25);
		jbIndiceProvincia.addActionListener(new BotonIndiceProvinciaListener());
		jbIndiceProvincia.setIcon(new ImageIcon(imgBuscar));
		jbIndiceProvincia.setFocusable(false);
		pDatosIdentificacion.add(jbIndiceProvincia);
				
		jtfProvincia = new JTextFieldFijo(2);
		jtfProvincia.setBounds(80, 240, 50, 25);
		jtfProvincia.setTextoAyuda("Provincia.<BR>" +
				"No se puede dejar vacío.", 4);
		jtfProvincia.setFont(Apariencia.cambiaFuente());
		jtfProvincia.addActionListener(new ProvinciaListener());
		jtfProvincia.addFocusListener(new ProvinciaListener());
		pDatosIdentificacion.add(jtfProvincia);				
		
		lNif = new JLabel("Nif");
		lNif.setBounds(150, 240, 50, 20);
		lNif.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(lNif);
		
		jbIndiceNif = new JButton();
		jbIndiceNif.setBounds(190, 240, 25, 25);
		//jbIndiceCuentaContable.addActionListener(new SalirListener());
		jbIndiceNif.setIcon(new ImageIcon(imgBuscar));
		jbIndiceNif.setFocusable(false);
		pDatosIdentificacion.add(jbIndiceNif);
				
		// 12 por si es un NIF intracomunitario
		jtfNif = new JTextFieldFijo(12);
		jtfNif.setBounds(220, 240, 110, 25);
		jtfNif.setTextoAyuda("N.I.F. o C.I.F.<BR>" + 
				"Para NIF utilizar el formato 99999999X, por ejemplo: 12345678A<BR>" +
				"Para CIF utilizar el formato X99999999, por ejemplo: B98765432<BR>" +
				"Se puede dejar vacío, pero no deberías", 4);
		jtfNif.setFont(Apariencia.cambiaFuente());
		jtfNif.addActionListener(new NifActionListener());
		jtfNif.addFocusListener(new NifActionListener());
		pDatosIdentificacion.add(jtfNif);
		
		lSuCliente = new JLabel("Su Clte");
		lSuCliente.setBounds(350, 240, 80, 20);
		lSuCliente.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(lSuCliente);		
				
		jtfSuCliente = new JTextFieldFijo(16);
		jtfSuCliente.setBounds(420, 240, 110, 25);
		jtfSuCliente.setTextoAyuda("Que número de cliente somos para este Proveedor/Acreedor.<BR>" +
				"Se puede dejar vacío.", 4);
		jtfSuCliente.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(jtfSuCliente);
		
		lObservaciones = new JLabel("Observaciones");
		lObservaciones.setBounds(10, 270, 120, 20);
		lObservaciones.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(lObservaciones);		
				
		jtfObservaciones = new JTextFieldFijo(30);
		jtfObservaciones.setBounds(140, 270, 390, 25);
		jtfObservaciones.setTextoAyuda("Observaciones.<BR>" +
				"Se puede dejar vacío.", 4);
		jtfObservaciones.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(jtfObservaciones);

		lEMail = new JLabel("EMail");
		lEMail.setBounds(10, 300, 80, 20);
		lEMail.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(lEMail);
				
		jtfEMail = new JTextFieldFijo(60);
		jtfEMail.setBounds(80, 300, 450, 25);
		jtfEMail.setTextoAyuda("Dirección de e-mail.<BR>" +
				"Se puede dejar vacío.", 4);
		jtfEMail.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(jtfEMail);				
		
		// Datos de pago
		Border borderDatosPago = BorderFactory.createRaisedBevelBorder();
		Border titledBorderDatosPago = new TitledBorder(borderDatosPago, "Datos Pago", TitledBorder.LEFT, TitledBorder.TOP, Apariencia.cambiaFuente(), Color.BLACK);
		JPanel pDatosPago = new JPanel();
		pDatosPago.setLayout(null);
		pDatosPago.setBorder(titledBorderDatosPago);
		pDatosPago.setBounds(580, 50, 360, 100);
		pantalla.add(pDatosPago);
		
		lNumeroEfectos = new JLabel("Nro.Efectos");
		lNumeroEfectos.setBounds(10, 30, 120, 20);
		lNumeroEfectos.setFont(Apariencia.cambiaFuente());
		pDatosPago.add(lNumeroEfectos);
		
		jtfNumeroEfectos = new JTextFieldFijo(2);
		jtfNumeroEfectos.setBounds(120, 30, 40, 25);
		jtfNumeroEfectos.setTextoAyuda("Número de efectos que nos gira habitualmente.<BR>" +
				"Se puede dejar vacío.", 4);
		jtfNumeroEfectos.setFont(Apariencia.cambiaFuente());
		pDatosPago.add(jtfNumeroEfectos);
		
		lIntervalo = new JLabel("Intervalo");
		lIntervalo.setBounds(200, 30, 120, 20);
		lIntervalo.setFont(Apariencia.cambiaFuente());
		pDatosPago.add(lIntervalo);
		
		jtfIntervalo = new JTextFieldFijo(3);
		jtfIntervalo.setBounds(280, 30, 60, 25);
		jtfIntervalo.setTextoAyuda("Días que pasan entre cada giro.<BR>" +
				"Se puede dejar vacío.", 4);
		jtfIntervalo.setFont(Apariencia.cambiaFuente());
		pDatosPago.add(jtfIntervalo);
		
		lPrimerVencimiento = new JLabel("Primer Vto.");
		lPrimerVencimiento.setBounds(10, 60, 120, 20);
		lPrimerVencimiento.setFont(Apariencia.cambiaFuente());
		pDatosPago.add(lPrimerVencimiento);
		
		jtfPrimervencimiento = new JTextFieldFijo(3);
		jtfPrimervencimiento.setBounds(120, 60, 40, 25);
		jtfPrimervencimiento.setTextoAyuda("Días que pasan hasta el primer giro.<BR>" +
				"Se puede dejar vacío.", 4);
		jtfPrimervencimiento.setFont(Apariencia.cambiaFuente());
		pDatosPago.add(jtfPrimervencimiento);
		
		
		jcSeNegocia = new JCheckBox("Se negocia");
		jcSeNegocia.setBounds(200, 60, 130, 20);
		jcSeNegocia.setFont(Apariencia.cambiaFuente());
		pDatosPago.add(jcSeNegocia);

		
		// Datos bancarios
		Border borderDatosBancarios = BorderFactory.createRaisedBevelBorder();
		Border titledBorderDatosBancarios = new TitledBorder(borderDatosBancarios, "Datos Bancarios", TitledBorder.LEFT, TitledBorder.TOP, Apariencia.cambiaFuente(), Color.BLACK);
		JPanel pDatosBancarios = new JPanel();
		pDatosBancarios.setLayout(null);
		pDatosBancarios.setBorder(titledBorderDatosBancarios);
		pDatosBancarios.setBounds(580, 150, 360, 130);
		pantalla.add(pDatosBancarios);		

		lBanco = new JLabel("Banco");
		lBanco.setBounds(10, 30, 80, 20);
		lBanco.setFont(Apariencia.cambiaFuente());
		pDatosBancarios.add(lBanco);
		
		jbIndiceBanco = new JButton();
		jbIndiceBanco.setBounds(80, 30, 25, 25);
		jbIndiceBanco.addActionListener(new BancoListener());
		jbIndiceBanco.setIcon(new ImageIcon(imgBuscar));
		jbIndiceBanco.setFocusable(false);
		pDatosBancarios.add(jbIndiceBanco);
				
		jtfBanco = new JTextFieldFijo(4);
		jtfBanco.setBounds(110, 30, 80, 25);
		jtfBanco.setFont(Apariencia.cambiaFuente());
		jtfBanco.setTextoAyuda("Cuatro primeros dígitos de la cuenta contable,<BR>" +
				"sin contar el código IBAN.<BR>" +
				"Se puede dejar vacío.", 4);
		jtfBanco.addActionListener(new BancoActionListener());
		jtfBanco.addFocusListener(new BancoActionListener());
		pDatosBancarios.add(jtfBanco);

		lSucursal = new JLabel("Sucursal");
		lSucursal.setBounds(10, 60, 80, 20);		
		lSucursal.setFont(Apariencia.cambiaFuente());
		pDatosBancarios.add(lSucursal);
		
		jbIndiceSucursal = new JButton();
		jbIndiceSucursal.setBounds(80, 60, 25, 25);
		jbIndiceSucursal.addActionListener(new BotonSucursalListener());
		jbIndiceSucursal.setIcon(new ImageIcon(imgBuscar));
		jbIndiceSucursal.setFocusable(false);
		pDatosBancarios.add(jbIndiceSucursal);
				
		jtfSucursal = new JTextFieldFijo(4);
		jtfSucursal.setBounds(110, 60, 80, 25);
		jtfSucursal.setFont(Apariencia.cambiaFuente());
		jtfSucursal.setTextoAyuda("Cuatro segundos dígitos de la cuenta contable,<BR>" +
				"sin contar el código IBAN.<BR>" +
				"Se puede dejar vacío.", 4);
		jtfSucursal.addActionListener(new SucursalActionListener());
		jtfSucursal.addFocusListener(new SucursalActionListener());
		pDatosBancarios.add(jtfSucursal);

		lDigitosControl = new JLabel("DC");
		lDigitosControl.setBounds(75, 90, 30, 20);
		lDigitosControl.setFont(Apariencia.cambiaFuente());
		pDatosBancarios.add(lDigitosControl);
		
		jtfDigitosControl = new JTextFieldFijo(2);
		jtfDigitosControl.setBounds(110, 90, 30, 25);
		jtfDigitosControl.setTextoAyuda("Los dígitos de control, los calcula el programa automáticamente.<BR>" +
				"No debes ponerlos.", 4);
		jtfDigitosControl.setFont(Apariencia.cambiaFuente());
		jtfDigitosControl.setEnabled(false);
		jtfDigitosControl.setFocusable(false);
		pDatosBancarios.add(jtfDigitosControl);

		lCuentaBancaria = new JLabel("Cuenta");
		lCuentaBancaria.setBounds(160, 90, 70, 20);
		lCuentaBancaria.setFont(Apariencia.cambiaFuente());
		pDatosBancarios.add(lCuentaBancaria);
		
		jtfCuentaBancaria = new JTextFieldFijo(10);
		jtfCuentaBancaria.setBounds(230, 90, 110, 25);
		jtfCuentaBancaria.setFont(Apariencia.cambiaFuente());
		jtfCuentaBancaria.setTextoAyuda("Son los últimos 10 dígitos de la cuenta bancaria.<BR>" +
				"Se puede dejar vacío.", 4);
		jtfCuentaBancaria.addActionListener(new CuentaBancariaActionListener());
		jtfCuentaBancaria.addFocusListener(new CuentaBancariaActionListener());
		pDatosBancarios.add(jtfCuentaBancaria);

		lIBAN = new JLabel("IBAN");
		lIBAN.setBounds(220, 30, 50, 20);
		lIBAN.setFont(Apariencia.cambiaFuente());
		pDatosBancarios.add(lIBAN);
		
		jtfIBAN = new JTextFieldFijo(4);
		jtfIBAN.setBounds(280, 30, 60, 25);
		jtfIBAN.setFont(Apariencia.cambiaFuente());
		jtfIBAN.setTextoAyuda("Código InterBANcario, lo calcula el ordenador.<BR>" +
				"No debes ponerlo.", 4);
		jtfIBAN.setEnabled(false);
		jtfIBAN.setFocusable(false);
		pDatosBancarios.add(jtfIBAN);			

		// Datos comerciales
		Border borderDatosComerciales = BorderFactory.createRaisedBevelBorder();
		Border titledBorderDatosComerciales = new TitledBorder(borderDatosComerciales, "Datos Comerciales", TitledBorder.LEFT, TitledBorder.TOP, Apariencia.cambiaFuente(), Color.BLACK);
		JPanel pDatosComerciales = new JPanel();
		pDatosComerciales.setLayout(null);
		pDatosComerciales.setBorder(titledBorderDatosComerciales);
		pDatosComerciales.setBounds(580, 280, 360, 170);
		pantalla.add(pDatosComerciales);		

		lDtoPP = new JLabel("Dto.P.P.");
		lDtoPP.setBounds(10, 30, 70, 20);
		lDtoPP.setFont(Apariencia.cambiaFuente());
		pDatosComerciales.add(lDtoPP);
		
		jtn2DtoPP = new JTextFieldNumero2Decimales();
		jtn2DtoPP.setBounds(80, 30, 60, 25);
		jtn2DtoPP.setTextoAyuda("% de descuento que nos aplican por Pronto Pago.<BR>" +
				"Se puede dejar vacío.", 4);
		jtn2DtoPP.setFont(Apariencia.cambiaFuente());
		pDatosComerciales.add(jtn2DtoPP);

		lDtoComercial = new JLabel("Dto.Comercial");
		lDtoComercial.setBounds(160, 30, 120, 20);
		lDtoComercial.setFont(Apariencia.cambiaFuente());
		pDatosComerciales.add(lDtoComercial);
		
		jtn2DtoComercial = new JTextFieldNumero2Decimales();
		jtn2DtoComercial.setBounds(280, 30, 60, 25);
		jtn2DtoComercial.setTextoAyuda("% de descuento comercial que nos aplican.<BR>" +
				"Se puede dejar vacío.", 4);
		jtn2DtoComercial.setFont(Apariencia.cambiaFuente());
		pDatosComerciales.add(jtn2DtoComercial);

		lTipoProveedor = new JLabel("Tipo Prov.");
		lTipoProveedor.setBounds(10, 60, 100, 20);
		lTipoProveedor.setFont(Apariencia.cambiaFuente());
		pDatosComerciales.add(lTipoProveedor);
		
		jtfTipoProveedor = new JTextFieldFijo(2);
		jtfTipoProveedor.setBounds(110, 60, 30, 25);
		jtfTipoProveedor.setTextoAyuda("Tipo de Proveedor.<BR>" +
				"Se puede dejar vacío.", 4);
		jtfTipoProveedor.setFont(Apariencia.cambiaFuente());
		pDatosComerciales.add(jtfTipoProveedor);

		lConfictividad = new JLabel("Conflictividad");
		lConfictividad.setBounds(160, 60, 120, 20);
		lConfictividad.setFont(Apariencia.cambiaFuente());
		pDatosComerciales.add(lConfictividad);
		
		jtfConfictividad = new JTextFieldFijo(2);
		jtfConfictividad.setBounds(280, 60, 30, 25);
		jtfConfictividad.setTextoAyuda("Nivel de conflictividad.<BR>" +
				"Se puede dejar vacío.", 4);
		jtfConfictividad.setFont(Apariencia.cambiaFuente());
		pDatosComerciales.add(jtfConfictividad);

		lTipoFacturacion = new JLabel("Tipo Fact.");
		lTipoFacturacion.setBounds(10, 90, 90, 20);
		lTipoFacturacion.setFont(Apariencia.cambiaFuente());
		pDatosComerciales.add(lTipoFacturacion);
		
		jtfTipoFacturacion = new JTextFieldFijo(2);
		jtfTipoFacturacion.setBounds(95, 90, 30, 25);
		jtfTipoFacturacion.setTextoAyuda("Tipo de Facturación.<BR>" +
				"Se puede dejar vacío.", 4);
		jtfTipoFacturacion.setFont(Apariencia.cambiaFuente());
		pDatosComerciales.add(jtfTipoFacturacion);

		lRappel = new JLabel("Rappel(%)");
		lRappel.setBounds(135, 90, 90, 20);
		lRappel.setFont(Apariencia.cambiaFuente());
		pDatosComerciales.add(lRappel);
		
		jtn2Rappel = new JTextFieldNumero2Decimales();
		jtn2Rappel.setBounds(220, 90, 55, 25);
		jtn2Rappel.setTextoAyuda("% de descuento por Rappel que nos aplica este Proveedor.<BR>" +
				"Se puede dejar vacío.", 4);
		jtn2Rappel.setFont(Apariencia.cambiaFuente());
		pDatosComerciales.add(jtn2Rappel);
		
		lTipoRappel = new JLabel("Tipo");
		lTipoRappel.setBounds(280, 90, 50, 20);
		lTipoRappel.setFont(Apariencia.cambiaFuente());
		pDatosComerciales.add(lTipoRappel);
		
		jtfTipoRappel = new JTextFieldFijo(2);
		jtfTipoRappel.setBounds(320, 90, 30, 25);
		jtfTipoRappel.setFont(Apariencia.cambiaFuente());
		pDatosComerciales.add(jtfTipoRappel);

		lIva = new JLabel("I.V.A.");
		lIva.setBounds(10, 120, 70, 20);
		lIva.setFont(Apariencia.cambiaFuente());
		pDatosComerciales.add(lIva);
		
		jcbTipoIva = new JComboBox();
		jcbTipoIva.addItem("Normal");
		jcbTipoIva.addItem("Exento");
		jcbTipoIva.setSelectedIndex(0);
		//jcbTipoIva.addActionListener(this);
		jcbTipoIva.setBounds(60, 120, 90, 25);
		jcbTipoIva.setFont(Apariencia.cambiaFuente());
		pDatosComerciales.add(jcbTipoIva);
				
		jcEmisionPagares = new JCheckBox("Emisión Pagarés");
		jcEmisionPagares.setBounds(160, 120, 170, 20);
		jcEmisionPagares.setFont(Apariencia.cambiaFuente());
		pDatosComerciales.add(jcEmisionPagares);
		
		jtnfCentro = new JTextFieldNumeroFijo(3);
		jtnfCentro.setBounds(370, 15, 30, 25);
		jtnfCentro.setFont(Apariencia.cambiaFuente());
		if(DatosComunes.sisTienda == 1){
			jtnfCentro.setEnabled(false);
			jtnfCentro.setFocusable(false);
		}
		pantalla.add(jtnfCentro);

		jcActivado = new JCheckBox("Activado");
		jcActivado.setBounds(430, 15, 130, 20);
		jcActivado.setFont(Apariencia.cambiaFuente());
		jcActivado.setFocusable(false);
		pantalla.add(jcActivado);

		lSaldo = new JLabel("Saldo");
		lSaldo.setBounds(750, 15, 90, 20);
		lSaldo.setFont(Apariencia.cambiaFuente());
		pantalla.add(lSaldo);
		
		jtn2Saldo = new JTextFieldNumero2Decimales();
		jtn2Saldo.setBounds(820, 15, 120, 25);
		jtn2Saldo.setTextoAyuda("Saldo de este Proveedor/Acrredor.", 4);
		jtn2Saldo.setFont(Apariencia.cambiaFuente());
		jtn2Saldo.setEnabled(false);
		pantalla.add(jtn2Saldo);

		// Botones inferiores
		jbAsignarCodigo = new JButton("Asignar Código");
		jbAsignarCodigo.setBounds(450, 470, 170, 30);
		jbAsignarCodigo.setFont(Apariencia.cambiaFuente());
		jbAsignarCodigo.addActionListener(new BotonAsignarCodigoListener());
		
		
		jbBorrar = new JButton("Borrar");
		jbBorrar.setBounds(630, 470, 90, 30);
		jbBorrar.setFont(Apariencia.cambiaFuente());
		jbBorrar.addActionListener(new BotonBorrarListener());
		//jbAsignarCodigo.setEnabled(false);
		
		
		jbGrabar = new JButton("Grabar");
		jbGrabar.setBounds(730, 470, 90, 30);
		jbGrabar.setFont(Apariencia.cambiaFuente());
		jbGrabar.addActionListener(new BotonGrabarListener());
		//jbAsignarCodigo.setEnabled(false);
		
		
		
		jbAtras = new JButton();
		jbAtras.setBounds(640, 510, 30, 30);
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
		jbAdelante.setBounds(680, 510, 30, 30);
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
		jbSalir.setBounds(20, 520, 30, 30);
		jbSalir.setFont(Apariencia.cambiaFuente());
		jbSalir.addActionListener(new SalirListener());
		try {
			imgSalir = ImageIO.read(getClass().getResource("/imagenes/SALIR.gif"));
			jbSalir.setIcon(new ImageIcon(imgSalir));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		pantalla.add(jbSalir);
		
		
		jbAcumuladoContable = new JButton("Ac.Contable");
		jbAcumuladoContable.setBounds(80, 470, 140, 30);
		jbAcumuladoContable.setFont(Apariencia.cambiaFuente());
		jbAcumuladoContable.addActionListener(new BotonAcumuladosContablesListener());
		//jbAsignarCodigo.setEnabled(false);
		
		
		jbAcumuladoEstadistico = new JButton("Ac.Estadís.");
		jbAcumuladoEstadistico.setBounds(80, 510, 140, 30);
		jbAcumuladoEstadistico.setFont(Apariencia.cambiaFuente());
		jbAcumuladoEstadistico.addActionListener(new BotonAcumuladosEstadisticosListener());
		//jbAsignarCodigo.setEnabled(false);
		
		
		jbMovimientos = new JButton("Movimientos");
		jbMovimientos.setBounds(230, 470, 140, 30);
		jbMovimientos.setFont(Apariencia.cambiaFuente());
		jbMovimientos.addActionListener(new BotonConsultaMovimientosListener());
		//jbAsignarCodigo.setEnabled(false);
		
		
		jbFacturas = new JButton("Facturas");
		jbFacturas.setBounds(380, 470, 140, 30);
		jbFacturas.setFont(Apariencia.cambiaFuente());
		jbFacturas.addActionListener(new BotonFacturas());
		//jbAsignarCodigo.setEnabled(false);
		
		
		jbUnaFactura = new JButton("Una FactuR");
		jbUnaFactura.setBounds(380, 510, 140, 30);
		jbUnaFactura.setFont(Apariencia.cambiaFuente());
		//jbAsignarCodigo.setEnabled(false);
		
		
		jbPrevisionesCobro = new JButton("Prev.Cobro");
		jbPrevisionesCobro.setBounds(530, 470, 140, 30);
		jbPrevisionesCobro.setFont(Apariencia.cambiaFuente());
		//jbAsignarCodigo.setEnabled(false);
		
		
		jbPrevisionesPago = new JButton("Prev.Pago");
		jbPrevisionesPago.setBounds(680, 470, 140, 30);
		jbPrevisionesPago.setFont(Apariencia.cambiaFuente());
		jbPrevisionesPago.addActionListener(new BotonPrevisionesPagoListener());
		//jbAsignarCodigo.setEnabled(false);
		
		
		jbNotas = new JButton("Notas");
		jbNotas.setBounds(840, 470, 80, 80);
		jbNotas.setFont(Apariencia.cambiaFuente());
		jbNotas.addActionListener(new BotonNotasListener());
		//jbAsignarCodigo.setEnabled(false);
		pantalla.add(jbNotas);
		
		if(consulta){
			jbAcumuladoContable.setVisible(true);
			jbAcumuladoEstadistico.setVisible(true);
			jbMovimientos.setVisible(true);
			jbFacturas.setVisible(true);
			jbUnaFactura.setVisible(true);
			jbPrevisionesCobro.setVisible(true);
			jbPrevisionesPago.setVisible(true);
			
			jbAsignarCodigo.setVisible(false);
			jbBorrar.setVisible(false);
			jbGrabar.setVisible(false);
		}else{
			jbAcumuladoContable.setVisible(false);
			jbAcumuladoEstadistico.setVisible(false);
			jbMovimientos.setVisible(false);
			jbFacturas.setVisible(false);
			jbUnaFactura.setVisible(false);
			jbPrevisionesCobro.setVisible(false);
			jbPrevisionesPago.setVisible(false);
			
			jbAsignarCodigo.setVisible(true);
			jbBorrar.setVisible(true);
			jbGrabar.setVisible(true);
		}
		
		pantalla.add(jbAsignarCodigo);
		pantalla.add(jbBorrar);
		pantalla.add(jbGrabar);
		
		pantalla.add(jbAcumuladoContable);
		pantalla.add(jbAcumuladoEstadistico);
		pantalla.add(jbMovimientos);
		pantalla.add(jbFacturas);
		pantalla.add(jbUnaFactura);
		pantalla.add(jbPrevisionesCobro);
		pantalla.add(jbPrevisionesPago);
		
                // Por si viene el Proveedor omo parámetro
                if(cuentaProveedor.length() > 0)
                    jtnfCuentaContable.setText(cuentaProveedor);
                
		cargaInicial();
		pantalla.setVisible(true);
		
		
	}
	
    private void cargaInicial() {
        if (cuentaProveedor.length() > 0) {
            String cuentaContable = jtnfCuentaContable.getText();

            String strSql = "SELECT * FROM PROVAC WHERE EMPRESA = '"
                    + DatosComunes.eEmpresa
                    + "' AND PROVAC_PROVACRE = " + jtnfCuentaContable.getText();

            if (DatosComunes.centroCont != 0) {
                strSql += " AND PROVAC_CENTRO = " + DatosComunes.centroCont;
            }

			// Si al cargar los datos nos devuelve que no se ha podido leer
            // ese proveedor, damos por hecho que es un proveedor nuevo
            if (cargaDatos(strSql) == 0) {
                borrarPantalla();
                jtnfCuentaContable.setText(cuentaContable);
                jtnfCuentaContable.transferFocus();
                proveedor = new Proveedor();

            }
        } else {
            // Carga inicial en el primer Proveedor
            if (jtnfCuentaContable.getText().length() == 0) {
                jtnfCuentaContable.setText("400000000");
            }

            String strSql = "SELECT * FROM PROVAC WHERE EMPRESA = '"
                    + DatosComunes.eEmpresa
                    + "' AND PROVAC_PROVACRE > " + jtnfCuentaContable.getText();

            if (DatosComunes.centroCont != 0) {
                strSql += " AND PROVAC_CENTRO = " + DatosComunes.centroCont;
            }

            strSql += " AND PROVAC_PROVACRE < 430000001 LIMIT 1";

            cargaDatos(strSql);
        }
    }
	
	class SalirListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// Si pinchamos en el botón 'Salir', 'tiramos' la pantalla
			//frameMenu.setEnabled(true);
			//pantalla.dispose();
			salir();
		}
	}

	class CuentaContableListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String cuentaContable = jtnfCuentaContable.getText();
			
			String strSql = "SELECT * FROM PROVAC WHERE EMPRESA = '" + 
	         DatosComunes.eEmpresa + 
	         "' AND PROVAC_PROVACRE = " + jtnfCuentaContable.getText();
			
			if(DatosComunes.centroCont != 0)
				strSql += " AND PROVAC_CENTRO = " + DatosComunes.centroCont;
			
			// Si al cargar los datos nos devuelve que no se ha podido leer
			// ese proveedor, damos por hecho que es un proveedor nuevo
			if(cargaDatos(strSql) == 0){
				borrarPantalla();
				jtnfCuentaContable.setText(cuentaContable);
				jtnfCuentaContable.transferFocus();
				proveedor = new Proveedor();	
				
			}
				
		}		
	}
	
	class BancoActionListener implements ActionListener, FocusListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String strSql = "SELECT * FROM BCOIND WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' " + " AND BCOIND_BANCO = " + jtfBanco.getText();
			
			if(!jtfBanco.getText().equals("0")){
				if(BaseDatos.countRows(strSql) > 0){				
					calculaIban();	
					jtfBanco.transferFocus();
				}else{
					jtfBanco.setText("0");
					JOptionPane.showMessageDialog(null,
					"Banco inexistente en el Indice de Bancos!!!");				
				}
			}
				
		}
		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			
            String strSql = "SELECT * FROM BCOIND WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' " + " AND BCOIND_BANCO = " + jtfBanco.getText();
			
            if(!jtfBanco.getText().equals("0")){
            	if(BaseDatos.countRows(strSql) > 0){
            		calculaIban();				
            	}else{
            		jtfBanco.setText("0");
            		JOptionPane.showMessageDialog(null,
            		"Banco inexistente en el Indice de Bancos!!!");				
            	}
            }
		}		
	}
	
	class CuentaBancariaActionListener implements ActionListener, FocusListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			calculaIban();
			jtfCuentaBancaria.transferFocus();
		}
		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void focusLost(FocusEvent arg0) {			
			calculaIban();	
		}		
		
	}
	
	class SucursalActionListener implements ActionListener, FocusListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String strSql = "SELECT * FROM BCOIND WHERE EMPRESA = '" + DatosComunes.eEmpresa + 
			"' " + " AND BCOIND_BANCO = " + jtfBanco.getText() + 
			" AND BCOIND_SUCURSAL = '" + String.format("%04d", Integer.valueOf(jtfSucursal.getText())) +"'";

			if(!jtfSucursal.getText().equals("0")){
				if(BaseDatos.countRows(strSql) > 0){				
					calculaIban();	
					jtfSucursal.transferFocus();
				}else{
					jtfSucursal.setText("0");
					JOptionPane.showMessageDialog(null,
					"Sucursal inexistente en el Indice de Bancos!!!");				
				}
			}			
		}
		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			String strSql = "SELECT * FROM BCOIND WHERE EMPRESA = '" + DatosComunes.eEmpresa + 
			"' " + " AND BCOIND_BANCO = " + jtfBanco.getText() + 
			" AND BCOIND_SUCURSAL = '" + String.format("%04d", Integer.valueOf(jtfSucursal.getText())) +"'";

			if(!jtfSucursal.getText().equals("0")){
				if(BaseDatos.countRows(strSql) > 0){				
					calculaIban();	
				}else{
					jtfSucursal.setText("0");
					JOptionPane.showMessageDialog(null,
					"Sucursal inexistente en el Indice de Bancos!!!");				
				}
			}		
		}		
	}
	
	class IndiceCuentaContableListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			IndiceProveedores ip = new IndiceProveedores();
			if(ip.getCuenta() != null){
				jtnfCuentaContable.setText(ip.getCuenta());

				String strSql = "SELECT * FROM PROVAC WHERE EMPRESA = '" + 
				DatosComunes.eEmpresa + 
				"' AND PROVAC_PROVACRE = " + jtnfCuentaContable.getText();

				if(DatosComunes.centroCont != 0)
					strSql += " AND PROVAC_CENTRO = " + DatosComunes.centroCont;

				cargaDatos(strSql);
			}
		}
		
	}
	
	class BotonIndicePaisListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(indicePaises == null)
				indicePaises = new IndicePaises();
			else
				indicePaises.muestra();
			jtfPais.setText(String.valueOf(indicePaises.getPais()));
		}
		
	}

	class PaisListener implements ActionListener, FocusListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			jtfPais.transferFocus();
		}

		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			compruebaExistePais();
		}
		
		private void compruebaExistePais(){
			int pais = Integer.valueOf(jtfPais.getText());
			
			String strSql = "SELECT * FROM CPPAIS WHERE EMPRESA = '" + 
			                 DatosComunes.eEmpresa + "' AND " +
			                 "CPPAIS_CODIGO = " + pais;
			
			if(BaseDatos.countRows(strSql) == 0){
				Apariencia.mensajeInformativo(5, "Pais Inexistente");
				jtfPais.setText("0");
				pais = 0;
			}else{
				jtfPais.transferFocus();
			}							
		}
	}

	
	class BotonIndiceProvinciaListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if(indiceProvincias == null)
				indiceProvincias = new IndiceProvincias();
			else
				indiceProvincias.muestra();
			jtfProvincia.setText(String.valueOf(indiceProvincias.getProvincia()));
		}
		
	}
	
	class CodigoPostalListener implements ActionListener, FocusListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			jtfCodigoPostal.transferFocus();
		}			

		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			compruebaExisteCp();
			
		}
		
		private void compruebaExisteCp(){
			int codigoPostal = Integer.valueOf(jtfCodigoPostal.getText());
			
			String strSql = "SELECT * FROM CPPOST WHERE EMPRESA = '" + 
            DatosComunes.eEmpresa + "' AND " +
            "CPPOST_CP = " + codigoPostal;
			
			if(BaseDatos.countRows(strSql) == 0){
				Apariencia.mensajeInformativo(5, "Código Postal Inexistente!!!");
				jtfCodigoPostal.setText("0");				
			}else
				jtfCodigoPostal.transferFocus();
		}
	}
	
	class ProvinciaListener implements ActionListener, FocusListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			jtfProvincia.transferFocus();
		}

		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			compruebaExisteProvincia();
		}
		
		private void compruebaExisteProvincia(){
			int provincia = Integer.valueOf(jtfProvincia.getText());
			int cpProvincia = Integer.valueOf(String.format("%05d", Integer.valueOf(jtfCodigoPostal.getText())).substring(0, 2));
			
			String strSql = "SELECT * FROM CPPROV WHERE EMPRESA = '" + 
			                 DatosComunes.eEmpresa + "' AND " +
			                 "CPPROV_CODIGO = " + provincia;
				
			if(provincia != 0 && cpProvincia != 0 && provincia != cpProvincia){
				Apariencia.mensajeInformativo(5, "Provincia no concuerda con Código Postal!!!");
				jtfProvincia.setText(String.valueOf(cpProvincia));
			}
			if(provincia != 0 && BaseDatos.countRows(strSql) == 0){
				Apariencia.mensajeInformativo(5, "Provincia Inexistente!!!");
				jtfProvincia.setText("0");
				provincia = 0;
			}else{
				jtfProvincia.transferFocus();
			}							
		}
	}

	class PoblacionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int pais = 0;
			
			if(jtfPais.getText().trim().length() > 0)
				pais = Integer.valueOf(jtfPais.getText());
					
			if(indicePoblaciones == null)
				indicePoblaciones = new IndicePoblaciones(pais);
			else
				indicePoblaciones.muestra();
			jtfPoblacion.setText(indicePoblaciones.getPoblacion());
			
		}
		
	}
	
	class BancoListener implements ActionListener, FocusListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			cb = new CuentaBancaria();
			
			IndiceBancosSucursales ib = new IndiceBancosSucursales();
			cb = ib.getBancoSucursal();
			
			if(cb != null){
				if(!cb.getBanco().isEmpty())
					jtfBanco.setText(cb.getBanco());
				if(!cb.getSucursal().isEmpty())
					jtfSucursal.setText(cb.getSucursal());
				calculaIban();
			}
		}

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			calculaIban();
		}
		
	}
	
	class BotonSucursalListener implements ActionListener, FocusListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			cb = new CuentaBancaria();
			
			IndiceBancosSucursales ib = new IndiceBancosSucursales();
			cb = ib.getBancoSucursal();
			
			if(cb != null){
				if(!cb.getBanco().isEmpty())
					jtfBanco.setText(cb.getBanco());
				if(!cb.getSucursal().isEmpty())
					jtfSucursal.setText(cb.getSucursal());
				calculaIban();
			}
		}

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			calculaIban();
		}
		
	}
	
	class AdelanteListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(jtnfCuentaContable.getText().length() == 0)
				jtnfCuentaContable.setText("400000000");
			
			String strSql = "SELECT * FROM PROVAC WHERE EMPRESA = '" + 
	         DatosComunes.eEmpresa + 
	         "' AND PROVAC_PROVACRE > " + jtnfCuentaContable.getText();
			
			if(DatosComunes.centroCont != 0)
				strSql += " AND PROVAC_CENTRO = " + DatosComunes.centroCont;
			
	        strSql += " AND PROVAC_PROVACRE < 430000001 LIMIT 1";
			
			cargaDatos(strSql);			
		}	
	}
	
	class AtrasListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(jtnfCuentaContable.getText().length() == 0)
				jtnfCuentaContable.setText("419999999");
			
			String strSql = "SELECT * FROM PROVAC WHERE EMPRESA = '" + 
	         DatosComunes.eEmpresa + 
	         "' AND PROVAC_PROVACRE < " + jtnfCuentaContable.getText();
			
			if(DatosComunes.centroCont != 0)
				strSql += " AND PROVAC_CENTRO = " + DatosComunes.centroCont;
			 
	        strSql += " AND PROVAC_PROVACRE >= 400000001 ORDER BY PROVAC_PROVACRE DESC LIMIT 1";

			cargaDatos(strSql);			
		}		
	}
	
	class NuevoListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			borrarPantalla();
			proveedor = new Proveedor();			
		}		
	}

	class BotonAsignarCodigoListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String cuentaNueva = "";
			
			AsignacionAutomaticaDeCodigos aadc = new AsignacionAutomaticaDeCodigos();
			cuentaNueva = aadc.getCuentaContable();
			
			if(cuentaNueva != null){			
				borrarPantalla();
				proveedor = new Proveedor();
				jtnfCuentaContable.setText(cuentaNueva);
			}
			
		}		
	}
	
	class NifActionListener implements ActionListener, FocusListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			jtfNif.setText(jtfNif.getText().toUpperCase());
			jtfNif.transferFocus();
		}
		
		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
			int numeroLetras = 0;
			jtfNif.setText(jtfNif.getText().toUpperCase());
			String strNif = jtfNif.getText().trim().toUpperCase();
			
			String strNifCorrecto = Cadena.compruebaCifNif(strNif, Integer.valueOf(jtfPais.getText()), true);
			
			if(!strNifCorrecto.equalsIgnoreCase(strNif)){
				jtfNif.setText(strNifCorrecto);
			}
		}

	}
	
	class BotonAcumuladosContablesListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			new IndiceAcumuladosContables(jtnfCuentaContable.getText().trim(), jtfApellidosRazon.getText().trim());
		}
		
	}
	
	

	class BotonAcumuladosEstadisticosListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// Tercer argumento 2 porque son Proveedores/Acreedores
			new IndiceAcumuladosEstadisticos(jtnfCuentaContable.getText().trim(), jtfApellidosRazon.getText().trim(), 2);
		}
		
	}

	
	class BotonConsultaMovimientosListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			new IndiceMovimientosContables(jtnfCuentaContable.getText().trim());		
		}		
	}
	
	class BotonFacturas implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			new IndiceFacturasRecibidas(jtnfCuentaContable.getText().trim());		
		}		
	}
	
	class BotonPrevisionesPagoListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//new ConsultaPrevisionesPago();
			new IndicePrevisionesPago(jtnfCuentaContable.getText().trim());		
		}		
	}
	
	class BotonGrabarListener implements ActionListener{

		// Acordarse de crear la cuenta en el la tabla 'Cuenta' 
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean grabarOk = true;

			if(jtnfCuentaContable.getText().trim().length() < 9){
				grabarOk = false;
				JOptionPane.showMessageDialog(null, "No se graba!!!\nMal Código de Cuenta.");
			}

			if(jtfApellidosRazon.getText().trim().isEmpty()){
				grabarOk = false;
				JOptionPane.showMessageDialog(null, "No se graba!!!\nFaltan Apellidos o Razón Social.");
			}

			if(jtfNif.getText().trim().isEmpty()){
				JOptionPane.showMessageDialog(null, "Debería ponerse NIF");
			}

			if(Integer.valueOf(jtfProvincia.getText().trim()) == 0){
				grabarOk = false;
				JOptionPane.showMessageDialog(null, "No se graba!!!\nFalta provincia.");
			}

			String strCp = String.format("%05d", Integer.valueOf(jtfCodigoPostal.getText().trim())).substring(0, 2);
			String strProvincia = String.format("%02d", Integer.valueOf(jtfProvincia.getText().trim())).substring(0, 2);

			if(Integer.valueOf(jtfProvincia.getText().trim()) != 0 && !strCp.equalsIgnoreCase(strProvincia))
				JOptionPane.showMessageDialog(null, "No corresponde CP a su provincia.");

			if(grabarOk)
				grabarCuentaProveedor();


		}



		private void grabarCuentaProveedor(){
			boolean cuentaWriteOk = true, proveedorWriteOk = true;
			Cuenta cuenta = new Cuenta();				

			cuenta.setActivo(1);
			cuenta.setCentro(Integer.valueOf(jtnfCentro.getText()));
			cuenta.setCuenta(jtnfCuentaContable.getText().trim());
			cuenta.setEmpresa(DatosComunes.eEmpresa);
			cuenta.setExtenOtroFichero(2);
			cuenta.setGrado("3");
			cuenta.setSaldo(0.0);
			cuenta.setSaldoUltimaDepuracion(0.0);
			cuenta.setTitulo(jtfApellidosRazon.getText().trim());
			cuentaWriteOk = cuenta.write();

			if(jcActivado.isSelected())
				proveedor.setActivo(1);
			else
				proveedor.setActivo(0);
			proveedor.setApellidosRazonSocial(jtfApellidosRazon.getText());
			proveedor.setCentro(Integer.valueOf(jtnfCentro.getText()));
			proveedor.setCodigoPostal(Integer.valueOf(jtfCodigoPostal.getText()));
			proveedor.setContacto(jtfContacto.getText());
			proveedor.setCuenta(Long.valueOf(jtfCuentaBancaria.getText()));
			proveedor.setDescuentoComercial(jtn2DtoComercial.getDouble());
			proveedor.setDescuentoProntoPago(jtn2DtoPP.getDouble());
			proveedor.setDigitosControl(Integer.valueOf(jtfDigitosControl.getText().trim()));
			proveedor.setDireccion(jtfDireccion.getText().trim());
			proveedor.setDireccion2(0);
			proveedor.setDireccionAtencion(jtfAttDireccion.getText().trim());
			proveedor.setEmail(jtfEMail.getText().trim());
			if(jcEmisionPagares.isSelected())
				proveedor.setEmisionPagares(1);
			else
				proveedor.setEmisionPagares(0);
			proveedor.setEmpresa(DatosComunes.eEmpresa);
			proveedor.setFax(jtfFax.getText().trim());
			proveedor.setFechaUltimaFactura(0);
			proveedor.setIntervalo(Integer.valueOf(jtfIntervalo.getText()));
			proveedor.setNif(jtfNif.getText().trim());
			proveedor.setNivelConflicto(Integer.valueOf(jtfConfictividad.getText().trim()));
			proveedor.setNombre(jtfNombre.getText().trim());
			proveedor.setNumeroBanco(Integer.valueOf(jtfBanco.getText().trim()));
			proveedor.setNumeroEfectos(Integer.valueOf(jtfNumeroEfectos.getText().trim()));
			proveedor.setNumeroSucursal(Integer.valueOf(jtfSucursal.getText().trim()));
			proveedor.setObservaciones(jtfObservaciones.getText().trim());
			proveedor.setPais(Integer.valueOf(jtfPais.getText().trim()));
			proveedor.setPoblacion(jtfPoblacion.getText().trim());
			proveedor.setPrimerVencimiento(Integer.valueOf(jtfPrimervencimiento.getText().trim()));
			proveedor.setProveedor(jtnfCuentaContable.getText().trim());
			proveedor.setProvincia(Integer.valueOf(jtfProvincia.getText().trim()));
			proveedor.setRappel(jtn2Rappel.getDouble());
			if(jcSeNegocia.isSelected())
				proveedor.setSeNegocia(1);
			else
				proveedor.setSeNegocia(0);
			proveedor.setSomosSuCliente(jtfSuCliente.getText().trim());
			proveedor.setTelefono(jtfTelefono.getText().trim());
			// Al Loro!!! No sabemos que es 'TipoDescuento'
			proveedor.setTipoDescuento(0);
			proveedor.setTipoFacturacion(Integer.valueOf(jtfTipoFacturacion.getText().trim()));
			proveedor.setTipoIva(jcbTipoIva.getSelectedIndex());
			proveedor.setTipoPrac(Integer.valueOf(jtfTipoProveedor.getText().trim()));
			proveedor.setTipoRappel(Integer.valueOf(jtfTipoRappel.getText().trim()));
			proveedorWriteOk = proveedor.write();

			if(!cuentaWriteOk || !proveedorWriteOk)
				JOptionPane.showMessageDialog(null, "Error al grabar Proveedor!!!");
			else
				JOptionPane.showMessageDialog(null, "Proveedor grabado correctamente");
		}
	}

	class BotonBorrarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			boolean existeProveedor = true;
			boolean existeCuenta = true;
			String strSqlProveedor = "SELECT * FROM PROVAC WHERE EMPRESA ='" + DatosComunes.eEmpresa + "'" +
					" AND PROVAC_CENTRO = " + DatosComunes.centroCont +
					" AND PROVAC_PROVACRE ='" + jtnfCuentaContable.getText().trim() + "'";
			String strSqlCuenta = "SELECT * FROM CONTAB WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' " +
	                " AND CONTAB_CENTRO = " + DatosComunes.centroCont +
			        " AND CONTAB_CUENTA = '" + jtnfCuentaContable.getText().trim() + "'";
			
			if(BaseDatos.countRows(strSqlProveedor) == 0)
				existeProveedor = false;
			if(BaseDatos.countRows(strSqlCuenta) == 0)
				existeCuenta = false;
			
			if(jtn2Saldo.getDouble() != 0.0)
				JOptionPane.showMessageDialog(null, "<html><font size='4'><strong>" + 
						"No se puede borrar, tiene saldo!!!" + 
				"</strong></font></html>");
			else
				if(existeProveedor && existeCuenta){
					Object[] opciones = {"Si", "No"};
					int n = JOptionPane.showOptionDialog(pantalla,
							"<html><font size='4'><strong>" +
							"Desea borrar la cuenta de:<BR>'" 
							+ jtfNombre.getText().trim() + " " + jtfApellidosRazon.getText().trim() 
							+"'</strong></font></html>",
							"Borrar Registro",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null,     // Sin Icono personalizado.
							opciones,  // Título de los botonoes
							opciones[1]); // Botón por defecto.

					if(n == 0){
						String strSqlDeleteProveedor = "DELETE" + strSqlProveedor.substring(8, strSqlProveedor.length());
						String strSqlDeleteCuenta = "DELETE" + strSqlCuenta.substring(8, strSqlCuenta.length());

						int registrosBorrados = 0;
						registrosBorrados += BaseDatos.borraRegistro(strSqlDeleteProveedor);
						registrosBorrados += BaseDatos.borraRegistro(strSqlDeleteCuenta);
						if(registrosBorrados == 2){
							JOptionPane.showMessageDialog(null, "<html><font size='4'><strong>" + 
									"Proveedor/Acreedor borrado correctamente." + 
							"</strong></font></html>");

							// Pasamos al siguiente registro
							if(jtnfCuentaContable.getText().length() == 0)
								jtnfCuentaContable.setText("400000000");

							String strSql = "SELECT * FROM PROVAC WHERE EMPRESA = '" + 
							DatosComunes.eEmpresa + 
							"' AND PROVAC_PROVACRE > " + jtnfCuentaContable.getText();

							if(DatosComunes.centroCont != 0)
								strSql += " AND PROVAC_CENTRO = " + DatosComunes.centroCont;

							strSql += " AND PROVAC_PROVACRE < 430000001 LIMIT 1";

							cargaDatos(strSql);	
						}
					}
				}else{
					JOptionPane.showMessageDialog(null, "<html><font size='4'><strong>" + 
							"No se puede borrar el Proveedor o la Cuenta Contable." + 
					"</strong></font></html>");								
				}

		}
		
	}
	
	
	
	private void borrarPantalla(){
		jtn2Saldo.setText("0,00");
		jtnfCuentaContable.setText("");
		jtnfCentro.setText(String.valueOf(DatosComunes.centroCont));
		jtfNombre.setText("");
		jtfApellidosRazon.setText("");
		jtfContacto.setText("");
		jtfAttDireccion.setText("");
		jtfDireccion.setText("");
	    jtfPais.setText("0");
	    jtfPoblacion.setText("");
	    jtfCodigoPostal.setText("0");
	    jtfTelefono.setText("");
	    jtfFax.setText("");
	    jtfProvincia.setText("0");
	    jtfNif.setText("");
	    jtfSuCliente.setText("");
	    jtfObservaciones.setText("");
	    jtfEMail.setText("");
	    jtfNumeroEfectos.setText("0");
	    jtfIBAN.setText("");
	    jtfIntervalo.setText("0");
	    jtfPrimervencimiento.setText("0");
	    jtfBanco.setText("0");
	    jtfSucursal.setText("0");
	    jtfDigitosControl.setText("0");
	    jtfCuentaBancaria.setText("0");
	    jtfTipoProveedor.setText("0");
	    jtfConfictividad.setText("0");
	    jtfTipoFacturacion.setText("0");
	    jtfTipoRappel.setText("0");
	    jcbTipoIva.setSelectedIndex(0);
	    jtn2DtoPP.setText("0,00");
	    jtn2DtoComercial.setText("0,00");
	    jtn2Rappel.setText("0,00");
	    jcActivado.setSelected(true);
	    jcSeNegocia.setSelected(false);
	    jcEmisionPagares.setSelected(false);
	}
	
	class BotonNotasListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			new ManNotas("CONTAB", 
					     jtnfCuentaContable.getText().trim(), 
					     jtfApellidosRazon.getText().trim());			
		}		
	}
	
	private int cargaDatos(String strSql){		
		int numeroDeFilas = 0;
		
		// System.out.println("strSql-> " + strSql);
		
		numeroDeFilas = BaseDatos.countRows(strSql);
		if(numeroDeFilas > 0){
			try {
				borrarPantalla();
				//Proveedor proveedor = new Proveedor(); 
				rs = m.query(strSql);
				
				// Recorremos el recodSet para ir rellenando la tabla de marcas
				if (rs.next() == true) {
					proveedor.read(rs);

					//jtn2Saldo.setText("SALDO");
					jtnfCuentaContable.setText(proveedor.getProveedor());
					jtnfCentro.setText(String.valueOf(proveedor.getCentro()));
					jtfNombre.setText(proveedor.getNombre());
					jtfApellidosRazon.setText(proveedor.getApellidosRazonSocial());
					jtfContacto.setText(proveedor.getContacto());
					jtfAttDireccion.setText(proveedor.getDireccionAtencion());
					jtfDireccion.setText(proveedor.getDireccion());
				    jtfPais.setText(String.valueOf(proveedor.getPais()));
				    jtfPoblacion.setText(proveedor.getPoblacion());
				    jtfCodigoPostal.setText(String.valueOf(proveedor.getCodigoPostal()));
				    jtfTelefono.setText(proveedor.getTelefono());
				    jtfFax.setText(proveedor.getFax());
				    jtfProvincia.setText(String.valueOf(proveedor.getProvincia()));
				    jtfNif.setText(proveedor.getNif());
				    jtfSuCliente.setText(proveedor.getSomosSuCliente());
				    jtfObservaciones.setText(proveedor.getObservaciones());
				    jtfEMail.setText(proveedor.getEmail());
				    jtfNumeroEfectos.setText(String.valueOf(proveedor.getNumeroEfectos()));
				    jtfIBAN.setText("");
				    jtfIntervalo.setText(String.valueOf(proveedor.getIntervalo()));
				    jtfPrimervencimiento.setText(String.valueOf(proveedor.getPrimerVencimiento()));
				    jtfBanco.setText(String.valueOf(proveedor.getNumeroBanco()));
				    jtfSucursal.setText(String.valueOf(proveedor.getNumeroSucursal()));
				    jtfDigitosControl.setText(String.valueOf(proveedor.getDigitosControl()));
				    jtfCuentaBancaria.setText(String.valueOf(proveedor.getCuenta()));
				    jtfTipoProveedor.setText(String.valueOf(proveedor.getTipoPrac()));
				    jtfConfictividad.setText(String.valueOf(proveedor.getNivelConflicto()));
				    jtfTipoFacturacion.setText(String.valueOf(proveedor.getTipoFacturacion()));
				    jtfTipoRappel.setText(String.valueOf(proveedor.getTipoRappel()));
				    jcbTipoIva.setSelectedIndex(proveedor.getTipoIva());
				    jtn2DtoPP.setText(String.valueOf(proveedor.getDescuentoProntoPago()));
				    jtn2DtoComercial.setText(String.valueOf(proveedor.getDescuentoComercial()));
				    jtn2Rappel.setText(String.valueOf(proveedor.getRappel()));
				    if(proveedor.getActivo() == 1)
				    	jcActivado.setSelected(true);
				    else
				    	jcActivado.setSelected(false);
				    if(proveedor.getSeNegocia() == 1)
				    	jcSeNegocia.setSelected(true);
				    else
				    	jcSeNegocia.setSelected(false);
				    if(proveedor.getEmisionPagares() == 1)
				    	jcEmisionPagares.setSelected(true);
				    else
				    	jcEmisionPagares.setSelected(false);
				    
				    if(jtfBanco.getText().trim().length() > 1)
				    	calculaIban();
				    
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			// Averiguamos el saldo de la cuenta
			
			Cuenta cuenta = new Cuenta();
			jtn2Saldo.setText(String.valueOf(cuenta.getSaldoCuenta(proveedor.getProveedor(), DatosComunes.centroCont)));
			
		}
		
		return numeroDeFilas;
	}
	
	private void calculaIban(){
		CuentaBancaria cb = new CuentaBancaria();
		
		cb.setBanco(Integer.valueOf(jtfBanco.getText()));
		cb.setSucursal(Integer.valueOf(jtfSucursal.getText()));
		cb.setDigitosControl(Integer.valueOf(jtfDigitosControl.getText()));
		cb.setCuenta(Long.valueOf(jtfCuentaBancaria.getText()));
		cb.setIbanPais("ES");
		
		cb.calcularIban();
		cb.calculaDigitosControl();
		
		jtfIBAN.setText(cb.getIbanPais() + cb.getIbanCodigoControl());
		jtfDigitosControl.setText(cb.getDigitosControl());
	}
	
	
	private void salir(){
		pantalla.dispose();
		frameMenu.setEnabled(true);		
	}
}

