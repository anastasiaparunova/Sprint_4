import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageElements.MainPageElements;

import java.time.Duration;

public class LogoLeadsToYandexMainTest {

    private WebDriver driver;
    String expectedUrl = "https://ya.ru";

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
        //Сохраняем данные о текущей странице
        String originalWindow = driver.getWindowHandle();
        //Кликаем на лого Яндекса
        objMainPage.clickYandexLogo();
        //Проверяем открывшиеся страницы и переключаемся на новую
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        //Получаем URL открытой вкладки
        String newUrl = driver.getCurrentUrl();

        //Сравниваем, что открывшаяся страница начинается с URL ожидаемой страницы
        assert newUrl != null;
        Assert.assertTrue("Открылась не та страница", newUrl.startsWith(expectedUrl));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}