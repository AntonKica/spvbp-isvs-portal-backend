package com.skica.spvbpisvsportalbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.skica.spvbpisvsportalbackend.generic.GenericEntity;
import jakarta.persistence.*;

@Entity
@Table(name="businessProcess")
public class BusinessProcess extends GenericEntity {
    @Column(nullable = false)
    public String name;
    @Column(nullable = false)
    public String goals;
    @Column(nullable = false)
    public String information;

    @JsonIgnoreProperties("businessProcesses")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "businessProcessId")
    public Human contact;
}
