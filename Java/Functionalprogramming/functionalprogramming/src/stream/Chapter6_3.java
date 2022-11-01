package stream;

import stream.model.Order;
import stream.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static stream.model.Order.OrderStatus.*;

public class Chapter6_3 {

    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(3, 7, -5);
        List<Integer> integerListX2 = integerList.stream()
                .map(x -> x * 2)
                .collect(Collectors.toList());
        System.out.println(integerListX2);

        Stream<Integer> integerStream = integerList.stream();
        List<String> stringList = integerStream
                .map(x -> "Number is " + x)
                .collect(Collectors.toList());
        System.out.println(stringList);

        User user1 = new User()
                .setId(101)
                .setName("Chris")
                .setVerified(true)
                .setEmailAddress("chris@gmail.com");
        User user2 = new User()
                .setId(102)
                .setName("Nick")
                .setVerified(false)
                .setEmailAddress("nick@gmail.com");
        User user3 = new User()
                .setId(103)
                .setName("Charlie")
                .setVerified(false)
                .setEmailAddress("charlie@gmail.com");
        List<User> users = Arrays.asList(user1, user2, user3);
        List<String> emailAddresses = users.stream()
                .map(User::getEmailAddress)
                .collect(Collectors.toList());
        System.out.println(emailAddresses);

        Order order1 = new Order()
                .setId(1001L)
                .setStatus(CREATED)
                .setCreatedByUserId(101L);
        Order order2 = new Order()
                .setId(1002L)
                .setStatus(ERROR)
                .setCreatedByUserId(103L);
        Order order3 = new Order()
                .setId(1003L)
                .setStatus(PROCESSED)
                .setCreatedByUserId(104L);
        Order order4 = new Order()
                .setId(1004L)
                .setStatus(ERROR)
                .setCreatedByUserId(102L);
        Order order5 = new Order()
                .setId(1005L)
                .setStatus(IN_PROGRESS)
                .setCreatedByUserId(101L);
        List<Order> orders = Arrays.asList(order1,  order2, order3, order4, order5);
        // TODO: Create list of createdByUserId
        List<Long> stringStream = orders.stream()
                .map(Order::getCreatedByUserId)
                .collect(Collectors.toList());
        System.out.println(stringStream);
    }
}
