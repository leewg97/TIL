package stream;

import stream.model.Order;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static stream.model.Order.OrderStatus.*;

public class Chapter6_6 {

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(3, -5, 4, -5, 7, 3, 9, -10);
        List<Integer> distinctIntegers = integers.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println(distinctIntegers);

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        Order order1 = new Order()
                .setId(1001L)
                .setStatus(CREATED)
                .setCreatedByUserId(101L)
                .setCreatedAt(now.minusHours(4));
        Order order2 = new Order()
                .setId(1002L)
                .setStatus(ERROR)
                .setCreatedByUserId(103L)
                .setCreatedAt(now.minusHours(40));
        Order order3 = new Order()
                .setId(1003L)
                .setStatus(PROCESSED)
                .setCreatedByUserId(104L)
                .setCreatedAt(now.minusHours(36));
        Order order4 = new Order()
                .setId(1004L)
                .setStatus(ERROR)
                .setCreatedByUserId(102L)
                .setCreatedAt(now.minusHours(15));
        Order order5 = new Order()
                .setId(1005L)
                .setStatus(IN_PROGRESS)
                .setCreatedByUserId(101L)
                .setCreatedAt(now.minusHours(10));
        List<Order> orders = Arrays.asList(order1,  order2, order3, order4, order5);

        // TODO: created a sorted list of unique CreatedByUserIds from the orders
        List<Long> userIds = orders.stream()
                .map(Order::getCreatedByUserId)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(userIds);
    }
}
