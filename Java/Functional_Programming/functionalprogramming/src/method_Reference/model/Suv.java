package method_Reference.model;

public class Suv extends Car {

    public Suv(String name, String brand) {
        super(name, brand);
    }

    public void drive() {
        System.out.println("Drive an SUV " + name + " from " + brand);
    }

}