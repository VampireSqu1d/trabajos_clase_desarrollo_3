package threaddemo;

import javax.swing.*;

public class ThreadBar2  extends Thread{
    Thread t;
    JProgressBar pb;
    int contador;

    public ThreadBar2(String name, JProgressBar pb, int contador){
        this.t = new Thread(name);
        this.pb = pb;
        this.contador = contador;
    }

    public void run(){
        pb.setValue(contador);
    }


}
