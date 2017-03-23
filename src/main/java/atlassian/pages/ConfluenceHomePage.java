package atlassian.pages;

import atlassian.core.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * Created by rajeshkalloor on 9/24/16.
 *
 * The page to navigate to once we login to atlassian
 * We create new page from here
 *
 */
public class ConfluenceHomePage extends PageObject{

    public ConfluenceHomePage() {
        super(conf);
    }

    By quickCreate = By.xpath("//*[@id='quick-create-page-button'][text()='Create']");
    By customCreate = By.xpath("//*[@id='create-page-button']/span");

    By searchBox = By.xpath("//*[@id='quick-search-query']");

    public DefaultPage createNewDefaultPage() {
        click(driver.findElement(quickCreate));
        return new DefaultPage();
    }

    public DefaultPage searchAndNavigateToPage(DefaultPage defaultPage, String title) {
        WebElement search = driver.findElement(searchBox);

        click(search);
        search.sendKeys(title);

        // The loading takes a second. Have put sleep to avoid failure because of that
        // This can be modified/optimized to use some wait.until(ExpectedConditions)
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        search.sendKeys(Keys.ARROW_DOWN);
        search.sendKeys(Keys.RETURN);

        return defaultPage;
    }
}
