package test;

import general.DatosComunes;

import javax.swing.UIManager;

import consultasMantenimientos.ConManBancos;



public class TestConManBancos {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DatosComunes("MV");
		
		// SOLO HAY QUE UTILIZARLO UNA VEZ!!!!!!!!!!
		try
        {
            UIManager.setLookAndFeel("org.jvnet.substance.skin.Substance"+"Sahara"+"LookAndFeel");        	    	
        } catch(Exception e)
        {
package test;

import general.DatosComunes;

import javax.swing.UIManager;

import consultasMantenimientos.ConManBancos;



public class TestConManBancos {
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
        
		// true -> Consulta
		// false  -> Mantenimiento
		new ConManBancos(false);
	}
}
