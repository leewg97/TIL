package stream;

import stream.model.Order;
import stream.model.OrderLine;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static stream.model.OrderLine.OrderLineType.DISCOUNT;
import static stream.model.OrderLine.OrderLineType.PURCHASE;

public class Chapter6_7 {

    public static void main(String[] args) {
        String[][] cities = new String[][] {
                {"Seoul", "Busan"},
                {"San Francisco", "New York"},
                {"Madrid", "Barcelona"}
        };
        Stream<String[]> cityStream1 = Arrays.stream(cities);
        Stream<Stream<String>> cityStreamStream = cityStream1.map(Arrays::stream);
        List<Stream<String>> cityStreamList = cityStreamStream.collect(Collectors.toList());

        Stream<String[]> cityStream2 = Arrays.stream(cities);
        Stream<String> flattenedCityStream = cityStream2.flatMap(Arrays::stream);
        List<String> flattenedCityList = flattenedCityStream.collect(Collectors.toList());
        System.out.println(flattenedCityList);

        Order order1 = new Order()
                .setId(1001L)
                .setOrderLines(Arrays.asList(
                        new OrderLine()
                                .setId(10001L)
                                .setType(PURCHASE)
                                .setAmount(BigDecimal.valueOf(5000)),
                        new OrderLine()
                                .setId(10002L)
                                .setType(PURCHASE)
                                .setAmount(BigDecimal.valueOf(4000))
                ));
        Order order2 = new Order()
                .setId(1002L)
                .setOrderLines(Arrays.asList(
                        new OrderLine()
                                .setId(10003L)
                                .setType(PURCHASE)
                                .setAmount(BigDecimal.valueOf(2000)),
                        new OrderLine()
                                .setId(10004L)
                                .setType(DISCOUNT)
                                .setAmount(BigDecimal.valueOf(-1000))
                ));
        Order order3 = new Order()
                .setId(1003L)
                .setOrderLines(Arrays.asList(
                        new OrderLine()
                                .setId(10005L)
                                .setType(PURCHASE)
                                .setAmount(BigDecimal.valueOf(2000))
                ));
        List<Order> orders = Arrays.asList(order1, order2, order3);
        List<OrderLine> orderLines = orders.stream()    // Stream<Order>
                .map(Order::getOrderLines)              // Stream<List<OrderLine>>
                .flatMap(List::stream)                  // Stream<OrderLine>
                .collect(Collectors.toList());
        orderLines.forEach(System.out::println);
    }
}
