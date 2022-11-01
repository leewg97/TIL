package method_Reference.model;

public class Sedan extends Car {

    public Sedan(String name, String brand) {
        super(name, brand);
    }

    @Override
    public void drive() {
        System.out.println("Drive a sedan " + name + " from " + brand);
    }
}
