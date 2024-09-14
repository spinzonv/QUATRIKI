package co.edu.unbosque.view;


import javax.swing.JFrame;


public class VentanaPrincipal extends JFrame {
	private PanelPrincipal panelPrincipal;
	public VentanaPrincipal() {
		
		setTitle("Cuatriki :D");
		setSize(750, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelPrincipal = new PanelPrincipal();
		add(panelPrincipal);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		
	}
	
	public Object getPanelPrincipal() {
		return null;
		
	}
}
