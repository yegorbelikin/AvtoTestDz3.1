package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlfacardTest {
    private WebDriver driver;

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }
        @BeforeEach
                void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }


    @Test
    void successfulForm() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Петров-Иванов Иван");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79021234567");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        WebElement actual = driver.findElement(By.cssSelector("[data-test-id='order-success']"));
        assertTrue(actual.isDisplayed());
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actual.getText().trim());
    }

    @Test
    void invalidFieldNameLat() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Belikin Egor");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79271234567");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        WebElement actual = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub"));
        assertTrue(actual.isDisplayed());
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actual.getText().trim());
    }

    @Test
    void invalidFieldNameNum() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("123");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79271234567");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        WebElement actual = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub"));
        assertTrue(actual.isDisplayed());
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actual.getText().trim());
    }

    @Test
    void invalidFieldNameSumb() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("#$");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79271234567");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        WebElement actual = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub"));
        assertTrue(actual.isDisplayed());
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actual.getText().trim());
    }

    @Test
    void invalidFieldNameSpace() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Беликин_Егор");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79271234567");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        WebElement actual = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub"));
        assertTrue(actual.isDisplayed());
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actual.getText().trim());
    }

    @Test
    void invalidFieldNameEmpty() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79271234567");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        WebElement actual = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub"));
        assertTrue(actual.isDisplayed());
        assertEquals("Поле обязательно для заполнения", actual.getText().trim());
    }

    @Test
    void invalidFieldPhoneEmpty() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Беликин Егор");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        WebElement actual = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub"));
        assertTrue(actual.isDisplayed());
        assertEquals("Поле обязательно для заполнения", actual.getText().trim());
    }

    @Test
    void invalidFieldPhoneLat() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Беликин Егор");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("Belikin Egor");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        WebElement actual = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub"));
        assertTrue(actual.isDisplayed());
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actual.getText().trim());
    }

    @Test
    void invalidFieldPhoneKir() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Беликин Егор");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("Беликин Егор");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        WebElement actual = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub"));
        assertTrue(actual.isDisplayed());
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actual.getText().trim());
    }

    @Test
    void invalidFieldPhoneSumb() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Беликин Егор");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+7(967)123-45-54");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        WebElement actual = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub"));
        assertTrue(actual.isDisplayed());
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actual.getText().trim());
    }

    @Test
    void invalidFieldPhoneWithoutPlus() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Беликин Егор");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("89121234567");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        WebElement actual = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub"));
        assertTrue(actual.isDisplayed());
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actual.getText().trim());
    }

    @Test
    void invalidFieldPhone10() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Беликин Егор");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+7961123456");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        WebElement actual = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub"));
        assertTrue(actual.isDisplayed());
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actual.getText().trim());
    }

    @Test
    void invalidFieldPhone12() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Беликин Егор");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+796112345678");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        WebElement actual = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub"));
        assertTrue(actual.isDisplayed());
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actual.getText().trim());
    }

    @Test
    void invalidFieldPhoneSpace() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Беликин Егор");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+7 961 123 45 67");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        WebElement actual = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub"));
        assertTrue(actual.isDisplayed());
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actual.getText().trim());
    }

    @Test
    void invalidCheckbox() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Беликин Егор");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79611234567");
        driver.findElement(By.className("button")).click();
        WebElement actual = driver.findElement(By.cssSelector("[data-test-id='agreement'].input_invalid .checkbox__text"));
        assertTrue(actual.isDisplayed());
        assertEquals("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй", actual.getText().trim());
    }

}
