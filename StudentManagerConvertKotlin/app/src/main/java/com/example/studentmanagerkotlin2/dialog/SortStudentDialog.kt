package com.example.studentmanagerkotlin2.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.studentmanagerkotlin2.R
import com.example.studentmanagerkotlin2.model.Student
import kotlinx.android.synthetic.main.sort_student_dialog.view.*

class SortStudentDialog(var studentList: ArrayList<Student>) : DialogFragment() {
    private lateinit var listener: SortStudentListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        Log.d("ShowStudent", "sort1: "+studentList.toString())
        val view: View = inflater.inflate(R.layout.sort_student_dialog, container)

        view.btn_SortByName.setOnClickListener {
            studentList.sortWith(compareBy { it.name })
//            Log.d("ShowStudent", "sort2: "+studentList.toString())
            listener.sortStudent(studentList)
            dismiss()
        }

        view.btn_SortByYear.setOnClickListener {
            studentList.sortWith(compareBy({ it.yearBirth }, { it.name }))
            listener.sortStudent(studentList)
            dismiss()
        }

        view.btn_SortByPhone.setOnClickListener {
            studentList.sortWith(compareBy { it.numberPhone })
            listener.sortStudent(studentList)
            dismiss()
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as SortStudentListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + "must implement SortStudentDialogListener")
        }
    }

    interface SortStudentListener {
        fun sortStudent(studentListSort: ArrayList<Student>)
    }
}