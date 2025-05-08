package programacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class DecimalSelectorSwing {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DecimalSelectorSwing().createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Selector de Decimales");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new FlowLayout());

        JLabel label = new JLabel("Cantidad de decimales:");
        frame.add(label);

        // Crear JComboBox con valores de 0 a 5
        Integer[] valores = {0, 1, 2, 3, 4, 5};
        JComboBox<Integer> comboBox = new JComboBox<>(valores);
        frame.add(comboBox);

        JLabel resultadoLabel = new JLabel();
        frame.add(resultadoLabel);

        // Número de ejemplo a formatear
        double numero = 123.456789;

        // Acción al seleccionar un valor
        comboBox.addActionListener(e -> {
            int decimales = (int) comboBox.getSelectedItem();
            String formato = "#." + "#".repeat(decimales);
            DecimalFormat df = new DecimalFormat(formato);
            resultadoLabel.setText("Resultado: " + df.format(numero));
        });

        // Mostrar el valor inicial
        comboBox.setSelectedIndex(2); // predeterminado: 2 decimales
        comboBox.getActionListeners()[0].actionPerformed(null); // Forzar actualización inicial

        frame.setVisible(true);
    }
}

