package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;


public class ArticuloCentro {
	private String empresa;
	private int centro;
	private String articulo;
	private String ubicacion;
	private String indiceNivel;
	private double stock;
	private double valorExistencias;
	private double precioCoste;
	private double stockInicioEjercicio;
	private double valorInicioEjercicio;
	private double pendienteRecibir;
	private double asignadoServir;
	private double disp72h;
	private double stockMaximo;
	private double stockMinimo;
	private int roturaBajoMinimo;
	private int roturasStockAnual;
	private int fechaUltimaCompra;
	private double cantidadUltimaCompra;
	private double precioUltimaCompra;
	private double precioAnteriorCompra;

	public ArticuloCentro(){
		empresa = DatosComunes.eEmpresa;
		centro = DatosComunes.centroGest;
		articulo = "";
		ubicacion = "";
		indiceNivel = "";
		stock = 0.0;
		valorExistencias = 0.0;
		precioCoste = 0.0;
		stockInicioEjercicio = 0.0;
		valorInicioEjercicio = 0.0;
		pendienteRecibir = 0.0;
		asignadoServir = 0.0;
		disp72h = 0.0;
		stockMaximo = 0.0;
		stockMinimo = 0.0;
		roturaBajoMinimo = 0;
		roturasStockAnual = 0;
		fechaUltimaCompra = 0;
		cantidadUltimaCompra = 0.0;
		precioUltimaCompra = 0.0;
		precioAnteriorCompra = 0.0;
	}

	public ArticuloCentro(ResultSet rs){
		try{
			if(rs.next() == true){			
				empresa = rs.getString("EMPRESA").trim();
				centro = rs.getInt("ARTCEN_CENTRO");
				articulo = rs.getString("ARTCEN_ARTICULO").trim();
				ubicacion = rs.getString("ARTCEN_UBICACION").trim();
				indiceNivel = rs.getString("ARTCEN_IND_NIVEL").trim();
				stock = rs.getDouble("ARTCEN_STOCK");
				valorExistencias = rs.getDouble("ARTCEN_VAL_EXIS");
				precioCoste = rs.getDouble("ARTCEN_PRE_COSTE");
				stockInicioEjercicio = rs.getDouble("ARTCEN_STK_INICEJER");
				valorInicioEjercicio = rs.getDouble("ARTCEN_VAL_INICEJER");
				pendienteRecibir = rs.getDouble("ARTCEN_PEND_RECIBIR");
				asignadoServir = rs.getDouble("ARTCEN_ASIG_SERVIR");
				disp72h = rs.getDouble("ARTCEN_DISP_72H");
				stockMaximo = rs.getDouble("ARTCEN_ST_MINIMO");
				stockMinimo = rs.getDouble("ARTCEN_ST_MAXIMO");
				roturaBajoMinimo = rs.getInt("ARTCEN_ROT_BAJMIN");
				roturasStockAnual = rs.getInt("ARTCEN_ROT_ST_ANY");
				fechaUltimaCompra = rs.getInt("ARTCEN_FEC_ULT_COM");
				cantidadUltimaCompra = rs.getDouble("ARTCEN_CAN_ULT_COM");
				precioUltimaCompra =rs.getDouble("ARTCEN_PRE_ULT_COM");
				precioAnteriorCompra = rs.getDouble("ARTCEN_PRE_ANT_COM");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de ArticuloCentro!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{			
			empresa = rs.getString("EMPRESA").trim();
			centro = rs.getInt("ARTCEN_CENTRO");
			articulo = rs.getString("ARTCEN_ARTICULO").trim();
			ubicacion = rs.getString("ARTCEN_UBICACION").trim();
			indiceNivel = rs.getString("ARTCEN_IND_NIVEL").trim();
			stock = rs.getDouble("ARTCEN_STOCK");
			valorExistencias = rs.getDouble("ARTCEN_VAL_EXIS");
			precioCoste = rs.getDouble("ARTCEN_PRE_COSTE");
			stockInicioEjercicio = rs.getDouble("ARTCEN_STK_INICEJER");
			valorInicioEjercicio = rs.getDouble("ARTCEN_VAL_INICEJER");
			pendienteRecibir = rs.getDouble("ARTCEN_PEND_RECIBIR");
			asignadoServir = rs.getDouble("ARTCEN_ASIG_SERVIR");
			disp72h = rs.getDouble("ARTCEN_DISP_72H");
			stockMaximo = rs.getDouble("ARTCEN_ST_MINIMO");
			stockMinimo = rs.getDouble("ARTCEN_ST_MAXIMO");
			roturaBajoMinimo = rs.getInt("ARTCEN_ROT_BAJMIN");
			roturasStockAnual = rs.getInt("ARTCEN_ROT_ST_ANY");
			fechaUltimaCompra = rs.getInt("ARTCEN_FEC_ULT_COM");
			cantidadUltimaCompra = rs.getDouble("ARTCEN_CAN_ULT_COM");
			precioUltimaCompra =rs.getDouble("ARTCEN_PRE_ULT_COM");
			precioAnteriorCompra = rs.getDouble("ARTCEN_PRE_ANT_COM");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de ArticuloCentro!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO ARTCEN " +
						   "(EMPRESA, " +
						   "ARTCEN_CENTRO, " +
						   "ARTCEN_ARTICULO, " +
						   "ARTCEN_UBICACION, " +
						   "ARTCEN_IND_NIVEL, " +
						   "ARTCEN_STOCK, " +
						   "ARTCEN_VAL_EXIS, " +
						   "ARTCEN_PRE_COSTE, " +
						   "ARTCEN_STK_INICEJER, " +
						   "ARTCEN_VAL_INICEJER, " +
						   "ARTCEN_PEND_RECIBIR, " +
						   "ARTCEN_ASIG_SERVIR, " +
						   "ARTCEN_DISP_72H, " +
						   "ARTCEN_ST_MINIMO, " +
						   "ARTCEN_ST_MAXIMO, " +
						   "ARTCEN_ROT_BAJMIN, " +
						   "ARTCEN_ROT_ST_ANY, " +
						   "ARTCEN_FEC_ULT_COM, " +
						   "ARTCEN_CAN_ULT_COM, " +
						   "ARTCEN_PRE_ULT_COM, " +
						   "ARTCEN_PRE_ANT_COM) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "ARTCEN_CENTRO = ?, " +
						   "ARTCEN_ARTICULO = ?, " +
						   "ARTCEN_UBICACION = ?, " +
						   "ARTCEN_IND_NIVEL = ?, " +
						   "ARTCEN_STOCK = ?, " +
						   "ARTCEN_VAL_EXIS = ?, " +
						   "ARTCEN_PRE_COSTE = ?, " +
						   "ARTCEN_STK_INICEJER = ?, " +
						   "ARTCEN_VAL_INICEJER = ?, " +
						   "ARTCEN_PEND_RECIBIR = ?, " +
						   "ARTCEN_ASIG_SERVIR = ?, " +
						   "ARTCEN_DISP_72H = ?, " +
						   "ARTCEN_ST_MINIMO = ?, " +
						   "ARTCEN_ST_MAXIMO = ?, " +
						   "ARTCEN_ROT_BAJMIN = ?, " +
						   "ARTCEN_ROT_ST_ANY = ?, " +
						   "ARTCEN_FEC_ULT_COM = ?, " +
						   "ARTCEN_CAN_ULT_COM = ?, " +
						   "ARTCEN_PRE_ULT_COM = ?, " +
						   "ARTCEN_PRE_ANT_COM = ?";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			// Insert
			ps.setString(1, Cadena.left(empresa, 2));
			ps.setInt(2, centro);
			ps.setString(3, Cadena.left(articulo, 13));
			ps.setString(4, Cadena.left(ubicacion, 10));
			ps.setString(5, Cadena.left(indiceNivel, 1));
			ps.setDouble(6, stock);
			ps.setDouble(7, valorExistencias);
			ps.setDouble(8, precioCoste);
			ps.setDouble(9, stockInicioEjercicio);
			ps.setDouble(10, valorInicioEjercicio);
			ps.setDouble(11, pendienteRecibir);
			ps.setDouble(12, asignadoServir);
			ps.setDouble(13, disp72h);
			ps.setDouble(14, stockMinimo);
			ps.setDouble(15, stockMaximo);
			ps.setInt(16, roturaBajoMinimo);
			ps.setInt(17, roturasStockAnual);
			ps.setInt(18, fechaUltimaCompra);
			ps.setDouble(19, cantidadUltimaCompra);
			ps.setDouble(20, precioUltimaCompra);
			ps.setDouble(21, precioAnteriorCompra);
			// Update
			ps.setString(22, Cadena.left(empresa, 2));
			ps.setInt(23, centro);
			ps.setString(24, Cadena.left(articulo, 13));
			ps.setString(25, Cadena.left(ubicacion, 10));
			ps.setString(26, Cadena.left(indiceNivel, 1));
			ps.setDouble(27, stock);
			ps.setDouble(28, valorExistencias);
			ps.setDouble(29, precioCoste);
			ps.setDouble(30, stockInicioEjercicio);
			ps.setDouble(31, valorInicioEjercicio);
			ps.setDouble(32, pendienteRecibir);
			ps.setDouble(33, asignadoServir);
			ps.setDouble(34, disp72h);
			ps.setDouble(35, stockMinimo);
			ps.setDouble(36, stockMaximo);
			ps.setInt(37, roturaBajoMinimo);
			ps.setInt(38, roturasStockAnual);
			ps.setInt(39, fechaUltimaCompra);
			ps.setDouble(40, cantidadUltimaCompra);
			ps.setDouble(41, precioUltimaCompra);
			ps.setDouble(42, precioAnteriorCompra);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de ArticuloCentro!!!");
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

	public int getCentro() {
		return centro;
	}

	public void setCentro(int centro) {
		this.centro = centro;
	}

	public String getArticulo() {
		return articulo;
	}

	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getIndiceNivel() {
		return indiceNivel;
	}

	public void setIndiceNivel(String indiceNivel) {
		this.indiceNivel = indiceNivel;
	}

	public double getStock() {
		return stock;
	}

	public void setStock(double stock) {
		this.stock = stock;
	}

	public double getValorExistencias() {
		return valorExistencias;
	}

	public void setValorExistencias(double valorExistencias) {
		this.valorExistencias = valorExistencias;
	}

	public double getPrecioCoste() {
		return precioCoste;
	}

	public void setPrecioCoste(double precioCoste) {
		this.precioCoste = precioCoste;
	}

	public double getStockInicioEjercicio() {
		return stockInicioEjercicio;
	}

	public void setStockInicioEjercicio(double stockInicioEjercicio) {
		this.stockInicioEjercicio = stockInicioEjercicio;
	}

	public double getValorInicioEjercicio() {
		return valorInicioEjercicio;
	}

	public void setValorInicioEjercicio(double valorInicioEjercicio) {
		this.valorInicioEjercicio = valorInicioEjercicio;
	}

	public double getPendienteRecibir() {
		return pendienteRecibir;
	}

	public void setPendienteRecibir(double pendienteRecibir) {
		this.pendienteRecibir = pendienteRecibir;
	}

	public double getAsignadoServir() {
		return asignadoServir;
	}

	public void setAsignadoServir(double asignadoServir) {
		this.asignadoServir = asignadoServir;
	}

	public double getDisp72h() {
		return disp72h;
	}

	public void setDisp72h(double disp72h) {
		this.disp72h = disp72h;
	}

	public double getStockMaximo() {
		return stockMaximo;
	}

	public void setStockMaximo(double stockMaximo) {
		this.stockMaximo = stockMaximo;
	}

	public double getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(double stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public int getRoturaBajoMinimo() {
		return roturaBajoMinimo;
	}

	public void setRoturaBajoMinimo(int roturaBajoMinimo) {
		this.roturaBajoMinimo = roturaBajoMinimo;
	}

	public int getRoturasStockAnual() {
		return roturasStockAnual;
	}

	public void setRoturasStockAnual(int roturasStockAnual) {
		this.roturasStockAnual = roturasStockAnual;
	}

	public int getFechaUltimaCompra() {
		return fechaUltimaCompra;
	}

	public void setFechaUltimaCompra(int fechaUltimaCompra) {
		this.fechaUltimaCompra = fechaUltimaCompra;
	}

	public double getCantidadUltimaCompra() {
		return cantidadUltimaCompra;
	}

	public void setCantidadUltimaCompra(double cantidadUltimaCompra) {
		this.cantidadUltimaCompra = cantidadUltimaCompra;
	}

	public double getPrecioUltimaCompra() {
		return precioUltimaCompra;
	}

	public void setPrecioUltimaCompra(double precioUltimaCompra) {
		this.precioUltimaCompra = precioUltimaCompra;
	}

	public double getPrecioAnteriorCompra() {
		return precioAnteriorCompra;
	}

	public void setPrecioAnteriorCompra(double precioAnteriorCompra) {
		this.precioAnteriorCompra = precioAnteriorCompra;
	}
}
