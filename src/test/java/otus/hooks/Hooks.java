package otus.hooks;

import com.google.inject.Inject;
import io.cucumber.java.After;
import io.cucumber.java.ru.Пусть;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import scoped.GuiceScoped;

public class Hooks {

    @Inject
    private GuiceScoped guiceScoped;

    @After
    public void closeDriver() {
        if (guiceScoped.driver != null) {
            guiceScoped.driver.close();
            guiceScoped.driver.quit();
        }
    }
}
