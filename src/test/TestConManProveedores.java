package test;

import general.DatosComunes;

import javax.swing.UIManager;

import consultasMantenimientos.ConManProveedor;

import util.Apariencia;

public class TestConManProveedores {

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
        
		// false -> Consulta
		// true  -> Mantenimiento
		new ConManProveedor(true);
	}

}
