package com.example.sharedviewmodel_ss

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.sharedviewmodel_ss.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {




    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!

    private val sharedViewModel : SharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSecondBinding.inflate(inflater, container, false)


        sharedViewModel.country.observe(viewLifecycleOwner, Observer {

                country -> binding.secondEditText.setText(country)

        })

        binding.secondButton.setOnClickListener {
            sharedViewModel.saveCountry(binding.secondEditText.text.toString())

            findNavController().navigate(R.id.action_secondFragment_to_firstFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
