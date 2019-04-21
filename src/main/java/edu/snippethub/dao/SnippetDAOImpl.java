package edu.snippethub.dao;

import edu.snippethub.config.HibernateUtil;
import edu.snippethub.entity.Snippet;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
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

    public Snippet getSnippetById(Long id) {
        Session session = null;
        Snippet snippet = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Snippet.class);
            criteria.add(Restrictions.eq("snippetId", id));
            snippet = (Snippet) criteria.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return snippet;
    }

    public void deleteSnippet(Snippet snippet) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(snippet);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Cannot delete customer.");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }
}
