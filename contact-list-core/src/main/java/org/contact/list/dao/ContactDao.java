package org.contact.list.dao;

import org.contact.list.entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactDao extends PagingAndSortingRepository<Contact, Integer> {

    /***
     * Validate if a contact exists
     * @param name Contact name
     * @param Id Identifier
     * @return Exists or not
     */
    @Query("SELECT COUNT(id) > 0 FROM Contact WHERE name LIKE :name AND id <> :id")
    boolean existsName(@Param("name") String name, @Param("id") Integer Id);

}
