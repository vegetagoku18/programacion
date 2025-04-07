package programacion;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ejercicio13 extends JFrame implements ActionListener, KeyListener {
    JTextField txfRed;
    JTextField txfGreen;
    JTextField txfBlue;
    JButton btnColor;

    public Ejercicio13() {
        super("Ejercicio 13");
        this.setLayout(new FlowLayout());
        JLabel lblInfo = new JLabel("Valores RGB entre 0 y 255");
        lblInfo.setBounds(0, 0, 150, 50);
        // Rojo
        JLabel lblRed = new JLabel("Valor Rojo:");
        lblRed.setBounds(0, 50, 100, 50);
        txfRed = new JTextField("Valor Rojo");
        txfRed.setColumns(10);
        txfRed.setToolTipText("Color Rojo");
        // Verde
        JLabel lblGreen = new JLabel("Valor Verde:");
        lblGreen.setBounds(0, 100, 100, 50);
        txfGreen = new JTextField("Valor Verde");
        txfGreen.setColumns(10);
        txfGreen.setToolTipText("Color Verde");
        // Azul
        JLabel lblBlue = new JLabel("Valor Azul:");
        lblBlue.setBounds(0, 150, 100, 50);
        txfBlue = new JTextField("Valor Azul ");
        txfBlue.setColumns(10);
        txfBlue.setToolTipText("Color Azul");
        txfBlue.addKeyListener(this);

        btnColor = new JButton("Cambiar color boton");
        btnColor.setToolTipText("Cambiar");
        btnColor.addActionListener(this);

        this.add(lblInfo);
        this.add(lblRed);
        this.add(txfRed);
        this.add(lblGreen);
        this.add(txfGreen);
        this.add(lblBlue);
        this.add(txfBlue);
        this.add(btnColor);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (Integer.parseInt(txfRed.getText()) < 0 || Integer.parseInt(txfRed.getText()) > 255
                    || Integer.parseInt(txfBlue.getText()) < 0 || Integer.parseInt(txfBlue.getText()) > 255
                    || Integer.parseInt(txfGreen.getText()) < 0 || Integer.parseInt(txfGreen.getText()) > 255) {
                        throw new NumberFormatException();
            }
            if ((e.getModifiers()&InputEvent.CTRL_DOWN_MASK) == InputEvent.CTRL_DOWN_MASK) {
                
            }
            if (e.getSource() == txfBlue) {
                if (e.getModifiers()&) {
                    
                }
            }
            btnColor.setBackground(new Color(Integer.parseInt(txfRed.getText()),
                    Integer.parseInt(txfGreen.getText()), Integer.parseInt(txfBlue.getText())));
        } catch (NullPointerException npe) {
            this.setTitle("Algun/os parametro/s están vacíos");
        } catch (NumberFormatException nfe) {
            this.setTitle("Algun/os parametro/s no son válidos");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
