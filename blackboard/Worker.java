import java.util.*;

public class Worker implements Runnable{
    private Task job;
    private Blackboard b;
    private static int workers = 0;
    private int id = 0;

    public Worker(Task j , Blackboard b){
        this.job = j;
        this.b = b; 
        workers++;
        id = workers;
    }


    //ADD ORDER OF STEPS CANT **PACK** BEFORE **SEAT**
    public void run(){
        while(true){
            ArrayList<Chair> chairs = b.getProject();
            for(Chair c : chairs){
                if(c.finishedChair() == false){
                    if(c.alreadyInWork() == false){                        
                        HashMap<Task, Boolean> tasks = c.getTasks();
                        if(this.job == Task.FEET || this.job == Task.BACK){
                            //verify if the SEAT was cut
                            if(tasks.get(Task.SEAT) == false)
                                continue; // cant complete this jobs since the basejob isn't complete
                               
                        }
                        if(this.job == Task.BAR){
                            if(tasks.get(Task.FEET) == false)
                                continue;
                        }
                        if(this.job == Task.PACK){
                            if(tasks.get(Task.BACK) == false || tasks.get(Task.BAR) == false || tasks.get(Task.FEET) == false || tasks.get(Task.SEAT) == false)
                                continue;  
                        }
                        if(tasks.get(job) == false){
                            c.setInWork();
                            tasks.put(job, true);
                            System.out.println("The worker with id: " + this.id + " has finished working on chair with id: " + c.getChairId());
                        }
                        c.outOfWork();
                        //Check if chair is complete
                         Iterator<HashMap.Entry<Task, Boolean> > iterator = tasks.entrySet().iterator();
                         boolean allStepsCompleted = true;
                         while(iterator.hasNext()){
                            HashMap.Entry<Task, Boolean> entry = iterator.next();
                            if(entry.getValue() == false){
                                allStepsCompleted = false;
                                break;
                            }
                        }
                        if(allStepsCompleted == true)
                            c.setFinished();
                    }
                }
            }
        }
    }
}
