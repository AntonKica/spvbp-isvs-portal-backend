package com.skica.spvbpisvsportalbackend;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
@Table(name="assets")
public class Asset {
    @Nullable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public String name;
}
