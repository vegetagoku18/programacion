package programacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Ejercicio19Guardar extends JDialog implements ActionListener {

    JLabel lblNombre;
    JTextField txfNombre;
    JButton btnGuardar;
    ArrayList<Integer> numerosCorrectos;

    public Ejercicio19Guardar(Ejercicio19 principal, ArrayList<Integer> numerosAcertados) {
        super(principal);
        setLayout(null);

        numerosCorrectos = numerosAcertados;

        lblNombre = new JLabel("Nombre: ");
        lblNombre.setBounds(20, 30, 70, 30);
        add(lblNombre);

        txfNombre = new JTextField();
        txfNombre.setBounds(80, 30, 100, 30);
        add(txfNombre);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(200, 30, 100, 30);
        btnGuardar.addActionListener(this);
        add(btnGuardar);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (txfNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes introducir tu nombre");
        } else {
            String ruta = System.getProperty("user.home") + "\\records.txt";
            File archivo = new File(ruta);
            System.out.println(ruta);
            try (PrintWriter pw = new PrintWriter(archivo)) {
                archivo.createNewFile();
                pw.print(txfNombre.getText()+": ");
                for (Integer integer : numerosCorrectos) {
                    pw.print(integer+" ");
                }
                pw.println();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Ha sucedido un error en el guardado");
            }
            JOptionPane.showMessageDialog(this, "Partida guardada correctamente");
            this.dispose();
        }
    }
}
