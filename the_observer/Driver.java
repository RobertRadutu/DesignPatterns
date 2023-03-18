import java.util.*;

import org.w3c.dom.Text;

interface Observer{
    void update(TemperatureSensor context);
}

interface Subject{
    public void attach(Observer o);
    public void detach(Observer o);
    public void notifyObservers();
}

class TemperatureSensor implements Subject{
    private ArrayList<Observer> observers;
    private double tempState;
    private double precision;
    private String sensorId;
    private String location;
    private String revision;

    public TemperatureSensor(double initialTemp, String sensorid, String location, String revision, double precision){
        tempState = initialTemp;
        this.precision = precision;
        this.sensorId = sensorid;
        this.location = location;   
        this.revision = revision;
        observers = new ArrayList<Observer>();
    }

    public double getTemp(){
        return tempState;
    }

    public String getsensorId(){
        return sensorId;
    }

    public String getlocation(){
        return location;
    }

    public String getrevision(){
        return revision;
    }

    public double getprecision(){
        return precision;
    }

    public void setTemp(double newTemp){
        tempState = newTemp;
        notifyObservers();
    }

    public void attach(Observer o){
        if(observers.contains(o) == true){
            System.out.println("The display you are trying to attach is already connected to this sensor, please try again.");
            return;
        }
        observers.add(o);
    }

    public void detach(Observer o){
        if(observers.contains(o) == false){
            System.out.println("The display you are trying to detach wasn't attach in the first place, please try again.");
            return;
        }
        observers.remove(o);
    }

    public void notifyObservers(){
        for(Observer o : observers)
            o.update(this);
    }
}

class NumericDisplay implements Observer{
    private double value;
    private String location;
    private double precision;
    public NumericDisplay(){
    }

    public void update(TemperatureSensor context){
        this.value = context.getTemp();
        this.location = context.getlocation();
        this.precision = context.getprecision();
        display();
    }

    public void display(){
        System.out.println("Current value  = " + value + ", the location is : " + location + " precision : " + precision);
    }
}

class TextDisplay implements Observer{
    private String value = "";
    private String location = "";

    public TextDisplay(){}

    public void update(TemperatureSensor context){
        if(context.getTemp() >= 20) this.value = "warm";
        else this.value = "cold";
        this.location = context.getlocation();
        display();
    }

    public void display(){
        System.out.println("It is : " + value + " and the location of the sensor is : " + location);
    }
}

class AverageDisplay implements Observer{
    private HashMap <TemperatureSensor, ArrayList<Double>> sums;
    private double average;
    private String sensordId;
    private String location;
    private String revision;
    private double precision;

    public AverageDisplay(){
        sums  = new HashMap<TemperatureSensor, ArrayList<Double>>();
        average = 0;
    }

    public void update(TemperatureSensor context){
        if(sums.containsKey(context) == false){
            sums.put(context, new ArrayList<Double>());
            sums.get(context).add(context.getTemp());
        }
        else 
            sums.get(context).add(context.getTemp());
        this.location = context.getlocation();
        this.precision = context.getprecision();
        this.sensordId = context.getsensorId();
        this.revision = context.getrevision();
        double sum = 0;
        for(Double v : sums.get(context))
            sum += v;    
        average = sum / sums.get(context).size();
        display();
    }

    public void display(){
        System.out.println("Average temperature for now : " + average + ", sensor id : " + sensordId + ", the location is : " + location + ", the precision is : " + precision + ", the revision date is is : " + revision);
    }

}


public class Driver{
    private static Scanner scan = new Scanner(System.in);
    public static TemperatureSensor createTemperatureSensor(){
        System.out.println("Please input the desired values for the new temperature sensor in this order: Temperature, SensorId, Location, Revision, Precision.");
        double temperature = scan.nextDouble();
        String sensordId = scan.next();
        String location = scan.next();
        String revision = scan.next();
        double precision = scan.nextDouble();
        TemperatureSensor o = new TemperatureSensor(temperature, sensordId, location, revision, precision);
        return o;
    }

    public static Observer createDisplay(){
        int select;
        Observer o = null;
        System.out.println("Please select one of the following displays:\n    1.Text\n    2.Numeric\n    3.Average");
        select = scan.nextInt();
        switch(select){
            case 1:
                o = new TextDisplay();
                break;
            case 2:
                o = new NumericDisplay();
                break;
            case 3:
                o = new AverageDisplay();
                break;
            default:
                System.out.println("Invalid value, please try again using the given values.");
                break;
        }
        return o;
    }

    public static void attachDisplay(ArrayList<TemperatureSensor> sensor, ArrayList<Observer> display){
        if(display.size() == 0){
            System.out.println("No displays are available for now, please create some.");
            return;
        }
        if(sensor.size() == 0){
            System.out.println("No temperature sensors are available for now, please create some.");
            return;
        }
        TemperatureSensor s = null;
        Observer o = null;
        int index;
        System.out.println("Please select which display you wish to attach: ");
        int cnt = 0;
        for(Observer iterator : display){
            cnt++;
            System.out.println(cnt + ". " + iterator.getClass());
        }

        index = scan.nextInt() - 1;
        o = display.get(index);

        System.out.println("Please select which temperature sensor you wish to attach: ");
        cnt = 0;
        for(TemperatureSensor iterator : sensor){
            cnt++;
            System.out.println(cnt + ". " + iterator.getsensorId());
        }
        index = scan.nextInt() - 1;
        s = sensor.get(index);
        s.attach(o);
    }

    public static void detachDisplay(ArrayList<TemperatureSensor> sensor, ArrayList<Observer> display){
        if(display.size() == 0){
            System.out.println("No displays are available for now, please create some.");
            return;
        }
        if(sensor.size() == 0){
            System.out.println("No temperature sensors are available for now, please create some.");
            return;
        }

        TemperatureSensor s = null;
        Observer o = null;
        int index;
        System.out.println("Please select which display you wish to detach: ");
        int cnt = 0;
        for(Observer iterator : display){
            cnt++;
            System.out.println(cnt + ". " + iterator.getClass());
        }
        index = scan.nextInt() - 1;
        o = display.get(index);

        System.out.println("Please select which temperature sensor you wish to detach from: ");
        cnt = 0;
        for(TemperatureSensor iterator : sensor){
            cnt++;
            System.out.println(cnt + ". " + iterator.getsensorId());
        }
        index = scan.nextInt() - 1;
        s = sensor.get(index);
        s.detach(o);
    }

    public static void setNewTemperatureToSensor(ArrayList<TemperatureSensor> sensors){
        if(sensors.size() == 0){
            System.out.println("No temperature sensors are available for now, please create some.");
            return;
        }
        double newTemp;
        int cnt = 0;
        TemperatureSensor s = null;
        System.out.print("Input a new temperature: ");
        newTemp = scan.nextDouble();
        System.out.print("Please select a temperature sensor: ");
        for(TemperatureSensor iterator : sensors){
            cnt++;
            System.out.println(cnt + ". " + iterator.getsensorId());
        }
        int index = scan.nextInt();
        index--;
        s = sensors.get(index);
        s.setTemp(newTemp);
    }

    public static void main(String[] args) {
        // TemperatureSensor s = new TemperatureSensor(7, "a", "Motor", "12-02-2023", 0.0001);

        // NumericDisplay o1=new NumericDisplay();
        // TextDisplay o2 = new TextDisplay();
        // AverageDisplay o3=new AverageDisplay();

        // s.attach(o1);
        // s.attach(o2);

        // s.setTemp(24);
        // s.setTemp(11);

        // s.detach(o2);
        // s.attach(o3);

        // s.setTemp(10);
        // s.setTemp(1);
        // s.setTemp(1);

        // TemperatureSensor s2 = new TemperatureSensor(20, "B", "Opening/Closing Windows", "20-09-1999", 0.1);
        // s2.attach(o1);
        // s2.attach(o2);
        // s2.attach(o3);
        // s2.setTemp(30);
        // s2.setTemp(10);
        // s2.setTemp(20);


        ArrayList<TemperatureSensor> sensors = new ArrayList<TemperatureSensor>();
        ArrayList<Observer> displays = new ArrayList<Observer>();
        int select = 0;
        Scanner s = new Scanner(System.in);
        do{
            System.out.println("1.To create a temperature sensor: Press 1");
            System.out.println("2.To create a display: Press 2");
            System.out.println("3.To attach an exisiting display to one of the temeprature sensors: Press 3");
            System.out.println("4.To detach a display from the sensor: Press 4");
            System.out.println("5.To set a new temperature value to one of the sensors: Press 5");
            System.out.println("---->To end this process: Press 0");
            System.out.print("Please input a value: ");
            select = s.nextInt();
            switch(select){
                case 1:
                    sensors.add(createTemperatureSensor());
                    break;
                case 2:
                    displays.add(createDisplay());
                    break;
                case 3:
                    attachDisplay(sensors, displays);
                    break;
                case 4:
                    detachDisplay(sensors, displays);
                    break;
                case 5:
                    setNewTemperatureToSensor(sensors);
                    break;
                case 0:
                    select = 0;
                    break;
                default:
                    System.out.println("This is an unknown value, please specify one from the list above");
                    select = -1;
                    break;
            }
        }while(select != 0);
        s.close();

        
    }

}