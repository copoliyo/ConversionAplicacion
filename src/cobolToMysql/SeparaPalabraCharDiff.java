
public class SeparaPalabraCharDiff {
	public String[] separa(String s){
		String Salida[] = new String[20];
		String valRetorno[] = new String[20];
		int numToken = 0, numRetorno = 0;
		int i = 0;
		int repeticiones = 0;
		boolean numerico1 = false, numerico2 = false;
		char ch1 = '\000', ch2 = '\000';
		String token = "", str="";
		
		for(i = 0; i < s.length(); i++){
			ch1 = s.charAt(i);
			if(ch1 >= '0' && ch1 <= '9')
				numerico1 = true;
			else
				numerico1 = false;
			
			
			if((ch1 != ch2 && numerico1 != numerico2) || (ch1 == '.' || ch1 == '(' || ch1 == ')' || ch1 == 'V')){	
				if(token.length() > 0 && ch1 != '.'){
					//System.out.println("TToken : '" + token + "'");
					Salida[numToken] = token;
					numToken++;					
				}
				if(ch1 != '.')
					token = String.valueOf(ch1);
			}
			else{
				token = token + String.valueOf(ch1);
			}
			
			ch2 = ch1;			
			numerico2 = numerico1;
		}
		Salida[numToken] = token;
		//System.out.println("TToken : '" + token + "'");
		
		// Expandimos los tokens con parentesis a su valor.
		// X(3) --> XXX
		for(i = 0; i <= numToken; i++){
				
			// Si no son parentesis, los pasamoa al retorno
			if(Salida[i].equalsIgnoreCase("(") == false && Salida[i].equalsIgnoreCase(")") == false){
				valRetorno[numRetorno] = Salida[i];
				numRetorno++;
			}
			// Si no, estamos delante de unos paréntesis y tenemos que retetir
			// el token anterior tantas veces como nos indiquen los parentesis.
			else{
				if(Salida[i].equalsIgnoreCase("(") == true){
					repeticiones = Integer.parseInt(Salida[i+1]);
					str = valRetorno[numRetorno-1];
					for(int j = 1; j < repeticiones; j++){
						valRetorno[numRetorno-1] += str;
					}
					i = i + 2;
				}				
			}			
		}
		
		return valRetorno;
	}

}
