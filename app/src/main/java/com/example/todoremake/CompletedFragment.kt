package com.example.todoremake

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.todoremake.databinding.FragmentCompletedBinding

class CompletedFragment : Fragment(R.layout.fragment_completed){

    private lateinit var binding: FragmentCompletedBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCompletedBinding.bind(view)
    }
}