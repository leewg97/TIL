package stream;

import stream.model.Order;
import stream.model.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static stream.model.Order.OrderStatus.*;

public class Chapter6_4 {

    public static void main(String[] args) {
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

        List<String> emails = new ArrayList<>();
        for (User user : users) {
            if (!user.isVerified()) {
                String email = user.getEmailAddress();
                emails.add(email);
            }
        }
        System.out.println(emails);

        List<String> emailList = users.stream()
                .filter(user -> !user.isVerified())
                .map(User::getEmailAddress)
                .collect(Collectors.toList());
        System.out.println(emailList);

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

        // TODO : Find orders in Error status, and extract createdByUserIds as a list
        List<Long> userIds = orders.stream()
                .filter(order -> order.getStatus().equals(ERROR))
                .map(Order::getCreatedByUserId)
                .collect(Collectors.toList());
        System.out.println(userIds);

        // TODO : Find orders in Error status and created within 24 hours
        List<Order> findOrdersInErrorStatusIn24hrs = orders.stream()
                .filter(order -> order.getStatus().equals(ERROR))
                .filter(order -> order.getCreatedAt().isAfter(now.minusHours(24)))
                .collect(Collectors.toList());
        System.out.println(findOrdersInErrorStatusIn24hrs);
    }
}
