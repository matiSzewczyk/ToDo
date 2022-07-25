package com.app.todominimalistapp

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

interface CustomClickInterface {
    fun onClickListener(hiddenLayout: LinearLayout, taskDescription: TextView)
    fun onLongClickListener(view: View, position: Int)
    fun onCheckedChangeListener(
        adapterPosition: Int,
        checked: Boolean,
        taskName: TextView,
        taskDescription: TextView
    )
}