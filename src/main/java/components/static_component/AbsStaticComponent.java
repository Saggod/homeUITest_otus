package components.static_component;

import components.AbsComponent;
import org.openqa.selenium.WebDriver;

public abstract class AbsStaticComponent<T> extends AbsComponent<T> {

    public AbsStaticComponent(WebDriver driver) {
        super(driver);
    }

    {
        try {
            waiters.waitForVisible(getComponentEntity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
