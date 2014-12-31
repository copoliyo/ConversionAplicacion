package consultasMantenimientos;

import general.DatosComunes;
import general.MysqlConnect;
import indices.IndicePaises;
import indices.IndicePoblaciones;
import indices.IndiceProvincias;
import indices.IndiceRepresentantes;

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



import tablas.Cuenta;
import tablas.Proveedor;
import tablas.Representante;
import util.Apariencia;
import util.BaseDatos;
import util.Cadena;
import util.CuentaBancaria;
import util.EscapeDialog;
import util.JTextFieldFijo;
import util.JTextFieldNumero2Decimales;
import util.JTextFieldNumeroFijo;


public class ConManRepresentantes {
	// Con esta variable definimos si estamos en una consulta (TRUE) o en
	// un mantenimiento (FALSE). Nos servirá para tener un sólo programa
	// para algunas Consultas/Mantenimientos que pueden compartir las 
	// mismas pantallas.
	private static boolean consulta;
	private static boolean enCreacion = false;
	
	IndicePoblaciones indicePoblaciones = null;
	IndiceProvincias indiceProvincias = null;
	IndicePaises indicePaises = null;
	
	static ResultSet rs = null;
	static MysqlConnect m = null;
	
	public Representante representante = new Representante();
	
	// Definiciones de componentes de pantalla
	public JFrame frameMenu = null;
	
	EscapeDialog pantalla;
	
	JLabel lCodigo, lCentro, lNombre, lApellidosRazon, lContacto, lAtencionDireccion,
			lDireccion, lPais, lPoblacion, lCodigoPostal, lTelefono, lFax, lProvincia, lNif,
			lFirmaElectronica, lEMail, lObservaciones, lComision, lTipo, lRetencion, 
			lPorcentaje1, lPorcentaje2;
	
	JTextFieldNumeroFijo jtfnfCodigo, jtfnfCentro, jtfnfPais, jtfnfCodigoPostal, jtfnfTelefono,
			jtfnfFax, jtfnfProvincia, jtfnfTipo;
	
	JTextFieldFijo jtffNombre, jtffApellidosRazon, jtffContacto, jtffAtencionDireccion, jtffDireccion,
			jtffPoblacion, jtffNif, jtffFirmaElectronica, jtffEMail, jtffObservaciones;
	
	JTextFieldNumero2Decimales jtfn2dComision, jtfn2dRetencion;
	
	JCheckBox jcbActivado, jcbPermitePedidosWeb;
	
	JButton jbIndiceRepresentantes, jbIndicePaises, jbIndicePoblaciones, jbIndiceProvincias,
			jbEtiquetasFirmaElectronica, jbGeneraFirmaElectronica, 
			jbSalir, jbBorrar, jbGrabar, jbAtras, jbAdelante, jbNuevo;
	
	Image imgBuscar, imgSalir, imgAtras, imgAdelante, imgNuevo;
	
	public ConManRepresentantes(boolean consultaOmantenimiento){
		this.consulta = consultaOmantenimiento;
		//this.consulta = false;
		m = MysqlConnect.getDbCon();
		creaGui();
	}
	
	public ConManRepresentantes(JFrame parentFrame, boolean consultaOmantenimiento){
		frameMenu = parentFrame;
		this.consulta = consultaOmantenimiento;
		//this.consulta = false;
		m = MysqlConnect.getDbCon();
		creaGui();
	}
	
	private void creaGui(){

		pantalla = new EscapeDialog();
		if(consulta)
			pantalla.setTitle("Consulta Representantes");
		else
			pantalla.setTitle("Mantenimiento Representantes");
		pantalla.setModal(true);
		pantalla.setSize(800, 490);
		pantalla.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		pantalla.setLayout(null);
		pantalla.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//frameMenu.setVisible(true);
				frameMenu.setEnabled(true);
				pantalla.dispose();
			}
		});
	
		lCodigo = new JLabel("Código");
		lCodigo.setBounds(10, 15, 90, 20);
		lCodigo.setFont(Apariencia.cambiaFuente());
		pantalla.add(lCodigo);
		
		jbIndiceRepresentantes = new JButton();
		jbIndiceRepresentantes.setBounds(80, 15, 25, 25);
		jbIndiceRepresentantes.addActionListener(new BotonIndiceRepresentantesListener());
		try {
			imgBuscar = ImageIO.read(getClass().getResource("/imagenes/BUSCAR.gif"));
			jbIndiceRepresentantes.setIcon(new ImageIcon(imgBuscar));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		jbIndiceRepresentantes.setFocusable(false);
		pantalla.add(jbIndiceRepresentantes);		
		
		jtfnfCodigo = new JTextFieldNumeroFijo(4);
		jtfnfCodigo.setBounds(110, 15, 120, 25);
		jtfnfCodigo.setTextoAyuda("Código numérico del representante.<BR>" +
				"Pinchar en 'Nuevo' para obtener codigo adecuado.", 4);
		jtfnfCodigo.setFont(Apariencia.cambiaFuente());
		jtfnfCodigo.addActionListener(new CodigoListener());
		jtfnfCodigo.addFocusListener(new CodigoListener());
		pantalla.add(jtfnfCodigo);
		
		jbNuevo = new JButton();
		jbNuevo.setBounds(250, 15, 30, 30);
		jbNuevo.addActionListener(new BontonNuevoListener());
		try {
			imgNuevo = ImageIO.read(getClass().getResource("/imagenes/Nuevo.gif"));
			jbNuevo.setIcon(new ImageIcon(imgNuevo));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		jbNuevo.setToolTipText("Pincha para crear un nuevo Representante");
		jbNuevo.setFocusable(false);
		pantalla.add(jbNuevo);
		
		
		lCentro = new JLabel("Centro");
		lCentro.setBounds(300, 15, 90, 20);
		lCentro.setFont(Apariencia.cambiaFuente());
		pantalla.add(lCentro);
		
		jtfnfCentro = new JTextFieldNumeroFijo(3);
		jtfnfCentro.setBounds(370, 15, 30, 25);
		jtfnfCentro.setFont(Apariencia.cambiaFuente());
		if(DatosComunes.sisTienda == 1){
			jtfnfCentro.setEnabled(false);
			jtfnfCentro.setFocusable(false);
		}
		pantalla.add(jtfnfCentro);
		
		jcbActivado = new JCheckBox("Activado");
		jcbActivado.setBounds(500, 15, 130, 20);
		jcbActivado.setFont(Apariencia.cambiaFuente());
		jcbActivado.setFocusable(false);
		pantalla.add(jcbActivado);
		
		// Dastos identificación
		Border borderDatosIdentificacion = BorderFactory.createRaisedBevelBorder();
		Border titledBorderDatosIdentificacion = new TitledBorder(borderDatosIdentificacion, "Datos Identificación", TitledBorder.LEFT, TitledBorder.TOP, Apariencia.cambiaFuente(), Color.BLACK);
		JPanel pDatosIdentificacion = new JPanel();
		pDatosIdentificacion.setLayout(null);
		pDatosIdentificacion.setBorder(titledBorderDatosIdentificacion);
		pDatosIdentificacion.setBounds(10, 50, 550, 350);
		pantalla.add(pDatosIdentificacion);
		
		
		lNombre = new JLabel("Nombre");
		lNombre.setBounds(10, 30, 90, 20);
		lNombre.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(lNombre);
		
		jtffNombre = new JTextFieldFijo(16);
		jtffNombre.setBounds(180, 30, 200, 25);
		jtffNombre.setTextoAyuda("Nombre del Representante.<BR>" +
				"Se puede dejar vacio.", 4);
		jtffNombre.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(jtffNombre);
		
		lApellidosRazon = new JLabel("Apellidos/Razón");
		lApellidosRazon.setBounds(10, 60, 180, 20);
		lApellidosRazon.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(lApellidosRazon);
		
		jtffApellidosRazon = new JTextFieldFijo(30);
		jtffApellidosRazon.setBounds(180, 60, 350, 25);
		jtffApellidosRazon.setTextoAyuda("Apellidos del representante.<BR>" +
				"En caso de ser empresa, poner la Razón Social.", 4);
		jtffApellidosRazon.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(jtffApellidosRazon);
		
		lContacto = new JLabel("Contacto");
		lContacto.setBounds(10, 90, 180, 20);
		lContacto.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(lContacto);
		
		jtffContacto = new JTextFieldFijo(30);
		jtffContacto.setBounds(180, 90, 350, 25);
		jtffContacto.setTextoAyuda("Persona de contacto.<BR>" +
				"Se puede dejar vacío.", 4);
		jtffContacto.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(jtffContacto);
		
		lAtencionDireccion = new JLabel("Att.Direccion");
		lAtencionDireccion.setBounds(10, 120, 180, 20);
		lAtencionDireccion.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(lAtencionDireccion);
		
		jtffAtencionDireccion = new JTextFieldFijo(30);
		jtffAtencionDireccion.setBounds(180, 120, 350, 25);
		jtffAtencionDireccion.setTextoAyuda("Para poner a la Atención de persona o departamento concretos.<BR>" +
				"También se puede usar para aumentar el campo de dirección.<BR>" +
				"Se puede dejar vacío.", 4);
		jtffAtencionDireccion.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(jtffAtencionDireccion);
		
		lDireccion = new JLabel("Direccion");
		lDireccion.setBounds(10, 150, 180, 20);
		lDireccion.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(lDireccion);
		
		jtffDireccion = new JTextFieldFijo(30);
		jtffDireccion.setBounds(180, 150, 350, 25);
		jtffDireccion.setTextoAyuda("Dirección.<BR>" +
				"Se puede dejar vacío.", 4);
		jtffDireccion.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(jtffDireccion);
		
		lPais = new JLabel("Pais");
		lPais.setBounds(10, 180, 60, 20);
		lPais.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(lPais);
		
		jbIndicePaises = new JButton();
		jbIndicePaises.setBounds(50, 180, 25, 25);
		jbIndicePaises.addActionListener(new BotonIndicePaisListener());
		jbIndicePaises.setIcon(new ImageIcon(imgBuscar));
		jbIndicePaises.setFocusable(false);
		pDatosIdentificacion.add(jbIndicePaises);
		
		jtfnfPais = new JTextFieldNumeroFijo(10);
		jtfnfPais.setBounds(80, 180, 50, 25);
		jtfnfPais.setTextoAyuda("Código de pais. Normalmente 0.<BR>" +
				"Se puede dejar vacío.", 4);
		jtfnfPais.setFont(Apariencia.cambiaFuente());
		jtfnfPais.addActionListener(new PaisListener());
		jtfnfPais.addFocusListener(new PaisListener());
		pDatosIdentificacion.add(jtfnfPais);
		
		lPoblacion = new JLabel("Poblacion");
		lPoblacion.setBounds(180, 180, 80, 20);
		lPoblacion.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(lPoblacion);
		
		jbIndicePoblaciones = new JButton();
		jbIndicePoblaciones.setBounds(270, 180, 25, 25);
		jbIndicePoblaciones.addActionListener(new PoblacionListener());
		jbIndicePoblaciones.setIcon(new ImageIcon(imgBuscar));
		jbIndicePoblaciones.setFocusable(false);
		pDatosIdentificacion.add(jbIndicePoblaciones);
				
		jtffPoblacion = new JTextFieldFijo(20);
		jtffPoblacion.setBounds(300, 180, 230, 25);
		jtffPoblacion.setTextoAyuda("Población.<BR>" +
				"Se puede dejar vacío.", 4);
		jtffPoblacion.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(jtffPoblacion);
		
		lCodigoPostal = new JLabel("Cp");
		lCodigoPostal.setBounds(10, 210, 50, 20);
		lCodigoPostal.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(lCodigoPostal);
		
		jtfnfCodigoPostal = new JTextFieldNumeroFijo(5);
		jtfnfCodigoPostal.setBounds(60, 210, 70, 25);
		jtfnfCodigoPostal.setTextoAyuda("Código Postal según Correos.<BR>" +
				"No se puede dejar vacío.", 4);
		jtfnfCodigoPostal.setFont(Apariencia.cambiaFuente());
		//jtfnfCodigoPostal.addActionListener(new CodigoPostalListener());
		//jtfnfCodigoPostal.addFocusListener(new CodigoPostalListener());
		pDatosIdentificacion.add(jtfnfCodigoPostal);
		
		lTelefono = new JLabel("Teléfono");
		lTelefono.setBounds(150, 210, 80, 20);
		lTelefono.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(lTelefono);	
				
		jtfnfTelefono = new JTextFieldNumeroFijo(16);
		jtfnfTelefono.setBounds(240, 210, 130, 25);
		jtfnfTelefono.setTextoAyuda("Teléfono.<BR>" +
				"Se puede dejar vacío.", 4);
		jtfnfTelefono.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(jtfnfTelefono);
		
		lFax = new JLabel("Fax");
		lFax.setBounds(380, 210, 40, 20);
		lFax.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(lFax);
				
		jtfnfFax = new JTextFieldNumeroFijo(16);
		jtfnfFax.setBounds(420, 210, 110, 25);
		jtfnfFax.setTextoAyuda("Fax.<BR>" +
				"Se puede dejar vacío.", 4);
		jtfnfFax.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(jtfnfFax);			
		
		lProvincia = new JLabel("Prov.");
		lProvincia.setBounds(10, 240, 50, 20);
		lProvincia.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(lProvincia);
		
		jbIndiceProvincias = new JButton();
		jbIndiceProvincias.setBounds(50, 240, 25, 25);
		jbIndiceProvincias.addActionListener(new BotonIndiceProvinciaListener());
		jbIndiceProvincias.setIcon(new ImageIcon(imgBuscar));
		jbIndiceProvincias.setFocusable(false);
		pDatosIdentificacion.add(jbIndiceProvincias);
				
		jtfnfProvincia = new JTextFieldNumeroFijo(2);
		jtfnfProvincia.setBounds(80, 240, 50, 25);
		jtfnfProvincia.setTextoAyuda("Provincia.<BR>" +
				"No se puede dejar vacío.", 4);
		jtfnfProvincia.setFont(Apariencia.cambiaFuente());
		jtfnfProvincia.addActionListener(new ProvinciaListener());
		jtfnfProvincia.addFocusListener(new ProvinciaListener());
		pDatosIdentificacion.add(jtfnfProvincia);		
		
		lNif = new JLabel("Nif");
		lNif.setBounds(150, 240, 50, 20);
		lNif.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(lNif);
				
		// 12 por si es un NIF intracomunitario
		jtffNif = new JTextFieldFijo(12);
		jtffNif.setBounds(200, 240, 130, 25);
		jtffNif.setTextoAyuda("N.I.F. o C.I.F.<BR>" + 
				"Para NIF utilizar el formato 99999999X, por ejemplo: 12345678A<BR>" +
				"Para CIF utilizar el formato X99999999, por ejemplo: B98765432<BR>" +
				"Se puede dejar vacío, pero no deberías", 4);
		jtffNif.setFont(Apariencia.cambiaFuente());
		jtffNif.addActionListener(new NifActionListener());
		jtffNif.addFocusListener(new NifActionListener());
		pDatosIdentificacion.add(jtffNif);
		
		lFirmaElectronica = new JLabel("Firma E.");
		lFirmaElectronica.setBounds(350, 240, 80, 20);
		lFirmaElectronica.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(lFirmaElectronica);		
				
		jtffFirmaElectronica = new JTextFieldFijo(8);
		jtffFirmaElectronica.setBounds(420, 240, 110, 25);
		jtffFirmaElectronica.setTextoAyuda("Firma electónica para procesos autorizados.<BR>" +
				"Se puede dejar vacío.", 4);
		jtffFirmaElectronica.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(jtffFirmaElectronica);
		
		lEMail = new JLabel("EMail");
		lEMail.setBounds(10, 270, 80, 20);
		lEMail.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(lEMail);
				
		jtffEMail = new JTextFieldFijo(60);
		jtffEMail.setBounds(80, 270, 450, 25);
		jtffEMail.setTextoAyuda("Dirección de e-mail.<BR>" +
				"Se puede dejar vacío.", 4);
		jtffEMail.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(jtffEMail);	
		
		
		
		lObservaciones = new JLabel("Observaciones");
		lObservaciones.setBounds(10, 300, 130, 20);
		lObservaciones.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(lObservaciones);
				
		jtffObservaciones = new JTextFieldFijo(30);
		jtffObservaciones.setBounds(130, 300, 400, 25);
		jtffObservaciones.setTextoAyuda("Observaciones.<BR>" +
				"Se puede dejar vacío.", 4);
		jtffObservaciones.setFont(Apariencia.cambiaFuente());
		pDatosIdentificacion.add(jtffObservaciones);
		
		
		// Condiciones especiales
		Border borderCondicionesEspeciales = BorderFactory.createRaisedBevelBorder();
		Border titledBorderCondicionesEspeciales = new TitledBorder(borderCondicionesEspeciales, "Condiciones Especiales", TitledBorder.LEFT, TitledBorder.TOP, Apariencia.cambiaFuente(), Color.BLACK);
		JPanel pCondicionesEspeciales = new JPanel();
		pCondicionesEspeciales.setLayout(null);
		pCondicionesEspeciales.setBorder(titledBorderCondicionesEspeciales);
		pCondicionesEspeciales.setBounds(565, 50, 220, 130);
		pantalla.add(pCondicionesEspeciales);
		
		lComision = new JLabel("Comisión");
		lComision.setBounds(10, 30, 90, 20);
		lComision.setFont(Apariencia.cambiaFuente());
		pCondicionesEspeciales.add(lComision);
		
		jtfn2dComision = new JTextFieldNumero2Decimales();
		jtfn2dComision.setBounds(110, 30, 70, 25);
		jtfn2dComision.setTextoAyuda("Comisión de ventas del Representante.<BR>" +
				"Se puede dejar vacio.", 4);
		jtfn2dComision.setFont(Apariencia.cambiaFuente());
		pCondicionesEspeciales.add(jtfn2dComision);
		
		lPorcentaje1 = new JLabel("%");
		lPorcentaje1.setBounds(190, 30, 20, 20);
		lPorcentaje1.setFont(Apariencia.cambiaFuente());
		pCondicionesEspeciales.add(lPorcentaje1);
		
		lTipo = new JLabel("Tipo");
		lTipo.setBounds(10, 60, 90, 20);
		lTipo.setFont(Apariencia.cambiaFuente());
		pCondicionesEspeciales.add(lTipo);
		
		jtfnfTipo = new JTextFieldNumeroFijo(10);
		jtfnfTipo.setBounds(110, 60, 70, 25);
		jtfnfTipo.setTextoAyuda("Tipo de comisión de ventas del Representante.<BR>" +
				"Se puede dejar vacio.", 4);
		jtfnfTipo.setFont(Apariencia.cambiaFuente());
		pCondicionesEspeciales.add(jtfnfTipo);
		
		lRetencion = new JLabel("Retención");
		lRetencion.setBounds(10, 90, 90, 20);
		lRetencion.setFont(Apariencia.cambiaFuente());
		pCondicionesEspeciales.add(lRetencion);
		
		jtfn2dRetencion = new JTextFieldNumero2Decimales();
		jtfn2dRetencion.setBounds(110, 90, 70, 25);
		jtfn2dRetencion.setTextoAyuda("Retención del Representante.<BR>" +
				"Se puede dejar vacio.", 4);
		jtfn2dRetencion.setFont(Apariencia.cambiaFuente());
		pCondicionesEspeciales.add(jtfn2dRetencion);
		
		lPorcentaje2 = new JLabel("%");
		lPorcentaje2.setBounds(190, 90, 20, 20);
		lPorcentaje2.setFont(Apariencia.cambiaFuente());
		pCondicionesEspeciales.add(lPorcentaje2);
		
		jbEtiquetasFirmaElectronica = new JButton("Etiquetas Firma E.");
		jbEtiquetasFirmaElectronica.setBounds(570, 240, 200, 30);
		jbEtiquetasFirmaElectronica.setFont(Apariencia.cambiaFuente());
		//jbEtiquetasFirmaElectronica.addActionListener(new BotonjbEtiquetasFirmaElectronicaListener());
		pantalla.add(jbEtiquetasFirmaElectronica);
		
		jbGeneraFirmaElectronica = new JButton("Genera Firma E.");
		jbGeneraFirmaElectronica.setBounds(570, 280, 200, 30);
		jbGeneraFirmaElectronica.setFont(Apariencia.cambiaFuente());
		//jbGeneraFirmaElectronica.addActionListener(new BotonjbGeneraFirmaElectronicaListener());
		pantalla.add(jbGeneraFirmaElectronica);
		
		jcbPermitePedidosWeb = new JCheckBox("Permite Pedidos Web");
		jcbPermitePedidosWeb.setBounds(570, 320, 200, 20);
		jcbPermitePedidosWeb.setFont(Apariencia.cambiaFuente());
		pantalla.add(jcbPermitePedidosWeb);
		
		jbBorrar = new JButton("Borrar");
		jbBorrar.setBounds(460, 410, 100, 30);
		jbBorrar.setFont(Apariencia.cambiaFuente());
		jbBorrar.addActionListener(new BotonBorrarListener());
		pantalla.add(jbBorrar);
		
		
		jbGrabar = new JButton("Grabar");
		jbGrabar.setBounds(570, 410, 90, 30);
		jbGrabar.setFont(Apariencia.cambiaFuente());
		jbGrabar.addActionListener(new BotonGrabarListener());
		pantalla.add(jbGrabar);
		
		jbAtras = new JButton();
		jbAtras.setBounds(670, 410, 30, 30);
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
		jbAdelante.setBounds(710, 410, 30, 30);
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
		jbSalir.setBounds(20, 410, 30, 30);
		jbSalir.setFont(Apariencia.cambiaFuente());
		jbSalir.addActionListener(new SalirListener());
		try {
			imgSalir = ImageIO.read(getClass().getResource("/imagenes/SALIR.gif"));
			jbSalir.setIcon(new ImageIcon(imgSalir));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		pantalla.add(jbSalir);
		
		if(consulta){
			jbNuevo.setVisible(false);
			jbBorrar.setVisible(false);
			jbGrabar.setVisible(false);
		}else{
			jbNuevo.setVisible(true);
			jbBorrar.setVisible(true);
			jbGrabar.setVisible(true);
		}
		
		enCreacion = false;
		cargaInicial();
		pantalla.setVisible(true);
	}
		
	private void cargaInicial(){
		// Carga inicial en el primer Proveedor
		if(jtfnfCodigo.getText().length() == 0)
			jtfnfCodigo.setText("1");
		
		String strSql = "SELECT * FROM REPRES WHERE EMPRESA = '" + 
         DatosComunes.eEmpresa + 
         "' AND REPRES_REPRE >= " + jtfnfCodigo.getText();
		
		if(DatosComunes.centroCont != 0)
			strSql += " AND REPRES_CENTRO = " + DatosComunes.centroCont;
		
        strSql += " LIMIT 1";
		
		cargaDatos(strSql);			
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
					representante.read(rs);

					jtfnfCodigo.setText(String.valueOf(representante.getRepresentante()));
					jtfnfCentro.setText(String.valueOf(representante.getCentro()));				
					jtffNombre.setText(representante.getNombre());
					jtffApellidosRazon.setText(representante.getApellidosRazonSocial());
					jtffContacto.setText(representante.getContacto());
					jtffAtencionDireccion.setText(representante.getDireccionAtencion());
					jtffDireccion.setText(representante.getDireccion());
					jtfnfPais.setText(String.valueOf(representante.getPais()));
					jtffPoblacion.setText(representante.getPoblacion());
					jtfnfCodigoPostal.setText(String.valueOf(representante.getCodigoPostal()));
					jtfnfTelefono.setText(representante.getTelefono());
					jtfnfFax.setText(representante.getFax());
					jtfnfProvincia.setText(String.valueOf(representante.getProvincia()));		
					jtffNif.setText(representante.getNif());
					jtffFirmaElectronica.setText(representante.getFirmaElectronica());
					jtffEMail.setText(representante.getEmail());
					jtffObservaciones.setText(representante.getObservaciones());
					jtfn2dComision.setText(String.valueOf(representante.getComision()));
					jtfnfTipo.setText(String.valueOf(representante.getTipoComision()));
					jtfn2dRetencion.setText(String.valueOf(representante.getRetencionIrpf()));
					
					if(representante.getActivo() == 1)
						jcbActivado.setSelected(true);
					else
						jcbActivado.setSelected(false);
					
					if(representante.getPermitePedidosWeb() == 1)
						jcbPermitePedidosWeb.setSelected(true);
					else	
						jcbPermitePedidosWeb.setSelected(false);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return numeroDeFilas;
	}
	
	private void borrarPantalla(){	
		if(!enCreacion)
			jtfnfCodigo.setText("0");
		jtfnfCentro.setText(String.valueOf(representante.getCentro()));				
		jtffNombre.setText("");
		jtffApellidosRazon.setText("");
		jtffContacto.setText("");
		jtffAtencionDireccion.setText("");
		jtffDireccion.setText("");
		jtfnfPais.setText("0");
		jtffPoblacion.setText("");
		jtfnfCodigoPostal.setText("0");
		jtfnfTelefono.setText("");
		jtfnfFax.setText("");
		jtfnfProvincia.setText("0");		
		jtffNif.setText("");
		jtffFirmaElectronica.setText("");
		jtffEMail.setText("");
		jtffObservaciones.setText("");
		jtfn2dComision.setText("0,00");
		jtfnfTipo.setText("0");
		jtfn2dRetencion.setText("0,00");
		
		jcbActivado.setSelected(true);
		jcbPermitePedidosWeb.setSelected(false);		
	}
	
	class AdelanteListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			enCreacion = false;
			if(jtfnfCodigo.getText().length() == 0)
				jtfnfCodigo.setText("1");
			
			String strSql = "SELECT * FROM REPRES WHERE EMPRESA = '" + 
	         DatosComunes.eEmpresa + 
	         "' AND REPRES_REPRE > " + jtfnfCodigo.getText();
			
			if(DatosComunes.centroCont != 0)
				strSql += " AND REPRES_CENTRO = " + DatosComunes.centroCont;
			
	        strSql += " AND REPRES_REPRE < 99999999 LIMIT 1";
			
			cargaDatos(strSql);			
		}	
	}
	
	class AtrasListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			enCreacion = false;
			if(jtfnfCodigo.getText().length() == 0)
				jtfnfCodigo.setText("999999999");
			
			String strSql = "SELECT * FROM REPRES WHERE EMPRESA = '" + 
	         DatosComunes.eEmpresa + 
	         "' AND REPRES_REPRE < " + jtfnfCodigo.getText();
			
			if(DatosComunes.centroCont != 0)
				strSql += " AND REPRES_CENTRO = " + DatosComunes.centroCont;
			 
	        strSql += " AND REPRES_REPRE >= 0 ORDER BY REPRES_REPRE DESC LIMIT 1";

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
	
	class BotonIndiceRepresentantesListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			IndiceRepresentantes indiceRepresentantes = new IndiceRepresentantes();
			jtfnfCodigo.setText(String.valueOf(indiceRepresentantes.getRepresentante()));
			
			// Si se ha pulsado ESCAPE en el indice, tendremos un '0' en Código
			if(!jtfnfCodigo.getText().equalsIgnoreCase("0")){
				String strSql = "SELECT * FROM REPRES WHERE EMPRESA = '" + 
				DatosComunes.eEmpresa + 
				"' AND REPRES_REPRE = " + jtfnfCodigo.getText();

				if(DatosComunes.centroCont != 0)
					strSql += " AND REPRES_CENTRO = " + DatosComunes.centroCont;			 	       


				cargaDatos(strSql);

				Cuenta cuenta = new Cuenta();
				cuenta.setCentro(DatosComunes.centroCont);
				if(!cuenta.existenCuentasSuperiores("417" + Cadena.enteroCerosIzquierda(Integer.valueOf(jtfnfCodigo.getText()), 4)))
					JOptionPane.showMessageDialog(null, "<html><font size='4'><strong>" + 
							"Problema con cuentas superiores 417!!!" + 
					"</strong></font></html>");
			}else{
				borrarPantalla();
			}
		}
	}
	
	class CodigoListener implements ActionListener, FocusListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(!jtfnfCodigo.getText().equalsIgnoreCase("0"))
				cargaPorCodigo(Integer.valueOf(jtfnfCodigo.getText()));
			else
				borrarPantalla();
			
		}
		
		private void cargaPorCodigo(int codigo){
			String strSql = "SELECT * FROM REPRES WHERE EMPRESA = '" + 
			DatosComunes.eEmpresa + 
			"' AND REPRES_REPRE = " + codigo;

			if(DatosComunes.centroCont != 0)
				strSql += " AND REPRES_CENTRO = " + DatosComunes.centroCont;			 	       


			if(BaseDatos.countRows(strSql) == 0){
				if(!enCreacion){
					JOptionPane.showMessageDialog(null, "<html><font size='4'><strong>" + 
							"Representante inexistente!!!" + 
					"</strong></font></html>");
					enCreacion = true;
				}
				borrarPantalla();
			}else{
				enCreacion = false;
				cargaDatos(strSql);

				Cuenta cuenta = new Cuenta();
				cuenta.setCentro(DatosComunes.centroCont);
				if(!cuenta.existenCuentasSuperiores("417" + Cadena.enteroCerosIzquierda(Integer.valueOf(jtfnfCodigo.getText()), 4)))
					JOptionPane.showMessageDialog(null, "<html><font size='4'><strong>" + 
							"Problema con cuentas superiores 417!!!" + 
					"</strong></font></html>");
			}
		}

		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub
			enCreacion = false;
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			if(!jtfnfCodigo.getText().equalsIgnoreCase("0"))
				cargaPorCodigo(Integer.valueOf(jtfnfCodigo.getText()));
			else
				borrarPantalla();
		}
	}
	
	class BontonNuevoListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			enCreacion = true;
			borrarPantalla();
			String strSql = "SELECT * FROM REPRES WHERE EMPRESA = '" + 
	         DatosComunes.eEmpresa + 
	         "' AND REPRES_REPRE < 999999";
			
			if(DatosComunes.centroCont != 0)
				strSql += " AND REPRES_CENTRO = " + DatosComunes.centroCont;
			 
	        strSql += " AND REPRES_REPRE >= 0 ORDER BY REPRES_REPRE DESC LIMIT 1";
	        	        	      
			if(BaseDatos.countRows(strSql) > 0){
				try {
					borrarPantalla();
					//Proveedor proveedor = new Proveedor(); 
					rs = m.query(strSql);
					
					// Recorremos el recodSet para ir rellenando la tabla de marcas
					if (rs.next() == true) {
						representante.read(rs);
						
						jtfnfCodigo.setText(String.valueOf(representante.getRepresentante() + 1));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else{
				jtfnfCodigo.setText("1");
			}
		}
	}
	
	// Necesitamos borrar tanta el representante como la cuenta contable asociada
	// 417 XX XX
	class BotonBorrarListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {	
			enCreacion = false;
			boolean existeCuentaContable = false;
			boolean cuentaContableConSaldo = false;
			int centro = DatosComunes.centroCont;
			String cuentaRepresentante = "417" + Cadena.enteroCerosIzquierda(Integer.valueOf(jtfnfCodigo.getText().trim()), 4);
			if(centro == 0 && jtfnfCentro.getText().trim().length() == 0)
				centro = 1;
			if(centro == 0 && jtfnfCentro.getText().trim() == "0")
				centro = 1;
			if(centro == 0 && Integer.valueOf(jtfnfCentro.getText().trim()) > 0)
				centro = Integer.valueOf(jtfnfCentro.getText().trim());
			
			String strSql = "SELECT * FROM REPRES WHERE EMPRESA = '" + 
	         DatosComunes.eEmpresa + 
	         "' AND REPRES_REPRE = " + Integer.valueOf(jtfnfCodigo.getText().trim());
			
			if(DatosComunes.centroCont != 0)
				strSql += " AND REPRES_CENTRO = " + centro;				

			// Comprobramos si existe el representante
			if(BaseDatos.countRows(strSql) > 0){
				// Comprobamos si existe la cuenta contable 417 XX XX donde XX XX = codigo representante
				if(Cuenta.existeCuenta(cuentaRepresentante, centro)){
					existeCuentaContable = true;					
					// Si existe la cuenta, tenemos que ver si tiene saldo, se es
					// así, NO SE PUEDE BORRAR.
					cuentaContableConSaldo = Cuenta.cuentaConSaldo(cuentaRepresentante, centro);
					if(cuentaContableConSaldo)
						Apariencia.mensajeInformativo(5, "Cuenta contable " + cuentaRepresentante + " tiene saldo!!!<BR>No se puede borrar ni el representante ni la cuenta contable asociada");
					else{
						// Tenemos que borrar el REPRESENTANTE y la CUENTA
						if(Cuenta.delete(cuentaRepresentante, centro) == 1){
							Apariencia.mensajeInformativo(5, "Cuenta contable " + cuentaRepresentante + " borrada correctamente.");
							if(representante.delete() == 1)
								Apariencia.mensajeInformativo(5, "Representante " + representante.getApellidosRazonSocial() + " borrado correctamente.");
						}						
					}										
				}else{
					Apariencia.mensajeInformativo(5, "No existe la cuenta contable de este representante.<BR>Revisarlo!!!");
				}					
			}else{
				JOptionPane.showMessageDialog(null, "<html><font size='4'><strong>" + 
						"No se puede borrar ese representante porque no existe!!!" + 
				"</strong></font></html>");
			}			
		}		
	}
	
	
	class BotonGrabarListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			boolean representanteGrabadoOk = true, cuentaGrabadaOk = true;
			int centro = DatosComunes.centroCont;			
			String strCuentaRepresentante = "417" + Cadena.enteroCerosIzquierda(Integer.valueOf(jtfnfCodigo.getText().trim()), 4);
			
			if(centro == 0 && jtfnfCentro.getText().trim().length() == 0)
				centro = 1;
			if(centro == 0 && jtfnfCentro.getText().trim() == "0")
				centro = 1;
			if(centro == 0 && Integer.valueOf(jtfnfCentro.getText().trim()) > 0)
				centro = Integer.valueOf(jtfnfCentro.getText().trim());
			
			Cuenta cuentaRepresentante = new Cuenta();
			cuentaRepresentante.setActivo(1);
			cuentaRepresentante.setCentro(centro);
			cuentaRepresentante.setCuenta(strCuentaRepresentante);
			cuentaRepresentante.setEmpresa(DatosComunes.eEmpresa);
			cuentaRepresentante.setExtenOtroFichero(3);
			cuentaRepresentante.setGrado("3");
			cuentaRepresentante.setSaldo(0.0);
			cuentaRepresentante.setSaldoUltimaDepuracion(0.0);
			cuentaRepresentante.setTitulo(jtffApellidosRazon.getText().trim());
			representanteGrabadoOk = cuentaRepresentante.write();
			
			
			representante.setRepresentante(Integer.valueOf(jtfnfCodigo.getText()));
			representante.setCentro(centro);				
			representante.setNombre(jtffNombre.getText().trim());
			representante.setApellidosRazonSocial(jtffApellidosRazon.getText().trim());
			representante.setContacto(jtffContacto.getText().trim());
			representante.setDireccionAtencion(jtffAtencionDireccion.getText().trim());
			representante.setDireccion(jtffDireccion.getText().trim());
			representante.setPais(Integer.valueOf(jtfnfPais.getText().trim()));
			representante.setPoblacion(jtffPoblacion.getText().trim());
			representante.setCodigoPostal(Integer.valueOf(jtfnfCodigoPostal.getText().trim()));
			representante.setTelefono(jtfnfTelefono.getText().trim());
			representante.setFax(jtfnfFax.getText().trim());
			representante.setProvincia(Integer.valueOf(jtfnfProvincia.getText().trim()));		
			representante.setNif(jtffNif.getText().trim());
			representante.setFirmaElectronica(jtffFirmaElectronica.getText().trim());
			representante.setEmail(jtffEMail.getText().trim());
			representante.setObservaciones(jtffObservaciones.getText().trim());
			representante.setComision(jtfn2dComision.getDouble());
			representante.setTipoComision(Integer.valueOf(jtfnfTipo.getText().trim()));
			representante.setRetencionIrpf(jtfn2dRetencion.getDouble());
			
			if(jcbActivado.isSelected())
				representante.setActivo(1);
			else
				representante.setActivo(0);
			if(jcbPermitePedidosWeb.isSelected())
				representante.setPermitePedidosWeb(1);
			else
				representante.setPermitePedidosWeb(0);
			
			representanteGrabadoOk = representante.write();
			
			if(!cuentaGrabadaOk || !representanteGrabadoOk)
				Apariencia.mensajeInformativo(5, "Error al grabar Representante!!!");
			else
				Apariencia.mensajeInformativo(5, "Representante grabado correctamente.");
		}
	}
	
	class BotonIndicePaisListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(indicePaises == null)
				indicePaises = new IndicePaises();
			else
				indicePaises.muestra();
			jtfnfPais.setText(String.valueOf(indicePaises.getPais()));
		}
		
	}
	
	class PaisListener implements ActionListener, FocusListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			jtfnfPais.transferFocus();
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
			int pais = Integer.valueOf(jtfnfPais.getText());
			
			String strSql = "SELECT * FROM CPPAIS WHERE EMPRESA = '" + 
			                 DatosComunes.eEmpresa + "' AND " +
			                 "CPPAIS_CODIGO = " + pais;
			
			if(BaseDatos.countRows(strSql) == 0){
				Apariencia.mensajeInformativo(5, "Pais Inexistente");
				jtfnfPais.setText("0");
				pais = 0;
			}else{
				jtfnfPais.transferFocus();
			}							
		}
	}

	class PoblacionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int pais = 0;
			
			if(jtfnfPais.getText().trim().length() > 0)
				pais = Integer.valueOf(jtfnfPais.getText());
					
			if(indicePoblaciones == null)
				indicePoblaciones = new IndicePoblaciones(pais);
			else
				indicePoblaciones.muestra();
			jtffPoblacion.setText(indicePoblaciones.getPoblacion());
			
		}
		
	}
	
	class BotonIndiceProvinciaListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if(indiceProvincias == null)
				indiceProvincias = new IndiceProvincias();
			else
				indiceProvincias.muestra();
			jtfnfProvincia.setText(String.valueOf(indiceProvincias.getProvincia()));
		}
		
	}
	
	class ProvinciaListener implements ActionListener, FocusListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			jtfnfProvincia.transferFocus();
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
			int provincia = Integer.valueOf(jtfnfProvincia.getText());
			int cpProvincia = Integer.valueOf(String.format("%05d", Integer.valueOf(jtfnfCodigoPostal.getText())).substring(0, 2));
			
			String strSql = "SELECT * FROM CPPROV WHERE EMPRESA = '" + 
			                 DatosComunes.eEmpresa + "' AND " +
			                 "CPPROV_CODIGO = " + provincia;
				
			if(provincia != 0 && cpProvincia != 0 && provincia != cpProvincia){
				Apariencia.mensajeInformativo(5, "Provincia no concuerda con Código Postal!!!");
				jtfnfProvincia.setText(String.valueOf(cpProvincia));
			}
			if(provincia != 0 && BaseDatos.countRows(strSql) == 0){
				Apariencia.mensajeInformativo(5, "Provincia Inexistente!!!");
				jtfnfProvincia.setText("0");
				provincia = 0;
			}else{
				jtfnfProvincia.transferFocus();
			}							
		}
	}

	class NifActionListener implements ActionListener, FocusListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			jtffNif.setText(jtffNif.getText().toUpperCase());
			jtffNif.transferFocus();
		}
		
		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
			int numeroLetras = 0;
			jtffNif.setText(jtffNif.getText().toUpperCase());
			String strNif = jtffNif.getText().trim().toUpperCase();
			
			String strNifCorrecto = Cadena.compruebaCifNif(strNif, Integer.valueOf(jtfnfPais.getText()), true);
			
			if(!strNifCorrecto.equalsIgnoreCase(strNif)){
				jtffNif.setText(strNifCorrecto);
			}
		}

	}	
	
	private void salir(){
		pantalla.dispose();
		frameMenu.setEnabled(true);		
	}
}
