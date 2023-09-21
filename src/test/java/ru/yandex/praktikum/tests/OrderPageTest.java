package ru.yandex.praktikum.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import ru.yandex.praktikum.pageobgect.MainPage;
import ru.yandex.praktikum.pageobgect.OrderPage;

@RunWith(Parameterized.class)

public class OrderPageTest {
    WebDriver webDriver;
    private final String name;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String numberPhone;
    private final String time;
    private final String comment;
    private final boolean isOrderButton;

    public OrderPageTest(String name, String lastName, String address, String metroStation, String numberPhone, String time, String comment, boolean isOrderButton) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.numberPhone = numberPhone;
        this.time = time;
        this.comment = comment;
        this.isOrderButton = isOrderButton;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {"Марина", "Столярова", "ул. Артековская, д.6, кв. 27", "Выхино", "79050909090", "17.09.2023", "без комментариев", true},
                {"Ангелина", "Ильченко", "ул. Котовская, д.90, кв. 454", "Университет", "79674657676", "16.09.2023", "без комментариев", true},
                {"Борис", "Изюмов", "ул. Которовская, д.56, кв. 7", "Аннино", "79093456789", "01.01.01", "без комментариев", false},
        };
    }

    @Before
    public void setupAll() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
        WebDriverManager.firefoxdriver().setup();
        webDriver = new FirefoxDriver();
    }

    @Test
    public void shouldBeOrderScooterOnButtonPageHeaderWithRequiredFieldTest() {
        OrderPage orderPage = new OrderPage(webDriver);
        MainPage mainPage = new MainPage(webDriver);
        mainPage.open();
        mainPage.clickButtonCookies();
        mainPage.clickOrderButton(isOrderButton);
        orderPage.enterOrderName(name);
        orderPage.enterOrderLastName(lastName);
        orderPage.enterOrderAddress(address);
        orderPage.enterMetroStation(metroStation);
        orderPage.clickMetroStationFull();
        orderPage.enterNumberPhone(numberPhone);
        orderPage.clickOrderButtonNext();
        orderPage.enterOrderTime(time);
        orderPage.clickOrderRentalPeriod();
        orderPage.enterOrderRentalTwoDay();
        orderPage.clickCheckBoxGreyColorScooter();
        orderPage.enterOrderComment(comment);
        orderPage.clickOrderButtonOnOrderPage();
        orderPage.clickOrderConfirmationButton();
        boolean isDisplayed = orderPage.checkOrderConfirmationIsDisplayed();
        Assert.assertTrue("Confirmation is not displayed", isDisplayed);
    }

    @After
    public void tearDown() {
        // Закрыть браузер
        driver.quit();
    }
}
