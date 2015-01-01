package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;

import com.mysql.jdbc.ResultSet;


public class Oferta {
	private String empresa;
	private int clienteDeFecha;
	private int clienteAFecha;
	private String marca;
	private int gama;
	private int familia;
	private String articulo;
	private int clienteTipoOferta;
	private double clientePrecioDescuento;
	private String clienteArticuloRegalo;
	private int clienteCantidadMinima;
	private int clienteCantidadRegalo;
	private String clienteObservaciones;
	private int clienteTipo[];
	private int clienteZona[];
	private int clienteTarifaCliente[];
	private int publicoTipoOferta;
	private double publicoPrecioDescuento;
	private String publicoArticuloRegalo;
	private int publicoCantidadMinima;
	private int publicoCantidadRegalo;
	private String publicoObservaciones;
	private int proveedorDeFecha;
	private int proveedorAFecha;
	private int proveedorTipoOferta;
	private double proveedorPrecioDescuento;
	private String proveedorArticuloRegalo;
	private int proveedorCantidadMinima;
	private int proveedorCantidadRegalo;
	private String proveedorObservaciones;
	private int superoferta;
	private int propia;

	public Oferta(){
		empresa = DatosComunes.eEmpresa;
		clienteDeFecha = 0;
		clienteAFecha = 0;
		marca = "";
		gama = 0;
		familia = 0;
		articulo = "";
		clienteTipoOferta = 0;
		clientePrecioDescuento = 0.0;
		clienteArticuloRegalo = "";
		clienteCantidadMinima = 0;
		clienteCantidadRegalo = 0;
		clienteObservaciones = "";
		clienteTipo = new int[3];
		clienteZona = new int[3];
		clienteTarifaCliente = new int[3];
		for(int i = 0; i < 3; i++){
			clienteTipo[i] = 0;
			clienteZona[i] = 0;
			clienteTarifaCliente[i] = 0;
		}
		publicoTipoOferta = 0;
		publicoPrecioDescuento = 0.0;
		publicoArticuloRegalo = "";
		publicoCantidadMinima = 0;
		publicoCantidadRegalo = 0;
		publicoObservaciones = "";
		proveedorDeFecha = 0;
		proveedorAFecha = 0;
		proveedorTipoOferta = 0;
		proveedorPrecioDescuento = 0.0;
		proveedorArticuloRegalo = "";
		proveedorCantidadMinima = 0;
		proveedorCantidadRegalo = 0;
		proveedorObservaciones = "";
		superoferta = 0;
		propia = 0;
	}

	public Oferta(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				clienteDeFecha = rs.getInt("OFERTA_CLTE_DFEC");
				clienteAFecha = rs.getInt("OFERTA_CLTE_AFEC");
				marca = rs.getString("OFERTA_MARCA").trim();
				gama = rs.getInt("OFERTA_GAMA");
				familia = rs.getInt("OFERTA_FAMILIA");
				articulo = rs.getString("OFERTA_ARTICULO").trim();
				clienteTipoOferta = rs.getInt("OFERTA_CLTE_TIPO_OFERTA");
				clientePrecioDescuento = rs.getDouble("OFERTA_CLTE_PRECDTO");
				clienteArticuloRegalo = rs.getString("OFERTA_CLTE_ARTIC_REGALO").trim();
				clienteCantidadMinima = rs.getInt("OFERTA_CLTE_CANT_MINIMA");
				clienteCantidadRegalo = rs.getInt("OFERTA_CLTE_CANT_REGALO");
				clienteObservaciones = rs.getString("OFERTA_CLTE_OBSERVAC");

				clienteTipo = new int[3];
				clienteZona = new int[3];
				clienteTarifaCliente = new int[3];

				clienteTipo[0] = rs.getInt("OFERTA_CLTE_TIPO_1");
				clienteTipo[1] = rs.getInt("OFERTA_CLTE_TIPO_2");
				clienteTipo[2] = rs.getInt("OFERTA_CLTE_TIPO_3");
				clienteZona[0] = rs.getInt("OFERTA_CLTE_ZONA_1");
				clienteZona[1] = rs.getInt("OFERTA_CLTE_ZONA_2");
				clienteZona[2] = rs.getInt("OFERTA_CLTE_ZONA_3");
				clienteTarifaCliente[0] = rs.getInt("OFERTA_CLTE_TARIFA_CLTE_1");
				clienteTarifaCliente[1] = rs.getInt("OFERTA_CLTE_TARIFA_CLTE_2");
				clienteTarifaCliente[2] = rs.getInt("OFERTA_CLTE_TARIFA_CLTE_3");

				publicoTipoOferta = rs.getInt("OFERTA_PUBL_TIPO_OFERTA");
				publicoPrecioDescuento = rs.getDouble("OFERTA_PUBL_PRECDTO");
				publicoArticuloRegalo = rs.getString("OFERTA_PUBL_ARTIC_REGALO");
				publicoCantidadMinima = rs.getInt("OFERTA_PUBL_CANT_MINIMA");
				publicoCantidadRegalo = rs.getInt("OFERTA_PUBL_CANT_REGALO");
				publicoObservaciones = rs.getString("OFERTA_PUBL_OBSERVAC").trim();
				proveedorDeFecha = rs.getInt("OFERTA_PROV_DFEC");
				proveedorAFecha = rs.getInt("OFERTA_PROV_AFEC");
				proveedorTipoOferta = rs.getInt("OFERTA_PROV_TIPO_OFERTA");
				proveedorPrecioDescuento = rs.getDouble("OFERTA_PROV_PRECDTO");
				proveedorArticuloRegalo = rs.getString("OFERTA_PROV_ARTIC_REGALO").trim();
				proveedorCantidadMinima = rs.getInt("OFERTA_PROV_CANT_MINIMA");
				proveedorCantidadRegalo = rs.getInt("OFERTA_PROV_CANT_REGALO");
				proveedorObservaciones = rs.getString("OFERTA_PROV_OBSERVAC").trim();
				superoferta = rs.getInt("OFERTA_SUPEROFERTA");
				propia = rs.getInt("OFERTA_PROPIA");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de Oferta!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{				
			empresa = rs.getString("EMPRESA").trim();
			clienteDeFecha = rs.getInt("OFERTA_CLTE_DFEC");
			clienteAFecha = rs.getInt("OFERTA_CLTE_AFEC");
			marca = rs.getString("OFERTA_MARCA").trim();
			gama = rs.getInt("OFERTA_GAMA");
			familia = rs.getInt("OFERTA_FAMILIA");
			articulo = rs.getString("OFERTA_ARTICULO").trim();
			clienteTipoOferta = rs.getInt("OFERTA_CLTE_TIPO_OFERTA");
			clientePrecioDescuento = rs.getDouble("OFERTA_CLTE_PRECDTO");
			clienteArticuloRegalo = rs.getString("OFERTA_CLTE_ARTIC_REGALO").trim();
			clienteCantidadMinima = rs.getInt("OFERTA_CLTE_CANT_MINIMA");
			clienteCantidadRegalo = rs.getInt("OFERTA_CLTE_CANT_REGALO");
			clienteObservaciones = rs.getString("OFERTA_CLTE_OBSERVAC").trim();

			clienteTipo[0] = rs.getInt("OFERTA_CLTE_TIPO_1");
			clienteTipo[1] = rs.getInt("OFERTA_CLTE_TIPO_2");
			clienteTipo[2] = rs.getInt("OFERTA_CLTE_TIPO_3");
			clienteZona[0] = rs.getInt("OFERTA_CLTE_ZONA_1");
			clienteZona[1] = rs.getInt("OFERTA_CLTE_ZONA_2");
			clienteZona[2] = rs.getInt("OFERTA_CLTE_ZONA_3");
			clienteTarifaCliente[0] = rs.getInt("OFERTA_CLTE_TARIFA_CLTE_1");
			clienteTarifaCliente[1] = rs.getInt("OFERTA_CLTE_TARIFA_CLTE_2");
			clienteTarifaCliente[2] = rs.getInt("OFERTA_CLTE_TARIFA_CLTE_3");

			publicoTipoOferta = rs.getInt("OFERTA_PUBL_TIPO_OFERTA");
			publicoPrecioDescuento = rs.getDouble("OFERTA_PUBL_PRECDTO");
			publicoArticuloRegalo = rs.getString("OFERTA_PUBL_ARTIC_REGALO").trim();
			publicoCantidadMinima = rs.getInt("OFERTA_PUBL_CANT_MINIMA");
			publicoCantidadRegalo = rs.getInt("OFERTA_PUBL_CANT_REGALO");
			publicoObservaciones = rs.getString("OFERTA_PUBL_OBSERVAC").trim();
			proveedorDeFecha = rs.getInt("OFERTA_PROV_DFEC");
			proveedorAFecha = rs.getInt("OFERTA_PROV_AFEC");
			proveedorTipoOferta = rs.getInt("OFERTA_PROV_TIPO_OFERTA");
			proveedorPrecioDescuento = rs.getDouble("OFERTA_PROV_PRECDTO");
			proveedorArticuloRegalo = rs.getString("OFERTA_PROV_ARTIC_REGALO").trim();
			proveedorCantidadMinima = rs.getInt("OFERTA_PROV_CANT_MINIMA");
			proveedorCantidadRegalo = rs.getInt("OFERTA_PROV_CANT_REGALO");
			proveedorObservaciones = rs.getString("OFERTA_PROV_OBSERVAC").trim();
			superoferta = rs.getInt("OFERTA_SUPEROFERTA");
			propia = rs.getInt("OFERTA_PROPIA");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de Oferta!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}
	
	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO OFERTA " +
						   "(EMPRESA, " +
						   "OFERTA_CLTE_DFEC, " +
						   "OFERTA_CLTE_AFEC, " +
						   "OFERTA_MARCA, " +
						   "OFERTA_GAMA, " +
						   "OFERTA_FAMILIA, " +
						   "OFERTA_ARTICULO, " +
						   "OFERTA_CLTE_TIPO_OFERTA, " +
						   "OFERTA_CLTE_PRECDTO, " +
						   "OFERTA_CLTE_ARTIC_REGALO, " +
						   "OFERTA_CLTE_CANT_MINIMA, " +
						   "OFERTA_CLTE_CANT_REGALO, " +
						   "OFERTA_CLTE_OBSERVAC, " +
						   "OFERTA_CLTE_TIPO_1, " +
						   "OFERTA_CLTE_TIPO_2, " +
						   "OFERTA_CLTE_TIPO_3, " +
						   "OFERTA_CLTE_ZONA_1, " +
						   "OFERTA_CLTE_ZONA_2, " +
						   "OFERTA_CLTE_ZONA_3, " +
						   "OFERTA_CLTE_TARIFA_CLTE_1, " +
						   "OFERTA_CLTE_TARIFA_CLTE_2, " +
						   "OFERTA_CLTE_TARIFA_CLTE_3, " +
						   "OFERTA_PUBL_TIPO_OFERTA, " +
						   "OFERTA_PUBL_PRECDTO, " +
						   "OFERTA_PUBL_ARTIC_REGALO, " +
						   "OFERTA_PUBL_CANT_MINIMA, " +
						   "OFERTA_PUBL_CANT_REGALO, " +
						   "OFERTA_PUBL_OBSERVAC, " +
						   "OFERTA_PROV_DFEC, " +
						   "OFERTA_PROV_AFEC, " +
						   "OFERTA_PROV_TIPO_OFERTA, " +
						   "OFERTA_PROV_PRECDTO, " +
						   "OFERTA_PROV_ARTIC_REGALO, " +
						   "OFERTA_PROV_CANT_MINIMA, " +
						   "OFERTA_PROV_CANT_REGALO, " +
						   "OFERTA_PROV_OBSERVAC, " +
						   "OFERTA_SUPEROFERTA, " +						   
						   "OFERTA_PROPIA) " +						   
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?) " +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "OFERTA_CLTE_DFEC = ?, " +
						   "OFERTA_CLTE_AFEC = ?, " +
						   "OFERTA_MARCA = ?, " +
						   "OFERTA_GAMA = ?, " +
						   "OFERTA_FAMILIA = ?, " +
						   "OFERTA_ARTICULO = ?, " +
						   "OFERTA_CLTE_TIPO_OFERTA = ?, " +
						   "OFERTA_CLTE_PRECDTO = ?, " +
						   "OFERTA_CLTE_ARTIC_REGALO = ?, " +
						   "OFERTA_CLTE_CANT_MINIMA = ?, " +
						   "OFERTA_CLTE_CANT_REGALO = ?, " +
						   "OFERTA_CLTE_OBSERVAC = ?, " +
						   "OFERTA_CLTE_TIPO_1 = ?, " +
						   "OFERTA_CLTE_TIPO_2 = ?, " +
						   "OFERTA_CLTE_TIPO_3 = ?, " +
						   "OFERTA_CLTE_ZONA_1 = ?, " +
						   "OFERTA_CLTE_ZONA_2 = ?, " +
						   "OFERTA_CLTE_ZONA_3 = ?, " +
						   "OFERTA_CLTE_TARIFA_CLTE_1 = ?, " +
						   "OFERTA_CLTE_TARIFA_CLTE_2 = ?, " +
						   "OFERTA_CLTE_TARIFA_CLTE_3 = ?, " +
						   "OFERTA_PUBL_TIPO_OFERTA = ?, " +
						   "OFERTA_PUBL_PRECDTO = ?, " +
						   "OFERTA_PUBL_ARTIC_REGALO = ?, " +
						   "OFERTA_PUBL_CANT_MINIMA = ?, " +
						   "OFERTA_PUBL_CANT_REGALO = ?, " +
						   "OFERTA_PUBL_OBSERVAC = ?, " +
						   "OFERTA_PROV_DFEC = ?, " +
						   "OFERTA_PROV_AFEC = ?, " +
						   "OFERTA_PROV_TIPO_OFERTA = ?, " +
						   "OFERTA_PROV_PRECDTO = ?, " +
						   "OFERTA_PROV_ARTIC_REGALO = ?, " +
						   "OFERTA_PROV_CANT_MINIMA = ?, " +
						   "OFERTA_PROV_CANT_REGALO = ?, " +
						   "OFERTA_PROV_OBSERVAC = ?, " +
						   "OFERTA_SUPEROFERTA = ?, " +						   
						   "OFERTA_PROPIA = ? ";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			int i = 1;
			// Insert
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, clienteDeFecha);
			ps.setInt(i++, clienteAFecha);
			ps.setString(i++, Cadena.left(marca, 3));
			ps.setInt(i++, gama);
			ps.setInt(i++, familia);
			ps.setString(i++, Cadena.left(articulo, 13));
			ps.setInt(i++, clienteTipoOferta);
			ps.setDouble(i++, clientePrecioDescuento);
			ps.setString(i++, Cadena.left(clienteArticuloRegalo, 13));
			ps.setInt(i++, clienteCantidadMinima);
			ps.setInt(i++, clienteCantidadRegalo);
			ps.setString(i++, Cadena.left(clienteObservaciones, 30));
			for(int j = 0; j < 3; j++)				
				ps.setInt(i++, clienteTipo[j]);
			for(int j = 0; j < 3; j++)
				ps.setInt(i++, clienteZona[j]);
			for(int j = 0; j < 3; j++)
				ps.setInt(i++, clienteTarifaCliente[j]);
			ps.setInt(i++, publicoTipoOferta);
			ps.setDouble(i++, publicoPrecioDescuento);
			ps.setString(i++, Cadena.left(publicoArticuloRegalo, 13));
			ps.setInt(i++, publicoCantidadMinima);
			ps.setInt(i++, publicoCantidadRegalo);
			ps.setString(i++, Cadena.left(publicoObservaciones, 30));
			ps.setInt(i++, proveedorDeFecha);
			ps.setInt(i++, proveedorAFecha);
			ps.setInt(i++, proveedorTipoOferta);
			ps.setDouble(i++, proveedorPrecioDescuento);
			ps.setString(i++, Cadena.left(proveedorArticuloRegalo, 13));
			ps.setInt(i++, proveedorCantidadMinima);
			ps.setInt(i++, proveedorCantidadRegalo);
			ps.setString(i++, Cadena.left(proveedorObservaciones, 30));
			ps.setInt(i++, superoferta);
			ps.setInt(i++, propia);
			
			// Update
			ps.setString(i++, Cadena.left(empresa, 2));
			ps.setInt(i++, clienteDeFecha);
			ps.setInt(i++, clienteAFecha);
			ps.setString(i++, Cadena.left(marca, 3));
			ps.setInt(i++, gama);
			ps.setInt(i++, familia);
			ps.setString(i++, Cadena.left(articulo, 13));
			ps.setInt(i++, clienteTipoOferta);
			ps.setDouble(i++, clientePrecioDescuento);
			ps.setString(i++, Cadena.left(clienteArticuloRegalo, 13));
			ps.setInt(i++, clienteCantidadMinima);
			ps.setInt(i++, clienteCantidadRegalo);
			ps.setString(i++, Cadena.left(clienteObservaciones, 30));
			for(int j = 0; j < 3; j++)				
				ps.setInt(i++, clienteTipo[j]);
			for(int j = 0; j < 3; j++)
				ps.setInt(i++, clienteZona[j]);
			for(int j = 0; j < 3; j++)
				ps.setInt(i++, clienteTarifaCliente[j]);
			ps.setInt(i++, publicoTipoOferta);
			ps.setDouble(i++, publicoPrecioDescuento);
			ps.setString(i++, Cadena.left(publicoArticuloRegalo, 13));
			ps.setInt(i++, publicoCantidadMinima);
			ps.setInt(i++, publicoCantidadRegalo);
			ps.setString(i++, Cadena.left(publicoObservaciones, 30));
			ps.setInt(i++, proveedorDeFecha);
			ps.setInt(i++, proveedorAFecha);
			ps.setInt(i++, proveedorTipoOferta);
			ps.setDouble(i++, proveedorPrecioDescuento);
			ps.setString(i++, Cadena.left(proveedorArticuloRegalo, 13));
			ps.setInt(i++, proveedorCantidadMinima);
			ps.setInt(i++, proveedorCantidadRegalo);
			ps.setString(i++, Cadena.left(proveedorObservaciones, 30));
			ps.setInt(i++, superoferta);
			ps.setInt(i++, propia);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de Oferta!!!");
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

	public int getClienteDeFecha() {
		return clienteDeFecha;
	}

	public void setClienteDeFecha(int clienteDeFecha) {
		this.clienteDeFecha = clienteDeFecha;
	}

	public int getClienteAFecha() {
		return clienteAFecha;
	}

	public void setClienteAFecha(int clienteAFecha) {
		this.clienteAFecha = clienteAFecha;
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

	public int getClienteTipoOferta() {
		return clienteTipoOferta;
	}

	public void setClienteTipoOferta(int clienteTipoOferta) {
		this.clienteTipoOferta = clienteTipoOferta;
	}

	public double getClientePrecioDescuento() {
		return clientePrecioDescuento;
	}

	public void setClientePrecioDescuento(double clientePrecioDescuento) {
		this.clientePrecioDescuento = clientePrecioDescuento;
	}

	public String getClienteArticuloRegalo() {
		return clienteArticuloRegalo;
	}

	public void setClienteArticuloRegalo(String clienteArticuloRegalo) {
		this.clienteArticuloRegalo = clienteArticuloRegalo;
	}

	public int getClienteCantidadMinima() {
		return clienteCantidadMinima;
	}

	public void setClienteCantidadMinima(int clienteCantidadMinima) {
		this.clienteCantidadMinima = clienteCantidadMinima;
	}

	public int getClienteCantidadRegalo() {
		return clienteCantidadRegalo;
	}

	public void setClienteCantidadRegalo(int clienteCantidadRegalo) {
		this.clienteCantidadRegalo = clienteCantidadRegalo;
	}

	public String getClienteObservaciones() {
		return clienteObservaciones;
	}

	public void setClienteObservaciones(String clienteObservaciones) {
		this.clienteObservaciones = clienteObservaciones;
	}

	public int getClienteTipo(int i) {
		return clienteTipo[i];
	}
	
	public int[] getClienteTipo() {
		return clienteTipo;
	}

	public void setClienteTipo(int indice, int valor) {
		this.clienteTipo[indice] = valor;
	}
	
	public void setClienteTipo(int[] clienteTipo) {
		this.clienteTipo = clienteTipo;
	}

	public int getClienteZona(int i) {
		return clienteZona[i];
	}
	
	public int[] getClienteZona() {
		return clienteZona;
	}

	public void setClienteZona(int indice, int valor) {
		this.clienteZona[indice] = valor;
	}
	
	public void setClienteZona(int[] clienteZona) {
		this.clienteZona = clienteZona;
	}

	public int getClienteTarifaCliente(int i) {
		return clienteTarifaCliente[i];
	}
	
	public int[] getClienteTarifaCliente() {
		return clienteTarifaCliente;
	}

	public void setClienteTarifaCliente(int indice, int valor) {
		this.clienteTarifaCliente[indice] = valor;
	}
	
	public void setClienteTarifaCliente(int[] clienteTarifaCliente) {
		this.clienteTarifaCliente = clienteTarifaCliente;
	}

	public int getPublicoTipoOferta() {
		return publicoTipoOferta;
	}

	public void setPublicoTipoOferta(int publicoTipoOferta) {
		this.publicoTipoOferta = publicoTipoOferta;
	}

	public double getPublicoPrecioDescuento() {
		return publicoPrecioDescuento;
	}

	public void setPublicoPrecioDescuento(double publicoPrecioDescuento) {
		this.publicoPrecioDescuento = publicoPrecioDescuento;
	}

	public String getPublicoArticuloRegalo() {
		return publicoArticuloRegalo;
	}

	public void setPublicoArticuloRegalo(String publicoArticuloRegalo) {
		this.publicoArticuloRegalo = publicoArticuloRegalo;
	}

	public int getPublicoCantidadMinima() {
		return publicoCantidadMinima;
	}

	public void setPublicoCantidadMinima(int publicoCantidadMinima) {
		this.publicoCantidadMinima = publicoCantidadMinima;
	}

	public int getPublicoCantidadregalo() {
		return publicoCantidadRegalo;
	}

	public void setPublicoCantidadregalo(int publicoCantidadRegalo) {
		this.publicoCantidadRegalo = publicoCantidadRegalo;
	}

	public String getPublicoObservaciones() {
		return publicoObservaciones;
	}

	public void setPublicoObservaciones(String publicoObservaciones) {
		this.publicoObservaciones = publicoObservaciones;
	}

	public int getProveedorDeFecha() {
		return proveedorDeFecha;
	}

	public void setProveedorDeFecha(int proveedorDeFecha) {
		this.proveedorDeFecha = proveedorDeFecha;
	}

	public int getProveedorAFecha() {
		return proveedorAFecha;
	}

	public void setProveedorAFecha(int proveedorAFecha) {
		this.proveedorAFecha = proveedorAFecha;
	}

	public int getProveedorTipoOferta() {
		return proveedorTipoOferta;
	}

	public void setProveedorTipoOferta(int proveedorTipoOferta) {
		this.proveedorTipoOferta = proveedorTipoOferta;
	}

	public double getProveedorPrecioDescuento() {
		return proveedorPrecioDescuento;
	}

	public void setProveedorPrecioDescuento(double proveedorPrecioDescuento) {
		this.proveedorPrecioDescuento = proveedorPrecioDescuento;
	}

	public String getProveedorArticuloRegalo() {
		return proveedorArticuloRegalo;
	}

	public void setProveedorArticuloRegalo(String proveedorArticuloRegalo) {
		this.proveedorArticuloRegalo = proveedorArticuloRegalo;
	}

	public int getProveedorCantidadMinima() {
		return proveedorCantidadMinima;
	}

	public void setProveedorCantidadMinima(int proveedorCantidadMinima) {
		this.proveedorCantidadMinima = proveedorCantidadMinima;
	}

	public int getProveedorCantidadRegalo() {
		return proveedorCantidadRegalo;
	}

	public void setProveedorCantidadRegalo(int proveedorCantidadRegalo) {
		this.proveedorCantidadRegalo = proveedorCantidadRegalo;
	}

	public String getProveedorObservaciones() {
		return proveedorObservaciones;
	}

	public void setProveedorObservaciones(String proveedorObservaciones) {
		this.proveedorObservaciones = proveedorObservaciones;
	}

	public int getSuperoferta() {
		return superoferta;
	}

	public void setSuperoferta(int superoferta) {
		this.superoferta = superoferta;
	}

	public int getPropia() {
		return propia;
	}

	public void setPropia(int propia) {
		this.propia = propia;
	}
}
