package general;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class DatosComunes {

	static final int MAX_FDP = 7;
	static final int MAX_TIPO_IVA = 4;

	public static boolean enDebug = true;
	public static String eEmpresa;
	public static String nombreEmpresa;
	public static String sisNomComer;
	public static String sisDir;
	public static String sisPob;
	public static String sisTlf;
	public static String sisReg;
	public static int sisSistOperativo;
	public static String sisEmpresaB;
	public static String sisEmpresaCentral;
	public static String sisEanEmpresa;
	public static int sisSwEncrypt;
	public static int sisAbiertaFactB;
	public static String sisQienAbreFactB;
	public static int sisClitesClient;
	public static String sisGrupo;
	public static int sisCltEnGrupo;
	public static int sisMascomplemRotos;
	public static int fecUltCierre;
	public static int fecUltRegpro;
	public static int fecUltInpucon;
	public static int fecUltDepmoc;
	public static int fecUltDecliva;
	public static int fecUltCierreFac;
	public static int sisComprasConrequ;
	public static int sisUltDocum;
	// Los cuatro siguiestes, como si no los ponemos. Son para prevenir
	// 'pirateria'
	public static int masCasaCont;
	public static double porCasaCont;
	public static int masCasaGest;
	public static double porCasaGest;

	public static String textFdp[];
	public static String litIva[];
	public static double porIva[];
	public static double porRequ[];
	public static String sisOrdenIva;
	public static int sisTipoEmpresa;
	public static int sisTienda;
	public static int sisProducc;
	public static int sisCentroACentral;
	public static int sisAdmiteRotura;
	public static int sisSWAlbPedcli;
	public static int sisPosibAnular;
	public static int sisPedcli;
	public static int sisPedpra;
	public static int sisChkOfrDtos;
	public static int sisPedcliNoFra;
	public static int sisPreciosDePedido;
	public static int sisDiasAplazPedcli;
	public static int sisEdi;
	public static int sisDisp72h;
	public static int sisLinesPapelst;
	public static int sisLongCepto;
	public static int sisPersonFacalb;
	public static int sisPersonRecibo;
	public static int sisPersonPapel;
	public static int sisPrintFdpAlb;
	public static int sisPrint2dtos;
	public static int sisPrintDocEan;
	public static String sisImpresAlbsB;
	public static int sisLinesAlbB;
	public static int sisPrintTicket;
	public static int sisSubctaInternas;
	public static int sisClivarH10200;
	public static int sisNroDecUnid;
	public static int sisPreciosRecomen;
	public static int sisPrecioCesion;
	public static int sisTarifabasePvps;
	public static int sisActPrecInp;
	public static int sisArtSinRequ;
	public static int sisFormCalcMinmax;
	public static int sisUnSoloPmc;
	public static int sisFraPdf;
	public static int sisPorcPedVirtual01;
	public static int sisSql;
	public static int nivelAclogi;
	public static String usuario;
	public static int usuarioN;
	public static String usuarioPassword;
	public static int centroCont;
	public static int centroGest;
	public static int nivelAccont;
	public static int nivelAcgest;
	public static String tty;
	public static String programa;
	public static int programaN;
	public static String descrPrograma;
	public static int yaPasoBrj;
	public static int yaPasoUsu;
	public static int yaPasoPrg;
	public static int idioma;
	public static int fechaDia;
	public static int achefDia;
	public static int fechaLlamada;
	public static int llamadaDia;
	public static String linkAlfa;
	public static String linkAlfa2;
	public static String linkAlfa3;
	public static String linkAlfa4;
	public static double linkNum;
	public static double linkNum2;
	public static double linkNum3;
	public static double linkNum4;
	public static int linkFecha;
	public static int linkAchef;
	public static int linkFecha2;
	// Esto no se ha utilizado en años.
	public static int rutPosi;
	public static int rutLine;
	public static int rutEnter;
	public static int rutDecim;
	public static int rutForma;
	public static int rutPuntu;

	public static int ultLv;
	public static int tTime;

	public static int tipoPapel;
	public static int win7Mayor;
	public static int lineasPapel;
	public static int anchoPapel;
	public static int impresNormal;
	public static int impresComprim;
	public static int impresExpand;
	public static int impresDoceavos;
	public static int impresCalidad;
	public static int impresNegrita;
	public static int impresDoblaltu;
	public static int impresSubraya;

	// Constructor

	public DatosComunes(String empresa) {
		// Necesitamos un ResulSet en el que dejar los datos
		// Hay que instanciar la conexión a la base de datos
		ResultSet rs = null;
		MysqlConnect m = null;
		m = MysqlConnect.getDbCon();

		boolean existeReg = false;

		// Por si acaso la empresa siempre en mayúsculas
		empresa = empresa.toUpperCase();
		// Ejecutamos la consulta
		try {
			rs = m.query("SELECT * FROM SISTEM WHERE EMPRESA = '" + empresa
					+ "'");

			while (rs.next() == true) {
				existeReg = true;

				eEmpresa = rs.getString("SISTEM_EMPRESA_CENTRAL");
				nombreEmpresa = rs.getString("SISTEM_NOMBRE");
				sisNomComer = rs.getString("SISTEM_NOM_COMER");
				sisDir = rs.getString("SISTEM_DIR");
				sisPob = rs.getString("SISTEM_POB");
				sisTlf = rs.getString("SISTEM_TLFNO");
				sisReg = rs.getString("SISTEM_DATOS_REGISTRO");
				sisSistOperativo = rs.getInt("SISTEM_SIST_OPERATIVO");
				sisEmpresaB = rs.getString("SISTEM_EMPRESA_B");
				sisEmpresaCentral = rs.getString("SISTEM_EMPRESA_CENTRAL");
				sisEanEmpresa = rs.getString("SISTEM_EAN_EMPRESA");
				// int sisSwEncrypt = rs.getInt("");
				sisAbiertaFactB = rs.getInt("SISTEM_ABIERTA_FACT_B");
				sisQienAbreFactB = rs.getString("SISTEM_QIEN_ABRE_FACT_B");
				sisClitesClient = rs.getInt("SISTEM_CLITES_CLIENT");
				sisGrupo = rs.getString("SISTEM_GRUPO");
				sisCltEnGrupo = rs.getInt("SISTEM_CLT_EN_GRUPO");
				sisMascomplemRotos = rs.getInt("SISTEM_MASCOMPLEM_ROTOS");
				fecUltCierre = rs.getInt("SISTCO_FEC_ULT_CIERRE");
				fecUltRegpro = rs.getInt("SISTCO_FEC_ULT_REGPRO");
				fecUltInpucon = rs.getInt("SISTCO_FEC_ULT_INPUCON");
				fecUltDepmoc = rs.getInt("SISTCO_FEC_ULT_DEPMOC");
				fecUltDecliva = rs.getInt("SISTCO_FEC_ULT_DECLIVA");
				fecUltCierreFac = rs.getInt("SISTCO_FEC_ULT_CIERRE_FAC");
				sisComprasConrequ = rs.getInt("SISTCO_COMPRAS_CONREQU");
				sisUltDocum = rs.getInt("SISTCO_ULT_DOCUM");
				// Los cuatro siguiestes, como si no los ponemos. Son para
				// prevenir 'pirateria'
				// int masCasaCont;
				// double porCasaCont;
				// int masCasaGest;
				// double porCasaGest;

				textFdp = new String[MAX_FDP];
				for (int i = 0; i < MAX_FDP; i++)
					textFdp[i] = "";
				textFdp[0] = rs.getString("SISTCO_TEXFDP_1");
				textFdp[1] = rs.getString("SISTCO_TEXFDP_2");
				textFdp[2] = rs.getString("SISTCO_TEXFDP_3");
				textFdp[3] = rs.getString("SISTCO_TEXFDP_4");
				textFdp[4] = rs.getString("SISTCO_TEXFDP_5");
				textFdp[5] = rs.getString("SISTCO_TEXFDP_6");
				textFdp[6] = rs.getString("SISTCO_TEXFDP_7");

				litIva = new String[MAX_TIPO_IVA];
				for (int i = 0; i < MAX_TIPO_IVA; i++)
					litIva[i] = "";
				litIva[0] = rs.getString("SISTCO_LIT_IVA_1");
				litIva[1] = rs.getString("SISTCO_LIT_IVA_2");
				litIva[2] = rs.getString("SISTCO_LIT_IVA_3");
				litIva[3] = rs.getString("SISTCO_LIT_IVA_4");

				porIva = new double[MAX_TIPO_IVA];
				for (int i = 0; i < MAX_TIPO_IVA; i++)
					porIva[i] = 0.0;
				porIva[0] = rs.getDouble("SISTCO_POR_IVA_1");
				porIva[1] = rs.getDouble("SISTCO_POR_IVA_2");
				porIva[2] = rs.getDouble("SISTCO_POR_IVA_3");
				porIva[3] = rs.getDouble("SISTCO_POR_IVA_4");

				porRequ = new double[MAX_TIPO_IVA];
				for (int i = 0; i < MAX_TIPO_IVA; i++)
					porRequ[i] = 0.0;
				porRequ[0] = rs.getDouble("SISTCO_POR_REQU_1");
				porRequ[1] = rs.getDouble("SISTCO_POR_REQU_2");
				porRequ[2] = rs.getDouble("SISTCO_POR_REQU_3");
				porRequ[3] = rs.getDouble("SISTCO_POR_REQU_4");

				sisOrdenIva = rs.getString("SISTCO_ORDEN_IVA");
				sisTipoEmpresa = rs.getInt("SISTEM_TIPO_EMPRESA");
				if (sisTipoEmpresa == 1)
					sisTienda = 1;
				sisProducc = rs.getInt("SISTPR_PRODUCC");
				sisCentroACentral = rs.getInt("SISTPR_CENTRO_A_CENTRAL");
				sisAdmiteRotura = rs.getInt("SISTPR_ADMITE_ROTURA_STOCK");
				// Repasar
				sisSWAlbPedcli = 0;

				sisPosibAnular = rs.getInt("SISTPR_POSIB_ANULAR");
				sisPedcli = rs.getInt("SISTPR_PEDCLI");
				sisPedpra = rs.getInt("SISTPR_PEDPRA");
				sisChkOfrDtos = rs.getInt("SISTPR_CHK_OFR_DTOS");
				sisPedcliNoFra = rs.getInt("SISTPR_PEDCLI_NO_FRA");
				sisPreciosDePedido = rs.getInt("SISTPR_PRECIOS_DE_PEDIDO");
				sisDiasAplazPedcli = rs.getInt("SISTPR_DIAS_APLAZ_PEDCLI");
				sisEdi = rs.getInt("SISTPR_EDI");
				// Repasar
				// sisDisp72h = rs.getInt("SISTPR_DISP_72H");

				sisLinesPapelst = rs.getInt("SISTPR_LINES_PAPELST");
				sisLongCepto = rs.getInt("SISTPR_LONG_CEPTO");
				sisPersonFacalb = rs.getInt("SISTPR_PERSON_FACALB");
				sisPersonRecibo = rs.getInt("SISTPR_PERSON_RECIBO");
				sisPersonPapel = rs.getInt("SISTPR_PERSON_PAPEL");
				sisPrintFdpAlb = rs.getInt("SISTPR_PRINT_FDP_ALB");
				sisPrint2dtos = rs.getInt("SISTPR_PRINT_2DTOS");
				sisPrintDocEan = rs.getInt("SISTPR_PRINT_DOC_EAN");
				sisImpresAlbsB = rs.getString("SISTPR_IMPRES_ALBS_B");
				sisLinesAlbB = rs.getInt("SISTPR_LINES_ALB_B");
				sisPrintTicket = rs.getInt("SISTPR_PRINT_TICKET");
				sisSubctaInternas = rs.getInt("SISTPR_SUBCTA_INTERNAS");
				sisClivarH10200 = rs.getInt("SISTPR_CLIVAR_H10200");
				sisNroDecUnid = rs.getInt("SISTPR_NRO_DEC_UNID");
				sisPreciosRecomen = rs.getInt("SISTPR_PRECIOS_RECOMEN");
				sisPrecioCesion = rs.getInt("SISTPR_PRECIO_CESION");
				sisTarifabasePvps = rs.getInt("SISTPR_TARIFABASE_PVPS");
				sisActPrecInp = rs.getInt("SISTPR_ACT_PREC_INP");
				sisArtSinRequ = rs.getInt("SISTPR_ART_SIN_REQU");
				sisFormCalcMinmax = rs.getInt("SISTPR_FORM_CALC_MINMAX");
				sisUnSoloPmc = rs.getInt("SISTPR_UN_SOLO_PMC");
				sisFraPdf = rs.getInt("SISTPR_FRA_PDF");
				sisPorcPedVirtual01 = rs.getInt("SISTPR_PORC_PED_VIRTUAL_101");
				sisSql = rs.getInt("SISTPR_SQL");

				// Hay que ponerlos en el programa lanzador de la aplicación
				// !!!!
				usuario = "";
				usuarioN = 0;
				usuarioPassword = "";
				centroCont = 1;
				centroGest = 1;
				nivelAccont = 9;
				nivelAcgest = 9;
				nivelAclogi = 9;
				tty = "";
				programa = "";
				programaN = 0;
				descrPrograma = "Inexistente!!!";
				// yaPasoBrj = rs.getInt("");
				// yaPasoUsu = rs.getInt("");
				// yaPasoPrg = rs.getInt("");
				idioma = 0;

				Date ahora = new Date();
				SimpleDateFormat formateadorYYYYMMDD = new SimpleDateFormat(
						"yyyyMMdd");
				SimpleDateFormat formateadorDDMMYYYY = new SimpleDateFormat(
						"ddMMyyyy");
				fechaDia = Integer.valueOf(formateadorYYYYMMDD.format(ahora));
				achefDia = Integer.valueOf(formateadorDDMMYYYY.format(ahora));

				fechaLlamada = fechaDia;
				llamadaDia = 0;

				// Estos salen del la configuracion de la impresora DATOSIMP
				tipoPapel = 1;
				win7Mayor = 0;
				lineasPapel = 51;
				anchoPapel = 132;

				// No vamos a imprimir en preimpreso con lo que los siguientes
				// no son necesarios.
				// int impresNormal;
				// int impresComprim;
				// int impresExpand;
				// int impresDoceavos;
				// int impresCalidad;
				// int impresNegrita;
				// int impresDoblaltu;
				// int impresSubraya;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (existeReg != true)
			JOptionPane.showMessageDialog(null,
					"No existe la empresa, CREARLA!!!");

	}

}
