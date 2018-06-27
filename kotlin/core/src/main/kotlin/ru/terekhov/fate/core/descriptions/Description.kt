package ru.terekhov.fate.core.descriptions

import ru.terekhov.fate.core.actions.BaseAction

data class Description(
        val description: String,
        val actions: List<BaseAction>
)