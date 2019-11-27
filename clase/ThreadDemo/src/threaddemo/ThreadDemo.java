package threaddemo;

public class ThreadDemo {

    public static void main(String[] args) {
        // TODO code application logic here
        Thread t = Thread.currentThread();
        
        System.out.println("Thread : " + t);
        
        t.setName("Principal");
        
        System.out.println("Thread : " + t);
        
        for(int i=0; i<5; i++) {
            System.out.print(". ");
            try {
                Thread.sleep(1000);
            } catch(Exception e) {
                System.out.println("Thread interrumpido!");
            }
        }
        System.out.println("Bye");
    }
    
}
