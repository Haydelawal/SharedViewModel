package com.example.typeconverter_ss.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.typeconverter_ss.R
import com.example.typeconverter_ss.databinding.RowLayoutBinding

class MyAdapter: RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    private var person = emptyList<Person>()

    inner class MyViewHolder(val binding: RowLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return person.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.firstNameTxt.text = person[position].firstName
        holder.binding.lastNameTxt.text = person[position].lastName
        holder.binding.imageView.load(person[position].profilePhoto)
    }

    fun setData(person: List<Person>){
        this.person = person
        notifyDataSetChanged()
    }
}