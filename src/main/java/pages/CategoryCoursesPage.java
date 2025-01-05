package pages;

import annotations.Path;
import com.google.inject.Inject;
import components.static_component.HeaderMenuCompoonent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

@Path("/catalog/courses")
public class CategoryCoursesPage extends AbsBasePage<CategoryCoursesPage> {


    public CategoryCoursesPage(WebDriver driver) {
        super(driver);
    }


    @Inject
    private CategoryCardPage categoryCardPage;

    @FindBy(xpath = "//main//section//a[contains(@href, '/lessons')]")
    private List<WebElement> lessonsCompScienceCatalogTab;

    @FindBy(xpath = "//a[contains(@href, '/categories')]")
    private List<WebElement> educationCatalogHeaderTab;



    public String getCategoryItem(int index) {
        return lessonsCompScienceCatalogTab.get(--index)
                .findElement(By.xpath(".//h6"))
                .getText();
    }

    public void clickCategoryTitle(String title) {
        String categoryCardLocatorTemplate = String.format(".//a/h6//*[text()='%s']", title);
        $(By.tagName("body")).sendKeys(Keys.HOME);
        $(By.xpath(categoryCardLocatorTemplate)).click();
    }

    public void clickEducationCourseOnHeader(String title) {
        String categoryCardLocatorTemplate = String.format("//a[contains(@href, '/categories')][text()='%s']", title);
        $(By.xpath(categoryCardLocatorTemplate)).click();
    }

    public String getEducationItem(int index) {
        String a = educationCatalogHeaderTab.get(--index)
                .findElement(By.xpath("child::label"))
                .getText();
        System.out.println(a);
        return a;
    }

    //a[contains(@href, '/categories')][text()='Тестирование']

}
