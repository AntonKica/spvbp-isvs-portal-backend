package com.skica.spvbpisvsportalbackend;

import jakarta.persistence.*;

@Entity
@Table(name="assets")
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public String name;
}
