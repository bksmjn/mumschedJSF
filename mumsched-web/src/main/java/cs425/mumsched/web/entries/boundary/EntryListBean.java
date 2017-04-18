/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs425.mumsched.web.entries.boundary;

import cs425.mumsched.web.entries.control.EntryFinder;
import cs425.mumsched.web.entries.entity.Entry;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

/**
 *
 * @author bikesh
 */
@Named
@ViewScoped
@Lazy
public class EntryListBean implements Serializable {

    private List<Entry> entries;

    @Autowired
    private EntryFinder entryFinder;

//    @Autowired
//    private Messages messsage;
    @PostConstruct
    private void init() {
        this.entries = this.entryFinder.getAllActiveEntries();

    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

}
