package com.example.studentmanagerkotlin2.dialog


import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.studentmanagerkotlin2.R
import com.example.studentmanagerkotlin2.model.Student
import kotlinx.android.synthetic.main.edit_student_dialog.*
import kotlinx.android.synthetic.main.edit_student_dialog.view.*

class EditStudentDialog(
    var student: Student,
    var listNumberPhone: ArrayList<String>,
    val position: Int
) : DialogFragment() {
    private var name: String = student.name
    private var yearBirth: String = student.yearBirth
    private var numberPhone: String = student.numberPhone
    private var specialized: String = student.specialized
    private var typeEducation: String = student.typeEducation

    private lateinit var listener: EditStudentListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.edit_student_dialog, container)
//        Log.d("ShowStudent", "OldStudents: "+student.toString())
//        Log.d("ShowStudent", "listNumberPhone1: "+listNumberPhone.toString())
        view.edt_Name_Edit.setText(name)
        view.edt_YearBirth_Edit.setText(yearBirth)
        view.edt_NumberPhone_Edit.setText(numberPhone)
        view.edt_Specialized_Edit.setText(specialized)

        if (typeEducation.equals("Đại Học")) {
            view.rb_University_Edit.isChecked = true
            view.rb_College_Edit.isChecked = false
        } else {
            view.rb_University_Edit.isChecked = false
            view.rb_College_Edit.isChecked = true
        }

        view.rb_University_Edit.setOnClickListener {
            typeEducation = "Đại Học"
        }

        view.rb_College_Edit.setOnClickListener {
            typeEducation = "Cao Đẳng"
        }

        view.btn_EditStudent.setOnClickListener {
            editStudent()
        }

        view.btn_Cancer_Edit.setOnClickListener {
            dismiss()
        }

        return view
    }

    private fun editStudent() {
        name = edt_Name_Edit.text.toString().trim()
        yearBirth = edt_YearBirth_Edit.text.toString().trim()
        numberPhone = edt_NumberPhone_Edit.text.toString().trim()
        specialized = edt_Specialized_Edit.text.toString().trim()

        var statusChecked = checkNumberPhone(numberPhone)
//        Log.d("ShowStudent", "statusChecked: "+statusChecked)
        if (name.length == 0 || yearBirth.length == 0 || numberPhone.length == 0 || specialized.length == 0 || typeEducation.length == 0 || statusChecked) {
            Toast.makeText(
                context,
                "Không để các trường trống hoac trùng số điện thoại",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            showAlertDialog()
        }
//        Log.d("ShowStudent", "NewStudents: "+student.toString())
    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Sửa Thông Tin")
        builder.setMessage("Bạn có chắc chắn muốn sửa không")
        builder.setPositiveButton("Yes") { dialogInterface, which ->
            student.name = name
            student.yearBirth = yearBirth
            student.numberPhone = numberPhone
            student.specialized = specialized
            student.typeEducation = typeEducation
            listener.editStudent(student, position)
//            Log.d("ShowStudent", "NewStudents: "+student.toString())
            Toast.makeText(context, "Đã sửa thành công", Toast.LENGTH_LONG).show()
            dismiss()
        }
        builder.setNegativeButton("No") { dialogInterface, which ->
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun checkNumberPhone(numberphoneCheck: String): Boolean {
        var statusChecked = false
        for (i in listNumberPhone) {
            if (numberphoneCheck.equals(i)) {
                statusChecked = true
                break
            }
        }
        return statusChecked
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = context as EditStudentListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + "must implement EditStudentDialogListener")
        }
    }

    interface EditStudentListener {
        fun editStudent(student: Student, position: Int)
    }
}