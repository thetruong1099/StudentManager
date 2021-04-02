package com.example.studentmanager;

public class Student {
    private int id;
    private String name;
    private String yearBirth;
    private String numberPhone;
    private String specialized;
    private String type;

    public Student() {
    }

    public Student(int id, String name, String yearBirth, String numberPhone, String specialized, String type) {
        this.id = id;
        this.name = name;
        this.yearBirth = yearBirth;
        this.numberPhone = numberPhone;
        this.specialized = specialized;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getYearBirth() {
        return yearBirth;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public String getSpecialized() {
        return specialized;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYearBirth(String yearBirth) {
        this.yearBirth = yearBirth;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public void setSpecialized(String specialized) {
        this.specialized = specialized;
    }

    public void setType(String type) {
        this.type = type;
    }
}
