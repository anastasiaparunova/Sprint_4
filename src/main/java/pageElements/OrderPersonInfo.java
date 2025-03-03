package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class OrderPersonInfo { // Страница «Для кого самокат»

    private final WebDriver driver;
    public OrderPersonInfo(WebDriver driver) {
        this.driver = driver;
    }

    // Поле «Имя»
    private final By personName = By.xpath(".//input[contains(@class, 'Input_Input__1iN_Z') and contains(@placeholder, 'Имя')]");
    // Поле «Фамилия»
    private final By personSurname = By.xpath(".//input[contains(@class, 'Input_Input__1iN_Z') and contains(@placeholder, 'Фамилия')]");
    // Поле  «Адрес»
    private final By deliveryAddress = By.xpath(".//input[contains(@class, 'Input_Input__1iN_Z') and contains(@placeholder, 'Адрес')]");
    // Поле с выпадающим списоком «Станция метро»
    private final By deliveryMetro = By.className("select-search__input");
    // Поле «Телефон»
    private final By phoneNumber = By.xpath(".//input[contains(@class, 'Input_Input__1iN_Z') and contains(@placeholder, 'Телефон')]");
    // Кнопка «Далее»
    private final By buttonNext = By.xpath(".//button[contains(@class, 'Button_Middle__1CSJM')]");

    // Ошибки полей ввода
    private static final By personNameError = By.xpath(".//input[contains(@class, 'Input_Input__1iN_Z') and contains(@placeholder, 'Имя')]/following-sibling::div[contains(@class,'Error')]");
    private static final By personSurnameError = By.xpath(".//input[contains(@class, 'Input_Input__1iN_Z') and contains(@placeholder, 'Фамилия')]/following-sibling::div[contains(@class,'Error')]");
    private static final By deliveryAddressError = By.xpath(".//input[contains(@class, 'Input_Input__1iN_Z') and contains(@placeholder, 'Адрес')]/following-sibling::div[contains(@class,'Error')]");
    private static final By deliveryMetroError = By.xpath(".//div[@class='select-search']//following-sibling::div[contains(@class,'Error')]");
    private static final By phoneNumberError = By.xpath(".//input[contains(@class, 'Input_Input__1iN_Z') and contains(@placeholder, 'Телефон')]/following-sibling::div[contains(@class,'Error')]");


    //Заполняем поле «Имя»
    public void inputName(String name) {
        driver.findElement(personName).sendKeys(name);
    }

    //Заполняем поле «Фамилия»
    public void inputSurname(String surname) {
        driver.findElement(personSurname).sendKeys(surname);
    }

    //Заполняем поле «Адрес»
    public void inputAddress(String address) {
        driver.findElement(deliveryAddress).sendKeys(address);
    }

    //Кликаем по полю  «Станция метро»
    public void clickMetro() {
        driver.findElement(deliveryMetro).click();
    }

    //Кликаем на нужную станцию метро в открывшемся списке.
    public void clickMetroStation(String metro) {
        List<WebElement> stations = driver.findElements(By.xpath(".//div[contains(@class, 'Order_Text__2broi')]"));

        for (WebElement station : stations) {
            if (station.getText().equals(metro)) {
                station.click();
                break;
            }
        }
    }

    //Заполняем поле «Телефон»
    public void inputPhone(String phone) {
        driver.findElement(phoneNumber).sendKeys(phone);
    }

    //Кликаем на кнопку «Далее»
    public void clickButtonNext() {
        driver.findElement(buttonNext).click();
    }

    //Заполнение формы «Для кого самокат»
    public void fillOrderForm(String name, String surname, String address, String metro, String phone) {
        inputName(name);
        inputSurname(surname);
        inputAddress(address);
        clickMetro();
        clickMetroStation(metro);
        inputPhone(phone);
    }

    //Получаем текст ошибки поля ввода
    public String getErrorMessage(By errorLocator) {
        return driver.findElement(errorLocator).getText();
    }

    //Геттеры полей ошибок
    public static By getPersonNameError() {
        return personNameError;
    }

    public static By getPersonSurnameError() {
        return personSurnameError;
    }

    public static By getDeliveryAddressError() {
        return deliveryAddressError;
    }

    public static By getDeliveryMetroError() {
        return deliveryMetroError;
    }

    public static By getPhoneNumberError() {
        return phoneNumberError;
    }

    //Метод, проверяющий отображение элемента
    public boolean isElementDisplayed(By element) {
        return !driver.findElements(element).isEmpty() && driver.findElement(element).isDisplayed();
    }
}
