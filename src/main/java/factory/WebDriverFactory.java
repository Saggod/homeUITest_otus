package factory;

import exceptions.BrowserNotFoundException;
import factory.settings.ChromeSettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WebDriverFactory {

    private String browserName = System.getProperty("browser");

    public WebDriver create() throws MalformedURLException {
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "Pixel 3");

        if (!System.getProperty("remote.url").isEmpty()) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.setExperimentalOption("mobileEmulation", mobileEmulation);
            options.setCapability("browserVersion", "128");
            options.setCapability("selenoid:options", new HashMap<String, Object>() {{

                put("name", "Test badge...");

                /* How to set session timeout */
                put("sessionTimeout", "15m");

                /* How to set timezone */
                put("env", new ArrayList<String>() {{
                    add("TZ=UTC");
                }});

                /* How to add "trash" button */
                put("labels", new HashMap<String, Object>() {{
                    put("manual", "true");
                }});

                /* How to enable video recording */
                put("enableVideo", false);
            }});
            return new RemoteWebDriver (new URL("http://192.168.182.128/wd/hub"), options);

        }

        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = (ChromeOptions) new ChromeSettings().settings();
               return new ChromeDriver(chromeOptions);
            default:
                throw new BrowserNotFoundException(browserName);
        }

    }
}
