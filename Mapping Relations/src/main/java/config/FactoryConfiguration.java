package config;

import entity.Laptop;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfigurationInstance;
    private final SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration cfg = new Configuration().configure();

        cfg.addAnnotatedClass(Student.class);
        cfg.addAnnotatedClass(Laptop.class);

        sessionFactory = cfg.buildSessionFactory();

    }

    public static FactoryConfiguration getInstance() {
        if (factoryConfigurationInstance == null) {
            synchronized (FactoryConfiguration.class) {
                if (factoryConfigurationInstance == null) {
                    factoryConfigurationInstance = new FactoryConfiguration();
                }
            }
        }
        return factoryConfigurationInstance;
    }

    public Session getSessionFactory() {
        return sessionFactory.openSession();
    }
}
