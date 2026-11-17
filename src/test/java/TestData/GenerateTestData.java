package TestData;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

public class GenerateTestData {
    Faker faker = new Faker();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = firstName.trim().toLowerCase() + "." +lastName.trim().toLowerCase() + "@gmail.com";

    @Test
    public void TestingUserName(){
        System.out.println(firstName + " " + lastName);
        System.out.println(email);
    }
}
