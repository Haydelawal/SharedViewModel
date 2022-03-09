package com.example.room_db_ss.fragments.list

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelLazy
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.room_db_ss.R
import com.example.room_db_ss.databinding.FragmentListBinding
import com.example.room_db_ss.model.User

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder> () {


    private var userlist = emptyList<User>()


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))

    }



    override fun getItemCount(): Int {
        return userlist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {



        val currentItem = userlist[position]

        holder.itemView.findViewById<TextView>(R.id.id_txt).text = currentItem.id.toString()
        holder.itemView.findViewById<TextView>(R.id.firstName_txt).text = currentItem.firstName.toString()
        holder.itemView.findViewById<TextView>(R.id.lastName_txt).text = currentItem.lastName.toString()
        holder.itemView.findViewById<TextView>(R.id.age_txt).text = currentItem.age.toString()

        holder.itemView.findViewById<View>(R.id.rowLayout).setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }


    }
    fun setData(user: List<User>) {
        this.userlist = user
        notifyDataSetChanged()
    }



}