package ru.tinkoff.qa.dbtests;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.tinkoff.qa.hibernate.BeforeCreator;
import ru.tinkoff.qa.hibernate.HibernateSessionFactoryCreator;
import ru.tinkoff.qa.hibernate.tables.Animal;
import ru.tinkoff.qa.hibernate.tables.Places;
import ru.tinkoff.qa.hibernate.tables.Workman;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZooHibernateTests {
    private static Session session;

    @BeforeAll
    static void init() {
        BeforeCreator.createData();
        session = HibernateSessionFactoryCreator.createSessionFactory().openSession();
    }

    /**
     * .
     * * В таблице public.animal ровно 10 записей
     */
    @Test
    public void countRowAnimal() {
        final int countRow = 10;
        Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Animal", Long.class);
        long count = (long) query.getSingleResult();
        assertEquals(countRow, count);
    }

    /**
     * .
     * В таблицу public.animal нельзя добавить строку с индексом от 1 до 10 включительно
     */
    @Test
    public void insertIndexAnimal() {
        Long startCount = (Long) session.createQuery("select count(*) from Animal").uniqueResult();
        final int countRow = 10;
        for (int i = 1; i <= countRow; i++) {
            Animal checkId = session.get(Animal.class, i);
            if (checkId == null) {
                Animal animal = new Animal();
                animal.setName("animal");
                animal.setAge(3);
                animal.setType(1);
                animal.setSex(1);
                animal.setPlace(3);
                animal.setId(i);
                session.beginTransaction();
                session.persist(animal);
                session.getTransaction().commit();
            }
        }
        Long count = (Long) session.createQuery("select count(*) from Animal").uniqueResult();
        assertEquals(startCount, count);
    }

    /**
     * .
     * В таблицу public.workman нельзя добавить строку с name = null
     */
    @Test
    public void insertNullToWorkman() {
        Workman workman = new Workman();
        Long startCount = (Long) session.createQuery("select count(*) from Workman").uniqueResult();
        workman.setAge(22);
        workman.setName(null);
        workman.setPosition(2);
        if (workman.getName() != null) {
            session.beginTransaction();
            session.persist(workman);
            session.getTransaction().commit();
        }
        Long finalCount = (Long) session.createQuery("select count(*) from Workman").uniqueResult();
        assertEquals(startCount, finalCount);
    }

    /**
     * .
     * Если в таблицу public.places добавить еще одну строку, то в ней будет 6 строк
     */
    @Test
    public void insertPlacesCountRow() {
        final int startResult = 6;
        Places place = new Places();
        place.setId(7);
        place.setRow(3);
        place.setPlaceNum(12);
        place.setName("Name");
        session.beginTransaction();
        session.persist(place);
        session.getTransaction().commit();
        Long count = (Long) session.createQuery("select count(*) from Places").uniqueResult();
        assertEquals(startResult, count);
    }

    /**
     * .
     * В таблице public.zoo всего три записи с name 'Центральный', 'Северный', 'Западный'
     */
    @Test
    public void countRowZoo() {
        final int startResult = 3;
        List<String> expectedNames = Arrays.asList("Центральный", "Северный", "Западный");
        Query<Long> query = session.createQuery("SELECT COUNT(name) FROM Zoo WHERE name IN :names", Long.class);
        query.setParameter("names", expectedNames);
        Long result = query.uniqueResult();
        assertEquals(startResult, result);
    }

}
