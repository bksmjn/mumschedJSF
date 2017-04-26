/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs425.mumsched.web.entries.boundary;

import cs425.mumsched.web.entries.control.EntryManager;
import cs425.mumsched.web.entries.entity.Entry;
import cs425.mumsched.web.utils.Messages;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;

/**
 *
 * @author amit
 */
@Named
public class EntryAddBean {

    private Entry entry;

    @Autowired
    private EntryManager entryManager;
    @Autowired
    private Messages message;

    @PostConstruct
    private void init() {
        this.entry = new Entry();
    }

    public String saveButtonClickedHandler() {
        try {
            this.entryManager.addEntry(entry);
            message.addInfo(null, "Entry", "Entry Added Successfully");
            this.entry=new Entry();
        } catch (IllegalArgumentException ex) {
            message.addError(null, "Entry", ex.getMessage());
        } catch (AccessDeniedException e) {
            message.addError(null, "Entry", e.getMessage());
        } catch (Exception ex) {
            message.addError(null, "Entry", ex.getMessage());
        }
        return null;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }
    
    

}
