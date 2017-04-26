/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs425.mumsched.web.entries.control;

import cs425.mumsched.web.entries.entity.Entry;
import java.util.ArrayList;
import java.util.List;
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
public class EntryFinder {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Entry getEntryByCode(String code) {
        Entry entry = new Entry();
        try {
            entry = (Entry) sessionFactory.getCurrentSession().getNamedQuery(Entry.FIND_BY_CODE).setParameter("entryCode", code).uniqueResult();
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
        return entry;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Entry> getAllActiveEntries() {
        List<Entry> entries = new ArrayList<>();
        try {
            entries = sessionFactory.getCurrentSession().getNamedQuery(Entry.FIND_ALL).list();
            System.out.println("SIZE"+entries.size());
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
        return entries;
    }

}
