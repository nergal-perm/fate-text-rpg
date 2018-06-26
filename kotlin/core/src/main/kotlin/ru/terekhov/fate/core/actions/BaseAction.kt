package ru.terekhov.fate.core.actions

abstract class BaseAction: FateAction {
    abstract val type: ActionType
    abstract val description: String
    abstract val callToAction: String
}