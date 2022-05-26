package com.example.wearetired.firebaseModels;

import com.example.wearetired.models.User;

import java.util.Objects;

public class FBUser {
    public String name;
    public String id;
    public String status;

    public FBUser() {
        //
    }

    public FBUser(String name, String id, String status) {
        this.name = name;
        this.status = status;
        this.id = id;
    }

    public FBUser(User user) {
        this.name = user.name;
        this.id = user.id;
        this.status = user.status;
    }

    @Override
    public String toString() {
        return "FBUser{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FBUser fbUser = (FBUser) o;
        return Objects.equals(name, fbUser.name) && Objects.equals(id, fbUser.id) && Objects.equals(status, fbUser.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, status);
    }
}
