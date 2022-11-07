package advanced_Stream;

import advanced_Stream.model.User;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Chapter8_1 {

    public static void main(String[] args) {
        Optional<Integer> max = Stream.of(5, 3, 6, 2, 1)
                .max(Integer::compareTo);
        System.out.println(max.get());

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

        User firstUser = users.stream()
                .min((u1, u2) -> u1.getName().compareTo(u2.getName()))
                .get();

        System.out.println(firstUser);

    }

}
