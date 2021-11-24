package model;

public class PersonsComparison {
    private Person youngestPerson;
    private Person oldestPerson;
    private long millisBetweenOldestAndYoungest;

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

    public void setMillisBetweenOldestAndYoungest(long millisBetweenOldestAndYoungest) {
        this.millisBetweenOldestAndYoungest = millisBetweenOldestAndYoungest;
    }
}
