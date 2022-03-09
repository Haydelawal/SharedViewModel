package com.example.sharedviewmodel_ss

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.sharedviewmodel_ss.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {



    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!

    private val sharedViewModel:SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFirstBinding.inflate(inflater, container, false)


        sharedViewModel.country.observe(viewLifecycleOwner, Observer {

            country -> binding.firstEditText.setText(country)

        })

        binding.firstButton.setOnClickListener {
            sharedViewModel.saveCountry(binding.firstEditText.text.toString())

            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}