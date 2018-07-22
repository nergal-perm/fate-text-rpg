package ru.terekhov.fate.core.descriptions

import ru.terekhov.fate.core.actions.BaseAction

data class Representation(
        val description: String,
        val actions: List<BaseAction>
)