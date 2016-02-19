package cobolToMysql;

import java.sql.*;

public class DumpCobol {
	static Campo[] ccc = new Campo[250];
	static Clave[] key = new Clave[10];
	static EstructuraCamposXF[] ecXF = new EstructuraCamposXF[300];
	//static final String fichero = "clites";
	/**
	 * @param args
	 * @throws SQLException 
	 */
	public void migra(String fichero, String rutaFicheroDatos, String rutaFicherosXfXs, String empresa, String schema) throws SQLException {
		int longitudRegistro = 0;
		//int resultado = 0;
		
		// Creamos la estructura que contandrá la información de las claves
		for(int i = 0 ; i < 10; i++)
			key[i] = new Clave();
		
		// Creamos la estructura que contendrá la información de los campos
		for(int i = 0 ; i < 250; i++)
			ccc[i] = new Campo();
		// Creamos la estructura que contendrá la información de los campos porque pueden ser compuestos
		for(int i = 0 ; i < 300; i++)
			ecXF[i] = new EstructuraCamposXF();
		
		Connection conexion;
		
		// Leemos el XF para conocer como están compuestos los campos
		LeeFicheroXF xf = new LeeFicheroXF();
		xf.lee(fichero, rutaFicherosXfXs);
		// Leemos el XS para averiguar la clave principal y las alternativas.
		LeeFicheroClaves fc = new LeeFicheroClaves();
		fc.lee(fichero, rutaFicherosXfXs);
		// Leemos el fichero para insertar los valores
		LeeFichero lf = new LeeFichero();
		lf.lee(fichero);
		
		CreaBd bd = new CreaBd();
		
		conexion = bd.abreBaseDeDatos(ccc, schema);
		
		// Preparamos la conexion
		Statement s=null;
		try {
			s = conexion.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		try {
			s.executeUpdate("SET AUTOCOMMIT = 0");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// Calculamos la longitud del registro
		for(int i = 0; i < 250 && ccc[i].nombre.length() > 0; i++){
			if(ccc[i].numerico == false || ccc[i].tipoComp == 0){
				longitudRegistro += ccc[i].longitud;
				System.out.println("Longitud registro: " + ccc[i].longitud + " - " + longitudRegistro);
			}else{
				/*
				longitudRegistro += (ccc[i].longitud + 1) / 2;
				*/
				if(ccc[i].tipoComp == 6)
					ccc[i].tipoComp = ccc[i].tipoComp; 
				longitudRegistro += ccc[i].longitud / 2;
				if(ccc[i].longitud % 2 != 0){											
					longitudRegistro++;
					System.out.println("Longitud registro: " + ((ccc[i].longitud / 2) + 1)  + " - " + longitudRegistro);
				}else{
					System.out.println("Longitud registro: " + (ccc[i].longitud /2) + " - " + longitudRegistro);				
				}				
			}
		}
			
		
		byte[] lineaDatos = new byte[longitudRegistro];
		
		// System.out.println("Longitud registro: " + longitudRegistro);
		
		DecodificaFicheroDatos fd = new DecodificaFicheroDatos();
		DecodificaLineaDatos ld = new DecodificaLineaDatos();
		String status = fd.abrirFicheroDatos(fichero, rutaFicheroDatos, empresa);
		
		int i=0;
		if (status != null){
			do{
				lineaDatos = fd.leerLineaDatos(longitudRegistro);
				if (lineaDatos != null)
					ld.decodificaLinea(lineaDatos, conexion, empresa);
				System.out.println("[" + fichero + "]Registro : " + ++i);
			}while(lineaDatos != null);
						
			fd.cerrarFicheroDatos();
			
			try {
				s.executeUpdate("COMMIT");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
