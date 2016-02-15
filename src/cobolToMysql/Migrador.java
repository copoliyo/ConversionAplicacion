import java.sql.SQLException;
import java.io.*;


public class Migrador {
	

	public int unloadFicheroCobol(String fichero, String empresa, String ruta){
		
		String comando = "";
		
		comando = "vutil32 -unload -b " + ruta + "MV" + fichero + " " + ruta + empresa + fichero + ".TXT";  
		String s = null;

        try {
            
	    // run the Unix "ps -ef" command
            // using the Runtime exec method:
            Process p = Runtime.getRuntime().exec(comando);
            
            BufferedReader stdInput = new BufferedReader(new 
                 InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new 
                 InputStreamReader(p.getErrorStream()));

            // read the output from the command
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }
            
            // read any errors from the attempted command
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
            
            return 0;
        }
        catch (IOException e) {       
            e.printStackTrace();
            return 1;
        }
 		
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String ficheros[]={
				/*"CPCALL", "CPPOST", "CPPAIS", "CPPOBL", "CPPROV"*/
				/*
				"2DADIR","ABNSTK","ACCCEN",
				*/
				/*"ACCESO","ACCEST",
				"ACCHST","ACCPRG","ACCUSU","AGENDA","ALBPRV",
				"ARTCEN","ARTCLO","ARTEAN","ARTEQV","ASTAUC",
				"ASTAUT","BANCOS","BCOIND","CACEST","CALVAR",
				"CARTCA","CARTCD","CARTCF",
				
				"CEMAGF", 
				
				
				"CEPTOS",
				"CLCEPC","CLIENT","CLITES","COBROS","COMPON",
				"CONTAB","CPZONA","DEBHAB","EFECOB"*/
				/*,"EFEPAG",
				"EXTFIN","FACAUT","FACCEP","FACEMI","FACLIN",
				"FACREC","FORCOB","GESCEP","GESCPN",
				
				
				
				
				"INDCAR","INDCEN","INDINF","LINVAR","MGFGST",
				"MOACEP","MOCCEP","MOVART","MOVCON","MOVIMP",
				"NOTASF","OFERTA","PARTIE",
				"PROVAC"*/
				/*,"REPRES",*/ "RUTCLI"/*,
				"SATREP","SATSAT",
				
				
				  
			
				"TICKET","TICKOT",
				 
				"TIPAGO","TIPCLI","TITCOB","TOTCOB","TOTPAG",
				"ZONCLI"
				*/
				
				/// NO TOCAR
				/*"SISTEM",*/
				};
		
		String empresa = "MV";
		String rutaDatos = "C:\\DENAORIGINAL\\";
		
		
		Migrador mg = new Migrador();
		
		
		
		
		DumpCobol dc = new DumpCobol();
		
		
		for(int i = 0; i < ficheros.length; i++){
			// Primero descargamos los datos a un fichero de texto Binario
			mg.unloadFicheroCobol(ficheros[i], empresa, rutaDatos);
			
			try {
				dc.migra(ficheros[i], rutaDatos, empresa);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
		// TODO Auto-generated method stub
		
	}

}
