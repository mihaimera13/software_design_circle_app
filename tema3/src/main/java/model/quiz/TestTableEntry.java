package model.quiz;

import java.sql.Timestamp;

public class TestTableEntry {
    private int index;

    private String name;
    private String surname;
    private int points;
    private Timestamp timestamp;

    public TestTableEntry(int index, String name, String surname, int points,Timestamp timestamp){
        this.index = index;
        this.name = name;
        this.surname = surname;
        this.points = points;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getPoints() {
        return points;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public int getIndex() {
        return index;
    }
}
