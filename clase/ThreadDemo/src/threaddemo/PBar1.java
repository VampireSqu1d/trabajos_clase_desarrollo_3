package threaddemo;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;

import static java.lang.Thread.State.WAITING;

public class PBar1 {
    
    static ThreadBar1 bar;
    static ThreadBar2 bar2;
    static int contador = 0;
    
    public static void main(String[] args){        
        JFrame v1 = new JFrame("Progress Bar");

        JProgressBar pb1 = new JProgressBar();
        pb1.setBounds(20, 20, 100, 20);
        pb1.setValue(0);
        pb1.setStringPainted(true);

        JProgressBar pb2 = new JProgressBar();
        pb2.setBounds(20, 130, 100, 20);
        pb2.setValue(0);
        pb2.setStringPainted(true);

        JButton button = new JButton("++");
        JButton bStart = new JButton("Start");
        JButton bStop = new JButton("Stop");
        bStop.setEnabled(false);
        JButton bPause = new JButton("Pause/Reanudar");
        bPause.setEnabled(false);                                        

        button.setBounds(20, 160, 100, 40);
        bStart.setBounds(20,70,100,40);
        bStop.setBounds(130,70,100,40);
        bPause.setBounds(240,70,100,40);
               
        ActionListener listener = e -> {

            if(e.getSource() == bStart) {
                bar = new ThreadBar1("ree", 50, pb1);
                bar.start();
                bStart.setEnabled(false);
                bStop.setEnabled(true);
                bPause.setEnabled(true);
            }

            if(e.getSource() == bStop) {
                bar.stop();
                bStart.setEnabled(true);
                bStop.setEnabled(false);
                bPause.setEnabled(false);
            }

            if(e.getSource() == bPause) {
                if(bar.getState().equals(WAITING)) {
                    bar.reanudarHilo();
                } else {
                    bar.suspenderHilo();
                }
            }

            if (e.getSource() == button){
                //pb2.setValue(pb2.getValue() + 1);
                bar2 = new ThreadBar2("BarUsuario", pb2, contador++);
                bar2.start();
            }


        };

        ChangeListener changeListener = changeEvent -> {
            if (pb1.getValue() == 100){
                bStart.setEnabled(true);
                bStop.setEnabled(false);
                bPause.setEnabled(false);
                JOptionPane.showMessageDialog(null, "Gano el thread autmatico :)");
                pb1.setValue(0);
                pb2.setValue(0);
            }

            if (pb2.getValue() == 100){
                JOptionPane.showMessageDialog(null, "Le ganaste al thread autmatico POG");
                pb1.setValue(0);
                pb2.setValue(0);
                button.setEnabled(false);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pb2.setValue(0);
                button.setEnabled(true);
            }

            if (pb2.getValue() == 100 && pb1.getValue() == 100){
                JOptionPane.showMessageDialog(null, "Empate POG");
            }

        };

        pb2.addChangeListener(changeListener);
        pb1.addChangeListener(changeListener);
        bStart.addActionListener(listener);
        bStop.addActionListener(listener);
        bPause.addActionListener(listener);
        button.addActionListener(listener);

        v1.add(button);
        v1.add(bStart);
        v1.add(bStop);
        v1.add(bPause);                            
        v1.add(pb1);
        v1.add(pb2);

        v1.setLayout(null);
        v1.setSize(400,400);
        v1.setResizable(false);
        v1.setDefaultCloseOperation(v1.EXIT_ON_CLOSE);
        v1.setVisible(true);
        v1.setLocationRelativeTo(null);
        
    }
}
