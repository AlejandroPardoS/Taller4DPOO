package Interfaz;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelSur extends JPanel {
	
	 private JLabel lblJugadas;
	 private JLabel lblJugador;
	 private JTextField txtJugadas;
	 private JTextField txtJugador;
	
	public PanelSur() {
		
        setLayout(new GridLayout( 1, 4 ));
        
        lblJugadas = new JLabel( "Jugadas: " );
        add( lblJugadas );
        
        txtJugadas=new JTextField( "0" );
        add( txtJugadas );
        txtJugadas.setEditable( false );
        
        lblJugador = new JLabel( "Jugador: " );
        add( lblJugador );
        
        txtJugador=new JTextField();
        add( txtJugador );
        txtJugador.setEditable( false );
        
	}
	
	public void actualizar(int jugada) {
		txtJugadas.setText( String.valueOf(jugada) );
	}
	
	public void actualizarJugadorSur(String nombre) {
		txtJugador.setText(nombre);
	}

	public JTextField getTextJugador() {
		return txtJugador;
	}
	
	public boolean ahiJugador() {
		boolean retorno = true;
		if (txtJugador.getText().isEmpty()) {
			retorno = false;
		}
		return retorno;
	}
}
