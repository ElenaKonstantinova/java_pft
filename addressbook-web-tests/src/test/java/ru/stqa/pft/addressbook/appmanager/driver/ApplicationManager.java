package ru.stqa.pft.addressbook.appmanager.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import ru.stqa.pft.addressbook.appmanager.services.HelperBase;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private final String browser;
    private WebDriver wd;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        if (browser.equals(BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();
        } else if (browser.equals(BrowserType.CHROME)) {
            wd = new ChromeDriver();
        } else if (browser.equals(BrowserType.IE)) {
            wd = new InternetExplorerDriver();
        }
        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        new HelperBase(wd);
    }

    public void stop() {
        wd.quit();
    }

    public WebDriver getDriver() {
        return wd;
    }
}
