
package programacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ejercicio12 extends JFrame implements ActionListener, KeyListener {
  JButton btnAmarillo;
  JButton btnAzul;
  JButton btnAleatorio;
  JLabel lblTecla;

  public Ejercicio12() {
    super("Colores");
    setLayout(null);
    getContentPane().addMouseMotionListener(new MouseHandler());
    getContentPane().addMouseListener(new MouseHandler());
    addKeyListener(this);
    setFocusable(true);
    // btnAmarillo
    btnAmarillo = new JButton("Amarillo");
    btnAmarillo.setBounds(50, 50, 100, 50);
    btnAmarillo.addActionListener(this);
    btnAmarillo.addMouseMotionListener(new MouseHandler());
    btnAmarillo.addKeyListener(this);

    // btnAzul
    btnAzul = new JButton("Azul");
    btnAzul.setBounds(150, 50, 100, 50);
    btnAzul.addActionListener(this);
    btnAzul.addMouseMotionListener(new MouseHandler());
    btnAzul.addKeyListener(this);

    // btnAleatorio
    btnAleatorio = new JButton("Aleatorio");
    btnAleatorio.setBounds(250, 50, 100, 50);
    btnAleatorio.addActionListener(this);
    btnAleatorio.addMouseMotionListener(new MouseHandler());
    btnAleatorio.addKeyListener(this);

    // lblTecla
    lblTecla = new JLabel("Tecla pulsada: ");
    lblTecla.setBounds(100, 150, 100, 50);

    this.add(btnAleatorio);
    this.add(btnAmarillo);
    this.add(btnAzul);
    this.add(lblTecla);

  }

  private class MouseHandler implements MouseMotionListener, MouseListener {
    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) { //
      int x = e.getX();
      int y = e.getY();
      if (e.getSource() == btnAmarillo) {
        x = e.getX() + btnAmarillo.getX();
        y = e.getY() + btnAmarillo.getY();
      }
      if (e.getSource() == btnAzul) {
        x = e.getX() + btnAzul.getX();
        y = e.getY() + btnAzul.getY();
      }
      if (e.getSource() == btnAleatorio) {
        x = e.getX() + btnAleatorio.getX();
        y = e.getY() + btnAleatorio.getY();
      }
      Ejercicio12.this.setTitle(btnAleatorio.getX() + "");
      Ejercicio12.this.setTitle("Control de ratón: (X:" + x + ", Y:" + y + ")");
    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) {

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

    }

    @Override
    public void mouseExited(MouseEvent e) {
      Ejercicio12.this.setTitle("Colores");
    }
  }

  @Override
  public void actionPerformed(ActionEvent evento) {

    if (evento.getSource() == btnAmarillo) {
      this.getContentPane().setBackground(Color.YELLOW);
    }
    if (evento.getSource() == btnAzul) {
      this.getContentPane().setBackground(Color.BLUE);
    }
    if (evento.getSource() == btnAleatorio) {
      int x;
      int y;
      if ((evento.getModifiers() & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK) {
        x = (int) (Math.random() * this.getContentPane().getWidth() + 0);
        y = (int) (Math.random() * this.getContentPane().getHeight() + 0);
        btnAleatorio.setLocation(x, y);
        this.setTitle("x: " + x + " y: " + y + " shift pulsado");

      } else {
        x = (int) (Math.random() * 600 + 0);
        y = (int) (Math.random() * 400 + 0);
        this.setLocation(x, y);
        this.setTitle("x: " + x + " y: " + y);
      }
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    lblTecla.setText(e.getKeyChar() + ": " + e.getKeyCode());
  }

  @Override
  public void keyReleased(KeyEvent e) {
    //lblTecla.setText(e.getKeyChar() + ": " + e.getKeyCode());
  }



}
