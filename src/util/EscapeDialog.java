package util;

import java.awt.Desktop.Action;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

public 	class EscapeDialog extends JDialog {
		  public EscapeDialog() {			  
		    this((Frame) null, false);		   
		  }

		  public EscapeDialog(Frame owner) {
		    this(owner, false);
		  }

		  public EscapeDialog(Frame owner, boolean modal) {
		    this(owner, null, modal);
		  }

		  public EscapeDialog(Frame owner, String title) {
		    this(owner, title, false);
		  }

		  public EscapeDialog(Frame owner, String title, boolean modal) {
		    super(owner, title, modal);
		  }

		  public EscapeDialog(Dialog owner) {
		    this(owner, false);
		  }

		  public EscapeDialog(Dialog owner, boolean modal) {
		    this(owner, null, modal);
		  }

		  public EscapeDialog(Dialog owner, String title) {
		    this(owner, title, false);
		  }

		  public EscapeDialog(Dialog owner, String title, boolean modal) {
		    super(owner, title, modal);
		  }

		  protected JRootPane createRootPane() {
		    JRootPane rootPane = new JRootPane();
		    KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
		    javax.swing.Action actionListener = new AbstractAction() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        //setVisible(false);
		        dispose();
		      }
		    };
		    InputMap inputMap = rootPane
		        .getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		    inputMap.put(stroke, "ESCAPE");
		    rootPane.getActionMap().put("ESCAPE", actionListener);

		    return rootPane;
		  }		  		 
}
