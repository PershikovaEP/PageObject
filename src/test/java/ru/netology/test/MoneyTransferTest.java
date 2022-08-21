package ru.netology.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPageV1;
import ru.netology.page.LoginPageV2;
import ru.netology.page.LoginPageV3;

import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.valueOf;

class MoneyTransferTest {

    @BeforeEach
    public void setUp() {
        Configuration.timeout = 15000;
    }

    @Test
    void shouldTransferMoneyOnCard1WithCard2V1() {

        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
//    var loginPage = open("http://localhost:9999", LoginPageV1.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var currentBalance2Card = DashboardPage.getCardBalance(DashboardPage.getId2());
        var currentBalance1Card = DashboardPage.getCardBalance(DashboardPage.getId1());
        var sumTranslation = DashboardPage.getAmount(DashboardPage.getId2()); //лимит баланса от 2 карты
        var choosingACard = dashboardPage.choosingACardToTopUpYourBalance(DashboardPage.getId1()); //выбираем пополнение 1 карты
        var transfer = choosingACard.transfer(sumTranslation, 1); //пополнение со второй карты
        int balanceAfterTransfer2Card = currentBalance2Card - Integer.parseInt(sumTranslation);
        int balanceAfterTransfer1Card = currentBalance1Card + Integer.parseInt(sumTranslation);
        transfer.chekingBalance(DashboardPage.getId2(), balanceAfterTransfer2Card);
        transfer.chekingBalance(DashboardPage.getId1(), balanceAfterTransfer1Card);
    }



    @Test
    void shouldTransferMoneyOnCard2WithCard1V2() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV2();
//    var loginPage = open("http://localhost:9999", LoginPageV2.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
         var dashboardPage = verificationPage.validVerify(verificationCode);
        var currentBalance2Card = DashboardPage.getCardBalance(DashboardPage.getId2());
        var currentBalance1Card = DashboardPage.getCardBalance(DashboardPage.getId1());
        var sumTranslation = DashboardPage.getAmount(DashboardPage.getId1()); //лимит баланса от 1 карты
        var choosingACard = dashboardPage.choosingACardToTopUpYourBalance(DashboardPage.getId2()); //выбираем пополнение 2 карты
        var transfer = choosingACard.transfer(sumTranslation, 0); //пополнение с 1 карты
        int balanceAfterTransfer2Card = currentBalance2Card + Integer.parseInt(sumTranslation);
        int balanceAfterTransfer1Card = currentBalance1Card - Integer.parseInt(sumTranslation);
        transfer.chekingBalance(DashboardPage.getId2(), balanceAfterTransfer2Card);
        transfer.chekingBalance(DashboardPage.getId1(), balanceAfterTransfer1Card);
    }

    @Test
    void shouldTransferMoneyOnCard2WithCard2V2() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV2();
//    var loginPage = open("http://localhost:9999", LoginPageV2.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var currentBalance2Card = DashboardPage.getCardBalance(DashboardPage.getId2());
        var currentBalance1Card = DashboardPage.getCardBalance(DashboardPage.getId1());
        var sumTranslation = DashboardPage.getAmount(DashboardPage.getId2()); //лимит баланса от 2 карты
        var choosingACard = dashboardPage.choosingACardToTopUpYourBalance(DashboardPage.getId2()); //выбираем пополнение 2 карты
        var transfer = choosingACard.transfer(sumTranslation, 1); //пополнение со 2 карты
        int balanceAfterTransfer2Card = currentBalance2Card;
        int balanceAfterTransfer1Card = currentBalance1Card;
        transfer.chekingBalance(DashboardPage.getId2(), balanceAfterTransfer2Card);
        transfer.chekingBalance(DashboardPage.getId1(), balanceAfterTransfer1Card);
    }

    @Test
    void shouldTransferMoneyOnCard1WithCard1V2() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV2();
//    var loginPage = open("http://localhost:9999", LoginPageV2.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var currentBalance2Card = DashboardPage.getCardBalance(DashboardPage.getId2());
        var currentBalance1Card = DashboardPage.getCardBalance(DashboardPage.getId1());
        var sumTranslation = DashboardPage.getAmount(DashboardPage.getId1()); //лимит баланса от 1 карты
        var choosingACard = dashboardPage.choosingACardToTopUpYourBalance(DashboardPage.getId1()); //выбираем пополнение 1 карты
        var transfer = choosingACard.transfer(sumTranslation, 0); //пополнение с 1 карты
        int balanceAfterTransfer2Card = currentBalance2Card;
        int balanceAfterTransfer1Card = currentBalance1Card;
        transfer.chekingBalance(DashboardPage.getId2(), balanceAfterTransfer2Card);
        transfer.chekingBalance(DashboardPage.getId1(), balanceAfterTransfer1Card);
    }

    @Test
    void shouldTransferMoneyOnCard1WithCard2NegativeTranslationV2() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV2();
//    var loginPage = open("http://localhost:9999", LoginPageV2.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var currentBalance2Card = DashboardPage.getCardBalance(DashboardPage.getId2());
        var currentBalance1Card = DashboardPage.getCardBalance(DashboardPage.getId1());
        var sumTranslation = "-" + DashboardPage.getAmount(DashboardPage.getId2()); //лимит баланса от 2 карты. сумма отрицательная
        var choosingACard = dashboardPage.choosingACardToTopUpYourBalance(DashboardPage.getId1()); //выбираем пополнение 1 карты
        var transfer = choosingACard.transfer(sumTranslation, 1); //пополнение со 2 карты
        int balanceAfterTransfer2Card = currentBalance2Card - Integer.parseInt(sumTranslation.substring(1));
        int balanceAfterTransfer1Card = currentBalance1Card + Integer.parseInt(sumTranslation.substring(1));
        transfer.chekingBalance(DashboardPage.getId2(), balanceAfterTransfer2Card);
        transfer.chekingBalance(DashboardPage.getId1(), balanceAfterTransfer1Card);
    }

    @Test
    void shouldTransferMoneyOn1CardWith2Card1KopecksV2() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV2();
//    var loginPage = open("http://localhost:9999", LoginPageV2.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var currentBalance2Card = DashboardPage.getCardBalance(DashboardPage.getId2());
        var currentBalance1Card = DashboardPage.getCardBalance(DashboardPage.getId1());
        var sumTranslation = "0,01"; //1 копейку переводим
        var choosingACard = dashboardPage.choosingACardToTopUpYourBalance(DashboardPage.getId1()); //выбираем пополнение 1 карты
        var transfer = choosingACard.transfer(sumTranslation, 1); //пополнение со 2 карты
        int balanceAfterTransfer2Card = currentBalance2Card; //так как целая сумма на счету, то 20 копеек в расчет не берем, баланс неизменный
        int balanceAfterTransfer1Card = currentBalance1Card;
        transfer.chekingBalance(DashboardPage.getId2(), balanceAfterTransfer2Card);
        transfer.chekingBalance(DashboardPage.getId1(), balanceAfterTransfer1Card);
    }

    @Test
    void shouldTransferMoneyOnCard2WihtCard1TranslationOverLimitV3() {
        var loginPage = open("http://localhost:9999", LoginPageV3.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var currentBalance2Card = DashboardPage.getCardBalance(DashboardPage.getId2());
        var currentBalance1Card = DashboardPage.getCardBalance(DashboardPage.getId1());
        var sumTranslation = DashboardPage.getAmount(DashboardPage.getId1()) + "000"; //лимит баланса от 1 карты. сумма больше лимита
        var choosingACard = dashboardPage.choosingACardToTopUpYourBalance(DashboardPage.getId2()); //выбираем пополнение 2 карты
        var transfer = choosingACard.transfer(sumTranslation, 0); //пополнение с 1 карты
        int balanceAfterTransfer2Card = currentBalance2Card + Integer.parseInt(sumTranslation.substring(0,(sumTranslation.length()-3)));
        int balanceAfterTransfer1Card = currentBalance1Card - Integer.parseInt(sumTranslation.substring(0,(sumTranslation.length()-3)));
        transfer.chekingBalance(DashboardPage.getId2(), balanceAfterTransfer2Card);
        transfer.chekingBalance(DashboardPage.getId1(), balanceAfterTransfer1Card);
    }
}
