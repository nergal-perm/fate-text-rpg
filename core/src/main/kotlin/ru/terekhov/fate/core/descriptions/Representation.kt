package ru.terekhov.fate.core.descriptions

import ru.terekhov.fate.core.model.ActionModel
import java.util.*

data class Representation(
        val description: String,
        val actions: Array<ActionModel>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Representation

        if (description != other.description) return false
        if (!Arrays.equals(actions, other.actions)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = description.hashCode()
        result = 31 * result + Arrays.hashCode(actions)
        return result
    }
}