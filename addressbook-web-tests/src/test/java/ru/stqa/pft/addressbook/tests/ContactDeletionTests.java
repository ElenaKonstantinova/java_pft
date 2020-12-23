package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.common.TestBase;
import ru.stqa.pft.addressbook.appmanager.services.ContactHelper;
import ru.stqa.pft.addressbook.appmanager.services.NavigationHelper;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    private NavigationHelper navigationHelperStep;
    private ContactHelper contactHelperStep;

    @BeforeClass
    public void initialization() {
        navigationHelperStep = new NavigationHelper(driver);
        contactHelperStep = new ContactHelper(driver);
    }

    @BeforeMethod
    public void ensurePreconditions() {
        if (!contactHelperStep.isThereAContact()) {
            contactHelperStep.createContact(new ContactData("test1", "test1", "test1", "test1", "test1"), true);
        }
    }

    @Test
    public void testContactDeletion() throws Exception {
        navigationHelperStep.gotoContactPage();
        List<ContactData> before = contactHelperStep.getContactList();
        contactHelperStep.selectContact(before.size() - 1);
        contactHelperStep.deleteSelectedContacts();
        contactHelperStep.isAlertPresent();
        navigationHelperStep.gotoContactPage();
        List<ContactData> after = contactHelperStep.getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
