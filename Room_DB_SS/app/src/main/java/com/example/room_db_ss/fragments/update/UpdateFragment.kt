package com.example.room_db_ss.fragments.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.annotation.IntegerRes
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.room_db_ss.R
import com.example.room_db_ss.databinding.ActivityMainBinding.inflate
import com.example.room_db_ss.databinding.FragmentAddBinding
import com.example.room_db_ss.databinding.FragmentUpdateBinding
import com.example.room_db_ss.model.User
import com.example.room_db_ss.viewmodel.UserViewModel


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var binding: FragmentUpdateBinding

    private lateinit var mUserViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentUpdateBinding.inflate(inflater, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.updateFirstName.setText(args.currentUser.firstName)
        binding.updateLastName.setText(args.currentUser.lastName)
        binding.updateAge.setText(args.currentUser.age.toString())

        binding.updateButton.setOnClickListener {

            updateItem()
        }

        //add delete menu
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun updateItem() {
        val firstName = binding.updateFirstName.text.toString()
        val lastName = binding.updateLastName.text.toString()
        val age = Integer.parseInt(binding.updateAge.text.toString())


        if (inputCheck(firstName, lastName, binding.updateAge.text)) {
            //Create User Object
            val updatedUser = User(args.currentUser.id, firstName, lastName, age)

            //updated Created User
            mUserViewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(), "Updated Successfully", Toast.LENGTH_SHORT).show()

            // Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        } else {
            Toast.makeText(requireContext(), "Update Not Successfull", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable):  Boolean{

        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menu_delete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setPositiveButton("Yes")
        {_, _ -> mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(), "Successfully Deleted ${args.currentUser.firstName}",
                Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_updateFragment_to_listFragment)}


        builder.setNegativeButton("No")
        {_, _ -> }

        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are You Sure You Want To Delete ${args.currentUser.firstName}?")
        builder.create().show()
    }
}