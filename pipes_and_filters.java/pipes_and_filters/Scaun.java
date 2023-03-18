package pipes_and_filters;
public class Scaun {
    private static int scaune = 0;
    private String componenteScaun;
    
    public Scaun(){
        componenteScaun = "";
        scaune++;
    }

    public void parteCompleta(Character task){
        componenteScaun += task;
    }

    public void display(){
        System.out.println(componenteScaun);
    }

    public static int getScaun(){
        return scaune;
    }
}
