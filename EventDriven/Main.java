public class Main {

    public static void main(String[] args) {
        Worker worker1 = new Worker("Worker1");
        Worker worker2 = new Worker("Worker2");
        Worker worker3 = new Worker("Worker3");
        Worker worker4 = new Worker("Worker4");
        Worker worker5 = new Worker("Worker5");

        EventBus eventBus = new EventBus();
        eventBus.subscribe(worker1);
        eventBus.subscribe(worker2);
        eventBus.subscribe(worker3);
        eventBus.subscribe(worker4);
        eventBus.subscribe(worker5);

        eventBus.publish(new Event(Event.Type.DONE_C));
        eventBus.publish(new Event(Event.Type.DONE_F));
        eventBus.publish(new Event(Event.Type.DONE_B));
        eventBus.publish(new Event(Event.Type.DONE_S));
        eventBus.publish(new Event(Event.Type.DONE_P));
       
    }
}