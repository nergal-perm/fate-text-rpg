package ru.terekhov.fate.core.actions

import ru.terekhov.fate.core.Game

interface FateAction {
    fun act(game: Game): List<String>
    fun validate(game: Game): Boolean
}

abstract class BaseAction: FateAction {
    abstract val id: String
    abstract val type: ActionType
    abstract val description: String
    abstract val callToAction: String
}