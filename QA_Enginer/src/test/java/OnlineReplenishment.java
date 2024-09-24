import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OnlineReplenishment {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {

        driver = new ChromeDriver();
        driver.get("https://mts.by/");
    }
//Закрываем все окна
    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testBlockTitle() {
        WebElement blockTitle = driver.findElement(By.xpath(
                "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/h2"));
        assertEquals("Онлайн пополнение\nбез комиссии", blockTitle.getText());
        System.out.println("Тест пройден успешно");
    }

    @Test
    public void testPaymentLogosPresence() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement paymentLogo1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[1]/img")));
        WebElement paymentLogo2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[2]/img")));
        WebElement paymentLogo3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[3]/img")));
        WebElement paymentLogo4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[4]/img")));
        WebElement paymentLogo5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[5]/img")));

        assertTrue(paymentLogo1.isDisplayed());
        assertTrue(paymentLogo2.isDisplayed());
        assertTrue(paymentLogo3.isDisplayed());
        assertTrue(paymentLogo4.isDisplayed());
        assertTrue(paymentLogo5.isDisplayed());
    }

    @Test
    public void testLinkMoreInfo() {
        WebElement moreInfoLink = driver.findElement(By.linkText("Подробнее о сервисе"));
        moreInfoLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/"));

        assertEquals("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/", driver.getCurrentUrl());
        System.out.println("Тест пройден успешно");
    }

    @Test
    public void testFormAndButtonContinue() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement phoneNumberField = wait.until(ExpectedConditions.elementToBeClickable(By.id("connection-phone")));
        phoneNumberField.sendKeys("297777777");

        WebElement sum = driver.findElement(By.id("connection-sum"));
        sum.sendKeys("100");

        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".button.button__default")));
        continueButton.click();

        System.out.println("Тест пройден успешно");
    }
}
