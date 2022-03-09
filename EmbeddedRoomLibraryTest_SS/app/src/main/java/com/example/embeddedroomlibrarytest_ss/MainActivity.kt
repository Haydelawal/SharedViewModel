package com.example.embeddedroomlibrarytest_ss

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.embeddedroomlibrarytest_ss.data.Address
import com.example.embeddedroomlibrarytest_ss.data.MyAdapter
import com.example.embeddedroomlibrarytest_ss.data.MyViewModel
import com.example.embeddedroomlibrarytest_ss.data.Person
import com.example.embeddedroomlibrarytest_ss.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //activity_main ==>> Activity_Main_Binding
    private lateinit var binding: ActivityMainBinding

    private val adapter by lazy { MyAdapter() }
    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val address = Address("Billy Street",76)
        val person = Person(0,"John", "Doe", 23, address)

        myViewModel.insertPerson(person)

        myViewModel.readPerson.observe(this, Observer {
            adapter.setData(it)
        })

    }
}