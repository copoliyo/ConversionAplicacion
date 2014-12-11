package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;


import com.mysql.jdbc.ResultSet;

public class Sistema {
	
	private String empresa;
	private String nombre;
	private String nombreComercial;
	private String logotipo;
	private String direccion;
	private String poblacion;
	private String nif;
	private String telefono;
	private String datosRegistro;
	private int logoBd;
	private String version;
	private int fechaVersion;
	private String chkHard;
	private int chkHastaDia;
	private String chk;
	private int fechaUltimaEntrada;
	private String accesoBrj;
	private int sistemaOperativo;
	private String empresaB;
	private String empresaCentral;
	private String eanEmpresa;
	private int swEncript;
	private int linLogo;
	private int colLogo;
	private int tipoEmpresa;
	private int abiertaFacturacionB;
	private String quienAbreFactB;
	private int clitesClient;
	private int masComplemRotos;
	private String licencia;
	private int clienteEnGrupo;
	private int fechaInstalacion;
	private String grupo;
	private int swDispD7mta;
	private int porcentjeAumentoVentas;
	private int produccion;
	private int centroACentral;
	private int admiteRoturaStock;
	private int swAlbaranPedCliente;
	private int posibilidadAnular;
	private int posibilidadDejarPendienteFactB;
	private int pedidosCliente;
	private int pedidosProveedor;
	private int unSoloPMC;
	private int chkOfertaDescuento;
	private int pedidosClienteNoFra;
	private int preciosDePedido;
	private int diasAplazamientoPedCli;
	private int edi;
	private int lineasPepelStandard;
	private int longitudConcepto;
	private int personalizarFacAlb;
	private int personalizarRecibo;
	private int personalizarPapel;
	private int printFormaPagoEnAlbaran;
	private int print2Descuentos;
	private int printDocEan;
	private String impresoraAlbaranesB;
	private int lineasAlbaranesB;
	private int printTicket;
	private int facturaPdf;
	private int subcuentaInternas;
	private int clivarH10200;
	private int numeroDecUnidades;
	private int preciosRecomendados;
	private int precioCesion;
	private int tarifaBaseParaPvp;
	private int actualizaPrecioImporte;
	private int articuloSinRecargoEqv;
	private int formaCalculoMinMax;
	private int tarifaInicio[];
	private int tarifaLong[];
	private String tarifaSistema;
	private int tarifaLongitudRegistro;
	private int ubicaciones;
	private int mapaUbicaciones;
	private int ubicacionesFijas;
	private int porcentajePedidoVirtual101;
	private int sql;
	private int fechaUltimoCierre;
	private int fechaUltimaRegularizacionProvisional;
	private int fechaUltimaDepuracionMovimientos;
	private int fechaUltimoInputContable;
	private int fechaUltimaDeclaracionIva;
	private int fechaUltimoCierreFacturacion;
	private int periodoDeclaracionIva;
	private int comprasConRecargoEqv;
	private String textoFormaDePago[];
	private String literalIva[];
	private double porcentajeIva[];
	private double porcentajeRecargoEquivalencia[];
	private int ordenIva;
	private int ultimoDocumento;
	private String literalIvaAnt[];
	private double porcentajeIvaAnt[];
	private double porcentajeRecargoEquivalenciaAnt[];
	private int ordenIvaAnt;
	private int fechaIvaAnt;
	
	static final int MAX_TARIFA = 30;
	
	public Sistema(){
		empresa = "";
		nombre = "";
		nombreComercial = "";
		logotipo = "";
		direccion = "";
		poblacion = "";
		nif = "";
		telefono = "";
		datosRegistro = "";
		logoBd = 0;
		version = "";
		fechaVersion = 0;
		chkHard = "";
		chkHastaDia = 0;
		chk = "";
		fechaUltimaEntrada = 0;
		accesoBrj = "";
		sistemaOperativo = 0;
		empresaB = "";
		empresaCentral = "";
		eanEmpresa = "";
		swEncript = 0;
		linLogo = 0;
		colLogo = 0;
		tipoEmpresa = 0;
		abiertaFacturacionB = 0;
		quienAbreFactB = "";
		clitesClient = 0;
		masComplemRotos = 0;
		licencia = "";
		clienteEnGrupo = 0;
		fechaInstalacion = 0;
		grupo = "";
		swDispD7mta = 0;
		porcentjeAumentoVentas = 0;
		produccion = 0;
		centroACentral = 0;
		admiteRoturaStock = 0;
		swAlbaranPedCliente = 0;
		posibilidadAnular = 0;
		posibilidadDejarPendienteFactB = 0;
		pedidosCliente = 0;
		pedidosProveedor = 0;
		unSoloPMC = 0;
		chkOfertaDescuento = 0;
		pedidosClienteNoFra = 0;
		preciosDePedido = 0;
		diasAplazamientoPedCli = 0;
		edi = 0;
		lineasPepelStandard = 0;
		longitudConcepto = 0;
		personalizarFacAlb = 0;
		personalizarRecibo = 0;
		personalizarPapel = 0;
		printFormaPagoEnAlbaran = 0;
		print2Descuentos = 0;
		printDocEan = 0;
		impresoraAlbaranesB = "";
		lineasAlbaranesB = 0;
		printTicket = 0;
		facturaPdf = 0;
		subcuentaInternas = 0;
		clivarH10200 = 0;
		numeroDecUnidades = 0;
		preciosRecomendados = 0;
		precioCesion = 0;
		tarifaBaseParaPvp = 0;
		actualizaPrecioImporte = 0;
		articuloSinRecargoEqv = 0;
		formaCalculoMinMax = 0;
		
		tarifaInicio = new int[MAX_TARIFA];
		tarifaLong = new int[MAX_TARIFA];
		for(int i = 0; i < MAX_TARIFA; i++){
			tarifaInicio[i] = 0;
			tarifaLong[i] = 0;
		}
		
		tarifaSistema = "";
		tarifaLongitudRegistro = 0;
		ubicaciones = 0;
		mapaUbicaciones = 0;
		ubicacionesFijas = 0;
		porcentajePedidoVirtual101 = 0;
		sql = 0;
		fechaUltimoCierre = 0;
		fechaUltimaRegularizacionProvisional = 0;
		fechaUltimaDepuracionMovimientos = 0;
		fechaUltimoInputContable = 0;
		fechaUltimaDeclaracionIva = 0;
		fechaUltimoCierreFacturacion = 0;
		periodoDeclaracionIva = 0;
		comprasConRecargoEqv = 0;
		
		textoFormaDePago = new String[9];
		for(int i = 0; i < 9; i++)
			textoFormaDePago[i] = "";
		
		literalIva = new String[4];
		porcentajeIva = new double[4];
		porcentajeRecargoEquivalencia = new double[4];
		literalIvaAnt = new String[4];
		porcentajeIvaAnt = new double[4];
		porcentajeRecargoEquivalenciaAnt = new double[4];
		for(int i = 0; i < 4; i++){
			literalIva[i] = "";
			porcentajeIva[i] = 0.0;
			porcentajeRecargoEquivalencia[i] = 0.0;
			literalIvaAnt[i] = "";
			porcentajeIvaAnt[i] = 0.0;
			porcentajeRecargoEquivalenciaAnt[i] = 0.0;
		}

		ordenIva = 0;
		ultimoDocumento = 0;
		ordenIvaAnt = 0;
		fechaIvaAnt = 0;
		
	}
	
	public Sistema(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				nombre = rs.getString("SISTEM_NOMBRE").trim();
				nombreComercial = rs.getString("SISTEM_NOM_COMER").trim();
				logotipo = rs.getString("SISTEM_LOGOTIPO").trim();
				direccion = rs.getString("SISTEM_DIR").trim();
				poblacion = rs.getString("SISTEM_POB").trim();
				nif = rs.getString("SISTEM_NIF").trim();
				telefono = rs.getString("SISTEM_TLFNO").trim();
				datosRegistro = rs.getString("SISTEM_DATOS_REGISTRO").trim();
				logoBd = rs.getInt("SISTEM_LOGO_BD");
				version = rs.getString("SISTEM_VERSION").trim();
				fechaVersion = rs.getInt("SISTEM_FEC_VERS");
				chkHard = rs.getString("SISTEM_CHK_HARD").trim();
				chkHastaDia = rs.getInt("SISTEM_CHK_HASTA_DIA");
				chk = rs.getString("SISTEM_CHK").trim();
				fechaUltimaEntrada = rs.getInt("SISTEM_FEC_ULT_ENTRADA");
				accesoBrj = rs.getString("SISTEM_ACCESO_BRJ").trim();
				sistemaOperativo = rs.getInt("SISTEM_SIST_OPERATIVO");
				empresaB = rs.getString("SISTEM_EMPRESA_B").trim();
				empresaCentral = rs.getString("SISTEM_EMPRESA_CENTRAL").trim();
				eanEmpresa = rs.getString("SISTEM_EAN_EMPRESA").trim();
				swEncript = rs.getInt("SISTEM_SW_ENCRIPT");
				linLogo = rs.getInt("SISTEM_LIN_LOGO");
				colLogo = rs.getInt("SISTEM_COL_LOGO");
				tipoEmpresa = rs.getInt("SISTEM_TIPO_EMPRESA");
				abiertaFacturacionB = rs.getInt("SISTEM_ABIERTA_FACT_B");
				quienAbreFactB = rs.getString("SISTEM_QIEN_ABRE_FACT_B").trim();
				clitesClient = rs.getInt("SISTEM_CLITES_CLIENT");
				masComplemRotos = rs.getInt("SISTEM_MASCOMPLEM_ROTOS");
				licencia = rs.getString("SISTEM_LICENCIA").trim();
				clienteEnGrupo = rs.getInt("SISTEM_CLT_EN_GRUPO");
				fechaInstalacion = rs.getInt("SISTEM_FEC_INSTALAC");
				grupo = rs.getString("SISTEM_GRUPO").trim();
				swDispD7mta = rs.getInt("SISTEM_SW_DISP_D7MTA");
				porcentjeAumentoVentas = rs.getInt("SISTEM_PORC_AUM_VTAS");
				produccion = rs.getInt("SISTPR_PRODUCC");
				centroACentral = rs.getInt("SISTPR_CENTRO_A_CENTRAL");
				admiteRoturaStock = rs.getInt("SISTPR_ADMITE_ROTURA_STOCK");
				swAlbaranPedCliente = rs.getInt("SISTPR_SW_ALB_PEDCLI");
				posibilidadAnular = rs.getInt("SISTPR_POSIB_ANULAR");
				posibilidadDejarPendienteFactB = rs.getInt("SISTPR_POSIB_DEJAR_PNDT_FACT_B");
				pedidosCliente = rs.getInt("SISTPR_PEDCLI");
				pedidosProveedor = rs.getInt("SISTPR_PEDPRA");
				unSoloPMC = rs.getInt("SISTPR_UN_SOLO_PMC");
				chkOfertaDescuento = rs.getInt("SISTPR_CHK_OFR_DTOS");
				pedidosClienteNoFra = rs.getInt("SISTPR_PEDCLI_NO_FRA");
				preciosDePedido = rs.getInt("SISTPR_PRECIOS_DE_PEDIDO");
				diasAplazamientoPedCli = rs.getInt("SISTPR_DIAS_APLAZ_PEDCLI");
				edi = rs.getInt("SISTPR_EDI");
				lineasPepelStandard = rs.getInt("SISTPR_LINES_PAPELST");
				longitudConcepto = rs.getInt("SISTPR_LONG_CEPTO");
				personalizarFacAlb = rs.getInt("SISTPR_PERSON_FACALB");
				personalizarRecibo = rs.getInt("SISTPR_PERSON_RECIBO");
				personalizarPapel = rs.getInt("SISTPR_PERSON_PAPEL");
				printFormaPagoEnAlbaran = rs.getInt("SISTPR_PRINT_FDP_ALB");
				print2Descuentos = rs.getInt("SISTPR_PRINT_2DTOS");
				printDocEan = rs.getInt("SISTPR_PRINT_DOC_EAN");
				impresoraAlbaranesB = rs.getString("SISTPR_IMPRES_ALBS_B").trim();
				lineasAlbaranesB = rs.getInt("SISTPR_LINES_ALB_B");
				printTicket = rs.getInt("SISTPR_PRINT_TICKET");
				facturaPdf = rs.getInt("SISTPR_FRA_PDF");
				subcuentaInternas = rs.getInt("SISTPR_SUBCTA_INTERNAS");
				clivarH10200 = rs.getInt("SISTPR_CLIVAR_H10200");
				numeroDecUnidades = rs.getInt("SISTPR_NRO_DEC_UNID");
				preciosRecomendados = rs.getInt("SISTPR_PRECIOS_RECOMEN");
				precioCesion = rs.getInt("SISTPR_PRECIO_CESION");
				tarifaBaseParaPvp = rs.getInt("SISTPR_TARIFABASE_PVPS");
				actualizaPrecioImporte = rs.getInt("SISTPR_ACT_PREC_INP");
				articuloSinRecargoEqv = rs.getInt("SISTPR_ART_SIN_REQU");
				formaCalculoMinMax = rs.getInt("SISTPR_FORM_CALC_MINMAX");
				
				tarifaInicio = new int[MAX_TARIFA];
				tarifaLong = new int[MAX_TARIFA];
				
				tarifaInicio[0] = rs.getInt("SISTPR_TARIFA_INICIO_1");
				tarifaInicio[1] = rs.getInt("SISTPR_TARIFA_INICIO_2");
				tarifaInicio[2] = rs.getInt("SISTPR_TARIFA_INICIO_3");
				tarifaInicio[3] = rs.getInt("SISTPR_TARIFA_INICIO_4");
				tarifaInicio[4] = rs.getInt("SISTPR_TARIFA_INICIO_5");
				tarifaInicio[5] = rs.getInt("SISTPR_TARIFA_INICIO_6");
				tarifaInicio[6] = rs.getInt("SISTPR_TARIFA_INICIO_7");
				tarifaInicio[7] = rs.getInt("SISTPR_TARIFA_INICIO_8");
				tarifaInicio[8] = rs.getInt("SISTPR_TARIFA_INICIO_9");
				tarifaInicio[9] = rs.getInt("SISTPR_TARIFA_INICIO_10");
				tarifaInicio[10] = rs.getInt("SISTPR_TARIFA_INICIO_11");
				tarifaInicio[11] = rs.getInt("SISTPR_TARIFA_INICIO_12");
				tarifaInicio[12] = rs.getInt("SISTPR_TARIFA_INICIO_13");
				tarifaInicio[13] = rs.getInt("SISTPR_TARIFA_INICIO_14");
				tarifaInicio[14] = rs.getInt("SISTPR_TARIFA_INICIO_15");
				tarifaInicio[15] = rs.getInt("SISTPR_TARIFA_INICIO_16");
				tarifaInicio[16] = rs.getInt("SISTPR_TARIFA_INICIO_17");
				tarifaInicio[17] = rs.getInt("SISTPR_TARIFA_INICIO_18");
				tarifaInicio[18] = rs.getInt("SISTPR_TARIFA_INICIO_19");
				tarifaInicio[19] = rs.getInt("SISTPR_TARIFA_INICIO_20");
				tarifaInicio[20] = rs.getInt("SISTPR_TARIFA_INICIO_21");
				tarifaInicio[21] = rs.getInt("SISTPR_TARIFA_INICIO_22");
				tarifaInicio[22] = rs.getInt("SISTPR_TARIFA_INICIO_23");
				tarifaInicio[23] = rs.getInt("SISTPR_TARIFA_INICIO_24");
				tarifaInicio[24] = rs.getInt("SISTPR_TARIFA_INICIO_25");
				tarifaInicio[25] = rs.getInt("SISTPR_TARIFA_INICIO_26");
				tarifaInicio[26] = rs.getInt("SISTPR_TARIFA_INICIO_27");
				tarifaInicio[27] = rs.getInt("SISTPR_TARIFA_INICIO_28");
				tarifaInicio[28] = rs.getInt("SISTPR_TARIFA_INICIO_29");
				tarifaInicio[29] = rs.getInt("SISTPR_TARIFA_INICIO_30");
																							
				tarifaLong[0] = rs.getInt("SISTPR_TARIFA_LONG_1");
				tarifaLong[1] = rs.getInt("SISTPR_TARIFA_LONG_2");
				tarifaLong[2] = rs.getInt("SISTPR_TARIFA_LONG_3");
				tarifaLong[3] = rs.getInt("SISTPR_TARIFA_LONG_4");
				tarifaLong[4] = rs.getInt("SISTPR_TARIFA_LONG_5");
				tarifaLong[5] = rs.getInt("SISTPR_TARIFA_LONG_6");
				tarifaLong[6] = rs.getInt("SISTPR_TARIFA_LONG_7");
				tarifaLong[7] = rs.getInt("SISTPR_TARIFA_LONG_8");
				tarifaLong[8] = rs.getInt("SISTPR_TARIFA_LONG_9");
				tarifaLong[9] = rs.getInt("SISTPR_TARIFA_LONG_10");
				tarifaLong[10] = rs.getInt("SISTPR_TARIFA_LONG_11");
				tarifaLong[11] = rs.getInt("SISTPR_TARIFA_LONG_12");
				tarifaLong[12] = rs.getInt("SISTPR_TARIFA_LONG_13");
				tarifaLong[13] = rs.getInt("SISTPR_TARIFA_LONG_14");
				tarifaLong[14] = rs.getInt("SISTPR_TARIFA_LONG_15");
				tarifaLong[15] = rs.getInt("SISTPR_TARIFA_LONG_16");
				tarifaLong[16] = rs.getInt("SISTPR_TARIFA_LONG_17");
				tarifaLong[17] = rs.getInt("SISTPR_TARIFA_LONG_18");
				tarifaLong[18] = rs.getInt("SISTPR_TARIFA_LONG_19");
				tarifaLong[19] = rs.getInt("SISTPR_TARIFA_LONG_20");
				tarifaLong[20] = rs.getInt("SISTPR_TARIFA_LONG_21");
				tarifaLong[21] = rs.getInt("SISTPR_TARIFA_LONG_22");
				tarifaLong[22] = rs.getInt("SISTPR_TARIFA_LONG_23");
				tarifaLong[23] = rs.getInt("SISTPR_TARIFA_LONG_24");
				tarifaLong[24] = rs.getInt("SISTPR_TARIFA_LONG_25");
				tarifaLong[25] = rs.getInt("SISTPR_TARIFA_LONG_26");
				tarifaLong[26] = rs.getInt("SISTPR_TARIFA_LONG_27");
				tarifaLong[27] = rs.getInt("SISTPR_TARIFA_LONG_28");
				tarifaLong[28] = rs.getInt("SISTPR_TARIFA_LONG_29");
				tarifaLong[29] = rs.getInt("SISTPR_TARIFA_LONG_30");
											
				tarifaSistema = rs.getString("SISTPR_TARIFA_SISTEMA").trim();
				tarifaLongitudRegistro = rs.getInt("SISTPR_TARIFA_LONGREG");
				ubicaciones = rs.getInt("SISTPR_UBICAC");
				mapaUbicaciones = rs.getInt("SISTPR_UBCMAP");
				ubicacionesFijas = rs.getInt("SISTPR_UBCFIJ");
				porcentajePedidoVirtual101 = rs.getInt("SISTPR_PORC_PED_VIRTUAL_101");
				sql = rs.getInt("SISTPR_SQL");
				fechaUltimoCierre = rs.getInt("SISTCO_FEC_ULT_CIERRE");
				fechaUltimaRegularizacionProvisional = rs.getInt("SISTCO_FEC_ULT_REGPRO");
				fechaUltimaDepuracionMovimientos = rs.getInt("SISTCO_FEC_ULT_DEPMOC");
				fechaUltimoInputContable = rs.getInt("SISTCO_FEC_ULT_INPUCON");
				fechaUltimaDeclaracionIva = rs.getInt("SISTCO_FEC_ULT_DECLIVA");
				fechaUltimoCierreFacturacion = rs.getInt("SISTCO_FEC_ULT_CIERRE_FAC");
				periodoDeclaracionIva = rs.getInt("SISTCO_PERIODO_DECLIVA");
				comprasConRecargoEqv = rs.getInt("SISTCO_COMPRAS_CONREQU");
				
				textoFormaDePago = new String[9];
				
				textoFormaDePago[0] = rs.getString("SISTCO_TEXFDP_1").trim();
				textoFormaDePago[1] = rs.getString("SISTCO_TEXFDP_2").trim();
				textoFormaDePago[2] = rs.getString("SISTCO_TEXFDP_3").trim();
				textoFormaDePago[3] = rs.getString("SISTCO_TEXFDP_4").trim();
				textoFormaDePago[4] = rs.getString("SISTCO_TEXFDP_5").trim();
				textoFormaDePago[5] = rs.getString("SISTCO_TEXFDP_6").trim();
				textoFormaDePago[6] = rs.getString("SISTCO_TEXFDP_7").trim();
				textoFormaDePago[7] = rs.getString("SISTCO_TEXFDP_8").trim();
				textoFormaDePago[8] = rs.getString("SISTCO_TEXFDP_9").trim();
				
				literalIva = new String[4];
				porcentajeIva = new double[4];
				porcentajeRecargoEquivalencia = new double[4];
				literalIvaAnt = new String[4];
				porcentajeIvaAnt = new double[4];
				porcentajeRecargoEquivalenciaAnt = new double[4];
				
				literalIva[0] = rs.getString("SISTCO_LIT_IVA_1").trim();
				literalIva[1] = rs.getString("SISTCO_LIT_IVA_2").trim();
				literalIva[2] = rs.getString("SISTCO_LIT_IVA_3").trim();
				literalIva[3] = rs.getString("SISTCO_LIT_IVA_4").trim();
				porcentajeIva[0] = rs.getDouble("SISTCO_POR_IVA_1");
				porcentajeIva[1] = rs.getDouble("SISTCO_POR_IVA_2");
				porcentajeIva[2] = rs.getDouble("SISTCO_POR_IVA_3");
				porcentajeIva[3] = rs.getDouble("SISTCO_POR_IVA_4");
				porcentajeRecargoEquivalencia[0] = rs.getDouble("SISTCO_POR_REQU_1");
				porcentajeRecargoEquivalencia[1] = rs.getDouble("SISTCO_POR_REQU_2");
				porcentajeRecargoEquivalencia[2] = rs.getDouble("SISTCO_POR_REQU_3");
				porcentajeRecargoEquivalencia[3] = rs.getDouble("SISTCO_POR_REQU_4");
				literalIvaAnt[0] = rs.getString("SISTCO_LIT_IVA_ANT_1").trim();
				literalIvaAnt[1] = rs.getString("SISTCO_LIT_IVA_ANT_2").trim();
				literalIvaAnt[2] = rs.getString("SISTCO_LIT_IVA_ANT_3").trim();
				literalIvaAnt[3] = rs.getString("SISTCO_LIT_IVA_ANT_4").trim();
				porcentajeIvaAnt[0] = rs.getDouble("SISTCO_POR_IVA_ANT_1");
				porcentajeIvaAnt[1] = rs.getDouble("SISTCO_POR_IVA_ANT_2");
				porcentajeIvaAnt[2] = rs.getDouble("SISTCO_POR_IVA_ANT_3");
				porcentajeIvaAnt[3] = rs.getDouble("SISTCO_POR_IVA_ANT_4");
				porcentajeRecargoEquivalenciaAnt[0] = rs.getDouble("SISTCO_POR_REQU_ANT_1");
				porcentajeRecargoEquivalenciaAnt[1] = rs.getDouble("SISTCO_POR_REQU_ANT_2");
				porcentajeRecargoEquivalenciaAnt[2] = rs.getDouble("SISTCO_POR_REQU_ANT_3");
				porcentajeRecargoEquivalenciaAnt[3] = rs.getDouble("SISTCO_POR_REQU_ANT_4");
				
				ordenIva = rs.getInt("SISTCO_ORDEN_IVA");
				ultimoDocumento = rs.getInt("SISTCO_ULT_DOCUM");
				ordenIvaAnt = rs.getInt("SISTCO_ORDEN_IVA_ANT");
				fechaIvaAnt = rs.getInt("SISTCO_FECHA_IVA_ANT");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de Sistema!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}
	
	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			nombre = rs.getString("SISTEM_NOMBRE").trim();
			nombreComercial = rs.getString("SISTEM_NOM_COMER").trim();
			logotipo = rs.getString("SISTEM_LOGOTIPO").trim();
			direccion = rs.getString("SISTEM_DIR").trim();
			poblacion = rs.getString("SISTEM_POB").trim();
			nif = rs.getString("SISTEM_NIF").trim();
			telefono = rs.getString("SISTEM_TLFNO").trim();
			datosRegistro = rs.getString("SISTEM_DATOS_REGISTRO").trim();
			logoBd = rs.getInt("SISTEM_LOGO_BD");
			version = rs.getString("SISTEM_VERSION").trim();
			fechaVersion = rs.getInt("SISTEM_FEC_VERS");
			chkHard = rs.getString("SISTEM_CHK_HARD").trim();
			chkHastaDia = rs.getInt("SISTEM_CHK_HASTA_DIA");
			chk = rs.getString("SISTEM_CHK").trim();
			fechaUltimaEntrada = rs.getInt("SISTEM_FEC_ULT_ENTRADA");
			accesoBrj = rs.getString("SISTEM_ACCESO_BRJ").trim();
			sistemaOperativo = rs.getInt("SISTEM_SIST_OPERATIVO");
			empresaB = rs.getString("SISTEM_EMPRESA_B").trim();
			empresaCentral = rs.getString("SISTEM_EMPRESA_CENTRAL").trim();
			eanEmpresa = rs.getString("SISTEM_EAN_EMPRESA").trim();
			swEncript = rs.getInt("SISTEM_SW_ENCRIPT");
			linLogo = rs.getInt("SISTEM_LIN_LOGO");
			colLogo = rs.getInt("SISTEM_COL_LOGO");
			tipoEmpresa = rs.getInt("SISTEM_TIPO_EMPRESA");
			abiertaFacturacionB = rs.getInt("SISTEM_ABIERTA_FACT_B");
			quienAbreFactB = rs.getString("SISTEM_QIEN_ABRE_FACT_B").trim();
			clitesClient = rs.getInt("SISTEM_CLITES_CLIENT");
			masComplemRotos = rs.getInt("SISTEM_MASCOMPLEM_ROTOS");
			licencia = rs.getString("SISTEM_LICENCIA").trim();
			clienteEnGrupo = rs.getInt("SISTEM_CLT_EN_GRUPO");
			fechaInstalacion = rs.getInt("SISTEM_FEC_INSTALAC");
			grupo = rs.getString("SISTEM_GRUPO").trim();
			swDispD7mta = rs.getInt("SISTEM_SW_DISP_D7MTA");
			porcentjeAumentoVentas = rs.getInt("SISTEM_PORC_AUM_VTAS");
			produccion = rs.getInt("SISTPR_PRODUCC");
			centroACentral = rs.getInt("SISTPR_CENTRO_A_CENTRAL");
			admiteRoturaStock = rs.getInt("SISTPR_ADMITE_ROTURA_STOCK");
			swAlbaranPedCliente = rs.getInt("SISTPR_SW_ALB_PEDCLI");
			posibilidadAnular = rs.getInt("SISTPR_POSIB_ANULAR");
			posibilidadDejarPendienteFactB = rs.getInt("SISTPR_POSIB_DEJAR_PNDT_FACT_B");
			pedidosCliente = rs.getInt("SISTPR_PEDCLI");
			pedidosProveedor = rs.getInt("SISTPR_PEDPRA");
			unSoloPMC = rs.getInt("SISTPR_UN_SOLO_PMC");
			chkOfertaDescuento = rs.getInt("SISTPR_CHK_OFR_DTOS");
			pedidosClienteNoFra = rs.getInt("SISTPR_PEDCLI_NO_FRA");
			preciosDePedido = rs.getInt("SISTPR_PRECIOS_DE_PEDIDO");
			diasAplazamientoPedCli = rs.getInt("SISTPR_DIAS_APLAZ_PEDCLI");
			edi = rs.getInt("SISTPR_EDI");
			lineasPepelStandard = rs.getInt("SISTPR_LINES_PAPELST");
			longitudConcepto = rs.getInt("SISTPR_LONG_CEPTO");
			personalizarFacAlb = rs.getInt("SISTPR_PERSON_FACALB");
			personalizarRecibo = rs.getInt("SISTPR_PERSON_RECIBO");
			personalizarPapel = rs.getInt("SISTPR_PERSON_PAPEL");
			printFormaPagoEnAlbaran = rs.getInt("SISTPR_PRINT_FDP_ALB");
			print2Descuentos = rs.getInt("SISTPR_PRINT_2DTOS");
			printDocEan = rs.getInt("SISTPR_PRINT_DOC_EAN");
			impresoraAlbaranesB = rs.getString("SISTPR_IMPRES_ALBS_B").trim();
			lineasAlbaranesB = rs.getInt("SISTPR_LINES_ALB_B");
			printTicket = rs.getInt("SISTPR_PRINT_TICKET");
			facturaPdf = rs.getInt("SISTPR_FRA_PDF");
			subcuentaInternas = rs.getInt("SISTPR_SUBCTA_INTERNAS");
			clivarH10200 = rs.getInt("SISTPR_CLIVAR_H10200");
			numeroDecUnidades = rs.getInt("SISTPR_NRO_DEC_UNID");
			preciosRecomendados = rs.getInt("SISTPR_PRECIOS_RECOMEN");
			precioCesion = rs.getInt("SISTPR_PRECIO_CESION");
			tarifaBaseParaPvp = rs.getInt("SISTPR_TARIFABASE_PVPS");
			actualizaPrecioImporte = rs.getInt("SISTPR_ACT_PREC_INP");
			articuloSinRecargoEqv = rs.getInt("SISTPR_ART_SIN_REQU");
			formaCalculoMinMax = rs.getInt("SISTPR_FORM_CALC_MINMAX");					
			tarifaInicio[0] = rs.getInt("SISTPR_TARIFA_INICIO_1");
			tarifaInicio[1] = rs.getInt("SISTPR_TARIFA_INICIO_2");
			tarifaInicio[2] = rs.getInt("SISTPR_TARIFA_INICIO_3");
			tarifaInicio[3] = rs.getInt("SISTPR_TARIFA_INICIO_4");
			tarifaInicio[4] = rs.getInt("SISTPR_TARIFA_INICIO_5");
			tarifaInicio[5] = rs.getInt("SISTPR_TARIFA_INICIO_6");
			tarifaInicio[6] = rs.getInt("SISTPR_TARIFA_INICIO_7");
			tarifaInicio[7] = rs.getInt("SISTPR_TARIFA_INICIO_8");
			tarifaInicio[8] = rs.getInt("SISTPR_TARIFA_INICIO_9");
			tarifaInicio[9] = rs.getInt("SISTPR_TARIFA_INICIO_10");
			tarifaInicio[10] = rs.getInt("SISTPR_TARIFA_INICIO_11");
			tarifaInicio[11] = rs.getInt("SISTPR_TARIFA_INICIO_12");
			tarifaInicio[12] = rs.getInt("SISTPR_TARIFA_INICIO_13");
			tarifaInicio[13] = rs.getInt("SISTPR_TARIFA_INICIO_14");
			tarifaInicio[14] = rs.getInt("SISTPR_TARIFA_INICIO_15");
			tarifaInicio[15] = rs.getInt("SISTPR_TARIFA_INICIO_16");
			tarifaInicio[16] = rs.getInt("SISTPR_TARIFA_INICIO_17");
			tarifaInicio[17] = rs.getInt("SISTPR_TARIFA_INICIO_18");
			tarifaInicio[18] = rs.getInt("SISTPR_TARIFA_INICIO_19");
			tarifaInicio[19] = rs.getInt("SISTPR_TARIFA_INICIO_20");
			tarifaInicio[20] = rs.getInt("SISTPR_TARIFA_INICIO_21");
			tarifaInicio[21] = rs.getInt("SISTPR_TARIFA_INICIO_22");
			tarifaInicio[22] = rs.getInt("SISTPR_TARIFA_INICIO_23");
			tarifaInicio[23] = rs.getInt("SISTPR_TARIFA_INICIO_24");
			tarifaInicio[24] = rs.getInt("SISTPR_TARIFA_INICIO_25");
			tarifaInicio[25] = rs.getInt("SISTPR_TARIFA_INICIO_26");
			tarifaInicio[26] = rs.getInt("SISTPR_TARIFA_INICIO_27");
			tarifaInicio[27] = rs.getInt("SISTPR_TARIFA_INICIO_28");
			tarifaInicio[28] = rs.getInt("SISTPR_TARIFA_INICIO_29");
			tarifaInicio[29] = rs.getInt("SISTPR_TARIFA_INICIO_30");
																						
			tarifaLong[0] = rs.getInt("SISTPR_TARIFA_LONG_1");
			tarifaLong[1] = rs.getInt("SISTPR_TARIFA_LONG_2");
			tarifaLong[2] = rs.getInt("SISTPR_TARIFA_LONG_3");
			tarifaLong[3] = rs.getInt("SISTPR_TARIFA_LONG_4");
			tarifaLong[4] = rs.getInt("SISTPR_TARIFA_LONG_5");
			tarifaLong[5] = rs.getInt("SISTPR_TARIFA_LONG_6");
			tarifaLong[6] = rs.getInt("SISTPR_TARIFA_LONG_7");
			tarifaLong[7] = rs.getInt("SISTPR_TARIFA_LONG_8");
			tarifaLong[8] = rs.getInt("SISTPR_TARIFA_LONG_9");
			tarifaLong[9] = rs.getInt("SISTPR_TARIFA_LONG_10");
			tarifaLong[10] = rs.getInt("SISTPR_TARIFA_LONG_11");
			tarifaLong[11] = rs.getInt("SISTPR_TARIFA_LONG_12");
			tarifaLong[12] = rs.getInt("SISTPR_TARIFA_LONG_13");
			tarifaLong[13] = rs.getInt("SISTPR_TARIFA_LONG_14");
			tarifaLong[14] = rs.getInt("SISTPR_TARIFA_LONG_15");
			tarifaLong[15] = rs.getInt("SISTPR_TARIFA_LONG_16");
			tarifaLong[16] = rs.getInt("SISTPR_TARIFA_LONG_17");
			tarifaLong[17] = rs.getInt("SISTPR_TARIFA_LONG_18");
			tarifaLong[18] = rs.getInt("SISTPR_TARIFA_LONG_19");
			tarifaLong[19] = rs.getInt("SISTPR_TARIFA_LONG_20");
			tarifaLong[20] = rs.getInt("SISTPR_TARIFA_LONG_21");
			tarifaLong[21] = rs.getInt("SISTPR_TARIFA_LONG_22");
			tarifaLong[22] = rs.getInt("SISTPR_TARIFA_LONG_23");
			tarifaLong[23] = rs.getInt("SISTPR_TARIFA_LONG_24");
			tarifaLong[24] = rs.getInt("SISTPR_TARIFA_LONG_25");
			tarifaLong[25] = rs.getInt("SISTPR_TARIFA_LONG_26");
			tarifaLong[26] = rs.getInt("SISTPR_TARIFA_LONG_27");
			tarifaLong[27] = rs.getInt("SISTPR_TARIFA_LONG_28");
			tarifaLong[28] = rs.getInt("SISTPR_TARIFA_LONG_29");
			tarifaLong[29] = rs.getInt("SISTPR_TARIFA_LONG_30");										
			tarifaSistema = rs.getString("SISTPR_TARIFA_SISTEMA").trim();
			tarifaLongitudRegistro = rs.getInt("SISTPR_TARIFA_LONGREG");
			ubicaciones = rs.getInt("SISTPR_UBICAC");
			mapaUbicaciones = rs.getInt("SISTPR_UBCMAP");
			ubicacionesFijas = rs.getInt("SISTPR_UBCFIJ");
			porcentajePedidoVirtual101 = rs.getInt("SISTPR_PORC_PED_VIRTUAL_101");
			sql = rs.getInt("SISTPR_SQL");
			fechaUltimoCierre = rs.getInt("SISTCO_FEC_ULT_CIERRE");
			fechaUltimaRegularizacionProvisional = rs.getInt("SISTCO_FEC_ULT_REGPRO");
			fechaUltimaDepuracionMovimientos = rs.getInt("SISTCO_FEC_ULT_DEPMOC");
			fechaUltimoInputContable = rs.getInt("SISTCO_FEC_ULT_INPUCON");
			fechaUltimaDeclaracionIva = rs.getInt("SISTCO_FEC_ULT_DECLIVA");
			fechaUltimoCierreFacturacion = rs.getInt("SISTCO_FEC_ULT_CIERRE_FAC");
			periodoDeclaracionIva = rs.getInt("SISTCO_PERIODO_DECLIVA");
			comprasConRecargoEqv = rs.getInt("SISTCO_COMPRAS_CONREQU");			
			textoFormaDePago[0] = rs.getString("SISTCO_TEXFDP_1").trim();
			textoFormaDePago[1] = rs.getString("SISTCO_TEXFDP_2").trim();
			textoFormaDePago[2] = rs.getString("SISTCO_TEXFDP_3").trim();
			textoFormaDePago[3] = rs.getString("SISTCO_TEXFDP_4").trim();
			textoFormaDePago[4] = rs.getString("SISTCO_TEXFDP_5").trim();
			textoFormaDePago[5] = rs.getString("SISTCO_TEXFDP_6").trim();
			textoFormaDePago[6] = rs.getString("SISTCO_TEXFDP_7").trim();
			textoFormaDePago[7] = rs.getString("SISTCO_TEXFDP_8").trim();
			textoFormaDePago[8] = rs.getString("SISTCO_TEXFDP_9").trim();
			literalIva[0] = rs.getString("SISTCO_LIT_IVA_1").trim();
			literalIva[1] = rs.getString("SISTCO_LIT_IVA_2").trim();
			literalIva[2] = rs.getString("SISTCO_LIT_IVA_3").trim();
			literalIva[3] = rs.getString("SISTCO_LIT_IVA_4").trim();
			porcentajeIva[0] = rs.getDouble("SISTCO_POR_IVA_1");
			porcentajeIva[1] = rs.getDouble("SISTCO_POR_IVA_2");
			porcentajeIva[2] = rs.getDouble("SISTCO_POR_IVA_3");
			porcentajeIva[3] = rs.getDouble("SISTCO_POR_IVA_4");
			porcentajeRecargoEquivalencia[0] = rs.getDouble("SISTCO_POR_REQU_1");
			porcentajeRecargoEquivalencia[1] = rs.getDouble("SISTCO_POR_REQU_2");
			porcentajeRecargoEquivalencia[2] = rs.getDouble("SISTCO_POR_REQU_3");
			porcentajeRecargoEquivalencia[3] = rs.getDouble("SISTCO_POR_REQU_4");
			literalIvaAnt[0] = rs.getString("SISTCO_LIT_IVA_ANT_1").trim();
			literalIvaAnt[1] = rs.getString("SISTCO_LIT_IVA_ANT_2").trim();
			literalIvaAnt[2] = rs.getString("SISTCO_LIT_IVA_ANT_3").trim();
			literalIvaAnt[3] = rs.getString("SISTCO_LIT_IVA_ANT_4").trim();
			porcentajeIvaAnt[0] = rs.getDouble("SISTCO_POR_IVA_ANT_1");
			porcentajeIvaAnt[1] = rs.getDouble("SISTCO_POR_IVA_ANT_2");
			porcentajeIvaAnt[2] = rs.getDouble("SISTCO_POR_IVA_ANT_3");
			porcentajeIvaAnt[3] = rs.getDouble("SISTCO_POR_IVA_ANT_4");
			porcentajeRecargoEquivalenciaAnt[0] = rs.getDouble("SISTCO_POR_REQU_ANT_1");
			porcentajeRecargoEquivalenciaAnt[1] = rs.getDouble("SISTCO_POR_REQU_ANT_2");
			porcentajeRecargoEquivalenciaAnt[2] = rs.getDouble("SISTCO_POR_REQU_ANT_3");
			porcentajeRecargoEquivalenciaAnt[3] = rs.getDouble("SISTCO_POR_REQU_ANT_4");			
			ordenIva = rs.getInt("SISTCO_ORDEN_IVA");
			ultimoDocumento = rs.getInt("SISTCO_ULT_DOCUM");
			ordenIvaAnt = rs.getInt("SISTCO_ORDEN_IVA_ANT");
			fechaIvaAnt = rs.getInt("SISTCO_FECHA_IVA_ANT");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de Sistema!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}
	
	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO SISTEM " +
						   "(EMPRESA, " +
						   "SISTEM_NOMBRE, " +
						   "SISTEM_NOM_COMER, " +
						   "SISTEM_LOGOTIPO, " +
						   "SISTEM_DIR, " +
						   "SISTEM_POB, " +
						   "SISTEM_NIF, " +
						   "SISTEM_TLFNO, " +
						   "SISTEM_DATOS_REGISTRO, " +
						   "SISTEM_LOGO_BD, " +
						   "SISTEM_VERSION, " +
						   "SISTEM_FEC_VERS, " +
						   "SISTEM_CHK_HARD, " +
						   "SISTEM_CHK_HASTA_DIA, " +
						   "SISTEM_CHK, " +
						   "SISTEM_FEC_ULT_ENTRADA, " +
						   "SISTEM_ACCESO_BRJ, " +
						   "SISTEM_SIST_OPERATIVO, " +
						   "SISTEM_EMPRESA_B, " +
						   "SISTEM_EMPRESA_CENTRAL, " +
						   "SISTEM_EAN_EMPRESA, " +
						   "SISTEM_SW_ENCRIPT, " +
						   "SISTEM_LIN_LOGO, " +
						   "SISTEM_COL_LOGO, " +
						   "SISTEM_TIPO_EMPRESA, " +
						   "SISTEM_ABIERTA_FACT_B, " +
						   "SISTEM_QIEN_ABRE_FACT_B, " +
						   "SISTEM_CLITES_CLIENT, " +
						   "SISTEM_MASCOMPLEM_ROTOS, " +
						   "SISTEM_LICENCIA, " +
						   "SISTEM_CLT_EN_GRUPO, " +
						   "SISTEM_FEC_INSTALAC, " +
						   "SISTEM_GRUPO, " +
						   "SISTEM_SW_DISP_D7MTA, " +
						   "SISTEM_PORC_AUM_VTAS, " +
						   "SISTPR_PRODUCC, " +
						   "SISTPR_CENTRO_A_CENTRAL, " +
						   "SISTPR_ADMITE_ROTURA_STOCK, " +
						   "SISTPR_SW_ALB_PEDCLI, " +
						   "SISTPR_POSIB_ANULAR, " +
						   "SISTPR_POSIB_DEJAR_PNDT_FACT_B, " +
						   "SISTPR_PEDCLI, " +
						   "SISTPR_PEDPRA, " +
						   "SISTPR_UN_SOLO_PMC, " +
						   "SISTPR_CHK_OFR_DTOS, " +
						   "SISTPR_PEDCLI_NO_FRA, " +
						   "SISTPR_PRECIOS_DE_PEDIDO, " +
						   "SISTPR_DIAS_APLAZ_PEDCLI, " +
						   "SISTPR_EDI, " +
						   "SISTPR_LINES_PAPELST, " +
						   "SISTPR_LONG_CEPTO, " +
						   "SISTPR_PERSON_FACALB, " +
						   "SISTPR_PERSON_RECIBO, " +
						   "SISTPR_PERSON_PAPEL, " +
						   "SISTPR_PRINT_FDP_ALB, " +
						   "SISTPR_PRINT_2DTOS, " +
						   "SISTPR_PRINT_DOC_EAN, " +
						   "SISTPR_IMPRES_ALBS_B, " +
						   "SISTPR_LINES_ALB_B, " +
						   "SISTPR_PRINT_TICKET, " +
						   "SISTPR_FRA_PDF, " +
						   "SISTPR_SUBCTA_INTERNAS, " +
						   "SISTPR_CLIVAR_H10200, " +
						   "SISTPR_NRO_DEC_UNID, " +
						   "SISTPR_PRECIOS_RECOMEN, " +
						   "SISTPR_PRECIO_CESION, " +
						   "SISTPR_TARIFABASE_PVPS, " +
						   "SISTPR_ACT_PREC_INP, " +
						   "SISTPR_ART_SIN_REQU, " +
						   "SISTPR_FORM_CALC_MINMAX, " +
						   "SISTPR_TARIFA_INICIO_1, " +
						   "SISTPR_TARIFA_INICIO_2, " +
						   "SISTPR_TARIFA_INICIO_3, " +
						   "SISTPR_TARIFA_INICIO_4, " +
						   "SISTPR_TARIFA_INICIO_5, " +
						   "SISTPR_TARIFA_INICIO_6, " +
						   "SISTPR_TARIFA_INICIO_7, " +
						   "SISTPR_TARIFA_INICIO_8, " +
						   "SISTPR_TARIFA_INICIO_9, " +
						   "SISTPR_TARIFA_INICIO_10, " +
						   "SISTPR_TARIFA_INICIO_11, " +
						   "SISTPR_TARIFA_INICIO_12, " +
						   "SISTPR_TARIFA_INICIO_13, " +
						   "SISTPR_TARIFA_INICIO_14, " +
						   "SISTPR_TARIFA_INICIO_15, " +
						   "SISTPR_TARIFA_INICIO_16, " +
						   "SISTPR_TARIFA_INICIO_17, " +
						   "SISTPR_TARIFA_INICIO_18, " +
						   "SISTPR_TARIFA_INICIO_19, " +
						   "SISTPR_TARIFA_INICIO_20, " +
						   "SISTPR_TARIFA_INICIO_21, " +
						   "SISTPR_TARIFA_INICIO_22, " +
						   "SISTPR_TARIFA_INICIO_23, " +
						   "SISTPR_TARIFA_INICIO_24, " +
						   "SISTPR_TARIFA_INICIO_25, " +
						   "SISTPR_TARIFA_INICIO_26, " +
						   "SISTPR_TARIFA_INICIO_27, " +
						   "SISTPR_TARIFA_INICIO_28, " +
						   "SISTPR_TARIFA_INICIO_29, " +
						   "SISTPR_TARIFA_INICIO_30, " +
						   "SISTPR_TARIFA_LONG_1, " +
						   "SISTPR_TARIFA_LONG_2, " +
						   "SISTPR_TARIFA_LONG_3, " +
						   "SISTPR_TARIFA_LONG_4, " +
						   "SISTPR_TARIFA_LONG_5, " +
						   "SISTPR_TARIFA_LONG_6, " +
						   "SISTPR_TARIFA_LONG_7, " +
						   "SISTPR_TARIFA_LONG_8, " +
						   "SISTPR_TARIFA_LONG_9, " +
						   "SISTPR_TARIFA_LONG_10, " +
						   "SISTPR_TARIFA_LONG_11, " +
						   "SISTPR_TARIFA_LONG_12, " +
						   "SISTPR_TARIFA_LONG_13, " +
						   "SISTPR_TARIFA_LONG_14, " +
						   "SISTPR_TARIFA_LONG_15, " +
						   "SISTPR_TARIFA_LONG_16, " +
						   "SISTPR_TARIFA_LONG_17, " +
						   "SISTPR_TARIFA_LONG_18, " +
						   "SISTPR_TARIFA_LONG_19, " +
						   "SISTPR_TARIFA_LONG_20, " +
						   "SISTPR_TARIFA_LONG_21, " +
						   "SISTPR_TARIFA_LONG_22, " +
						   "SISTPR_TARIFA_LONG_23, " +
						   "SISTPR_TARIFA_LONG_24, " +
						   "SISTPR_TARIFA_LONG_25, " +
						   "SISTPR_TARIFA_LONG_26, " +
						   "SISTPR_TARIFA_LONG_27, " +
						   "SISTPR_TARIFA_LONG_28, " +
						   "SISTPR_TARIFA_LONG_29, " +
						   "SISTPR_TARIFA_LONG_30, " +
						   "SISTPR_TARIFA_SISTEMA, " +
						   "SISTPR_TARIFA_LONGREG, " +
						   "SISTPR_UBICAC, " +
						   "SISTPR_UBCMAP, " +
						   "SISTPR_UBCFIJ, " +
						   "SISTPR_PORC_PED_VIRTUAL_101, " +
						   "SISTPR_SQL, " +
						   "SISTCO_FEC_ULT_CIERRE, " +
						   "SISTCO_FEC_ULT_REGPRO, " +
						   "SISTCO_FEC_ULT_DEPMOC, " +
						   "SISTCO_FEC_ULT_INPUCON, " +
						   "SISTCO_FEC_ULT_DECLIVA, " +
						   "SISTCO_FEC_ULT_CIERRE_FAC, " +
						   "SISTCO_PERIODO_DECLIVA, " +
						   "SISTCO_COMPRAS_CONREQU, " +
						   "SISTCO_TEXFDP_1, " +
						   "SISTCO_TEXFDP_2, " +
						   "SISTCO_TEXFDP_3, " +
						   "SISTCO_TEXFDP_4, " +
						   "SISTCO_TEXFDP_5, " +
						   "SISTCO_TEXFDP_6, " +
						   "SISTCO_TEXFDP_7, " +
						   "SISTCO_TEXFDP_8, " +
						   "SISTCO_TEXFDP_91, " +
						   "SISTCO_LIT_IVA_1, " +
						   "SISTCO_LIT_IVA_2, " +
						   "SISTCO_LIT_IVA_3, " +
						   "SISTCO_LIT_IVA_4, " +
						   "SISTCO_POR_IVA_1, " +
						   "SISTCO_POR_IVA_2, " +
						   "SISTCO_POR_IVA_3, " +
						   "SISTCO_POR_IVA_4, " +
						   "SISTCO_POR_REQU_1, " +
						   "SISTCO_POR_REQU_2, " +
						   "SISTCO_POR_REQU_3, " +
						   "SISTCO_POR_REQU_4, " +
						   "SISTCO_ORDEN_IVA, " +
						   "SISTCO_ULT_DOCUM, " +
						   "SISTCO_LIT_IVA_ANT_1, " +
						   "SISTCO_LIT_IVA_ANT_2, " +
						   "SISTCO_LIT_IVA_ANT_3, " +
						   "SISTCO_LIT_IVA_ANT_4, " +
						   "SISTCO_POR_IVA_ANT_1, " +
						   "SISTCO_POR_IVA_ANT_2, " +
						   "SISTCO_POR_IVA_ANT_3, " +
						   "SISTCO_POR_IVA_ANT_4, " +
						   "SISTCO_POR_REQU_ANT_1, " +
						   "SISTCO_POR_REQU_ANT_2, " +
						   "SISTCO_POR_REQU_ANT_3, " +
						   "SISTCO_POR_REQU_ANT_4, " +
						   "SISTCO_ORDEN_IVA_ANT, " +
						   "SISTCO_FECHA_IVA_ANT) " +		   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "SISTEM_NOMBRE = ?, " +
						   "SISTEM_NOM_COMER = ?, " +
						   "SISTEM_LOGOTIPO = ?, " +
						   "SISTEM_DIR = ?, " +
						   "SISTEM_POB = ?, " +
						   "SISTEM_NIF = ?, " +
						   "SISTEM_TLFNO = ?, " +
						   "SISTEM_DATOS_REGISTRO = ?, " +
						   "SISTEM_LOGO_BD = ?, " +
						   "SISTEM_VERSION = ?, " +
						   "SISTEM_FEC_VERS = ?, " +
						   "SISTEM_CHK_HARD = ?, " +
						   "SISTEM_CHK_HASTA_DIA = ?, " +
						   "SISTEM_CHK = ?, " +
						   "SISTEM_FEC_ULT_ENTRADA = ?, " +
						   "SISTEM_ACCESO_BRJ = ?, " +
						   "SISTEM_SIST_OPERATIVO = ?, " +
						   "SISTEM_EMPRESA_B = ?, " +
						   "SISTEM_EMPRESA_CENTRAL = ?, " +
						   "SISTEM_EAN_EMPRESA = ?, " +
						   "SISTEM_SW_ENCRIPT = ?, " +
						   "SISTEM_LIN_LOGO = ?, " +
						   "SISTEM_COL_LOGO = ?, " +
						   "SISTEM_TIPO_EMPRESA = ?, " +
						   "SISTEM_ABIERTA_FACT_B = ?, " +
						   "SISTEM_QIEN_ABRE_FACT_B = ?, " +
						   "SISTEM_CLITES_CLIENT = ?, " +
						   "SISTEM_MASCOMPLEM_ROTOS = ?, " +
						   "SISTEM_LICENCIA = ?, " +
						   "SISTEM_CLT_EN_GRUPO = ?, " +
						   "SISTEM_FEC_INSTALAC = ?, " +
						   "SISTEM_GRUPO = ?, " +
						   "SISTEM_SW_DISP_D7MTA = ?, " +
						   "SISTEM_PORC_AUM_VTAS = ?, " +
						   "SISTPR_PRODUCC = ?, " +
						   "SISTPR_CENTRO_A_CENTRAL = ?, " +
						   "SISTPR_ADMITE_ROTURA_STOCK = ?, " +
						   "SISTPR_SW_ALB_PEDCLI = ?, " +
						   "SISTPR_POSIB_ANULAR = ?, " +
						   "SISTPR_POSIB_DEJAR_PNDT_FACT_B = ?, " +
						   "SISTPR_PEDCLI = ?, " +
						   "SISTPR_PEDPRA = ?, " +
						   "SISTPR_UN_SOLO_PMC = ?, " +
						   "SISTPR_CHK_OFR_DTOS = ?, " +
						   "SISTPR_PEDCLI_NO_FRA = ?, " +
						   "SISTPR_PRECIOS_DE_PEDIDO = ?, " +
						   "SISTPR_DIAS_APLAZ_PEDCLI = ?, " +
						   "SISTPR_EDI = ?, " +
						   "SISTPR_LINES_PAPELST = ?, " +
						   "SISTPR_LONG_CEPTO = ?, " +
						   "SISTPR_PERSON_FACALB = ?, " +
						   "SISTPR_PERSON_RECIBO = ?, " +
						   "SISTPR_PERSON_PAPEL = ?, " +
						   "SISTPR_PRINT_FDP_ALB = ?, " +
						   "SISTPR_PRINT_2DTOS = ?, " +
						   "SISTPR_PRINT_DOC_EAN = ?, " +
						   "SISTPR_IMPRES_ALBS_B = ?, " +
						   "SISTPR_LINES_ALB_B = ?, " +
						   "SISTPR_PRINT_TICKET = ?, " +
						   "SISTPR_FRA_PDF = ?, " +
						   "SISTPR_SUBCTA_INTERNAS = ?, " +
						   "SISTPR_CLIVAR_H10200 = ?, " +
						   "SISTPR_NRO_DEC_UNID = ?, " +
						   "SISTPR_PRECIOS_RECOMEN = ?, " +
						   "SISTPR_PRECIO_CESION = ?, " +
						   "SISTPR_TARIFABASE_PVPS = ?, " +
						   "SISTPR_ACT_PREC_INP = ?, " +
						   "SISTPR_ART_SIN_REQU = ?, " +
						   "SISTPR_FORM_CALC_MINMAX = ?, " +
						   "SISTPR_TARIFA_INICIO_1 = ?, " +
						   "SISTPR_TARIFA_INICIO_2 = ?, " +
						   "SISTPR_TARIFA_INICIO_3 = ?, " +
						   "SISTPR_TARIFA_INICIO_4 = ?, " +
						   "SISTPR_TARIFA_INICIO_5 = ?, " +
						   "SISTPR_TARIFA_INICIO_6 = ?, " +
						   "SISTPR_TARIFA_INICIO_7 = ?, " +
						   "SISTPR_TARIFA_INICIO_8 = ?, " +
						   "SISTPR_TARIFA_INICIO_9 = ?, " +
						   "SISTPR_TARIFA_INICIO_10 = ?, " +
						   "SISTPR_TARIFA_INICIO_11 = ?, " +
						   "SISTPR_TARIFA_INICIO_12 = ?, " +
						   "SISTPR_TARIFA_INICIO_13 = ?, " +
						   "SISTPR_TARIFA_INICIO_14 = ?, " +
						   "SISTPR_TARIFA_INICIO_15 = ?, " +
						   "SISTPR_TARIFA_INICIO_16 = ?, " +
						   "SISTPR_TARIFA_INICIO_17 = ?, " +
						   "SISTPR_TARIFA_INICIO_18 = ?, " +
						   "SISTPR_TARIFA_INICIO_19 = ?, " +
						   "SISTPR_TARIFA_INICIO_20 = ?, " +
						   "SISTPR_TARIFA_INICIO_21 = ?, " +
						   "SISTPR_TARIFA_INICIO_22 = ?, " +
						   "SISTPR_TARIFA_INICIO_23 = ?, " +
						   "SISTPR_TARIFA_INICIO_24 = ?, " +
						   "SISTPR_TARIFA_INICIO_25 = ?, " +
						   "SISTPR_TARIFA_INICIO_26 = ?, " +
						   "SISTPR_TARIFA_INICIO_27 = ?, " +
						   "SISTPR_TARIFA_INICIO_28 = ?, " +
						   "SISTPR_TARIFA_INICIO_29 = ?, " +
						   "SISTPR_TARIFA_INICIO_30 = ?, " +
						   "SISTPR_TARIFA_LONG_1 = ?, " +
						   "SISTPR_TARIFA_LONG_2 = ?, " +
						   "SISTPR_TARIFA_LONG_3 = ?, " +
						   "SISTPR_TARIFA_LONG_4 = ?, " +
						   "SISTPR_TARIFA_LONG_5 = ?, " +
						   "SISTPR_TARIFA_LONG_6 = ?, " +
						   "SISTPR_TARIFA_LONG_7 = ?, " +
						   "SISTPR_TARIFA_LONG_8 = ?, " +
						   "SISTPR_TARIFA_LONG_9 = ?, " +
						   "SISTPR_TARIFA_LONG_10 = ?, " +
						   "SISTPR_TARIFA_LONG_11 = ?, " +
						   "SISTPR_TARIFA_LONG_12 = ?, " +
						   "SISTPR_TARIFA_LONG_13 = ?, " +
						   "SISTPR_TARIFA_LONG_14 = ?, " +
						   "SISTPR_TARIFA_LONG_15 = ?, " +
						   "SISTPR_TARIFA_LONG_16 = ?, " +
						   "SISTPR_TARIFA_LONG_17 = ?, " +
						   "SISTPR_TARIFA_LONG_18 = ?, " +
						   "SISTPR_TARIFA_LONG_19 = ?, " +
						   "SISTPR_TARIFA_LONG_20 = ?, " +
						   "SISTPR_TARIFA_LONG_21 = ?, " +
						   "SISTPR_TARIFA_LONG_22 = ?, " +
						   "SISTPR_TARIFA_LONG_23 = ?, " +
						   "SISTPR_TARIFA_LONG_24 = ?, " +
						   "SISTPR_TARIFA_LONG_25 = ?, " +
						   "SISTPR_TARIFA_LONG_26 = ?, " +
						   "SISTPR_TARIFA_LONG_27 = ?, " +
						   "SISTPR_TARIFA_LONG_28 = ?, " +
						   "SISTPR_TARIFA_LONG_29 = ?, " +
						   "SISTPR_TARIFA_LONG_30 = ?, " +
						   "SISTPR_TARIFA_SISTEMA = ?, " +
						   "SISTPR_TARIFA_LONGREG = ?, " +
						   "SISTPR_UBICAC = ?, " +
						   "SISTPR_UBCMAP = ?, " +
						   "SISTPR_UBCFIJ = ?, " +
						   "SISTPR_PORC_PED_VIRTUAL_101 = ?, " +
						   "SISTPR_SQL = ?, " +
						   "SISTCO_FEC_ULT_CIERRE = ?, " +
						   "SISTCO_FEC_ULT_REGPRO = ?, " +
						   "SISTCO_FEC_ULT_DEPMOC = ?, " +
						   "SISTCO_FEC_ULT_INPUCON = ?, " +
						   "SISTCO_FEC_ULT_DECLIVA = ?, " +
						   "SISTCO_FEC_ULT_CIERRE_FAC = ?, " +
						   "SISTCO_PERIODO_DECLIVA = ?, " +
						   "SISTCO_COMPRAS_CONREQU = ?, " +
						   "SISTCO_TEXFDP_1 = ?, " +
						   "SISTCO_TEXFDP_2 = ?, " +
						   "SISTCO_TEXFDP_3 = ?, " +
						   "SISTCO_TEXFDP_4 = ?, " +
						   "SISTCO_TEXFDP_5 = ?, " +
						   "SISTCO_TEXFDP_6 = ?, " +
						   "SISTCO_TEXFDP_7 = ?, " +
						   "SISTCO_TEXFDP_8 = ?, " +
						   "SISTCO_TEXFDP_91 = ?, " +
						   "SISTCO_LIT_IVA_1 = ?, " +
						   "SISTCO_LIT_IVA_2 = ?, " +
						   "SISTCO_LIT_IVA_3 = ?, " +
						   "SISTCO_LIT_IVA_4 = ?, " +
						   "SISTCO_POR_IVA_1 = ?, " +
						   "SISTCO_POR_IVA_2 = ?, " +
						   "SISTCO_POR_IVA_3 = ?, " +
						   "SISTCO_POR_IVA_4 = ?, " +
						   "SISTCO_POR_REQU_1 = ?, " +
						   "SISTCO_POR_REQU_2 = ?, " +
						   "SISTCO_POR_REQU_3 = ?, " +
						   "SISTCO_POR_REQU_4 = ?, " +
						   "SISTCO_ORDEN_IVA = ?, " +
						   "SISTCO_ULT_DOCUM = ?, " +
						   "SISTCO_LIT_IVA_ANT_1 = ?, " +
						   "SISTCO_LIT_IVA_ANT_2 = ?, " +
						   "SISTCO_LIT_IVA_ANT_3 = ?, " +
						   "SISTCO_LIT_IVA_ANT_4 = ?, " +
						   "SISTCO_POR_IVA_ANT_1 = ?, " +
						   "SISTCO_POR_IVA_ANT_2 = ?, " +
						   "SISTCO_POR_IVA_ANT_3 = ?, " +
						   "SISTCO_POR_IVA_ANT_4 = ?, " +
						   "SISTCO_POR_REQU_ANT_1 = ?, " +
						   "SISTCO_POR_REQU_ANT_2 = ?, " +
						   "SISTCO_POR_REQU_ANT_3 = ?, " +
						   "SISTCO_POR_REQU_ANT_4 = ?, " +
						   "SISTCO_ORDEN_IVA_ANT = ?, " +
						   "SISTCO_FECHA_IVA_ANT = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(nombre, 32));
			ps.setString(i++, Cadena.left(nombreComercial, 32));
			ps.setString(i++, Cadena.left(logotipo, 10));
			ps.setString(i++, Cadena.left(direccion, 32));
			ps.setString(i++, Cadena.left(poblacion, 32));
			ps.setString(i++, Cadena.left(nif, 9));
			ps.setString(i++, Cadena.left(telefono, 15));
			ps.setString(i++, Cadena.left(datosRegistro, 48));
			ps.setInt(i++, logoBd);
			ps.setString(i++, Cadena.left(version, 8));
			ps.setInt(i++, fechaVersion);
			ps.setString(i++, Cadena.left(chkHard, 17));
			ps.setInt(i++, chkHastaDia);
			ps.setString(i++, Cadena.left(chk, 8));
			ps.setInt(i++, fechaUltimaEntrada);
			ps.setString(i++, Cadena.left(accesoBrj, 8));
			ps.setInt(i++, sistemaOperativo);
			ps.setString(i++, Cadena.left(empresaB, 2));
			ps.setString(i++, Cadena.left(empresaCentral, 2));
			ps.setString(i++, Cadena.left(eanEmpresa, 7));
			ps.setInt(i++, swEncript);
			ps.setInt(i++, linLogo);
			ps.setInt(i++, colLogo);
			ps.setInt(i++, tipoEmpresa);
			ps.setInt(i++, abiertaFacturacionB);
			ps.setString(i++, Cadena.left(quienAbreFactB, 8));
			ps.setInt(i++, clitesClient);
			ps.setInt(i++, masComplemRotos);
			ps.setString(i++, Cadena.left(licencia, 10));
			ps.setInt(i++, clienteEnGrupo);
			ps.setInt(i++, fechaInstalacion);
			ps.setString(i++, Cadena.left(grupo, 10));
			ps.setInt(i++, swDispD7mta);
			ps.setInt(i++, porcentjeAumentoVentas);
			ps.setInt(i++, produccion);
			ps.setInt(i++, centroACentral);
			ps.setInt(i++, admiteRoturaStock);
			ps.setInt(i++, swAlbaranPedCliente);
			ps.setInt(i++, posibilidadAnular);
			ps.setInt(i++, posibilidadDejarPendienteFactB);
			ps.setInt(i++, pedidosCliente);
			ps.setInt(i++, pedidosProveedor);
			ps.setInt(i++, unSoloPMC);
			ps.setInt(i++, chkOfertaDescuento);
			ps.setInt(i++, pedidosClienteNoFra);
			ps.setInt(i++, preciosDePedido);
			ps.setInt(i++, diasAplazamientoPedCli);
			ps.setInt(i++, edi);
			ps.setInt(i++, lineasPepelStandard);
			ps.setInt(i++, longitudConcepto);
			ps.setInt(i++, personalizarFacAlb);
			ps.setInt(i++, personalizarRecibo);
			ps.setInt(i++, personalizarPapel);
			ps.setInt(i++, printFormaPagoEnAlbaran);
			ps.setInt(i++, print2Descuentos);
			ps.setInt(i++, printDocEan);
			ps.setString(i++, Cadena.left(impresoraAlbaranesB, 8));
			ps.setInt(i++, lineasAlbaranesB);
			ps.setInt(i++, printTicket);
			ps.setInt(i++, facturaPdf);
			ps.setInt(i++, subcuentaInternas);
			ps.setInt(i++, clivarH10200);
			ps.setInt(i++, numeroDecUnidades);
			ps.setInt(i++, preciosRecomendados);
			ps.setInt(i++, precioCesion);
			ps.setInt(i++, tarifaBaseParaPvp);
			ps.setInt(i++, actualizaPrecioImporte);
			ps.setInt(i++, articuloSinRecargoEqv);
			ps.setInt(i++, formaCalculoMinMax);
			for(int j = 0; j < MAX_TARIFA; j++)
				ps.setInt(i++, tarifaInicio[j]);
			for(int j = 0; j < MAX_TARIFA; j++)
				ps.setInt(i++, tarifaLong[j]);
			ps.setString(i++, Cadena.left(tarifaSistema, 12));
			ps.setInt(i++, tarifaLongitudRegistro);
			ps.setInt(i++, ubicaciones);
			ps.setInt(i++, mapaUbicaciones);
			ps.setInt(i++, ubicacionesFijas);
			ps.setInt(i++, porcentajePedidoVirtual101);
			ps.setInt(i++, sql);
			ps.setInt(i++, fechaUltimoCierre);
			ps.setInt(i++, fechaUltimaRegularizacionProvisional);
			ps.setInt(i++, fechaUltimaDepuracionMovimientos);
			ps.setInt(i++, fechaUltimoInputContable);
			ps.setInt(i++, fechaUltimaDeclaracionIva);
			ps.setInt(i++, fechaUltimoCierreFacturacion);
			ps.setInt(i++, periodoDeclaracionIva);
			ps.setInt(i++, comprasConRecargoEqv);
			for(int j = 0; j < 9; j++)
				ps.setString(i++, Cadena.left(textoFormaDePago[j], 14));
			for(int j = 0; j < 4; j++)
				ps.setString(i++, Cadena.left(literalIva[j], 9));
			for(int j = 0; j < 4; j++)
				ps.setDouble(i++, porcentajeIva[j]);
			for(int j = 0; j < 4; j++)
				ps.setDouble(i++, porcentajeRecargoEquivalencia[j]);
			ps.setInt(i++, ordenIva);
			ps.setInt(i++, ultimoDocumento);
			for(int j = 0; j < 4; j++)
				ps.setString(i++, Cadena.left(literalIvaAnt[j], 9));
			for(int j = 0; j < 4; j++)
				ps.setDouble(i++, porcentajeIvaAnt[j]);
			for(int j = 0; j < 4; j++)
				ps.setDouble(i++, porcentajeRecargoEquivalenciaAnt[j]);
			ps.setInt(i++, ordenIvaAnt);
			ps.setInt(i++, fechaIvaAnt);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(nombre, 32));
			ps.setString(i++, Cadena.left(nombreComercial, 32));
			ps.setString(i++, Cadena.left(logotipo, 10));
			ps.setString(i++, Cadena.left(direccion, 32));
			ps.setString(i++, Cadena.left(poblacion, 32));
			ps.setString(i++, Cadena.left(nif, 9));
			ps.setString(i++, Cadena.left(telefono, 15));
			ps.setString(i++, Cadena.left(datosRegistro, 48));
			ps.setInt(i++, logoBd);
			ps.setString(i++, Cadena.left(version, 8));
			ps.setInt(i++, fechaVersion);
			ps.setString(i++, Cadena.left(chkHard, 17));
			ps.setInt(i++, chkHastaDia);
			ps.setString(i++, Cadena.left(chk, 8));
			ps.setInt(i++, fechaUltimaEntrada);
			ps.setString(i++, Cadena.left(accesoBrj, 8));
			ps.setInt(i++, sistemaOperativo);
			ps.setString(i++, Cadena.left(empresaB, 2));
			ps.setString(i++, Cadena.left(empresaCentral, 2));
			ps.setString(i++, Cadena.left(eanEmpresa, 7));
			ps.setInt(i++, swEncript);
			ps.setInt(i++, linLogo);
			ps.setInt(i++, colLogo);
			ps.setInt(i++, tipoEmpresa);
			ps.setInt(i++, abiertaFacturacionB);
			ps.setString(i++, Cadena.left(quienAbreFactB, 8));
			ps.setInt(i++, clitesClient);
			ps.setInt(i++, masComplemRotos);
			ps.setString(i++, Cadena.left(licencia, 10));
			ps.setInt(i++, clienteEnGrupo);
			ps.setInt(i++, fechaInstalacion);
			ps.setString(i++, Cadena.left(grupo, 10));
			ps.setInt(i++, swDispD7mta);
			ps.setInt(i++, porcentjeAumentoVentas);
			ps.setInt(i++, produccion);
			ps.setInt(i++, centroACentral);
			ps.setInt(i++, admiteRoturaStock);
			ps.setInt(i++, swAlbaranPedCliente);
			ps.setInt(i++, posibilidadAnular);
			ps.setInt(i++, posibilidadDejarPendienteFactB);
			ps.setInt(i++, pedidosCliente);
			ps.setInt(i++, pedidosProveedor);
			ps.setInt(i++, unSoloPMC);
			ps.setInt(i++, chkOfertaDescuento);
			ps.setInt(i++, pedidosClienteNoFra);
			ps.setInt(i++, preciosDePedido);
			ps.setInt(i++, diasAplazamientoPedCli);
			ps.setInt(i++, edi);
			ps.setInt(i++, lineasPepelStandard);
			ps.setInt(i++, longitudConcepto);
			ps.setInt(i++, personalizarFacAlb);
			ps.setInt(i++, personalizarRecibo);
			ps.setInt(i++, personalizarPapel);
			ps.setInt(i++, printFormaPagoEnAlbaran);
			ps.setInt(i++, print2Descuentos);
			ps.setInt(i++, printDocEan);
			ps.setString(i++, Cadena.left(impresoraAlbaranesB, 8));
			ps.setInt(i++, lineasAlbaranesB);
			ps.setInt(i++, printTicket);
			ps.setInt(i++, facturaPdf);
			ps.setInt(i++, subcuentaInternas);
			ps.setInt(i++, clivarH10200);
			ps.setInt(i++, numeroDecUnidades);
			ps.setInt(i++, preciosRecomendados);
			ps.setInt(i++, precioCesion);
			ps.setInt(i++, tarifaBaseParaPvp);
			ps.setInt(i++, actualizaPrecioImporte);
			ps.setInt(i++, articuloSinRecargoEqv);
			ps.setInt(i++, formaCalculoMinMax);
			for(int j = 0; j < MAX_TARIFA; j++)
				ps.setInt(i++, tarifaInicio[j]);
			for(int j = 0; j < MAX_TARIFA; j++)
				ps.setInt(i++, tarifaLong[j]);
			ps.setString(i++, Cadena.left(tarifaSistema, 12));
			ps.setInt(i++, tarifaLongitudRegistro);
			ps.setInt(i++, ubicaciones);
			ps.setInt(i++, mapaUbicaciones);
			ps.setInt(i++, ubicacionesFijas);
			ps.setInt(i++, porcentajePedidoVirtual101);
			ps.setInt(i++, sql);
			ps.setInt(i++, fechaUltimoCierre);
			ps.setInt(i++, fechaUltimaRegularizacionProvisional);
			ps.setInt(i++, fechaUltimaDepuracionMovimientos);
			ps.setInt(i++, fechaUltimoInputContable);
			ps.setInt(i++, fechaUltimaDeclaracionIva);
			ps.setInt(i++, fechaUltimoCierreFacturacion);
			ps.setInt(i++, periodoDeclaracionIva);
			ps.setInt(i++, comprasConRecargoEqv);
			for(int j = 0; j < 9; j++)
				ps.setString(i++, Cadena.left(textoFormaDePago[j], 14));
			for(int j = 0; j < 4; j++)
				ps.setString(i++, Cadena.left(literalIva[j], 9));
			for(int j = 0; j < 4; j++)
				ps.setDouble(i++, porcentajeIva[j]);
			for(int j = 0; j < 4; j++)
				ps.setDouble(i++, porcentajeRecargoEquivalencia[j]);
			ps.setInt(i++, ordenIva);
			ps.setInt(i++, ultimoDocumento);
			for(int j = 0; j < 4; j++)
				ps.setString(i++, Cadena.left(literalIvaAnt[j], 9));
			for(int j = 0; j < 4; j++)
				ps.setDouble(i++, porcentajeIvaAnt[j]);
			for(int j = 0; j < 4; j++)
				ps.setDouble(i++, porcentajeRecargoEquivalenciaAnt[j]);
			ps.setInt(i++, ordenIvaAnt);
			ps.setInt(i++, fechaIvaAnt);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de Sistema!!!");
				e.printStackTrace();
			}
		}
	}


}
