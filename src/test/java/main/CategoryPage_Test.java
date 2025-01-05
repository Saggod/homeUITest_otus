package main;

import com.google.inject.Inject;
import components.static_component.HeaderMenuCompoonent;
import data.menu.HeaderMenuData;
import extensions.UiExtensions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.CategoryCardPage;
import pages.CategoryCoursesPage;
import pages.MainPage;

@ExtendWith(UiExtensions.class)
public class CategoryPage_Test {

    @Inject
    public CategoryCoursesPage categoryPage;

    @Inject
    private CategoryCardPage categoryCardPage;

    @Inject
    private MainPage mainPage;

    @Inject
    private HeaderMenuCompoonent headerMenuCompoonent;

    @Test
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
    public void catalogEducationSelectTest() {
        String educationCatalog = categoryPage
                .open()
                .getEducationItem(1);

        headerMenuCompoonent
            .setFocusToMenuItem(HeaderMenuData.EDUCATION);
        categoryPage
                .clickEducationCourseOnHeader(educationCatalog);
        categoryCardPage
                .pageHeaderShoulbBeSameAs(educationCatalog);







    }

}
