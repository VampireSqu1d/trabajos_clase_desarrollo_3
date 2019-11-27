package threaddemo;

public class ThreadExtends {

    public static void main(String[] args) {
        
        MyExtends me1 = new MyExtends("ME1", 3000);
        MyExtends me2 = new MyExtends("ME2", 1000);
        
        me1.start();
        me2.start();
        
    }
    
}
