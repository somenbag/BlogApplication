package com.myblog.blogapp.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="users",uniqueConstraints = {//(@UniqueConstraint is used to make username & email unique)
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String name;
    private String username;
    private String password;

    @ManyToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL)//when the user table is loaded roles table should also be loaded
   //joining two table using @JoinTable annotation based on id by using JoinColumn annotation.
    @JoinTable(name="user_roles",joinColumns = @JoinColumn(name="user_id" , referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id",referencedColumnName = "id")
    )
    private Set<Role> roles;
}
