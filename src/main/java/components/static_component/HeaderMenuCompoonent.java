package components.static_component;

import annotations.component.Component;
import com.google.inject.Inject;
import components.AbsComponent;
import data.menu.HeaderMenuData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


@Component("css:a[href='/'] ~ nav")
public class HeaderMenuCompoonent extends AbsComponent<HeaderMenuCompoonent> {

    public HeaderMenuCompoonent(WebDriver driver) {
        super(driver);
    }

    @Inject
    private HeaderMenuCompoonent headerMenuCompoonent;


    public void setFocusToMenuItem(HeaderMenuData headerMenuData){
        String locator = String.format("//span[text()='%s']]", headerMenuData.getName());
        actions.moveToElement($(By.xpath(locator))).click().build().perform();
    }


}
