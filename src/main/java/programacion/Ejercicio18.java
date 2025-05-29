package programacion;

import java.awt.event.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

// TODO traspasar ç, no mçcopiar.   
public class Ejercicio18 extends JFrame implements ActionListener, ItemListener {
    JComboBox<String> cbA;
    JComboBox<String> cbB;
    int cbBSeleccionado = 0;

    JButton btnAnhadir;
    JButton btnQuitar;
    JButton btnTraspasarAB;
    JButton btnTraspasarBA;

    JTextField txfAnhadir;
    JTextField txfBorrarTodo;

    JLabel lblCantidadA;
    JLabel lblSeleccionadoA;
    JLabel lblContador;

    Timer tmrReset;
    int contadorSegundos;

    public Ejercicio18() {
        super("Nose");
        setLayout(null);
        this.addWindowListener(new WindowHandler());

        cbA = new JComboBox<String>();
        cbA.setBounds(30, 30, 100, 25);
        cbA.setToolTipText("Lista de elementos de la lista A");
        cbA.addItemListener(this);
        add(cbA);

        cbB = new JComboBox<String>();
        cbB.setBounds(150, 30, 100, 25);
        cbB.setToolTipText("0");
        cbB.addItemListener(this);
        add(cbB);

        txfAnhadir = new JTextField();
        txfAnhadir.setBounds(30, 70, 100, 30);
        txfAnhadir.addActionListener(this);
        txfAnhadir.setToolTipText(
                "Caja de texto donde introducir elementos en la primera lista, para separar cada elemento se debe usar ';'");
        add(txfAnhadir);

        btnAnhadir = new JButton("Añadir");
        btnAnhadir.setBounds(30, 110, 100, 30);
        btnAnhadir.addActionListener(this);
        btnAnhadir.setToolTipText("Añadir elementos en la lista A");
        add(btnAnhadir);

        txfBorrarTodo = new JTextField();
        txfBorrarTodo.setBounds(150, 70, 100, 30);
        txfBorrarTodo.setToolTipText("Caja de texto que sirve como filtro para borrar elementos");
        add(txfBorrarTodo);

        btnQuitar = new JButton("Quitar");
        btnQuitar.setBounds(150, 110, 100, 30);
        btnQuitar.addActionListener(this);
        btnQuitar.setEnabled(false);
        btnQuitar.setToolTipText("Eliminar elementos en la lista A");
        btnQuitar.addMouseListener(new MouseHandler());
        add(btnQuitar);

        btnTraspasarAB = new JButton("A->B");
        btnTraspasarAB.setBounds(30, 150, 100, 30);
        btnTraspasarAB.addActionListener(this);
        btnTraspasarAB.setToolTipText("Pasar el elemento de la lista A a la B");
        btnTraspasarAB.setEnabled(false);
        add(btnTraspasarAB);

        btnTraspasarBA = new JButton("B->A");
        btnTraspasarBA.setBounds(150, 150, 100, 30);
        btnTraspasarBA.addActionListener(this);
        btnTraspasarBA.setToolTipText("Pasar el elemento de la lista B a A");
        btnTraspasarBA.setEnabled(false);
        add(btnTraspasarBA);

        lblCantidadA = new JLabel();
        lblCantidadA.setBounds(30, 200, 100, 30);
        lblCantidadA.setToolTipText("Cantidad de elementos de la lista A");
        add(lblCantidadA);

        lblSeleccionadoA = new JLabel();
        lblSeleccionadoA.setBounds(150, 200, 100, 30);
        lblSeleccionadoA.setToolTipText("Índice del elemento de la lista A seleccionado");
        add(lblSeleccionadoA);

        lblContador = new JLabel();
        lblContador.setBounds(260, 200, 100, 30);
        lblContador.setToolTipText("Contador que al llegar al minuto reinicia todos los valores");
        add(lblContador);

        tmrReset = new Timer(1000, this);
        tmrReset.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tmrReset) {
            contadorSegundos++;
            if (contadorSegundos >= 60) {
                contadorSegundos = 0;
                cbA.removeAllItems();
                cbB.removeAllItems();
                txfAnhadir.setText("");
                txfBorrarTodo.setText("");
                lblCantidadA.setText("");
                lblSeleccionadoA.setText("");
                tmrReset.restart();
            }
        } else {
            if (e.getSource() == btnAnhadir || e.getSource() == txfAnhadir) {
                String[] canciones = txfAnhadir.getText().split(";");
                for (String cancion : canciones) {
                    if (!cancion.trim().isEmpty()) {
                        cbA.addItem(cancion.trim());
                    }
                }
            } else {
                if (e.getSource() == btnQuitar) {
                    if (txfBorrarTodo.getText().trim().isEmpty()) {
                        cbA.removeItemAt(cbA.getSelectedIndex());
                    } else {
                        for (int i = cbA.getItemCount() - 1; i >= 0; i--) {
                            if (cbA.getItemAt(i).startsWith(txfBorrarTodo.getText())) {
                                cbA.removeItemAt(i);
                            }
                        }
                    }
                } else {
                    if (e.getSource() == btnTraspasarAB) {
                        cbB.addItem(cbA.getSelectedItem().toString());
                        cbA.removeItem(cbA.getSelectedItem());
                    } else {
                        if (e.getSource() == btnTraspasarBA) {
                            cbA.addItem(cbB.getSelectedItem().toString());
                            cbB.removeItem(cbB.getSelectedItem());
                        }
                    }
                }
            }
            tmrReset.restart();
            contadorSegundos = 0;
            lblSeleccionadoA.setText(cbA.getSelectedIndex() + "");
            lblCantidadA.setText(cbA.getItemCount() + "");
        }
         
        btnQuitar.setEnabled(cbA.getItemCount() != 0);
        btnTraspasarAB.setEnabled(cbA.getItemCount() != 0);
        btnTraspasarBA.setEnabled(cbB.getItemCount() != 0);
        lblContador.setText(contadorSegundos + "");
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == cbA) {
            lblSeleccionadoA.setText(cbA.getSelectedIndex() + "");
        } else {
            cbBSeleccionado = cbB.getSelectedIndex();
            cbB.setToolTipText(cbBSeleccionado + "");
        }
        tmrReset.restart();
        contadorSegundos = 0;
    }

    private class MouseHandler extends MouseAdapter { 
        @Override
        public void mouseEntered(MouseEvent e) {
            JButton button = (JButton) e.getSource();
            button.setForeground(Color.RED);
            tmrReset.restart();
            contadorSegundos = 0;
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JButton button = (JButton) e.getSource();
            button.setForeground(Color.BLACK);
            tmrReset.restart();
            contadorSegundos = 0;
        }

    }

    class WindowHandler extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Quieres salir del programa?", "Salir",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                e.getWindow().dispose();
            }
        }
    }
}
