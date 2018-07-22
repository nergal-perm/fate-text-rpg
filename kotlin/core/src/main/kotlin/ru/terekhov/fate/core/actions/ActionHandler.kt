package ru.terekhov.fate.core.actions

import ru.terekhov.fate.core.descriptions.Representation

interface ActionHandler {
    fun handleAction(action: BaseAction)
    fun setListener(listener: ActionResultListener)
    fun startGame()
}

interface ActionResultListener {
    fun showDescription(desc: Representation)
}