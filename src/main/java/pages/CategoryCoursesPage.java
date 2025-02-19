package pages;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import annotations.Path;
import com.google.inject.Inject;
import data.CourseDetails;
import data.Pair;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;



import static org.assertj.core.api.Assertions.fail;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Path("/catalog/courses")
public class CategoryCoursesPage extends AbsBasePage<CategoryCoursesPage> {


    public CategoryCoursesPage(WebDriver driver) {
        super(driver);
    }

    @Inject
    private CategoryCardPage categoryCardPage;

    @FindBy(xpath = "//main//section//a[contains(@href, '/lessons')]")
    private List<WebElement> lessonsCompScienceCatalogTab;

    @FindBy(xpath = "//nav//span[@title='Обучение']")
    private WebElement education;

    @FindBy(xpath = "//section/div[.//p[text()='Направление']]//div[@class='ReactCollapse--content']/div/div")
    private List<WebElement> filtrCategories;

    @FindBy(tagName = "h1")
    private WebElement header;

    @FindBy(xpath = "//div[contains(text(),'Старт занятий')]")
    private WebElement dateStart;

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

    public String selectRandomCategory() {
        moveToElement(education);
        List<WebElement> categoriesList = driver.findElements(By.xpath("//nav//a[contains(@href,'/categories')]"));
        WebElement selectedCategory = categoriesList.get(new Random().nextInt(categoriesList.size()));
        String selectedCategoryName = selectedCategory.getText().substring(0, selectedCategory.getText().indexOf(" ("));
        selectedCategory.click();
        return selectedCategoryName;
    }

    public void checkCheckboxSelectedOnCategory(String selectedCategory) {
        List<WebElement> checkedCategories = filtrCategories.stream().filter(st -> st.getDomAttribute("value").equals("true")).toList();
        assertThat(checkedCategories.size())
                .isEqualTo(1);
        assertThat(checkedCategories.get(0).getText())
                .isEqualTo(selectedCategory);
    }

    public List<CourseDetails> sortedByEarlyAndLateDate() {

        List<CourseDetails> courseDetailsList = lessonsCompScienceCatalogTab.stream()
                .map(it -> {
                    List<WebElement> elements = it.findElements(By.xpath(".//div[text() != '']"));
                    String name = elements.get(0).getText();
                    String date = elements.get(1).getText();
                    return new CourseDetails(name, date, it.getDomAttribute("href"));
                })
                .toList();

        Pair<CourseDetails, CourseDetails> minMax = courseDetailsList.stream()
                .reduce(
                        new Pair<>(null, null),
                        (acc, course) -> {
                            CourseDetails min = acc.first() == null || course.getFormattedDate().isBefore(acc.first().getFormattedDate())
                                    ? course
                                    : acc.first();
                            CourseDetails max = acc.second() == null || course.getFormattedDate().isAfter(acc.second().getFormattedDate())
                                    ? course
                                    : acc.second();
                            return new Pair<>(min, max);
                        },
                        (left, right) -> {
                            CourseDetails min = left.first().getFormattedDate().isBefore(right.first().getFormattedDate())
                                    ? left.first()
                                    : right.first();
                            CourseDetails max = left.second().getFormattedDate().isAfter(right.second().getFormattedDate())
                                    ? left.second()
                                    : right.second();
                            return new Pair<>(min, max);
                        });

        return courseDetailsList.stream()
                .filter(course -> course.equals(minMax.first()) || course.equals(minMax.second()))
                .toList();
    }

    private LocalDate parseStartDate(String rawDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", new Locale("ru"));
        int spaceCount = rawDate.length() - rawDate.replace(" ", "").length();
        if (spaceCount == 1) {
            rawDate = rawDate + " " + LocalDate.now().getYear();
        }
        return LocalDate.parse(rawDate, formatter);
    }

    public void validateFirstAndLastNameCourses(List<CourseDetails> expectedCourses) {
        List<CourseDetails> actualCourses = new ArrayList<>();
        for (CourseDetails course : expectedCourses) {
            open(course.getUrl());
            actualCourses.add(fetchCourseDetails(course.getUrl()));
        }
        boolean areEqual = actualCourses.equals(expectedCourses);
        assertThat(areEqual)
                .as("Course names or dates do not match between the page and the expected list")
                .isTrue();
    }

    private CourseDetails fetchCourseDetails(String url) {
        try {
            Document document = Jsoup.connect(url).get();

            String courseName = document.select("h1").text();
            String courseDate = document.select("//p[contains(@class, 'sc-1og4wiw-0 sc-3cb1l3-0 gcChXs dgWykw')])[1]").text();

            return new CourseDetails(courseName, courseDate, url);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
