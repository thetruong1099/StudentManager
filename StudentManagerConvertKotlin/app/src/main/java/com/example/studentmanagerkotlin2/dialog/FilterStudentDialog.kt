package com.example.studentmanagerkotlin2.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.studentmanagerkotlin2.R
import com.example.studentmanagerkotlin2.model.Student
import kotlinx.android.synthetic.main.filter_student_dialog.view.*

class FilterStudentDialog(var studentList: ArrayList<Student>) : DialogFragment() {
    private lateinit var listener: FilterStudentDialogListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.filter_student_dialog, container)

        view.btn_FilterByUniversity.setOnClickListener {
            var list: ArrayList<Student> = studentList.filter { it ->
                it.typeEducation == "Đại Học"
            } as ArrayList<Student>

            listener.filterStudent(list)
//            Log.d("ShowStudent", "filter1: "+list.toString())
            dismiss()
        }

        view.btn_FilterByColleges.setOnClickListener {
            var list: ArrayList<Student> = studentList.filter { it ->
                it.typeEducation == "Cao Đẳng"
            } as ArrayList<Student>

            listener.filterStudent(list)
            dismiss()
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = context as FilterStudentDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + "must implement FilterStudentDialogListener")
        }
    }

    interface FilterStudentDialogListener {
        fun filterStudent(studentListFilter: ArrayList<Student>)
    }
}