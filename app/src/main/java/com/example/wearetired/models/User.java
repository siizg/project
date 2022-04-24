package com.example.wearetired.models;

import java.util.ArrayList;
import java.util.Objects;

public class User {
    public String email;
    public String id;
    public String name;
    public long cups;
    //public ArrayList<String> invite;

    public User(String email, String id, long cups, String name) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.cups = cups;
    }
    public User() {
        //пусть будет
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return cups == user.cups && Objects.equals(email, user.email) && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, id, cups);
    }
}
