package programacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

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

    private final String rutaConfig = System.getProperty("user.home") + "\\ejercicio14.properties";

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
        lblIgual.setBounds(220, 20, 80, 20);
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

        cargarConfiguracion();
        addWindowListener(new CierreVentana());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tmr) {
            contadorSegundos++;
            if (contadorSegundos >= 60) {
                contadorSegundos = 0;
                contadorMinutos++;
            }
            this.setTitle(String.format("%02d:%02d", contadorMinutos, contadorSegundos));
        } else {
            try {
                int decimales = Integer.parseInt(cmbDecimales.getSelectedItem().toString());
                float resultado = 0;
                resultado = Float.parseFloat(txfNumeroUno.getText().trim())
                        + Float.parseFloat(txfNumeroDos.getText().trim());
                lblIgual.setText(String.format("= %." + decimales + "f", resultado));
                lblInformarError.setText("");

            } catch (NumberFormatException nfe) {
                lblInformarError.setText("Debe rellenar todos los parámetros");
                lblInformarError.setForeground(Color.RED);
            }
            System.out.println(System.getProperty("user.home") + "\\ejercicio14.txt");
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

    private void cargarConfiguracion() {
        File archivo = new File(rutaConfig);
        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
                System.out.println("Archivo de configuración creado.");
            }
            try (FileInputStream fis = new FileInputStream(archivo)) {
                Properties props = new Properties();
                props.load(fis);
                txfNumeroUno.setText(props.getProperty("campo1", ""));
                txfNumeroDos.setText(props.getProperty("campo2", ""));
                cmbDecimales.setSelectedItem(props.getProperty("operacion", ""));
                //lblIgual.setText("= %." + decimales + "f", resultado)"");
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer o crear el archivo de configuración.");
            e.printStackTrace();
        }
    }

    public void guardarConfiguracion() {
        try (FileOutputStream fos = new FileOutputStream(rutaConfig)) {
            Properties props = new Properties();
            props.setProperty("campo1", txfNumeroUno.getText());
            props.setProperty("campo2", txfNumeroDos.getText());
            props.setProperty("operacion", cmbDecimales.getSelectedItem().toString());
            props.store(fos, "Configuración de la última sesión");
            System.out.println("Guardado");
        } catch (IOException e) {
            System.out.println("No se pudo crear");
        }
    }

    class CierreVentana extends java.awt.event.WindowAdapter {
        public void windowClosing(WindowEvent e) {
            guardarConfiguracion();
        }
    }
}
