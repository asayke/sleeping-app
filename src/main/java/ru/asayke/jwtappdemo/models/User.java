package ru.asayke.jwtappdemo.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(name = "username")
    @NotEmpty(message = "Username should not be empty")
    @NotNull
    @Size(min = 4, max = 16, message = "Name should be between 4 and 16 characters")
    private String username;

    @Column(name = "full_name")
    @NotNull
    @NotEmpty(message = "Fullname should not be empty")
    private String fullName;

    @Column(name = "gender")
    @NotNull
    @NotEmpty(message = "Gender should not be empty")
    private String gender;

    @Column(name = "email")
    @Email
    @NotNull
    @NotEmpty(message = "Email should not be empty")
    private String email;

    @Column(name = "password")
    @NotEmpty(message = "Password should not be empty")
    @NotNull
    private String password;

    @Column(name = "color")
    private Integer color = 0;

    @Column(name = "image")
    private Integer image = 0;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;
}