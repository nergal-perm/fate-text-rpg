package ru.terekhov.fate.core.model

import java.util.*

data class ConditionalDescription (
        val condition: String,
        val description: String
)

data class LocationModel(
        val id: String,
        val description: String,
        val conditionalDescription: Array<ConditionalDescription>?,
        val actions: Array<ActionModel>

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LocationModel

        if (id != other.id) return false
        if (description != other.description) return false
        if (!Arrays.equals(conditionalDescription, other.conditionalDescription)) return false
        if (!Arrays.equals(actions, other.actions)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + Arrays.hashCode(conditionalDescription)
        result = 31 * result + Arrays.hashCode(actions)
        return result
    }
}

enum class ActionType {
    MOVE, INVOKE, OVERCOME, ATTACK, DEFEND, ADVANTAGE, SEARCH
}

data class ActionModel(
        val id: String,
        val type: ActionType,
        val description: String,
        val condition: String?,
        val diceOutcome: DiceOutcome?,
        val outcome: GenericOutcome?
)

data class DiceOutcome (
        val opposition: Opposition,
        val failure: GenericOutcome,
        val tie: GenericOutcome,
        val success: GenericOutcome,
        val style: GenericOutcome
)

data class Opposition (
        val skill: String,
        val level: Number
)

data class GenericOutcome (
        val description: String?,
        val outcome: String
)