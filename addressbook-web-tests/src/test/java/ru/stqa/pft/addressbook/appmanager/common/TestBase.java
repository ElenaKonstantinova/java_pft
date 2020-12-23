package ru.stqa.pft.addressbook.appmanager.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.driver.ApplicationManager;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);
    protected final String username = "admin";
    protected final String password = "secret";
    protected WebDriver driver = null;

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        app.init();
        driver = app.getDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }


}
