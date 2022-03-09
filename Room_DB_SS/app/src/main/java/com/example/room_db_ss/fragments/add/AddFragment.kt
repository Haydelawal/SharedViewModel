package com.example.room_db_ss.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.room_db_ss.R
import com.example.room_db_ss.model.User
import com.example.room_db_ss.viewmodel.UserViewModel
import com.example.room_db_ss.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding

    private lateinit var myUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater, container, false)

        myUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.addButton.setOnClickListener {
            insertDataToDatabase()
        }

        return binding.root
    }

    private fun insertDataToDatabase() {

        val firstName = binding.addFirstName.text.toString()
        val lastName = binding.addLastName.text.toString()
        val age = binding.addAge.text

        if (inputCheck(firstName, lastName, age)) {
            // Create User Object

            val user = User(0, firstName, lastName, Integer.parseInt(age.toString()))

            // ADD DATABASE
            myUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Success In Add", Toast.LENGTH_SHORT).show()

            // NAVIGATE BACK
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please Fill All Fields", Toast.LENGTH_SHORT).show()
        }





    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable):  Boolean{

        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())

    }


}