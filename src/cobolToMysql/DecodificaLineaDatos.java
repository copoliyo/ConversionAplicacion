import java.io.UnsupportedEncodingException;
import java.sql.*;

public class DecodificaLineaDatos {
	
	protected int calculaNumeroBytes(Campo c){
		int nBytes = 0;
		
		// Si es un campo caracter, su longitud = nBytes
		if(c.numerico == false){
			nBytes = c.longitud;
		}
		else{
			nBytes = c.longitud;
		
			// Si es un computacional puede que tengamos que sumar
			// 'medio byte' para hacer un byte completo.
			if(c.tipoComp != 0){
				if(nBytes % 2 != 0){
					nBytes++;
					nBytes = nBytes / 2;
				}
				else
					// Si no, estamos ante un 9999 y su longitud es el úmero de digitos
					nBytes = nBytes / 2;
			}
		}
				
		return nBytes;
	}
	
	private String decodificaComp(byte[] lineaByte, int offset, int bytesCampo){
		final String hexDigit = "0123456789ABCDEF";
		String compDecodificado = new String("");
		int valorByte = 0;
		
		
		for(int j = 0; j < bytesCampo; j++){
			valorByte = lineaByte[offset + j];
			if(valorByte < 0)
				valorByte = 256 + valorByte;
			
			compDecodificado = compDecodificado + hexDigit.charAt(valorByte / 16);
			compDecodificado = compDecodificado + hexDigit.charAt(valorByte % 16);												
		}
		
		
		return compDecodificado;
	}
	
	public void decodificaLinea(byte[] lineaByte, Connection conexion){
		int offset = 0;
		int bytesCampo = 0;		
		String strDato = new String("");
		String strDatoComp3 = new String("");
		String linea = new String("");
		String strInsertP1 = "INSERT INTO ";
		String strInsertP2 = " VALUES('MV', ";
		String aux = null;
		
		// Preparamos la conexion
		Statement s=null;
		try {
			s = conexion.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		//Averiguamos el nombre de la tabla
		String[] nombreTabla = DumpCobol.ccc[0].nombre.split("_");
		
		strInsertP1 = strInsertP1 + nombreTabla[0] + "(EMPRESA, ";
		
		for(int j = 0; j < lineaByte.length; j++)
			linea = linea + (char)lineaByte[j];
		
		for(int i = 0; i < 250 && DumpCobol.ccc[i].nombre.length() > 0; i++){
			if(i == 12)
				i = 12;
			bytesCampo = calculaNumeroBytes(DumpCobol.ccc[i]);
			// Si es un dato Alfanumerico o un numerico sin signo ni decimales
			// el dato, es él directamente
			if(DumpCobol.ccc[i].numerico == false || DumpCobol.ccc[i].tipoComp == 0)
				strDato = linea.substring(offset, offset + bytesCampo);
			else{
				// Si no, hay que decodificarlo.
				strDato = decodificaComp(lineaByte, offset, bytesCampo);
				if(DumpCobol.ccc[i].tipoComp == 3){
					if(strDato.charAt(strDato.length() - 1 ) == 'D')
						strDatoComp3 = "-";
					else
						strDatoComp3 = "";
					if(DumpCobol.ccc[i].signo = true && DumpCobol.ccc[i].decimal == false){
						strDatoComp3 = strDato.substring(0,	DumpCobol.ccc[i].lentero);
						strDato = strDatoComp3;
					}
					if(DumpCobol.ccc[i].decimal == true){
						//strDatoComp3 = strDatoComp3 + strDato.substring(0, DumpCobol.ccc[i].lentero + 1);
						strDatoComp3 = strDatoComp3 + strDato.substring(0, DumpCobol.ccc[i].lentero);
						strDatoComp3 = strDatoComp3 + ".";
						strDatoComp3 = strDatoComp3 + strDato.substring(DumpCobol.ccc[i].lentero , DumpCobol.ccc[i].lentero + DumpCobol.ccc[i].ldecimal);
						strDato = strDatoComp3;
					}
				}
				else{
					if(DumpCobol.ccc[i].tipoComp == 6){
						if(DumpCobol.ccc[i].longitud % 2 != 0)
							strDato = strDato.substring(1, DumpCobol.ccc[i].longitud+1);						
					}
				}
			}
			offset += bytesCampo;

			// Metemos los nombres de campo en la sentencia INSERT
			strInsertP1 = strInsertP1 + DumpCobol.ccc[i].nombre;

			// Metemos los valores para el INSERT
			if(DumpCobol.ccc[i].numerico == false){
				if(strDato.startsWith("0053CAPRES"))
					aux=strDato;
				strInsertP2 = strInsertP2 + "'" + strDato.replace("'", "p") + "'";
			}
			else{
				if(DumpCobol.ccc[i].decimal == false){
					try {
						Long.parseLong(strDato);
					} catch (NumberFormatException nfe){
						strDato = "0";
					}	
					if(strDato == " "  || strDato.length() == 0)
						strInsertP2 = strInsertP2 + "0";
					else
						strInsertP2 = strInsertP2 + strDato;
				}else{
					try {
						Float.parseFloat(strDato);
					} catch (NumberFormatException nfe){
						strDato = "0.00";
					}
					if(strDato == " "  || strDato.length() == 0)
						strInsertP2 = strInsertP2 + "0.00";
					else
						strInsertP2 = strInsertP2 + strDato;
				}
					
				

			}
			
			if(DumpCobol.ccc[i+1].nombre.length() > 0){
				strInsertP1 = strInsertP1 + ", ";
				strInsertP2 = strInsertP2 + ", ";
			}else{
				strInsertP1 = strInsertP1 + ") ";
				strInsertP2 = strInsertP2 + ")";
			}

			//System.out.print(strDato + ",");
		}
		//System.out.println("");
		strInsertP1 = strInsertP1 + strInsertP2;
		System.out.println("strInsertP1: " + strInsertP1);
		
		try {
			s.executeUpdate(strInsertP1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
