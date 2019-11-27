package threaddemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Thread.State.WAITING;

public class VentanaThread {
    
    static HiloTiempo ht;
    
    public static void main(String[] args) {
        
        JFrame v1 = new JFrame("Threads");
        
        JButton b1 = new JButton("Inicio");
        JButton b2 = new JButton("Inicio");
        
        JButton bStart = new JButton("Start");
        JButton bStop = new JButton("Stop");
        bStop.setEnabled(false);
        JButton bPause = new JButton("Pause/Reanudar");
        bPause.setEnabled(false);
        
        Font fuente1 = new Font("Courier New", 1, 32);
        JLabel segundos = new JLabel("0:0:0");
        segundos.setFont(fuente1);
        
        
        
        b1.setBounds(20,20,100,40);
        b2.setBounds(130,20,100,40);
        bStart.setBounds(20,70,100,40);
        bStop.setBounds(130,70,100,40);
        bPause.setBounds(240,70,100,40);
        segundos.setBounds(20,120,150,40);
        
        ActionListener listener = new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                
                if(e.getSource() == b1) {
                    VentanaThExtends vt1 = new VentanaThExtends(b1);
                    vt1.start();
                }
                if(e.getSource() == b2) {
                    VentanaThExtends vt2 = new VentanaThExtends(b2);
                    vt2.start();
                }
                
                if(e.getSource() == bStart) {
                    ht = new HiloTiempo("ht1", segundos);
                    ht.start();
                    bStart.setEnabled(false);
                    bStop.setEnabled(true);
                    bPause.setEnabled(true);
                }
                
                if(e.getSource() == bStop) {
                    ht.stopHilo();
                    bStart.setEnabled(true);
                    bStop.setEnabled(false);
                    bPause.setEnabled(false);
                }
                
                if(e.getSource() == bPause) {
                    if(ht.getState().equals(WAITING)) {
                        ht.reanudarHilo();
                    } else {
                        ht.suspenderHilo();
                    }
                }
            }
            
        };
        
        b1.addActionListener(listener);
        b2.addActionListener(listener);
        bStart.addActionListener(listener);
        bStop.addActionListener(listener);
        bPause.addActionListener(listener);
        
        v1.add(b1);
        v1.add(b2);
        v1.add(bStart);
        v1.add(bStop);
        v1.add(bPause);
        v1.add(segundos);
        
        v1.setSize(400,400);
        v1.setLayout(null);
        v1.setResizable(false);
        v1.setLocationRelativeTo(null);
        v1.setDefaultCloseOperation(v1.EXIT_ON_CLOSE);
        v1.setVisible(true);
        
    }
    
}
