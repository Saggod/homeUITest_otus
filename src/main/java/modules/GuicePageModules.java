package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import components.static_component.HeaderMenuCompoonent;
import factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import pages.CategoryCardPage;
import pages.CategoryCoursesPage;
import pages.MainPage;

public class GuicePageModules extends AbstractModule {

    private final WebDriver driver = new WebDriverFactory().create();


    @Singleton
    @Provides
    public MainPage getMainPage() {
        return new MainPage(driver);
    }

    @Provides
    private WebDriver getDriver() {
        return driver;
    }

    @Singleton
    @Provides
    public CategoryCoursesPage getCategoryPage(){
        return new CategoryCoursesPage(driver);
    }

    @Singleton
    @Provides
    public CategoryCardPage getCategoryCardPage(){
        return new CategoryCardPage(driver);
    }

    @Singleton
    @Provides
    public HeaderMenuCompoonent getHeaderMenuCompoonent(){
        return new HeaderMenuCompoonent(driver);
    }

}
