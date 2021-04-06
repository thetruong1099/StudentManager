package com.example.studentmanagerkotlin2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanagerkotlin2.R
import com.example.studentmanagerkotlin2.model.Student
import kotlinx.android.synthetic.main.item_student_simplify.view.*

class RecyclerListStudentAdapterSimplify(var arrayList: ArrayList<Student>) :
    RecyclerView.Adapter<RecyclerListStudentAdapterSimplify.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName = itemView.tv_Name_Simplify
        var tvYearBirth = itemView.tv_YearBirth_Simplify
        var tvNumberPhone = itemView.tv_NumberPhone_Simplify
        var tvSpecialized = itemView.tv_Specialized_Simplify
        var tvTypeEducation = itemView.tv_TypeEducation_Simplify
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_student_simplify, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = arrayList[position].name
        holder.tvYearBirth.text = "Năm Sinh: " + arrayList[position].yearBirth
        holder.tvNumberPhone.text = "SDT: " + arrayList[position].numberPhone
        holder.tvSpecialized.text = "Chuyên ngành: " + arrayList[position].specialized
        holder.tvTypeEducation.text = "Trình độ: " + arrayList[position].typeEducation
    }

    override fun getItemCount(): Int = arrayList.size
}