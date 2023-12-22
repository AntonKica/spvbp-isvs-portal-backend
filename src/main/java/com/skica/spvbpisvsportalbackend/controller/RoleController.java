package com.skica.spvbpisvsportalbackend.controller;

import com.skica.spvbpisvsportalbackend.entity.Human;
import com.skica.spvbpisvsportalbackend.entity.Role;
import com.skica.spvbpisvsportalbackend.generic.GenericController;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController extends GenericController<Role> {
    public RoleController() {
        super(Role.class);
    }

    @PostMapping(value = "{roleId}/clear")
    ResponseEntity<String> bindRole(@PathVariable long roleId) {
        Session session = sessionFactory.openSession();
        var role = session.get(Role.class, roleId);
        if(role == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No role with id" + roleId);
        }

        Transaction tx = session.beginTransaction();
        role.humans.forEach(human -> {
            human.roles.remove(role);
            session.merge(human);
        });
        tx.commit();
        session.close();

        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully unbound");
    }
}
