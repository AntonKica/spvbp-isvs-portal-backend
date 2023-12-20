package com.skica.spvbpisvsportalbackend.generic;

import jakarta.annotation.Nullable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class GenericEntity {
    @Nullable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
}
