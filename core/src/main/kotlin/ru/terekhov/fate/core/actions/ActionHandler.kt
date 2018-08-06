package ru.terekhov.fate.core.actions

import ru.terekhov.fate.core.descriptions.Representation

interface ActionHandler {
    fun setListener(listener: ActionResultListener)
    fun startGame()
    fun handleAction(actionId: String)
}

interface ActionResultListener {
    fun showDescription(desc: Representation)
}