package ru.stqa.pft.addressbook.appmanager.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.appmanager.pages.GroupPage;

public class NavigationHelper extends HelperBase {

    private static final Logger logger = LogManager.getLogger(GroupPage.class);

    private final GroupPage groupPage;

    public NavigationHelper(WebDriver wd) {
        super(wd);
        groupPage = new GroupPage(wd);
    }

    public void openGroupPage() {
        logger.debug("Open Group page");
        if (groupPage.isHeaderButtonPresent() && groupPage.isNewButtonPresent()
                && groupPage.getHeaderButtonText().equals("Groups")) {
            groupPage.clickOnGroupsButton();
        }
    }

    public void gotoHomePage() {
        logger.debug("Open Home page");
        groupPage.isMainButtonPresent();
    }

    public void gotoContactPage() {
        logger.debug("Open Contact page");
        groupPage.clickOnHomeButton();
    }

}
