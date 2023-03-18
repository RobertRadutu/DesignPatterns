public class Main {
    public static void main(String[] argv){
        Blackboard b = new Blackboard();
        b.developChair(new Chair());
        b.developChair(new Chair());
        b.developChair(new Chair());
        b.developChair(new Chair());
        Worker w1 = new Worker(Task.SEAT, b);
        Worker w2 = new Worker(Task.FEET, b);
        Worker w3 = new Worker(Task.BACK, b);
        Worker w4 = new Worker(Task.BAR, b);
        Worker w5 = new Worker(Task.PACK, b);
        new Thread(w1).start();
        new Thread(w2).start();
        new Thread(w3).start();
        new Thread(w4).start();
        new Thread(w5).start();
    }
}
