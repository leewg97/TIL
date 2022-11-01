package functionalInterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class chapter4_2 {

    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(4, 2, 3);
        Consumer<Integer> integerConsumer = x -> System.out.println("Processing Integer" + x);
        process(integerList, integerConsumer);

        Consumer<Integer> anotherIntegerProcessor = x -> System.out.println("Another Processing Integer" + x);
        process(integerList, anotherIntegerProcessor);

        Consumer<Double> doubleConsumer = x -> System.out.println("Double Processing" + x);
        List<Double> doubles = Arrays.asList(1.1, 2.2, 3.3);
        process(doubles, doubleConsumer);
    }

    public static <T> void process(List<T> inputs, Consumer<T> processor) {
        for (T input : inputs) {
            processor.accept(input);
        }
    }

}
