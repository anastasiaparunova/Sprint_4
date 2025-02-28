import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageElements.MainPageElements;
import pageElements.OrderPersonInfo;

import java.time.Duration;

@RunWith(Parameterized.class)
public class ErrorMessagesForInvalidInputs {

    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String expectedErrorMessage;
    private final By errorLocator;

    private WebDriver driver;

    public ErrorMessagesForInvalidInputs(String name, String surname, String address, String metro, String phone, String expectedErrorMessage, By errorLocator) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.expectedErrorMessage = expectedErrorMessage;
        this.errorLocator = errorLocator;
    }

    @Parameterized.Parameters
    public static Object[][] testingData() {
        return new Object[][]{

                //Невалидные значения (кроме станции метро)
                {"1234", "Парунова", "проспект 50 лет Октября", "Сокольники", "89631235643", "Введите корректное имя", OrderPersonInfo.getPersonNameError()},
                {"Настя", "Test123", "проспект 50 лет Октября", "Сокольники", "89631235643", "Введите корректную фамилию", OrderPersonInfo.getPersonSurnameError()},
                {"Настя", "Парунова", "Test@!123", "Сокольники", "89631235643", "Введите корректный адрес", OrderPersonInfo.getDeliveryAddressError()},
                {"Настя", "Парунова", "проспект 50 лет Октября", "Сокольники", "89abc123def", "Введите корректный номер", OrderPersonInfo.getPhoneNumberError()}
        };
    }

    ;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    public void test() {
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

        //Смотрим, какой текст у нас в ошибке
        String actualError = objOrderPersonInfo.getErrorMessage(errorLocator);

        Assert.assertTrue("Ошибка не отображается", objOrderPersonInfo.isElementDisplayed(errorLocator));
        Assert.assertEquals("Ошибка не совпадает", expectedErrorMessage, actualError);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
