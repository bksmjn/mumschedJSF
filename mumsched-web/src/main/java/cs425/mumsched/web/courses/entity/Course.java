package cs425.mumsched.web.courses.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author anita
 */
@Entity
@Table(name = "courses")
@NamedQueries({
    @NamedQuery(name = Course.FIND_ALL, query = "SELECT c FROM Course c where c.isActive=true"),
    @NamedQuery(name = Course.FIND_BY_COURSE_CODE, query = "SELECT c FROM Course c where c.courseCode=:code and c.isActive=true"),
    @NamedQuery(name = Course.FIND_BY_COURSE_TYPE, query = "SELECT c FROM Course c where c.courseType=:courseType and c.isActive=true")
})
public class Course implements Serializable {

    @Id
    @GeneratedValue
    private int courseId;
    @NotNull
    @Column(unique = true, name = "course_code")
    private String courseCode;
    @NotNull
    @Column(name = "course_title")
    private String courseTitle;
    @NotNull
    @Column(name = "course_description")
    private String courseDescription;

    @NotNull
    @Column(name = "course_level")
    private String courseLevel;

    @Column(name = "course_type")
    private String courseType="ELECTIVE";

    @Column(name = "is_active")
    private boolean isActive=true;

    private static final String DOMAIN_PREFIX = "cs425.myblog.web.usermgmt.entity.Course";
    public static final String FIND_ALL = DOMAIN_PREFIX + "FIND_ALL";
    public static final String FIND_BY_COURSE_CODE = DOMAIN_PREFIX + "FIND_BY_COURSE_CODE";
    public static final String FIND_BY_COURSE_TYPE = DOMAIN_PREFIX + "FIND_BY_COURSE_TYPE";

    public Course() {
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCourseLevel() {
        return courseLevel;
    }

    public void setCourseLevel(String courseLevel) {
        this.courseLevel = courseLevel;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    @Override
    public String toString() {
        return this.courseCode;
    }

}
