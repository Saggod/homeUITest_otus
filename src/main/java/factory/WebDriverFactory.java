package factory;

import exceptions.BrowserNotFoundException;
import factory.settings.ChromeSettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

    private String browserName = System.getProperty("browser");

    public WebDriver create() {
        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = (ChromeOptions) new ChromeSettings().settings();
               return new ChromeDriver(chromeOptions);
            default:
                throw new BrowserNotFoundException(browserName);
        }

    }
}
