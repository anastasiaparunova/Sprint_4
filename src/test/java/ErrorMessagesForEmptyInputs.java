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
public class ErrorMessagesForEmptyInputs {
    private final String expectedErrorMessage;
    private final By errorLocator;

    private WebDriver driver;

    public ErrorMessagesForEmptyInputs(String expectedErrorMessage, By errorLocator) {
        this.expectedErrorMessage = expectedErrorMessage;
        this.errorLocator = errorLocator;
    }

    //Параметры для тестирования.
    @Parameterized.Parameters
    public static Object[][] testingData() {
        return new Object[][]{

                //Пустые значения
                {"Введите корректное имя", OrderPersonInfo.getPersonNameError()},
                {"Введите корректную фамилию", OrderPersonInfo.getPersonSurnameError()},
                {"Введите корректный адрес", OrderPersonInfo.getDeliveryAddressError()},
                {"Выберите станцию", OrderPersonInfo.getDeliveryMetroError()},
                {"Введите корректный номер", OrderPersonInfo.getPhoneNumberError()},
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
    public void shouldShowErrorMessagesForEmptyInput() {

        MainPageElements objMainPage = new MainPageElements(driver);

        //Кликаем на кнопку куки "все уже привыкли"
        objMainPage.clickAcceptCookieButton();
        //Кликаем на кнопку "Заказать" в хедере
        objMainPage.clickOrderButtonHeader();

        OrderPersonInfo objOrderPersonInfo = new OrderPersonInfo(driver);

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
