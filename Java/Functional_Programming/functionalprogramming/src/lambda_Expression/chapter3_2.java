package lambda_Expression;

import java.util.function.BiFunction;

public class chapter3_2 {

    public static void main(String[] args) {
        // input 2개 (Integer)
        BiFunction<Integer, Integer, Integer> add = (x, y) -> x + y;
        Integer result = add.apply(3, 6);
        System.out.println(result);
    }

}
