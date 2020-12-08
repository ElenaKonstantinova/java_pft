package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() throws Exception {
        app.getContactHelper().modifyContact();
        app.getContactHelper().fillContactForm(new ContactData("test1","test1","test1","test1", null), false);
        app.getContactHelper().submitContactModification();

    }



}