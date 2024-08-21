package config;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.function.Supplier;

import static com.codeborne.selenide.Selenide.open;

/*public class WebDriverProvider implements Supplier<WebDriver> {
    @Override
    public WebDriver get() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        Configuration.timeout = 8000; // Увеличение таймаута
        open("https://qa-mesto.praktikum-services.ru/");
        return driver;
    }


}*/
