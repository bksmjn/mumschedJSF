package cs425.mumsched.web.sections.entity;

import cs425.mumsched.web.blocks.entity.Block;
import cs425.mumsched.web.courses.entity.Course;
import cs425.mumsched.web.usermgmt.entity.User;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author bikesh
 */
@Entity
public class Section implements Serializable {

    private static final String DOMAIN_PREFIX = "cs425.mumsched.web.sections.entity.Section";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sectionId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany
    private List<User> users;

    @ManyToOne
    private Block block;

    @Column(name = "room_no")
    private String roomNo;

    @Column(name = "available_seat")
    private int availableSeats = 30;

    @Column(name = "section_type")
    private String sectionType;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "status")
    private String status;

    public Section() {

    }

    public Section(Block block, String status, String sectionType) {
        this.block = block;
        this.status = status;
        this.sectionType = sectionType;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSectionType() {
        return sectionType;
    }

    public void setSectionType(String sectionType) {
        this.sectionType = sectionType;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

}
