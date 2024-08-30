package model.quiz;

public enum Difficulty {
    EASY (3),
    MEDIUM (5),
    HARD (7);

    private final int value;

    Difficulty(int value){
        this.value = value;
    }

    public int value(){
        return value;
    }
}
