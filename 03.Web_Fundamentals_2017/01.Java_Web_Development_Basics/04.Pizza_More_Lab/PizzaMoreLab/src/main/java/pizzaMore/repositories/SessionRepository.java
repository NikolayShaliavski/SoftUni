package pizzaMore.repositories;

import pizzaMore.models.sessions.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class SessionRepository {

    public long createSession(Session session) {
        long id;

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("pizzaMore");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(session);
        id = session.getId();
        em.getTransaction().commit();
        em.close();
        emf.close();

        return id;
    }

    public Session findById(long id) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("pizzaMore");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("SELECT s FROM Session AS s " +
                "WHERE s.id = :id");
        query.setParameter("id", id);

        Session session = null;
        if (query.getResultList().size() != 0) {
            session = (Session) query.getResultList().get(0);
        }
        em.getTransaction().commit();
        em.close();
        emf.close();

        return session;
    }

    public void delete(long id) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("pizzaMore");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Query deleteSessionDataQuery = em.createQuery("DELETE FROM SessionData " +
                "WHERE session.id = :session_id");
        deleteSessionDataQuery.setParameter("session_id", id);
        deleteSessionDataQuery.executeUpdate();
        Query query = em.createQuery("DELETE FROM Session " +
                "WHERE id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
