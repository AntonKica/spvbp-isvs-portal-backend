package com.skica.spvbpisvsportalbackend.generic;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

public abstract class GenericController<Entity extends GenericEntity> {
    @Autowired
    protected SessionFactory sessionFactory;

    private final Class<Entity> entityClass;
    public GenericController(Class<Entity> entityClass) {
        this.entityClass = entityClass;
    }


    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> post(@RequestBody Entity t) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(t);
        tx.commit();
        session.close();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(t.id)
                .toUri();

        return ResponseEntity.created(location).body("Succesfully created");
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Entity> get(@PathVariable long id) {
        var session = sessionFactory.openSession();
        var entity = session.get(entityClass, id);
        session.close();
        if(entity != null)
            return ResponseEntity.ok(entity);
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Entity>> list() {
        var session = sessionFactory.openSession();
        var query = session.getCriteriaBuilder().createQuery(entityClass);
        query.select(query.from(entityClass));

        List<Entity> res = session.createQuery(query).getResultList();
        session.close();
        return ResponseEntity.ok().body(res);
    }
}





