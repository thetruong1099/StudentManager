package com.example.studentmanager;

import java.util.Comparator;

public class SortStudentByPhone implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getNumberPhone().compareTo(o2.getNumberPhone());
    }
}
