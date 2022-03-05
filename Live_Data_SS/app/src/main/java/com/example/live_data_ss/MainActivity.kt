                                        package com.example.live_data_ss

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.live_data_ss.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.seconds().observe(this, Observer {
            binding.numberTxt.text = it.toString()
        })

        viewModel.finished.observe(this, Observer {
            if (it) {
                Toast.makeText(this, "Finished YO!!!", Toast.LENGTH_SHORT).show()
                binding.numberTxt.text = "0"
            }
        })

         binding.startBtn.setOnClickListener {
             if (binding.numberInput.text.isEmpty() || binding.numberInput.length() < 4) {

                 Toast.makeText(this, "Wrong number", Toast.LENGTH_SHORT).show()
             } else {
                 viewModel.timerValue.value = binding.numberInput.text.toString().toLong()
                 viewModel.startTimer()
             }

         }

        binding.stopBtn.setOnClickListener {
            binding.numberTxt.text = "0"
              viewModel.stopTimer()
        }

    }
}