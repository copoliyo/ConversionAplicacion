package cobolToMysql;

import java.io.*;
import java.util.StringTokenizer;

public class LeeFicheroClaves {
	/*
	 * WITH DUPLICATES tiene que ir despues de el ultimo campo. Si no NO FUNCIONA.
	 */
	
	public void lee(String fichero, String ruta){
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		int numeroClave = 0;
		int numeroIndice = 0;
		boolean enClaveAlternativa = false;
		String auxStr = "";
		

    
		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File (ruta + "xs" + fichero + ".cbl");
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);

			// Lectura del fichero
			String linea, linea2;
			String l;
			while((linea = br.readLine())!=null){
				linea = linea.trim();
				// Buscamos el 'RECORD KEY IS'
				if(linea.startsWith("RECORD") == true){
					StringTokenizer palabra = new StringTokenizer(linea);					
					while(palabra.hasMoreTokens()){		
						l = palabra.nextToken();
						if (l.equalsIgnoreCase("RECORD")){
							l = palabra.nextToken();
							if (l.equalsIgnoreCase("KEY")){
								l = palabra.nextToken();
									if(l.equalsIgnoreCase("IS")){
										l = palabra.nextToken();
										l = l.replace('-', '_');
										if(l.endsWith("."))
											l = l.substring(0,	l.length()-1);
										DumpCobol.key[numeroClave].nombre[numeroIndice] = l;
										DumpCobol.key[numeroClave].primaria = true;
										DumpCobol.key[numeroClave].unica = true;
										// Ya tenemos la clave primaria		
										enClaveAlternativa = false;
									}
							}
						}
					}
				}
				
				//Buscamos 'ALTERNATE RECORD KEY IS'
				if(linea.indexOf("ALTERNATE") != -1){
					StringTokenizer palabra = new StringTokenizer(linea);
					while(palabra.hasMoreTokens()){
						l = palabra.nextToken();
						if (l.equalsIgnoreCase("ALTERNATE")){
							l = palabra.nextToken();
							if (l.equalsIgnoreCase("RECORD")){
								l = palabra.nextToken();
									if(l.equalsIgnoreCase("KEY")){
										l = palabra.nextToken();
											if(l.equalsIgnoreCase("IS")){
												l = palabra.nextToken();
												numeroClave++;
												numeroIndice = 0;
												DumpCobol.key[numeroClave].nombre[numeroIndice] = l.replace('-', '_');
												enClaveAlternativa = true;
												// Ya tenemos el nombre de la clave alternativa
												if(palabra.hasMoreTokens()){
													l = palabra.nextToken();
													if(l.equalsIgnoreCase("=")){
														// Para meter la Empresa en todas la claves
														numeroIndice++;														
														DumpCobol.key[numeroClave].nombre[numeroIndice] = "EMPRESA";
														
														while(palabra.hasMoreTokens()){
															l = palabra.nextToken();
															numeroIndice++;
															auxStr = l.replace('-', '_');
															if(auxStr.endsWith(",") || auxStr.endsWith("."))
																auxStr = auxStr.substring(0, auxStr.length()-1);
															DumpCobol.key[numeroClave].nombre[numeroIndice] = auxStr;
														}													
													}
												}else{
													DumpCobol.key[numeroClave].nombre[numeroIndice+1] = DumpCobol.key[numeroClave].nombre[numeroIndice];													
												}
											}
										}
									}
							}
						
					}
				}else{
					//Si ya estamos en una clave alternativa seguimos metiendo campos
					if(enClaveAlternativa == true){
						StringTokenizer palabra = new StringTokenizer(linea);
						while(palabra.hasMoreTokens()){
							l = palabra.nextToken();
							if(l.equalsIgnoreCase("WITH")){
								l = palabra.nextToken();
								if(l.startsWith("DUPLICATES")){
									DumpCobol.key[numeroClave].unica = false;
								}								
							}else{
								numeroIndice++;
								auxStr = l.replace('-', '_');
								if(auxStr.endsWith(",") || auxStr.endsWith("."))
									auxStr = auxStr.substring(0, auxStr.length()-1);
								DumpCobol.key[numeroClave].nombre[numeroIndice] = auxStr;
							}
						}
						enClaveAlternativa = false;
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
	}
}
