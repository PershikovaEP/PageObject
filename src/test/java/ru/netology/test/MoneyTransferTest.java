package ru.netology.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPageV1;

import static com.codeborne.selenide.Selenide.open;


class MoneyTransferTest {


    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
        LoginPageV1 loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        loginPage.validLogin(authInfo).validVerify(verificationCode);
    }

    @Test
    void shouldTransferMoneyOnCard1WithCard2() {
        DashboardPage dashboardPage = new DashboardPage();
        var currentBalance2Card = dashboardPage.getCardBalance(DataHelper.getCard(1).getId());//баланс 2 карты
        var currentBalance1Card = dashboardPage.getCardBalance(DataHelper.getCard(0).getId());//баланс 1 карты
        var sumTranslation = DataHelper.getAmount(currentBalance2Card); // вычисляем лимит баланса от 2 карты на старнице dashboard, чтобы использовать на странице transfer
        var cardNumber = DataHelper.getCard(1).getNumber(); //пополнение со 2 карты, номер определяем на старнице dashboard, чтобы передать его на странице transfer
        var choosingACard = dashboardPage.choosingACardToTopUpYourBalance(DataHelper.getCard(0).getId()); //выбираем пополнение 1 карты
        choosingACard.transfer(sumTranslation, cardNumber);
        int balanceAfterTransfer2Card = currentBalance2Card - Integer.parseInt(sumTranslation);
        int balanceAfterTransfer1Card = currentBalance1Card + Integer.parseInt(sumTranslation);
        assertEquals(balanceAfterTransfer2Card, dashboardPage.getCardBalance(DataHelper.getCard(1).getId()));
        assertEquals(balanceAfterTransfer1Card, dashboardPage.getCardBalance(DataHelper.getCard(0).getId()));
    }



    @Test
    void shouldTransferMoneyOnCard2WithCard1() {
        DashboardPage dashboardPage = new DashboardPage();
        var currentBalance2Card = dashboardPage.getCardBalance(DataHelper.getCard(1).getId());//баланс 2 карты
        var currentBalance1Card = dashboardPage.getCardBalance(DataHelper.getCard(0).getId());//баланс 1 карты
        var sumTranslation = DataHelper.getAmount(currentBalance1Card); //вычисляем лимит баланса от 1 карты на старнице dashboard, чтобы использовать на странице transfer
        var cardNumber = DataHelper.getCard(0).getNumber(); //пополнение с 1 карты, номер определяем на старнице dashboard, чтобы передать его на странице transfer
        var choosingACard = dashboardPage.choosingACardToTopUpYourBalance(DataHelper.getCard(1).getId()); //выбираем пополнение 2 карты
        choosingACard.transfer(sumTranslation, cardNumber);
        int balanceAfterTransfer2Card = currentBalance2Card + Integer.parseInt(sumTranslation);
        int balanceAfterTransfer1Card = currentBalance1Card - Integer.parseInt(sumTranslation);
        assertEquals(balanceAfterTransfer2Card, dashboardPage.getCardBalance(DataHelper.getCard(1).getId()));
        assertEquals(balanceAfterTransfer1Card, dashboardPage.getCardBalance(DataHelper.getCard(0).getId()));
    }

    @Test
    void shouldTransferMoneyOnCard2WithCard2() {
        DashboardPage dashboardPage = new DashboardPage();
        var currentBalance2Card = dashboardPage.getCardBalance(DataHelper.getCard(1).getId());//баланс 2 карты
        var currentBalance1Card = dashboardPage.getCardBalance(DataHelper.getCard(0).getId());//баланс 1 карты
        var sumTranslation = DataHelper.getAmount(currentBalance2Card); //вычисляем лимит баланса от 2 карты на старнице dashboard, чтобы использовать на странице transfer
        var cardNumber = DataHelper.getCard(1).getNumber(); //пополнение со 2 карты, номер определяем на старнице dashboard, чтобы передать его на странице transfer
        var choosingACard = dashboardPage.choosingACardToTopUpYourBalance(DataHelper.getCard(1).getId()); //выбираем пополнение 2 карты
        choosingACard.transfer(sumTranslation, cardNumber);
        int balanceAfterTransfer2Card = currentBalance2Card;
        int balanceAfterTransfer1Card = currentBalance1Card;
        assertEquals(balanceAfterTransfer2Card, dashboardPage.getCardBalance(DataHelper.getCard(1).getId()));
        assertEquals(balanceAfterTransfer1Card, dashboardPage.getCardBalance(DataHelper.getCard(0).getId()));
    }

    @Test
    void shouldTransferMoneyOnCard1WithCard1() {
        DashboardPage dashboardPage = new DashboardPage();
        var currentBalance2Card = dashboardPage.getCardBalance(DataHelper.getCard(1).getId());//баланс 2 карты
        var currentBalance1Card = dashboardPage.getCardBalance(DataHelper.getCard(0).getId());//баланс 1 карты
        var sumTranslation = DataHelper.getAmount(currentBalance1Card); //вычисляем лимит баланса от 1 карты на старнице dashboard, чтобы использовать на странице transfer
        var cardNumber = DataHelper.getCard(0).getNumber(); //пополнение с 1 карты, номер определяем на старнице dashboard, чтобы передать его на странице transfer
        var choosingACard = dashboardPage.choosingACardToTopUpYourBalance(DataHelper.getCard(0).getId()); //выбираем пополнение 1 карты
        choosingACard.transfer(sumTranslation, cardNumber);
        int balanceAfterTransfer2Card = currentBalance2Card;
        int balanceAfterTransfer1Card = currentBalance1Card;
        assertEquals(balanceAfterTransfer2Card, dashboardPage.getCardBalance(DataHelper.getCard(1).getId()));
        assertEquals(balanceAfterTransfer1Card, dashboardPage.getCardBalance(DataHelper.getCard(0).getId()));

    }

    @Test
    void shouldTransferMoneyOnCard1WithCard2NegativeTranslation() {
        DashboardPage dashboardPage = new DashboardPage();
        var currentBalance2Card = dashboardPage.getCardBalance(DataHelper.getCard(1).getId());//баланс 2 карты
        var currentBalance1Card = dashboardPage.getCardBalance(DataHelper.getCard(0).getId());//баланс 1 карты
        var sumTranslation = "-" + DataHelper.getAmount(currentBalance2Card); //вычисляем лимит баланса от 2 карты на старнице dashboard, чтобы использовать на странице transfer. сумма отрицательная
        var cardNumber = DataHelper.getCard(1).getNumber(); //пополнение со 2 карты, номер определяем на старнице dashboard, чтобы передать его на странице transfer
        var choosingACard = dashboardPage.choosingACardToTopUpYourBalance(DataHelper.getCard(0).getId()); //выбираем пополнение 1 карты
        choosingACard.transfer(sumTranslation, cardNumber);
        int balanceAfterTransfer2Card = currentBalance2Card - Integer.parseInt(sumTranslation.substring(1));
        int balanceAfterTransfer1Card = currentBalance1Card + Integer.parseInt(sumTranslation.substring(1));
        assertEquals(balanceAfterTransfer2Card, dashboardPage.getCardBalance(DataHelper.getCard(1).getId()));
        assertEquals(balanceAfterTransfer1Card, dashboardPage.getCardBalance(DataHelper.getCard(0).getId()));
    }

    @Test
    void shouldTransferMoneyOn1CardWith2Card1Kopecks() {
        DashboardPage dashboardPage = new DashboardPage();
        double currentBalance2Card = dashboardPage.getCardBalance(DataHelper.getCard(1).getId());//баланс 2 карты
        double currentBalance1Card = dashboardPage.getCardBalance(DataHelper.getCard(0).getId());//баланс 1 карты
        var cardNumber = DataHelper.getCard(1).getNumber(); //пополнение со 2 карты, номер определяем на старнице dashboard, чтобы передать его на странице transfer
        var choosingACard = dashboardPage.choosingACardToTopUpYourBalance(DataHelper.getCard(0).getId()); //выбираем пополнение 1 карты
        choosingACard.transfer("0,01", cardNumber); // 1 копейку переводим
        double balanceAfterTransfer2Card = currentBalance2Card - 0.01;
        double balanceAfterTransfer1Card = currentBalance1Card + 0.01; //сумма меняется на 1 копейку
        assertEquals(balanceAfterTransfer2Card, dashboardPage.getCardBalance(DataHelper.getCard(1).getId()));
        assertEquals(balanceAfterTransfer1Card, dashboardPage.getCardBalance(DataHelper.getCard(0).getId()));
    }

    @Test
    void shouldTransferMoneyOnCard2WihtCard1TranslationOverLimit() {
        DashboardPage dashboardPage = new DashboardPage();
        var cardNumber = DataHelper.getCard(0).getNumber(); //пополнение с 1 карты, номер определяем на старнице dashboard, чтобы передать его на странице transfer
        var choosingACard = dashboardPage.choosingACardToTopUpYourBalance(DataHelper.getCard(1).getId()); //выбираем пополнение 2 карты
        choosingACard.transfer("1000000", cardNumber).popUpError(); //переводим сумму сверх лимита карт

    }

    @Test
    void shouldTransferMoneyOnCard2WihtCard1OfEntireAmount() {
        DashboardPage dashboardPage = new DashboardPage();
        var currentBalance2Card = dashboardPage.getCardBalance(DataHelper.getCard(1).getId());//баланс 2 карты
        var currentBalance1Card = dashboardPage.getCardBalance(DataHelper.getCard(0).getId());//баланс 1 карты
        var cardNumber = DataHelper.getCard(0).getNumber(); //пополнение с 1 карты, номер определяем на старнице dashboard, чтобы передать его на странице transfer
        var choosingACard = dashboardPage.choosingACardToTopUpYourBalance(DataHelper.getCard(1).getId()); //выбираем пополнение 2 карты
        var transfer = choosingACard.transfer(Integer.toString(currentBalance1Card), cardNumber); //списываем всю сумму с 1 карты
        int balanceAfterTransfer2Card = currentBalance2Card + currentBalance1Card;
        int balanceAfterTransfer1Card = currentBalance1Card - currentBalance1Card;
        assertEquals(balanceAfterTransfer2Card, dashboardPage.getCardBalance(DataHelper.getCard(1).getId()));
        assertEquals(balanceAfterTransfer1Card, dashboardPage.getCardBalance(DataHelper.getCard(0).getId()));

    }
}
