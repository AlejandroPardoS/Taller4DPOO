package Interfaz;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PanelNorte extends JPanel implements ActionListener{
	
	private JLabel lblTamanio;
	private JLabel lblDificultad;
	private JRadioButton cbFacil;
	private JRadioButton cbMedio;
	private JRadioButton cbDificil;
	private JComboBox<String> comBTamanio;
	private ButtonGroup buttonGroup;
	private ButtonModel selectedButton;
	private VentanaPrincipal principal;
	
	public PanelNorte(VentanaPrincipal pPrincipal) {
		principal = pPrincipal;
		
		setLayout( new GridLayout( 1,6 ) );
				
		lblTamanio = new JLabel( "Tamaño: " );
		lblTamanio.setForeground( Color.WHITE );
        add( lblTamanio );
        
        String tamanios[] = {
        		"5x5",
        		"4x4",
        		"3x3"
        };
        comBTamanio = new JComboBox<String>(tamanios);
        comBTamanio.setBackground( Color.WHITE );
        comBTamanio.setForeground( Color.BLACK );
        add( comBTamanio );
        comBTamanio.addActionListener(comBTamanio);
        comBTamanio.addActionListener( this );
        
        lblDificultad = new JLabel( "Dificultad: " );
        lblDificultad.setForeground( Color.WHITE );
        add( lblDificultad );
        
        buttonGroup = new ButtonGroup();
        
        cbFacil= new JRadioButton( "Facil" );
        cbFacil.setSelected( true );
        cbFacil.setBackground(new Color(27,62,228));
        cbFacil.setForeground( Color.WHITE );
        buttonGroup.add( cbFacil );
        add( cbFacil );
        
        cbMedio= new JRadioButton( "Medio" );
        cbMedio.setBackground(new Color(27,62,228));
        cbMedio.setForeground( Color.WHITE );
        buttonGroup.add( cbMedio );
        add( cbMedio );
        
        cbDificil= new JRadioButton( "Dificil" );
        cbDificil.setBackground(new Color(27,62,228));
        cbDificil.setForeground( Color.WHITE );
        buttonGroup.add( cbDificil );
        add( cbDificil );
        
        setBackground( new Color(27,62,228) );
	}
	public int getTamanio() {
		String selectedValue = (String) comBTamanio.getSelectedItem();
		int retorno;
		if (selectedValue.equals("5x5")) {
			principal.actualizarTablero(); 
			retorno = 5;}
		else if (selectedValue.equals("4x4")) {
			principal.actualizarTablero(); 
			retorno = 4;}
		else if (selectedValue.equals("3x3")) {
			principal.actualizarTablero(); 
			retorno = 3;}
		else retorno = 0;
		return retorno;
	}
	
	public int getDificultad() {
		selectedButton = buttonGroup.getSelection();
		if (selectedButton == cbFacil.getModel()) 
			return 5;
		else if (selectedButton == cbMedio.getModel()) 
			return 10;
		else //es dificil
			return 15;

	}

	@Override
    public void actionPerformed( ActionEvent evento )
    {
        // TODO Auto-generated method stub
		principal.reiniciarTablero();
    }
}
