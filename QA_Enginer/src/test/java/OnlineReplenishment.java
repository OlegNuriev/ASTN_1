import io.qameta.allure.Owner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OnlineReplenishment {

    private static WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://mts.by/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Принять Cookies
        OnlineReplenishment.clickRejectCookies();

        //acceptCookies();  // Добавляем метод для принятия cookies
        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie);
        }
    }

    // Закрываем все окна
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
    // Метод для принятия cookies
    public static void clickRejectCookies() {
        try {
            WebElement rejectButton = driver.findElement(By.xpath("//button[contains(text(), 'Принять')]"));
            rejectButton.click();
        } catch (Exception e) {
            System.out.println("Окно Cookies не появилось.");
        }
    }
    @Owner("Nuriev O.R.")
    @Test
    public void testBlockTitle() {
        WebElement blockTitle = driver.findElement(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/h2"));
        assertEquals("Онлайн пополнение\nбез комиссии", blockTitle.getText());
        System.out.println("Тест пройден успешно");
    }
    @Owner("Nuriev O.R.")
    @Test
    public void testPaymentLogosPresence() {
        WebElement paymentLogo1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[1]/img")));
        WebElement paymentLogo2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[2]/img")));
        WebElement paymentLogo3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[3]/img")));
        WebElement paymentLogo4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[4]/img")));
        WebElement paymentLogo5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[5]/img")));

        assertTrue(paymentLogo1.isDisplayed());
        assertTrue(paymentLogo2.isDisplayed());
        assertTrue(paymentLogo3.isDisplayed());
        assertTrue(paymentLogo4.isDisplayed());
        assertTrue(paymentLogo5.isDisplayed());
        System.out.println("Тест пройден успешно");
    }
    @Owner("Nuriev O.R.")
    @Test
    public void testLinkMoreInfo() {
        // Get All available cookies
        //Set<Cookie> cookies = driver.manage().getCookies();

        WebElement moreInfoLink = driver.findElement(By.linkText("Подробнее о сервисе"));
        moreInfoLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/"));

        assertEquals("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/", driver.getCurrentUrl());
        System.out.println("Тест пройден успешно");
    }
    @Owner("Nuriev O.R.")
    @Test
    public void testFormAndButtonContinue() {

        WebElement phoneNumberField = driver.findElement(By.xpath("//*[@id=\"connection-phone\"]"));

        // Прокрутка к элементу, если он может быть вне зоны видимости
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", phoneNumberField);

        // Ввод номера телефона
        phoneNumberField.sendKeys("297777777");

        WebElement sum = driver.findElement(By.id("connection-sum"));
        sum.sendKeys("100");

        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//*[@id=\"pay-connection\"]/button")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueButton);
        //continueButton.click();

        System.out.println("Тест пройден успешно");

    }
}
