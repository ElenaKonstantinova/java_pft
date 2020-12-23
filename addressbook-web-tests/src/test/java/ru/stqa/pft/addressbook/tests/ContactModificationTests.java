package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.common.TestBase;
import ru.stqa.pft.addressbook.appmanager.services.ContactHelper;
import ru.stqa.pft.addressbook.appmanager.services.NavigationHelper;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;


public class ContactModificationTests extends TestBase {

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
    public void testContactModification() throws Exception {
        navigationHelperStep.gotoContactPage();
        List<ContactData> before = contactHelperStep.getContactList();
        int index = before.size() - 1;
        ContactData contact = new ContactData(before.get(index).getId(), "test2", "test2", "test2", "test2", null);
        contactHelperStep.modifyContact(before, index, contact);
        List<ContactData> after = contactHelperStep.getContactList();
        Assert.assertEquals(after.size(), before.size());

        //удаляем из списка последний элемент и добавляем тот, который добавили
        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}