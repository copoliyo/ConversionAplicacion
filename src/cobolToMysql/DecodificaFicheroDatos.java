import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;


public class DecodificaFicheroDatos {
	public File archivo = null;
	public FileInputStream fr = null;
	public BufferedInputStream br = null;

	public String abrirFicheroDatos(String fichero, String ruta, String empresa){
		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			// archivo = new File ("C:\\midena\\dsmovcon.txt");
			
            // Se abre el fichero original para lectura
			FileInputStream frG = new FileInputStream(ruta + empresa+ fichero + ".txt");
			BufferedInputStream brG = new BufferedInputStream(frG);
			fr = frG;
			br = brG;
		}catch(Exception e){
			e.printStackTrace();
			return (String)null;
		}
		return "Abierto";
	}
	
	public byte[] leerLineaDatos(int longitudRegistro){
		String linea = new String("");
		byte [] array = new byte[longitudRegistro];
		int leidos;
				
		// Bucle para leer de un fichero y escribir en el otro.
				
		try{
			leidos=br.read(array);
			if(leidos < 1){
				br.close();
				return null;
			}
				
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		return array;
	}
	
	public void cerrarFicheroDatos(){
		try{                    
			if( null != fr ){   
				fr.close();     
			}                  
		}catch (Exception e2){ 
			e2.printStackTrace();
		}
	}

}
