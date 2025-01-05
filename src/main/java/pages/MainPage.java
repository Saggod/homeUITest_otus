package pages;

import annotations.Path;
import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;

@Path("/")
public class MainPage extends AbsBasePage<MainPage>{

    public MainPage(WebDriver driver) {
        super(driver);
    }

}
