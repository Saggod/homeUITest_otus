package scoped;

import factory.WebDriverFactory;
import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;

@ScenarioScoped
public class GuiceScoped {

    private WebDriver webDriver = createWebDriver();

    public WebDriver getWebDriver() {
        return webDriver;
    }

    private WebDriver createWebDriver() {
        WebDriver driver = new WebDriverFactory().create();
        driver.manage().window().maximize();
        return driver;
    }
}