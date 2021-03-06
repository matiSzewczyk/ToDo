package com.app.todominimalistapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.todominimalistapp.databinding.FragmentCompletedTasksBinding

class CompletedTasksFragment : Fragment(R.layout.fragment_completed_tasks){

    private lateinit var binding: FragmentCompletedTasksBinding
    private val completedTasksViewModel: CompletedTasksViewModel by activityViewModels()
    private lateinit var completedTasksAdapter: CompletedTasksAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCompletedTasksBinding.bind(view)
        setupRecyclerView()

        val tasksObserver = Observer<List<Task>> {
            completedTasksAdapter.notifyDataSetChanged()
        }
        completedTasksViewModel.tasks.observe(viewLifecycleOwner, tasksObserver)

        completedTasksViewModel.getTasks()
    }

    private fun setupRecyclerView() = binding.completedRecyclerView.apply {
        completedTasksAdapter = CompletedTasksAdapter(
            completedTasksViewModel.tasks.value!!
        )
        adapter = completedTasksAdapter
        layoutManager = LinearLayoutManager(context)
    }
}