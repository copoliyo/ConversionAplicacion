package util;

public class CuentaBancaria {
	private String banco;
	private String sucursal;
	private String digitosControl;
	private String cuenta;
	private String ibanPais;
	private String ibanCodigoControl;
	
	private String caracteresIban = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public CuentaBancaria(){
		banco = "0000";
		sucursal = "0000";
		digitosControl = "00";
		cuenta = "0000000000";
		// Vamos a suponer que estamos en Ejpaña
		ibanPais = "ES";
		ibanCodigoControl = "00";
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}
	
	public void setBanco(int banco) {		
		this.banco = String.format("%04d", banco);
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	
	public void setSucursal(int sucursal) {
		this.sucursal = String.format("%04d", sucursal);
	}

	public String getDigitosControl() {
		return digitosControl;
	}

	public void setDigitosControl(String digitosControl) {
		this.digitosControl = digitosControl;
	}
	
	public void setDigitosControl(int digitosControl) {
		this.digitosControl = String.format("%02d", digitosControl);
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	
	public void setCuenta(long cuenta) {
		this.cuenta = String.format("%010d", cuenta);
	}

	public String getIbanPais() {
		return ibanPais;
	}

	public void setIbanPais(String ibanPais) {
		ibanPais = ibanPais.toUpperCase();
		if(ibanPais.length() > 2)
			ibanPais = ibanPais.substring(0, 2);
		this.ibanPais = ibanPais;
	}

	public String getIbanCodigoControl() {
		return ibanCodigoControl;
	}

	public void setIbanCodigoControl(String ibanCodigoControl) {
		this.ibanCodigoControl = ibanCodigoControl;
	}
	
	public void setIbanCodigoControl(int ibanCodigoControl) {
		this.ibanCodigoControl = String.format("%02d", ibanCodigoControl);
	}

	public void calcularIban(){
		/*
		 * Cálculo del IBAN:
		 * 
		 * El código IBAN, es como los 20 dígitos de la cuenta bancaria mas otros 4 caracteres.
		 * Dos alfanuméricos, que indican el país. Por ejemplo ES. Y otros dos caracteres
		 * numéricos que son un código de control.
		 * 
		 * Para calcular el IBAN el procedimiento es el siguiente.
		 * 
		 * Cuenta inicial: 0012 0345 03 0000067890
		 * 	Banco:           0012
		 * 	Sucursal:        0345
		 * 	DC:                03
		 * 	Cuenta:     000067890
		 * 
		 * La cuenta con el IBAN sería: ES0000120345030000067890
		 * 
		 * Paso 1: pasamos los cuatro primero caracteres al final.
		 * 
		 * 00120345030000067890ES00
		 * 
		 * Paso 2: sustituimos los caracteres que indican el pais por su valor numérico obtenido de la
		 * siguiente tabla.
		 * 
		 * A - 10	G - 16	M - 22	S - 28	Y - 34
		 * B - 11	H - 17	N - 23	T - 29	Z - 35
		 * C - 12	I - 18	O - 24	U - 30
		 * D - 13	J - 19	P - 25	V - 31
		 * E - 14	K - 20	Q - 26	W - 32
		 * F - 15	L - 21	R - 27	X - 33
		 * 
		 * 00120345030000067890142800     E = 14; S = 28
		 * 
		 * Paso 3: Hallar el resto de dividir el numero de arriba (00120345030000067890142800) entre 97
		 * 
		 * PROBLEMA: en un numero entero de tipo 'long' caben numeros hasta 9.223.372.036.854.775.808, 
		 * es decir números de 17 cifras y el número que tenemos que dividir tiene 26. Hay que hacer un
		 * algoritmo para esto.
		 * 
		 * Para ello podemos dividir el número en dos partes de 13 dígitos cada una. Dividiremos la primera
		 * entre 97 y obtendremos el resto. Este resto, lo tenemos que anteponer y juntarlo con el segundo
		 * numero de 13 dígitos. Con esto, obtendremos un número 'como máximo' de 15 dígitos, el cual ya
		 * podemos manejar correctamente. Ejemplo.
		 * 
		 * 123456 modulo 7 = (4)
		 * --------------------
		 * 123 modulo 7 = 4
		 * 
		 * 4456 modulo 7 = (4)
		 * 
		 * Por último  a 98 hay que restarle el resto anterior: 98 - 91 = 7 siguiendo el ejemplo inicial.
		 * 
		 * Si es menor de 10, le añadimos un cero por la izquierda.
		 */
		
		String strCC1 = "";
		String strCC2 = "";
		String strIban = "";
		String strIban13H = "";
		String strIban13L = "";
		String strIbanDigitosControl = "";
		
		long iban13H = 0;
		long iban13L = 0;
		long ibanResto = 0;
		
		strCC1 = String.format("%02d", caracteresIban.indexOf(ibanPais.substring(0, 1)) + 10);
		strCC2 = String.format("%02d", caracteresIban.indexOf(ibanPais.substring(1, 2)) + 10);
			
		// Montamos el número completo
		strIban = banco + sucursal + digitosControl + cuenta + strCC1 + strCC2 + ibanCodigoControl;
		
		// Lo dividimos en dos partes de 13 dígitos cada una y obtenemos el valor 'long' de
		// cada una de ellas.
		strIban13H = strIban.substring(0, strIban.length() - 13);
		strIban13L = strIban.substring(strIban.length() - 13, strIban.length());
		
		//System.out.println("strIban   : '" + strIban + "'");
		//System.out.println("strIban13H: '" + strIban13H + "'");
		//System.out.println("strIban13L: '" + strIban13L + "'");
		
		iban13H = Long.valueOf(strIban13H);
		iban13L = Long.valueOf(strIban13L);
		
		//System.out.println("iban13H: '" + iban13H + "'");
		//System.out.println("iban13L: '" + iban13L + "'");
		
		// Calculamos el resto de dividir la parte alta entre 97
		ibanResto = iban13H % 97;
		//System.out.println("Resto: " + ibanResto);
		
		// Añadimos el resto obtenido a la izquierda de la parte baja
		strIban13L = String.valueOf(ibanResto) + strIban13L;
		//System.out.println("resto + iban13L: '" + strIban13L + "'");
		
		// Lo pasamos a su valor 'long'
		iban13L = Long.valueOf(strIban13L);
		
		// Obtenemos el resto DEFINITIVO
		ibanResto = iban13L % 97;				
		//System.out.println("Resto: " + ibanResto);
		
		strIbanDigitosControl = String.valueOf(98 - ibanResto);
		if(strIbanDigitosControl.length() < 2)
			strIbanDigitosControl = "0" + strIbanDigitosControl;
		
		//System.out.println("strIbanDigitosControl: '" + strIbanDigitosControl + "'");
		
		setIbanCodigoControl(strIbanDigitosControl);
	}
	
	public String calculaDigitosControl(){
		/*
		 * La forma de calcular el dígito de control es esta:

			La primera cifra del banco se multiplica por 4.
			La segunda cifra del banco se multiplica por 8.
			La tercera cifra del banco se multiplica por 5.
			La cuarta cifra del banco se multiplica por 10.

			La primera cifra de la entidad se multiplica por 9.
			La segunda cifra de la entidad se multiplica por 7.
			La tercera cifra de la entidad se multiplica por 3.
			La cuarta cifra de la entidad se multiplica por 6.

			Se suman todos los resultados obtenidos.
			Se divide entre 11 y nos quedamos con el resto de la división.
			A 11 le quitamos el resto anterior, y ese el el segundo dígito de control, 
			con la salvedad de que si nos da mas de 9, el dígito es 11 - el mismo dígito.

			Para obtener el segundo dígito de control:
			La primera cifra de la cuenta se multiplica por 1
			La segunda cifra de la cuenta se multiplica por 2
			La tercera cifra de la cuenta se multiplica por 4
			La cuarta cifra de la cuenta se multiplica por 8
			La quinta cifra de la cuenta se multiplica por 5
			La sexta cifra de la cuenta se multiplica por 10
			La septima cifra de la cuenta se multiplica por 9
			La octava cifra de la cuenta se multiplica por 7
			La novena cifra de la cuenta se multiplica por 3
			La decima cifra de la cuenta se multiplica por 6
			Se suman todos los resultados obtenidos.
			Se divide entre 11 y nos quedamos con el resto de la división.
			A 11 le quitamos el resto anterior, y ese el el segundo dígito de control, 
			con la salvedad de que si nos da mas de 9, el dígito es 11 - el mismo dígito.
		 */
		String strDigitosControl = "";
		int dc = 0;
		int dc1 = 0;
		int dc2 = 0;
		
		dc1 = (Integer.valueOf(banco.substring(0, 1)) * 4) +
		      (Integer.valueOf(banco.substring(1, 2)) * 8) +
		      (Integer.valueOf(banco.substring(2, 3)) * 5) +
		      (Integer.valueOf(banco.substring(3, 4)) * 10) +
		      (Integer.valueOf(sucursal.substring(0, 1)) * 9) +
		      (Integer.valueOf(sucursal.substring(1, 2)) * 7) +
		      (Integer.valueOf(sucursal.substring(2, 3)) * 3) +
		      (Integer.valueOf(sucursal.substring(3, 4)) * 6);
		dc1 = dc1 % 11;
		dc1 = 11 - dc1;
		if (dc1 > 9)
			dc1 = 11 - dc1;
		
		dc2 = (Integer.valueOf(cuenta.substring(0, 1)) * 1) +
		      (Integer.valueOf(cuenta.substring(1, 2)) * 2) +
		      (Integer.valueOf(cuenta.substring(2, 3)) * 4) +
		      (Integer.valueOf(cuenta.substring(3, 4)) * 8) +
		      (Integer.valueOf(cuenta.substring(4, 5)) * 5) +
		      (Integer.valueOf(cuenta.substring(5, 6)) * 10) +
		      (Integer.valueOf(cuenta.substring(6, 7)) * 9) +
		      (Integer.valueOf(cuenta.substring(7, 8)) * 7) +
		      (Integer.valueOf(cuenta.substring(8, 9)) * 3) +
		      (Integer.valueOf(cuenta.substring(9, 10)) * 6);
		dc2 = dc2 % 11;
		dc2 = 11 - dc2;
		if (dc2 > 9)
			dc2 = 11 -dc2;
		
		dc = (dc1 * 10) + dc2;
		
		strDigitosControl = String.format("%02d", dc);
		
		setDigitosControl(strDigitosControl);
		
		return strDigitosControl;
	}
}
