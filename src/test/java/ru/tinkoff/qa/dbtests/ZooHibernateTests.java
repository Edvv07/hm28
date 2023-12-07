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
     * В таблице public.animal ровно 10 записей
     */
    @Test
    public void countRowAnimal() {
        Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Animal", Long.class);
        long count = (long) query.getSingleResult();
        assertEquals(10, count);
    }
    /**
     * В таблицу public.animal нельзя добавить строку с индексом от 1 до 10 включительно
     */
    @Test
    public void insertIndexAnimal() {
        Animal animal = new Animal();
        animal.setId(1);
        animal.setAge(12);
        animal.setType(3);
        animal.setPlace(3);
        animal.setSex(2);
        animal.setName("вня");
        Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Animal", Long.class);
        var count = query.getSingleResult();
        assertEquals(11, count);
    }

    /**
     * В таблицу public.workman нельзя добавить строку с name = null
     */
    @Test
    public void insertNullToWorkman() {
        Workman workman = new Workman();
        workman.setAge(22);
        workman.setId(6);
        workman.setName(null);
        workman.setPosition(2);

        Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Workman WHERE name IS NULL", Long.class);
        Long result = query.getSingleResult();
        Query<Long> query1 = session.createQuery("SELECT COUNT(*) FROM Workman", Long.class);
        Long result1 = query1.getSingleResult();
        System.out.println(result + " " + workman.getName());
        assertEquals(5-result, result1);
    }

    /**
     * Если в таблицу public.places добавить еще одну строку, то в ней будет 6 строк
     */
    @Test
    public void insertPlacesCountRow() {
        Places place = new Places();
        place.setId(6);
        place.setRow(3);
        place.setPlace_num(1);
        place.setName("newName");
        Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Places WHERE id = 6", Long.class);
        Long result = query.getSingleResult();
        session.persist(place);
        assertEquals(6, result);

    }
    /**
     * В таблице public.zoo всего три записи с name 'Центральный', 'Северный', 'Западный'
     */
    @Test
    public void countRowZoo() {
        List<String> expectedNames = Arrays.asList("Центральный", "Северный", "Западный");
        Query<Long> query = session.createQuery("SELECT COUNT(name) FROM Zoo WHERE name IN :names", Long.class);
        query.setParameter("names", expectedNames);
        Long result = query.uniqueResult();
        assertEquals(3, result);
    }

}
