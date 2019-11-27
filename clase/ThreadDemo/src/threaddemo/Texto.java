package threaddemo;

import javax.swing.*;
import java.awt.event.ActionListener;

import static java.lang.Thread.State.WAITING;

public class Texto {

    public static TextoThread textoThread;

    public static String[] arreglo = {"H","E","L","L","O"," ","T","H","E","R","E"};

    public static void main(String[] args){
        JFrame ventana = new JFrame("Mover Texto");
        JButton bStart = new JButton("Start");
        JButton bStop = new JButton("Stop");
        JButton bPause = new JButton("Pause");
        JLabel Label1 = new JLabel("Hola");
        JLabel label2 = new JLabel();
        bStop.setEnabled(false);
        bPause.setEnabled(false);


        bStart.setBounds(20, 20, 100, 20);
        Label1.setBounds(20,50,100,20);
        label2.setBounds(20,50,100,20);
        bStop.setBounds(130,70,100,20);
        bPause.setBounds(240,70,100,20);

        ActionListener actionListener = e -> {
            if (e.getSource() == bStart){
                textoThread = new TextoThread("Texto", Label1, arreglo, label2);
                textoThread.start();
                bStart.setEnabled(false);
                bPause.setEnabled(true);
                bStop.setEnabled(true);
            }

            if(e.getSource() == bStop) {
                textoThread.stopHilo();
                bStart.setEnabled(true);
                bStop.setEnabled(false);
                bPause.setEnabled(false);
            }

            if(e.getSource() == bPause) {
                if(textoThread.getState().equals(WAITING)) {
                    textoThread.reanudarHilo();
                } else {
                    textoThread.suspenderHilo();
                }
            }
        };

        bStart.addActionListener(actionListener);
        bStop.addActionListener(actionListener);
        bPause.addActionListener(actionListener);



        ventana.add(bStart);
        ventana.add(Label1);
        ventana.add(bPause);
        ventana.add(bStop);
        ventana.add(label2);

        ventana.setSize(400, 350);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(ventana.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setLayout(null);
        ventana.setVisible(true);




    }
}
