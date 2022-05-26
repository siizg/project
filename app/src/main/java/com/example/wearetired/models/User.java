package com.example.wearetired.models;

import java.util.ArrayList;
import java.util.Objects;

public class User {
    public String email;
    public String id;
    public String name;
    public long cups;
    public String invite;
    public String status;
    public String playWith;
    public String turn;

    public User(String email, String id, String name, long cups, String invite, String status, String playWith, String turn) {
        this.email = email;
        this.id = id;
        this.turn = turn;
        this.playWith = playWith;
        this.status = status;
        this.name = name;
        this.cups = cups;
        this.invite = invite;
    }

    public User() {
        //пусть будет
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", cups=" + cups +
                ", invite='" + invite + '\'' +
                ", status='" + status + '\'' +
                ", playWith='" + playWith + '\'' +
                ", turn='" + turn + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return cups == user.cups && Objects.equals(email, user.email) && Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(invite, user.invite) && Objects.equals(status, user.status) && Objects.equals(playWith, user.playWith) && Objects.equals(turn, user.turn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, id, name, cups, invite, status, playWith, turn);
    }
}
