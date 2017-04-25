/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs425.mumsched.web.utils;

import cs425.mumsched.web.courses.control.CourseFinder;
import cs425.mumsched.web.courses.entity.Course;
import cs425.mumsched.web.entries.control.EntryFinder;
import cs425.mumsched.web.usermgmt.control.UserFinder;
import cs425.mumsched.web.usermgmt.entity.User;
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
    
    @Inject
    private UserFinder userFinder;
    @Inject
    private CourseFinder courseFinder;

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

    public SelectItem[] toArrayOfSelectItemProfessor(List<User> users) {
        SelectItem[] items = new SelectItem[users.size()];
        int index = 0;
        for (User u : users) {
            items[index] = new SelectItem(u.getUserId(), u.getUserName());
            index++;
        }
        return items;
    }

    public SelectItem[] getAllProfessors() {
        return toArrayOfSelectItemProfessor(this.userFinder.findUsersByRole("PROFESSOR"));
    }
    
      public SelectItem[] toArrayOfSelectItemCourse(List<Course> courses) {
        SelectItem[] items = new SelectItem[courses.size()];
        int index = 0;
        for (Course c : courses) {
            items[index] = new SelectItem(c.getCourseId(), c.getCourseTitle());
            index++;
        }
        return items;
    }

    public SelectItem[] getAllCourses() {
        return toArrayOfSelectItemCourse(this.courseFinder.findAll());
    }
}
