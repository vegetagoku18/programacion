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
    JLabel lblInformarError;
    JComboBox<Integer> cmbDecimales;
    Timer tmr;
    int contadorSegundos;
    int contadorMinutos;

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
        rbtSuma.setBounds(20, 50, 60, 40);
        rbtSuma.addItemListener(this);
        add(rbtSuma);

        rbtResta = new JRadioButton("-");
        rbtResta.setBounds(80, 50, 60, 40);
        rbtResta.addItemListener(this);
        add(rbtResta);

        rbtProducto = new JRadioButton("*");
        rbtProducto.setBounds(150, 50, 60, 40);
        rbtProducto.addItemListener(this);
        add(rbtProducto);

        rbtDivision = new JRadioButton("/");
        rbtDivision.setBounds(220, 50, 60, 40);
        rbtDivision.addItemListener(this);
        add(rbtDivision);

        grupoOperadores = new ButtonGroup();
        grupoOperadores.add(rbtSuma);
        grupoOperadores.add(rbtResta);
        grupoOperadores.add(rbtProducto);
        grupoOperadores.add(rbtDivision);

        btnCalcular = new JButton("Operación");
        btnCalcular.setBounds(20, 100, 100, 20);
        btnCalcular.addActionListener(this);
        add(btnCalcular);

        lblInformarError = new JLabel("");
        lblInformarError.setBounds(130, 100, 250, 20);
        add(lblInformarError);

        cmbDecimales = new JComboBox<>();
        cmbDecimales.setBounds(20, 130, 80, 30);
        for (int i = 0; i <= 5; i++) {
            cmbDecimales.addItem(i);
        }
        cmbDecimales.setSelectedItem(0);
        add(cmbDecimales);

        contadorSegundos = 0;
        contadorMinutos = 0;
        tmr = new Timer(1000, this);
        tmr.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tmr) {
            contadorSegundos++;
            if (contadorSegundos >= 60) {
                contadorSegundos = 0;
                contadorMinutos++;
            }
            this.setTitle(String.format("%2d:%2d", contadorMinutos, contadorSegundos));
        } else {
            try {

                float resultado = 0;
                resultado = Float.parseFloat(txfNumeroUno.getText().trim())
                        + Float.parseFloat(txfNumeroDos.getText().trim());
                lblIgual.setText(String.format("= %f0.2", resultado));
                lblInformarError.setText("");

            } catch (NumberFormatException nfe) {
                lblInformarError.setText("Debe rellenar todos los parámetros");
                lblInformarError.setForeground(Color.RED);
            }
        }
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
                    if (e.getSource() == rbtDivision) {
                        lblOperacion.setText("/");
                    } else {

                    }
                }
            }
        }
        lblIgual.setText("=");

    }
}
