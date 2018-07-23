package ru.terekhov.fate.core.descriptions

import ru.terekhov.fate.core.model.ActionModel

data class Representation(
        val description: String,
        val actions: Array<ActionModel>
)