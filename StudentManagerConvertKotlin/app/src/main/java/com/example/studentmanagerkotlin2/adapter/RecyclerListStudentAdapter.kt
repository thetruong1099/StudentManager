package com.example.studentmanagerkotlin2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanagerkotlin2.R
import com.example.studentmanagerkotlin2.model.Student
import kotlinx.android.synthetic.main.item_student.view.*

class RecyclerListStudentAdapter(
    private var arrayList: ArrayList<Student>,
    private var listener: OnItemClickListenter,
) : RecyclerView.Adapter<RecyclerListStudentAdapter.ViewHolder>() {

    private var studentListRemove = ArrayList<Student>()
    private var statusChecked: Boolean = false

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var tvName = itemView.tv_Name
        var tvYearBirth = itemView.tv_YearBirth
        var tvNumberPhone = itemView.tv_NumberPhone
        var tvSpecialized = itemView.tv_Specialized
        var tvTypeEducation = itemView.tv_TypeEducation
        var cbSelectStudent = itemView.cb_SelectStudent

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = arrayList[position].name
        holder.tvYearBirth.text = "Năm Sinh: " + arrayList[position].yearBirth
        holder.tvNumberPhone.text = "SDT: " + arrayList[position].numberPhone
        holder.tvSpecialized.text = "Chuyên ngành: " + arrayList[position].specialized
        holder.tvTypeEducation.text = "Trình độ: " + arrayList[position].typeEducation
        holder.cbSelectStudent.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) studentListRemove.add(arrayList[position])
            else studentListRemove.remove(arrayList[position])
        }
        if (statusChecked) holder.cbSelectStudent.isChecked = false
//        Log.d("ShowStudent", "studentListRemoveAdapter: " + studentListRemove.toString())
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    interface OnItemClickListenter {
        fun onItemClick(position: Int)
    }

    fun getStudentList(): ArrayList<Student> = studentListRemove

    fun setStudentListRemove(status: Boolean) {
        if (status) {
            studentListRemove.clear()
            statusChecked = status
        }
    }
}