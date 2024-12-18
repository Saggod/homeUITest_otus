package pages;

import annotations.Path;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Path("/catalog/courses")
public class CategoryPage extends AbsBasePage<CategoryPage> {


    public CategoryPage(WebDriver driver) {
        super(driver);
    }


    @Inject
    private CategoryCardPage categoryCardPage;

    @FindBy(xpath = "//main//section//a[contains(@href, '/lessons')]")
    private List<WebElement> lessonsCompScienceCatalogTab;


    public String getCategoryItem(int index) {
        return lessonsCompScienceCatalogTab.get(--index)
                .findElement(By.xpath(".//h6"))
                .getText();

    }


    public void clickCategoryTitle(String title) {
        String categoryCardLocatorTemplate = String.format(".//a/h6//*[text()='%s']", title);
        $(By.tagName("body")).sendKeys(Keys.HOME);   //иначе не кликает, при клике идет прокрутка страницы верх.
        $(By.xpath(categoryCardLocatorTemplate)).click();
//        String headerCourse = String.format("//main//h1[text()='%s']", title);
//
//        return $(By.xpath(headerCourse)).getText();
    }



}
