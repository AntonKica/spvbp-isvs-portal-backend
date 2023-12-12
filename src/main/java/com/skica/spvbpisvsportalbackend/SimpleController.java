package com.skica.spvbpisvsportalbackend;


import org.apache.catalina.connector.Response;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class SimpleController implements InitializingBean {
    private static final SessionFactory sessionFactory;
    static {
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static Session getSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        var asset = new Asset();
        asset.name = "Some asset";

        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.persist(asset);
        tx.commit();
    }

    @PostMapping(value = "/asset", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity create(@RequestBody Asset asset) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.persist(asset);
        tx.commit();
        session.close();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(asset.id)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/asset/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Asset> getAsset(@PathVariable long id) {
        var session = getSession();
        var asset =  session.get(Asset.class, id);
        session.close();
        if(asset != null)
            return ResponseEntity.ok(asset);
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/asset/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity update(@PathVariable long id, @RequestBody Asset asset) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Asset old = session.get(Asset.class, id);
        if(old == null) {
            return ResponseEntity.notFound().build();
        }
        session.merge(asset);
        tx.commit();
        session.close();

        return ResponseEntity.ok(Response.SC_OK);
    }

    @GetMapping(value = "/list-assets", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Asset> getHello() {
        var session = getSession();
        var query = session.getCriteriaBuilder().createQuery(Asset.class);
        query.select(query.from(Asset.class));

        List<Asset> res = session.createQuery(query).getResultList();
        session.close();
        return res;
    }



}





