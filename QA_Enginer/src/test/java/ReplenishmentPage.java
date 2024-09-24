package Lesson_16;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

/*public class ReplenishmentPage {
    WebDriver driver;
    WebDriverWait wait;


    // Локатор кнопки "Оплатить"
    @FindBy(css = "button[type='submit']")
    WebElement payButton;

    @FindBy(className = "pay-description__cost")
    WebElement descriptionCost;

    // Локаторы для выбора типа оплаты
    @FindBy(id = "pay")
    WebElement paymentTypeDropdown;

    @FindBy(id = "pay-connection")
    WebElement payConnectionForm;

    @FindBy(id = "pay-internet")
    WebElement payInternetForm;

    @FindBy(id = "pay-instalment")
    WebElement payInstalmentForm;

    @FindBy(id = "pay-arrears")
    WebElement payArrearsForm;

    // Локаторы для полей формы "Услуги связи"
    @FindBy(id = "connection-phone")
    WebElement connectionPhoneInput;

    @FindBy(id = "connection-sum")
    WebElement connectionSumInput;

    @FindBy(id = "connection-email")
    WebElement connectionEmailInput;

    @FindBy(css = "button.button__default")
    WebElement continueButton;

    // Локаторы для поля подтверждения
    @FindBy(id = "total-amount")
    WebElement totalAmount;

    @FindBy(id = "popup-phone-number")
    WebElement popupPhoneNumber;

    @FindBy(xpath = "//div[@class='payment-icons']")
    WebElement paymentIcons;

    // Локаторы для полей формы "Домашний интернет"
    @FindBy(id = "internet-phone")
    WebElement internetPhoneInput;

    @FindBy(id = "internet-sum")
    WebElement internetSumInput;

    @FindBy(id = "internet-email")
    WebElement internetEmailInput;

    // Локаторы для полей формы "Рассрочка"
    @FindBy(id = "score-instalment")
    WebElement scoreInstalmentInput;

    @FindBy(id = "instalment-sum")
    WebElement instalmentSumInput;

    @FindBy(id = "instalment-email")
    WebElement instalmentEmailInput;

    // Локаторы для полей формы "Задолженность"
    @FindBy(id = "score-arrears")
    WebElement scoreArrearsInput;

    @FindBy(id = "arrears-sum")
    WebElement arrearsSumInput;

    @FindBy(id = "arrears-email")
    WebElement arrearsEmailInput;


    // Конструктор Page Object
    public ReplenishmentPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void checkPlaceholders() {
        // Услуги связи
        assert connectionPhoneInput.getAttribute("placeholder").equals("Номер телефона");
        assert connectionSumInput.getAttribute("placeholder").equals("Сумма");
        assert connectionEmailInput.getAttribute("placeholder").equals("E-mail для отправки чека");

        // Домашний интернет
        assert internetPhoneInput.getAttribute("placeholder").equals("Номер абонента");
        assert internetSumInput.getAttribute("placeholder").equals("Сумма");
        assert internetEmailInput.getAttribute("placeholder").equals("E-mail для отправки чека");

        // Рассрочка
        assert scoreInstalmentInput.getAttribute("placeholder").equals("Номер счета на 44");
        assert instalmentSumInput.getAttribute("placeholder").equals("Сумма");
        assert instalmentEmailInput.getAttribute("placeholder").equals("E-mail для отправки чека");

        // Задолженность
        assert scoreArrearsInput.getAttribute("placeholder").equals("Номер счета на 2073");
        assert arrearsSumInput.getAttribute("placeholder").equals("Сумма");
        assert arrearsEmailInput.getAttribute("placeholder").equals("E-mail для отправки чека");
    }


    // Метод для проверки отображаемой суммы и номера телефона
    public void checkPopupInfo(String expectedPhoneNumber, String expectedAmount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement iframe = driver.findElement(By.xpath("//iframe[@class='bepaid-iframe']"));
        driver.switchTo().frame(iframe);
        // Поиск iframe для переключения контекста
        //WebElement iframe = driver.findElement(By.cssSelector("div.bepaid-app > div > iframe"));
        driver.switchTo().frame(iframe);

        // Ожидание и поиск элемента, отображающего сумму оплаты
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "/html/body/app-root/div/div/div/app-payment-container/section/div/div/div/span[1]")));
        WebElement totalAmount = driver.findElement(By.xpath(
                "/html/body/app-root/div/div/div/app-payment-container/section/div/div/div/span[1]"));
        // Проверка, содержит ли отображаемая сумма ожидаемую сумму
        assert totalAmount.getText().contains(expectedAmount) : "Неверная сумма оплаты";

        // Поиск элемента с номером телефона
        WebElement popupPhoneNumber = driver.findElement(By.xpath(
                "/html/body/app-root/div/div/div/app-payment-container/section/div/div/div[2]/span"));
        // Проверка, содержит ли отображаемый номер телефона ожидаемый номер
        assert popupPhoneNumber.getText().contains(expectedPhoneNumber) : "Неверный номер телефона";

        // Поиск иконок платежных систем
        WebElement paymentIcons = driver.findElement(By.id("payment-icons"));
        // Проверка, отображаются ли иконки платежных систем
        assert paymentIcons.isDisplayed() : "Иконки платежных систем не отображаются";


        // Возврат к основному контексту страницы
        driver.switchTo().defaultContent();
    }


    // Выбор типа оплаты
    public void selectPaymentType(String paymentType) {
        paymentTypeDropdown.sendKeys(paymentType);
    }

    // Заполнение полей для услуги "Услуги связи"
    public void fillServiceFields(String phoneNumber, String sum, String email) {
        connectionPhoneInput.sendKeys(phoneNumber);
        connectionSumInput.sendKeys(sum);
        connectionEmailInput.sendKeys(email);
    }


    public void clickContinueButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.button__default")));

        // Прокрутка к элементу
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", continueButton);

        // Клик через JavaScript, если обычный клик не работает
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueButton);
    }

}

 */


public class ReplenishmentPage {
    WebDriver driver;
    WebDriverWait wait;

    // Локатор кнопки "Продолжить"
    @FindBy(xpath = "//*[@id=\"pay-connection\"]/button")
    WebElement continueButton;

    @FindBy(className = "pay-description__cost")
    WebElement descriptionCost;

    // Локаторы для выбора типа оплаты
    @FindBy(id = "pay")
    WebElement paymentTypeDropdown;

    @FindBy(id = "pay-connection")
    WebElement payConnectionForm;

    @FindBy(id = "pay-internet")
    WebElement payInternetForm;

    @FindBy(id = "pay-instalment")
    WebElement payInstalmentForm;

    @FindBy(id = "pay-arrears")
    WebElement payArrearsForm;

    // Локаторы для полей формы "Услуги связи"
    @FindBy(id = "connection-phone")
    WebElement connectionPhoneInput;

    @FindBy(id = "connection-sum")
    WebElement connectionSumInput;

    @FindBy(id = "connection-email")
    WebElement connectionEmailInput;

    //@FindBy(css = "button.button__default")
    //WebElement continueButton;

    // Локаторы для поля подтверждения
    @FindBy(id = "total-amount")
    WebElement totalAmount;

    @FindBy(id = "popup-phone-number")
    WebElement popupPhoneNumber;

    @FindBy(xpath = "//div[@class='payment-icons']")
    WebElement paymentIcons;

    // Локаторы для полей формы "Домашний интернет"
    @FindBy(id = "internet-phone")
    WebElement internetPhoneInput;

    @FindBy(id = "internet-sum")
    WebElement internetSumInput;

    @FindBy(id = "internet-email")
    WebElement internetEmailInput;

    // Локаторы для полей формы "Рассрочка"
    @FindBy(id = "score-instalment")
    WebElement scoreInstalmentInput;

    @FindBy(id = "instalment-sum")
    WebElement instalmentSumInput;

    @FindBy(id = "instalment-email")
    WebElement instalmentEmailInput;

    // Локаторы для полей формы "Задолженность"
    @FindBy(id = "score-arrears")
    WebElement scoreArrearsInput;

    @FindBy(id = "arrears-sum")
    WebElement arrearsSumInput;

    @FindBy(id = "arrears-email")
    WebElement arrearsEmailInput;


    // Конструктор Page Object
    public ReplenishmentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Перенос в конструктор
        PageFactory.initElements(driver, this);
    }

    public void checkPlaceholders() {
        // Услуги связи
        assert connectionPhoneInput.getAttribute("placeholder").equals("Номер телефона") : "Неверный placeholder для 'Номер телефона'";
        assert connectionSumInput.getAttribute("placeholder").equals("Сумма") : "Неверный placeholder для 'Сумма'";
        assert connectionEmailInput.getAttribute("placeholder").equals("E-mail для отправки чека") : "Неверный placeholder для 'E-mail для отправки чека'";

        // Домашний интернет
        assert internetPhoneInput.getAttribute("placeholder").equals("Номер абонента") : "Неверный placeholder для 'Номер абонента'";
        assert internetSumInput.getAttribute("placeholder").equals("Сумма") : "Неверный placeholder для 'Сумма'";
        assert internetEmailInput.getAttribute("placeholder").equals("E-mail для отправки чека") : "Неверный placeholder для 'E-mail для отправки чека'";

        // Рассрочка
        assert scoreInstalmentInput.getAttribute("placeholder").equals("Номер счета на 44") : "Неверный placeholder для 'Номер счета на 44'";
        assert instalmentSumInput.getAttribute("placeholder").equals("Сумма") : "Неверный placeholder для 'Сумма'";
        assert instalmentEmailInput.getAttribute("placeholder").equals("E-mail для отправки чека") : "Неверный placeholder для 'E-mail для отправки чека'";

        // Задолженность
        assert scoreArrearsInput.getAttribute("placeholder").equals("Номер счета на 2073") : "Неверный placeholder для 'Номер счета на 2073'";
        assert arrearsSumInput.getAttribute("placeholder").equals("Сумма") : "Неверный placeholder для 'Сумма'";
        assert arrearsEmailInput.getAttribute("placeholder").equals("E-mail для отправки чека") : "Неверный placeholder для 'E-mail для отправки чека'";
    }

    // Метод для проверки отображаемой суммы и номера телефона
    public void checkPopupInfo(String expectedPhoneNumber, String expectedAmount) {
        // Ожидание появления iframe
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class='bepaid-iframe']")));
        driver.switchTo().frame(iframe);  // Переключение в iframe

        // Ожидание и проверка суммы
        WebElement totalAmount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "/html/body/app-root/div/div/div/app-payment-container/section/div/div/div/span[1]")));
        assert totalAmount.getText().contains(expectedAmount) : "Неверная сумма оплаты";

        // Ожидание и проверка номера телефона
        WebElement popupPhoneNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "/html/body/app-root/div/div/div/app-payment-container/section/div/div/div[2]/span")));
        assert popupPhoneNumber.getText().contains(expectedPhoneNumber) : "Неверный номер телефона";

        // Проверка иконок платежных систем
        WebElement paymentIcons = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "/html/body/app-root/div/div/div/app-payment-container/section/div/app-card-page/div/div[1]/app-card-input/form/div[1]/div[1]/app-input/div/div/div[2]")));
        assert paymentIcons.isDisplayed() : "Иконки платежных систем не отображаются";

        // Возврат к основному контексту страницы
        driver.switchTo().defaultContent();
    }


    // Заполнение полей для услуги "Услуги связи"
    public void fillServiceFields(String phoneNumber, String sum, String email) {
        connectionPhoneInput.sendKeys(phoneNumber);
        connectionSumInput.sendKeys(sum);
        connectionEmailInput.sendKeys(email);
    }

    // Клик на кнопку продолжения
    public void clickContinueButton() {
        // Ожидание, пока кнопка станет кликабельной
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(continueButton));

        // Прокрутка к элементу, если это необходимо
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);

        // Клик через JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }
}

