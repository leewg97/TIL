package lambda_Expression;

import lambda_Expression.util.TriFunction;

public class chapter3_3 {

    public static void main(String[] args) {
        TriFunction<Integer, Integer, Integer, Integer> addThreeNumbers = (x, y, z) -> x + y + z;
        Integer apply = addThreeNumbers.apply(3, 5, 6);
        System.out.println(apply);
    }

}
