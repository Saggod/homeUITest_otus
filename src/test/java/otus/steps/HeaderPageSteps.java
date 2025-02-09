package otus.steps;

import com.google.inject.Inject;
import io.cucumber.java.ru.Пусть;
import pages.CategoryCoursesPage;


public class HeaderPageSteps {

    public static String selectedCategory;

    @Inject
    public CategoryCoursesPage categoryCoursesPage;

    @Пусть("Переходим на случайную категорию курсов")
    public void selectRandomLessonCategory() {
        selectedCategory = categoryCoursesPage.selectRandomCategory();
    }

}
