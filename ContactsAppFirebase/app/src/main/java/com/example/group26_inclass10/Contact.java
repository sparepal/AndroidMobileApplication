package com.example.group26_inclass10;


import java.io.Serializable;



public class Contact implements Serializable {
    String name;
    String email;
    int iId;
    String phone;
    String department;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (iId != contact.iId) return false;
        if (name != null ? !name.equals(contact.name) : contact.name != null) return false;
        if (email != null ? !email.equals(contact.email) : contact.email != null) return false;
        if (phone != null ? !phone.equals(contact.phone) : contact.phone != null) return false;
        return department != null ? department.equals(contact.department) : contact.department == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + iId;
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        return result;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", iId=" + iId +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getiId() {
        return iId;
    }

    public void setiId(int iId) {
        this.iId = iId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}