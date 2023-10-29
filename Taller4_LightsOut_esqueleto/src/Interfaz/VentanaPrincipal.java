package Interfaz;

import java.awt.BorderLayout;
import java.io.File;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import uniandes.dpoo.taller4.modelo.RegistroTop10;
import uniandes.dpoo.taller4.modelo.Tablero;
import uniandes.dpoo.taller4.modelo.Top10;


public class VentanaPrincipal extends JFrame{
	
	private PanelSur panelSur;
	private PanelEste panelEste;
	private PanelNorte panelNorte;
	private PanelLuces panelLuces;
	private Tablero tablero;
	private Top10 top10;
	private JList<String> Jlista_nuevo;
	private Collection<RegistroTop10> registros;
	private boolean ejecutadoNuevo = false;
	
	public VentanaPrincipal()
    {
        setSize( 550, 550 );
        setTitle( "LightsOut" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setResizable( true );
        setLayout( new BorderLayout( ) );
        
        panelSur = new PanelSur(  );
        add(panelSur, BorderLayout.SOUTH);
        
        panelNorte = new PanelNorte( this );
        add(panelNorte, BorderLayout.NORTH);
        
        try
        {
        	tablero = new Tablero( 5 ); //SE INICILIZA EN 5X5
        	top10 = new Top10();
        	File archivo = new File("./data/top10.csv");
    		top10.cargarRecords(archivo);
        }
        catch( Exception e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        panelEste = new PanelEste( this , panelNorte, panelSur);
        add(panelEste, BorderLayout.EAST);
        
        panelLuces = new PanelLuces( tablero.darTablero(), panelNorte , this );
        add(panelLuces, BorderLayout.CENTER);
        
    }
	
	public void nuevoTablero(int tamanio, int dificultad) {
		tablero.reiniciar();
		new Tablero(tamanio);
		tablero.desordenar(dificultad);
		panelLuces.actualizar();
		panelSur.actualizar( tablero.darJugadas() );
		ejecutadoNuevo = true;
	}
	
	public void reiniciarTablero(  ) {
		tablero.reiniciar();
		panelLuces.actualizarReinciar();
		panelSur.actualizar( tablero.darJugadas() );
		ejecutadoNuevo = false;
		
	}
	
	public JList<String> mostrarTop10() {
		Jlista_nuevo = new JList<String>();
		registros = top10.darRegistros();
		
		DefaultListModel<String> modeloLista = new DefaultListModel<>();

		int i = 1;
		for (RegistroTop10 registro : registros) {
			String texto = registro.toString();
		    modeloLista.addElement(i+" "+texto);
		    i++;
		}

		Jlista_nuevo.setModel(modeloLista);
		
		return Jlista_nuevo;
	}
	
	public void jugarJ(int x, int y) {
		tablero.jugar(x, y);
		
		if (ejecutadoNuevo) {
			if (tablero.tableroIluminado()) {
				String nombre = panelSur.getTextJugador().getText();
				int puntos = tablero.calcularPuntaje();
				top10.agregarRegistro(nombre, puntos);
				reiniciarTablero();
				if (top10.esTop10(puntos)) {
					top10.agregarRegistro(nombre, puntos);
					JOptionPane.showMessageDialog(this, 
							"Felicitaciones "+nombre+"has acumulado "+puntos+" puntos entraste al top 10", "Juego finalizado", 
							JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(this, 
							"Felicitaciones "+nombre+"has acumulado "+puntos+" puntos", "Juego finalizado", 
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
	
	public void actualizarTablero() {
		panelLuces.actualizar();
		panelSur.actualizar( tablero.darJugadas() );
	}
	
	public static void main(String[] args)
    {
        VentanaPrincipal ventana = new VentanaPrincipal( );
        ventana.setLocationRelativeTo( null );
        ventana.setVisible( true );
    }
}
