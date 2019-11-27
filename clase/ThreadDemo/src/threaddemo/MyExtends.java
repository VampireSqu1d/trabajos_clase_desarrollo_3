package threaddemo;

public class MyExtends extends Thread {
    
    Thread t;
    int tiempo;
    
    public MyExtends(String name, int tiempo) {
        t = new Thread(this, name);
        this.tiempo = tiempo;
    }

    public void run() {
        for(int i=0; i<5; i++) {
            System.out.println("Hi " + t);
            try {
                sleep(tiempo);
            } catch(Exception e) {
                System.out.println(e);
            }
        }
        
    }
    
}
