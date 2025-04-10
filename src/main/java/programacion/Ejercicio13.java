package programacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ejercicio13 extends JFrame implements ActionListener {
    // TODO fronteras en colores
  
    JLabel lblInfo;
    JTextField txfRed;
    JLabel lblRed;
    JTextField txfGreen;
    JLabel lblGreen;
    JTextField txfBlue;
    JLabel lblBlue;
    JButton btnColor;
    JLabel lblImagen;
    JTextField txfImagen;
    JLabel lblMuestraImagen;

    public Ejercicio13() {
        super("Ejercicio 13");
        this.setLayout(new FlowLayout());
        lblInfo = new JLabel("Valores RGB entre 0 y 255");
        this.add(lblInfo);
        this.addWindowListener(new CierreVentana());

        // txfRojo
        lblRed = new JLabel("Valor Rojo:");
        this.add(lblRed);

        txfRed = new JTextField();
        txfRed.setColumns(10);
        txfRed.setToolTipText("Color Rojo");
        this.add(txfRed);

        // txfVerde
        JLabel lblGreen = new JLabel("Valor Verde:");
        this.add(lblGreen);

        txfGreen = new JTextField();
        txfGreen.setColumns(10);
        txfGreen.setToolTipText("Color Verde");
        this.add(txfGreen);

        // txfAzul
        JLabel lblBlue = new JLabel("Valor Azul:");
        this.add(lblBlue);

        txfBlue = new JTextField();
        txfBlue.setColumns(10);
        txfBlue.setToolTipText("Color Azul");
        txfBlue.addActionListener(this);
        this.add(txfBlue);

        // Boton Color
        btnColor = new JButton("Cambiar color boton");
        btnColor.setToolTipText("Cambiar");
        btnColor.addActionListener(this);
        this.add(btnColor);

        // txfImagen
        // C:\\Users\\Carlos\\Pictures\\Bocanegra.jpg
        JLabel lblImagen = new JLabel("Ruta de la imagen");
        this.add(lblImagen);

        txfImagen = new JTextField();
        txfImagen.setToolTipText("Ruta de la Imagen");
        txfImagen.setColumns(20);
        txfImagen.addActionListener(this);
        this.add(txfImagen);

        lblMuestraImagen = new JLabel();
        this.add(lblMuestraImagen);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (Integer.parseInt(txfRed.getText().trim()) < 0 || Integer.parseInt(txfRed.getText().trim()) > 255
                    || Integer.parseInt(txfBlue.getText().trim()) < 0 || Integer.parseInt(txfBlue.getText().trim()) > 255
                    || Integer.parseInt(txfGreen.getText().trim()) < 0 || Integer.parseInt(txfGreen.getText().trim()) > 255) {
                throw new NumberFormatException();
            }
            if (e.getSource() != txfImagen) {
                if ((e.getModifiers() & ActionEvent.CTRL_MASK) == ActionEvent.CTRL_MASK) {
                    btnColor.setForeground(new Color(Integer.parseInt(txfRed.getText()),
                            Integer.parseInt(txfGreen.getText()), Integer.parseInt(txfBlue.getText())));
                    System.out.println("Ctrl");
                } else {
                    btnColor.setBackground(new Color(Integer.parseInt(txfRed.getText()),
                            Integer.parseInt(txfGreen.getText()), Integer.parseInt(txfBlue.getText())));
                    System.out.println("No Ctrl");

                }
            } else {
                lblMuestraImagen.setIcon(new ImageIcon(txfImagen.getText()));
            }
            this.setTitle("Ejercicio 13");
        } catch (NumberFormatException nfe) {
            this.setTitle("Algun/os parametro/s no son válidos");
        }
    }

    class CierreVentana extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            int res = JOptionPane.showConfirmDialog(null, "¿Quieres salir del programa?", "Salir",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (res == JOptionPane.YES_OPTION) {
                e.getWindow().dispose();
            }
        }
    }
}
