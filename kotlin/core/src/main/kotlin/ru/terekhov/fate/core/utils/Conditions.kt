package ru.terekhov.fate.core.utils

import ru.terekhov.fate.core.states.GameStateRepository

class ConditionEvaluator(private val gameState: GameStateRepository) {
    var negative: Boolean = false
    var argCount: Int = 0


    /*
        Condition pattern is: [!](world|player|npc|location|limbo|game).FIELD_NAME (has|eq|gt|lt) SOME_VALUE"
        should always have 4 parts in it
    */

    fun eval(condition: String?): ConditionResult {
        if (condition == null) return ConditionResult("", true)
        val conditionParts: List<String> = condition.replace("!", "").split(".", " ")
        negative = condition.startsWith("!")

        argCount = conditionParts.size
        // TODO: validate conditionParts.size somehow?

        val actual = when(conditionParts[0]) {
            "game" -> gameState.getValue(conditionParts[1])
            else -> "false"
        }

        val result = when(conditionParts[2]) {
            "eq" -> actual == conditionParts[3]
            "gt" -> actual.toDouble() > conditionParts[3].toDouble()
            "lt" -> actual.toDouble() < conditionParts[3].toDouble()
            else -> false
        }

        return ConditionResult(condition, if (negative) !result else result)
    }
}

data class ConditionResult(
        val condition: String,
        val result: Boolean
)

class OutcomeHandler(private val gameState: GameStateRepository) {
    fun eval(outcomeExpr: String) {
        val outcomes = outcomeExpr.split(" and ")
        for (outcome in outcomes) {
            val outcomeParts = outcome.replace(" ", "").split(".","=")
            when (outcomeParts[0]) {
                "game" -> gameState.setValue(outcomeParts[1], outcomeParts[2])
                else -> {
                    // do nothing yet
                }
            }
        }
    }
}