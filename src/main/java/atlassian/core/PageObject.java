package atlassian.core;

import atlassian.configuration.Configuration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

/**
 * Created by rajeshkalloor on 9/24/16.
 *
 * The PageObject class is extended by every Page class
 * It contains wrapper functions for the webdriver actions
 * It also initializes the jsExecutor, default timeouts etc.
 *
 */
public class PageObject extends BaseSetup{

    protected WebDriver driver;
    JavascriptExecutor jsExecutor;
    public long defaultWaitTime = 60;

    public PageObject(Configuration conf) {
        this.driver = webDriver;
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(this.defaultWaitTime, TimeUnit.SECONDS);
        driver.manage().timeouts()
                .implicitlyWait(this.defaultWaitTime, TimeUnit.SECONDS);

    }

    public void openUrl(String url) { driver.get(url); }

    public void click(WebElement element) { element.click(); }

    public void type(WebElement element, String text) { element.sendKeys(new CharSequence[]{text}); }

    public void clear(WebElement element) { element.clear(); }

    public void executeJavascript(String script) {
        jsExecutor.executeScript(script);
    }

}
