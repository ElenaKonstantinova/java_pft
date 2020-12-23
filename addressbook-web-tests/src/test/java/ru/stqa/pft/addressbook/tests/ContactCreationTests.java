package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.common.TestBase;
import ru.stqa.pft.addressbook.appmanager.services.ContactHelper;
import ru.stqa.pft.addressbook.appmanager.services.GroupHelper;
import ru.stqa.pft.addressbook.appmanager.services.NavigationHelper;
import ru.stqa.pft.addressbook.appmanager.services.SessionHelper;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase {

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

    @Test
    public void testContactCreation() throws Exception {
        navigationHelperStep.gotoContactPage();
        List<ContactData> before = contactHelperStep.getContactList();
        ContactData contact = new ContactData("test1", "test1", "test1", "test1", "test1");
        contactHelperStep.initContactCreation();
        contactHelperStep.fillContactForm(contact, true);
        contactHelperStep.submitContactCreation();
        navigationHelperStep.gotoContactPage();
        List<ContactData> after = contactHelperStep.getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);


        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
