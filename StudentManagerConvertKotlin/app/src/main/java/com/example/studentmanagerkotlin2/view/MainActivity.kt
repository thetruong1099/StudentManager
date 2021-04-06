package com.example.studentmanagerkotlin2.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentmanagerkotlin2.R
import com.example.studentmanagerkotlin2.adapter.RecyclerListStudentAdapter
import com.example.studentmanagerkotlin2.adapter.RecyclerListStudentAdapterSimplify
import com.example.studentmanagerkotlin2.dialog.*
import com.example.studentmanagerkotlin2.model.Student
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AddStudentDialog.AddStudentDialogListener,
    RecyclerListStudentAdapter.OnItemClickListenter, EditStudentDialog.EditStudentListener,
    SortStudentDialog.SortStudentListener, FilterStudentDialog.FilterStudentDialogListener,
    SearchStudentDialog.SearchStudentDialogListener {
    private var listStudent: ArrayList<Student> = ArrayList()
    private var listStudentAdapter = RecyclerListStudentAdapter(listStudent, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setEventButtonShowFuntion()

        setRecyclerViewListStudent()

        setEventButtonAdd()

        setEventButtonRemove()

        setEventButtonSort()

        setEventButtonFilter()

        setEventButtonSearch()

        setEventButtonRefresh()
    }

    private fun setRecyclerViewListStudent() {
        recyclerView_ListStuednt.adapter = listStudentAdapter
        recyclerView_ListStuednt.layoutManager = LinearLayoutManager(this)
        recyclerView_ListStuednt.setHasFixedSize(true)
    }

    private fun setEventButtonShowFuntion() {
        var statusButton: Boolean
        fab_Open.setOnClickListener {
            statusButton = true
            showFuntionButton(statusButton)
        }

        fab_Close.setOnClickListener {
            statusButton = false
            showFuntionButton(statusButton)
        }
    }

    private fun showFuntionButton(statusButton: Boolean) {
        if (statusButton) {
            fab_Open.visibility = View.GONE
            fab_Close.visibility = View.VISIBLE
            fab_Add.visibility = View.VISIBLE
            fab_Remove.visibility = View.VISIBLE
            fab_Sort.visibility = View.VISIBLE
            fab_Filter.visibility = View.VISIBLE
            fab_Search.visibility = View.VISIBLE
            fab_Refresh.visibility = View.VISIBLE
        } else {
            fab_Open.visibility = View.VISIBLE
            fab_Close.visibility = View.GONE
            fab_Add.visibility = View.GONE
            fab_Remove.visibility = View.GONE
            fab_Sort.visibility = View.GONE
            fab_Filter.visibility = View.GONE
            fab_Search.visibility = View.GONE
            fab_Refresh.visibility = View.GONE
        }

    }

    /////Add funtion

    private fun setEventButtonAdd() {
        fab_Add.setOnClickListener {
            val addStudentDialog = AddStudentDialog()
            addStudentDialog.show(supportFragmentManager, "customDialog")
            addStudentDialog.isCancelable = false
        }
    }

    override fun applyStudents(student: Student) {
        if (listStudent.size == 0) listStudent.add(student)
        else {
            var statusCheckPhone = true
            for (i in listStudent) {
                if (student.numberPhone.equals(i.numberPhone)) {
                    statusCheckPhone = false
                    break
                }
            }
            if (statusCheckPhone) listStudent.add(student)
            else Toast.makeText(this, "SDT bị trùng", Toast.LENGTH_SHORT).show()
        }
        listStudentAdapter.notifyDataSetChanged()
//        Log.d("ShowStudent", "-------------------------------------")
//        Log.d("ShowStudent", "applyStudents size: "+listStudent.size.toString())
//        Log.d("ShowStudent", "applyStudents: "+listStudent.toString())
    }


    /////Edit Funtion

    override fun onItemClick(position: Int) {
        var listNumberPhone = getListNumberPhone(position)
        var editStudentDialog = EditStudentDialog(listStudent[position], listNumberPhone, position)
        editStudentDialog.show(supportFragmentManager, "Custom Edit Dialog")
        editStudentDialog.isCancelable = false
//        Log.d("ShowStudent", "listNumberPhone: "+listNumberPhone.toString())
    }

    private fun getListNumberPhone(position: Int): ArrayList<String> {
        var listNumberPhone = ArrayList<String>()
        for (i in listStudent) {
            if (!i.equals(listStudent[position])) listNumberPhone.add(i.numberPhone)
        }
        return listNumberPhone
    }

    override fun editStudent(student: Student, position: Int) {
        listStudent[position] = student
        listStudentAdapter.notifyItemChanged(position)
    }

    ////Remove funtion

    private fun setEventButtonRemove() {
        fab_Remove.setOnClickListener {
            showRemoveDialog()
        }
    }

    private fun showRemoveDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Xóa Thông Tin Sinh Viên")
        builder.setMessage("Bạn có chắc chắn muốn xóa không")
        builder.setPositiveButton("Yes") { dialogInterface, which ->
            val studentListRemove = listStudentAdapter.getStudentList()
//            Log.d("ShowStudent", "studentListRemove: " + studentListRemove.toString())

            if (studentListRemove.size == 0) {
                Toast.makeText(this, "Chọn sinh viên để xóa", Toast.LENGTH_LONG).show()
            } else {
                for (i in studentListRemove) {
                    listStudent.remove(i)
                }
                listStudentAdapter.setStudentListRemove(true)
                studentListRemove.clear()
                listStudentAdapter.notifyDataSetChanged()

//                Log.d("ShowStudent", "RemoveStudents: " + listStudent.toString())
                Toast.makeText(this, "Đã xóa thành công", Toast.LENGTH_LONG).show()
            }
        }
        builder.setNegativeButton("No") { dialogInterface, which ->
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }


    /////Sort funtion

    private fun setEventButtonSort() {
        fab_Sort.setOnClickListener {
            val sortStudentDialog = SortStudentDialog(listStudent)
            sortStudentDialog.show(supportFragmentManager, "Custom sort dialog")
        }
    }

    override fun sortStudent(studentListSort: ArrayList<Student>) {
        listStudent = studentListSort
        listStudentAdapter.notifyDataSetChanged()
    }

    /////Filter funtion

    private fun setEventButtonFilter() {
        fab_Filter.setOnClickListener {
            val filterStudentDialog = FilterStudentDialog(listStudent)
            filterStudentDialog.show(supportFragmentManager, "Custom filter dialog")
        }
    }

    override fun filterStudent(studentListFilter: ArrayList<Student>) {
        var filterStudentAdapter = RecyclerListStudentAdapterSimplify(studentListFilter)
        recyclerView_ListStuednt.adapter = filterStudentAdapter
        recyclerView_ListStuednt.layoutManager = LinearLayoutManager(this)
        recyclerView_ListStuednt.setHasFixedSize(true)
    }

    /////Search funtion

    private fun setEventButtonSearch() {
        fab_Search.setOnClickListener {
            var searchStudentDialog = SearchStudentDialog(listStudent)
            searchStudentDialog.show(supportFragmentManager, "Custom search dialog")
        }
    }

    override fun searchStudent(studentListSeacrh: ArrayList<Student>) {
        var filterStudentAdapter = RecyclerListStudentAdapterSimplify(studentListSeacrh)
        recyclerView_ListStuednt.adapter = filterStudentAdapter
        recyclerView_ListStuednt.layoutManager = LinearLayoutManager(this)
        recyclerView_ListStuednt.setHasFixedSize(true)
    }

    // Refresh funtion

    private fun setEventButtonRefresh() {
        fab_Refresh.setOnClickListener {
            setRecyclerViewListStudent()
        }
    }

}