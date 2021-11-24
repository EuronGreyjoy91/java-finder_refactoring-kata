package service;

import enumerator.FinderType;
import model.Person;
import model.PersonsAgeComparison;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Finder {
    private final Integer MINIMUM_SIZE_OF_LIST_FOR_PROCESS = 1;

    private final List<Person> persons;

    public Finder(List<Person> persons) {
        this.persons = persons;
    }

    public PersonsAgeComparison findByFinderType(FinderType finderType) {
        List<PersonsAgeComparison> personsAgeComparisons = new ArrayList<>();

        for (int i = 0; i < persons.size() - 1; i++) {
            for (int j = i + 1; j < persons.size(); j++)
                personsAgeComparisons.add(getAgeComparisonBetweenPersons(persons.get(i), persons.get(j)));
        }

        return getResult(personsAgeComparisons, finderType);
    }

    private PersonsAgeComparison getAgeComparisonBetweenPersons(Person firstPerson, Person secondPerson) {
        Person youngest;
        Person oldest;

        if (firstPerson.isYoungestThan(secondPerson)) {
            youngest = firstPerson;
            oldest = secondPerson;
        } else {
            youngest = secondPerson;
            oldest = firstPerson;
        }

        return new PersonsAgeComparison(youngest, oldest);
    }

    private PersonsAgeComparison getResult(List<PersonsAgeComparison> personsAgeComparisons, FinderType finderType) {
        if (!listHasMinimumSizeToProcess(personsAgeComparisons))
            return new PersonsAgeComparison();

        if (FinderType.CLOSEST.equals(finderType))
            return getMinimumAmplitude(personsAgeComparisons);

        return getMaximumAmplitude(personsAgeComparisons);
    }

    private Boolean listHasMinimumSizeToProcess(List<PersonsAgeComparison> personsAgeComparisons) {
        return personsAgeComparisons.size() >= MINIMUM_SIZE_OF_LIST_FOR_PROCESS;
    }

    private PersonsAgeComparison getMinimumAmplitude(List<PersonsAgeComparison> personsAgeComparisons){
        return Collections.min(
                personsAgeComparisons,
                Comparator.comparing(PersonsAgeComparison::getMillisBetweenOldestAndYoungest)
        );
    }

    private PersonsAgeComparison getMaximumAmplitude(List<PersonsAgeComparison> personsAgeComparisons){
        return Collections.max(
                personsAgeComparisons,
                Comparator.comparing(PersonsAgeComparison::getMillisBetweenOldestAndYoungest)
        );
    }
}
