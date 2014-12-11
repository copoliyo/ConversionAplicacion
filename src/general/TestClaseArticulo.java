package general;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JOptionPane;

import com.mysql.jdbc.ResultSetMetaData;

import tablas.Acceso;
import tablas.Articulo;
import util.BaseDatos;

public class TestClaseArticulo {
	
	static ResultSet rs = null;
	static MysqlConnect m = null;
	
	TestClaseArticulo() {
		m = MysqlConnect.getDbCon();
	}
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Articulo art;
		TestClaseArticulo tca = new TestClaseArticulo();
		
		new DatosComunes("MV");
		
		// Buscamos un articulo

		try {
			String sqlQuery = "SELECT * FROM ARTCLO WHERE EMPRESA = '" + 
		      				  DatosComunes.eEmpresa + 
		      				  "' AND ARTCLO_ARTICULO like 'E%'";
			
			
			System.out.println("Número de lineas : " + BaseDatos.countRows(sqlQuery));
			
			rs = m.query(sqlQuery);
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de Articulos!!!");
		}

		try {
			if (rs.next()){
				art = new Articulo(rs);
				System.out.println("Articulo : " + art.getArticulo());
				JOptionPane.showMessageDialog(null,
				"Esperando...");
				rs.close();
				JOptionPane.showMessageDialog(null,
				"Esperando...2");
				m.conn.close();
				JOptionPane.showMessageDialog(null,
				"Esperando...3");
			}else {
				System.out.println("No existe el articulo");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error leyendo Articulo!!!");
		}finally{
			rs.close();
			m.conn.close();
		}
	
		
	
	}

}
