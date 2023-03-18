import java.util.HashMap;

public class Chair {
    private HashMap<Task, Boolean> tasks;
    private int id = 0;
    private static int chairs = 0;
    private boolean finished;
    private boolean takenInWork;

    public Chair(){
        tasks = new HashMap<Task, Boolean>();
        tasks.put(Task.SEAT, false);
        tasks.put(Task.FEET, false);
        tasks.put(Task.BACK, false);
        tasks.put(Task.BAR, false);
        tasks.put(Task.PACK, false);
        chairs++;
        id = chairs;
        finished = false;
        takenInWork = false;
    }

    public boolean finishedChair(){
         if(finished == true)
            return true;
         return false;
    }

    public void setFinished(){
        finished = true;
    }

    public boolean alreadyInWork(){
        if(takenInWork == true)
            return true;
        return false;
    }

    public void setInWork(){
        takenInWork = true;
    }

    public void outOfWork(){
        takenInWork = false;
    }

    public HashMap<Task, Boolean> getTasks(){
        return tasks;
    }

    public int getChairId(){
        return id;
    }
}
