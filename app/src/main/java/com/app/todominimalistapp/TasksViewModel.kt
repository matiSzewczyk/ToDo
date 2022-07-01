package com.app.todominimalistapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val repository: TasksRepository
): ViewModel() {

    var tasks = MutableLiveData<MutableList<Task>>(mutableListOf())

//     These variables allow me to know if an item was removed or inserted into the list
//     as well as know it's position.
//     As a result I can have the item animated when the RecyclerView changes.
    var removedPos = 0
    var listCount = 0

    fun addTask(task: Task) {
        tasks.value!!.add(task)
        tasks.value = tasks.value
        repository.saveToObjectBox(task)
    }

    fun getTasks() {
        if (tasks.value!!.isEmpty()) {
            repository.getObjectBoxList().forEach {
                val task = Task(
                    it.taskName,
                    it.taskDescription,
                    it.isChecked
                )
                if (!task.isChecked) {
                    tasks.value!!.add(task)
                    tasks.value = tasks.value
                }
            }
            listCount = tasks.value!!.size
        }
    }

    fun removeTaskFromRv(position: Int) {
        tasks.value!!.removeAt(position)
        removedPos = position
        tasks.value = tasks.value
    }
}