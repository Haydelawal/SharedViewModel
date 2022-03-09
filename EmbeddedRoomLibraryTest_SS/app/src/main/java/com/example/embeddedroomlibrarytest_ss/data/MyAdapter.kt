package com.example.embeddedroomlibrarytest_ss.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.embeddedroomlibrarytest_ss.databinding.RowLayoutBinding


class MyAdapter: RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    private var personList = emptyList<Person>()

    inner class MyViewHolder(val binding: RowLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.firstNameTxt.text = personList[position].firstName
        holder.binding.lastNameTxt.text = personList[position].lastName
        holder.binding.ageTxt.text = personList[position].age.toString()

        holder.binding.streetNameTxt.text = personList[position].address.streetName
        holder.binding.streetNumberTxt.text = personList[position].address.streetNumber.toString()
    }

    fun setData(person: List<Person>){
        this.personList = person
        notifyDataSetChanged()
    }
}