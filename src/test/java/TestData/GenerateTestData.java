package TestData;

import com.github.javafaker.Faker;


import java.util.Random;

public class GenerateTestData {

    Faker faker = new Faker();

    String firstName =faker.address().lastName();
    String surname =faker.name().lastName();
    String email = firstName+surname+ "@gmail.com";
    String emailLydia = firstName.trim().toUpperCase() + "." + surname.trim().toLowerCase() + "@gmail.com";

    Random random = new Random();
    int number = random.nextInt(1455466,8986557);




    }

