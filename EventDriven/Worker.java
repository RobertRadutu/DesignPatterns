public class Worker {

    private String name;

    public Worker(String name) {
        this.name = name;
    }

    public Worker(){
        
    }

    @EventHandler
    public void DONE_C(Event event) {
        System.out.println(name + " cut seat.");
    }

    @EventHandler
    public void DONE_F(Event event) {
        System.out.println(name + " assembled feet.");
    }

    @EventHandler
    public void DONE_B(Event event) {
        System.out.println(name + " assembled backrest.");
    }

    @EventHandler
    public void DONE_S(Event event) {
        System.out.println(name + " assembled stabilizer bar.");
    }

    @EventHandler
    public void DONE_P(Event event) {
        System.out.println(name + " packaged chair.");
    }

    public String getWorkerName(){
        return name;
    }
}
