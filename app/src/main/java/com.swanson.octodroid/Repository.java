package com.swanson.octodroid;

public class Repository {
    public String name;
    public String language;
    public Owner owner;

    public Repository(String name, String language, Owner owner) {
        this.name = name;
        this.language = language;
        this.owner = owner;
    }

    public String fullName() {
        return owner.username + "/" + name;
    }
}
