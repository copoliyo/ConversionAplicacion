package test;

import general.DatosComunes;

public class TestStringReplaceFirst {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String strSqlProveedor = "SELECT * FROM PROVAC WHERE EMPRESA ='";
		String strSqlCuenta = "SELECT * FROM CONTAB WHERE EMPRESA = '";	
		
		String strSqlDeleteProveedor = "DELETE" + strSqlProveedor.substring(8, strSqlProveedor.length() - 8);
		String strSqlDeleteCuenta = strSqlCuenta.replaceFirst("SELECT *", "DELETE  ");
	
		System.out.println("strSqlDeleteProveedor: '" + strSqlDeleteProveedor + "'");
		System.out.println("strSqlDeleteCuenta: '" + strSqlDeleteCuenta + "'");
	}

}
