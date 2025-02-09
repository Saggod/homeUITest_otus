package pages;

import annotations.PathTemplate;
import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import scoped.GuiceScoped;


@PathTemplate("/lessons/$1")
public class CategoryCardPage extends AbsBasePage<CategoryCardPage> {

    @Inject
    public CategoryCardPage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    @Inject
    private CategoryCardPage categoryCardPage;

}
