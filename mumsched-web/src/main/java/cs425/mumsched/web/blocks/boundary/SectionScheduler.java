package cs425.mumsched.web.blocks.boundary;

import cs425.mumsched.web.courses.control.CourseFinder;
import cs425.mumsched.web.courses.entity.Course;
import cs425.mumsched.web.sections.control.SectionFinder;
import cs425.mumsched.web.sections.control.SectionManager;
import cs425.mumsched.web.sections.entity.Section;
import cs425.mumsched.web.usermgmt.control.UserFinder;
import cs425.mumsched.web.usermgmt.entity.User;
import cs425.mumsched.web.utils.Messages;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

/**
 *
 * @author bikesh
 */
@Controller
@ViewScoped
@Lazy
public class SectionScheduler implements Serializable {

    private Section section;
    private List<User> users;
    private List<Course> courses;
    private String sectionId;
    private Course assignedCourse;
    private String courseCode;
    private User assignedUser;
    private String userId;

    @Autowired
    private SectionFinder sectionFinder;

    @Autowired
    private SectionManager sectionManager;

    @Autowired
    private UserFinder userFinder;

    @Autowired
    private CourseFinder courseFinder;

    @Autowired
    private Messages message;

    @PostConstruct
    private void init() {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            this.sectionId = this.parameterForEdit(fc);
            System.out.println("Section ID" + this.sectionId);
            this.section = this.sectionFinder.findBySectionId(Integer.parseInt(sectionId));
            this.courses = this.courseFinder.findByCourseType(this.section.getSectionType());
            this.users = this.userFinder.findUsersByRole("PROFESSOR");
        } catch (Exception ex) {
            message.addError(null, "Section", "Internal Server error");
        }

    }

    private String parameterForEdit(FacesContext fc) {
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("sectionId");
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
        return toArrayOfSelectItemProfessor(this.users);
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
        return toArrayOfSelectItemCourse(this.courses);
    }

    public String saveButtonClickedHandler() {
        try {
            this.sectionManager.updateSection(this.section);
            message.addInfo(null, "Section", "Section Scheduled Successfully");
            return "/faces/sections/listsection.xhtml?sectionId="+this.sectionId +"& faces-redirect=true";
        } catch (Exception ex) {
            message.addError(null, "Section", ex.getMessage());
        }
        return null;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public Course getAssignedCourse() {
        return assignedCourse;
    }

    public void setAssignedCourse(Course assignedCourse) {
        this.assignedCourse = assignedCourse;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
