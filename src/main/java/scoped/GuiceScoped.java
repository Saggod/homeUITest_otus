package scoped;

import factory.WebDriverFactory;
import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;

import java.util.Locale;

@ScenarioScoped
public class GuiceScoped {
    public WebDriver driver = null;

    public void browser(String browserName) {
        System.setProperty("browser", browserName);
        this.driver = new WebDriverFactory().create();
    }
}