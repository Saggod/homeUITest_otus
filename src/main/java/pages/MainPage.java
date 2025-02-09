package pages;

import annotations.Path;
import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import scoped.GuiceScoped;

@Path("/")
public class MainPage extends AbsBasePage<MainPage>{

    @Inject
    public MainPage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

}
