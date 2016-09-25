package atlassian.configuration;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rajeshkalloor on 9/24/16.
 *
 * The job of WebDriverConfig class is to prepare the browser
 * profiles [chrome/firefox etc] and return it to calling class
 *
 */
public class WebDriverConfig {

    public static FirefoxProfile getFirefoxProfile(Configuration conf) {

        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("geo.enabled", false);
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        profile.setPreference("browser.download.manager.showWheneStarting", false);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/csv");
        profile.setPreference("app.update.auto", false);
        profile.setPreference("app.update.enabled", false);
        profile.setPreference("app.update.silent", false);

        return profile;
    }

    public static DesiredCapabilities getFireFoxConfig(Configuration conf){


        FirefoxProfile profile = new FirefoxProfile();

        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("geo.enabled", false);
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        profile.setPreference("browser.download.manager.showWheneStarting", false);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/csv,application/vnd.ms-excel");
        if(!profile.areNativeEventsEnabled())
            profile.setEnableNativeEvents(true);
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setJavascriptEnabled(true);
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);
        return capabilities;
    }

    public static DesiredCapabilities getChromeConfig(Configuration conf) {

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        Map<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("disable-popup-blocking", true);
        chromePrefs.put("ignore-certifiate-errors", true);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--test-type");
        System.setProperty("webdriver.chrome.args", "--disable-logging");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        capabilities.setJavascriptEnabled(true);
        System.setProperty("webdriver.chrome.driver", conf.getConfig("chromeDriverPath"));
        return capabilities;
    }
}
