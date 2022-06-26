package com.app.todominimalistapp

import android.widget.LinearLayout
import android.widget.TextView

interface CustomClickInterface {
    fun onClickListener(position: Int, hiddenLayout: LinearLayout, taskDescription: TextView)
    fun onCheckedChangeListener(
        adapterPosition: Int,
        checked: Boolean,
        taskName: TextView,
        taskDescription: TextView
    )

}