package cucumber.steps.common;

import com.google.inject.Inject;
import io.cucumber.java.ru.Пусть;
import pages.MainPage;

public class BaseSteps {

    private static final String LESSONS_URL = "/catalog/courses";

    @Inject
    public MainPage mainPage;

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
