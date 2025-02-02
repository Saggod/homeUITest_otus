package main;

import com.google.inject.Inject;
import data.CourseDetails;
import extensions.UiExtensions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pages.CategoryCardPage;
import pages.CategoryCoursesPage;
import pages.MainPage;
import java.util.List;

@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(UiExtensions.class)
public class CategoryPage_Test {

    @Inject
    public CategoryCoursesPage categoryPage;

    @Inject
    private CategoryCardPage categoryCardPage;

    @Inject
    private MainPage mainPage;


    @Test
    @DisplayName("Сценарий 1: Найти курс по имени")
    public void categoryPageTest() {
        String categoryTitle = categoryPage
                .open()
                .getCategoryItem(1);
        categoryPage
                .clickCategoryTitle(categoryTitle);
        categoryCardPage
                .pageHeaderShoulbBeSameAs(categoryTitle);

    }

    @Test
    @DisplayName("Сценарий 2: Найти курсы, которые стартуют раньше и позже всех.")
    public void sortedByEarlyAndLateCoursesDatesTest() {
        categoryPage
                .open();
        List<CourseDetails> listLessons = categoryPage
                .sortedByEarlyAndLateDate();
        categoryPage.
                validateFirstAndLastCourses(listLessons);

    }

    @Test
    @DisplayName("Сценарий 3: Открыть меню 'Обучение' и выбрать случайную категорию курсов")
    public void catalogEducationSelectTest() {
        categoryPage
                .open();
        String educationCatalog = categoryPage
                .selectRandomCategory();
        categoryPage
                .checkCheckboxSelectedOnCategory(educationCatalog);
    }



}
