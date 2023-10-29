package Interfaz;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;


public class PanelLuces extends JPanel implements MouseListener{
	
	private boolean[][] tablero;
	private PanelNorte panelNorte;
	private VentanaPrincipal principal;
	
	public PanelLuces(boolean[][] tableroP, PanelNorte pPanelNorte, VentanaPrincipal pPrincipal) {
		tablero = tableroP;
		panelNorte = pPanelNorte;
		principal = pPrincipal;
		
		add(new JLabel(  "                                   " ));
        addMouseListener( this );
        
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int ancho = getWidth();
		int alto= getHeight( );
		int tamanio = panelNorte.getTamanio();
		int anchoRect= ancho/tamanio; 
        int altoRect= alto/tamanio;
        ImageIcon iconoLuces= new ImageIcon( "./data/luz.png" );
        Image img = iconoLuces.getImage();
        for( int i = 0; i < tamanio; i++ )
        {
            for( int j = 0; j < tamanio; j++ )
            {
                Rectangle2D.Double rect= new Rectangle2D.Double( i*anchoRect , j*altoRect, anchoRect, altoRect );
                
                if(tablero[i][j]==true)
                {	
                    g.setColor( Color.YELLOW );
                }
                else
                {
                    g.setColor( Color.GRAY );
                }
                g2d.fill(rect);
                int imgWidth = img.getWidth(null);
                int imgHeight = img.getHeight(null);
                int x = (int) (rect.x + (rect.width - imgWidth) / 2);
                int y = (int) (rect.y + (rect.height - imgHeight) / 2);
                g2d.drawImage(img, x, y, imgWidth, imgHeight, null);
                g.setColor( Color.WHITE );
                g2d.draw( rect );
            }
        }
	}
	
	public void actualizar() {
		repaint();
	}
	
	public void actualizarReinciar() {
		int tamanio = panelNorte.getTamanio();
		for (int i = 0; i < tamanio; i++) {
	        for (int j = 0; j < tamanio; j++) {
	            tablero[i][j] = true;
	        }
	    }
	    
		repaint();
	}
    
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int click_x = e.getX();
        int click_y = e.getY();
        int[] casilla = convertirCoordenadasACasilla(click_x, click_y);
        
        if(tablero[casilla[0]][casilla[1]]==true)
        {
            tablero[casilla[0]][casilla[1]]=false;
        }
        else
        {
            tablero[casilla[0]][casilla[1]]=true;
        }
        
        principal.jugarJ(casilla[0], casilla[1]);
        repaint();
		
	}
	
    private int[] convertirCoordenadasACasilla(int x, int y)
    {
    int ladoTablero = panelNorte.getTamanio();
    int altoPanelTablero = getHeight();
    int anchoPanelTablero = getWidth();
    int altoCasilla = altoPanelTablero / ladoTablero;
    int anchoCasilla = anchoPanelTablero / ladoTablero;
    int fila = (int) (y / altoCasilla);
    int columna = (int) (x / anchoCasilla);
    return new int[] { columna, fila };
    }

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
