/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs425.mumsched.web.entries.control;

import cs425.mumsched.web.entries.entity.Entry;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author amit
 */
@Repository
public class EntryManager {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(propagation = Propagation.REQUIRED)
    public void addEntry(Entry entry) {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(entry);
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
    
    
}
