package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import pages.CategoryCardPage;
import pages.CategoryCoursesPage;
import pages.MainPage;
import scoped.GuiceScoped;

public class GuicePageModules extends AbstractModule {

//    private final WebDriver driver = new WebDriverFactory().create();

    @Inject
    private GuiceScoped guiceScoped;

    @Singleton
    @Provides
    public MainPage getMainPage() {
        return new MainPage(guiceScoped);
    }

    @Provides
    private GuiceScoped getDriver() {
        return guiceScoped;
    }

    @Singleton
    @Provides
    public CategoryCoursesPage getCategoryPage(){
        return new CategoryCoursesPage(guiceScoped);
    }

    @Singleton
    @Provides
    public CategoryCardPage getCategoryCardPage(){
        return new CategoryCardPage(guiceScoped);
    }


}
