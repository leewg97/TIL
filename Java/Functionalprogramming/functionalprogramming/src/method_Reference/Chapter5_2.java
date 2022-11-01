package method_Reference;

import method_Reference.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;

public class Chapter5_2 {

    public static void main(String[] args) {
        Function<String, Integer> strLength = String::length;
        Integer length = strLength.apply("hello world");
        System.out.println(length);

        BiPredicate<String, String> strEquals = String::equals;
        System.out.println(strEquals.test("hello", "hello"));

        List<User> users = new ArrayList<>();
        users.add(new User(3, "Chris"));
        users.add(new User(1, "Brendon"));
        users.add(new User(5, "Nick"));

        printUserField(users, User::getName);
    }

    public static void printUserField(List<User> users, Function<User, Object> getter) {
        for (User user : users) {
            System.out.println(getter.apply(user));
        }
    }

}
