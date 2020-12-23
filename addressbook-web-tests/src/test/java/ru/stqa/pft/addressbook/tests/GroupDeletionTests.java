package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.common.TestBase;
import ru.stqa.pft.addressbook.appmanager.services.GroupHelper;
import ru.stqa.pft.addressbook.appmanager.services.NavigationHelper;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

    private GroupHelper groupHelperStep;
    private NavigationHelper navigationHelperStep;

    @BeforeClass
    public void initialization() {
        groupHelperStep = new GroupHelper(driver);
        navigationHelperStep = new NavigationHelper(driver);
    }

    @BeforeMethod
    public void ensurePreconditions() {
        navigationHelperStep.openGroupPage();
        if (groupHelperStep.getGroups().size() == 0) {
            groupHelperStep.createGroup(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupDeletion() throws Exception {
        Groups before = groupHelperStep.getGroups();
        GroupData deletedGroup = before.iterator().next();
        groupHelperStep.delete(deletedGroup);
        assertThat(groupHelperStep.count(), equalTo(before.size() - 1));
        Groups after = groupHelperStep.getGroups();
        assertThat(after, equalTo(before.without(deletedGroup)));
    }
}
