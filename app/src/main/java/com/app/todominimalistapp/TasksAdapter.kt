package com.app.todominimalistapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.todominimalistapp.databinding.TaskItemBinding

class TasksAdapter(
    private val tasks: MutableList<Task>,
    private val customClickInterface: CustomClickInterface
) : RecyclerView.Adapter<TasksAdapter.ItemsViewHolder>() {

    inner class ItemsViewHolder(val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            apply {
                itemView.setOnClickListener {
                    customClickInterface.onClickListener(
                        adapterPosition,
                        binding.hiddenLayout,
                        binding.taskDescription
                    )
                }
                binding.taskCheckBox.setOnCheckedChangeListener { _, isChecked ->
                    customClickInterface.onCheckedChangeListener(
                        adapterPosition,
                        isChecked,
                        binding.taskName,
                        binding.taskDescription
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return ItemsViewHolder(
            TaskItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.binding.apply {
            taskName.text = tasks[position].name
            taskDescription.text = tasks[position].description
            taskCheckBox.isChecked = tasks[position].isChecked
        }
    }

    override fun getItemCount() = tasks.size
}
