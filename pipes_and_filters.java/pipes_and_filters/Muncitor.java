package pipes_and_filters;
import java.util.concurrent.BlockingQueue;

public class Muncitor implements Runnable {
    private static int muncitori = 0;
    private Character task;
    private BlockingQueue<Scaun> intrare, iesire;
    
    public Muncitor(Character t){
        task = t;
        ++muncitori;
    }

    public void setIntrare(BlockingQueue<Scaun> b){
        intrare = b;
    }

    public void setIesire(BlockingQueue<Scaun> b){
        iesire = b;
    }

    public void run(){
        if(intrare != null && iesire != null){
            while(true){    
                try{
                    Scaun scaun = intrare.take();
                    scaun.parteCompleta(task);
                    iesire.put(scaun);
                }catch(InterruptedException e){
                    System.out.println("NU are input muncitorul: " + String.valueOf(task));
                }
            }
        }
       else if(iesire == null){ // last filter
        while(true){
            try{
                Scaun scaun = intrare.take();
                scaun.parteCompleta(task);
                System.out.println("Muncitorul : " + String.valueOf(task) + " a terminat scaunul cu id-ul : " + Scaun.getScaun());
            }catch(InterruptedException e){
                System.out.println("Nu are input muncitorul: " + String.valueOf(task));
            }
        }
       }
       else if(intrare == null){// first filter
            while(true){
                    Scaun scaun = new Scaun();
                    scaun.parteCompleta(task);
                    System.out.println("Muncitorul : " + task + "a inceput scaunul cu id-ul : " + Scaun.getScaun());
                try{
                    iesire.put(scaun);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
       } 
    }
}
