package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import pages.CategoryCardPage;
import pages.CategoryCoursesPage;
import pages.MainPage;

import java.net.MalformedURLException;

public class GuicePageModules extends AbstractModule {

    private final WebDriver driver = new WebDriverFactory().create();

    public GuicePageModules() throws MalformedURLException {
    }

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


}
