package com.example.studentmanager;

import java.util.Comparator;

public class SortStudentByYear implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return o1.getYearBirth().compareTo(o2.getYearBirth());
    }
}
