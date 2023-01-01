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
    private lateinit var points : TextView

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
        points = view.findViewById(R.id.points)
        var pointsString  = getString(R.string.dopamine_level, task.todaysPoints)
        points.text = pointsString
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