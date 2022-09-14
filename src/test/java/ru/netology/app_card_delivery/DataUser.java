package ru.netology.app_card_delivery;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import lombok.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

class DataUser {
    static Faker faker = new Faker(new Locale("ru"));

    private DataUser() {
    }

    static String cityForInput() {
        Random random = new Random();
        int rand = random.nextInt(12);
        String city[] = {"Екатеринбург", "Йошкар-Ола", "Калининград", "Кемерово", "Киров", "Кострома", "Краснодар",
                "Красноярск", "Курган", "Курск", "Санкт-Петербург", "Сыктывкар", "Чебоксары"};
        return city[rand];
    }



    static String dataPhone() {
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    static String dataName() {
        String name = faker.name().lastName();
        name = name + " " + faker.name().firstName();
        return name;
    }



    static String dataInput(int days) {
        String inputDate = LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        return inputDate;
    }
    static String dataInput2(int days) {
        String inputDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        return inputDate;
    }
}

