package com.skica.spvbpisvsportalbackend;

import com.skica.spvbpisvsportalbackend.entity.Asset;
import com.skica.spvbpisvsportalbackend.entity.Human;
import com.skica.spvbpisvsportalbackend.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@SpringBootTest
class RoleTest {

    @Autowired
    EntityManagerFactory emf;
        @Test
        public void testOneToOneRelationship() {
            EntityManager em = emf.createEntityManager();

            Role role = new Role();
            role.name = "Test role";
            role.humans = Set.of(new Human());

            em.getTransaction().begin();

            em.persist(role);
            em.getTransaction().commit();
            em.clear();

            Role f = em.find(Role.class, role.id);
            System.out.println(f.humans);
            Asset a = em.find(Asset.class, 1);
            System.out.println(a.name);
            em.close();
            emf.close();
        }

}
