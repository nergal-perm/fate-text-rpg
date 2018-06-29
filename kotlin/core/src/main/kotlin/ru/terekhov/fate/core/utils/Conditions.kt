package ru.terekhov.fate.core.utils

class ConditionEvaluator(val condition:String) {
    /*
        Condition pattern is: [!](world|player|npc|location|limbo|game).FIELD_NAME (has|eq|gt|lt) SOME_VALUE"
     */

    fun eval() {
        var splitCondition = condition.split(" ")

    }
}