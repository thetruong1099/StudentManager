package com.example.studentmanagerkotlin2.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.studentmanagerkotlin2.R
import com.example.studentmanagerkotlin2.model.Student
import com.example.studentmanagerkotlin2.util.VietnameseCharacterUtil
import kotlinx.android.synthetic.main.search_student_dialog.*
import kotlinx.android.synthetic.main.search_student_dialog.view.*

class SearchStudentDialog(var studentList: ArrayList<Student>) : DialogFragment() {
    private lateinit var listener: SearchStudentDialogListener
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.search_student_dialog, container)
        var list = ArrayList<Student>()
        view.btn_Search.setOnClickListener {
            var keyword = VietnameseCharacterUtil.removeAccent(
                edt_Keyword.text.toString().trim().toLowerCase()
            )
            if (keyword.length > 0) {
                for (i in studentList) {
                    if (VietnameseCharacterUtil.removeAccent(i.name.toLowerCase()).contains(keyword)
                        || VietnameseCharacterUtil.removeAccent(i.yearBirth).contains(keyword)
                        || VietnameseCharacterUtil.removeAccent(i.numberPhone).contains(keyword)
                        || VietnameseCharacterUtil.removeAccent(i.specialized.toLowerCase())
                            .contains(keyword)
                        || VietnameseCharacterUtil.removeAccent(i.typeEducation.toLowerCase())
                            .contains(keyword)
                    ) {
                        list.add(i)
                    }
                }
            }
            listener.searchStudent(list)
//            Log.d("ShowStudent", "search: "+list.toString())
            dismiss()
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as SearchStudentDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + "must implement SortStudentDialogListener")
        }
    }

    interface SearchStudentDialogListener {
        fun searchStudent(studentListSeacrh: ArrayList<Student>)
    }
}