package com.example.testapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.models.Task

class TasksRecyclerAdapter(private var context: Context,
                           private val onItemCheckListener: OnItemCheckListener) :
    RecyclerView.Adapter<TasksRecyclerAdapter.TaskViewHolder>() {

    private var dataSet = ArrayList<Task>()

    inner class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view), CompoundButton.OnCheckedChangeListener {

        private val checkbox: CheckBox = view.findViewById(R.id.checkbox)


        fun bind(task: Task){
            checkbox.text = task.title
            checkbox.isChecked = task.completed

            if(!task.completed){
                checkbox.setOnCheckedChangeListener(this)
            }
            else
                checkbox.isEnabled = false
        }


        override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
            if(p1){
                val task = dataSet[adapterPosition]
                task.completed = true
                onItemCheckListener.onItemCheck(task)
                checkbox.setOnCheckedChangeListener(null)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row_task, viewGroup, false)

        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: TaskViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
    }

    public fun setData(tasksList: List<Task>){
        this.dataSet = ArrayList(tasksList)
        notifyDataSetChanged()

    }
    override fun getItemCount() = dataSet.size

    interface OnItemCheckListener {
        fun onItemCheck(task: Task)
    }

}


