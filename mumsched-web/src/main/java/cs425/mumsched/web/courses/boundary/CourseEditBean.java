
package cs425.mumsched.web.courses.boundary;

import cs425.mumsched.web.courses.control.CourseFinder;
import cs425.mumsched.web.courses.control.CourseManager;
import cs425.mumsched.web.courses.entity.Course;
import cs425.mumsched.web.utils.Messages;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;

/**
 *
 * @author bikesh
 */
@Controller
@ViewScoped
@Lazy
public class CourseEditBean implements Serializable{

    private Course course;

    private String code;
    @Autowired
    private CourseFinder courseFinder;
    @Autowired
    private CourseManager courseManager;
    @Autowired
    private Messages message;

    @PostConstruct
    private void init() {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            this.code = this.parameterForEdit(fc);
            this.course = this.courseFinder.findByCourseCode(this.code);
            System.out.println("Course EDIT"+this.code);
        } catch (Exception ex) {
            message.addError(null, "Course Edit", ex.getMessage());
        }

    }

    private String parameterForEdit(FacesContext fc) {
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("code");
    }

    public String saveButtonClickedHandler() {
          try {
            this.courseManager.updateCourse(course);
            message.addInfo(null, "Course", "Course Updated Successfully");
        } catch (IllegalArgumentException ex) {
            message.addError(null, "Course", ex.getMessage());
        } catch (AccessDeniedException e) {
            message.addError(null, "Chapter Edit", e.getMessage());
        }
        return "/faces/courses/courselist.xhtml?faces-redirect=true";
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
