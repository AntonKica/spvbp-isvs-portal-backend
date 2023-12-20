package com.skica.spvbpisvsportalbackend.entity;

import com.skica.spvbpisvsportalbackend.generic.GenericEntity;
import jakarta.persistence.*;

@Entity
@Table(name="asset")
public class Asset extends GenericEntity {
    @Column(nullable = false)
    public String name;
}
