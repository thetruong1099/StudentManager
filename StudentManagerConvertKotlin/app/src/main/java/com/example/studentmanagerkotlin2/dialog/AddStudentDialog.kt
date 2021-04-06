package com.example.studentmanagerkotlin2.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.studentmanagerkotlin2.R
import com.example.studentmanagerkotlin2.model.Student
import kotlinx.android.synthetic.main.add_student_dialog.*
import kotlinx.android.synthetic.main.add_student_dialog.view.*


class AddStudentDialog : DialogFragment() {

    private var name: String = ""
    private var yearBirth: String = ""
    private var numberPhone: String = ""
    private var specialized: String = ""
    private var typeEducation: String = ""
    private var idStudent: String = ""
    private lateinit var listener: AddStudentDialogListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.add_student_dialog, container)

        view.rb_University.setOnClickListener {
            typeEducation = "Đại Học"
        }

        view.rb_College.setOnClickListener {
            typeEducation = "Cao Đẳng"
        }

        view.btn_Cancer.setOnClickListener {
            dismiss()
        }

        view.btn_AddStudent.setOnClickListener {
            addStudent()
        }
        return view
    }

    private fun addStudent() {
        name = edt_Name.text.toString().trim()
        yearBirth = edt_YearBirth.text.toString().trim()
        numberPhone = edt_NumberPhone.text.toString().trim()
        specialized = edt_Specialized.text.toString().trim()
        idStudent = numberPhone
        if (name.length == 0 || yearBirth.length == 0 || numberPhone.length == 0 || specialized.length == 0 || typeEducation.length == 0) {
            Toast.makeText(context, "Không để các trường trống", Toast.LENGTH_SHORT).show()
        } else {
            var student =
                Student(idStudent, name, yearBirth, numberPhone, specialized, typeEducation)
            listener.applyStudents(student)
            edt_Name.text.clear()
            edt_YearBirth.text.clear()
            edt_NumberPhone.text.clear()
            edt_Specialized.text.clear()
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = context as AddStudentDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + "must implement AddStudentDialogListener")
        }
    }

    interface AddStudentDialogListener {
        fun applyStudents(student: Student)
    }

}