package lambdaExpression;

import java.util.function.Function;

public class chapter3_1 {

    public static void main(String[] args) {
        Function<Integer, Integer> myAdder = x -> x + 10;

        Integer result = myAdder.apply(3);
        System.out.println(result);
    }

}
