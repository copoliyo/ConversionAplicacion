package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;



public class Articulo {
	private String empresa;
	private String indNivel;
	// Que será mejor, String ó char[3]???
	private String marca;
	private int gama;
	private int familia;
	private String articulo;
	private String ean;
	private String codigoAbc;
	private String descrip;
	private int provHabitual;
	private String sustituyeA;
	private String sustituidoPor;
	// Ojito con DOUBLE ó INT
	private double stock;
	private double valExis;
	private double preCoste;
	private double stkInicejer;
	private double valInicejer;
	private double pendRecibir;
	private double asignServir;
	private double disp72h;
	private double stMinimo;
	private double stMaximo;
	private int rotBajmin;
	private int rotStAny;
	private int fecUltCom;
	private double canUltCom;
	private double preUltCom;
	private double preAntCom;
	private int prfrMgfSuper;
	private double prPrvCesion;
	private double prPrvFra;
	private double prPrvRapel;
	// 6
	private double porcCesFra[];
	// 5
	private int formCesFra[];
	// 2
	private double porcFraRapel[];
	private int formFraRapel;
	private double masImpPrFijo;
	private double newPrPrvCesion;
	private double newPrPrvFra;
	private double newPrPrvRapel;
	// 6
	private double newPorcCesFra[];
	// 5
	private int newFormCesFra[];
	// 2
	private double newPorcFraRapel[];
	private int newFormFraRapel;
	private double newfutMasInpPrFijo;
	private double antPrPrvCesion;
	private double antPrPrvFra;
	private double antPrPrvRapel;
	// 6
	private double antPorcCesFra[];
	// 5
	private int antFormCesFra[];
	// 2
	private double antPorcFraRapel[];
	private int antFormFraRapel;
	private double antfutMasImpPrFijo;
	private int diasAntelPed;
	private int diasDemoraServ;
	private int loteServicio;
	private int lotePedido;
	private String campania;
	private int margenSuper;
	// 9
	private double margen[];
	// 6
	private double precVenta[];
	// 6
	private double precNewTarifa[];
	// 6
	private int dto[];
	// 2
	private double pvpTienda[];
	// 2
	private double pvrefTienda[];
	// 2
	private double pvespTienda[];
	private double precomendFabr;
	private int iva;
	private int requ;
	// 3
	private double comision[];
	private String ctaVtas;
	private int tipo;
	private int exclusivo;
	private int fecModif;
	private int fecAlta;
	private int noValstk;
	private int prvrcpFec;
	private double prvrcpCan;

	// Constructores

	public Articulo(){
		empresa = DatosComunes.eEmpresa;
		indNivel = "";
		// Que será mejor, String ó char[3]???
		marca = "";
		gama = familia = 0;		
		articulo = ean = "";
		codigoAbc = "";
		descrip = "";
		provHabitual = 0;
		sustituyeA = sustituidoPor = "";
		// Ojito con DOUBLE ó INT
		stock = valExis = preCoste = stkInicejer = valInicejer =
			pendRecibir = asignServir = disp72h = stMinimo = stMaximo = 0.0;
		rotBajmin = rotStAny = fecUltCom = 0;
		canUltCom = preUltCom = preAntCom = 0.0;
		prfrMgfSuper = 0;
		prPrvCesion = prPrvFra = prPrvRapel = 0.0;
		// 6
		porcCesFra = new double[6];
		for(int i = 0; i < 6; i++)
			porcCesFra[i] = 0.0;

		// 5
		formCesFra = new int[5];
		for(int i = 0 ; i < 5; i++)
			formCesFra[i] = 0;
		// 2
		porcFraRapel = new double[2];
		for(int i= 0; i < 2; i ++)
			porcFraRapel[i] = 0.0;

		formFraRapel = 0;

		masImpPrFijo = newPrPrvCesion = newPrPrvFra = newPrPrvRapel = 0.0;

		// 6
		newPorcCesFra = new double[6];
		for(int i = 0; i < 6; i++)
			newPorcCesFra[i] = 0.0;
		// 5
		newFormCesFra = new int[5];
		for(int i = 0; i < 5; i++)
			newFormCesFra[i] = 0;
		// 2
		newPorcFraRapel = new double[2];
		for(int i = 0; i < 2; i++)
			newPorcFraRapel[i] = 0.0;

		newFormFraRapel = 0;
		newfutMasInpPrFijo = antPrPrvCesion = antPrPrvFra = antPrPrvRapel = 0.0;
		// 6
		antPorcCesFra = new double[6];
		for(int i = 0; i < 6; i++)
			antPorcCesFra[i] = 0.0;
		// 5
		antFormCesFra = new int[5];
		for(int i = 0; i < 5; i++)
			antFormCesFra[i] = 0;
		// 2
		antPorcFraRapel = new double[2];
		for(int i = 0; i < 2; i++)
			antPorcFraRapel[i] = 0.0;

		antFormFraRapel = 0;
		antfutMasImpPrFijo = 0.0;
		diasAntelPed = diasDemoraServ = loteServicio = lotePedido = 0;
		campania = "";
		margenSuper = 0;
		// 9
		margen = new double[9];
		for(int i = 0; i < 9; i++)
			margen[i] = 0.0;
		// 6
		precVenta = new double[6];
		for(int i = 0; i < 6; i++)
			precVenta[i] = 0.0;
		// 6
		precNewTarifa= new double[6];
		for(int i = 0; i < 6; i++)
			precNewTarifa[i] = 0.0;
		// 6
		dto = new int[6];
		for(int i = 0; i < 6; i++)
			dto[i] = 0;
		// 2
		pvpTienda = new double[2];
		for(int i = 0; i < 2; i++)
			pvpTienda[i] = 0;
		// 2
		pvrefTienda = new double[2];
		for(int i = 0; i < 2; i++)
			pvrefTienda[i] = 0.0;
		// 2
		pvespTienda = new double[2];
		for(int i = 0; i < 2; i++)
			pvespTienda[i] = 0.0;

		precomendFabr = 0.0; 
		iva = requ = 0;
		// 3
		comision = new double[3];
		for(int i = 0; i < 3; i++)
			comision[i] = 0.0;

		ctaVtas = "";
		tipo = exclusivo = fecModif = fecAlta = noValstk = prvrcpFec = 0;
		prvrcpCan = 0.0;
	}

	public Articulo(ResultSet rs){

		try{
			if(rs.next() == true){
				empresa = rs.getString("EMPRESA").trim();
				indNivel = rs.getString("ARTCLO_IND_NIVEL").trim();
				// Que será mejor, String ó char[3]???
				marca = rs.getString("ARTCLO_MARCA").trim();
				gama = rs.getInt("ARTCLO_GAMA");
				familia = rs.getInt("ARTCLO_FAMILIA");		
				articulo = rs.getString("ARTCLO_ARTICULO").trim();
				ean = rs.getString("ARTCLO_EAN").trim();
				codigoAbc = rs.getString("ARTCLO_CODIGO_ABC").trim();
				descrip = rs.getString("ARTCLO_DESCRIP").trim();
				provHabitual = rs.getInt("ARTCLO_PROV_HABITUAL");
				sustituyeA = rs.getString("ARTCLO_SUSTITUYE_A");
				sustituidoPor = rs.getString("ARTCLO_SUSTITUIDO_POR").trim();
				// Ojito con DOUBLE ó INT
				stock = rs.getDouble("ARTCLO_STOCK");
				valExis = rs.getDouble("ARTCLO_VAL_EXIS"); 
				preCoste = rs.getDouble("ARTCLO_PRE_COSTE");
				stkInicejer = rs.getDouble("ARTCLO_STK_INICEJER");
				valInicejer = rs.getDouble("ARTCLO_VAL_INICEJER");
				pendRecibir = rs.getDouble("ARTCLO_PEND_RECIBIR");
				asignServir = rs.getDouble("ARTCLO_ASIG_SERVIR");
				disp72h = rs.getDouble("ARTCLO_DISP_72H");
				stMinimo = rs.getDouble("ARTCLO_ST_MINIMO");
				stMaximo = rs.getDouble("ARTCLO_ST_MAXIMO");
				rotBajmin = rs.getInt("ARTCLO_ROT_BAJMIN");
				rotStAny = rs.getInt("ARTCLO_ROT_ST_ANY");
				fecUltCom = rs.getInt("ARTCLO_FEC_ULT_COM");
				canUltCom = rs.getDouble("ARTCLO_CAN_ULT_COM");
				preUltCom = rs.getDouble("ARTCLO_PRE_ULT_COM");
				preAntCom = rs.getDouble("ARTCLO_PRE_ANT_COM");;
				prfrMgfSuper = rs.getInt("ARTCLO_PRFR_MGF_SUPER");
				prPrvCesion = rs.getDouble("ARTCLO_PR_PRV_CESION");
				prPrvFra = rs.getDouble("ARTCLO_PR_PRV_FRA");
				prPrvRapel = rs.getDouble("ARTCLO_PR_PRV_RAPEL");
				// 6
				porcCesFra = new double[6];				
				porcCesFra[0] = rs.getDouble("ARTCLO_PORC_CES_FRA_1");
				porcCesFra[1] = rs.getDouble("ARTCLO_PORC_CES_FRA_2");
				porcCesFra[2] = rs.getDouble("ARTCLO_PORC_CES_FRA_3");
				porcCesFra[3] = rs.getDouble("ARTCLO_PORC_CES_FRA_4");
				porcCesFra[4] = rs.getDouble("ARTCLO_PORC_CES_FRA_5");
				porcCesFra[5] = rs.getDouble("ARTCLO_PORC_CES_FRA_6");

				// 5
				formCesFra = new int[5];				
				formCesFra[0] = rs.getInt("ARTCLO_FORM_CES_FRA_1");
				formCesFra[1] = rs.getInt("ARTCLO_FORM_CES_FRA_2");
				formCesFra[2] = rs.getInt("ARTCLO_FORM_CES_FRA_3");
				formCesFra[3] = rs.getInt("ARTCLO_FORM_CES_FRA_4");
				formCesFra[4] = rs.getInt("ARTCLO_FORM_CES_FRA_5");

				// 2
				porcFraRapel = new double[2];				
				porcFraRapel[0] = rs.getDouble("ARTCLO_PORC_FRA_RAPEL_1");
				porcFraRapel[1] = rs.getDouble("ARTCLO_PORC_FRA_RAPEL_2");

				formFraRapel = rs.getInt("ARTCLO_FORM_FRA_RAPEL");

				masImpPrFijo = rs.getDouble("ARTCLO_MAS_IMP_PR_FIJO");
				newPrPrvCesion = rs.getDouble("ARTCLO_NEW_PR_PRV_CESION");
				newPrPrvFra = rs.getDouble("ARTCLO_NEW_PR_PRV_FRA");
				newPrPrvRapel = rs.getDouble("ARTCLO_NEW_PR_PRV_RAPEL");

				// 6
				newPorcCesFra = new double[6];				
				newPorcCesFra[0] = rs.getDouble("ARTCLO_NEW_PORC_CES_FRA_1");
				newPorcCesFra[1] = rs.getDouble("ARTCLO_NEW_PORC_CES_FRA_2");
				newPorcCesFra[2] = rs.getDouble("ARTCLO_NEW_PORC_CES_FRA_3");
				newPorcCesFra[3] = rs.getDouble("ARTCLO_NEW_PORC_CES_FRA_4");
				newPorcCesFra[4] = rs.getDouble("ARTCLO_NEW_PORC_CES_FRA_5");
				newPorcCesFra[5] = rs.getDouble("ARTCLO_NEW_PORC_CES_FRA_6");

				// 5
				newFormCesFra = new int[5];				
				newFormCesFra[0] = rs.getInt("ARTCLO_NEW_FORM_CES_FRA_1");
				newFormCesFra[1] = rs.getInt("ARTCLO_NEW_FORM_CES_FRA_2");
				newFormCesFra[2] = rs.getInt("ARTCLO_NEW_FORM_CES_FRA_3");
				newFormCesFra[3] = rs.getInt("ARTCLO_NEW_FORM_CES_FRA_4");
				newFormCesFra[4] = rs.getInt("ARTCLO_NEW_FORM_CES_FRA_5");

				// 2
				newPorcFraRapel = new double[2];				
				newPorcFraRapel[0] = rs.getDouble("ARTCLO_NEW_PORC_FRA_RAPEL_1");
				newPorcFraRapel[1] = rs.getDouble("ARTCLO_NEW_PORC_FRA_RAPEL_2");

				newFormFraRapel = rs.getInt("ARTCLO_NEW_FORM_FRA_RAPEL");
				newfutMasInpPrFijo = rs.getDouble("ARTCLO_NEWFUT_MAS_IMP_PR_FIJO");
				antPrPrvCesion = rs.getDouble("ARTCLO_ANT_PR_PRV_CESION");
				antPrPrvFra = rs.getDouble("ARTCLO_ANT_PR_PRV_FRA");
				antPrPrvRapel = rs.getDouble("ARTCLO_ANT_PR_PRV_RAPEL");
				// 6
				antPorcCesFra = new double[6];				
				antPorcCesFra[0] = rs.getDouble("ARTCLO_ANT_PORC_CES_FRA_1");
				antPorcCesFra[1] = rs.getDouble("ARTCLO_ANT_PORC_CES_FRA_2");
				antPorcCesFra[2] = rs.getDouble("ARTCLO_ANT_PORC_CES_FRA_3");
				antPorcCesFra[3] = rs.getDouble("ARTCLO_ANT_PORC_CES_FRA_4");
				antPorcCesFra[4] = rs.getDouble("ARTCLO_ANT_PORC_CES_FRA_5");
				antPorcCesFra[5] = rs.getDouble("ARTCLO_ANT_PORC_CES_FRA_6");

				// 5
				antFormCesFra = new int[5];				
				antFormCesFra[0] = rs.getInt("ARTCLO_ANT_FORM_CES_FRA_1");
				antFormCesFra[1] = rs.getInt("ARTCLO_ANT_FORM_CES_FRA_2");
				antFormCesFra[2] = rs.getInt("ARTCLO_ANT_FORM_CES_FRA_3");
				antFormCesFra[3] = rs.getInt("ARTCLO_ANT_FORM_CES_FRA_4");
				antFormCesFra[4] = rs.getInt("ARTCLO_ANT_FORM_CES_FRA_5");

				// 2
				antPorcFraRapel = new double[2];
				antPorcFraRapel[0] = rs.getDouble("ARTCLO_ANT_PORC_FRA_RAPEL_1");
				antPorcFraRapel[1] = rs.getDouble("ARTCLO_ANT_PORC_FRA_RAPEL_2");

				antFormFraRapel = rs.getInt("ARTCLO_ANT_FORM_FRA_RAPEL");
				antfutMasImpPrFijo = rs.getDouble("ARTCLO_ANTFUT_MAS_IMP_PR_FIJO");
				diasAntelPed = rs.getInt("ARTCLO_DIAS_ANTEL_PED");
				diasDemoraServ = rs.getInt("ARTCLO_DIAS_DEMORA_SERV");
				loteServicio = rs.getInt("ARTCLO_LOTE_SERVICIO");
				lotePedido = rs.getInt("ARTCLO_LOTE_PEDIDO");
				campania = rs.getString("ARTCLO_CAMPANIA").trim();
				margenSuper = rs.getInt("ARTCLO_MARGEN_SUPER");
				// 9
				margen = new double[9];				
				margen[0] = rs.getDouble("ARTCLO_MARGEN_1");
				margen[1] = rs.getDouble("ARTCLO_MARGEN_2");
				margen[2] = rs.getDouble("ARTCLO_MARGEN_3");
				margen[3] = rs.getDouble("ARTCLO_MARGEN_4");
				margen[4] = rs.getDouble("ARTCLO_MARGEN_5");
				margen[5] = rs.getDouble("ARTCLO_MARGEN_6");
				margen[6] = rs.getDouble("ARTCLO_MARGEN_7");
				margen[7] = rs.getDouble("ARTCLO_MARGEN_8");
				margen[8] = rs.getDouble("ARTCLO_MARGEN_9");

				// 6
				precVenta = new double[6];				
				precVenta[0] = rs.getDouble("ARTCLO_PREC_VENTA_1");
				precVenta[1] = rs.getDouble("ARTCLO_PREC_VENTA_2");
				precVenta[2] = rs.getDouble("ARTCLO_PREC_VENTA_3");
				precVenta[3] = rs.getDouble("ARTCLO_PREC_VENTA_4");
				precVenta[4] = rs.getDouble("ARTCLO_PREC_VENTA_5");
				precVenta[5] = rs.getDouble("ARTCLO_PREC_VENTA_6");

				// 6
				precNewTarifa= new double[6];				
				precNewTarifa[0] = rs.getDouble("ARTCLO_PREC_NEW_TARIF_1");
				precNewTarifa[1] = rs.getDouble("ARTCLO_PREC_NEW_TARIF_2");
				precNewTarifa[2] = rs.getDouble("ARTCLO_PREC_NEW_TARIF_3");
				precNewTarifa[3] = rs.getDouble("ARTCLO_PREC_NEW_TARIF_4");
				precNewTarifa[4] = rs.getDouble("ARTCLO_PREC_NEW_TARIF_5");
				precNewTarifa[5] = rs.getDouble("ARTCLO_PREC_NEW_TARIF_6");

				// 6
				dto = new int[6];				
				dto[0] = rs.getInt("ARTCLO_DTO_1");
				dto[1] = rs.getInt("ARTCLO_DTO_2");
				dto[2] = rs.getInt("ARTCLO_DTO_3");
				dto[3] = rs.getInt("ARTCLO_DTO_4");
				dto[4] = rs.getInt("ARTCLO_DTO_5");
				dto[5] = rs.getInt("ARTCLO_DTO_6");

				// 2
				pvpTienda = new double[2];				
				pvpTienda[0] = rs.getDouble("ARTCLO_PVP_TIENDA_1");
				pvpTienda[1] = rs.getDouble("ARTCLO_PVP_TIENDA_2");

				// 2
				pvrefTienda = new double[2];				
				pvrefTienda[0] = rs.getDouble("ARTCLO_PVREF_TIENDA_1");
				pvrefTienda[1] = rs.getDouble("ARTCLO_PVREF_TIENDA_2");

				// 2
				pvespTienda = new double[2];				
				pvespTienda[0] = rs.getDouble("ARTCLO_PVESP_TIENDA_1");
				pvespTienda[1] = rs.getDouble("ARTCLO_PVESP_TIENDA_2");

				precomendFabr = rs.getDouble("ARTCLO_PRECOMEND_FABR"); 
				iva = rs.getInt("ARTCLO_IVA");
				requ = rs.getInt("ARTCLO_REQU");
				// 3
				comision = new double[3];				
				comision[0] = rs.getDouble("ARTCLO_COMISION_1");
				comision[1] = rs.getDouble("ARTCLO_COMISION_2");
				comision[2] = rs.getDouble("ARTCLO_COMISION_3");

				ctaVtas = rs.getString("ARTCLO_CTA_VTAS").trim();
				tipo = rs.getInt("ARTCLO_TIPO");
				exclusivo = rs.getInt("ARTCLO_EXCLUSIVO");
				fecModif = rs.getInt("ARTCLO_FEC_MODIF");
				fecAlta = rs.getInt("ARTCLO_FEC_ALTA");
				noValstk = rs.getInt("ARTCLO_NO_VALTSTK");
				prvrcpFec = rs.getInt("ARTCLO_PRVRCP_FEC");
				prvrcpCan = rs.getDouble("ARTCLO_PRVRCP_CAN");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de Articulo!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}					
	}

	public void read(ResultSet rs){

		try{
			empresa = rs.getString("EMPRESA").trim();
			indNivel = rs.getString("ARTCLO_IND_NIVEL").trim();
			// Que será mejor, String ó char[3]???
			marca = rs.getString("ARTCLO_MARCA").trim();
			gama = rs.getInt("ARTCLO_GAMA");
			familia = rs.getInt("ARTCLO_FAMILIA");		
			articulo = rs.getString("ARTCLO_ARTICULO").trim();
			ean = rs.getString("ARTCLO_EAN").trim();
			codigoAbc = rs.getString("ARTCLO_CODIGO_ABC").trim();
			descrip = rs.getString("ARTCLO_DESCRIP").trim();
			provHabitual = rs.getInt("ARTCLO_PROV_HABITUAL");
			sustituyeA = rs.getString("ARTCLO_SUSTITUYE_A");
			sustituidoPor = rs.getString("ARTCLO_SUSTITUIDO_POR").trim();
			// Ojito con DOUBLE ó INT
			stock = rs.getDouble("ARTCLO_STOCK");
			valExis = rs.getDouble("ARTCLO_VAL_EXIS"); 
			preCoste = rs.getDouble("ARTCLO_PRE_COSTE");
			stkInicejer = rs.getDouble("ARTCLO_STK_INICEJER");
			valInicejer = rs.getDouble("ARTCLO_VAL_INICEJER");
			pendRecibir = rs.getDouble("ARTCLO_PEND_RECIBIR");
			asignServir = rs.getDouble("ARTCLO_ASIG_SERVIR");
			disp72h = rs.getDouble("ARTCLO_DISP_72H");
			stMinimo = rs.getDouble("ARTCLO_ST_MINIMO");
			stMaximo = rs.getDouble("ARTCLO_ST_MAXIMO");
			rotBajmin = rs.getInt("ARTCLO_ROT_BAJMIN");
			rotStAny = rs.getInt("ARTCLO_ROT_ST_ANY");
			fecUltCom = rs.getInt("ARTCLO_FEC_ULT_COM");
			canUltCom = rs.getDouble("ARTCLO_CAN_ULT_COM");
			preUltCom = rs.getDouble("ARTCLO_PRE_ULT_COM");
			preAntCom = rs.getDouble("ARTCLO_PRE_ANT_COM");;
			prfrMgfSuper = rs.getInt("ARTCLO_PRFR_MGF_SUPER");
			prPrvCesion = rs.getDouble("ARTCLO_PR_PRV_CESION");
			prPrvFra = rs.getDouble("ARTCLO_PR_PRV_FRA");
			prPrvRapel = rs.getDouble("ARTCLO_PR_PRV_RAPEL");
			// 6		
			porcCesFra[0] = rs.getDouble("ARTCLO_PORC_CES_FRA_1");
			porcCesFra[1] = rs.getDouble("ARTCLO_PORC_CES_FRA_2");
			porcCesFra[2] = rs.getDouble("ARTCLO_PORC_CES_FRA_3");
			porcCesFra[3] = rs.getDouble("ARTCLO_PORC_CES_FRA_4");
			porcCesFra[4] = rs.getDouble("ARTCLO_PORC_CES_FRA_5");
			porcCesFra[5] = rs.getDouble("ARTCLO_PORC_CES_FRA_6");

			// 5				
			formCesFra[0] = rs.getInt("ARTCLO_FORM_CES_FRA_1");
			formCesFra[1] = rs.getInt("ARTCLO_FORM_CES_FRA_2");
			formCesFra[2] = rs.getInt("ARTCLO_FORM_CES_FRA_3");
			formCesFra[3] = rs.getInt("ARTCLO_FORM_CES_FRA_4");
			formCesFra[4] = rs.getInt("ARTCLO_FORM_CES_FRA_5");

			// 2				
			porcFraRapel[0] = rs.getDouble("ARTCLO_PORC_FRA_RAPEL_1");
			porcFraRapel[1] = rs.getDouble("ARTCLO_PORC_FRA_RAPEL_2");

			formFraRapel = rs.getInt("ARTCLO_FORM_FRA_RAPEL");

			masImpPrFijo = rs.getDouble("ARTCLO_MAS_IMP_PR_FIJO");
			newPrPrvCesion = rs.getDouble("ARTCLO_NEW_PR_PRV_CESION");
			newPrPrvFra = rs.getDouble("ARTCLO_NEW_PR_PRV_FRA");
			newPrPrvRapel = rs.getDouble("ARTCLO_NEW_PR_PRV_RAPEL");

			// 6				
			newPorcCesFra[0] = rs.getDouble("ARTCLO_NEW_PORC_CES_FRA_1");
			newPorcCesFra[1] = rs.getDouble("ARTCLO_NEW_PORC_CES_FRA_2");
			newPorcCesFra[2] = rs.getDouble("ARTCLO_NEW_PORC_CES_FRA_3");
			newPorcCesFra[3] = rs.getDouble("ARTCLO_NEW_PORC_CES_FRA_4");
			newPorcCesFra[4] = rs.getDouble("ARTCLO_NEW_PORC_CES_FRA_5");
			newPorcCesFra[5] = rs.getDouble("ARTCLO_NEW_PORC_CES_FRA_6");

			// 5				
			newFormCesFra[0] = rs.getInt("ARTCLO_NEW_FORM_CES_FRA_1");
			newFormCesFra[1] = rs.getInt("ARTCLO_NEW_FORM_CES_FRA_2");
			newFormCesFra[2] = rs.getInt("ARTCLO_NEW_FORM_CES_FRA_3");
			newFormCesFra[3] = rs.getInt("ARTCLO_NEW_FORM_CES_FRA_4");
			newFormCesFra[4] = rs.getInt("ARTCLO_NEW_FORM_CES_FRA_5");

			// 2				
			newPorcFraRapel[0] = rs.getDouble("ARTCLO_NEW_PORC_FRA_RAPEL_1");
			newPorcFraRapel[1] = rs.getDouble("ARTCLO_NEW_PORC_FRA_RAPEL_2");

			newFormFraRapel = rs.getInt("ARTCLO_NEW_FORM_FRA_RAPEL");
			newfutMasInpPrFijo = rs.getDouble("ARTCLO_NEWFUT_MAS_IMP_PR_FIJO");
			antPrPrvCesion = rs.getDouble("ARTCLO_ANT_PR_PRV_CESION");
			antPrPrvFra = rs.getDouble("ARTCLO_ANT_PR_PRV_FRA");
			antPrPrvRapel = rs.getDouble("ARTCLO_ANT_PR_PRV_RAPEL");
			// 6				
			antPorcCesFra[0] = rs.getDouble("ARTCLO_ANT_PORC_CES_FRA_1");
			antPorcCesFra[1] = rs.getDouble("ARTCLO_ANT_PORC_CES_FRA_2");
			antPorcCesFra[2] = rs.getDouble("ARTCLO_ANT_PORC_CES_FRA_3");
			antPorcCesFra[3] = rs.getDouble("ARTCLO_ANT_PORC_CES_FRA_4");
			antPorcCesFra[4] = rs.getDouble("ARTCLO_ANT_PORC_CES_FRA_5");
			antPorcCesFra[5] = rs.getDouble("ARTCLO_ANT_PORC_CES_FRA_6");

			// 5				
			antFormCesFra[0] = rs.getInt("ARTCLO_ANT_FORM_CES_FRA_1");
			antFormCesFra[1] = rs.getInt("ARTCLO_ANT_FORM_CES_FRA_2");
			antFormCesFra[2] = rs.getInt("ARTCLO_ANT_FORM_CES_FRA_3");
			antFormCesFra[3] = rs.getInt("ARTCLO_ANT_FORM_CES_FRA_4");
			antFormCesFra[4] = rs.getInt("ARTCLO_ANT_FORM_CES_FRA_5");

			// 2
			antPorcFraRapel[0] = rs.getDouble("ARTCLO_ANT_PORC_FRA_RAPEL_1");
			antPorcFraRapel[1] = rs.getDouble("ARTCLO_ANT_PORC_FRA_RAPEL_2");

			antFormFraRapel = rs.getInt("ARTCLO_ANT_FORM_FRA_RAPEL");
			antfutMasImpPrFijo = rs.getDouble("ARTCLO_ANTFUT_MAS_IMP_PR_FIJO");
			diasAntelPed = rs.getInt("ARTCLO_DIAS_ANTEL_PED");
			diasDemoraServ = rs.getInt("ARTCLO_DIAS_DEMORA_SERV");
			loteServicio = rs.getInt("ARTCLO_LOTE_SERVICIO");
			lotePedido = rs.getInt("ARTCLO_LOTE_PEDIDO");
			campania = rs.getString("ARTCLO_CAMPANIA").trim();
			margenSuper = rs.getInt("ARTCLO_MARGEN_SUPER");
			// 9				
			margen[0] = rs.getDouble("ARTCLO_MARGEN_1");
			margen[1] = rs.getDouble("ARTCLO_MARGEN_2");
			margen[2] = rs.getDouble("ARTCLO_MARGEN_3");
			margen[3] = rs.getDouble("ARTCLO_MARGEN_4");
			margen[4] = rs.getDouble("ARTCLO_MARGEN_5");
			margen[5] = rs.getDouble("ARTCLO_MARGEN_6");
			margen[6] = rs.getDouble("ARTCLO_MARGEN_7");
			margen[7] = rs.getDouble("ARTCLO_MARGEN_8");
			margen[8] = rs.getDouble("ARTCLO_MARGEN_9");

			// 6				
			precVenta[0] = rs.getDouble("ARTCLO_PREC_VENTA_1");
			precVenta[1] = rs.getDouble("ARTCLO_PREC_VENTA_2");
			precVenta[2] = rs.getDouble("ARTCLO_PREC_VENTA_3");
			precVenta[3] = rs.getDouble("ARTCLO_PREC_VENTA_4");
			precVenta[4] = rs.getDouble("ARTCLO_PREC_VENTA_5");
			precVenta[5] = rs.getDouble("ARTCLO_PREC_VENTA_6");

			// 6				
			precNewTarifa[0] = rs.getDouble("ARTCLO_PREC_NEW_TARIF_1");
			precNewTarifa[1] = rs.getDouble("ARTCLO_PREC_NEW_TARIF_2");
			precNewTarifa[2] = rs.getDouble("ARTCLO_PREC_NEW_TARIF_3");
			precNewTarifa[3] = rs.getDouble("ARTCLO_PREC_NEW_TARIF_4");
			precNewTarifa[4] = rs.getDouble("ARTCLO_PREC_NEW_TARIF_5");
			precNewTarifa[5] = rs.getDouble("ARTCLO_PREC_NEW_TARIF_6");

			// 6				
			dto[0] = rs.getInt("ARTCLO_DTO_1");
			dto[1] = rs.getInt("ARTCLO_DTO_2");
			dto[2] = rs.getInt("ARTCLO_DTO_3");
			dto[3] = rs.getInt("ARTCLO_DTO_4");
			dto[4] = rs.getInt("ARTCLO_DTO_5");
			dto[5] = rs.getInt("ARTCLO_DTO_6");

			// 2				
			pvpTienda[0] = rs.getDouble("ARTCLO_PVP_TIENDA_1");
			pvpTienda[1] = rs.getDouble("ARTCLO_PVP_TIENDA_2");

			// 2				
			pvrefTienda[0] = rs.getDouble("ARTCLO_PVREF_TIENDA_1");
			pvrefTienda[1] = rs.getDouble("ARTCLO_PVREF_TIENDA_2");

			// 2				
			pvespTienda[0] = rs.getDouble("ARTCLO_PVESP_TIENDA_1");
			pvespTienda[1] = rs.getDouble("ARTCLO_PVESP_TIENDA_2");

			precomendFabr = rs.getDouble("ARTCLO_PRECOMEND_FABR"); 
			iva = rs.getInt("ARTCLO_IVA");
			requ = rs.getInt("ARTCLO_REQU");
			// 3				
			comision[0] = rs.getDouble("ARTCLO_COMISION_1");
			comision[1] = rs.getDouble("ARTCLO_COMISION_2");
			comision[2] = rs.getDouble("ARTCLO_COMISION_3");

			ctaVtas = rs.getString("ARTCLO_CTA_VTAS").trim();
			tipo = rs.getInt("ARTCLO_TIPO");
			exclusivo = rs.getInt("ARTCLO_EXCLUSIVO");
			fecModif = rs.getInt("ARTCLO_FEC_MODIF");
			fecAlta = rs.getInt("ARTCLO_FEC_ALTA");
			noValstk = rs.getInt("ARTCLO_NO_VALTSTK");
			prvrcpFec = rs.getInt("ARTCLO_PRVRCP_FEC");
			prvrcpCan = rs.getDouble("ARTCLO_PRVRCP_CAN");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de Articulo!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}					
	}
	
	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO ARTCLO " +
						   "(EMPRESA, " +
						   "ARTCLO_IND_NIVEL, " +
						   "ARTCLO_MARCA, " +
						   "ARTCLO_GAMA, " +
						   "ARTCLO_FAMILIA, " +
						   "ARTCLO_ARTICULO, " +
						   "ARTCLO_EAN, " +
						   "ARTCLO_CODIGO_ABC, " +
						   "ARTCLO_DESCRIP, " +
						   "ARTCLO_PROV_HABITUAL, " +
						   "ARTCLO_SUSTITUYE_A, " +
						   "ARTCLO_SUSTITUIDO_POR, " +
						   "ARTCLO_STOCK, " +
						   "ARTCLO_VAL_EXIS, " +
						   "ARTCLO_PRE_COSTE, " +
						   "ARTCLO_STK_INICEJER, " +
						   "ARTCLO_VAL_INICEJER, " +
						   "ARTCLO_PEND_RECIBIR, " +
						   "ARTCLO_ASIG_SERVIR, " +
						   "ARTCLO_DISP_72H, " +
						   "ARTCLO_ST_MINIMO, " +
						   "ARTCLO_ST_MAXIMO, " +
						   "ARTCLO_ROT_BAJMIN, " +
						   "ARTCLO_ROT_ST_ANY, " +
						   "ARTCLO_FEC_ULT_COM, " +
						   "ARTCLO_CAN_ULT_COM, " +
						   "ARTCLO_PRE_ULT_COM, " +
						   "ARTCLO_PRE_ANT_COM, " +
						   "ARTCLO_PRFR_MGF_SUPER, " +
						   "ARTCLO_PR_PRV_CESION, " +
						   "ARTCLO_PR_PRV_FRA, " +
						   "ARTCLO_PR_PRV_RAPEL, " +
						   "ARTCLO_PORC_CES_FRA_1, " +
						   "ARTCLO_PORC_CES_FRA_2, " +
						   "ARTCLO_PORC_CES_FRA_3, " +
						   "ARTCLO_PORC_CES_FRA_4, " +
						   "ARTCLO_PORC_CES_FRA_5, " +
						   "ARTCLO_PORC_CES_FRA_6, " +
						   "ARTCLO_FORM_CES_FRA_1, " +
						   "ARTCLO_FORM_CES_FRA_2, " +
						   "ARTCLO_FORM_CES_FRA_3, " +
						   "ARTCLO_FORM_CES_FRA_4, " +
						   "ARTCLO_FORM_CES_FRA_5, " +
						   "ARTCLO_PORC_FRA_RAPEL_1, " +
						   "ARTCLO_PORC_FRA_RAPEL_2, " +
						   "ARTCLO_FORM_FRA_RAPEL, " +
						   "ARTCLO_MAS_IMP_PR_FIJO, " +
						   "ARTCLO_NEW_PR_PRV_CESION, " +
						   "ARTCLO_NEW_PR_PRV_FRA, " +
						   "ARTCLO_NEW_PR_PRV_RAPEL, " +
						   "ARTCLO_NEW_PORC_CES_FRA_1, " +
						   "ARTCLO_NEW_PORC_CES_FRA_2, " +
						   "ARTCLO_NEW_PORC_CES_FRA_3, " +
						   "ARTCLO_NEW_PORC_CES_FRA_4, " +
						   "ARTCLO_NEW_PORC_CES_FRA_5, " +
						   "ARTCLO_NEW_PORC_CES_FRA_6, " +
						   "ARTCLO_NEW_FORM_CES_FRA_1, " +
						   "ARTCLO_NEW_FORM_CES_FRA_2, " +
						   "ARTCLO_NEW_FORM_CES_FRA_3, " +
						   "ARTCLO_NEW_FORM_CES_FRA_4, " +
						   "ARTCLO_NEW_FORM_CES_FRA_5, " +
						   "ARTCLO_NEW_PORC_FRA_RAPEL_1, " +
						   "ARTCLO_NEW_PORC_FRA_RAPEL_2, " +
						   "ARTCLO_NEW_FORM_FRA_RAPEL, " +
						   "ARTCLO_NEWFUT_MAS_IMP_PR_FIJO, " +
						   "ARTCLO_ANT_PR_PRV_CESION, " +
						   "ARTCLO_ANT_PR_PRV_FRA, " +
						   "ARTCLO_ANT_PR_PRV_RAPEL, " +
						   "ARTCLO_ANT_PORC_CES_FRA_1, " +
						   "ARTCLO_ANT_PORC_CES_FRA_2, " +
						   "ARTCLO_ANT_PORC_CES_FRA_3, " +
						   "ARTCLO_ANT_PORC_CES_FRA_4, " +
						   "ARTCLO_ANT_PORC_CES_FRA_5, " +
						   "ARTCLO_ANT_PORC_CES_FRA_6, " +
						   "ARTCLO_ANT_FORM_CES_FRA_1, " +
						   "ARTCLO_ANT_FORM_CES_FRA_2, " +
						   "ARTCLO_ANT_FORM_CES_FRA_3, " +
						   "ARTCLO_ANT_FORM_CES_FRA_4, " +
						   "ARTCLO_ANT_FORM_CES_FRA_5, " +
						   "ARTCLO_ANT_PORC_FRA_RAPEL_1, " +
						   "ARTCLO_ANT_PORC_FRA_RAPEL_2, " +
						   "ARTCLO_ANT_FORM_FRA_RAPEL, " +
						   "ARTCLO_ANTFUT_MAS_IMP_PR_FIJO, " +
						   "ARTCLO_DIAS_ANTEL_PED, " +
						   "ARTCLO_DIAS_DEMORA_SERV, " +
						   "ARTCLO_LOTE_SERVICIO, " +
						   "ARTCLO_LOTE_PEDIDO, " +
						   "ARTCLO_CAMPANIA, " +
						   "ARTCLO_MARGEN_SUPER, " +
						   "ARTCLO_MARGEN_1, " +
						   "ARTCLO_MARGEN_2, " +
						   "ARTCLO_MARGEN_3, " +
						   "ARTCLO_MARGEN_4, " +
						   "ARTCLO_MARGEN_5, " +
						   "ARTCLO_MARGEN_6, " +
						   "ARTCLO_MARGEN_7, " +
						   "ARTCLO_MARGEN_8, " +
						   "ARTCLO_MARGEN_9, " +
						   "ARTCLO_PREC_VENTA_1, " +
						   "ARTCLO_PREC_VENTA_2, " +
						   "ARTCLO_PREC_VENTA_3, " +
						   "ARTCLO_PREC_VENTA_4, " +
						   "ARTCLO_PREC_VENTA_5, " +
						   "ARTCLO_PREC_VENTA_6, " +
						   "ARTCLO_PREC_NEW_TARIF_1, " +
						   "ARTCLO_PREC_NEW_TARIF_2, " +
						   "ARTCLO_PREC_NEW_TARIF_3, " +
						   "ARTCLO_PREC_NEW_TARIF_4, " +
						   "ARTCLO_PREC_NEW_TARIF_5, " +
						   "ARTCLO_PREC_NEW_TARIF_6, " +
						   "ARTCLO_DTO_1, " +
						   "ARTCLO_DTO_2, " +
						   "ARTCLO_DTO_3, " +
						   "ARTCLO_DTO_4, " +
						   "ARTCLO_DTO_5, " +
						   "ARTCLO_DTO_6, " +
						   "ARTCLO_PVP_TIENDA_1, " +
						   "ARTCLO_PVP_TIENDA_2, " +
						   "ARTCLO_PVREF_TIENDA_1, " +
						   "ARTCLO_PVREF_TIENDA_2, " +
						   "ARTCLO_PVESP_TIENDA_1, " +
						   "ARTCLO_PVESP_TIENDA_2, " +
						   "ARTCLO_PRECOMEND_FABR, " +
						   "ARTCLO_IVA, " +
						   "ARTCLO_REQU, " +
						   "ARTCLO_COMISION_1, " +
						   "ARTCLO_COMISION_2, " +
						   "ARTCLO_COMISION_3, " +
						   "ARTCLO_CTA_VTAS, " +
						   "ARTCLO_TIPO, " +
						   "ARTCLO_EXCLUSIVO, " +
						   "ARTCLO_FEC_MODIF, " +
						   "ARTCLO_FEC_ALTA, " +
						   "ARTCLO_NO_VALTSTK, " +
						   "ARTCLO_PRVRCP_FEC, " +
						   "ARTCLO_PRVRCP_CAN) " +						   
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
				                   "?, ?, ?, ?, ?, ?) " +						   
				           "ON DUPLICATE KEY UPDATE " +
				           "ARTCLO_IND_NIVEL = ?, " +
						   "ARTCLO_MARCA = ?, " +
						   "ARTCLO_GAMA = ?, " +
						   "ARTCLO_FAMILIA = ?, " +
						   "ARTCLO_ARTICULO = ?, " +
						   "ARTCLO_EAN = ?, " +
						   "ARTCLO_CODIGO_ABC = ?, " +
						   "ARTCLO_DESCRIP = ?, " +
						   "ARTCLO_PROV_HABITUAL = ?, " +
						   "ARTCLO_SUSTITUYE_A = ?, " +
						   "ARTCLO_SUSTITUIDO_POR = ?, " +
						   "ARTCLO_STOCK = ?, " +
						   "ARTCLO_VAL_EXIS = ?, " +
						   "ARTCLO_PRE_COSTE = ?, " +
						   "ARTCLO_STK_INICEJER = ?, " +
						   "ARTCLO_VAL_INICEJER = ?, " +
						   "ARTCLO_PEND_RECIBIR = ?, " +
						   "ARTCLO_ASIG_SERVIR = ?, " +
						   "ARTCLO_DISP_72H = ?, " +
						   "ARTCLO_ST_MINIMO = ?, " +
						   "ARTCLO_ST_MAXIMO = ?, " +
						   "ARTCLO_ROT_BAJMIN = ?, " +
						   "ARTCLO_ROT_ST_ANY = ?, " +
						   "ARTCLO_FEC_ULT_COM = ?, " +
						   "ARTCLO_CAN_ULT_COM = ?, " +
						   "ARTCLO_PRE_ULT_COM = ?, " +
						   "ARTCLO_PRE_ANT_COM = ?, " +
						   "ARTCLO_PRFR_MGF_SUPER = ?, " +
						   "ARTCLO_PR_PRV_CESION = ?, " +
						   "ARTCLO_PR_PRV_FRA = ?, " +
						   "ARTCLO_PR_PRV_RAPEL = ?, " +
						   "ARTCLO_PORC_CES_FRA_1 = ?, " +
						   "ARTCLO_PORC_CES_FRA_2 = ?, " +
						   "ARTCLO_PORC_CES_FRA_3 = ?, " +
						   "ARTCLO_PORC_CES_FRA_4 = ?, " +
						   "ARTCLO_PORC_CES_FRA_5 = ?, " +
						   "ARTCLO_PORC_CES_FRA_6 = ?, " +
						   "ARTCLO_FORM_CES_FRA_1 = ?, " +
						   "ARTCLO_FORM_CES_FRA_2 = ?, " +
						   "ARTCLO_FORM_CES_FRA_3 = ?, " +
						   "ARTCLO_FORM_CES_FRA_4 = ?, " +
						   "ARTCLO_FORM_CES_FRA_5 = ?, " +
						   "ARTCLO_PORC_FRA_RAPEL_1 = ?, " +
						   "ARTCLO_PORC_FRA_RAPEL_2 = ?, " +
						   "ARTCLO_FORM_FRA_RAPEL = ?, " +
						   "ARTCLO_MAS_IMP_PR_FIJO = ?, " +
						   "ARTCLO_NEW_PR_PRV_CESION = ?, " +
						   "ARTCLO_NEW_PR_PRV_FRA = ?, " +
						   "ARTCLO_NEW_PR_PRV_RAPEL = ?, " +
						   "ARTCLO_NEW_PORC_CES_FRA_1 = ?, " +
						   "ARTCLO_NEW_PORC_CES_FRA_2 = ?, " +
						   "ARTCLO_NEW_PORC_CES_FRA_3 = ?, " +
						   "ARTCLO_NEW_PORC_CES_FRA_4 = ?, " +
						   "ARTCLO_NEW_PORC_CES_FRA_5 = ?, " +
						   "ARTCLO_NEW_PORC_CES_FRA_6 = ?, " +
						   "ARTCLO_NEW_FORM_CES_FRA_1 = ?, " +
						   "ARTCLO_NEW_FORM_CES_FRA_2 = ?, " +
						   "ARTCLO_NEW_FORM_CES_FRA_3 = ?, " +
						   "ARTCLO_NEW_FORM_CES_FRA_4 = ?, " +
						   "ARTCLO_NEW_FORM_CES_FRA_5 = ?, " +
						   "ARTCLO_NEW_PORC_FRA_RAPEL_1 = ?, " +
						   "ARTCLO_NEW_PORC_FRA_RAPEL_2 = ?, " +
						   "ARTCLO_NEW_FORM_FRA_RAPEL = ?, " +
						   "ARTCLO_NEWFUT_MAS_IMP_PR_FIJO = ?, " +
						   "ARTCLO_ANT_PR_PRV_CESION = ?, " +
						   "ARTCLO_ANT_PR_PRV_FRA = ?, " +
						   "ARTCLO_ANT_PR_PRV_RAPEL = ?, " +
						   "ARTCLO_ANT_PORC_CES_FRA_1 = ?, " +
						   "ARTCLO_ANT_PORC_CES_FRA_2 = ?, " +
						   "ARTCLO_ANT_PORC_CES_FRA_3 = ?, " +
						   "ARTCLO_ANT_PORC_CES_FRA_4 = ?, " +
						   "ARTCLO_ANT_PORC_CES_FRA_5 = ?, " +
						   "ARTCLO_ANT_PORC_CES_FRA_6 = ?, " +
						   "ARTCLO_ANT_FORM_CES_FRA_1 = ?, " +
						   "ARTCLO_ANT_FORM_CES_FRA_2 = ?, " +
						   "ARTCLO_ANT_FORM_CES_FRA_3 = ?, " +
						   "ARTCLO_ANT_FORM_CES_FRA_4 = ?, " +
						   "ARTCLO_ANT_FORM_CES_FRA_5 = ?, " +
						   "ARTCLO_ANT_PORC_FRA_RAPEL_1 = ?, " +
						   "ARTCLO_ANT_PORC_FRA_RAPEL_2 = ?, " +
						   "ARTCLO_ANT_FORM_FRA_RAPEL = ?, " +
						   "ARTCLO_ANTFUT_MAS_IMP_PR_FIJO = ?, " +
						   "ARTCLO_DIAS_ANTEL_PED = ?, " +
						   "ARTCLO_DIAS_DEMORA_SERV = ?, " +
						   "ARTCLO_LOTE_SERVICIO = ?, " +
						   "ARTCLO_LOTE_PEDIDO = ?, " +
						   "ARTCLO_CAMPANIA = ?, " +
						   "ARTCLO_MARGEN_SUPER = ?, " +
						   "ARTCLO_MARGEN_1 = ?, " +
						   "ARTCLO_MARGEN_2 = ?, " +
						   "ARTCLO_MARGEN_3 = ?, " +
						   "ARTCLO_MARGEN_4 = ?, " +
						   "ARTCLO_MARGEN_5 = ?, " +
						   "ARTCLO_MARGEN_6 = ?, " +
						   "ARTCLO_MARGEN_7 = ?, " +
						   "ARTCLO_MARGEN_8 = ?, " +
						   "ARTCLO_MARGEN_9 = ?, " +
						   "ARTCLO_PREC_VENTA_1 = ?, " +
						   "ARTCLO_PREC_VENTA_2 = ?, " +
						   "ARTCLO_PREC_VENTA_3 = ?, " +
						   "ARTCLO_PREC_VENTA_4 = ?, " +
						   "ARTCLO_PREC_VENTA_5 = ?, " +
						   "ARTCLO_PREC_VENTA_6 = ?, " +
						   "ARTCLO_PREC_NEW_TARIF_1 = ?, " +
						   "ARTCLO_PREC_NEW_TARIF_2 = ?, " +
						   "ARTCLO_PREC_NEW_TARIF_3 = ?, " +
						   "ARTCLO_PREC_NEW_TARIF_4 = ?, " +
						   "ARTCLO_PREC_NEW_TARIF_5 = ?, " +
						   "ARTCLO_PREC_NEW_TARIF_6 = ?, " +
						   "ARTCLO_DTO_1 = ?, " +
						   "ARTCLO_DTO_2 = ?, " +
						   "ARTCLO_DTO_3 = ?, " +
						   "ARTCLO_DTO_4 = ?, " +
						   "ARTCLO_DTO_5 = ?, " +
						   "ARTCLO_DTO_6 = ?, " +
						   "ARTCLO_PVP_TIENDA_1 = ?, " +
						   "ARTCLO_PVP_TIENDA_2 = ?, " +
						   "ARTCLO_PVREF_TIENDA_1 = ?, " +
						   "ARTCLO_PVREF_TIENDA_2 = ?, " +
						   "ARTCLO_PVESP_TIENDA_1 = ?, " +
						   "ARTCLO_PVESP_TIENDA_2 = ?, " +
						   "ARTCLO_PRECOMEND_FABR = ?, " +
						   "ARTCLO_IVA = ?, " +
						   "ARTCLO_REQU = ?, " +
						   "ARTCLO_COMISION_1 = ?, " +
						   "ARTCLO_COMISION_2 = ?, " +
						   "ARTCLO_COMISION_3 = ?, " +
						   "ARTCLO_CTA_VTAS = ?, " +
						   "ARTCLO_TIPO = ?, " +
						   "ARTCLO_EXCLUSIVO = ?, " +
						   "ARTCLO_FEC_MODIF = ?, " +
						   "ARTCLO_FEC_ALTA = ?, " +
						   "ARTCLO_NO_VALTSTK = ?, " +
						   "ARTCLO_PRVRCP_FEC = ?, " +
						   "ARTCLO_PRVRCP_CAN = ?";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			// Insert
			ps.setString(1, Cadena.left(empresa, 2));
			ps.setString(2, Cadena.left(indNivel, 1));
			ps.setString(3, Cadena.left(marca, 3));
			ps.setInt(4, gama);
			ps.setInt(5, familia);
			ps.setString(6, Cadena.left(articulo, 13));
			ps.setString(7, Cadena.left(ean, 13));
			ps.setString(8, Cadena.left(codigoAbc, 1));
			ps.setString(9, Cadena.left(descrip, 30));
			ps.setInt(10, provHabitual);
			ps.setString(11, Cadena.left(sustituyeA, 13));
			ps.setString(12, Cadena.left(sustituidoPor, 13));
			ps.setDouble(13, stock);
			ps.setDouble(14, valExis);
			ps.setDouble(15, preCoste);
			ps.setDouble(16, stkInicejer);
			ps.setDouble(17, valInicejer);
			ps.setDouble(18, pendRecibir);
			ps.setDouble(19, asignServir);
			ps.setDouble(20, disp72h);
			ps.setDouble(21, stMinimo);
			ps.setDouble(22, stMaximo);
			ps.setInt(23, rotBajmin);
			ps.setInt(24, rotStAny);
			ps.setInt(25, fecUltCom);
			ps.setDouble(26, canUltCom);
			ps.setDouble(27, preUltCom);
			ps.setDouble(28, preAntCom);
			ps.setInt(29, prfrMgfSuper);
			ps.setDouble(30, prPrvCesion);
			ps.setDouble(31, prPrvFra);
			ps.setDouble(32, prPrvRapel);
			//33
			for(int i = 0; i < 6; i++)
				ps.setDouble(33+i, porcCesFra[i]);
			//39
			for(int i = 0; i < 5; i++)
				ps.setInt(39+i, formCesFra[i]);
			//44
			ps.setDouble(44, porcFraRapel[0]);
			ps.setDouble(45, porcFraRapel[1]);
			ps.setInt(46, formFraRapel);
			ps.setDouble(47, masImpPrFijo);
			ps.setDouble(48, newPrPrvCesion);
			ps.setDouble(49, newPrPrvFra);
			ps.setDouble(50, newPrPrvRapel);
			//51
			for(int i = 0; i < 6; i++)
				ps.setDouble(51 + i, newPorcCesFra[i]);
			//57
			for(int i = 0; i < 5; i++)
				ps.setInt(57 + i, newFormCesFra[i]);
			//62
			ps.setDouble(62, newPorcFraRapel[0]);
			ps.setDouble(63, newPorcFraRapel[1]);
			ps.setInt(64, newFormFraRapel);
			ps.setDouble(65, newfutMasInpPrFijo);
			ps.setDouble(66, antPrPrvCesion);
			ps.setDouble(67, antPrPrvFra);
			ps.setDouble(68, antPrPrvRapel);
			//69
			for(int i = 0; i < 6; i++)
				ps.setDouble(69 + i, antPorcCesFra[i]);
			//75
			for(int i = 0; i < 5; i++)
				ps.setInt(75 + i, antFormCesFra[i]);
			//80
			ps.setDouble(80, antPorcFraRapel[0]);
			ps.setDouble(81, antPorcFraRapel[1]);
			ps.setInt(82, antFormFraRapel);
			ps.setDouble(83, antfutMasImpPrFijo);
			ps.setInt(84, diasAntelPed);
			ps.setInt(85, diasDemoraServ);
			ps.setInt(86, loteServicio);
			ps.setInt(87, lotePedido);
			ps.setString(88, Cadena.left(campania, 10));
			ps.setInt(89, margenSuper);
			//90
			for(int i = 0; i < 9; i++)
				ps.setDouble(90 + i, margen[i]);
			//99
			for(int i = 0; i < 6; i++)
				ps.setDouble(99 + i, precVenta[i]);
			//105
			for(int i = 0; i < 6; i++)
				ps.setDouble(105 + i, precNewTarifa[i]);
			//111
			for(int i = 0; i < 6; i++)
				ps.setInt(111 + i, dto[i]);
			//117
			ps.setDouble(117, pvpTienda[0]);
			ps.setDouble(118, pvpTienda[1]);
			ps.setDouble(119, pvrefTienda[0]);
			ps.setDouble(120, pvrefTienda[1]);
			ps.setDouble(121, pvespTienda[0]);
			ps.setDouble(122, pvespTienda[1]);
			ps.setDouble(123, precomendFabr);
			ps.setInt(124, iva);
			ps.setInt(125, requ);
			//126
			for(int i = 0; i < 3; i++)
				ps.setDouble(126 + i, comision[i]);
			//129
			ps.setString(129, Cadena.left(ctaVtas, 9));
			ps.setInt(130, tipo);
			ps.setInt(131, exclusivo);
			ps.setInt(132, fecModif);
			ps.setInt(133, fecAlta);
			ps.setInt(134, noValstk);
			ps.setInt(135, prvrcpFec);
			ps.setDouble(136, prvrcpCan);
			// Update
			ps.setString(137, Cadena.left(empresa, 2));
			ps.setString(138, Cadena.left(indNivel, 1));
			ps.setString(139, Cadena.left(marca, 3));
			ps.setInt(140, gama);
			ps.setInt(141, familia);
			ps.setString(142, Cadena.left(articulo, 13));
			ps.setString(143, Cadena.left(ean, 13));
			ps.setString(144, Cadena.left(codigoAbc, 1));
			ps.setString(145, Cadena.left(descrip, 30));
			ps.setInt(146, provHabitual);
			ps.setString(147, Cadena.left(sustituyeA, 13));
			ps.setString(148, Cadena.left(sustituidoPor, 13));
			ps.setDouble(149, stock);
			ps.setDouble(150, valExis);
			ps.setDouble(151, preCoste);
			ps.setDouble(152, stkInicejer);
			ps.setDouble(153, valInicejer);
			ps.setDouble(154, pendRecibir);
			ps.setDouble(155, asignServir);
			ps.setDouble(156, disp72h);
			ps.setDouble(157, stMinimo);
			ps.setDouble(158, stMaximo);
			ps.setInt(159, rotBajmin);
			ps.setInt(160, rotStAny);
			ps.setInt(161, fecUltCom);
			ps.setDouble(162, canUltCom);
			ps.setDouble(163, preUltCom);
			ps.setDouble(164, preAntCom);
			ps.setInt(165, prfrMgfSuper);
			ps.setDouble(166, prPrvCesion);
			ps.setDouble(167, prPrvFra);
			ps.setDouble(168, prPrvRapel);
			//169
			for(int i = 0; i < 6; i++)
				ps.setDouble(169 + i, porcCesFra[i]);
			//175
			for(int i = 0; i < 5; i++)
				ps.setInt(175 + i, formCesFra[i]);
			//180
			ps.setDouble(180, porcFraRapel[0]);
			ps.setDouble(181, porcFraRapel[1]);
			ps.setInt(182, formFraRapel);
			ps.setDouble(183, masImpPrFijo);
			ps.setDouble(184, newPrPrvCesion);
			ps.setDouble(185, newPrPrvFra);
			ps.setDouble(186, newPrPrvRapel);
			//187
			for(int i = 0; i < 6; i++)
				ps.setDouble(187 + i, newPorcCesFra[i]);
			//193
			for(int i = 0; i < 5; i++)
				ps.setInt(193 + i, newFormCesFra[i]);
			//198
			ps.setDouble(198, newPorcFraRapel[0]);
			ps.setDouble(199, newPorcFraRapel[1]);
			ps.setInt(200, newFormFraRapel);
			ps.setDouble(201, newfutMasInpPrFijo);
			ps.setDouble(202, antPrPrvCesion);
			ps.setDouble(203, antPrPrvFra);
			ps.setDouble(204, antPrPrvRapel);
			//205
			for(int i = 0; i < 6; i++)
				ps.setDouble(205 + i, antPorcCesFra[i]);
			//211
			for(int i = 0; i < 5; i++)
				ps.setInt(211 + i, antFormCesFra[i]);
			//216
			ps.setDouble(216, antPorcFraRapel[0]);
			ps.setDouble(217, antPorcFraRapel[1]);
			ps.setInt(218, antFormFraRapel);
			ps.setDouble(219, antfutMasImpPrFijo);
			ps.setInt(220, diasAntelPed);
			ps.setInt(221, diasDemoraServ);
			ps.setInt(222, loteServicio);
			ps.setInt(223, lotePedido);
			ps.setString(224, Cadena.left(campania, 10));
			ps.setInt(225, margenSuper);
			//226
			for(int i = 0; i < 9; i++)
				ps.setDouble(226 + i, margen[i]);
			//235
			for(int i = 0; i < 6; i++)
				ps.setDouble(235 + i, precVenta[i]);
			//241
			for(int i = 0; i < 6; i++)
				ps.setDouble(241 + i, precNewTarifa[i]);
			//247
			for(int i = 0; i < 6; i++)
				ps.setInt(247 + i, dto[i]);
			//253
			ps.setDouble(253, pvpTienda[0]);
			ps.setDouble(254, pvpTienda[1]);
			ps.setDouble(255, pvrefTienda[0]);
			ps.setDouble(256, pvrefTienda[1]);
			ps.setDouble(257, pvespTienda[0]);
			ps.setDouble(258, pvespTienda[1]);
			ps.setDouble(259, precomendFabr);
			ps.setInt(260, iva);
			ps.setInt(261, requ);
			//262
			for(int i = 0; i < 3; i++)
				ps.setDouble(262 + i, comision[i]);
			//265
			ps.setString(265, Cadena.left(ctaVtas, 9));
			ps.setInt(266, tipo);
			ps.setInt(267, exclusivo);
			ps.setInt(268, fecModif);
			ps.setInt(269, fecAlta);
			ps.setInt(270, noValstk);
			ps.setInt(271, prvrcpFec);
			ps.setDouble(272, prvrcpCan);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de Articulo!!!");
				e.printStackTrace();
			}
		}
	}

	public String getIndNivel() {
		return indNivel;
	}
	public void setIndNivel(String indNivel) {
		this.indNivel = indNivel;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public int getGama() {
		return gama;
	}
	public void setGama(int gama) {
		this.gama = gama;
	}
	public int getFamilia() {
		return familia;
	}
	public void setFamilia(int familia) {
		this.familia = familia;
	}
	public String getArticulo() {
		return articulo;
	}
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}
	public String getEan() {
		return ean;
	}
	public void setEan(String ean) {
		this.ean = ean;
	}
	public String getCodigoAbc() {
		return codigoAbc;
	}
	public void setCodigoAbc(String codigoAbc) {
		this.codigoAbc = codigoAbc;
	}
	public String getDescrip() {
		return descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	public int getProvHabitual() {
		return provHabitual;
	}
	public void setProvHabitual(int provHabitual) {
		this.provHabitual = provHabitual;
	}
	public String getSustituyeA() {
		return sustituyeA;
	}
	public void setSustituyeA(String sustituyeA) {
		this.sustituyeA = sustituyeA;
	}
	public String getSustituidoPor() {
		return sustituidoPor;
	}
	public void setSustituidoPor(String sustituidoPor) {
		this.sustituidoPor = sustituidoPor;
	}
	public double getStock() {
		return stock;
	}
	public void setStock(double stock) {
		this.stock = stock;
	}
	public double getValExis() {
		return valExis;
	}
	public void setValExis(double valExis) {
		this.valExis = valExis;
	}
	public double getPreCoste() {
		return preCoste;
	}
	public void setPreCoste(double preCoste) {
		this.preCoste = preCoste;
	}
	public double getStkInicejer() {
		return stkInicejer;
	}
	public void setStkInicejer(double stkInicejer) {
		this.stkInicejer = stkInicejer;
	}
	public double getValInicejer() {
		return valInicejer;
	}
	public void setValInicejer(double valInicejer) {
		this.valInicejer = valInicejer;
	}
	public double getPendRecibir() {
		return pendRecibir;
	}
	public void setPendRecibir(double pendRecibir) {
		this.pendRecibir = pendRecibir;
	}
	public double getAsignServir() {
		return asignServir;
	}
	public void setAsignServir(double asignServir) {
		this.asignServir = asignServir;
	}
	public double getDisp72h() {
		return disp72h;
	}
	public void setDisp72h(double disp72h) {
		this.disp72h = disp72h;
	}
	public double getStMinimo() {
		return stMinimo;
	}
	public void setStMinimo(double stMinimo) {
		this.stMinimo = stMinimo;
	}
	public double getStMaximo() {
		return stMaximo;
	}
	public void setStMaximo(double stMaximo) {
		this.stMaximo = stMaximo;
	}
	public int getRotBajmin() {
		return rotBajmin;
	}
	public void setRotBajmin(int rotBajmin) {
		this.rotBajmin = rotBajmin;
	}
	public int getRotStAny() {
		return rotStAny;
	}
	public void setRotStAny(int rotStAny) {
		this.rotStAny = rotStAny;
	}
	public int getFecUltCom() {
		return fecUltCom;
	}
	public void setFecUltCom(int fecUltCom) {
		this.fecUltCom = fecUltCom;
	}
	public double getCanUltCom() {
		return canUltCom;
	}
	public void setCanUltCom(double canUltCom) {
		this.canUltCom = canUltCom;
	}
	public double getPreUltCom() {
		return preUltCom;
	}
	public void setPreUltCom(double preUltCom) {
		this.preUltCom = preUltCom;
	}
	public double getPreAntCom() {
		return preAntCom;
	}
	public void setPreAntCom(double preAntCom) {
		this.preAntCom = preAntCom;
	}
	public int getPrfrMgfSuper() {
		return prfrMgfSuper;
	}
	public void setPrfrMgfSuper(int prfrMgfSuper) {
		this.prfrMgfSuper = prfrMgfSuper;
	}
	public double getPrPrvCesion() {
		return prPrvCesion;
	}
	public void setPrPrvCesion(double prPrvCesion) {
		this.prPrvCesion = prPrvCesion;
	}
	public double getPrPrvFra() {
		return prPrvFra;
	}
	public void setPrPrvFra(double prPrvFra) {
		this.prPrvFra = prPrvFra;
	}
	public double getPrPrvRapel() {
		return prPrvRapel;
	}
	public void setPrPrvRapel(double prPrvRapel) {
		this.prPrvRapel = prPrvRapel;
	}
	public double[] getPorcCesFra() {
		return porcCesFra;
	}
	public double getPorcCesFra(int i){
		return porcCesFra[i];
	}
	public void setPorcCesFra(double[] porcCesFra) {
		this.porcCesFra = porcCesFra;
	}
	public void setPorcCesFra(int indice, double valor){
		this.porcCesFra[indice] = valor;
	}
	public int[] getFormCesFra() {
		return formCesFra;
	}
	public int getFormCesFra(int i){
		return formCesFra[i];
	}
	public void setFormCesFra(int[] formCesFra) {
		this.formCesFra = formCesFra;
	}
	public void setFormCesFra(int indice, int valor) {
		this.formCesFra[indice] = valor;
	}
	public double[] getPorcFraRapel() {
		return porcFraRapel;
	}
	public double getPorcFraRapel(int i) {
		return porcFraRapel[i];
	}
	public void setPorcFraRapel(double[] porcFraRapel) {
		this.porcFraRapel = porcFraRapel;
	}
	public void setPorcFraRapel(int indice, double valor) {
		this.porcFraRapel[indice] = valor;
	}
	public int getFormFraRapel() {
		return formFraRapel;
	}
	public void setFormFraRapel(int formFraRapel) {
		this.formFraRapel = formFraRapel;
	}
	public double getMasImpPrFijo() {
		return masImpPrFijo;
	}
	public void setMasImpPrFijo(double masImpPrFijo) {
		this.masImpPrFijo = masImpPrFijo;
	}
	public double getNewPrPrvCesion() {
		return newPrPrvCesion;
	}
	public void setNewPrPrvCesion(double newPrPrvCesion) {
		this.newPrPrvCesion = newPrPrvCesion;
	}
	public double getNewPrPrvFra() {
		return newPrPrvFra;
	}
	public void setNewPrPrvFra(double newPrPrvFra) {
		this.newPrPrvFra = newPrPrvFra;
	}
	public double getNewPrPrvRapel() {
		return newPrPrvRapel;
	}
	public void setNewPrPrvRapel(double newPrPrvRapel) {
		this.newPrPrvRapel = newPrPrvRapel;
	}
	public double[] getNewPorcCesFra() {
		return newPorcCesFra;
	}
	public double getNewPorcCesFra(int i) {
		return newPorcCesFra[i];
	}
	public void setNewPorcCesFra(double[] newPorcCesFra) {
		this.newPorcCesFra = newPorcCesFra;
	}
	public void setNewPorcCesFra(int indice, double valor) {
		this.newPorcCesFra[indice] = valor;
	}
	public int[] getNewFormCesFra() {
		return newFormCesFra;
	}
	public int getNewFormCesFra(int i) {
		return newFormCesFra[i];
	}
	public void setNewFormCesFra(int[] newFormCesFra) {
		this.newFormCesFra = newFormCesFra;
	}
	public void setNewFormCesFra(int indice, int valor) {
		this.newFormCesFra[indice] = valor;
	}
	public double[] getNewPorcFraRapel() {
		return newPorcFraRapel;
	}
	public double getNewPorcFraRapel(int i) {
		return newPorcFraRapel[i];
	}
	public void setNewPorcFraRapel(double[] newPorcFraRapel) {
		this.newPorcFraRapel = newPorcFraRapel;
	}
	public void setNewPorcFraRapel(int indice, double valor) {
		this.newPorcFraRapel[indice] = valor;
	}
	public int getNewFormFraRapel() {
		return newFormFraRapel;
	}
	public void setNewFormFraRapel(int newFormFraRapel) {
		this.newFormFraRapel = newFormFraRapel;
	}
	public double getNewfutMasInpPrFijo() {
		return newfutMasInpPrFijo;
	}
	public void setNewfutMasInpPrFijo(double newfutMasInpPrFijo) {
		this.newfutMasInpPrFijo = newfutMasInpPrFijo;
	}
	public double getAntPrPrvCesion() {
		return antPrPrvCesion;
	}
	public void setAntPrPrvCesion(double antPrPrvCesion) {
		this.antPrPrvCesion = antPrPrvCesion;
	}
	public double getantPrPrvFra() {
		return antPrPrvFra;
	}
	public void setantPrPrvFra(double antPrPrvFra) {
		this.antPrPrvFra = antPrPrvFra;
	}
	public double getAntPrPrvRapel() {
		return antPrPrvRapel;
	}
	public void setAntPrPrvRapel(double antPrPrvRapel) {
		this.antPrPrvRapel = antPrPrvRapel;
	}
	public double[] getAntPorcCesFra() {
		return antPorcCesFra;
	}
	public double getAntPorcCesFra(int i) {
		return antPorcCesFra[i];
	}
	public void setAntPorcCesFra(double[] antPorcCesFra) {
		this.antPorcCesFra = antPorcCesFra;
	}
	public void setAntPorcCesFra(int indice, double valor) {
		this.antPorcCesFra[indice] = valor;
	}
	public int[] getAntFormCesFra() {
		return antFormCesFra;
	}
	public int getAntFormCesFra(int i) {
		return antFormCesFra[i];
	}
	public void setAntFormCesFra(int[] antFormCesFra) {
		this.antFormCesFra = antFormCesFra;
	}
	public void setAntFormCesFra(int indice, int valor) {
		this.antFormCesFra[indice] = valor;
	}
	public double[] getAntPorcFraRapel() {
		return antPorcFraRapel;
	}
	public double getAntPorcFraRapel(int i) {
		return antPorcFraRapel[i];
	}
	public void setAntPorcFraRapel(double[] antPorcFraRapel) {
		this.antPorcFraRapel = antPorcFraRapel;
	}
	public void setAntPorcFraRapel(int indice, double valor) {
		this.antPorcFraRapel[indice] = valor;
	}
	public int getAntFormFraRapel() {
		return antFormFraRapel;
	}
	public void setAntFormFraRapel(int antFormFraRapel) {
		this.antFormFraRapel = antFormFraRapel;
	}
	public double getAntfutMasImpPrFijo() {
		return antfutMasImpPrFijo;
	}
	public void setAntfutMasImpPrFijo(double antfutMasImpPrFijo) {
		this.antfutMasImpPrFijo = antfutMasImpPrFijo;
	}
	public int getDiasAntelPed() {
		return diasAntelPed;
	}
	public void setDiasAntelPed(int diasAntelPed) {
		this.diasAntelPed = diasAntelPed;
	}
	public int getDiasDemoraServ() {
		return diasDemoraServ;
	}
	public void setDiasDemoraServ(int diasDemoraServ) {
		this.diasDemoraServ = diasDemoraServ;
	}
	public int getLoteServicio() {
		return loteServicio;
	}
	public void setLoteServicio(int loteServicio) {
		this.loteServicio = loteServicio;
	}
	public int getLotePedido() {
		return lotePedido;
	}
	public void setLotePedido(int lotePedido) {
		this.lotePedido = lotePedido;
	}
	public String getCampania() {
		return campania;
	}
	public void setCampania(String campania) {
		this.campania = campania;
	}
	public int getMargenSuper() {
		return margenSuper;
	}
	public void setMargenSuper(int margenSuper) {
		this.margenSuper = margenSuper;
	}
	public double[] getMargen() {
		return margen;
	}
	public void setMargen(double[] margen) {
		this.margen = margen;
	}
	public void setMargen(int indice, double valor) {
		this.margen[indice] = valor;
	}
	public double[] getPrecVenta() {
		return precVenta;
	}
	public double getPrecVenta(int i) {
		return precVenta[i];
	}
	public void setPrecVenta(double[] precVenta) {
		this.precVenta = precVenta;
	}
	public void setPrecVenta(int indice, double valor) {
		this.precVenta[indice] = valor;
	}
	public double[] getPrecnewTarifa() {
		return precNewTarifa;
	}
	public double getPrecnewTarifa(int i) {
		return precNewTarifa[i];
	}
	public void setPrecnewTarifa(double[] precnewTarifa) {
		this.precNewTarifa = precnewTarifa;
	}
	public void setPrecnewTarifa(int indice, double valor) {
		this.precNewTarifa[indice] = valor;
	}
	public int[] getDto() {
		return dto;
	}
	public int getDto(int i) {
		return dto[i];
	}
	public void setDto(int[] dto) {
		this.dto = dto;
	}
	public void setDto(int indice, int valor) {
		this.dto[indice] = valor;
	}
	public double[] getPvpTienda() {
		return pvpTienda;
	}
	public double getPvpTienda(int i) {
		return pvpTienda[i];
	}
	public void setPvpTienda(double[] pvpTienda) {
		this.pvpTienda = pvpTienda;
	}
	public void setPvpTienda(int indice, double valor) {
		this.pvpTienda[indice] = valor;
	}
	public double[] getPvrefTienda() {
		return pvrefTienda;
	}
	public double getPvrefTienda(int i) {
		return pvrefTienda[i];
	}
	public void setPvrefTienda(double[] pvrefTienda) {
		this.pvrefTienda = pvrefTienda;
	}
	public void setPvrefTienda(int indice, double valor) {
		this.pvrefTienda[indice] = valor;
	}
	public double[] getPvespTienda() {
		return pvespTienda;
	}
	public double getPvespTienda(int i) {
		return pvespTienda[i];
	}
	public void setPvespTienda(double[] pvespTienda) {
		this.pvespTienda = pvespTienda;
	}
	public void setPvespTienda(int indice, double valor) {
		this.pvespTienda[indice] = valor;
	}
	public double getPrecomendFabr() {
		return precomendFabr;
	}
	public void setPrecomendFabr(double precomendFabr) {
		this.precomendFabr = precomendFabr;
	}
	public int getIva() {
		return iva;
	}
	public void setIva(int iva) {
		this.iva = iva;
	}
	public int getRequ() {
		return requ;
	}
	public void setRequ(int requ) {
		this.requ = requ;
	}
	public double[] getComision() {
		return comision;
	}
	public double getComision(int i) {
		return comision[i];
	}
	public void setComision(double[] comision) {
		this.comision = comision;
	}
	public void setComision(int indice, double valor) {
		this.comision[indice] = valor;
	}
	public String getCtaVtas() {
		return ctaVtas;
	}
	public void setCtaVtas(String ctaVtas) {
		this.ctaVtas = ctaVtas;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public int getExclusivo() {
		return exclusivo;
	}
	public void setExclusivo(int exclusivo) {
		this.exclusivo = exclusivo;
	}
	public int getFecModif() {
		return fecModif;
	}
	public void setFecModif(int fecModif) {
		this.fecModif = fecModif;
	}
	public int getFecAlta() {
		return fecAlta;
	}
	public void setFecAlta(int fecAlta) {
		this.fecAlta = fecAlta;
	}
	public int getNoValstk() {
		return noValstk;
	}
	public void setNoValstk(int noValstk) {
		this.noValstk = noValstk;
	}
	public int getPrvrcpFec() {
		return prvrcpFec;
	}
	public void setPrvrcpFec(int prvrcpFec) {
		this.prvrcpFec = prvrcpFec;
	}
	public double getPrvrcpCan() {
		return prvrcpCan;
	}
	public void setPrvrcpCan(double prvrcpCan) {
		this.prvrcpCan = prvrcpCan;
	}


}
