package Lesson_16;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReplenishmentTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://mts.by/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    //WebDriver driver;

    @Test
    public void testCheckPlaceholders() {

        driver.get("https://mts.by/");

        ReplenishmentPage replenishmentPage = new ReplenishmentPage(driver);

        // Проверка placeholder'ов всех форм оплаты
        replenishmentPage.checkPlaceholders();

        // Вывод сообщения в случае успешного прохождения теста
        System.out.println("Тест testCheckPlaceholders успешно пройден");

    }

    @Test
    public void testServiceReplenishment() {

        driver.get("https://mts.by/");

        ReplenishmentPage replenishmentPage = new ReplenishmentPage(driver);

        // Заполнение полей для услуги "Услуги связи"
        replenishmentPage.fillServiceFields("297777777", "100.00", "test@mail.com");

        // Нажимаем кнопку "Продолжить"
        replenishmentPage.clickContinueButton();

        // Проверяем данные в попапе
        replenishmentPage.checkPopupInfo("297777777", "100.00");

        // Вывод сообщения в случае успешного прохождения теста
        System.out.println("Тест testServiceReplenishment успешно пройден");
    }

}
