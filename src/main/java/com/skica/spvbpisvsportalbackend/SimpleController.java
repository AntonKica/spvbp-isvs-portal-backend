package com.skica.spvbpisvsportalbackend;


import org.apache.catalina.connector.Response;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/asset/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Asset getHello(@PathVariable long id) {
        var session = getSession();
        var asset =  session.get(Asset.class, id);
        session.close();
        return asset;
    }

    @GetMapping(value = "/asset", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Asset> getHello() {
        var session = getSession();
        var query = session.getCriteriaBuilder().createQuery(Asset.class);
        query.select(query.from(Asset.class));

        List<Asset> res = session.createQuery(query).getResultList();
        session.close();
        return res;
    }

    @PostMapping(value = "/asset", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity update(@RequestBody Asset asset) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Asset old = session.get(Asset.class, asset.id);
        if(old == null) {
            return ResponseEntity.status(Response.SC_NOT_FOUND).body("Not found");
        }
        session.merge(asset);
        tx.commit();
        session.close();

        return ResponseEntity.ok(Response.SC_OK);
    }

}





