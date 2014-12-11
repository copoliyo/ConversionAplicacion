package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import com.mysql.jdbc.ResultSet;


public class FormaCobro {
	private String empresa;
	private int codigo;
	private String descripcion;
	private int activo;
	private String cuentaContable;
	private double importeDia;
	private int apunteDetallado;
	private int tarjeta;
	private int grupo;

	public FormaCobro(){
		empresa = DatosComunes.eEmpresa;
		codigo = 0;
		descripcion = "";
		activo = 0;
		cuentaContable = "";
		importeDia = 0.0;
		apunteDetallado = 0;
		tarjeta = 0;
		grupo = 0;
	}

	public FormaCobro(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				codigo = rs.getInt("FORCOB_CODIGO");
				descripcion = rs.getString("FORCOB_DESCRIP").trim();
				activo = rs.getInt("FORCOB_ACTIVO");
				cuentaContable = rs.getString("FORCOB_CTA_CONTAB").trim();
				importeDia = rs.getDouble("FORCOB_IMPORTE_DIA");
				apunteDetallado = rs.getInt("FORCOB_APT_DETALLE");
				tarjeta = rs.getInt("FORCOB_TARJETA");
				grupo = rs.getInt("FORCOB_GRUPO");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de FormaCobro!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			codigo = rs.getInt("FORCOB_CODIGO");
			descripcion = rs.getString("FORCOB_DESCRIP").trim();
			activo = rs.getInt("FORCOB_ACTIVO");
			cuentaContable = rs.getString("FORCOB_CTA_CONTAB").trim();
			importeDia = rs.getDouble("FORCOB_IMPORTE_DIA");
			apunteDetallado = rs.getInt("FORCOB_APT_DETALLE");
			tarjeta = rs.getInt("FORCOB_TARJETA");
			grupo = rs.getInt("FORCOB_GRUPO");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de FormaCobro!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}
	
	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO FORCOB " +
						   "(EMPRESA, " +
						   "FORCOB_CODIGO, " +
						   "FORCOB_DESCRIP, " +
						   "FORCOB_ACTIVO, " +
						   "FORCOB_CTA_CONTAB, " +
						   "FORCOB_IMPORTE_DIA, " +
						   "FORCOB_APT_DETALLE, " +
						   "FORCOB_TARJETA, " +
						   "FORCOB_GRUPO) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "FORCOB_CODIGO = ?, " +
						   "FORCOB_DESCRIP = ?, " +
						   "FORCOB_ACTIVO = ?, " +
						   "FORCOB_CTA_CONTAB = ?, " +
						   "FORCOB_IMPORTE_DIA = ?, " +
						   "FORCOB_APT_DETALLE = ?, " +
						   "FORCOB_TARJETA = ?, " +
						   "FORCOB_GRUPO = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, codigo);
			ps.setString(i++, Cadena.left(descripcion, 20));
			ps.setInt(i++, activo);
			ps.setString(i++, Cadena.left(cuentaContable, 9));
			ps.setDouble(i++, importeDia);
			ps.setInt(i++, apunteDetallado);
			ps.setInt(i++, tarjeta);
			ps.setInt(i++, grupo);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, codigo);
			ps.setString(i++, Cadena.left(descripcion, 20));
			ps.setInt(i++, activo);
			ps.setString(i++, Cadena.left(cuentaContable, 9));
			ps.setDouble(i++, importeDia);
			ps.setInt(i++, apunteDetallado);
			ps.setInt(i++, tarjeta);
			ps.setInt(i++, grupo);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de FormaCobro!!!");
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

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
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

	public String getCuentaContable() {
		return cuentaContable;
	}

	public void setCuentaContable(String cuentaContable) {
		this.cuentaContable = cuentaContable;
	}

	public double getImporteDia() {
		return importeDia;
	}

	public void setImporteDia(double importeDia) {
		this.importeDia = importeDia;
	}

	public int getApunteDetallado() {
		return apunteDetallado;
	}

	public void setApunteDetallado(int apunteDetallado) {
		this.apunteDetallado = apunteDetallado;
	}

	public int getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(int tarjeta) {
		this.tarjeta = tarjeta;
	}

	public int getGrupo() {
		return grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}
}
