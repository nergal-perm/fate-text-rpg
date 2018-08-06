package ru.terekhov.fate.core.locations

import ru.terekhov.fate.core.actions.BaseAction

data class Location(
        val id: Int,
        val description: String,
        val actions: List<BaseAction>)