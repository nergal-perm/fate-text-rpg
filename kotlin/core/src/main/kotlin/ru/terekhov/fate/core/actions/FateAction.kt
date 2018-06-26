package ru.terekhov.fate.core.actions

import ru.terekhov.fate.core.Game

interface FateAction {
    fun act(game: Game)
}