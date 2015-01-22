package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import util.Apariencia;
import util.BaseDatos;

import util.Cadena;

/**
 *
 * @author Txus
 */
public class FacturaEmitida {
	private String empresa;
	private int cliente;
	private int año;
	private String serie;
	private int factura;
	private int centro;
	private int fecha;
	private long fechaAsientoApunte;
	private String nombreCliente;
	private String nif;
	private double baseIva[];
	private double baseRecargoEquivalencia[];
	private double iva;
	private double recargoEquivalencia;
	private double total;
	
    /**
     *
     */
    public FacturaEmitida(){
		empresa = DatosComunes.eEmpresa;
		cliente = 0;
		año = 0;
		serie = "";
		factura = 0;
		centro = 0;
		fecha = 0;
		fechaAsientoApunte = 0L;
		nombreCliente = "";
		nif = "";
		
		baseIva = new double[3];
		baseRecargoEquivalencia = new double[3];
		for(int i = 0; i < 3; i++){
			baseIva[i] = 0.0;
			baseRecargoEquivalencia[i] = 0.0;
		}
					
		iva = 0.0;
		recargoEquivalencia = 0.0;
		total = 0.0;
	}
	
    /**
     *
     * @param rs
     */
    public FacturaEmitida(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				cliente = rs.getInt("FACEMI_CLIENTE");
				año = rs.getInt("FACEMI_ANY");
				serie = rs.getString("FACEMI_SERIE").trim();
				factura = rs.getInt("FACEMI_FACTURA");
				centro = rs.getInt("FACEMI_CENTRO");
				fecha = rs.getInt("FACEMI_FECHA");
				fechaAsientoApunte = rs.getLong("FACEMI_FECH_ASTO_APT");
				nombreCliente = rs.getString("FACEMI_NOMBRE_CLI").trim();
				nif = rs.getString("FACEMI_NIF").trim();	
				baseIva = new double[3];
				baseIva[0] = rs.getDouble("FACEMI_BASES_IVA_1");
				baseIva[1] = rs.getDouble("FACEMI_BASES_IVA_2");
				baseIva[2] = rs.getDouble("FACEMI_BASES_IVA_3");
				baseRecargoEquivalencia = new double[3];
				baseRecargoEquivalencia[0] = rs.getDouble("FACEMI_BASES_RE_1");
				baseRecargoEquivalencia[1] = rs.getDouble("FACEMI_BASES_RE_2");
				baseRecargoEquivalencia[2] = rs.getDouble("FACEMI_BASES_RE_3");											
				iva = rs.getDouble("FACEMI_IVA");
				recargoEquivalencia = rs.getDouble("FACEMI_RECEQ");
				total = rs.getDouble("FACEMI_TOTAL");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error en lectura fichero de FacturaEmitida!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}
	
    /**
     *
     * @param rs
     */
    public void read(ResultSet rs){
		try{				
				empresa = rs.getString("EMPRESA").trim();
				cliente = rs.getInt("FACEMI_CLIENTE");
				año = rs.getInt("FACEMI_ANY");
				serie = rs.getString("FACEMI_SERIE").trim();
				factura = rs.getInt("FACEMI_FACTURA");
				centro = rs.getInt("FACEMI_CENTRO");
				fecha = rs.getInt("FACEMI_FECHA");
				fechaAsientoApunte = rs.getLong("FACEMI_FECH_ASTO_APT");
				nombreCliente = rs.getString("FACEMI_NOMBRE_CLI").trim();
				nif = rs.getString("FACEMI_NIF").trim();	
				baseIva[0] = rs.getDouble("FACEMI_BASES_IVA_1");
				baseIva[1] = rs.getDouble("FACEMI_BASES_IVA_2");
				baseIva[2] = rs.getDouble("FACEMI_BASES_IVA_3");
				baseRecargoEquivalencia[0] = rs.getDouble("FACEMI_BASES_RE_1");
				baseRecargoEquivalencia[1] = rs.getDouble("FACEMI_BASES_RE_2");
				baseRecargoEquivalencia[2] = rs.getDouble("FACEMI_BASES_RE_3");											
				iva = rs.getDouble("FACEMI_IVA");
				recargoEquivalencia = rs.getDouble("FACEMI_RECEQ");
				total = rs.getDouble("FACEMI_TOTAL");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error en lectura fichero de FacturaEmitida!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}
        
    /**
     *
     * @param strSql Cadena con la consulta a ejecutar.
     * @return <code>true</code> Si la lectura ha sido correcta. <br>
     */
    public boolean read(String strSql) {
        boolean lecturaCorrecta = true;

        ResultSet rsSql = null;
        MysqlConnect m = null;

        m = MysqlConnect.getDbCon();

        int registrosLeidos = BaseDatos.countRows(strSql);

        if (registrosLeidos != 0) {
            try {
                rsSql = m.query(strSql);
                // Como ya tenemos el ResultSet se lo pasamos al mñrodo 'read(ResultSet rs)'.
                if (rsSql.next()) {
                    this.read(rsSql);
                    // Cerramos para evitar gastar memoria
                    rsSql.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                lecturaCorrecta = false;
                if (DatosComunes.enDebug) {
                    e.printStackTrace();
                }
                Apariencia.mensajeInformativo(5, "Error en lectura fichero de Facturas Emitidas");
            }            
        }else{
            lecturaCorrecta = false;
        }
            

        return lecturaCorrecta;
    }

    /**
     *
     * @param centro Centro contable
     * @param factura Número de factura
     * @param any Año de la factura
     * @param serie Serie 
     * @return <code>registrosBorrados</code>  Número de registros borrados si
     * -1 es que no se ha borrado
     */
    public int delete(int centro, int factura, int any, String serie) {
        int registrosBorrados = 0;

        Statement ps = null;

        String sqlDelete = "DELETE FROM FACEMI WHERE "
                + "EMPRESA = '" + DatosComunes.eEmpresa + "' AND "
                + "FACEMI_CENTRO = " + centro + " AND "
                + "FACEMI_FACTURA = " + factura + " AND "
                + "FACEMI_ANY = " + any + " AND "
                + "FACEMI_SERIE = '" + serie +"'";

        try {
            ps = MysqlConnect.db.conn.createStatement();

            registrosBorrados = ps.executeUpdate(sqlDelete);

        } catch (SQLException e) {
            registrosBorrados = -1;
            if (DatosComunes.enDebug) {
                JOptionPane.showMessageDialog(null,
                        "Error en borrado fichero de Efectos a Cobrar!!!");
                e.printStackTrace();
            }
        }

        return registrosBorrados;
    }
    
    /**
     *
     * @return 
     */
    public boolean write() {

        boolean escrituraCorrecta = true;
        PreparedStatement ps = null;

        String sqlInsert = "INSERT INTO FACEMI "
                + "(EMPRESA, "
                + "FACEMI_CLIENTE, "
                + "FACEMI_ANY, "
                + "FACEMI_SERIE, "
                + "FACEMI_FACTURA, "
                + "FACEMI_CENTRO, "
                + "FACEMI_FECHA, "
                + "FACEMI_FECH_ASTO_APT, "
                + "FACEMI_NOMBRE_CLI, "
                + "FACEMI_NIF, "
                + "FACEMI_BASES_IVA_1, "
                + "FACEMI_BASES_IVA_2, "
                + "FACEMI_BASES_IVA_3, "
                + "FACEMI_BASES_RE_1, "
                + "FACEMI_BASES_RE_2, "
                + "FACEMI_BASES_RE_3, "
                + "FACEMI_IVA, "
                + "FACEMI_RECEQ, "
                + "FACEMI_TOTAL) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
                + "?, ?, ?, ?, ?, ?, ?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE "
                + "EMPRESA = ?, "
                + "FACEMI_CLIENTE = ?, "
                + "FACEMI_ANY = ?, "
                + "FACEMI_SERIE = ?, "
                + "FACEMI_FACTURA = ?, "
                + "FACEMI_CENTRO = ?, "
                + "FACEMI_FECHA = ?, "
                + "FACEMI_FECH_ASTO_APT = ?, "
                + "FACEMI_NOMBRE_CLI = ?, "
                + "FACEMI_NIF = ?, "
                + "FACEMI_BASES_IVA_1 = ?, "
                + "FACEMI_BASES_IVA_2 = ?, "
                + "FACEMI_BASES_IVA_3 = ?, "
                + "FACEMI_BASES_RE_1 = ?, "
                + "FACEMI_BASES_RE_2 = ?, "
                + "FACEMI_BASES_RE_3 = ?, "
                + "FACEMI_IVA = ?, "
                + "FACEMI_RECEQ = ?, "
                + "FACEMI_TOTAL = ? ";

        try {
            ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
            int i = 1;
            // Insert
            ps.setString(i++, Cadena.left(empresa, 2));
            ps.setInt(i++, cliente);
            ps.setInt(i++, año);
            ps.setString(i++, Cadena.left(serie, 2));
            ps.setInt(i++, factura);
            ps.setInt(i++, centro);
            ps.setInt(i++, fecha);
            ps.setLong(i++, fechaAsientoApunte);
            ps.setString(i++, Cadena.left(nombreCliente, 30));
            ps.setString(i++, Cadena.left(nif, 16));
            for (int j = 0; j < 3; j++) {
                ps.setDouble(i++, baseIva[j]);
            }
            for (int j = 0; j < 3; j++) {
                ps.setDouble(i++, baseRecargoEquivalencia[j]);
            }
            ps.setDouble(i++, iva);
            ps.setDouble(i++, recargoEquivalencia);
            ps.setDouble(i++, total);

            // Update
            ps.setString(i++, Cadena.left(empresa, 2));
            ps.setInt(i++, cliente);
            ps.setInt(i++, año);
            ps.setString(i++, Cadena.left(serie, 2));
            ps.setInt(i++, factura);
            ps.setInt(i++, centro);
            ps.setInt(i++, fecha);
            ps.setLong(i++, fechaAsientoApunte);
            ps.setString(i++, Cadena.left(nombreCliente, 30));
            ps.setString(i++, Cadena.left(nif, 16));
            for (int j = 0; j < 3; j++) {
                ps.setDouble(i++, baseIva[j]);
            }
            for (int j = 0; j < 3; j++) {
                ps.setDouble(i++, baseRecargoEquivalencia[j]);
            }
            ps.setDouble(i++, iva);
            ps.setDouble(i++, recargoEquivalencia);
            ps.setDouble(i++, total);

            ps.execute();

        } catch (SQLException e) {
            escrituraCorrecta = false;
            if (DatosComunes.enDebug) {
                JOptionPane.showMessageDialog(null,
                        "Error en escritura fichero de FacturaEmitida!!!");
                e.printStackTrace();
            }
        }
        
        return escrituraCorrecta;
    }
	
    /**
     *
     * @return
     */
    public String getEmpresa() {
		return empresa;
	}

    /**
     *
     * @param empresa
     */
    public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

    /**
     *
     * @return
     */
    public int getCliente() {
		return cliente;
	}

    /**
     *
     * @param cliente
     */
    public void setCliente(int cliente) {
		this.cliente = cliente;
	}

    /**
     *
     * @return
     */
    public int getAño() {
		return año;
	}

    /**
     *
     * @param año
     */
    public void setAño(int año) {
		this.año = año;
	}

    /**
     *
     * @return
     */
    public String getSerie() {
		return serie;
	}

    /**
     *
     * @param serie
     */
    public void setSerie(String serie) {
		this.serie = serie;
	}

    /**
     *
     * @return
     */
    public int getFactura() {
		return factura;
	}

    /**
     *
     * @param factura
     */
    public void setFactura(int factura) {
		this.factura = factura;
	}

    /**
     *
     * @return
     */
    public int getCentro() {
		return centro;
	}

    /**
     *
     * @param centro
     */
    public void setCentro(int centro) {
		this.centro = centro;
	}

    /**
     *
     * @return
     */
    public int getFecha() {
		return fecha;
	}

    /**
     *
     * @param fecha
     */
    public void setFecha(int fecha) {
		this.fecha = fecha;
	}

    /**
     *
     * @return
     */
    public long getFechaAsientoApunte() {
		return fechaAsientoApunte;
	}

    /**
     *
     * @param fechaAsientoApunte
     */
    public void setFechaAsientoApunte(long fechaAsientoApunte) {
		this.fechaAsientoApunte = fechaAsientoApunte;
	}

    /**
     *
     * @return
     */
    public String getNombreCliente() {
		return nombreCliente;
	}

    /**
     *
     * @param nombreCliente
     */
    public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

    /**
     *
     * @return
     */
    public String getNif() {
		return nif;
	}

    /**
     *
     * @param nif
     */
    public void setNif(String nif) {
		this.nif = nif;
	}

    /**
     *
     * @param i
     * @return
     */
    public double getBaseIva(int i) {
		return baseIva[i];
	}
	
    /**
     *
     * @return
     */
    public double[] getBaseIva() {
		return baseIva;
	}

    /**
     *
     * @param indice
     * @param valor
     */
    public void setBaseIva(int indice, double valor) {
		this.baseIva[indice] = valor;
	}
	
    /**
     *
     * @param baseIva
     */
    public void setBaseIva(double[] baseIva) {
		this.baseIva = baseIva;
	}

    /**
     *
     * @param i
     * @return
     */
    public double getBaseRecargoEquivalencia(int i) {
		return baseRecargoEquivalencia[i];
	}

    /**
     *
     * @return
     */
    public double[] getBaseRecargoEquivalencia() {
		return baseRecargoEquivalencia;
	}

    /**
     *
     * @param indice
     * @param valor
     */
    public void setBaseRecargoEquivalencia(int indice, double valor) {
		this.baseRecargoEquivalencia[indice] = valor;
	}

    /**
     *
     * @param baseRecargoEquivalencia
     */
    public void setBaseRecargoEquivalencia(double[] baseRecargoEquivalencia) {
		this.baseRecargoEquivalencia = baseRecargoEquivalencia;
	}

    /**
     *
     * @return
     */
    public double getIva() {
		return iva;
	}

    /**
     *
     * @param iva
     */
    public void setIva(double iva) {
		this.iva = iva;
	}

    /**
     *
     * @return
     */
    public double getRecargoEquivalencia() {
		return recargoEquivalencia;
	}

    /**
     *
     * @param recargoEquivalencia
     */
    public void setRecargoEquivalencia(double recargoEquivalencia) {
		this.recargoEquivalencia = recargoEquivalencia;
	}

    /**
     *
     * @return
     */
    public double getTotal() {
		return total;
	}

    /**
     *
     * @param total
     */
    public void setTotal(double total) {
		this.total = total;
	}
}
