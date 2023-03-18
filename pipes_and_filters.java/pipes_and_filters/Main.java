package pipes_and_filters;
import java.util.concurrent.*;

public class Main {
    public static void main(String argc[]){
        //Can create task
        Task.tasks.add('M');
        Task.tasks.add('N');

        //Create the workers
        Muncitor mC = new Muncitor(Task.tasks.get(0));
        Muncitor mF = new Muncitor(Task.tasks.get(1));
        Muncitor mB = new Muncitor(Task.tasks.get(2));
        Muncitor mS = new Muncitor(Task.tasks.get(3));
        Muncitor mP = new Muncitor(Task.tasks.get(4));

        //Create the pipes communication lines
        BlockingQueue<Scaun> q1 = new ArrayBlockingQueue<>(5); // q1 represents the number of pipes that ENTER the filer Worker and the number of pipes that EXIT
        BlockingQueue<Scaun> q2 = new ArrayBlockingQueue<>(5);
        BlockingQueue<Scaun> q3 = new ArrayBlockingQueue<>(5);
        BlockingQueue<Scaun> q4 = new ArrayBlockingQueue<>(5);
                            
        mC.setIesire(q1);
        mF.setIntrare(q1);
        mF.setIesire(q2);
        mB.setIntrare(q2);
        mB.setIesire(q3);
        mS.setIntrare(q3);
        mS.setIesire(q4);
        mP.setIntrare(q4);

        new Thread(mC).start();
        new Thread(mF).start();
        new Thread(mB).start();
        new Thread(mS).start();
        new Thread(mP).start();
    }
}
