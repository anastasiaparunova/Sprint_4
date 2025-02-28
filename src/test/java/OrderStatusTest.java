import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageElements.MainPageElements;

import java.time.Duration;

public class OrderStatusTest {

    private WebDriver driver;

    //Несуществующий номер заказа
    private final String nonExistingOrder = "test1234";

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

        //Принимаем куки
        objMainPage.clickAcceptCookieButton();
        //Нажимаем на кнопку Статус заказа
        objMainPage.clickOrderStatusButton();
        //Заполняем поле тестовым значением
        objMainPage.fillOrderNumber(nonExistingOrder);
        //Нажимаем на кнопку «Go!»
        objMainPage.clickGoButton();

        //Проверяем, что сообщение о ненайденной заказе отображается
        Assert.assertTrue(objMainPage.checkOrderNotFound());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}