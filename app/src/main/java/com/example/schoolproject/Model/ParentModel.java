package com.example.schoolproject.Model;

public class ParentModel
{
    String name,email,contact,id;

    public ParentModel(String name, String email, String contact, String id) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }

    public String getId() {
        return id;
    }
}
