package components.popus;

import annotations.component.Component;
import components.AbsComponent;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

@Component("internal_component:component:.//div[./a[contains(@href, '/categories')]]")
public class PopupHeaderSubMenu extends AbsComponent<PopupHeaderSubMenu> implements IPopup<PopupHeaderSubMenu>{

    public PopupHeaderSubMenu(WebDriver driver) {
        super(driver);
    }

    @Override
    public PopupHeaderSubMenu popupShouldNotBeVisible() throws Exception{
        assertThat(waiters.waitForInvisible(getComponentEntity().findElement(getComponentBy())))
                .as("Error")
                .isTrue();
        return this;
    }

    @Override
    public PopupHeaderSubMenu popupShouldBeVisible() throws Exception{
        assertThat(waiters.waitForVisible(getComponentEntity().findElement(getComponentBy())))
                .as("Error")
                .isTrue();
        return this;
    }
}
