package cobolToMysql;

// Si la clave es la clave primaria, el primer nombre el el nombre de la clave primaria.
// Si la clave no es primaria, es una clave alternativa y por tanto, otro indice
// El nombre de este indice alternativo estará en nombre[0] y el nombre de los campos
// que componen el índice alternavivo, estará entre nombre[1]..nombre[9]
// con 'unica', sabremos si es un índice tipo 'UNIQUE' porque no tiene la sentencia
// 'WITH DUPLICATES' en el fichero XS.
public class Clave {
	String nombre[]= new String[10];
	boolean primaria;
	boolean unica;
	
	Clave(){				
		for(int i = 0; i < 10; i++)
			nombre[i]="";
		
		primaria = false;
		unica = true;		
	}
}
