package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import util.Apariencia;
import util.BaseDatos;

import util.Cadena;




public class MovimientoContable {
	private String empresa;
	private String cuenta;
	private long fechaAsientoApunte;
	private int centro;
	private int documento;
	private int clave;
	private double importe;
	private int actualizaAcumulado;
	private int punteo;
	private String tty;
	private int usuario;
	private long dayTime;
	private int asientoAño;
	private int enviado;
	private int fechaBanco;
	private int documentoBanco;

	public MovimientoContable(){
		empresa = DatosComunes.eEmpresa;
		cuenta = "";
		fechaAsientoApunte = (long)0;
		centro = 0;
		documento = 0;
		clave = 0;
		importe = 0.0;
		actualizaAcumulado = 0;
		punteo = 0;
		tty = "";
		usuario = 0;
		dayTime = (long)0;
		asientoAño = 0;
		enviado = 0;
		fechaBanco = 0;
		documentoBanco = 0;
	}

	public MovimientoContable(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				cuenta = rs.getString("MOVCON_CUENTA").trim();
				fechaAsientoApunte = rs.getLong("MOVCON_FECH_ASTO_APT");
				centro = rs.getInt("MOVCON_CENTRO");
				documento = rs.getInt("MOVCON_DOCUMENTO");
				clave = rs.getInt("MOVCON_CLAVE");
				importe = rs.getDouble("MOVCON_IMPORTE");
				actualizaAcumulado = rs.getInt("MOVCON_ACT_ACUM");
				punteo = rs.getInt("MOVCON_PUNTEO");
				tty = rs.getString("MOVCON_TTY").trim();
				usuario = rs.getInt("MOVCON_USUARIO");
				dayTime = rs.getLong("MOVCON_DAY_TIME");
				asientoAño = rs.getInt("MOVCON_ASTO_ANY");
				enviado = rs.getInt("MOVCON_ENVIADO");
				fechaBanco = rs.getInt("MOVCON_FECH_BCO");
				documentoBanco = rs.getInt("MOVCON_DOC_BCO");
			}                      
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de MovimientoContable!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			cuenta = rs.getString("MOVCON_CUENTA").trim();
			fechaAsientoApunte = rs.getLong("MOVCON_FECH_ASTO_APT");
			centro = rs.getInt("MOVCON_CENTRO");
			documento = rs.getInt("MOVCON_DOCUMENTO");
			clave = rs.getInt("MOVCON_CLAVE");
			importe = rs.getDouble("MOVCON_IMPORTE");
			actualizaAcumulado = rs.getInt("MOVCON_ACT_ACUM");
			punteo = rs.getInt("MOVCON_PUNTEO");
			tty = rs.getString("MOVCON_TTY").trim();
			usuario = rs.getInt("MOVCON_USUARIO");
			dayTime = rs.getLong("MOVCON_DAY_TIME");
			asientoAño = rs.getInt("MOVCON_ASTO_ANY");
			enviado = rs.getInt("MOVCON_ENVIADO");
			fechaBanco = rs.getInt("MOVCON_FECH_BCO");
			documentoBanco = rs.getInt("MOVCON_DOC_BCO");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de MovimientoContable!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

        
    // Leemos un apunde de un asiento, para ello pasamos como paraámetros
    // la fecha, el asiento, el apunte y el centro
    // Si lo hemos leido bien, devolvemos TRUE, en caso de problemas FALSE
    /**
     *
     * @param centro
     * @param fecha
     * @param asiento
     * @param apunte
     * @return lecturaOk
     */
    public boolean read(int centro, int fecha, int asiento, int apunte) {

        boolean lecturaOk = true;

        String claveFechaAsientoApunte = String.valueOf(fecha)
                + String.format("%05d", asiento)
                + String.format("%05d", apunte);

        String strSqlCuenta = "SELECT * FROM MOVCON WHERE "
                + "EMPRESA = '" + DatosComunes.eEmpresa + "' "
                + " AND MOVCON_CENTRO = " + centro
                + " AND MOVCON_FECH_ASTO_APT = '" + claveFechaAsientoApunte + "' LIMIT 1";

        ResultSet rsSql = null;
        MysqlConnect m = null;

        m = MysqlConnect.getDbCon();

        if (BaseDatos.countRows(strSqlCuenta) != 0) {
            try {
                rsSql = m.query(strSqlCuenta);
                // Como ya tenemos el ResultSet se lo pasamos al mñrodo 'read(ResultSet rs)'.
                if (rsSql.next()) {
                    this.read(rsSql);
                    lecturaOk = true;
                    // Cerramos para evitar gastar memoria
                    rsSql.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                lecturaOk = false;
                if (DatosComunes.enDebug) {
                    e.printStackTrace();
                }
                Apariencia.mensajeInformativo(5, "Error en lectura fichero de Movimientos Contables");
            }
        } else {
            lecturaOk = false;
        }

        rsSql = null;
        m = null;
        
        return lecturaOk;
    }
        
	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO MOVCON " +
						   "(EMPRESA, " +
						   "MOVCON_CUENTA, " +
						   "MOVCON_FECH_ASTO_APT, " +
						   "MOVCON_CENTRO, " +
						   "MOVCON_DOCUMENTO, " +
						   "MOVCON_CLAVE, " +
						   "MOVCON_IMPORTE, " +
						   "MOVCON_ACT_ACUM, " +
						   "MOVCON_PUNTEO, " +
						   "MOVCON_TTY, " +
						   "MOVCON_USUARIO, " +
						   "MOVCON_DAY_TIME, " +
						   "MOVCON_ASTO_ANY, " +
						   "MOVCON_ENVIADO, " +
						   "MOVCON_FECH_BCO, " +
						   "MOVCON_DOC_BCO) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "MOVCON_CUENTA = ?, " +
						   "MOVCON_FECH_ASTO_APT = ?, " +
						   "MOVCON_CENTRO = ?, " +
						   "MOVCON_DOCUMENTO = ?, " +
						   "MOVCON_CLAVE = ?, " +
						   "MOVCON_IMPORTE = ?, " +
						   "MOVCON_ACT_ACUM = ?, " +
						   "MOVCON_PUNTEO = ?, " +
						   "MOVCON_TTY = ?, " +
						   "MOVCON_USUARIO = ?, " +
						   "MOVCON_DAY_TIME = ?, " +
						   "MOVCON_ASTO_ANY = ?, " +
						   "MOVCON_ENVIADO = ?, " +
						   "MOVCON_FECH_BCO = ?, " +
						   "MOVCON_DOC_BCO = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(cuenta, 9));
			ps.setLong(i++, fechaAsientoApunte);
			ps.setInt(i++, centro);
			ps.setInt(i++, documento);
			ps.setInt(i++, clave);
			ps.setDouble(i++, importe);
			ps.setInt(i++, actualizaAcumulado);
			ps.setInt(i++, punteo);
			ps.setString(i++, Cadena.left(tty, 5));
			ps.setInt(i++, usuario);
			ps.setLong(i++, dayTime);
			ps.setInt(i++, asientoAño);
			ps.setInt(i++, enviado);
			ps.setInt(i++, fechaBanco);
			ps.setInt(i++, documentoBanco);
				
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setString(i++, Cadena.left(cuenta, 9));
			ps.setLong(i++, fechaAsientoApunte);
			ps.setInt(i++, centro);
			ps.setInt(i++, documento);
			ps.setInt(i++, clave);
			ps.setDouble(i++, importe);
			ps.setInt(i++, actualizaAcumulado);
			ps.setInt(i++, punteo);
			ps.setString(i++, Cadena.left(tty, 5));
			ps.setInt(i++, usuario);
			ps.setLong(i++, dayTime);
			ps.setInt(i++, asientoAño);
			ps.setInt(i++, enviado);
			ps.setInt(i++, fechaBanco);
			ps.setInt(i++, documentoBanco);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de MovimientoContable!!!");
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

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public long getFechaAsientoApunte() {
		return fechaAsientoApunte;
	}

	public void setFechaAsientoApunte(long fechaAsientoApunte) {
		this.fechaAsientoApunte = fechaAsientoApunte;
	}

	public int getCentro() {
		return centro;
	}

	public void setCentro(int centro) {
		this.centro = centro;
	}

	public int getDocumento() {
		return documento;
	}

	public void setDocumento(int documento) {
		this.documento = documento;
	}

	public int getClave() {
		return clave;
	}

	public void setClave(int clave) {
		this.clave = clave;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public int getActualizaAcumulado() {
		return actualizaAcumulado;
	}

	public void setActualizaAcumulado(int actualizaAcumulado) {
		this.actualizaAcumulado = actualizaAcumulado;
	}

	public int getPunteo() {
		return punteo;
	}

	public void setPunteo(int punteo) {
		this.punteo = punteo;
	}

	public String getTty() {
		return tty;
	}

	public void setTty(String tty) {
		this.tty = tty;
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public long getDayTime() {
		return dayTime;
	}

	public void setDayTime(long dayTime) {
		this.dayTime = dayTime;
	}

	public int getAsientoAño() {
		return asientoAño;
	}

	public void setAsientoAño(int asientoAño) {
		this.asientoAño = asientoAño;
	}

	public int getEnviado() {
		return enviado;
	}

	public void setEnviado(int enviado) {
		this.enviado = enviado;
	}

	public int getFechaBanco() {
		return fechaBanco;
	}

	public void setFechaBanco(int fechaBanco) {
		this.fechaBanco = fechaBanco;
	}

	public int getDocumentoBanco() {
		return documentoBanco;
	}

	public void setDocumentoBanco(int documentoBanco) {
		this.documentoBanco = documentoBanco;
	}
}
