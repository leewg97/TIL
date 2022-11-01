package stream;

import stream.model.Order;
import stream.model.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static stream.model.Order.OrderStatus.*;

public class Chapter6_5 {

    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(3, -6, 12, 9, 17, -21, 0, 33, -14);
        List<Integer> sortedInteger = integerList.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(sortedInteger);

        User user1 = new User()
                .setId(101)
                .setName("Nick")
                .setVerified(true)
                .setEmailAddress("nick@gmail.com");
        User user2 = new User()
                .setId(102)
                .setName("Chris")
                .setVerified(false)
                .setEmailAddress("chris@gmail.com");
        User user3 = new User()
                .setId(103)
                .setName("Brendon")
                .setVerified(false)
                .setEmailAddress("brendon@gmail.com");
        List<User> users = Arrays.asList(user1, user2, user3);
        List<User> sortedUsers = users.stream()
                .sorted(Comparator.comparing(User::getName))
                .collect(Collectors.toList());
        System.out.println(sortedUsers);

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

        // TODO : sort the orders based on createdAt
        List<Order> sortedOrdersByCreatedAt = orders.stream()
                .sorted(Comparator.comparing(Order::getCreatedAt))
                .collect(Collectors.toList());
        System.out.println(sortedOrdersByCreatedAt);

    }
}
