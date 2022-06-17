package com.example.todoremake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.todoremake.databinding.DialogNewTaskFragmentBinding

class NewTaskDialogFragment : DialogFragment() {

    private lateinit var binding: DialogNewTaskFragmentBinding
    private val tasksViewModel: TasksViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, com.google.android.material.R.style.Theme_MaterialComponents_Light_Dialog_MinWidth)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogNewTaskFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            cancelButton.setOnClickListener {
                dialog!!.dismiss()
            }
            confirmButton.setOnClickListener {
                if (taskName.text.isEmpty()) {
                    Toast.makeText(context, "Name your next task.", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }
                tasksViewModel.addTask(Task(
                    taskName.text.toString(),
                    taskDescription.text.toString(),
                    false
                ))
                dialog!!.dismiss()
            }
        }
    }
}