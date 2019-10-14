package com.example.passpaper;

import android.graphics.Bitmap;

public class Photograph {

    int ID;
    String Name;
    int AID;
    String Category;
    Bitmap image;

    public Photograph(int ID, String name, int AID, String category, Bitmap image) {
        this.ID = ID;
        Name = name;
        this.AID = AID;
        Category = category;
        this.image = image;
    }

    public Photograph(String name, int AID, String category, Bitmap image) {
        Name = name;
        this.AID = AID;
        Category = category;
        this.image = image;
    }

    public Photograph(int ID, Bitmap image) {
        this.ID = ID;
        this.image = image;
    }

    public Photograph() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAID() {
        return AID;
    }

    public void setAID(int AID) {
        this.AID = AID;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
