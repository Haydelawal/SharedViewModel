package com.example.room_db_ss.fragments.list

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room_db_ss.R
import com.example.room_db_ss.viewmodel.UserViewModel
import com.example.room_db_ss.databinding.FragmentListBinding
import com.example.room_db_ss.fragments.update.UpdateFragmentArgs

class ListFragment : Fragment() {



    private lateinit var mUserViewModel: UserViewModel

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)

        // RecyclerView
        val adapter = ListAdapter()

        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //UseViewModel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })

          binding.floatingActionButton.setOnClickListener {
              findNavController().navigate(R.id.action_listFragment_to_addFragment)
          }


        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menu_delete) {
            deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setPositiveButton("Yes")
        {_, _ -> mUserViewModel.deleteALlUsers()
            Toast.makeText(requireContext(), "Successfully Deleted Everything",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)}


        builder.setNegativeButton("No")
        {_, _ -> }

        builder.setTitle("Delete Everything?")
        builder.setMessage("Are You Sure You Want To Delete Everything?")
        builder.create().show()
    }

}