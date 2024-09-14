package co.edu.unbosque.controller;

import co.edu.unbosque.view.PanelPrincipal;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    private JButton[][] botones; 
    private Color turno = Color.BLUE; 
    private PanelPrincipal panelPrincipal;

    public Controller(JButton[][] botones, PanelPrincipal panelPrincipal) {
        this.botones = botones;
        this.panelPrincipal = panelPrincipal;
        inicializarBotones();
    }

  
    private void inicializarBotones() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                botones[i][j].addActionListener(this);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton botonPresionado = (JButton) e.getSource();

      
        if (botonPresionado.getBackground().equals(Color.WHITE)) {
            botonPresionado.setBackground(turno);
            
            if (verificarGanador()) {
                String colorGanador = (turno == Color.BLUE) ? "Azul" : "Rojo";
                panelPrincipal.mostrarMensajeGanador(colorGanador);
                reiniciarJuego();
            } else if (verificarEmpate()) {
                panelPrincipal.mostrarMensajeEmpate();
                reiniciarJuego();
            } else {
                cambiarTurno();
            }
        }
    }

   
    private void cambiarTurno() {
        turno = (turno == Color.BLUE) ? Color.RED : Color.BLUE;
    }

   
    private boolean verificarGanador() {
        
        for (int i = 0; i < 4; i++) {
            if (verificarLinea(botones[i][0].getBackground(), botones[i][1].getBackground(), 
                               botones[i][2].getBackground(), botones[i][3].getBackground()) ||
                verificarLinea(botones[0][i].getBackground(), botones[1][i].getBackground(), 
                               botones[2][i].getBackground(), botones[3][i].getBackground())) {
                return true;
            }
        }

      
        return verificarLinea(botones[0][0].getBackground(), botones[1][1].getBackground(), 
                              botones[2][2].getBackground(), botones[3][3].getBackground()) ||
               verificarLinea(botones[0][3].getBackground(), botones[1][2].getBackground(), 
                              botones[2][1].getBackground(), botones[3][0].getBackground());
    }

 
    private boolean verificarLinea(Color c1, Color c2, Color c3, Color c4) {
        return !c1.equals(Color.WHITE) && c1.equals(c2) && c2.equals(c3) && c3.equals(c4);
    }

    
    private boolean verificarEmpate() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (botones[i][j].getBackground().equals(Color.WHITE)) {
                    return false;
                }
            }
        }
        return !verificarGanador(); 
    }

   
    private void reiniciarJuego() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                botones[i][j].setBackground(Color.WHITE);
            }
        }
        turno = Color.BLUE;  
    }

	public void iniciar() {
		
		
	}
}
