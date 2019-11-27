package threaddemo;//package threaddemo;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MoverImagen {

    static threaddemo.ThMoverImagen th1;
    static threaddemo.ThRock thR , thR2;

    public static void main(String[] args){

        JFrame ventana = new JFrame("ventana imagenes");
        JLabel camino = new JLabel(new ImageIcon("imagenes/camino.png"));
        JLabel carro = new JLabel(new ImageIcon("imagenes/carro2.png"));
        JLabel roca = new JLabel(new ImageIcon("imagenes/rock.png"));
        JLabel roca2 = new JLabel(new ImageIcon("imagenes/rock.png"));
        JButton start = new JButton("start");
        JButton stop = new JButton("stop");

        carro.setFocusable(true);
        start.setFocusable(false);
        roca.setFocusable(false);
        roca2.setFocusable(false);
        stop.setFocusable(false);

        stop.setEnabled(false);

        camino.setBounds(20, 20, 360, 60);
        carro.setBounds(20,20, 24,24);
        start.setBounds(20,80,100,20);
        roca.setBounds(350, 20, 24, 24);
        roca2.setBounds(350, 45, 24, 24);
        stop.setBounds(135, 80, 100, 20);

        ActionListener actionListener = actionEvent -> {
            if (actionEvent.getSource() == start) {
                th1 = new ThMoverImagen("carrito", carro);
                th1.start();
                start.setEnabled(false);
                stop.setEnabled(true);
                thR = new ThRock("rocky", roca, carro, th1, 100);
                thR.start();
                thR2 = new ThRock("roca", roca2, carro, th1, 25);
                thR2.start();
            }

            if (actionEvent.getSource() == stop){
                th1.stopHilo();
                start.setEnabled(true);
                stop.setEnabled(false);
                thR.stopHilo();
                thR2.stopHilo();
            }
        };

        start.addActionListener(actionListener);
        stop.addActionListener(actionListener);

        ventana.add(carro);
        ventana.add(roca);
        ventana.add(roca2);
        ventana.add(camino);
        ventana.add(start);
        ventana.add(stop);

        ventana.setSize(400, 350);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(ventana.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setLayout(null);
        ventana.setVisible(true);
    }

}
