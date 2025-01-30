package common;

import com.google.inject.Guice;
import modules.GuicePageModules;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import waiters.Waiters;

import javax.swing.*;

public abstract class AbsCommon<T> {

    protected WebDriver driver;
    protected Actions actions;
    protected Waiters waiters;

    public AbsCommon(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.waiters = new Waiters(driver);

        PageFactory.initElements(driver, this);
    }

    protected WebElement $(By locator) {
        return driver.findElement(locator);
    }

}
