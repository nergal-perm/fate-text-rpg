package ru.terekhov.fate.core.actions

interface ActionHandler {
    fun handleAction(action: UserAction)
}