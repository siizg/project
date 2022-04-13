package com.example.wearetired.models;

import java.util.Objects;

public class User {
    public String email;
    public String id;
    public long cups;

    public User(String email, String id, long cups) {
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
        return Objects.hash(email, id, cups);
    }
}
