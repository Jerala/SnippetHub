package edu.snippethub.dao;

import edu.snippethub.config.HibernateUtil;
import edu.snippethub.entity.Snippet;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class SnippetDAOImpl implements SnippetDAO {

    public Snippet addSnippet(Snippet snippet) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(snippet);
            session.getTransaction().commit();
            return snippet;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }
}
