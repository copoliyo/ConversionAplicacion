package general;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.Border;


import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import util.Apariencia;
import util.Cadena;
import util.JOptionPaneConTimeOut;
import util.JTextFieldFijo;
import util.JTextFieldNumero2Decimales;

public class MantenimientoSistem extends JFrame {

	public JFrame frameMenu = null;
	private JTabbedPane tabbedSistem;
	private JPanel panelDatosIdentificacion;
	private JPanel panelDatosContables;
	private JPanel panelDatosConfiguracion;
	
	JTextFieldNumero2Decimales tf2dPrueba, tfIvaReducido, tfIvaGeneral,
			tfIvaSuperReducido, tfIvaREA, tfReReducido, tfReGeneral,
			tfReSuperReducido, tfReREA;

	// Datos identificacion
	JLabel lNombre, lNombreComercial, lDireccion, lCpPoblacion, lRegistro,
			lTelefono, lLogotipo, lNif, lEmpresaComp, lAccesoComp, lLogoLin,
			lLogoCol, lLicencia, lVersion, lFechaVersion, lEmpresaCentral,
			lQuienAbreB, lUltimaEntrada, lSistemaOperativo, lEanEmpresa,
			lGrupo, lComplenRotos, lClienteGrupo;
	JTextField tfNombre, tfNombreComercial, tfDireccion, tfCpPoblacion,
			tfRegistro, tfTelefono, tfLogotipo, tfNif, tfEmpresaComp,
			tfAccesoComp, tfLogoLin, tfLogoCol, tfLicencia, tfVersion,
			tfFechaVersion, tfEmpresaCentral, tfQuienAbreB, tfUltimaEntrada,
			tfSistemaOperativo, tfEanEmpresa, tfGrupo, tfComplenRotos,
			tfClienteGrupo;
	JCheckBox jcLogoBd, jcAbiertaB, jcClitesClient;
	JList jlTipoEmpresa;
	JScrollPane jspTipoEmpresa;

	// Datos contables
	JLabel lUltimoCierre, lUltimaRegularizacionProvisional,
			lUltimaDepuracionMovimientos, lUltimoInputMovimientos,
			lUltimaDeclaracionIva, lUltimoCierreFacturacion, lPeriodoIva,
			lUltimoDocumento, lCabeceraIva, lCabeceraRE, lCabeceraOrden,
			lReducido, lGeneral, lSuperReducido, lREA, lCabeceraTextosFDP;
	JTextField tfUltimoCierre, tfUltimaRegularizacionProvisional,
			tfUltimaDepuracionMovimientos, tfUltimoInputMovimientos,
			tfUltimaDeclaracionIva, tfUltimoCierreFacturacion,
			tfUltimoDocumento, tfOrdenReducido, tfOrdenGeneral,
			tfOrdenSuperReducido, tfOrdenREA, tfTfdp1, tfTfdp2, tfTfdp3,
			tfTfdp4, tfTfdp5, tfTfdp6, tfTfdp7;
	JCheckBox jcRecargoEqvCompra;
	JRadioButton jrbMensual, jrbTrimstral;
	ButtonGroup bgPeriodoIva;
	Border raisedbevel;

	// Datos configuracion
	JCheckBox jcUnSoloPmc, jcProduccion, jcPedidosCliente, jcAdmiteRotura,
			jcPosibleDejarB, jcChequearDtos, jcSeFra, jcPreciosPedido,
			jcFormatoEdi, jcPedidosProveedor, jcDispAlm72h, jcAlturaStDena,
			jcGescep50, jcCabFacAlb, jcCabRecibo, jcCabContinuom, jcFrasPdf,
			jcPrintFpagoAlb, jcImprimir2Dtos, jcImprimirEan, jcLineasAlbsB,
			jcPrintTicket, jcCliVarH10200, jcPrecRecomen, jcPrecCesion,
			jcActuaPrecioInput, jcArtSinRec, jcSQL, jcUbicaciones,
			jcMapaUbicaciones, jcUbicaFijas;
	JLabel lCentroACentral, lPosibAnulAlb, lDiasAplzam, lImprimirAlbPor,
			lSubCtaInterna, lNroDecUnid, lRsvCompCn21, lTarifaBase,
			lFormMinMax;
	JTextField tfCentroACentral, tfPosibAnulAlb, tfDiasAplzam,
			tfImprimirAlbPor, tfSubCtaInterna, tfNroDecUnid, tfRsvCompCn21,
			tfTarifaBase, tfFormMinMax;

	// Constructor
	public MantenimientoSistem(JFrame parentFrame) {

		frameMenu = parentFrame;
		

		setTitle("Mantenimiento Sistem");
		setSize(800, 400);
		setBackground(Color.gray);

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		getContentPane().add(topPanel);

		// Creamos cada página del Tab
		crearDatosIdentificacion();
		crearDatosContables();
		crearDatosConfiguracion();

		// Creamos el panel con las etiquetas
		tabbedSistem = new JTabbedPane();
		tabbedSistem.addTab("Datos Identificación", panelDatosIdentificacion);
		tabbedSistem.addTab("Datos Contables", panelDatosContables);
		tabbedSistem.addTab("Datos Configuración", panelDatosConfiguracion);
		topPanel.add(tabbedSistem, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				frameMenu.setVisible(true);
				dispose();
			}

		});
		this.setVisible(true);
	}

	public void crearDatosIdentificacion() {
		panelDatosIdentificacion = new JPanel();
		panelDatosIdentificacion.setLayout(null);

		JButton bGrabar, bSalir;

		lNombre = new JLabel("Nombre:");
		lNombre.setBounds(10, 15, 190, 20);
		panelDatosIdentificacion.add(lNombre);

		tfNombre = new JTextField();
		tfNombre.setBounds(150, 15, 300, 20);
		panelDatosIdentificacion.add(tfNombre);

		lNombreComercial = new JLabel("Nombre Comercial:");
		lNombreComercial.setBounds(10, 40, 190, 20);
		panelDatosIdentificacion.add(lNombreComercial);

		tfNombreComercial = new JTextField();
		tfNombreComercial.setBounds(150, 40, 300, 20);
		panelDatosIdentificacion.add(tfNombreComercial);

		lDireccion = new JLabel("Dirección:");
		lDireccion.setBounds(10, 65, 190, 20);
		panelDatosIdentificacion.add(lDireccion);

		tfDireccion = new JTextField();
		tfDireccion.setBounds(150, 65, 300, 20);
		panelDatosIdentificacion.add(tfDireccion);

		lCpPoblacion = new JLabel("Cp. Población:");
		lCpPoblacion.setBounds(10, 90, 190, 20);
		panelDatosIdentificacion.add(lCpPoblacion);

		tfCpPoblacion = new JTextField();
		tfCpPoblacion.setBounds(150, 90, 300, 20);
		panelDatosIdentificacion.add(tfCpPoblacion);

		lRegistro = new JLabel("Registro Mercantil:");
		lRegistro.setBounds(10, 115, 190, 20);
		panelDatosIdentificacion.add(lRegistro);

		tfRegistro = new JTextField();
		tfRegistro.setBounds(150, 115, 400, 20);
		panelDatosIdentificacion.add(tfRegistro);

		lTelefono = new JLabel("Teléfono:");
		lTelefono.setBounds(10, 140, 190, 20);
		panelDatosIdentificacion.add(lTelefono);

		tfTelefono = new JTextField();
		tfTelefono.setBounds(150, 140, 150, 20);
		panelDatosIdentificacion.add(tfTelefono);

		lLogotipo = new JLabel("Logotipo:");
		lLogotipo.setBounds(10, 165, 190, 20);
		panelDatosIdentificacion.add(lLogotipo);

		tfLogotipo = new JTextField();
		tfLogotipo.setBounds(150, 165, 150, 20);
		panelDatosIdentificacion.add(tfLogotipo);

		lNif = new JLabel("Nif:");
		lNif.setBounds(10, 190, 190, 20);
		panelDatosIdentificacion.add(lNif);

		tfNif = new JTextField();
		tfNif.setBounds(150, 190, 150, 20);
		panelDatosIdentificacion.add(tfNif);

		lEmpresaComp = new JLabel("Empresa Comp.:");
		lEmpresaComp.setBounds(10, 215, 190, 20);
		panelDatosIdentificacion.add(lEmpresaComp);

		tfEmpresaComp = new JTextField();
		tfEmpresaComp.setBounds(150, 215, 150, 20);
		panelDatosIdentificacion.add(tfEmpresaComp);

		lAccesoComp = new JLabel("Acceso Comp.:");
		lAccesoComp.setBounds(10, 240, 190, 20);
		panelDatosIdentificacion.add(lAccesoComp);

		tfAccesoComp = new JTextField();
		tfAccesoComp.setBounds(150, 240, 30, 20);
		panelDatosIdentificacion.add(tfAccesoComp);

		// //// Quitar

		tf2dPrueba = new JTextFieldNumero2Decimales();
		tf2dPrueba.setBounds(150, 265, 100, 20);
		panelDatosIdentificacion.add(tf2dPrueba);
		tf2dPrueba.setText("12345.00");
		// //// Hasta aqui

		lLogoLin = new JLabel("Logo Lin.:");
		lLogoLin.setBounds(500, 15, 190, 20);
		panelDatosIdentificacion.add(lLogoLin);

		tfLogoLin = new JTextField();
		tfLogoLin.setBounds(570, 15, 30, 20);
		panelDatosIdentificacion.add(tfLogoLin);

		lLogoCol = new JLabel("Col.:");
		lLogoCol.setBounds(640, 15, 190, 20);
		panelDatosIdentificacion.add(lLogoCol);

		tfLogoCol = new JTextField();
		tfLogoCol.setBounds(680, 15, 30, 20);
		panelDatosIdentificacion.add(tfLogoCol);

		lLicencia = new JLabel("Licencia:");
		lLicencia.setBounds(590, 65, 190, 20);
		panelDatosIdentificacion.add(lLicencia);

		tfLicencia = new JTextField();
		tfLicencia.setBounds(650, 65, 110, 20);
		panelDatosIdentificacion.add(tfLicencia);

		lVersion = new JLabel("Versión:");
		lVersion.setBounds(590, 90, 190, 20);
		panelDatosIdentificacion.add(lVersion);

		tfVersion = new JTextField();
		tfVersion.setBounds(650, 90, 110, 20);
		panelDatosIdentificacion.add(tfVersion);

		lFechaVersion = new JLabel("Fecha Versión:");
		lFechaVersion.setBounds(560, 115, 190, 20);
		panelDatosIdentificacion.add(lFechaVersion);

		tfFechaVersion = new JTextField();
		tfFechaVersion.setBounds(650, 115, 110, 20);
		panelDatosIdentificacion.add(tfFechaVersion);

		lEmpresaCentral = new JLabel("Empresa Central:");
		lEmpresaCentral.setBounds(330, 140, 190, 20);
		panelDatosIdentificacion.add(lEmpresaCentral);

		tfEmpresaCentral = new JTextField();
		tfEmpresaCentral.setBounds(450, 140, 30, 20);
		panelDatosIdentificacion.add(tfEmpresaCentral);

		lQuienAbreB = new JLabel("Quien Abre B:");
		lQuienAbreB.setBounds(330, 165, 190, 20);
		panelDatosIdentificacion.add(lQuienAbreB);

		tfQuienAbreB = new JTextField();
		tfQuienAbreB.setBounds(450, 165, 100, 20);
		panelDatosIdentificacion.add(tfQuienAbreB);

		lUltimaEntrada = new JLabel("Ult. Entrada Equipo:");
		lUltimaEntrada.setBounds(330, 190, 190, 20);
		panelDatosIdentificacion.add(lUltimaEntrada);

		tfUltimaEntrada = new JTextField();
		tfUltimaEntrada.setBounds(450, 190, 100, 20);
		panelDatosIdentificacion.add(tfUltimaEntrada);

		lSistemaOperativo = new JLabel("Sist.Operativo:");
		lSistemaOperativo.setBounds(330, 215, 190, 20);
		panelDatosIdentificacion.add(lSistemaOperativo);

		tfSistemaOperativo = new JTextField();
		tfSistemaOperativo.setBounds(450, 215, 100, 20);
		panelDatosIdentificacion.add(tfSistemaOperativo);

		lEanEmpresa = new JLabel("EAN empresa:");
		lEanEmpresa.setBounds(330, 240, 190, 20);
		panelDatosIdentificacion.add(lEanEmpresa);

		tfEanEmpresa = new JTextField();
		tfEanEmpresa.setBounds(450, 240, 100, 20);
		panelDatosIdentificacion.add(tfEanEmpresa);

		lGrupo = new JLabel("Grupo:");
		lGrupo.setBounds(330, 265, 190, 20);
		panelDatosIdentificacion.add(lGrupo);

		tfGrupo = new JTextField();
		tfGrupo.setBounds(450, 265, 100, 20);
		panelDatosIdentificacion.add(tfGrupo);

		lComplenRotos = new JLabel("Comp. rotos:");
		lComplenRotos.setBounds(570, 240, 190, 20);
		panelDatosIdentificacion.add(lComplenRotos);

		tfComplenRotos = new JTextField();
		tfComplenRotos.setBounds(650, 240, 100, 20);
		panelDatosIdentificacion.add(tfComplenRotos);

		lClienteGrupo = new JLabel("Clte.Grupo:");
		lClienteGrupo.setBounds(570, 265, 190, 20);
		panelDatosIdentificacion.add(lClienteGrupo);

		tfClienteGrupo = new JTextField();
		tfClienteGrupo.setBounds(650, 265, 100, 20);
		panelDatosIdentificacion.add(tfClienteGrupo);		

		jcLogoBd = new JCheckBox("Logo Bd");
		jcLogoBd.setBounds(570, 40, 100, 20);
		panelDatosIdentificacion.add(jcLogoBd);

		jcAbiertaB = new JCheckBox("AbiertaFactB");
		jcAbiertaB.setBounds(680, 215, 100, 20);
		panelDatosIdentificacion.add(jcAbiertaB);

		jcClitesClient = new JCheckBox("Clites->Client");
		jcClitesClient.setBounds(570, 215, 100, 20);
		panelDatosIdentificacion.add(jcClitesClient);

		DefaultListModel listModel = new DefaultListModel();
		listModel.addElement("Empresa Normal");
		listModel.addElement("Tienda");
		listModel.addElement("Empresa Secundaria");
		listModel.addElement("Tienda Propia");

		jlTipoEmpresa = new JList(listModel);
		jlTipoEmpresa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jspTipoEmpresa = new JScrollPane(jlTipoEmpresa);
		// jspTipoEmpresa.setPreferredSize(new Dimension(250, 80));
		jspTipoEmpresa.setBounds(570, 140, 150, 60);
		panelDatosIdentificacion.add(jspTipoEmpresa);
				
		// Ponemos el botón de grabar
		bGrabar = new JButton("Grabar");
		bGrabar.setBounds(630, 300, 100, 20);

		ActionListener grabarListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// The component parameter must be declared "final" so that it
				// can be
				// referenced in the anonymous listener class like this.
				grabarDatosIdentificacion();
			}
		};
		bGrabar.addActionListener(grabarListener);
		panelDatosIdentificacion.add(bGrabar);

		// Ponemos el botón de salir
		bSalir = new JButton("Salir");
		bSalir.setBounds(100, 300, 100, 30);

		ActionListener salirListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// The component parameter must be declared "final" so that it
				// can be
				// referenced in the anonymous listener class like this.
				frameMenu.setVisible(true);
				dispose();
				// JOptionPane.showMessageDialog(null, "Has pinchado en SALIR");
			}
		};
		bSalir.addActionListener(salirListener);
		panelDatosIdentificacion.add(bSalir);

		leerDatosIdentificacion();

	}

	public void crearDatosContables() {
		panelDatosContables = new JPanel();
		panelDatosContables.setLayout(null);

		JButton bGrabar, bSalir;

		Border bordeDatosContables = BorderFactory
				.createTitledBorder("Datos contables");

		JPanel bordeCompuesto = new JPanel();
		bordeCompuesto.setBorder(bordeDatosContables);
		bordeCompuesto.setLayout(null);
		bordeCompuesto.setBounds(10, 10, 200, 200);
		panelDatosContables.add(bordeCompuesto);

		lUltimoCierre = new JLabel("Fec.Ult.Cierre:");
		lUltimoCierre.setBounds(20, 40, 90, 20);
		bordeCompuesto.add(lUltimoCierre);

		tfUltimoCierre = new JTextField();
		tfUltimoCierre.setBounds(110, 40, 70, 20);
		bordeCompuesto.add(tfUltimoCierre);

		lUltimaRegularizacionProvisional = new JLabel("Ult.Reg.Pro.");
		lUltimaRegularizacionProvisional.setBounds(20, 65, 90, 20);
		bordeCompuesto.add(lUltimaRegularizacionProvisional);

		tfUltimaRegularizacionProvisional = new JTextField();
		tfUltimaRegularizacionProvisional.setBounds(110, 65, 70, 20);
		bordeCompuesto.add(tfUltimaRegularizacionProvisional);

		lUltimaDepuracionMovimientos = new JLabel("Ult.Dep.Mov.");
		lUltimaDepuracionMovimientos.setBounds(20, 90, 90, 20);
		bordeCompuesto.add(lUltimaDepuracionMovimientos);

		tfUltimaDepuracionMovimientos = new JTextField();
		tfUltimaDepuracionMovimientos.setBounds(110, 90, 70, 20);
		bordeCompuesto.add(tfUltimaDepuracionMovimientos);

		lUltimoInputMovimientos = new JLabel("Ult.Inp.Mov.");
		lUltimoInputMovimientos.setBounds(20, 115, 90, 20);
		bordeCompuesto.add(lUltimoInputMovimientos);

		tfUltimoInputMovimientos = new JTextField();
		tfUltimoInputMovimientos.setBounds(110, 115, 70, 20);
		bordeCompuesto.add(tfUltimoInputMovimientos);

		lUltimaDeclaracionIva = new JLabel("Ult.Dec.IVA.");
		lUltimaDeclaracionIva.setBounds(20, 140, 90, 20);
		bordeCompuesto.add(lUltimaDeclaracionIva);

		tfUltimaDeclaracionIva = new JTextField();
		tfUltimaDeclaracionIva.setBounds(110, 140, 70, 20);
		bordeCompuesto.add(tfUltimaDeclaracionIva);

		lUltimoCierreFacturacion = new JLabel("Ult.Cierre Fact.");
		lUltimoCierreFacturacion.setBounds(20, 165, 90, 20);
		bordeCompuesto.add(lUltimoCierreFacturacion);

		tfUltimoCierreFacturacion = new JTextField();
		tfUltimoCierreFacturacion.setBounds(110, 165, 70, 20);
		bordeCompuesto.add(tfUltimoCierreFacturacion);

		lPeriodoIva = new JLabel("Periodo I.V.A.");
		lPeriodoIva.setBounds(20, 215, 90, 20);
		panelDatosContables.add(lPeriodoIva);

		jrbMensual = new JRadioButton("Mensual");
		jrbMensual.setBounds(110, 215, 90, 20);
		panelDatosContables.add(jrbMensual);

		jrbTrimstral = new JRadioButton("Trimestral");
		jrbTrimstral.setBounds(110, 240, 90, 20);
		panelDatosContables.add(jrbTrimstral);

		bgPeriodoIva = new ButtonGroup();
		bgPeriodoIva.add(jrbMensual);
		bgPeriodoIva.add(jrbTrimstral);

		jcRecargoEqvCompra = new JCheckBox("Reqv.Compra");
		jcRecargoEqvCompra.setBounds(110, 265, 120, 20);
		panelDatosContables.add(jcRecargoEqvCompra);

		lUltimoDocumento = new JLabel("Ultimo Doc.");
		lUltimoDocumento.setBounds(220, 20, 90, 20);
		panelDatosContables.add(lUltimoDocumento);

		tfUltimoDocumento = new JTextField();
		tfUltimoDocumento.setBounds(310, 20, 70, 20);
		panelDatosContables.add(tfUltimoDocumento);

		lCabeceraTextosFDP = new JLabel("Textos FDP");
		lCabeceraTextosFDP.setBounds(220, 70, 90, 20);
		panelDatosContables.add(lCabeceraTextosFDP);

		tfTfdp1 = new JTextField();
		tfTfdp1.setBounds(310, 70, 90, 20);
		panelDatosContables.add(tfTfdp1);

		tfTfdp2 = new JTextField();
		tfTfdp2.setBounds(310, 95, 90, 20);
		panelDatosContables.add(tfTfdp2);

		tfTfdp3 = new JTextField();
		tfTfdp3.setBounds(310, 120, 90, 20);
		panelDatosContables.add(tfTfdp3);

		tfTfdp4 = new JTextField();
		tfTfdp4.setBounds(310, 145, 90, 20);
		panelDatosContables.add(tfTfdp4);

		tfTfdp5 = new JTextField();
		tfTfdp5.setBounds(310, 170, 90, 20);
		panelDatosContables.add(tfTfdp5);

		tfTfdp6 = new JTextField();
		tfTfdp6.setBounds(310, 195, 90, 20);
		panelDatosContables.add(tfTfdp6);

		tfTfdp7 = new JTextField();
		tfTfdp7.setBounds(310, 220, 90, 20);
		panelDatosContables.add(tfTfdp7);

		lCabeceraIva = new JLabel("I.V.A.");
		lCabeceraIva.setBounds(570, 45, 90, 20);
		panelDatosContables.add(lCabeceraIva);

		lCabeceraRE = new JLabel("Requ. Eq.");
		lCabeceraRE.setBounds(640, 45, 90, 20);
		panelDatosContables.add(lCabeceraRE);

		lCabeceraOrden = new JLabel("Orden");
		lCabeceraOrden.setBounds(720, 45, 90, 20);
		panelDatosContables.add(lCabeceraOrden);

		lReducido = new JLabel("Reducido");
		lReducido.setBounds(450, 70, 90, 20);
		panelDatosContables.add(lReducido);

		lGeneral = new JLabel("General");
		lGeneral.setBounds(450, 95, 90, 20);
		panelDatosContables.add(lGeneral);

		lSuperReducido = new JLabel("Super reduc.");
		lSuperReducido.setBounds(450, 120, 90, 20);
		panelDatosContables.add(lSuperReducido);

		lREA = new JLabel("Rég.Esp.Agra.");
		lREA.setBounds(450, 145, 90, 20);
		panelDatosContables.add(lREA);

		tfIvaReducido = new JTextFieldNumero2Decimales();
		tfIvaReducido.setBounds(560, 70, 60, 20);
		panelDatosContables.add(tfIvaReducido);

		tfIvaGeneral = new JTextFieldNumero2Decimales();
		tfIvaGeneral.setBounds(560, 95, 60, 20);
		panelDatosContables.add(tfIvaGeneral);

		tfIvaSuperReducido = new JTextFieldNumero2Decimales();
		tfIvaSuperReducido.setBounds(560, 120, 60, 20);
		panelDatosContables.add(tfIvaSuperReducido);

		tfIvaREA = new JTextFieldNumero2Decimales();
		tfIvaREA.setBounds(560, 145, 60, 20);
		panelDatosContables.add(tfIvaREA);

		tfReReducido = new JTextFieldNumero2Decimales();
		tfReReducido.setBounds(640, 70, 60, 20);
		panelDatosContables.add(tfReReducido);

		tfReGeneral = new JTextFieldNumero2Decimales();
		tfReGeneral.setBounds(640, 95, 60, 20);
		panelDatosContables.add(tfReGeneral);

		tfReSuperReducido = new JTextFieldNumero2Decimales();
		tfReSuperReducido.setBounds(640, 120, 60, 20);
		panelDatosContables.add(tfReSuperReducido);

		tfReREA = new JTextFieldNumero2Decimales();
		tfReREA.setBounds(640, 145, 60, 20);
		panelDatosContables.add(tfReREA);

		tfOrdenReducido = new JTextField();
		tfOrdenReducido.setBounds(720, 70, 40, 20);
		panelDatosContables.add(tfOrdenReducido);

		tfOrdenGeneral = new JTextField();
		tfOrdenGeneral.setBounds(720, 95, 40, 20);
		panelDatosContables.add(tfOrdenGeneral);

		tfOrdenSuperReducido = new JTextField();
		tfOrdenSuperReducido.setBounds(720, 120, 40, 20);
		panelDatosContables.add(tfOrdenSuperReducido);

		tfOrdenREA = new JTextField();
		tfOrdenREA.setBounds(720, 145, 40, 20);
		panelDatosContables.add(tfOrdenREA);

		// Ponemos el botón de grabar
		bGrabar = new JButton("Grabar");
		bGrabar.setBounds(630, 300, 100, 30);

		ActionListener grabarListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// The component parameter must be declared "final" so that it
				// can be
				// referenced in the anonymous listener class like this.
				grabarDatosContables();
			}
		};
		bGrabar.addActionListener(grabarListener);
		panelDatosContables.add(bGrabar);

		// Ponemos el botón de salir
		bSalir = new JButton("Salir");
		bSalir.setBounds(100, 300, 100, 30);

		ActionListener salirListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// The component parameter must be declared "final" so that it
				// can be
				// referenced in the anonymous listener class like this.
				frameMenu.setVisible(true);
				dispose();
				// JOptionPane.showMessageDialog(null, "Has pinchado en SALIR");
			}
		};
		bSalir.addActionListener(salirListener);
		panelDatosContables.add(bSalir);

		leerDatosContables();
	}

	public void crearDatosConfiguracion() {
		panelDatosConfiguracion = new JPanel();
		panelDatosConfiguracion.setLayout(null);

		JButton bGrabar, bSalir;

		Border bordeDatosConfiguracion = BorderFactory
				.createTitledBorder("Datos configuración");

		JPanel bordeCompuesto = new JPanel();
		bordeCompuesto.setBorder(bordeDatosConfiguracion);
		bordeCompuesto.setLayout(null);
		bordeCompuesto.setBounds(10, 5, 775, 295);
		panelDatosConfiguracion.add(bordeCompuesto);

		// Columna 1
		jcUnSoloPmc = new JCheckBox("Un solo PMC");
		jcUnSoloPmc.setBounds(10, 20, 150, 20);
		bordeCompuesto.add(jcUnSoloPmc);

		jcProduccion = new JCheckBox("Producción");
		jcProduccion.setBounds(10, 45, 150, 20);
		bordeCompuesto.add(jcProduccion);

		lCentroACentral = new JLabel("Centro a Central");
		lCentroACentral.setBounds(10, 70, 100, 20);
		bordeCompuesto.add(lCentroACentral);

		tfCentroACentral = new JTextField();
		tfCentroACentral.setBounds(120, 70, 30, 20);
		bordeCompuesto.add(tfCentroACentral);

		jcPedidosCliente = new JCheckBox("Ped.Clientes");
		jcPedidosCliente.setBounds(10, 95, 150, 20);
		bordeCompuesto.add(jcPedidosCliente);

		jcAdmiteRotura = new JCheckBox("Admite Rotura");
		jcAdmiteRotura.setBounds(10, 120, 150, 20);
		bordeCompuesto.add(jcAdmiteRotura);

		lPosibAnulAlb = new JLabel("Posib.Anular Alb.");
		lPosibAnulAlb.setBounds(10, 145, 100, 20);
		bordeCompuesto.add(lPosibAnulAlb);

		tfPosibAnulAlb = new JTextField();
		tfPosibAnulAlb.setBounds(120, 145, 30, 20);
		bordeCompuesto.add(tfPosibAnulAlb);

		jcPosibleDejarB = new JCheckBox("Posib DejarB");
		jcPosibleDejarB.setBounds(10, 170, 150, 20);
		bordeCompuesto.add(jcPosibleDejarB);

		jcChequearDtos = new JCheckBox("Chequear Dtos.");
		jcChequearDtos.setBounds(10, 195, 150, 20);
		bordeCompuesto.add(jcChequearDtos);

		jcSeFra = new JCheckBox("Se-Fra/N0-Fra");
		jcSeFra.setBounds(10, 220, 150, 20);
		bordeCompuesto.add(jcSeFra);

		// Columna 2

		jcPreciosPedido = new JCheckBox("Precios Pedido");
		jcPreciosPedido.setBounds(170, 20, 150, 20);
		bordeCompuesto.add(jcPreciosPedido);

		lDiasAplzam = new JLabel("Días Aplazamiento");
		lDiasAplzam.setBounds(170, 45, 110, 20);
		bordeCompuesto.add(lDiasAplzam);

		tfDiasAplzam = new JTextField();
		tfDiasAplzam.setBounds(290, 45, 30, 20);
		bordeCompuesto.add(tfDiasAplzam);

		jcFormatoEdi = new JCheckBox("Precios Pedido");
		jcFormatoEdi.setBounds(170, 70, 150, 20);
		bordeCompuesto.add(jcFormatoEdi);

		jcPedidosProveedor = new JCheckBox("Pedidos Proveedor");
		jcPedidosProveedor.setBounds(170, 95, 150, 20);
		bordeCompuesto.add(jcPedidosProveedor);

		jcDispAlm72h = new JCheckBox("Disp.Alm.72h.");
		jcDispAlm72h.setBounds(170, 120, 150, 20);
		bordeCompuesto.add(jcDispAlm72h);

		jcAlturaStDena = new JCheckBox("Altura St.Dena");
		jcAlturaStDena.setBounds(170, 145, 150, 20);
		bordeCompuesto.add(jcAlturaStDena);

		jcGescep50 = new JCheckBox("Gescep de 50");
		jcGescep50.setBounds(170, 170, 150, 20);
		bordeCompuesto.add(jcGescep50);

		jcCabFacAlb = new JCheckBox("Cabecera Fact/Alb");
		jcCabFacAlb.setBounds(170, 195, 150, 20);
		bordeCompuesto.add(jcCabFacAlb);

		jcCabRecibo = new JCheckBox("Cabecera Recibo");
		jcCabRecibo.setBounds(170, 220, 150, 20);
		bordeCompuesto.add(jcCabRecibo);

		jcCabContinuom = new JCheckBox("Cabecera Continuo");
		jcCabContinuom.setBounds(170, 245, 150, 20);
		bordeCompuesto.add(jcCabContinuom);

		jcFrasPdf = new JCheckBox("Facturas en PDF");
		jcFrasPdf.setBounds(170, 270, 150, 20);
		bordeCompuesto.add(jcFrasPdf);

		// Columna 3

		jcPrintFpagoAlb = new JCheckBox("Print FDP Albarán");
		jcPrintFpagoAlb.setBounds(350, 20, 150, 20);
		bordeCompuesto.add(jcPrintFpagoAlb);

		jcImprimir2Dtos = new JCheckBox("Impreso 2Dtos");
		jcImprimir2Dtos.setBounds(350, 45, 150, 20);
		bordeCompuesto.add(jcImprimir2Dtos);

		jcImprimirEan = new JCheckBox("Imprimir EAN");
		jcImprimirEan.setBounds(350, 70, 150, 20);
		bordeCompuesto.add(jcImprimirEan);

		lImprimirAlbPor = new JLabel("Imprimir.Alb. x");
		lImprimirAlbPor.setBounds(350, 95, 100, 20);
		bordeCompuesto.add(lImprimirAlbPor);

		tfImprimirAlbPor = new JTextField();
		tfImprimirAlbPor.setBounds(460, 95, 50, 20);
		bordeCompuesto.add(tfImprimirAlbPor);

		jcLineasAlbsB = new JCheckBox("Lineas AlbsB");
		jcLineasAlbsB.setBounds(350, 120, 150, 20);
		bordeCompuesto.add(jcLineasAlbsB);

		jcPrintTicket = new JCheckBox("Print Ticket");
		jcPrintTicket.setBounds(350, 145, 150, 20);
		bordeCompuesto.add(jcPrintTicket);

		lSubCtaInterna = new JLabel("Subcta.Interna");
		lSubCtaInterna.setBounds(350, 170, 100, 20);
		bordeCompuesto.add(lSubCtaInterna);

		tfSubCtaInterna = new JTextField();
		tfSubCtaInterna.setBounds(460, 170, 30, 20);
		bordeCompuesto.add(tfSubCtaInterna);

		jcCliVarH10200 = new JCheckBox("CliVarH10200");
		jcCliVarH10200.setBounds(350, 195, 150, 20);
		bordeCompuesto.add(jcCliVarH10200);

		lNroDecUnid = new JLabel("Nro.Dec.Unid.");
		lNroDecUnid.setBounds(350, 245, 100, 20);
		bordeCompuesto.add(lNroDecUnid);

		tfNroDecUnid = new JTextField();
		tfNroDecUnid.setBounds(460, 245, 30, 20);
		bordeCompuesto.add(tfNroDecUnid);

		lRsvCompCn21 = new JLabel("%Rsv.Comp.Cn1");
		lRsvCompCn21.setBounds(350, 270, 100, 20);
		bordeCompuesto.add(lRsvCompCn21);

		tfRsvCompCn21 = new JTextField();
		tfRsvCompCn21.setBounds(460, 270, 30, 20);
		bordeCompuesto.add(tfRsvCompCn21);

		// Columna 4

		jcPrecRecomen = new JCheckBox("Prec.Recomendado");
		jcPrecRecomen.setBounds(550, 20, 150, 20);
		bordeCompuesto.add(jcPrecRecomen);

		jcPrecCesion = new JCheckBox("Prec.Cesión");
		jcPrecCesion.setBounds(550, 45, 150, 20);
		bordeCompuesto.add(jcPrecCesion);

		lTarifaBase = new JLabel("Tarifa Base");
		lTarifaBase.setBounds(550, 70, 100, 20);
		bordeCompuesto.add(lTarifaBase);

		tfTarifaBase = new JTextField();
		tfTarifaBase.setBounds(660, 70, 30, 20);
		bordeCompuesto.add(tfTarifaBase);

		jcActuaPrecioInput = new JCheckBox("Act.Precio en Input");
		jcActuaPrecioInput.setBounds(550, 95, 150, 20);
		bordeCompuesto.add(jcActuaPrecioInput);

		jcArtSinRec = new JCheckBox("Art. sin Rec.");
		jcArtSinRec.setBounds(550, 120, 150, 20);
		bordeCompuesto.add(jcArtSinRec);

		lFormMinMax = new JLabel("Form. Min.Max.");
		lFormMinMax.setBounds(550, 145, 100, 20);
		bordeCompuesto.add(lFormMinMax);

		tfFormMinMax = new JTextField();
		tfFormMinMax.setBounds(660, 145, 30, 20);
		bordeCompuesto.add(tfFormMinMax);

		jcSQL = new JCheckBox("SQL");
		jcSQL.setBounds(550, 195, 150, 20);
		bordeCompuesto.add(jcSQL);

		jcUbicaciones = new JCheckBox("Ubicaciones");
		jcUbicaciones.setBounds(550, 220, 150, 20);
		bordeCompuesto.add(jcUbicaciones);

		jcMapaUbicaciones = new JCheckBox("Mapa Ubicaciones");
		jcMapaUbicaciones.setBounds(550, 245, 150, 20);
		bordeCompuesto.add(jcMapaUbicaciones);

		jcUbicaFijas = new JCheckBox("Ubicaciones Fijas");
		jcUbicaFijas.setBounds(550, 270, 150, 20);
		bordeCompuesto.add(jcUbicaFijas);

		// Ponemos el botón de grabar
		bGrabar = new JButton("Grabar");
		bGrabar.setBounds(630, 300, 100, 30);

		ActionListener grabarListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// The component parameter must be declared "final" so that it
				// can be
				// referenced in the anonymous listener class like this.
				grabarDatosConfiguracion();
			}
		};
		bGrabar.addActionListener(grabarListener);
		panelDatosConfiguracion.add(bGrabar);

		// Ponemos el botón de salir
		bSalir = new JButton("Salir");
		bSalir.setBounds(100, 300, 100, 30);

		ActionListener salirListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// The component parameter must be declared "final" so that it
				// can be
				// referenced in the anonymous listener class like this.
				frameMenu.setVisible(true);
				dispose();
				// JOptionPane.showMessageDialog(null, "Has pinchado en SALIR");
			}
		};
		bSalir.addActionListener(salirListener);
		panelDatosConfiguracion.add(bSalir);

		leerDatosConfiguracion();
	}

	void leerDatosIdentificacion() {
		// Necesitamos un ResulSet en el que dejar los datos
		// Hay que instanciar la conexión a la base de datos
		ResultSet rs = null;
		MysqlConnect m = null;
		m = MysqlConnect.getDbCon();

		String empresa = "MV";

		// Por si acaso la empresa siempre en mayúsculas
		empresa = empresa.toUpperCase();
		// Ejecutamos la consulta
		try {
			rs = m.query("SELECT * FROM SISTEM WHERE EMPRESA = '" + empresa
					+ "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"Error en lectura fichero de Sistema!!!");
		}

		try {
			boolean existeReg = false;

			while (rs.next() == true) {
				int auxInt = 0;

				existeReg = true;
				tfNombre.setText(rs.getString("SISTEM_NOMBRE"));
				tfNombreComercial.setText(rs.getString("SISTEM_NOM_COMER"));
				// .setText(rs.getString("SISTEM"));
				tfDireccion.setText(rs.getString("SISTEM_DIR"));
				tfCpPoblacion.setText(rs.getString("SISTEM_POB"));
				tfRegistro.setText(rs.getString("SISTEM_DATOS_REGISTRO"));
				tfTelefono.setText(rs.getString("SISTEM_TLFNO"));
				tfLogotipo.setText(rs.getString("SISTEM_LOGOTIPO"));
				tfNif.setText(rs.getString("SISTEM_NIF"));
				tfEmpresaComp.setText(rs.getString("SISTEM_EMPRESA_B"));
				tfAccesoComp.setText(rs.getString("SISTEM_ACCESO_BRJ"));
				tfLogoLin.setText(rs.getString("SISTEM_LIN_LOGO"));
				tfLogoCol.setText(rs.getString("SISTEM_COL_LOGO"));
				if (rs.getInt("SISTEM_LOGO_BD") == 1)
					jcLogoBd.setSelected(true);
				else
					jcLogoBd.setSelected(false);
				tfLicencia.setText(rs.getString("SISTEM_LICENCIA"));
				tfVersion.setText(rs.getString("SISTEM_VERSION"));

				tfFechaVersion.setText(Cadena.fechaAcadena(rs
						.getInt("SISTEM_FEC_VERS")));
				tfEmpresaCentral
						.setText(rs.getString("SISTEM_EMPRESA_CENTRAL"));
				tfQuienAbreB.setText(rs.getString("SISTEM_QIEN_ABRE_FACT_B"));
				tfUltimaEntrada.setText(Cadena.fechaAcadena(rs
						.getInt("SISTEM_FEC_ULT_ENTRADA")));
				tfSistemaOperativo.setText(rs
						.getString("SISTEM_SIST_OPERATIVO"));
				tfEanEmpresa.setText(rs.getString("SISTEM_EAN_EMPRESA"));
				tfGrupo.setText(rs.getString("SISTEM_GRUPO"));
				tfComplenRotos.setText(rs.getString("SISTEM_MASCOMPLEM_ROTOS"));
				tfClienteGrupo.setText(rs.getString("SISTEM_CLT_EN_GRUPO"));
				if (rs.getInt("SISTEM_ABIERTA_FACT_B") == 1)
					jcAbiertaB.setSelected(true);
				else
					jcAbiertaB.setSelected(false);

				if (rs.getInt("SISTEM_CLITES_CLIENT") == 1)
					jcClitesClient.setSelected(true);
				else
					jcClitesClient.setSelected(false);

				jlTipoEmpresa
						.setSelectedIndex(rs.getInt("SISTEM_TIPO_EMPRESA"));

				// System.out.println(Cadena.fechaAcadena(19710727));
				// System.out.println(Cadena.cadenaAfecha("27/07/71"));
			}

			if (existeReg == false)
				JOptionPane.showMessageDialog(null,
						"No existe la empresa, CREARLA!!!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error en lectura fichero de Sistema!!!");
			e.printStackTrace();
		}
	}

	void leerDatosContables() {
		// Necesitamos un ResulSet en el que dejar los datos
		// Hay que instanciar la conexión a la base de datos
		ResultSet rs = null;
		MysqlConnect m = null;
		m = MysqlConnect.getDbCon();

		String empresa = "MV";

		// Por si acaso la empresa siempre en mayúsculas
		empresa = empresa.toUpperCase();
		// Ejecutamos la consulta
		try {
			rs = m.query("SELECT * FROM SISTEM WHERE EMPRESA = '" + empresa
					+ "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			boolean existeReg = false;

			while (rs.next() == true) {
				int auxInt = 0;
				String auxStr = null;

				existeReg = true;

				tfUltimoCierre.setText(Cadena.fechaAcadena(rs
						.getInt("SISTCO_FEC_ULT_CIERRE")));
				tfUltimaRegularizacionProvisional.setText(Cadena
						.fechaAcadena(rs.getInt("SISTCO_FEC_ULT_REGPRO")));
				tfUltimaDepuracionMovimientos.setText(Cadena.fechaAcadena(rs
						.getInt("SISTCO_FEC_ULT_DEPMOC")));
				tfUltimoInputMovimientos.setText(Cadena.fechaAcadena(rs
						.getInt("SISTCO_FEC_ULT_INPUCON")));
				tfUltimaDeclaracionIva.setText(Cadena.fechaAcadena(rs
						.getInt("SISTCO_FEC_ULT_DECLIVA")));
				tfUltimoCierreFacturacion.setText(Cadena.fechaAcadena(rs
						.getInt("SISTCO_FEC_ULT_CIERRE_FAC")));
				tfUltimoDocumento.setText(rs.getString("SISTCO_ULT_DOCUM"));

				tfTfdp1.setText(rs.getString("SISTCO_TEXFDP_1"));
				tfTfdp2.setText(rs.getString("SISTCO_TEXFDP_2"));
				tfTfdp3.setText(rs.getString("SISTCO_TEXFDP_3"));
				tfTfdp4.setText(rs.getString("SISTCO_TEXFDP_4"));
				tfTfdp5.setText(rs.getString("SISTCO_TEXFDP_5"));
				tfTfdp6.setText(rs.getString("SISTCO_TEXFDP_6"));
				tfTfdp7.setText(rs.getString("SISTCO_TEXFDP_7"));

				tfIvaReducido.setText(rs.getString("SISTCO_POR_IVA_1"));
				tfIvaGeneral.setText(rs.getString("SISTCO_POR_IVA_2"));
				tfIvaSuperReducido.setText(rs.getString("SISTCO_POR_IVA_3"));
				tfIvaREA.setText(rs.getString("SISTCO_POR_IVA_4"));

				tfReReducido.setText(rs.getString("SISTCO_POR_REQU_1"));
				tfReGeneral.setText(rs.getString("SISTCO_POR_REQU_2"));
				tfReSuperReducido.setText(rs.getString("SISTCO_POR_REQU_3"));
				tfReREA.setText(rs.getString("SISTCO_POR_REQU_4"));

				auxStr = rs.getString("SISTCO_ORDEN_IVA");

				tfOrdenReducido.setText(auxStr.substring(0, 1));
				tfOrdenGeneral.setText(auxStr.substring(1, 2));
				tfOrdenSuperReducido.setText(auxStr.substring(2, 3));
				tfOrdenREA.setText(auxStr.substring(3, 4));

				if (rs.getInt("SISTCO_PERIODO_DECLIVA") == 3)
					jrbTrimstral.setSelected(true);
				else
					jrbMensual.setSelected(true);

				if (rs.getInt("SISTCO_COMPRAS_CONREQU") == 1)
					jcRecargoEqvCompra.setSelected(true);
				else
					jcRecargoEqvCompra.setSelected(false);

			}

			if (existeReg == false)
				JOptionPane.showMessageDialog(null,
						"No existe la empresa, CREARLA!!!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error en lectura fichero de Sistema (2)!!!");
			e.printStackTrace();
		}
	}

	void leerDatosConfiguracion() {
		// Necesitamos un ResulSet en el que dejar los datos
		// Hay que instanciar la conexión a la base de datos
		ResultSet rs = null;
		MysqlConnect m = null;
		m = MysqlConnect.getDbCon();

		String empresa = "MV";

		// Por si acaso la empresa siempre en mayúsculas
		empresa = empresa.toUpperCase();
		// Ejecutamos la consulta
		try {
			rs = m.query("SELECT * FROM SISTEM WHERE EMPRESA = '" + empresa
					+ "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			boolean existeReg = false;

			while (rs.next() == true) {
				int auxInt = 0;
				String auxStr = null;

				existeReg = true;

				if (rs.getInt("SISTPR_UN_SOLO_PMC") == 1)
					jcUnSoloPmc.setSelected(true);
				else
					jcUnSoloPmc.setSelected(false);

				if (rs.getInt("SISTPR_PRODUCC") == 1)
					jcProduccion.setSelected(true);
				else
					jcProduccion.setSelected(false);

				if (rs.getInt("SISTPR_PEDCLI") == 1)
					jcPedidosCliente.setSelected(true);
				else
					jcPedidosCliente.setSelected(false);

				if (rs.getInt("SISTPR_ADMITE_ROTURA_STOCK") == 1)
					jcAdmiteRotura.setSelected(true);
				else
					jcAdmiteRotura.setSelected(false);

				if (rs.getInt("SISTPR_POSIB_DEJAR_PNDT_FACT_B") == 1)
					jcPosibleDejarB.setSelected(true);
				else
					jcPosibleDejarB.setSelected(false);

				if (rs.getInt("SISTPR_CHK_OFR_DTOS") == 1)
					jcChequearDtos.setSelected(true);
				else
					jcChequearDtos.setSelected(false);

				if (rs.getInt("SISTPR_PEDCLI_NO_FRA") == 1)
					jcSeFra.setSelected(true);
				else
					jcSeFra.setSelected(false);

				if (rs.getInt("SISTPR_PRECIOS_DE_PEDIDO") == 1)
					jcPreciosPedido.setSelected(true);
				else
					jcPreciosPedido.setSelected(false);

				if (rs.getInt("SISTPR_EDI") == 1)
					jcFormatoEdi.setSelected(true);
				else
					jcFormatoEdi.setSelected(false);

				if (rs.getInt("SISTPR_PEDPRA") == 1)
					jcPedidosProveedor.setSelected(true);
				else
					jcPedidosProveedor.setSelected(false);

				/*
				 * Repasar!!! if (rs.getInt("SISTPR-DISP-72H") == 1)
				 * jcDispAlm72h.setSelected(true); else
				 * jcDispAlm72h.setSelected(false);
				 */

				if (rs.getInt("SISTPR_LINES_PAPELST") == 1)
					jcAlturaStDena.setSelected(true);
				else
					jcAlturaStDena.setSelected(false);

				if (rs.getInt("SISTPR_LONG_CEPTO") == 1)
					jcGescep50.setSelected(true);
				else
					jcGescep50.setSelected(false);

				if (rs.getInt("SISTPR_PERSON_FACALB") == 1)
					jcCabFacAlb.setSelected(true);
				else
					jcCabFacAlb.setSelected(false);

				if (rs.getInt("SISTPR_PERSON_RECIBO") == 1)
					jcCabRecibo.setSelected(true);
				else
					jcCabRecibo.setSelected(false);

				if (rs.getInt("SISTPR_PERSON_PAPEL") == 1)
					jcCabContinuom.setSelected(true);
				else
					jcCabContinuom.setSelected(false);

				if (rs.getInt("SISTPR_FRA_PDF") == 1)
					jcFrasPdf.setSelected(true);
				else
					jcFrasPdf.setSelected(false);

				if (rs.getInt("SISTPR_PRINT_FDP_ALB") == 1)
					jcPrintFpagoAlb.setSelected(true);
				else
					jcPrintFpagoAlb.setSelected(false);

				if (rs.getInt("SISTPR_PRINT_2DTOS") == 1)
					jcImprimir2Dtos.setSelected(true);
				else
					jcImprimir2Dtos.setSelected(false);

				if (rs.getInt("SISTPR_PRINT_DOC_EAN") == 1)
					jcImprimirEan.setSelected(true);
				else
					jcImprimirEan.setSelected(false);

				if (rs.getInt("SISTPR_LINES_ALB_B") == 1)
					jcLineasAlbsB.setSelected(true);
				else
					jcLineasAlbsB.setSelected(false);

				if (rs.getInt("SISTPR_PRINT_TICKET") == 1)
					jcPrintTicket.setSelected(true);
				else
					jcPrintTicket.setSelected(false);

				if (rs.getInt("SISTPR_CLIVAR_H10200") == 1)
					jcCliVarH10200.setSelected(true);
				else
					jcCliVarH10200.setSelected(false);

				if (rs.getInt("SISTPR_PRECIOS_RECOMEN") == 1)
					jcPrecRecomen.setSelected(true);
				else
					jcPrecRecomen.setSelected(false);

				if (rs.getInt("SISTPR_PRECIO_CESION") == 1)
					jcPrecCesion.setSelected(true);
				else
					jcPrecCesion.setSelected(false);

				if (rs.getInt("SISTPR_ACT_PREC_INP") == 1)
					jcActuaPrecioInput.setSelected(true);
				else
					jcActuaPrecioInput.setSelected(false);

				if (rs.getInt("SISTPR_ART_SIN_REQU") == 1)
					jcArtSinRec.setSelected(true);
				else
					jcArtSinRec.setSelected(false);

				if (rs.getInt("SISTPR_SQL") == 1)
					jcSQL.setSelected(true);
				else
					jcSQL.setSelected(false);

				if (rs.getInt("SISTPR_UBICAC") == 1)
					jcUbicaciones.setSelected(true);
				else
					jcUbicaciones.setSelected(false);

				if (rs.getInt("SISTPR_UBCMAP") == 1)
					jcMapaUbicaciones.setSelected(true);
				else
					jcMapaUbicaciones.setSelected(false);

				if (rs.getInt("SISTPR_UBCFIJ") == 1)
					jcUbicaFijas.setSelected(true);
				else
					jcUbicaFijas.setSelected(false);

				tfCentroACentral.setText(rs
						.getString("SISTPR_CENTRO_A_CENTRAL"));
				tfPosibAnulAlb.setText(rs.getString("SISTPR_POSIB_ANULAR"));
				tfDiasAplzam.setText(rs.getString("SISTPR_DIAS_APLAZ_PEDCLI"));
				tfImprimirAlbPor.setText(rs.getString("SISTPR_IMPRES_ALBS_B"));
				tfSubCtaInterna.setText(rs.getString("SISTPR_SUBCTA_INTERNAS"));
				tfNroDecUnid.setText(rs.getString("SISTPR_NRO_DEC_UNID"));
				tfRsvCompCn21.setText(rs
						.getString("SISTPR_PORC_PED_VIRTUAL_101"));
				tfTarifaBase.setText(rs.getString("SISTPR_TARIFABASE_PVPS"));
				tfFormMinMax.setText(rs.getString("SISTPR_FORM_CALC_MINMAX"));
			}

			if (existeReg == false)
				JOptionPane.showMessageDialog(null,
						"No existe la empresa, CREARLA!!!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error en lectura fichero de Sistema (3)!!!");
			e.printStackTrace();
		}
	}

	public void grabarDatosIdentificacion() {
		String strSql = null;
		MysqlConnect m = null;
		m = MysqlConnect.getDbCon();

		String empresa = "MV";

		// UPDATE `test`.`sistem` SET `SISTEM_NOMBRE`='LAS VEGAS, S.L.' WHERE
		// `EMPRESA`='MV';
		strSql = "UPDATE SISTEM SET ";
		strSql += "SISTEM_NOMBRE = '" + tfNombre.getText() + "', \n";
		strSql += "SISTEM_NOM_COMER = '" + tfNombreComercial.getText()
				+ "', \n";
		strSql += "SISTEM_DIR = '" + tfDireccion.getText() + "', \n";
		strSql += "SISTEM_DATOS_REGISTRO = '" + tfRegistro.getText() + "', \n";
		strSql += "SISTEM_POB = '" + tfCpPoblacion.getText() + "', \n";
		strSql += "SISTEM_TLFNO = '" + tfTelefono.getText() + "', \n";
		strSql += "SISTEM_LOGOTIPO = '" + tfLogotipo.getText() + "', \n";
		strSql += "SISTEM_NIF = '" + tfNif.getText() + "', \n";
		strSql += "SISTEM_EMPRESA_B = '" + tfEmpresaComp.getText() + "', \n";
		strSql += "SISTEM_ACCESO_BRJ = '" + tfAccesoComp.getText() + "', \n";
		strSql += "SISTEM_LIN_LOGO = '" + tfLogoLin.getText() + "', \n";
		strSql += "SISTEM_COL_LOGO = '" + tfLogoCol.getText() + "', \n";
		strSql += "SISTEM_LOGO_BD = ";
		if (jcLogoBd.isSelected())
			strSql += "1, ";
		else
			strSql += "0, ";

		strSql += "SISTEM_LICENCIA = '" + tfLicencia.getText() + "', \n";
		strSql += "SISTEM_VERSION = '" + tfVersion.getText() + "', \n";
		strSql += "SISTEM_FEC_VERS = "
				+ Cadena.cadenaAfecha(tfFechaVersion.getText()) + ", ";
		strSql += "SISTEM_EMPRESA_CENTRAL = '" + tfEmpresaCentral.getText()
				+ "', \n";
		strSql += "SISTEM_QIEN_ABRE_FACT_B = '" + tfQuienAbreB.getText()
				+ "', \n";
		strSql += "SISTEM_FEC_ULT_ENTRADA = "
				+ Cadena.cadenaAfecha(tfUltimaEntrada.getText()) + ", ";
		// Sería bueno tener un desplegable para escoger bien
		strSql += "SISTEM_SIST_OPERATIVO = "
				+ Integer.valueOf(tfSistemaOperativo.getText()) + ", ";
		strSql += "SISTEM_EAN_EMPRESA = '" + tfEanEmpresa.getText() + "', \n";
		strSql += "SISTEM_GRUPO = '" + tfGrupo.getText() + "', \n";
		strSql += "SISTEM_MASCOMPLEM_ROTOS = "
				+ Integer.valueOf(tfComplenRotos.getText()) + ", ";
		strSql += "SISTEM_CLT_EN_GRUPO = "
				+ Integer.valueOf(tfClienteGrupo.getText()) + ", ";
		strSql += "SISTEM_ABIERTA_FACT_B = ";
		if (jcAbiertaB.isSelected())
			strSql += "1, ";
		else
			strSql += "0, ";
		strSql += "SISTEM_CLITES_CLIENT = ";
		if (jcClitesClient.isSelected())
			strSql += "1, ";
		else
			strSql += "0, ";

		strSql += "SISTEM_TIPO_EMPRESA = ";
		strSql += jlTipoEmpresa.getSelectedIndex() + " ";

		strSql += "WHERE EMPRESA = '" + empresa + "'";
		JOptionPane.showMessageDialog(null, strSql);
		try {
			if (m.insert(strSql) > 0)
				// JOptionPane.showMessageDialog(null, "Grabación correcta.");
				JOptionPaneConTimeOut.visualizaDialogo(null,
						"Grabación correcta.",
						"Grabacion datos identificación", 5000);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error al grabar SISTEM 1");
			e.printStackTrace();
		}
	}

	public void grabarDatosContables() {
		String strSql = null;
		MysqlConnect m = null;
		m = MysqlConnect.getDbCon();

		String empresa = "MV";

		// UPDATE `test`.`sistem` SET `SISTEM_NOMBRE`='LAS VEGAS, S.L.' WHERE
		// `EMPRESA`='MV';
		strSql = "UPDATE SISTEM SET ";
		strSql += "SISTCO_FEC_ULT_CIERRE = "
				+ Cadena.cadenaAfecha(tfUltimoCierre.getText()) + ", \n";
		strSql += "SISTCO_FEC_ULT_REGPRO = "
				+ Cadena.cadenaAfecha(tfUltimaRegularizacionProvisional
						.getText()) + ", \n";
		strSql += "SISTCO_FEC_ULT_DEPMOC = "
				+ Cadena.cadenaAfecha(tfUltimaDepuracionMovimientos.getText())
				+ ", \n";
		strSql += "SISTCO_FEC_ULT_INPUCON = "
				+ Cadena.cadenaAfecha(tfUltimoInputMovimientos.getText())
				+ ", \n";
		strSql += "SISTCO_FEC_ULT_DECLIVA = "
				+ Cadena.cadenaAfecha(tfUltimaDeclaracionIva.getText())
				+ ", \n";
		strSql += "SISTCO_FEC_ULT_CIERRE_FAC = "
				+ Cadena.cadenaAfecha(tfUltimoCierreFacturacion.getText())
				+ ", \n";
		strSql += "SISTCO_ULT_DOCUM = "
				+ Integer.valueOf(tfUltimoDocumento.getText()) + ", \n";

		strSql += "SISTCO_TEXFDP_1 = '" + tfTfdp1.getText() + "', \n";
		strSql += "SISTCO_TEXFDP_2 = '" + tfTfdp2.getText() + "', \n";
		strSql += "SISTCO_TEXFDP_3 = '" + tfTfdp3.getText() + "', \n";
		strSql += "SISTCO_TEXFDP_4 = '" + tfTfdp4.getText() + "', \n";
		strSql += "SISTCO_TEXFDP_5 = '" + tfTfdp5.getText() + "', \n";
		strSql += "SISTCO_TEXFDP_6 = '" + tfTfdp6.getText() + "', \n";
		strSql += "SISTCO_TEXFDP_7 = '" + tfTfdp7.getText() + "', \n";

		strSql += "SISTCO_POR_IVA_1 = "
				+ Double.valueOf(tfIvaReducido.getText()) + ", \n";
		strSql += "SISTCO_POR_IVA_2 = "
				+ Double.valueOf(tfIvaGeneral.getText()) + ", \n";
		strSql += "SISTCO_POR_IVA_3 = "
				+ Double.valueOf(tfIvaSuperReducido.getText()) + ", \n";
		strSql += "SISTCO_POR_IVA_4 = " + Double.valueOf(tfIvaREA.getText())
				+ ", \n";

		strSql += "SISTCO_POR_REQU_1 = "
				+ Double.valueOf(tfReReducido.getText()) + ", \n";
		strSql += "SISTCO_POR_REQU_2 = "
				+ Double.valueOf(tfReGeneral.getText()) + ", \n";
		strSql += "SISTCO_POR_REQU_3 = "
				+ Double.valueOf(tfReSuperReducido.getText()) + ", \n";
		strSql += "SISTCO_POR_REQU_4 = " + Double.valueOf(tfReREA.getText())
				+ ", \n";

		strSql += "SISTCO_ORDEN_IVA = '" + tfOrdenReducido.getText()
				+ tfOrdenGeneral.getText() + tfOrdenSuperReducido.getText()
				+ tfOrdenREA.getText() + "', \n";

		if (jrbTrimstral.isSelected())
			strSql += "SISTCO_PERIODO_DECLIVA = 3, ";
		else
			strSql += "SISTCO_PERIODO_DECLIVA = 1, ";

		if (jcRecargoEqvCompra.isSelected())
			strSql += "SISTCO_COMPRAS_CONREQU = 1 ";
		else
			strSql += "SISTCO_COMPRAS_CONREQU = 0 ";

		strSql += "WHERE EMPRESA = '" + empresa + "'";
		JOptionPane.showMessageDialog(null, strSql);
		try {
			if (m.insert(strSql) > 0)
				// JOptionPane.showMessageDialog(null, "Grabación correcta.");
				JOptionPaneConTimeOut.visualizaDialogo(null,
						"Grabación correcta.", "Grabacion datos contables",
						5000);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error al grabar SISTEM 2");
			e.printStackTrace();
		}
	}

	public void grabarDatosConfiguracion() {
		String strSql = null;
		MysqlConnect m = null;
		m = MysqlConnect.getDbCon();

		String empresa = "MV";

		// UPDATE `test`.`sistem` SET `SISTEM_NOMBRE`='LAS VEGAS, S.L.' WHERE
		// `EMPRESA`='MV';
		strSql = "UPDATE SISTEM SET ";

		if (jcUnSoloPmc.isSelected())
			strSql += "SISTPR_UN_SOLO_PMC = 1, \n";
		else
			strSql += "SISTPR_UN_SOLO_PMC = 0, \n";

		if (jcProduccion.isSelected())
			strSql += "SISTPR_PRODUCC = 1, \n";
		else
			strSql += "SISTPR_PRODUCC = 0, \n";

		if (jcPedidosCliente.isSelected())
			strSql += "SISTPR_PEDCLI = 1, \n";
		else
			strSql += "SISTPR_PEDCLI = 0, \n";

		if (jcAdmiteRotura.isSelected())
			strSql += "SISTPR_ADMITE_ROTURA_STOCK = 1, \n";
		else
			strSql += "SISTPR_ADMITE_ROTURA_STOCK = 0, \n";

		if (jcPosibleDejarB.isSelected())
			strSql += "SISTPR_POSIB_DEJAR_PNDT_FACT_B = 1, \n";
		else
			strSql += "SISTPR_POSIB_DEJAR_PNDT_FACT_B = 0, \n";

		if (jcChequearDtos.isSelected())
			strSql += "SISTPR_CHK_OFR_DTOS = 1, \n";
		else
			strSql += "SISTPR_CHK_OFR_DTOS = 0, \n";

		if (jcSeFra.isSelected())
			strSql += "SISTPR_PEDCLI_NO_FRA = 1, \n";
		else
			strSql += "SISTPR_PEDCLI_NO_FRA = 0, \n";

		if (jcPreciosPedido.isSelected())
			strSql += "SISTPR_PRECIOS_DE_PEDIDO = 1, \n";
		else
			strSql += "SISTPR_PRECIOS_DE_PEDIDO = 0, \n";

		if (jcFormatoEdi.isSelected())
			strSql += "SISTPR_EDI = 1, \n";
		else
			strSql += "SISTPR_EDI = 0, \n";

		if (jcPedidosProveedor.isSelected())
			strSql += "SISTPR_PEDPRA = 1, \n";
		else
			strSql += "SISTPR_PEDPRA = 0, \n";

		if (jcPedidosProveedor.isSelected())
			strSql += "SISTPR_PEDPRA = 1, \n";
		else
			strSql += "SISTPR_PEDPRA = 0, \n";

		/*
		 * Repasar!!! if (jcDispAlm72h.setSelected()) strSql +=
		 * "SISTPR-DISP-72H = 1, "; else strSql += "SISTPR-DISP-72H = 0, ";
		 */

		if (jcAlturaStDena.isSelected())
			strSql += "SISTPR_LINES_PAPELST = 1, \n";
		else
			strSql += "SISTPR_LINES_PAPELST = 0, \n";

		if (jcGescep50.isSelected())
			strSql += "SISTPR_LONG_CEPTO = 1, \n";
		else
			strSql += "SISTPR_LONG_CEPTO = 0, \n";

		if (jcCabFacAlb.isSelected())
			strSql += "SISTPR_PERSON_FACALB = 1, \n";
		else
			strSql += "SISTPR_PERSON_FACALB = 0, \n";

		if (jcCabRecibo.isSelected())
			strSql += "SISTPR_PERSON_RECIBO = 1, \n";
		else
			strSql += "SISTPR_PERSON_RECIBO = 0, \n";

		if (jcCabContinuom.isSelected())
			strSql += "SISTPR_PERSON_PAPEL = 1, \n";
		else
			strSql += "SISTPR_PERSON_PAPEL = 0, \n";

		if (jcFrasPdf.isSelected())
			strSql += "SISTPR_FRA_PDF = 1, \n";
		else
			strSql += "SISTPR_FRA_PDF = 0, \n";

		if (jcPrintFpagoAlb.isSelected())
			strSql += "SISTPR_PRINT_FDP_ALB = 1, \n";
		else
			strSql += "SISTPR_PRINT_FDP_ALB = 0, \n";

		if (jcImprimir2Dtos.isSelected())
			strSql += "SISTPR_PRINT_2DTOS = 1, \n";
		else
			strSql += "SISTPR_PRINT_2DTOS = 0, \n";

		if (jcImprimirEan.isSelected())
			strSql += "SISTPR_PRINT_DOC_EAN = 1, \n";
		else
			strSql += "SISTPR_PRINT_DOC_EAN = 0, \n";

		if (jcLineasAlbsB.isSelected())
			strSql += "SISTPR_LINES_ALB_B = 1, \n";
		else
			strSql += "SISTPR_LINES_ALB_B = 0, \n";

		if (jcPrintTicket.isSelected())

			strSql += "SISTPR_PRINT_TICKET = 1, \n";
		else
			strSql += "SISTPR_PRINT_TICKET = 0, \n";

		if (jcCliVarH10200.isSelected())
			strSql += "SISTPR_CLIVAR_H10200 = 1, \n";
		else
			strSql += "SISTPR_CLIVAR_H10200 = 0, \n";

		if (jcPrecRecomen.isSelected())
			strSql += "SISTPR_PRECIOS_RECOMEN = 1, \n";
		else
			strSql += "SISTPR_PRECIOS_RECOMEN = 0, \n";

		if (jcPrecCesion.isSelected())
			strSql += "SISTPR_PRECIO_CESION = 1, \n";
		else
			strSql += "SISTPR_PRECIO_CESION = 0, \n";

		if (jcActuaPrecioInput.isSelected())
			strSql += "SISTPR_ACT_PREC_INP = 1, \n";
		else
			strSql += "SISTPR_ACT_PREC_INP = 0, \n";

		if (jcArtSinRec.isSelected())
			strSql += "SISTPR_ART_SIN_REQU = 1, \n";
		else
			strSql += "SISTPR_ART_SIN_REQU = 0, \n";

		if (jcSQL.isSelected())
			strSql += "SISTPR_SQL = 1, \n";
		else
			strSql += "SISTPR_SQL = 0, \n";

		if (jcUbicaciones.isSelected())
			strSql += "SISTPR_UBICAC = 1, \n";
		else
			strSql += "SISTPR_UBICAC = 0, \n";

		if (jcMapaUbicaciones.isSelected())
			strSql += "SISTPR_UBCMAP = 1, \n";
		else
			strSql += "SISTPR_UBCMAP = 0, \n";

		if (jcUbicaFijas.isSelected())
			strSql += "SISTPR_UBCFIJ = 1, \n";
		else
			strSql += "SISTPR_UBCFIJ = 0, \n";

		strSql += "SISTPR_CENTRO_A_CENTRAL = "
				+ Integer.valueOf(tfCentroACentral.getText()) + ", \n";
		strSql += "SISTPR_POSIB_ANULAR = "
				+ Integer.valueOf(tfPosibAnulAlb.getText()) + ", \n";
		strSql += "SISTPR_DIAS_APLAZ_PEDCLI = "
				+ Integer.valueOf(tfDiasAplzam.getText()) + ", \n";
		strSql += "SISTPR_IMPRES_ALBS_B = '" + tfImprimirAlbPor.getText()
				+ "', \n";
		strSql += "SISTPR_SUBCTA_INTERNAS = "
				+ Integer.valueOf(tfSubCtaInterna.getText()) + ", \n";
		strSql += "SISTPR_NRO_DEC_UNID = "
				+ Integer.valueOf(tfNroDecUnid.getText()) + ", \n";
		strSql += "SISTPR_PORC_PED_VIRTUAL_101 = "
				+ Double.valueOf(tfRsvCompCn21.getText()) + ", \n";
		strSql += "SISTPR_TARIFABASE_PVPS = "
				+ Integer.valueOf(tfTarifaBase.getText()) + ", \n";
		strSql += "SISTPR_FORM_CALC_MINMAX = "
				+ Integer.valueOf(tfFormMinMax.getText()) + " \n";

		strSql += "WHERE EMPRESA = '" + empresa + "'";
		JOptionPane.showMessageDialog(null, strSql);
		try {
			if (m.insert(strSql) > 0)
				// JOptionPane.showMessageDialog(null, "Grabación correcta.");
				JOptionPaneConTimeOut.visualizaDialogo(null,
						"Grabación correcta.", "Grabacion datos configuración",
						5000);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error al grabar SISTEM 3");
			e.printStackTrace();
		}
	}

	public boolean getResultado() {
		return true;
	}
	/*
	 * public static void main( String args[] ) { // Create an instance of the
	 * test application //MantenimientoSistem mainFrame = new
	 * MantenimientoSistem(); SwingUtilities.invokeLater(new Runnable() { public
	 * void run() { new MantenimientoSistem(); } });
	 * 
	 * 
	 * }
	 */
}
