package com.AGIndigo8.dailydopamine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class TaskCardFrag : Fragment() {
    private var task: Task = Task()
    private lateinit var taskName : TextView
    private lateinit var taskDescription : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            task = it.getSerializable("task", Task::class.java)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskName = view.findViewById(R.id.taskName)
        taskName.text = task.name
        taskDescription = view.findViewById(R.id.taskDescription)
        taskDescription.text = task.description

    }

    companion object {
        @JvmStatic
        fun newInstance(task: Task) =
            TaskCardFrag().apply {
                arguments = Bundle().apply {
                    putSerializable("task", task)
                }
            }
    }
}