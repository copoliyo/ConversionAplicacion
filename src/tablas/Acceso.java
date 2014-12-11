// Tabla Acceso

package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;


public class Acceso {
	private String empresa;
	private String menu;
	private String titulo;
	private String tituloAbreviado;
	private int tipoOpcion[];
	private String programa[];
	private int visible[];
	private String descripcion[];

	static final int MAX_OPCIONES = 38;

	public Acceso(){
		empresa = DatosComunes.eEmpresa;
		menu = "";
		titulo = "";
		tituloAbreviado = "";
		
		tipoOpcion = new int[MAX_OPCIONES];
		programa = new String[MAX_OPCIONES];
		visible = new int[MAX_OPCIONES];
		descripcion = new String[MAX_OPCIONES];
		
		for(int i = 0; i < MAX_OPCIONES; i++){
			tipoOpcion[i] = 0;
			programa[i] = "";
			visible[i] = 0;
			descripcion[i] = "";
		}
	}
	public Acceso(ResultSet rs) {

		try {
			if (rs.next() == true) {				
				empresa = rs.getString("EMPRESA").trim();
				menu = rs.getString("ACCESO_MENU").trim();
				titulo = rs.getString("ACCESO_TITULO").trim();
				tituloAbreviado = rs.getString("ACCESO_ABREV_TIT").trim();

				tipoOpcion = new int[MAX_OPCIONES];
				programa = new String[MAX_OPCIONES];
				visible = new int[MAX_OPCIONES];
				descripcion = new String[MAX_OPCIONES];
				
				tipoOpcion[0] = rs.getInt("ACCESO_PRGMB_IZQU_1");
				tipoOpcion[1] = rs.getInt("ACCESO_PRGMB_IZQU_2");
				tipoOpcion[2] = rs.getInt("ACCESO_PRGMB_IZQU_3");
				tipoOpcion[3] = rs.getInt("ACCESO_PRGMB_IZQU_4");
				tipoOpcion[4] = rs.getInt("ACCESO_PRGMB_IZQU_5");
				tipoOpcion[5] = rs.getInt("ACCESO_PRGMB_IZQU_6");
				tipoOpcion[6] = rs.getInt("ACCESO_PRGMB_IZQU_7");
				tipoOpcion[7] = rs.getInt("ACCESO_PRGMB_IZQU_8");
				tipoOpcion[8] = rs.getInt("ACCESO_PRGMB_IZQU_9");
				tipoOpcion[9] = rs.getInt("ACCESO_PRGMB_IZQU_10");
				tipoOpcion[10] = rs.getInt("ACCESO_PRGMB_IZQU_11");
				tipoOpcion[11] = rs.getInt("ACCESO_PRGMB_IZQU_12");
				tipoOpcion[12] = rs.getInt("ACCESO_PRGMB_IZQU_13");
				tipoOpcion[13] = rs.getInt("ACCESO_PRGMB_IZQU_14");
				tipoOpcion[14] = rs.getInt("ACCESO_PRGMB_IZQU_15");
				tipoOpcion[15] = rs.getInt("ACCESO_PRGMB_IZQU_16");
				tipoOpcion[16] = rs.getInt("ACCESO_PRGMB_IZQU_17");
				tipoOpcion[17] = rs.getInt("ACCESO_PRGMB_IZQU_18");
				tipoOpcion[18] = rs.getInt("ACCESO_PRGMB_IZQU_19");

				tipoOpcion[19] = rs.getInt("ACCESO_PRGMB_DRCH_1");
				tipoOpcion[20] = rs.getInt("ACCESO_PRGMB_DRCH_2");
				tipoOpcion[21] = rs.getInt("ACCESO_PRGMB_DRCH_3");
				tipoOpcion[22] = rs.getInt("ACCESO_PRGMB_DRCH_4");
				tipoOpcion[23] = rs.getInt("ACCESO_PRGMB_DRCH_5");
				tipoOpcion[24] = rs.getInt("ACCESO_PRGMB_DRCH_6");
				tipoOpcion[25] = rs.getInt("ACCESO_PRGMB_DRCH_7");
				tipoOpcion[26] = rs.getInt("ACCESO_PRGMB_DRCH_8");
				tipoOpcion[27] = rs.getInt("ACCESO_PRGMB_DRCH_9");
				tipoOpcion[28] = rs.getInt("ACCESO_PRGMB_DRCH_10");
				tipoOpcion[29] = rs.getInt("ACCESO_PRGMB_DRCH_11");
				tipoOpcion[30] = rs.getInt("ACCESO_PRGMB_DRCH_12");
				tipoOpcion[31] = rs.getInt("ACCESO_PRGMB_DRCH_13");
				tipoOpcion[32] = rs.getInt("ACCESO_PRGMB_DRCH_14");
				tipoOpcion[33] = rs.getInt("ACCESO_PRGMB_DRCH_15");
				tipoOpcion[34] = rs.getInt("ACCESO_PRGMB_DRCH_16");
				tipoOpcion[35] = rs.getInt("ACCESO_PRGMB_DRCH_17");
				tipoOpcion[36] = rs.getInt("ACCESO_PRGMB_DRCH_18");
				tipoOpcion[37] = rs.getInt("ACCESO_PRGMB_DRCH_19");

				programa[0] = rs.getString("ACCESO_LLAMA_IZQU_1").trim();
				programa[1] = rs.getString("ACCESO_LLAMA_IZQU_2").trim();
				programa[2] = rs.getString("ACCESO_LLAMA_IZQU_3").trim();
				programa[3] = rs.getString("ACCESO_LLAMA_IZQU_4").trim();
				programa[4] = rs.getString("ACCESO_LLAMA_IZQU_5").trim();
				programa[5] = rs.getString("ACCESO_LLAMA_IZQU_6").trim();
				programa[6] = rs.getString("ACCESO_LLAMA_IZQU_7").trim();
				programa[7] = rs.getString("ACCESO_LLAMA_IZQU_8").trim();
				programa[8] = rs.getString("ACCESO_LLAMA_IZQU_9").trim();
				programa[9] = rs.getString("ACCESO_LLAMA_IZQU_10").trim();
				programa[10] = rs.getString("ACCESO_LLAMA_IZQU_11").trim();
				programa[11] = rs.getString("ACCESO_LLAMA_IZQU_12").trim();
				programa[12] = rs.getString("ACCESO_LLAMA_IZQU_13").trim();
				programa[13] = rs.getString("ACCESO_LLAMA_IZQU_14").trim();
				programa[14] = rs.getString("ACCESO_LLAMA_IZQU_15").trim();
				programa[15] = rs.getString("ACCESO_LLAMA_IZQU_16").trim();
				programa[16] = rs.getString("ACCESO_LLAMA_IZQU_17").trim();
				programa[17] = rs.getString("ACCESO_LLAMA_IZQU_18").trim();
				programa[18] = rs.getString("ACCESO_LLAMA_IZQU_19").trim();

				programa[19] = rs.getString("ACCESO_LLAMA_DRCH_1").trim();
				programa[20] = rs.getString("ACCESO_LLAMA_DRCH_2").trim();
				programa[21] = rs.getString("ACCESO_LLAMA_DRCH_3").trim();
				programa[22] = rs.getString("ACCESO_LLAMA_DRCH_4").trim();
				programa[23] = rs.getString("ACCESO_LLAMA_DRCH_5").trim();
				programa[24] = rs.getString("ACCESO_LLAMA_DRCH_6").trim();
				programa[25] = rs.getString("ACCESO_LLAMA_DRCH_7").trim();
				programa[26] = rs.getString("ACCESO_LLAMA_DRCH_8").trim();
				programa[27] = rs.getString("ACCESO_LLAMA_DRCH_9").trim();
				programa[28] = rs.getString("ACCESO_LLAMA_DRCH_10").trim();
				programa[29] = rs.getString("ACCESO_LLAMA_DRCH_11").trim();
				programa[30] = rs.getString("ACCESO_LLAMA_DRCH_12").trim();
				programa[31] = rs.getString("ACCESO_LLAMA_DRCH_13").trim();
				programa[32] = rs.getString("ACCESO_LLAMA_DRCH_14").trim();
				programa[33] = rs.getString("ACCESO_LLAMA_DRCH_15").trim();
				programa[34] = rs.getString("ACCESO_LLAMA_DRCH_16").trim();
				programa[35] = rs.getString("ACCESO_LLAMA_DRCH_17").trim();
				programa[36] = rs.getString("ACCESO_LLAMA_DRCH_18").trim();
				programa[37] = rs.getString("ACCESO_LLAMA_DRCH_19").trim();

				visible[0] = rs.getInt("ACCESO_FDISP_IZQU_1");
				visible[1] = rs.getInt("ACCESO_FDISP_IZQU_2");
				visible[2] = rs.getInt("ACCESO_FDISP_IZQU_3");
				visible[3] = rs.getInt("ACCESO_FDISP_IZQU_4");
				visible[4] = rs.getInt("ACCESO_FDISP_IZQU_5");
				visible[5] = rs.getInt("ACCESO_FDISP_IZQU_6");
				visible[6] = rs.getInt("ACCESO_FDISP_IZQU_7");
				visible[7] = rs.getInt("ACCESO_FDISP_IZQU_8");
				visible[8] = rs.getInt("ACCESO_FDISP_IZQU_9");
				visible[9] = rs.getInt("ACCESO_FDISP_IZQU_10");
				visible[10] = rs.getInt("ACCESO_FDISP_IZQU_11");
				visible[11] = rs.getInt("ACCESO_FDISP_IZQU_12");
				visible[12] = rs.getInt("ACCESO_FDISP_IZQU_13");
				visible[13] = rs.getInt("ACCESO_FDISP_IZQU_14");
				visible[14] = rs.getInt("ACCESO_FDISP_IZQU_15");
				visible[15] = rs.getInt("ACCESO_FDISP_IZQU_16");
				visible[16] = rs.getInt("ACCESO_FDISP_IZQU_17");
				visible[17] = rs.getInt("ACCESO_FDISP_IZQU_18");
				visible[18] = rs.getInt("ACCESO_FDISP_IZQU_19");

				visible[19] = rs.getInt("ACCESO_FDISP_DRCH_1");
				visible[20] = rs.getInt("ACCESO_FDISP_DRCH_2");
				visible[21] = rs.getInt("ACCESO_FDISP_DRCH_3");
				visible[22] = rs.getInt("ACCESO_FDISP_DRCH_4");
				visible[23] = rs.getInt("ACCESO_FDISP_DRCH_5");
				visible[24] = rs.getInt("ACCESO_FDISP_DRCH_6");
				visible[25] = rs.getInt("ACCESO_FDISP_DRCH_7");
				visible[26] = rs.getInt("ACCESO_FDISP_DRCH_8");
				visible[27] = rs.getInt("ACCESO_FDISP_DRCH_9");
				visible[28] = rs.getInt("ACCESO_FDISP_DRCH_10");
				visible[29] = rs.getInt("ACCESO_FDISP_DRCH_11");
				visible[30] = rs.getInt("ACCESO_FDISP_DRCH_12");
				visible[31] = rs.getInt("ACCESO_FDISP_DRCH_13");
				visible[32] = rs.getInt("ACCESO_FDISP_DRCH_14");
				visible[33] = rs.getInt("ACCESO_FDISP_DRCH_15");
				visible[34] = rs.getInt("ACCESO_FDISP_DRCH_16");
				visible[35] = rs.getInt("ACCESO_FDISP_DRCH_17");
				visible[36] = rs.getInt("ACCESO_FDISP_DRCH_18");
				visible[37] = rs.getInt("ACCESO_FDISP_DRCH_19");
				
				descripcion[0] = rs.getString("ACCESO_DESCR_IZQU_1").trim();
				descripcion[1] = rs.getString("ACCESO_DESCR_IZQU_2").trim();
				descripcion[2] = rs.getString("ACCESO_DESCR_IZQU_3").trim();
				descripcion[3] = rs.getString("ACCESO_DESCR_IZQU_4").trim();
				descripcion[4] = rs.getString("ACCESO_DESCR_IZQU_5").trim();
				descripcion[5] = rs.getString("ACCESO_DESCR_IZQU_6").trim();
				descripcion[6] = rs.getString("ACCESO_DESCR_IZQU_7").trim();
				descripcion[7] = rs.getString("ACCESO_DESCR_IZQU_8").trim();
				descripcion[8] = rs.getString("ACCESO_DESCR_IZQU_9").trim();
				descripcion[9] = rs.getString("ACCESO_DESCR_IZQU_10").trim();
				descripcion[10] = rs.getString("ACCESO_DESCR_IZQU_11").trim();
				descripcion[11] = rs.getString("ACCESO_DESCR_IZQU_12").trim();
				descripcion[12] = rs.getString("ACCESO_DESCR_IZQU_13").trim();
				descripcion[13] = rs.getString("ACCESO_DESCR_IZQU_14").trim();
				descripcion[14] = rs.getString("ACCESO_DESCR_IZQU_15").trim();
				descripcion[15] = rs.getString("ACCESO_DESCR_IZQU_16").trim();
				descripcion[16] = rs.getString("ACCESO_DESCR_IZQU_17").trim();
				descripcion[17] = rs.getString("ACCESO_DESCR_IZQU_18").trim();
				descripcion[18] = rs.getString("ACCESO_DESCR_IZQU_19").trim();

				descripcion[19] = rs.getString("ACCESO_DESCR_DRCH_1").trim();
				descripcion[20] = rs.getString("ACCESO_DESCR_DRCH_2").trim();
				descripcion[21] = rs.getString("ACCESO_DESCR_DRCH_3").trim();
				descripcion[22] = rs.getString("ACCESO_DESCR_DRCH_4").trim();
				descripcion[23] = rs.getString("ACCESO_DESCR_DRCH_5").trim();
				descripcion[24] = rs.getString("ACCESO_DESCR_DRCH_6").trim();
				descripcion[25] = rs.getString("ACCESO_DESCR_DRCH_7").trim();
				descripcion[26] = rs.getString("ACCESO_DESCR_DRCH_8").trim();
				descripcion[27] = rs.getString("ACCESO_DESCR_DRCH_9").trim();
				descripcion[28] = rs.getString("ACCESO_DESCR_DRCH_10").trim();
				descripcion[29] = rs.getString("ACCESO_DESCR_DRCH_11").trim();
				descripcion[30] = rs.getString("ACCESO_DESCR_DRCH_12").trim();
				descripcion[31] = rs.getString("ACCESO_DESCR_DRCH_13").trim();
				descripcion[32] = rs.getString("ACCESO_DESCR_DRCH_14").trim();
				descripcion[33] = rs.getString("ACCESO_DESCR_DRCH_15").trim();
				descripcion[34] = rs.getString("ACCESO_DESCR_DRCH_16").trim();
				descripcion[35] = rs.getString("ACCESO_DESCR_DRCH_17").trim();
				descripcion[36] = rs.getString("ACCESO_DESCR_DRCH_18").trim();
				descripcion[37] = rs.getString("ACCESO_DESCR_DRCH_19").trim();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error en lectura fichero de Acceso!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}

	}

	public void read(ResultSet rs) {

		try {
			empresa = rs.getString("EMPRESA").trim();
			menu = rs.getString("ACCESO_MENU").trim();
			titulo = rs.getString("ACCESO_TITULO").trim();
			tituloAbreviado = rs.getString("ACCESO_ABREV_TIT").trim();

			tipoOpcion[0] = rs.getInt("ACCESO_PRGMB_IZQU_1");
			tipoOpcion[1] = rs.getInt("ACCESO_PRGMB_IZQU_2");
			tipoOpcion[2] = rs.getInt("ACCESO_PRGMB_IZQU_3");
			tipoOpcion[3] = rs.getInt("ACCESO_PRGMB_IZQU_4");
			tipoOpcion[4] = rs.getInt("ACCESO_PRGMB_IZQU_5");
			tipoOpcion[5] = rs.getInt("ACCESO_PRGMB_IZQU_6");
			tipoOpcion[6] = rs.getInt("ACCESO_PRGMB_IZQU_7");
			tipoOpcion[7] = rs.getInt("ACCESO_PRGMB_IZQU_8");
			tipoOpcion[8] = rs.getInt("ACCESO_PRGMB_IZQU_9");
			tipoOpcion[9] = rs.getInt("ACCESO_PRGMB_IZQU_10");
			tipoOpcion[10] = rs.getInt("ACCESO_PRGMB_IZQU_11");
			tipoOpcion[11] = rs.getInt("ACCESO_PRGMB_IZQU_12");
			tipoOpcion[12] = rs.getInt("ACCESO_PRGMB_IZQU_13");
			tipoOpcion[13] = rs.getInt("ACCESO_PRGMB_IZQU_14");
			tipoOpcion[14] = rs.getInt("ACCESO_PRGMB_IZQU_15");
			tipoOpcion[15] = rs.getInt("ACCESO_PRGMB_IZQU_16");
			tipoOpcion[16] = rs.getInt("ACCESO_PRGMB_IZQU_17");
			tipoOpcion[17] = rs.getInt("ACCESO_PRGMB_IZQU_18");
			tipoOpcion[18] = rs.getInt("ACCESO_PRGMB_IZQU_19");

			tipoOpcion[19] = rs.getInt("ACCESO_PRGMB_DRCH_1");
			tipoOpcion[20] = rs.getInt("ACCESO_PRGMB_DRCH_2");
			tipoOpcion[21] = rs.getInt("ACCESO_PRGMB_DRCH_3");
			tipoOpcion[22] = rs.getInt("ACCESO_PRGMB_DRCH_4");
			tipoOpcion[23] = rs.getInt("ACCESO_PRGMB_DRCH_5");
			tipoOpcion[24] = rs.getInt("ACCESO_PRGMB_DRCH_6");
			tipoOpcion[25] = rs.getInt("ACCESO_PRGMB_DRCH_7");
			tipoOpcion[26] = rs.getInt("ACCESO_PRGMB_DRCH_8");
			tipoOpcion[27] = rs.getInt("ACCESO_PRGMB_DRCH_9");
			tipoOpcion[28] = rs.getInt("ACCESO_PRGMB_DRCH_10");
			tipoOpcion[29] = rs.getInt("ACCESO_PRGMB_DRCH_11");
			tipoOpcion[30] = rs.getInt("ACCESO_PRGMB_DRCH_12");
			tipoOpcion[31] = rs.getInt("ACCESO_PRGMB_DRCH_13");
			tipoOpcion[32] = rs.getInt("ACCESO_PRGMB_DRCH_14");
			tipoOpcion[33] = rs.getInt("ACCESO_PRGMB_DRCH_15");
			tipoOpcion[34] = rs.getInt("ACCESO_PRGMB_DRCH_16");
			tipoOpcion[35] = rs.getInt("ACCESO_PRGMB_DRCH_17");
			tipoOpcion[36] = rs.getInt("ACCESO_PRGMB_DRCH_18");
			tipoOpcion[37] = rs.getInt("ACCESO_PRGMB_DRCH_19");

			programa[0] = rs.getString("ACCESO_LLAMA_IZQU_1").trim();
			programa[1] = rs.getString("ACCESO_LLAMA_IZQU_2").trim();
			programa[2] = rs.getString("ACCESO_LLAMA_IZQU_3").trim();
			programa[3] = rs.getString("ACCESO_LLAMA_IZQU_4").trim();
			programa[4] = rs.getString("ACCESO_LLAMA_IZQU_5").trim();
			programa[5] = rs.getString("ACCESO_LLAMA_IZQU_6").trim();
			programa[6] = rs.getString("ACCESO_LLAMA_IZQU_7").trim();
			programa[7] = rs.getString("ACCESO_LLAMA_IZQU_8").trim();
			programa[8] = rs.getString("ACCESO_LLAMA_IZQU_9").trim();
			programa[9] = rs.getString("ACCESO_LLAMA_IZQU_10").trim();
			programa[10] = rs.getString("ACCESO_LLAMA_IZQU_11").trim();
			programa[11] = rs.getString("ACCESO_LLAMA_IZQU_12").trim();
			programa[12] = rs.getString("ACCESO_LLAMA_IZQU_13").trim();
			programa[13] = rs.getString("ACCESO_LLAMA_IZQU_14").trim();
			programa[14] = rs.getString("ACCESO_LLAMA_IZQU_15").trim();
			programa[15] = rs.getString("ACCESO_LLAMA_IZQU_16").trim();
			programa[16] = rs.getString("ACCESO_LLAMA_IZQU_17").trim();
			programa[17] = rs.getString("ACCESO_LLAMA_IZQU_18").trim();
			programa[18] = rs.getString("ACCESO_LLAMA_IZQU_19").trim();

			programa[19] = rs.getString("ACCESO_LLAMA_DRCH_1").trim();
			programa[20] = rs.getString("ACCESO_LLAMA_DRCH_2").trim();
			programa[21] = rs.getString("ACCESO_LLAMA_DRCH_3").trim();
			programa[22] = rs.getString("ACCESO_LLAMA_DRCH_4").trim();
			programa[23] = rs.getString("ACCESO_LLAMA_DRCH_5").trim();
			programa[24] = rs.getString("ACCESO_LLAMA_DRCH_6").trim();
			programa[25] = rs.getString("ACCESO_LLAMA_DRCH_7").trim();
			programa[26] = rs.getString("ACCESO_LLAMA_DRCH_8").trim();
			programa[27] = rs.getString("ACCESO_LLAMA_DRCH_9").trim();
			programa[28] = rs.getString("ACCESO_LLAMA_DRCH_10").trim();
			programa[29] = rs.getString("ACCESO_LLAMA_DRCH_11").trim();
			programa[30] = rs.getString("ACCESO_LLAMA_DRCH_12").trim();
			programa[31] = rs.getString("ACCESO_LLAMA_DRCH_13").trim();
			programa[32] = rs.getString("ACCESO_LLAMA_DRCH_14").trim();
			programa[33] = rs.getString("ACCESO_LLAMA_DRCH_15").trim();
			programa[34] = rs.getString("ACCESO_LLAMA_DRCH_16").trim();
			programa[35] = rs.getString("ACCESO_LLAMA_DRCH_17").trim();
			programa[36] = rs.getString("ACCESO_LLAMA_DRCH_18").trim();
			programa[37] = rs.getString("ACCESO_LLAMA_DRCH_19").trim();

			visible[0] = rs.getInt("ACCESO_FDISP_IZQU_1");
			visible[1] = rs.getInt("ACCESO_FDISP_IZQU_2");
			visible[2] = rs.getInt("ACCESO_FDISP_IZQU_3");
			visible[3] = rs.getInt("ACCESO_FDISP_IZQU_4");
			visible[4] = rs.getInt("ACCESO_FDISP_IZQU_5");
			visible[5] = rs.getInt("ACCESO_FDISP_IZQU_6");
			visible[6] = rs.getInt("ACCESO_FDISP_IZQU_7");
			visible[7] = rs.getInt("ACCESO_FDISP_IZQU_8");
			visible[8] = rs.getInt("ACCESO_FDISP_IZQU_9");
			visible[9] = rs.getInt("ACCESO_FDISP_IZQU_10");
			visible[10] = rs.getInt("ACCESO_FDISP_IZQU_11");
			visible[11] = rs.getInt("ACCESO_FDISP_IZQU_12");
			visible[12] = rs.getInt("ACCESO_FDISP_IZQU_13");
			visible[13] = rs.getInt("ACCESO_FDISP_IZQU_14");
			visible[14] = rs.getInt("ACCESO_FDISP_IZQU_15");
			visible[15] = rs.getInt("ACCESO_FDISP_IZQU_16");
			visible[16] = rs.getInt("ACCESO_FDISP_IZQU_17");
			visible[17] = rs.getInt("ACCESO_FDISP_IZQU_18");
			visible[18] = rs.getInt("ACCESO_FDISP_IZQU_19");

			visible[0] = rs.getInt("ACCESO_FDISP_DRCH_1");
			visible[1] = rs.getInt("ACCESO_FDISP_DRCH_2");
			visible[2] = rs.getInt("ACCESO_FDISP_DRCH_3");
			visible[3] = rs.getInt("ACCESO_FDISP_DRCH_4");
			visible[4] = rs.getInt("ACCESO_FDISP_DRCH_5");
			visible[5] = rs.getInt("ACCESO_FDISP_DRCH_6");
			visible[6] = rs.getInt("ACCESO_FDISP_DRCH_7");
			visible[7] = rs.getInt("ACCESO_FDISP_DRCH_8");
			visible[8] = rs.getInt("ACCESO_FDISP_DRCH_9");
			visible[9] = rs.getInt("ACCESO_FDISP_DRCH_10");
			visible[10] = rs.getInt("ACCESO_FDISP_DRCH_11");
			visible[11] = rs.getInt("ACCESO_FDISP_DRCH_12");
			visible[12] = rs.getInt("ACCESO_FDISP_DRCH_13");
			visible[13] = rs.getInt("ACCESO_FDISP_DRCH_14");
			visible[14] = rs.getInt("ACCESO_FDISP_DRCH_15");
			visible[15] = rs.getInt("ACCESO_FDISP_DRCH_16");
			visible[16] = rs.getInt("ACCESO_FDISP_DRCH_17");
			visible[17] = rs.getInt("ACCESO_FDISP_DRCH_18");
			visible[18] = rs.getInt("ACCESO_FDISP_DRCH_19");

			descripcion[0] = rs.getString("ACCESO_DESCR_IZQU_1").trim();
			descripcion[1] = rs.getString("ACCESO_DESCR_IZQU_2").trim();
			descripcion[2] = rs.getString("ACCESO_DESCR_IZQU_3").trim();
			descripcion[3] = rs.getString("ACCESO_DESCR_IZQU_4").trim();
			descripcion[4] = rs.getString("ACCESO_DESCR_IZQU_5").trim();
			descripcion[5] = rs.getString("ACCESO_DESCR_IZQU_6").trim();
			descripcion[6] = rs.getString("ACCESO_DESCR_IZQU_7").trim();
			descripcion[7] = rs.getString("ACCESO_DESCR_IZQU_8").trim();
			descripcion[8] = rs.getString("ACCESO_DESCR_IZQU_9").trim();
			descripcion[9] = rs.getString("ACCESO_DESCR_IZQU_10").trim();
			descripcion[10] = rs.getString("ACCESO_DESCR_IZQU_11").trim();
			descripcion[11] = rs.getString("ACCESO_DESCR_IZQU_12").trim();
			descripcion[12] = rs.getString("ACCESO_DESCR_IZQU_13").trim();
			descripcion[13] = rs.getString("ACCESO_DESCR_IZQU_14").trim();
			descripcion[14] = rs.getString("ACCESO_DESCR_IZQU_15").trim();
			descripcion[15] = rs.getString("ACCESO_DESCR_IZQU_16").trim();
			descripcion[16] = rs.getString("ACCESO_DESCR_IZQU_17").trim();
			descripcion[17] = rs.getString("ACCESO_DESCR_IZQU_18").trim();
			descripcion[18] = rs.getString("ACCESO_DESCR_IZQU_19").trim();

			descripcion[19] = rs.getString("ACCESO_DESCR_DRCH_1").trim();
			descripcion[20] = rs.getString("ACCESO_DESCR_DRCH_2").trim();
			descripcion[21] = rs.getString("ACCESO_DESCR_DRCH_3").trim();
			descripcion[22] = rs.getString("ACCESO_DESCR_DRCH_4").trim();
			descripcion[23] = rs.getString("ACCESO_DESCR_DRCH_5").trim();
			descripcion[24] = rs.getString("ACCESO_DESCR_DRCH_6").trim();
			descripcion[25] = rs.getString("ACCESO_DESCR_DRCH_7").trim();
			descripcion[26] = rs.getString("ACCESO_DESCR_DRCH_8").trim();
			descripcion[27] = rs.getString("ACCESO_DESCR_DRCH_9").trim();
			descripcion[28] = rs.getString("ACCESO_DESCR_DRCH_10").trim();
			descripcion[29] = rs.getString("ACCESO_DESCR_DRCH_11").trim();
			descripcion[30] = rs.getString("ACCESO_DESCR_DRCH_12").trim();
			descripcion[31] = rs.getString("ACCESO_DESCR_DRCH_13").trim();
			descripcion[32] = rs.getString("ACCESO_DESCR_DRCH_14").trim();
			descripcion[33] = rs.getString("ACCESO_DESCR_DRCH_15").trim();
			descripcion[34] = rs.getString("ACCESO_DESCR_DRCH_16").trim();
			descripcion[35] = rs.getString("ACCESO_DESCR_DRCH_17").trim();
			descripcion[36] = rs.getString("ACCESO_DESCR_DRCH_18").trim();
			descripcion[37] = rs.getString("ACCESO_DESCR_DRCH_19").trim();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error en lectura fichero de Acceso!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}

	}
	
	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO ACCESO (" +
						   "EMPRESA, " +
						   "ACCESO_MENU, " +
						   "ACCESO_TITULO, " +
						   "ACCESO_ABREV_TIT, " +
						   "ACCESO_PRGMB_IZQU_1, " +
						   "ACCESO_PRGMB_IZQU_2, " +
						   "ACCESO_PRGMB_IZQU_3, " +
						   "ACCESO_PRGMB_IZQU_4, " +
						   "ACCESO_PRGMB_IZQU_5, " +
						   "ACCESO_PRGMB_IZQU_6, " +
						   "ACCESO_PRGMB_IZQU_7, " +
						   "ACCESO_PRGMB_IZQU_8, " +
						   "ACCESO_PRGMB_IZQU_9, " +
						   "ACCESO_PRGMB_IZQU_10, " +
						   "ACCESO_PRGMB_IZQU_11, " +
						   "ACCESO_PRGMB_IZQU_12, " +
						   "ACCESO_PRGMB_IZQU_13, " +
						   "ACCESO_PRGMB_IZQU_14, " +
						   "ACCESO_PRGMB_IZQU_15, " +
						   "ACCESO_PRGMB_IZQU_16, " +
						   "ACCESO_PRGMB_IZQU_17, " +
						   "ACCESO_PRGMB_IZQU_18, " +
						   "ACCESO_PRGMB_IZQU_19, " +
						   "ACCESO_LLAMA_IZQU_1, " +
						   "ACCESO_LLAMA_IZQU_2, " +
						   "ACCESO_LLAMA_IZQU_3, " +
						   "ACCESO_LLAMA_IZQU_4, " +
						   "ACCESO_LLAMA_IZQU_5, " +
						   "ACCESO_LLAMA_IZQU_6, " +
						   "ACCESO_LLAMA_IZQU_7, " +
						   "ACCESO_LLAMA_IZQU_8, " +
						   "ACCESO_LLAMA_IZQU_9, " +
						   "ACCESO_LLAMA_IZQU_10, " +
						   "ACCESO_LLAMA_IZQU_11, " +
						   "ACCESO_LLAMA_IZQU_12, " +
						   "ACCESO_LLAMA_IZQU_13, " +
						   "ACCESO_LLAMA_IZQU_14, " +
						   "ACCESO_LLAMA_IZQU_15, " +
						   "ACCESO_LLAMA_IZQU_16, " +
						   "ACCESO_LLAMA_IZQU_17, " +
						   "ACCESO_LLAMA_IZQU_18, " +
						   "ACCESO_LLAMA_IZQU_19, " +
						   "ACCESO_FDISP_IZQU_1, " +
						   "ACCESO_FDISP_IZQU_2, " +
						   "ACCESO_FDISP_IZQU_3, " +
						   "ACCESO_FDISP_IZQU_4, " +
						   "ACCESO_FDISP_IZQU_5, " +
						   "ACCESO_FDISP_IZQU_6, " +
						   "ACCESO_FDISP_IZQU_7, " +
						   "ACCESO_FDISP_IZQU_8, " +
						   "ACCESO_FDISP_IZQU_9, " +
						   "ACCESO_FDISP_IZQU_10, " +
						   "ACCESO_FDISP_IZQU_11, " +
						   "ACCESO_FDISP_IZQU_12, " +
						   "ACCESO_FDISP_IZQU_13, " +
						   "ACCESO_FDISP_IZQU_14, " +
						   "ACCESO_FDISP_IZQU_15, " +
						   "ACCESO_FDISP_IZQU_16, " +
						   "ACCESO_FDISP_IZQU_17, " +
						   "ACCESO_FDISP_IZQU_18, " +
						   "ACCESO_FDISP_IZQU_19, " +
						   "ACCESO_DESCR_IZQU_1, " +
						   "ACCESO_DESCR_IZQU_2, " +
						   "ACCESO_DESCR_IZQU_3, " +
						   "ACCESO_DESCR_IZQU_4, " +
						   "ACCESO_DESCR_IZQU_5, " +
						   "ACCESO_DESCR_IZQU_6, " +
						   "ACCESO_DESCR_IZQU_7, " +
						   "ACCESO_DESCR_IZQU_8, " +
						   "ACCESO_DESCR_IZQU_9, " +
						   "ACCESO_DESCR_IZQU_10, " +
						   "ACCESO_DESCR_IZQU_11, " +
						   "ACCESO_DESCR_IZQU_12, " +
						   "ACCESO_DESCR_IZQU_13, " +
						   "ACCESO_DESCR_IZQU_14, " +
						   "ACCESO_DESCR_IZQU_15, " +
						   "ACCESO_DESCR_IZQU_16, " +
						   "ACCESO_DESCR_IZQU_17, " +
						   "ACCESO_DESCR_IZQU_18, " +
						   "ACCESO_DESCR_IZQU_19, " +
						   "ACCESO_PRGMB_DRCH_1, " +
						   "ACCESO_PRGMB_DRCH_2, " +
						   "ACCESO_PRGMB_DRCH_3, " +
						   "ACCESO_PRGMB_DRCH_4, " +
						   "ACCESO_PRGMB_DRCH_5, " +
						   "ACCESO_PRGMB_DRCH_6, " +
						   "ACCESO_PRGMB_DRCH_7, " +
						   "ACCESO_PRGMB_DRCH_8, " +
						   "ACCESO_PRGMB_DRCH_9, " +
						   "ACCESO_PRGMB_DRCH_10, " +
						   "ACCESO_PRGMB_DRCH_11, " +
						   "ACCESO_PRGMB_DRCH_12, " +
						   "ACCESO_PRGMB_DRCH_13, " +
						   "ACCESO_PRGMB_DRCH_14, " +
						   "ACCESO_PRGMB_DRCH_15, " +
						   "ACCESO_PRGMB_DRCH_16, " +
						   "ACCESO_PRGMB_DRCH_17, " +
						   "ACCESO_PRGMB_DRCH_18, " +
						   "ACCESO_PRGMB_DRCH_19, " +
						   "ACCESO_LLAMA_DRCH_1, " +
						   "ACCESO_LLAMA_DRCH_2, " +
						   "ACCESO_LLAMA_DRCH_3, " +
						   "ACCESO_LLAMA_DRCH_4, " +
						   "ACCESO_LLAMA_DRCH_5, " +
						   "ACCESO_LLAMA_DRCH_6, " +
						   "ACCESO_LLAMA_DRCH_7, " +
						   "ACCESO_LLAMA_DRCH_8, " +
						   "ACCESO_LLAMA_DRCH_9, " +
						   "ACCESO_LLAMA_DRCH_10, " +
						   "ACCESO_LLAMA_DRCH_11, " +
						   "ACCESO_LLAMA_DRCH_12, " +
						   "ACCESO_LLAMA_DRCH_13, " +
						   "ACCESO_LLAMA_DRCH_14, " +
						   "ACCESO_LLAMA_DRCH_15, " +
						   "ACCESO_LLAMA_DRCH_16, " +
						   "ACCESO_LLAMA_DRCH_17, " +
						   "ACCESO_LLAMA_DRCH_18, " +
						   "ACCESO_LLAMA_DRCH_19, " +
						   "ACCESO_FDISP_DRCH_1, " +
						   "ACCESO_FDISP_DRCH_2, " +
						   "ACCESO_FDISP_DRCH_3, " +
						   "ACCESO_FDISP_DRCH_4, " +
						   "ACCESO_FDISP_DRCH_5, " +
						   "ACCESO_FDISP_DRCH_6, " +
						   "ACCESO_FDISP_DRCH_7, " +
						   "ACCESO_FDISP_DRCH_8, " +
						   "ACCESO_FDISP_DRCH_9, " +
						   "ACCESO_FDISP_DRCH_10, " +
						   "ACCESO_FDISP_DRCH_11, " +
						   "ACCESO_FDISP_DRCH_12, " +
						   "ACCESO_FDISP_DRCH_13, " +
						   "ACCESO_FDISP_DRCH_14, " +
						   "ACCESO_FDISP_DRCH_15, " +
						   "ACCESO_FDISP_DRCH_16, " +
						   "ACCESO_FDISP_DRCH_17, " +
						   "ACCESO_FDISP_DRCH_18, " +
						   "ACCESO_FDISP_DRCH_19, " +
						   "ACCESO_DESCR_DRCH_1, " +
						   "ACCESO_DESCR_DRCH_2, " +
						   "ACCESO_DESCR_DRCH_3, " +
						   "ACCESO_DESCR_DRCH_4, " +
						   "ACCESO_DESCR_DRCH_5, " +
						   "ACCESO_DESCR_DRCH_6, " +
						   "ACCESO_DESCR_DRCH_7, " +
						   "ACCESO_DESCR_DRCH_8, " +
						   "ACCESO_DESCR_DRCH_9, " +
						   "ACCESO_DESCR_DRCH_10, " +
						   "ACCESO_DESCR_DRCH_11, " +
						   "ACCESO_DESCR_DRCH_12, " +
						   "ACCESO_DESCR_DRCH_13, " +
						   "ACCESO_DESCR_DRCH_14, " +
						   "ACCESO_DESCR_DRCH_15, " +
						   "ACCESO_DESCR_DRCH_16, " +
						   "ACCESO_DESCR_DRCH_17, " +
						   "ACCESO_DESCR_DRCH_18, " +
						   "ACCESO_DESCR_DRCH_19) " +
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
				                  " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
				                  " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
				                  " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
				                  " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
				                  " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
				                  " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
				                  " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
				                  " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
				                  " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
				                  " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
				                  " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
				                  " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
				                  " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
				                  " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
				                  " ?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
						   "EMPRESA = ?, " +
						   "ACCESO_MENU = ?, " +
						   "ACCESO_TITULO = ?, " +
						   "ACCESO_ABREV_TIT = ?, " +
						   "ACCESO_PRGMB_IZQU_1 = ?, " +
						   "ACCESO_PRGMB_IZQU_2 = ?, " +
						   "ACCESO_PRGMB_IZQU_3 = ?, " +
						   "ACCESO_PRGMB_IZQU_4 = ?, " +
						   "ACCESO_PRGMB_IZQU_5 = ?, " +
						   "ACCESO_PRGMB_IZQU_6 = ?, " +
						   "ACCESO_PRGMB_IZQU_7 = ?, " +
						   "ACCESO_PRGMB_IZQU_8 = ?, " +
						   "ACCESO_PRGMB_IZQU_9 = ?, " +
						   "ACCESO_PRGMB_IZQU_10 = ?, " +
						   "ACCESO_PRGMB_IZQU_11 = ?, " +
						   "ACCESO_PRGMB_IZQU_12 = ?, " +
						   "ACCESO_PRGMB_IZQU_13 = ?, " +
						   "ACCESO_PRGMB_IZQU_14 = ?, " +
						   "ACCESO_PRGMB_IZQU_15 = ?, " +
						   "ACCESO_PRGMB_IZQU_16 = ?, " +
						   "ACCESO_PRGMB_IZQU_17 = ?, " +
						   "ACCESO_PRGMB_IZQU_18 = ?, " +
						   "ACCESO_PRGMB_IZQU_19 = ?, " +
						   "ACCESO_LLAMA_IZQU_1 = ?, " +
						   "ACCESO_LLAMA_IZQU_2 = ?, " +
						   "ACCESO_LLAMA_IZQU_3 = ?, " +
						   "ACCESO_LLAMA_IZQU_4 = ?, " +
						   "ACCESO_LLAMA_IZQU_5 = ?, " +
						   "ACCESO_LLAMA_IZQU_6 = ?, " +
						   "ACCESO_LLAMA_IZQU_7 = ?, " +
						   "ACCESO_LLAMA_IZQU_8 = ?, " +
						   "ACCESO_LLAMA_IZQU_9 = ?, " +
						   "ACCESO_LLAMA_IZQU_10 = ?, " +
						   "ACCESO_LLAMA_IZQU_11 = ?, " +
						   "ACCESO_LLAMA_IZQU_12 = ?, " +
						   "ACCESO_LLAMA_IZQU_13 = ?, " +
						   "ACCESO_LLAMA_IZQU_14 = ?, " +
						   "ACCESO_LLAMA_IZQU_15 = ?, " +
						   "ACCESO_LLAMA_IZQU_16 = ?, " +
						   "ACCESO_LLAMA_IZQU_17 = ?, " +
						   "ACCESO_LLAMA_IZQU_18 = ?, " +
						   "ACCESO_LLAMA_IZQU_19 = ?, " +
						   "ACCESO_FDISP_IZQU_1 = ?, " +
						   "ACCESO_FDISP_IZQU_2 = ?, " +
						   "ACCESO_FDISP_IZQU_3 = ?, " +
						   "ACCESO_FDISP_IZQU_4 = ?, " +
						   "ACCESO_FDISP_IZQU_5 = ?, " +
						   "ACCESO_FDISP_IZQU_6 = ?, " +
						   "ACCESO_FDISP_IZQU_7 = ?, " +
						   "ACCESO_FDISP_IZQU_8 = ?, " +
						   "ACCESO_FDISP_IZQU_9 = ?, " +
						   "ACCESO_FDISP_IZQU_10 = ?, " +
						   "ACCESO_FDISP_IZQU_11 = ?, " +
						   "ACCESO_FDISP_IZQU_12 = ?, " +
						   "ACCESO_FDISP_IZQU_13 = ?, " +
						   "ACCESO_FDISP_IZQU_14 = ?, " +
						   "ACCESO_FDISP_IZQU_15 = ?, " +
						   "ACCESO_FDISP_IZQU_16 = ?, " +
						   "ACCESO_FDISP_IZQU_17 = ?, " +
						   "ACCESO_FDISP_IZQU_18 = ?, " +
						   "ACCESO_FDISP_IZQU_19 = ?, " +
						   "ACCESO_DESCR_IZQU_1 = ?, " +
						   "ACCESO_DESCR_IZQU_2 = ?, " +
						   "ACCESO_DESCR_IZQU_3 = ?, " +
						   "ACCESO_DESCR_IZQU_4 = ?, " +
						   "ACCESO_DESCR_IZQU_5 = ?, " +
						   "ACCESO_DESCR_IZQU_6 = ?, " +
						   "ACCESO_DESCR_IZQU_7 = ?, " +
						   "ACCESO_DESCR_IZQU_8 = ?, " +
						   "ACCESO_DESCR_IZQU_9 = ?, " +
						   "ACCESO_DESCR_IZQU_10 = ?, " +
						   "ACCESO_DESCR_IZQU_11 = ?, " +
						   "ACCESO_DESCR_IZQU_12 = ?, " +
						   "ACCESO_DESCR_IZQU_13 = ?, " +
						   "ACCESO_DESCR_IZQU_14 = ?, " +
						   "ACCESO_DESCR_IZQU_15 = ?, " +
						   "ACCESO_DESCR_IZQU_16 = ?, " +
						   "ACCESO_DESCR_IZQU_17 = ?, " +
						   "ACCESO_DESCR_IZQU_18 = ?, " +
						   "ACCESO_DESCR_IZQU_19 = ?, " +
						   "ACCESO_PRGMB_DRCH_1 = ?, " +
						   "ACCESO_PRGMB_DRCH_2 = ?, " +
						   "ACCESO_PRGMB_DRCH_3 = ?, " +
						   "ACCESO_PRGMB_DRCH_4 = ?, " +
						   "ACCESO_PRGMB_DRCH_5 = ?, " +
						   "ACCESO_PRGMB_DRCH_6 = ?, " +
						   "ACCESO_PRGMB_DRCH_7 = ?, " +
						   "ACCESO_PRGMB_DRCH_8 = ?, " +
						   "ACCESO_PRGMB_DRCH_9 = ?, " +
						   "ACCESO_PRGMB_DRCH_10 = ?, " +
						   "ACCESO_PRGMB_DRCH_11 = ?, " +
						   "ACCESO_PRGMB_DRCH_12 = ?, " +
						   "ACCESO_PRGMB_DRCH_13 = ?, " +
						   "ACCESO_PRGMB_DRCH_14 = ?, " +
						   "ACCESO_PRGMB_DRCH_15 = ?, " +
						   "ACCESO_PRGMB_DRCH_16 = ?, " +
						   "ACCESO_PRGMB_DRCH_17 = ?, " +
						   "ACCESO_PRGMB_DRCH_18 = ?, " +
						   "ACCESO_PRGMB_DRCH_19 = ?, " +
						   "ACCESO_LLAMA_DRCH_1 = ?, " +
						   "ACCESO_LLAMA_DRCH_2 = ?, " +
						   "ACCESO_LLAMA_DRCH_3 = ?, " +
						   "ACCESO_LLAMA_DRCH_4 = ?, " +
						   "ACCESO_LLAMA_DRCH_5 = ?, " +
						   "ACCESO_LLAMA_DRCH_6 = ?, " +
						   "ACCESO_LLAMA_DRCH_7 = ?, " +
						   "ACCESO_LLAMA_DRCH_8 = ?, " +
						   "ACCESO_LLAMA_DRCH_9 = ?, " +
						   "ACCESO_LLAMA_DRCH_10 = ?, " +
						   "ACCESO_LLAMA_DRCH_11 = ?, " +
						   "ACCESO_LLAMA_DRCH_12 = ?, " +
						   "ACCESO_LLAMA_DRCH_13 = ?, " +
						   "ACCESO_LLAMA_DRCH_14 = ?, " +
						   "ACCESO_LLAMA_DRCH_15 = ?, " +
						   "ACCESO_LLAMA_DRCH_16 = ?, " +
						   "ACCESO_LLAMA_DRCH_17 = ?, " +
						   "ACCESO_LLAMA_DRCH_18 = ?, " +
						   "ACCESO_LLAMA_DRCH_19 = ?, " +
						   "ACCESO_FDISP_DRCH_1 = ?, " +
						   "ACCESO_FDISP_DRCH_2 = ?, " +
						   "ACCESO_FDISP_DRCH_3 = ?, " +
						   "ACCESO_FDISP_DRCH_4 = ?, " +
						   "ACCESO_FDISP_DRCH_5 = ?, " +
						   "ACCESO_FDISP_DRCH_6 = ?, " +
						   "ACCESO_FDISP_DRCH_7 = ?, " +
						   "ACCESO_FDISP_DRCH_8 = ?, " +
						   "ACCESO_FDISP_DRCH_9 = ?, " +
						   "ACCESO_FDISP_DRCH_10 = ?, " +
						   "ACCESO_FDISP_DRCH_11 = ?, " +
						   "ACCESO_FDISP_DRCH_12 = ?, " +
						   "ACCESO_FDISP_DRCH_13 = ?, " +
						   "ACCESO_FDISP_DRCH_14 = ?, " +
						   "ACCESO_FDISP_DRCH_15 = ?, " +
						   "ACCESO_FDISP_DRCH_16 = ?, " +
						   "ACCESO_FDISP_DRCH_17 = ?, " +
						   "ACCESO_FDISP_DRCH_18 = ?, " +
						   "ACCESO_FDISP_DRCH_19 = ?, " +
						   "ACCESO_DESCR_DRCH_1 = ?, " +
						   "ACCESO_DESCR_DRCH_2 = ?, " +
						   "ACCESO_DESCR_DRCH_3 = ?, " +
						   "ACCESO_DESCR_DRCH_4 = ?, " +
						   "ACCESO_DESCR_DRCH_5 = ?, " +
						   "ACCESO_DESCR_DRCH_6 = ?, " +
						   "ACCESO_DESCR_DRCH_7 = ?, " +
						   "ACCESO_DESCR_DRCH_8 = ?, " +
						   "ACCESO_DESCR_DRCH_9 = ?, " +
						   "ACCESO_DESCR_DRCH_10 = ?, " +
						   "ACCESO_DESCR_DRCH_11 = ?, " +
						   "ACCESO_DESCR_DRCH_12 = ?, " +
						   "ACCESO_DESCR_DRCH_13 = ?, " +
						   "ACCESO_DESCR_DRCH_14 = ?, " +
						   "ACCESO_DESCR_DRCH_15 = ?, " +
						   "ACCESO_DESCR_DRCH_16 = ?, " +
						   "ACCESO_DESCR_DRCH_17 = ?, " +
						   "ACCESO_DESCR_DRCH_18 = ?, " +
						   "ACCESO_DESCR_DRCH_19 = ?";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			// Insert
			ps.setString(1, Cadena.left(empresa, 2));
			ps.setString(2, Cadena.left(menu, 8));
			ps.setString(3, Cadena.left(titulo, 32));
			ps.setString(4, Cadena.left(tituloAbreviado, 12));
			// 5
			for(int i = 0; i < 19; i++)
				ps.setInt(5 + i, tipoOpcion[i]);
			// 24
			for(int i = 0; i < 19; i++)
				ps.setString(23 + i, Cadena.left(programa[i], 8));
			// 43
			for(int i = 0; i < 19; i++)
				ps.setInt(43 + i, visible[i]);
			// 62
			for(int i = 0; i < 19; i++)
				ps.setString(62 + i, Cadena.left(descripcion[i], 35));
			
			// 81
			for(int i = 0; i < 19; i++)
				ps.setInt(81 + i, tipoOpcion[19 + i]);
			// 100
			for(int i = 0; i < 19; i++)
				ps.setString(100 + i, Cadena.left(programa[19 + i], 8));
			// 119
			for(int i = 0; i < 19; i++)
				ps.setInt(119 + i, visible[19 + i]);
			// 138
			for(int i = 0; i < 19; i++)
				ps.setString(138 + i, Cadena.left(descripcion[19 + i], 35));
			
			// Update
			ps.setString(157, Cadena.left(empresa, 2));
			ps.setString(158, Cadena.left(menu, 8));
			ps.setString(159, Cadena.left(titulo, 32));
			ps.setString(160, Cadena.left(tituloAbreviado, 12));
			// 161
			for(int i = 0; i < 19; i++)
				ps.setInt(161 + i, tipoOpcion[i]);
			// 180
			for(int i = 0; i < 19; i++)
				ps.setString(180 + i, Cadena.left(programa[i], 8));
			// 199
			for(int i = 0; i < 19; i++)
				ps.setInt(43 + i, visible[i]);
			// 218
			for(int i = 0; i < 19; i++)
				ps.setString(218 + i, Cadena.left(descripcion[i], 35));
			
			// 237
			for(int i = 0; i < 19; i++)
				ps.setInt(237 + i, tipoOpcion[19 + i]);
			// 256
			for(int i = 0; i < 19; i++)
				ps.setString(256 + i, Cadena.left(programa[19 + i], 8));
			// 275
			for(int i = 0; i < 19; i++)
				ps.setInt(275 + i, visible[19 + i]);
			// 294
			for(int i = 0; i < 19; i++)
				ps.setString(294 + i, Cadena.left(descripcion[19 + i], 35));
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de Acceso!!!");
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

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getTipoOpcion(int i) {
		return tipoOpcion[i];
	}

	public int[] getTipoOpcion() {
		return tipoOpcion;
	}

	public void setTipoOpcion(int indice, int valor) {
		this.tipoOpcion[indice] = valor;
	}

	public void setTipoOpcion(int[] tipoOpcion) {
		this.tipoOpcion = tipoOpcion;
	}

	public String getPrograma(int i) {
		return programa[i];
	}

	public String[] getPrograma() {
		return programa;
	}

	public void setPrograma(int indice, String str) {
		this.programa[indice] = str;
	}

	public void setPrograma(String[] programa) {
		this.programa = programa;
	}

	public int getVisible(int i) {
		return visible[i];
	}

	public int[] getVisible() {
		return visible;
	}

	public void setVisible(int indice, int visibleSiNo) {
		this.visible[indice] = visibleSiNo;
	}

	public void setVisible(int[] visible) {
		this.visible = visible;
	}

	public String getDescripcion(int i) {
		return descripcion[i];
	}

	public String[] getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(int indice, String str) {
		this.descripcion[indice] = str;
	}

	public void setDescripcion(String[] descripcion) {
		this.descripcion = descripcion;
	}
	public String getTituloAbreviado() {
		return tituloAbreviado;
	}
	public void setTituloAbreviado(String tituloAbreviado) {
		this.tituloAbreviado = tituloAbreviado;
	}

}
