package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPageV1;
import ru.netology.page.TransferPage;


import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.valueOf;

class MoneyTransferTest {

    @Test
    void shouldTransferMoneyOnCard1WithCard2() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var currentBalance2Card = DashboardPage.getCardBalance(DataHelper.getCard(1).getId());//баланс 2 карты
        var currentBalance1Card = DashboardPage.getCardBalance(DataHelper.getCard(0).getId());//баланс 1 карты
        var sumTranslation = DataHelper.getAmount(DataHelper.getCard(1).getId()); // вычисляем лимит баланса от 2 карты на старнице dashboard, чтобы использовать на странице transfer
        var cardNumber = DataHelper.getCard(1).getNumber(); //пополнение со 2 карты, номер определяем на старнице dashboard, чтобы передать его на странице transfer
        var choosingACard = dashboardPage.choosingACardToTopUpYourBalance(DataHelper.getCard(0).getId()); //выбираем пополнение 1 карты
        choosingACard.transfer(sumTranslation, cardNumber);
        int balanceAfterTransfer2Card = currentBalance2Card - Integer.parseInt(sumTranslation);
        int balanceAfterTransfer1Card = currentBalance1Card + Integer.parseInt(sumTranslation);
        assertEquals(balanceAfterTransfer2Card, DashboardPage.getCardBalance(DataHelper.getCard(1).getId()));
        assertEquals(balanceAfterTransfer1Card, DashboardPage.getCardBalance(DataHelper.getCard(0).getId()));
    }



    @Test
    void shouldTransferMoneyOnCard2WithCard1() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var currentBalance2Card = DashboardPage.getCardBalance(DataHelper.getCard(1).getId());//баланс 2 карты
        var currentBalance1Card = DashboardPage.getCardBalance(DataHelper.getCard(0).getId());//баланс 1 карты
        var sumTranslation = DataHelper.getAmount(DataHelper.getCard(0).getId()); //вычисляем лимит баланса от 1 карты на старнице dashboard, чтобы использовать на странице transfer
        var cardNumber = DataHelper.getCard(0).getNumber(); //пополнение с 1 карты, номер определяем на старнице dashboard, чтобы передать его на странице transfer
        var choosingACard = dashboardPage.choosingACardToTopUpYourBalance(DataHelper.getCard(1).getId()); //выбираем пополнение 2 карты
        choosingACard.transfer(sumTranslation, cardNumber);
        int balanceAfterTransfer2Card = currentBalance2Card + Integer.parseInt(sumTranslation);
        int balanceAfterTransfer1Card = currentBalance1Card - Integer.parseInt(sumTranslation);
        assertEquals(balanceAfterTransfer2Card, DashboardPage.getCardBalance(DataHelper.getCard(1).getId()));
        assertEquals(balanceAfterTransfer1Card, DashboardPage.getCardBalance(DataHelper.getCard(0).getId()));
    }

    @Test
    void shouldTransferMoneyOnCard2WithCard2() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var currentBalance2Card = DashboardPage.getCardBalance(DataHelper.getCard(1).getId());//баланс 2 карты
        var currentBalance1Card = DashboardPage.getCardBalance(DataHelper.getCard(0).getId());//баланс 1 карты
        var sumTranslation = DataHelper.getAmount(DataHelper.getCard(1).getId()); //вычисляем лимит баланса от 2 карты на старнице dashboard, чтобы использовать на странице transfer
        var cardNumber = DataHelper.getCard(1).getNumber(); //пополнение со 2 карты, номер определяем на старнице dashboard, чтобы передать его на странице transfer
        var choosingACard = dashboardPage.choosingACardToTopUpYourBalance(DataHelper.getCard(1).getId()); //выбираем пополнение 2 карты
        choosingACard.transfer(sumTranslation, cardNumber);
        int balanceAfterTransfer2Card = currentBalance2Card;
        int balanceAfterTransfer1Card = currentBalance1Card;
        assertEquals(balanceAfterTransfer2Card, DashboardPage.getCardBalance(DataHelper.getCard(1).getId()));
        assertEquals(balanceAfterTransfer1Card, DashboardPage.getCardBalance(DataHelper.getCard(0).getId()));
    }

    @Test
    void shouldTransferMoneyOnCard1WithCard1() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var currentBalance2Card = DashboardPage.getCardBalance(DataHelper.getCard(1).getId());//баланс 2 карты
        var currentBalance1Card = DashboardPage.getCardBalance(DataHelper.getCard(0).getId());//баланс 1 карты
        var sumTranslation = DataHelper.getAmount(DataHelper.getCard(0).getId()); //вычисляем лимит баланса от 1 карты на старнице dashboard, чтобы использовать на странице transfer
        var cardNumber = DataHelper.getCard(0).getNumber(); //пополнение с 1 карты, номер определяем на старнице dashboard, чтобы передать его на странице transfer
        var choosingACard = dashboardPage.choosingACardToTopUpYourBalance(DataHelper.getCard(0).getId()); //выбираем пополнение 1 карты
        choosingACard.transfer(sumTranslation, cardNumber);
        int balanceAfterTransfer2Card = currentBalance2Card;
        int balanceAfterTransfer1Card = currentBalance1Card;
        assertEquals(balanceAfterTransfer2Card, DashboardPage.getCardBalance(DataHelper.getCard(1).getId()));
        assertEquals(balanceAfterTransfer1Card, DashboardPage.getCardBalance(DataHelper.getCard(0).getId()));

    }

    @Test
    void shouldTransferMoneyOnCard1WithCard2NegativeTranslation() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var currentBalance2Card = DashboardPage.getCardBalance(DataHelper.getCard(1).getId());//баланс 2 карты
        var currentBalance1Card = DashboardPage.getCardBalance(DataHelper.getCard(0).getId());//баланс 1 карты
        var sumTranslation = "-" + DataHelper.getAmount(DataHelper.getCard(1).getId()); //вычисляем лимит баланса от 2 карты на старнице dashboard, чтобы использовать на странице transfer. сумма отрицательная
        var cardNumber = DataHelper.getCard(1).getNumber(); //пополнение со 2 карты, номер определяем на старнице dashboard, чтобы передать его на странице transfer
        var choosingACard = dashboardPage.choosingACardToTopUpYourBalance(DataHelper.getCard(0).getId()); //выбираем пополнение 1 карты
        choosingACard.transfer(sumTranslation, cardNumber);
        int balanceAfterTransfer2Card = currentBalance2Card - Integer.parseInt(sumTranslation.substring(1));
        int balanceAfterTransfer1Card = currentBalance1Card + Integer.parseInt(sumTranslation.substring(1));
        assertEquals(balanceAfterTransfer2Card, DashboardPage.getCardBalance(DataHelper.getCard(1).getId()));
        assertEquals(balanceAfterTransfer1Card, DashboardPage.getCardBalance(DataHelper.getCard(0).getId()));
    }

    @Test
    void shouldTransferMoneyOn1CardWith2Card1Kopecks() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        double currentBalance2Card = DashboardPage.getCardBalance(DataHelper.getCard(1).getId());//баланс 2 карты
        double currentBalance1Card = DashboardPage.getCardBalance(DataHelper.getCard(0).getId());//баланс 1 карты
        var cardNumber = DataHelper.getCard(1).getNumber(); //пополнение со 2 карты, номер определяем на старнице dashboard, чтобы передать его на странице transfer
        var choosingACard = dashboardPage.choosingACardToTopUpYourBalance(DataHelper.getCard(0).getId()); //выбираем пополнение 1 карты
        choosingACard.transfer("0,01", cardNumber); // 1 копейку переводим
        double balanceAfterTransfer2Card = currentBalance2Card - 0.01;
        double balanceAfterTransfer1Card = currentBalance1Card + 0.01; //сумма меняется на 1 копейку
        assertEquals(balanceAfterTransfer2Card, DashboardPage.getCardBalance(DataHelper.getCard(1).getId()));
        assertEquals(balanceAfterTransfer1Card, DashboardPage.getCardBalance(DataHelper.getCard(0).getId()));
    }

    @Test
    void shouldTransferMoneyOnCard2WihtCard1TranslationOverLimit() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
//        var currentBalance2Card = DashboardPage.getCardBalance(DataHelper.getCard(1).getId());//баланс 2 карты
//        var currentBalance1Card = DashboardPage.getCardBalance(DataHelper.getCard(0).getId());//баланс 1 карты
        var cardNumber = DataHelper.getCard(0).getNumber(); //пополнение с 1 карты, номер определяем на старнице dashboard, чтобы передать его на странице transfer
        var sumTranslation = DataHelper.getAmount(DataHelper.getCard(0).getId()) + "00"; //лимит баланса от 1 карты. сумма в 100 раз больше лимита
        var choosingACard = dashboardPage.choosingACardToTopUpYourBalance(DataHelper.getCard(1).getId()); //выбираем пополнение 2 карты
        choosingACard.transfer(sumTranslation, cardNumber);
        $("[data-test-id='error-notification]").shouldBe(Condition.visible);

    }

    @Test
    void shouldTransferMoneyOnCard2WihtCard1OfEntireAmount() {
        Configuration.timeout = 10000;
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var currentBalance2Card = DashboardPage.getCardBalance(DataHelper.getCard(1).getId());//баланс 2 карты
        var currentBalance1Card = DashboardPage.getCardBalance(DataHelper.getCard(0).getId());//баланс 1 карты
        var cardNumber = DataHelper.getCard(0).getNumber(); //пополнение с 1 карты, номер определяем на старнице dashboard, чтобы передать его на странице transfer
        var choosingACard = dashboardPage.choosingACardToTopUpYourBalance(DataHelper.getCard(1).getId()); //выбираем пополнение 2 карты
        var transfer = choosingACard.transfer(Integer.toString(currentBalance1Card), cardNumber); // сумма намного больше лимита
        int balanceAfterTransfer2Card = currentBalance2Card + currentBalance1Card;
        int balanceAfterTransfer1Card = currentBalance1Card - currentBalance1Card;
        assertEquals(balanceAfterTransfer2Card, DashboardPage.getCardBalance(DataHelper.getCard(1).getId()));
        assertEquals(balanceAfterTransfer1Card, DashboardPage.getCardBalance(DataHelper.getCard(0).getId()));

    }
}
