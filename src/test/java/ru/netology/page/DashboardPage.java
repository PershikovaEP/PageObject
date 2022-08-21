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
    private static ElementsCollection ids = $$("li div");



    public DashboardPage() {
        heading.shouldBe(visible);  //проверка видимости или в конструкторе или в методе ниже
    }

    public void verifyIsDashboardPage(){
        heading.shouldBe(visible);
    }

    public static int getCardBalance(String id) {
        val text = cards.find(attribute("data-test-id", id)).text();
        return extractBalance(text);
    }

    private static int extractBalance(String text) {
//        val start = text.indexOf(balanceStart);
//        val finish = text.indexOf(balanceFinish);
//        val value = text.substring(start + balanceStart.length(), finish);
//        или так
        val start = text.split(":");
        val value = start[1].substring(0, start[1].indexOf("р.")).trim();
        return Integer.parseInt(value);
    }

    public static String getId1() {
        String id1 = ids.first().getAttribute("data-test-id");
        return id1;
    }

    public static String getId2() {
        String id2 = ids.last().getAttribute("data-test-id");
        return id2;
    }

//    выбираем карту, которую будем пополнять и переходим на страницу пополнения
    public DashboardPage choosingACardToTopUpYourBalance(String id) {
        $("[data-test-id='" + id + "'] [data-test-id='action-deposit']").click();
        return new DashboardPage();
    }

//   для факер берем лимит баланса той карты, с которой переводим
    public static String getAmount(String id){
        Faker faker = new Faker();
        int amount = faker.number().numberBetween(0, getCardBalance(id));
        String result = valueOf(amount);
        return result;
    }

//  пополняем баланс. индекс 0-номер первой карты, 1-номер второй карты
    public DashboardPage transfer(String amount, int index) {
        $("[data-test-id='amount'] input").setValue(amount);
        $("[data-test-id='from'] input").setValue(DataHelper.getNumberCard(index));
        $("[data-test-id='action-transfer']").click();
        return new DashboardPage();
    }

//    проверяем баланс
    public void chekingBalance(String id, int balance) {
        Assertions.assertEquals(getCardBalance(id),balance);
    }
}
