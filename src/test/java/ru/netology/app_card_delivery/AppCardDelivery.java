package ru.netology.app_card_delivery;


import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

class AppCardDelivery {

    void dataInput(int days) {
        SelenideElement data = $("[data-test-id=date]");
        data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(DataUser.dataInput(days));
    }
    void dataInput2(int days) {
        SelenideElement data = $("[data-test-id=date]");
        data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(DataUser.dataInput2(days));
    }

    @BeforeEach
    void before() {
        open("http://localhost:9999");
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void getTrueInputValidForm() {
        $("[placeholder=Город]").setValue(DataUser.cityForInput());
        int inDays = 4;
        dataInput(inDays);
        $("[data-test-id=phone]").$("[name=phone]").setValue(DataUser.dataPhone());
        $("[data-test-id=name].input_type_text .input__control").setValue(DataUser.dataName());
        $("[data-test-id=agreement]").click();
        $$("[class=button__text]").find(exactText("Запланировать")).click();
        $("[data-test-id=success-notification]").$("[class=notification__content]")
                .shouldHave(textCaseSensitive("Встреча успешно запланирована на " + DataUser.dataInput(inDays)));
        int inDays2 = 3;
        dataInput2(inDays2);
        $$("[class=button__text]").find(exactText("Запланировать")).click();
        $$("[class=button__text]").find(exactText("Перепланировать")).click();
        $("[data-test-id=success-notification]").$("[class=notification__content]")
                .shouldHave(textCaseSensitive("Встреча успешно запланирована на " + DataUser.dataInput2(inDays2)));
    }

}