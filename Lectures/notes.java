import java.util.ArrayList;
import java.util.List;

import sun.java2d.pipe.SpanShapeRenderer.Simple;
import sun.security.ssl.TrustManagerFactoryImpl.SimpleFactory;
public class Division {
  private List<Employee> division = new ArrayList<Employee>();
  private Employee[] employees = new Employee[10];
}

    ...
    LibraryMember theLibraryMember = null; 
    theLibraryMember.borrow(theCopy);
}
public class LibraryMember {
    public boolean borrow (Copy theCopy) {
        okToBorrow();
        theCopy.borrow();
    }
   public boolean okToBorrow() { ... } 
}
public class Copy {
   Book theBook;
   public void borrow() {
       theBook.borrowed();
   }
}
public class Book { 
    public boolean borrowed(){ ... } 
}


public class HonorCourses {
    private Module[] module;
    public HonorCourses(Module[] mod) {
        this.module = mod;  // pointer
    }
}
public void main(String[] args) {
    Module mod = new Module[6];
    HonorCourses hc = new HonorCourse(mod);
}

public class Board {
    private Square[] squares;
    public Board() {
        squares = new Square[9];  // new instances
    }  
}

public class DoS {
    private List<People> directees = new ArrayList<>();
    public void addDirectee(Student s) {
        directees.add(s)
    }
}









// Strategy Pattern

public interface FlyBehavior {
    public void fly();
}
public class FlyNo implements FlyBehavior {
    public void fly() { System.out.println("Does not fly"); }
}

/* =========================================================*/
public abstract class Duck {
    // shared by all types of ducks
    public void swim() {...} 
    abstract public void display();

    // different ducks have different behaviors
    private FlyBehavior fb;
    public void setFly(Flybehavior f) { fb = f; }
    public void performFly() { fb.fly(); }
}

public class DuckA extends Duck {
    public DuckA() { FlyBehavior flyA = new FlyNo(); } // Set fly behavior
    public void display() { System.out.println("I'm DuckA"); }
}

/* =========================================================*/
public void main(String[] args) {
    DuckA a = new DuckA();
    a.performFly();
}












// Observer Pattern
public interface Subject {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();

}

public class WeatherData implements Subject { // concrete subject
    
    private ArrayList observers;
    private float temperature;
    private float humidity;
    private float pressure;
    
    public WeatherData() { observers = new ArrayList(); }
    public void registerObserver(Observer o) { observers.add(o); }
    public void removeObserver(Observer o) { observers.remove(observers.indexOf(o)); }
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer)observers.get(i);
            observer.update(temperature, humidity, pressure);  // update info for each observer
        }
    }

    public void measurementsChanged() { notifyObservers(); }
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}
/* =========================================================*/
public interface Observer {
    public void update(float temperature, float humidity, float pressure);
}
public interface DisplayElement {
    public void display();
}

public class CurrentConditionDisplay implements Observer, DisplayElement { // concrete observer
    private float temperature;
    private float humidity;
    private Subject weatherData;

    public CurrentConditionDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }
    public void display() { /*print weather measurements*/ }
}

/* =========================================================*/
public static void main(String[] args) {
    WeatherData wd = new WeatherData(); // subject
    CurrentConditionDisplay ccd = new CurrentConditionDisplay(wd);  // observer
    wd.setMeasurements(1, 1, 1);  // will notify to get observer updated
}














// Factory Method pattern

// Product
public abstract class Pizza {
    String name; 
    void prepare() {...}
    void bake() {...}
    public String getName() { return name; }
}
public class CheesePizza extends Pizza { }
public class GreekPizza extends Pizza { }

/* =========================================================*/
// Factory
public abstract class SimplePizzaFactory {
    public abstract Pizza create(String type);
}
// Concrete factory
public class NYFactory extends SimplePizzaFactory {
    public Pizza create(String type) {
        System.out.println("New york style pizza: ");
        if (type.equals("cheese")) { 
            pizza = new CheesePizza(); 
        } else if (type.equals("greek")) { 
            pizza = new GreekPizza();
        }
    }
}

/* =========================================================*/
// Creator
public abstract class PizzaStore {

    SimplePizzaFactory factory; // has different implementation of create pizza
    public PizzaStore(SimplePizzaFactory factory) {
        this.factory = factory;
    }

    public Pizza createPizza(String type) { // factory method
        factory.create(type);
    }

    public Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);  // does not know which factory creates the pizza
        pizza.prepare(); // common operations
        pizza.bake();
        return pizza;
    }
}

// Concrete Creator
public class NYPizzaStore extends PizzaStore { }

/* =========================================================*/
public static void main(String[] args) {
    SimplePizzaFactory nyfactory = new NYFactory();
    PizzaStoreA store = new NYPizzaStore(nyfactory);
    Pizza pizza = store.orderPizza("cheese");
}












// Abstract Factory Pattern
/* =========================================================*/
// Product
public abstract class Pizza {
    CheeseIngredientCollection cheese;
    GreekIngredientCollection greek;
    void prepare() {...} // common operations
    void bake() {...}
}
public class CheesePizza extends Pizza {
    IngredientCollection collection;
    public CheesePizza(IngredientCollection collection) { // Which cheese is created is determined at run time by the factory passed at object creation time
        this.collection = collection;
        this.cheese = collection.createCheese();
        this.greek = collection.createGreek();
    }
}
public class GreekPizza extends Pizza {...}

/* =========================================================*/
// Ingredient collection
public interface IngredientCollection {
    public Dough creatDough();
    public Sauce createSauce();
}
public class CheeseIngredientCollection implements IngredientCollection {
    public Dough createDough() {
        return new ThinCrustDough();
    }
    public Sauce createSauce() {
        return new MarinaraSauce();
    }
}
public class GreekIngredientCollection implements IngredientCollection { }


/* =========================================================*/
// Creator
public abstract class PizzaStore {
    IngredientCollection collection = null; // Abstraction

    public abstract Pizza createPizza(String type); // factory method

    public Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);  // does not know which instance creates the pizza
        pizza.prepare(); // common operations
        pizza.bake();
        return pizza;
    }
}

// Concrete Creator
public class PizzaStoreA extends PizzaStore {
    public Pizza createPizza(String type) { // must implement abstract method
        Pizza pizza = null;
        if (type.equals("cheese")) {
            IngredientCollection collection = new CheeseIngredientCollection();
            pizza = new CheesePizza(collection); // object creation time --> determine the type of ingredients
        } else if (type.equals("greek")) {
            ...
        }
        return pizza;

    }
}

/* =========================================================*/
public static void main(String[] args) {
    PizzaStoreA store = new PizzaFactoryA();
    Pizza pizza = store.orderPizza("cheese");
}


//Singleton
public class Singleton {
    private static volatile Singleton uniqueInstance;
    private Singleton() { }
    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueSingleton;
    }
}



// Command

// Command Object Interface
public interface Command {
    public void execute();
}

// Concrete Command Object
public class LightOnCommand implements Command {
    Light light;
    public LightOnCommand(Light light) { // pass specific light that this command controls
        this.light = light;
    }
    public void execute() {
        light.on();  // Light has a method on()
    }
}

// Invoker
public class RemoteControl {
    Command slot;  // one slot to hold one command, control one device
    public RemoteControl() {}
    public void setCommand(Command command) { // modify the behavior of the button
        slot = command;  // pass in command
    }
    public void buttonWasPressed() { // called when button is pressed
        slot.execute(); 
    }
}

// Client
public class RemoteControlTest {
    public static void main(String[] args) {
        RemoteControl remote = new RemoteControl(); // invoker
        Light light = new Light(); // receiver
        LightOnCommand lightOn = new LightOnCommand(light); // create a command and pass to receiver
        remote.setCommand(lightOn); // pass the command to the invoker
        remote.buttonWasPressed();
    }
}




// ADAPTER

// Target interface
public interface Duck {
    public void quack();
    public void fly();
    public void bjky();
}

// Adaptee
public interface Turkey {
    public void gobble();
    public void fly();
}

// Adapter
public class TurkeyAdapter implements Duck {
    Turkey turkey = null;
    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }
    // ideal interfaces
    public void quack() { turkey.gobble(); } 
    public void fly() {
        for (int i = 0; i < 5; i++)
            turkey.fly();
    }
    // other nonsupported ideal interfaces
    public void bjky() {
        throw new UnsupportedOperationException();
    }
}




// Template

public abstract class CaffeineBeverage {
    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    // varying steps
    abstract void brew();
    abstract void addCondiments();

    // common steps
    void boilWater() { }
    void pourInCup() { }
}


// State


// Context
public class GumballMachine {
    State soldOutState;
    State noQuarterState;
    State hasQuarterState;
    State soldState;

    State state = soldOutState;
    int count = 0;

    public GumballMachine(int nGumballs) {
        soldOutState = new SoldOutState(this);
        noQuarterState = new NoQuarterState(this);
        hasQuarterState = new HasQuarterState(this);
        soldState = new SoldState(this);
        this.count = nGumballs;
        if (nGumballs > 0) { state = noQuarterState; }
    }

    // transitions
    public void insert() { state.insert(); }
    public void eject() { state.eject(); }
    public void turnCrank() { state.turnCrank(); }

    // setter/getter methods
    public State getNoQuarterState() { return noQuarterState; }
    ...
    public void setState(State state) { this.state = state; }
}

/* =========================================================*/
// State
public interface State {
    // transitions
    public void insert(); 
    public void eject();
    public void turnCrank();
    public void dispense();
}
public class SoldState implements State {
    GumballMachine machine;
    public SoldState(GumballMachine machine) {
        this.machine = machine;
    }
    
    // transitions
    public void insert() { }
    public void eject() { }
    public void turnCrank() { }
    public void dispense() {
        machine.releaseBall();
        if (machine.getCount()>0) { // set conditions 
            machine.setState(machine.getNoQuarterState()); // set state transition
        } else {
            machine.setState(machine.getSoldOutState()); 
        }
    }
}





public char[] foo(Object x, int z)
    if (x != null) { 
        n = x.f;
    } else { 
        n = z-1;
        z++; 
    }
    a = new char[n]; 
    return a;
}