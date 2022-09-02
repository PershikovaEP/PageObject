package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import lombok.Value;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.String.valueOf;
import static org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.Conversions.trim;

public class DashboardPage {

    private SelenideElement heading = $("[data-test-id=dashboard]");
    private static ElementsCollection cards = $$(".list__item div");
    private static final String balanceStart = "баланс: ";
    private static final String balanceFinish = " р.";


    public DashboardPage() {
        heading.shouldBe(visible);  //проверка видимости или в конструкторе или в методе ниже
    }

    public int getCardBalance(String id) {
        val text = cards.find(attribute("data-test-id", id)).text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
//        val start = text.indexOf(balanceStart);
//        val finish = text.indexOf(balanceFinish);
//        val value = text.substring(start + balanceStart.length(), finish);
//        или так
        val start = text.split(":");
        val value = start[1].substring(0, start[1].indexOf("р.")).trim();
        return Integer.parseInt(value);
    }

//    выбираем карту, которую будем пополнять и переходим на страницу пополнения
    public TransferPage choosingACardToTopUpYourBalance(String id) {
        $("[data-test-id='" + id + "'] [data-test-id='action-deposit']").click();
        return new TransferPage();
    }

    public void popUpError() {
        $("[data-test-id='error-notification]").shouldBe(Condition.visible);
    }



}
