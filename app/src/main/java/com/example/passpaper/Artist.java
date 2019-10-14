package com.example.passpaper;

public class Artist {
    int ID;
    String Name;

    public Artist(String name) {
        Name = name;
    }

    public Artist(int ID, String name) {
        this.ID = ID;
        Name = name;
    }

    public Artist() {
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
}
