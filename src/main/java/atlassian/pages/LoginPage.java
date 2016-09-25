package atlassian.pages;

import atlassian.core.PageObject;
import org.openqa.selenium.By;

/**
 * Created by rajeshkalloor on 9/24/16.
 */
public class LoginPage extends PageObject {

    public LoginPage() {
        super(conf);
    }

    By username = By.id("username");
    By password = By.id("password");
    By loginButton = By.id("login");
    By logoutButton = By.id("logout");

    public HomePage loginToHome(String user, String pwd) {
        type(driver.findElement(username), user);
        type(driver.findElement(password), pwd);
        click(driver.findElement(loginButton));
        return new HomePage();
    }

    public void logout() {
        click(driver.findElement(logoutButton));
    }

}
