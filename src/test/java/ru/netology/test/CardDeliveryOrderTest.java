package ru.netology.test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;


import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryOrderTest {
    LocalDate date = LocalDate.now();
    LocalDate dateDelivery = date.plusDays(5);
    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d.MM.uuuu");
    String dataText = dateDelivery.format(formatters);


    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }


    @Test
    void shouldTestAllFields() {
        $$("[class=input__control]").get(0).setValue("Москва");
        $$("[class=input__control]").get(1).sendKeys( Keys.CONTROL +"A",Keys.DELETE);
        $$("[class=input__control]").get(1).setValue(String.valueOf(dataText));
        $$("[class=input__control]").get(2).setValue("Иван Грозный");
        $$("[class=input__control]").get(3).setValue("+79012345678");
        $("[class=checkbox__box]").click();
        $(withText("Забронировать")).click();
        $(withText("Встреча успешно забронирована")).shouldBe(visible, Duration.ofSeconds(15));



    }
}
