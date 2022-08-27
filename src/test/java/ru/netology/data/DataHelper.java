package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;
import ru.netology.page.DashboardPage;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.valueOf;

public class DataHelper {
    private DataHelper() {}

    @Value
    public static class AuthInfo {
        private String login;
        private String password;

    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }


   @Value
   public static class Card {
        String number;
        String id;

   }

   public static Card getCard(int numberCard) {  //0 - 1 карта, 1 - 2 карта
        Card card1 = new Card("5559000000000001", $x("//div[contains(text(), '0001')]").getAttribute("data-test-id"));
        Card card2 = new Card("5559000000000002", $x("//div[contains(text(), '0002')]").getAttribute("data-test-id"));
        Card[] cards = {card1, card2};
        return cards[numberCard];
   }

    //   для факер берем лимит баланса той карты, с которой переводим
    public static String getAmount(String id){
        Faker faker = new Faker();
        int amount = faker.number().numberBetween(0, DashboardPage.getCardBalance(id));
        String result = valueOf(amount);
        return result;
    }


}
