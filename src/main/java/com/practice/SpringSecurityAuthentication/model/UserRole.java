package com.practice.SpringSecurityAuthentication.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_generator")
    @SequenceGenerator(name="role_generator", sequenceName = "role_seq",initialValue = 103, allocationSize=50)
    private int id;
    @Enumerated(EnumType.STRING)
    private RoleType role;
    @OneToMany(mappedBy = "userRole")
    private List<User> user;

    public UserRole(){

    }
    public UserRole(int id){
        this.id = id;

    }
    public int getId() {
        return id;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public void setId(int id) {
        this.id = id;
    }
}
