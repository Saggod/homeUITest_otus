package cucumber.hooks;

import com.google.inject.Inject;
import io.cucumber.java.After;
import io.cucumber.java.ru.Пусть;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import scoped.GuiceScoped;

public class Hooks {

    private static final String LESSONS_URL = "/catalog/courses";
    @Inject
    private MainPage mainPage;

    @Пусть("Открываем страницу: {string}")
    public void open(String name) {
        switch (name) {
            case "главная":
                mainPage.open();
                break;
            case "список курсов":
                mainPage.open(LESSONS_URL);
                break;
        }
    }
}
