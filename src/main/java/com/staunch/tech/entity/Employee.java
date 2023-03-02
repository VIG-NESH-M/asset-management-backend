package com.staunch.tech.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_employees")
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @Column(name = "name")
    private  String name;
    @Column(name = "email",unique = true,nullable = false)
    private  String email;
    @Column(name = "username",unique=true, nullable = false)
    private  String username;
    @Column(name = "phone",unique = true,nullable = false)
    private  long phoneNumber;
    @Column(name = "department", nullable = false)
    private  String department;
    @Column(name = "designation", nullable = false)
    private  String designation;
    
    @Column(name = "roles")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "roles_tab",joinColumns = @JoinColumn(name="uid"))
    private Set<String> roles;
    
    @Column(name = "address", nullable = false)
    private  String address;
    @Column(name = "location", nullable = false)
    private  String location;
    @Column(name = "usertype", nullable = false)
    private  String usertype;
    @Column(name = "resourceplanner", nullable = false)
    private  String resourceplanner;
    @Column(name = "password", nullable = false)
    private  String password;
   
    
    
    @Column(name ="created_by")
    private String createdBy;
    @Column(name ="created_time")
    private long createdTime;
    @Column(name ="updated_by")
    private String updatedBy;
    @Column(name ="updated_time")
    private long updatedTime;

}
