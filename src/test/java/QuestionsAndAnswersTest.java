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

    private final String questionId;
    private final String answerPanel;
    private final String answerText;

    private WebDriver driver;

    public QuestionsAndAnswersTest(String questionId, String answerPanel, String answerText) {
        this.questionId = questionId;
        this.answerPanel = answerPanel;
        this.answerText = answerText;
    }

    //Параметры для тестирования: id блока с вопросом, id блока с ответом, ожидаемый текст ответа.
    @Parameterized.Parameters
    public static Object[][] testingData() {
        return new Object[][]{
                {"accordion__heading-0", "accordion__panel-0", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"accordion__heading-3", "accordion__panel-3", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"accordion__heading-7", "accordion__panel-7", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
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
    public void test() {

        MainPageElements objMainPage = new MainPageElements(driver);

        //Кликаем на кнопку куки "все уже привыкли"
        objMainPage.clickAcceptCookieButton();
        //Кликаем на вопрос
        objMainPage.clickQuestion(questionId);
        //Получаем текст ответа
        String actualAnswer;
        actualAnswer = objMainPage.getAnswerText(answerPanel);
        //Сравниваем текст ответа на сайте с ожидаемым
        Assert.assertEquals("Ответ не совпадает с ожидаемым:", answerText, actualAnswer);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
