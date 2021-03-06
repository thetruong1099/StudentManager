package com.example.studentmanager;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private final List<Student> studentList = new ArrayList<>();
    private EditText editTextName, editTextYearBirth, editTextNumberPhone, editTextSpecialized, editTextTypeEducation;
    private Button buttonAdd;
    private Button buttonShowListStudent;
    private EditText edtFindIDStudent;
    private Button buttonFindID, buttonEdit;
    private Button buttonRemove;
    private Button buttonSortByName, buttonSortByYear, buttonSortByPhone;
    private Button buttonFilterByColleges, buttonFilterByUniversity;
    private EditText editTextSearch;
    private Button buttonSearch;
    private int idStudent = 1;
    private int idStudentEdit = 0;
    private Boolean checkStatusButtonEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapping();

        buttonShowListStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showListStudent();
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent();
            }
        });

        buttonFindID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextFileForEdit();
            }
        });

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editStudent();
            }
        });

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeStudent();
            }
        });

        buttonSortByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortByName();
            }
        });

        buttonSortByYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortByYear();
            }
        });

        buttonSortByPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortByPhone();
            }
        });

        buttonFilterByColleges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterBy("cao ?????ng");
            }
        });

        buttonFilterByUniversity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterBy("?????i h???c");
            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchStudent();
            }
        });
    }

    private void searchStudent() {
        Set<Student> studentSet = new HashSet<>();
        String keyword = VietnameseCharacterUtils.removeAccent(editTextSearch.getText().toString().trim().toLowerCase());
        if (keyword.length() > 0) {
            for (Student student : studentList) {
                if (VietnameseCharacterUtils.removeAccent(student.getName().toLowerCase()).contains(keyword)
                        || student.getYearBirth().toLowerCase().contains(keyword)
                        || student.getNumberPhone().toLowerCase().contains(keyword)
                        || VietnameseCharacterUtils.removeAccent(student.getSpecialized()).toLowerCase().contains(keyword)
                        || VietnameseCharacterUtils.removeAccent(student.getType()).toLowerCase().contains(keyword)) {
                    studentSet.add(student);
                }
            }
            Log.d("ShowStudent", "searchStudent: Name  Year  Phone  Specialized  Type Education");
            for (Student student : studentSet) {
                Log.d("ShowStudent", "searchStudent: " + student.getName() + " " + student.getYearBirth() + " " +
                        student.getNumberPhone() + " " + student.getSpecialized() + " " + student.getType());
            }
            editTextSearch.setText("");
        } else Toast.makeText(this, "nhap key word", Toast.LENGTH_SHORT).show();
    }

    private void filterBy(String type) {
        Log.d("ShowStudent", "Student: Name  Year  Phone  Specialized  Type Education");
        for (Student student : studentList) {
            if (student.getType().toLowerCase().equals(type))
                Log.d("ShowStudent", "Student " + student.getId() + " " + student.getName() + " " +
                        student.getYearBirth() + " " + student.getNumberPhone() + " " + student.getSpecialized() + " " + student.getType());
        }
    }

    private void sortByPhone() {
        Collections.sort(studentList, new SortStudentByPhone());
        setIdAfterSort();
        Log.d("ShowStudent", "S???p x???p theo s??? ??i???n tho???i");
        showListStudent();
    }

    private void sortByYear() {
        Collections.sort(studentList, new SortStudentByYear());
        setIdAfterSort();
        Log.d("ShowStudent", "S???p x???p theo n??m");
        showListStudent();
    }

    private void sortByName() {
        Collections.sort(studentList, new SortStudentByName());
        setIdAfterSort();
        Log.d("ShowStudent", "S???p x???p theo t??n");
        showListStudent();
    }

    private void setIdAfterSort() {
        for (int i = 0; i < studentList.size(); i++) {
            studentList.get(i).setId(i + 1);
        }
    }

    private void removeStudent() {
        idStudentEdit = Integer.parseInt(edtFindIDStudent.getText().toString().trim()) - 1;
        studentList.remove(idStudentEdit);
        edtFindIDStudent.setText("");
        for (int i = idStudentEdit; i<studentList.size();i++){
            studentList.get(i).setId(i+1);
            idStudent=i+2;
        }
        Log.d("ShowStudent", "???? X??a");
    }

    private void editStudent() {
        String nameStudent = editTextName.getText().toString().trim();
        String yearBirthStudent = editTextYearBirth.getText().toString().trim();
        String numberPhone = editTextNumberPhone.getText().toString().trim();
        String specializedStudent = editTextSpecialized.getText().toString().trim();
        String typeEducation = editTextTypeEducation.getText().toString().trim();

        Boolean checkPhone = true;
        if (nameStudent.length() == 0 || yearBirthStudent.length() == 0 || numberPhone.length() == 0 || specializedStudent.length() == 0) {
            Toast.makeText(this, "Kh??ng ????? tr???ng c??c tr?????ng", Toast.LENGTH_SHORT).show();
        } else {
            for (int i=0; i<studentList.size();i++){
                if (i!=idStudentEdit&&numberPhone.equals(studentList.get(i).getNumberPhone())){
                        checkPhone = false;
                        break;
                }
            }
            if (checkPhone) {
                studentList.get(idStudentEdit).setName(nameStudent);
                studentList.get(idStudentEdit).setYearBirth(yearBirthStudent);
                studentList.get(idStudentEdit).setNumberPhone(numberPhone);
                studentList.get(idStudentEdit).setSpecialized(specializedStudent);
                studentList.get(idStudentEdit).setType(typeEducation);
                Log.d("ShowStudent", "EditStudentNew: Name  Year  Phone  Specialized  Type Education");
                Log.d("ShowStudent", "EditStudentNew: " + studentList.get(idStudentEdit).getName() + " " + studentList.get(idStudentEdit).getYearBirth() + " " +
                        studentList.get(idStudentEdit).getNumberPhone() + " " + studentList.get(idStudentEdit).getSpecialized() + " " + studentList.get(idStudentEdit).getType());
                editTextName.setText("");
                editTextYearBirth.setText("");
                editTextNumberPhone.setText("");
                editTextSpecialized.setText("");
                editTextTypeEducation.setText("");
            } else {
                Toast.makeText(this, "trung sdt", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setTextFileForEdit() {
        idStudentEdit = Integer.parseInt(edtFindIDStudent.getText().toString().trim()) - 1;
        try {
            Student student = studentList.get(idStudentEdit);
            editTextName.setText(student.getName());
            editTextYearBirth.setText(student.getYearBirth());
            editTextNumberPhone.setText(student.getNumberPhone());
            editTextSpecialized.setText(student.getSpecialized());
            editTextTypeEducation.setText(student.getType());
            Student studentold = studentList.get(idStudentEdit);
            Log.d("ShowStudent", "EditStudentOld: Name  Year  Phone  Specialized  Type Education");
            Log.d("ShowStudent", "EditStudentOld: " + studentold.getName() + " " + studentold.getYearBirth() + " " +
                    studentold.getNumberPhone() + " " + studentold.getSpecialized() + " " + student.getType());

        } catch (Exception e) {
            Toast.makeText(this, "nhap id", Toast.LENGTH_SHORT).show();
        }

    }

    private void showListStudent() {
        Log.d("ShowStudent", "Student: Name  Year  Phone  Specialized  Type Education");
        for (Student student : studentList) {
            Log.d("ShowStudent", "Student " + student.getId() + " " + student.getName() + " " + student.getYearBirth() + " " +
                    student.getNumberPhone() + " " + student.getSpecialized() + " " + student.getType());
        }
    }

    private void addStudent() {
        String nameStudent = editTextName.getText().toString().trim();
        String yearBirthStudent = editTextYearBirth.getText().toString().trim();
        String numberPhone = editTextNumberPhone.getText().toString().trim();
        String specializedStudent = editTextSpecialized.getText().toString().trim();
        String typeEducation = editTextTypeEducation.getText().toString().trim();

        Boolean checkPhone = true;
        if (nameStudent.length() == 0 || yearBirthStudent.length() == 0 || numberPhone.length() == 0 || specializedStudent.length() == 0) {
            Toast.makeText(this, "Kh??ng ????? tr???ng c??c tr?????ng", Toast.LENGTH_SHORT).show();
        } else {
            for (Student student : studentList) {
                if (numberPhone.equals(student.getNumberPhone())) {
                    checkPhone = false;
                    break;
                }
            }
            if (checkPhone) {
                Student student = new Student(idStudent, nameStudent, yearBirthStudent, numberPhone, specializedStudent, typeEducation);
                idStudent++;
                studentList.add(student);
                Log.d("ShowStudent", "addStudent: Name  Year  Phone  Specialized  Type Education");
                Log.d("ShowStudent", "addStudent: " + student.getName() + " " + student.getYearBirth() + " " +
                        student.getNumberPhone() + " " + student.getSpecialized() + " " + student.getType());
                editTextName.setText("");
                editTextYearBirth.setText("");
                editTextNumberPhone.setText("");
                editTextSpecialized.setText("");
                editTextTypeEducation.setText("");
            } else {
                Toast.makeText(this, "trung sdt", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void mapping() {
        editTextName = findViewById(R.id.editTextName);
        editTextYearBirth = findViewById(R.id.editTextYearBirth);
        editTextNumberPhone = findViewById(R.id.editTextNumberPhone);
        editTextSpecialized = findViewById(R.id.editTextSpecialized);
        editTextTypeEducation = findViewById(R.id.editTextTypeEducation);

        buttonAdd = findViewById(R.id.buttonAdd);

        buttonShowListStudent = findViewById(R.id.buttonShowListStudent);

        edtFindIDStudent = findViewById(R.id.edtFindIDStudent);

        buttonFindID = findViewById(R.id.buttonFindID);
        buttonEdit = findViewById(R.id.buttonEdit);

        buttonRemove = findViewById(R.id.buttonRemove);

        buttonSortByName = findViewById(R.id.buttonSortByName);
        buttonSortByYear = findViewById(R.id.buttonSortByYear);
        buttonSortByPhone = findViewById(R.id.buttonSortByPhone);

        buttonFilterByColleges = findViewById(R.id.buttonFilterByColleges);
        buttonFilterByUniversity = findViewById(R.id.buttonFilterByUniversity);

        editTextSearch = findViewById(R.id.editTextSearch);
        buttonSearch = findViewById(R.id.buttonSearch);
    }
}