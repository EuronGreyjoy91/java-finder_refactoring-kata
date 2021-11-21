package test;

import enumerator.FT;
import model.F;
import model.Person;
import org.junit.Before;
import org.junit.Test;
import service.Finder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FinderTests {

    Person sue = new Person();
    Person greg = new Person();
    Person sarah = new Person();
    Person mike = new Person();

    @Before
    public void setup() {
        sue.setName("Sue");
        sue.setBirthDate(new Date(50, 0, 1));
        greg.setName("Greg");
        greg.setBirthDate(new Date(52, 5, 1));
        sarah.setName("Sarah");
        sarah.setBirthDate(new Date(82, 0, 1));
        mike.setName("Mike");
        mike.setBirthDate(new Date(79, 0, 1));
    }

    @Test
    public void Returns_Empty_Results_When_Given_Empty_List() {
        List<Person> list = new ArrayList<Person>();
        Finder finder = new Finder(list);

        F result = finder.Find(FT.ONE);
        assertEquals(null, result.getPersonOne());

        assertEquals(null, result.getPersonTwo());
    }

    @Test
    public void Returns_Empty_Results_When_Given_One_Person() {
        List<Person> list = new ArrayList<Person>();
        list.add(sue);

        Finder finder = new Finder(list);

        F result = finder.Find(FT.ONE);

        assertEquals(null, result.getPersonOne());
        assertEquals(null, result.getPersonTwo());
    }

    @Test
    public void Returns_Closest_Two_For_Two_People() {
        List<Person> list = new ArrayList<Person>();
        list.add(sue);
        list.add(greg);
        Finder finder = new Finder(list);

        F result = finder.Find(FT.ONE);

        assertEquals(sue, result.getPersonOne());
        assertEquals(greg, result.getPersonTwo());
    }

    @Test
    public void Returns_Furthest_Two_For_Two_People() {
        List<Person> list = new ArrayList<Person>();
        list.add(mike);
        list.add(greg);

        Finder finder = new Finder(list);

        F result = finder.Find(FT.TWO);

        assertEquals(greg, result.getPersonOne());
        assertEquals(mike, result.getPersonTwo());
    }

    @Test
    public void Returns_Furthest_Two_For_Four_People() {
        List<Person> list = new ArrayList<Person>();
        list.add(sue);
        list.add(sarah);
        list.add(mike);
        list.add(greg);
        Finder finder = new Finder(list);

        F result = finder.Find(FT.TWO);

        assertEquals(sue, result.getPersonOne());
        assertEquals(sarah, result.getPersonTwo());
    }

    @Test
    public void Returns_Closest_Two_For_Four_People() {
        List<Person> list = new ArrayList<Person>();
        list.add(sue);
        list.add(sarah);
        list.add(mike);
        list.add(greg);

        Finder finder = new Finder(list);

        F result = finder.Find(FT.ONE);

        assertEquals(sue, result.getPersonOne());
        assertEquals(greg, result.getPersonTwo());
    }

}
