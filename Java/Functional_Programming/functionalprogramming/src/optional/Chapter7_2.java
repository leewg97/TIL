package optional;

import optional.model.User;

import java.util.Optional;

public class Chapter7_2 {

    public static void main(String[] args) {
        Optional<User> maybeUser = Optional.ofNullable(maybeGetUser(true));
        maybeUser.ifPresent(System.out::println);

        Optional<Integer> maybeId = Optional.ofNullable(maybeGetUser(true))
                .map(User::getId);
        maybeId.ifPresent(System.out::println);

        String userName = Optional.ofNullable(maybeGetUser(false))
                .map(User::getName)
                .map(name -> "The name is " + name)
                .orElse("Name is empty");
        System.out.println(userName);

        Optional<String> maybeEmail = Optional.ofNullable(maybeGetUser(true))
                .flatMap(User::getEmailAddress);
        maybeEmail.ifPresent(System.out::println);
    }

    public static User maybeGetUser(boolean returnUser) {
        if (returnUser) {
            return new User()
                    .setId(1001)
                    .setName("Chris")
                    .setEmailAddress("chris@gmail.com")
                    .setVerified(false);
        }
        return null;
    }

}
