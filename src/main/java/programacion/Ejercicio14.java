package programacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Ejercicio14 extends JFrame implements ActionListener, ItemListener {
    JTextField txfNumeroUno;
    JLabel lblOperacion;
    JTextField txfNumeroDos;
    JLabel lblIgual;
    ButtonGroup grupoOperadores;
    JRadioButton rbtSuma;
    JRadioButton rbtResta;
    JRadioButton rbtProducto;
    JRadioButton rbtDivision;
    JButton btnCalcular;

    public Ejercicio14() {
        super("Calculadora");
        setLayout(null);

        txfNumeroUno = new JTextField();
        txfNumeroUno.addActionListener(this);
        txfNumeroUno.setBounds(20, 20, 80, 20);
        add(txfNumeroUno);

        lblOperacion = new JLabel("+");
        lblOperacion.setBounds(110, 20, 50, 20);
        add(lblOperacion);

        txfNumeroDos = new JTextField();
        txfNumeroDos.setBounds(130, 20, 80, 20);
        txfNumeroDos.addActionListener(null);
        add(txfNumeroDos);

        lblIgual = new JLabel("=");
        lblIgual.setBounds(220, 20, 50, 20);
        add(lblIgual);

        rbtSuma = new JRadioButton("+");
        rbtSuma.setSelected(true);
        rbtSuma.setBounds(20, 50, 60, 60);
        rbtSuma.addItemListener(this);
        add(rbtSuma);

        rbtResta = new JRadioButton("-");
        rbtResta.setBounds(80, 50, 60, 60);
        rbtResta.addItemListener(this);
        add(rbtResta);

        rbtProducto = new JRadioButton("*");
        rbtProducto.setBounds(150, 50, 60, 60);
        rbtProducto.addItemListener(this);
        add(rbtProducto);

        rbtDivision = new JRadioButton("/");
        rbtDivision.setBounds(220, 50, 60, 60);
        rbtDivision.addItemListener(this);
        add(rbtDivision);

        grupoOperadores = new ButtonGroup();
        grupoOperadores.add(rbtSuma);
        grupoOperadores.add(rbtResta);
        grupoOperadores.add(rbtProducto);
        grupoOperadores.add(rbtDivision);

        btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(280, 70, 100, 20);
        btnCalcular.addActionListener(this);
        add(btnCalcular);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        float resultado = 0;
        resultado = Float.parseFloat(txfNumeroUno.getText().trim()) + Float.parseFloat(txfNumeroDos.getText().trim());
        lblIgual.setText(String.format("= %f0.2", resultado));
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == rbtSuma) {
            lblOperacion.setText("+");
        } else {
            if (e.getSource() == rbtResta) {
                lblOperacion.setText("-");
            } else {
                if (e.getSource() == rbtProducto) {
                    lblOperacion.setText("*");
                } else {
                    lblOperacion.setText("/");
                }
            }
        }
    }
}
