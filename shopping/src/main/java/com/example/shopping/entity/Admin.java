package com.example.shopping.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "admin")
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String imange;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "admin_roles",
            joinColumns = @JoinColumn(name = "admin_admin_id", referencedColumnName = "admin_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_role_id", referencedColumnName = "role_id"))
    private List<Role> roles = new ArrayList<>();

}