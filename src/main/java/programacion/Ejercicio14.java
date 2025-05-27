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
import java.util.Scanner;

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
        txfNumeroDos.addActionListener(this);
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
        lblInformarError.setBounds(130, 100, 300, 20);
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
                switch (lblOperacion.getText()) {
                    case "+":
                        resultado = Float.parseFloat(txfNumeroUno.getText().trim())// Mejor double
                                + Float.parseFloat(txfNumeroDos.getText().trim());
                        break;
                    case "-":
                        resultado = Float.parseFloat(txfNumeroUno.getText().trim())
                                - Float.parseFloat(txfNumeroDos.getText().trim());
                        break;
                    case "*":
                        resultado = Float.parseFloat(txfNumeroUno.getText().trim())
                                * Float.parseFloat(txfNumeroDos.getText().trim());
                        break;
                    default:
                        resultado = Float.parseFloat(txfNumeroUno.getText().trim())
                                / Float.parseFloat(txfNumeroDos.getText().trim());
                        break;
                }
                if (lblOperacion.getText().equals("/") && txfNumeroDos.getText().equals("0")) {
                    lblInformarError.setForeground(Color.RED);
                    lblInformarError.setText("No se puede dividir un número entre 0");
                } else {
                    lblIgual.setText(String.format("= %." + decimales + "f", resultado));
                    lblInformarError.setText("");
                }

            } catch (NumberFormatException nfe) {
                lblInformarError.setText("Debe rellenar todos los parámetros correctamente");
                lblInformarError.setForeground(Color.RED);
            }
            System.out.println(System.getProperty("user.home") + "\\ejercicio14.txt");
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {  
        lblOperacion.setText(((JRadioButton) e.getSource()).getText());
        lblIgual.setText("=");
    }

    private void cargarConfiguracion() {
        File archivo = new File(rutaConfig);
        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
                System.out.println("Archivo de configuración creado.");
            }
            try (Scanner scanner = new Scanner(archivo)) {
                String campo1 = "";
                String campo2 = "";
                String operacion = "";
                while (scanner.hasNextLine()) {
                    String linea = scanner.nextLine();
                    String[] partes = linea.split("=", 2);
                    if (partes.length == 2) {
                        String clave = partes[0].trim();
                        String valor = partes[1].trim();

                        switch (clave) {
                            case "campo1":
                                campo1 = valor;
                                break;
                            case "campo2":
                                campo2 = valor;
                                break;
                            case "operacion":
                                operacion = valor;
                                break;
                        }
                    }
                }
                txfNumeroUno.setText(campo1);
                txfNumeroDos.setText(campo2);
                switch (operacion) {
                    case "+":
                        rbtSuma.setSelected(true);
                        break;
                    case "-":
                        rbtResta.setSelected(true);
                        break;
                    case "*":
                        rbtProducto.setSelected(true);
                        break;
                    default:
                        rbtDivision.setSelected(true);
                        break;
                }
            }

        } catch (IOException e) {
            System.out.println("No se pudo leer o crear el archivo de configuración.");
            e.printStackTrace();
        }
    }

    public void guardarConfiguracion() {
        File archivo = new File(rutaConfig);
        try (PrintWriter writer = new PrintWriter(archivo)) {
            writer.println("campo1=" + txfNumeroUno.getText());
            writer.println("campo2=" + txfNumeroDos.getText());
            writer.println("operacion=" + operacionSeleccionada());
            System.out.println("Guardado");
        } catch (IOException e) {
            System.out.println("No se pudo crear el archivo de configuración.");
            e.printStackTrace();
        }
    }

    public String operacionSeleccionada() {
        if (rbtSuma.isSelected()) {
            return "+";
        }
        if (rbtResta.isSelected()) {
            return "-";
        }
        if (rbtProducto.isSelected()) {
            return "*";
        }
        return "/";
    }

    class CierreVentana extends java.awt.event.WindowAdapter {
        public void windowClosing(WindowEvent e) {
            guardarConfiguracion();
        }
    }
}
