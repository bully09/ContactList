package org.contact.list.controller;

import org.contact.list.entities.Contact;
import org.contact.list.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/contact")
public class ContactController {

    @Autowired
    ContactService contactService;

    @GetMapping()
    public List<Contact> findAll() {
        return contactService.findAll();
    }

    @GetMapping(value = "/findAll")
    public Page<Contact> findAll(Pageable pageable){
        return contactService.findAll(pageable);
    }

    @PostMapping()
    public void save(@RequestBody Contact contact) {
        contactService.saveOrUpdate(contact);
    }

    @PutMapping()
    public void update(@RequestBody Contact contact) {
        contactService.saveOrUpdate(contact);
    }

    @DeleteMapping(value = "/{id}")
    public void update(@PathVariable Integer id) {
        contactService.delete(id);
    }
}
