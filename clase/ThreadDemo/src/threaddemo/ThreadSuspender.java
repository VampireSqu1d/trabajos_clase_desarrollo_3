
package threaddemo;

public class ThreadSuspender {
    
    public static void main(String[] args) {
        
        Suspender s1 = new Suspender("S1", 1000);
        
        s1.start();
        
        try {
            Thread.sleep(10000);
        } catch(Exception e) {}
        System.out.println("Suspendiendo");
        s1.suspenderHilo();
        
        try {
            Thread.sleep(2000);
        } catch(Exception e) {}
        System.out.println("Reanudar");
        s1.reanudarHilo();
        
        try {
            Thread.sleep(40000);
        } catch(Exception e) {}
        System.out.println("Suspendiendo");
        s1.suspenderHilo();
        
        try {
            Thread.sleep(5000);
        } catch(Exception e) {}
        System.out.println("Reanudar");
        s1.reanudarHilo();
        
        try {
            Thread.sleep(10000);
        } catch(Exception e) {}
        s1.stopHilo();
        
        try {
            //System.out.println("Esperando a que termine el Thread");
            s1.join();
        } catch(Exception e) {}
        
        System.out.println("Termina Thread Principal");
        
    }
    
}
