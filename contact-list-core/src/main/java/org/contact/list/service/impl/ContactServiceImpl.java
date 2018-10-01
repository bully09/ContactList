package org.contact.list.service.impl;

import org.contact.list.dao.ContactDao;
import org.contact.list.entities.Contact;
import org.contact.list.exception.BusinessException;
import org.contact.list.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactDao contactDao;

    @Override
    public List<Contact> findAll() {
        return (List<Contact>) contactDao.findAll();
    }

    @Override
    public Page<Contact> findAll(Pageable pageable) {
        return contactDao.findAll(pageable);
    }

    @Override
    public void saveOrUpdate(Contact contact) {
        if(isNullOrEmpty(contact.getName())) {
            throw new BusinessException("Name can not be empty");
        }

        if(isNullOrEmpty(contact.getPhone())) {
            throw new BusinessException("Phone can not be empty");
        }

        if(contactDao.existsName(contact.getName(), contact.getId())) {
            throw new BusinessException("Contact name already exists");
        }
        contactDao.save(contact);
    }

    @Override
    public void delete(Integer id) {
        contactDao.deleteById(id);
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().equalsIgnoreCase(new String());
    }
}
