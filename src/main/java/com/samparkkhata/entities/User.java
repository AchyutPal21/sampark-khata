package com.samparkkhata.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User implements UserDetails {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String userFirstName;

    @Column(nullable = false)
    private String userLastName;

    @Column(unique = true, nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String userPassword;

    @Column(length = 1000)
    private String userAbout;

    @Column(length = 1000)
    private String userProfilePictureUrl;

    @Column(unique = true, nullable = false)
    private String userPhoneNumber;

    @Builder.Default
    private Boolean userEnabled = true;

    @Builder.Default
    private Boolean userEmailVerified = false;

    @Builder.Default
    private Boolean userPhoneVerified = false;

    // User registered vai
    @Builder.Default
    @Enumerated(value = EnumType.STRING)
    private Providers userProvider = Providers.SELF;
    private String userProviderId;

    // User Contact Details
    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();

    // Storing the authentication roles
    @Builder.Default
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> userRoleList = new ArrayList<>();

    // Below methods are UserDetails interface implementations for spring security
    // This getAuthorities() method is used to give role to the users
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // From the list of userRoleList[USER, ADMIN]
        // List of SimpleGrantedAuthority[roles[{USER,ADMIN}]
        List<SimpleGrantedAuthority> userRoles = this.userRoleList.stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole))
                .collect(Collectors.toList());
        return userRoles;
    }

    @Override
    public String getPassword() {
        return this.userPassword;
    }

    // We consider userEmail as username
    @Override
    public String getUsername() {
        return this.userEmail;
    }

    // later we can use a field in db to maintain account expiry
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // later we can use a field in db to maintain account expiry
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
        return this.userEnabled;
    }

}
