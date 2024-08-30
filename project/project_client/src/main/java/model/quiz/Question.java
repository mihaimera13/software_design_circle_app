package model.quiz;

import java.util.ArrayList;
import java.util.Collections;

public class Question {

    private String text;
    private Difficulty difficulty;
    private ArrayList<String> options;
    private String imageFile;

    public Question(String text, Difficulty difficulty, ArrayList<String> options, String imageFile){
        this.text = text;
        this.difficulty = difficulty;
        this.options = options;
        this.imageFile = imageFile;
    }

    public Question(){

    }

    public String getText() {
        return text;
    }


    public Difficulty getDifficulty() {
        return difficulty;
    }


    public ArrayList<String> getOptions() {
        return options;
    }


    public String getImageFile() {
        return imageFile;
    }


    public int randomizeOptions(){
        String correct_opt = options.get(0);
        Collections.shuffle(options);
        return options.indexOf(correct_opt);
    }

    @Override
    public String toString(){
        return this.text + "\n"
                + "A." + this.options.get(0) + "\n"
                + "B." + this.options.get(1) + "\n"
                + "C." + this.options.get(2) + "\n"
                + "D." + this.options.get(3) + "\n";
    }
}
