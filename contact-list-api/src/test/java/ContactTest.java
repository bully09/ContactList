import org.contact.list.Application;
import org.contact.list.entities.Contact;
import org.contact.list.exception.BusinessException;
import org.contact.list.service.ContactService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContactTest {

    @Autowired
    ContactService contactService;

    private Contact contact;

    @Before
    public void loadData() {
        contact = new Contact();
        contact.setAge(20);
        contact.setName("Wade Wilson");
        contact.setPhone("0123456789");
    }

    @Test
    public void test1_save() {
        contactService.saveOrUpdate(contact);
        assertNotNull(contact.getId());
    }

    @Test
    public void test2_saveComplete() {
        contact.setId(54);
        contact.setNickName("Deadpool");
        contactService.saveOrUpdate(contact);
        assertNotNull(contact.getId());
    }

    @Test(expected = BusinessException.class)
    public void test3_saveInvalid() {
        contactService.saveOrUpdate(contact);
    }

    @Test(expected = BusinessException.class)
    public void test4_saveEmptyName() {
        contactService.saveOrUpdate(new Contact());
    }

    @Test
    public void test5_getAll() {
        List<Contact> contacts = contactService.findAll();
        assertNotNull(contacts);
        assertTrue(contacts.size() > 0);
    }

    @Test
    public void test6_delete() {
        contact.setId(1);
        contactService.delete(contact.getId());
        assertNotNull(contact.getId());
    }

}
