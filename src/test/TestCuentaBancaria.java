package test;

import util.CuentaBancaria;

public class TestCuentaBancaria {

	public static CuentaBancaria cb;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		cb = new CuentaBancaria();
		
		muestraCuenta();
		cb.setBanco(182);
		cb.setSucursal(2337);
		cb.setDigitosControl(74);
		cb.setCuenta(101507744L);
		cb.setIbanPais("ES");
		cb.setIbanCodigoControl(0);
		
		muestraCuenta();
		cb.calcularIban();
		muestraCuenta();
		System.out.println("Digitoc de control calculados: " + cb.calculaDigitosControl());
		
	}
	
	public static void muestraCuenta(){
		System.out.println("-----------------------------------------");
		System.out.println("Banco:          '" + cb.getBanco() + "'");
		System.out.println("Sucursal:       '" + cb.getSucursal() + "'");
		System.out.println("DC:             '" + cb.getDigitosControl() + "'");
		System.out.println("Cuenta:         '" + cb.getCuenta() + "'");
		System.out.println("Pais:           '" + cb.getIbanPais() + "'");
		System.out.println("Código Control: '" + cb.getIbanCodigoControl() + "'");
	}

}
