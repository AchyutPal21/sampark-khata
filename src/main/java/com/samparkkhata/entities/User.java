package com.samparkkhata.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class User {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String userName;

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
    private Boolean userEnabled = false;

    @Builder.Default
    private Boolean userEmailVerified = false;

    @Builder.Default
    private Boolean userPhoneVerified = false;

    // User registered vai
    @Builder.Default
    private Providers userProvider = Providers.SELF;
    private String userProviderId;

    // User Contact Details
    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();




}
