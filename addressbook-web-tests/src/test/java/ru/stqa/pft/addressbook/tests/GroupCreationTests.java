package ru.stqa.pft.addressbook.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.common.TestBase;
import ru.stqa.pft.addressbook.appmanager.services.GroupHelper;
import ru.stqa.pft.addressbook.appmanager.services.NavigationHelper;
import ru.stqa.pft.addressbook.appmanager.services.SessionHelper;
import ru.stqa.pft.addressbook.appmanager.utils.CommonUtils;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    private static final Logger logger = LogManager.getLogger(GroupCreationTests.class);

    private GroupHelper groupHelperStep;
    private NavigationHelper navigationHelperStep;
    private SessionHelper sessionHelperStep;

    @BeforeClass
    public void initialization() {
        groupHelperStep = new GroupHelper(driver);
        navigationHelperStep = new NavigationHelper(driver);
        sessionHelperStep = new SessionHelper(driver);
    }

    @BeforeMethod
    public void login() {
        logger.info("Perform login");
        sessionHelperStep.login(username, password);
    }

    @Test(description = "Check creation of test group")
    public void testGroupCreation() {
        logger.info("Step 1: Open group page");
        navigationHelperStep.openGroupPage();

        logger.info("Step 2: Get all groups");
        Groups before = groupHelperStep.getGroups();

        logger.info("Step 3: Create new group with name \"test2\"");
        GroupData group = new GroupData().withName("test2");
        groupHelperStep.createGroup(group);

        logger.info("Step 4: Check that count of the groups was changed");
        assertThat("Count of the groups", groupHelperStep.count(), equalTo(before.size() + 1));

        logger.info("Step 5: Get all groups");
        Groups after = groupHelperStep.getGroups();
        assertThat(after, equalTo(CommonUtils.compareGroups(before, after, group)));
    }

//  @Test
//  public void testBadGroupCreation() throws Exception {
//    app.goTo().groupPage();
//    Groups before = app.group().all();
//    GroupData group = new GroupData().withName("test2'");
//    app.group().createGroup(group);
//    assertThat(app.group().count(), equalTo(before.size()));
//    Groups after = app.group().all();
//    assertThat(after, equalTo(before));
//  }
}
