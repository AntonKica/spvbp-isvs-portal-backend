package com.skica.spvbpisvsportalbackend.controller;

import com.skica.spvbpisvsportalbackend.entity.Role;
import com.skica.spvbpisvsportalbackend.generic.GenericController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController extends GenericController<Role> {
    public RoleController() {
        super(Role.class);
    }
}
