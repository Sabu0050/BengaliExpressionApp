package com.sabutos.translation.model;

/**
 * Created by devil on 15-Nov-16.
 */

public class Translation {
    private int id;
    private String sentence;
    private String s_meaning;

    public Translation(int id, String sentence, String s_meaning) {
        this.id = id;
        this.sentence = sentence;
        this.s_meaning = s_meaning;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getS_meaning() {
        return s_meaning;
    }

    public void setS_meaning(String s_meaning) {
        this.s_meaning = s_meaning;
    }
}
