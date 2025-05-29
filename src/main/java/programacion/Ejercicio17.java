package programacion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
<<<<<<< HEAD
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
=======
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
>>>>>>> f755ca6ccbc320d060f4bac14a95199eea595a99

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Ejercicio17 extends JFrame implements ActionListener, MouseListener, KeyListener {
    public String[] botonesTelefono = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "#", "0", "*" };
<<<<<<< HEAD
    // public String letras ="123456789#0*";
=======
>>>>>>> f755ca6ccbc320d060f4bac14a95199eea595a99
    public JButton[] btnTeclas = new JButton[botonesTelefono.length];
    boolean grabado = true;
    final String archivoDatos = "numeros.txt";

    JTextField txfNumeroTelefonico;
    JButton btnReset;
    JMenuBar mnuPrincipal;
    JMenu mnuArchivo;
    JMenu mnuMovil;
    JMenuItem mniGrabar;
    JMenuItem mniLeer;
    JMenuItem mniReset;
    JMenuItem mniSalir;

<<<<<<< HEAD
    // setMnemonic
=======
>>>>>>> f755ca6ccbc320d060f4bac14a95199eea595a99
    public Ejercicio17() {
        super("Telefono");
        setLayout(null);
        addKeyListener(this);
        setFocusable(true);

        txfNumeroTelefonico = new JTextField();
        txfNumeroTelefonico.setEditable(false);
        txfNumeroTelefonico.setBounds(40, 40, 220, 20);
        add(txfNumeroTelefonico);

        mnuPrincipal = new JMenuBar();
        mnuArchivo = new JMenu("Archivo");
        mnuMovil = new JMenu("Móvil");

        mniGrabar = new JMenuItem("Grabar número");
        mniLeer = new JMenuItem("Leer números");
        mniReset = new JMenuItem("Reset");
        mniSalir = new JMenuItem("Salir");

        mniGrabar.addActionListener(this);
        mniLeer.addActionListener(this);
        mniReset.addActionListener(this);
        mniSalir.addActionListener(this);

        mnuArchivo.add(mniGrabar);
        mnuArchivo.add(mniLeer);

        mnuMovil.add(mniReset);
        mnuMovil.addSeparator();
        mnuMovil.add(mniSalir);

        mnuPrincipal.add(mnuArchivo);
        mnuPrincipal.add(mnuMovil);

<<<<<<< HEAD
        mnuArchivo.setMnemonic('a');
        mnuMovil.setMnemonic('m');
        mniGrabar.setMnemonic('g');
        mniLeer.setMnemonic('l');
        mniReset.setMnemonic('r');
        mniSalir.setMnemonic('s');
=======
>>>>>>> f755ca6ccbc320d060f4bac14a95199eea595a99
        setJMenuBar(mnuPrincipal);

        int x = 40;
        int y = 70;
        for (int i = 0; i < botonesTelefono.length; i++) {
            JButton button = new JButton(botonesTelefono[i]);

            button.setBounds(x, y, 60, 30);
            button.addActionListener(this);
            button.addMouseListener(this);
<<<<<<< HEAD
            button.addKeyListener(this);
=======
>>>>>>> f755ca6ccbc320d060f4bac14a95199eea595a99
            add(button);
            x += 80;
            if (x >= 220) {
                x = 40;
                y += 50;
            }
            btnTeclas[i] = button;
        }

        btnReset = new JButton("Reiniciar");
        btnReset.setBounds(100, 320, 100, 30);
        btnReset.addActionListener(this);
<<<<<<< HEAD
        btnReset.addKeyListener(this);
=======
>>>>>>> f755ca6ccbc320d060f4bac14a95199eea595a99
        add(btnReset);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
<<<<<<< HEAD
        if (src == btnReset || src == mniReset) {
            txfNumeroTelefonico.setText("");
=======

        if (src == btnReset || src == mniReset) {
            txfNumeroTelefonico.setText("");    
>>>>>>> f755ca6ccbc320d060f4bac14a95199eea595a99
            grabado = true;
            for (JButton button : btnTeclas) {
                button.setForeground(Color.BLACK);
            }
<<<<<<< HEAD
        } else if (src instanceof JButton) {
            JButton button = (JButton) src;
            button.setForeground(Color.BLUE);
            txfNumeroTelefonico.setText(txfNumeroTelefonico.getText() + button.getText());
            grabado = false;
        } else if (src == mniGrabar) {
            if (txfNumeroTelefonico.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Número vacío");
            } else {

                try (PrintWriter writer = new PrintWriter(new FileWriter(archivoDatos, true))) {
                    writer.println(txfNumeroTelefonico.getText());
                    grabado = true;
                    JOptionPane.showMessageDialog(this, "Número guardado.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error al guardar el número.");
                }
            }
        } else if (src == mniLeer) {
            try (Scanner reader = new Scanner(new FileReader(archivoDatos))) {
                StringBuilder contenido = new StringBuilder();
                String linea;
                while (reader.hasNextLine()) {
                    linea = reader.nextLine();
=======

        } else if (src instanceof JButton) {
            JButton button = (JButton) src;
            if (!button.getText().equals("Reiniciar")) { 
                txfNumeroTelefonico.setText(txfNumeroTelefonico.getText() + button.getText());
                grabado = false;
            }

        } else if (src == mniGrabar) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoDatos, true))) {
                writer.write(txfNumeroTelefonico.getText());
                writer.newLine();
                grabado = true;
                JOptionPane.showMessageDialog(this, "Número guardado.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar el número.");
            }

        } else if (src == mniLeer) {
            try (BufferedReader reader = new BufferedReader(new FileReader(archivoDatos))) {
                StringBuilder contenido = new StringBuilder();
                String linea;
                while ((linea = reader.readLine()) != null) {
>>>>>>> f755ca6ccbc320d060f4bac14a95199eea595a99
                    contenido.append(linea).append("\n");
                }
                JOptionPane.showMessageDialog(this, contenido.toString(), "Números guardados",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "No se pudo leer el archivo.");
            }
<<<<<<< HEAD
=======

>>>>>>> f755ca6ccbc320d060f4bac14a95199eea595a99
        } else if (src == mniSalir) {
            if (!txfNumeroTelefonico.getText().isEmpty() && !grabado) {
                int respuesta = JOptionPane.showConfirmDialog(this,
                        "Tienes un número sin guardar. ¿Deseas salir de todas formas?",
                        "Confirmar salida", JOptionPane.YES_NO_OPTION);
                if (respuesta != JOptionPane.YES_OPTION) {
                    return;
                }
            }
            dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JButton button = (JButton) e.getSource();
<<<<<<< HEAD
        if (button.getForeground() != Color.BLUE) {
            button.setForeground(Color.ORANGE);
        }
=======
        button.setForeground(Color.ORANGE);
>>>>>>> f755ca6ccbc320d060f4bac14a95199eea595a99
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JButton button = (JButton) e.getSource();
<<<<<<< HEAD
        if (button.getForeground() != Color.BLUE) {
            button.setForeground(Color.BLACK);
        }
=======
        button.setForeground(Color.PINK);
>>>>>>> f755ca6ccbc320d060f4bac14a95199eea595a99

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
            txfNumeroTelefonico.setText(txfNumeroTelefonico.getText() + e.getKeyChar());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
