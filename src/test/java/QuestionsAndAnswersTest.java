import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.Test;
import org.junit.After;
import pageElements.MainPageElements;

import java.time.Duration;


@RunWith(Parameterized.class)
public class QuestionsAndAnswersTest {

    private final int index;
    private final String answerText;

    private WebDriver driver;

    public QuestionsAndAnswersTest(int index, String answerText) {
        this.index = index;
        this.answerText = answerText;
    }

    //Параметры для тестирования: id блока с вопросом, id блока с ответом, ожидаемый текст ответа.
    @Parameterized.Parameters
    public static Object[][] testingData() {
        return new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void shouldDisplayCorrectAnswerWhenQuestionIsClicked() {

        MainPageElements objMainPage = new MainPageElements(driver);

        //Кликаем на кнопку куки "все уже привыкли"
        objMainPage.clickAcceptCookieButton();
        //Кликаем на вопрос
        objMainPage.clickQuestion(index);
        //Получаем текст ответа
        String actualAnswer;
        actualAnswer = objMainPage.getAnswerText(index);
        //Сравниваем текст ответа на сайте с ожидаемым
        Assert.assertEquals("Ответ не совпадает с ожидаемым:", answerText, actualAnswer);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
