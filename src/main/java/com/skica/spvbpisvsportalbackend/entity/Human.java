package com.skica.spvbpisvsportalbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.skica.spvbpisvsportalbackend.generic.GenericEntity;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "human")
public class Human extends GenericEntity {

    @Column(nullable = false)
    public String firstName;
    @Column(nullable = false)
    public String lastName;

    @JsonIgnoreProperties("humans")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "human_role",
            joinColumns = @JoinColumn(name = "humanId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    public Set<Role> roles;
}
