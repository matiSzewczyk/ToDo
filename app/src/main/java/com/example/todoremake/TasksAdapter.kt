package com.example.todoremake

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoremake.databinding.TaskItemBinding

class TasksAdapter : RecyclerView.Adapter<TasksAdapter.ItemsViewHolder>() {

    private var tasks = mutableListOf<Task>()
    inner class ItemsViewHolder(val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return ItemsViewHolder(
            TaskItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false))
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.binding.apply {
            taskName.text = tasks[position].name
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun insertTask(task: Task) {
        tasks.add(task)
        notifyItemInserted(tasks.size - 1)
    }
}
