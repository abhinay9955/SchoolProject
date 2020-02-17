package com.example.schoolproject.Model;

public class SheduleModel
{
    String subject,period,classG;

    public String getSubject() {
        return subject;
    }

    public String getPeriod() {
        return period;
    }

    public String getClassG() {
        return classG;
    }

    public SheduleModel(String subject, String period, String classG) {
        this.subject = subject;
        this.period = period;
        this.classG = classG;
    }

    public SheduleModel() {
    }
}
