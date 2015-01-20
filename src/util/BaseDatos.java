package util;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JOptionPane;


public class BaseDatos {
	
	// Sólo vale para consultas del tipo SELECT * FROM
	// Parámetros:  String - la consulta de la que queremos saber el número de registros
	//                       que devolverá.
	// Devuelve:	int    - número de registros de la consulta.
	
	public static int countRows(String consulta){
		ResultSet rs = null;
		MysqlConnect m = null;   
		int rowCount = -1;

		m = MysqlConnect.getDbCon();
		String sqlQueryCount = consulta.replaceFirst("\\*", "COUNT(*)");

		try {
			rs = m.query(sqlQueryCount);
			rs.first();
			// La primera y única columna que devuelve es el número de registros.
			rowCount =  rs.getInt(1);
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null,
					"Error en recuento registros (countRows)!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}

		return rowCount;
	}
	
	public static int borraRegistro(String strSqlDelete){
		ResultSet rs = null;
		MysqlConnect m = null;   
		int rowCount = -1;

		m = MysqlConnect.getDbCon();
		

		try {
			rowCount = m.insert(strSqlDelete);
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null,
					"Error en borrado de registros (borraRegistro)!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}

		return rowCount;
	}
	
	public static ResultSet sqlToRecordSet(String consulta){
		ResultSet rs = null;
		MysqlConnect m = null;   

		m = MysqlConnect.getDbCon();
		String strSql = consulta;

		try {
			rs = m.query(strSql);			
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null,
					"Error en sqlToRecordSet!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}

		return rs;
	}
}
