package com.example.swat_riders.Model;

public class ModelClass {
    private int image;
    private String textview;
    private String textview1;
    private String textview2;
    private String textview3;

    public ModelClass(int image, String textview, String textview1, String textview2, String textview3){

        this.image=image;
        this.textview = textview;
        this.textview1=textview1;
        this.textview2=textview2;
        this.textview3=textview3;

    }
    public int getImage() {
        return image;
    }

    public String getTextview() {
        return textview;
    }

    public String getTextview1() {
        return textview1;
    }

    public String getTextview2() {
        return textview2;
    }

    public String getTextview3() {
        return textview3;
    }



}

