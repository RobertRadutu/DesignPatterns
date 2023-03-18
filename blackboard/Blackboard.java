import java.util.ArrayList;

public class Blackboard {
    private ArrayList<Chair> chair;
    
    public Blackboard(){
        chair = new ArrayList<Chair>();
    }

    public void developChair(Chair c){
        chair.add(c);
    }

    public ArrayList<Chair> getProject(){
        return chair;
    }
}
