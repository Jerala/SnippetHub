package edu.snippethub.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(Class.forName("edu.snippethub.entity.User"));
            configuration.addAnnotatedClass(Class.forName("edu.snippethub.entity.Snippet"));
            configuration.addAnnotatedClass(Class.forName("edu.snippethub.entity.Like"));
//            configuration.addAnnotatedClass(Class.forName("edu.snippethub.entity.Role"));
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }
}
