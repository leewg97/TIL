package method_Reference.model;

public class Van extends Car {

    public Van(String name, String brand) {
        super(name, brand);
    }

    public void drive() {
        System.out.println("Drive a van " + name + " from " + brand);
    }

}
