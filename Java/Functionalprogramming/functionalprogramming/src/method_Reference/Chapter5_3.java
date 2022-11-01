package method_Reference;

import method_Reference.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class Chapter5_3 {

    public static void main(String[] args) {
        Map<String, BiFunction<String, String, Car>> carTypeToConstructorMap = new HashMap<>();
        carTypeToConstructorMap.put("sedan", Sedan::new);
        carTypeToConstructorMap.put("suv", Suv::new);
        carTypeToConstructorMap.put("van", Van::new);

        BiFunction<Integer, String, User> createUser = User::new;
        User brendon = createUser.apply(7, "brendon");
        System.out.println(brendon);

        String[][] inputs = new String[][] {
                {"sedan", "G90", "Genesis"},
                {"sedan", "Model S", "Tesla"},
                {"van", "splinter", "Benz"},
                {"suv", "X7", "BMW"}
        };

        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < inputs.length; i++) {
            String[] input = inputs[i];
            String carType = input[0];
            String carName = input[1];
            String carBrand = input[2];

            cars.add(carTypeToConstructorMap.get(carType).apply(carName, carBrand));
        }

        for (Car car : cars) {
            car.drive();
        }

    }


}
