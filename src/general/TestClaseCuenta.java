package general;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JOptionPane;

import com.mysql.jdbc.ResultSetMetaData;

import tablas.Acceso;
import tablas.Cuenta;
import util.BaseDatos;

public class TestClaseCuenta {
	
	static ResultSet rs = null;
	static MysqlConnect m = null;
	
	TestClaseCuenta() {
		m = MysqlConnect.getDbCon();
	}
	
	public static void main(String[] args) throws SQLException {
		Cuenta c;
		TestClaseCuenta tcc = new TestClaseCuenta();
		
		new DatosComunes("MV");
		
		// Buscamos un articulo

		try {
			String sqlQuery = "SELECT * FROM contab WHERE EMPRESA = '" + 
		      				  DatosComunes.eEmpresa + 
		      				  "' AND contab_cuenta = '111'";
			
			
			System.out.println("Número de lineas : " + BaseDatos.countRows(sqlQuery));
			
			rs = m.query(sqlQuery);
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de Cuentas!!!");
		}

		c = new Cuenta();
		
		try {
			while(rs.next()){
				c.read(rs);
				System.out.println("Cuenta : " + c.getCuenta() + " - " + c.getTitulo() + " - " + c.getSaldo());	
				JOptionPane.showMessageDialog(null, "Voy a escribir!!!");
				c.setCuenta("12345678901234567890");
				c.setTitulo("Andaive");
				double a = c.getSaldo();
				c.setSaldo(a + 0.31);
				a = c.getSaldo();
				System.out.println("Cuenta : " + c.getCuenta() + " - " + c.getTitulo() + " - " + c.getSaldo());	
				c.write();
				JOptionPane.showMessageDialog(null, "Ya he escrito!!!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error leyendo Cuenta!!!");
		}
		
		finally{
			rs.close();
			m.conn.close();
		}
		
		
	
		

	}

}
