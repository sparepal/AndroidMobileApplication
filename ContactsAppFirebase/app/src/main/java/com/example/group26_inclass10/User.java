package com.example.group26_inclass10;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by smank on 11/13/2017.
 */

public class User {

    public String firstname, lastname, email, password;
    String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String,Object> result = new HashMap<>();
        result.put("email", email);
        result.put("firstname", firstname);
        result.put("lastname", lastname);
        result.put("password", password);

        return result;
    }
}
