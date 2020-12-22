package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RetoTablero extends JPanel {
	public RetoTablero(int capacidad) {
		JButton[][] botones;
		botones = new JButton[capacidad][capacidad];

		// SE CREA UN OYENTE DE ACCIONES Y SE LE PASA EL
		// PANEL COMO ARGUMENTO
		OyenteAcciones oyenteAcciones = new OyenteAcciones(this);

		for (int i = 0; i < botones.length; i++) {
			for (int j = 0; j < botones[i].length; j++) {
				botones[i][j] = new JButton();
				// SE CREAN LOS BOTONES Y SE ESTABLECE SU TAMAÑO PREFERIDO
				botones[i][j].setPreferredSize(new Dimension(50, 50));
				if ((i + j + 1) % 2 == 0) {
					botones[i][j].setBackground(Color.BLACK);
				}
				botones[i][j].addActionListener(oyenteAcciones);
				add(botones[i][j]);
			}
			setLayout(new GridLayout(capacidad, capacidad));

		}

	}

	class OyenteAcciones implements ActionListener {
		private JPanel panel;

		public OyenteAcciones(JPanel panel) {
			this.panel = panel;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// Se obtiene el color del boton pulsado
			JButton boton = (JButton) e.getSource();
			String color = "blanco";
			if (boton.getBackground() == Color.BLACK) {
				color = "negro";
			}
			JOptionPane.showMessageDialog(panel, "Se ha pulsado un cuadro con color" + color, "Cuadro pulsado",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

	public static void main(String[] args) {
		JFrame ventana = new JFrame("Tablero de ajedrez");
		RetoTablero tablero = new RetoTablero(8);
		ventana.add(tablero);
		ventana.pack();
		ventana.setVisible(true);
	}
}
