package com.example.schoolproject.Model;

public class ParentAppointmentModel {

    String name,tutorid,id,tutorContact;

    public ParentAppointmentModel(String name, String tutorid, String id, String tutorContact) {
        this.name = name;
        this.tutorid = tutorid;
        this.id = id;
        this.tutorContact=tutorContact;
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

    public String getTutorContact() {
        return tutorContact;
    }
}
