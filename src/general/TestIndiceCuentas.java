package general;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import util.Apariencia;

import indices.IndiceCuentas;
import indices.IndiceProveedores;

public class TestIndiceCuentas {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        
		String cuenta = "";
		
		new DatosComunes("MV");
		
		IndiceCuentas ic = new IndiceCuentas();
		
		cuenta = ic.getCuenta();
		System.out.println("Test Indice Cuentas - Cuenta : " + cuenta);
		
	}

}
