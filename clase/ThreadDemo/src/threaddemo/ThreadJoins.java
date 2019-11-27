package threaddemo;

public class ThreadJoins {
    
    public static void main(String[] args) {
        
        Joins j1 = new Joins("J1", 2000);
        Joins j2 = new Joins("J2", 1000);
        
        j1.start();
        j2.start();
        
        System.out.println("Thread J1 Status : " + j1.isAlive());
        System.out.println("Thread J2 Status : " + j2.isAlive());
        
        try {
            System.out.println("En espera de finalizacion del thread");
            j1.join();
            j2.join();
        } catch(Exception e) {
            System.out.println(e);
        }
        
        System.out.println("Thread J1 Status : " + j1.isAlive());
        System.out.println("Thread J2 Status : " + j2.isAlive());
        System.out.println("Thread Principal Terminado!");
        
    }
    
}
