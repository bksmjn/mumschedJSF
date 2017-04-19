/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs425.mumsched.web.utils;

import cs425.mumsched.web.entries.control.EntryFinder;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author bikesh
 */
@Named
@ApplicationScoped
public class UserHelperBean {

    @Inject
    private EntryFinder entryFinder;

    public <T> SelectItem[] toArrayOfSelectItem(List<T> obj) {
        SelectItem[] items = new SelectItem[obj.size()];
        int index = 0;
        for (T r : obj) {
            items[index] = new SelectItem(r);
            index++;
        }
        return items;
    }

    public SelectItem[] getAllEntries() {
        return toArrayOfSelectItem(this.entryFinder.getAllActiveEntries());
    }
}

