/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import general.DatosComunes;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import mantenimientos.MantenimientoPrevisionesCobro;

/**
 *
 * @author Txus
 */
public class TestMantenimientoPrevisionesCobro {
    /**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DatosComunes("MV");
		// SOLO HAY QUE UTILIZARLO UNA VEZ!!!!!!!!!!
	/*	try
        {
            UIManager.setLookAndFeel("org.jvnet.substance.skin.Substance"+"Sahara"+"LookAndFeel");        	    	
        } catch(Exception e)
        {
            System.out.println("Falló la carga del tema");
        } 
		MantenimientoPrevisionesCobro mpc = new MantenimientoPrevisionesCobro(new JFrame());
	}*/
        
         try {
                    try {
                        UIManager.setLookAndFeel("org.jvnet.substance.skin.Substance"+"Sahara"+"LookAndFeel");
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(TestMantenimientoPrevisionesCobro.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InstantiationException ex) {
                        Logger.getLogger(TestMantenimientoPrevisionesCobro.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(TestMantenimientoPrevisionesCobro.class.getName()).log(Level.SEVERE, null, ex);
                    }
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }
         
       
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               new MantenimientoPrevisionesCobro(new JFrame());
            }
        });      
    }
}
