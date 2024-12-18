package mail;

import com.google.inject.Inject;
import extensions.UiExtensions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.CategoryCardPage;
import pages.CategoryPage;

@ExtendWith(UiExtensions.class)
public class CategoryPage_Test {

    @Inject
    public CategoryPage categoryPage;

    @Inject
    private CategoryCardPage categoryCardPage;

    @Test
    public void categoryPageTest() {
        String categoryTitle = categoryPage
                .open()
                .getCategoryItem(1);

        categoryPage
                .clickCategoryTitle(categoryTitle);
        categoryCardPage
                .pageHeaderShoulbBeSameAs(categoryTitle);


//        Практика: Написание авто-теста с нуля  -1:42:26

    }


}
