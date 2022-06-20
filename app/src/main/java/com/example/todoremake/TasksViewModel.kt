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
        println("List: ${repository.getObjectBoxList()}")
        if (tasks.isEmpty()) {
            repository.getObjectBoxList().forEach {
                val task = Task(
                    it.taskName,
                    it.taskDescription,
                    it.isChecked
                )
                tasks.add(task)
                _tasks.postValue(tasks)
            }
        }
//            tasks = repository.getObjectBoxList()
//            _tasks.postValue(tasks)
    }

    fun removeAll() {
        repository.removeAll()
    }
}