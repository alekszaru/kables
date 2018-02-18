package hibernate;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class SessionFactoryImpl{
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void closeSessionFactory() throws HibernateException {
        ourSessionFactory.close();
    }

}