import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageElements.MainPageElements;

import java.time.Duration;

public class LogoLeadsToScooterMainTest {

    private WebDriver driver;
    String expectedUrl = "https://qa-scooter.praktikum-services.ru/";

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    public void shouldLeadToScooterMainPage() {

        MainPageElements objMainPage = new MainPageElements(driver);

        //Принимаем куки
        objMainPage.clickAcceptCookieButton();
        //Уходим со страницы в форму заказа
        objMainPage.clickOrderButtonHeader();
        //Кликаем на лого Самоката
        objMainPage.clickScooterLogo();
        //Получаем текущий URL
        String actualUrl = driver.getCurrentUrl();

        //Сравниваем с ожидаемым
        Assert.assertEquals("Не произошел переход на новую страницу!", expectedUrl, actualUrl);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}