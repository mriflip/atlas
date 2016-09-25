package atlassian.pages;

import atlassian.core.PageObject;
import atlassian.models.Access;
import atlassian.models.PageData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

/**
 * Created by rajeshkalloor on 9/24/16.
 *
 * The DefaultPage holds locators and methods for all actions
 * performed on a new page as well as an existing page.
 */
public class DefaultPage extends PageObject {

    public DefaultPage() {
        super(conf);
    }

    By editTitle = By.xpath("//*[@id='content-title']");
    By editBody = By.xpath("//body[@id='tinymce']/p");
    By saveButton = By.xpath("//*[@id='rte-button-publish'][text()='Save']");

    By savedTitle = By.xpath(".//*[@id='title-text']/a");
    By savedBody = By.xpath("//*[@id='main-content']/p");

    By restrictionsButton = By.xpath("//*[@id='content-metadata-page-restrictions']");
    By restrictionsDropdown = By.xpath("//*[@id='s2id_page-restrictions-dialog-selector']/a");
    By editRestriction = By.xpath("//*[@id='select2-drop']//span[text()='Editing restricted']");
    By viewAndEditRestriction = By.xpath("//*[@id='select2-drop']//span[text()='Viewing and editing restricted']");
    By pageRestrictionsSave = By.xpath("//*[@id='page-restrictions-dialog-save-button']");

    By editButton = By.xpath("//*[@id='editPageLink']");

    By noAccessText = By.xpath("//*[@id='page-restricted-container']");

    By confluenceHomeButton = By.xpath("//*[@id='logo']/a/span[text()='Confluence']");
    By confluenceButton = By.xpath("//a[@title='TestAutomation'][text()='TestAutomation']");

    public void populatePage(String title, String body) {
        type(driver.findElement(editTitle), title);
        String script = "tinyMCE.activeEditor.setContent('<h1>Heading for the test page</h1> "+body+"')";
        executeJavascript(script);
        click(driver.findElement(saveButton));
    }

    public void validateCreatedPage(String title, String body) {
        Assert.assertEquals(driver.findElement(savedTitle).getText(), title);
        Assert.assertEquals(driver.findElement(savedBody).getText(), body);
    }

    public void editPage(String newTitle) throws InterruptedException {
        click(driver.findElement(editButton));

        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.elementToBeClickable(editTitle));

        click(driver.findElement(editTitle));
        clear(driver.findElement(editTitle));
        type(driver.findElement(editTitle), newTitle);
        click(driver.findElement(saveButton));
    }

    public void setRestrictions(Access restrictions) throws InterruptedException {
        click(driver.findElement(restrictionsButton));
        click(driver.findElement(restrictionsDropdown));
        if (restrictions == Access.EditingRestricted) {
            click(driver.findElement(editRestriction));
        }
        else if (restrictions == Access.ViewingAndEditingRestricted) {
            click(driver.findElement(viewAndEditRestriction));
        }
        click(driver.findElement(pageRestrictionsSave));

        // The loading takes a second. Have put sleep to avoid failure because of that
        // This can be modified/optimized to use some wait.until(ExpectedConditions)
        Thread.sleep(3000);
    }

    public void validateEditRestriction() {
        long newWaitTime = 5;
        driver.manage().timeouts().implicitlyWait(newWaitTime, TimeUnit.SECONDS);
        Assert.assertEquals(0, driver.findElements(editButton).size());
        driver.manage().timeouts().implicitlyWait(defaultWaitTime, TimeUnit.SECONDS);
    }

    public void validateViewAndEditRestriction(String pageLink) {
        openUrl(pageLink);
        Assert.assertTrue(driver.findElement(noAccessText).isDisplayed());
    }

    public ConfluenceHomePage navigateToConfluence() {
        click(driver.findElement(confluenceButton));
        return new ConfluenceHomePage();
    }

    public String getPageLink() {
        return driver.getCurrentUrl();
    }

}
