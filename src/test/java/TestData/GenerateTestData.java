package TestData;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

import java.util.Random;

public class GenerateTestData {

    Faker faker = new Faker();

    String firstName =faker.address().lastName();
    String surname =faker.name().lastName();
    String email = firstName+surname+ "@gmail.com";
    String emailWilbert = firstName.trim().toUpperCase() + "." + surname.trim().toLowerCase() + "@gmail.com";

    Random random = new Random();
    int number = random.nextInt(1455466,8986557);



    @Test
    public void deleteThisWhenDone(){
        System.out.println(number);

    }

}