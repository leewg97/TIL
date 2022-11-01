package stream;

import stream.model.Order;
import stream.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static stream.model.Order.OrderStatus.*;

public class Chapter6_2 {

    public static void main(String[] args) {
        Stream<Integer> integerStream = Stream.of(3, -5, 8, 15, -20);
        Stream<Integer> filteredIntegerStream = integerStream.filter(x -> x > 0);// Predicate 람다로 만들어 넘김
        List<Integer> filteredIntegerList = filteredIntegerStream.collect(Collectors.toList());
        System.out.println(filteredIntegerList);

        List<Integer> newFilteredIntegerStream = Stream.of(3, -5, 8, 15, -20)
                .filter(x -> x > 0)
                .collect(Collectors.toList());
        System.out.println(newFilteredIntegerStream);

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
        List<User> verifiedUser = users.stream()
                .filter(User::isVerified)
                .collect(Collectors.toList());
        System.out.println(verifiedUser);

        List<User> unVerifiedUser = users.stream()
                .filter(user -> !user.isVerified())
                .collect(Collectors.toList());
        System.out.println(unVerifiedUser);

        Order order1 = new Order()
                .setId(1001L)
                .setStatus(CREATED);
        Order order2 = new Order()
                .setId(1002L)
                .setStatus(ERROR);
        Order order3 = new Order()
                .setId(1003L)
                .setStatus(PROCESSED);
        Order order4 = new Order()
                .setId(1004L)
                .setStatus(ERROR);
        Order order5 = new Order()
                .setId(1005L)
                .setStatus(IN_PROGRESS);
        List<Order> orders = Arrays.asList(order1,  order2, order3, order4, order5);
        // Filter orders in ERROR state
        List<Order> findError = orders.stream()
                .filter(order -> order.getStatus().equals(ERROR))
                .collect(Collectors.toList());
        System.out.println(findError);
    }
}
