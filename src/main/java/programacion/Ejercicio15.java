package programacion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

 
public class Ejercicio15 extends JFrame implements ActionListener {
    double creditos = 10;
    File ruta = new File("imagenes\\Tragaperras\\");
    File[] imagenes = ruta.listFiles();
    ImageIcon[] imagenesIcon = new ImageIcon[imagenes.length];
    JLabel lblPrimerSlot;
    JLabel lblSgundoSlot;
    JLabel lblTercerSlot;
    JButton btnJugar;
    JLabel lblInformar;
    Timer timerVictoria;
    JLabel lblCreditos;

    public Ejercicio15() {
        super("Superminitragaperras");
        setLayout(null);

        for (int i = 0; i < imagenes.length; i++) {
            imagenesIcon[i] = new ImageIcon(imagenes[i].getAbsolutePath());

        }

        lblPrimerSlot = new JLabel("");
        lblPrimerSlot.setBounds(10, 25, 250, 250);
        add(lblPrimerSlot);

        lblSgundoSlot = new JLabel("");
        lblSgundoSlot.setBounds(270, 25, 250, 250);
        add(lblSgundoSlot);

        lblTercerSlot = new JLabel("");
        lblTercerSlot.setBounds(530, 25, 250, 250);
        add(lblTercerSlot);

        lblCreditos = new JLabel("Creditos: " + creditos + "€");
        lblCreditos.setBounds(160, 280, 150, 20);
        add(lblCreditos);

        btnJugar = new JButton("Tirar");
        btnJugar.setBounds(250, 280, 70, 20);
        btnJugar.addActionListener(this);
        add(btnJugar);

        lblInformar = new JLabel();
        lblInformar.setBounds(330, 280, 150, 20);
        add(lblInformar);

        timerVictoria = new Timer(1000, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnJugar) {
            if (timerVictoria.isRunning()) {
                timerVictoria.stop();
                lblInformar.setForeground(Color.BLACK);
            }
            creditos--;
            int primerValor = (int) (Math.random() * 6 + 0);
            lblPrimerSlot.setIcon(imagenesIcon[primerValor]);

            int segundoValor = (int) (Math.random() * 6 + 0);
            lblSgundoSlot.setIcon(imagenesIcon[segundoValor]);

            int tercerValor = (int) (Math.random() * 6 + 0);
            lblTercerSlot.setIcon(imagenesIcon[tercerValor]);
            // TODO cambiar orde if para simplificar
            if (primerValor == segundoValor && primerValor == tercerValor) {
                creditos += 5;
                timerVictoria.start();
                lblInformar.setText("PREMIO GORDO 5€");
            } else {
                if (primerValor == segundoValor || primerValor == tercerValor || segundoValor == tercerValor) {
                    creditos += 1.5;
                    timerVictoria.start();
                    lblInformar.setText("PREMIO 1,5€");
                } else {
                    lblInformar.setText("PERDISTE");
                }
            }
            lblCreditos.setText("Creditos: " + creditos + "€");
            if (creditos<1) {
                btnJugar.setEnabled(false);
            }
        } else {
            if (lblInformar.getForeground() != Color.BLUE) {
                lblInformar.setForeground(Color.BLUE);
            } else {
                lblInformar.setForeground(Color.YELLOW);
            }

        }
    }
}
