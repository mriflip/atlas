package atlassian;

import atlassian.core.BaseSetup;
import atlassian.models.Access;
import atlassian.models.PageData;
import atlassian.pages.DefaultPage;
import atlassian.pages.ConfluenceHomePage;
import atlassian.pages.HomePage;
import atlassian.pages.LoginPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Created by rajeshkalloor on 9/24/16.
 */
public class UITest extends BaseSetup {

    ConfluenceHomePage confluenceHomePage;
    LoginPage loginPage = new LoginPage();
    HomePage homePage;
    DefaultPage defaultPage;
    List<PageData> confluencesPages = new ArrayList<PageData>();

    @Test
    public void createNewPageAndSetRestrictions() throws InterruptedException {

        login("rajjj23@gmail.com", "welcome4414");

        // Creating 3 pages with different restriction levels
        for (Access acc : EnumSet.allOf(Access.class)) {
            defaultPage = confluenceHomePage.createNewDefaultPage();
            PageData pageData = new PageData();

            String title = "TestPage-" + Integer.toString((int)(System.currentTimeMillis()));
            String body = "Contents for " + title;

            pageData.setTitle(title);
            pageData.setBody(body);
            pageData.setRestrictions(acc);

            defaultPage.populatePage(title, body);
            defaultPage.validateCreatedPage(title, body);

            pageData.setPageLink(defaultPage.getPageLink());

            if (acc != Access.NoRestrictions)
                defaultPage.setRestrictions(acc);

            confluencesPages.add(pageData);
            confluenceHomePage = defaultPage.navigateToConfluence();
        }

        logout();
    }

    @Test(dependsOnMethods = {"createNewPageAndSetRestrictions"})
    public void testRestrictions() throws InterruptedException {

        login("raj_kalloor23@rediffmail.com", "welcome4414");

        for (PageData page : confluencesPages) {
            defaultPage = confluenceHomePage.searchAndNavigateToPage(defaultPage, page.getTitle());
            switch (page.getRestrictions()){
                case NoRestrictions: {
                    String newTitle = page.getTitle() + "-modified";
                    page.setTitle(newTitle);

                    defaultPage.editPage(newTitle);
                    defaultPage.validateCreatedPage(page.getTitle(), page.getBody());
                    break;
                }
                case EditingRestricted: {
                    defaultPage.validateEditRestriction();
                    break;
                }
                case ViewingAndEditingRestricted: {
                    defaultPage.validateViewAndEditRestriction(page.getPageLink());
                    break;
                }
            }
        }

        logout();
    }

    public void login(String user, String pwd) {
        webDriver.get(conf.getConfig("atlassianBaseUrl"));
        homePage = loginPage.loginToHome(user, pwd);
        confluenceHomePage = homePage.navigateToConfluence();
    }

    public void logout() {
        webDriver.get(conf.getConfig("atlassianBaseUrl") + "/logout");
        loginPage.logout();
    }

    @AfterClass
    public void quitBrowser() {
        webDriver.close();
    }

}
