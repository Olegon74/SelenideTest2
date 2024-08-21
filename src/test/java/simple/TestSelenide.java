package simple;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class TestSelenide {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        Configuration.timeout = 8000; // Увеличение таймаута
        open("https://qa-mesto.praktikum-services.ru/");
    }


    @Test
    public void openPage() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Кликаем по кнопке Регистрация", () -> {
            $(byXpath(".//a[@class='header__auth-link']")).shouldBe(enabled).click();
        });
        step("вводим в поле email", () -> {
            $(byId("email")).setValue("user@example.com");
        });
        step("Вводим в поле password", () -> {
            $(byId("password")).setValue("1qaz2WSX");
        });
        step("Нажимаем на кнопку Зарегистрироваться", () -> {
            $(byClassName("auth-form__button")).click();
        });

    }

    @AfterEach
    void tearDown() {
        closeWebDriver(); // Закрываем браузер после каждого теста
    }

}


