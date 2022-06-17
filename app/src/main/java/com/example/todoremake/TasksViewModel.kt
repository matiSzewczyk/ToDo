package com.example.todoremake

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(): ViewModel() {

    var tasks = MutableLiveData<MutableList<Task>>()

    fun addTask() {
        tasks.postValue(mutableListOf(Task("task name", "", false)))
    }
}