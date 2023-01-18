package com.AGIndigo8.dailydopamine

object TaskManager {
    private val ADD = EventType.ADD
    private val REMOVE = EventType.REMOVE
    private val MODIFY = EventType.MODIFY
    private val ALL = EventType.ALL

    var tasks: MutableList<Task> = mutableListOf()
    var subscribersToAdd : MutableList<() -> Unit> = mutableListOf()
    var subscribersToRemove : MutableList<() -> Unit> = mutableListOf()
    var subscribersToModify : MutableList<() -> Unit> = mutableListOf()

    fun subscribe(eventType: EventType, subscriber: () -> Unit) {
        when (eventType) {
            ADD -> subscribersToAdd.add(subscriber)
            REMOVE -> subscribersToRemove.add(subscriber)
            MODIFY -> subscribersToModify.add(subscriber)
            ALL -> {
                subscribersToAdd.add(subscriber)
                subscribersToRemove.add(subscriber)
                subscribersToModify.add(subscriber)
            }
            else -> {}
        }
    }

    fun unsubscribe(subscriber: () -> Unit) {
        subscribersToAdd.remove(subscriber)
        subscribersToRemove.remove(subscriber)
        subscribersToModify.remove(subscriber)
    }

    fun notifySubscribers(event: EventType = ALL) {
        when (event) {
            ADD -> subscribersToAdd.forEach { it() }
            REMOVE -> subscribersToRemove.forEach { it() }
            MODIFY -> subscribersToModify.forEach { it() }
            ALL -> {
                subscribersToAdd.forEach { it() }
                subscribersToRemove.forEach { it() }
                subscribersToModify.forEach { it() }
            }
            else -> {}
        }
    }

    fun addTask(task: Task) {
        tasks.add(task)
        notifySubscribers(ADD)
    }

    fun removeTask(task: Task) {
        tasks.remove(task)
        notifySubscribers(REMOVE)
    }

    fun getUserTasks(): MutableList<Task> {
        return tasks
    }

    fun getTask(index: Int): Task {
        return tasks[index]
    }

    fun getTaskCount(): Int {
        return tasks.size
    }

    fun modifyTask(index: Int, modification : (Task) -> Unit){
        modification(tasks[index])
        notifySubscribers(MODIFY)
    }

    fun modifyTask(task: Task, modification : (Task) -> Unit){
        modification(task)
        notifySubscribers(MODIFY)
    }
}

enum class EventType {
    ADD,
    REMOVE,
    MODIFY,
    ALL
}