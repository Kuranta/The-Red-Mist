package com.springproject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usertable")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Size(min=2, message = "Incorrect name created")
    private String name;

    @Column
    @Size(min=2, message = "Incorrect surname created")
    private String surname;

    @Column
    @Size(min=2, message = "Incorrect email created")
    @Email
    private String email; //Unique parameter

    @Column
    @Size(min = 4, message = "Incorrect password created")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usertable_roles",
    joinColumns = @JoinColumn(name = "users_id"),
    inverseJoinColumns = @JoinColumn(name = "roles_id"))

    private Set<Role> roles = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
