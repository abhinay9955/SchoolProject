package com.example.schoolproject.Model;

public class ParentAppointmentModel {

    String name,tutorid,id;

    public ParentAppointmentModel(String name, String tutorid, String id) {
        this.name = name;
        this.tutorid = tutorid;
        this.id = id;
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
}
