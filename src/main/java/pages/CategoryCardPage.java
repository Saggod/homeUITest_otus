package pages;

import annotations.PathTemplate;
import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;


@PathTemplate("/lessons/$1")
public class CategoryCardPage extends AbsBasePage<CategoryCardPage> {

    public CategoryCardPage(WebDriver driver) {
        super(driver);
    }

    @Inject
    private CategoryCardPage categoryCardPage;

}
