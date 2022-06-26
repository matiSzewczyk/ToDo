package com.app.todominimalistapp

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.todominimalistapp.databinding.FragmentTasksBinding
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class TasksFragment : Fragment(R.layout.fragment_tasks), CustomClickInterface {

    private lateinit var binding: FragmentTasksBinding
    private lateinit var tasksAdapter: TasksAdapter
    private val tasksViewModel: TasksViewModel by activityViewModels()
    private val completedTasksViewModel: CompletedTasksViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTasksBinding.bind(view)
        setupRecyclerView()

        val tasksObserver = Observer<List<Task>> {
            if (it.size > tasksViewModel.listCount) {
                tasksAdapter.notifyItemInserted(it.lastIndex)
                tasksViewModel.listCount++
            }
            else if (it.size < tasksViewModel.listCount) {
                tasksAdapter.notifyItemRemoved(tasksViewModel.removedPos)
                tasksViewModel.listCount--
            }
        }
        tasksViewModel._tasks.observe(viewLifecycleOwner, tasksObserver)

        binding.addTaskButton.setOnClickListener {
            lifecycleScope.launch(Main) {
                val dialog = NewTaskDialogFragment()
                dialog.show(childFragmentManager, "show")
            }
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

    override fun onClickListener(position: Int, hiddenLayout: LinearLayout, taskDescription: TextView) {
        if (taskDescription.text.isNotEmpty()) {
            hiddenLayout.visibility =
                if (hiddenLayout.visibility == View.GONE) View.VISIBLE else View.GONE
        }
    }

    override fun onCheckedChangeListener(
        adapterPosition: Int,
        checked: Boolean,
        taskName: TextView,
        taskDescription: TextView
    ) {
        if (checked) {
            val task =
                Task(taskName.text.toString(), taskDescription.text.toString(), true)
            tasksViewModel.removeTaskFromRv(adapterPosition)
            completedTasksViewModel.addToCompleted(task)
        }
    }
}