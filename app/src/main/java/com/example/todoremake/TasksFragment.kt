package com.example.todoremake

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoremake.databinding.FragmentTasksBinding
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class TasksFragment : Fragment(R.layout.fragment_tasks), CustomClickInterface {

    private lateinit var binding: FragmentTasksBinding
    private lateinit var tasksAdapter: TasksAdapter
    private val tasksViewModel: TasksViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTasksBinding.bind(view)
        setupRecyclerView()
        val tasksObserver = Observer<List<Task>> {
            tasksAdapter.notifyItemInserted(it.size - 1)
        }
        tasksViewModel._tasks.observe(viewLifecycleOwner, tasksObserver)

        binding.button.setOnClickListener {
            lifecycleScope.launch(Main) {
                val dialog = NewTaskDialogFragment()
                dialog.show(childFragmentManager, "show")
            }
        }
        binding.buttonRemove.setOnClickListener {
            tasksViewModel.removeAll()
        }
        lifecycleScope.launch(Main) {
            tasksViewModel.getTasks()
        }

    }

    private fun setupRecyclerView() = binding.tasksRecyclerView.apply {
        tasksAdapter = TasksAdapter(
            tasksViewModel.tasks,
            this@TasksFragment
        )
        adapter = tasksAdapter
        layoutManager = LinearLayoutManager(context)
    }

    override fun onClickListener(position: Int, view: View) {
        val hiddenLayout = view.findViewById<LinearLayout>(R.id.hidden_layout)
        hiddenLayout.visibility = if (hiddenLayout.visibility == View.GONE) View.VISIBLE else View.GONE
    }
}