package cobolToMysql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;


public class LeeFicheroXF {
	
	private static boolean isNumeric(String cadena){	  
		try{
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
		return false;
		}
	}
	
	public void lee(String fichero, String rutaXfXs){
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		int nLinea = 0;
		String tipoCampo;
		int numeroCampo = 0;
		

    
		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File (rutaXfXs + "xf" + fichero + ".cbl");
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);

			// Lectura del fichero
			String linea;
			String l;
			// Para poder meter la empresa en todos los ficheros y que pueda se MULTIEMPRESA con
			// una sola base de datos.
			DumpCobol.ecXF[0].nivel = 1;
			DumpCobol.ecXF[0].nombreCampo = "EMPRESA";
			DumpCobol.ecXF[0].esPic = true;
			numeroCampo++;
			
			while((linea = br.readLine())!=null){
				linea = linea.trim();
				StringTokenizer palabra = new StringTokenizer(linea);					
				while(palabra.hasMoreTokens()){
					l = palabra.nextToken();
					if(isNumeric(l)){
						DumpCobol.ecXF[numeroCampo].nivel = Integer.parseInt(l);
						l = palabra.nextToken();
						DumpCobol.ecXF[numeroCampo].nombreCampo = l.replace('-', '_');
						if(DumpCobol.ecXF[numeroCampo].nombreCampo.endsWith("."))
							DumpCobol.ecXF[numeroCampo].nombreCampo = DumpCobol.ecXF[numeroCampo].nombreCampo.substring(0, DumpCobol.ecXF[numeroCampo].nombreCampo.length()-1);
						if(palabra.hasMoreTokens() == true){
							l = palabra.nextToken();
							if(l.equalsIgnoreCase("PIC"))
								DumpCobol.ecXF[numeroCampo].esPic = true;
							else
								DumpCobol.ecXF[numeroCampo].esPic = false;
						}					
						numeroCampo++;
					}
					break;
				}	
			}									
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta 
			// una excepcion.
			try{                    
				if( null != fr ){   
					fr.close();     
				}                  
			}catch (Exception e2){ 
				e2.printStackTrace();
			}
		}
		
	}	
	
	public String obtenClave(String clave){
		String claveCompuesta = "";
		int i = 0;
		int nivelClave = 0;
		
		while(i < 300 && DumpCobol.ecXF[i].nivel != 0){
			// Buscamos el nombre de la clave
			if(clave.equalsIgnoreCase(DumpCobol.ecXF[i].nombreCampo)){
				// Si el nombre del campo que nos llega es un PIC ya hemos acabado, la clave NO es compuesta
				// y tenemos que devolver el mismo nombre del campo
				if(DumpCobol.ecXF[i].esPic == true){
					claveCompuesta = clave;
				}else{
				// Si llegamos aquí, significa que el nombre del campo está compuesto por más campos. Hay que concatenarlos.
					nivelClave = DumpCobol.ecXF[i].nivel;
					// Tenemos que pasar al siguiente campo
					i++;
					while(i < 300 && DumpCobol.ecXF[i].nivel > nivelClave){
						if(DumpCobol.ecXF[i].esPic){
							claveCompuesta = claveCompuesta + DumpCobol.ecXF[i].nombreCampo + ",";
						}
						i++;
					}	
					// Quitamos la última coma
					if(claveCompuesta.endsWith(","))
						claveCompuesta = claveCompuesta.substring(0, claveCompuesta.length()-1);
				}					
			}
			
			i++;
		}
		if( claveCompuesta == "")
			claveCompuesta = clave;
		
		return claveCompuesta;
		
	}

}
