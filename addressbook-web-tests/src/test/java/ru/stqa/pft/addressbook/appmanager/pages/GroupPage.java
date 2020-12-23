package ru.stqa.pft.addressbook.appmanager.pages;

import jdk.jfr.Name;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GroupPage extends CommonPage {

    private static final Logger logger = LogManager.getLogger(GroupPage.class);

    @Name("Header button")
    @FindBy(css = "h1")
    private WebElement headerButton;
    @Name("Groups button")
    @FindBy(xpath = ".//*[text()='groups']")
    private WebElement groupsButton;
    @Name("New button")
    @FindBy(css = "new")
    private WebElement newButton;
    @Name("Maintable button")
    @FindBy(css = "maintable")
    private WebElement mainButton;
    @Name("Home button")
    @FindBy(xpath = ".//*[text()='home']")
    private WebElement homeButton;

    public GroupPage(WebDriver driver) {
        super(driver);
    }

    public boolean isMainButtonPresent() {
        logger.trace("Get status of Main button");
        return mainButton.isEnabled();
    }

    public boolean isHeaderButtonPresent() {
        logger.trace("Get status of Header button");
        return headerButton.isEnabled() && headerButton.isDisplayed();
    }

    public boolean isNewButtonPresent() {
        logger.trace("Get status of New button");
        return newButton.isDisplayed() && newButton.isEnabled();
    }

    public String getHeaderButtonText() {
        logger.trace("Get text of Header button");
        return headerButton.getText();
    }

    public void clickOnHomeButton() {
        logger.trace("Click on Home button");
        homeButton.click();
    }

    public void clickOnGroupsButton() {
        logger.trace("Click on Groups button");
        groupsButton.click();
    }
}
