package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CategoryCardPage;
import pages.CategoryPage;

public class GuicePageModules extends AbstractModule {

    private final WebDriver driver = new WebDriverFactory().create();


    @Provides
    private WebDriver getDriver() {
        return driver;
    }

    @Singleton
    @Provides
    public CategoryPage getCategoryPage(){
        return new CategoryPage(driver);
    }

    @Singleton
    @Provides
    public CategoryCardPage getCategoryCardPage(){
        return new CategoryCardPage(driver);
    }
}
