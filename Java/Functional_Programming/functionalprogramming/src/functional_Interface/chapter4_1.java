package functional_Interface;

import java.util.function.Supplier;

public class chapter4_1 {

    public static void main(String[] args) {
        Supplier<Double> doubleSupplier = () -> Math.random();
        printRandomDoubles(doubleSupplier, 5);
    }

    public static void printRandomDoubles(Supplier<Double> randomSupplier, Integer count) {
        for (int i = 0; i < count; i++) {
            System.out.println(randomSupplier.get());
        }
    }
}
