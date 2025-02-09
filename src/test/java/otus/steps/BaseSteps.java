package otus.steps;

import com.google.inject.Inject;
import io.cucumber.java.ru.Пусть;
import scoped.GuiceScoped;

public class BaseSteps {

    @Inject
    private GuiceScoped guiceScoped;

    @Пусть("Открываю браузер {string}")
    public void openBrowser(String browser) {
        guiceScoped.browser(browser);
    }
}
