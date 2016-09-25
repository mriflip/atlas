package atlassian.pages;

import atlassian.core.PageObject;
import org.openqa.selenium.By;

/**
 * Created by rajeshkalloor on 9/24/16.
 */
public class HomePage extends PageObject {

    public HomePage() {
        super(conf);
    }

    By linkedApplications = By.xpath("//*[@id='header']//span[text()='Linked Applications']");
    By confluenceLink = By.xpath("//*[@id='app-switcher']//span[text()='Confluence']");
    By jiraLink = By.xpath("//*[@id='header']//span[text()='JIRA']");

    public ConfluenceHomePage navigateToConfluence() {
        click(driver.findElement(linkedApplications));
        click(driver.findElement(confluenceLink));
        return new ConfluenceHomePage();
    }

}
