package cobolToMysql;

import java.io.*;
import java.util.*;

public class LeeFichero {
	private static boolean isNumeric(String cadena){	  
		try{
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
		return false;
		}
	}
	
	public static Campo descifraTipoCampo(String cadena, Campo campo){
		String tp = "";
		String t[];
		boolean numerico = false;
		boolean signo = false;
		int longitud = 0;
		int longitudParteEntera = 0;
		int longitudParteDecimal = 0;
		boolean decimal = false;
		Campo cp = new Campo();
		cp = campo;
		
				
		SeparaPalabraCharDiff sp = new SeparaPalabraCharDiff();
		t = sp.separa(cadena.trim());
		for(int ii = 0; ii < t.length && t[ii] != null; ii++){
			System.out.println("++Token : '" + t[ii] + "'");

			// Es tipo caracter
			if(t[ii].charAt(0) == 'X'){
				numerico = false;
				signo = false;				
				longitudParteEntera = longitudParteDecimal = 0;
				decimal = false;
				longitud = t[ii].length();
			}
			
			// ¿Es un punto decimal?
			if(t[ii].charAt(0) == 'V'){
				decimal = true;	
				//longitud++;
			}
			// Tiene que ser signo???						
			if(t[ii].equalsIgnoreCase("S")){
				numerico = true;
				signo = true;
				longitud++;
			}
			
			
			// Si es un 9, tenemos que mirar si es 9, 99 ó 9(n)
			if(t[ii].charAt(0) == '9'){
				numerico=true;
				//Si no hemos leido la coma, estamos en la parte entera
				if(decimal == false){
					longitudParteEntera = t[ii].length();
					longitud+=longitudParteEntera;
				}
				else{
					longitudParteDecimal = t[ii].length();
					longitud+=longitudParteDecimal;
				}					
			}
		}
		System.out.println("Longitud : " + longitud);
		System.out.println("Numerico : " + numerico);		
		System.out.println("Signo    : " + signo);
		System.out.println("Decimal  : " + decimal);
		System.out.println("Le       : " + longitudParteEntera);
		System.out.println("ld       : " + longitudParteDecimal);
		cp.longitud = longitud;
		cp.numerico = numerico;
		cp.signo = signo;
		cp.decimal = decimal;
		cp.lentero = longitudParteEntera;
		cp.ldecimal = longitudParteDecimal;
		cp.occurs = 0;
		cp.tipoComp = 0;
				
		return cp;
	}


	public void lee(String fichero){
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		int nLinea = 0;
		String tipoCampo;
		int numeroCampo = 0;
		

    
		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File ("C:\\conversion\\xf" + fichero + ".cbl");
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);

			// Lectura del fichero
			String linea, linea2;
			String l;
			while((linea = br.readLine())!=null){
				linea2 = "";
				//System.out.println(linea.indexOf("PIC"));
				// Tenemos que aseguraros que lee todas las lineas hasta que encuentra un punto.
				while(!linea.endsWith(".") && linea.indexOf("*") == -1)
					if((linea2 = br.readLine())!=null)
						linea = linea + linea2;
				if(linea.indexOf("PIC") != -1){
					if(!linea.endsWith(".")){
						linea = linea + br.readLine();
					}
					
					System.out.print("Linea " + ++nLinea + ":" + linea);
					StringTokenizer palabra = new StringTokenizer(linea);					
					while(palabra.hasMoreTokens()){						
					// Vemos si tiene número de nivel.
						l = palabra.nextToken();
						if(isNumeric(l))
							System.out.print("-- Nivel: " + Integer.parseInt(l));
						else
							break;
					// Obtenemos el nombre del campo
						l = palabra.nextToken();
					
						l = l.replace('-', '_');
						DumpCobol.ccc[numeroCampo].nombre = l;
						System.out.print("\nNombre campo: " + DumpCobol.ccc[numeroCampo].nombre);
					// Vemos si lo siquiente es un 'PIC'
						l = palabra.nextToken();
						if(!l.equalsIgnoreCase("PIC")){
							System.out.print("Error, se esperaba 'PIC'");
							break;
						}	
					// Sacamos el tipo de campo
						
						l = palabra.nextToken();
						System.out.println("\nTipo de campo a descifrar '" + l + "'");												
						DumpCobol.ccc[numeroCampo] = descifraTipoCampo(l, DumpCobol.ccc[numeroCampo]);
						System.out.println(" Tipo campo: " + DumpCobol.ccc[numeroCampo]);
						while(palabra.hasMoreTokens()){
							l = palabra.nextToken();
							if(l.startsWith("COMP-"))
								System.out.println("Computacional!!!!!!");
							if(l.startsWith("COMP-3"))
								DumpCobol.ccc[numeroCampo].tipoComp = 3;
							if(l.startsWith("COMP-6"))
								DumpCobol.ccc[numeroCampo].tipoComp = 6;
							if(l.startsWith("OCCURS")){
								l = palabra.nextToken();
								int occurs = Integer.parseInt(l);
								Campo auxCampo = new Campo();	
								
								auxCampo.nombre = DumpCobol.ccc[numeroCampo].nombre;		
								auxCampo.longitud = DumpCobol.ccc[numeroCampo].longitud;
								auxCampo.numerico = DumpCobol.ccc[numeroCampo].numerico;
								auxCampo.signo = DumpCobol.ccc[numeroCampo].signo;
								auxCampo.decimal = DumpCobol.ccc[numeroCampo].decimal;
								auxCampo.lentero = DumpCobol.ccc[numeroCampo].lentero;
								auxCampo.ldecimal = DumpCobol.ccc[numeroCampo].ldecimal;
								auxCampo.tipoComp = DumpCobol.ccc[numeroCampo].tipoComp;
								auxCampo.occurs = DumpCobol.ccc[numeroCampo].occurs;
															
								// Tenemos que repetir el campo si hay un OCCURS
								for(int i = 1; i <= occurs; i++){
									DumpCobol.ccc[numeroCampo].nombre = auxCampo.nombre;		
									DumpCobol.ccc[numeroCampo].longitud = auxCampo.longitud;
									DumpCobol.ccc[numeroCampo].numerico = auxCampo.numerico;
									DumpCobol.ccc[numeroCampo].signo = auxCampo.signo;
									DumpCobol.ccc[numeroCampo].decimal = auxCampo.decimal;
									DumpCobol.ccc[numeroCampo].lentero = auxCampo.lentero;
									DumpCobol.ccc[numeroCampo].ldecimal = auxCampo.ldecimal;
									DumpCobol.ccc[numeroCampo].tipoComp = auxCampo.tipoComp;
									DumpCobol.ccc[numeroCampo].occurs = auxCampo.occurs;
								
									DumpCobol.ccc[numeroCampo].nombre = DumpCobol.ccc[numeroCampo].nombre + "_" + String.valueOf(i);
									numeroCampo++;
								}
								numeroCampo--;
							}
						}
						
						System.out.println("Número campo : " + numeroCampo);
						numeroCampo++;
						
						
					}
					
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
		
		for(int i = 0; i < numeroCampo; i++){
			System.out.println("Campo " + i + "," + DumpCobol.ccc[i].nombre + "," +
													DumpCobol.ccc[i].numerico + "," +
					                                DumpCobol.ccc[i].longitud + "," +
					                                DumpCobol.ccc[i].signo + "," +
					                                DumpCobol.ccc[i].decimal + "," +
					                                DumpCobol.ccc[i].lentero + "," +
					                                DumpCobol.ccc[i].ldecimal + "," +
					                                DumpCobol.ccc[i].tipoComp + "," +
					                                DumpCobol.ccc[i].occurs);
		}		
	}
}
