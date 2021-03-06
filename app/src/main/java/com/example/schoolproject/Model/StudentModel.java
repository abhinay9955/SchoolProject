package com.example.schoolproject.Model;

public class StudentModel {

    String name,id,roll,parent,contact,std;
    int grade,rank,tdp,twd;

    public StudentModel() {
    }


    public StudentModel(String name, String id, String roll, String contact, int grade, int rank, int tdp, int twd, String std, String parent) {
        this.name = name;
        this.id = id;
        this.roll = roll;
        this.contact = contact;
        this.grade = grade;
        this.rank = rank;
        this.tdp = tdp;
        this.twd = twd;
        this.std = std;
        this.parent=parent;
    }

    public String getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getRoll() {
        return roll;
    }

    public String getContact() {
        return contact;
    }

    public int getGrade() {
        return grade;
    }

    public int getRank() {
        return rank;
    }

    public int getTdp() {
        return tdp;
    }

    public int getTwd() {
        return twd;
    }

    public String getStd() {
        return std;
    }
}
