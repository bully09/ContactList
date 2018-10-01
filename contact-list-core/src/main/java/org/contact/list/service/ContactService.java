package org.contact.list.service;

import org.contact.list.entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContactService {

    /**
     * Get all contact list
     * @return Contact list
     */
    List<Contact> findAll();

    /**
     * Get all contact list by page
     * @param pageable Page to show
     * @return Contact list
     */
    Page<Contact> findAll(Pageable pageable);

    /**
     * Save or update a contact
     * @param contact
     */
    void saveOrUpdate(Contact contact);

    /**
     * Delete a contact
     * @param id Identifier of contact
     */
    void delete(Integer id);
}
