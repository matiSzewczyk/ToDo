package com.example.todoremake

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoremake.databinding.CompletedTaskItemBinding


class CompletedTasksAdapter(
    private val completedTasks: MutableList<Task>
) : RecyclerView.Adapter<CompletedTasksAdapter.ItemsViewHolder>() {

    inner class ItemsViewHolder(val binding: CompletedTaskItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return ItemsViewHolder(
            CompletedTaskItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.binding.apply {
            taskName.text = completedTasks[position].name
        }
    }

    override fun getItemCount() = completedTasks.size
}
