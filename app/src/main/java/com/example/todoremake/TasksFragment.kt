package com.example.todoremake

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoremake.databinding.FragmentTasksBinding
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class TasksFragment : Fragment(R.layout.fragment_tasks){

    private lateinit var binding: FragmentTasksBinding
    private lateinit var tasksAdapter: TasksAdapter
    private val tasksViewModel: TasksViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTasksBinding.bind(view)
        setupRecyclerView()

        val tasksObserver = Observer<MutableList<Task>> {
            tasksAdapter.insertTask(it[it.size - 1])
        }
        tasksViewModel.tasks.observe(viewLifecycleOwner, tasksObserver)

        binding.button.setOnClickListener {
            lifecycleScope.launch(Main) {
                val dialog = NewTaskDialogFragment()
                dialog.show(childFragmentManager, "show")
            }
        }
    }

    private fun setupRecyclerView() = binding.tasksRecyclerView.apply {
        tasksAdapter = TasksAdapter()
        adapter = tasksAdapter
        layoutManager = LinearLayoutManager(context)
    }
}