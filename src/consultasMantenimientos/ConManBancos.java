package consultasMantenimientos;

import general.DatosComunes;
import general.MysqlConnect;
import indices.IndiceBancosSucursales;
import indices.IndiceCodigosBanco;
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




import tablas.Banco;
import tablas.Cuenta;
import tablas.IndiceBancos;
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

public class ConManBancos {
	// Con esta variable definimos si estamos en una consulta (TRUE) o en
	// un mantenimiento (FALSE). Nos servirá para tener un sólo programa
	// para algunas Consultas/Mantenimientos que pueden compartir las 
	// mismas pantallas.
	private static boolean consulta;
	private static boolean enCreacion = false;
	
	private static IndiceBancosSucursales ib = null;
	
	static ResultSet rs = null;
	static MysqlConnect m = null;
	
	public Banco banco = new Banco();
	
	// Definiciones de componentes de pantalla
	public JFrame frameMenu = null;
	
	EscapeDialog pantalla;
	
	JLabel lCodigo, lCentro, lBanco, lDescripcionBanco, lSucursal, lDescripcionSucursal, lDigitosControl, lCuentaBancaria,	
		   lIban, lContacto, lTelefono, lFax, lEmail, lConcedido, lRiesgo, lDisponible;
	
	JTextFieldNumeroFijo jtfnfCodigo, jtfnfCentro, jtfnfBanco, jtfnfSucursal,
			jtfnfDigitosControl, jtfnfCuenta;
	
	JTextFieldFijo jtffContacto, jtffTelefono, jtffFax, jtffEmail, jtffIban;
	
	JTextFieldNumero2Decimales jtfn2dConcedido, jtfn2dRiesgo, jtfn2dDisponible;
	
	JCheckBox jcbActivado;
	
	JButton jbIndiceCodigoBanco, jbIndiceBancos, jbIndiceSucursales,
		jbSalir, jbBorrar, jbGrabar, jbAtras, jbAdelante;
	
	Image imgBuscar, imgSalir, imgAtras, imgAdelante, imgNuevo;
	
	public ConManBancos(boolean consultaOmantenimiento){
		this.consulta = consultaOmantenimiento;
		//this.consulta = false;
		m = MysqlConnect.getDbCon();
		creaGui();
	}
	
	public ConManBancos(JFrame parentFrame, boolean consultaOmantenimiento){
		frameMenu = parentFrame;
		this.consulta = consultaOmantenimiento;
		//this.consulta = false;
		m = MysqlConnect.getDbCon();
		creaGui();
	}
	
	private void creaGui(){
		pantalla = new EscapeDialog();
		if(consulta)
			pantalla.setTitle("Consulta Bancos");
		else
			pantalla.setTitle("Mantenimiento Bancos");
		pantalla.setModal(true);
		pantalla.setSize(800, 370);
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
	
	jbIndiceCodigoBanco = new JButton();
	jbIndiceCodigoBanco.setBounds(80, 15, 25, 25);
	jbIndiceCodigoBanco.addActionListener(new BotonIndiceCodigoBancoListener());
	try {
		imgBuscar = ImageIO.read(getClass().getResource("/imagenes/BUSCAR.gif"));
		jbIndiceCodigoBanco.setIcon(new ImageIcon(imgBuscar));
	} catch (IOException ex) {
		ex.printStackTrace();
	}
	jbIndiceCodigoBanco.setFocusable(false);
	pantalla.add(jbIndiceCodigoBanco);		
	
	jtfnfCodigo = new JTextFieldNumeroFijo(4);
	jtfnfCodigo.setBounds(110, 15, 120, 25);
	jtfnfCodigo.setTextoAyuda("Código numérico del banco.<BR>" +
			"Son los 4 primeros dígitos de la cuenta bancaria.<BR>" +
			"Se creará una cuenta contable del tipo 572 XX YYYY, <BR>" +
			"Donde YYYY será el código del banco.", 4);
	jtfnfCodigo.setFont(Apariencia.cambiaFuente());
	jtfnfCodigo.addActionListener(new CodigoListener());
	jtfnfCodigo.addFocusListener(new CodigoListener());
	pantalla.add(jtfnfCodigo);
	
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
	
	// Datos identificación
	Border borderDatosIdentificacion = BorderFactory.createRaisedBevelBorder();
	Border titledBorderDatosIdentificacion = new TitledBorder(borderDatosIdentificacion, "Datos Identificación", TitledBorder.LEFT, TitledBorder.TOP, Apariencia.cambiaFuente(), Color.BLACK);
	JPanel pDatosIdentificacion = new JPanel();
	pDatosIdentificacion.setLayout(null);
	pDatosIdentificacion.setBorder(titledBorderDatosIdentificacion);
	pDatosIdentificacion.setBounds(10, 50, 480, 220);
	pantalla.add(pDatosIdentificacion);
	
	lBanco = new JLabel("Banco");
	lBanco.setBounds(10, 30, 90, 20);
	lBanco.setFont(Apariencia.cambiaFuente());
	pDatosIdentificacion.add(lBanco);
	
	jbIndiceBancos = new JButton();
	jbIndiceBancos.setBounds(100, 30, 25, 25);
	jbIndiceBancos.addActionListener(new BotonIndiceBancosListener());
	jbIndiceBancos.setIcon(new ImageIcon(imgBuscar));
	jbIndiceBancos.setFocusable(false);
	pDatosIdentificacion.add(jbIndiceBancos);
	
	jtfnfBanco = new JTextFieldNumeroFijo(4);
	jtfnfBanco.setBounds(130, 30, 60, 25);
	jtfnfBanco.setTextoAyuda("Cuatro primeros dígitos de la cuenta bancaria.", 4);
	jtfnfBanco.setFont(Apariencia.cambiaFuente());
	jtfnfBanco.addActionListener(new BancoListener());
	pDatosIdentificacion.add(jtfnfBanco);
	
	lDescripcionBanco = new JLabel("lDescripcionBanco");
	lDescripcionBanco.setBounds(200, 30, 190, 20);
	lDescripcionBanco.setFont(Apariencia.cambiaFuente());
	pDatosIdentificacion.add(lDescripcionBanco);
	
	lSucursal = new JLabel("Sucursal");
	lSucursal.setBounds(10, 60, 90, 20);
	lSucursal.setFont(Apariencia.cambiaFuente());
	pDatosIdentificacion.add(lSucursal);
	
	jbIndiceSucursales = new JButton();
	jbIndiceSucursales.setBounds(100, 60, 25, 25);
	jbIndiceSucursales.addActionListener(new BotonIndiceSucursalesListener());
	jbIndiceSucursales.setIcon(new ImageIcon(imgBuscar));
	jbIndiceSucursales.setFocusable(false);
	pDatosIdentificacion.add(jbIndiceSucursales);
	
	jtfnfSucursal = new JTextFieldNumeroFijo(4);
	jtfnfSucursal.setBounds(130, 60, 60, 25);
	jtfnfSucursal.setTextoAyuda("Dígitos 5 al 8 (inclusive) de la cuenta bancaria.", 4);
	jtfnfSucursal.setFont(Apariencia.cambiaFuente());
	jtfnfSucursal.addActionListener(new SucursalListener());
	pDatosIdentificacion.add(jtfnfSucursal);
	
	lDescripcionSucursal = new JLabel("lDescripcionSucursal");
	lDescripcionSucursal.setBounds(200, 60, 290, 20);
	lDescripcionSucursal.setFont(Apariencia.cambiaFuente());
	pDatosIdentificacion.add(lDescripcionSucursal);
	
	lDigitosControl = new JLabel("DC");
	lDigitosControl.setBounds(10, 90, 35, 20);
	lDigitosControl.setFont(Apariencia.cambiaFuente());
	pDatosIdentificacion.add(lDigitosControl);
			
	jtfnfDigitosControl = new JTextFieldNumeroFijo(2);
	jtfnfDigitosControl.setBounds(50, 90, 30, 25);
	jtfnfDigitosControl.setTextoAyuda("Dígitos 9 y 10 de la cuenta bancaria.", 4);
	jtfnfDigitosControl.setFont(Apariencia.cambiaFuente());
	jtfnfDigitosControl.addActionListener(new DigitosControlListener());
	pDatosIdentificacion.add(jtfnfDigitosControl);
	
	lCuentaBancaria = new JLabel("Cuenta");
	lCuentaBancaria.setBounds(90, 90, 70, 20);
	lCuentaBancaria.setFont(Apariencia.cambiaFuente());
	pDatosIdentificacion.add(lCuentaBancaria);
			
	jtfnfCuenta = new JTextFieldNumeroFijo(10);
	jtfnfCuenta.setBounds(160, 90, 130, 25);
	jtfnfCuenta.setTextoAyuda("Últimos 10 dígitos de la cuenta bancaria.", 4);
	jtfnfCuenta.setFont(Apariencia.cambiaFuente());
	jtfnfCuenta.addActionListener(new CuentaListener());
	jtfnfCuenta.addFocusListener(new CuentaListener());
	pDatosIdentificacion.add(jtfnfCuenta);
	
	lIban = new JLabel("IBAN");
	lIban.setBounds(300, 90, 40, 20);
	lIban.setFont(Apariencia.cambiaFuente());
	pDatosIdentificacion.add(lIban);
			
	jtffIban = new JTextFieldFijo(4);
	jtffIban.setBounds(350, 90, 60, 25);
	jtffIban.setTextoAyuda("Cuatro caractéres para el IBAN.<br>Los dos primeros son el código alfabético del pais y <br> los dos siguientes son númericos.", 4);
	jtffIban.setFont(Apariencia.cambiaFuente());
	jtffIban.addActionListener(new IbanListener());
	jtffIban.addFocusListener(new IbanListener());
	pDatosIdentificacion.add(jtffIban);
	
	lContacto = new JLabel("Contacto");
	lContacto.setBounds(10, 120, 150, 20);
	lContacto.setFont(Apariencia.cambiaFuente());
	pDatosIdentificacion.add(lContacto);
			
	jtffContacto = new JTextFieldFijo(30);
	jtffContacto.setBounds(90, 120, 320, 25);
	jtffContacto.setTextoAyuda("Persona de contacto en el Banco.", 4);
	jtffContacto.setFont(Apariencia.cambiaFuente());
	pDatosIdentificacion.add(jtffContacto);
	
	lTelefono = new JLabel("Teléfono");
	lTelefono.setBounds(10, 150, 150, 20);
	lTelefono.setFont(Apariencia.cambiaFuente());
	pDatosIdentificacion.add(lTelefono);
			
	jtffTelefono = new JTextFieldFijo(16);
	jtffTelefono.setBounds(90, 150, 160, 25);
	jtffTelefono.setTextoAyuda("Teléfono de contacto en el Banco.", 4);
	jtffTelefono.setFont(Apariencia.cambiaFuente());
	pDatosIdentificacion.add(jtffTelefono);
	
	lFax = new JLabel("Fax");
	lFax.setBounds(260, 150, 50, 20);
	lFax.setFont(Apariencia.cambiaFuente());
	pDatosIdentificacion.add(lFax);
			
	jtffFax = new JTextFieldFijo(16);
	jtffFax.setBounds(300, 150, 160, 25);
	jtffFax.setTextoAyuda("Fax de contacto en el Banco.", 4);
	jtffFax.setFont(Apariencia.cambiaFuente());
	pDatosIdentificacion.add(jtffFax);
	
	lEmail = new JLabel("Email");
	lEmail.setBounds(10, 180, 70, 20);
	lEmail.setFont(Apariencia.cambiaFuente());
	pDatosIdentificacion.add(lEmail);
			
	jtffEmail = new JTextFieldFijo(60);
	jtffEmail.setBounds(90, 180, 370, 25);
	jtffEmail.setTextoAyuda("E-mail de contacto del Banco.", 4);
	jtffEmail.setFont(Apariencia.cambiaFuente());
	pDatosIdentificacion.add(jtffEmail);
	
	// Datos Financieros
	Border borderDatosFinancieros = BorderFactory.createRaisedBevelBorder();
	Border titledBorderDatosFinancieros = new TitledBorder(borderDatosFinancieros, "Datos Financieros", TitledBorder.LEFT, TitledBorder.TOP, Apariencia.cambiaFuente(), Color.BLACK);
	JPanel pDatosFinancieros = new JPanel();
	pDatosFinancieros.setLayout(null);
	pDatosFinancieros.setBorder(titledBorderDatosFinancieros);
	pDatosFinancieros.setBounds(500, 50, 280, 130);
	pantalla.add(pDatosFinancieros);
	
	lConcedido = new JLabel("Concedido");
	lConcedido.setBounds(10, 30, 90, 20);
	lConcedido.setFont(Apariencia.cambiaFuente());
	pDatosFinancieros.add(lConcedido);
			
	jtfn2dConcedido = new JTextFieldNumero2Decimales();
	jtfn2dConcedido.setBounds(120, 30, 140, 25);
	jtfn2dConcedido.setTextoAyuda("Crédito que nos conceden.", 4);
	jtfn2dConcedido.setFont(Apariencia.cambiaFuente());
	pDatosFinancieros.add(jtfn2dConcedido);
	
	lRiesgo = new JLabel("Riesgo");
	lRiesgo.setBounds(10, 60, 90, 20);
	lRiesgo.setFont(Apariencia.cambiaFuente());
	pDatosFinancieros.add(lRiesgo);
			
	jtfn2dRiesgo = new JTextFieldNumero2Decimales();
	jtfn2dRiesgo.setBounds(120, 60, 140, 25);
	jtfn2dRiesgo.setTextoAyuda("Riesgo.", 4);
	jtfn2dRiesgo.setFont(Apariencia.cambiaFuente());
	pDatosFinancieros.add(jtfn2dRiesgo);
	
	lDisponible = new JLabel("Disponible");
	lDisponible.setBounds(10, 90, 90, 20);
	lDisponible.setFont(Apariencia.cambiaFuente());
	pDatosFinancieros.add(lDisponible);
			
	jtfn2dDisponible = new JTextFieldNumero2Decimales();
	jtfn2dDisponible.setBounds(120, 90, 140, 25);
	jtfn2dDisponible.setTextoAyuda("Disponible.", 4);
	jtfn2dDisponible.setFont(Apariencia.cambiaFuente());
	jtfn2dDisponible.setEnabled(false);
	pDatosFinancieros.add(jtfn2dDisponible);
	
	jbBorrar = new JButton("Borrar");
	jbBorrar.setBounds(460, 290, 100, 30);
	jbBorrar.setFont(Apariencia.cambiaFuente());
	jbBorrar.addActionListener(new BotonBorrarListener());
	pantalla.add(jbBorrar);
	
	
	jbGrabar = new JButton("Grabar");
	jbGrabar.setBounds(570, 290, 90, 30);
	jbGrabar.setFont(Apariencia.cambiaFuente());
	jbGrabar.addActionListener(new BotonGrabarListener());
	pantalla.add(jbGrabar);
	
	jbAtras = new JButton();
	jbAtras.setBounds(670, 290, 30, 30);
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
	jbAdelante.setBounds(710, 290, 30, 30);
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
	jbSalir.setBounds(20, 290, 30, 30);
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
		jbBorrar.setVisible(false);
		jbGrabar.setVisible(false);
	}else{
		jbBorrar.setVisible(true);
		jbGrabar.setVisible(true);
	}
	
	borrarPantalla();
	cargaInicial();
	pantalla.setVisible(true);
	}
	
	private void cargaInicial(){
		// Carga inicial en el primer Proveedor
		if(jtfnfCodigo.getText().length() == 0)
			jtfnfCodigo.setText("0");
		
		String strSql = "SELECT * FROM BANCOS WHERE EMPRESA = '" + 
         DatosComunes.eEmpresa + 
         "' AND BANCOS_BANCO >= " + jtfnfCodigo.getText();
		
		if(DatosComunes.centroCont != 0)
			strSql += " AND BANCOS_CENTRO = " + DatosComunes.centroCont;
		
        strSql += " LIMIT 1";
		
		cargaDatos(strSql);			
	}
	
	private void cargaDatos(String strSql){		
		int numeroDeFilas = 0;
		String descripcionBanco = "", descripcionSucursal = "";
		
		
		
		numeroDeFilas = BaseDatos.countRows(strSql);
		if(numeroDeFilas > 0){
			try {
				borrarPantalla();				
				rs = m.query(strSql);
				
				// Recorremos el recodSet para ir rellenando la tabla de marcas
				if (rs.next() == true) {
					banco.read(rs);

					// Vamos a averiguar la descripción del Banco y de la Sucursal
					IndiceBancos indiceBancos = new IndiceBancos();
					String strSqlIndiceBancos = "SELECT * FROM BCOIND WHERE " +
							"EMPRESA = '" + DatosComunes.eEmpresa + "' AND " +
							"BCOIND_BANCO = " + banco.getBanco() + " " +
							"ORDER BY BCOIND_BANCO, BCOIND_SUCURSAL LIMIT 1";
					indiceBancos.read(strSqlIndiceBancos);
					lDescripcionBanco.setText(indiceBancos.getDescripcion());
					// Ahora la sucursal
					strSqlIndiceBancos = "SELECT * FROM BCOIND WHERE " +
					"EMPRESA = '" + DatosComunes.eEmpresa + "' AND " +
					"BCOIND_BANCO = " + banco.getBanco() + " AND " +
					"BCOIND_SUCURSAL = '" + util.Cadena.enteroCerosIzquierda(Integer.valueOf(banco.getNumeroSucursal()), 4) + 
					"' LIMIT 1";
					indiceBancos.read(strSqlIndiceBancos);
					lDescripcionSucursal.setText(indiceBancos.getDescripcion());
					
					
					
					jtfnfCodigo.setText(String.valueOf(banco.getBanco()));
					jtfnfCentro.setText(String.valueOf(banco.getCentro()));
					jtfnfBanco.setText(String.valueOf(Cadena.enteroCerosIzquierda(banco.getBanco(), 4)));										
					jtfnfSucursal.setText(String.valueOf(Cadena.enteroCerosIzquierda(banco.getNumeroSucursal(), 4)));			
					jtfnfDigitosControl.setText(String.valueOf(Cadena.enteroCerosIzquierda(banco.getDigitoControl(), 2)));
					jtfnfCuenta.setText(String.valueOf(Cadena.enteroCerosIzquierda(Long.valueOf(banco.getCuenta()), 10)));
					jtffContacto.setText(banco.getContacto());
					jtffTelefono.setText(banco.getTelefono());
					jtffFax.setText(banco.getFax());
					jtffEmail.setText(banco.getEmail());
					jtfn2dConcedido.setText(String.valueOf(banco.getConcedido()));
					jtfn2dRiesgo.setText(String.valueOf(banco.getTotalRiesgo()));
					jtfn2dDisponible.setText(String.valueOf(banco.getConcedido() - banco.getTotalRiesgo()));
					
					// Para saber si el banco esta ACTIVO, tenemos que mirar en su cuenta contable
					Cuenta cuenta = new Cuenta();
					cuenta.read("572" + "00" + Cadena.enteroCerosIzquierda(banco.getBanco(), 4), DatosComunes.centroCont);
					if(cuenta.getActivo() == 1)
						jcbActivado.setSelected(true);
					else
						jcbActivado.setSelected(false);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	private void borrarPantalla(){	
		
		jtfnfCodigo.setText("0");
		jtfnfCentro.setText(String.valueOf(DatosComunes.centroCont));				
		jtfnfBanco.setText("0");
		lDescripcionBanco.setText("");
		jtfnfSucursal.setText("0");
		lDescripcionSucursal.setText("");
		jtfnfDigitosControl.setText("0");
		jtfnfCuenta.setText("0");
		jtffContacto.setText("");
		jtffTelefono.setText("");
		jtffFax.setText("");
		jtffEmail.setText("");
		jtfn2dConcedido.setText("0,00");
		jtfn2dRiesgo.setText("0,00");
		jtfn2dDisponible.setText("0,00");			
		jcbActivado.setSelected(true);
				
	}
	
	class CodigoListener implements ActionListener, FocusListener{
		int codigo = 0;
		boolean vieneDeFocusLost = false;
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			codigo = Integer.valueOf(jtfnfCodigo.getText());
			vieneDeFocusLost = false;
			if(codigo != 0){
				//enCreacion = true;
				cargaPorCodigo(codigo);
				jtfnfCodigo.transferFocus();
			}
		}
		
		

		@Override
		public void focusGained(FocusEvent arg0) {
			
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			
			codigo = Integer.valueOf(jtfnfCodigo.getText());
			vieneDeFocusLost = true;
	
			if(!arg0.isTemporary() && codigo != 0)
				cargaPorCodigo(Integer.valueOf(jtfnfCodigo.getText()));
			
		}
	}
	
	
	
	class BancoListener implements ActionListener, FocusListener{
		int banco = 0;
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			banco = Integer.valueOf(jtfnfBanco.getText());		
			jtfnfBanco.setText(util.Cadena.enteroCerosIzquierda(banco, 4));
			if(banco != 0)
				ponDescripcionBanco(banco);
			else
				lDescripcionBanco.setText("");
			jtfnfBanco.transferFocus();
		}
		
		@Override
		public void focusGained(FocusEvent arg0) {
			
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			
			banco = Integer.valueOf(jtfnfBanco.getText());			
			jtfnfBanco.setText(util.Cadena.enteroCerosIzquierda(banco, 4));
			if(banco != 0)
				ponDescripcionBanco(banco);
			else
				lDescripcionBanco.setText("");
			
		}
	}
	
	class SucursalListener implements ActionListener, FocusListener{
		int sucursal = 0;
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			sucursal = Integer.valueOf(jtfnfSucursal.getText());		
			jtfnfSucursal.setText(util.Cadena.enteroCerosIzquierda(sucursal, 4));
			//if(sucursal != 0)
				ponDescripcionSucursal(Integer.valueOf(jtfnfBanco.getText()), sucursal);
			//else
			//	lDescripcionSucursal.setText("");
			jtfnfSucursal.transferFocus();
		}

		@Override
		public void focusGained(FocusEvent arg0) {
			
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			
			sucursal = Integer.valueOf(jtfnfSucursal.getText());		
			jtfnfSucursal.setText(util.Cadena.enteroCerosIzquierda(sucursal, 4));
			//if(sucursal != 0)
				ponDescripcionSucursal(Integer.valueOf(jtfnfBanco.getText()), sucursal);
			//else
			//	lDescripcionSucursal.setText("");
		}
	}
	
	
	class BotonBorrarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			boolean existeBanco = true;
			boolean existeCuenta = true;
			String strSqlBanco = "SELECT * FROM BANCOS WHERE EMPRESA ='" + DatosComunes.eEmpresa + "'" +
					" AND BANCOS_CENTRO = " + DatosComunes.centroCont +
					" AND BANCOS_BANCO ='" + jtfnfBanco.getText().trim() + "'";
			String strSqlCuenta = "SELECT * FROM CONTAB WHERE EMPRESA = '" + DatosComunes.eEmpresa + "' " +
	                " AND CONTAB_CENTRO = " + DatosComunes.centroCont +
			        " AND CONTAB_CUENTA = '" + "572" + Cadena.enteroCerosIzquierda(Integer.valueOf(jtfnfBanco.getText().trim()), 6) + "'";
			
			if(BaseDatos.countRows(strSqlBanco) == 0)
				existeBanco = false;
			if(BaseDatos.countRows(strSqlCuenta) == 0)
				existeCuenta = false;
			
			Cuenta cuenta = new Cuenta();
			cuenta.read(strSqlCuenta);
			
			if(cuenta.getSaldo() != 0.0)
				JOptionPane.showMessageDialog(null, "<html><font size='4'><strong>" + 
						"No se puede borrar, tiene saldo!!!" + 
				"</strong></font></html>");
			else
				if(existeBanco && existeCuenta){
					Object[] opciones = {"Si", "No"};
					int n = JOptionPane.showOptionDialog(pantalla,
							"<html><font size='4'><strong>" +
							"Desea borrar la cuenta de:<BR>'" 
							+ cuenta.getTitulo() 
							+ "'</strong></font></html>",
							"Borrar Registro",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null,     // Sin Icono personalizado.
							opciones,  // Título de los botonoes
							opciones[1]); // Botón por defecto.

					if(n == 0){
						String strSqlDeleteBanco = "DELETE" + strSqlBanco.substring(8, strSqlBanco.length());
						String strSqlDeleteCuenta = "DELETE" + strSqlCuenta.substring(8, strSqlCuenta.length());

						int registrosBorrados = 0;
						registrosBorrados += BaseDatos.borraRegistro(strSqlDeleteBanco);
						registrosBorrados += BaseDatos.borraRegistro(strSqlDeleteCuenta);
						if(registrosBorrados == 2){
							JOptionPane.showMessageDialog(null, "<html><font size='4'><strong>" + 
									"Banco borrado correctamente." + 
							"</strong></font></html>");

							// Pasamos al siguiente registro
							if(jtfnfBanco.getText().length() == 0)
								jtfnfBanco.setText("0");

							String strSql = "SELECT * FROM BANCOS WHERE EMPRESA = '" + 
							DatosComunes.eEmpresa +   
							"' AND BANCOS_BANCO > " + jtfnfBanco.getText();

							if(DatosComunes.centroCont != 0)
								strSql += " AND BANCOS_CENTRO = " + DatosComunes.centroCont;

							strSql += " AND BANCOS_BANCO < 999999 LIMIT 1";

							cargaDatos(strSql);	
						}
					}
				}else{
					JOptionPane.showMessageDialog(null, "<html><font size='4'><strong>" + 
							"No se puede borrar el Banco o la Cuenta Contable." + 
					"</strong></font></html>");								
				}

		}
		
	}
	
	class AdelanteListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {

			enCreacion = false;
			
			if(jtfnfCodigo.getText().length() == 0)
				jtfnfCodigo.setText("1");
			
			String strSql = "SELECT * FROM BANCOS WHERE EMPRESA = '" + 
	         DatosComunes.eEmpresa + 
	         "' AND BANCOS_BANCO > " + jtfnfCodigo.getText();
			
			if(DatosComunes.centroCont != 0)
				strSql += " AND BANCOS_CENTRO = " + DatosComunes.centroCont;
			
	        strSql += " AND BANCOS_BANCO < 99999999 LIMIT 1";
			
			cargaDatos(strSql);			
		}	
	}

	class AtrasListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			enCreacion = false;
			
			if(jtfnfCodigo.getText().length() == 0)
				jtfnfCodigo.setText("999999999");
			
			String strSql = "SELECT * FROM BANCOS WHERE EMPRESA = '" + 
	         DatosComunes.eEmpresa + 
	         "' AND BANCOS_BANCO < " + jtfnfCodigo.getText();
			
			if(DatosComunes.centroCont != 0)
				strSql += " AND BANCOS_CENTRO = " + DatosComunes.centroCont;
			 
	        strSql += " AND BANCOS_BANCO >= 0 ORDER BY BANCOS_BANCO DESC LIMIT 1";

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
	
	class BotonIndiceBancosListener implements ActionListener, FocusListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			CuentaBancaria cb = new CuentaBancaria();
			
			ib = new IndiceBancosSucursales();
			cb = ib.getBancoSucursal();
			
			if(cb != null){
				if(!cb.getBanco().isEmpty()){
					jtfnfBanco.setText(cb.getBanco());
					ponDescripcionBanco(Integer.valueOf(cb.getBanco()));
				}
				if(!cb.getSucursal().isEmpty()){
					jtfnfSucursal.setText(cb.getSucursal());
					ponDescripcionSucursal(Integer.valueOf(cb.getBanco()), Integer.valueOf(cb.getSucursal()));
				}
			}
		}

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub		
		}
		
	}
	
	class CuentaListener implements ActionListener, FocusListener{
		
		private boolean dcBien = true;		
		
		@Override
		public void actionPerformed(ActionEvent e) {		
			dcBien = compruebaCuenta();		
			if(dcBien)
				jtfnfCuenta.transferFocus();

		}	
		
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			if(!arg0.isTemporary())
				compruebaCuenta();					
		}
	}
	
	class IbanListener implements ActionListener, FocusListener{

		private boolean ibanBien = true;		
		
		@Override
		public void actionPerformed(ActionEvent e) {		
			ibanBien = compruebaIban();		
			if(ibanBien)
				jtffIban.transferFocus();

		}	
		
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			if(!arg0.isTemporary())
				compruebaIban();					
		}
	}
	
	class DigitosControlListener implements ActionListener, FocusListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			jtfnfDigitosControl.transferFocus();
		}

		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	class BotonIndiceSucursalesListener implements ActionListener, FocusListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			CuentaBancaria cb = new CuentaBancaria();
			
			ib = new IndiceBancosSucursales();
			cb = ib.getBancoSucursal();
			
			if(cb != null){
				if(!cb.getBanco().isEmpty()){
					jtfnfBanco.setText(cb.getBanco());
					ponDescripcionBanco(Integer.valueOf(cb.getBanco()));
				}
				if(!cb.getSucursal().isEmpty()){
					jtfnfSucursal.setText(cb.getSucursal());
					ponDescripcionSucursal(Integer.valueOf(cb.getBanco()), Integer.valueOf(cb.getSucursal()));
				}				
			}
		}

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub			
		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub			
		}
		
	}		
	
	class BotonGrabarListener implements ActionListener{

		// Acordarse de crear la cuenta en el la tabla 'Cuenta' 
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean grabarOk = true;

			if(jtfnfCodigo.getText().trim().length() == 0 || jtfnfBanco.getText().trim().length() == 0 ||
			   jtfnfSucursal.getText().trim().length() == 0 || jtfnfDigitosControl.getText().trim().length() == 0 ||
			   jtfnfCuenta.getText().trim().length() == 0){
				util.JOptionPaneConTimeOut.visualizaDialogo(null, "No graba sin datos", "Advertencia!!!", 5000);
				grabarOk = false;
			}	
			
			String strSql = "SELECT * FROM BCOIND WHERE EMPRESA = '" + 
			DatosComunes.eEmpresa + 
			"' AND BCOIND_BANCO = " + Integer.valueOf(jtfnfBanco.getText()) +
			" AND BCOIND_SUCURSAL = '" + jtfnfSucursal.getText().trim() + "'";
			
			if(BaseDatos.countRows(strSql) == 0){
				util.JOptionPaneConTimeOut.visualizaDialogo(null, "No existe Banco o Sucursal en el Indice de Bancos", "Advertencia!!!", 5000);
				grabarOk = false;
			}

			if(grabarOk)
				grabarBanco();
		}

		private void grabarBanco(){
			boolean cuentaWriteOk = true, bancoWriteOk = true;
			Cuenta cuenta = new Cuenta();				

			if(jcbActivado.isSelected())
				cuenta.setActivo(1);
			else
				cuenta.setActivo(0);
			cuenta.setCentro(Integer.valueOf(jtfnfCentro.getText()));
			cuenta.setCuenta("572" + Cadena.enteroCerosIzquierda(Integer.valueOf(jtfnfCodigo.getText().trim()), 6));
			cuenta.setEmpresa(DatosComunes.eEmpresa);
			cuenta.setExtenOtroFichero(4);
			cuenta.setGrado("3");
			cuenta.setSaldo(0);
			cuenta.setSaldoUltimaDepuracion(0);
			cuenta.setTitulo(lDescripcionBanco.getText().trim());
			cuentaWriteOk = cuenta.write();

			Banco banco = new Banco();
			banco.setEmpresa(DatosComunes.eEmpresa);
			banco.setBanco(Integer.valueOf(jtfnfCodigo.getText().trim()));
			banco.setCentro(Integer.valueOf(jtfnfCentro.getText().trim()));
			banco.setNumeroBanco(Integer.valueOf(jtfnfBanco.getText().trim()));
			banco.setNumeroSucursal(Integer.valueOf(jtfnfSucursal.getText().trim()));
			banco.setDigitoControl(Integer.valueOf(jtfnfDigitosControl.getText().trim()));
			banco.setCuenta(Long.valueOf(jtfnfCuenta.getText().trim()));
			banco.setContacto(jtffContacto.getText().trim());
			banco.setTelefono(jtffTelefono.getText().trim());
			banco.setFax(jtffFax.getText().trim());
			banco.setEmail(jtffEmail.getText());
			banco.setConcedido(jtfn2dConcedido.getDouble());
			banco.setTotalRiesgo(jtfn2dRiesgo.getDouble());
		
		
			bancoWriteOk = banco.write();
			
			if(!cuentaWriteOk || !bancoWriteOk)
				JOptionPane.showMessageDialog(null, "Error al grabar Banco!!!");
			else
				JOptionPane.showMessageDialog(null, "Banco grabado correctamente");
		}
	}
	
	class BotonIndiceCodigoBancoListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {		
			
			IndiceCodigosBanco icb = new IndiceCodigosBanco();
			banco = icb.getBanco();
			
			if(icb != null){
				jtfnfBanco.setText(String.valueOf(banco.getBanco()));
				int codigo = banco.getBanco();
					
				if(codigo != 0)
					cargaPorCodigo(codigo);

			}
		}		
	}
	
	private void ponDescripcionBanco(int codigoBanco){
		String strSql = "SELECT * FROM BCOIND WHERE EMPRESA = '" + 
        DatosComunes.eEmpresa + 
        "' AND BCOIND_BANCO = " + codigoBanco;
			
		IndiceBancos indiceBancos = new IndiceBancos();
		
		if(BaseDatos.countRows(strSql) == 0){
			JOptionPane.showMessageDialog(null, "<html><font size='4'><strong>" + 
						"Banco inexistente!!!" + 
				"</strong></font></html>");
			lDescripcionBanco.setText("Inexistente!!!");
		}else{
			indiceBancos.read(strSql);
			lDescripcionBanco.setText(indiceBancos.getDescripcion());
		}				
	}
	
	private void ponDescripcionSucursal(int codigoBanco, int codigoSucursal){
		String strSql = "SELECT * FROM BCOIND WHERE EMPRESA = '" + 
        DatosComunes.eEmpresa + 
        "' AND BCOIND_BANCO = " + codigoBanco +  " AND BCOIND_SUCURSAL = '" + util.Cadena.enteroCerosIzquierda(codigoSucursal, 4) + "'";
			
		IndiceBancos indiceBancos = new IndiceBancos();
		
		if(BaseDatos.countRows(strSql) == 0){
			JOptionPane.showMessageDialog(null, "<html><font size='4'><strong>" + 
						"Sucursal inexistente!!!" + 
				"</strong></font></html>");
			lDescripcionSucursal.setText("Inexistente!!!");
		}else{
			indiceBancos.read(strSql);
			lDescripcionSucursal.setText(indiceBancos.getDescripcion());
		}				
	}
	
	
	private boolean compruebaCuenta(){

		boolean dcBien = true;
		String str1, str2;
		
		CuentaBancaria cb = new CuentaBancaria();
		cb.setBanco(Integer.valueOf(jtfnfBanco.getText()));
		cb.setSucursal(jtfnfSucursal.getText());
		cb.setCuenta(jtfnfCuenta.getText());
		cb.calculaDigitosControl();
		
		str1 = jtfnfDigitosControl.getText();
		str2 = cb.getDigitosControl();
		if(!str1.equalsIgnoreCase(str2)){
			JOptionPane.showMessageDialog(null, "<html><font size='4'><strong>" + 
					"Dígitos de control incorrectos!!!<br>Dígitos correctos: " + cb.getDigitosControl() + 
			"</strong></font></html>");
		    dcBien = false;
		}
		
		return dcBien;
	}
	
	private boolean compruebaIban(){

		boolean ibanBien = true;
		String str1, str2;
		
		CuentaBancaria cb = new CuentaBancaria();
		cb.setBanco(Integer.valueOf(jtfnfBanco.getText()));
		cb.setSucursal(jtfnfSucursal.getText());
		cb.setCuenta(jtfnfCuenta.getText());
		cb.setDigitosControl(jtfnfDigitosControl.getText());
		cb.calcularIban();
		
		str1 = jtffIban.getText();
		str2 = cb.getIbanPais() + cb.getIbanCodigoControl();
		if(!str1.equalsIgnoreCase(str2)){
			JOptionPane.showMessageDialog(null, "<html><font size='4'><strong>" + 
					"IBAN incorrecto!!!<br>IBAN correcto: " + str2 + 
			"</strong></font></html>");
		    ibanBien = false;
		}
		
		return ibanBien;
	}
	
	private void cargaPorCodigo(int codigo){
		String strSql = "SELECT * FROM BANCOS WHERE EMPRESA = '" + 
		DatosComunes.eEmpresa + 
		"' AND BANCOS_BANCO = " + codigo;

		if(DatosComunes.centroCont != 0)
			strSql += " AND BANCOS_CENTRO = " + DatosComunes.centroCont;			 	       


		if(BaseDatos.countRows(strSql) == 0){
			if(!enCreacion){
				JOptionPane.showMessageDialog(null, "<html><font size='4'><strong>" + 
						"Banco inexistente!!!" + 
				"</strong></font></html>");
				
				enCreacion = true;
			}
			borrarPantalla();
			jtfnfCodigo.setText(String.valueOf(codigo));
			
		}else{
			enCreacion = false;
			cargaDatos(strSql);

			Cuenta cuenta = new Cuenta();
			cuenta.setCentro(DatosComunes.centroCont);
			if(!cuenta.existenCuentasSuperiores("572" + Cadena.enteroCerosIzquierda(Integer.valueOf(jtfnfCodigo.getText()), 6)))
				JOptionPane.showMessageDialog(null, "<html><font size='4'><strong>" + 
						"Problema con cuentas superiores 572!!!" + 
				"</strong></font></html>");
		}
	}
	
	private void salir(){
		pantalla.dispose();
		frameMenu.setEnabled(true);		
	}
}

