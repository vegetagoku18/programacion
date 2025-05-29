package programacion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Ejercicio19 extends JFrame implements ActionListener, ItemListener {
    JCheckBox[] chbNumeros = new JCheckBox[49];
    int x = 60;
    int y = 60;
    int cantidadBotonesSeleccionados = 0;
    int[] numerosElegidos = new int[6];
    ArrayList<Integer> numerosAcertados = new ArrayList<Integer>();

    JButton btnJugar;
    JButton btnGuardar;
    JButton btnVer;
    JLabel lblExplicacion;

    Timer tmrTitulo;
    String tituloCompleto = "Lotería";
    int esperaContador;
    int posicion;
    int longitudActual = 1;
    boolean esperando = false;

    char[] division;

    public Ejercicio19() {
        super("Loteria");
        setLayout(null);

        for (int i = 1; i <= chbNumeros.length; i++) {
            JCheckBox numero = new JCheckBox(i + "");
            numero.setBounds(x, y, 50, 50);
            numero.addItemListener(this);
            chbNumeros[i - 1] = numero;
            x += 60;
            if (x > 420) {
                x = 60;
                y += 60;
            }
            add(numero);
        }

        btnJugar = new JButton("Jugar");
        btnJugar.setBounds(60, 30, 100, 30);
        btnJugar.addActionListener(this);
        btnJugar.setEnabled(false);
        add(btnJugar);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(320, 30, 100, 30);
        btnGuardar.addActionListener(this);
        btnGuardar.setEnabled(false);
        add(btnGuardar);

        lblExplicacion = new JLabel("Debes elegir 6 números");
        lblExplicacion.setBounds(170, 30, 300, 30);
        add(lblExplicacion);

        btnVer = new JButton("Ver Records");
        btnVer.setBounds(430, 30, 120, 30);
        btnVer.addActionListener(this);
        add(btnVer);

        tmrTitulo = new Timer(300, this);
        tmrTitulo.start();
        esperaContador = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnJugar) {
            for (JCheckBox jCheckBox : chbNumeros) {
                jCheckBox.setForeground(Color.BLACK);
            }
            for (int i = 0; i < numerosElegidos.length; i++) {
                int valor = (int) (Math.random() * 49 + 1);
                while (valor == numerosElegidos[0] || valor == numerosElegidos[1] || valor == numerosElegidos[2]
                        || valor == numerosElegidos[3] || valor == numerosElegidos[4]) {
                    valor = (int) (Math.random() * 49 + 1);
                }
                numerosElegidos[i] = valor;
                System.out.println(numerosElegidos[i]);
            }
            chbNumeros[numerosElegidos[0] - 1].setForeground(Color.RED);
            chbNumeros[numerosElegidos[1] - 1].setForeground(Color.RED);
            chbNumeros[numerosElegidos[2] - 1].setForeground(Color.RED);
            chbNumeros[numerosElegidos[3] - 1].setForeground(Color.RED);
            chbNumeros[numerosElegidos[4] - 1].setForeground(Color.RED);
            chbNumeros[numerosElegidos[5] - 1].setForeground(Color.RED);

            for (int i = 0; i < numerosElegidos.length; i++) {
                if (chbNumeros[numerosElegidos[i] - 1].isSelected()) {
                    chbNumeros[numerosElegidos[i] - 1].setForeground(Color.GREEN);
                    numerosAcertados.add(Integer.parseInt(chbNumeros[numerosElegidos[i] - 1].getText()));
                }
            }
            btnGuardar.setEnabled(true);
        }
        if (e.getSource() == btnGuardar) {
            Ejercicio19Guardar secundario = new Ejercicio19Guardar(this, numerosAcertados);
            secundario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            secundario.setSize(600, 600);
            secundario.setVisible(true);
        }
        if (e.getSource() == btnVer) {
            System.out.println("prueba");
            Ejercicio19Ver secundario = new Ejercicio19Ver(this);
            secundario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            secundario.setSize(600, 600);
            secundario.setVisible(true);
        }

        if (e.getSource() == tmrTitulo) {
            if (esperando) {
                esperaContador++;
                if (esperaContador >= 4) {
                    esperando = false;
                    esperaContador = 0;
                    longitudActual = 1;
                }
                return;
            }
            if (longitudActual <= tituloCompleto.length()) {
                String nuevoTitulo = tituloCompleto.substring(tituloCompleto.length() - longitudActual);
                setTitle(nuevoTitulo);
                longitudActual++;
            } else {
                esperando = true;
            }
        }

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JCheckBox numero = (JCheckBox) e.getSource();
        if (numero.isSelected()) {
            cantidadBotonesSeleccionados++;
        } else {
            cantidadBotonesSeleccionados--;
        }
        btnJugar.setEnabled(cantidadBotonesSeleccionados == 6);
    }
}
