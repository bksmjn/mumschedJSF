/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs425.mumsched.web.sections.entity;

import cs425.mumsched.web.courses.entity.Course;
import cs425.mumsched.web.usermgmt.entity.User;
import java.util.List;
import javassist.bytecode.analysis.ControlFlow.Block;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author bikesh
 */
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sectionId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany
    private List<User> users;

    @OneToOne
    @JoinColumn(name = "block_id")
    private Block block;

    @Column(name = "room_no")
    private String roomNo;
    
    @Column(name = "is_active")
    private boolean isActive;

}
