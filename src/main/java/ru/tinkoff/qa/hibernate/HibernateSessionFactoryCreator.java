package ru.tinkoff.qa.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.tinkoff.qa.hibernate.tables.Animal;
import ru.tinkoff.qa.hibernate.tables.Places;
import ru.tinkoff.qa.hibernate.tables.Workman;
import ru.tinkoff.qa.hibernate.tables.Zoo;

public class HibernateSessionFactoryCreator {
    private static SessionFactory sessionFactory;
    public static SessionFactory createSessionFactory(){
        Configuration configuration = new Configuration().configure("/hibernate.cfg.xml")
                .addAnnotatedClass(Animal.class)
                .addAnnotatedClass(Workman.class)
                .addAnnotatedClass(Zoo.class)
                .addAnnotatedClass(Places.class);
        sessionFactory = configuration.buildSessionFactory();
        return sessionFactory;
    }
}
