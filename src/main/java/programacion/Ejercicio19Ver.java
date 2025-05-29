package programacion;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Ejercicio19Ver extends JDialog {

    JTextArea txaRecords;
    String ruta = System.getProperty("user.home") + "\\records.txt";

    public Ejercicio19Ver(Ejercicio19 principal) {
        super(principal);
        setLayout(null);

        txaRecords = new JTextArea();
        txaRecords.setBounds(20, 20, 300, 500);
        txaRecords.setEditable(false);
        JScrollPane scroll=new JScrollPane(txaRecords);
        add(scroll);

        add(txaRecords);

        File archivo = new File(ruta);
        try (Scanner sc = new Scanner(new FileReader(archivo))) {
            if (archivo.createNewFile()) {
                JOptionPane.showConfirmDialog(this, "No hay partidas guardadas");
                dispose();
            } else {
                while (sc.hasNext()) {
                    txaRecords.append(sc.nextLine());
                }
            }
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(this, "Ha sucedido un error");
        }
    }
}
