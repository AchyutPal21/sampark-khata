package com.samparkkhata.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "contacts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Contact {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;

    @Column(nullable = false)
    private String contactName;

    @Column(nullable = false)
    private String contactEmail;

    private LocalDate contactDoB;

    @Column(length = 500)
    private String contactAddress;

    @Column(length = 500)
    private String contactDescription;

    @Column(length = 1000)
    private String contactPictureUrl;

    @Builder.Default
    private Boolean favorite = false;

    @ManyToOne
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ContactNumber> contactNumbers = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<SocialLink> socialLinks = new ArrayList<>();

}
