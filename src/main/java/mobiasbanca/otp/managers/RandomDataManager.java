package mobiasbanca.otp.managers;

import com.github.javafaker.Faker;

public class RandomDataManager {

    private static Faker fakerObject = new Faker();

    public static String getRandomEmail() {
        return fakerObject.internet().emailAddress();
    }

    public static String getRandomFirstName() {
        return fakerObject.name().firstName();
    }

    public static String getRandomLastName() {
        return fakerObject.name().lastName();
    }

    public static String getRandomPassword() {
        return fakerObject.internet().password();
    }

}
