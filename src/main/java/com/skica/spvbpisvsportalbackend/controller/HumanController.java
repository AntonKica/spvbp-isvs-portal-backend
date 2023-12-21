package com.skica.spvbpisvsportalbackend.controller;

import com.skica.spvbpisvsportalbackend.entity.Human;
import com.skica.spvbpisvsportalbackend.entity.Role;
import com.skica.spvbpisvsportalbackend.generic.GenericController;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("human")
public class HumanController extends GenericController<Human> {
    public HumanController() {
        super(Human.class);
    }

    @PostMapping(value = "{humanId}/bind/role/{roleId}")
    ResponseEntity<String> bindRole(@PathVariable long humanId, @PathVariable long roleId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        var human = session.get(Human.class, humanId);
        if(human == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No human with id" + humanId);
        }

        var role = session.get(Role.class, roleId);
        if(role == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No role with id" + roleId);
        }
        human.roles.add(role);
        tx.commit();
        session.close();

        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully bound");
    }
    @PostMapping(value = "{humanId}/unbind/role/{roleId}")
    ResponseEntity<String> unbindRole(@PathVariable long humanId, @PathVariable long roleId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        var human = session.get(Human.class, humanId);
        if(human == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No human with id" + humanId);
        }
        Optional<Role> removeRole = human.roles.stream().filter(role -> role.id == roleId).findAny();
        if(removeRole.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No role with id" + roleId);
        }
        human.roles.remove(removeRole.get());
        tx.commit();
        session.close();

        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully unbound");
    }
}
