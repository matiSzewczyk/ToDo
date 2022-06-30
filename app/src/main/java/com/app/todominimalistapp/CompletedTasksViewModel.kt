package com.app.todominimalistapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CompletedTasksViewModel @Inject constructor(
    private val repository: TasksRepository
): ViewModel() {

    var _tasks = MutableLiveData<MutableList<Task>>()
    var tasks = mutableListOf<Task>()

    fun getTasks() {
        tasks.clear()
        repository.getObjectBoxList().forEach {
            val task = Task(
                it.taskName,
                it.taskDescription,
                it.isChecked,
                it.completionDate
            )
            if (task.isChecked) {
                if (task.date != null) {
                    if (Constants().DATE > task.date!!) {
                        repository.removeFromBox(task)
                        return@forEach
                    }
                }
                tasks.add(task)
                _tasks.postValue(tasks)
            }
        }
    }

    fun addToCompleted(task: Task) {
        repository.addToCompleted(task)
    }
}