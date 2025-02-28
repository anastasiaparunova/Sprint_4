package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class MainPageElements {

    private final WebDriver driver;
    public MainPageElements(WebDriver driver) {
        this.driver = driver;
    }

    //Кнопка Принять куки
    private final By acceptCookieButton = By.className("App_CookieButton__3cvqF");

    // Кнопка «Заказать» в хедере
    private final By orderButtonHeader = By.className("Button_Button__ra12g");

    // Кнопка «Заказать» в середине страницы
    private final By orderButtonMiddle = By.xpath(".//button[contains(@class,'Button_Middle__1CSJM') and text()='Заказать']");

    // Кнопка «Статус заказа»
    private final By orderStatusButton = By.className("Header_Link__1TAG7");

    // Поле для ввода номера заказа
    private final By inputOrderNumber = By.xpath(".//input[contains(@class,'Input_Input__1iN_Z')]");

    // Кнопка «Go!»
    private final By goButton = By.xpath(".//button[text()='Go!']");

    // Изображение с сообщением «Такого заказа нет»
    private final By orderNotFoundMessage = By.xpath(".//img[@alt='Not found']");

    // Лого «Яндекс»
    private final By yandexLogo = By.xpath(".//img[@alt='Yandex']");

    // Лого «Самокат»
    private final By scooterLogo = By.xpath(".//img[@alt='Scooter']");


    // Клик на кнопку принятия куки
    public void clickAcceptCookieButton() {
        driver.findElement(acceptCookieButton).click();
    }

    // Клик на элемент с вопросом
    public void clickQuestion(String questionId) {
        driver.findElement(By.id(questionId)).click();
    }

    // Получаем текст ответа на нажатый вопрос
    public String getAnswerText(String answerPanel) {
        return driver.findElement(By.xpath(".//div[@id='" + answerPanel + "']/p")).getText();
    }

    // Клик на кнопку «Заказать» в хедере
    public void clickOrderButtonHeader() {
        driver.findElement(orderButtonHeader).click();
    }

    // Клик на кнопку «Заказать» в середине страницы
    public void clickOrderButtonMiddle() {
        driver.findElement(orderButtonMiddle).click();
    }

    // Клик на кнопку «Статус заказа» в середине страницы
    public void clickOrderStatusButton() {
        driver.findElement(orderStatusButton).click();
    }

    //Заполняем поле «Номер заказа»
    public void fillOrderNumber(String orderNumber) {
        driver.findElement(inputOrderNumber).sendKeys(orderNumber);
    }

    // Клик на кнопку «Go!»
    public void clickGoButton() {
        driver.findElement(goButton).click();
    }

    // Проверяем отображение сообщения «Заказ не найден»
    public boolean checkOrderNotFound() {
        return !driver.findElements(orderNotFoundMessage).isEmpty();
    }

    // Клик на лого Самоката
    public void clickScooterLogo() {
        driver.findElement(scooterLogo).click();
    }

    // Клик на лого Самоката
    public void clickYandexLogo() {
        driver.findElement(yandexLogo).click();
    }
}
