package com.AGIndigo8.dailydopamine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class TaskCardFrag : Fragment() {
    private var task: Task = Task()
    private lateinit var taskName : TextView
    private lateinit var points : TextView
    private lateinit var addButton : Button
    private lateinit var minusButton : Button

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

        setupTaskName(view)
        setupPoints(view)

        setupAddButton(view)
        setupMinusButton(view)
    }

    fun setupTaskName(view: View) {
        taskName = view.findViewById(R.id.taskName)
        taskName.text = task.name
    }

    fun setupPoints(view: View) {
        points = view.findViewById(R.id.points)
        var pointsString  = getString(R.string.dopamine_level, task.todaysPoints)
        points.text = pointsString

        TaskManager.subscribe(EventType.MODIFY) {
            pointsString  = getString(R.string.dopamine_level, task.todaysPoints)
            points.text = pointsString
        }
    }

    fun setupAddButton(view: View){
        addButton = view.findViewById(R.id.addButton)

        addButton.setOnClickListener {
            TaskManager.modifyTask(task){
                it.incrementPoints()
            }
        }

        TaskManager.subscribe(EventType.MODIFY) {
            addButton.isEnabled = !task.isMaxPoints()
        }
    }

    fun setupMinusButton(view: View) {
        minusButton = view.findViewById(R.id.minusButton)
        
        minusButton.setOnClickListener {
            TaskManager.modifyTask(task){
                it.decrementPoints()
            }
        }

        TaskManager.subscribe(EventType.MODIFY) {
            minusButton.isEnabled = !task.isMinPoints()
        }
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