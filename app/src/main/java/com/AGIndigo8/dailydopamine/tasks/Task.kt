package com.AGIndigo8.dailydopamine.tasks

import java.io.Serializable

class Task: Serializable {
    private val maxDailyPoints = 20
    private val dailyPointNonZeroMin = 10
    private val INCREMENT = 1
    private val DECREMENT = -1

    var name: String = ""
    var description: String = ""
    var minumumRequirement: String = ""
    var todaysPoints: Int = 0
    var totalPoints: Int = 0

    fun isMaxPoints(): Boolean {
        return todaysPoints == maxDailyPoints
    }

    fun isMinPoints(): Boolean {
        return todaysPoints == 0
    }

    fun incrementPoints() {
        if (! isMaxPoints()) {
            shiftPoints(INCREMENT)
        }
    }

    fun decrementPoints() {
        if (! isMinPoints()) {
            shiftPoints(DECREMENT)
        }
    }

    private fun shiftPoints(direction: Int) {
        var deltaPoints = 0

        val bigJumpNeeded : Boolean = when{
            direction == INCREMENT -> isMinPoints()
            direction == DECREMENT -> todaysPoints == dailyPointNonZeroMin
            else -> false
        }

        if (bigJumpNeeded) {
            deltaPoints = dailyPointNonZeroMin * direction
        } else {
            deltaPoints = direction
        }

        todaysPoints += deltaPoints
        totalPoints += deltaPoints
    }
    
}