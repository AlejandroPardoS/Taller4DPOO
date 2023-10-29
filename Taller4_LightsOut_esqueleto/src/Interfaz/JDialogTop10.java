package Interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.SwingConstants;

public class JDialogTop10 extends JDialog {
	
	private JLabel lblTitulo;
	private JLabel label;
	private GridBagConstraints constraints;
	private ListModel<String> model;
	private VentanaPrincipal principal;
	
	public JDialogTop10(VentanaPrincipal pPrincipal) {
		
		principal = pPrincipal;
		
		setSize( 250, 350 );
		setResizable( false );
		setModal(true);
		setLayout(new GridBagLayout( ));
        setTitle( "Top 10" );
        setLocationRelativeTo( principal );
        constraints = new GridBagConstraints();
        
        lblTitulo = new JLabel( "#  Nombre" );
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground( Color.WHITE );
        lblTitulo.setBackground(new Color(27,62,228));
        lblTitulo.setOpaque(true);
        lblTitulo.setSize(250,35);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        
        constraints.gridx = 0; // Columna
        constraints.gridy = 0; // Fila
        constraints.fill = GridBagConstraints.BOTH; // Relleno horizontal y vertical
        constraints.weightx = 1.0; // Expansión horizontal
        constraints.weighty = 0.0; // Sin expansión vertical
        //constraints.insets = new Insets(5, 5, 5, 5); // Espacios internos
        add(lblTitulo, constraints);
        //add(lblTitulo);      
        
	}
	public void mostrarTop(JList<String> list) { 
		
		model = list.getModel();
		for (int i = 0; i <10; i++) { 
			
            String selectedValue = model.getElementAt(i);
        	label = new JLabel(selectedValue);
        	label.setFont(new Font("Arial", Font.BOLD, 14));
        	label.setSize(250, 35);
        	label.setHorizontalAlignment(SwingConstants.CENTER);
	    	constraints.gridx = 0; // Columna
	        constraints.gridy = i+1; // Fila
	        constraints.fill = GridBagConstraints.BOTH; // Relleno horizontal y vertical
	        constraints.weightx = 1.0; // Expansión horizontal
	        constraints.weighty = 0.5; // Expansión vertical
	        if (i<3)
	        	label.setForeground(Color.BLUE);
	        else if (i>=3 && i <7)
	        	label.setForeground(Color.GREEN);
	        add(label, constraints);
        }
	}
}
