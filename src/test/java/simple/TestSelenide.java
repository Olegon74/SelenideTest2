package simple;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
@Tag("simple")
public class TestSelenide {
    private WebDriver driver;

    @BeforeEach
    void beforeAll() {
        baseUrl = "https://qa-mesto.praktikum-services.ru/";
        Configuration.browser = "chrome";
        Configuration.browserVersion = "126";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 8000; // Увеличение таймаута
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        //WebDriverManager.chromedriver().setup();
        //SelenideLogger.addListener("allure", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Configuration.browserCapabilities = capabilities;
        open(baseUrl);
    }


    @Test
    public void openPage() {

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
    @Test
    public void textLogoutTest() {
        String buttonText = $(byXpath(".//button[@class='auth-form__button']")).shouldBe(editable).getText();
        System.out.println("Текст на кнопке: " + buttonText);
        $(byXpath(".//button[@class='auth-form__button']")).shouldHave(exactText("Войти"));

    }
    @Test
    public void countCards() {
        $(byXpath(".//a[@class='header__auth-link']")).shouldBe(enabled).click();
        $(byId("email")).setValue("user@example.com");
        $(byId("password")).setValue("1qaz2WSX");
        $(byClassName("auth-form__button")).click();
        Integer cardsQuantity = $$(byText("Байкал")).size();
        System.out.println(cardsQuantity);

    }
    @Test
    public void testAvatar() {
        $(byId("email")).shouldBe(editable).setValue("user@example.com");
        $(byId("password")).setValue("1qaz2WSX");
        $(byXpath(".//button[@class='auth-form__button']")).click();
        $(byXpath("//section[@class='profile page__section']/div[@class='profile__image']")).click();
        $(byId("owner-avatar")).setValue("https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/avatarSelenide.png");
        $(byXpath(".//form[@name='edit-avatar']/button[text()='Сохранить']")).click();

    }
    @Test
    public void testNewCard() {
        $(byId("email")).shouldBe(editable).setValue("user@example.com");
        $(byId("password")).setValue("1qaz2WSX");
        $(byXpath(".//button[@class='auth-form__button']")).click();
        $(byXpath(".//button[@class='profile__add-button']")).click();
        $(byId("place-name")).setValue("Москва");
        $(byId("place-link")).setValue("https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenide.jpg");
        $(byXpath(".//form[@name='new-card']/button[text()='Сохранить']")).click();
        $(byXpath(".//button[@class='card__delete-button card__delete-button_visible']")).click();

    }
    @Test
    public void testEditingProfile() {
        $(byId("email")).shouldBe(editable).setValue("user@example.com");
        $(byId("password")).setValue("1qaz2WSX");
        $(byXpath(".//button[@class='auth-form__button']")).click();
        $(byClassName("profile__edit-button")).click();
        $(byId("owner-name")).setValue("Тестер");
        $(byId("owner-description")).setValue("Тестов");
        $(byXpath(".//form[@name='edit']/button[text()='Сохранить']")).shouldBe(editable).click();

    }
    @Test
    public void cardTest() {
        $(byId("email")).shouldBe(editable).setValue("user@example.com");
        $(byId("password")).setValue("1qaz2WSX");
        $(byXpath(".//button[@class='auth-form__button']")).click();
        String cardText = $$(byXpath(".//li[@class='places__item card']")).get(1).$(byXpath(".//h2[@class='card__title']")).getText();
        System.out.println("Текст второй карточки: " + cardText);

    }

    @AfterEach
    void tearDown() {
        closeWebDriver(); // Закрываем браузер после каждого теста
    }

}


