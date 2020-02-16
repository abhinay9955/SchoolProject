package com.example.schoolproject.Model;

public class TutorAppointmentModel
{
    String parentname,parentcontact,TofAppointment;

    public TutorAppointmentModel(String parentname, String parentcontact, String tofAppointment) {
        this.parentname = parentname;
        this.parentcontact = parentcontact;
        TofAppointment = tofAppointment;
    }

    public String getParentname() {
        return parentname;
    }

    public String getParentcontact() {
        return parentcontact;
    }

    public String getTofAppointment() {
        return TofAppointment;
    }
}
