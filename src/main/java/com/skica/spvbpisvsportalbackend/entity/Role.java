package com.skica.spvbpisvsportalbackend.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.skica.spvbpisvsportalbackend.generic.GenericEntity;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="role")
public class Role extends GenericEntity {
    @Column(nullable = false)
    public String name;

    @JsonIgnoreProperties("roles")
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    public Set<Human> humans;
}
