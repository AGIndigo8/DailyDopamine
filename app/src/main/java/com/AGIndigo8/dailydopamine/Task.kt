package com.AGIndigo8.dailydopamine

import java.io.Serializable

class Task: Serializable {
    var name: String = ""
    var description: String = ""
    var minumumRequirement: String = ""
    var todaysPoints: Int = 0
    var totalPoints: Int = 0
}