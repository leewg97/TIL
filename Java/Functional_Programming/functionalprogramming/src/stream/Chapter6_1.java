package stream;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter6_1 {

    public static void main(String[] args) {
        Stream<String> stream = Stream.of("Chris", "Brendon", "Nick");
        List<String> names = stream.collect(Collectors.toList());
        System.out.println(names);

        String[] city = new String[]{"Seoul", "Daejeon", "Busan"};
        Stream<String> cityStream = Arrays.stream(city);
        List<String> cityList = cityStream.collect(Collectors.toList());
        System.out.println(cityList);

        Set<Integer> integerSet = new HashSet<>(Arrays.asList(3, 5, 7));
        Stream<Integer> integerStream = integerSet.stream();
        List<Integer> integerList = integerStream.collect(Collectors.toList());
        System.out.println(integerList);
    }
}
