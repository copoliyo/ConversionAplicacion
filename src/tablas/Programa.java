package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;


public class Programa {
	private String empresa;
	private String programa;
	private int programaNumero;
	private String descripcion;
	private int activo;
	private String password;
	private int nivelAcceso;
	private String usando[];
	private int conMsjprg;
	
	public Programa(){
		empresa = DatosComunes.eEmpresa;
		programa = DatosComunes.programa;
		programaNumero = DatosComunes.programaN;
		descripcion = "";
		activo = 0;
		password = "";
		// Por si acaso, para que no tenga privilegios totales
		nivelAcceso = 9;
		usando = new String[32];
		for(int i = 0; i < 32; i++)
			usando[i] = "";
		conMsjprg = 0;
	}
	
	public Programa(ResultSet rs){
		try{
			if(rs.next() == true){		
				empresa = rs.getString("EMPRESA").trim();
				programa = rs.getString("ACCPRG_PROGRAMA").trim();
				programaNumero = rs.getInt("ACCPRG_PROGRAMAN");
				descripcion = rs.getString("ACCPRG_DESCRIP").trim();
				activo = rs.getInt("ACCPRG_ACTIVO");
				password = rs.getString("ACCPRG_PASSWORD").trim();
				nivelAcceso = rs.getInt("ACCPRG_NIVEL_ACCESO");
				usando = new String[32];
				usando[0] = rs.getString("ACCPRG_USANDO_1").trim();
				usando[1] = rs.getString("ACCPRG_USANDO_2").trim();
				usando[2] = rs.getString("ACCPRG_USANDO_3").trim();
				usando[3] = rs.getString("ACCPRG_USANDO_4").trim();
				usando[4] = rs.getString("ACCPRG_USANDO_5").trim();
				usando[5] = rs.getString("ACCPRG_USANDO_6").trim();
				usando[6] = rs.getString("ACCPRG_USANDO_7").trim();
				usando[7] = rs.getString("ACCPRG_USANDO_8").trim();
				usando[8] = rs.getString("ACCPRG_USANDO_9").trim();
				usando[9] = rs.getString("ACCPRG_USANDO_10").trim();
				usando[10] = rs.getString("ACCPRG_USANDO_11").trim();
				usando[11] = rs.getString("ACCPRG_USANDO_12").trim();
				usando[12] = rs.getString("ACCPRG_USANDO_13").trim();
				usando[13] = rs.getString("ACCPRG_USANDO_14").trim();
				usando[14] = rs.getString("ACCPRG_USANDO_15").trim();
				usando[15] = rs.getString("ACCPRG_USANDO_16").trim();
				usando[16] = rs.getString("ACCPRG_USANDO_17").trim();
				usando[17] = rs.getString("ACCPRG_USANDO_18").trim();
				usando[18] = rs.getString("ACCPRG_USANDO_19").trim();
				usando[19] = rs.getString("ACCPRG_USANDO_20").trim();
				usando[20] = rs.getString("ACCPRG_USANDO_21").trim();
				usando[21] = rs.getString("ACCPRG_USANDO_22").trim();
				usando[22] = rs.getString("ACCPRG_USANDO_23").trim();
				usando[23] = rs.getString("ACCPRG_USANDO_24").trim();
				usando[24] = rs.getString("ACCPRG_USANDO_25").trim();
				usando[25] = rs.getString("ACCPRG_USANDO_26").trim();
				usando[26] = rs.getString("ACCPRG_USANDO_27").trim();
				usando[27] = rs.getString("ACCPRG_USANDO_28").trim();
				usando[28] = rs.getString("ACCPRG_USANDO_29").trim();
				usando[29] = rs.getString("ACCPRG_USANDO_30").trim();
				usando[30] = rs.getString("ACCPRG_USANDO_31").trim();
				usando[31] = rs.getString("ACCPRG_USANDO_32").trim();
				conMsjprg = rs.getInt("ACCPRG_CON_MSJPRG");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error en lectura fichero de Programa!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{						
			empresa = rs.getString("EMPRESA").trim();
			programa = rs.getString("ACCPRG_PROGRAMA").trim();
			programaNumero = rs.getInt("ACCPRG_PROGRAMAN");
			descripcion = rs.getString("ACCPRG_DESCRIP").trim();
			activo = rs.getInt("ACCPRG_ACTIVO");
			password = rs.getString("ACCPRG_PASSWORD").trim();
			nivelAcceso = rs.getInt("ACCPRG_NIVEL_ACCESO");
			usando[0] = rs.getString("ACCPRG_USANDO_1").trim();
			usando[1] = rs.getString("ACCPRG_USANDO_2").trim();
			usando[2] = rs.getString("ACCPRG_USANDO_3").trim();
			usando[3] = rs.getString("ACCPRG_USANDO_4").trim();
			usando[4] = rs.getString("ACCPRG_USANDO_5").trim();
			usando[5] = rs.getString("ACCPRG_USANDO_6").trim();
			usando[6] = rs.getString("ACCPRG_USANDO_7").trim();
			usando[7] = rs.getString("ACCPRG_USANDO_8").trim();
			usando[8] = rs.getString("ACCPRG_USANDO_9").trim();
			usando[9] = rs.getString("ACCPRG_USANDO_10").trim();
			usando[10] = rs.getString("ACCPRG_USANDO_11").trim();
			usando[11] = rs.getString("ACCPRG_USANDO_12").trim();
			usando[12] = rs.getString("ACCPRG_USANDO_13").trim();
			usando[13] = rs.getString("ACCPRG_USANDO_14").trim();
			usando[14] = rs.getString("ACCPRG_USANDO_15").trim();
			usando[15] = rs.getString("ACCPRG_USANDO_16").trim();
			usando[16] = rs.getString("ACCPRG_USANDO_17").trim();
			usando[17] = rs.getString("ACCPRG_USANDO_18").trim();
			usando[18] = rs.getString("ACCPRG_USANDO_19").trim();
			usando[19] = rs.getString("ACCPRG_USANDO_20").trim();
			usando[20] = rs.getString("ACCPRG_USANDO_21").trim();
			usando[21] = rs.getString("ACCPRG_USANDO_22").trim();
			usando[22] = rs.getString("ACCPRG_USANDO_23").trim();
			usando[23] = rs.getString("ACCPRG_USANDO_24").trim();
			usando[24] = rs.getString("ACCPRG_USANDO_25").trim();
			usando[25] = rs.getString("ACCPRG_USANDO_26").trim();
			usando[26] = rs.getString("ACCPRG_USANDO_27").trim();
			usando[27] = rs.getString("ACCPRG_USANDO_28").trim();
			usando[28] = rs.getString("ACCPRG_USANDO_29").trim();
			usando[29] = rs.getString("ACCPRG_USANDO_30").trim();
			usando[30] = rs.getString("ACCPRG_USANDO_31").trim();
			usando[31] = rs.getString("ACCPRG_USANDO_32").trim();
			conMsjprg = rs.getInt("ACCPRG_CON_MSJPRG");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error en lectura fichero de Programa!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO ACCPRG " +
						   "(EMPRESA, " +
						   "ACCPRG_PROGRAMA, " +
						   "ACCPRG_PROGRAMAN, " +
						   "ACCPRG_DESCRIP, " +
						   "ACCPRG_ACTIVO, " +
						   "ACCPRG_PASSWORD, " +
						   "ACCPRG_NIVEL_ACCESO, " +
						   "ACCPRG_USANDO_1, " +
						   "ACCPRG_USANDO_2, " +
						   "ACCPRG_USANDO_3, " +
						   "ACCPRG_USANDO_4, " +
						   "ACCPRG_USANDO_5, " +
						   "ACCPRG_USANDO_6, " +
						   "ACCPRG_USANDO_7, " +
						   "ACCPRG_USANDO_8, " +
						   "ACCPRG_USANDO_9, " +
						   "ACCPRG_USANDO_10, " +
						   "ACCPRG_USANDO_11, " +
						   "ACCPRG_USANDO_12, " +
						   "ACCPRG_USANDO_13, " +
						   "ACCPRG_USANDO_14, " +
						   "ACCPRG_USANDO_15, " +
						   "ACCPRG_USANDO_16, " +
						   "ACCPRG_USANDO_17, " +
						   "ACCPRG_USANDO_18, " +
						   "ACCPRG_USANDO_19, " +
						   "ACCPRG_USANDO_20, " +
						   "ACCPRG_USANDO_21, " +
						   "ACCPRG_USANDO_22, " +
						   "ACCPRG_USANDO_23, " +
						   "ACCPRG_USANDO_24, " +
						   "ACCPRG_USANDO_25, " +
						   "ACCPRG_USANDO_26, " +
						   "ACCPRG_USANDO_27, " +
						   "ACCPRG_USANDO_28, " +
						   "ACCPRG_USANDO_29, " +
						   "ACCPRG_USANDO_30, " +
						   "ACCPRG_USANDO_31, " +
						   "ACCPRG_USANDO_32, " +
						   "ACCPRG_CON_MSJPRG) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " + 
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "ACCPRG_PROGRAMA = ?, " +
						   "ACCPRG_PROGRAMAN = ?, " +
						   "ACCPRG_DESCRIP = ?, " +
						   "ACCPRG_ACTIVO = ?, " +
						   "ACCPRG_PASSWORD = ?, " +
						   "ACCPRG_NIVEL_ACCESO = ?, " +
						   "ACCPRG_USANDO_1 = ?, " +
						   "ACCPRG_USANDO_2 = ?, " +
						   "ACCPRG_USANDO_3 = ?, " +
						   "ACCPRG_USANDO_4 = ?, " +
						   "ACCPRG_USANDO_5 = ?, " +
						   "ACCPRG_USANDO_6 = ?, " +
						   "ACCPRG_USANDO_7 = ?, " +
						   "ACCPRG_USANDO_8 = ?, " +
						   "ACCPRG_USANDO_9 = ?, " +
						   "ACCPRG_USANDO_10 = ?, " +
						   "ACCPRG_USANDO_11 = ?, " +
						   "ACCPRG_USANDO_12 = ?, " +
						   "ACCPRG_USANDO_13 = ?, " +
						   "ACCPRG_USANDO_14 = ?, " +
						   "ACCPRG_USANDO_15 = ?, " +
						   "ACCPRG_USANDO_16 = ?, " +
						   "ACCPRG_USANDO_17 = ?, " +
						   "ACCPRG_USANDO_18 = ?, " +
						   "ACCPRG_USANDO_19 = ?, " +
						   "ACCPRG_USANDO_20 = ?, " +
						   "ACCPRG_USANDO_21 = ?, " +
						   "ACCPRG_USANDO_22 = ?, " +
						   "ACCPRG_USANDO_23 = ?, " +
						   "ACCPRG_USANDO_24 = ?, " +
						   "ACCPRG_USANDO_25 = ?, " +
						   "ACCPRG_USANDO_26 = ?, " +
						   "ACCPRG_USANDO_27 = ?, " +
						   "ACCPRG_USANDO_28 = ?, " +
						   "ACCPRG_USANDO_29 = ?, " +
						   "ACCPRG_USANDO_30 = ?, " +
						   "ACCPRG_USANDO_31 = ?, " +
						   "ACCPRG_USANDO_32 = ?, " +
						   "ACCPRG_CON_MSJPRG = ?)";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			// Insert
			ps.setString(1, Cadena.left(empresa, 2));
			ps.setString(2, Cadena.left(programa, 8));
			ps.setInt(3, programaNumero);
			ps.setString(4, Cadena.left(descripcion, 32));
			ps.setInt(5, activo);
			ps.setString(6, Cadena.left(password, 8));
			ps.setInt(7, nivelAcceso);
			// 8
			for(int i = 0; i < 32; i++)
				ps.setString(7+i, Cadena.left(usando[i], 8));
			// 40
			ps.setInt(39, conMsjprg);
			// Update
			ps.setString(41, Cadena.left(empresa, 2));
			ps.setString(42, Cadena.left(programa, 8));
			ps.setInt(43, programaNumero);
			ps.setString(44, Cadena.left(descripcion, 32));
			ps.setInt(45, activo);
			ps.setString(46, Cadena.left(password, 8));
			ps.setInt(47, nivelAcceso);
			// 48
			for(int i = 0; i < 32; i++)
				ps.setString(48+i, Cadena.left(usando[i], 8));
			// 80
			ps.setInt(80, conMsjprg);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de Programa!!!");
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

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public int getProgramaNumero() {
		return programaNumero;
	}

	public void setProgramaNumero(int programaNumero) {
		this.programaNumero = programaNumero;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getNivelAcceso() {
		return nivelAcceso;
	}

	public void setNivelAcceso(int nivelAcceso) {
		this.nivelAcceso = nivelAcceso;
	}

	public String getUsando(int i) {
		return usando[i];
	}
	
	public String[] getUsando() {
		return usando;
	}

	public void setUsando(int indice, String valor) {
		this.usando[indice] = valor;
	}
	
	public void setUsando(String[] usando) {
		this.usando = usando;
	}

	public int getConMsjprg() {
		return conMsjprg;
	}

	public void setConMsjprg(int conMsjprg) {
		this.conMsjprg = conMsjprg;
	}
}
