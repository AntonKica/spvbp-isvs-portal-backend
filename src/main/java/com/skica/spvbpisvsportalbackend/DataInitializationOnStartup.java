package com.skica.spvbpisvsportalbackend;

import com.skica.spvbpisvsportalbackend.entity.Role;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


/**
 * Initializes default role (if not present) on startup.
 */
@Component
public class DataInitializationOnStartup implements ApplicationListener<ApplicationReadyEvent> {
    /**
     * ISO: Information Security Officer
     */
    private static final String ISO_NAME = "ISO";
    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Role> cq = cb.createQuery(Role.class);
        Root<Role> root = cq.from(Role.class);
        cq.select(root).where(cb.equal(root.get("name"), ISO_NAME));

        try {
            session.createQuery(cq).getSingleResult();
        }
        catch (NoResultException ex) {
            Transaction tx = session.beginTransaction();
            Role isoRole = new Role();
            isoRole.name = ISO_NAME;
            session.persist(isoRole);
            tx.commit();
        }

        session.close();
    }
}
