package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import java.sql.ResultSet;


public class IndiceInforme {
	private String empresa;
	private String balance;
	private String masa;
	private String capitulo;
	private String grupo;
	private String subGrupo;
	private String titulo;
	private String tituloAbreviado;
	private String cuenta[];
	private String contabilidad[];
	private String masMenos[];
	private double porcentaje[];
	private String deudorAcreedor[];
	private double importe;
	private double importe1;
	private double importeMes;
	private double importeMes1;
	private int print;

	public IndiceInforme(){
		empresa = DatosComunes.eEmpresa;
		balance = "";
		masa = "";
		capitulo = "";
		grupo = "";
		subGrupo = "";
		titulo = "";
		tituloAbreviado = "";

		cuenta = new String[36];
		contabilidad = new String[36];
		masMenos = new String[36];
		porcentaje = new double[36];
		deudorAcreedor = new String[36];
		for(int i = 0; i < 36; i++){
			cuenta[i] = "";
			contabilidad[i] = "";
			masMenos[i] = "";
			porcentaje[i] = 0.0;
			deudorAcreedor[i] = "";
		}

		importe = 0.0;
		importe1 = 0.0;
		importeMes = 0.0;
		importeMes1 = 0.0;		
		print = 0;
	}

	public IndiceInforme(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				balance = rs.getString("INDINF_BAL").trim();
				masa = rs.getString("INDINF_MASA").trim();
				capitulo = rs.getString("INDINF_CAP").trim();
				grupo = rs.getString("INDINF_GRU").trim();
				subGrupo = rs.getString("INDINF_SUBG").trim();
				titulo = rs.getString("INDINF_TITULO").trim();
				tituloAbreviado = rs.getString("INDINF_TITULO_ABREVIADO").trim();

				cuenta = new String[36];
				contabilidad = new String[36];
				masMenos = new String[36];
				porcentaje = new double[36];
				deudorAcreedor = new String[36];

				cuenta[0] = rs.getString("INDINF_CUENTA_1").trim();
				cuenta[1] = rs.getString("INDINF_CUENTA_2").trim();
				cuenta[2] = rs.getString("INDINF_CUENTA_3").trim();
				cuenta[3] = rs.getString("INDINF_CUENTA_4").trim();
				cuenta[4] = rs.getString("INDINF_CUENTA_5").trim();
				cuenta[5] = rs.getString("INDINF_CUENTA_6").trim();
				cuenta[6] = rs.getString("INDINF_CUENTA_7").trim();
				cuenta[7] = rs.getString("INDINF_CUENTA_8").trim();
				cuenta[8] = rs.getString("INDINF_CUENTA_9").trim();
				cuenta[9] = rs.getString("INDINF_CUENTA_10").trim();
				cuenta[10] = rs.getString("INDINF_CUENTA_11").trim();
				cuenta[11] = rs.getString("INDINF_CUENTA_12").trim();
				cuenta[12] = rs.getString("INDINF_CUENTA_13").trim();
				cuenta[13] = rs.getString("INDINF_CUENTA_14").trim();
				cuenta[14] = rs.getString("INDINF_CUENTA_15").trim();
				cuenta[15] = rs.getString("INDINF_CUENTA_16").trim();
				cuenta[16] = rs.getString("INDINF_CUENTA_17").trim();
				cuenta[17] = rs.getString("INDINF_CUENTA_18").trim();
				cuenta[18] = rs.getString("INDINF_CUENTA_19").trim();
				cuenta[19] = rs.getString("INDINF_CUENTA_20").trim();
				cuenta[20] = rs.getString("INDINF_CUENTA_21").trim();
				cuenta[21] = rs.getString("INDINF_CUENTA_22").trim();
				cuenta[22] = rs.getString("INDINF_CUENTA_23").trim();
				cuenta[23] = rs.getString("INDINF_CUENTA_24").trim();
				cuenta[24] = rs.getString("INDINF_CUENTA_25").trim();
				cuenta[25] = rs.getString("INDINF_CUENTA_26").trim();
				cuenta[26] = rs.getString("INDINF_CUENTA_27").trim();
				cuenta[27] = rs.getString("INDINF_CUENTA_28").trim();
				cuenta[28] = rs.getString("INDINF_CUENTA_29").trim();
				cuenta[29] = rs.getString("INDINF_CUENTA_30").trim();
				cuenta[30] = rs.getString("INDINF_CUENTA_31").trim();
				cuenta[31] = rs.getString("INDINF_CUENTA_32").trim();
				cuenta[32] = rs.getString("INDINF_CUENTA_33").trim();
				cuenta[33] = rs.getString("INDINF_CUENTA_34").trim();
				cuenta[34] = rs.getString("INDINF_CUENTA_35").trim();
				cuenta[35] = rs.getString("INDINF_CUENTA_36").trim();

				contabilidad[0] = rs.getString("INDINF_CONTABILIDAD_1").trim();
				contabilidad[1] = rs.getString("INDINF_CONTABILIDAD_2").trim();
				contabilidad[2] = rs.getString("INDINF_CONTABILIDAD_3").trim();
				contabilidad[3] = rs.getString("INDINF_CONTABILIDAD_4").trim();
				contabilidad[4] = rs.getString("INDINF_CONTABILIDAD_5").trim();
				contabilidad[5] = rs.getString("INDINF_CONTABILIDAD_6").trim();
				contabilidad[6] = rs.getString("INDINF_CONTABILIDAD_7").trim();
				contabilidad[7] = rs.getString("INDINF_CONTABILIDAD_8").trim();
				contabilidad[8] = rs.getString("INDINF_CONTABILIDAD_9").trim();
				contabilidad[9] = rs.getString("INDINF_CONTABILIDAD_10").trim();
				contabilidad[10] = rs.getString("INDINF_CONTABILIDAD_11").trim();
				contabilidad[11] = rs.getString("INDINF_CONTABILIDAD_12").trim();
				contabilidad[12] = rs.getString("INDINF_CONTABILIDAD_13").trim();
				contabilidad[13] = rs.getString("INDINF_CONTABILIDAD_14").trim();
				contabilidad[14] = rs.getString("INDINF_CONTABILIDAD_15").trim();
				contabilidad[15] = rs.getString("INDINF_CONTABILIDAD_16").trim();
				contabilidad[16] = rs.getString("INDINF_CONTABILIDAD_17").trim();
				contabilidad[17] = rs.getString("INDINF_CONTABILIDAD_18").trim();
				contabilidad[18] = rs.getString("INDINF_CONTABILIDAD_19").trim();
				contabilidad[19] = rs.getString("INDINF_CONTABILIDAD_20").trim();
				contabilidad[20] = rs.getString("INDINF_CONTABILIDAD_21").trim();
				contabilidad[21] = rs.getString("INDINF_CONTABILIDAD_22").trim();
				contabilidad[22] = rs.getString("INDINF_CONTABILIDAD_23").trim();
				contabilidad[23] = rs.getString("INDINF_CONTABILIDAD_24").trim();
				contabilidad[24] = rs.getString("INDINF_CONTABILIDAD_25").trim();
				contabilidad[25] = rs.getString("INDINF_CONTABILIDAD_26").trim();
				contabilidad[26] = rs.getString("INDINF_CONTABILIDAD_27").trim();
				contabilidad[27] = rs.getString("INDINF_CONTABILIDAD_28").trim();
				contabilidad[28] = rs.getString("INDINF_CONTABILIDAD_29").trim();
				contabilidad[29] = rs.getString("INDINF_CONTABILIDAD_30").trim();
				contabilidad[30] = rs.getString("INDINF_CONTABILIDAD_31").trim();
				contabilidad[31] = rs.getString("INDINF_CONTABILIDAD_32").trim();
				contabilidad[32] = rs.getString("INDINF_CONTABILIDAD_33").trim();
				contabilidad[33] = rs.getString("INDINF_CONTABILIDAD_34").trim();
				contabilidad[34] = rs.getString("INDINF_CONTABILIDAD_35").trim();
				contabilidad[35] = rs.getString("INDINF_CONTABILIDAD_36").trim();

				masMenos[0] = rs.getString("INDINF_MASMENOS_1").trim();
				masMenos[1] = rs.getString("INDINF_MASMENOS_2").trim();
				masMenos[2] = rs.getString("INDINF_MASMENOS_3").trim();
				masMenos[3] = rs.getString("INDINF_MASMENOS_4").trim();
				masMenos[4] = rs.getString("INDINF_MASMENOS_5").trim();
				masMenos[5] = rs.getString("INDINF_MASMENOS_6").trim();
				masMenos[6] = rs.getString("INDINF_MASMENOS_7").trim();
				masMenos[7] = rs.getString("INDINF_MASMENOS_8").trim();
				masMenos[8] = rs.getString("INDINF_MASMENOS_9").trim();
				masMenos[9] = rs.getString("INDINF_MASMENOS_10").trim();
				masMenos[10] = rs.getString("INDINF_MASMENOS_11").trim();
				masMenos[11] = rs.getString("INDINF_MASMENOS_12").trim();
				masMenos[12] = rs.getString("INDINF_MASMENOS_13").trim();
				masMenos[13] = rs.getString("INDINF_MASMENOS_14").trim();
				masMenos[14] = rs.getString("INDINF_MASMENOS_15").trim();
				masMenos[15] = rs.getString("INDINF_MASMENOS_16").trim();
				masMenos[16] = rs.getString("INDINF_MASMENOS_17").trim();
				masMenos[17] = rs.getString("INDINF_MASMENOS_18").trim();
				masMenos[18] = rs.getString("INDINF_MASMENOS_19").trim();
				masMenos[19] = rs.getString("INDINF_MASMENOS_20").trim();
				masMenos[20] = rs.getString("INDINF_MASMENOS_21").trim();
				masMenos[21] = rs.getString("INDINF_MASMENOS_22").trim();
				masMenos[22] = rs.getString("INDINF_MASMENOS_23").trim();
				masMenos[23] = rs.getString("INDINF_MASMENOS_24").trim();
				masMenos[24] = rs.getString("INDINF_MASMENOS_25").trim();
				masMenos[25] = rs.getString("INDINF_MASMENOS_26").trim();
				masMenos[26] = rs.getString("INDINF_MASMENOS_27").trim();
				masMenos[27] = rs.getString("INDINF_MASMENOS_28").trim();
				masMenos[28] = rs.getString("INDINF_MASMENOS_29").trim();
				masMenos[29] = rs.getString("INDINF_MASMENOS_30").trim();
				masMenos[30] = rs.getString("INDINF_MASMENOS_31").trim();
				masMenos[31] = rs.getString("INDINF_MASMENOS_32").trim();
				masMenos[32] = rs.getString("INDINF_MASMENOS_33").trim();
				masMenos[33] = rs.getString("INDINF_MASMENOS_34").trim();
				masMenos[34] = rs.getString("INDINF_MASMENOS_35").trim();
				masMenos[35] = rs.getString("INDINF_MASMENOS_36").trim();

				porcentaje[0] = rs.getDouble("INDINF_PORCENT_1");
				porcentaje[1] = rs.getDouble("INDINF_PORCENT_2");
				porcentaje[2] = rs.getDouble("INDINF_PORCENT_3");
				porcentaje[3] = rs.getDouble("INDINF_PORCENT_4");
				porcentaje[4] = rs.getDouble("INDINF_PORCENT_5");
				porcentaje[5] = rs.getDouble("INDINF_PORCENT_6");
				porcentaje[6] = rs.getDouble("INDINF_PORCENT_7");
				porcentaje[7] = rs.getDouble("INDINF_PORCENT_8");
				porcentaje[8] = rs.getDouble("INDINF_PORCENT_9");
				porcentaje[9] = rs.getDouble("INDINF_PORCENT_10");
				porcentaje[10] = rs.getDouble("INDINF_PORCENT_11");
				porcentaje[11] = rs.getDouble("INDINF_PORCENT_12");
				porcentaje[12] = rs.getDouble("INDINF_PORCENT_13");
				porcentaje[13] = rs.getDouble("INDINF_PORCENT_14");
				porcentaje[14] = rs.getDouble("INDINF_PORCENT_15");
				porcentaje[15] = rs.getDouble("INDINF_PORCENT_16");
				porcentaje[16] = rs.getDouble("INDINF_PORCENT_17");
				porcentaje[17] = rs.getDouble("INDINF_PORCENT_18");
				porcentaje[18] = rs.getDouble("INDINF_PORCENT_19");
				porcentaje[19] = rs.getDouble("INDINF_PORCENT_20");
				porcentaje[20] = rs.getDouble("INDINF_PORCENT_21");
				porcentaje[21] = rs.getDouble("INDINF_PORCENT_22");
				porcentaje[22] = rs.getDouble("INDINF_PORCENT_23");
				porcentaje[23] = rs.getDouble("INDINF_PORCENT_24");
				porcentaje[24] = rs.getDouble("INDINF_PORCENT_25");
				porcentaje[25] = rs.getDouble("INDINF_PORCENT_26");
				porcentaje[26] = rs.getDouble("INDINF_PORCENT_27");
				porcentaje[27] = rs.getDouble("INDINF_PORCENT_28");
				porcentaje[28] = rs.getDouble("INDINF_PORCENT_29");
				porcentaje[29] = rs.getDouble("INDINF_PORCENT_30");
				porcentaje[30] = rs.getDouble("INDINF_PORCENT_31");
				porcentaje[31] = rs.getDouble("INDINF_PORCENT_32");
				porcentaje[32] = rs.getDouble("INDINF_PORCENT_33");
				porcentaje[33] = rs.getDouble("INDINF_PORCENT_34");
				porcentaje[34] = rs.getDouble("INDINF_PORCENT_35");
				porcentaje[35] = rs.getDouble("INDINF_PORCENT_36");

				deudorAcreedor[0] = rs.getString("INDINF_DEUDACRE_1").trim();
				deudorAcreedor[1] = rs.getString("INDINF_DEUDACRE_2").trim();
				deudorAcreedor[2] = rs.getString("INDINF_DEUDACRE_3").trim();
				deudorAcreedor[3] = rs.getString("INDINF_DEUDACRE_4").trim();
				deudorAcreedor[4] = rs.getString("INDINF_DEUDACRE_5").trim();
				deudorAcreedor[5] = rs.getString("INDINF_DEUDACRE_6").trim();
				deudorAcreedor[6] = rs.getString("INDINF_DEUDACRE_7").trim();
				deudorAcreedor[7] = rs.getString("INDINF_DEUDACRE_8").trim();
				deudorAcreedor[8] = rs.getString("INDINF_DEUDACRE_9").trim();
				deudorAcreedor[9] = rs.getString("INDINF_DEUDACRE_10").trim();
				deudorAcreedor[10] = rs.getString("INDINF_DEUDACRE_11").trim();
				deudorAcreedor[11] = rs.getString("INDINF_DEUDACRE_12").trim();
				deudorAcreedor[12] = rs.getString("INDINF_DEUDACRE_13").trim();
				deudorAcreedor[13] = rs.getString("INDINF_DEUDACRE_14").trim();
				deudorAcreedor[14] = rs.getString("INDINF_DEUDACRE_15").trim();
				deudorAcreedor[15] = rs.getString("INDINF_DEUDACRE_16").trim();
				deudorAcreedor[16] = rs.getString("INDINF_DEUDACRE_17").trim();
				deudorAcreedor[17] = rs.getString("INDINF_DEUDACRE_18").trim();
				deudorAcreedor[18] = rs.getString("INDINF_DEUDACRE_19").trim();
				deudorAcreedor[19] = rs.getString("INDINF_DEUDACRE_20").trim();
				deudorAcreedor[20] = rs.getString("INDINF_DEUDACRE_21").trim();
				deudorAcreedor[21] = rs.getString("INDINF_DEUDACRE_22").trim();
				deudorAcreedor[22] = rs.getString("INDINF_DEUDACRE_23").trim();
				deudorAcreedor[23] = rs.getString("INDINF_DEUDACRE_24").trim();
				deudorAcreedor[24] = rs.getString("INDINF_DEUDACRE_25").trim();
				deudorAcreedor[25] = rs.getString("INDINF_DEUDACRE_26").trim();
				deudorAcreedor[26] = rs.getString("INDINF_DEUDACRE_27").trim();
				deudorAcreedor[27] = rs.getString("INDINF_DEUDACRE_28").trim();
				deudorAcreedor[28] = rs.getString("INDINF_DEUDACRE_29").trim();
				deudorAcreedor[29] = rs.getString("INDINF_DEUDACRE_30").trim();
				deudorAcreedor[30] = rs.getString("INDINF_DEUDACRE_31").trim();
				deudorAcreedor[31] = rs.getString("INDINF_DEUDACRE_32").trim();
				deudorAcreedor[32] = rs.getString("INDINF_DEUDACRE_33").trim();
				deudorAcreedor[33] = rs.getString("INDINF_DEUDACRE_34").trim();
				deudorAcreedor[34] = rs.getString("INDINF_DEUDACRE_35").trim();
				deudorAcreedor[35] = rs.getString("INDINF_DEUDACRE_36").trim();

				importe = rs.getDouble("INDINF_IMP");
				importe1 = rs.getDouble("INDINF_IMP_1");
				importeMes = rs.getDouble("INDINF_IMP_MES");
				importeMes1 = rs.getDouble("INDINF_IMP_MES_1");			
				print = rs.getInt("INDINF_PRINT");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de IndiceInforme!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			balance = rs.getString("INDINF_BAL").trim();
			masa = rs.getString("INDINF_MASA").trim();
			capitulo = rs.getString("INDINF_CAP").trim();
			grupo = rs.getString("INDINF_GRU").trim();
			subGrupo = rs.getString("INDINF_SUBG").trim();
			titulo = rs.getString("INDINF_TITULO").trim();
			tituloAbreviado = rs.getString("INDINF_TITULO_ABREVIADO").trim();				

			cuenta[0] = rs.getString("INDINF_CUENTA_1").trim();
			cuenta[1] = rs.getString("INDINF_CUENTA_2").trim();
			cuenta[2] = rs.getString("INDINF_CUENTA_3").trim();
			cuenta[3] = rs.getString("INDINF_CUENTA_4").trim();
			cuenta[4] = rs.getString("INDINF_CUENTA_5").trim();
			cuenta[5] = rs.getString("INDINF_CUENTA_6").trim();
			cuenta[6] = rs.getString("INDINF_CUENTA_7").trim();
			cuenta[7] = rs.getString("INDINF_CUENTA_8").trim();
			cuenta[8] = rs.getString("INDINF_CUENTA_9").trim();
			cuenta[9] = rs.getString("INDINF_CUENTA_10").trim();
			cuenta[10] = rs.getString("INDINF_CUENTA_11").trim();
			cuenta[11] = rs.getString("INDINF_CUENTA_12").trim();
			cuenta[12] = rs.getString("INDINF_CUENTA_13").trim();
			cuenta[13] = rs.getString("INDINF_CUENTA_14").trim();
			cuenta[14] = rs.getString("INDINF_CUENTA_15").trim();
			cuenta[15] = rs.getString("INDINF_CUENTA_16").trim();
			cuenta[16] = rs.getString("INDINF_CUENTA_17").trim();
			cuenta[17] = rs.getString("INDINF_CUENTA_18").trim();
			cuenta[18] = rs.getString("INDINF_CUENTA_19").trim();
			cuenta[19] = rs.getString("INDINF_CUENTA_20").trim();
			cuenta[20] = rs.getString("INDINF_CUENTA_21").trim();
			cuenta[21] = rs.getString("INDINF_CUENTA_22").trim();
			cuenta[22] = rs.getString("INDINF_CUENTA_23").trim();
			cuenta[23] = rs.getString("INDINF_CUENTA_24").trim();
			cuenta[24] = rs.getString("INDINF_CUENTA_25").trim();
			cuenta[25] = rs.getString("INDINF_CUENTA_26").trim();
			cuenta[26] = rs.getString("INDINF_CUENTA_27").trim();
			cuenta[27] = rs.getString("INDINF_CUENTA_28").trim();
			cuenta[28] = rs.getString("INDINF_CUENTA_29").trim();
			cuenta[29] = rs.getString("INDINF_CUENTA_30").trim();
			cuenta[30] = rs.getString("INDINF_CUENTA_31").trim();
			cuenta[31] = rs.getString("INDINF_CUENTA_32").trim();
			cuenta[32] = rs.getString("INDINF_CUENTA_33").trim();
			cuenta[33] = rs.getString("INDINF_CUENTA_34").trim();
			cuenta[34] = rs.getString("INDINF_CUENTA_35").trim();
			cuenta[35] = rs.getString("INDINF_CUENTA_36").trim();

			contabilidad[0] = rs.getString("INDINF_CONTABILIDAD_1").trim();
			contabilidad[1] = rs.getString("INDINF_CONTABILIDAD_2").trim();
			contabilidad[2] = rs.getString("INDINF_CONTABILIDAD_3").trim();
			contabilidad[3] = rs.getString("INDINF_CONTABILIDAD_4").trim();
			contabilidad[4] = rs.getString("INDINF_CONTABILIDAD_5").trim();
			contabilidad[5] = rs.getString("INDINF_CONTABILIDAD_6").trim();
			contabilidad[6] = rs.getString("INDINF_CONTABILIDAD_7").trim();
			contabilidad[7] = rs.getString("INDINF_CONTABILIDAD_8").trim();
			contabilidad[8] = rs.getString("INDINF_CONTABILIDAD_9").trim();
			contabilidad[9] = rs.getString("INDINF_CONTABILIDAD_10").trim();
			contabilidad[10] = rs.getString("INDINF_CONTABILIDAD_11").trim();
			contabilidad[11] = rs.getString("INDINF_CONTABILIDAD_12").trim();
			contabilidad[12] = rs.getString("INDINF_CONTABILIDAD_13").trim();
			contabilidad[13] = rs.getString("INDINF_CONTABILIDAD_14").trim();
			contabilidad[14] = rs.getString("INDINF_CONTABILIDAD_15").trim();
			contabilidad[15] = rs.getString("INDINF_CONTABILIDAD_16").trim();
			contabilidad[16] = rs.getString("INDINF_CONTABILIDAD_17").trim();
			contabilidad[17] = rs.getString("INDINF_CONTABILIDAD_18").trim();
			contabilidad[18] = rs.getString("INDINF_CONTABILIDAD_19").trim();
			contabilidad[19] = rs.getString("INDINF_CONTABILIDAD_20").trim();
			contabilidad[20] = rs.getString("INDINF_CONTABILIDAD_21").trim();
			contabilidad[21] = rs.getString("INDINF_CONTABILIDAD_22").trim();
			contabilidad[22] = rs.getString("INDINF_CONTABILIDAD_23").trim();
			contabilidad[23] = rs.getString("INDINF_CONTABILIDAD_24").trim();
			contabilidad[24] = rs.getString("INDINF_CONTABILIDAD_25").trim();
			contabilidad[25] = rs.getString("INDINF_CONTABILIDAD_26").trim();
			contabilidad[26] = rs.getString("INDINF_CONTABILIDAD_27").trim();
			contabilidad[27] = rs.getString("INDINF_CONTABILIDAD_28").trim();
			contabilidad[28] = rs.getString("INDINF_CONTABILIDAD_29").trim();
			contabilidad[29] = rs.getString("INDINF_CONTABILIDAD_30").trim();
			contabilidad[30] = rs.getString("INDINF_CONTABILIDAD_31").trim();
			contabilidad[31] = rs.getString("INDINF_CONTABILIDAD_32").trim();
			contabilidad[32] = rs.getString("INDINF_CONTABILIDAD_33").trim();
			contabilidad[33] = rs.getString("INDINF_CONTABILIDAD_34").trim();
			contabilidad[34] = rs.getString("INDINF_CONTABILIDAD_35").trim();
			contabilidad[35] = rs.getString("INDINF_CONTABILIDAD_36").trim();

			masMenos[0] = rs.getString("INDINF_MASMENOS_1").trim();
			masMenos[1] = rs.getString("INDINF_MASMENOS_2").trim();
			masMenos[2] = rs.getString("INDINF_MASMENOS_3").trim();
			masMenos[3] = rs.getString("INDINF_MASMENOS_4").trim();
			masMenos[4] = rs.getString("INDINF_MASMENOS_5").trim();
			masMenos[5] = rs.getString("INDINF_MASMENOS_6").trim();
			masMenos[6] = rs.getString("INDINF_MASMENOS_7").trim();
			masMenos[7] = rs.getString("INDINF_MASMENOS_8").trim();
			masMenos[8] = rs.getString("INDINF_MASMENOS_9").trim();
			masMenos[9] = rs.getString("INDINF_MASMENOS_10").trim();
			masMenos[10] = rs.getString("INDINF_MASMENOS_11").trim();
			masMenos[11] = rs.getString("INDINF_MASMENOS_12").trim();
			masMenos[12] = rs.getString("INDINF_MASMENOS_13").trim();
			masMenos[13] = rs.getString("INDINF_MASMENOS_14").trim();
			masMenos[14] = rs.getString("INDINF_MASMENOS_15").trim();
			masMenos[15] = rs.getString("INDINF_MASMENOS_16").trim();
			masMenos[16] = rs.getString("INDINF_MASMENOS_17").trim();
			masMenos[17] = rs.getString("INDINF_MASMENOS_18").trim();
			masMenos[18] = rs.getString("INDINF_MASMENOS_19").trim();
			masMenos[19] = rs.getString("INDINF_MASMENOS_20").trim();
			masMenos[20] = rs.getString("INDINF_MASMENOS_21").trim();
			masMenos[21] = rs.getString("INDINF_MASMENOS_22").trim();
			masMenos[22] = rs.getString("INDINF_MASMENOS_23").trim();
			masMenos[23] = rs.getString("INDINF_MASMENOS_24").trim();
			masMenos[24] = rs.getString("INDINF_MASMENOS_25").trim();
			masMenos[25] = rs.getString("INDINF_MASMENOS_26").trim();
			masMenos[26] = rs.getString("INDINF_MASMENOS_27").trim();
			masMenos[27] = rs.getString("INDINF_MASMENOS_28").trim();
			masMenos[28] = rs.getString("INDINF_MASMENOS_29").trim();
			masMenos[29] = rs.getString("INDINF_MASMENOS_30").trim();
			masMenos[30] = rs.getString("INDINF_MASMENOS_31").trim();
			masMenos[31] = rs.getString("INDINF_MASMENOS_32").trim();
			masMenos[32] = rs.getString("INDINF_MASMENOS_33").trim();
			masMenos[33] = rs.getString("INDINF_MASMENOS_34").trim();
			masMenos[34] = rs.getString("INDINF_MASMENOS_35").trim();
			masMenos[35] = rs.getString("INDINF_MASMENOS_36").trim();

			porcentaje[0] = rs.getDouble("INDINF_PORCENT_1");
			porcentaje[1] = rs.getDouble("INDINF_PORCENT_2");
			porcentaje[2] = rs.getDouble("INDINF_PORCENT_3");
			porcentaje[3] = rs.getDouble("INDINF_PORCENT_4");
			porcentaje[4] = rs.getDouble("INDINF_PORCENT_5");
			porcentaje[5] = rs.getDouble("INDINF_PORCENT_6");
			porcentaje[6] = rs.getDouble("INDINF_PORCENT_7");
			porcentaje[7] = rs.getDouble("INDINF_PORCENT_8");
			porcentaje[8] = rs.getDouble("INDINF_PORCENT_9");
			porcentaje[9] = rs.getDouble("INDINF_PORCENT_10");
			porcentaje[10] = rs.getDouble("INDINF_PORCENT_11");
			porcentaje[11] = rs.getDouble("INDINF_PORCENT_12");
			porcentaje[12] = rs.getDouble("INDINF_PORCENT_13");
			porcentaje[13] = rs.getDouble("INDINF_PORCENT_14");
			porcentaje[14] = rs.getDouble("INDINF_PORCENT_15");
			porcentaje[15] = rs.getDouble("INDINF_PORCENT_16");
			porcentaje[16] = rs.getDouble("INDINF_PORCENT_17");
			porcentaje[17] = rs.getDouble("INDINF_PORCENT_18");
			porcentaje[18] = rs.getDouble("INDINF_PORCENT_19");
			porcentaje[19] = rs.getDouble("INDINF_PORCENT_20");
			porcentaje[20] = rs.getDouble("INDINF_PORCENT_21");
			porcentaje[21] = rs.getDouble("INDINF_PORCENT_22");
			porcentaje[22] = rs.getDouble("INDINF_PORCENT_23");
			porcentaje[23] = rs.getDouble("INDINF_PORCENT_24");
			porcentaje[24] = rs.getDouble("INDINF_PORCENT_25");
			porcentaje[25] = rs.getDouble("INDINF_PORCENT_26");
			porcentaje[26] = rs.getDouble("INDINF_PORCENT_27");
			porcentaje[27] = rs.getDouble("INDINF_PORCENT_28");
			porcentaje[28] = rs.getDouble("INDINF_PORCENT_29");
			porcentaje[29] = rs.getDouble("INDINF_PORCENT_30");
			porcentaje[30] = rs.getDouble("INDINF_PORCENT_31");
			porcentaje[31] = rs.getDouble("INDINF_PORCENT_32");
			porcentaje[32] = rs.getDouble("INDINF_PORCENT_33");
			porcentaje[33] = rs.getDouble("INDINF_PORCENT_34");
			porcentaje[34] = rs.getDouble("INDINF_PORCENT_35");
			porcentaje[35] = rs.getDouble("INDINF_PORCENT_36");

			deudorAcreedor[0] = rs.getString("INDINF_DEUDACRE_1").trim();
			deudorAcreedor[1] = rs.getString("INDINF_DEUDACRE_2").trim();
			deudorAcreedor[2] = rs.getString("INDINF_DEUDACRE_3").trim();
			deudorAcreedor[3] = rs.getString("INDINF_DEUDACRE_4").trim();
			deudorAcreedor[4] = rs.getString("INDINF_DEUDACRE_5").trim();
			deudorAcreedor[5] = rs.getString("INDINF_DEUDACRE_6").trim();
			deudorAcreedor[6] = rs.getString("INDINF_DEUDACRE_7").trim();
			deudorAcreedor[7] = rs.getString("INDINF_DEUDACRE_8").trim();
			deudorAcreedor[8] = rs.getString("INDINF_DEUDACRE_9").trim();
			deudorAcreedor[9] = rs.getString("INDINF_DEUDACRE_10").trim();
			deudorAcreedor[10] = rs.getString("INDINF_DEUDACRE_11").trim();
			deudorAcreedor[11] = rs.getString("INDINF_DEUDACRE_12").trim();
			deudorAcreedor[12] = rs.getString("INDINF_DEUDACRE_13").trim();
			deudorAcreedor[13] = rs.getString("INDINF_DEUDACRE_14").trim();
			deudorAcreedor[14] = rs.getString("INDINF_DEUDACRE_15").trim();
			deudorAcreedor[15] = rs.getString("INDINF_DEUDACRE_16").trim();
			deudorAcreedor[16] = rs.getString("INDINF_DEUDACRE_17").trim();
			deudorAcreedor[17] = rs.getString("INDINF_DEUDACRE_18").trim();
			deudorAcreedor[18] = rs.getString("INDINF_DEUDACRE_19").trim();
			deudorAcreedor[19] = rs.getString("INDINF_DEUDACRE_20").trim();
			deudorAcreedor[20] = rs.getString("INDINF_DEUDACRE_21").trim();
			deudorAcreedor[21] = rs.getString("INDINF_DEUDACRE_22").trim();
			deudorAcreedor[22] = rs.getString("INDINF_DEUDACRE_23").trim();
			deudorAcreedor[23] = rs.getString("INDINF_DEUDACRE_24").trim();
			deudorAcreedor[24] = rs.getString("INDINF_DEUDACRE_25").trim();
			deudorAcreedor[25] = rs.getString("INDINF_DEUDACRE_26").trim();
			deudorAcreedor[26] = rs.getString("INDINF_DEUDACRE_27").trim();
			deudorAcreedor[27] = rs.getString("INDINF_DEUDACRE_28").trim();
			deudorAcreedor[28] = rs.getString("INDINF_DEUDACRE_29").trim();
			deudorAcreedor[29] = rs.getString("INDINF_DEUDACRE_30").trim();
			deudorAcreedor[30] = rs.getString("INDINF_DEUDACRE_31").trim();
			deudorAcreedor[31] = rs.getString("INDINF_DEUDACRE_32").trim();
			deudorAcreedor[32] = rs.getString("INDINF_DEUDACRE_33").trim();
			deudorAcreedor[33] = rs.getString("INDINF_DEUDACRE_34").trim();
			deudorAcreedor[34] = rs.getString("INDINF_DEUDACRE_35").trim();
			deudorAcreedor[35] = rs.getString("INDINF_DEUDACRE_36").trim();

			importe = rs.getDouble("INDINF_IMP");
			importe1 = rs.getDouble("INDINF_IMP_1");
			importeMes = rs.getDouble("INDINF_IMP_MES");
			importeMes1 = rs.getDouble("INDINF_IMP_MES_1");			
			print = rs.getInt("INDINF_PRINT");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de IndiceInforme!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}
	
	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO INDINF " +
						   "(EMPRESA, " +
						   "INDINF_BAL, " +
						   "INDINF_MASA, " +
						   "INDINF_CAP, " +
						   "INDINF_GRU, " +
						   "INDINF_SUBG, " +
						   "INDINF_TITULO, " +
						   "INDINF_TITULO_ABREVIADO, " +
						   "INDINF_CUENTA_1, " +
						   "INDINF_CUENTA_2, " +
						   "INDINF_CUENTA_3, " +
						   "INDINF_CUENTA_4, " +
						   "INDINF_CUENTA_5, " +
						   "INDINF_CUENTA_6, " +
						   "INDINF_CUENTA_7, " +
						   "INDINF_CUENTA_8, " +
						   "INDINF_CUENTA_9, " +
						   "INDINF_CUENTA_10, " +
						   "INDINF_CUENTA_11, " +
						   "INDINF_CUENTA_12, " +
						   "INDINF_CUENTA_13, " +
						   "INDINF_CUENTA_14, " +
						   "INDINF_CUENTA_15, " +
						   "INDINF_CUENTA_16, " +
						   "INDINF_CUENTA_17, " +
						   "INDINF_CUENTA_18, " +
						   "INDINF_CUENTA_19, " +
						   "INDINF_CUENTA_20, " +
						   "INDINF_CUENTA_21, " +
						   "INDINF_CUENTA_22, " +
						   "INDINF_CUENTA_23, " +
						   "INDINF_CUENTA_24, " +
						   "INDINF_CUENTA_25, " +
						   "INDINF_CUENTA_26, " +
						   "INDINF_CUENTA_27, " +
						   "INDINF_CUENTA_28, " +
						   "INDINF_CUENTA_29, " +
						   "INDINF_CUENTA_30, " +
						   "INDINF_CUENTA_31, " +
						   "INDINF_CUENTA_32, " +
						   "INDINF_CUENTA_33, " +
						   "INDINF_CUENTA_34, " +
						   "INDINF_CUENTA_35, " +
						   "INDINF_CUENTA_36, " +
						   "INDINF_CONTABILIDAD_1, " +
						   "INDINF_CONTABILIDAD_2, " +
						   "INDINF_CONTABILIDAD_3, " +
						   "INDINF_CONTABILIDAD_4, " +
						   "INDINF_CONTABILIDAD_5, " +
						   "INDINF_CONTABILIDAD_6, " +
						   "INDINF_CONTABILIDAD_7, " +
						   "INDINF_CONTABILIDAD_8, " +
						   "INDINF_CONTABILIDAD_9, " +
						   "INDINF_CONTABILIDAD_10, " +
						   "INDINF_CONTABILIDAD_11, " +
						   "INDINF_CONTABILIDAD_12, " +
						   "INDINF_CONTABILIDAD_13, " +
						   "INDINF_CONTABILIDAD_14, " +
						   "INDINF_CONTABILIDAD_15, " +
						   "INDINF_CONTABILIDAD_16, " +
						   "INDINF_CONTABILIDAD_17, " +
						   "INDINF_CONTABILIDAD_18, " +
						   "INDINF_CONTABILIDAD_19, " +
						   "INDINF_CONTABILIDAD_20, " +
						   "INDINF_CONTABILIDAD_21, " +
						   "INDINF_CONTABILIDAD_22, " +
						   "INDINF_CONTABILIDAD_23, " +
						   "INDINF_CONTABILIDAD_24, " +
						   "INDINF_CONTABILIDAD_25, " +
						   "INDINF_CONTABILIDAD_26, " +
						   "INDINF_CONTABILIDAD_27, " +
						   "INDINF_CONTABILIDAD_28, " +
						   "INDINF_CONTABILIDAD_29, " +
						   "INDINF_CONTABILIDAD_30, " +
						   "INDINF_CONTABILIDAD_31, " +
						   "INDINF_CONTABILIDAD_32, " +
						   "INDINF_CONTABILIDAD_33, " +
						   "INDINF_CONTABILIDAD_34, " +
						   "INDINF_CONTABILIDAD_35, " +
						   "INDINF_CONTABILIDAD_36, " +
						   "INDINF_MASMENOS_1, " +
						   "INDINF_MASMENOS_2, " +
						   "INDINF_MASMENOS_3, " +
						   "INDINF_MASMENOS_4, " +
						   "INDINF_MASMENOS_5, " +
						   "INDINF_MASMENOS_6, " +
						   "INDINF_MASMENOS_7, " +
						   "INDINF_MASMENOS_8, " +
						   "INDINF_MASMENOS_9, " +
						   "INDINF_MASMENOS_10, " +
						   "INDINF_MASMENOS_11, " +
						   "INDINF_MASMENOS_12, " +
						   "INDINF_MASMENOS_13, " +
						   "INDINF_MASMENOS_14, " +
						   "INDINF_MASMENOS_15, " +
						   "INDINF_MASMENOS_16, " +
						   "INDINF_MASMENOS_17, " +
						   "INDINF_MASMENOS_18, " +
						   "INDINF_MASMENOS_19, " +
						   "INDINF_MASMENOS_20, " +
						   "INDINF_MASMENOS_21, " +
						   "INDINF_MASMENOS_22, " +
						   "INDINF_MASMENOS_23, " +
						   "INDINF_MASMENOS_24, " +
						   "INDINF_MASMENOS_25, " +
						   "INDINF_MASMENOS_26, " +
						   "INDINF_MASMENOS_27, " +
						   "INDINF_MASMENOS_28, " +
						   "INDINF_MASMENOS_29, " +
						   "INDINF_MASMENOS_30, " +
						   "INDINF_MASMENOS_31, " +
						   "INDINF_MASMENOS_32, " +
						   "INDINF_MASMENOS_33, " +
						   "INDINF_MASMENOS_34, " +
						   "INDINF_MASMENOS_35, " +
						   "INDINF_MASMENOS_36, " +
						   "INDINF_PORCENT_1, " +
						   "INDINF_PORCENT_2, " +
						   "INDINF_PORCENT_3, " +
						   "INDINF_PORCENT_4, " +
						   "INDINF_PORCENT_5, " +
						   "INDINF_PORCENT_6, " +
						   "INDINF_PORCENT_7, " +
						   "INDINF_PORCENT_8, " +
						   "INDINF_PORCENT_9, " +
						   "INDINF_PORCENT_10, " +
						   "INDINF_PORCENT_11, " +
						   "INDINF_PORCENT_12, " +
						   "INDINF_PORCENT_13, " +
						   "INDINF_PORCENT_14, " +
						   "INDINF_PORCENT_15, " +
						   "INDINF_PORCENT_16, " +
						   "INDINF_PORCENT_17, " +
						   "INDINF_PORCENT_18, " +
						   "INDINF_PORCENT_19, " +
						   "INDINF_PORCENT_20, " +
						   "INDINF_PORCENT_21, " +
						   "INDINF_PORCENT_22, " +
						   "INDINF_PORCENT_23, " +
						   "INDINF_PORCENT_24, " +
						   "INDINF_PORCENT_25, " +
						   "INDINF_PORCENT_26, " +
						   "INDINF_PORCENT_27, " +
						   "INDINF_PORCENT_28, " +
						   "INDINF_PORCENT_29, " +
						   "INDINF_PORCENT_30, " +
						   "INDINF_PORCENT_31, " +
						   "INDINF_PORCENT_32, " +
						   "INDINF_PORCENT_33, " +
						   "INDINF_PORCENT_34, " +
						   "INDINF_PORCENT_35, " +
						   "INDINF_PORCENT_36, " +
						   "INDINF_DEUDACRE_1, " +
						   "INDINF_DEUDACRE_2, " +
						   "INDINF_DEUDACRE_3, " +
						   "INDINF_DEUDACRE_4, " +
						   "INDINF_DEUDACRE_5, " +
						   "INDINF_DEUDACRE_6, " +
						   "INDINF_DEUDACRE_7, " +
						   "INDINF_DEUDACRE_8, " +
						   "INDINF_DEUDACRE_9, " +
						   "INDINF_DEUDACRE_10, " +
						   "INDINF_DEUDACRE_11, " +
						   "INDINF_DEUDACRE_12, " +
						   "INDINF_DEUDACRE_13, " +
						   "INDINF_DEUDACRE_14, " +
						   "INDINF_DEUDACRE_15, " +
						   "INDINF_DEUDACRE_16, " +
						   "INDINF_DEUDACRE_17, " +
						   "INDINF_DEUDACRE_18, " +
						   "INDINF_DEUDACRE_19, " +
						   "INDINF_DEUDACRE_20, " +
						   "INDINF_DEUDACRE_21, " +
						   "INDINF_DEUDACRE_22, " +
						   "INDINF_DEUDACRE_23, " +
						   "INDINF_DEUDACRE_24, " +
						   "INDINF_DEUDACRE_25, " +
						   "INDINF_DEUDACRE_26, " +
						   "INDINF_DEUDACRE_27, " +
						   "INDINF_DEUDACRE_28, " +
						   "INDINF_DEUDACRE_29, " +
						   "INDINF_DEUDACRE_30, " +
						   "INDINF_DEUDACRE_31, " +
						   "INDINF_DEUDACRE_32, " +
						   "INDINF_DEUDACRE_33, " +
						   "INDINF_DEUDACRE_34, " +
						   "INDINF_DEUDACRE_35, " +
						   "INDINF_DEUDACRE_36, " +
						   "INDINF_IMP, " +
						   "INDINF_IMP_1, " +
						   "INDINF_IMP_MES, " +
						   "INDINF_IMP_MES_1, " +						   
						   "INDINF_PRINT) " +						   
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
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "INDINF_BAL = ?, " +
						   "INDINF_MASA = ?, " +
						   "INDINF_CAP = ?, " +
						   "INDINF_GRU = ?, " +
						   "INDINF_SUBG = ?, " +
						   "INDINF_TITULO = ?, " +
						   "INDINF_TITULO_ABREVIADO = ?, " +
						   "INDINF_CUENTA_1 = ?, " +
						   "INDINF_CUENTA_2 = ?, " +
						   "INDINF_CUENTA_3 = ?, " +
						   "INDINF_CUENTA_4 = ?, " +
						   "INDINF_CUENTA_5 = ?, " +
						   "INDINF_CUENTA_6 = ?, " +
						   "INDINF_CUENTA_7 = ?, " +
						   "INDINF_CUENTA_8 = ?, " +
						   "INDINF_CUENTA_9 = ?, " +
						   "INDINF_CUENTA_10 = ?, " +
						   "INDINF_CUENTA_11 = ?, " +
						   "INDINF_CUENTA_12 = ?, " +
						   "INDINF_CUENTA_13 = ?, " +
						   "INDINF_CUENTA_14 = ?, " +
						   "INDINF_CUENTA_15 = ?, " +
						   "INDINF_CUENTA_16 = ?, " +
						   "INDINF_CUENTA_17 = ?, " +
						   "INDINF_CUENTA_18 = ?, " +
						   "INDINF_CUENTA_19 = ?, " +
						   "INDINF_CUENTA_20 = ?, " +
						   "INDINF_CUENTA_21 = ?, " +
						   "INDINF_CUENTA_22 = ?, " +
						   "INDINF_CUENTA_23 = ?, " +
						   "INDINF_CUENTA_24 = ?, " +
						   "INDINF_CUENTA_25 = ?, " +
						   "INDINF_CUENTA_26 = ?, " +
						   "INDINF_CUENTA_27 = ?, " +
						   "INDINF_CUENTA_28 = ?, " +
						   "INDINF_CUENTA_29 = ?, " +
						   "INDINF_CUENTA_30 = ?, " +
						   "INDINF_CUENTA_31 = ?, " +
						   "INDINF_CUENTA_32 = ?, " +
						   "INDINF_CUENTA_33 = ?, " +
						   "INDINF_CUENTA_34 = ?, " +
						   "INDINF_CUENTA_35 = ?, " +
						   "INDINF_CUENTA_36 = ?, " +
						   "INDINF_CONTABILIDAD_1 = ?, " +
						   "INDINF_CONTABILIDAD_2 = ?, " +
						   "INDINF_CONTABILIDAD_3 = ?, " +
						   "INDINF_CONTABILIDAD_4 = ?, " +
						   "INDINF_CONTABILIDAD_5 = ?, " +
						   "INDINF_CONTABILIDAD_6 = ?, " +
						   "INDINF_CONTABILIDAD_7 = ?, " +
						   "INDINF_CONTABILIDAD_8 = ?, " +
						   "INDINF_CONTABILIDAD_9 = ?, " +
						   "INDINF_CONTABILIDAD_10 = ?, " +
						   "INDINF_CONTABILIDAD_11 = ?, " +
						   "INDINF_CONTABILIDAD_12 = ?, " +
						   "INDINF_CONTABILIDAD_13 = ?, " +
						   "INDINF_CONTABILIDAD_14 = ?, " +
						   "INDINF_CONTABILIDAD_15 = ?, " +
						   "INDINF_CONTABILIDAD_16 = ?, " +
						   "INDINF_CONTABILIDAD_17 = ?, " +
						   "INDINF_CONTABILIDAD_18 = ?, " +
						   "INDINF_CONTABILIDAD_19 = ?, " +
						   "INDINF_CONTABILIDAD_20 = ?, " +
						   "INDINF_CONTABILIDAD_21 = ?, " +
						   "INDINF_CONTABILIDAD_22 = ?, " +
						   "INDINF_CONTABILIDAD_23 = ?, " +
						   "INDINF_CONTABILIDAD_24 = ?, " +
						   "INDINF_CONTABILIDAD_25 = ?, " +
						   "INDINF_CONTABILIDAD_26 = ?, " +
						   "INDINF_CONTABILIDAD_27 = ?, " +
						   "INDINF_CONTABILIDAD_28 = ?, " +
						   "INDINF_CONTABILIDAD_29 = ?, " +
						   "INDINF_CONTABILIDAD_30 = ?, " +
						   "INDINF_CONTABILIDAD_31 = ?, " +
						   "INDINF_CONTABILIDAD_32 = ?, " +
						   "INDINF_CONTABILIDAD_33 = ?, " +
						   "INDINF_CONTABILIDAD_34 = ?, " +
						   "INDINF_CONTABILIDAD_35 = ?, " +
						   "INDINF_CONTABILIDAD_36 = ?, " +
						   "INDINF_MASMENOS_1 = ?, " +
						   "INDINF_MASMENOS_2 = ?, " +
						   "INDINF_MASMENOS_3 = ?, " +
						   "INDINF_MASMENOS_4 = ?, " +
						   "INDINF_MASMENOS_5 = ?, " +
						   "INDINF_MASMENOS_6 = ?, " +
						   "INDINF_MASMENOS_7 = ?, " +
						   "INDINF_MASMENOS_8 = ?, " +
						   "INDINF_MASMENOS_9 = ?, " +
						   "INDINF_MASMENOS_10 = ?, " +
						   "INDINF_MASMENOS_11 = ?, " +
						   "INDINF_MASMENOS_12 = ?, " +
						   "INDINF_MASMENOS_13 = ?, " +
						   "INDINF_MASMENOS_14 = ?, " +
						   "INDINF_MASMENOS_15 = ?, " +
						   "INDINF_MASMENOS_16 = ?, " +
						   "INDINF_MASMENOS_17 = ?, " +
						   "INDINF_MASMENOS_18 = ?, " +
						   "INDINF_MASMENOS_19 = ?, " +
						   "INDINF_MASMENOS_20 = ?, " +
						   "INDINF_MASMENOS_21 = ?, " +
						   "INDINF_MASMENOS_22 = ?, " +
						   "INDINF_MASMENOS_23 = ?, " +
						   "INDINF_MASMENOS_24 = ?, " +
						   "INDINF_MASMENOS_25 = ?, " +
						   "INDINF_MASMENOS_26 = ?, " +
						   "INDINF_MASMENOS_27 = ?, " +
						   "INDINF_MASMENOS_28 = ?, " +
						   "INDINF_MASMENOS_29 = ?, " +
						   "INDINF_MASMENOS_30 = ?, " +
						   "INDINF_MASMENOS_31 = ?, " +
						   "INDINF_MASMENOS_32 = ?, " +
						   "INDINF_MASMENOS_33 = ?, " +
						   "INDINF_MASMENOS_34 = ?, " +
						   "INDINF_MASMENOS_35 = ?, " +
						   "INDINF_MASMENOS_36 = ?, " +
						   "INDINF_PORCENT_1 = ?, " +
						   "INDINF_PORCENT_2 = ?, " +
						   "INDINF_PORCENT_3 = ?, " +
						   "INDINF_PORCENT_4 = ?, " +
						   "INDINF_PORCENT_5 = ?, " +
						   "INDINF_PORCENT_6 = ?, " +
						   "INDINF_PORCENT_7 = ?, " +
						   "INDINF_PORCENT_8 = ?, " +
						   "INDINF_PORCENT_9 = ?, " +
						   "INDINF_PORCENT_10 = ?, " +
						   "INDINF_PORCENT_11 = ?, " +
						   "INDINF_PORCENT_12 = ?, " +
						   "INDINF_PORCENT_13 = ?, " +
						   "INDINF_PORCENT_14 = ?, " +
						   "INDINF_PORCENT_15 = ?, " +
						   "INDINF_PORCENT_16 = ?, " +
						   "INDINF_PORCENT_17 = ?, " +
						   "INDINF_PORCENT_18 = ?, " +
						   "INDINF_PORCENT_19 = ?, " +
						   "INDINF_PORCENT_20 = ?, " +
						   "INDINF_PORCENT_21 = ?, " +
						   "INDINF_PORCENT_22 = ?, " +
						   "INDINF_PORCENT_23 = ?, " +
						   "INDINF_PORCENT_24 = ?, " +
						   "INDINF_PORCENT_25 = ?, " +
						   "INDINF_PORCENT_26 = ?, " +
						   "INDINF_PORCENT_27 = ?, " +
						   "INDINF_PORCENT_28 = ?, " +
						   "INDINF_PORCENT_29 = ?, " +
						   "INDINF_PORCENT_30 = ?, " +
						   "INDINF_PORCENT_31 = ?, " +
						   "INDINF_PORCENT_32 = ?, " +
						   "INDINF_PORCENT_33 = ?, " +
						   "INDINF_PORCENT_34 = ?, " +
						   "INDINF_PORCENT_35 = ?, " +
						   "INDINF_PORCENT_36 = ?, " +
						   "INDINF_DEUDACRE_1 = ?, " +
						   "INDINF_DEUDACRE_2 = ?, " +
						   "INDINF_DEUDACRE_3 = ?, " +
						   "INDINF_DEUDACRE_4 = ?, " +
						   "INDINF_DEUDACRE_5 = ?, " +
						   "INDINF_DEUDACRE_6 = ?, " +
						   "INDINF_DEUDACRE_7 = ?, " +
						   "INDINF_DEUDACRE_8 = ?, " +
						   "INDINF_DEUDACRE_9 = ?, " +
						   "INDINF_DEUDACRE_10 = ?, " +
						   "INDINF_DEUDACRE_11 = ?, " +
						   "INDINF_DEUDACRE_12 = ?, " +
						   "INDINF_DEUDACRE_13 = ?, " +
						   "INDINF_DEUDACRE_14 = ?, " +
						   "INDINF_DEUDACRE_15 = ?, " +
						   "INDINF_DEUDACRE_16 = ?, " +
						   "INDINF_DEUDACRE_17 = ?, " +
						   "INDINF_DEUDACRE_18 = ?, " +
						   "INDINF_DEUDACRE_19 = ?, " +
						   "INDINF_DEUDACRE_20 = ?, " +
						   "INDINF_DEUDACRE_21 = ?, " +
						   "INDINF_DEUDACRE_22 = ?, " +
						   "INDINF_DEUDACRE_23 = ?, " +
						   "INDINF_DEUDACRE_24 = ?, " +
						   "INDINF_DEUDACRE_25 = ?, " +
						   "INDINF_DEUDACRE_26 = ?, " +
						   "INDINF_DEUDACRE_27 = ?, " +
						   "INDINF_DEUDACRE_28 = ?, " +
						   "INDINF_DEUDACRE_29 = ?, " +
						   "INDINF_DEUDACRE_30 = ?, " +
						   "INDINF_DEUDACRE_31 = ?, " +
						   "INDINF_DEUDACRE_32 = ?, " +
						   "INDINF_DEUDACRE_33 = ?, " +
						   "INDINF_DEUDACRE_34 = ?, " +
						   "INDINF_DEUDACRE_35 = ?, " +
						   "INDINF_DEUDACRE_36 = ?, " +
						   "INDINF_IMP = ?, " +
						   "INDINF_IMP_1 = ?, " +
						   "INDINF_IMP_MES = ?, " +
						   "INDINF_IMP_MES_1 = ?, " +						   
						   "INDINF_PRINT = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(balance, 3));
			ps.setString(i++, Cadena.left(masa, 3));
			ps.setString(i++, Cadena.left(capitulo, 3));
			ps.setString(i++, Cadena.left(grupo, 3));
			ps.setString(i++, Cadena.left(subGrupo, 3));
			ps.setString(i++, Cadena.left(titulo, 46));
			ps.setString(i++, Cadena.left(tituloAbreviado, 33));
			for(int j = 0; j < 36; j++)
				ps.setString(i++, Cadena.left(cuenta[j], 9));
			for(int j = 0; j < 36; j++)
				ps.setString(i++, Cadena.left(contabilidad[j], 1));
			for(int j = 0; j < 36; j++)
				ps.setString(i++, Cadena.left(masMenos[j], 1));
			for(int j = 0; j < 36; j++)
				ps.setDouble(i++, porcentaje[j]);
			for(int j = 0; j < 36; j++)
				ps.setString(i++, Cadena.left(deudorAcreedor[j], 1));
			ps.setDouble(i++, importe);
			ps.setDouble(i++, importe1);
			ps.setDouble(i++, importeMes);
			ps.setDouble(i++, importeMes1);
			ps.setInt(i++, print);
						
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(balance, 3));
			ps.setString(i++, Cadena.left(masa, 3));
			ps.setString(i++, Cadena.left(capitulo, 3));
			ps.setString(i++, Cadena.left(grupo, 3));
			ps.setString(i++, Cadena.left(subGrupo, 3));
			ps.setString(i++, Cadena.left(titulo, 46));
			ps.setString(i++, Cadena.left(tituloAbreviado, 33));
			for(int j = 0; j < 36; j++)
				ps.setString(i++, Cadena.left(cuenta[j], 9));
			for(int j = 0; j < 36; j++)
				ps.setString(i++, Cadena.left(contabilidad[j], 1));
			for(int j = 0; j < 36; j++)
				ps.setString(i++, Cadena.left(masMenos[j], 1));
			for(int j = 0; j < 36; j++)
				ps.setDouble(i++, porcentaje[j]);
			for(int j = 0; j < 36; j++)
				ps.setString(i++, Cadena.left(deudorAcreedor[j], 1));
			ps.setDouble(i++, importe);
			ps.setDouble(i++, importe1);
			ps.setDouble(i++, importeMes);
			ps.setDouble(i++, importeMes1);
			ps.setInt(i++, print);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de IndiceInforme!!!");
				e.printStackTrace();
			}
		}
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getMasa() {
		return masa;
	}

	public void setMasa(String masa) {
		this.masa = masa;
	}

	public String getCapitulo() {
		return capitulo;
	}

	public void setCapitulo(String capitulo) {
		this.capitulo = capitulo;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getSubGrupo() {
		return subGrupo;
	}

	public void setSubGrupo(String subGrupo) {
		this.subGrupo = subGrupo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTituloAbreviado() {
		return tituloAbreviado;
	}

	public void setTituloAbreviado(String tituloAbreviado) {
		this.tituloAbreviado = tituloAbreviado;
	}

	public String getCuenta(int i) {
		return cuenta[i];
	}
	
	public String[] getCuenta() {
		return cuenta;
	}

	public void setCuenta(int indice, String valor) {
		this.cuenta[indice] = valor;
	}
	
	public void setCuenta(String[] cuenta) {
		this.cuenta = cuenta;
	}

	public String getContabilidad(int i) {
		return contabilidad[i];
	}
	
	public String[] getContabilidad() {
		return contabilidad;
	}

	public void setContabilidad(int indice, String valor) {
		this.contabilidad[indice] = valor;
	}
	
	public void setContabilidad(String[] contabilidad) {
		this.contabilidad = contabilidad;
	}

	public String getMasMenos(int i) {
		return masMenos[i];
	}
	
	public String[] getMasMenos() {
		return masMenos;
	}

	public void setMasMenos(int indice, String valor) {
		this.masMenos[indice] = valor;
	}
	
	public void setMasMenos(String[] masMenos) {
		this.masMenos = masMenos;
	}

	public double getPorcentaje(int i) {
		return porcentaje[i];
	}
	
	public double[] getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(int indice, double valor) {
		this.porcentaje[indice] = valor;
	}

	public void setPorcentaje(double[] porcentaje) {
		this.porcentaje = porcentaje;
	}

	public String getDeudorAcreedor(int i) {
		return deudorAcreedor[i];
	}
	
	public String[] getDeudorAcreedor() {
		return deudorAcreedor;
	}

	public void setDeudorAcreedor(int indice, String valor) {
		this.deudorAcreedor[indice] = valor;
	}
	
	public void setDeudorAcreedor(String[] deudorAcreedor) {
		this.deudorAcreedor = deudorAcreedor;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public double getImporte1() {
		return importe1;
	}

	public void setImporte1(double importe1) {
		this.importe1 = importe1;
	}

	public double getImporteMes() {
		return importeMes;
	}

	public void setImporteMes(double importeMes) {
		this.importeMes = importeMes;
	}

	public double getImporteMes1() {
		return importeMes1;
	}

	public void setImporteMes1(double importeMes1) {
		this.importeMes1 = importeMes1;
	}

	public int getPrint() {
		return print;
	}

	public void setPrint(int print) {
		this.print = print;
	}
}
