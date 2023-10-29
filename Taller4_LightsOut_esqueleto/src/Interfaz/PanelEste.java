package Interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class PanelEste extends JPanel implements ActionListener{
	
	private JButton btnNuevo;
	private JButton btnReiniciar;
	private JButton btnTop10;
	private JButton btnCambiarJugador;
	private VentanaPrincipal principal;
	private PanelNorte panelNorte;
	private PanelSur panelSur;
	
	public PanelEste(VentanaPrincipal pPrincipal, PanelNorte pPanelNorte, PanelSur pPanelSur) {
		
		principal=pPrincipal;
		panelNorte = pPanelNorte;
		panelSur = pPanelSur;
		setLayout( new GridLayout(10, 1, 8, 8) );
		add(Box.createRigidArea(new Dimension(0, 0)));// espacios blancos pa centrar
		add(Box.createRigidArea(new Dimension(0, 0)));
		add(Box.createRigidArea(new Dimension(0, 0)));
		
		btnNuevo = new JButton( "	NUEVO	" );
		btnNuevo.setBackground( new Color(27,62,228) );
		btnNuevo.setForeground( Color.WHITE );
        add( btnNuevo );

        btnReiniciar = new JButton( "	REINICIAR	" );
        btnReiniciar.setBackground( new Color(27,62,228) );
        btnReiniciar.setForeground( Color.WHITE );
        add( btnReiniciar );
        
        btnTop10 = new JButton( "	TOP-10	" );
        btnTop10.setBackground( new Color(27,62,228) );
        btnTop10.setForeground( Color.WHITE );
        add( btnTop10 );
        
        btnCambiarJugador = new JButton( "CAMBIAR JUGADOR" );
        btnCambiarJugador.setBackground( new Color(27,62,228) );
        btnCambiarJugador.setForeground( Color.WHITE );
        add( btnCambiarJugador );
        
        btnNuevo.addActionListener( this );
        btnNuevo.setActionCommand( "NUEVO" );
        btnReiniciar.addActionListener( this );
        btnReiniciar.setActionCommand( "REINICIAR" );
        btnTop10.addActionListener( this );
        btnTop10.setActionCommand( "TOP-10" );
        btnCambiarJugador.addActionListener( this );
        btnCambiarJugador.setActionCommand( "CAMBIAR JUGADOR" );
        
	}
	
	@Override
    public void actionPerformed( ActionEvent evento )
    {
        // TODO Auto-generated method stub
        if(evento.getActionCommand( ).equals( "NUEVO" ))
        {
        	if (!panelSur.ahiJugador()) {
        		String nombreJugador = JOptionPane.showInputDialog( principal, "Debe ingresar el nombre del jugador antes de empezar", 
                        "Cambiar Jugador", JOptionPane.QUESTION_MESSAGE );
         		if (nombreJugador != null) {
         			if (!nombreJugador.trim().isEmpty()) {
         		        panelSur.actualizarJugadorSur(nombreJugador);
         		        principal.reiniciarTablero();
         		        int tamanio = panelNorte.getTamanio();
         	        	int dificultad = panelNorte.getDificultad();
         	        	principal.nuevoTablero( tamanio, dificultad );
         		    } 
         		 else JOptionPane.showMessageDialog(principal, "Debe ingresar un nombre válido", "Error en Cambiar Nombre", JOptionPane.WARNING_MESSAGE);
         		}
         	}
        	else {
        		int tamanio = panelNorte.getTamanio();
 	        	int dificultad = panelNorte.getDificultad();
 	        	principal.nuevoTablero( tamanio, dificultad );
        	}
        	
        }
        else if (evento.getActionCommand( ).equals( "REINICIAR" ))
        {
        	principal.reiniciarTablero( );
        }
        else if (evento.getActionCommand( ).equals( "TOP-10" ))
        {
        	JList<String> lista = principal.mostrarTop10();
        	JDialogTop10 top10class = new JDialogTop10(principal);
            top10class.mostrarTop(lista);
            top10class.setVisible(true);
        }
        else if (evento.getActionCommand( ).equals( "CAMBIAR JUGADOR" ))
        {
        	String nombreJugador = JOptionPane.showInputDialog( principal, "Ingrese el nombre del jugador", 
                       "Cambiar Jugador", JOptionPane.QUESTION_MESSAGE );
        	if (nombreJugador != null) {
        		 if (!nombreJugador.trim().isEmpty()) {
        		        panelSur.actualizarJugadorSur(nombreJugador);
        		        principal.reiniciarTablero();
        		    } 
        		 else JOptionPane.showMessageDialog(principal, "Debe ingresar un nombre válido", "Error en Cambiar Nombre", JOptionPane.WARNING_MESSAGE);
        	}        	
        }
    }

}
