/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import consultasMantenimientos.ConManTipos;
import general.DatosComunes;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author Txus
 */
public class TestConManTipos {
    public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DatosComunes("MV");
		
		// SOLO HAY QUE UTILIZARLO UNA VEZ!!!!!!!!!!
		try
        {
            UIManager.setLookAndFeel("org.jvnet.substance.skin.Substance"+"Sahara"+"LookAndFeel");        	    	
        } catch(Exception e)
        {
            System.out.println("Fall� la carga del tema");
        } 
        
		// true -> Consulta
		// false  -> Mantenimiento
		new ConManTipos(new JFrame(), false);
	}
}
