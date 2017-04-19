/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author bikesh
 */
@Entity
public class Section implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sectionId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany
    private List<User> users;

    @ManyToOne
    private Block block;

    @Column(name = "room_no")
    private String roomNo;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "status")
    private String status;

    public Section(Block block, String status) {
        this.block = block;
        this.status=status;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
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

}
