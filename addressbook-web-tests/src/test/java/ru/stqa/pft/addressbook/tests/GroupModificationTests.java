package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.common.TestBase;
import ru.stqa.pft.addressbook.appmanager.services.ContactHelper;
import ru.stqa.pft.addressbook.appmanager.services.GroupHelper;
import ru.stqa.pft.addressbook.appmanager.services.NavigationHelper;
import ru.stqa.pft.addressbook.appmanager.services.SessionHelper;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

    private GroupHelper groupHelperStep;
    private NavigationHelper navigationHelperStep;
    private SessionHelper sessionHelperStep;
    private ContactHelper contactHelperStep;

    @BeforeClass
    public void initialization() {
        groupHelperStep = new GroupHelper(driver);
        navigationHelperStep = new NavigationHelper(driver);
        sessionHelperStep = new SessionHelper(driver);
        contactHelperStep = new ContactHelper(driver);
    }

    @BeforeMethod
    public void ensurePreconditions() {
        navigationHelperStep.openGroupPage();
        if (groupHelperStep.getGroups().size() == 0) {
            groupHelperStep.createGroup(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = groupHelperStep.getGroups();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");
        groupHelperStep.modify(group);
        assertThat(groupHelperStep.count(), equalTo(before.size()));
        Groups after = groupHelperStep.getGroups();
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }
}
