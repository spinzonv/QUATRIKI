package co.edu.unbosque.view;

import javax.swing.*;
import javax.swing.border.Border;
import co.edu.unbosque.controller.Controller;
import co.edu.unbosque.model.Historial;
import co.edu.unbosque.model.Jugador;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PanelPrincipal extends JPanel {

    private JButton[][] botones; 
    private Controller controller;

    private JLabel lblTitulo;
    private JLabel lblJugador1;
    private JLabel lblJugador2;
    private JLabel lblEmpate;
    private JLabel lblHistorial;

    private JTextArea jtaJugador1;
    private JTextArea jtaJugador2;
    private JTextArea jtaEmpates;
    private JTextArea jtaResultados;

    private Historial historial;
    private long tiempoInicio;
    private boolean primerClickRealizado;

    private Jugador jugador1 = new Jugador("Jugador 1", "Azul");
    private Jugador jugador2 = new Jugador("Jugador 2", "Rojo");
    private int empates = 0;

    private JButton btnMostrarHistorial;  
    private Image imagenFondo; 
    public PanelPrincipal() {
       
        imagenFondo = new ImageIcon("src/co/edu/unbosque/view/Imgfondoo.png").getImage(); // Asegúrate de ajustar la ruta a tu imagen

        setLayout(null); 
        primerClickRealizado = false;

        Font fuenteTM24 = new Font("Times New Roman", Font.PLAIN, 24);
        Font fuenteTM22 = new Font("Times New Roman", Font.PLAIN, 22);
        Font fuenteTM16 = new Font("Times New Roman", Font.PLAIN, 16);

        lblTitulo = new JLabel("El mejor cuaTriki del mundo :)");
        lblTitulo.setFont(fuenteTM24);
        lblTitulo.setBounds(230, 10, 310, 100);
        lblTitulo.setForeground(Color.white);
        add(lblTitulo);

        lblJugador1 = new JLabel("Jugador 1: ");
        lblJugador1.setFont(fuenteTM22);
        lblJugador1.setBounds(260, 80, 310, 100);
        lblJugador1.setForeground(Color.BLUE);
        add(lblJugador1);

        lblJugador2 = new JLabel("Jugador 2: ");
        lblJugador2.setFont(fuenteTM22);
        lblJugador2.setBounds(260, 150, 310, 100);
        lblJugador2.setForeground(Color.RED);
        add(lblJugador2);

        lblEmpate = new JLabel("Empates: ");
        lblEmpate.setFont(fuenteTM22);
        lblEmpate.setBounds(260, 220, 310, 100);
        lblEmpate.setForeground(Color.white);
        add(lblEmpate);

        lblHistorial = new JLabel("Partidas Jugadas :D");
        lblHistorial.setFont(fuenteTM22);
        lblHistorial.setBounds(100, 620, 310, 100);
        lblHistorial.setForeground(Color.white);
        add(lblHistorial);

        Border bordeNegro = BorderFactory.createLineBorder(Color.black, 2);

        jtaJugador1 = new JTextArea("");
        jtaJugador1.setBounds(360, 110, 100, 40);
        jtaJugador1.setFont(fuenteTM22);
        jtaJugador1.setBorder(bordeNegro);
        jtaJugador1.setEditable(false);
        jtaJugador1.setBackground(Color.gray);
        add(jtaJugador1);

        jtaJugador2 = new JTextArea("");
        jtaJugador2.setBounds(360, 180, 100, 40);
        jtaJugador2.setFont(fuenteTM22);
        jtaJugador2.setBorder(bordeNegro);
        jtaJugador2.setEditable(false);
        jtaJugador2.setBackground(Color.gray);
        add(jtaJugador2);

        jtaEmpates = new JTextArea("");
        jtaEmpates.setBounds(360, 250, 100, 40);
        jtaEmpates.setFont(fuenteTM22);
        jtaEmpates.setBorder(bordeNegro);
        jtaEmpates.setBackground(Color.gray);
        jtaEmpates.setEditable(false);
        add(jtaEmpates);


        
        jtaResultados = new JTextArea("");
        jtaResultados.setFont(new Font("Serif", Font.PLAIN, 22));
        jtaResultados.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
        jtaResultados.setEditable(false);
        jtaResultados.setBackground(Color.GRAY);

  
        JScrollPane scrollPane = new JScrollPane(jtaResultados);
        scrollPane.setBounds(100, 700, 550, 250);

        
        add(scrollPane);
    


        botones = new JButton[4][4];


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                botones[i][j] = new JButton();
                botones[i][j].setBounds(100 + j * 110, 100 + i * 110, 100, 100);
                botones[i][j].setBackground(Color.WHITE); 
                
                botones[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton button = (JButton) e.getSource();
                        handleButtonPress(button);
                    }
                });

                add(botones[i][j]); 
            }
        }

        
        historial = new Historial();

       
        btnMostrarHistorial = new JButton("Mostrar Historial");
        btnMostrarHistorial.setFont(fuenteTM16);
        btnMostrarHistorial.setBounds(500, 655, 150, 40);
        btnMostrarHistorial.setBackground(Color.GRAY);
        add(btnMostrarHistorial);

        btnMostrarHistorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaHistorial();
            }
        });

      
        controller = new Controller(botones, this);
        controller.iniciar(); 
    }

    private void mostrarVentanaHistorial() {
        
        JFrame ventanaHistorial = new JFrame("Historial de Partidas");
        ventanaHistorial.setSize(500, 500);
        ventanaHistorial.setResizable(false);
        ventanaHistorial.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaHistorial.setLocationRelativeTo(null);

       
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

      
        try (BufferedReader br = new BufferedReader(new FileReader("src/co/edu/unbosque/model/persistence/registros.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                textArea.append(linea + "\n");
            }
        } catch (IOException e) {
            textArea.setText("Error al leer el archivo: " + e.getMessage());
        }

     
        JScrollPane scrollPane = new JScrollPane(textArea);
        ventanaHistorial.add(scrollPane);

      
        ventanaHistorial.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
       
        if (imagenFondo != null) {
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }

        
        int panelWidthAvailable = getWidth();
        int panelHeightAvailable = getHeight();
        int buttonSize = 70;
        int padding = 5; 
        int panelWidth = 4 * buttonSize + 3 * padding; 
        int panelHeight = 4 * buttonSize + 3 * padding; 

        int xOffset = (panelWidthAvailable - panelWidth) / 2;
        int yOffset = (panelHeightAvailable - panelHeight) / 2;

        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                botones[i][j].setBounds(xOffset + j * (buttonSize + padding), yOffset + i * (buttonSize + padding), buttonSize, buttonSize);
            }
        }
    }

    public void mostrarMensajeGanador(String color) {
        JOptionPane.showMessageDialog(this, "¡Ganó " + color + "!");
        if (color.equals(jugador1.getColor())) {
            jugador1.incrementarVictorias();
            jtaJugador1.setText(String.valueOf(jugador1.getVictorias()));
        } else if (color.equals(jugador2.getColor())) {
            jugador2.incrementarVictorias();
            jtaJugador2.setText(String.valueOf(jugador2.getVictorias()));
        }

      
        historial.agregarResultado(color, tiempoInicio);
        actualizarResultados();
        reiniciarJuego();
    }

    public void mostrarMensajeEmpate() {
        JOptionPane.showMessageDialog(this, "¡Empate!");
        jtaEmpates.append("¡Empate!\n");

        empates++;
        jtaEmpates.setText(String.valueOf(empates));

        historial.agregarResultado("Empate", tiempoInicio);
        actualizarResultados();
        reiniciarJuego();
    }

    private void handleButtonPress(JButton button) {
        if (!primerClickRealizado) {
            tiempoInicio = System.currentTimeMillis(); 
            primerClickRealizado = true; 
        }
        
       
    }

    private void reiniciarJuego() {
        primerClickRealizado = false; 
        tiempoInicio = 0; 
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                botones[i][j].setEnabled(true);
                botones[i][j].setBackground(Color.WHITE); 
            }
        }
    }

    private void actualizarResultados() {
        jtaResultados.setText(""); 
        for (String resultado : historial.obtenerHistorial()) {
            jtaResultados.append(resultado + "\n");
        }
    }
}

