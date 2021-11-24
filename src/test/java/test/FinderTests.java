package test;

import enumerator.FinderType;
import model.Person;
import model.PersonsAgeComparison;
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
        List<Person> list = new ArrayList<>();
        Finder finder = new Finder(list);

        PersonsAgeComparison result = finder.findByFinderType(FinderType.CLOSEST);

        assertEquals(null, result.getYoungestPerson());
        assertEquals(null, result.getOldestPerson());
    }

    @Test
    public void Returns_Empty_Results_When_Given_One_Person() {
        List<Person> list = new ArrayList<>();
        list.add(sue);

        Finder finder = new Finder(list);

        PersonsAgeComparison result = finder.findByFinderType(FinderType.CLOSEST);

        assertEquals(null, result.getYoungestPerson());
        assertEquals(null, result.getOldestPerson());
    }

    @Test
    public void Returns_Closest_Two_For_Two_People() {
        List<Person> list = new ArrayList<>();
        list.add(sue);
        list.add(greg);
        Finder finder = new Finder(list);

        PersonsAgeComparison result = finder.findByFinderType(FinderType.CLOSEST);

        assertEquals(sue, result.getYoungestPerson());
        assertEquals(greg, result.getOldestPerson());
    }

    @Test
    public void Returns_Furthest_Two_For_Two_People() {
        List<Person> list = new ArrayList<>();
        list.add(mike);
        list.add(greg);

        Finder finder = new Finder(list);

        PersonsAgeComparison result = finder.findByFinderType(FinderType.FURTHEST);

        assertEquals(greg, result.getYoungestPerson());
        assertEquals(mike, result.getOldestPerson());
    }

    @Test
    public void Returns_Furthest_Two_For_Four_People() {
        List<Person> list = new ArrayList<>();
        list.add(sue);
        list.add(sarah);
        list.add(mike);
        list.add(greg);
        Finder finder = new Finder(list);

        PersonsAgeComparison result = finder.findByFinderType(FinderType.FURTHEST);

        assertEquals(sue, result.getYoungestPerson());
        assertEquals(sarah, result.getOldestPerson());
    }

    @Test
    public void Returns_Closest_Two_For_Four_People() {
        List<Person> list = new ArrayList<>();
        list.add(sue);
        list.add(sarah);
        list.add(mike);
        list.add(greg);

        Finder finder = new Finder(list);

        PersonsAgeComparison result = finder.findByFinderType(FinderType.CLOSEST);

        assertEquals(sue, result.getYoungestPerson());
        assertEquals(greg, result.getOldestPerson());
    }

}
