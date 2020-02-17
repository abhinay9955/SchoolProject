package com.example.schoolproject.Model;

public class TutorModel
{
    String email,contact,name,tutorid,id;

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }

    public String getName() {
        return name;
    }

    public String getTutorid() {
        return tutorid;
    }

    public String getId() {
        return id;
    }

    public TutorModel(String email, String contact, String name, String tutorid, String id) {
        this.email = email;
        this.contact = contact;
        this.name = name;
        this.tutorid = tutorid;
        this.id=id;
    }
}
