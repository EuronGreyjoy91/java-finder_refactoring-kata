package model;

public class PersonsAgeComparison {
    private Person youngestPerson;
    private Person oldestPerson;
    private long millisBetweenOldestAndYoungest;

    public PersonsAgeComparison() {

    }

    public PersonsAgeComparison(Person youngestPerson, Person oldestPerson) {
        this.youngestPerson = youngestPerson;
        this.oldestPerson = oldestPerson;
        setMillisBetweenOldestAndYoungest();
    }

    public Person getYoungestPerson() {
        return youngestPerson;
    }

    public void setYoungestPerson(Person youngestPerson) {
        this.youngestPerson = youngestPerson;
    }

    public Person getOldestPerson() {
        return oldestPerson;
    }

    public void setOldestPerson(Person oldestPerson) {
        this.oldestPerson = oldestPerson;
    }

    public long getMillisBetweenOldestAndYoungest() {
        return millisBetweenOldestAndYoungest;
    }

    private void setMillisBetweenOldestAndYoungest() {
        this.millisBetweenOldestAndYoungest = this.oldestPerson.getBirthDate().getTime() - this.youngestPerson.getBirthDate().getTime();
    }
}
