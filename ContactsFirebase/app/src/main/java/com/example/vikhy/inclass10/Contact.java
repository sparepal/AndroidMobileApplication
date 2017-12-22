package com.example.vikhy.inclass10;

import java.io.Serializable;

/**
 * Created by vikhy on 9/11/2017.
 */

public class Contact implements Serializable {
    String name;
    String email;
    int iid;
    String department;
    int mood;

    public Contact(String name, String email, int iid, String department, int mood) {
        this.name = name;
        this.email = email;
        this.iid=iid;
        this.department = department;
        this.mood = mood;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +

                ", department='" + department + '\'' +
                ", mood=" + mood +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (mood != contact.mood) return false;
        if (name != null ? !name.equals(contact.name) : contact.name != null) return false;
        if (email != null ? !email.equals(contact.email) : contact.email != null) return false;

        return department != null ? department.equals(contact.department) : contact.department == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + mood;
        return result;
    }
}
