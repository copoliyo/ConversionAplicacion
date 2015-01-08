/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import general.DatosComunes;

import javax.swing.UIManager;

import consultasMantenimientos.ConManRutas;
import javax.swing.JFrame;
/**
 *
 * @author Txus
 */
public class TestConManRutas {
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
		new ConManRutas(new JFrame(), false);
	}
}
