package cucumber.steps.pages;

import com.google.inject.Inject;
import data.CourseDetails;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import pages.CategoryCardPage;
import pages.CategoryCoursesPage;


import java.util.List;

public class LessonsPageSteps {

    private static List<CourseDetails> courseDetails;

    @Inject
    public CategoryCardPage categoryCardPage; //lessonPage

    @Inject
    public CategoryCoursesPage categoryCoursesPage;  //lessonsListPage

    @Пусть("Открываем курс: {string}")
    public void selectLessonByName(String name) {
        categoryCoursesPage.clickCategoryTitle(name);
    }

    @Тогда("Название курса на его странице = {string}")
    public void checkHeaderTitle(String name) {
        categoryCardPage.pageHeaderShoulbBeSameAs(name);
    }

    @Пусть("Получаем список курсов с самой ранней и самой поздней датой")
    public void getSortedLessonsByDate() {
        courseDetails = categoryCoursesPage.sortedByEarlyAndLateDate();
    }

    @Тогда("Проверяем даты и названия полученных курсов")
    public void checkFirstAndLastLessons() {
        categoryCoursesPage.validateFirstAndLastNameCourses(courseDetails);
    }
//
//    @Тогда("Проверяем, что отображаются курсы выбранной категории")
//    public void checkSelectedCategory() {
//        categoryCoursesPage.checkSelectedCategory(HeaderPageSteps.selectedCategory);
//    }
}
