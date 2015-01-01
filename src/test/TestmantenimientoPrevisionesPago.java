package test;

import javax.swing.UIManager;

import mantenimientos.MantenimientoPrevisionesPago;
import general.DatosComunes;


public class TestmantenimientoPrevisionesPago {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DatosComunes("MV");
		// SOLO HAY QUE UTILIZARLO UNA VEZ!!!!!!!!!!
		try
        {
            UIManager.setLookAndFeel("org.jvnet.substance.skin.Substance"+"Sahara"+"LookAndFeel");        	    	
        } catch(Exception e)
        {
            System.out.println("Falló la carga del tema");
        } 
		MantenimientoPrevisionesPago mpp = new MantenimientoPrevisionesPago();
	}

}
