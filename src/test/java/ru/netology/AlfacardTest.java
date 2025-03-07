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

//    @Test
//    void successfulFormV1() {
//
//        WebElement form = driver.findElement(By.cssSelector("[data-test-id]"));
//       form.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Беликин Егор");
//
//        form.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79617737745");
//        form.findElement(By.cssSelector("[data-test-id='agreement']")).click();
//        form.findElement(By.className("button")).click();
//        WebElement actual = driver.findElement(By.cssSelector("[data-test-id='order-success']"));
//        assertTrue(actual.isDisplayed());
//        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actual.getText().trim());
//    }

    @Test
    void successfulFormV2() {

        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("Беликин Егор");
        elements.get(1).sendKeys("+79271234567");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        WebElement actual = driver.findElement(By.cssSelector("[data-test-id='order-success']"));
        assertTrue(actual.isDisplayed());
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actual.getText().trim());
    }

    @Test
    void invalidFieldNameLat() {

        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("Belikin Egor");
        elements.get(1).sendKeys("+79271234567");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        WebElement actual = driver.findElement(By.cssSelector("[data-test-id='name'] .input__sub"));
        assertTrue(actual.isDisplayed());
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actual.getText().trim());
    }

    @Test
    void invalidFieldNameNum() {

        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("123");
        elements.get(1).sendKeys("+79271234567");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        WebElement actual = driver.findElement(By.cssSelector("[data-test-id='name'] .input__sub"));
        assertTrue(actual.isDisplayed());
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actual.getText().trim());
    }

    @Test
    void invalidFieldNameSumb() {

        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("#$");
        elements.get(1).sendKeys("+79271234567");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        WebElement actual = driver.findElement(By.cssSelector("[data-test-id='name'] .input__sub"));
        assertTrue(actual.isDisplayed());
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actual.getText().trim());
    }

    @Test
    void invalidFieldNameSpace() {

        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("Беликин_Егор");
        elements.get(1).sendKeys("+79271234567");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        WebElement actual = driver.findElement(By.cssSelector("[data-test-id='name'] .input__sub"));
        assertTrue(actual.isDisplayed());
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actual.getText().trim());
    }

    @Test
    void invalidFieldNameEmpty() {

        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("");
        elements.get(1).sendKeys("+79271234567");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        WebElement actual = driver.findElement(By.cssSelector("[data-test-id='name'] .input__sub"));
        assertTrue(actual.isDisplayed());
        assertEquals("Поле обязательно для заполнения", actual.getText().trim());
    }

    @Test
    void invalidFieldPhoneEmpty() {

        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("Беликин Егор");
        elements.get(1).sendKeys("");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        WebElement actual = driver.findElement(By.cssSelector("[data-test-id='phone'] .input__sub"));
        assertTrue(actual.isDisplayed());
        assertEquals("Поле обязательно для заполнения", actual.getText().trim());
    }

    @Test
    void invalidFieldPhoneLat() {

        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("Беликин Егор");
        elements.get(1).sendKeys("Belikin Egor");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        WebElement actual = driver.findElement(By.cssSelector("[data-test-id='phone'] .input__sub"));
        assertTrue(actual.isDisplayed());
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actual.getText().trim());
    }

    @Test
    void invalidFieldPhoneKir() {

        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("Беликин Егор");
        elements.get(1).sendKeys("Беликин Егор");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        WebElement actual = driver.findElement(By.cssSelector("[data-test-id='phone'] .input__sub"));
        assertTrue(actual.isDisplayed());
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actual.getText().trim());
    }

    @Test
    void invalidFieldPhoneSumb() {

        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("Беликин Егор");
        elements.get(1).sendKeys("+7(967)123-45-54");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        WebElement actual = driver.findElement(By.cssSelector("[data-test-id='phone'] .input__sub"));
        assertTrue(actual.isDisplayed());
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actual.getText().trim());
    }

    @Test
    void invalidFieldPhoneWithoutPlus() {

        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("Беликин Егор");
        elements.get(1).sendKeys("89121234567");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        WebElement actual = driver.findElement(By.cssSelector("[data-test-id='phone'] .input__sub"));
        assertTrue(actual.isDisplayed());
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actual.getText().trim());
    }

    @Test
    void invalidFieldPhone10() {

        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("Беликин Егор");
        elements.get(1).sendKeys("+7961123456");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        WebElement actual = driver.findElement(By.cssSelector("[data-test-id='phone'] .input__sub"));
        assertTrue(actual.isDisplayed());
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actual.getText().trim());
    }

    @Test
    void invalidFieldPhone12() {

        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("Беликин Егор");
        elements.get(1).sendKeys("+796112345678");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        WebElement actual = driver.findElement(By.cssSelector("[data-test-id='phone'] .input__sub"));
        assertTrue(actual.isDisplayed());
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actual.getText().trim());
    }

    @Test
    void invalidFieldPhoneSpace() {

        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("Беликин Егор");
        elements.get(1).sendKeys("+7 961 123 45 67");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        WebElement actual = driver.findElement(By.cssSelector("[data-test-id='phone'] .input__sub"));
        assertTrue(actual.isDisplayed());
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actual.getText().trim());
    }



}
