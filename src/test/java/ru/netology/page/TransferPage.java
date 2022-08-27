package ru.netology.page;

import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    //  пополняем баланс. индекс 0-номер первой карты, 1-номер второй карты
    public DashboardPage transfer(String amount, String numberCard) {
        $("[data-test-id='amount'] input").setValue(amount);
        $("[data-test-id='from'] input").setValue(numberCard);
        $("[data-test-id='action-transfer']").click();
        return new DashboardPage();
    }
}
