package com.example.todoremake

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val repository: TasksRepository
): ViewModel() {

    var _tasks = MutableLiveData<MutableList<Task>>()
    var tasks = mutableListOf<Task>()

    fun addTask(task: Task) {
        tasks.add(task)
        _tasks.postValue(tasks)
        repository.saveToObjectBox(task)
    }
    fun getTasks() {
        if (tasks.isEmpty()) {
            repository.getObjectBoxList().forEach {
                val task = Task(
                    it.taskName,
                    it.taskDescription,
                    it.isChecked
                )
                if (!task.isChecked) {
                    tasks.add(task)
                    _tasks.postValue(tasks)
                }
            }
        }
    }

    fun removeTaskFromRv(position: Int) {
        tasks.removeAt(position)
        _tasks.postValue(tasks)
    }

    fun removeAll() {
        repository.removeAll()
    }
}