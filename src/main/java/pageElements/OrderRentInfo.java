package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;


public class OrderRentInfo { // Страница «Про аренду»

    private final WebDriver driver;

    public OrderRentInfo(WebDriver driver) {
        this.driver = driver;
    }

    // Поле «Когда привезти самокат»
    private final By deliveryDate = By.xpath(".//input[contains(@class, 'Input_Responsible__1jDKN') and contains(@placeholder, 'Когда привезти')]");
    // Выпадающий список «Срок аренды»
    private final By rentDuration = By.className("Dropdown-control");
    // Поле «Комментарий для курьера»
    private final By commentForCourier = By.xpath(".//input[contains(@class, 'Input_Input__1iN_Z') and contains(@placeholder, 'Комментарий')]");
    // Кнопка «Заказать»
    private final By buttonToOrder = By.xpath(".//button[contains(@class, 'Button_Middle__1CSJM') and text()= 'Заказать']");
    // Кнопка «Да»
    private final By buttonYes = By.xpath(".//button[contains(@class, 'Button_Button__ra12g') and text() = 'Да']");
    //Заголовок «Заказ оформлен»
    private final By orderHeader = By.className("Order_ModalHeader__3FDaJ");


    //Заполняем поле «Когда привезти самокат»
    public void inputDate(String date) {
        driver.findElement(deliveryDate).sendKeys(date);
        driver.findElement(deliveryDate).sendKeys(Keys.RETURN);
    }

    //Кликаем по полю  «Срок аренды»
    public void clickRentDuration() {
        driver.findElement(rentDuration).click();
    }

    //Кликаем на нужный срок аренды в открывшемся списке
    public void clickRentDurationDays(String duration) {
        List<WebElement> durations = driver.findElements(By.xpath(".//div[contains(@class,'Dropdown-option')]"));
        for (WebElement option : durations) {
            if (option.getText().equals(duration)) {
                option.click();
                break;
            }
        }
    }

    //Кликаем на чекбокс с нужным цветом
    public void selectColour(String colour) {
        List<WebElement> checkboxes = driver.findElements(By.xpath(".//label[contains(@class, 'Checkbox_Label__3wxSf')]"));
        for (WebElement checkbox : checkboxes) {
            if (checkbox.getText().equals(colour)) {
                checkbox.click();
                break;
            }
        }
    }

    //Заполняем поле «Комментарий для курьера»
    public void inputCommentForCourier(String comment) {
        driver.findElement(commentForCourier).sendKeys(comment);
    }

    //Кликаем по кнопке  «Срок аренды»
    public void clickButtonToOrder() {
        driver.findElement(buttonToOrder).click();
    }

    public String getOrderHeaderText() {
        return driver.findElement(orderHeader).getText();
    }

    // Клик на кнопку «Да»
    public void clickButtonYes() {
        driver.findElement(buttonYes).click();
    }

    //Заполнение формы «Про аренду»
    public void fillRentInfo(String date, String duration, String colour, String comment) {
        inputDate(date);
        clickRentDuration();
        clickRentDurationDays(duration);
        selectColour(colour);
        inputCommentForCourier(comment);
    }
}
