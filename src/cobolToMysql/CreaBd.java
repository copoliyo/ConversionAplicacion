package cobolToMysql;

import java.sql.*;
import java.util.StringTokenizer;

public class CreaBd {
	protected Connection abreBaseDeDatos(Campo c[], String schema) throws SQLException{
		int resultado = 0;
		Connection conexion=null;
		ResultSet rs = null;
		LeeFicheroXF xf = new LeeFicheroXF();
		String clave = "";
		
		try
		{
		   // Declaramos el tipo de driver que vamos a utilizar
		   Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e)
		{
		   e.printStackTrace();
		}
		
		try
		{
			// Conectamos a la base de datos
			conexion = DriverManager.getConnection ("jdbc:mysql://localhost:3306/" + schema, "root", "copoliyo");
		} catch (Exception e)
		{
		   e.printStackTrace();
		}
		
		
		Statement s=null;
		
		 try {
			 // Creamos
			s = conexion.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		try {
			
			rs = s.executeQuery ("select * from prueba");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (rs.next())
		{
		   System.out.println("IDPRUEBA="+rs.getObject("idprueba")+
		      ", NOMBRE="+rs.getObject("nombre"));
		}
		*/
		// Divide el nombre del primer campo para ponerle un nombre a la tabla.
		String[] nombreTabla = c[0].nombre.split("_");
		try{
			int i = 0;
			
			System.out.println("Creando base de datos COBOl");
			
			// Creamos una conexion de tipo 'CREATE' ???
			Statement st = null;
	        st = conexion.createStatement();
	        
                
                ////////////////////////////////////////////////////////////////
                // No deberíamos borrar las tablas!!!!!
                ////////////////////////////////////////////////////////////////
                
	        try {
				s.executeUpdate("DROP TABLE " + nombreTabla[0]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        // Creamos la sentencia del CREATE TABLE añadiendo cada campo.
	        
	        String table = "CREATE TABLE " + nombreTabla[0] + "(EMPRESA varchar(2), ";
	        
	        // String table = "CREATE TABLE Cobol(Codigo integer, Nombre_Empresa varchar(30), primary key(Codigo))";	        
	        
	        while(c[i].longitud != 0){
	        	// Añadimos el nombre
	        	// Acordarse de si hay OCCURS
	        	table = table + " " + c[i].nombre;
	        	if (c[i].numerico == false)
	        		table = table + " varchar(" + c[i].longitud + "), ";
	        	else{
	        		if(c[i].decimal == false)
	        			table = table + " BigInt,";
	        		else{
	        			table = table + " decimal(" + c[i].longitud + "," + c[i].ldecimal + "), ";
	        		}
	        			
	        	}	
	        	i++;
	        }
	        // Tenemos que ver si la clave primaria es una clave compuesta por varios campos.
	        clave = xf.obtenClave(DumpCobol.key[0].nombre[0]);
	        
	        
	        table = table + " primary key(EMPRESA, " + clave + "))";
	        
	        System.out.println(table);
	        
	        st.executeUpdate(table);
	        st.close();
	        
	        System.out.println("Table creation process successfully!");
	      }
	      catch(SQLException e){
	        System.out.println(e.getMessage());
	      }
	      
	      // Creamos los indices
	      int numeroClave = 1;
	      while(numeroClave < 10 && DumpCobol.key[numeroClave].nombre[0].length() > 0){	    	  
	    	  String table = "CREATE INDEX ";
	    	  // Si no es WITH DUCLICATES, es una clave única => 'UNIQUE INDEX'
	    	  if(DumpCobol.key[numeroClave].unica == true)
	    		  table = "CREATE UNIQUE INDEX ";
	    	  
	    	  table = table + DumpCobol.key[numeroClave].nombre[0] + " ON " + nombreTabla[0] + " (EMPRESA, ";
	    	  int i = 1;
	    	  while(i < 10 && DumpCobol.key[numeroClave].nombre[i].length() > 0){
	    		  table = table + xf.obtenClave(DumpCobol.key[numeroClave].nombre[i]) + ",";
	    		  i++;
	    	  }
	    	  // Quitamos la coma del final
	    	  if (table.endsWith(","))
	    		  table = table.substring(0, table.length()-1);
	    	  
	    	  table = table + ")";
	    	  
	    	  // No se si esto hay repetirlo todo el rato ???
			  Statement st = null;
		      st = conexion.createStatement();
	    	  try{
	    		st.executeUpdate(table);
	  	        st.close();
	  	        
	  	        System.out.println("Indice creado crorrectamente: "+ table);
	  	      }
	  	      catch(SQLException e){
	  	    	System.out.println("Error creando indice: " + table);  
	  	        System.out.println(e.getMessage());
	  	      }  
	    	  	    	  
	    	  numeroClave++;
	      }
	      
	      
	     // HAY QUE CREAR LOS INDICES
		//rs.close();
		return conexion;
	}

}
