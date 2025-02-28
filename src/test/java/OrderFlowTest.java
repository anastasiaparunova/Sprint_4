import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageElements.MainPageElements;
import pageElements.OrderPersonInfo;
import pageElements.OrderRentInfo;

import java.time.Duration;

@RunWith(Parameterized.class)
public class OrderFlowTest {
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String duration;
    private final String colour;
    private final String comment;

    private final String expectedSuccessMessage = "Заказ оформлен";

    private WebDriver driver;

    public OrderFlowTest(String name, String surname, String address, String metro, String phone, String date, String duration, String colour, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.duration = duration;
        this.colour = colour;
        this.comment = comment;
    }

    //Параметры для тестирования.
    @Parameterized.Parameters
    public static Object[][] testingData() {
        return new Object[][]{
                {"Настя", "Парунова", "проспект 50 лет Октября", "Сокольники", "89631235643", "06.04.2025", "сутки", "серая безысходность", "Супер-комментарий для супер-курьера"},
                {"Кузьма", "Петров", "Улица Пушкина, дом 2К", "Петровско-Разумовская", "+79021233212", "29.05.2025", "шестеро суток", "чёрный жемчуг", ""},
        };
    }

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    public void testHeader() {

        MainPageElements objMainPage = new MainPageElements(driver);

        //Кликаем на кнопку куки "все уже привыкли"
        objMainPage.clickAcceptCookieButton();
        //Кликаем на кнопку "Заказать" в хедере
        objMainPage.clickOrderButtonHeader();

        OrderPersonInfo objOrderPersonInfo = new OrderPersonInfo(driver);

        //Заполняем поля формы «Для кого самокат»
        objOrderPersonInfo.fillOrderForm(name, surname, address, metro, phone);
        //Нажимаем на кнопку «Далее»
        objOrderPersonInfo.clickButtonNext();

        OrderRentInfo objOrderRentInfo = new OrderRentInfo(driver);

        //Заполняем поля формы «Про аренду»
        objOrderRentInfo.fillRentInfo(date, duration, colour, comment);
        //Нажимаем на кнопку «Заказать»
        objOrderRentInfo.clickButtonToOrder();
        //А потом кнопку «Да»
        objOrderRentInfo.clickButtonYes();

        //Смотрим, какой текст у нас в заголовке
        String actualSuccessMessage;
        actualSuccessMessage = objOrderRentInfo.getOrderHeaderText();

        Assert.assertTrue("Проблема с подтверждением заказа", actualSuccessMessage.contains(expectedSuccessMessage));
    }

    @Test
    public void testMiddle() {

        MainPageElements objMainPage = new MainPageElements(driver);

        //Кликаем на кнопку куки «все уже привыкли»
        objMainPage.clickAcceptCookieButton();
        //Кликаем на кнопку «Заказать» в середине страницы
        objMainPage.clickOrderButtonMiddle();

        OrderPersonInfo objOrderPersonInfo = new OrderPersonInfo(driver);

        //Заполняем поля формы «Для кого самокат»
        objOrderPersonInfo.fillOrderForm(name, surname, address, metro, phone);
        //Нажимаем на кнопку «Далее»
        objOrderPersonInfo.clickButtonNext();

        OrderRentInfo objOrderRentInfo = new OrderRentInfo(driver);

        //Заполняем поля формы «Про аренду»
        objOrderRentInfo.fillRentInfo(date, duration, colour, comment);
        //Нажимаем на кнопку «Заказать»
        objOrderRentInfo.clickButtonToOrder();
        //А потом кнопку «Да»
        objOrderRentInfo.clickButtonYes();

        //Смотрим, какой текст у нас в заголовке
        String actualSuccessMessage;
        actualSuccessMessage = objOrderRentInfo.getOrderHeaderText();

        Assert.assertTrue("Проблема с подтверждением заказа", actualSuccessMessage.contains(expectedSuccessMessage));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
