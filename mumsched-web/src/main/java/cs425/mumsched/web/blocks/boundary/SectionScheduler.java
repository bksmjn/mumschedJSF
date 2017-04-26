package cs425.mumsched.web.blocks.boundary;

import cs425.mumsched.web.courses.control.CourseFinder;
import cs425.mumsched.web.courses.entity.Course;
import cs425.mumsched.web.sections.control.SectionFinder;
import cs425.mumsched.web.sections.control.SectionManager;
import cs425.mumsched.web.sections.entity.Section;
import cs425.mumsched.web.usermgmt.control.UserFinder;
import cs425.mumsched.web.usermgmt.entity.User;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author praman
 */
@Component
@Scope("prototype")
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

//    @Autowired
//    private Messages message;
    @PostConstruct
    private void init() {
        try {
            Integer sectionId = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sectionId");
            System.out.println("Section ID" + this.sectionId);
            this.section = this.sectionFinder.findBySectionId(sectionId);
            System.out.println("Section=" + this.section.getSectionType());
            this.courses = this.courseFinder.findByCourseType(this.section.getSectionType());
            this.users = this.userFinder.findUsersByRole("PROFESSOR");
        } catch (Exception ex) {
//            message.addError(null, "Section", "Internal Server error");
        }

    }

    private String parameterForEdit(FacesContext fc) {
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("sectionId");
    }

    public String saveButtonClickedHandler() {
        try {
            System.out.println("SECTION" + this.section.getSectionId());
            this.sectionManager.updateSection(this.section);
//            message.addInfo(null, "Section", "Section Scheduled Successfully");

        } catch (Exception ex) {
//            message.addError(null, "Section", ex.getMessage());
        }
        return "/faces/blocks/listblock.xhtml?faces-redirect=true";
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
